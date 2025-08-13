interface Camera{
    void takePhoto();
    void recordVideo();
    void printSpecs();
}

interface Phone{
    void makeCall(String number);
    void sendSMS(String number, String message);
    void printSpecs();
}

class SmartPhone implements Camera,Phone{
    public void takePhoto(){
        System.out.println("Photo Taken");
    }
    public void recordVideo(){
        System.out.println("Video Taken");
    }
    public void printSpecs(){
        System.out.println("Specs displayed");
    }

    public void makeCall(String number){
        System.out.println("Call made to : "+number);
    }

    public void sendSMS(String number, String message){
        System.out.println("\"" +message+ "\" sent to "+number);
    }

    void browseInternet(){
        System.out.println("Browsing on the internet");
    }
} 


public class LabLec1_2 {
    public static void main(String[] args) {
        SmartPhone s=new SmartPhone();
        s.takePhoto();
        s.recordVideo();
        s.printSpecs();
        s.makeCall("8767304327");
        s.sendSMS("8767304327", "Hello World");
        s.browseInternet();
    }
}
