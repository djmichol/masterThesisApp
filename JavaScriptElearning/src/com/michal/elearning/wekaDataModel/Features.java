package com.michal.elearning.wekaDataModel;

public class Features {

	// mause
	private int mauseClicksMean;
	private int mauseClickDeviation;
	private int mauseCliskEventCount;
	// SigleKey
	private int tabFreq;
	private int delFreq;
	private int enterFreq;
	private int backSpaceFreq;
	private int spaceFreq;
	private double writingSpeed;
	private int singleKeyDwellMean;
	private int singleKeyDwellDeviation;
	// digraphs
	private int diGraphFirstDwellMean;
	private int diGraphSecondDwellMean;
	private int diGraphPressToPressMean;
	private int diGraphFlightMean;
	private int digraphDurationMean;
	private int diGraphFirstDwellDeviation;
	private int diGraphSecondDwellDeviation;
	private int diGraphPressToPressDeviation;
	private int diGraphFlightDeviation;
	private int digraphDurationDeviation;
	// trigraphs
	private int triGraphDwellFirstMean;
	private int triGraphDwellSecondMean;
	private int triGraphDwellThirdMean;
	private int triGraphPressFirstSecMean;
	private int triGraphPressSecThirdMean;
	private int triGraphFlightFirstSecMean;
	private int triGraphFlightSecThirdMean;
	private int triGraphPressToPressMean;
	private int triGraphKeyDwellMean;
	private int triGraphDurationMean;
	private int triGraphDwellFirstDeviation;
	private int triGraphDwellSecondDeviation;
	private int triGraphDwellThirdDeviation;
	private int triGraphPressFirstSecDeviation;
	private int triGraphPressSecThirdDeviation;
	private int triGraphFlightFirstSecDeviation;
	private int triGraphFlightSecThirdDeviation;
	private int triGraphPressToPressDeviation;
	private int triGraphKeyDwellDeviation;
	private int triGraphDurationDeviation;
	// graphs
	private int graphPressToPressMean;
	private int graphKeyDwellMean;
	private int graphWordDurationMean;
	private int graphPressToPressDeviation;
	private int graphKeyDwellDeviation;
	private int graphWordDurationDeviation;

	// Settery i gettery
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

	public void setWritingSpeed(double writigSpeed) {
		this.writingSpeed = writigSpeed;
	}

	public int getSingleKeyDwellMean() {
		return singleKeyDwellMean;
	}

	public void setSingleKeyDwellMean(int sigleKeyDwellMean) {
		this.singleKeyDwellMean = sigleKeyDwellMean;
	}

	public int getSingleKeyDwellDeviation() {
		return singleKeyDwellDeviation;
	}

	public void setSingleKeyDwellDeviation(int sigleKeyDwellDeviation) {
		this.singleKeyDwellDeviation = sigleKeyDwellDeviation;
	}

	public int getDiGraphFirstDwellMean() {
		return diGraphFirstDwellMean;
	}

	public void setDiGraphFirstDwellMean(int diGraphFirstDwellMean) {
		this.diGraphFirstDwellMean = diGraphFirstDwellMean;
	}

	public int getDiGraphSecondDwellMean() {
		return diGraphSecondDwellMean;
	}

	public void setDiGraphSecondDwellMean(int diGraphSecondDwellMean) {
		this.diGraphSecondDwellMean = diGraphSecondDwellMean;
	}

	public int getDiGraphPressToPressMean() {
		return diGraphPressToPressMean;
	}

	public void setDiGraphPressToPressMean(int diGraphPressToPressMean) {
		this.diGraphPressToPressMean = diGraphPressToPressMean;
	}

	public int getDiGraphFlightMean() {
		return diGraphFlightMean;
	}

	public void setDiGraphFlightMean(int diGraphFlightMean) {
		this.diGraphFlightMean = diGraphFlightMean;
	}

	public int getDigraphDurationMean() {
		return digraphDurationMean;
	}

	public void setDigraphDurationMean(int digraphDurationMean) {
		this.digraphDurationMean = digraphDurationMean;
	}

	public int getDiGraphFirstDwellDeviation() {
		return diGraphFirstDwellDeviation;
	}

	public void setDiGraphFirstDwellDeviation(int diGraphFirstDwellDeviation) {
		this.diGraphFirstDwellDeviation = diGraphFirstDwellDeviation;
	}

	public int getDiGraphSecondDwellDeviation() {
		return diGraphSecondDwellDeviation;
	}

	public void setDiGraphSecondDwellDeviation(int diGraphSecondDwellDeviation) {
		this.diGraphSecondDwellDeviation = diGraphSecondDwellDeviation;
	}

	public int getDiGraphPressToPressDeviation() {
		return diGraphPressToPressDeviation;
	}

	public void setDiGraphPressToPressDeviation(int diGraphPressToPressDeviation) {
		this.diGraphPressToPressDeviation = diGraphPressToPressDeviation;
	}

	public int getDiGraphFlightDeviation() {
		return diGraphFlightDeviation;
	}

	public void setDiGraphFlightDeviation(int diGraphFlightDeviation) {
		this.diGraphFlightDeviation = diGraphFlightDeviation;
	}

	public int getDigraphDurationDeviation() {
		return digraphDurationDeviation;
	}

	public void setDigraphDurationDeviation(int digraphDurationDeviation) {
		this.digraphDurationDeviation = digraphDurationDeviation;
	}

	public int getTriGraphDwellFirstMean() {
		return triGraphDwellFirstMean;
	}

	public void setTriGraphDwellFirstMean(int triGraphDwellFirstMean) {
		this.triGraphDwellFirstMean = triGraphDwellFirstMean;
	}

	public int getTriGraphDwellSecondMean() {
		return triGraphDwellSecondMean;
	}

	public void setTriGraphDwellSecondMean(int triGraphDwellSecondMean) {
		this.triGraphDwellSecondMean = triGraphDwellSecondMean;
	}

	public int getTriGraphDwellThirdMean() {
		return triGraphDwellThirdMean;
	}

	public void setTriGraphDwellThirdMean(int triGraphDwellThirdMean) {
		this.triGraphDwellThirdMean = triGraphDwellThirdMean;
	}

	public int getTriGraphPressFirstSecMean() {
		return triGraphPressFirstSecMean;
	}

	public void setTriGraphPressFirstSecMean(int triGraphPressFirstSecMean) {
		this.triGraphPressFirstSecMean = triGraphPressFirstSecMean;
	}

	public int getTriGraphPressSecThirdMean() {
		return triGraphPressSecThirdMean;
	}

	public void setTriGraphPressSecThirdMean(int triGraphPressSecThirdMean) {
		this.triGraphPressSecThirdMean = triGraphPressSecThirdMean;
	}

	public int getTriGraphFlightFirstSecMean() {
		return triGraphFlightFirstSecMean;
	}

	public void setTriGraphFlightFirstSecMean(int triGraphFlightFirstSecMean) {
		this.triGraphFlightFirstSecMean = triGraphFlightFirstSecMean;
	}

	public int getTriGraphFlightSecThirdMean() {
		return triGraphFlightSecThirdMean;
	}

	public void setTriGraphFlightSecThirdMean(int triGraphFlightSecThirdMean) {
		this.triGraphFlightSecThirdMean = triGraphFlightSecThirdMean;
	}

	public int getTriGraphPressToPressMean() {
		return triGraphPressToPressMean;
	}

	public void setTriGraphPressToPressMean(int triGraphPressToPressMean) {
		this.triGraphPressToPressMean = triGraphPressToPressMean;
	}

	public int getTriGraphKeyDwellMean() {
		return triGraphKeyDwellMean;
	}

	public void setTriGraphKeyDwellMean(int triGraphKeyDwellMean) {
		this.triGraphKeyDwellMean = triGraphKeyDwellMean;
	}

	public int getTriGraphDurationMean() {
		return triGraphDurationMean;
	}

	public void setTriGraphDurationMean(int triGraphDurationMean) {
		this.triGraphDurationMean = triGraphDurationMean;
	}

	public int getTriGraphDwellFirstDeviation() {
		return triGraphDwellFirstDeviation;
	}

	public void setTriGraphDwellFirstDeviation(int triGraphDwellFirstDeviation) {
		this.triGraphDwellFirstDeviation = triGraphDwellFirstDeviation;
	}

	public int getTriGraphDwellSecondDeviation() {
		return triGraphDwellSecondDeviation;
	}

	public void setTriGraphDwellSecondDeviation(int triGraphDwellSecondDeviation) {
		this.triGraphDwellSecondDeviation = triGraphDwellSecondDeviation;
	}

	public int getTriGraphDwellThirdDeviation() {
		return triGraphDwellThirdDeviation;
	}

	public void setTriGraphDwellThirdDeviation(int triGraphDwellThirdDeviation) {
		this.triGraphDwellThirdDeviation = triGraphDwellThirdDeviation;
	}

	public int getTriGraphPressFirstSecDeviation() {
		return triGraphPressFirstSecDeviation;
	}

	public void setTriGraphPressFirstSecDeviation(int triGraphPressFirstSecDeviation) {
		this.triGraphPressFirstSecDeviation = triGraphPressFirstSecDeviation;
	}

	public int getTriGraphPressSecThirdDeviation() {
		return triGraphPressSecThirdDeviation;
	}

	public void setTriGraphPressSecThirdDeviation(int triGraphPressSecThirdDeviation) {
		this.triGraphPressSecThirdDeviation = triGraphPressSecThirdDeviation;
	}

	public int getTriGraphFlightFirstSecDeviation() {
		return triGraphFlightFirstSecDeviation;
	}

	public void setTriGraphFlightFirstSecDeviation(int triGraphFlightFirstSecDeviation) {
		this.triGraphFlightFirstSecDeviation = triGraphFlightFirstSecDeviation;
	}

	public int getTriGraphFlightSecThirdDeviation() {
		return triGraphFlightSecThirdDeviation;
	}

	public void setTriGraphFlightSecThirdDeviation(int triGraphFlightSecThirdDeviation) {
		this.triGraphFlightSecThirdDeviation = triGraphFlightSecThirdDeviation;
	}

	public int getTriGraphPressToPressDeviation() {
		return triGraphPressToPressDeviation;
	}

	public void setTriGraphPressToPressDeviation(int triGraphPressToPressDeviation) {
		this.triGraphPressToPressDeviation = triGraphPressToPressDeviation;
	}

	public int getTriGraphKeyDwellDeviation() {
		return triGraphKeyDwellDeviation;
	}

	public void setTriGraphKeyDwellDeviation(int triGraphKeyDwellDeviation) {
		this.triGraphKeyDwellDeviation = triGraphKeyDwellDeviation;
	}

	public int getTriGraphDurationDeviation() {
		return triGraphDurationDeviation;
	}

	public void setTriGraphDurationDeviation(int triGraphDurationDeviation) {
		this.triGraphDurationDeviation = triGraphDurationDeviation;
	}

	public int getGraphPressToPressMean() {
		return graphPressToPressMean;
	}

	public void setGraphPressToPressMean(int graphPressToPressMean) {
		this.graphPressToPressMean = graphPressToPressMean;
	}

	public int getGraphKeyDwellMean() {
		return graphKeyDwellMean;
	}

	public void setGraphKeyDwellMean(int graphKeyDwellMean) {
		this.graphKeyDwellMean = graphKeyDwellMean;
	}

	public int getGraphWordDurationMean() {
		return graphWordDurationMean;
	}

	public void setGraphWordDurationMean(int graphWordDurationMean) {
		this.graphWordDurationMean = graphWordDurationMean;
	}

	public int getGraphPressToPressDeviation() {
		return graphPressToPressDeviation;
	}

	public void setGraphPressToPressDeviation(int graphPressToPressDeviation) {
		this.graphPressToPressDeviation = graphPressToPressDeviation;
	}

	public int getGraphKeyDwellDeviation() {
		return graphKeyDwellDeviation;
	}

	public void setGraphKeyDwellDeviation(int graphKeyDwellDeviation) {
		this.graphKeyDwellDeviation = graphKeyDwellDeviation;
	}

	public int getGraphWordDurationDeviation() {
		return graphWordDurationDeviation;
	}

	public void setGraphWordDurationDeviation(int graphWordDurationDeviation) {
		this.graphWordDurationDeviation = graphWordDurationDeviation;
	}
}
