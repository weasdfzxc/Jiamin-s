/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.blood;

/**
 *
 * @author xdwea
 */
public enum BloodType {
        TYPEA("A"),
        TYPEB("B"),
        TYPEAB("AB"),
        TYPEO("O"),
        TYPERHA("A(RH-)"),
        TYPERHB("B(RH-)"),
        TYPERHAB("AB(RH-)"),
        TYPERHO("O(RH-)");
        
        
        private String value;

        private BloodType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
