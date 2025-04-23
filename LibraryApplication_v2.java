package aC;
import java.util.ArrayList;

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

public class LibraryApplication_v2 extends Application {

    static final int GAP = 15;
    static final int LARGE_FONT = 30;
    static final int SMALL_FONT = 14;

    private ListView<LibraryItem> lstBookList;

    // the ArrayList reference
    private ArrayList<LibraryItem> libraryBooks;

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

        Button btnAddBooks = new Button("Add Books");
        root.add(btnAddBooks, 1, 3); // pos (1,3)
        btnAddBooks.setOnAction(event -> addBooks());

        Button btnRemoveBook = new Button("Remove Book");
        root.add(btnRemoveBook, 1, 4); // pos (1,4)
        btnRemoveBook.setOnAction(event -> removeBook());

        libraryBooks = new ArrayList <LibraryItem>();

        addBooks();

        Scene scene = new Scene(root);

        myStage.setTitle("Virtual Library");
        myStage.setScene(scene);
        myStage.show();
    }

    private void addBooks() {
        //fill the library with Books
    	String title, author;
    	int numBooks = FXDialog.readInt("How many books would you like to add?");
    	
    	for (int i = 0; i < numBooks; i++) {
    	title = FXDialog.readString("Book " + (i + 1) + " Title");
    	author = FXDialog.readString("Book " + (i + 1) + " Author");
    	libraryBooks.add(new Book(title, author));
    	
        updateListView();
    }
  }

    private void checkOut() {
        int bNum = lstBookList.getSelectionModel().getSelectedIndex();
        if (bNum == -1) {
            FXDialog.print("Please make a selection");
        } else {
        	LibraryItem book = libraryBooks.get(bNum);
        	if (book.isOut()) {
        		FXDialog.print("This book is already out!");
        	} else {	
        		String name = FXDialog.readString("What is the name of the person checking out the book?");
        		book.checkOut(name);
        		
        		updateListView();
        
        }
      }
    }

    private void returnBook() {
        int bNum = lstBookList.getSelectionModel().getSelectedIndex();
        if (bNum == -1) {
            FXDialog.print("Please make a selection");
        } else {
        	LibraryItem book = libraryBooks.get(bNum);
        	if (book.isOut()) {
        	book.returnItem();	
        	} else {
        	FXDialog.print("This book is already returned!");	
           
        	updateListView();
        }
      }
    }

    private void removeBook() {
        int bNum = lstBookList.getSelectionModel().getSelectedIndex();
        if (bNum == -1) {
            FXDialog.print("Please make a selection");
        } else {
        	if (FXDialog.askYesNoQuestion("Are you sure you want to remove this entry? \n" + libraryBooks.get(bNum))) {
        		libraryBooks.remove(bNum);
        	
            updateListView();
        }
      }
        
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
