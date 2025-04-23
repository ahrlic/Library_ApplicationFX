package aC.library;

/**
 * Book Object Class extends Library Item Object Class
 * Overrides the toString method
 */
public class Book extends LibraryItem {
  private String author;

  /**
   * @param bookTitle the book's title
   * @param authorName the book's author
   */
  public Book(String bookTitle, String authorName) {
    super(bookTitle);
    author = authorName;
  }

  /**
   * @return author the author of the book
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Identifies the item as a book.
   * {@inheritDoc}
   * Also displays the author name.
   * 
   */
  @Override
  public String toString() {
    String output = new String("Book ");

    output += super.toString();
    output += "Author: " + author + "\n";

    return output;
  }

  /**
   * Determines if two books are equal.
   */
  @Override
  public boolean equals(Object obj) {

    if (obj instanceof Book) {
      Book test = (Book) obj;

      boolean authorMatches = author.equals(test.getAuthor());

      return authorMatches && super.equals(obj);

    }

    return false;
  }
}
