package com.michal.elearning.wekaDataModel;

public class Features {

	// mause
	private double mauseClicksMean;
	private double mauseClickDeviation;
	private int mauseCliskEventCount;
	//mouse move
	private double mauseSpeed;
	private double horizontalToTotalRatio;
	private double verticalToTotalRatio;
	private double clickToClickDistanceToTotalPathLengthRatioMean;
	private double clickToClickDistanceToTotalPathLengthRatioDeviation;
	// SigleKey
	private int tabFreq;
	private int delFreq;
	private int enterFreq;
	private int backSpaceFreq;
	private int spaceFreq;
	private double writingSpeed;
	private double singleKeyDwellMean;
	private double singleKeyDwellDeviation;
	// digraphs
	private double diGraphFirstDwellMean;
	private double diGraphSecondDwellMean;
	private double diGraphPressToPressMean;
	private double diGraphFlightMean;
	private double digraphDurationMean;
	private double diGraphFirstDwellDeviation;
	private double diGraphSecondDwellDeviation;
	private double diGraphPressToPressDeviation;
	private double diGraphFlightDeviation;
	private double digraphDurationDeviation;
	// trigraphs
	private double triGraphDwellFirstMean;
	private double triGraphDwellSecondMean;
	private double triGraphDwellThirdMean;
	private double triGraphPressFirstSecMean;
	private double triGraphPressSecThirdMean;
	private double triGraphFlightFirstSecMean;
	private double triGraphFlightSecThirdMean;
	private double triGraphPressToPressMean;
	private double triGraphKeyDwellMean;
	private double triGraphDurationMean;
	private double triGraphDwellFirstDeviation;
	private double triGraphDwellSecondDeviation;
	private double triGraphDwellThirdDeviation;
	private double triGraphPressFirstSecDeviation;
	private double triGraphPressSecThirdDeviation;
	private double triGraphFlightFirstSecDeviation;
	private double triGraphFlightSecThirdDeviation;
	private double triGraphPressToPressDeviation;
	private double triGraphKeyDwellDeviation;
	private double triGraphDurationDeviation;
	// graphs
	private double graphPressToPressMean;
	private double graphKeyDwellMean;
	private double graphWordDurationMean;
	private double graphPressToPressDeviation;
	private double graphKeyDwellDeviation;
	private double graphWordDurationDeviation;
	public double getMauseClicksMean() {
		return mauseClicksMean;
	}
	public void setMauseClicksMean(double mauseClicksMean) {
		this.mauseClicksMean = mauseClicksMean;
	}
	public double getMauseClickDeviation() {
		return mauseClickDeviation;
	}
	public void setMauseClickDeviation(double mauseClickDeviation) {
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
	public double getSingleKeyDwellMean() {
		return singleKeyDwellMean;
	}
	public void setSingleKeyDwellMean(double singleKeyDwellMean) {
		this.singleKeyDwellMean = singleKeyDwellMean;
	}
	public double getSingleKeyDwellDeviation() {
		return singleKeyDwellDeviation;
	}
	public void setSingleKeyDwellDeviation(double singleKeyDwellDeviation) {
		this.singleKeyDwellDeviation = singleKeyDwellDeviation;
	}
	public double getDiGraphFirstDwellMean() {
		return diGraphFirstDwellMean;
	}
	public void setDiGraphFirstDwellMean(double diGraphFirstDwellMean) {
		this.diGraphFirstDwellMean = diGraphFirstDwellMean;
	}
	public double getDiGraphSecondDwellMean() {
		return diGraphSecondDwellMean;
	}
	public void setDiGraphSecondDwellMean(double diGraphSecondDwellMean) {
		this.diGraphSecondDwellMean = diGraphSecondDwellMean;
	}
	public double getDiGraphPressToPressMean() {
		return diGraphPressToPressMean;
	}
	public void setDiGraphPressToPressMean(double diGraphPressToPressMean) {
		this.diGraphPressToPressMean = diGraphPressToPressMean;
	}
	public double getDiGraphFlightMean() {
		return diGraphFlightMean;
	}
	public void setDiGraphFlightMean(double diGraphFlightMean) {
		this.diGraphFlightMean = diGraphFlightMean;
	}
	public double getDigraphDurationMean() {
		return digraphDurationMean;
	}
	public void setDigraphDurationMean(double digraphDurationMean) {
		this.digraphDurationMean = digraphDurationMean;
	}
	public double getDiGraphFirstDwellDeviation() {
		return diGraphFirstDwellDeviation;
	}
	public void setDiGraphFirstDwellDeviation(double diGraphFirstDwellDeviation) {
		this.diGraphFirstDwellDeviation = diGraphFirstDwellDeviation;
	}
	public double getDiGraphSecondDwellDeviation() {
		return diGraphSecondDwellDeviation;
	}
	public void setDiGraphSecondDwellDeviation(double diGraphSecondDwellDeviation) {
		this.diGraphSecondDwellDeviation = diGraphSecondDwellDeviation;
	}
	public double getDiGraphPressToPressDeviation() {
		return diGraphPressToPressDeviation;
	}
	public void setDiGraphPressToPressDeviation(double diGraphPressToPressDeviation) {
		this.diGraphPressToPressDeviation = diGraphPressToPressDeviation;
	}
	public double getDiGraphFlightDeviation() {
		return diGraphFlightDeviation;
	}
	public void setDiGraphFlightDeviation(double diGraphFlightDeviation) {
		this.diGraphFlightDeviation = diGraphFlightDeviation;
	}
	public double getDigraphDurationDeviation() {
		return digraphDurationDeviation;
	}
	public void setDigraphDurationDeviation(double digraphDurationDeviation) {
		this.digraphDurationDeviation = digraphDurationDeviation;
	}
	public double getTriGraphDwellFirstMean() {
		return triGraphDwellFirstMean;
	}
	public void setTriGraphDwellFirstMean(double triGraphDwellFirstMean) {
		this.triGraphDwellFirstMean = triGraphDwellFirstMean;
	}
	public double getTriGraphDwellSecondMean() {
		return triGraphDwellSecondMean;
	}
	public void setTriGraphDwellSecondMean(double triGraphDwellSecondMean) {
		this.triGraphDwellSecondMean = triGraphDwellSecondMean;
	}
	public double getTriGraphDwellThirdMean() {
		return triGraphDwellThirdMean;
	}
	public void setTriGraphDwellThirdMean(double triGraphDwellThirdMean) {
		this.triGraphDwellThirdMean = triGraphDwellThirdMean;
	}
	public double getTriGraphPressFirstSecMean() {
		return triGraphPressFirstSecMean;
	}
	public void setTriGraphPressFirstSecMean(double triGraphPressFirstSecMean) {
		this.triGraphPressFirstSecMean = triGraphPressFirstSecMean;
	}
	public double getTriGraphPressSecThirdMean() {
		return triGraphPressSecThirdMean;
	}
	public void setTriGraphPressSecThirdMean(double triGraphPressSecThirdMean) {
		this.triGraphPressSecThirdMean = triGraphPressSecThirdMean;
	}
	public double getTriGraphFlightFirstSecMean() {
		return triGraphFlightFirstSecMean;
	}
	public void setTriGraphFlightFirstSecMean(double triGraphFlightFirstSecMean) {
		this.triGraphFlightFirstSecMean = triGraphFlightFirstSecMean;
	}
	public double getTriGraphFlightSecThirdMean() {
		return triGraphFlightSecThirdMean;
	}
	public void setTriGraphFlightSecThirdMean(double triGraphFlightSecThirdMean) {
		this.triGraphFlightSecThirdMean = triGraphFlightSecThirdMean;
	}
	public double getTriGraphPressToPressMean() {
		return triGraphPressToPressMean;
	}
	public void setTriGraphPressToPressMean(double triGraphPressToPressMean) {
		this.triGraphPressToPressMean = triGraphPressToPressMean;
	}
	public double getTriGraphKeyDwellMean() {
		return triGraphKeyDwellMean;
	}
	public void setTriGraphKeyDwellMean(double triGraphKeyDwellMean) {
		this.triGraphKeyDwellMean = triGraphKeyDwellMean;
	}
	public double getTriGraphDurationMean() {
		return triGraphDurationMean;
	}
	public void setTriGraphDurationMean(double triGraphDurationMean) {
		this.triGraphDurationMean = triGraphDurationMean;
	}
	public double getTriGraphDwellFirstDeviation() {
		return triGraphDwellFirstDeviation;
	}
	public void setTriGraphDwellFirstDeviation(double triGraphDwellFirstDeviation) {
		this.triGraphDwellFirstDeviation = triGraphDwellFirstDeviation;
	}
	public double getTriGraphDwellSecondDeviation() {
		return triGraphDwellSecondDeviation;
	}
	public void setTriGraphDwellSecondDeviation(double triGraphDwellSecondDeviation) {
		this.triGraphDwellSecondDeviation = triGraphDwellSecondDeviation;
	}
	public double getTriGraphDwellThirdDeviation() {
		return triGraphDwellThirdDeviation;
	}
	public void setTriGraphDwellThirdDeviation(double triGraphDwellThirdDeviation) {
		this.triGraphDwellThirdDeviation = triGraphDwellThirdDeviation;
	}
	public double getTriGraphPressFirstSecDeviation() {
		return triGraphPressFirstSecDeviation;
	}
	public void setTriGraphPressFirstSecDeviation(double triGraphPressFirstSecDeviation) {
		this.triGraphPressFirstSecDeviation = triGraphPressFirstSecDeviation;
	}
	public double getTriGraphPressSecThirdDeviation() {
		return triGraphPressSecThirdDeviation;
	}
	public void setTriGraphPressSecThirdDeviation(double triGraphPressSecThirdDeviation) {
		this.triGraphPressSecThirdDeviation = triGraphPressSecThirdDeviation;
	}
	public double getTriGraphFlightFirstSecDeviation() {
		return triGraphFlightFirstSecDeviation;
	}
	public void setTriGraphFlightFirstSecDeviation(double triGraphFlightFirstSecDeviation) {
		this.triGraphFlightFirstSecDeviation = triGraphFlightFirstSecDeviation;
	}
	public double getTriGraphFlightSecThirdDeviation() {
		return triGraphFlightSecThirdDeviation;
	}
	public void setTriGraphFlightSecThirdDeviation(double triGraphFlightSecThirdDeviation) {
		this.triGraphFlightSecThirdDeviation = triGraphFlightSecThirdDeviation;
	}
	public double getTriGraphPressToPressDeviation() {
		return triGraphPressToPressDeviation;
	}
	public void setTriGraphPressToPressDeviation(double triGraphPressToPressDeviation) {
		this.triGraphPressToPressDeviation = triGraphPressToPressDeviation;
	}
	public double getTriGraphKeyDwellDeviation() {
		return triGraphKeyDwellDeviation;
	}
	public void setTriGraphKeyDwellDeviation(double triGraphKeyDwellDeviation) {
		this.triGraphKeyDwellDeviation = triGraphKeyDwellDeviation;
	}
	public double getTriGraphDurationDeviation() {
		return triGraphDurationDeviation;
	}
	public void setTriGraphDurationDeviation(double triGraphDurationDeviation) {
		this.triGraphDurationDeviation = triGraphDurationDeviation;
	}
	public double getGraphPressToPressMean() {
		return graphPressToPressMean;
	}
	public void setGraphPressToPressMean(double graphPressToPressMean) {
		this.graphPressToPressMean = graphPressToPressMean;
	}
	public double getGraphKeyDwellMean() {
		return graphKeyDwellMean;
	}
	public void setGraphKeyDwellMean(double graphKeyDwellMean) {
		this.graphKeyDwellMean = graphKeyDwellMean;
	}
	public double getGraphWordDurationMean() {
		return graphWordDurationMean;
	}
	public void setGraphWordDurationMean(double graphWordDurationMean) {
		this.graphWordDurationMean = graphWordDurationMean;
	}
	public double getGraphPressToPressDeviation() {
		return graphPressToPressDeviation;
	}
	public void setGraphPressToPressDeviation(double graphPressToPressDeviation) {
		this.graphPressToPressDeviation = graphPressToPressDeviation;
	}
	public double getGraphKeyDwellDeviation() {
		return graphKeyDwellDeviation;
	}
	public void setGraphKeyDwellDeviation(double graphKeyDwellDeviation) {
		this.graphKeyDwellDeviation = graphKeyDwellDeviation;
	}
	public double getGraphWordDurationDeviation() {
		return graphWordDurationDeviation;
	}
	public void setGraphWordDurationDeviation(double graphWordDurationDeviation) {
		this.graphWordDurationDeviation = graphWordDurationDeviation;
	}
	public double getMauseSpeed() {
		return mauseSpeed;
	}
	public void setMauseSpeed(double mauseSpeed) {
		this.mauseSpeed = mauseSpeed;
	}
	public double getHorizontalToTotalRatio() {
		return horizontalToTotalRatio;
	}
	public void setHorizontalToTotalRatio(double horizontalToTotalRatio) {
		this.horizontalToTotalRatio = horizontalToTotalRatio;
	}
	public double getVerticalToTotalRatio() {
		return verticalToTotalRatio;
	}
	public void setVerticalToTotalRatio(double verticalToTotalRatio) {
		this.verticalToTotalRatio = verticalToTotalRatio;
	}
	public double getClickToClickDistanceToTotalPathLengthRatioMean() {
		return clickToClickDistanceToTotalPathLengthRatioMean;
	}
	public void setClickToClickDistanceToTotalPathLengthRatioMean(double clickToClickDistanceToTotalPathLengthRatioMean) {
		this.clickToClickDistanceToTotalPathLengthRatioMean = clickToClickDistanceToTotalPathLengthRatioMean;
	}
	public double getClickToClickDistanceToTotalPathLengthRatioDeviation() {
		return clickToClickDistanceToTotalPathLengthRatioDeviation;
	}
	public void setClickToClickDistanceToTotalPathLengthRatioDeviation(double clickToClickDistanceToTotalPathLengthRatioDeviation) {
		this.clickToClickDistanceToTotalPathLengthRatioDeviation = clickToClickDistanceToTotalPathLengthRatioDeviation;
	}
}
