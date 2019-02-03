package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class Component {

	String name;
	int dimension;
	Date lastChange;
	String owner;
	List<User> can_view = new ArrayList<User>();
	List<User> can_edit = new ArrayList<User>();
	
	public Component() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDimension() {
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

	public List<User> getCan_view() {
		return can_view;
	}

	public void setCan_view(List<User> can_view) {
		this.can_view = can_view;
	}

	public List<User> getCan_edit() {
		return can_edit;
	}

	public void setCan_edit(List<User> can_edit) {
		this.can_edit = can_edit;
	}
	
	
	public void share(User u,boolean canEdit) {
		if(canEdit) {
			can_edit.add(u);
		}
		else {
			can_view.add(u);
		}
	}
	
	public void add(Component c) {}
	
}
