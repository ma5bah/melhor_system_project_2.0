package access_management;

// import java.awt.event.ActionListener;
// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;
// import javafx.embed.swing.SwingNode;
// import access_management_dep.LoginAndRegister;

// import javax.swing.*;
// import java.io.IOException;
// import static javafx.application.Application.launch;
// import javafx.application.Platform;
// import javafx.beans.property.ObjectProperty;
// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;
// import javafx.event.EventDispatcher;
// import javafx.scene.control.Alert;
// import javafx.scene.layout.AnchorPane;
// import javafx.stage.Modality;

// import swing.Password;
// import swing.TextField;
// import swing.login_register_event;

public class login_registration {//extends Application implements EventHandler<ActionEvent> {

//     private SwingNode swingNode;
//     LoginAndRegister l_scene;
//     login_register_event login_register_event_state;
//     Stage login_register_window;

//     public void start(Stage stage) {

//         swingNode = new SwingNode();
//         createAndSetSwingContent(swingNode);

//         AnchorPane pane = new AnchorPane();
//         pane.getChildren().add(swingNode);

//         stage.setScene(new Scene(pane, 960, 600));
//         stage.show();
//     }

//     public boolean init_scene() {
//         try {
//             login_register_window = new Stage();
//             swingNode = new SwingNode();
//             createAndSetSwingContent(swingNode);

//             StackPane pane = new StackPane();
//             pane.getChildren().add(swingNode);
//             login_register_window.setScene(new Scene(pane, 960, 600));
//             login_register_window.initModality(Modality.APPLICATION_MODAL);
//             login_register_window.show();

//             return true;
//         } catch (Exception e) {
//             return false;
          
//         }
//     }

//     private void createAndSetSwingContent(final SwingNode swingNode) {
//         Platform.runLater(() -> {
//             l_scene = new LoginAndRegister();
//             JFrame t = new JFrame();
//             t.add(l_scene);
//             login_register_event_state = new login_register_event() {
//                 @Override
//                 public void initial_state() {
//                     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                 }

//                 @Override
//                 public void login_done(java.awt.event.ActionEvent evt, String username, String password) {
//                     try {
//                         System.out.println("Login from fx");
// //                            System.out.println(evt);
//                         System.out.println(username);
//                         System.out.println(password);
//                         BackEnd.CommonTask.showAlert(Alert.AlertType.CONFIRMATION, "Login Done", "weee");
// //                            swingNode.getContent().removeAll();
// //                            
// //                            Thread.currentThread().stop();
// //                            l_scene.dispatchEvent(evt);
// //                            JComponent comp = ;
// //                            java.awt.Window win = SwingUtilities.getWindowAncestor((JComponent) evt.getSource());
// //                            
// //                            win.dispose();

// //                            login_register_window.close();
//                     } catch (Exception e) {
//                         System.out.println("Error");
//                         System.out.println(e);
//                     }

//                 }

//                 @Override
//                 public void registration_done(java.awt.event.ActionEvent evt, TextField username, TextField email, Password password, TextField name) {
//                     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                 }

//                 @Override
//                 public void log_out() {
//                     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                 }
//             };

//             l_scene.set_login_register_event(login_register_event_state);
//             swingNode.setContent(l_scene);

// //                System.out.println("Event : ");
// //                System.out.println(swingNode.eventDispatcherProperty());
//         });
// //        SwingUtilities.invokeLater(new Runnable() {
// //            @Override
// //            public void run() {
// //                l_scene = new LoginAndRegister();
// //                JFrame t=new JFrame();
// //                t.add(l_scene);
// //                login_register_event_state = new login_register_event() {
// //                    @Override
// //                    public void initial_state() {
// //                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
// //                    }
// //
// //                    @Override
// //                    public void login_done(java.awt.event.ActionEvent evt, String username, String password) {
// //                        try {
// //                            System.out.println("Login from fx");
// ////                            System.out.println(evt);
// //                            System.out.println(username);
// //                            System.out.println(password);
// ////                            swingNode.getContent().removeAll();
// ////                            
// ////                            Thread.currentThread().stop();
// ////                            l_scene.dispatchEvent(evt);
// ////                            JComponent comp = ;
// ////                            java.awt.Window win = SwingUtilities.getWindowAncestor((JComponent) evt.getSource());
// ////
// ////                            win.dispose();
// //
// ////                            login_register_window.close();
// //                        } catch (Exception e) {
// //                            System.out.println("Error");
// //                            System.out.println(e);
// //                        }
// //
// //                    }
// //
// //                    @Override
// //                    public void registration_done(java.awt.event.ActionEvent evt, TextField username, TextField email, Password password, TextField name) {
// //                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
// //                    }
// //
// //                    @Override
// //                    public void log_out() {
// //                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
// //                    }
// //                };
// //                
// //                l_scene.set_login_register_event(login_register_event_state);
// //                swingNode.setContent(l_scene);
// //
// ////                System.out.println("Event : ");
// ////                System.out.println(swingNode.eventDispatcherProperty());
// //            }
// //        });
// //        SwingUtilities.
//     }

// //    public static void main(String[] args) {
// //        launch(args);
// //    }
//     @Override
//     public void handle(ActionEvent t) {
//         System.out.print("Event from handle");
//         System.out.println(t);

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(l_scene);
//            }
//        });
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    // }
}
