package pkgLibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Book {

	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private Date publish_date;
	private String description;
	private double cost;
	

	public Book() {
	
		}
	
	public Book(String id) throws BookException {
		Book book = GetBook(id);
	}
	
	public static Book GetBook(String id) throws BookException {
		Catalog cat = ReadXMLFile();
		ArrayList<Book> BookList = cat.getBooks();
		Book BookID = null; 
		
		for (Book j : BookList )
			if (j.getId().equals(id)) {
				BookID = j;
			}
				
		if (BookID == null) {
			throw new BookException("This book can't be found");
		}
		else
			return BookID;
}

	public Book(String id, String author, String title, String genre, double cost, double price, Date publish_date, String description)
	{
		super();
		this.cost = cost;
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;		
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
	}

	public static void AddBook(int CatalogID, Book BookInstance) throws BookException {
		Catalog cat = ReadXMLFile();
		cat.setId(CatalogID);
		ArrayList<Book> BookList = cat.getBooks();
		Book BookID = null;
		for (Book j : BookList) {
			if (j.getId().equals(BookInstance.getId())) {
				throw new BookException("This book already exists");
			}
		}
			
		if (BookID == null) {
			BookList.add(BookInstance);
			cat.setBooks(BookList);
			WriteXMLFile(cat);
		}
		
} 
	
 

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	@XmlElement
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	@XmlElement
	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}
	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath;
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}

// In order to use WriteXMLFile

	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
}

	public char[] getDescription() {
		
		return null;
	}
	
	

}
