package data.entity;

import java.math.BigDecimal;

public class VatSummary {

    private BigDecimal afaSzazalek;
    private BigDecimal afaalapSum;
    private BigDecimal afaertekSum;
    private BigDecimal bruttoSum;

    // Deviza
    private BigDecimal devizaAfaalapSum;
    private BigDecimal devizaAfaertekSum;
    private BigDecimal devizaBruttoSum;

    public VatSummary(BigDecimal afaSzazalek) {
        this.afaSzazalek = afaSzazalek;
        this.afaalapSum = BigDecimal.ZERO;
        this.afaertekSum = BigDecimal.ZERO;
        this.bruttoSum = BigDecimal.ZERO;
        this.devizaAfaalapSum = BigDecimal.ZERO;
        this.devizaAfaertekSum = BigDecimal.ZERO;
        this.devizaBruttoSum = BigDecimal.ZERO;
    }

    public BigDecimal getAfaSzazalek() {
        return afaSzazalek;
    }

    public BigDecimal getAfaalapSum() {
        return afaalapSum;
    }

    public BigDecimal getAfaertekSum() {
        return afaertekSum;
    }

    public BigDecimal getBruttoSum() {
        return bruttoSum;
    }

    public BigDecimal getDevizaAfaalapSum() {
        return devizaAfaalapSum;
    }

    public BigDecimal getDevizaAfaertekSum() {
        return devizaAfaertekSum;
    }

    public BigDecimal getDevizaBruttoSum() {
        return devizaBruttoSum;
    }

    public void addToAfaAlap(BigDecimal b) {
        this.afaalapSum = this.afaalapSum.add(b);
    }

    public void addToAfaErtek(BigDecimal b) {
        this.afaertekSum = this.afaertekSum.add(b);
    }

    public void addToBrutto(BigDecimal b) {
        this.bruttoSum = this.bruttoSum.add(b);
    }

    public void addToDevizaAfaAlap(BigDecimal b) {
        this.devizaAfaalapSum = this.devizaAfaalapSum.add(b);
    }

    public void addToDevizaAfaErtek(BigDecimal b) {
        this.devizaAfaertekSum = this.devizaAfaertekSum.add(b);
    }

    public void addToDevizaBrutto(BigDecimal b) {
        this.devizaBruttoSum = this.devizaBruttoSum.add(b);
    }
}
