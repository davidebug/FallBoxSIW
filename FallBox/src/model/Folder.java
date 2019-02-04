package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder extends Component {
	
	List<Component> content = new ArrayList<Component>();
	public Folder(String name, int dimension, Date lastChange, String owner) {
		super();
		this.name = name;
		this.dimension = dimension;
		this.lastChange = lastChange;
		this.owner = owner;
	}
	@Override
	public Integer getDimension() {
		Integer dim = 0;
		
		for(Component c: content) {
			dim+= c.getDimension();
		}
		return dim;
		
	}
	@Override
	public List<Component> getContent() {
		return content;
	}

	public void setContent(List<Component> content) {
		this.content = content;
	}

	@Override
	public void add(Component c) {
		content.add(c);
	}
}
