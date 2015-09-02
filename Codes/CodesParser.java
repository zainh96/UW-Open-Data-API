package Codes;

import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ZainH on 02/09/2015.
 *
 *  A /codes/units call back's JSON Object looks like
 *  {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *              "unit_code": (String)
 *              "group_code": (String)
 *              "unit_short_name": (String)
 *              "unit_full_name": (String)
 *          },
 *          ...
 *      ]
 *  }
 *
 *  A /codes/terms and /codes/instructions call back's JSON Object looks like
 *  {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *             "abbreviation": (String)
 *             "description": (String)
 *          },
 *          ...
 *      ]
 *  }
 *
 *  A /codes/groups call back's JSON Object looks like
 *  {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *              "group_code": (String)
 *              "group_short_name": (String)
 *              "group_full_name": (String)
 *          },
 *          ...
 *      ]
 *  }
 *
 *
 *  A /codes/groups call back's JSON Object looks like
 *  {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *             "subject": (String)
 *             "description": (String)
 *             "unit": (String)
 *          },
 *          ...
 *      ]
 *  }
 *
 *
 * Proper Use:
 * 1) call setParseType()
 * 2) call getEndPoint() and use it to build a URL with UWOpenDataAPI.buildURL(...)
 * 3) once an APIResult is received in onDownloadComplete(APIResult apiResult), call setAPIResult, then call parseJSON()
 * 4) the proper data is now parsed, and can be retrieved. (This depends on your parseType). Data can be requested through
 *      * getUnits()
 *      * getTerms()
 *      * getGroups()
 *      * getSubjects()
 *      * getInstructions()
 */
public class CodesParser extends UWParser {
    // end points
    private static final String UNITS_END_POINT= "codes/units";
    private static final String TERMS_END_POINT= "codes/terms";
    private static final String GROUPS_END_POINT= "codes/groups";
    private static final String SUBJECTS_END_POINT= "codes/subjects";
    private static final String INSTRUCTIONS_END_POINT= "codes/instructions";

    // JSON Object Leaf Node tags
    private static final String UNIT_CODE_TAG = "unit_code";
    private static final String GROUP_CODE_TAG = "group_code";
    private static final String UNIT_SHORT_NAME_TAG = "unit_short_name";
    private static final String UNIT_FULL_NAME_TAG = "unit_full_name";
    private static final String ABBREVIATION_TAG = "abbreviation";
    private static final String DESCRIPTION_TAG = "description";
    private static final String GROUP_SHORT_NAME_TAG = "group_short_name";
    private static final String GROUP_FULL_NAME_TAG = "group_full_name";
    private static final String SUBJECT_TAG = "subject";
    private static final String UNIT_TAG = "unit";

    // JSON Array Tags
    private static final String DATA_TAG = "data";

    // contains all JSON data
    private APIResult apiResult = null;

    // types of parsing
    public enum ParseType{
        UNITS,
        TERMS,
        GROUPS,
        SUBJECTS,
        INSTRUCTIONS
    }

    private ParseType parseType = ParseType.UNITS;

    // /codes/units variables
    private ArrayList<Unit> units = new ArrayList<>();

    // /codes/terms variables
    private ArrayList<Term> terms = new ArrayList<>();

    // /codes/groups variables
    private ArrayList<Group> groups = new ArrayList<>();

    // /codes/subject variables
    private ArrayList<Subject> subjects = new ArrayList<>();

    // /codes/instructions
    private ArrayList<Instruction> instructions = new ArrayList<>();

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;
        switch (parseType){
            case UNITS:
                parseUnitsJSON();
                break;
            case TERMS:
                parseTermsJSON();
                break;
            case GROUPS:
                parseGroupsJSON();
                break;
            case SUBJECTS:
                parseSubjectsJSON();
                break;
            case INSTRUCTIONS:
                parseInstructionsJSON();
                break;
        }
    }

    private void parseUnitsJSON(){
        try {
            JSONArray unitArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int unitArrayLength = unitArray.length();

            for(int i = 0; i < unitArrayLength; i++){
                JSONObject unitObject = unitArray.getJSONObject(i);
                Unit unit = new Unit();

                if(!unitObject.isNull(UNIT_CODE_TAG))
                    unit.setUnitCode(unitObject.getString(UNIT_CODE_TAG));

                if(!unitObject.isNull(GROUP_CODE_TAG))
                    unit.setGroupCode(unitObject.getString(GROUP_CODE_TAG));

                if(!unitObject.isNull(UNIT_SHORT_NAME_TAG))
                    unit.setUnitShortName(unitObject.getString(UNIT_SHORT_NAME_TAG));

                if(!unitObject.isNull(UNIT_FULL_NAME_TAG))
                    unit.setUnitFullName(unitObject.getString(UNIT_FULL_NAME_TAG));

                units.add(unit);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseTermsJSON(){
        try {
            JSONArray termArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int termArrayLength = termArray.length();

            for(int i = 0; i < termArrayLength; i++){
                JSONObject termObject = termArray.getJSONObject(i);
                Term term = new Term();

                if(!termObject.isNull(ABBREVIATION_TAG))
                    term.setAbbreviation(termObject.getString(ABBREVIATION_TAG));

                if(!termObject.isNull(DESCRIPTION_TAG))
                    term.setDescription(termObject.getString(DESCRIPTION_TAG));

                terms.add(term);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseGroupsJSON(){
        try {
            JSONArray groupArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int groupArrayLength = groupArray.length();

            for(int i = 0; i < groupArrayLength; i++){
                JSONObject groupObject = groupArray.getJSONObject(i);
                Group group = new Group();

                if(!groupObject.isNull(GROUP_CODE_TAG))
                    group.setGroupCode(groupObject.getString(GROUP_CODE_TAG));

                if(!groupObject.isNull(GROUP_SHORT_NAME_TAG))
                    group.setGroupShortName(groupObject.getString(GROUP_SHORT_NAME_TAG));

                if(!groupObject.isNull(GROUP_FULL_NAME_TAG))
                    group.setGroupFullName(groupObject.getString(GROUP_FULL_NAME_TAG));

                groups.add(group);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseSubjectsJSON(){
        try {
            JSONArray subjectArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int subjectArrayLength = subjectArray.length();

            for(int i = 0; i < subjectArrayLength; i++){
                JSONObject subjectObject = subjectArray.getJSONObject(i);
                Subject subject = new Subject();

                if(!subjectObject.isNull(SUBJECT_TAG))
                    subject.setSubject(subjectObject.getString(SUBJECT_TAG));

                if(!subjectObject.isNull(DESCRIPTION_TAG))
                    subject.setDescription(subjectObject.getString(DESCRIPTION_TAG));

                if(!subjectObject.isNull(UNIT_TAG))
                    subject.setUnit(subjectObject.getString(UNIT_TAG));

                subjects.add(subject);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseInstructionsJSON(){
        try {
            JSONArray instructionsArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int instructionsArrayLength = instructionsArray.length();

            for(int i = 0; i < instructionsArrayLength ; i++){
                JSONObject termObject = instructionsArray.getJSONObject(i);
                Instruction instruction = new Instruction();

                if(!termObject.isNull(ABBREVIATION_TAG))
                    instruction.setAcronym(termObject.getString(ABBREVIATION_TAG));

                if(!termObject.isNull(DESCRIPTION_TAG))
                    instruction.setDescription(termObject.getString(DESCRIPTION_TAG));

                instructions.add(instruction);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public void setAPIResult(APIResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public APIResult getAPIResult() {
        return apiResult;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    @Override
    public MetaData getMeta() {
        MetaDataParser parser = new MetaDataParser();
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        return parser.getMeta();
    }

    public String getEndPoint(){
        switch (parseType){
            case UNITS:
                return UNITS_END_POINT;
            case TERMS:
                return TERMS_END_POINT;
            case GROUPS:
                return GROUPS_END_POINT;
            case SUBJECTS:
                return SUBJECTS_END_POINT;
            case INSTRUCTIONS:
                return INSTRUCTIONS_END_POINT;
            default:
                return UNITS_END_POINT;
        }
    }
}
