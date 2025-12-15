package restvideogames.restsql;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Videogame 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String platform;
	private Rating rating;
	private Boolean isCollection;
	private Boolean isRomhack;
	private Boolean isFavourite;
	
	public Videogame() {
		
	}
	
	public Videogame(String title, String platform, Rating rating, Boolean isCollection, Boolean isRomhack, Boolean isFavourite) {
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean isCollection() {
		return isCollection;
	}

	public void setCollection(Boolean isCollection) {
		this.isCollection = isCollection;
	}

	public Boolean isRomhack() {
		return isRomhack;
	}

	public void setRomhack(Boolean isRomhack) {
		this.isRomhack = isRomhack;
	}

	public Boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videogame other = (Videogame) obj;
		return Objects.equals(id, other.id);
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
