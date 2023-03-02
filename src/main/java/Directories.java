
import com.mycompany.flowmanagementsystem.flights;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sbilau
 */
public class Directories {
    static String pathAuto = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/";
//    String pathAuto = "/var/www/html/flowcontrol/public/run/Internal_lists/auto/"; 

    static File config = new File("C:/wamp64/www/flowcontrol/public/run/configs/config.xml");
//    File config = new File("/var/www/html/flowcontrol/public/run/configs/config.xml");

    static File InternalAutoListXML = new File("C:/wamp64/www/flowcontrol/public/run/Internal_list/internal_auto_list.xml");
//    static File InternalAutoListXM = new File("/var/www/html/flowcontrol/public/run/Internal_list/internal_auto_list.xml");

    static File fileInternalManualFilePath = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");
//    static File fileInternalManualFilePath = new File("/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");

    static Path star2 = Paths.get("C:/wamp64/www/flowcontrol/public/run/configs/star2.xml");
//    static Path star2 = Paths.get("/var/www/html/flowcontrol/public/run/configs/star2.xml");

    static File[] fileStream = flights.getXMLFiles(new File("C:/wamp64/www/flowcontrol/public/run/stream"));
//      static File[] fileStream = flights.getXMLFiles(new File("/var/www/html/flowcontrol/public/run/stream"));   

    static File fileStreamPath = new File("C:/wamp64/www/flowcontrol/public/run/stream");
//    static File fileStreamPath = new File("/var/www/html/flowcontrol/public/run/stream");

    static Path pathStream = Paths.get("C:/wamp64/www/flowcontrol/public/run/stream");
//  static    Path pathStream = Paths.get("/var/www/html/flowcontrol/public/run/stream");

    static String stringPathInternalAutoList = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";
//    static String  filePathInternalAutoList = "/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";;

    static String stringPathInternalManualList = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
//    static String  stringPathInternalManualList = "/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";

    static File filePathInternalAutoList = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//    static File filePathInternalAutoList = new File("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");

    static File filePathManualList = new File("C:/wamp64/www/flowcontrol/public/run/configs/manual.xml");
//    static File filePathManualList= new File("/var/www/html/flowcontrol/public/run/configs/manual.xml");

    static File xsltFile = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml");
//    static File xsltFile =  new File("/var/www/html/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml");    

    static File configFilePath = new File("C:/wamp64/www/flowcontrol/run/configs/config.xml");
//    static File configFilePath = new File("/var/www/html/flowcontrol/public/run/configs/config.xml");

    static File fileInternalReserveListPath = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/internal_reserve_list.xml");
//    static File reserveListPath new File("/var/www/html/flowcontrol/run/Internal_lists/reserve/internal_reserve_list.xml");

    static String internalManualStringPath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
//    static String manualStringPath = "/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";

    static File fileReserveListPath = new File("C:/wamp64/www/flowcontrol/public/run/configs/reserve.xml");
//    static File fileReserveListPath = new FileInputStream(new File("/var/www/html/flowcontrol/public/run/configs/reserve.xml"));

    static String stringInternalReserveListPath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml";
//    static String stringInternalReserveListPath = "/var/www/html/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml";

    static File fileStarPath = new File("C:/wamp64/www/flowcontrol/public/run/configs/star2.xml");
//    static File fileStarPath = new File("/var/www/html/flowcontrol/public/run/configs/star2.xml");

    static File fileSequenceListPath = new File("C:/wamp64/www/flowcontrol/public/run/data/sequence_list.xml");
//    static File fileSequenceListPath = new File("/var/www/html/flowcontrol/public/run/data/sequence_list.xml");

    static String stringRun = "C:/wamp64/www/flowcontrol/public/run";
//    static String stringRun = "/var/www/html/flowcontrol/public/run";
    
}
