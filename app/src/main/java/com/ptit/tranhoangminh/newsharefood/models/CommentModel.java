package com.ptit.tranhoangminh.newsharefood.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Dell on 5/7/2018.
 */

public class CommentModel implements Parcelable {
    long chamdiem,luotthich;
    String noidung;
    String tieude;
    String mauser;
    MemberModel memberModel;
    List<String>listImageComment;



    public CommentModel() {
    }

    public CommentModel(long chamdiem, long luotthich, String noidung, String tieude, MemberModel memberModel) {

        this.chamdiem = chamdiem;
        this.luotthich = luotthich;
        this.noidung = noidung;
        this.tieude = tieude;
        this.memberModel = memberModel;
    }

    protected CommentModel(Parcel in) {
        chamdiem = in.readLong();
        luotthich = in.readLong();
        noidung = in.readString();
        tieude = in.readString();
        mauser = in.readString();
        listImageComment = in.createStringArrayList();
        memberModel=in.readParcelable(MemberModel.class.getClassLoader());
    }

    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel in) {
            return new CommentModel(in);
        }

        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };

    public long getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(long chamdiem) {
        this.chamdiem = chamdiem;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }

    public void setMemberModel(MemberModel memberModel) {
        this.memberModel = memberModel;
    }
    public List<String> getListImageComment() {
        return listImageComment;
    }

    public void setListImageComment(List<String> listImageComment) {
        this.listImageComment = listImageComment;
    }

    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(chamdiem);
        parcel.writeLong(luotthich);
        parcel.writeString(noidung);
        parcel.writeString(tieude);
        parcel.writeString(mauser);
        parcel.writeStringList(listImageComment);
        parcel.writeParcelable(memberModel,i);
    }
}
