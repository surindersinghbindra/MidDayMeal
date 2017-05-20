package com.mdm.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mdm.BR;
import com.mdm.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;

import java.util.Date;

/**
 * Created by think360 on 15/03/17.
 */
@Table(database = AppDatabase.class)
public class RawData extends BaseObservable {

    @Unique
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;
    @Column
    public long time;
    @Column
    public Date dayOfMonth;
    @Column(defaultValue = "0")
    public int dayCode;
    @Column(defaultValue = "0")
    public double mirch = 0;
    @Column(defaultValue = "0")
    double strength;
    @Column(defaultValue = "0")
    int day;
    @Column(defaultValue = "0")
    int month;
    @Column(defaultValue = "0")
    int year;
    //Money
    @Column(defaultValue = "0")
    int openingBalanceMoney;
    @Column(defaultValue = "0")
    int recievedBalanceMoney;
    @Column(defaultValue = "0")
    int todayExpensesMoney;
    @Column(defaultValue = "0")
    int finalTodayBalanceMoney;
    //Wheat
    @Column(defaultValue = "0")
    double openingBalanceWheat;
    @Column(defaultValue = "0")
    double recievedBalanceWheat;
    @Column(defaultValue = "0")
    double consumeTodayBalanceWheat;
    @Column(defaultValue = "0")
    double finalTodayBalanceWheat;
    //Rice
    @Column(defaultValue = "0")
    double openingBalanceRice;
    @Column(defaultValue = "0")
    double recievedBalanceRice;
    @Column(defaultValue = "0")
    double cousumeTodayRice;
    @Column(defaultValue = "0")
    double finalBalanceRice;
    @Column(defaultValue = "0")
    double dallChana = 0;
    @Column(defaultValue = "0")
    double sabutMungi = 0;
    @Column(defaultValue = "0")
    double refinedOil = 0;
    @Column(defaultValue = "0")
    double jeera = 0;
    @Column(defaultValue = "0")
    double haldi = 0;
    @Column(defaultValue = "0")
    double garamMasala = 0;
    @Column(defaultValue = "0")
    double namak = 0;
    @Column(defaultValue = "0")
    double khoppa = 0;
    @Column(defaultValue = "0")
    double dakha = 0;
    @Column(defaultValue = "0")
    double kaalCholle = 0;
    @Column(defaultValue = "0")
    double bessan = 0;
    @Column(defaultValue = "0")
    double dahi = 0;
    @Column(defaultValue = "0")
    double pyaaz = 0;
    @Column(defaultValue = "0")
    double khandd = 0;
    @Column(defaultValue = "0")
    double tamattar = 0;
    @Column(defaultValue = "0")
    double sabzi = 0;
    @Column(defaultValue = "0")
    double aloo = 0;
    @Column(defaultValue = "0")
    double mausamiSabzi = 0;
    @Column(defaultValue = "0")
    double kanakPissai = 0;
    @Column(defaultValue = "0")
    double dudh = 0;
    @Column(defaultValue = "0")
    double totalCondiment = 0;
    @Column(defaultValue = "0")
    double ballan = 0;
    @Column(defaultValue = "0")
    double total = 0;

    public RawData() {

    }

    public RawData(int recievedBalanceMoney, double recievedBalanceWheat, double recievedBalanceRice, int dayCode, long time) {
        this.dayCode = dayCode;
        this.time = time;
        this.recievedBalanceMoney = recievedBalanceMoney;
        this.recievedBalanceWheat = recievedBalanceWheat;
        this.recievedBalanceRice = recievedBalanceRice;
        notifyPropertyChanged(BR.strength);

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Bindable({"dayCode"})
    public int getDayCode() {
        return dayCode;
    }

    public void setDayCode(int dayCode) {
        this.dayCode = dayCode;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Date dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Bindable
    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
        notifyPropertyChanged(BR.strength);
    }

    public int getOpeningBalanceMoney() {
        return openingBalanceMoney;
    }

    public void setOpeningBalanceMoney(int openingBalanceMoney) {
        this.openingBalanceMoney = openingBalanceMoney;
    }

    public int getRecievedBalanceMoney() {
        return recievedBalanceMoney;
    }

    public void setRecievedBalanceMoney(int recievedBalanceMoney) {
        this.recievedBalanceMoney = recievedBalanceMoney;
    }

    public int getTodayExpensesMoney() {
        return todayExpensesMoney;
    }

    public void setTodayExpensesMoney(int todayExpensesMoney) {
        this.todayExpensesMoney = todayExpensesMoney;
    }

    public int getFinalTodayBalanceMoney() {
        return finalTodayBalanceMoney;
    }

    //Others

    public void setFinalTodayBalanceMoney(int finalTodayBalanceMoney) {
        this.finalTodayBalanceMoney = finalTodayBalanceMoney;
    }

    public double getOpeningBalanceWheat() {
        return openingBalanceWheat;
    }

    public void setOpeningBalanceWheat(double openingBalanceWheat) {
        this.openingBalanceWheat = openingBalanceWheat;
    }

    public double getRecievedBalanceWheat() {
        return recievedBalanceWheat;
    }

    public void setRecievedBalanceWheat(double recievedBalanceWheat) {
        this.recievedBalanceWheat = recievedBalanceWheat;
    }

    public double getConsumeTodayBalanceWheat() {
        return consumeTodayBalanceWheat;
    }

    public void setConsumeTodayBalanceWheat(double consumeTodayBalanceWheat) {
        this.consumeTodayBalanceWheat = consumeTodayBalanceWheat;
    }

    public double getFinalTodayBalanceWheat() {
        return finalTodayBalanceWheat;
    }

    public void setFinalTodayBalanceWheat(double finalTodayBalanceWheat) {
        this.finalTodayBalanceWheat = finalTodayBalanceWheat;
    }

    public double getOpeningBalanceRice() {
        return openingBalanceRice;
    }

    public void setOpeningBalanceRice(double openingBalanceRice) {
        this.openingBalanceRice = openingBalanceRice;
    }

    public double getRecievedBalanceRice() {
        return recievedBalanceRice;
    }

    public void setRecievedBalanceRice(double recievedBalanceRice) {
        this.recievedBalanceRice = recievedBalanceRice;
    }

    public double getCousumeTodayRice() {
        return cousumeTodayRice;
    }

    public void setCousumeTodayRice(double cousumeTodayRice) {
        this.cousumeTodayRice = cousumeTodayRice;
    }

    public double getFinalBalanceRice() {
        return finalBalanceRice;
    }

    public void setFinalBalanceRice(double finalBalanceRice) {
        this.finalBalanceRice = finalBalanceRice;
    }

    @Bindable({"strength"})
    public double getDallChana() {
        if (dayCode == 1 | dayCode == 6) {
            dallChana = strength * 1.54;
        } else {
            dallChana = 0.0;
        }
        return dallChana;
    }

    public void setDallChana(double dallChana) {
        this.dallChana = dallChana;
    }

    @Bindable({"strength"})
    public double getSabutMungi() {
        if (dayCode == 2) {
            sabutMungi = strength * 1.85;
        } else {
            sabutMungi = 0.0;
        }

        return sabutMungi;
    }

    public void setSabutMungi(double sabutMungi) {
        this.sabutMungi = sabutMungi;
    }

    @Bindable({"strength"})
    public double getRefinedOil() {

        if (dayCode == 1 || dayCode == 2 || dayCode == 3 || dayCode == 6) {
            refinedOil = strength * 0.75;
        } else if (dayCode == 4) {
            refinedOil = strength * 0.80;
        } else if (dayCode == 5) {
            refinedOil = strength * 0.70;
        } else {
            refinedOil = 0.0;
        }

        return refinedOil;
    }

    public void setRefinedOil(double refinedOil) {
        this.refinedOil = refinedOil;
    }

    @Bindable({"strength"})
    public double getMirch() {
        mirch = strength * .10;
        return mirch;
    }

    public void setMirch(double mirch) {
        this.mirch = mirch;
    }

    @Bindable({"strength"})
    public double getJeera() {

        if (dayCode == 2 || dayCode == 4) {
            jeera = strength * 0.07;
        } else if (dayCode == 6) {
            jeera = strength * 0.10;
        } else {
            jeera = 0.0;
        }
        return jeera;
    }

    public void setJeera(double jeera) {
        this.jeera = jeera;
    }

    @Bindable({"strength"})
    public double getHaldi() {

        if (dayCode == 1 || dayCode == 2 || dayCode == 3 || dayCode == 4 || dayCode == 6) {
            haldi = strength * 0.10;
        } else if (dayCode == 5) {
            haldi = strength * 0.08;
        } else {
            haldi = 0.0;
        }

        return haldi;
    }

    public void setHaldi(double haldi) {
        this.haldi = haldi;
    }

    @Bindable({"strength"})
    public double getGaramMasala() {
        if (dayCode == 1 || dayCode == 2 || dayCode == 3 || dayCode == 5) {
            garamMasala = strength * 0.04;
        } else if (dayCode == 6) {
            garamMasala = strength * 0.05;
        } else {
            garamMasala = 0.0;
        }

        return garamMasala;
    }

    public void setGaramMasala(double garamMasala) {
        this.garamMasala = garamMasala;
    }

    @Bindable({"strength"})
    public double getNamak() {
        namak = strength * 0.05;
        return namak;
    }

    public void setNamak(double namak) {
        this.namak = namak;
    }

    @Bindable({"strength"})
    public double getKhoppa() {
        if (dayCode == 5) {
            khoppa = strength * 0.10;
        } else {
            khoppa = 0.0;
        }
        return khoppa;
    }

    public void setKhoppa(double khoppa) {
        this.khoppa = khoppa;
    }

    @Bindable({"strength"})
    public double getDakha() {

        if (dayCode == 5) {
            dakha = strength * 0.10;
        } else {
            dakha = 0.0;
        }
        return dakha;
    }

    public void setDakha(double dakha) {
        this.dakha = dakha;
    }

    @Bindable({"strength"})
    public double getKaalCholle() {


        if (dayCode == 3) {
            kaalCholle = strength * 1.50;
        } else {
            kaalCholle = 0.0;
        }
        return kaalCholle;
    }

    public void setKaalCholle(double kaalCholle) {
        this.kaalCholle = kaalCholle;
    }

    @Bindable({"strength"})
    public double getBessan() {

        if (dayCode == 4) {
            bessan = strength * 1.20;
        } else {
            bessan = 0.0;
        }
        return bessan;
    }

    public void setBessan(double bessan) {
        this.bessan = bessan;
    }

    @Bindable({"strength"})
    public double getDahi() {
        if (dayCode == 4) {
            dahi = strength * 0.50;
        } else {
            dahi = 0.0;
        }
        return dahi;
    }

    public void setDahi(double dahi) {
        this.dahi = dahi;
    }

    @Bindable({"strength"})
    public double getKhandd() {
        if (dayCode == 5) {
            khandd = strength * 0.30;
        } else {
            khandd = 0.0;
        }
        return khandd;

    }

    public void setKhandd(double khandd) {
        this.khandd = khandd;
    }

    @Bindable({"strength"})
    public double getPyaaz() {

        if (dayCode == 1 || dayCode == 2 || dayCode == 3 || dayCode == 4 || dayCode == 6) {
            pyaaz = strength * 0.20;
        } else if (dayCode == 5) {
            pyaaz = strength * 0.18;
        } else {
            pyaaz = 0.0;
        }

        return pyaaz;
    }

    public void setPyaaz(double pyaaz) {
        this.pyaaz = pyaaz;
    }

    @Bindable({"strength"})
    public double getTamattar() {

        if (dayCode == 1) {
            tamattar = strength * 0.15;
        } else if (dayCode == 2) {
            tamattar = strength * 0.17;
        } else if (dayCode == 3) {
            tamattar = strength * 0.20;
        } else if (dayCode == 4) {
            tamattar = strength * 0.16;
        } else if (dayCode == 5) {
            tamattar = strength * 0.10;
        } else if (dayCode == 6) {
            tamattar = 0.19;
        } else {
            tamattar = 0.0;
        }

        return tamattar;
    }

    public void setTamattar(double tamattar) {
        this.tamattar = tamattar;
    }

    @Bindable({"strength"})
    public double getSabzi() {

        if (dayCode == 5) {
            sabzi = strength * 0.64;
        } else {
            sabzi = 0.0;
        }

        return sabzi;
    }

    public void setSabzi(double sabzi) {
        this.sabzi = sabzi;
    }

    @Bindable({"strength"})
    public double getAloo() {

        if (dayCode == 3) {
            aloo = strength * 0.24;
        } else if (dayCode == 4) {
            aloo = strength * 0.15;
        } else {
            aloo = 0.0;
        }

        return aloo;
    }

    public void setAloo(double aloo) {
        this.aloo = aloo;
    }

    @Bindable({"strength"})
    public double getMausamiSabzi() {
        if (dayCode == 1 || dayCode == 6) {
            mausamiSabzi = strength * 0.25;
        } else {
            mausamiSabzi = 0.0;
        }
        return mausamiSabzi;
    }

    public void setMausamiSabzi(double mausamiSabzi) {
        this.mausamiSabzi = mausamiSabzi;
    }

    @Bindable({"strength"})
    public double getKanakPissai() {

        if (dayCode == 1 || dayCode == 3 || dayCode == 5) {
            kanakPissai = strength * 0.15;
        } else {
            kanakPissai = 0.0;
        }
        return kanakPissai;
    }

    public void setKanakPissai(double kanakPissai) {
        this.kanakPissai = kanakPissai;
    }

    @Bindable({"strength"})
    public double getDudh() {
        if (dayCode == 5) {
            dudh = strength * 0.79;
        } else {
            dudh = 0.0;
        }
        return dudh;
    }

    public void setDudh(double dudh) {
        this.dudh = dudh;
    }

    @Bindable({"strength"})
    public double getTotalCondiment() {
        return totalCondiment;
    }

    public void setTotalCondiment(double totalCondiment) {
        this.totalCondiment = totalCondiment;
    }

    @Bindable({"strength"})
    public double getBallan() {
        ballan = 0.80 * strength;
        return ballan;
    }

    public void setBallan(double ballan) {
        this.ballan = ballan;
    }

    @Bindable({"strength"})
    public double getTotal() {
        return ((getDallChana() + getSabutMungi() + getRefinedOil() + getMirch() + getJeera() + getHaldi() + getGaramMasala() + getNamak() + getKhoppa() + getDakha() + getKaalCholle() + getBessan() + getDahi() + getKhandd() + getPyaaz() + getTamattar() + getSabzi() + getAloo() + getMausamiSabzi() + getKanakPissai() + getDudh() + getBallan()));

    }


}
