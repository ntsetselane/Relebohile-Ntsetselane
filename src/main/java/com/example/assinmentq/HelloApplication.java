package com.example.assinmentq;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane borderPane = new BorderPane();
        Slider progressBar = new Slider();
        VBox vBox = new VBox();
        Slider volume = new Slider();
        volume.setMaxHeight(0);
        volume.setMaxWidth(100);

        String style = getClass().getResource("/style.css").toExternalForm();

        String src = getClass().getResource("/cgi_animated_short_hd_runaway_by_susan_yung_emily_buchanan_and_esther_parobek_h264_69995.mp4").toExternalForm();
        Media media = new Media(src);
        MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView();
        mediaView.setFitHeight(750);
       // mediaView.setFitWidth(250);
        mediaView.setMediaPlayer(player);

        volume.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                volume.setValue(player.getVolume()*200);
                volume.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        player.setVolume(volume.getValue()/100);

                    }
                });

            }
        });

         //Slider progressBar = new Slider();


      /*  player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                progressBar.setValue(newValue.toSeconds());
            }
        });
       */
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {

             //   progressBar.setValue(new);
            }
        });
        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total=media.getDuration();
                progressBar.setMax(total.toSeconds());
            }
        });

        ImageView play = new ImageView("/play.png");
        play.setFitHeight(30);
        play.setFitWidth(30);
        ImageView stop = new ImageView("/stop-button.png");
        stop.setFitHeight(30);
        stop.setFitWidth(30);
        ImageView left = new ImageView("/left.png");
        left.setFitHeight(30);
        left.setFitWidth(30);
        ImageView right = new ImageView("/right.png");
        right.setFitHeight(30);
        right.setFitWidth(30);
        ImageView pause = new ImageView("/pause-button.png");
        pause.setFitHeight(30);
        pause.setFitWidth(30);
        ImageView exit = new ImageView("/exit.png");
        exit.setFitHeight(30);
        exit.setFitWidth(30);
        exit.setId("exit");
        HBox hBox = new HBox(20,volume, play, left, pause, right, stop,exit);
        hBox.setId("player-controls");

        play.setOnMouseClicked(event -> {
            player.play();
        });
        stop.setOnMouseClicked(event -> {
            player.stop();
        });

        exit.setOnMouseClicked(event -> {
            Platform.exit();
        });
        pause.setOnMouseClicked(event -> {
            player.pause();
        });

//        vBox.getChildren().addAll(hBox, media);


        Scene scene = new Scene(borderPane , 600, 500);
        borderPane.setCenter(mediaView);
        borderPane.setBottom(hBox);
        scene.getStylesheets().add(style);
        stage.setTitle("Cgi Player");
        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}