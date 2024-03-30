import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class FileManipulation {
    static int fileCount = getLineCount(new File("ClassData.txt"));
    static WeightedGraph.Graph myGraph = new WeightedGraph.Graph(fileCount);
    public static void readFile(File selectedFile){

        try { //scanner main code

            Scanner sc = new Scanner(selectedFile);

            while (sc.hasNextLine()) {
                //Start with destination
                String classData = sc.nextLine();
                int dest = Integer.parseInt(classData.substring(5, 9)); //Get Initial dest ID
                String remainder = classData.substring(9);
                int[] startIntArr = checkForInts(dest, remainder);
                //For the graph: The weight is the class number, the dest is the ID
                int startOne = startIntArr[0];
                addEdges(startOne, dest, 0);
                int startTwo = startIntArr[1];
                addEdges(startTwo, dest, 0);
                int startThree = startIntArr[2];
                addEdges(startThree, dest, 0);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static int[] checkForInts(int dest, String str){

        int[] arr = {0,0,0};

        if (str.contains("(")){ //This means there are pre-reqs

            String temp = cutAtString(str, "(", true); //Only look at the pre-reqs
            //Part 1: Replace "ands" (fix formatting)
            if (temp.contains("and")) {
                temp = temp.replace(" and", ",");
            }

            //Part 2: Get the first element
            arr[0] = Integer.parseInt(temp.substring(5,9));

            //Part 3: Get the second element
            if (temp.contains(",")){
                temp = cutAtString(temp, ",", true);
                if (temp.contains("3330")) { //Handling formatting exception, second element
                    arr[1] = 3330;
                    //ID goes up by one
                    return arr;
                }

                arr[1] = Integer.parseInt(temp.substring(6, 10)); //No formatting exception, second element

                //Third element:
                if (temp.contains(",")) {
                    temp = cutAtString(str, ",", true);
                    arr[2] = Integer.parseInt(temp.substring(temp.length()-5, temp.length()-1)); //Last item
                }
            }

        }
        return arr;
    }

    public static String cutAtString(String fullString, String toCut, boolean leftCut){
        if (leftCut){
            return fullString.substring(fullString.indexOf(toCut) + 1);
        }
        else {
            return fullString.substring(0, fullString.indexOf(toCut) + 1);
        }
    }
    public static String getFullName(int codeNumber){
        return "stuff";
    }

    public static int getLineCount(File file){
        try{
            long lines = 0;
            lines = Files.lines(file.toPath()).count();
            return (int) lines;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void addEdges(int start, int destination, int weight){
        if (start != 0){
            myGraph.addEdge(start, destination, weight);
        }
    }
}
