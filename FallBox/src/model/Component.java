package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class Component {

	String name;
	Integer dimension;
	Date lastChange;
	String owner;
	List<String> can_view = new ArrayList<String>();
	List<String> can_edit = new ArrayList<String>();
	
	public Component() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public Date getLastChange() {
		return lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<String> getCan_view() {
		return can_view;
	}

	public void setCan_view(List<String> can_view) {
		this.can_view = can_view;
	}

	public List<String> getCan_edit() {
		return can_edit;
	}

	public void setCan_edit(List<String> can_edit) {
		this.can_edit = can_edit;
	}
	
	
	public void share(String u,boolean canEdit) {
		if(canEdit) {
			can_edit.add(u);
		}
		else {
			can_view.add(u);
		}
	}
	
	public void add(Component c) {}
	public  List<Component> getContent(){return null;}
	
}
