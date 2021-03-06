package controller.utilityController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexController {

    public boolean registerRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("user create (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) ([\\w- ]+) (\\S+)").matcher(command);
        if (matcher.find()) {
            return isRegisterValid(matcher, info);
        } else return false;
    }

    private static boolean isRegisterValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        inputParameters.add(matcher.group(5));
        inputParameters.add(matcher.group(7));
        expectedParameters.add("--username");
        expectedParameters.add("--password1");
        expectedParameters.add("--password2");
        expectedParameters.add("--email Address");
        if (inputParameters.containsAll(expectedParameters)) {
            info.put("username", matcher.group(2));
            info.put("password1", matcher.group(4));
            info.put("password2", matcher.group(6));
            info.put("email", matcher.group(8));
            return true;
        } else return false;
    }

    public boolean enterMenuRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("menu enter (.+)").matcher(command);
        if (matcher.find()){
            info.put("menuName",matcher.group(1));
            return true;
        }
        else return false;
    }

    public boolean changePassRegex(HashMap<String, String> info, String command) {
        Matcher matcher = Pattern.compile("Profile --change (\\S+) (\\S+) (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isChangePassFormatValid(info,matcher);
        }
        else return false;
    }

    private static boolean isChangePassFormatValid(HashMap<String, String> info, Matcher matcher) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        expectedParameters.add("--oldPassword");
        expectedParameters.add("--newPassword");
        if (expectedParameters.containsAll(inputParameters)){
            info.put("oldPass", matcher.group(2));
            info.put("newPass", matcher.group(4));
            return true;
        }
        else return false;
    }

    public boolean changeUsernameRegex(HashMap<String, String> info, String command) {
        Matcher matcher = Pattern.compile("Profile --change (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isChangeUsernameValid(info,matcher);
        }
        return false;
    }

    private static boolean isChangeUsernameValid(HashMap<String, String> info, Matcher matcher) {
        ArrayList<String>expectedParameters = new ArrayList<>();
        ArrayList<String>inputParameters = new ArrayList<>();
        expectedParameters.add("--username");
        inputParameters.add(matcher.group(1));
        if (expectedParameters.containsAll(inputParameters)){
            info.put("username",matcher.group(2));
            return true;
        }
        return false;
    }

    public boolean addUserRegex(String command, HashMap<String, ArrayList<String>> info) {
        Matcher matcher = Pattern.compile("edit --task (\\S+) (\\S+) (\\S+) (.+) --add").matcher(command);
        if (matcher.find()){
            return isAddUsersCommandValid(matcher,info);
        }
        return false;
    }

    private static boolean isAddUsersCommandValid(Matcher matcher, HashMap<String, ArrayList<String>> info) {
        ArrayList<String>expectedParameters = new ArrayList<>();
        ArrayList<String>inputParameters = new ArrayList<>();
        expectedParameters.add("--id");
        expectedParameters.add("--assignedUsers");
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        if (expectedParameters.containsAll(inputParameters)){
            ArrayList<String>id = new ArrayList<>();
            id.add(matcher.group(2));
            info.put("id",id);
            info.put("users",getUsersFromString(matcher.group(4)));
            return true;
        }
        return false;
    }

    private static ArrayList<String> getUsersFromString(String usernames) {
        String[] strings = usernames.split(",");
        return new ArrayList<>(Arrays.asList(strings));
    }

    public static boolean removeUserRegex(String command, HashMap<String, ArrayList<String>> info) {
        Matcher matcher = Pattern.compile("edit --task (\\S+) (\\S+) (\\S+) (.+) --remove").matcher(command);
        if (matcher.find()){
            return isRemoveUsersCommandValid(matcher,info);
        }
        return false;
    }

    private static boolean isRemoveUsersCommandValid(Matcher matcher, HashMap<String, ArrayList<String>> info) {
        ArrayList<String>expectedParameters = new ArrayList<>();
        ArrayList<String>inputParameters = new ArrayList<>();
        expectedParameters.add("--id");
        expectedParameters.add("--assignedUsers");
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        if (expectedParameters.containsAll(inputParameters)){
            ArrayList<String>id = new ArrayList<>();
            id.add(matcher.group(2));
            info.put("id",id);
            info.put("users",getUsersFromString(matcher.group(4)));
            return true;
        }
        return false;
    }

    public static boolean deadlineRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("edit --task (\\S+) (\\S+) (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isDeadlineValidCommandValid(matcher, info);
        }
        return false;
    }

    private static boolean isDeadlineValidCommandValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String>expectedParameters = new ArrayList<>();
        ArrayList<String>inputParameters = new ArrayList<>();
        expectedParameters.add("--id");
        expectedParameters.add("--deadline");
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        if (expectedParameters.containsAll(inputParameters)){
            info.put("id",matcher.group(2));
            info.put("deadline", matcher.group(4));
            return true;
        }
        return false;
    }

    public static boolean priorityRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("edit --task (\\S+) (\\S+) (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isPriorityValid(matcher,info);
        }
        return false;
    }

    private static boolean isPriorityValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String>expectedParameters = new ArrayList<>();
        ArrayList<String>inputParameters = new ArrayList<>();
        expectedParameters.add("--id");
        expectedParameters.add("--priority");
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        if (expectedParameters.containsAll(inputParameters)){
            info.put("id",matcher.group(2));
            info.put("priority", matcher.group(4));
            return true;
        }
        return false;
    }

    public static boolean titleRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("edit --task (\\d+) (\\S+) (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isTitleValid(matcher,info);
        }
        return false;
    }

    private static boolean isTitleValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        expectedParameters.add("--title");
        expectedParameters.add("--id");
        if (expectedParameters.containsAll(inputParameters)){
            info.put("id",matcher.group(2));
            info.put("title",matcher.group(4));
            return true;
        }
        return false;
    }

    public static boolean descriptionRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("edit --task (\\S+) (\\S+) (\\S+) (.+)").matcher(command);
        if (matcher.find()){
            return isDescriptionValid(matcher,info);
        }
        return false;
    }

    private static boolean isDescriptionValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        expectedParameters.add("--id");
        expectedParameters.add("--descriptions");
        if (expectedParameters.containsAll(inputParameters)){
            info.put("id",matcher.group(2));
            info.put("description",matcher.group(4));
            return true;
        }
        return false;
    }


    public boolean loginRegex(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("user login (\\S+) (\\S+) (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isLoginValid(matcher,info);
        }
        else return false;

    }

    private boolean isLoginValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        inputParameters.add(matcher.group(3));
        expectedParameters.add("--username");
        expectedParameters.add("--password");
        if (expectedParameters.containsAll(inputParameters)){
            info.put("username",matcher.group(2));
            info.put("password",matcher.group(4));
            return true;
        }
        return false;
    }


    public static boolean isPasswordValid(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
        char[] charArray = password.toCharArray();
        for (char i : charArray) {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }
        return hasDigit && hasLower && hasUpper && specialChar && (n >= 8) && (n<= 32);
    }

    public static boolean isEmailValid(String email) {
        Matcher matcher = Pattern.compile("[A-Za-z0-9+_.-]+@(.+)").matcher(email);
        return matcher.find();
    }


    public boolean teamEnterRegex(HashMap<String, String> info, String command) {
        Matcher matcher = Pattern.compile("Enter (\\S+) (\\S+)").matcher(command);
        if (matcher.find()){
            return isEnterValid(matcher,info);
        }
        else return false;

    }

    private boolean isEnterValid(Matcher matcher, HashMap<String, String> info) {
        ArrayList<String> expectedParameters = new ArrayList<>();
        ArrayList<String> inputParameters = new ArrayList<>();
        inputParameters.add(matcher.group(1));
        expectedParameters.add("team");
        if (expectedParameters.containsAll(inputParameters)){
            info.put("teamName",matcher.group(2));
            return true;
        }
        return false;

    }

    public boolean enterSubMenuMatcher(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("(\\w+) --show").matcher(command);
        if (matcher.find()){
            info.put("menuName",matcher.group(1));
            return true;
        }
        else return false;
    }

    public boolean showTaskByID(String command, HashMap<String, String> info) {
        Matcher matcher = Pattern.compile("").matcher(command);
        if (matcher.find()){
            info.put("id", matcher.group(1));
            return true;
        }
        return false;
    }
}

