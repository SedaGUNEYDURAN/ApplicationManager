package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static final String KILL = "taskkill/ IM";
    Stage window;
    TableView<Product> table;
    TextField idInput, dirInput, exeInput, argInput, runInput;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Product prod=new Product();
        window = primaryStage;
        window.setTitle("Application Manager");
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");

        TableColumn<Product, Integer> idColumn = new TableColumn<>("İd");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> dirColumn = new TableColumn<>("Directory");
        dirColumn.setMinWidth(200);
        dirColumn.setCellValueFactory(new PropertyValueFactory<>("directory"));


        TableColumn<Product, Integer> exeColumn = new TableColumn<>("Executable");
        exeColumn.setMinWidth(200);
        exeColumn.setCellValueFactory(new PropertyValueFactory<>("executable"));

        TableColumn<Product, Integer> runColumn = new TableColumn<>("Runnıng Duratıon");
        runColumn.setMinWidth(200);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("running"));





        dirInput = new TextField();
        dirInput.setPromptText("Directory");
        dirInput.setMinWidth(100);

        exeInput = new TextField();
        exeInput.setPromptText("Executable");

        argInput=new TextField();
        argInput.setPromptText("Arguman");



        Button addButton = new Button("Start Process");
        addButton.setOnAction(e -> {
            try {
                String a=addButtonClicked();
                prod.setExecutable(a);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button killButton = new Button("Kill");


        killButton.setOnAction(e-> {

                killButtonClick(prod.getExecutable());

        });




        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll( dirInput, exeInput,argInput, addButton,killButton);


        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(idColumn, dirColumn, exeColumn, runColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();


        // primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }



    public String addButtonClicked() throws IOException {
        Product product = new Product();

        product.setDirectory(dirInput.getText());
        product.setExecutable(argInput.getText());

        String firefox = product.getDirectory();

        Map harıta= System.getenv();//belırtılen ortam değışkenının değerını aldık, pathını

        for(Object key:harıta.keySet()) {

            String deger = (String) harıta.get(key);
            if (deger.contains(argInput.getText()))
            {

                firefox = deger;

                break;
            }
        }

        Runtime.getRuntime().exec(new String[]{firefox, argInput.getText()});

        Instant start = Instant.now();
        double startTime = System.nanoTime();
        double tıme1=startTime/1000000000;
        int tıme=(int)tıme1/1000;
        System.out.println("zaman:"+tıme);

        String vmName= ManagementFactory.getRuntimeMXBean().getName();
        int p=vmName.indexOf("@");
        String pıd=vmName.substring(0,p);
        System.out.println("pıd:"+pıd);
        TextField idInput=new TextField(pıd);
        product.setId(Integer.parseInt(idInput.getText()));


        TextField runInput=new TextField(String.valueOf(tıme));
        product.setRunning(Double.parseDouble(runInput.getText()));
        table.getItems().add(product);
 /* while (tıme<1000){
          methodToTıme();

          tıme=tıme+1;
          runInput=new TextField(String.valueOf(tıme));

          product.setRunning(Double.parseDouble(runInput.getText()));


          table.refresh();

      }*/
        dirInput.clear();
        exeInput.clear();
        argInput.clear();
        return exeInput.getText();
    }




    public void killButtonClick(String servıceName) {

        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        try {
            Runtime.getRuntime().exec(KILL+servıceName);
            System.out.println("killed successfully!");
        } catch (IOException e) {
            System.out.println("Not able to find the process" + servıceName);
            e.printStackTrace();
        }
        productSelected.forEach(allProducts::remove);
   /*    long endTime = System.nanoTime();
          long duration = ((endTime - startTime) / 1000000000);
          TextField runInput=new TextField();
          pro1.setRunning(Double.parseDouble(runInput.getText()));
         table.getItems().add(pro1);0/
   */
    }


        public ObservableList<Product> getProduct () {
            ObservableList<Product> products = FXCollections.observableArrayList();
           // products.add(new Product(123, "Seda", "Güney", 1213)); //koddan tabloya verı ekleme
            return products;

        }


        public static void main (String[]args){
            launch(args);


        }
        private static void methodToTıme () {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

}
