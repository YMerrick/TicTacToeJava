package com.mycompany.app;

public enum BoardElementStatus {
        O("O"),
        X("X"),
        N(" ");
    
        private final String value;
        private BoardElementStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
