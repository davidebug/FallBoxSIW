package model;

import java.util.Date;
import java.util.List;

public class File extends Component {
	
	public File(String name, int dimension, Date lastChange, String owner) {
		super();
		this.name = name;
		this.dimension = dimension;
		this.lastChange = lastChange;
		this.owner = owner;
	}

	
}
