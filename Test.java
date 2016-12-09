import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
	private void readFile(File file) throws FileNotFoundException, IOException{
       String packageName = "";
       String functionName = "";
       List<String> params = new ArrayList<>();
       List<String> inout = new ArrayList<>();
       List<String> dataType = new ArrayList<>();
       
       BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
       String line = null;
       String tokens[] = new String[]{};
       while( (line = br.readLine())!= null ){
    	    tokens = combine(tokens, line.split("\\s+"));
       }
       br.close();
       List<String> fileData =  Arrays.asList(tokens);
       packageName = fileData.get(fileData.indexOf("PACKAGE")+2);
       functionName = fileData.get(fileData.indexOf("FUNCTION")+1);
       if(functionName.endsWith("(")){
    	   functionName=functionName.substring(0, functionName.length()-1);
       }
       int endsAt = fileData.indexOf("RETURN");
       fileData.remove(null);
       for(int i=fileData.indexOf("FUNCTION")+2;i<endsAt;i=i+2){
    	   params.add(fileData.get(i).trim());
    	   inout.add(fileData.get(i+1).trim());
    	   dataType.add(fileData.get(i+2).trim());
       }
       for(String files:fileData){
    	   System.out.println(files);
       }
       for(int o =0;o<params.size();o++){
    	   System.out.println(params.get(o)+"\t\t\t\t\t\t\t\t|"+inout.get(0)+"\t\t\t\t\t\t\t\t|"+dataType.get(o));
       }
    }
	
	
	
	public static String[] combine(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
	
	public static void main(String[] args) {
		try {
			new Test().readFile(new File("C:\\Users\\sarvesh.tank\\Desktop\\xxorc_create_pa_request_pkg.pkb"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
