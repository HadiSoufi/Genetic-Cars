package com.mycompany.jracerevolution;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.dynamics.Body;
import static javafx.application.Application.launch;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {        
        stage.setTitle("Bouncy Ball");
        stage.setFullScreen(false);
        stage.setResizable(false);
        Group root = new Group(); //Create a group for holding all objects on the screen
        Scene scene = new Scene(root, Utils.WIDTH, Utils.HEIGHT);
        
        //create ball   
        final Car car = new Car(45, 90);
         
        //Add ground to the application, this is where balls will land
        Utils.addGround(100, 10);
        
        //Add left and right walls so balls will not move outside the viewing area.
        Utils.addWall(0,100,1,100); //Left wall
        Utils.addWall(99,100,1,100); //Right wall
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(1.0/60.0); // Set duration for frame.
        
        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position 
        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                        //Create time step. Set Iteration count 8 for velocity and 3 for positions
                        Utils.world.step(1.0f/60.f, 8, 3); 
                       
                        //Move balls to the new position computed by JBox2D
                        Body body = (Body)car.node.getUserData();
                        float xpos = Utils.toPixelPosX(body.getPosition().x);
                        float ypos = Utils.toPixelPosY(body.getPosition().y);
                        car.node.setLayoutX(xpos);
                        car.node.setLayoutY(ypos);
           }
        };

                
         /**
         * Set ActionEvent and duration to the KeyFrame. 
         * The ActionEvent is triggered when KeyFrame execution is over. 
         */
        KeyFrame frame = new KeyFrame(duration, ae, null,null);

        timeline.getKeyFrames().add(frame);
        
        //Create button to start simulation.
        final Button btn = new Button();
        btn.setLayoutX((Utils.WIDTH/2) -15);
        btn.setLayoutY((Utils.HEIGHT-30));
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                        timeline.playFromStart(); 
                        btn.setVisible(false);
            }
        });
        root.getChildren().add(btn);
        root.getChildren().add(ball.node);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
