package com.kaltura.live.model.aggregation.keys;

public class EntryLocationKey extends EntryKey {
	
	private static final long serialVersionUID = 7416244826202491902L;
	
	
	protected String country;
	protected String city;
	
	public EntryLocationKey(String entryId, long eventTime, int partnerId, String country, String city) {
		super(entryId, eventTime, partnerId);
		this.country= country;
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
		result = prime * result + Long.valueOf(eventTime).hashCode();
		result = prime * result + partnerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntryLocationKey other = (EntryLocationKey) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (entryId == null) {
			if (other.entryId != null)
				return false;
		} else if (!entryId.equals(other.entryId))
			return false;
		if (eventTime != other.eventTime)
			return false;
		if (partnerId != other.partnerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntryLocationKey [entryId=" + entryId + ", eventTime="
				+ eventTime + ", partnerId=" + partnerId + ", country="
				+ country + ", city=" + city + "]";
	}
	
}
