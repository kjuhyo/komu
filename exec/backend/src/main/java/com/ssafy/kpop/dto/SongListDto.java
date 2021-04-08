package com.ssafy.kpop.dto;

public class SongListDto {
	
	//노래리스트보여줄때 cnt 까지 합쳐서 보여줄때 사용할 DTO
	private int id;
	private String song_name;
	private String singer_name;
	private String singer_img;
	private String album_name;
	private String album_cover;
	private String genre;
	private String issue_date;
	private int song_like_cnt;
	
	public SongListDto(int id, String song_name, String singer_name, String singer_img, String album_name,
			String album_cover, String genre, String issue_date, int song_like_cnt) {
		super();
		this.id = id;
		this.song_name = song_name;
		this.singer_name = singer_name;
		this.singer_img = singer_img;
		this.album_name = album_name;
		this.album_cover = album_cover;
		this.genre = genre;
		this.issue_date = issue_date;
		this.song_like_cnt = song_like_cnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public String getSinger_img() {
		return singer_img;
	}

	public void setSinger_img(String singer_img) {
		this.singer_img = singer_img;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getAlbum_cover() {
		return album_cover;
	}

	public void setAlbum_cover(String album_cover) {
		this.album_cover = album_cover;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public int getSong_like_cnt() {
		return song_like_cnt;
	}

	public void setSong_like_cnt(int song_like_cnt) {
		this.song_like_cnt = song_like_cnt;
	}

	@Override
	public String toString() {
		return "SongListDto [id=" + id + ", song_name=" + song_name + ", singer_name=" + singer_name + ", singer_img="
				+ singer_img + ", album_name=" + album_name + ", album_cover=" + album_cover + ", genre=" + genre
				+ ", issue_date=" + issue_date + ", song_like_cnt=" + song_like_cnt + "]";
	}
}
