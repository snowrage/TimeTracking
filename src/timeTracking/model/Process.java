package timeTracking.model;

import java.sql.Timestamp;

public class Process
{
	private int id;
	private String name;
	private String description;
	private Timestamp startDate = null;
	private Timestamp endDate = null;

	private boolean started;
	private boolean paused;
	private boolean ended;

	// ###################
	// ## Getter/Setter ##
	// ###################
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Timestamp getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Timestamp startDate)
	{
		this.startDate = startDate;
		this.started = true;
	}

	public Timestamp getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Timestamp endDate)
	{
		if (this.started && endDate != null)
		{
			this.endDate = endDate;
			this.ended = true;
		}
	}

	public boolean getStarted()
	{
		return this.started;
	}

	public boolean getPaused()
	{
		return this.paused;
	}

	public boolean getEnded()
	{
		return this.ended;
	}

}
