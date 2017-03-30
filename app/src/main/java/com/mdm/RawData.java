package com.mdm;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

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
    int id;

    @Column
    Date dayOfMonth;

    @Column
    int dayCode;

    @Bindable({"dayCode"})
    public int getDayCode() {
        return dayCode;
    }

    public void setDayOfMonth(Date dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setDayCode(int dayCode) {
        this.dayCode = dayCode;
    }


    @Column
    double strength;

    public void setStrength(double strength) {
        this.strength = strength;
        notifyPropertyChanged(BR.strength);
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

    @Column
    int day;
    @Column
    int month;
    @Column
    int year;


    public void setId(int id) {
        this.id = id;
    }

    public void setOpeningBalanceMoney(int openingBalanceMoney) {
        this.openingBalanceMoney = openingBalanceMoney;
    }

    public void setRecievedBalanceMoney(int recievedBalanceMoney) {
        this.recievedBalanceMoney = recievedBalanceMoney;
    }

    public void setTodayExpensesMoney(int todayExpensesMoney) {
        this.todayExpensesMoney = todayExpensesMoney;
    }

    public void setFinalTodayBalanceMoney(int finalTodayBalanceMoney) {
        this.finalTodayBalanceMoney = finalTodayBalanceMoney;
    }

    public void setOpeningBalanceWheat(double openingBalanceWheat) {
        this.openingBalanceWheat = openingBalanceWheat;
    }

    public void setRecievedBalanceWheat(double recievedBalanceWheat) {
        this.recievedBalanceWheat = recievedBalanceWheat;
    }

    public void setConsumeTodayBalanceWheat(double consumeTodayBalanceWheat) {
        this.consumeTodayBalanceWheat = consumeTodayBalanceWheat;
    }

    public void setFinalTodayBalanceWheat(double finalTodayBalanceWheat) {
        this.finalTodayBalanceWheat = finalTodayBalanceWheat;
    }

    public void setOpeningBalanceRice(double openingBalanceRice) {
        this.openingBalanceRice = openingBalanceRice;
    }

    public void setRecievedBalanceRice(double recievedBalanceRice) {
        this.recievedBalanceRice = recievedBalanceRice;
    }

    public void setCousumeTodayRice(double cousumeTodayRice) {
        this.cousumeTodayRice = cousumeTodayRice;
    }

    public void setFinalBalanceRice(double finalBalanceRice) {
        this.finalBalanceRice = finalBalanceRice;
    }

    public void setDallChana(double dallChana) {
        this.dallChana = dallChana;
    }

    public void setSabutMungi(double sabutMungi) {
        this.sabutMungi = sabutMungi;
    }

    public void setRefinedOil(double refinedOil) {
        this.refinedOil = refinedOil;
    }

    public void setMirch(double mirch) {
        this.mirch = mirch;
    }

    public void setJeera(double jeera) {
        this.jeera = jeera;
    }

    public void setHaldi(double haldi) {
        this.haldi = haldi;
    }

    public void setGaramMasala(double garamMasala) {
        this.garamMasala = garamMasala;
    }

    public void setNamak(double namak) {
        this.namak = namak;
    }

    public void setKhoppa(double khoppa) {
        this.khoppa = khoppa;
    }

    public void setDakha(double dakha) {
        this.dakha = dakha;
    }

    public void setKaalCholle(double kaalCholle) {
        this.kaalCholle = kaalCholle;
    }

    public void setBessan(double bessan) {
        this.bessan = bessan;
    }

    public void setDahi(double dahi) {
        this.dahi = dahi;
    }

    public void setPyaaz(double pyaaz) {
        this.pyaaz = pyaaz;
    }

    public void setKhandd(double khandd) {
        this.khandd = khandd;
    }

    public void setTamattar(double tamattar) {
        this.tamattar = tamattar;
    }

    public void setSabzi(double sabzi) {
        this.sabzi = sabzi;
    }

    public void setAloo(double aloo) {
        this.aloo = aloo;
    }

    public void setMausamiSabzi(double mausamiSabzi) {
        this.mausamiSabzi = mausamiSabzi;
    }

    public void setKanakPissai(double kanakPissai) {
        this.kanakPissai = kanakPissai;
    }

    public void setDudh(double dudh) {
        this.dudh = dudh;
    }

    public void setTotalCondiment(double totalCondiment) {
        this.totalCondiment = totalCondiment;
    }

    public void setBallan(double ballan) {
        this.ballan = ballan;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //Money
    @Column
    int openingBalanceMoney;

    @Column
    int recievedBalanceMoney;

    @Column
    int todayExpensesMoney;

    @Column
    int finalTodayBalanceMoney;

    //Wheat
    @Column
    double openingBalanceWheat;

    @Column
    double recievedBalanceWheat;

    @Column
    double consumeTodayBalanceWheat;

    @Column
    double finalTodayBalanceWheat;

    //Rice
    @Column
    double openingBalanceRice;

    @Column
    double recievedBalanceRice;

    @Column
    double cousumeTodayRice;

    @Column
    double finalBalanceRice;

    //Others

    public int getId() {
        return id;
    }

    public Date getDayOfMonth() {
        return dayOfMonth;
    }

    @Bindable
    public double getStrength() {
        return strength;
    }

    public int getOpeningBalanceMoney() {
        return openingBalanceMoney;
    }

    public int getRecievedBalanceMoney() {
        return recievedBalanceMoney;
    }

    public int getTodayExpensesMoney() {
        return todayExpensesMoney;
    }

    public int getFinalTodayBalanceMoney() {
        return finalTodayBalanceMoney;
    }

    public double getOpeningBalanceWheat() {
        return openingBalanceWheat;
    }

    public double getRecievedBalanceWheat() {
        return recievedBalanceWheat;
    }

    public double getConsumeTodayBalanceWheat() {
        return consumeTodayBalanceWheat;
    }

    public double getFinalTodayBalanceWheat() {
        return finalTodayBalanceWheat;
    }

    public double getOpeningBalanceRice() {
        return openingBalanceRice;
    }

    public double getRecievedBalanceRice() {
        return recievedBalanceRice;
    }

    public double getCousumeTodayRice() {
        return cousumeTodayRice;
    }

    public double getFinalBalanceRice() {
        return finalBalanceRice;
    }


    @Column
    double dallChana = 0;

    @Column
    double sabutMungi = 0;

    @Column
    double refinedOil = 0;

    @Column
    double mirch = 0;

    @Column
    double jeera = 0;

    @Column
    double haldi = 0;

    @Column
    double garamMasala = 0;

    @Column
    double namak = 0;

    @Column
    double khoppa = 0;

    @Column
    double dakha = 0;

    @Column
    double kaalCholle = 0;

    @Column
    double bessan = 0;

    @Column
    double dahi = 0;


    @Column
    double pyaaz = 0;

    @Column
    double khandd = 0;

    @Column
    double tamattar = 0;

    @Column
    double sabzi = 0;

    @Column
    double aloo = 0;

    @Column
    double mausamiSabzi = 0;

    @Column
    double kanakPissai = 0;

    @Column
    double dudh = 0;

    @Column
    double totalCondiment = 0;

    @Column
    double ballan = 0;

    @Column
    double total = 0;

    @Bindable({"strength"})
    public double getDallChana() {
        if (dayCode == 1) {
            dallChana = strength * 1.54;
        } else {
            dallChana = 0.0;
        }
        return dallChana;
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

    @Bindable({"strength"})
    public double getMirch() {
        mirch = strength * .10;
        return mirch;
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

    @Bindable({"strength"})
    public double getGaramMasala() {
        if (dayCode == 1 || dayCode == 2 || dayCode == 3 || dayCode == 5) {
            garamMasala = strength * 0.04;
        } else if (dayCode == 4) {
            garamMasala = strength * 0.05;
        } else {
            garamMasala = 0.0;
        }

        return garamMasala;
    }

    @Bindable({"strength"})
    public double getNamak() {
        namak = strength * 0.5;
        return namak;
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

    @Bindable({"strength"})
    public double getDakha() {

        if (dayCode == 5) {
            dakha = strength * 0.10;
        } else {
            dakha = 0.0;
        }
        return dakha;
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

    @Bindable({"strength"})
    public double getBessan() {

        if (dayCode == 4) {
            bessan = strength * 1.20;
        } else {
            bessan = 0.0;
        }
        return bessan;
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

    @Bindable({"strength"})
    public double getKhandd() {
        if (dayCode == 5) {
            khandd = strength * 0.30;
        } else {
            khandd = 0.0;
        }
        return khandd;
    }

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

    @Bindable({"strength"})
    public double getTamattar() {

        if (dayCode == 1) {
            tamattar = strength * 0.15;
        } else if (dayCode == 2) {
            tamattar = strength * 0.17;
        } else if (dayCode == 3 || dayCode == 5) {
            tamattar = strength * 0.20;
        } else if (dayCode == 4) {
            tamattar = strength * 0.16;
        } else if (dayCode == 6) {
            tamattar = strength * 0.19;
        } else {
            tamattar = 0.0;
        }

        return tamattar;
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

    @Bindable({"strength"})
    public double getMausamiSabzi() {
        if (dayCode == 1 || dayCode == 6) {
            mausamiSabzi = strength * 0.25;
        } else {
            mausamiSabzi = 0.0;
        }
        return mausamiSabzi;
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

    @Bindable({"strength"})
    public double getDudh() {
        if (dayCode == 5) {
            dudh = strength * 0.79;
        } else {
            dudh = 0.0;
        }
        return dudh;
    }

    @Bindable({"strength"})
    public double getTotalCondiment() {
        return totalCondiment;
    }

    @Bindable({"strength"})
    public double getBallan() {
        ballan = ballan * strength;
        return ballan;
    }

    @Bindable({"strength"})
    public double getTotal() {

        return total;
    }

    public RawData() {

    }

    public RawData(int strength, int recievedBalanceMoney, double recievedBalanceWheat, double recievedBalanceRice) {

        this.strength = strength;
        this.recievedBalanceMoney = recievedBalanceMoney;
        this.recievedBalanceWheat = recievedBalanceWheat;
        this.recievedBalanceRice = recievedBalanceRice;
    }

}
