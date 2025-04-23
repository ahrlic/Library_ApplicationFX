/*
* Main class for Library Application, Scene launcher for FX 
* Library Application
* Ariana Hrlic 
*/
import simpleIO.FXDialog;
import aC.library.Book;
import aC.library.LibraryItem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LibraryApplication extends Application {

    static final int GAP = 15;
    static final int LARGE_FONT = 30;
    static final int SMALL_FONT = 14;

    private ListView<LibraryItem> lstBookList;

    private LibraryItem[] libraryBooks;

    @Override
    public void start(Stage myStage) throws Exception {

        GridPane root = new GridPane();

        root.setHgap(GAP);
        root.setVgap(GAP);
        root.setPadding(new Insets(GAP, GAP, GAP, GAP));

        Label lblTitle = new Label("Library");
        lblTitle.setFont(Font.font(LARGE_FONT));
        root.add(lblTitle, 0, 0, 2, 1); // pos (0,0), colspan = 2

        lstBookList = new ListView<LibraryItem>();
        root.add(lstBookList, 2, 0, 1, 6); // pos (2,0), rowspan = 6

        Button btnCheckOut = new Button("Check Out Book");
        root.add(btnCheckOut, 1, 1); // pos (1,1)
        btnCheckOut.setOnAction(event -> checkOut());

        Button btnReturn = new Button("Return Book");
        root.add(btnReturn, 1, 2); // pos (1,2)
        btnReturn.setOnAction(event -> returnBook());

        addBooks();

        Scene scene = new Scene(root);

        myStage.setTitle("Virtual Library");
        myStage.setScene(scene);
        myStage.show();
    }

    private void addBooks() {
    	//Declarations
    	String title, author;
    	
    	//prompt the use for how many books they want to add to the library(array)
    	int numBooks = FXDialog.readInt("How many books would you like to create?");
    	
    	//create the array and use the userInput as the given size
    	libraryBooks = new LibraryItem[numBooks];
    	
    	//for loop to get the required information for each of the books that the user choose to add to the array
    	for (int i = 0; i < numBooks; i++) {
    		//prompt the user for the required information
    		author = FXDialog.readString("Book " + (i + 1) + " Author");
    		title = FXDialog.readString("Book " + (i + 1) + " Title");
    		
    		//add to the array
    		libraryBooks[i] = new Book(title, author);
    		
    } 
    		//updating the list of the array for the library
    		updateListView();
    }

    private void checkOut() {
        int bNum = lstBookList.getSelectionModel().getSelectedIndex();
        if (bNum == -1) {
            FXDialog.print("Please make a selection");
            
            //check if the book is already checked out
        } else if (libraryBooks[bNum].isOut()) {
         
        	FXDialog.print("This book is already out!");  
        } else {
        	//prompting user for their name to check out the book
        	String name = FXDialog.readString("What is the name of the person checking out the book?");
        	
        	//removing the book from the array(library)
        	libraryBooks[bNum].checkOut(name);
        	
        }
        	//updating the list of the array for the library
        	updateListView();
    }

    private void returnBook() {
        int bNum = lstBookList.getSelectionModel().getSelectedIndex();
        if (bNum == -1) {
            FXDialog.print("Please make a selection");
            
            //checking if the book was checked out of the array
        } else if (libraryBooks[bNum].isOut()) {

        	//if it was checked out, return to the library (add it back into the array)
        	libraryBooks[bNum].returnItem();
        	
        } else {
        	//if the book has not been checked out, a friendly message to tell the user 
        	FXDialog.print("This book is already returned!");
        }
        	updateListView();
    }

    private void updateListView() {
        lstBookList.getItems().clear();

        for (LibraryItem book : libraryBooks) {
            lstBookList.getItems().add(book);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
