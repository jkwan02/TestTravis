
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;


public class MyCalculator extends Application{
    Label label_1;
    Label label_2;
    Button button_1;
    Button plus_button;
    Button sub_button;
    Button divide_button;
    Button mult_button;
    Button clear_but;
    TextField textfield_1;
    TextField textfield_2;
    TextField textfield_3;
    Label answer_label;
    int i = 1;

    public class Add implements DoMathStuff {
        @Override
        public int do_math2(int num1, int num2) { //show answer
            return num1 + num2;
        }
    }

    public class Sub implements DoMathStuff {
        @Override
        public int do_math2(int num1, int num2) {
            return num1 - num2;
        }
    }
    public class Mul implements DoMathStuff {
        @Override
        public int do_math2(int num1, int num2) {
            return num1 * num2;
        }
    }
    public class Div implements DoMathStuff {
        @Override
        public int do_math2(int num1, int num2) {
            return num1 / num2;
        }
    }


    public class Factory {
        public DoMathStuff get_math_symbol(Button symbol) {
            if(symbol == plus_button) {
                return new Add();
            }
            else if(symbol == sub_button) {
                return new Sub();
            }
            else if(symbol == mult_button) {
                return new Mul();
            }
            else if(symbol == divide_button) {
                return new Div();
            }
            return null;
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */



    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("My Calculator");

        label_1 = new Label("Number 1: "); // labels be like prompts
        label_2 = new Label("Number 2: ");

        VBox vb = new VBox(); //dunno what boxes do

        textfield_1 = new TextField();
        textfield_2 = new TextField();
        answer_label = new Label("Answer: ");

        textfield_1.setPrefWidth(70);
        textfield_2.setPrefWidth(70);




        Scene scene = new Scene(vb, 300, 300);
        stage.setScene(scene);

        button_1 = new Button("name of button");
        button_1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Hello World!!!");
                label_1.setText("Try #" + i);
                i++;
            }
        });

        plus_button = new Button(" + ");
        plus_button.setOnAction(e -> do_math(e));

        sub_button = new Button(" - ");
        sub_button.setOnAction(e -> do_math(e));

        divide_button = new Button(" / ");
        divide_button.setOnAction(e -> do_math(e));

        mult_button = new Button(" * ");
        mult_button.setOnAction(e -> do_math(e));

        clear_but = new Button("Clear");
        clear_but.setOnAction(e -> do_math(e));


        vb.getChildren().addAll(label_1, textfield_1,
                label_2, textfield_2,
                plus_button, sub_button, divide_button, mult_button, clear_but,
                answer_label);

        stage.show();
    }

    public void do_math(ActionEvent e) {
        int num1, num2, answer;

        if(e.getSource() == clear_but) {
            textfield_1.setText("");
            textfield_2.setText("");
            answer_label.setText("Answer: ");
            textfield_1.requestFocus();

            return;
        }
        num1 = Integer.parseInt(textfield_1.getText());
        num2 = Integer.parseInt(textfield_2.getText());

        answer = 0;

        Factory my_factory = new Factory();
        DoMathStuff my_math;


        my_math = my_factory.get_math_symbol((Button) e.getSource());
        answer = my_math.do_math2(num1, num2);

        answer_label.setText("Answer: " + answer);
    }


}