package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
    // variable
    static int speed = 5;
    static int foodcolor = 0;
    static int stonecolor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int stoneX = 0;
    static int stoneY = 0;
    static int stoneX1= 0;
    static int stoneY1 = 0;

    static int stoneX2= 0;
    static int stoneY2 = 0;

    static int stoneX3= 0;
    static int stoneY3 = 0;

    static int stoneX4= 0;
    static int stoneY4 = 0;

    static int stoneX5= 0;
    static int stoneY5 = 0;

    static int stoneX6= 0;
    static int stoneY6 = 0;

    static int stoneX7= 0;
    static int stoneY7 = 0;

    static int stoneX8= 0;
    static int stoneY8 = 0;

    static int stoneX9= 0;
    static int stoneY9 = 0;
    static int cornersize = 25;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static boolean gameWin = false;
    static Random rand = new Random();
    static Random rand2 = new Random();
    static Random rand3 = new Random();
    static Random rand4 = new Random();
    static Random rand5 = new Random();
    static int score = 0;

    public enum Dir {
        left, right, up, down
    }

    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public void start(Stage primaryStage) {
        try {
            newFood();
            newStone();
            newStone1();
            newStone2();
            newStone3();
            newStone4();

            VBox root = new VBox();
            Canvas c = new Canvas(width * cornersize, height * cornersize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, width * cornersize, height * cornersize);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.W) {
                    direction = Dir.up;
                }
                if (key.getCode() == KeyCode.A) {
                    direction = Dir.left;
                }
                if (key.getCode() == KeyCode.S) {
                    direction = Dir.down;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Dir.right;
                }

            });

            // add start snake parts
            snake.add(new Corner(width / 2, height / 2));

            //If you do not want to use css style, you can just delete the next line.

            primaryStage.setScene(scene);
            primaryStage.setTitle("ИГРА");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("Конец Игры", 100, 250);
            return;
        }

        if (gameWin) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("Победа!", 100, 250);
            return;
        }

//        for (int i = snake.size() - 1; i >= 1; i--) {
//            snake.get(i).x = snake.get(i - 1).x;
//            snake.get(i).y = snake.get(i - 1).y;
//        }

        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;
        }

        if (stoneX == snake.get(0).x && stoneY == snake.get(0).y) {
            gameOver = true;
        }

        if (stoneX1 == snake.get(0).x && stoneY1 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX2 == snake.get(0).x && stoneY2 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX3 == snake.get(0).x && stoneY3 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX4 == snake.get(0).x && stoneY4 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX5 == snake.get(0).x && stoneY5 == snake.get(0).y) {
            gameOver = true;

        }
        if (stoneX6 == snake.get(0).x && stoneY6 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX7 == snake.get(0).x && stoneY7 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX8 == snake.get(0).x && stoneY8 == snake.get(0).y) {
            gameOver = true;
        }
        if (stoneX9 == snake.get(0).x && stoneY9 == snake.get(0).y) {
            gameOver = true;
        }
        if (score == 10) {
            gameWin = true;
        }



        // eat food
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            score +=1 ;
            snake.add(new Corner(-1, -1));
            newFood();
        }

//        // self destroy
//        for (int i = 1; i < snake.size(); i++) {
//            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
//                gameOver = true;
//            }
//        }

        // fill
        // background
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // score
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + ( score), 10, 30);

        // random foodcolor
        Color cc = Color.WHITE;
        Color gg = Color.BLACK;
        switch (foodcolor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }

        switch (stonecolor) {
            case 0:
                gg = Color.BLACK;
                break;

        }
        gc.setFill(cc);
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);

        if (score == 0) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
        }

        if (score == 1) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
        }
        if (score == 2) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
        }
        if (score == 3) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
        }
        if (score == 4) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
        }
        if (score == 5) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX5 * cornersize, stoneY5 * cornersize, cornersize, cornersize);
        }
        if (score == 6) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX5 * cornersize, stoneY5 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX6 * cornersize, stoneY6 * cornersize, cornersize, cornersize);
        }
        if (score == 7) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX5 * cornersize, stoneY5 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX6 * cornersize, stoneY6 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX7 * cornersize, stoneY7 * cornersize, cornersize, cornersize);
        }
        if (score == 8) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX5 * cornersize, stoneY5 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX6 * cornersize, stoneY6 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX7 * cornersize, stoneY7 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX8 * cornersize, stoneY8 * cornersize, cornersize, cornersize);
        }
        if (score == 9) {
            gc.setFill(gg);
            gc.fillRect(stoneX * cornersize, stoneY * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX1 * cornersize, stoneY1 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX2 * cornersize, stoneY2 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX3 * cornersize, stoneY3 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX4 * cornersize, stoneY4 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX5 * cornersize, stoneY5 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX6 * cornersize, stoneY6 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX7 * cornersize, stoneY7 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX8 * cornersize, stoneY8 * cornersize, cornersize, cornersize);
            gc.setFill(gg);
            gc.fillRect(stoneX9 * cornersize, stoneY9 * cornersize, cornersize, cornersize);
        }
//        if (score >= 10) {
//            gc.setFill(gg);
//            gc.fillRect(stoneX9 * cornersize, stoneY9 * cornersize, cornersize, cornersize);
//        }


        // snake
        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);

        }

    }

    // food
    public static void newFood() {
        start: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);

            break;

        }
    }

    public static void newStone() {

        start: while (true) {
            stoneX = rand2.nextInt(width);
            stoneY = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX && c.y == stoneY) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);
            ;

            break;
        }

    }


    public static void newStone1() {

        start: while (true) {
            stoneX1 = rand3.nextInt(width);
            stoneY1 = rand3.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX1 && c.y == stoneY1) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone2() {

        start: while (true) {
            stoneX2 = rand4.nextInt(width);
            stoneY2 = rand4.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX2 && c.y == stoneY2) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone3() {

        start: while (true) {
            stoneX3 = rand2.nextInt(width);
            stoneY3 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX3 && c.y == stoneY3) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone4() {

        start: while (true) {
            stoneX4 = rand2.nextInt(width);
            stoneY4 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX4 && c.y == stoneY4) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone5() {

        start: while (true) {
            stoneX5 = rand2.nextInt(width);
            stoneY5 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX5 && c.y == stoneY5) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone6() {

        start: while (true) {
            stoneX6 = rand2.nextInt(width);
            stoneY6 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX6 && c.y == stoneY6) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone7() {

        start: while (true) {
            stoneX7 = rand2.nextInt(width);
            stoneY7 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX7 && c.y == stoneY7) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone8() {

        start: while (true) {
            stoneX8 = rand2.nextInt(width);
            stoneY8 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX8 && c.y == stoneY8) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void newStone9() {

        start: while (true) {
            stoneX9 = rand2.nextInt(width);
            stoneY9 = rand2.nextInt(height);

            for (Corner c : snake) {
                if (c.x == stoneX9 && c.y == stoneY9) {
                    continue start;
                }
            }

            stonecolor = rand.nextInt(1);


            break;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}