package com.sliit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sliit.model.*;
import com.sliit.util.DBConnection;

public class AlbumServiceImp implements Albumservice{
	
	private int size;
	private int count;
	private ArrayList<Albums> albums = null;
	private boolean status;
	private int albumId;
	private int tmp_albumId;
	private String albumName;
	private String albumDescription;

	
	@Override
	public boolean addAlbum(Albums alb) throws ClassNotFoundException, SQLException {
		
		int Size = 0;
		ArrayList<Integer> idArray = new ArrayList<Integer>();

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select * from Albums");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			idArray.add(rs.getInt(1));
			++Size;
		}

		this.size = Size;
		this.size += 1;
		
		PreparedStatement ps1 = DBConnection.getTmpConnection().prepareStatement("select albumId from album_tmp");
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			if (rs1.getInt(1) > 0) {
				this.tmp_albumId = rs1.getInt(1);

			} else
				this.tmp_albumId = -1;
		}

		if (tmp_albumId > 0) {
			this.albumId = this.tmp_albumId;
		} else {
			this.albumId = this.size;
		}
		PreparedStatement ps2 = DBConnection.getMainConnection().prepareStatement("insert into Albums values("
				+ this.albumId + ", '" + alb.getAlbumName() + "', '" + alb.getAlbumDescription() + "')");
		int res = ps2.executeUpdate();

		if (res > 0) {
			if (this.tmp_albumId > 0) {
				PreparedStatement ps3 = DBConnection.getTmpConnection()
						.prepareStatement("delete from album_tmp where albumId = " + this.albumId);
				ps3.executeUpdate();
			}
			this.status = true;
		} else {
			this.status = false;
		}
		
		return status;
	}

	@Override
	public boolean updateAlbum(Albums alb) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("update Albums set AlbumName = '" + alb.getAlbumName() + "', AlbumDescription = '"
						+ alb.getAlbumDescription() + "' where AlbumId = '" + alb.getAlbumId() + "'");
		int res = ps.executeUpdate();
		if (res > 0)
			status = true;
		else
			status = false;
		
		return status;
		

	}

	@Override
	public boolean removeAlbum(int albumId) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("Delete from Albums where albumId = " + albumId);
		int result = ps.executeUpdate();

		if (result > 0) {

			PreparedStatement ps1 = DBConnection.getTmpConnection()
					.prepareStatement("Insert into album_tmp(albumId) values(" + albumId + ")");
			int tmpResult = ps1.executeUpdate();

			if (tmpResult > 0)
				status = true;
			else
				status = false;
		} else
			status = false;
		return status;
	}

	@Override
	public ArrayList<Albums> searchAlbum(Albums alb) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Albums> getAllAlbums() throws SQLException, ClassNotFoundException {

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select * from Albums");
		ResultSet result = ps.executeQuery();

		ArrayList<Albums> album = new ArrayList<Albums>();

		while (result.next()) {
			albumId = result.getInt(1);
			albumName = result.getString(2);
			albumDescription = result.getString(3);

			album.add(new Albums(albumId, albumName, albumDescription));
			++size;
		}

		if (size > 0) {
			this.albums = album;
		} else
			this.albums = null;
		return this.albums;
	}
	
	@Override
	public int countAlbums() throws ClassNotFoundException, SQLException {
		
		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select count(*) from Albums");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
			count = rs.getInt(1);
		
		if(count == 0)
			count = -1;
		
		return count;
		
	}
	
	
	
}
