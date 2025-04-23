package aC.library;

/**
 * Library Object Class
 *
 */
public class LibraryItem {

  protected String title;
  protected String checkOutName;
  protected boolean isCheckedOut;

  /**
   * Creates a LibraryItem with a given title. By default the item is available for check out.
   * @param title the title of the LibraryItem
   */
  public LibraryItem(String title) {
    this.title = title;
    isCheckedOut = false;
    checkOutName = "";
  }

  /**
   * @return the title of the LibraryItem
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return <code>true</code> if this book is checked out, <code>false</code> if it is available
   */
  public boolean isOut() {
    return isCheckedOut;
  }

  /**
   * @return the name of the person that has the book. Returns an empty String "" if the book is not checked out.
   */
  public String getCheckOutName() {
    return checkOutName;
  }

  /**
   * Changes the check out status to false. Clears the name of the person that checked out the book.
   */
  public void returnItem() {
    isCheckedOut = false;
    checkOutName = "";
  }

  /**
   * Changes the check out status to true. Saves the name of the person borrowing the book.
   * @param name the name of the person borrowing the book
   */
  public void checkOut(String name) {
    isCheckedOut = true;
    checkOutName = name;
  }

  /**
   * ToString method
   * Gives the title and a String indicating if the item is available or checked out. 
   * If the item is checked out, gives the borrower's name.
   */
  @Override
  public String toString() {
    String output = "Title: " + title + "\n";

    if (isCheckedOut) {
      output += "Checked Out By: " + checkOutName + "\n";
    } else {
      output += "Available for Check Out\n";
    }

    return output;
  }

  /**
   * Determines if two library items are the same by checking the title and check out name
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LibraryItem) {
      LibraryItem test = (LibraryItem) obj;

      boolean titleMatches = title.equals(test.getTitle());
      boolean borrowerMatches = checkOutName.equals(test.getCheckOutName());

      return titleMatches && borrowerMatches;

    }

    return false;

  }

}
