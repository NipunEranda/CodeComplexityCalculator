package com.sliit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sliit.model.*;
import com.sliit.util.DBConnection;

public class ArtistServiceImp implements Artistservice {

	private int size;
	private int count;
	private ArrayList<Artists> artist;
	private boolean status;
	private int albumId;
	private int artistId;
	private int tmp_artistId;
	private String artistDescription;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private String password;

	@Override
	public boolean addArtist(Artists art) throws ClassNotFoundException, SQLException {

		int Size = 0;
		PreparedStatement ps1 = DBConnection.getTmpConnection()
				.prepareStatement("select min(artistId) from artist_tmp");
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			if (rs1.getInt(1) > 0) {
				this.tmp_artistId = rs1.getInt(1);
			} else {
				this.tmp_artistId = -1;
			}
		}

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select * from Artists");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			++Size;
		}
		
		Size += 1;

		if (this.tmp_artistId > 0)
			this.artistId = this.tmp_artistId;
		else
			this.artistId = Size;

		PreparedStatement ps2 = DBConnection.getMainConnection().prepareStatement(
				"insert into Artists(ArtistId, userName, firstName, lastName, email, password, telephone, address, ArtistDescription) values(" + this.artistId + ", '"
						+ art.getUserName() + "', '" + art.getFirstName() + "', '" + art.getLastName() + "', '"
						+ art.getEmail() + "', '" + art.getPassword() + "', '" + art.getTelephone() + "', '"
						+ art.getAddress() + "', '" + art.getArtistDescription() + "')");
		int res = ps2.executeUpdate();

		if (res > 0) {

			if (this.tmp_artistId > 0) {
				PreparedStatement ps3 = DBConnection.getTmpConnection()
						.prepareStatement("delete from artist_tmp where artistId = " + this.artistId);
				ps3.executeUpdate();
			}
			status = true;
		} else {
			status = false;
		}

		return status;
	}

	@Override
	public boolean updateArtist(Artists art) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("update Artists set userName = '" + art.getUserName() + "', firstName = '"
						+ art.getFirstName() + "', lastName = '" + art.getLastName() + "', ArtistDescription = '"
						+ art.getArtistDescription() + "', email = '" + art.getEmail() + "', telephone = '"
						+ art.getTelephone() + "', address = '" + art.getAddress() + "'  where ArtistId = "
						+ art.getuId());
		int res = ps.executeUpdate();
		if (res > 0) {
			status = true;
		} else {
			status = false;
		}
		return status;

	}

	@Override
	public boolean removeArtist(int artistId) throws ClassNotFoundException, SQLException {

		boolean status = true;

		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("Delete from Artists where artistId = " + artistId);
		int result = ps.executeUpdate();

		if (result > 0) {

			PreparedStatement ps1 = DBConnection.getTmpConnection()
					.prepareStatement("Insert into artist_tmp(artistId) values(" + artistId + ")");
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
	public ArrayList<Artists> getAllArtists() throws SQLException, ClassNotFoundException {

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select * from Artists");
		ResultSet result = ps.executeQuery();

		ArrayList<Artists> artist = new ArrayList<Artists>();

		while (result.next()) {
			artistId = result.getInt(1);
			userName = result.getString(2);
			firstName = result.getString(3);
			lastName = result.getString(4);
			artistDescription = result.getString(5);
			email = result.getString(6);
			password = result.getString(7);
			telephone = result.getString(9);
			address = result.getString(10);

			artist.add(new Artists(artistId, userName, firstName, lastName, email, password, telephone, address,
					artistDescription));
			++size;
		}

		if (size > 0) {
			this.artist = artist;
		} else
			this.artist = null;
		return artist;
	}

	@Override
	public int countArtists() throws ClassNotFoundException, SQLException {

		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select count(*) from Artists");
		ResultSet rs = ps.executeQuery();

		while (rs.next())
			count = rs.getInt(1);

		if (count == 0)
			count = -1;

		return count;

	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<Artists> searchArtist(String artistName) throws ClassNotFoundException, SQLException {
		
		PreparedStatement ps = DBConnection.getMainConnection()
				.prepareStatement("select * from Artists where userName LIKE '%" + artistName + "%'");
		ResultSet result = ps.executeQuery();

		ArrayList<Artists> artist = new ArrayList<Artists>();

		while (result.next()) {
			artistId = result.getInt(1);
			userName = result.getString(2);
			firstName = result.getString(3);
			lastName = result.getString(4);
			artistDescription = result.getString(5);
			email = result.getString(6);
			password = result.getString(7);
			telephone = result.getString(9);
			address = result.getString(10);

			artist.add(new Artists(artistId, userName, firstName, lastName, email, password, telephone, address,
					artistDescription));
			++size;
		}

		if (size > 0) {
			this.artist = artist;
		} else
			this.artist = null;
		return artist;

	}

	@Override
	public String getArtistName(int artistId) throws ClassNotFoundException, SQLException {

		
		PreparedStatement ps = DBConnection.getMainConnection().prepareStatement("select userName from Artists where ArtistId =" + artistId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			this.userName = rs.getString(1);
		}
		
		return userName;
	}
}
