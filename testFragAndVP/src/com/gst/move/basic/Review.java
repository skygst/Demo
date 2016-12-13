package com.gst.move.basic;

public class Review {
	private String teacher_name;
	private String teacher_avatar;
	private int fluency; // 流利度
	private int accuracy; // 音准 
	private int sense; // 语感
	private String text_comment;
	private String audio_comment;
	private String keyword;
	private String reviewed_at;

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getTeacher_avatar() {
		return teacher_avatar;
	}

	public void setTeacher_avatar(String teacher_avatar) {
		this.teacher_avatar = teacher_avatar;
	}

	public int getFluency() {
		return fluency;
	}

	public void setFluency(int fluency) {
		this.fluency = fluency;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getSense() {
		return sense;
	}

	public void setSense(int sense) {
		this.sense = sense;
	}

	public String getText_comment() {
		return text_comment;
	}

	public void setText_comment(String text_comment) {
		this.text_comment = text_comment;
	}

	public String getAudio_comment() {
		return audio_comment;
	}

	public void setAudio_comment(String audio_comment) {
		this.audio_comment = audio_comment;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getReviewed_at() {
		return reviewed_at;
	}

	public void setReviewed_at(String reviewed_at) {
		this.reviewed_at = reviewed_at;
	}

}
