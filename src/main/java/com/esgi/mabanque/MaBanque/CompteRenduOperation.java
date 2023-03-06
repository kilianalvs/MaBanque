package com.esgi.mabanque.MaBanque;

public class CompteRenduOperation {
    private int nouveauSolde;
    private int montantCredite;
    private int montantNonCredite;
    private int montantDebite;
    private int montantNonDebite;

    public CompteRenduOperation(int nouveauSolde,
                                int montantCredite,
                                int montantNonCredite,
                                int montantDebite,
                                int montantNonDebite) {
        this.nouveauSolde = nouveauSolde;
        this.montantCredite = montantCredite;
        this.montantNonCredite = montantNonCredite;
        this.montantDebite = montantDebite;
        this.montantNonDebite = montantNonDebite;
    }

    public void getDateHeureOperation() {
        return;
    }


    public static class CompteRenduOperationBuilder
    {
        private final int nouveauSolde;
        private int montantCredite;
        private int montantNonCredite;
        private int montantDebite;
        private int montantNonDebite;

        public CompteRenduOperationBuilder(int nouveauSolde) {
            this.nouveauSolde = nouveauSolde;
        }

        public CompteRenduOperationBuilder montantCredite(int montantCredite) {
            this.montantCredite = montantCredite;
            return this;
        }

        public CompteRenduOperationBuilder montantNonCredite(int montantNonCredite) {
            this.montantNonCredite = montantNonCredite;
            return this;
        }

        public CompteRenduOperationBuilder montantDebite(int montantDebite) {
            this.montantDebite = montantDebite;
            return this;
        }

        public CompteRenduOperationBuilder montantNonDebite(int montantNonDebite) {
            this.montantNonDebite = montantNonDebite;
            return this;
        }

        public CompteRenduOperation build() {
            return new CompteRenduOperation(this.nouveauSolde,
                    this.montantCredite,
                    this.montantNonCredite,
                    this.montantDebite,
                    this.montantNonDebite
            );
        }
    }

    public int getNouveauSolde() {
        return nouveauSolde;
    }

    public void setNouveauSolde(int nouveauSolde) {
        this.nouveauSolde = nouveauSolde;
    }

    public int getMontantCredite() {
        return montantCredite;
    }

    public void setMontantCredite(int montantCredite) {
        this.montantCredite = montantCredite;
    }

    public int getMontantNonCredite() {
        return montantNonCredite;
    }

    public void setMontantNonCredite(int montantNonCredite) {
        this.montantNonCredite = montantNonCredite;
    }

    public int getMontantDebite() {
        return montantDebite;
    }

    public void setMontantDebite(int montantDebite) {
        this.montantDebite = montantDebite;
    }

    public int getMontantNonDebite() {
        return montantNonDebite;
    }

    public void setMontantNonDebite(int montantNonDebite) {
        this.montantNonDebite = montantNonDebite;
    }
}
