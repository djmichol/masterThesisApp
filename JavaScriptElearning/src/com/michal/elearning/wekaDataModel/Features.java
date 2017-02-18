package com.michal.elearning.wekaDataModel;

public class Features {

	private int mauseClicksMean;
	private int mauseClickDeviation;
	private int mauseCliskEventCount;
	private int tabFreq;
	private int delFreq;
	private int enterFreq;
	private int backSpaceFreq;
	private int spaceFreq;
	private double writingSpeed;
	private int singleKeyDwellMean;
	private int singleKeyDwellDeviation;	
	
	public int getMauseClicksMean() {
		return mauseClicksMean;
	}
	public void setMauseClicksMean(int mauseClicksMean) {
		this.mauseClicksMean = mauseClicksMean;
	}
	public int getMauseClickDeviation() {
		return mauseClickDeviation;
	}
	public void setMauseClickDeviation(int mauseClickDeviation) {
		this.mauseClickDeviation = mauseClickDeviation;
	}
	public int getMauseCliskEventCount() {
		return mauseCliskEventCount;
	}
	public void setMauseCliskEventCount(int mauseCliskEventCount) {
		this.mauseCliskEventCount = mauseCliskEventCount;
	}
	public int getTabFreq() {
		return tabFreq;
	}
	public void setTabFreq(int tabFreq) {
		this.tabFreq = tabFreq;
	}
	public int getDelFreq() {
		return delFreq;
	}
	public void setDelFreq(int delFreq) {
		this.delFreq = delFreq;
	}
	public int getEnterFreq() {
		return enterFreq;
	}
	public void setEnterFreq(int enterFreq) {
		this.enterFreq = enterFreq;
	}
	public int getBackSpaceFreq() {
		return backSpaceFreq;
	}
	public void setBackSpaceFreq(int backSpaceFreq) {
		this.backSpaceFreq = backSpaceFreq;
	}
	public int getSpaceFreq() {
		return spaceFreq;
	}
	public void setSpaceFreq(int spaceFreq) {
		this.spaceFreq = spaceFreq;
	}
	public double getWritingSpeed() {
		return writingSpeed;
	}
	public void setWritingSpeed(double writingSpeed) {
		this.writingSpeed = writingSpeed;
	}
	public int getSingleKeyDwellMean() {
		return singleKeyDwellMean;
	}
	public void setSingleKeyDwellMean(int singleKeyDwellMean) {
		this.singleKeyDwellMean = singleKeyDwellMean;
	}
	public int getSingleKeyDwellDeviation() {
		return singleKeyDwellDeviation;
	}
	public void setSingleKeyDwellDeviation(int singleKeyDwellDeviation) {
		this.singleKeyDwellDeviation = singleKeyDwellDeviation;
	}
	
}
