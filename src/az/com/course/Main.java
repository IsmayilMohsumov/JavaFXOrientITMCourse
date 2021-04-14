package az.com.course;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/index.fxml"));
        primaryStage.setTitle("Hello to Register ");
        //icons qoymaq ucun : (Ve resources de de icons directory yaratdiq)
        primaryStage.getIcons().add(new Image("/icons/pngUFM.jpeg"));
        //sadece olcuye root verdikde FRAME in olcusune gore ozu deyerleri verir;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

//CTRL+AlT+SHIFT+S yeni ProjectStructure ye girib orada artifactsa elave ede absib main classini gosteririk
//Daha sonra BUILD menusunda Build Artifactsa basib Build deyirik ve basiriq
//Artiq artifact papkamizinicinde JAR file i hazirdir
//Launch4j de fieldleri doldururuq ve jre hissesinde jre ni tanitdirirq/
//daha sonra build wrapper edirik DUMP adi veririk istediyimiz vaxt dump ile exe ikinci defe istifade edib exe cixarda bilerik.

