//package au.com.jiangren.games.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnakeAppPureJavaFx extends Application {

    private static final int GAME_SIZE = 60;
    private static final int TILE_SIZE = 10;

    private static final String copyRight = "Thanks for joining our Jiang Ren Project Course.\n Happy Coding! \n @2018 jiangren.com.au";

    private Game game = new Game(GAME_SIZE);




    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage theStage)
    {
        theStage.setTitle("Snake Game v1.0");

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        setupUserInput(theScene);

        Canvas canvas = new Canvas( GAME_SIZE * TILE_SIZE, GAME_SIZE * TILE_SIZE );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();


        new AnimationTimer()
        {
            private long lastUpdateSecs = 0 ;

            public void handle(long currentNanoTime) {

                if (currentNanoTime - lastUpdateSecs >= 100_000_000) {
                    game.update();

                    if (game.isOver()) {
                        showMessageBox("Game over", "Oops, your game is over!");
                        this.stop();
                    }

                    render(gc);
                    lastUpdateSecs = currentNanoTime ;
                }


            }
        }.start();



        theStage.show();
    }

    private void render(GraphicsContext g) {
        g.clearRect(0,0, TILE_SIZE * GAME_SIZE, TILE_SIZE * GAME_SIZE);

        g.setFill(Color.BLUE);
        game.getSnake().getBody().forEach(p -> {
            g.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });

        g.setFill(Color.RED);
        g.fillRect(game.getFood().getPosition().getX() * TILE_SIZE, game.getFood().getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void showMessageBox(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(copyRight);

        alert.setOnHidden(evt -> Platform.exit());

        alert.show();
    }

    private void setupUserInput(Scene theScene) {
        theScene.setOnKeyPressed( event -> {

            // System.out.println(event);

            switch (event.getCode()) {
                case UP:    game.setDirection(Direction.UP); break;
                case DOWN:  game.setDirection(Direction.DOWN); break;
                case LEFT:  game.setDirection(Direction.LEFT); break;
                case RIGHT: game.setDirection(Direction.RIGHT); break;
            }

        });

        theScene.setOnKeyReleased( event -> {
            //  System.out.println(event);
        });
    }
}
