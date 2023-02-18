import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    private String _fileName;
    private final int DEFAULT_SCORE_VALUE = 1;
    private final float DEFAULT_AWARD = 0.05F, DEFAULT_PENALTY = 0.03F;
    private File _file;
    private String _scoreWin = "0", _scoreLoss = "0";

    public Data(String filename){
        this._fileName = filename;
        setFile();
        fileInitialProcess();
    }
    private void setFile() {
        this._file = new File(this._fileName);
    }
    private void createFile() throws IOException {
        if (!this._file.exists()){
            this._file.createNewFile();
        }
    }
    private void fileInitialProcess() {
        try {
            createFile();
            String fileContent = readFile();
            extractContent(fileContent);
        }
        catch (IOException e) {
            System.out.println("Error exception caught from user.");
        }
    }
    private String readFile() throws FileNotFoundException {
        Scanner scan = new Scanner(this._file);
        String fileContent = "";
        while(scan.hasNextLine()){
            fileContent = fileContent.concat(scan.nextLine());
        }
        return fileContent;
    }
    public String getName(){
        return this._fileName;
    }
    public String getWin(){
        return this._scoreWin;
    }
    public String getLoss(){
        return this._scoreLoss;
    }
    public void addWin(){
        int cur_score = Integer.parseInt(this._scoreWin);
        int new_score = cur_score + DEFAULT_SCORE_VALUE;
        this._scoreWin = String.valueOf(new_score);
    }
    public void addLoss(){
        int cur_score = Integer.parseInt(this._scoreLoss);
        int new_score = cur_score + DEFAULT_SCORE_VALUE;
        this._scoreLoss = String.valueOf(new_score);
    }
    public String totalEarning(){
        float win = Integer.parseInt(this._scoreWin) * DEFAULT_AWARD;
        float loss = Integer.parseInt(this._scoreLoss) * DEFAULT_PENALTY;
        float output = win - loss;
        return String.valueOf(output);
    }
    private void extractContent(String fileData){
        if (fileData.length() > 0) {
            String[] dataList = fileData.split("\\|");
            this._fileName = dataList[0];
            this._scoreWin = dataList[1];
            this._scoreLoss = dataList[2];
        }
    }
    private String stringFileContent(){
        return this._fileName + "|" + this._scoreWin + "|" + this._scoreLoss;
    }
    public void saveToFile() {
        String fileData = stringFileContent();
        try {
            FileWriter writer = new FileWriter(this._fileName);
            writer.write(fileData);
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error caught from user.");
        }
    }
}
