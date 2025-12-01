package net.hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videogame")
public class Videogame 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "platform")
	private String platform;
	
	@Column(name = "rating")
	private Rating rating;
	
	@Column(name = "isCollection")
	private boolean isCollection;
	
	@Column(name = "isRomhack")
	private boolean isRomhack;
	
	@Column(name = "isFavourite")
	private boolean isFavourite;
	
	public Videogame() {
		
	}
	
	public Videogame(String title, String platform, Rating rating, boolean isCollection, boolean isRomhack, boolean isFavourite) {
		if(title.length() > 200) {
			throw new IllegalArgumentException("Field 'Title' cannot be longer than 200 characters.");
		}
		if(platform.length() > 200) {
			throw new IllegalArgumentException("Field 'Platform' cannot be longer than 200 characters.");
		}
		this.title = title;
		this.platform = platform;
		this.rating = rating;
		this.isCollection = isCollection;
		this.isRomhack = isRomhack;
		this.isFavourite = isFavourite;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.length() > 200) {
			throw new IllegalArgumentException("Field 'Title' cannot be longer than 200 characters.");
		}
		this.title = title;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		if(platform.length() > 200) {
			throw new IllegalArgumentException("Field 'Platform' cannot be longer than 200 characters.");
		}
		this.platform = platform;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public boolean isCollection() {
		return isCollection;
	}

	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}

	public boolean isRomhack() {
		return isRomhack;
	}

	public void setRomhack(boolean isRomhack) {
		this.isRomhack = isRomhack;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	@Override
	public String toString() {
		String toString = String.format("%d.\t%s - Platform: %s - Rating: %s",this.id,this.title,this.platform,this.rating.name());
		if(isCollection()) {
			toString += "\t(Collection)";
		}
		if(isRomhack()) {
			toString += "\t(Romhack)";
		}
		if(isFavourite()) {
			toString += "\t(★)";
		}
		return toString;
	}
	
	public String toConsole() {
		String toString = String.format("\u001B[33m%d.\t\u001B[37m%s \u001B[36m- Platform:\u001B[37m %s \u001B[36m- Rating:\u001B[37m %s",this.id,this.title,this.platform,this.rating.name());
		if(isCollection()) {
			toString += "\u001B[32m\t(Collection)\u001B[37m";
		}
		if(isRomhack()) {
			toString += "\u001B[35m\t(Romhack)\u001B[37m";
		}
		if(isFavourite()) {
			toString += "\u001B[33m\t(★)\u001B[37m";
		}
		return toString;
	}
	
	public String[] toTable() {
		return String.format("%d;%s;%s;%s;%s;%s;%s",this.id,this.title,this.platform,this.rating.name(),
				this.isCollection	? "X" : " ",
				this.isRomhack		? "X" : " ",
				this.isFavourite	? "★" : " ").split(";");
	}
}
