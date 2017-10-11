import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class Axon7Tool extends Application{
    private enum PARTITIONS {
        BOOT, GPT, RECOVERY, ALL
    }
    private enum ACTION {
        BACKUP, FLASH
    }

    private FlowPane root;
    @Override
    public void start(Stage stage) throws Exception {
        root = new FlowPane();
        root.setPadding(new Insets(4));
        root.setVgap(20);
        root.setHgap(20);
        root.setAlignment(Pos.CENTER);
        HBox.setHgrow(root, Priority.ALWAYS);
        addBackupButton();
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Axon7 Tool (EDL MANAGER)");
        stage.setResizable(false);
        stage.show();
    }

    private void addBackupButton() {
        Button backup = new Button("Backup ALL Images");
        backup.setOnAction(actionEvent -> managePartitions(ACTION.BACKUP, PARTITIONS.ALL));
        root.getChildren().add(backup);
        addBackupBoot();
    }
    private void addBackupBoot(){
        Button boot = new Button("Backup Boot");
        boot.setOnAction(actionEvent -> managePartitions(ACTION.BACKUP, PARTITIONS.BOOT));
        root.getChildren().add(boot);
        addBackupGPT();
    }
    private void addBackupGPT(){
        Button gpt = new Button ("Backup GPT");
        gpt.setOnAction(actionEvent -> managePartitions(ACTION.BACKUP, PARTITIONS.GPT));
        root.getChildren().add(gpt);
        addBackupRecovery();

    }
    private void addBackupRecovery(){
        Button recovery_Stock = new Button("Backup Current Recovery");
        recovery_Stock.setOnAction(actionEvent -> managePartitions(ACTION.BACKUP, PARTITIONS.RECOVERY));
        root.getChildren().add(recovery_Stock);
        addFlashAll();
    }
    private void addFlashAll(){
        Button flash_All = new Button("Flash All Patitions");
        flash_All.setOnAction(actionEvent -> managePartitions(ACTION.FLASH, PARTITIONS.ALL));
        root.getChildren().add(flash_All);
        addflashBoot();

    }
    private void addflashBoot(){
        Button flash_Boot = new Button("Flash Boot");
        flash_Boot.setOnAction(actionEvent -> managePartitions(ACTION.FLASH, PARTITIONS.BOOT));
        root.getChildren().add(flash_Boot);
        addFlashGPT();
    }
    private void addFlashGPT() {
        Button flash_GPT = new Button("Flash GPT");
        flash_GPT.setOnAction(actionEvent -> managePartitions(ACTION.FLASH, PARTITIONS.GPT));
        root.getChildren().add(flash_GPT);
        addFlashRecovery();
    }
    private void addFlashRecovery(){
        Button flash_Recovery = new Button("Flash Recovery");
        flash_Recovery.setOnAction(actionEvent -> managePartitions(ACTION.FLASH, PARTITIONS.RECOVERY));
        root.getChildren().add(flash_Recovery);
    }

    private void managePartitions(ACTION action, PARTITIONS partitions) {

        ProcessBuilder processBuilder;
        String[] boot_to_edl = {"/usr/bin/bash","-C","adb reboot edl", "&&"};
        String[] backup = {"/usr/local/bin/axon7tool","-r"};
        String[] flash = {"axon7tool", "-w"};

        if(action == ACTION.BACKUP){
            if(partitions == PARTITIONS.ALL){
                processBuilder = new ProcessBuilder(boot_to_edl[0], boot_to_edl[1],
                        boot_to_edl[2], boot_to_edl[3], backup[0], backup[1], "");
            }
        }

    }
}
