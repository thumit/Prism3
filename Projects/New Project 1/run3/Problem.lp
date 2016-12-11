\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj: l_0 + l_1 + l_2 + 2 l_3 + u_0 + u_1 + u_2
Subject To
 c1:    y_0 + l_0 >= 500000
 c2:    y_1 + l_1 >= 500000
 c3:    y_2 + l_2 >= 500000
 c4:    y_3 + l_3 >= 10000000
 c5:    y_0 - u_0 - Rgc5  = 0
 c6:    y_1 - u_1 - Rgc6  = 0
 c7:    y_2 - u_2 - Rgc7  = 0
 c8:    y_3 - u_3 >= 0
 c9:    x_B,R,N,L,A,L,0 + x_B,R,N,L,A,L,1 + x_B,R,N,L,A,L,2 + x_B,R,N,L,A,L,3
        + x_B,R,N,L,A,L,4  = 310
 c10:   x_B,R,N,C,A,L,0 + x_B,R,N,C,A,L,1 + x_B,R,N,C,A,L,2 + x_B,R,N,C,A,L,3
        + x_B,R,N,C,A,L,4  = 137
 c11:   x_B,R,N,R,A,L,0 + x_B,R,N,R,A,L,1 + x_B,R,N,R,A,L,2 + x_B,R,N,R,A,L,3
        + x_B,R,N,R,A,L,4  = 79
 c12:   x_B,R,P,L,A,L,0 + x_B,R,P,L,A,L,1 + x_B,R,P,L,A,L,2 + x_B,R,P,L,A,L,3
        + x_B,R,P,L,A,L,4  = 85
 c13:   x_B,R,P,C,A,L,0 + x_B,R,P,C,A,L,1 + x_B,R,P,C,A,L,2 + x_B,R,P,C,A,L,3
        + x_B,R,P,C,A,L,4  = 86
 c14:   x_B,R,P,R,A,L,0 + x_B,R,P,R,A,L,1 + x_B,R,P,R,A,L,2 + x_B,R,P,R,A,L,3
        + x_B,R,P,R,A,L,4  = 52
 c15:   x_B,N,N,L,A,L,0 + x_B,N,N,L,A,L,1 + x_B,N,N,L,A,L,2 + x_B,N,N,L,A,L,3
        + x_B,N,N,L,A,L,4  = 378
 c16:   x_B,N,N,C,A,L,0 + x_B,N,N,C,A,L,1 + x_B,N,N,C,A,L,2 + x_B,N,N,C,A,L,3
        + x_B,N,N,C,A,L,4  = 335
 c17:   x_B,N,P,R,A,L,0 + x_B,N,P,R,A,L,1 + x_B,N,P,R,A,L,2 + x_B,N,P,R,A,L,3
        + x_B,N,P,R,A,L,4  = 118
 c18:   x_C,N,N,L,A,L,0 + x_C,N,N,L,A,L,1 + x_C,N,N,L,A,L,2 + x_C,N,N,L,A,L,3
        + x_C,N,N,L,A,L,4  = 33323
 c19:   x_C,N,N,H,A,L,0 + x_C,N,N,H,A,L,1 + x_C,N,N,H,A,L,2 + x_C,N,N,H,A,L,3
        + x_C,N,N,H,A,L,4  = 997
 c20:   x_C,N,N,C,A,L,0 + x_C,N,N,C,A,L,1 + x_C,N,N,C,A,L,2 + x_C,N,N,C,A,L,3
        + x_C,N,N,C,A,L,4  = 13028
 c21:   x_C,N,N,R,A,L,0 + x_C,N,N,R,A,L,1 + x_C,N,N,R,A,L,2 + x_C,N,N,R,A,L,3
        + x_C,N,N,R,A,L,4  = 1237
 c22:   x_C,N,O,L,A,L,0 + x_C,N,O,L,A,L,1 + x_C,N,O,L,A,L,2 + x_C,N,O,L,A,L,3
        + x_C,N,O,L,A,L,4  = 97
 c23:   x_C,N,O,H,A,L,0 + x_C,N,O,H,A,L,1 + x_C,N,O,H,A,L,2 + x_C,N,O,H,A,L,3
        + x_C,N,O,H,A,L,4  = 426
 c24:   x_C,N,O,C,A,L,0 + x_C,N,O,C,A,L,1 + x_C,N,O,C,A,L,2 + x_C,N,O,C,A,L,3
        + x_C,N,O,C,A,L,4  = 120
 c25:   x_C,N,O,R,A,L,0 + x_C,N,O,R,A,L,1 + x_C,N,O,R,A,L,2 + x_C,N,O,R,A,L,3
        + x_C,N,O,R,A,L,4  = 336
 c26:   x_B,R,N,L,A,L,4  = 9.3
 c27:   x_B,R,N,C,A,L,4  = 4.11
 c28:   x_B,R,N,R,A,L,4  = 2.37
 c29:   x_B,R,P,L,A,L,4  = 2.55
 c30:   x_B,R,P,C,A,L,4  = 2.58
 c31:   x_B,R,P,R,A,L,4  = 1.56
 c32:   x_B,N,N,L,A,L,4  = 11.34
 c33:   x_B,N,N,C,A,L,4  = 10.05
 c34:   x_B,N,P,R,A,L,4  = 3.54
 c35:   x_C,N,N,L,A,L,4  = 999.69
 c36:   x_C,N,N,H,A,L,4  = 29.91
 c37:   x_C,N,N,C,A,L,4  = 390.84
 c38:   x_C,N,N,R,A,L,4  = 37.11
 c39:   x_C,N,O,L,A,L,4  = 2.91
 c40:   x_C,N,O,H,A,L,4  = 12.78
 c41:   x_C,N,O,C,A,L,4  = 3.6
 c42:   x_C,N,O,R,A,L,4  = 10.08
 c43:   x_B,R,N,L,A,L,4 - xMS_B,R,N,L,A,L,0,1 - xMS_B,R,N,L,A,L,1,1
        - xMS_B,R,N,L,A,L,2,1 - xMS_B,R,N,L,A,L,3,1 - xMS_B,R,N,L,A,L,4,1  = 0
 c44:   x_B,R,N,L,A,L,4 - xMS_B,R,N,L,A,L,0,2 - xMS_B,R,N,L,A,L,1,2
        - xMS_B,R,N,L,A,L,2,2 - xMS_B,R,N,L,A,L,3,2 - xMS_B,R,N,L,A,L,4,2  = 0
 c45:   x_B,R,N,L,A,L,4 - xMS_B,R,N,L,A,L,0,3 - xMS_B,R,N,L,A,L,1,3
        - xMS_B,R,N,L,A,L,2,3 - xMS_B,R,N,L,A,L,3,3 - xMS_B,R,N,L,A,L,4,3  = 0
 c46:   x_B,R,N,L,A,L,4 - xMS_B,R,N,L,A,L,0,4 - xMS_B,R,N,L,A,L,1,4
        - xMS_B,R,N,L,A,L,2,4 - xMS_B,R,N,L,A,L,3,4 - xMS_B,R,N,L,A,L,4,4  = 0
 c47:   x_B,R,N,L,A,L,4 - xMS_B,R,N,L,A,L,0,5 - xMS_B,R,N,L,A,L,1,5
        - xMS_B,R,N,L,A,L,2,5 - xMS_B,R,N,L,A,L,3,5 - xMS_B,R,N,L,A,L,4,5  = 0
 c48:   x_B,R,N,C,A,L,4 - xMS_B,R,N,C,A,L,0,1 - xMS_B,R,N,C,A,L,1,1
        - xMS_B,R,N,C,A,L,2,1 - xMS_B,R,N,C,A,L,3,1 - xMS_B,R,N,C,A,L,4,1  = 0
 c49:   x_B,R,N,C,A,L,4 - xMS_B,R,N,C,A,L,0,2 - xMS_B,R,N,C,A,L,1,2
        - xMS_B,R,N,C,A,L,2,2 - xMS_B,R,N,C,A,L,3,2 - xMS_B,R,N,C,A,L,4,2  = 0
 c50:   x_B,R,N,C,A,L,4 - xMS_B,R,N,C,A,L,0,3 - xMS_B,R,N,C,A,L,1,3
        - xMS_B,R,N,C,A,L,2,3 - xMS_B,R,N,C,A,L,3,3 - xMS_B,R,N,C,A,L,4,3  = 0
 c51:   x_B,R,N,C,A,L,4 - xMS_B,R,N,C,A,L,0,4 - xMS_B,R,N,C,A,L,1,4
        - xMS_B,R,N,C,A,L,2,4 - xMS_B,R,N,C,A,L,3,4 - xMS_B,R,N,C,A,L,4,4  = 0
 c52:   x_B,R,N,C,A,L,4 - xMS_B,R,N,C,A,L,0,5 - xMS_B,R,N,C,A,L,1,5
        - xMS_B,R,N,C,A,L,2,5 - xMS_B,R,N,C,A,L,3,5 - xMS_B,R,N,C,A,L,4,5  = 0
 c53:   x_B,R,N,R,A,L,4 - xMS_B,R,N,R,A,L,0,1 - xMS_B,R,N,R,A,L,1,1
        - xMS_B,R,N,R,A,L,2,1 - xMS_B,R,N,R,A,L,3,1 - xMS_B,R,N,R,A,L,4,1  = 0
 c54:   x_B,R,N,R,A,L,4 - xMS_B,R,N,R,A,L,0,2 - xMS_B,R,N,R,A,L,1,2
        - xMS_B,R,N,R,A,L,2,2 - xMS_B,R,N,R,A,L,3,2 - xMS_B,R,N,R,A,L,4,2  = 0
 c55:   x_B,R,N,R,A,L,4 - xMS_B,R,N,R,A,L,0,3 - xMS_B,R,N,R,A,L,1,3
        - xMS_B,R,N,R,A,L,2,3 - xMS_B,R,N,R,A,L,3,3 - xMS_B,R,N,R,A,L,4,3  = 0
 c56:   x_B,R,N,R,A,L,4 - xMS_B,R,N,R,A,L,0,4 - xMS_B,R,N,R,A,L,1,4
        - xMS_B,R,N,R,A,L,2,4 - xMS_B,R,N,R,A,L,3,4 - xMS_B,R,N,R,A,L,4,4  = 0
 c57:   x_B,R,N,R,A,L,4 - xMS_B,R,N,R,A,L,0,5 - xMS_B,R,N,R,A,L,1,5
        - xMS_B,R,N,R,A,L,2,5 - xMS_B,R,N,R,A,L,3,5 - xMS_B,R,N,R,A,L,4,5  = 0
 c58:   x_B,R,P,L,A,L,4 - xMS_B,R,P,L,A,L,0,1 - xMS_B,R,P,L,A,L,1,1
        - xMS_B,R,P,L,A,L,2,1 - xMS_B,R,P,L,A,L,3,1 - xMS_B,R,P,L,A,L,4,1  = 0
 c59:   x_B,R,P,L,A,L,4 - xMS_B,R,P,L,A,L,0,2 - xMS_B,R,P,L,A,L,1,2
        - xMS_B,R,P,L,A,L,2,2 - xMS_B,R,P,L,A,L,3,2 - xMS_B,R,P,L,A,L,4,2  = 0
 c60:   x_B,R,P,L,A,L,4 - xMS_B,R,P,L,A,L,0,3 - xMS_B,R,P,L,A,L,1,3
        - xMS_B,R,P,L,A,L,2,3 - xMS_B,R,P,L,A,L,3,3 - xMS_B,R,P,L,A,L,4,3  = 0
 c61:   x_B,R,P,L,A,L,4 - xMS_B,R,P,L,A,L,0,4 - xMS_B,R,P,L,A,L,1,4
        - xMS_B,R,P,L,A,L,2,4 - xMS_B,R,P,L,A,L,3,4 - xMS_B,R,P,L,A,L,4,4  = 0
 c62:   x_B,R,P,L,A,L,4 - xMS_B,R,P,L,A,L,0,5 - xMS_B,R,P,L,A,L,1,5
        - xMS_B,R,P,L,A,L,2,5 - xMS_B,R,P,L,A,L,3,5 - xMS_B,R,P,L,A,L,4,5  = 0
 c63:   x_B,R,P,C,A,L,4 - xMS_B,R,P,C,A,L,0,1 - xMS_B,R,P,C,A,L,1,1
        - xMS_B,R,P,C,A,L,2,1 - xMS_B,R,P,C,A,L,3,1 - xMS_B,R,P,C,A,L,4,1  = 0
 c64:   x_B,R,P,C,A,L,4 - xMS_B,R,P,C,A,L,0,2 - xMS_B,R,P,C,A,L,1,2
        - xMS_B,R,P,C,A,L,2,2 - xMS_B,R,P,C,A,L,3,2 - xMS_B,R,P,C,A,L,4,2  = 0
 c65:   x_B,R,P,C,A,L,4 - xMS_B,R,P,C,A,L,0,3 - xMS_B,R,P,C,A,L,1,3
        - xMS_B,R,P,C,A,L,2,3 - xMS_B,R,P,C,A,L,3,3 - xMS_B,R,P,C,A,L,4,3  = 0
 c66:   x_B,R,P,C,A,L,4 - xMS_B,R,P,C,A,L,0,4 - xMS_B,R,P,C,A,L,1,4
        - xMS_B,R,P,C,A,L,2,4 - xMS_B,R,P,C,A,L,3,4 - xMS_B,R,P,C,A,L,4,4  = 0
 c67:   x_B,R,P,C,A,L,4 - xMS_B,R,P,C,A,L,0,5 - xMS_B,R,P,C,A,L,1,5
        - xMS_B,R,P,C,A,L,2,5 - xMS_B,R,P,C,A,L,3,5 - xMS_B,R,P,C,A,L,4,5  = 0
 c68:   x_B,R,P,R,A,L,4 - xMS_B,R,P,R,A,L,0,1 - xMS_B,R,P,R,A,L,1,1
        - xMS_B,R,P,R,A,L,2,1 - xMS_B,R,P,R,A,L,3,1 - xMS_B,R,P,R,A,L,4,1  = 0
 c69:   x_B,R,P,R,A,L,4 - xMS_B,R,P,R,A,L,0,2 - xMS_B,R,P,R,A,L,1,2
        - xMS_B,R,P,R,A,L,2,2 - xMS_B,R,P,R,A,L,3,2 - xMS_B,R,P,R,A,L,4,2  = 0
 c70:   x_B,R,P,R,A,L,4 - xMS_B,R,P,R,A,L,0,3 - xMS_B,R,P,R,A,L,1,3
        - xMS_B,R,P,R,A,L,2,3 - xMS_B,R,P,R,A,L,3,3 - xMS_B,R,P,R,A,L,4,3  = 0
 c71:   x_B,R,P,R,A,L,4 - xMS_B,R,P,R,A,L,0,4 - xMS_B,R,P,R,A,L,1,4
        - xMS_B,R,P,R,A,L,2,4 - xMS_B,R,P,R,A,L,3,4 - xMS_B,R,P,R,A,L,4,4  = 0
 c72:   x_B,R,P,R,A,L,4 - xMS_B,R,P,R,A,L,0,5 - xMS_B,R,P,R,A,L,1,5
        - xMS_B,R,P,R,A,L,2,5 - xMS_B,R,P,R,A,L,3,5 - xMS_B,R,P,R,A,L,4,5  = 0
 c73:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,1 - xMS_B,N,N,L,A,L,1,1
        - xMS_B,N,N,L,A,L,2,1 - xMS_B,N,N,L,A,L,3,1 - xMS_B,N,N,L,A,L,4,1  = 0
 c74:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,2 - xMS_B,N,N,L,A,L,1,2
        - xMS_B,N,N,L,A,L,2,2 - xMS_B,N,N,L,A,L,3,2 - xMS_B,N,N,L,A,L,4,2  = 0
 c75:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,3 - xMS_B,N,N,L,A,L,1,3
        - xMS_B,N,N,L,A,L,2,3 - xMS_B,N,N,L,A,L,3,3 - xMS_B,N,N,L,A,L,4,3  = 0
 c76:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,4 - xMS_B,N,N,L,A,L,1,4
        - xMS_B,N,N,L,A,L,2,4 - xMS_B,N,N,L,A,L,3,4 - xMS_B,N,N,L,A,L,4,4  = 0
 c77:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,5 - xMS_B,N,N,L,A,L,1,5
        - xMS_B,N,N,L,A,L,2,5 - xMS_B,N,N,L,A,L,3,5 - xMS_B,N,N,L,A,L,4,5  = 0
 c78:   x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,1 - xMS_B,N,N,C,A,L,1,1
        - xMS_B,N,N,C,A,L,2,1 - xMS_B,N,N,C,A,L,3,1 - xMS_B,N,N,C,A,L,4,1  = 0
 c79:   x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,2 - xMS_B,N,N,C,A,L,1,2
        - xMS_B,N,N,C,A,L,2,2 - xMS_B,N,N,C,A,L,3,2 - xMS_B,N,N,C,A,L,4,2  = 0
 c80:   x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,3 - xMS_B,N,N,C,A,L,1,3
        - xMS_B,N,N,C,A,L,2,3 - xMS_B,N,N,C,A,L,3,3 - xMS_B,N,N,C,A,L,4,3  = 0
 c81:   x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,4 - xMS_B,N,N,C,A,L,1,4
        - xMS_B,N,N,C,A,L,2,4 - xMS_B,N,N,C,A,L,3,4 - xMS_B,N,N,C,A,L,4,4  = 0
 c82:   x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,5 - xMS_B,N,N,C,A,L,1,5
        - xMS_B,N,N,C,A,L,2,5 - xMS_B,N,N,C,A,L,3,5 - xMS_B,N,N,C,A,L,4,5  = 0
 c83:   x_B,N,P,R,A,L,4 - xMS_B,N,P,R,A,L,0,1 - xMS_B,N,P,R,A,L,1,1
        - xMS_B,N,P,R,A,L,2,1 - xMS_B,N,P,R,A,L,3,1 - xMS_B,N,P,R,A,L,4,1  = 0
 c84:   x_B,N,P,R,A,L,4 - xMS_B,N,P,R,A,L,0,2 - xMS_B,N,P,R,A,L,1,2
        - xMS_B,N,P,R,A,L,2,2 - xMS_B,N,P,R,A,L,3,2 - xMS_B,N,P,R,A,L,4,2  = 0
 c85:   x_B,N,P,R,A,L,4 - xMS_B,N,P,R,A,L,0,3 - xMS_B,N,P,R,A,L,1,3
        - xMS_B,N,P,R,A,L,2,3 - xMS_B,N,P,R,A,L,3,3 - xMS_B,N,P,R,A,L,4,3  = 0
 c86:   x_B,N,P,R,A,L,4 - xMS_B,N,P,R,A,L,0,4 - xMS_B,N,P,R,A,L,1,4
        - xMS_B,N,P,R,A,L,2,4 - xMS_B,N,P,R,A,L,3,4 - xMS_B,N,P,R,A,L,4,4  = 0
 c87:   x_B,N,P,R,A,L,4 - xMS_B,N,P,R,A,L,0,5 - xMS_B,N,P,R,A,L,1,5
        - xMS_B,N,P,R,A,L,2,5 - xMS_B,N,P,R,A,L,3,5 - xMS_B,N,P,R,A,L,4,5  = 0
 c88:   x_C,N,N,L,A,L,4 - xMS_C,N,N,L,A,L,0,1 - xMS_C,N,N,L,A,L,1,1
        - xMS_C,N,N,L,A,L,2,1 - xMS_C,N,N,L,A,L,3,1 - xMS_C,N,N,L,A,L,4,1  = 0
 c89:   x_C,N,N,L,A,L,4 - xMS_C,N,N,L,A,L,0,2 - xMS_C,N,N,L,A,L,1,2
        - xMS_C,N,N,L,A,L,2,2 - xMS_C,N,N,L,A,L,3,2 - xMS_C,N,N,L,A,L,4,2  = 0
 c90:   x_C,N,N,L,A,L,4 - xMS_C,N,N,L,A,L,0,3 - xMS_C,N,N,L,A,L,1,3
        - xMS_C,N,N,L,A,L,2,3 - xMS_C,N,N,L,A,L,3,3 - xMS_C,N,N,L,A,L,4,3  = 0
 c91:   x_C,N,N,L,A,L,4 - xMS_C,N,N,L,A,L,0,4 - xMS_C,N,N,L,A,L,1,4
        - xMS_C,N,N,L,A,L,2,4 - xMS_C,N,N,L,A,L,3,4 - xMS_C,N,N,L,A,L,4,4  = 0
 c92:   x_C,N,N,L,A,L,4 - xMS_C,N,N,L,A,L,0,5 - xMS_C,N,N,L,A,L,1,5
        - xMS_C,N,N,L,A,L,2,5 - xMS_C,N,N,L,A,L,3,5 - xMS_C,N,N,L,A,L,4,5  = 0
 c93:   x_C,N,N,H,A,L,4 - xMS_C,N,N,H,A,L,0,1 - xMS_C,N,N,H,A,L,1,1
        - xMS_C,N,N,H,A,L,2,1 - xMS_C,N,N,H,A,L,3,1 - xMS_C,N,N,H,A,L,4,1  = 0
 c94:   x_C,N,N,H,A,L,4 - xMS_C,N,N,H,A,L,0,2 - xMS_C,N,N,H,A,L,1,2
        - xMS_C,N,N,H,A,L,2,2 - xMS_C,N,N,H,A,L,3,2 - xMS_C,N,N,H,A,L,4,2  = 0
 c95:   x_C,N,N,H,A,L,4 - xMS_C,N,N,H,A,L,0,3 - xMS_C,N,N,H,A,L,1,3
        - xMS_C,N,N,H,A,L,2,3 - xMS_C,N,N,H,A,L,3,3 - xMS_C,N,N,H,A,L,4,3  = 0
 c96:   x_C,N,N,H,A,L,4 - xMS_C,N,N,H,A,L,0,4 - xMS_C,N,N,H,A,L,1,4
        - xMS_C,N,N,H,A,L,2,4 - xMS_C,N,N,H,A,L,3,4 - xMS_C,N,N,H,A,L,4,4  = 0
 c97:   x_C,N,N,H,A,L,4 - xMS_C,N,N,H,A,L,0,5 - xMS_C,N,N,H,A,L,1,5
        - xMS_C,N,N,H,A,L,2,5 - xMS_C,N,N,H,A,L,3,5 - xMS_C,N,N,H,A,L,4,5  = 0
 c98:   x_C,N,N,C,A,L,4 - xMS_C,N,N,C,A,L,0,1 - xMS_C,N,N,C,A,L,1,1
        - xMS_C,N,N,C,A,L,2,1 - xMS_C,N,N,C,A,L,3,1 - xMS_C,N,N,C,A,L,4,1  = 0
 c99:   x_C,N,N,C,A,L,4 - xMS_C,N,N,C,A,L,0,2 - xMS_C,N,N,C,A,L,1,2
        - xMS_C,N,N,C,A,L,2,2 - xMS_C,N,N,C,A,L,3,2 - xMS_C,N,N,C,A,L,4,2  = 0
 c100:  x_C,N,N,C,A,L,4 - xMS_C,N,N,C,A,L,0,3 - xMS_C,N,N,C,A,L,1,3
        - xMS_C,N,N,C,A,L,2,3 - xMS_C,N,N,C,A,L,3,3 - xMS_C,N,N,C,A,L,4,3  = 0
 c101:  x_C,N,N,C,A,L,4 - xMS_C,N,N,C,A,L,0,4 - xMS_C,N,N,C,A,L,1,4
        - xMS_C,N,N,C,A,L,2,4 - xMS_C,N,N,C,A,L,3,4 - xMS_C,N,N,C,A,L,4,4  = 0
 c102:  x_C,N,N,C,A,L,4 - xMS_C,N,N,C,A,L,0,5 - xMS_C,N,N,C,A,L,1,5
        - xMS_C,N,N,C,A,L,2,5 - xMS_C,N,N,C,A,L,3,5 - xMS_C,N,N,C,A,L,4,5  = 0
 c103:  x_C,N,N,R,A,L,4 - xMS_C,N,N,R,A,L,0,1 - xMS_C,N,N,R,A,L,1,1
        - xMS_C,N,N,R,A,L,2,1 - xMS_C,N,N,R,A,L,3,1 - xMS_C,N,N,R,A,L,4,1  = 0
 c104:  x_C,N,N,R,A,L,4 - xMS_C,N,N,R,A,L,0,2 - xMS_C,N,N,R,A,L,1,2
        - xMS_C,N,N,R,A,L,2,2 - xMS_C,N,N,R,A,L,3,2 - xMS_C,N,N,R,A,L,4,2  = 0
 c105:  x_C,N,N,R,A,L,4 - xMS_C,N,N,R,A,L,0,3 - xMS_C,N,N,R,A,L,1,3
        - xMS_C,N,N,R,A,L,2,3 - xMS_C,N,N,R,A,L,3,3 - xMS_C,N,N,R,A,L,4,3  = 0
 c106:  x_C,N,N,R,A,L,4 - xMS_C,N,N,R,A,L,0,4 - xMS_C,N,N,R,A,L,1,4
        - xMS_C,N,N,R,A,L,2,4 - xMS_C,N,N,R,A,L,3,4 - xMS_C,N,N,R,A,L,4,4  = 0
 c107:  x_C,N,N,R,A,L,4 - xMS_C,N,N,R,A,L,0,5 - xMS_C,N,N,R,A,L,1,5
        - xMS_C,N,N,R,A,L,2,5 - xMS_C,N,N,R,A,L,3,5 - xMS_C,N,N,R,A,L,4,5  = 0
 c108:  x_C,N,O,L,A,L,4 - xMS_C,N,O,L,A,L,0,1 - xMS_C,N,O,L,A,L,1,1
        - xMS_C,N,O,L,A,L,2,1 - xMS_C,N,O,L,A,L,3,1 - xMS_C,N,O,L,A,L,4,1  = 0
 c109:  x_C,N,O,L,A,L,4 - xMS_C,N,O,L,A,L,0,2 - xMS_C,N,O,L,A,L,1,2
        - xMS_C,N,O,L,A,L,2,2 - xMS_C,N,O,L,A,L,3,2 - xMS_C,N,O,L,A,L,4,2  = 0
 c110:  x_C,N,O,L,A,L,4 - xMS_C,N,O,L,A,L,0,3 - xMS_C,N,O,L,A,L,1,3
        - xMS_C,N,O,L,A,L,2,3 - xMS_C,N,O,L,A,L,3,3 - xMS_C,N,O,L,A,L,4,3  = 0
 c111:  x_C,N,O,L,A,L,4 - xMS_C,N,O,L,A,L,0,4 - xMS_C,N,O,L,A,L,1,4
        - xMS_C,N,O,L,A,L,2,4 - xMS_C,N,O,L,A,L,3,4 - xMS_C,N,O,L,A,L,4,4  = 0
 c112:  x_C,N,O,L,A,L,4 - xMS_C,N,O,L,A,L,0,5 - xMS_C,N,O,L,A,L,1,5
        - xMS_C,N,O,L,A,L,2,5 - xMS_C,N,O,L,A,L,3,5 - xMS_C,N,O,L,A,L,4,5  = 0
 c113:  x_C,N,O,H,A,L,4 - xMS_C,N,O,H,A,L,0,1 - xMS_C,N,O,H,A,L,1,1
        - xMS_C,N,O,H,A,L,2,1 - xMS_C,N,O,H,A,L,3,1 - xMS_C,N,O,H,A,L,4,1  = 0
 c114:  x_C,N,O,H,A,L,4 - xMS_C,N,O,H,A,L,0,2 - xMS_C,N,O,H,A,L,1,2
        - xMS_C,N,O,H,A,L,2,2 - xMS_C,N,O,H,A,L,3,2 - xMS_C,N,O,H,A,L,4,2  = 0
 c115:  x_C,N,O,H,A,L,4 - xMS_C,N,O,H,A,L,0,3 - xMS_C,N,O,H,A,L,1,3
        - xMS_C,N,O,H,A,L,2,3 - xMS_C,N,O,H,A,L,3,3 - xMS_C,N,O,H,A,L,4,3  = 0
 c116:  x_C,N,O,H,A,L,4 - xMS_C,N,O,H,A,L,0,4 - xMS_C,N,O,H,A,L,1,4
        - xMS_C,N,O,H,A,L,2,4 - xMS_C,N,O,H,A,L,3,4 - xMS_C,N,O,H,A,L,4,4  = 0
 c117:  x_C,N,O,H,A,L,4 - xMS_C,N,O,H,A,L,0,5 - xMS_C,N,O,H,A,L,1,5
        - xMS_C,N,O,H,A,L,2,5 - xMS_C,N,O,H,A,L,3,5 - xMS_C,N,O,H,A,L,4,5  = 0
 c118:  x_C,N,O,C,A,L,4 - xMS_C,N,O,C,A,L,0,1 - xMS_C,N,O,C,A,L,1,1
        - xMS_C,N,O,C,A,L,2,1 - xMS_C,N,O,C,A,L,3,1 - xMS_C,N,O,C,A,L,4,1  = 0
 c119:  x_C,N,O,C,A,L,4 - xMS_C,N,O,C,A,L,0,2 - xMS_C,N,O,C,A,L,1,2
        - xMS_C,N,O,C,A,L,2,2 - xMS_C,N,O,C,A,L,3,2 - xMS_C,N,O,C,A,L,4,2  = 0
 c120:  x_C,N,O,C,A,L,4 - xMS_C,N,O,C,A,L,0,3 - xMS_C,N,O,C,A,L,1,3
        - xMS_C,N,O,C,A,L,2,3 - xMS_C,N,O,C,A,L,3,3 - xMS_C,N,O,C,A,L,4,3  = 0
 c121:  x_C,N,O,C,A,L,4 - xMS_C,N,O,C,A,L,0,4 - xMS_C,N,O,C,A,L,1,4
        - xMS_C,N,O,C,A,L,2,4 - xMS_C,N,O,C,A,L,3,4 - xMS_C,N,O,C,A,L,4,4  = 0
 c122:  x_C,N,O,C,A,L,4 - xMS_C,N,O,C,A,L,0,5 - xMS_C,N,O,C,A,L,1,5
        - xMS_C,N,O,C,A,L,2,5 - xMS_C,N,O,C,A,L,3,5 - xMS_C,N,O,C,A,L,4,5  = 0
 c123:  x_C,N,O,R,A,L,4 - xMS_C,N,O,R,A,L,0,1 - xMS_C,N,O,R,A,L,1,1
        - xMS_C,N,O,R,A,L,2,1 - xMS_C,N,O,R,A,L,3,1 - xMS_C,N,O,R,A,L,4,1  = 0
 c124:  x_C,N,O,R,A,L,4 - xMS_C,N,O,R,A,L,0,2 - xMS_C,N,O,R,A,L,1,2
        - xMS_C,N,O,R,A,L,2,2 - xMS_C,N,O,R,A,L,3,2 - xMS_C,N,O,R,A,L,4,2  = 0
 c125:  x_C,N,O,R,A,L,4 - xMS_C,N,O,R,A,L,0,3 - xMS_C,N,O,R,A,L,1,3
        - xMS_C,N,O,R,A,L,2,3 - xMS_C,N,O,R,A,L,3,3 - xMS_C,N,O,R,A,L,4,3  = 0
 c126:  x_C,N,O,R,A,L,4 - xMS_C,N,O,R,A,L,0,4 - xMS_C,N,O,R,A,L,1,4
        - xMS_C,N,O,R,A,L,2,4 - xMS_C,N,O,R,A,L,3,4 - xMS_C,N,O,R,A,L,4,4  = 0
 c127:  x_C,N,O,R,A,L,4 - xMS_C,N,O,R,A,L,0,5 - xMS_C,N,O,R,A,L,1,5
        - xMS_C,N,O,R,A,L,2,5 - xMS_C,N,O,R,A,L,3,5 - xMS_C,N,O,R,A,L,4,5  = 0
 c128:  x_B,R,N,L,A,L,0 - xNG_B,R,N,L,A,L,1  = 0
 c129:  x_B,R,N,C,A,L,0 - xNG_B,R,N,C,A,L,1  = 0
 c130:  x_B,R,N,R,A,L,0 - xNG_B,R,N,R,A,L,1  = 0
 c131:  x_B,R,P,L,A,L,0 - xNG_B,R,P,L,A,L,1  = 0
 c132:  x_B,R,P,C,A,L,0 - xNG_B,R,P,C,A,L,1  = 0
 c133:  x_B,R,P,R,A,L,0 - xNG_B,R,P,R,A,L,1  = 0
 c134:  x_B,N,N,L,A,L,0 - xNG_B,N,N,L,A,L,1  = 0
 c135:  x_B,N,N,C,A,L,0 - xNG_B,N,N,C,A,L,1  = 0
 c136:  x_B,N,P,R,A,L,0 - xNG_B,N,P,R,A,L,1  = 0
 c137:  x_C,N,N,L,A,L,0 - xNG_C,N,N,L,A,L,1  = 0
 c138:  x_C,N,N,H,A,L,0 - xNG_C,N,N,H,A,L,1  = 0
 c139:  x_C,N,N,C,A,L,0 - xNG_C,N,N,C,A,L,1  = 0
 c140:  x_C,N,N,R,A,L,0 - xNG_C,N,N,R,A,L,1  = 0
 c141:  x_C,N,O,L,A,L,0 - xNG_C,N,O,L,A,L,1  = 0
 c142:  x_C,N,O,H,A,L,0 - xNG_C,N,O,H,A,L,1  = 0
 c143:  x_C,N,O,C,A,L,0 - xNG_C,N,O,C,A,L,1  = 0
 c144:  x_C,N,O,R,A,L,0 - xNG_C,N,O,R,A,L,1  = 0
 c145:  0.998 xNG_B,R,N,L,A,L,1 - xNG_B,R,N,L,A,L,2  = 0
 c146:  0.998 xNG_B,R,N,L,A,L,2 - xNG_B,R,N,L,A,L,3  = 0
 c147:  0.998 xNG_B,R,N,L,A,L,3 - xNG_B,R,N,L,A,L,4  = 0
 c148:  0.998 xNG_B,R,N,L,A,L,4 - xNG_B,R,N,L,A,L,5  = 0
 c149:  0.998 xNG_B,R,N,C,A,L,1 - xNG_B,R,N,C,A,L,2  = 0
 c150:  0.998 xNG_B,R,N,C,A,L,2 - xNG_B,R,N,C,A,L,3  = 0
 c151:  0.998 xNG_B,R,N,C,A,L,3 - xNG_B,R,N,C,A,L,4  = 0
 c152:  0.998 xNG_B,R,N,C,A,L,4 - xNG_B,R,N,C,A,L,5  = 0
 c153:  0.998 xNG_B,R,N,R,A,L,1 - xNG_B,R,N,R,A,L,2  = 0
 c154:  0.998 xNG_B,R,N,R,A,L,2 - xNG_B,R,N,R,A,L,3  = 0
 c155:  0.998 xNG_B,R,N,R,A,L,3 - xNG_B,R,N,R,A,L,4  = 0
 c156:  0.998 xNG_B,R,N,R,A,L,4 - xNG_B,R,N,R,A,L,5  = 0
 c157:  0.998 xNG_B,R,P,L,A,L,1 - xNG_B,R,P,L,A,L,2  = 0
 c158:  0.998 xNG_B,R,P,L,A,L,2 - xNG_B,R,P,L,A,L,3  = 0
 c159:  0.998 xNG_B,R,P,L,A,L,3 - xNG_B,R,P,L,A,L,4  = 0
 c160:  0.998 xNG_B,R,P,L,A,L,4 - xNG_B,R,P,L,A,L,5  = 0
 c161:  0.998 xNG_B,R,P,C,A,L,1 - xNG_B,R,P,C,A,L,2  = 0
 c162:  0.998 xNG_B,R,P,C,A,L,2 - xNG_B,R,P,C,A,L,3  = 0
 c163:  0.998 xNG_B,R,P,C,A,L,3 - xNG_B,R,P,C,A,L,4  = 0
 c164:  0.998 xNG_B,R,P,C,A,L,4 - xNG_B,R,P,C,A,L,5  = 0
 c165:  0.998 xNG_B,R,P,R,A,L,1 - xNG_B,R,P,R,A,L,2  = 0
 c166:  0.998 xNG_B,R,P,R,A,L,2 - xNG_B,R,P,R,A,L,3  = 0
 c167:  0.998 xNG_B,R,P,R,A,L,3 - xNG_B,R,P,R,A,L,4  = 0
 c168:  0.998 xNG_B,R,P,R,A,L,4 - xNG_B,R,P,R,A,L,5  = 0
 c169:  0.998 xNG_B,N,N,L,A,L,1 - xNG_B,N,N,L,A,L,2  = 0
 c170:  0.998 xNG_B,N,N,L,A,L,2 - xNG_B,N,N,L,A,L,3  = 0
 c171:  0.998 xNG_B,N,N,L,A,L,3 - xNG_B,N,N,L,A,L,4  = 0
 c172:  0.998 xNG_B,N,N,L,A,L,4 - xNG_B,N,N,L,A,L,5  = 0
 c173:  0.998 xNG_B,N,N,C,A,L,1 - xNG_B,N,N,C,A,L,2  = 0
 c174:  0.998 xNG_B,N,N,C,A,L,2 - xNG_B,N,N,C,A,L,3  = 0
 c175:  0.998 xNG_B,N,N,C,A,L,3 - xNG_B,N,N,C,A,L,4  = 0
 c176:  0.998 xNG_B,N,N,C,A,L,4 - xNG_B,N,N,C,A,L,5  = 0
 c177:  0.998 xNG_B,N,P,R,A,L,1 - xNG_B,N,P,R,A,L,2  = 0
 c178:  0.998 xNG_B,N,P,R,A,L,2 - xNG_B,N,P,R,A,L,3  = 0
 c179:  0.998 xNG_B,N,P,R,A,L,3 - xNG_B,N,P,R,A,L,4  = 0
 c180:  0.998 xNG_B,N,P,R,A,L,4 - xNG_B,N,P,R,A,L,5  = 0
 c181:  0.998 xNG_C,N,N,L,A,L,1 - xNG_C,N,N,L,A,L,2  = 0
 c182:  0.998 xNG_C,N,N,L,A,L,2 - xNG_C,N,N,L,A,L,3  = 0
 c183:  0.998 xNG_C,N,N,L,A,L,3 - xNG_C,N,N,L,A,L,4  = 0
 c184:  0.998 xNG_C,N,N,L,A,L,4 - xNG_C,N,N,L,A,L,5  = 0
 c185:  0.998 xNG_C,N,N,H,A,L,1 - xNG_C,N,N,H,A,L,2  = 0
 c186:  0.998 xNG_C,N,N,H,A,L,2 - xNG_C,N,N,H,A,L,3  = 0
 c187:  0.998 xNG_C,N,N,H,A,L,3 - xNG_C,N,N,H,A,L,4  = 0
 c188:  0.998 xNG_C,N,N,H,A,L,4 - xNG_C,N,N,H,A,L,5  = 0
 c189:  0.998 xNG_C,N,N,C,A,L,1 - xNG_C,N,N,C,A,L,2  = 0
 c190:  0.998 xNG_C,N,N,C,A,L,2 - xNG_C,N,N,C,A,L,3  = 0
 c191:  0.998 xNG_C,N,N,C,A,L,3 - xNG_C,N,N,C,A,L,4  = 0
 c192:  0.998 xNG_C,N,N,C,A,L,4 - xNG_C,N,N,C,A,L,5  = 0
 c193:  0.998 xNG_C,N,N,R,A,L,1 - xNG_C,N,N,R,A,L,2  = 0
 c194:  0.998 xNG_C,N,N,R,A,L,2 - xNG_C,N,N,R,A,L,3  = 0
 c195:  0.998 xNG_C,N,N,R,A,L,3 - xNG_C,N,N,R,A,L,4  = 0
 c196:  0.998 xNG_C,N,N,R,A,L,4 - xNG_C,N,N,R,A,L,5  = 0
 c197:  0.998 xNG_C,N,O,L,A,L,1 - xNG_C,N,O,L,A,L,2  = 0
 c198:  0.998 xNG_C,N,O,L,A,L,2 - xNG_C,N,O,L,A,L,3  = 0
 c199:  0.998 xNG_C,N,O,L,A,L,3 - xNG_C,N,O,L,A,L,4  = 0
 c200:  0.998 xNG_C,N,O,L,A,L,4 - xNG_C,N,O,L,A,L,5  = 0
 c201:  0.998 xNG_C,N,O,H,A,L,1 - xNG_C,N,O,H,A,L,2  = 0
 c202:  0.998 xNG_C,N,O,H,A,L,2 - xNG_C,N,O,H,A,L,3  = 0
 c203:  0.998 xNG_C,N,O,H,A,L,3 - xNG_C,N,O,H,A,L,4  = 0
 c204:  0.998 xNG_C,N,O,H,A,L,4 - xNG_C,N,O,H,A,L,5  = 0
 c205:  0.998 xNG_C,N,O,C,A,L,1 - xNG_C,N,O,C,A,L,2  = 0
 c206:  0.998 xNG_C,N,O,C,A,L,2 - xNG_C,N,O,C,A,L,3  = 0
 c207:  0.998 xNG_C,N,O,C,A,L,3 - xNG_C,N,O,C,A,L,4  = 0
 c208:  0.998 xNG_C,N,O,C,A,L,4 - xNG_C,N,O,C,A,L,5  = 0
 c209:  0.998 xNG_C,N,O,R,A,L,1 - xNG_C,N,O,R,A,L,2  = 0
 c210:  0.998 xNG_C,N,O,R,A,L,2 - xNG_C,N,O,R,A,L,3  = 0
 c211:  0.998 xNG_C,N,O,R,A,L,3 - xNG_C,N,O,R,A,L,4  = 0
 c212:  0.998 xNG_C,N,O,R,A,L,4 - xNG_C,N,O,R,A,L,5  = 0
 c213:  x_B,R,N,L,A,L,1 - xPB_B,R,N,L,A,L,0,1 - xPB_B,R,N,L,A,L,1,1
        - xPB_B,R,N,L,A,L,2,1 - xPB_B,R,N,L,A,L,3,1 - xPB_B,R,N,L,A,L,4,1  = 0
 c214:  x_B,R,N,C,A,L,1 - xPB_B,R,N,C,A,L,0,1 - xPB_B,R,N,C,A,L,1,1
        - xPB_B,R,N,C,A,L,2,1 - xPB_B,R,N,C,A,L,3,1 - xPB_B,R,N,C,A,L,4,1  = 0
 c215:  x_B,R,N,R,A,L,1 - xPB_B,R,N,R,A,L,0,1 - xPB_B,R,N,R,A,L,1,1
        - xPB_B,R,N,R,A,L,2,1 - xPB_B,R,N,R,A,L,3,1 - xPB_B,R,N,R,A,L,4,1  = 0
 c216:  x_B,R,P,L,A,L,1 - xPB_B,R,P,L,A,L,0,1 - xPB_B,R,P,L,A,L,1,1
        - xPB_B,R,P,L,A,L,2,1 - xPB_B,R,P,L,A,L,3,1 - xPB_B,R,P,L,A,L,4,1  = 0
 c217:  x_B,R,P,C,A,L,1 - xPB_B,R,P,C,A,L,0,1 - xPB_B,R,P,C,A,L,1,1
        - xPB_B,R,P,C,A,L,2,1 - xPB_B,R,P,C,A,L,3,1 - xPB_B,R,P,C,A,L,4,1  = 0
 c218:  x_B,R,P,R,A,L,1 - xPB_B,R,P,R,A,L,0,1 - xPB_B,R,P,R,A,L,1,1
        - xPB_B,R,P,R,A,L,2,1 - xPB_B,R,P,R,A,L,3,1 - xPB_B,R,P,R,A,L,4,1  = 0
 c219:  x_B,N,N,L,A,L,1 - xPB_B,N,N,L,A,L,0,1 - xPB_B,N,N,L,A,L,1,1
        - xPB_B,N,N,L,A,L,2,1 - xPB_B,N,N,L,A,L,3,1 - xPB_B,N,N,L,A,L,4,1  = 0
 c220:  x_B,N,N,C,A,L,1 - xPB_B,N,N,C,A,L,0,1 - xPB_B,N,N,C,A,L,1,1
        - xPB_B,N,N,C,A,L,2,1 - xPB_B,N,N,C,A,L,3,1 - xPB_B,N,N,C,A,L,4,1  = 0
 c221:  x_B,N,P,R,A,L,1 - xPB_B,N,P,R,A,L,0,1 - xPB_B,N,P,R,A,L,1,1
        - xPB_B,N,P,R,A,L,2,1 - xPB_B,N,P,R,A,L,3,1 - xPB_B,N,P,R,A,L,4,1  = 0
 c222:  x_C,N,N,L,A,L,1 - xPB_C,N,N,L,A,L,0,1 - xPB_C,N,N,L,A,L,1,1
        - xPB_C,N,N,L,A,L,2,1 - xPB_C,N,N,L,A,L,3,1 - xPB_C,N,N,L,A,L,4,1  = 0
 c223:  x_C,N,N,H,A,L,1 - xPB_C,N,N,H,A,L,0,1 - xPB_C,N,N,H,A,L,1,1
        - xPB_C,N,N,H,A,L,2,1 - xPB_C,N,N,H,A,L,3,1 - xPB_C,N,N,H,A,L,4,1  = 0
 c224:  x_C,N,N,C,A,L,1 - xPB_C,N,N,C,A,L,0,1 - xPB_C,N,N,C,A,L,1,1
        - xPB_C,N,N,C,A,L,2,1 - xPB_C,N,N,C,A,L,3,1 - xPB_C,N,N,C,A,L,4,1  = 0
 c225:  x_C,N,N,R,A,L,1 - xPB_C,N,N,R,A,L,0,1 - xPB_C,N,N,R,A,L,1,1
        - xPB_C,N,N,R,A,L,2,1 - xPB_C,N,N,R,A,L,3,1 - xPB_C,N,N,R,A,L,4,1  = 0
 c226:  x_C,N,O,L,A,L,1 - xPB_C,N,O,L,A,L,0,1 - xPB_C,N,O,L,A,L,1,1
        - xPB_C,N,O,L,A,L,2,1 - xPB_C,N,O,L,A,L,3,1 - xPB_C,N,O,L,A,L,4,1  = 0
 c227:  x_C,N,O,H,A,L,1 - xPB_C,N,O,H,A,L,0,1 - xPB_C,N,O,H,A,L,1,1
        - xPB_C,N,O,H,A,L,2,1 - xPB_C,N,O,H,A,L,3,1 - xPB_C,N,O,H,A,L,4,1  = 0
 c228:  x_C,N,O,C,A,L,1 - xPB_C,N,O,C,A,L,0,1 - xPB_C,N,O,C,A,L,1,1
        - xPB_C,N,O,C,A,L,2,1 - xPB_C,N,O,C,A,L,3,1 - xPB_C,N,O,C,A,L,4,1  = 0
 c229:  x_C,N,O,R,A,L,1 - xPB_C,N,O,R,A,L,0,1 - xPB_C,N,O,R,A,L,1,1
        - xPB_C,N,O,R,A,L,2,1 - xPB_C,N,O,R,A,L,3,1 - xPB_C,N,O,R,A,L,4,1  = 0
 c230:  0.998 xPB_B,R,N,L,A,L,0,1 - xPB_B,R,N,L,A,L,0,2  = 0
 c231:  0.998 xPB_B,R,N,L,A,L,0,2 - xPB_B,R,N,L,A,L,0,3  = 0
 c232:  0.998 xPB_B,R,N,L,A,L,0,3 - xPB_B,R,N,L,A,L,0,4  = 0
 c233:  0.998 xPB_B,R,N,L,A,L,0,4 - xPB_B,R,N,L,A,L,0,5  = 0
 c234:  0.998 xPB_B,R,N,L,A,L,1,1 - xPB_B,R,N,L,A,L,1,2  = 0
 c235:  0.998 xPB_B,R,N,L,A,L,1,2 - xPB_B,R,N,L,A,L,1,3  = 0
 c236:  0.998 xPB_B,R,N,L,A,L,1,3 - xPB_B,R,N,L,A,L,1,4  = 0
 c237:  0.998 xPB_B,R,N,L,A,L,1,4 - xPB_B,R,N,L,A,L,1,5  = 0
 c238:  0.998 xPB_B,R,N,L,A,L,2,1 - xPB_B,R,N,L,A,L,2,2  = 0
 c239:  0.998 xPB_B,R,N,L,A,L,2,2 - xPB_B,R,N,L,A,L,2,3  = 0
 c240:  0.998 xPB_B,R,N,L,A,L,2,3 - xPB_B,R,N,L,A,L,2,4  = 0
 c241:  0.998 xPB_B,R,N,L,A,L,2,4 - xPB_B,R,N,L,A,L,2,5  = 0
 c242:  0.998 xPB_B,R,N,L,A,L,3,1 - xPB_B,R,N,L,A,L,3,2  = 0
 c243:  0.998 xPB_B,R,N,L,A,L,3,2 - xPB_B,R,N,L,A,L,3,3  = 0
 c244:  0.998 xPB_B,R,N,L,A,L,3,3 - xPB_B,R,N,L,A,L,3,4  = 0
 c245:  0.998 xPB_B,R,N,L,A,L,3,4 - xPB_B,R,N,L,A,L,3,5  = 0
 c246:  0.998 xPB_B,R,N,L,A,L,4,1 - xPB_B,R,N,L,A,L,4,2  = 0
 c247:  0.998 xPB_B,R,N,L,A,L,4,2 - xPB_B,R,N,L,A,L,4,3  = 0
 c248:  0.998 xPB_B,R,N,L,A,L,4,3 - xPB_B,R,N,L,A,L,4,4  = 0
 c249:  0.998 xPB_B,R,N,L,A,L,4,4 - xPB_B,R,N,L,A,L,4,5  = 0
 c250:  0.998 xPB_B,R,N,C,A,L,0,1 - xPB_B,R,N,C,A,L,0,2  = 0
 c251:  0.998 xPB_B,R,N,C,A,L,0,2 - xPB_B,R,N,C,A,L,0,3  = 0
 c252:  0.998 xPB_B,R,N,C,A,L,0,3 - xPB_B,R,N,C,A,L,0,4  = 0
 c253:  0.998 xPB_B,R,N,C,A,L,0,4 - xPB_B,R,N,C,A,L,0,5  = 0
 c254:  0.998 xPB_B,R,N,C,A,L,1,1 - xPB_B,R,N,C,A,L,1,2  = 0
 c255:  0.998 xPB_B,R,N,C,A,L,1,2 - xPB_B,R,N,C,A,L,1,3  = 0
 c256:  0.998 xPB_B,R,N,C,A,L,1,3 - xPB_B,R,N,C,A,L,1,4  = 0
 c257:  0.998 xPB_B,R,N,C,A,L,1,4 - xPB_B,R,N,C,A,L,1,5  = 0
 c258:  0.998 xPB_B,R,N,C,A,L,2,1 - xPB_B,R,N,C,A,L,2,2  = 0
 c259:  0.998 xPB_B,R,N,C,A,L,2,2 - xPB_B,R,N,C,A,L,2,3  = 0
 c260:  0.998 xPB_B,R,N,C,A,L,2,3 - xPB_B,R,N,C,A,L,2,4  = 0
 c261:  0.998 xPB_B,R,N,C,A,L,2,4 - xPB_B,R,N,C,A,L,2,5  = 0
 c262:  0.998 xPB_B,R,N,C,A,L,3,1 - xPB_B,R,N,C,A,L,3,2  = 0
 c263:  0.998 xPB_B,R,N,C,A,L,3,2 - xPB_B,R,N,C,A,L,3,3  = 0
 c264:  0.998 xPB_B,R,N,C,A,L,3,3 - xPB_B,R,N,C,A,L,3,4  = 0
 c265:  0.998 xPB_B,R,N,C,A,L,3,4 - xPB_B,R,N,C,A,L,3,5  = 0
 c266:  0.998 xPB_B,R,N,C,A,L,4,1 - xPB_B,R,N,C,A,L,4,2  = 0
 c267:  0.998 xPB_B,R,N,C,A,L,4,2 - xPB_B,R,N,C,A,L,4,3  = 0
 c268:  0.998 xPB_B,R,N,C,A,L,4,3 - xPB_B,R,N,C,A,L,4,4  = 0
 c269:  0.998 xPB_B,R,N,C,A,L,4,4 - xPB_B,R,N,C,A,L,4,5  = 0
 c270:  0.998 xPB_B,R,N,R,A,L,0,1 - xPB_B,R,N,R,A,L,0,2  = 0
 c271:  0.998 xPB_B,R,N,R,A,L,0,2 - xPB_B,R,N,R,A,L,0,3  = 0
 c272:  0.998 xPB_B,R,N,R,A,L,0,3 - xPB_B,R,N,R,A,L,0,4  = 0
 c273:  0.998 xPB_B,R,N,R,A,L,0,4 - xPB_B,R,N,R,A,L,0,5  = 0
 c274:  0.998 xPB_B,R,N,R,A,L,1,1 - xPB_B,R,N,R,A,L,1,2  = 0
 c275:  0.998 xPB_B,R,N,R,A,L,1,2 - xPB_B,R,N,R,A,L,1,3  = 0
 c276:  0.998 xPB_B,R,N,R,A,L,1,3 - xPB_B,R,N,R,A,L,1,4  = 0
 c277:  0.998 xPB_B,R,N,R,A,L,1,4 - xPB_B,R,N,R,A,L,1,5  = 0
 c278:  0.998 xPB_B,R,N,R,A,L,2,1 - xPB_B,R,N,R,A,L,2,2  = 0
 c279:  0.998 xPB_B,R,N,R,A,L,2,2 - xPB_B,R,N,R,A,L,2,3  = 0
 c280:  0.998 xPB_B,R,N,R,A,L,2,3 - xPB_B,R,N,R,A,L,2,4  = 0
 c281:  0.998 xPB_B,R,N,R,A,L,2,4 - xPB_B,R,N,R,A,L,2,5  = 0
 c282:  0.998 xPB_B,R,N,R,A,L,3,1 - xPB_B,R,N,R,A,L,3,2  = 0
 c283:  0.998 xPB_B,R,N,R,A,L,3,2 - xPB_B,R,N,R,A,L,3,3  = 0
 c284:  0.998 xPB_B,R,N,R,A,L,3,3 - xPB_B,R,N,R,A,L,3,4  = 0
 c285:  0.998 xPB_B,R,N,R,A,L,3,4 - xPB_B,R,N,R,A,L,3,5  = 0
 c286:  0.998 xPB_B,R,N,R,A,L,4,1 - xPB_B,R,N,R,A,L,4,2  = 0
 c287:  0.998 xPB_B,R,N,R,A,L,4,2 - xPB_B,R,N,R,A,L,4,3  = 0
 c288:  0.998 xPB_B,R,N,R,A,L,4,3 - xPB_B,R,N,R,A,L,4,4  = 0
 c289:  0.998 xPB_B,R,N,R,A,L,4,4 - xPB_B,R,N,R,A,L,4,5  = 0
 c290:  0.998 xPB_B,R,P,L,A,L,0,1 - xPB_B,R,P,L,A,L,0,2  = 0
 c291:  0.998 xPB_B,R,P,L,A,L,0,2 - xPB_B,R,P,L,A,L,0,3  = 0
 c292:  0.998 xPB_B,R,P,L,A,L,0,3 - xPB_B,R,P,L,A,L,0,4  = 0
 c293:  0.998 xPB_B,R,P,L,A,L,0,4 - xPB_B,R,P,L,A,L,0,5  = 0
 c294:  0.998 xPB_B,R,P,L,A,L,1,1 - xPB_B,R,P,L,A,L,1,2  = 0
 c295:  0.998 xPB_B,R,P,L,A,L,1,2 - xPB_B,R,P,L,A,L,1,3  = 0
 c296:  0.998 xPB_B,R,P,L,A,L,1,3 - xPB_B,R,P,L,A,L,1,4  = 0
 c297:  0.998 xPB_B,R,P,L,A,L,1,4 - xPB_B,R,P,L,A,L,1,5  = 0
 c298:  0.998 xPB_B,R,P,L,A,L,2,1 - xPB_B,R,P,L,A,L,2,2  = 0
 c299:  0.998 xPB_B,R,P,L,A,L,2,2 - xPB_B,R,P,L,A,L,2,3  = 0
 c300:  0.998 xPB_B,R,P,L,A,L,2,3 - xPB_B,R,P,L,A,L,2,4  = 0
 c301:  0.998 xPB_B,R,P,L,A,L,2,4 - xPB_B,R,P,L,A,L,2,5  = 0
 c302:  0.998 xPB_B,R,P,L,A,L,3,1 - xPB_B,R,P,L,A,L,3,2  = 0
 c303:  0.998 xPB_B,R,P,L,A,L,3,2 - xPB_B,R,P,L,A,L,3,3  = 0
 c304:  0.998 xPB_B,R,P,L,A,L,3,3 - xPB_B,R,P,L,A,L,3,4  = 0
 c305:  0.998 xPB_B,R,P,L,A,L,3,4 - xPB_B,R,P,L,A,L,3,5  = 0
 c306:  0.998 xPB_B,R,P,L,A,L,4,1 - xPB_B,R,P,L,A,L,4,2  = 0
 c307:  0.998 xPB_B,R,P,L,A,L,4,2 - xPB_B,R,P,L,A,L,4,3  = 0
 c308:  0.998 xPB_B,R,P,L,A,L,4,3 - xPB_B,R,P,L,A,L,4,4  = 0
 c309:  0.998 xPB_B,R,P,L,A,L,4,4 - xPB_B,R,P,L,A,L,4,5  = 0
 c310:  0.998 xPB_B,R,P,C,A,L,0,1 - xPB_B,R,P,C,A,L,0,2  = 0
 c311:  0.998 xPB_B,R,P,C,A,L,0,2 - xPB_B,R,P,C,A,L,0,3  = 0
 c312:  0.998 xPB_B,R,P,C,A,L,0,3 - xPB_B,R,P,C,A,L,0,4  = 0
 c313:  0.998 xPB_B,R,P,C,A,L,0,4 - xPB_B,R,P,C,A,L,0,5  = 0
 c314:  0.998 xPB_B,R,P,C,A,L,1,1 - xPB_B,R,P,C,A,L,1,2  = 0
 c315:  0.998 xPB_B,R,P,C,A,L,1,2 - xPB_B,R,P,C,A,L,1,3  = 0
 c316:  0.998 xPB_B,R,P,C,A,L,1,3 - xPB_B,R,P,C,A,L,1,4  = 0
 c317:  0.998 xPB_B,R,P,C,A,L,1,4 - xPB_B,R,P,C,A,L,1,5  = 0
 c318:  0.998 xPB_B,R,P,C,A,L,2,1 - xPB_B,R,P,C,A,L,2,2  = 0
 c319:  0.998 xPB_B,R,P,C,A,L,2,2 - xPB_B,R,P,C,A,L,2,3  = 0
 c320:  0.998 xPB_B,R,P,C,A,L,2,3 - xPB_B,R,P,C,A,L,2,4  = 0
 c321:  0.998 xPB_B,R,P,C,A,L,2,4 - xPB_B,R,P,C,A,L,2,5  = 0
 c322:  0.998 xPB_B,R,P,C,A,L,3,1 - xPB_B,R,P,C,A,L,3,2  = 0
 c323:  0.998 xPB_B,R,P,C,A,L,3,2 - xPB_B,R,P,C,A,L,3,3  = 0
 c324:  0.998 xPB_B,R,P,C,A,L,3,3 - xPB_B,R,P,C,A,L,3,4  = 0
 c325:  0.998 xPB_B,R,P,C,A,L,3,4 - xPB_B,R,P,C,A,L,3,5  = 0
 c326:  0.998 xPB_B,R,P,C,A,L,4,1 - xPB_B,R,P,C,A,L,4,2  = 0
 c327:  0.998 xPB_B,R,P,C,A,L,4,2 - xPB_B,R,P,C,A,L,4,3  = 0
 c328:  0.998 xPB_B,R,P,C,A,L,4,3 - xPB_B,R,P,C,A,L,4,4  = 0
 c329:  0.998 xPB_B,R,P,C,A,L,4,4 - xPB_B,R,P,C,A,L,4,5  = 0
 c330:  0.998 xPB_B,R,P,R,A,L,0,1 - xPB_B,R,P,R,A,L,0,2  = 0
 c331:  0.998 xPB_B,R,P,R,A,L,0,2 - xPB_B,R,P,R,A,L,0,3  = 0
 c332:  0.998 xPB_B,R,P,R,A,L,0,3 - xPB_B,R,P,R,A,L,0,4  = 0
 c333:  0.998 xPB_B,R,P,R,A,L,0,4 - xPB_B,R,P,R,A,L,0,5  = 0
 c334:  0.998 xPB_B,R,P,R,A,L,1,1 - xPB_B,R,P,R,A,L,1,2  = 0
 c335:  0.998 xPB_B,R,P,R,A,L,1,2 - xPB_B,R,P,R,A,L,1,3  = 0
 c336:  0.998 xPB_B,R,P,R,A,L,1,3 - xPB_B,R,P,R,A,L,1,4  = 0
 c337:  0.998 xPB_B,R,P,R,A,L,1,4 - xPB_B,R,P,R,A,L,1,5  = 0
 c338:  0.998 xPB_B,R,P,R,A,L,2,1 - xPB_B,R,P,R,A,L,2,2  = 0
 c339:  0.998 xPB_B,R,P,R,A,L,2,2 - xPB_B,R,P,R,A,L,2,3  = 0
 c340:  0.998 xPB_B,R,P,R,A,L,2,3 - xPB_B,R,P,R,A,L,2,4  = 0
 c341:  0.998 xPB_B,R,P,R,A,L,2,4 - xPB_B,R,P,R,A,L,2,5  = 0
 c342:  0.998 xPB_B,R,P,R,A,L,3,1 - xPB_B,R,P,R,A,L,3,2  = 0
 c343:  0.998 xPB_B,R,P,R,A,L,3,2 - xPB_B,R,P,R,A,L,3,3  = 0
 c344:  0.998 xPB_B,R,P,R,A,L,3,3 - xPB_B,R,P,R,A,L,3,4  = 0
 c345:  0.998 xPB_B,R,P,R,A,L,3,4 - xPB_B,R,P,R,A,L,3,5  = 0
 c346:  0.998 xPB_B,R,P,R,A,L,4,1 - xPB_B,R,P,R,A,L,4,2  = 0
 c347:  0.998 xPB_B,R,P,R,A,L,4,2 - xPB_B,R,P,R,A,L,4,3  = 0
 c348:  0.998 xPB_B,R,P,R,A,L,4,3 - xPB_B,R,P,R,A,L,4,4  = 0
 c349:  0.998 xPB_B,R,P,R,A,L,4,4 - xPB_B,R,P,R,A,L,4,5  = 0
 c350:  0.998 xPB_B,N,N,L,A,L,0,1 - xPB_B,N,N,L,A,L,0,2  = 0
 c351:  0.998 xPB_B,N,N,L,A,L,0,2 - xPB_B,N,N,L,A,L,0,3  = 0
 c352:  0.998 xPB_B,N,N,L,A,L,0,3 - xPB_B,N,N,L,A,L,0,4  = 0
 c353:  0.998 xPB_B,N,N,L,A,L,0,4 - xPB_B,N,N,L,A,L,0,5  = 0
 c354:  0.998 xPB_B,N,N,L,A,L,1,1 - xPB_B,N,N,L,A,L,1,2  = 0
 c355:  0.998 xPB_B,N,N,L,A,L,1,2 - xPB_B,N,N,L,A,L,1,3  = 0
 c356:  0.998 xPB_B,N,N,L,A,L,1,3 - xPB_B,N,N,L,A,L,1,4  = 0
 c357:  0.998 xPB_B,N,N,L,A,L,1,4 - xPB_B,N,N,L,A,L,1,5  = 0
 c358:  0.998 xPB_B,N,N,L,A,L,2,1 - xPB_B,N,N,L,A,L,2,2  = 0
 c359:  0.998 xPB_B,N,N,L,A,L,2,2 - xPB_B,N,N,L,A,L,2,3  = 0
 c360:  0.998 xPB_B,N,N,L,A,L,2,3 - xPB_B,N,N,L,A,L,2,4  = 0
 c361:  0.998 xPB_B,N,N,L,A,L,2,4 - xPB_B,N,N,L,A,L,2,5  = 0
 c362:  0.998 xPB_B,N,N,L,A,L,3,1 - xPB_B,N,N,L,A,L,3,2  = 0
 c363:  0.998 xPB_B,N,N,L,A,L,3,2 - xPB_B,N,N,L,A,L,3,3  = 0
 c364:  0.998 xPB_B,N,N,L,A,L,3,3 - xPB_B,N,N,L,A,L,3,4  = 0
 c365:  0.998 xPB_B,N,N,L,A,L,3,4 - xPB_B,N,N,L,A,L,3,5  = 0
 c366:  0.998 xPB_B,N,N,L,A,L,4,1 - xPB_B,N,N,L,A,L,4,2  = 0
 c367:  0.998 xPB_B,N,N,L,A,L,4,2 - xPB_B,N,N,L,A,L,4,3  = 0
 c368:  0.998 xPB_B,N,N,L,A,L,4,3 - xPB_B,N,N,L,A,L,4,4  = 0
 c369:  0.998 xPB_B,N,N,L,A,L,4,4 - xPB_B,N,N,L,A,L,4,5  = 0
 c370:  0.998 xPB_B,N,N,C,A,L,0,1 - xPB_B,N,N,C,A,L,0,2  = 0
 c371:  0.998 xPB_B,N,N,C,A,L,0,2 - xPB_B,N,N,C,A,L,0,3  = 0
 c372:  0.998 xPB_B,N,N,C,A,L,0,3 - xPB_B,N,N,C,A,L,0,4  = 0
 c373:  0.998 xPB_B,N,N,C,A,L,0,4 - xPB_B,N,N,C,A,L,0,5  = 0
 c374:  0.998 xPB_B,N,N,C,A,L,1,1 - xPB_B,N,N,C,A,L,1,2  = 0
 c375:  0.998 xPB_B,N,N,C,A,L,1,2 - xPB_B,N,N,C,A,L,1,3  = 0
 c376:  0.998 xPB_B,N,N,C,A,L,1,3 - xPB_B,N,N,C,A,L,1,4  = 0
 c377:  0.998 xPB_B,N,N,C,A,L,1,4 - xPB_B,N,N,C,A,L,1,5  = 0
 c378:  0.998 xPB_B,N,N,C,A,L,2,1 - xPB_B,N,N,C,A,L,2,2  = 0
 c379:  0.998 xPB_B,N,N,C,A,L,2,2 - xPB_B,N,N,C,A,L,2,3  = 0
 c380:  0.998 xPB_B,N,N,C,A,L,2,3 - xPB_B,N,N,C,A,L,2,4  = 0
 c381:  0.998 xPB_B,N,N,C,A,L,2,4 - xPB_B,N,N,C,A,L,2,5  = 0
 c382:  0.998 xPB_B,N,N,C,A,L,3,1 - xPB_B,N,N,C,A,L,3,2  = 0
 c383:  0.998 xPB_B,N,N,C,A,L,3,2 - xPB_B,N,N,C,A,L,3,3  = 0
 c384:  0.998 xPB_B,N,N,C,A,L,3,3 - xPB_B,N,N,C,A,L,3,4  = 0
 c385:  0.998 xPB_B,N,N,C,A,L,3,4 - xPB_B,N,N,C,A,L,3,5  = 0
 c386:  0.998 xPB_B,N,N,C,A,L,4,1 - xPB_B,N,N,C,A,L,4,2  = 0
 c387:  0.998 xPB_B,N,N,C,A,L,4,2 - xPB_B,N,N,C,A,L,4,3  = 0
 c388:  0.998 xPB_B,N,N,C,A,L,4,3 - xPB_B,N,N,C,A,L,4,4  = 0
 c389:  0.998 xPB_B,N,N,C,A,L,4,4 - xPB_B,N,N,C,A,L,4,5  = 0
 c390:  0.998 xPB_B,N,P,R,A,L,0,1 - xPB_B,N,P,R,A,L,0,2  = 0
 c391:  0.998 xPB_B,N,P,R,A,L,0,2 - xPB_B,N,P,R,A,L,0,3  = 0
 c392:  0.998 xPB_B,N,P,R,A,L,0,3 - xPB_B,N,P,R,A,L,0,4  = 0
 c393:  0.998 xPB_B,N,P,R,A,L,0,4 - xPB_B,N,P,R,A,L,0,5  = 0
 c394:  0.998 xPB_B,N,P,R,A,L,1,1 - xPB_B,N,P,R,A,L,1,2  = 0
 c395:  0.998 xPB_B,N,P,R,A,L,1,2 - xPB_B,N,P,R,A,L,1,3  = 0
 c396:  0.998 xPB_B,N,P,R,A,L,1,3 - xPB_B,N,P,R,A,L,1,4  = 0
 c397:  0.998 xPB_B,N,P,R,A,L,1,4 - xPB_B,N,P,R,A,L,1,5  = 0
 c398:  0.998 xPB_B,N,P,R,A,L,2,1 - xPB_B,N,P,R,A,L,2,2  = 0
 c399:  0.998 xPB_B,N,P,R,A,L,2,2 - xPB_B,N,P,R,A,L,2,3  = 0
 c400:  0.998 xPB_B,N,P,R,A,L,2,3 - xPB_B,N,P,R,A,L,2,4  = 0
 c401:  0.998 xPB_B,N,P,R,A,L,2,4 - xPB_B,N,P,R,A,L,2,5  = 0
 c402:  0.998 xPB_B,N,P,R,A,L,3,1 - xPB_B,N,P,R,A,L,3,2  = 0
 c403:  0.998 xPB_B,N,P,R,A,L,3,2 - xPB_B,N,P,R,A,L,3,3  = 0
 c404:  0.998 xPB_B,N,P,R,A,L,3,3 - xPB_B,N,P,R,A,L,3,4  = 0
 c405:  0.998 xPB_B,N,P,R,A,L,3,4 - xPB_B,N,P,R,A,L,3,5  = 0
 c406:  0.998 xPB_B,N,P,R,A,L,4,1 - xPB_B,N,P,R,A,L,4,2  = 0
 c407:  0.998 xPB_B,N,P,R,A,L,4,2 - xPB_B,N,P,R,A,L,4,3  = 0
 c408:  0.998 xPB_B,N,P,R,A,L,4,3 - xPB_B,N,P,R,A,L,4,4  = 0
 c409:  0.998 xPB_B,N,P,R,A,L,4,4 - xPB_B,N,P,R,A,L,4,5  = 0
 c410:  0.998 xPB_C,N,N,L,A,L,0,1 - xPB_C,N,N,L,A,L,0,2  = 0
 c411:  0.998 xPB_C,N,N,L,A,L,0,2 - xPB_C,N,N,L,A,L,0,3  = 0
 c412:  0.998 xPB_C,N,N,L,A,L,0,3 - xPB_C,N,N,L,A,L,0,4  = 0
 c413:  0.998 xPB_C,N,N,L,A,L,0,4 - xPB_C,N,N,L,A,L,0,5  = 0
 c414:  0.998 xPB_C,N,N,L,A,L,1,1 - xPB_C,N,N,L,A,L,1,2  = 0
 c415:  0.998 xPB_C,N,N,L,A,L,1,2 - xPB_C,N,N,L,A,L,1,3  = 0
 c416:  0.998 xPB_C,N,N,L,A,L,1,3 - xPB_C,N,N,L,A,L,1,4  = 0
 c417:  0.998 xPB_C,N,N,L,A,L,1,4 - xPB_C,N,N,L,A,L,1,5  = 0
 c418:  0.998 xPB_C,N,N,L,A,L,2,1 - xPB_C,N,N,L,A,L,2,2  = 0
 c419:  0.998 xPB_C,N,N,L,A,L,2,2 - xPB_C,N,N,L,A,L,2,3  = 0
 c420:  0.998 xPB_C,N,N,L,A,L,2,3 - xPB_C,N,N,L,A,L,2,4  = 0
 c421:  0.998 xPB_C,N,N,L,A,L,2,4 - xPB_C,N,N,L,A,L,2,5  = 0
 c422:  0.998 xPB_C,N,N,L,A,L,3,1 - xPB_C,N,N,L,A,L,3,2  = 0
 c423:  0.998 xPB_C,N,N,L,A,L,3,2 - xPB_C,N,N,L,A,L,3,3  = 0
 c424:  0.998 xPB_C,N,N,L,A,L,3,3 - xPB_C,N,N,L,A,L,3,4  = 0
 c425:  0.998 xPB_C,N,N,L,A,L,3,4 - xPB_C,N,N,L,A,L,3,5  = 0
 c426:  0.998 xPB_C,N,N,L,A,L,4,1 - xPB_C,N,N,L,A,L,4,2  = 0
 c427:  0.998 xPB_C,N,N,L,A,L,4,2 - xPB_C,N,N,L,A,L,4,3  = 0
 c428:  0.998 xPB_C,N,N,L,A,L,4,3 - xPB_C,N,N,L,A,L,4,4  = 0
 c429:  0.998 xPB_C,N,N,L,A,L,4,4 - xPB_C,N,N,L,A,L,4,5  = 0
 c430:  0.998 xPB_C,N,N,H,A,L,0,1 - xPB_C,N,N,H,A,L,0,2  = 0
 c431:  0.998 xPB_C,N,N,H,A,L,0,2 - xPB_C,N,N,H,A,L,0,3  = 0
 c432:  0.998 xPB_C,N,N,H,A,L,0,3 - xPB_C,N,N,H,A,L,0,4  = 0
 c433:  0.998 xPB_C,N,N,H,A,L,0,4 - xPB_C,N,N,H,A,L,0,5  = 0
 c434:  0.998 xPB_C,N,N,H,A,L,1,1 - xPB_C,N,N,H,A,L,1,2  = 0
 c435:  0.998 xPB_C,N,N,H,A,L,1,2 - xPB_C,N,N,H,A,L,1,3  = 0
 c436:  0.998 xPB_C,N,N,H,A,L,1,3 - xPB_C,N,N,H,A,L,1,4  = 0
 c437:  0.998 xPB_C,N,N,H,A,L,1,4 - xPB_C,N,N,H,A,L,1,5  = 0
 c438:  0.998 xPB_C,N,N,H,A,L,2,1 - xPB_C,N,N,H,A,L,2,2  = 0
 c439:  0.998 xPB_C,N,N,H,A,L,2,2 - xPB_C,N,N,H,A,L,2,3  = 0
 c440:  0.998 xPB_C,N,N,H,A,L,2,3 - xPB_C,N,N,H,A,L,2,4  = 0
 c441:  0.998 xPB_C,N,N,H,A,L,2,4 - xPB_C,N,N,H,A,L,2,5  = 0
 c442:  0.998 xPB_C,N,N,H,A,L,3,1 - xPB_C,N,N,H,A,L,3,2  = 0
 c443:  0.998 xPB_C,N,N,H,A,L,3,2 - xPB_C,N,N,H,A,L,3,3  = 0
 c444:  0.998 xPB_C,N,N,H,A,L,3,3 - xPB_C,N,N,H,A,L,3,4  = 0
 c445:  0.998 xPB_C,N,N,H,A,L,3,4 - xPB_C,N,N,H,A,L,3,5  = 0
 c446:  0.998 xPB_C,N,N,H,A,L,4,1 - xPB_C,N,N,H,A,L,4,2  = 0
 c447:  0.998 xPB_C,N,N,H,A,L,4,2 - xPB_C,N,N,H,A,L,4,3  = 0
 c448:  0.998 xPB_C,N,N,H,A,L,4,3 - xPB_C,N,N,H,A,L,4,4  = 0
 c449:  0.998 xPB_C,N,N,H,A,L,4,4 - xPB_C,N,N,H,A,L,4,5  = 0
 c450:  0.998 xPB_C,N,N,C,A,L,0,1 - xPB_C,N,N,C,A,L,0,2  = 0
 c451:  0.998 xPB_C,N,N,C,A,L,0,2 - xPB_C,N,N,C,A,L,0,3  = 0
 c452:  0.998 xPB_C,N,N,C,A,L,0,3 - xPB_C,N,N,C,A,L,0,4  = 0
 c453:  0.998 xPB_C,N,N,C,A,L,0,4 - xPB_C,N,N,C,A,L,0,5  = 0
 c454:  0.998 xPB_C,N,N,C,A,L,1,1 - xPB_C,N,N,C,A,L,1,2  = 0
 c455:  0.998 xPB_C,N,N,C,A,L,1,2 - xPB_C,N,N,C,A,L,1,3  = 0
 c456:  0.998 xPB_C,N,N,C,A,L,1,3 - xPB_C,N,N,C,A,L,1,4  = 0
 c457:  0.998 xPB_C,N,N,C,A,L,1,4 - xPB_C,N,N,C,A,L,1,5  = 0
 c458:  0.998 xPB_C,N,N,C,A,L,2,1 - xPB_C,N,N,C,A,L,2,2  = 0
 c459:  0.998 xPB_C,N,N,C,A,L,2,2 - xPB_C,N,N,C,A,L,2,3  = 0
 c460:  0.998 xPB_C,N,N,C,A,L,2,3 - xPB_C,N,N,C,A,L,2,4  = 0
 c461:  0.998 xPB_C,N,N,C,A,L,2,4 - xPB_C,N,N,C,A,L,2,5  = 0
 c462:  0.998 xPB_C,N,N,C,A,L,3,1 - xPB_C,N,N,C,A,L,3,2  = 0
 c463:  0.998 xPB_C,N,N,C,A,L,3,2 - xPB_C,N,N,C,A,L,3,3  = 0
 c464:  0.998 xPB_C,N,N,C,A,L,3,3 - xPB_C,N,N,C,A,L,3,4  = 0
 c465:  0.998 xPB_C,N,N,C,A,L,3,4 - xPB_C,N,N,C,A,L,3,5  = 0
 c466:  0.998 xPB_C,N,N,C,A,L,4,1 - xPB_C,N,N,C,A,L,4,2  = 0
 c467:  0.998 xPB_C,N,N,C,A,L,4,2 - xPB_C,N,N,C,A,L,4,3  = 0
 c468:  0.998 xPB_C,N,N,C,A,L,4,3 - xPB_C,N,N,C,A,L,4,4  = 0
 c469:  0.998 xPB_C,N,N,C,A,L,4,4 - xPB_C,N,N,C,A,L,4,5  = 0
 c470:  0.998 xPB_C,N,N,R,A,L,0,1 - xPB_C,N,N,R,A,L,0,2  = 0
 c471:  0.998 xPB_C,N,N,R,A,L,0,2 - xPB_C,N,N,R,A,L,0,3  = 0
 c472:  0.998 xPB_C,N,N,R,A,L,0,3 - xPB_C,N,N,R,A,L,0,4  = 0
 c473:  0.998 xPB_C,N,N,R,A,L,0,4 - xPB_C,N,N,R,A,L,0,5  = 0
 c474:  0.998 xPB_C,N,N,R,A,L,1,1 - xPB_C,N,N,R,A,L,1,2  = 0
 c475:  0.998 xPB_C,N,N,R,A,L,1,2 - xPB_C,N,N,R,A,L,1,3  = 0
 c476:  0.998 xPB_C,N,N,R,A,L,1,3 - xPB_C,N,N,R,A,L,1,4  = 0
 c477:  0.998 xPB_C,N,N,R,A,L,1,4 - xPB_C,N,N,R,A,L,1,5  = 0
 c478:  0.998 xPB_C,N,N,R,A,L,2,1 - xPB_C,N,N,R,A,L,2,2  = 0
 c479:  0.998 xPB_C,N,N,R,A,L,2,2 - xPB_C,N,N,R,A,L,2,3  = 0
 c480:  0.998 xPB_C,N,N,R,A,L,2,3 - xPB_C,N,N,R,A,L,2,4  = 0
 c481:  0.998 xPB_C,N,N,R,A,L,2,4 - xPB_C,N,N,R,A,L,2,5  = 0
 c482:  0.998 xPB_C,N,N,R,A,L,3,1 - xPB_C,N,N,R,A,L,3,2  = 0
 c483:  0.998 xPB_C,N,N,R,A,L,3,2 - xPB_C,N,N,R,A,L,3,3  = 0
 c484:  0.998 xPB_C,N,N,R,A,L,3,3 - xPB_C,N,N,R,A,L,3,4  = 0
 c485:  0.998 xPB_C,N,N,R,A,L,3,4 - xPB_C,N,N,R,A,L,3,5  = 0
 c486:  0.998 xPB_C,N,N,R,A,L,4,1 - xPB_C,N,N,R,A,L,4,2  = 0
 c487:  0.998 xPB_C,N,N,R,A,L,4,2 - xPB_C,N,N,R,A,L,4,3  = 0
 c488:  0.998 xPB_C,N,N,R,A,L,4,3 - xPB_C,N,N,R,A,L,4,4  = 0
 c489:  0.998 xPB_C,N,N,R,A,L,4,4 - xPB_C,N,N,R,A,L,4,5  = 0
 c490:  0.998 xPB_C,N,O,L,A,L,0,1 - xPB_C,N,O,L,A,L,0,2  = 0
 c491:  0.998 xPB_C,N,O,L,A,L,0,2 - xPB_C,N,O,L,A,L,0,3  = 0
 c492:  0.998 xPB_C,N,O,L,A,L,0,3 - xPB_C,N,O,L,A,L,0,4  = 0
 c493:  0.998 xPB_C,N,O,L,A,L,0,4 - xPB_C,N,O,L,A,L,0,5  = 0
 c494:  0.998 xPB_C,N,O,L,A,L,1,1 - xPB_C,N,O,L,A,L,1,2  = 0
 c495:  0.998 xPB_C,N,O,L,A,L,1,2 - xPB_C,N,O,L,A,L,1,3  = 0
 c496:  0.998 xPB_C,N,O,L,A,L,1,3 - xPB_C,N,O,L,A,L,1,4  = 0
 c497:  0.998 xPB_C,N,O,L,A,L,1,4 - xPB_C,N,O,L,A,L,1,5  = 0
 c498:  0.998 xPB_C,N,O,L,A,L,2,1 - xPB_C,N,O,L,A,L,2,2  = 0
 c499:  0.998 xPB_C,N,O,L,A,L,2,2 - xPB_C,N,O,L,A,L,2,3  = 0
 c500:  0.998 xPB_C,N,O,L,A,L,2,3 - xPB_C,N,O,L,A,L,2,4  = 0
 c501:  0.998 xPB_C,N,O,L,A,L,2,4 - xPB_C,N,O,L,A,L,2,5  = 0
 c502:  0.998 xPB_C,N,O,L,A,L,3,1 - xPB_C,N,O,L,A,L,3,2  = 0
 c503:  0.998 xPB_C,N,O,L,A,L,3,2 - xPB_C,N,O,L,A,L,3,3  = 0
 c504:  0.998 xPB_C,N,O,L,A,L,3,3 - xPB_C,N,O,L,A,L,3,4  = 0
 c505:  0.998 xPB_C,N,O,L,A,L,3,4 - xPB_C,N,O,L,A,L,3,5  = 0
 c506:  0.998 xPB_C,N,O,L,A,L,4,1 - xPB_C,N,O,L,A,L,4,2  = 0
 c507:  0.998 xPB_C,N,O,L,A,L,4,2 - xPB_C,N,O,L,A,L,4,3  = 0
 c508:  0.998 xPB_C,N,O,L,A,L,4,3 - xPB_C,N,O,L,A,L,4,4  = 0
 c509:  0.998 xPB_C,N,O,L,A,L,4,4 - xPB_C,N,O,L,A,L,4,5  = 0
 c510:  0.998 xPB_C,N,O,H,A,L,0,1 - xPB_C,N,O,H,A,L,0,2  = 0
 c511:  0.998 xPB_C,N,O,H,A,L,0,2 - xPB_C,N,O,H,A,L,0,3  = 0
 c512:  0.998 xPB_C,N,O,H,A,L,0,3 - xPB_C,N,O,H,A,L,0,4  = 0
 c513:  0.998 xPB_C,N,O,H,A,L,0,4 - xPB_C,N,O,H,A,L,0,5  = 0
 c514:  0.998 xPB_C,N,O,H,A,L,1,1 - xPB_C,N,O,H,A,L,1,2  = 0
 c515:  0.998 xPB_C,N,O,H,A,L,1,2 - xPB_C,N,O,H,A,L,1,3  = 0
 c516:  0.998 xPB_C,N,O,H,A,L,1,3 - xPB_C,N,O,H,A,L,1,4  = 0
 c517:  0.998 xPB_C,N,O,H,A,L,1,4 - xPB_C,N,O,H,A,L,1,5  = 0
 c518:  0.998 xPB_C,N,O,H,A,L,2,1 - xPB_C,N,O,H,A,L,2,2  = 0
 c519:  0.998 xPB_C,N,O,H,A,L,2,2 - xPB_C,N,O,H,A,L,2,3  = 0
 c520:  0.998 xPB_C,N,O,H,A,L,2,3 - xPB_C,N,O,H,A,L,2,4  = 0
 c521:  0.998 xPB_C,N,O,H,A,L,2,4 - xPB_C,N,O,H,A,L,2,5  = 0
 c522:  0.998 xPB_C,N,O,H,A,L,3,1 - xPB_C,N,O,H,A,L,3,2  = 0
 c523:  0.998 xPB_C,N,O,H,A,L,3,2 - xPB_C,N,O,H,A,L,3,3  = 0
 c524:  0.998 xPB_C,N,O,H,A,L,3,3 - xPB_C,N,O,H,A,L,3,4  = 0
 c525:  0.998 xPB_C,N,O,H,A,L,3,4 - xPB_C,N,O,H,A,L,3,5  = 0
 c526:  0.998 xPB_C,N,O,H,A,L,4,1 - xPB_C,N,O,H,A,L,4,2  = 0
 c527:  0.998 xPB_C,N,O,H,A,L,4,2 - xPB_C,N,O,H,A,L,4,3  = 0
 c528:  0.998 xPB_C,N,O,H,A,L,4,3 - xPB_C,N,O,H,A,L,4,4  = 0
 c529:  0.998 xPB_C,N,O,H,A,L,4,4 - xPB_C,N,O,H,A,L,4,5  = 0
 c530:  0.998 xPB_C,N,O,C,A,L,0,1 - xPB_C,N,O,C,A,L,0,2  = 0
 c531:  0.998 xPB_C,N,O,C,A,L,0,2 - xPB_C,N,O,C,A,L,0,3  = 0
 c532:  0.998 xPB_C,N,O,C,A,L,0,3 - xPB_C,N,O,C,A,L,0,4  = 0
 c533:  0.998 xPB_C,N,O,C,A,L,0,4 - xPB_C,N,O,C,A,L,0,5  = 0
 c534:  0.998 xPB_C,N,O,C,A,L,1,1 - xPB_C,N,O,C,A,L,1,2  = 0
 c535:  0.998 xPB_C,N,O,C,A,L,1,2 - xPB_C,N,O,C,A,L,1,3  = 0
 c536:  0.998 xPB_C,N,O,C,A,L,1,3 - xPB_C,N,O,C,A,L,1,4  = 0
 c537:  0.998 xPB_C,N,O,C,A,L,1,4 - xPB_C,N,O,C,A,L,1,5  = 0
 c538:  0.998 xPB_C,N,O,C,A,L,2,1 - xPB_C,N,O,C,A,L,2,2  = 0
 c539:  0.998 xPB_C,N,O,C,A,L,2,2 - xPB_C,N,O,C,A,L,2,3  = 0
 c540:  0.998 xPB_C,N,O,C,A,L,2,3 - xPB_C,N,O,C,A,L,2,4  = 0
 c541:  0.998 xPB_C,N,O,C,A,L,2,4 - xPB_C,N,O,C,A,L,2,5  = 0
 c542:  0.998 xPB_C,N,O,C,A,L,3,1 - xPB_C,N,O,C,A,L,3,2  = 0
 c543:  0.998 xPB_C,N,O,C,A,L,3,2 - xPB_C,N,O,C,A,L,3,3  = 0
 c544:  0.998 xPB_C,N,O,C,A,L,3,3 - xPB_C,N,O,C,A,L,3,4  = 0
 c545:  0.998 xPB_C,N,O,C,A,L,3,4 - xPB_C,N,O,C,A,L,3,5  = 0
 c546:  0.998 xPB_C,N,O,C,A,L,4,1 - xPB_C,N,O,C,A,L,4,2  = 0
 c547:  0.998 xPB_C,N,O,C,A,L,4,2 - xPB_C,N,O,C,A,L,4,3  = 0
 c548:  0.998 xPB_C,N,O,C,A,L,4,3 - xPB_C,N,O,C,A,L,4,4  = 0
 c549:  0.998 xPB_C,N,O,C,A,L,4,4 - xPB_C,N,O,C,A,L,4,5  = 0
 c550:  0.998 xPB_C,N,O,R,A,L,0,1 - xPB_C,N,O,R,A,L,0,2  = 0
 c551:  0.998 xPB_C,N,O,R,A,L,0,2 - xPB_C,N,O,R,A,L,0,3  = 0
 c552:  0.998 xPB_C,N,O,R,A,L,0,3 - xPB_C,N,O,R,A,L,0,4  = 0
 c553:  0.998 xPB_C,N,O,R,A,L,0,4 - xPB_C,N,O,R,A,L,0,5  = 0
 c554:  0.998 xPB_C,N,O,R,A,L,1,1 - xPB_C,N,O,R,A,L,1,2  = 0
 c555:  0.998 xPB_C,N,O,R,A,L,1,2 - xPB_C,N,O,R,A,L,1,3  = 0
 c556:  0.998 xPB_C,N,O,R,A,L,1,3 - xPB_C,N,O,R,A,L,1,4  = 0
 c557:  0.998 xPB_C,N,O,R,A,L,1,4 - xPB_C,N,O,R,A,L,1,5  = 0
 c558:  0.998 xPB_C,N,O,R,A,L,2,1 - xPB_C,N,O,R,A,L,2,2  = 0
 c559:  0.998 xPB_C,N,O,R,A,L,2,2 - xPB_C,N,O,R,A,L,2,3  = 0
 c560:  0.998 xPB_C,N,O,R,A,L,2,3 - xPB_C,N,O,R,A,L,2,4  = 0
 c561:  0.998 xPB_C,N,O,R,A,L,2,4 - xPB_C,N,O,R,A,L,2,5  = 0
 c562:  0.998 xPB_C,N,O,R,A,L,3,1 - xPB_C,N,O,R,A,L,3,2  = 0
 c563:  0.998 xPB_C,N,O,R,A,L,3,2 - xPB_C,N,O,R,A,L,3,3  = 0
 c564:  0.998 xPB_C,N,O,R,A,L,3,3 - xPB_C,N,O,R,A,L,3,4  = 0
 c565:  0.998 xPB_C,N,O,R,A,L,3,4 - xPB_C,N,O,R,A,L,3,5  = 0
 c566:  0.998 xPB_C,N,O,R,A,L,4,1 - xPB_C,N,O,R,A,L,4,2  = 0
 c567:  0.998 xPB_C,N,O,R,A,L,4,2 - xPB_C,N,O,R,A,L,4,3  = 0
 c568:  0.998 xPB_C,N,O,R,A,L,4,3 - xPB_C,N,O,R,A,L,4,4  = 0
 c569:  0.998 xPB_C,N,O,R,A,L,4,4 - xPB_C,N,O,R,A,L,4,5  = 0
 c570:  x_B,R,N,L,A,L,2 - xGS_B,R,N,L,A,L,0,1 - xGS_B,R,N,L,A,L,1,1
        - xGS_B,R,N,L,A,L,2,1 - xGS_B,R,N,L,A,L,3,1 - xGS_B,R,N,L,A,L,4,1  = 0
 c571:  x_B,R,N,C,A,L,2 - xGS_B,R,N,C,A,L,0,1 - xGS_B,R,N,C,A,L,1,1
        - xGS_B,R,N,C,A,L,2,1 - xGS_B,R,N,C,A,L,3,1 - xGS_B,R,N,C,A,L,4,1  = 0
 c572:  x_B,R,N,R,A,L,2 - xGS_B,R,N,R,A,L,0,1 - xGS_B,R,N,R,A,L,1,1
        - xGS_B,R,N,R,A,L,2,1 - xGS_B,R,N,R,A,L,3,1 - xGS_B,R,N,R,A,L,4,1  = 0
 c573:  x_B,R,P,L,A,L,2 - xGS_B,R,P,L,A,L,0,1 - xGS_B,R,P,L,A,L,1,1
        - xGS_B,R,P,L,A,L,2,1 - xGS_B,R,P,L,A,L,3,1 - xGS_B,R,P,L,A,L,4,1  = 0
 c574:  x_B,R,P,C,A,L,2 - xGS_B,R,P,C,A,L,0,1 - xGS_B,R,P,C,A,L,1,1
        - xGS_B,R,P,C,A,L,2,1 - xGS_B,R,P,C,A,L,3,1 - xGS_B,R,P,C,A,L,4,1  = 0
 c575:  x_B,R,P,R,A,L,2 - xGS_B,R,P,R,A,L,0,1 - xGS_B,R,P,R,A,L,1,1
        - xGS_B,R,P,R,A,L,2,1 - xGS_B,R,P,R,A,L,3,1 - xGS_B,R,P,R,A,L,4,1  = 0
 c576:  x_B,N,N,L,A,L,2 - xGS_B,N,N,L,A,L,0,1 - xGS_B,N,N,L,A,L,1,1
        - xGS_B,N,N,L,A,L,2,1 - xGS_B,N,N,L,A,L,3,1 - xGS_B,N,N,L,A,L,4,1  = 0
 c577:  x_B,N,N,C,A,L,2 - xGS_B,N,N,C,A,L,0,1 - xGS_B,N,N,C,A,L,1,1
        - xGS_B,N,N,C,A,L,2,1 - xGS_B,N,N,C,A,L,3,1 - xGS_B,N,N,C,A,L,4,1  = 0
 c578:  x_B,N,P,R,A,L,2 - xGS_B,N,P,R,A,L,0,1 - xGS_B,N,P,R,A,L,1,1
        - xGS_B,N,P,R,A,L,2,1 - xGS_B,N,P,R,A,L,3,1 - xGS_B,N,P,R,A,L,4,1  = 0
 c579:  x_C,N,N,L,A,L,2 - xGS_C,N,N,L,A,L,0,1 - xGS_C,N,N,L,A,L,1,1
        - xGS_C,N,N,L,A,L,2,1 - xGS_C,N,N,L,A,L,3,1 - xGS_C,N,N,L,A,L,4,1  = 0
 c580:  x_C,N,N,H,A,L,2 - xGS_C,N,N,H,A,L,0,1 - xGS_C,N,N,H,A,L,1,1
        - xGS_C,N,N,H,A,L,2,1 - xGS_C,N,N,H,A,L,3,1 - xGS_C,N,N,H,A,L,4,1  = 0
 c581:  x_C,N,N,C,A,L,2 - xGS_C,N,N,C,A,L,0,1 - xGS_C,N,N,C,A,L,1,1
        - xGS_C,N,N,C,A,L,2,1 - xGS_C,N,N,C,A,L,3,1 - xGS_C,N,N,C,A,L,4,1  = 0
 c582:  x_C,N,N,R,A,L,2 - xGS_C,N,N,R,A,L,0,1 - xGS_C,N,N,R,A,L,1,1
        - xGS_C,N,N,R,A,L,2,1 - xGS_C,N,N,R,A,L,3,1 - xGS_C,N,N,R,A,L,4,1  = 0
 c583:  x_C,N,O,L,A,L,2 - xGS_C,N,O,L,A,L,0,1 - xGS_C,N,O,L,A,L,1,1
        - xGS_C,N,O,L,A,L,2,1 - xGS_C,N,O,L,A,L,3,1 - xGS_C,N,O,L,A,L,4,1  = 0
 c584:  x_C,N,O,H,A,L,2 - xGS_C,N,O,H,A,L,0,1 - xGS_C,N,O,H,A,L,1,1
        - xGS_C,N,O,H,A,L,2,1 - xGS_C,N,O,H,A,L,3,1 - xGS_C,N,O,H,A,L,4,1  = 0
 c585:  x_C,N,O,C,A,L,2 - xGS_C,N,O,C,A,L,0,1 - xGS_C,N,O,C,A,L,1,1
        - xGS_C,N,O,C,A,L,2,1 - xGS_C,N,O,C,A,L,3,1 - xGS_C,N,O,C,A,L,4,1  = 0
 c586:  x_C,N,O,R,A,L,2 - xGS_C,N,O,R,A,L,0,1 - xGS_C,N,O,R,A,L,1,1
        - xGS_C,N,O,R,A,L,2,1 - xGS_C,N,O,R,A,L,3,1 - xGS_C,N,O,R,A,L,4,1  = 0
 c587:  0.998 xGS_B,R,N,L,A,L,0,1 - xGS_B,R,N,L,A,L,0,2  = 0
 c588:  0.998 xGS_B,R,N,L,A,L,0,2 - xGS_B,R,N,L,A,L,0,3  = 0
 c589:  0.998 xGS_B,R,N,L,A,L,0,3 - xGS_B,R,N,L,A,L,0,4  = 0
 c590:  0.998 xGS_B,R,N,L,A,L,0,4 - xGS_B,R,N,L,A,L,0,5  = 0
 c591:  0.998 xGS_B,R,N,L,A,L,1,1 - xGS_B,R,N,L,A,L,1,2  = 0
 c592:  0.998 xGS_B,R,N,L,A,L,1,2 - xGS_B,R,N,L,A,L,1,3  = 0
 c593:  0.998 xGS_B,R,N,L,A,L,1,3 - xGS_B,R,N,L,A,L,1,4  = 0
 c594:  0.998 xGS_B,R,N,L,A,L,1,4 - xGS_B,R,N,L,A,L,1,5  = 0
 c595:  0.998 xGS_B,R,N,L,A,L,2,1 - xGS_B,R,N,L,A,L,2,2  = 0
 c596:  0.998 xGS_B,R,N,L,A,L,2,2 - xGS_B,R,N,L,A,L,2,3  = 0
 c597:  0.998 xGS_B,R,N,L,A,L,2,3 - xGS_B,R,N,L,A,L,2,4  = 0
 c598:  0.998 xGS_B,R,N,L,A,L,2,4 - xGS_B,R,N,L,A,L,2,5  = 0
 c599:  0.998 xGS_B,R,N,L,A,L,3,1 - xGS_B,R,N,L,A,L,3,2  = 0
 c600:  0.998 xGS_B,R,N,L,A,L,3,2 - xGS_B,R,N,L,A,L,3,3  = 0
 c601:  0.998 xGS_B,R,N,L,A,L,3,3 - xGS_B,R,N,L,A,L,3,4  = 0
 c602:  0.998 xGS_B,R,N,L,A,L,3,4 - xGS_B,R,N,L,A,L,3,5  = 0
 c603:  0.998 xGS_B,R,N,L,A,L,4,1 - xGS_B,R,N,L,A,L,4,2  = 0
 c604:  0.998 xGS_B,R,N,L,A,L,4,2 - xGS_B,R,N,L,A,L,4,3  = 0
 c605:  0.998 xGS_B,R,N,L,A,L,4,3 - xGS_B,R,N,L,A,L,4,4  = 0
 c606:  0.998 xGS_B,R,N,L,A,L,4,4 - xGS_B,R,N,L,A,L,4,5  = 0
 c607:  0.998 xGS_B,R,N,C,A,L,0,1 - xGS_B,R,N,C,A,L,0,2  = 0
 c608:  0.998 xGS_B,R,N,C,A,L,0,2 - xGS_B,R,N,C,A,L,0,3  = 0
 c609:  0.998 xGS_B,R,N,C,A,L,0,3 - xGS_B,R,N,C,A,L,0,4  = 0
 c610:  0.998 xGS_B,R,N,C,A,L,0,4 - xGS_B,R,N,C,A,L,0,5  = 0
 c611:  0.998 xGS_B,R,N,C,A,L,1,1 - xGS_B,R,N,C,A,L,1,2  = 0
 c612:  0.998 xGS_B,R,N,C,A,L,1,2 - xGS_B,R,N,C,A,L,1,3  = 0
 c613:  0.998 xGS_B,R,N,C,A,L,1,3 - xGS_B,R,N,C,A,L,1,4  = 0
 c614:  0.998 xGS_B,R,N,C,A,L,1,4 - xGS_B,R,N,C,A,L,1,5  = 0
 c615:  0.998 xGS_B,R,N,C,A,L,2,1 - xGS_B,R,N,C,A,L,2,2  = 0
 c616:  0.998 xGS_B,R,N,C,A,L,2,2 - xGS_B,R,N,C,A,L,2,3  = 0
 c617:  0.998 xGS_B,R,N,C,A,L,2,3 - xGS_B,R,N,C,A,L,2,4  = 0
 c618:  0.998 xGS_B,R,N,C,A,L,2,4 - xGS_B,R,N,C,A,L,2,5  = 0
 c619:  0.998 xGS_B,R,N,C,A,L,3,1 - xGS_B,R,N,C,A,L,3,2  = 0
 c620:  0.998 xGS_B,R,N,C,A,L,3,2 - xGS_B,R,N,C,A,L,3,3  = 0
 c621:  0.998 xGS_B,R,N,C,A,L,3,3 - xGS_B,R,N,C,A,L,3,4  = 0
 c622:  0.998 xGS_B,R,N,C,A,L,3,4 - xGS_B,R,N,C,A,L,3,5  = 0
 c623:  0.998 xGS_B,R,N,C,A,L,4,1 - xGS_B,R,N,C,A,L,4,2  = 0
 c624:  0.998 xGS_B,R,N,C,A,L,4,2 - xGS_B,R,N,C,A,L,4,3  = 0
 c625:  0.998 xGS_B,R,N,C,A,L,4,3 - xGS_B,R,N,C,A,L,4,4  = 0
 c626:  0.998 xGS_B,R,N,C,A,L,4,4 - xGS_B,R,N,C,A,L,4,5  = 0
 c627:  0.998 xGS_B,R,N,R,A,L,0,1 - xGS_B,R,N,R,A,L,0,2  = 0
 c628:  0.998 xGS_B,R,N,R,A,L,0,2 - xGS_B,R,N,R,A,L,0,3  = 0
 c629:  0.998 xGS_B,R,N,R,A,L,0,3 - xGS_B,R,N,R,A,L,0,4  = 0
 c630:  0.998 xGS_B,R,N,R,A,L,0,4 - xGS_B,R,N,R,A,L,0,5  = 0
 c631:  0.998 xGS_B,R,N,R,A,L,1,1 - xGS_B,R,N,R,A,L,1,2  = 0
 c632:  0.998 xGS_B,R,N,R,A,L,1,2 - xGS_B,R,N,R,A,L,1,3  = 0
 c633:  0.998 xGS_B,R,N,R,A,L,1,3 - xGS_B,R,N,R,A,L,1,4  = 0
 c634:  0.998 xGS_B,R,N,R,A,L,1,4 - xGS_B,R,N,R,A,L,1,5  = 0
 c635:  0.998 xGS_B,R,N,R,A,L,2,1 - xGS_B,R,N,R,A,L,2,2  = 0
 c636:  0.998 xGS_B,R,N,R,A,L,2,2 - xGS_B,R,N,R,A,L,2,3  = 0
 c637:  0.998 xGS_B,R,N,R,A,L,2,3 - xGS_B,R,N,R,A,L,2,4  = 0
 c638:  0.998 xGS_B,R,N,R,A,L,2,4 - xGS_B,R,N,R,A,L,2,5  = 0
 c639:  0.998 xGS_B,R,N,R,A,L,3,1 - xGS_B,R,N,R,A,L,3,2  = 0
 c640:  0.998 xGS_B,R,N,R,A,L,3,2 - xGS_B,R,N,R,A,L,3,3  = 0
 c641:  0.998 xGS_B,R,N,R,A,L,3,3 - xGS_B,R,N,R,A,L,3,4  = 0
 c642:  0.998 xGS_B,R,N,R,A,L,3,4 - xGS_B,R,N,R,A,L,3,5  = 0
 c643:  0.998 xGS_B,R,N,R,A,L,4,1 - xGS_B,R,N,R,A,L,4,2  = 0
 c644:  0.998 xGS_B,R,N,R,A,L,4,2 - xGS_B,R,N,R,A,L,4,3  = 0
 c645:  0.998 xGS_B,R,N,R,A,L,4,3 - xGS_B,R,N,R,A,L,4,4  = 0
 c646:  0.998 xGS_B,R,N,R,A,L,4,4 - xGS_B,R,N,R,A,L,4,5  = 0
 c647:  0.998 xGS_B,R,P,L,A,L,0,1 - xGS_B,R,P,L,A,L,0,2  = 0
 c648:  0.998 xGS_B,R,P,L,A,L,0,2 - xGS_B,R,P,L,A,L,0,3  = 0
 c649:  0.998 xGS_B,R,P,L,A,L,0,3 - xGS_B,R,P,L,A,L,0,4  = 0
 c650:  0.998 xGS_B,R,P,L,A,L,0,4 - xGS_B,R,P,L,A,L,0,5  = 0
 c651:  0.998 xGS_B,R,P,L,A,L,1,1 - xGS_B,R,P,L,A,L,1,2  = 0
 c652:  0.998 xGS_B,R,P,L,A,L,1,2 - xGS_B,R,P,L,A,L,1,3  = 0
 c653:  0.998 xGS_B,R,P,L,A,L,1,3 - xGS_B,R,P,L,A,L,1,4  = 0
 c654:  0.998 xGS_B,R,P,L,A,L,1,4 - xGS_B,R,P,L,A,L,1,5  = 0
 c655:  0.998 xGS_B,R,P,L,A,L,2,1 - xGS_B,R,P,L,A,L,2,2  = 0
 c656:  0.998 xGS_B,R,P,L,A,L,2,2 - xGS_B,R,P,L,A,L,2,3  = 0
 c657:  0.998 xGS_B,R,P,L,A,L,2,3 - xGS_B,R,P,L,A,L,2,4  = 0
 c658:  0.998 xGS_B,R,P,L,A,L,2,4 - xGS_B,R,P,L,A,L,2,5  = 0
 c659:  0.998 xGS_B,R,P,L,A,L,3,1 - xGS_B,R,P,L,A,L,3,2  = 0
 c660:  0.998 xGS_B,R,P,L,A,L,3,2 - xGS_B,R,P,L,A,L,3,3  = 0
 c661:  0.998 xGS_B,R,P,L,A,L,3,3 - xGS_B,R,P,L,A,L,3,4  = 0
 c662:  0.998 xGS_B,R,P,L,A,L,3,4 - xGS_B,R,P,L,A,L,3,5  = 0
 c663:  0.998 xGS_B,R,P,L,A,L,4,1 - xGS_B,R,P,L,A,L,4,2  = 0
 c664:  0.998 xGS_B,R,P,L,A,L,4,2 - xGS_B,R,P,L,A,L,4,3  = 0
 c665:  0.998 xGS_B,R,P,L,A,L,4,3 - xGS_B,R,P,L,A,L,4,4  = 0
 c666:  0.998 xGS_B,R,P,L,A,L,4,4 - xGS_B,R,P,L,A,L,4,5  = 0
 c667:  0.998 xGS_B,R,P,C,A,L,0,1 - xGS_B,R,P,C,A,L,0,2  = 0
 c668:  0.998 xGS_B,R,P,C,A,L,0,2 - xGS_B,R,P,C,A,L,0,3  = 0
 c669:  0.998 xGS_B,R,P,C,A,L,0,3 - xGS_B,R,P,C,A,L,0,4  = 0
 c670:  0.998 xGS_B,R,P,C,A,L,0,4 - xGS_B,R,P,C,A,L,0,5  = 0
 c671:  0.998 xGS_B,R,P,C,A,L,1,1 - xGS_B,R,P,C,A,L,1,2  = 0
 c672:  0.998 xGS_B,R,P,C,A,L,1,2 - xGS_B,R,P,C,A,L,1,3  = 0
 c673:  0.998 xGS_B,R,P,C,A,L,1,3 - xGS_B,R,P,C,A,L,1,4  = 0
 c674:  0.998 xGS_B,R,P,C,A,L,1,4 - xGS_B,R,P,C,A,L,1,5  = 0
 c675:  0.998 xGS_B,R,P,C,A,L,2,1 - xGS_B,R,P,C,A,L,2,2  = 0
 c676:  0.998 xGS_B,R,P,C,A,L,2,2 - xGS_B,R,P,C,A,L,2,3  = 0
 c677:  0.998 xGS_B,R,P,C,A,L,2,3 - xGS_B,R,P,C,A,L,2,4  = 0
 c678:  0.998 xGS_B,R,P,C,A,L,2,4 - xGS_B,R,P,C,A,L,2,5  = 0
 c679:  0.998 xGS_B,R,P,C,A,L,3,1 - xGS_B,R,P,C,A,L,3,2  = 0
 c680:  0.998 xGS_B,R,P,C,A,L,3,2 - xGS_B,R,P,C,A,L,3,3  = 0
 c681:  0.998 xGS_B,R,P,C,A,L,3,3 - xGS_B,R,P,C,A,L,3,4  = 0
 c682:  0.998 xGS_B,R,P,C,A,L,3,4 - xGS_B,R,P,C,A,L,3,5  = 0
 c683:  0.998 xGS_B,R,P,C,A,L,4,1 - xGS_B,R,P,C,A,L,4,2  = 0
 c684:  0.998 xGS_B,R,P,C,A,L,4,2 - xGS_B,R,P,C,A,L,4,3  = 0
 c685:  0.998 xGS_B,R,P,C,A,L,4,3 - xGS_B,R,P,C,A,L,4,4  = 0
 c686:  0.998 xGS_B,R,P,C,A,L,4,4 - xGS_B,R,P,C,A,L,4,5  = 0
 c687:  0.998 xGS_B,R,P,R,A,L,0,1 - xGS_B,R,P,R,A,L,0,2  = 0
 c688:  0.998 xGS_B,R,P,R,A,L,0,2 - xGS_B,R,P,R,A,L,0,3  = 0
 c689:  0.998 xGS_B,R,P,R,A,L,0,3 - xGS_B,R,P,R,A,L,0,4  = 0
 c690:  0.998 xGS_B,R,P,R,A,L,0,4 - xGS_B,R,P,R,A,L,0,5  = 0
 c691:  0.998 xGS_B,R,P,R,A,L,1,1 - xGS_B,R,P,R,A,L,1,2  = 0
 c692:  0.998 xGS_B,R,P,R,A,L,1,2 - xGS_B,R,P,R,A,L,1,3  = 0
 c693:  0.998 xGS_B,R,P,R,A,L,1,3 - xGS_B,R,P,R,A,L,1,4  = 0
 c694:  0.998 xGS_B,R,P,R,A,L,1,4 - xGS_B,R,P,R,A,L,1,5  = 0
 c695:  0.998 xGS_B,R,P,R,A,L,2,1 - xGS_B,R,P,R,A,L,2,2  = 0
 c696:  0.998 xGS_B,R,P,R,A,L,2,2 - xGS_B,R,P,R,A,L,2,3  = 0
 c697:  0.998 xGS_B,R,P,R,A,L,2,3 - xGS_B,R,P,R,A,L,2,4  = 0
 c698:  0.998 xGS_B,R,P,R,A,L,2,4 - xGS_B,R,P,R,A,L,2,5  = 0
 c699:  0.998 xGS_B,R,P,R,A,L,3,1 - xGS_B,R,P,R,A,L,3,2  = 0
 c700:  0.998 xGS_B,R,P,R,A,L,3,2 - xGS_B,R,P,R,A,L,3,3  = 0
 c701:  0.998 xGS_B,R,P,R,A,L,3,3 - xGS_B,R,P,R,A,L,3,4  = 0
 c702:  0.998 xGS_B,R,P,R,A,L,3,4 - xGS_B,R,P,R,A,L,3,5  = 0
 c703:  0.998 xGS_B,R,P,R,A,L,4,1 - xGS_B,R,P,R,A,L,4,2  = 0
 c704:  0.998 xGS_B,R,P,R,A,L,4,2 - xGS_B,R,P,R,A,L,4,3  = 0
 c705:  0.998 xGS_B,R,P,R,A,L,4,3 - xGS_B,R,P,R,A,L,4,4  = 0
 c706:  0.998 xGS_B,R,P,R,A,L,4,4 - xGS_B,R,P,R,A,L,4,5  = 0
 c707:  0.998 xGS_B,N,N,L,A,L,0,1 - xGS_B,N,N,L,A,L,0,2  = 0
 c708:  0.998 xGS_B,N,N,L,A,L,0,2 - xGS_B,N,N,L,A,L,0,3  = 0
 c709:  0.998 xGS_B,N,N,L,A,L,0,3 - xGS_B,N,N,L,A,L,0,4  = 0
 c710:  0.998 xGS_B,N,N,L,A,L,0,4 - xGS_B,N,N,L,A,L,0,5  = 0
 c711:  0.998 xGS_B,N,N,L,A,L,1,1 - xGS_B,N,N,L,A,L,1,2  = 0
 c712:  0.998 xGS_B,N,N,L,A,L,1,2 - xGS_B,N,N,L,A,L,1,3  = 0
 c713:  0.998 xGS_B,N,N,L,A,L,1,3 - xGS_B,N,N,L,A,L,1,4  = 0
 c714:  0.998 xGS_B,N,N,L,A,L,1,4 - xGS_B,N,N,L,A,L,1,5  = 0
 c715:  0.998 xGS_B,N,N,L,A,L,2,1 - xGS_B,N,N,L,A,L,2,2  = 0
 c716:  0.998 xGS_B,N,N,L,A,L,2,2 - xGS_B,N,N,L,A,L,2,3  = 0
 c717:  0.998 xGS_B,N,N,L,A,L,2,3 - xGS_B,N,N,L,A,L,2,4  = 0
 c718:  0.998 xGS_B,N,N,L,A,L,2,4 - xGS_B,N,N,L,A,L,2,5  = 0
 c719:  0.998 xGS_B,N,N,L,A,L,3,1 - xGS_B,N,N,L,A,L,3,2  = 0
 c720:  0.998 xGS_B,N,N,L,A,L,3,2 - xGS_B,N,N,L,A,L,3,3  = 0
 c721:  0.998 xGS_B,N,N,L,A,L,3,3 - xGS_B,N,N,L,A,L,3,4  = 0
 c722:  0.998 xGS_B,N,N,L,A,L,3,4 - xGS_B,N,N,L,A,L,3,5  = 0
 c723:  0.998 xGS_B,N,N,L,A,L,4,1 - xGS_B,N,N,L,A,L,4,2  = 0
 c724:  0.998 xGS_B,N,N,L,A,L,4,2 - xGS_B,N,N,L,A,L,4,3  = 0
 c725:  0.998 xGS_B,N,N,L,A,L,4,3 - xGS_B,N,N,L,A,L,4,4  = 0
 c726:  0.998 xGS_B,N,N,L,A,L,4,4 - xGS_B,N,N,L,A,L,4,5  = 0
 c727:  0.998 xGS_B,N,N,C,A,L,0,1 - xGS_B,N,N,C,A,L,0,2  = 0
 c728:  0.998 xGS_B,N,N,C,A,L,0,2 - xGS_B,N,N,C,A,L,0,3  = 0
 c729:  0.998 xGS_B,N,N,C,A,L,0,3 - xGS_B,N,N,C,A,L,0,4  = 0
 c730:  0.998 xGS_B,N,N,C,A,L,0,4 - xGS_B,N,N,C,A,L,0,5  = 0
 c731:  0.998 xGS_B,N,N,C,A,L,1,1 - xGS_B,N,N,C,A,L,1,2  = 0
 c732:  0.998 xGS_B,N,N,C,A,L,1,2 - xGS_B,N,N,C,A,L,1,3  = 0
 c733:  0.998 xGS_B,N,N,C,A,L,1,3 - xGS_B,N,N,C,A,L,1,4  = 0
 c734:  0.998 xGS_B,N,N,C,A,L,1,4 - xGS_B,N,N,C,A,L,1,5  = 0
 c735:  0.998 xGS_B,N,N,C,A,L,2,1 - xGS_B,N,N,C,A,L,2,2  = 0
 c736:  0.998 xGS_B,N,N,C,A,L,2,2 - xGS_B,N,N,C,A,L,2,3  = 0
 c737:  0.998 xGS_B,N,N,C,A,L,2,3 - xGS_B,N,N,C,A,L,2,4  = 0
 c738:  0.998 xGS_B,N,N,C,A,L,2,4 - xGS_B,N,N,C,A,L,2,5  = 0
 c739:  0.998 xGS_B,N,N,C,A,L,3,1 - xGS_B,N,N,C,A,L,3,2  = 0
 c740:  0.998 xGS_B,N,N,C,A,L,3,2 - xGS_B,N,N,C,A,L,3,3  = 0
 c741:  0.998 xGS_B,N,N,C,A,L,3,3 - xGS_B,N,N,C,A,L,3,4  = 0
 c742:  0.998 xGS_B,N,N,C,A,L,3,4 - xGS_B,N,N,C,A,L,3,5  = 0
 c743:  0.998 xGS_B,N,N,C,A,L,4,1 - xGS_B,N,N,C,A,L,4,2  = 0
 c744:  0.998 xGS_B,N,N,C,A,L,4,2 - xGS_B,N,N,C,A,L,4,3  = 0
 c745:  0.998 xGS_B,N,N,C,A,L,4,3 - xGS_B,N,N,C,A,L,4,4  = 0
 c746:  0.998 xGS_B,N,N,C,A,L,4,4 - xGS_B,N,N,C,A,L,4,5  = 0
 c747:  0.998 xGS_B,N,P,R,A,L,0,1 - xGS_B,N,P,R,A,L,0,2  = 0
 c748:  0.998 xGS_B,N,P,R,A,L,0,2 - xGS_B,N,P,R,A,L,0,3  = 0
 c749:  0.998 xGS_B,N,P,R,A,L,0,3 - xGS_B,N,P,R,A,L,0,4  = 0
 c750:  0.998 xGS_B,N,P,R,A,L,0,4 - xGS_B,N,P,R,A,L,0,5  = 0
 c751:  0.998 xGS_B,N,P,R,A,L,1,1 - xGS_B,N,P,R,A,L,1,2  = 0
 c752:  0.998 xGS_B,N,P,R,A,L,1,2 - xGS_B,N,P,R,A,L,1,3  = 0
 c753:  0.998 xGS_B,N,P,R,A,L,1,3 - xGS_B,N,P,R,A,L,1,4  = 0
 c754:  0.998 xGS_B,N,P,R,A,L,1,4 - xGS_B,N,P,R,A,L,1,5  = 0
 c755:  0.998 xGS_B,N,P,R,A,L,2,1 - xGS_B,N,P,R,A,L,2,2  = 0
 c756:  0.998 xGS_B,N,P,R,A,L,2,2 - xGS_B,N,P,R,A,L,2,3  = 0
 c757:  0.998 xGS_B,N,P,R,A,L,2,3 - xGS_B,N,P,R,A,L,2,4  = 0
 c758:  0.998 xGS_B,N,P,R,A,L,2,4 - xGS_B,N,P,R,A,L,2,5  = 0
 c759:  0.998 xGS_B,N,P,R,A,L,3,1 - xGS_B,N,P,R,A,L,3,2  = 0
 c760:  0.998 xGS_B,N,P,R,A,L,3,2 - xGS_B,N,P,R,A,L,3,3  = 0
 c761:  0.998 xGS_B,N,P,R,A,L,3,3 - xGS_B,N,P,R,A,L,3,4  = 0
 c762:  0.998 xGS_B,N,P,R,A,L,3,4 - xGS_B,N,P,R,A,L,3,5  = 0
 c763:  0.998 xGS_B,N,P,R,A,L,4,1 - xGS_B,N,P,R,A,L,4,2  = 0
 c764:  0.998 xGS_B,N,P,R,A,L,4,2 - xGS_B,N,P,R,A,L,4,3  = 0
 c765:  0.998 xGS_B,N,P,R,A,L,4,3 - xGS_B,N,P,R,A,L,4,4  = 0
 c766:  0.998 xGS_B,N,P,R,A,L,4,4 - xGS_B,N,P,R,A,L,4,5  = 0
 c767:  0.998 xGS_C,N,N,L,A,L,0,1 - xGS_C,N,N,L,A,L,0,2  = 0
 c768:  0.998 xGS_C,N,N,L,A,L,0,2 - xGS_C,N,N,L,A,L,0,3  = 0
 c769:  0.998 xGS_C,N,N,L,A,L,0,3 - xGS_C,N,N,L,A,L,0,4  = 0
 c770:  0.998 xGS_C,N,N,L,A,L,0,4 - xGS_C,N,N,L,A,L,0,5  = 0
 c771:  0.998 xGS_C,N,N,L,A,L,1,1 - xGS_C,N,N,L,A,L,1,2  = 0
 c772:  0.998 xGS_C,N,N,L,A,L,1,2 - xGS_C,N,N,L,A,L,1,3  = 0
 c773:  0.998 xGS_C,N,N,L,A,L,1,3 - xGS_C,N,N,L,A,L,1,4  = 0
 c774:  0.998 xGS_C,N,N,L,A,L,1,4 - xGS_C,N,N,L,A,L,1,5  = 0
 c775:  0.998 xGS_C,N,N,L,A,L,2,1 - xGS_C,N,N,L,A,L,2,2  = 0
 c776:  0.998 xGS_C,N,N,L,A,L,2,2 - xGS_C,N,N,L,A,L,2,3  = 0
 c777:  0.998 xGS_C,N,N,L,A,L,2,3 - xGS_C,N,N,L,A,L,2,4  = 0
 c778:  0.998 xGS_C,N,N,L,A,L,2,4 - xGS_C,N,N,L,A,L,2,5  = 0
 c779:  0.998 xGS_C,N,N,L,A,L,3,1 - xGS_C,N,N,L,A,L,3,2  = 0
 c780:  0.998 xGS_C,N,N,L,A,L,3,2 - xGS_C,N,N,L,A,L,3,3  = 0
 c781:  0.998 xGS_C,N,N,L,A,L,3,3 - xGS_C,N,N,L,A,L,3,4  = 0
 c782:  0.998 xGS_C,N,N,L,A,L,3,4 - xGS_C,N,N,L,A,L,3,5  = 0
 c783:  0.998 xGS_C,N,N,L,A,L,4,1 - xGS_C,N,N,L,A,L,4,2  = 0
 c784:  0.998 xGS_C,N,N,L,A,L,4,2 - xGS_C,N,N,L,A,L,4,3  = 0
 c785:  0.998 xGS_C,N,N,L,A,L,4,3 - xGS_C,N,N,L,A,L,4,4  = 0
 c786:  0.998 xGS_C,N,N,L,A,L,4,4 - xGS_C,N,N,L,A,L,4,5  = 0
 c787:  0.998 xGS_C,N,N,H,A,L,0,1 - xGS_C,N,N,H,A,L,0,2  = 0
 c788:  0.998 xGS_C,N,N,H,A,L,0,2 - xGS_C,N,N,H,A,L,0,3  = 0
 c789:  0.998 xGS_C,N,N,H,A,L,0,3 - xGS_C,N,N,H,A,L,0,4  = 0
 c790:  0.998 xGS_C,N,N,H,A,L,0,4 - xGS_C,N,N,H,A,L,0,5  = 0
 c791:  0.998 xGS_C,N,N,H,A,L,1,1 - xGS_C,N,N,H,A,L,1,2  = 0
 c792:  0.998 xGS_C,N,N,H,A,L,1,2 - xGS_C,N,N,H,A,L,1,3  = 0
 c793:  0.998 xGS_C,N,N,H,A,L,1,3 - xGS_C,N,N,H,A,L,1,4  = 0
 c794:  0.998 xGS_C,N,N,H,A,L,1,4 - xGS_C,N,N,H,A,L,1,5  = 0
 c795:  0.998 xGS_C,N,N,H,A,L,2,1 - xGS_C,N,N,H,A,L,2,2  = 0
 c796:  0.998 xGS_C,N,N,H,A,L,2,2 - xGS_C,N,N,H,A,L,2,3  = 0
 c797:  0.998 xGS_C,N,N,H,A,L,2,3 - xGS_C,N,N,H,A,L,2,4  = 0
 c798:  0.998 xGS_C,N,N,H,A,L,2,4 - xGS_C,N,N,H,A,L,2,5  = 0
 c799:  0.998 xGS_C,N,N,H,A,L,3,1 - xGS_C,N,N,H,A,L,3,2  = 0
 c800:  0.998 xGS_C,N,N,H,A,L,3,2 - xGS_C,N,N,H,A,L,3,3  = 0
 c801:  0.998 xGS_C,N,N,H,A,L,3,3 - xGS_C,N,N,H,A,L,3,4  = 0
 c802:  0.998 xGS_C,N,N,H,A,L,3,4 - xGS_C,N,N,H,A,L,3,5  = 0
 c803:  0.998 xGS_C,N,N,H,A,L,4,1 - xGS_C,N,N,H,A,L,4,2  = 0
 c804:  0.998 xGS_C,N,N,H,A,L,4,2 - xGS_C,N,N,H,A,L,4,3  = 0
 c805:  0.998 xGS_C,N,N,H,A,L,4,3 - xGS_C,N,N,H,A,L,4,4  = 0
 c806:  0.998 xGS_C,N,N,H,A,L,4,4 - xGS_C,N,N,H,A,L,4,5  = 0
 c807:  0.998 xGS_C,N,N,C,A,L,0,1 - xGS_C,N,N,C,A,L,0,2  = 0
 c808:  0.998 xGS_C,N,N,C,A,L,0,2 - xGS_C,N,N,C,A,L,0,3  = 0
 c809:  0.998 xGS_C,N,N,C,A,L,0,3 - xGS_C,N,N,C,A,L,0,4  = 0
 c810:  0.998 xGS_C,N,N,C,A,L,0,4 - xGS_C,N,N,C,A,L,0,5  = 0
 c811:  0.998 xGS_C,N,N,C,A,L,1,1 - xGS_C,N,N,C,A,L,1,2  = 0
 c812:  0.998 xGS_C,N,N,C,A,L,1,2 - xGS_C,N,N,C,A,L,1,3  = 0
 c813:  0.998 xGS_C,N,N,C,A,L,1,3 - xGS_C,N,N,C,A,L,1,4  = 0
 c814:  0.998 xGS_C,N,N,C,A,L,1,4 - xGS_C,N,N,C,A,L,1,5  = 0
 c815:  0.998 xGS_C,N,N,C,A,L,2,1 - xGS_C,N,N,C,A,L,2,2  = 0
 c816:  0.998 xGS_C,N,N,C,A,L,2,2 - xGS_C,N,N,C,A,L,2,3  = 0
 c817:  0.998 xGS_C,N,N,C,A,L,2,3 - xGS_C,N,N,C,A,L,2,4  = 0
 c818:  0.998 xGS_C,N,N,C,A,L,2,4 - xGS_C,N,N,C,A,L,2,5  = 0
 c819:  0.998 xGS_C,N,N,C,A,L,3,1 - xGS_C,N,N,C,A,L,3,2  = 0
 c820:  0.998 xGS_C,N,N,C,A,L,3,2 - xGS_C,N,N,C,A,L,3,3  = 0
 c821:  0.998 xGS_C,N,N,C,A,L,3,3 - xGS_C,N,N,C,A,L,3,4  = 0
 c822:  0.998 xGS_C,N,N,C,A,L,3,4 - xGS_C,N,N,C,A,L,3,5  = 0
 c823:  0.998 xGS_C,N,N,C,A,L,4,1 - xGS_C,N,N,C,A,L,4,2  = 0
 c824:  0.998 xGS_C,N,N,C,A,L,4,2 - xGS_C,N,N,C,A,L,4,3  = 0
 c825:  0.998 xGS_C,N,N,C,A,L,4,3 - xGS_C,N,N,C,A,L,4,4  = 0
 c826:  0.998 xGS_C,N,N,C,A,L,4,4 - xGS_C,N,N,C,A,L,4,5  = 0
 c827:  0.998 xGS_C,N,N,R,A,L,0,1 - xGS_C,N,N,R,A,L,0,2  = 0
 c828:  0.998 xGS_C,N,N,R,A,L,0,2 - xGS_C,N,N,R,A,L,0,3  = 0
 c829:  0.998 xGS_C,N,N,R,A,L,0,3 - xGS_C,N,N,R,A,L,0,4  = 0
 c830:  0.998 xGS_C,N,N,R,A,L,0,4 - xGS_C,N,N,R,A,L,0,5  = 0
 c831:  0.998 xGS_C,N,N,R,A,L,1,1 - xGS_C,N,N,R,A,L,1,2  = 0
 c832:  0.998 xGS_C,N,N,R,A,L,1,2 - xGS_C,N,N,R,A,L,1,3  = 0
 c833:  0.998 xGS_C,N,N,R,A,L,1,3 - xGS_C,N,N,R,A,L,1,4  = 0
 c834:  0.998 xGS_C,N,N,R,A,L,1,4 - xGS_C,N,N,R,A,L,1,5  = 0
 c835:  0.998 xGS_C,N,N,R,A,L,2,1 - xGS_C,N,N,R,A,L,2,2  = 0
 c836:  0.998 xGS_C,N,N,R,A,L,2,2 - xGS_C,N,N,R,A,L,2,3  = 0
 c837:  0.998 xGS_C,N,N,R,A,L,2,3 - xGS_C,N,N,R,A,L,2,4  = 0
 c838:  0.998 xGS_C,N,N,R,A,L,2,4 - xGS_C,N,N,R,A,L,2,5  = 0
 c839:  0.998 xGS_C,N,N,R,A,L,3,1 - xGS_C,N,N,R,A,L,3,2  = 0
 c840:  0.998 xGS_C,N,N,R,A,L,3,2 - xGS_C,N,N,R,A,L,3,3  = 0
 c841:  0.998 xGS_C,N,N,R,A,L,3,3 - xGS_C,N,N,R,A,L,3,4  = 0
 c842:  0.998 xGS_C,N,N,R,A,L,3,4 - xGS_C,N,N,R,A,L,3,5  = 0
 c843:  0.998 xGS_C,N,N,R,A,L,4,1 - xGS_C,N,N,R,A,L,4,2  = 0
 c844:  0.998 xGS_C,N,N,R,A,L,4,2 - xGS_C,N,N,R,A,L,4,3  = 0
 c845:  0.998 xGS_C,N,N,R,A,L,4,3 - xGS_C,N,N,R,A,L,4,4  = 0
 c846:  0.998 xGS_C,N,N,R,A,L,4,4 - xGS_C,N,N,R,A,L,4,5  = 0
 c847:  0.998 xGS_C,N,O,L,A,L,0,1 - xGS_C,N,O,L,A,L,0,2  = 0
 c848:  0.998 xGS_C,N,O,L,A,L,0,2 - xGS_C,N,O,L,A,L,0,3  = 0
 c849:  0.998 xGS_C,N,O,L,A,L,0,3 - xGS_C,N,O,L,A,L,0,4  = 0
 c850:  0.998 xGS_C,N,O,L,A,L,0,4 - xGS_C,N,O,L,A,L,0,5  = 0
 c851:  0.998 xGS_C,N,O,L,A,L,1,1 - xGS_C,N,O,L,A,L,1,2  = 0
 c852:  0.998 xGS_C,N,O,L,A,L,1,2 - xGS_C,N,O,L,A,L,1,3  = 0
 c853:  0.998 xGS_C,N,O,L,A,L,1,3 - xGS_C,N,O,L,A,L,1,4  = 0
 c854:  0.998 xGS_C,N,O,L,A,L,1,4 - xGS_C,N,O,L,A,L,1,5  = 0
 c855:  0.998 xGS_C,N,O,L,A,L,2,1 - xGS_C,N,O,L,A,L,2,2  = 0
 c856:  0.998 xGS_C,N,O,L,A,L,2,2 - xGS_C,N,O,L,A,L,2,3  = 0
 c857:  0.998 xGS_C,N,O,L,A,L,2,3 - xGS_C,N,O,L,A,L,2,4  = 0
 c858:  0.998 xGS_C,N,O,L,A,L,2,4 - xGS_C,N,O,L,A,L,2,5  = 0
 c859:  0.998 xGS_C,N,O,L,A,L,3,1 - xGS_C,N,O,L,A,L,3,2  = 0
 c860:  0.998 xGS_C,N,O,L,A,L,3,2 - xGS_C,N,O,L,A,L,3,3  = 0
 c861:  0.998 xGS_C,N,O,L,A,L,3,3 - xGS_C,N,O,L,A,L,3,4  = 0
 c862:  0.998 xGS_C,N,O,L,A,L,3,4 - xGS_C,N,O,L,A,L,3,5  = 0
 c863:  0.998 xGS_C,N,O,L,A,L,4,1 - xGS_C,N,O,L,A,L,4,2  = 0
 c864:  0.998 xGS_C,N,O,L,A,L,4,2 - xGS_C,N,O,L,A,L,4,3  = 0
 c865:  0.998 xGS_C,N,O,L,A,L,4,3 - xGS_C,N,O,L,A,L,4,4  = 0
 c866:  0.998 xGS_C,N,O,L,A,L,4,4 - xGS_C,N,O,L,A,L,4,5  = 0
 c867:  0.998 xGS_C,N,O,H,A,L,0,1 - xGS_C,N,O,H,A,L,0,2  = 0
 c868:  0.998 xGS_C,N,O,H,A,L,0,2 - xGS_C,N,O,H,A,L,0,3  = 0
 c869:  0.998 xGS_C,N,O,H,A,L,0,3 - xGS_C,N,O,H,A,L,0,4  = 0
 c870:  0.998 xGS_C,N,O,H,A,L,0,4 - xGS_C,N,O,H,A,L,0,5  = 0
 c871:  0.998 xGS_C,N,O,H,A,L,1,1 - xGS_C,N,O,H,A,L,1,2  = 0
 c872:  0.998 xGS_C,N,O,H,A,L,1,2 - xGS_C,N,O,H,A,L,1,3  = 0
 c873:  0.998 xGS_C,N,O,H,A,L,1,3 - xGS_C,N,O,H,A,L,1,4  = 0
 c874:  0.998 xGS_C,N,O,H,A,L,1,4 - xGS_C,N,O,H,A,L,1,5  = 0
 c875:  0.998 xGS_C,N,O,H,A,L,2,1 - xGS_C,N,O,H,A,L,2,2  = 0
 c876:  0.998 xGS_C,N,O,H,A,L,2,2 - xGS_C,N,O,H,A,L,2,3  = 0
 c877:  0.998 xGS_C,N,O,H,A,L,2,3 - xGS_C,N,O,H,A,L,2,4  = 0
 c878:  0.998 xGS_C,N,O,H,A,L,2,4 - xGS_C,N,O,H,A,L,2,5  = 0
 c879:  0.998 xGS_C,N,O,H,A,L,3,1 - xGS_C,N,O,H,A,L,3,2  = 0
 c880:  0.998 xGS_C,N,O,H,A,L,3,2 - xGS_C,N,O,H,A,L,3,3  = 0
 c881:  0.998 xGS_C,N,O,H,A,L,3,3 - xGS_C,N,O,H,A,L,3,4  = 0
 c882:  0.998 xGS_C,N,O,H,A,L,3,4 - xGS_C,N,O,H,A,L,3,5  = 0
 c883:  0.998 xGS_C,N,O,H,A,L,4,1 - xGS_C,N,O,H,A,L,4,2  = 0
 c884:  0.998 xGS_C,N,O,H,A,L,4,2 - xGS_C,N,O,H,A,L,4,3  = 0
 c885:  0.998 xGS_C,N,O,H,A,L,4,3 - xGS_C,N,O,H,A,L,4,4  = 0
 c886:  0.998 xGS_C,N,O,H,A,L,4,4 - xGS_C,N,O,H,A,L,4,5  = 0
 c887:  0.998 xGS_C,N,O,C,A,L,0,1 - xGS_C,N,O,C,A,L,0,2  = 0
 c888:  0.998 xGS_C,N,O,C,A,L,0,2 - xGS_C,N,O,C,A,L,0,3  = 0
 c889:  0.998 xGS_C,N,O,C,A,L,0,3 - xGS_C,N,O,C,A,L,0,4  = 0
 c890:  0.998 xGS_C,N,O,C,A,L,0,4 - xGS_C,N,O,C,A,L,0,5  = 0
 c891:  0.998 xGS_C,N,O,C,A,L,1,1 - xGS_C,N,O,C,A,L,1,2  = 0
 c892:  0.998 xGS_C,N,O,C,A,L,1,2 - xGS_C,N,O,C,A,L,1,3  = 0
 c893:  0.998 xGS_C,N,O,C,A,L,1,3 - xGS_C,N,O,C,A,L,1,4  = 0
 c894:  0.998 xGS_C,N,O,C,A,L,1,4 - xGS_C,N,O,C,A,L,1,5  = 0
 c895:  0.998 xGS_C,N,O,C,A,L,2,1 - xGS_C,N,O,C,A,L,2,2  = 0
 c896:  0.998 xGS_C,N,O,C,A,L,2,2 - xGS_C,N,O,C,A,L,2,3  = 0
 c897:  0.998 xGS_C,N,O,C,A,L,2,3 - xGS_C,N,O,C,A,L,2,4  = 0
 c898:  0.998 xGS_C,N,O,C,A,L,2,4 - xGS_C,N,O,C,A,L,2,5  = 0
 c899:  0.998 xGS_C,N,O,C,A,L,3,1 - xGS_C,N,O,C,A,L,3,2  = 0
 c900:  0.998 xGS_C,N,O,C,A,L,3,2 - xGS_C,N,O,C,A,L,3,3  = 0
 c901:  0.998 xGS_C,N,O,C,A,L,3,3 - xGS_C,N,O,C,A,L,3,4  = 0
 c902:  0.998 xGS_C,N,O,C,A,L,3,4 - xGS_C,N,O,C,A,L,3,5  = 0
 c903:  0.998 xGS_C,N,O,C,A,L,4,1 - xGS_C,N,O,C,A,L,4,2  = 0
 c904:  0.998 xGS_C,N,O,C,A,L,4,2 - xGS_C,N,O,C,A,L,4,3  = 0
 c905:  0.998 xGS_C,N,O,C,A,L,4,3 - xGS_C,N,O,C,A,L,4,4  = 0
 c906:  0.998 xGS_C,N,O,C,A,L,4,4 - xGS_C,N,O,C,A,L,4,5  = 0
 c907:  0.998 xGS_C,N,O,R,A,L,0,1 - xGS_C,N,O,R,A,L,0,2  = 0
 c908:  0.998 xGS_C,N,O,R,A,L,0,2 - xGS_C,N,O,R,A,L,0,3  = 0
 c909:  0.998 xGS_C,N,O,R,A,L,0,3 - xGS_C,N,O,R,A,L,0,4  = 0
 c910:  0.998 xGS_C,N,O,R,A,L,0,4 - xGS_C,N,O,R,A,L,0,5  = 0
 c911:  0.998 xGS_C,N,O,R,A,L,1,1 - xGS_C,N,O,R,A,L,1,2  = 0
 c912:  0.998 xGS_C,N,O,R,A,L,1,2 - xGS_C,N,O,R,A,L,1,3  = 0
 c913:  0.998 xGS_C,N,O,R,A,L,1,3 - xGS_C,N,O,R,A,L,1,4  = 0
 c914:  0.998 xGS_C,N,O,R,A,L,1,4 - xGS_C,N,O,R,A,L,1,5  = 0
 c915:  0.998 xGS_C,N,O,R,A,L,2,1 - xGS_C,N,O,R,A,L,2,2  = 0
 c916:  0.998 xGS_C,N,O,R,A,L,2,2 - xGS_C,N,O,R,A,L,2,3  = 0
 c917:  0.998 xGS_C,N,O,R,A,L,2,3 - xGS_C,N,O,R,A,L,2,4  = 0
 c918:  0.998 xGS_C,N,O,R,A,L,2,4 - xGS_C,N,O,R,A,L,2,5  = 0
 c919:  0.998 xGS_C,N,O,R,A,L,3,1 - xGS_C,N,O,R,A,L,3,2  = 0
 c920:  0.998 xGS_C,N,O,R,A,L,3,2 - xGS_C,N,O,R,A,L,3,3  = 0
 c921:  0.998 xGS_C,N,O,R,A,L,3,3 - xGS_C,N,O,R,A,L,3,4  = 0
 c922:  0.998 xGS_C,N,O,R,A,L,3,4 - xGS_C,N,O,R,A,L,3,5  = 0
 c923:  0.998 xGS_C,N,O,R,A,L,4,1 - xGS_C,N,O,R,A,L,4,2  = 0
 c924:  0.998 xGS_C,N,O,R,A,L,4,2 - xGS_C,N,O,R,A,L,4,3  = 0
 c925:  0.998 xGS_C,N,O,R,A,L,4,3 - xGS_C,N,O,R,A,L,4,4  = 0
 c926:  0.998 xGS_C,N,O,R,A,L,4,4 - xGS_C,N,O,R,A,L,4,5  = 0
 c927:  x_B,R,N,L,A,L,3 - xEAe_B,R,N,L,A,L,1  = 0
 c928:  x_B,R,N,C,A,L,3 - xEAe_B,R,N,C,A,L,1  = 0
 c929:  x_B,R,N,R,A,L,3 - xEAe_B,R,N,R,A,L,1  = 0
 c930:  x_B,R,P,L,A,L,3 - xEAe_B,R,P,L,A,L,1  = 0
 c931:  x_B,R,P,C,A,L,3 - xEAe_B,R,P,C,A,L,1  = 0
 c932:  x_B,R,P,R,A,L,3 - xEAe_B,R,P,R,A,L,1  = 0
 c933:  x_B,N,N,L,A,L,3 - xEAe_B,N,N,L,A,L,1  = 0
 c934:  x_B,N,N,C,A,L,3 - xEAe_B,N,N,C,A,L,1  = 0
 c935:  x_B,N,P,R,A,L,3 - xEAe_B,N,P,R,A,L,1  = 0
 c936:  x_C,N,N,L,A,L,3 - xEAe_C,N,N,L,A,L,1  = 0
 c937:  x_C,N,N,H,A,L,3 - xEAe_C,N,N,H,A,L,1  = 0
 c938:  x_C,N,N,C,A,L,3 - xEAe_C,N,N,C,A,L,1  = 0
 c939:  x_C,N,N,R,A,L,3 - xEAe_C,N,N,R,A,L,1  = 0
 c940:  x_C,N,O,L,A,L,3 - xEAe_C,N,O,L,A,L,1  = 0
 c941:  x_C,N,O,H,A,L,3 - xEAe_C,N,O,H,A,L,1  = 0
 c942:  x_C,N,O,C,A,L,3 - xEAe_C,N,O,C,A,L,1  = 0
 c943:  x_C,N,O,R,A,L,3 - xEAe_C,N,O,R,A,L,1  = 0
 c944:  0.998 xEAe_B,R,N,L,A,L,1 - xEAe_B,R,N,L,A,L,2  = 0
 c945:  0.998 xEAe_B,R,N,L,A,L,2 - xEAe_B,R,N,L,A,L,3  = 0
 c946:  0.998 xEAe_B,R,N,L,A,L,3 - xEAe_B,R,N,L,A,L,4  = 0
 c947:  0.998 xEAe_B,R,N,L,A,L,4 - xEAe_B,R,N,L,A,L,5  = 0
 c948:  0.998 xEAe_B,R,N,C,A,L,1 - xEAe_B,R,N,C,A,L,2  = 0
 c949:  0.998 xEAe_B,R,N,C,A,L,2 - xEAe_B,R,N,C,A,L,3  = 0
 c950:  0.998 xEAe_B,R,N,C,A,L,3 - xEAe_B,R,N,C,A,L,4  = 0
 c951:  0.998 xEAe_B,R,N,C,A,L,4 - xEAe_B,R,N,C,A,L,5  = 0
 c952:  0.998 xEAe_B,R,N,R,A,L,1 - xEAe_B,R,N,R,A,L,2  = 0
 c953:  0.998 xEAe_B,R,N,R,A,L,2 - xEAe_B,R,N,R,A,L,3  = 0
 c954:  0.998 xEAe_B,R,N,R,A,L,3 - xEAe_B,R,N,R,A,L,4  = 0
 c955:  0.998 xEAe_B,R,N,R,A,L,4 - xEAe_B,R,N,R,A,L,5  = 0
 c956:  0.998 xEAe_B,R,P,L,A,L,1 - xEAe_B,R,P,L,A,L,2  = 0
 c957:  0.998 xEAe_B,R,P,L,A,L,2 - xEAe_B,R,P,L,A,L,3  = 0
 c958:  0.998 xEAe_B,R,P,L,A,L,3 - xEAe_B,R,P,L,A,L,4  = 0
 c959:  0.998 xEAe_B,R,P,L,A,L,4 - xEAe_B,R,P,L,A,L,5  = 0
 c960:  0.998 xEAe_B,R,P,C,A,L,1 - xEAe_B,R,P,C,A,L,2  = 0
 c961:  0.998 xEAe_B,R,P,C,A,L,2 - xEAe_B,R,P,C,A,L,3  = 0
 c962:  0.998 xEAe_B,R,P,C,A,L,3 - xEAe_B,R,P,C,A,L,4  = 0
 c963:  0.998 xEAe_B,R,P,C,A,L,4 - xEAe_B,R,P,C,A,L,5  = 0
 c964:  0.998 xEAe_B,R,P,R,A,L,1 - xEAe_B,R,P,R,A,L,2  = 0
 c965:  0.998 xEAe_B,R,P,R,A,L,2 - xEAe_B,R,P,R,A,L,3  = 0
 c966:  0.998 xEAe_B,R,P,R,A,L,3 - xEAe_B,R,P,R,A,L,4  = 0
 c967:  0.998 xEAe_B,R,P,R,A,L,4 - xEAe_B,R,P,R,A,L,5  = 0
 c968:  0.998 xEAe_B,N,N,L,A,L,1 - xEAe_B,N,N,L,A,L,2  = 0
 c969:  0.998 xEAe_B,N,N,L,A,L,2 - xEAe_B,N,N,L,A,L,3  = 0
 c970:  0.998 xEAe_B,N,N,L,A,L,3 - xEAe_B,N,N,L,A,L,4  = 0
 c971:  0.998 xEAe_B,N,N,L,A,L,4 - xEAe_B,N,N,L,A,L,5  = 0
 c972:  0.998 xEAe_B,N,N,C,A,L,1 - xEAe_B,N,N,C,A,L,2  = 0
 c973:  0.998 xEAe_B,N,N,C,A,L,2 - xEAe_B,N,N,C,A,L,3  = 0
 c974:  0.998 xEAe_B,N,N,C,A,L,3 - xEAe_B,N,N,C,A,L,4  = 0
 c975:  0.998 xEAe_B,N,N,C,A,L,4 - xEAe_B,N,N,C,A,L,5  = 0
 c976:  0.998 xEAe_B,N,P,R,A,L,1 - xEAe_B,N,P,R,A,L,2  = 0
 c977:  0.998 xEAe_B,N,P,R,A,L,2 - xEAe_B,N,P,R,A,L,3  = 0
 c978:  0.998 xEAe_B,N,P,R,A,L,3 - xEAe_B,N,P,R,A,L,4  = 0
 c979:  0.998 xEAe_B,N,P,R,A,L,4 - xEAe_B,N,P,R,A,L,5  = 0
 c980:  0.998 xEAe_C,N,N,L,A,L,1 - xEAe_C,N,N,L,A,L,2  = 0
 c981:  0.998 xEAe_C,N,N,L,A,L,2 - xEAe_C,N,N,L,A,L,3  = 0
 c982:  0.998 xEAe_C,N,N,L,A,L,3 - xEAe_C,N,N,L,A,L,4  = 0
 c983:  0.998 xEAe_C,N,N,L,A,L,4 - xEAe_C,N,N,L,A,L,5  = 0
 c984:  0.998 xEAe_C,N,N,H,A,L,1 - xEAe_C,N,N,H,A,L,2  = 0
 c985:  0.998 xEAe_C,N,N,H,A,L,2 - xEAe_C,N,N,H,A,L,3  = 0
 c986:  0.998 xEAe_C,N,N,H,A,L,3 - xEAe_C,N,N,H,A,L,4  = 0
 c987:  0.998 xEAe_C,N,N,H,A,L,4 - xEAe_C,N,N,H,A,L,5  = 0
 c988:  0.998 xEAe_C,N,N,C,A,L,1 - xEAe_C,N,N,C,A,L,2  = 0
 c989:  0.998 xEAe_C,N,N,C,A,L,2 - xEAe_C,N,N,C,A,L,3  = 0
 c990:  0.998 xEAe_C,N,N,C,A,L,3 - xEAe_C,N,N,C,A,L,4  = 0
 c991:  0.998 xEAe_C,N,N,C,A,L,4 - xEAe_C,N,N,C,A,L,5  = 0
 c992:  0.998 xEAe_C,N,N,R,A,L,1 - xEAe_C,N,N,R,A,L,2  = 0
 c993:  0.998 xEAe_C,N,N,R,A,L,2 - xEAe_C,N,N,R,A,L,3  = 0
 c994:  0.998 xEAe_C,N,N,R,A,L,3 - xEAe_C,N,N,R,A,L,4  = 0
 c995:  0.998 xEAe_C,N,N,R,A,L,4 - xEAe_C,N,N,R,A,L,5  = 0
 c996:  0.998 xEAe_C,N,O,L,A,L,1 - xEAe_C,N,O,L,A,L,2  = 0
 c997:  0.998 xEAe_C,N,O,L,A,L,2 - xEAe_C,N,O,L,A,L,3  = 0
 c998:  0.998 xEAe_C,N,O,L,A,L,3 - xEAe_C,N,O,L,A,L,4  = 0
 c999:  0.998 xEAe_C,N,O,L,A,L,4 - xEAe_C,N,O,L,A,L,5  = 0
 c1000: 0.998 xEAe_C,N,O,H,A,L,1 - xEAe_C,N,O,H,A,L,2  = 0
 c1001: 0.998 xEAe_C,N,O,H,A,L,2 - xEAe_C,N,O,H,A,L,3  = 0
 c1002: 0.998 xEAe_C,N,O,H,A,L,3 - xEAe_C,N,O,H,A,L,4  = 0
 c1003: 0.998 xEAe_C,N,O,H,A,L,4 - xEAe_C,N,O,H,A,L,5  = 0
 c1004: 0.998 xEAe_C,N,O,C,A,L,1 - xEAe_C,N,O,C,A,L,2  = 0
 c1005: 0.998 xEAe_C,N,O,C,A,L,2 - xEAe_C,N,O,C,A,L,3  = 0
 c1006: 0.998 xEAe_C,N,O,C,A,L,3 - xEAe_C,N,O,C,A,L,4  = 0
 c1007: 0.998 xEAe_C,N,O,C,A,L,4 - xEAe_C,N,O,C,A,L,5  = 0
 c1008: 0.998 xEAe_C,N,O,R,A,L,1 - xEAe_C,N,O,R,A,L,2  = 0
 c1009: 0.998 xEAe_C,N,O,R,A,L,2 - xEAe_C,N,O,R,A,L,3  = 0
 c1010: 0.998 xEAe_C,N,O,R,A,L,3 - xEAe_C,N,O,R,A,L,4  = 0
 c1011: 0.998 xEAe_C,N,O,R,A,L,4 - xEAe_C,N,O,R,A,L,5  = 0
 c1012: xEAe_B,R,N,L,A,L,5  = 0
 c1013: xEAe_B,R,N,C,A,L,5  = 0
 c1014: xEAe_B,R,N,R,A,L,5  = 0
 c1015: xEAe_B,R,P,L,A,L,5  = 0
 c1016: xEAe_B,R,P,C,A,L,5  = 0
 c1017: xEAe_B,R,P,R,A,L,5  = 0
 c1018: xEAe_B,N,N,L,A,L,5  = 0
 c1019: xEAe_B,N,N,C,A,L,5  = 0
 c1020: xEAe_B,N,P,R,A,L,5  = 0
 c1021: xEAe_C,N,N,L,A,L,5  = 0
 c1022: xEAe_C,N,N,H,A,L,5  = 0
 c1023: xEAe_C,N,N,C,A,L,5  = 0
 c1024: xEAe_C,N,N,R,A,L,5  = 0
 c1025: xEAe_C,N,O,L,A,L,5  = 0
 c1026: xEAe_C,N,O,H,A,L,5  = 0
 c1027: xEAe_C,N,O,C,A,L,5  = 0
 c1028: xEAe_C,N,O,R,A,L,5  = 0
 c1029: fire_B,R,N,L,P,1,P + fire_B,R,N,L,P,1,D + fire_B,R,N,L,P,1,W
        + fire_B,R,N,L,P,1,C + fire_B,R,N,L,P,1,I + fire_B,R,N,L,P,1,A
        + fire_B,R,N,L,P,1,L + fire_B,R,N,L,P,1,N  = 0
 c1030: - 0.002 xEAr_B,R,N,L,P,2,1 + fire_B,R,N,L,P,2,P + fire_B,R,N,L,P,2,D
        + fire_B,R,N,L,P,2,W + fire_B,R,N,L,P,2,C + fire_B,R,N,L,P,2,I
        + fire_B,R,N,L,P,2,A + fire_B,R,N,L,P,2,L + fire_B,R,N,L,P,2,N  = 0
 c1031: - 0.002 xEAr_B,R,N,L,P,3,1 - 0.002 xEAr_B,R,N,L,P,3,2
        + fire_B,R,N,L,P,3,P + fire_B,R,N,L,P,3,D + fire_B,R,N,L,P,3,W
        + fire_B,R,N,L,P,3,C + fire_B,R,N,L,P,3,I + fire_B,R,N,L,P,3,A
        + fire_B,R,N,L,P,3,L + fire_B,R,N,L,P,3,N  = 0
 c1032: - 0.002 xEAr_B,R,N,L,P,4,1 - 0.002 xEAr_B,R,N,L,P,4,2
        - 0.002 xEAr_B,R,N,L,P,4,3 + fire_B,R,N,L,P,4,P + fire_B,R,N,L,P,4,D
        + fire_B,R,N,L,P,4,W + fire_B,R,N,L,P,4,C + fire_B,R,N,L,P,4,I
        + fire_B,R,N,L,P,4,A + fire_B,R,N,L,P,4,L + fire_B,R,N,L,P,4,N  = 0
 c1033: - 0.002 xEAr_B,R,N,L,P,5,1 - 0.002 xEAr_B,R,N,L,P,5,2
        - 0.002 xEAr_B,R,N,L,P,5,3 - 0.002 xEAr_B,R,N,L,P,5,4
        + fire_B,R,N,L,P,5,P + fire_B,R,N,L,P,5,D + fire_B,R,N,L,P,5,W
        + fire_B,R,N,L,P,5,C + fire_B,R,N,L,P,5,I + fire_B,R,N,L,P,5,A
        + fire_B,R,N,L,P,5,L + fire_B,R,N,L,P,5,N  = 0
 c1034: fire_B,R,N,L,D,1,P + fire_B,R,N,L,D,1,D + fire_B,R,N,L,D,1,W
        + fire_B,R,N,L,D,1,C + fire_B,R,N,L,D,1,I + fire_B,R,N,L,D,1,A
        + fire_B,R,N,L,D,1,L + fire_B,R,N,L,D,1,N  = 0
 c1035: - 0.002 xEAr_B,R,N,L,D,2,1 + fire_B,R,N,L,D,2,P + fire_B,R,N,L,D,2,D
        + fire_B,R,N,L,D,2,W + fire_B,R,N,L,D,2,C + fire_B,R,N,L,D,2,I
        + fire_B,R,N,L,D,2,A + fire_B,R,N,L,D,2,L + fire_B,R,N,L,D,2,N  = 0
 c1036: - 0.002 xEAr_B,R,N,L,D,3,1 - 0.002 xEAr_B,R,N,L,D,3,2
        + fire_B,R,N,L,D,3,P + fire_B,R,N,L,D,3,D + fire_B,R,N,L,D,3,W
        + fire_B,R,N,L,D,3,C + fire_B,R,N,L,D,3,I + fire_B,R,N,L,D,3,A
        + fire_B,R,N,L,D,3,L + fire_B,R,N,L,D,3,N  = 0
 c1037: - 0.002 xEAr_B,R,N,L,D,4,1 - 0.002 xEAr_B,R,N,L,D,4,2
        - 0.002 xEAr_B,R,N,L,D,4,3 + fire_B,R,N,L,D,4,P + fire_B,R,N,L,D,4,D
        + fire_B,R,N,L,D,4,W + fire_B,R,N,L,D,4,C + fire_B,R,N,L,D,4,I
        + fire_B,R,N,L,D,4,A + fire_B,R,N,L,D,4,L + fire_B,R,N,L,D,4,N  = 0
 c1038: - 0.002 xEAr_B,R,N,L,D,5,1 - 0.002 xEAr_B,R,N,L,D,5,2
        - 0.002 xEAr_B,R,N,L,D,5,3 - 0.002 xEAr_B,R,N,L,D,5,4
        + fire_B,R,N,L,D,5,P + fire_B,R,N,L,D,5,D + fire_B,R,N,L,D,5,W
        + fire_B,R,N,L,D,5,C + fire_B,R,N,L,D,5,I + fire_B,R,N,L,D,5,A
        + fire_B,R,N,L,D,5,L + fire_B,R,N,L,D,5,N  = 0
 c1039: fire_B,R,N,L,W,1,P + fire_B,R,N,L,W,1,D + fire_B,R,N,L,W,1,W
        + fire_B,R,N,L,W,1,C + fire_B,R,N,L,W,1,I + fire_B,R,N,L,W,1,A
        + fire_B,R,N,L,W,1,L + fire_B,R,N,L,W,1,N  = 0
 c1040: - 0.23 xEAr_B,R,N,L,W,2,1 + fire_B,R,N,L,W,2,P + fire_B,R,N,L,W,2,D
        + fire_B,R,N,L,W,2,W + fire_B,R,N,L,W,2,C + fire_B,R,N,L,W,2,I
        + fire_B,R,N,L,W,2,A + fire_B,R,N,L,W,2,L + fire_B,R,N,L,W,2,N  = 0
 c1041: - 0.23 xEAr_B,R,N,L,W,3,1 - 0.002 xEAr_B,R,N,L,W,3,2
        + fire_B,R,N,L,W,3,P + fire_B,R,N,L,W,3,D + fire_B,R,N,L,W,3,W
        + fire_B,R,N,L,W,3,C + fire_B,R,N,L,W,3,I + fire_B,R,N,L,W,3,A
        + fire_B,R,N,L,W,3,L + fire_B,R,N,L,W,3,N  = 0
 c1042: - 0.23 xEAr_B,R,N,L,W,4,1 - 0.002 xEAr_B,R,N,L,W,4,2
        - 0.002 xEAr_B,R,N,L,W,4,3 + fire_B,R,N,L,W,4,P + fire_B,R,N,L,W,4,D
        + fire_B,R,N,L,W,4,W + fire_B,R,N,L,W,4,C + fire_B,R,N,L,W,4,I
        + fire_B,R,N,L,W,4,A + fire_B,R,N,L,W,4,L + fire_B,R,N,L,W,4,N  = 0
 c1043: - 0.23 xEAr_B,R,N,L,W,5,1 - 0.002 xEAr_B,R,N,L,W,5,2
        - 0.002 xEAr_B,R,N,L,W,5,3 - 0.002 xEAr_B,R,N,L,W,5,4
        + fire_B,R,N,L,W,5,P + fire_B,R,N,L,W,5,D + fire_B,R,N,L,W,5,W
        + fire_B,R,N,L,W,5,C + fire_B,R,N,L,W,5,I + fire_B,R,N,L,W,5,A
        + fire_B,R,N,L,W,5,L + fire_B,R,N,L,W,5,N  = 0
 c1044: fire_B,R,N,L,C,1,P + fire_B,R,N,L,C,1,D + fire_B,R,N,L,C,1,W
        + fire_B,R,N,L,C,1,C + fire_B,R,N,L,C,1,I + fire_B,R,N,L,C,1,A
        + fire_B,R,N,L,C,1,L + fire_B,R,N,L,C,1,N  = 0
 c1045: - 0.002 xEAr_B,R,N,L,C,2,1 + fire_B,R,N,L,C,2,P + fire_B,R,N,L,C,2,D
        + fire_B,R,N,L,C,2,W + fire_B,R,N,L,C,2,C + fire_B,R,N,L,C,2,I
        + fire_B,R,N,L,C,2,A + fire_B,R,N,L,C,2,L + fire_B,R,N,L,C,2,N  = 0
 c1046: - 0.002 xEAr_B,R,N,L,C,3,1 - 0.002 xEAr_B,R,N,L,C,3,2
        + fire_B,R,N,L,C,3,P + fire_B,R,N,L,C,3,D + fire_B,R,N,L,C,3,W
        + fire_B,R,N,L,C,3,C + fire_B,R,N,L,C,3,I + fire_B,R,N,L,C,3,A
        + fire_B,R,N,L,C,3,L + fire_B,R,N,L,C,3,N  = 0
 c1047: - 0.002 xEAr_B,R,N,L,C,4,1 - 0.002 xEAr_B,R,N,L,C,4,2
        - 0.002 xEAr_B,R,N,L,C,4,3 + fire_B,R,N,L,C,4,P + fire_B,R,N,L,C,4,D
        + fire_B,R,N,L,C,4,W + fire_B,R,N,L,C,4,C + fire_B,R,N,L,C,4,I
        + fire_B,R,N,L,C,4,A + fire_B,R,N,L,C,4,L + fire_B,R,N,L,C,4,N  = 0
 c1048: - 0.002 xEAr_B,R,N,L,C,5,1 - 0.002 xEAr_B,R,N,L,C,5,2
        - 0.002 xEAr_B,R,N,L,C,5,3 - 0.002 xEAr_B,R,N,L,C,5,4
        + fire_B,R,N,L,C,5,P + fire_B,R,N,L,C,5,D + fire_B,R,N,L,C,5,W
        + fire_B,R,N,L,C,5,C + fire_B,R,N,L,C,5,I + fire_B,R,N,L,C,5,A
        + fire_B,R,N,L,C,5,L + fire_B,R,N,L,C,5,N  = 0
 c1049: fire_B,R,N,L,I,1,P + fire_B,R,N,L,I,1,D + fire_B,R,N,L,I,1,W
        + fire_B,R,N,L,I,1,C + fire_B,R,N,L,I,1,I + fire_B,R,N,L,I,1,A
        + fire_B,R,N,L,I,1,L + fire_B,R,N,L,I,1,N  = 0
 c1050: - 0.002 xEAr_B,R,N,L,I,2,1 + fire_B,R,N,L,I,2,P + fire_B,R,N,L,I,2,D
        + fire_B,R,N,L,I,2,W + fire_B,R,N,L,I,2,C + fire_B,R,N,L,I,2,I
        + fire_B,R,N,L,I,2,A + fire_B,R,N,L,I,2,L + fire_B,R,N,L,I,2,N  = 0
 c1051: - 0.002 xEAr_B,R,N,L,I,3,1 - 0.002 xEAr_B,R,N,L,I,3,2
        + fire_B,R,N,L,I,3,P + fire_B,R,N,L,I,3,D + fire_B,R,N,L,I,3,W
        + fire_B,R,N,L,I,3,C + fire_B,R,N,L,I,3,I + fire_B,R,N,L,I,3,A
        + fire_B,R,N,L,I,3,L + fire_B,R,N,L,I,3,N  = 0
 c1052: - 0.002 xEAr_B,R,N,L,I,4,1 - 0.002 xEAr_B,R,N,L,I,4,2
        - 0.002 xEAr_B,R,N,L,I,4,3 + fire_B,R,N,L,I,4,P + fire_B,R,N,L,I,4,D
        + fire_B,R,N,L,I,4,W + fire_B,R,N,L,I,4,C + fire_B,R,N,L,I,4,I
        + fire_B,R,N,L,I,4,A + fire_B,R,N,L,I,4,L + fire_B,R,N,L,I,4,N  = 0
 c1053: - 0.002 xEAr_B,R,N,L,I,5,1 - 0.002 xEAr_B,R,N,L,I,5,2
        - 0.002 xEAr_B,R,N,L,I,5,3 - 0.002 xEAr_B,R,N,L,I,5,4
        + fire_B,R,N,L,I,5,P + fire_B,R,N,L,I,5,D + fire_B,R,N,L,I,5,W
        + fire_B,R,N,L,I,5,C + fire_B,R,N,L,I,5,I + fire_B,R,N,L,I,5,A
        + fire_B,R,N,L,I,5,L + fire_B,R,N,L,I,5,N  = 0
 c1054: - 0.002 xNG_B,R,N,L,A,L,1 - 0.002 xPB_B,R,N,L,A,L,0,1
        - 0.002 xPB_B,R,N,L,A,L,1,1 - 0.002 xPB_B,R,N,L,A,L,2,1
        - 0.002 xPB_B,R,N,L,A,L,3,1 - 0.002 xPB_B,R,N,L,A,L,4,1
        - 0.002 xGS_B,R,N,L,A,L,0,1 - 0.002 xGS_B,R,N,L,A,L,1,1
        - 0.002 xGS_B,R,N,L,A,L,2,1 - 0.002 xGS_B,R,N,L,A,L,3,1
        - 0.002 xGS_B,R,N,L,A,L,4,1 - 0.002 xEAe_B,R,N,L,A,L,1
        + fire_B,R,N,L,A,1,P + fire_B,R,N,L,A,1,D + fire_B,R,N,L,A,1,W
        + fire_B,R,N,L,A,1,C + fire_B,R,N,L,A,1,I + fire_B,R,N,L,A,1,A
        + fire_B,R,N,L,A,1,L + fire_B,R,N,L,A,1,N  = 0
 c1055: - 0.002 xNG_B,R,N,L,A,L,2 - 0.002 xPB_B,R,N,L,A,L,0,2
        - 0.002 xPB_B,R,N,L,A,L,1,2 - 0.002 xPB_B,R,N,L,A,L,2,2
        - 0.002 xPB_B,R,N,L,A,L,3,2 - 0.002 xPB_B,R,N,L,A,L,4,2
        - 0.002 xGS_B,R,N,L,A,L,0,2 - 0.002 xGS_B,R,N,L,A,L,1,2
        - 0.002 xGS_B,R,N,L,A,L,2,2 - 0.002 xGS_B,R,N,L,A,L,3,2
        - 0.002 xGS_B,R,N,L,A,L,4,2 - 0.002 xEAe_B,R,N,L,A,L,2
        - 0.002 xEAr_B,R,N,L,A,2,1 + fire_B,R,N,L,A,2,P + fire_B,R,N,L,A,2,D
        + fire_B,R,N,L,A,2,W + fire_B,R,N,L,A,2,C + fire_B,R,N,L,A,2,I
        + fire_B,R,N,L,A,2,A + fire_B,R,N,L,A,2,L + fire_B,R,N,L,A,2,N  = 0
 c1056: - 0.002 xNG_B,R,N,L,A,L,3 - 0.002 xPB_B,R,N,L,A,L,0,3
        - 0.002 xPB_B,R,N,L,A,L,1,3 - 0.002 xPB_B,R,N,L,A,L,2,3
        - 0.002 xPB_B,R,N,L,A,L,3,3 - 0.002 xPB_B,R,N,L,A,L,4,3
        - 0.002 xGS_B,R,N,L,A,L,0,3 - 0.002 xGS_B,R,N,L,A,L,1,3
        - 0.002 xGS_B,R,N,L,A,L,2,3 - 0.002 xGS_B,R,N,L,A,L,3,3
        - 0.002 xGS_B,R,N,L,A,L,4,3 - 0.002 xEAe_B,R,N,L,A,L,3
        - 0.002 xEAr_B,R,N,L,A,3,1 - 0.002 xEAr_B,R,N,L,A,3,2
        + fire_B,R,N,L,A,3,P + fire_B,R,N,L,A,3,D + fire_B,R,N,L,A,3,W
        + fire_B,R,N,L,A,3,C + fire_B,R,N,L,A,3,I + fire_B,R,N,L,A,3,A
        + fire_B,R,N,L,A,3,L + fire_B,R,N,L,A,3,N  = 0
 c1057: - 0.002 xNG_B,R,N,L,A,L,4 - 0.002 xPB_B,R,N,L,A,L,0,4
        - 0.002 xPB_B,R,N,L,A,L,1,4 - 0.002 xPB_B,R,N,L,A,L,2,4
        - 0.002 xPB_B,R,N,L,A,L,3,4 - 0.002 xPB_B,R,N,L,A,L,4,4
        - 0.002 xGS_B,R,N,L,A,L,0,4 - 0.002 xGS_B,R,N,L,A,L,1,4
        - 0.002 xGS_B,R,N,L,A,L,2,4 - 0.002 xGS_B,R,N,L,A,L,3,4
        - 0.002 xGS_B,R,N,L,A,L,4,4 - 0.002 xEAe_B,R,N,L,A,L,4
        - 0.002 xEAr_B,R,N,L,A,4,1 - 0.002 xEAr_B,R,N,L,A,4,2
        - 0.002 xEAr_B,R,N,L,A,4,3 + fire_B,R,N,L,A,4,P + fire_B,R,N,L,A,4,D
        + fire_B,R,N,L,A,4,W + fire_B,R,N,L,A,4,C + fire_B,R,N,L,A,4,I
        + fire_B,R,N,L,A,4,A + fire_B,R,N,L,A,4,L + fire_B,R,N,L,A,4,N  = 0
 c1058: - 0.002 xNG_B,R,N,L,A,L,5 - 0.002 xPB_B,R,N,L,A,L,0,5
        - 0.002 xPB_B,R,N,L,A,L,1,5 - 0.002 xPB_B,R,N,L,A,L,2,5
        - 0.002 xPB_B,R,N,L,A,L,3,5 - 0.002 xPB_B,R,N,L,A,L,4,5
        - 0.002 xGS_B,R,N,L,A,L,0,5 - 0.002 xGS_B,R,N,L,A,L,1,5
        - 0.002 xGS_B,R,N,L,A,L,2,5 - 0.002 xGS_B,R,N,L,A,L,3,5
        - 0.002 xGS_B,R,N,L,A,L,4,5 - 0.002 xEAe_B,R,N,L,A,L,5
        - 0.002 xEAr_B,R,N,L,A,5,1 - 0.002 xEAr_B,R,N,L,A,5,2
        - 0.002 xEAr_B,R,N,L,A,5,3 - 0.002 xEAr_B,R,N,L,A,5,4
        + fire_B,R,N,L,A,5,P + fire_B,R,N,L,A,5,D + fire_B,R,N,L,A,5,W
        + fire_B,R,N,L,A,5,C + fire_B,R,N,L,A,5,I + fire_B,R,N,L,A,5,A
        + fire_B,R,N,L,A,5,L + fire_B,R,N,L,A,5,N  = 0
 c1059: fire_B,R,N,L,L,1,P + fire_B,R,N,L,L,1,D + fire_B,R,N,L,L,1,W
        + fire_B,R,N,L,L,1,C + fire_B,R,N,L,L,1,I + fire_B,R,N,L,L,1,A
        + fire_B,R,N,L,L,1,L + fire_B,R,N,L,L,1,N  = 0
 c1060: - 0.002 xEAr_B,R,N,L,L,2,1 + fire_B,R,N,L,L,2,P + fire_B,R,N,L,L,2,D
        + fire_B,R,N,L,L,2,W + fire_B,R,N,L,L,2,C + fire_B,R,N,L,L,2,I
        + fire_B,R,N,L,L,2,A + fire_B,R,N,L,L,2,L + fire_B,R,N,L,L,2,N  = 0
 c1061: - 0.002 xEAr_B,R,N,L,L,3,1 - 0.002 xEAr_B,R,N,L,L,3,2
        + fire_B,R,N,L,L,3,P + fire_B,R,N,L,L,3,D + fire_B,R,N,L,L,3,W
        + fire_B,R,N,L,L,3,C + fire_B,R,N,L,L,3,I + fire_B,R,N,L,L,3,A
        + fire_B,R,N,L,L,3,L + fire_B,R,N,L,L,3,N  = 0
 c1062: - 0.002 xEAr_B,R,N,L,L,4,1 - 0.002 xEAr_B,R,N,L,L,4,2
        - 0.002 xEAr_B,R,N,L,L,4,3 + fire_B,R,N,L,L,4,P + fire_B,R,N,L,L,4,D
        + fire_B,R,N,L,L,4,W + fire_B,R,N,L,L,4,C + fire_B,R,N,L,L,4,I
        + fire_B,R,N,L,L,4,A + fire_B,R,N,L,L,4,L + fire_B,R,N,L,L,4,N  = 0
 c1063: - 0.002 xEAr_B,R,N,L,L,5,1 - 0.002 xEAr_B,R,N,L,L,5,2
        - 0.002 xEAr_B,R,N,L,L,5,3 - 0.002 xEAr_B,R,N,L,L,5,4
        + fire_B,R,N,L,L,5,P + fire_B,R,N,L,L,5,D + fire_B,R,N,L,L,5,W
        + fire_B,R,N,L,L,5,C + fire_B,R,N,L,L,5,I + fire_B,R,N,L,L,5,A
        + fire_B,R,N,L,L,5,L + fire_B,R,N,L,L,5,N  = 0
 c1064: fire_B,R,N,L,N,1,P + fire_B,R,N,L,N,1,D + fire_B,R,N,L,N,1,W
        + fire_B,R,N,L,N,1,C + fire_B,R,N,L,N,1,I + fire_B,R,N,L,N,1,A
        + fire_B,R,N,L,N,1,L + fire_B,R,N,L,N,1,N  = 0
 c1065: fire_B,R,N,L,N,2,P + fire_B,R,N,L,N,2,D + fire_B,R,N,L,N,2,W
        + fire_B,R,N,L,N,2,C + fire_B,R,N,L,N,2,I + fire_B,R,N,L,N,2,A
        + fire_B,R,N,L,N,2,L + fire_B,R,N,L,N,2,N  = 0
 c1066: fire_B,R,N,L,N,3,P + fire_B,R,N,L,N,3,D + fire_B,R,N,L,N,3,W
        + fire_B,R,N,L,N,3,C + fire_B,R,N,L,N,3,I + fire_B,R,N,L,N,3,A
        + fire_B,R,N,L,N,3,L + fire_B,R,N,L,N,3,N  = 0
 c1067: fire_B,R,N,L,N,4,P + fire_B,R,N,L,N,4,D + fire_B,R,N,L,N,4,W
        + fire_B,R,N,L,N,4,C + fire_B,R,N,L,N,4,I + fire_B,R,N,L,N,4,A
        + fire_B,R,N,L,N,4,L + fire_B,R,N,L,N,4,N  = 0
 c1068: fire_B,R,N,L,N,5,P + fire_B,R,N,L,N,5,D + fire_B,R,N,L,N,5,W
        + fire_B,R,N,L,N,5,C + fire_B,R,N,L,N,5,I + fire_B,R,N,L,N,5,A
        + fire_B,R,N,L,N,5,L + fire_B,R,N,L,N,5,N  = 0
 c1069: fire_B,R,N,C,P,1,P + fire_B,R,N,C,P,1,D + fire_B,R,N,C,P,1,W
        + fire_B,R,N,C,P,1,C + fire_B,R,N,C,P,1,I + fire_B,R,N,C,P,1,A
        + fire_B,R,N,C,P,1,L + fire_B,R,N,C,P,1,N  = 0
 c1070: - 0.002 xEAr_B,R,N,C,P,2,1 + fire_B,R,N,C,P,2,P + fire_B,R,N,C,P,2,D
        + fire_B,R,N,C,P,2,W + fire_B,R,N,C,P,2,C + fire_B,R,N,C,P,2,I
        + fire_B,R,N,C,P,2,A + fire_B,R,N,C,P,2,L + fire_B,R,N,C,P,2,N  = 0
 c1071: - 0.002 xEAr_B,R,N,C,P,3,1 - 0.002 xEAr_B,R,N,C,P,3,2
        + fire_B,R,N,C,P,3,P + fire_B,R,N,C,P,3,D + fire_B,R,N,C,P,3,W
        + fire_B,R,N,C,P,3,C + fire_B,R,N,C,P,3,I + fire_B,R,N,C,P,3,A
        + fire_B,R,N,C,P,3,L + fire_B,R,N,C,P,3,N  = 0
 c1072: - 0.002 xEAr_B,R,N,C,P,4,1 - 0.002 xEAr_B,R,N,C,P,4,2
        - 0.002 xEAr_B,R,N,C,P,4,3 + fire_B,R,N,C,P,4,P + fire_B,R,N,C,P,4,D
        + fire_B,R,N,C,P,4,W + fire_B,R,N,C,P,4,C + fire_B,R,N,C,P,4,I
        + fire_B,R,N,C,P,4,A + fire_B,R,N,C,P,4,L + fire_B,R,N,C,P,4,N  = 0
 c1073: - 0.002 xEAr_B,R,N,C,P,5,1 - 0.002 xEAr_B,R,N,C,P,5,2
        - 0.002 xEAr_B,R,N,C,P,5,3 - 0.002 xEAr_B,R,N,C,P,5,4
        + fire_B,R,N,C,P,5,P + fire_B,R,N,C,P,5,D + fire_B,R,N,C,P,5,W
        + fire_B,R,N,C,P,5,C + fire_B,R,N,C,P,5,I + fire_B,R,N,C,P,5,A
        + fire_B,R,N,C,P,5,L + fire_B,R,N,C,P,5,N  = 0
 c1074: fire_B,R,N,C,D,1,P + fire_B,R,N,C,D,1,D + fire_B,R,N,C,D,1,W
        + fire_B,R,N,C,D,1,C + fire_B,R,N,C,D,1,I + fire_B,R,N,C,D,1,A
        + fire_B,R,N,C,D,1,L + fire_B,R,N,C,D,1,N  = 0
 c1075: - 0.002 xEAr_B,R,N,C,D,2,1 + fire_B,R,N,C,D,2,P + fire_B,R,N,C,D,2,D
        + fire_B,R,N,C,D,2,W + fire_B,R,N,C,D,2,C + fire_B,R,N,C,D,2,I
        + fire_B,R,N,C,D,2,A + fire_B,R,N,C,D,2,L + fire_B,R,N,C,D,2,N  = 0
 c1076: - 0.002 xEAr_B,R,N,C,D,3,1 - 0.002 xEAr_B,R,N,C,D,3,2
        + fire_B,R,N,C,D,3,P + fire_B,R,N,C,D,3,D + fire_B,R,N,C,D,3,W
        + fire_B,R,N,C,D,3,C + fire_B,R,N,C,D,3,I + fire_B,R,N,C,D,3,A
        + fire_B,R,N,C,D,3,L + fire_B,R,N,C,D,3,N  = 0
 c1077: - 0.002 xEAr_B,R,N,C,D,4,1 - 0.002 xEAr_B,R,N,C,D,4,2
        - 0.002 xEAr_B,R,N,C,D,4,3 + fire_B,R,N,C,D,4,P + fire_B,R,N,C,D,4,D
        + fire_B,R,N,C,D,4,W + fire_B,R,N,C,D,4,C + fire_B,R,N,C,D,4,I
        + fire_B,R,N,C,D,4,A + fire_B,R,N,C,D,4,L + fire_B,R,N,C,D,4,N  = 0
 c1078: - 0.002 xEAr_B,R,N,C,D,5,1 - 0.002 xEAr_B,R,N,C,D,5,2
        - 0.002 xEAr_B,R,N,C,D,5,3 - 0.002 xEAr_B,R,N,C,D,5,4
        + fire_B,R,N,C,D,5,P + fire_B,R,N,C,D,5,D + fire_B,R,N,C,D,5,W
        + fire_B,R,N,C,D,5,C + fire_B,R,N,C,D,5,I + fire_B,R,N,C,D,5,A
        + fire_B,R,N,C,D,5,L + fire_B,R,N,C,D,5,N  = 0
 c1079: fire_B,R,N,C,W,1,P + fire_B,R,N,C,W,1,D + fire_B,R,N,C,W,1,W
        + fire_B,R,N,C,W,1,C + fire_B,R,N,C,W,1,I + fire_B,R,N,C,W,1,A
        + fire_B,R,N,C,W,1,L + fire_B,R,N,C,W,1,N  = 0
 c1080: - 0.23 xEAr_B,R,N,C,W,2,1 + fire_B,R,N,C,W,2,P + fire_B,R,N,C,W,2,D
        + fire_B,R,N,C,W,2,W + fire_B,R,N,C,W,2,C + fire_B,R,N,C,W,2,I
        + fire_B,R,N,C,W,2,A + fire_B,R,N,C,W,2,L + fire_B,R,N,C,W,2,N  = 0
 c1081: - 0.23 xEAr_B,R,N,C,W,3,1 - 0.002 xEAr_B,R,N,C,W,3,2
        + fire_B,R,N,C,W,3,P + fire_B,R,N,C,W,3,D + fire_B,R,N,C,W,3,W
        + fire_B,R,N,C,W,3,C + fire_B,R,N,C,W,3,I + fire_B,R,N,C,W,3,A
        + fire_B,R,N,C,W,3,L + fire_B,R,N,C,W,3,N  = 0
 c1082: - 0.23 xEAr_B,R,N,C,W,4,1 - 0.002 xEAr_B,R,N,C,W,4,2
        - 0.002 xEAr_B,R,N,C,W,4,3 + fire_B,R,N,C,W,4,P + fire_B,R,N,C,W,4,D
        + fire_B,R,N,C,W,4,W + fire_B,R,N,C,W,4,C + fire_B,R,N,C,W,4,I
        + fire_B,R,N,C,W,4,A + fire_B,R,N,C,W,4,L + fire_B,R,N,C,W,4,N  = 0
 c1083: - 0.23 xEAr_B,R,N,C,W,5,1 - 0.002 xEAr_B,R,N,C,W,5,2
        - 0.002 xEAr_B,R,N,C,W,5,3 - 0.002 xEAr_B,R,N,C,W,5,4
        + fire_B,R,N,C,W,5,P + fire_B,R,N,C,W,5,D + fire_B,R,N,C,W,5,W
        + fire_B,R,N,C,W,5,C + fire_B,R,N,C,W,5,I + fire_B,R,N,C,W,5,A
        + fire_B,R,N,C,W,5,L + fire_B,R,N,C,W,5,N  = 0
 c1084: fire_B,R,N,C,C,1,P + fire_B,R,N,C,C,1,D + fire_B,R,N,C,C,1,W
        + fire_B,R,N,C,C,1,C + fire_B,R,N,C,C,1,I + fire_B,R,N,C,C,1,A
        + fire_B,R,N,C,C,1,L + fire_B,R,N,C,C,1,N  = 0
 c1085: - 0.002 xEAr_B,R,N,C,C,2,1 + fire_B,R,N,C,C,2,P + fire_B,R,N,C,C,2,D
        + fire_B,R,N,C,C,2,W + fire_B,R,N,C,C,2,C + fire_B,R,N,C,C,2,I
        + fire_B,R,N,C,C,2,A + fire_B,R,N,C,C,2,L + fire_B,R,N,C,C,2,N  = 0
 c1086: - 0.002 xEAr_B,R,N,C,C,3,1 - 0.002 xEAr_B,R,N,C,C,3,2
        + fire_B,R,N,C,C,3,P + fire_B,R,N,C,C,3,D + fire_B,R,N,C,C,3,W
        + fire_B,R,N,C,C,3,C + fire_B,R,N,C,C,3,I + fire_B,R,N,C,C,3,A
        + fire_B,R,N,C,C,3,L + fire_B,R,N,C,C,3,N  = 0
 c1087: - 0.002 xEAr_B,R,N,C,C,4,1 - 0.002 xEAr_B,R,N,C,C,4,2
        - 0.002 xEAr_B,R,N,C,C,4,3 + fire_B,R,N,C,C,4,P + fire_B,R,N,C,C,4,D
        + fire_B,R,N,C,C,4,W + fire_B,R,N,C,C,4,C + fire_B,R,N,C,C,4,I
        + fire_B,R,N,C,C,4,A + fire_B,R,N,C,C,4,L + fire_B,R,N,C,C,4,N  = 0
 c1088: - 0.002 xEAr_B,R,N,C,C,5,1 - 0.002 xEAr_B,R,N,C,C,5,2
        - 0.002 xEAr_B,R,N,C,C,5,3 - 0.002 xEAr_B,R,N,C,C,5,4
        + fire_B,R,N,C,C,5,P + fire_B,R,N,C,C,5,D + fire_B,R,N,C,C,5,W
        + fire_B,R,N,C,C,5,C + fire_B,R,N,C,C,5,I + fire_B,R,N,C,C,5,A
        + fire_B,R,N,C,C,5,L + fire_B,R,N,C,C,5,N  = 0
 c1089: fire_B,R,N,C,I,1,P + fire_B,R,N,C,I,1,D + fire_B,R,N,C,I,1,W
        + fire_B,R,N,C,I,1,C + fire_B,R,N,C,I,1,I + fire_B,R,N,C,I,1,A
        + fire_B,R,N,C,I,1,L + fire_B,R,N,C,I,1,N  = 0
 c1090: - 0.002 xEAr_B,R,N,C,I,2,1 + fire_B,R,N,C,I,2,P + fire_B,R,N,C,I,2,D
        + fire_B,R,N,C,I,2,W + fire_B,R,N,C,I,2,C + fire_B,R,N,C,I,2,I
        + fire_B,R,N,C,I,2,A + fire_B,R,N,C,I,2,L + fire_B,R,N,C,I,2,N  = 0
 c1091: - 0.002 xEAr_B,R,N,C,I,3,1 - 0.002 xEAr_B,R,N,C,I,3,2
        + fire_B,R,N,C,I,3,P + fire_B,R,N,C,I,3,D + fire_B,R,N,C,I,3,W
        + fire_B,R,N,C,I,3,C + fire_B,R,N,C,I,3,I + fire_B,R,N,C,I,3,A
        + fire_B,R,N,C,I,3,L + fire_B,R,N,C,I,3,N  = 0
 c1092: - 0.002 xEAr_B,R,N,C,I,4,1 - 0.002 xEAr_B,R,N,C,I,4,2
        - 0.002 xEAr_B,R,N,C,I,4,3 + fire_B,R,N,C,I,4,P + fire_B,R,N,C,I,4,D
        + fire_B,R,N,C,I,4,W + fire_B,R,N,C,I,4,C + fire_B,R,N,C,I,4,I
        + fire_B,R,N,C,I,4,A + fire_B,R,N,C,I,4,L + fire_B,R,N,C,I,4,N  = 0
 c1093: - 0.002 xEAr_B,R,N,C,I,5,1 - 0.002 xEAr_B,R,N,C,I,5,2
        - 0.002 xEAr_B,R,N,C,I,5,3 - 0.002 xEAr_B,R,N,C,I,5,4
        + fire_B,R,N,C,I,5,P + fire_B,R,N,C,I,5,D + fire_B,R,N,C,I,5,W
        + fire_B,R,N,C,I,5,C + fire_B,R,N,C,I,5,I + fire_B,R,N,C,I,5,A
        + fire_B,R,N,C,I,5,L + fire_B,R,N,C,I,5,N  = 0
 c1094: - 0.002 xNG_B,R,N,C,A,L,1 - 0.002 xPB_B,R,N,C,A,L,0,1
        - 0.002 xPB_B,R,N,C,A,L,1,1 - 0.002 xPB_B,R,N,C,A,L,2,1
        - 0.002 xPB_B,R,N,C,A,L,3,1 - 0.002 xPB_B,R,N,C,A,L,4,1
        - 0.002 xGS_B,R,N,C,A,L,0,1 - 0.002 xGS_B,R,N,C,A,L,1,1
        - 0.002 xGS_B,R,N,C,A,L,2,1 - 0.002 xGS_B,R,N,C,A,L,3,1
        - 0.002 xGS_B,R,N,C,A,L,4,1 - 0.002 xEAe_B,R,N,C,A,L,1
        + fire_B,R,N,C,A,1,P + fire_B,R,N,C,A,1,D + fire_B,R,N,C,A,1,W
        + fire_B,R,N,C,A,1,C + fire_B,R,N,C,A,1,I + fire_B,R,N,C,A,1,A
        + fire_B,R,N,C,A,1,L + fire_B,R,N,C,A,1,N  = 0
 c1095: - 0.002 xNG_B,R,N,C,A,L,2 - 0.002 xPB_B,R,N,C,A,L,0,2
        - 0.002 xPB_B,R,N,C,A,L,1,2 - 0.002 xPB_B,R,N,C,A,L,2,2
        - 0.002 xPB_B,R,N,C,A,L,3,2 - 0.002 xPB_B,R,N,C,A,L,4,2
        - 0.002 xGS_B,R,N,C,A,L,0,2 - 0.002 xGS_B,R,N,C,A,L,1,2
        - 0.002 xGS_B,R,N,C,A,L,2,2 - 0.002 xGS_B,R,N,C,A,L,3,2
        - 0.002 xGS_B,R,N,C,A,L,4,2 - 0.002 xEAe_B,R,N,C,A,L,2
        - 0.002 xEAr_B,R,N,C,A,2,1 + fire_B,R,N,C,A,2,P + fire_B,R,N,C,A,2,D
        + fire_B,R,N,C,A,2,W + fire_B,R,N,C,A,2,C + fire_B,R,N,C,A,2,I
        + fire_B,R,N,C,A,2,A + fire_B,R,N,C,A,2,L + fire_B,R,N,C,A,2,N  = 0
 c1096: - 0.002 xNG_B,R,N,C,A,L,3 - 0.002 xPB_B,R,N,C,A,L,0,3
        - 0.002 xPB_B,R,N,C,A,L,1,3 - 0.002 xPB_B,R,N,C,A,L,2,3
        - 0.002 xPB_B,R,N,C,A,L,3,3 - 0.002 xPB_B,R,N,C,A,L,4,3
        - 0.002 xGS_B,R,N,C,A,L,0,3 - 0.002 xGS_B,R,N,C,A,L,1,3
        - 0.002 xGS_B,R,N,C,A,L,2,3 - 0.002 xGS_B,R,N,C,A,L,3,3
        - 0.002 xGS_B,R,N,C,A,L,4,3 - 0.002 xEAe_B,R,N,C,A,L,3
        - 0.002 xEAr_B,R,N,C,A,3,1 - 0.002 xEAr_B,R,N,C,A,3,2
        + fire_B,R,N,C,A,3,P + fire_B,R,N,C,A,3,D + fire_B,R,N,C,A,3,W
        + fire_B,R,N,C,A,3,C + fire_B,R,N,C,A,3,I + fire_B,R,N,C,A,3,A
        + fire_B,R,N,C,A,3,L + fire_B,R,N,C,A,3,N  = 0
 c1097: - 0.002 xNG_B,R,N,C,A,L,4 - 0.002 xPB_B,R,N,C,A,L,0,4
        - 0.002 xPB_B,R,N,C,A,L,1,4 - 0.002 xPB_B,R,N,C,A,L,2,4
        - 0.002 xPB_B,R,N,C,A,L,3,4 - 0.002 xPB_B,R,N,C,A,L,4,4
        - 0.002 xGS_B,R,N,C,A,L,0,4 - 0.002 xGS_B,R,N,C,A,L,1,4
        - 0.002 xGS_B,R,N,C,A,L,2,4 - 0.002 xGS_B,R,N,C,A,L,3,4
        - 0.002 xGS_B,R,N,C,A,L,4,4 - 0.002 xEAe_B,R,N,C,A,L,4
        - 0.002 xEAr_B,R,N,C,A,4,1 - 0.002 xEAr_B,R,N,C,A,4,2
        - 0.002 xEAr_B,R,N,C,A,4,3 + fire_B,R,N,C,A,4,P + fire_B,R,N,C,A,4,D
        + fire_B,R,N,C,A,4,W + fire_B,R,N,C,A,4,C + fire_B,R,N,C,A,4,I
        + fire_B,R,N,C,A,4,A + fire_B,R,N,C,A,4,L + fire_B,R,N,C,A,4,N  = 0
 c1098: - 0.002 xNG_B,R,N,C,A,L,5 - 0.002 xPB_B,R,N,C,A,L,0,5
        - 0.002 xPB_B,R,N,C,A,L,1,5 - 0.002 xPB_B,R,N,C,A,L,2,5
        - 0.002 xPB_B,R,N,C,A,L,3,5 - 0.002 xPB_B,R,N,C,A,L,4,5
        - 0.002 xGS_B,R,N,C,A,L,0,5 - 0.002 xGS_B,R,N,C,A,L,1,5
        - 0.002 xGS_B,R,N,C,A,L,2,5 - 0.002 xGS_B,R,N,C,A,L,3,5
        - 0.002 xGS_B,R,N,C,A,L,4,5 - 0.002 xEAe_B,R,N,C,A,L,5
        - 0.002 xEAr_B,R,N,C,A,5,1 - 0.002 xEAr_B,R,N,C,A,5,2
        - 0.002 xEAr_B,R,N,C,A,5,3 - 0.002 xEAr_B,R,N,C,A,5,4
        + fire_B,R,N,C,A,5,P + fire_B,R,N,C,A,5,D + fire_B,R,N,C,A,5,W
        + fire_B,R,N,C,A,5,C + fire_B,R,N,C,A,5,I + fire_B,R,N,C,A,5,A
        + fire_B,R,N,C,A,5,L + fire_B,R,N,C,A,5,N  = 0
 c1099: fire_B,R,N,C,L,1,P + fire_B,R,N,C,L,1,D + fire_B,R,N,C,L,1,W
        + fire_B,R,N,C,L,1,C + fire_B,R,N,C,L,1,I + fire_B,R,N,C,L,1,A
        + fire_B,R,N,C,L,1,L + fire_B,R,N,C,L,1,N  = 0
 c1100: - 0.002 xEAr_B,R,N,C,L,2,1 + fire_B,R,N,C,L,2,P + fire_B,R,N,C,L,2,D
        + fire_B,R,N,C,L,2,W + fire_B,R,N,C,L,2,C + fire_B,R,N,C,L,2,I
        + fire_B,R,N,C,L,2,A + fire_B,R,N,C,L,2,L + fire_B,R,N,C,L,2,N  = 0
 c1101: - 0.002 xEAr_B,R,N,C,L,3,1 - 0.002 xEAr_B,R,N,C,L,3,2
        + fire_B,R,N,C,L,3,P + fire_B,R,N,C,L,3,D + fire_B,R,N,C,L,3,W
        + fire_B,R,N,C,L,3,C + fire_B,R,N,C,L,3,I + fire_B,R,N,C,L,3,A
        + fire_B,R,N,C,L,3,L + fire_B,R,N,C,L,3,N  = 0
 c1102: - 0.002 xEAr_B,R,N,C,L,4,1 - 0.002 xEAr_B,R,N,C,L,4,2
        - 0.002 xEAr_B,R,N,C,L,4,3 + fire_B,R,N,C,L,4,P + fire_B,R,N,C,L,4,D
        + fire_B,R,N,C,L,4,W + fire_B,R,N,C,L,4,C + fire_B,R,N,C,L,4,I
        + fire_B,R,N,C,L,4,A + fire_B,R,N,C,L,4,L + fire_B,R,N,C,L,4,N  = 0
 c1103: - 0.002 xEAr_B,R,N,C,L,5,1 - 0.002 xEAr_B,R,N,C,L,5,2
        - 0.002 xEAr_B,R,N,C,L,5,3 - 0.002 xEAr_B,R,N,C,L,5,4
        + fire_B,R,N,C,L,5,P + fire_B,R,N,C,L,5,D + fire_B,R,N,C,L,5,W
        + fire_B,R,N,C,L,5,C + fire_B,R,N,C,L,5,I + fire_B,R,N,C,L,5,A
        + fire_B,R,N,C,L,5,L + fire_B,R,N,C,L,5,N  = 0
 c1104: fire_B,R,N,C,N,1,P + fire_B,R,N,C,N,1,D + fire_B,R,N,C,N,1,W
        + fire_B,R,N,C,N,1,C + fire_B,R,N,C,N,1,I + fire_B,R,N,C,N,1,A
        + fire_B,R,N,C,N,1,L + fire_B,R,N,C,N,1,N  = 0
 c1105: fire_B,R,N,C,N,2,P + fire_B,R,N,C,N,2,D + fire_B,R,N,C,N,2,W
        + fire_B,R,N,C,N,2,C + fire_B,R,N,C,N,2,I + fire_B,R,N,C,N,2,A
        + fire_B,R,N,C,N,2,L + fire_B,R,N,C,N,2,N  = 0
 c1106: fire_B,R,N,C,N,3,P + fire_B,R,N,C,N,3,D + fire_B,R,N,C,N,3,W
        + fire_B,R,N,C,N,3,C + fire_B,R,N,C,N,3,I + fire_B,R,N,C,N,3,A
        + fire_B,R,N,C,N,3,L + fire_B,R,N,C,N,3,N  = 0
 c1107: fire_B,R,N,C,N,4,P + fire_B,R,N,C,N,4,D + fire_B,R,N,C,N,4,W
        + fire_B,R,N,C,N,4,C + fire_B,R,N,C,N,4,I + fire_B,R,N,C,N,4,A
        + fire_B,R,N,C,N,4,L + fire_B,R,N,C,N,4,N  = 0
 c1108: fire_B,R,N,C,N,5,P + fire_B,R,N,C,N,5,D + fire_B,R,N,C,N,5,W
        + fire_B,R,N,C,N,5,C + fire_B,R,N,C,N,5,I + fire_B,R,N,C,N,5,A
        + fire_B,R,N,C,N,5,L + fire_B,R,N,C,N,5,N  = 0
 c1109: fire_B,R,N,R,P,1,P + fire_B,R,N,R,P,1,D + fire_B,R,N,R,P,1,W
        + fire_B,R,N,R,P,1,C + fire_B,R,N,R,P,1,I + fire_B,R,N,R,P,1,A
        + fire_B,R,N,R,P,1,L + fire_B,R,N,R,P,1,N  = 0
 c1110: - 0.002 xEAr_B,R,N,R,P,2,1 + fire_B,R,N,R,P,2,P + fire_B,R,N,R,P,2,D
        + fire_B,R,N,R,P,2,W + fire_B,R,N,R,P,2,C + fire_B,R,N,R,P,2,I
        + fire_B,R,N,R,P,2,A + fire_B,R,N,R,P,2,L + fire_B,R,N,R,P,2,N  = 0
 c1111: - 0.002 xEAr_B,R,N,R,P,3,1 - 0.002 xEAr_B,R,N,R,P,3,2
        + fire_B,R,N,R,P,3,P + fire_B,R,N,R,P,3,D + fire_B,R,N,R,P,3,W
        + fire_B,R,N,R,P,3,C + fire_B,R,N,R,P,3,I + fire_B,R,N,R,P,3,A
        + fire_B,R,N,R,P,3,L + fire_B,R,N,R,P,3,N  = 0
 c1112: - 0.002 xEAr_B,R,N,R,P,4,1 - 0.002 xEAr_B,R,N,R,P,4,2
        - 0.002 xEAr_B,R,N,R,P,4,3 + fire_B,R,N,R,P,4,P + fire_B,R,N,R,P,4,D
        + fire_B,R,N,R,P,4,W + fire_B,R,N,R,P,4,C + fire_B,R,N,R,P,4,I
        + fire_B,R,N,R,P,4,A + fire_B,R,N,R,P,4,L + fire_B,R,N,R,P,4,N  = 0
 c1113: - 0.002 xEAr_B,R,N,R,P,5,1 - 0.002 xEAr_B,R,N,R,P,5,2
        - 0.002 xEAr_B,R,N,R,P,5,3 - 0.002 xEAr_B,R,N,R,P,5,4
        + fire_B,R,N,R,P,5,P + fire_B,R,N,R,P,5,D + fire_B,R,N,R,P,5,W
        + fire_B,R,N,R,P,5,C + fire_B,R,N,R,P,5,I + fire_B,R,N,R,P,5,A
        + fire_B,R,N,R,P,5,L + fire_B,R,N,R,P,5,N  = 0
 c1114: fire_B,R,N,R,D,1,P + fire_B,R,N,R,D,1,D + fire_B,R,N,R,D,1,W
        + fire_B,R,N,R,D,1,C + fire_B,R,N,R,D,1,I + fire_B,R,N,R,D,1,A
        + fire_B,R,N,R,D,1,L + fire_B,R,N,R,D,1,N  = 0
 c1115: - 0.002 xEAr_B,R,N,R,D,2,1 + fire_B,R,N,R,D,2,P + fire_B,R,N,R,D,2,D
        + fire_B,R,N,R,D,2,W + fire_B,R,N,R,D,2,C + fire_B,R,N,R,D,2,I
        + fire_B,R,N,R,D,2,A + fire_B,R,N,R,D,2,L + fire_B,R,N,R,D,2,N  = 0
 c1116: - 0.002 xEAr_B,R,N,R,D,3,1 - 0.002 xEAr_B,R,N,R,D,3,2
        + fire_B,R,N,R,D,3,P + fire_B,R,N,R,D,3,D + fire_B,R,N,R,D,3,W
        + fire_B,R,N,R,D,3,C + fire_B,R,N,R,D,3,I + fire_B,R,N,R,D,3,A
        + fire_B,R,N,R,D,3,L + fire_B,R,N,R,D,3,N  = 0
 c1117: - 0.002 xEAr_B,R,N,R,D,4,1 - 0.002 xEAr_B,R,N,R,D,4,2
        - 0.002 xEAr_B,R,N,R,D,4,3 + fire_B,R,N,R,D,4,P + fire_B,R,N,R,D,4,D
        + fire_B,R,N,R,D,4,W + fire_B,R,N,R,D,4,C + fire_B,R,N,R,D,4,I
        + fire_B,R,N,R,D,4,A + fire_B,R,N,R,D,4,L + fire_B,R,N,R,D,4,N  = 0
 c1118: - 0.002 xEAr_B,R,N,R,D,5,1 - 0.002 xEAr_B,R,N,R,D,5,2
        - 0.002 xEAr_B,R,N,R,D,5,3 - 0.002 xEAr_B,R,N,R,D,5,4
        + fire_B,R,N,R,D,5,P + fire_B,R,N,R,D,5,D + fire_B,R,N,R,D,5,W
        + fire_B,R,N,R,D,5,C + fire_B,R,N,R,D,5,I + fire_B,R,N,R,D,5,A
        + fire_B,R,N,R,D,5,L + fire_B,R,N,R,D,5,N  = 0
 c1119: fire_B,R,N,R,W,1,P + fire_B,R,N,R,W,1,D + fire_B,R,N,R,W,1,W
        + fire_B,R,N,R,W,1,C + fire_B,R,N,R,W,1,I + fire_B,R,N,R,W,1,A
        + fire_B,R,N,R,W,1,L + fire_B,R,N,R,W,1,N  = 0
 c1120: - 0.23 xEAr_B,R,N,R,W,2,1 + fire_B,R,N,R,W,2,P + fire_B,R,N,R,W,2,D
        + fire_B,R,N,R,W,2,W + fire_B,R,N,R,W,2,C + fire_B,R,N,R,W,2,I
        + fire_B,R,N,R,W,2,A + fire_B,R,N,R,W,2,L + fire_B,R,N,R,W,2,N  = 0
 c1121: - 0.23 xEAr_B,R,N,R,W,3,1 - 0.002 xEAr_B,R,N,R,W,3,2
        + fire_B,R,N,R,W,3,P + fire_B,R,N,R,W,3,D + fire_B,R,N,R,W,3,W
        + fire_B,R,N,R,W,3,C + fire_B,R,N,R,W,3,I + fire_B,R,N,R,W,3,A
        + fire_B,R,N,R,W,3,L + fire_B,R,N,R,W,3,N  = 0
 c1122: - 0.23 xEAr_B,R,N,R,W,4,1 - 0.002 xEAr_B,R,N,R,W,4,2
        - 0.002 xEAr_B,R,N,R,W,4,3 + fire_B,R,N,R,W,4,P + fire_B,R,N,R,W,4,D
        + fire_B,R,N,R,W,4,W + fire_B,R,N,R,W,4,C + fire_B,R,N,R,W,4,I
        + fire_B,R,N,R,W,4,A + fire_B,R,N,R,W,4,L + fire_B,R,N,R,W,4,N  = 0
 c1123: - 0.23 xEAr_B,R,N,R,W,5,1 - 0.002 xEAr_B,R,N,R,W,5,2
        - 0.002 xEAr_B,R,N,R,W,5,3 - 0.002 xEAr_B,R,N,R,W,5,4
        + fire_B,R,N,R,W,5,P + fire_B,R,N,R,W,5,D + fire_B,R,N,R,W,5,W
        + fire_B,R,N,R,W,5,C + fire_B,R,N,R,W,5,I + fire_B,R,N,R,W,5,A
        + fire_B,R,N,R,W,5,L + fire_B,R,N,R,W,5,N  = 0
 c1124: fire_B,R,N,R,C,1,P + fire_B,R,N,R,C,1,D + fire_B,R,N,R,C,1,W
        + fire_B,R,N,R,C,1,C + fire_B,R,N,R,C,1,I + fire_B,R,N,R,C,1,A
        + fire_B,R,N,R,C,1,L + fire_B,R,N,R,C,1,N  = 0
 c1125: - 0.002 xEAr_B,R,N,R,C,2,1 + fire_B,R,N,R,C,2,P + fire_B,R,N,R,C,2,D
        + fire_B,R,N,R,C,2,W + fire_B,R,N,R,C,2,C + fire_B,R,N,R,C,2,I
        + fire_B,R,N,R,C,2,A + fire_B,R,N,R,C,2,L + fire_B,R,N,R,C,2,N  = 0
 c1126: - 0.002 xEAr_B,R,N,R,C,3,1 - 0.002 xEAr_B,R,N,R,C,3,2
        + fire_B,R,N,R,C,3,P + fire_B,R,N,R,C,3,D + fire_B,R,N,R,C,3,W
        + fire_B,R,N,R,C,3,C + fire_B,R,N,R,C,3,I + fire_B,R,N,R,C,3,A
        + fire_B,R,N,R,C,3,L + fire_B,R,N,R,C,3,N  = 0
 c1127: - 0.002 xEAr_B,R,N,R,C,4,1 - 0.002 xEAr_B,R,N,R,C,4,2
        - 0.002 xEAr_B,R,N,R,C,4,3 + fire_B,R,N,R,C,4,P + fire_B,R,N,R,C,4,D
        + fire_B,R,N,R,C,4,W + fire_B,R,N,R,C,4,C + fire_B,R,N,R,C,4,I
        + fire_B,R,N,R,C,4,A + fire_B,R,N,R,C,4,L + fire_B,R,N,R,C,4,N  = 0
 c1128: - 0.002 xEAr_B,R,N,R,C,5,1 - 0.002 xEAr_B,R,N,R,C,5,2
        - 0.002 xEAr_B,R,N,R,C,5,3 - 0.002 xEAr_B,R,N,R,C,5,4
        + fire_B,R,N,R,C,5,P + fire_B,R,N,R,C,5,D + fire_B,R,N,R,C,5,W
        + fire_B,R,N,R,C,5,C + fire_B,R,N,R,C,5,I + fire_B,R,N,R,C,5,A
        + fire_B,R,N,R,C,5,L + fire_B,R,N,R,C,5,N  = 0
 c1129: fire_B,R,N,R,I,1,P + fire_B,R,N,R,I,1,D + fire_B,R,N,R,I,1,W
        + fire_B,R,N,R,I,1,C + fire_B,R,N,R,I,1,I + fire_B,R,N,R,I,1,A
        + fire_B,R,N,R,I,1,L + fire_B,R,N,R,I,1,N  = 0
 c1130: - 0.002 xEAr_B,R,N,R,I,2,1 + fire_B,R,N,R,I,2,P + fire_B,R,N,R,I,2,D
        + fire_B,R,N,R,I,2,W + fire_B,R,N,R,I,2,C + fire_B,R,N,R,I,2,I
        + fire_B,R,N,R,I,2,A + fire_B,R,N,R,I,2,L + fire_B,R,N,R,I,2,N  = 0
 c1131: - 0.002 xEAr_B,R,N,R,I,3,1 - 0.002 xEAr_B,R,N,R,I,3,2
        + fire_B,R,N,R,I,3,P + fire_B,R,N,R,I,3,D + fire_B,R,N,R,I,3,W
        + fire_B,R,N,R,I,3,C + fire_B,R,N,R,I,3,I + fire_B,R,N,R,I,3,A
        + fire_B,R,N,R,I,3,L + fire_B,R,N,R,I,3,N  = 0
 c1132: - 0.002 xEAr_B,R,N,R,I,4,1 - 0.002 xEAr_B,R,N,R,I,4,2
        - 0.002 xEAr_B,R,N,R,I,4,3 + fire_B,R,N,R,I,4,P + fire_B,R,N,R,I,4,D
        + fire_B,R,N,R,I,4,W + fire_B,R,N,R,I,4,C + fire_B,R,N,R,I,4,I
        + fire_B,R,N,R,I,4,A + fire_B,R,N,R,I,4,L + fire_B,R,N,R,I,4,N  = 0
 c1133: - 0.002 xEAr_B,R,N,R,I,5,1 - 0.002 xEAr_B,R,N,R,I,5,2
        - 0.002 xEAr_B,R,N,R,I,5,3 - 0.002 xEAr_B,R,N,R,I,5,4
        + fire_B,R,N,R,I,5,P + fire_B,R,N,R,I,5,D + fire_B,R,N,R,I,5,W
        + fire_B,R,N,R,I,5,C + fire_B,R,N,R,I,5,I + fire_B,R,N,R,I,5,A
        + fire_B,R,N,R,I,5,L + fire_B,R,N,R,I,5,N  = 0
 c1134: - 0.002 xNG_B,R,N,R,A,L,1 - 0.002 xPB_B,R,N,R,A,L,0,1
        - 0.002 xPB_B,R,N,R,A,L,1,1 - 0.002 xPB_B,R,N,R,A,L,2,1
        - 0.002 xPB_B,R,N,R,A,L,3,1 - 0.002 xPB_B,R,N,R,A,L,4,1
        - 0.002 xGS_B,R,N,R,A,L,0,1 - 0.002 xGS_B,R,N,R,A,L,1,1
        - 0.002 xGS_B,R,N,R,A,L,2,1 - 0.002 xGS_B,R,N,R,A,L,3,1
        - 0.002 xGS_B,R,N,R,A,L,4,1 - 0.002 xEAe_B,R,N,R,A,L,1
        + fire_B,R,N,R,A,1,P + fire_B,R,N,R,A,1,D + fire_B,R,N,R,A,1,W
        + fire_B,R,N,R,A,1,C + fire_B,R,N,R,A,1,I + fire_B,R,N,R,A,1,A
        + fire_B,R,N,R,A,1,L + fire_B,R,N,R,A,1,N  = 0
 c1135: - 0.002 xNG_B,R,N,R,A,L,2 - 0.002 xPB_B,R,N,R,A,L,0,2
        - 0.002 xPB_B,R,N,R,A,L,1,2 - 0.002 xPB_B,R,N,R,A,L,2,2
        - 0.002 xPB_B,R,N,R,A,L,3,2 - 0.002 xPB_B,R,N,R,A,L,4,2
        - 0.002 xGS_B,R,N,R,A,L,0,2 - 0.002 xGS_B,R,N,R,A,L,1,2
        - 0.002 xGS_B,R,N,R,A,L,2,2 - 0.002 xGS_B,R,N,R,A,L,3,2
        - 0.002 xGS_B,R,N,R,A,L,4,2 - 0.002 xEAe_B,R,N,R,A,L,2
        - 0.002 xEAr_B,R,N,R,A,2,1 + fire_B,R,N,R,A,2,P + fire_B,R,N,R,A,2,D
        + fire_B,R,N,R,A,2,W + fire_B,R,N,R,A,2,C + fire_B,R,N,R,A,2,I
        + fire_B,R,N,R,A,2,A + fire_B,R,N,R,A,2,L + fire_B,R,N,R,A,2,N  = 0
 c1136: - 0.002 xNG_B,R,N,R,A,L,3 - 0.002 xPB_B,R,N,R,A,L,0,3
        - 0.002 xPB_B,R,N,R,A,L,1,3 - 0.002 xPB_B,R,N,R,A,L,2,3
        - 0.002 xPB_B,R,N,R,A,L,3,3 - 0.002 xPB_B,R,N,R,A,L,4,3
        - 0.002 xGS_B,R,N,R,A,L,0,3 - 0.002 xGS_B,R,N,R,A,L,1,3
        - 0.002 xGS_B,R,N,R,A,L,2,3 - 0.002 xGS_B,R,N,R,A,L,3,3
        - 0.002 xGS_B,R,N,R,A,L,4,3 - 0.002 xEAe_B,R,N,R,A,L,3
        - 0.002 xEAr_B,R,N,R,A,3,1 - 0.002 xEAr_B,R,N,R,A,3,2
        + fire_B,R,N,R,A,3,P + fire_B,R,N,R,A,3,D + fire_B,R,N,R,A,3,W
        + fire_B,R,N,R,A,3,C + fire_B,R,N,R,A,3,I + fire_B,R,N,R,A,3,A
        + fire_B,R,N,R,A,3,L + fire_B,R,N,R,A,3,N  = 0
 c1137: - 0.002 xNG_B,R,N,R,A,L,4 - 0.002 xPB_B,R,N,R,A,L,0,4
        - 0.002 xPB_B,R,N,R,A,L,1,4 - 0.002 xPB_B,R,N,R,A,L,2,4
        - 0.002 xPB_B,R,N,R,A,L,3,4 - 0.002 xPB_B,R,N,R,A,L,4,4
        - 0.002 xGS_B,R,N,R,A,L,0,4 - 0.002 xGS_B,R,N,R,A,L,1,4
        - 0.002 xGS_B,R,N,R,A,L,2,4 - 0.002 xGS_B,R,N,R,A,L,3,4
        - 0.002 xGS_B,R,N,R,A,L,4,4 - 0.002 xEAe_B,R,N,R,A,L,4
        - 0.002 xEAr_B,R,N,R,A,4,1 - 0.002 xEAr_B,R,N,R,A,4,2
        - 0.002 xEAr_B,R,N,R,A,4,3 + fire_B,R,N,R,A,4,P + fire_B,R,N,R,A,4,D
        + fire_B,R,N,R,A,4,W + fire_B,R,N,R,A,4,C + fire_B,R,N,R,A,4,I
        + fire_B,R,N,R,A,4,A + fire_B,R,N,R,A,4,L + fire_B,R,N,R,A,4,N  = 0
 c1138: - 0.002 xNG_B,R,N,R,A,L,5 - 0.002 xPB_B,R,N,R,A,L,0,5
        - 0.002 xPB_B,R,N,R,A,L,1,5 - 0.002 xPB_B,R,N,R,A,L,2,5
        - 0.002 xPB_B,R,N,R,A,L,3,5 - 0.002 xPB_B,R,N,R,A,L,4,5
        - 0.002 xGS_B,R,N,R,A,L,0,5 - 0.002 xGS_B,R,N,R,A,L,1,5
        - 0.002 xGS_B,R,N,R,A,L,2,5 - 0.002 xGS_B,R,N,R,A,L,3,5
        - 0.002 xGS_B,R,N,R,A,L,4,5 - 0.002 xEAe_B,R,N,R,A,L,5
        - 0.002 xEAr_B,R,N,R,A,5,1 - 0.002 xEAr_B,R,N,R,A,5,2
        - 0.002 xEAr_B,R,N,R,A,5,3 - 0.002 xEAr_B,R,N,R,A,5,4
        + fire_B,R,N,R,A,5,P + fire_B,R,N,R,A,5,D + fire_B,R,N,R,A,5,W
        + fire_B,R,N,R,A,5,C + fire_B,R,N,R,A,5,I + fire_B,R,N,R,A,5,A
        + fire_B,R,N,R,A,5,L + fire_B,R,N,R,A,5,N  = 0
 c1139: fire_B,R,N,R,L,1,P + fire_B,R,N,R,L,1,D + fire_B,R,N,R,L,1,W
        + fire_B,R,N,R,L,1,C + fire_B,R,N,R,L,1,I + fire_B,R,N,R,L,1,A
        + fire_B,R,N,R,L,1,L + fire_B,R,N,R,L,1,N  = 0
 c1140: - 0.002 xEAr_B,R,N,R,L,2,1 + fire_B,R,N,R,L,2,P + fire_B,R,N,R,L,2,D
        + fire_B,R,N,R,L,2,W + fire_B,R,N,R,L,2,C + fire_B,R,N,R,L,2,I
        + fire_B,R,N,R,L,2,A + fire_B,R,N,R,L,2,L + fire_B,R,N,R,L,2,N  = 0
 c1141: - 0.002 xEAr_B,R,N,R,L,3,1 - 0.002 xEAr_B,R,N,R,L,3,2
        + fire_B,R,N,R,L,3,P + fire_B,R,N,R,L,3,D + fire_B,R,N,R,L,3,W
        + fire_B,R,N,R,L,3,C + fire_B,R,N,R,L,3,I + fire_B,R,N,R,L,3,A
        + fire_B,R,N,R,L,3,L + fire_B,R,N,R,L,3,N  = 0
 c1142: - 0.002 xEAr_B,R,N,R,L,4,1 - 0.002 xEAr_B,R,N,R,L,4,2
        - 0.002 xEAr_B,R,N,R,L,4,3 + fire_B,R,N,R,L,4,P + fire_B,R,N,R,L,4,D
        + fire_B,R,N,R,L,4,W + fire_B,R,N,R,L,4,C + fire_B,R,N,R,L,4,I
        + fire_B,R,N,R,L,4,A + fire_B,R,N,R,L,4,L + fire_B,R,N,R,L,4,N  = 0
 c1143: - 0.002 xEAr_B,R,N,R,L,5,1 - 0.002 xEAr_B,R,N,R,L,5,2
        - 0.002 xEAr_B,R,N,R,L,5,3 - 0.002 xEAr_B,R,N,R,L,5,4
        + fire_B,R,N,R,L,5,P + fire_B,R,N,R,L,5,D + fire_B,R,N,R,L,5,W
        + fire_B,R,N,R,L,5,C + fire_B,R,N,R,L,5,I + fire_B,R,N,R,L,5,A
        + fire_B,R,N,R,L,5,L + fire_B,R,N,R,L,5,N  = 0
 c1144: fire_B,R,N,R,N,1,P + fire_B,R,N,R,N,1,D + fire_B,R,N,R,N,1,W
        + fire_B,R,N,R,N,1,C + fire_B,R,N,R,N,1,I + fire_B,R,N,R,N,1,A
        + fire_B,R,N,R,N,1,L + fire_B,R,N,R,N,1,N  = 0
 c1145: fire_B,R,N,R,N,2,P + fire_B,R,N,R,N,2,D + fire_B,R,N,R,N,2,W
        + fire_B,R,N,R,N,2,C + fire_B,R,N,R,N,2,I + fire_B,R,N,R,N,2,A
        + fire_B,R,N,R,N,2,L + fire_B,R,N,R,N,2,N  = 0
 c1146: fire_B,R,N,R,N,3,P + fire_B,R,N,R,N,3,D + fire_B,R,N,R,N,3,W
        + fire_B,R,N,R,N,3,C + fire_B,R,N,R,N,3,I + fire_B,R,N,R,N,3,A
        + fire_B,R,N,R,N,3,L + fire_B,R,N,R,N,3,N  = 0
 c1147: fire_B,R,N,R,N,4,P + fire_B,R,N,R,N,4,D + fire_B,R,N,R,N,4,W
        + fire_B,R,N,R,N,4,C + fire_B,R,N,R,N,4,I + fire_B,R,N,R,N,4,A
        + fire_B,R,N,R,N,4,L + fire_B,R,N,R,N,4,N  = 0
 c1148: fire_B,R,N,R,N,5,P + fire_B,R,N,R,N,5,D + fire_B,R,N,R,N,5,W
        + fire_B,R,N,R,N,5,C + fire_B,R,N,R,N,5,I + fire_B,R,N,R,N,5,A
        + fire_B,R,N,R,N,5,L + fire_B,R,N,R,N,5,N  = 0
 c1149: fire_B,R,P,L,P,1,P + fire_B,R,P,L,P,1,D + fire_B,R,P,L,P,1,W
        + fire_B,R,P,L,P,1,C + fire_B,R,P,L,P,1,I + fire_B,R,P,L,P,1,A
        + fire_B,R,P,L,P,1,L + fire_B,R,P,L,P,1,N  = 0
 c1150: - 0.002 xEAr_B,R,P,L,P,2,1 + fire_B,R,P,L,P,2,P + fire_B,R,P,L,P,2,D
        + fire_B,R,P,L,P,2,W + fire_B,R,P,L,P,2,C + fire_B,R,P,L,P,2,I
        + fire_B,R,P,L,P,2,A + fire_B,R,P,L,P,2,L + fire_B,R,P,L,P,2,N  = 0
 c1151: - 0.002 xEAr_B,R,P,L,P,3,1 - 0.002 xEAr_B,R,P,L,P,3,2
        + fire_B,R,P,L,P,3,P + fire_B,R,P,L,P,3,D + fire_B,R,P,L,P,3,W
        + fire_B,R,P,L,P,3,C + fire_B,R,P,L,P,3,I + fire_B,R,P,L,P,3,A
        + fire_B,R,P,L,P,3,L + fire_B,R,P,L,P,3,N  = 0
 c1152: - 0.002 xEAr_B,R,P,L,P,4,1 - 0.002 xEAr_B,R,P,L,P,4,2
        - 0.002 xEAr_B,R,P,L,P,4,3 + fire_B,R,P,L,P,4,P + fire_B,R,P,L,P,4,D
        + fire_B,R,P,L,P,4,W + fire_B,R,P,L,P,4,C + fire_B,R,P,L,P,4,I
        + fire_B,R,P,L,P,4,A + fire_B,R,P,L,P,4,L + fire_B,R,P,L,P,4,N  = 0
 c1153: - 0.002 xEAr_B,R,P,L,P,5,1 - 0.002 xEAr_B,R,P,L,P,5,2
        - 0.002 xEAr_B,R,P,L,P,5,3 - 0.002 xEAr_B,R,P,L,P,5,4
        + fire_B,R,P,L,P,5,P + fire_B,R,P,L,P,5,D + fire_B,R,P,L,P,5,W
        + fire_B,R,P,L,P,5,C + fire_B,R,P,L,P,5,I + fire_B,R,P,L,P,5,A
        + fire_B,R,P,L,P,5,L + fire_B,R,P,L,P,5,N  = 0
 c1154: fire_B,R,P,L,D,1,P + fire_B,R,P,L,D,1,D + fire_B,R,P,L,D,1,W
        + fire_B,R,P,L,D,1,C + fire_B,R,P,L,D,1,I + fire_B,R,P,L,D,1,A
        + fire_B,R,P,L,D,1,L + fire_B,R,P,L,D,1,N  = 0
 c1155: - 0.002 xEAr_B,R,P,L,D,2,1 + fire_B,R,P,L,D,2,P + fire_B,R,P,L,D,2,D
        + fire_B,R,P,L,D,2,W + fire_B,R,P,L,D,2,C + fire_B,R,P,L,D,2,I
        + fire_B,R,P,L,D,2,A + fire_B,R,P,L,D,2,L + fire_B,R,P,L,D,2,N  = 0
 c1156: - 0.002 xEAr_B,R,P,L,D,3,1 - 0.002 xEAr_B,R,P,L,D,3,2
        + fire_B,R,P,L,D,3,P + fire_B,R,P,L,D,3,D + fire_B,R,P,L,D,3,W
        + fire_B,R,P,L,D,3,C + fire_B,R,P,L,D,3,I + fire_B,R,P,L,D,3,A
        + fire_B,R,P,L,D,3,L + fire_B,R,P,L,D,3,N  = 0
 c1157: - 0.002 xEAr_B,R,P,L,D,4,1 - 0.002 xEAr_B,R,P,L,D,4,2
        - 0.002 xEAr_B,R,P,L,D,4,3 + fire_B,R,P,L,D,4,P + fire_B,R,P,L,D,4,D
        + fire_B,R,P,L,D,4,W + fire_B,R,P,L,D,4,C + fire_B,R,P,L,D,4,I
        + fire_B,R,P,L,D,4,A + fire_B,R,P,L,D,4,L + fire_B,R,P,L,D,4,N  = 0
 c1158: - 0.002 xEAr_B,R,P,L,D,5,1 - 0.002 xEAr_B,R,P,L,D,5,2
        - 0.002 xEAr_B,R,P,L,D,5,3 - 0.002 xEAr_B,R,P,L,D,5,4
        + fire_B,R,P,L,D,5,P + fire_B,R,P,L,D,5,D + fire_B,R,P,L,D,5,W
        + fire_B,R,P,L,D,5,C + fire_B,R,P,L,D,5,I + fire_B,R,P,L,D,5,A
        + fire_B,R,P,L,D,5,L + fire_B,R,P,L,D,5,N  = 0
 c1159: fire_B,R,P,L,W,1,P + fire_B,R,P,L,W,1,D + fire_B,R,P,L,W,1,W
        + fire_B,R,P,L,W,1,C + fire_B,R,P,L,W,1,I + fire_B,R,P,L,W,1,A
        + fire_B,R,P,L,W,1,L + fire_B,R,P,L,W,1,N  = 0
 c1160: - 0.23 xEAr_B,R,P,L,W,2,1 + fire_B,R,P,L,W,2,P + fire_B,R,P,L,W,2,D
        + fire_B,R,P,L,W,2,W + fire_B,R,P,L,W,2,C + fire_B,R,P,L,W,2,I
        + fire_B,R,P,L,W,2,A + fire_B,R,P,L,W,2,L + fire_B,R,P,L,W,2,N  = 0
 c1161: - 0.23 xEAr_B,R,P,L,W,3,1 - 0.002 xEAr_B,R,P,L,W,3,2
        + fire_B,R,P,L,W,3,P + fire_B,R,P,L,W,3,D + fire_B,R,P,L,W,3,W
        + fire_B,R,P,L,W,3,C + fire_B,R,P,L,W,3,I + fire_B,R,P,L,W,3,A
        + fire_B,R,P,L,W,3,L + fire_B,R,P,L,W,3,N  = 0
 c1162: - 0.23 xEAr_B,R,P,L,W,4,1 - 0.002 xEAr_B,R,P,L,W,4,2
        - 0.002 xEAr_B,R,P,L,W,4,3 + fire_B,R,P,L,W,4,P + fire_B,R,P,L,W,4,D
        + fire_B,R,P,L,W,4,W + fire_B,R,P,L,W,4,C + fire_B,R,P,L,W,4,I
        + fire_B,R,P,L,W,4,A + fire_B,R,P,L,W,4,L + fire_B,R,P,L,W,4,N  = 0
 c1163: - 0.23 xEAr_B,R,P,L,W,5,1 - 0.002 xEAr_B,R,P,L,W,5,2
        - 0.002 xEAr_B,R,P,L,W,5,3 - 0.002 xEAr_B,R,P,L,W,5,4
        + fire_B,R,P,L,W,5,P + fire_B,R,P,L,W,5,D + fire_B,R,P,L,W,5,W
        + fire_B,R,P,L,W,5,C + fire_B,R,P,L,W,5,I + fire_B,R,P,L,W,5,A
        + fire_B,R,P,L,W,5,L + fire_B,R,P,L,W,5,N  = 0
 c1164: fire_B,R,P,L,C,1,P + fire_B,R,P,L,C,1,D + fire_B,R,P,L,C,1,W
        + fire_B,R,P,L,C,1,C + fire_B,R,P,L,C,1,I + fire_B,R,P,L,C,1,A
        + fire_B,R,P,L,C,1,L + fire_B,R,P,L,C,1,N  = 0
 c1165: - 0.002 xEAr_B,R,P,L,C,2,1 + fire_B,R,P,L,C,2,P + fire_B,R,P,L,C,2,D
        + fire_B,R,P,L,C,2,W + fire_B,R,P,L,C,2,C + fire_B,R,P,L,C,2,I
        + fire_B,R,P,L,C,2,A + fire_B,R,P,L,C,2,L + fire_B,R,P,L,C,2,N  = 0
 c1166: - 0.002 xEAr_B,R,P,L,C,3,1 - 0.002 xEAr_B,R,P,L,C,3,2
        + fire_B,R,P,L,C,3,P + fire_B,R,P,L,C,3,D + fire_B,R,P,L,C,3,W
        + fire_B,R,P,L,C,3,C + fire_B,R,P,L,C,3,I + fire_B,R,P,L,C,3,A
        + fire_B,R,P,L,C,3,L + fire_B,R,P,L,C,3,N  = 0
 c1167: - 0.002 xEAr_B,R,P,L,C,4,1 - 0.002 xEAr_B,R,P,L,C,4,2
        - 0.002 xEAr_B,R,P,L,C,4,3 + fire_B,R,P,L,C,4,P + fire_B,R,P,L,C,4,D
        + fire_B,R,P,L,C,4,W + fire_B,R,P,L,C,4,C + fire_B,R,P,L,C,4,I
        + fire_B,R,P,L,C,4,A + fire_B,R,P,L,C,4,L + fire_B,R,P,L,C,4,N  = 0
 c1168: - 0.002 xEAr_B,R,P,L,C,5,1 - 0.002 xEAr_B,R,P,L,C,5,2
        - 0.002 xEAr_B,R,P,L,C,5,3 - 0.002 xEAr_B,R,P,L,C,5,4
        + fire_B,R,P,L,C,5,P + fire_B,R,P,L,C,5,D + fire_B,R,P,L,C,5,W
        + fire_B,R,P,L,C,5,C + fire_B,R,P,L,C,5,I + fire_B,R,P,L,C,5,A
        + fire_B,R,P,L,C,5,L + fire_B,R,P,L,C,5,N  = 0
 c1169: fire_B,R,P,L,I,1,P + fire_B,R,P,L,I,1,D + fire_B,R,P,L,I,1,W
        + fire_B,R,P,L,I,1,C + fire_B,R,P,L,I,1,I + fire_B,R,P,L,I,1,A
        + fire_B,R,P,L,I,1,L + fire_B,R,P,L,I,1,N  = 0
 c1170: - 0.002 xEAr_B,R,P,L,I,2,1 + fire_B,R,P,L,I,2,P + fire_B,R,P,L,I,2,D
        + fire_B,R,P,L,I,2,W + fire_B,R,P,L,I,2,C + fire_B,R,P,L,I,2,I
        + fire_B,R,P,L,I,2,A + fire_B,R,P,L,I,2,L + fire_B,R,P,L,I,2,N  = 0
 c1171: - 0.002 xEAr_B,R,P,L,I,3,1 - 0.002 xEAr_B,R,P,L,I,3,2
        + fire_B,R,P,L,I,3,P + fire_B,R,P,L,I,3,D + fire_B,R,P,L,I,3,W
        + fire_B,R,P,L,I,3,C + fire_B,R,P,L,I,3,I + fire_B,R,P,L,I,3,A
        + fire_B,R,P,L,I,3,L + fire_B,R,P,L,I,3,N  = 0
 c1172: - 0.002 xEAr_B,R,P,L,I,4,1 - 0.002 xEAr_B,R,P,L,I,4,2
        - 0.002 xEAr_B,R,P,L,I,4,3 + fire_B,R,P,L,I,4,P + fire_B,R,P,L,I,4,D
        + fire_B,R,P,L,I,4,W + fire_B,R,P,L,I,4,C + fire_B,R,P,L,I,4,I
        + fire_B,R,P,L,I,4,A + fire_B,R,P,L,I,4,L + fire_B,R,P,L,I,4,N  = 0
 c1173: - 0.002 xEAr_B,R,P,L,I,5,1 - 0.002 xEAr_B,R,P,L,I,5,2
        - 0.002 xEAr_B,R,P,L,I,5,3 - 0.002 xEAr_B,R,P,L,I,5,4
        + fire_B,R,P,L,I,5,P + fire_B,R,P,L,I,5,D + fire_B,R,P,L,I,5,W
        + fire_B,R,P,L,I,5,C + fire_B,R,P,L,I,5,I + fire_B,R,P,L,I,5,A
        + fire_B,R,P,L,I,5,L + fire_B,R,P,L,I,5,N  = 0
 c1174: - 0.002 xNG_B,R,P,L,A,L,1 - 0.002 xPB_B,R,P,L,A,L,0,1
        - 0.002 xPB_B,R,P,L,A,L,1,1 - 0.002 xPB_B,R,P,L,A,L,2,1
        - 0.002 xPB_B,R,P,L,A,L,3,1 - 0.002 xPB_B,R,P,L,A,L,4,1
        - 0.002 xGS_B,R,P,L,A,L,0,1 - 0.002 xGS_B,R,P,L,A,L,1,1
        - 0.002 xGS_B,R,P,L,A,L,2,1 - 0.002 xGS_B,R,P,L,A,L,3,1
        - 0.002 xGS_B,R,P,L,A,L,4,1 - 0.002 xEAe_B,R,P,L,A,L,1
        + fire_B,R,P,L,A,1,P + fire_B,R,P,L,A,1,D + fire_B,R,P,L,A,1,W
        + fire_B,R,P,L,A,1,C + fire_B,R,P,L,A,1,I + fire_B,R,P,L,A,1,A
        + fire_B,R,P,L,A,1,L + fire_B,R,P,L,A,1,N  = 0
 c1175: - 0.002 xNG_B,R,P,L,A,L,2 - 0.002 xPB_B,R,P,L,A,L,0,2
        - 0.002 xPB_B,R,P,L,A,L,1,2 - 0.002 xPB_B,R,P,L,A,L,2,2
        - 0.002 xPB_B,R,P,L,A,L,3,2 - 0.002 xPB_B,R,P,L,A,L,4,2
        - 0.002 xGS_B,R,P,L,A,L,0,2 - 0.002 xGS_B,R,P,L,A,L,1,2
        - 0.002 xGS_B,R,P,L,A,L,2,2 - 0.002 xGS_B,R,P,L,A,L,3,2
        - 0.002 xGS_B,R,P,L,A,L,4,2 - 0.002 xEAe_B,R,P,L,A,L,2
        - 0.002 xEAr_B,R,P,L,A,2,1 + fire_B,R,P,L,A,2,P + fire_B,R,P,L,A,2,D
        + fire_B,R,P,L,A,2,W + fire_B,R,P,L,A,2,C + fire_B,R,P,L,A,2,I
        + fire_B,R,P,L,A,2,A + fire_B,R,P,L,A,2,L + fire_B,R,P,L,A,2,N  = 0
 c1176: - 0.002 xNG_B,R,P,L,A,L,3 - 0.002 xPB_B,R,P,L,A,L,0,3
        - 0.002 xPB_B,R,P,L,A,L,1,3 - 0.002 xPB_B,R,P,L,A,L,2,3
        - 0.002 xPB_B,R,P,L,A,L,3,3 - 0.002 xPB_B,R,P,L,A,L,4,3
        - 0.002 xGS_B,R,P,L,A,L,0,3 - 0.002 xGS_B,R,P,L,A,L,1,3
        - 0.002 xGS_B,R,P,L,A,L,2,3 - 0.002 xGS_B,R,P,L,A,L,3,3
        - 0.002 xGS_B,R,P,L,A,L,4,3 - 0.002 xEAe_B,R,P,L,A,L,3
        - 0.002 xEAr_B,R,P,L,A,3,1 - 0.002 xEAr_B,R,P,L,A,3,2
        + fire_B,R,P,L,A,3,P + fire_B,R,P,L,A,3,D + fire_B,R,P,L,A,3,W
        + fire_B,R,P,L,A,3,C + fire_B,R,P,L,A,3,I + fire_B,R,P,L,A,3,A
        + fire_B,R,P,L,A,3,L + fire_B,R,P,L,A,3,N  = 0
 c1177: - 0.002 xNG_B,R,P,L,A,L,4 - 0.002 xPB_B,R,P,L,A,L,0,4
        - 0.002 xPB_B,R,P,L,A,L,1,4 - 0.002 xPB_B,R,P,L,A,L,2,4
        - 0.002 xPB_B,R,P,L,A,L,3,4 - 0.002 xPB_B,R,P,L,A,L,4,4
        - 0.002 xGS_B,R,P,L,A,L,0,4 - 0.002 xGS_B,R,P,L,A,L,1,4
        - 0.002 xGS_B,R,P,L,A,L,2,4 - 0.002 xGS_B,R,P,L,A,L,3,4
        - 0.002 xGS_B,R,P,L,A,L,4,4 - 0.002 xEAe_B,R,P,L,A,L,4
        - 0.002 xEAr_B,R,P,L,A,4,1 - 0.002 xEAr_B,R,P,L,A,4,2
        - 0.002 xEAr_B,R,P,L,A,4,3 + fire_B,R,P,L,A,4,P + fire_B,R,P,L,A,4,D
        + fire_B,R,P,L,A,4,W + fire_B,R,P,L,A,4,C + fire_B,R,P,L,A,4,I
        + fire_B,R,P,L,A,4,A + fire_B,R,P,L,A,4,L + fire_B,R,P,L,A,4,N  = 0
 c1178: - 0.002 xNG_B,R,P,L,A,L,5 - 0.002 xPB_B,R,P,L,A,L,0,5
        - 0.002 xPB_B,R,P,L,A,L,1,5 - 0.002 xPB_B,R,P,L,A,L,2,5
        - 0.002 xPB_B,R,P,L,A,L,3,5 - 0.002 xPB_B,R,P,L,A,L,4,5
        - 0.002 xGS_B,R,P,L,A,L,0,5 - 0.002 xGS_B,R,P,L,A,L,1,5
        - 0.002 xGS_B,R,P,L,A,L,2,5 - 0.002 xGS_B,R,P,L,A,L,3,5
        - 0.002 xGS_B,R,P,L,A,L,4,5 - 0.002 xEAe_B,R,P,L,A,L,5
        - 0.002 xEAr_B,R,P,L,A,5,1 - 0.002 xEAr_B,R,P,L,A,5,2
        - 0.002 xEAr_B,R,P,L,A,5,3 - 0.002 xEAr_B,R,P,L,A,5,4
        + fire_B,R,P,L,A,5,P + fire_B,R,P,L,A,5,D + fire_B,R,P,L,A,5,W
        + fire_B,R,P,L,A,5,C + fire_B,R,P,L,A,5,I + fire_B,R,P,L,A,5,A
        + fire_B,R,P,L,A,5,L + fire_B,R,P,L,A,5,N  = 0
 c1179: fire_B,R,P,L,L,1,P + fire_B,R,P,L,L,1,D + fire_B,R,P,L,L,1,W
        + fire_B,R,P,L,L,1,C + fire_B,R,P,L,L,1,I + fire_B,R,P,L,L,1,A
        + fire_B,R,P,L,L,1,L + fire_B,R,P,L,L,1,N  = 0
 c1180: - 0.002 xEAr_B,R,P,L,L,2,1 + fire_B,R,P,L,L,2,P + fire_B,R,P,L,L,2,D
        + fire_B,R,P,L,L,2,W + fire_B,R,P,L,L,2,C + fire_B,R,P,L,L,2,I
        + fire_B,R,P,L,L,2,A + fire_B,R,P,L,L,2,L + fire_B,R,P,L,L,2,N  = 0
 c1181: - 0.002 xEAr_B,R,P,L,L,3,1 - 0.002 xEAr_B,R,P,L,L,3,2
        + fire_B,R,P,L,L,3,P + fire_B,R,P,L,L,3,D + fire_B,R,P,L,L,3,W
        + fire_B,R,P,L,L,3,C + fire_B,R,P,L,L,3,I + fire_B,R,P,L,L,3,A
        + fire_B,R,P,L,L,3,L + fire_B,R,P,L,L,3,N  = 0
 c1182: - 0.002 xEAr_B,R,P,L,L,4,1 - 0.002 xEAr_B,R,P,L,L,4,2
        - 0.002 xEAr_B,R,P,L,L,4,3 + fire_B,R,P,L,L,4,P + fire_B,R,P,L,L,4,D
        + fire_B,R,P,L,L,4,W + fire_B,R,P,L,L,4,C + fire_B,R,P,L,L,4,I
        + fire_B,R,P,L,L,4,A + fire_B,R,P,L,L,4,L + fire_B,R,P,L,L,4,N  = 0
 c1183: - 0.002 xEAr_B,R,P,L,L,5,1 - 0.002 xEAr_B,R,P,L,L,5,2
        - 0.002 xEAr_B,R,P,L,L,5,3 - 0.002 xEAr_B,R,P,L,L,5,4
        + fire_B,R,P,L,L,5,P + fire_B,R,P,L,L,5,D + fire_B,R,P,L,L,5,W
        + fire_B,R,P,L,L,5,C + fire_B,R,P,L,L,5,I + fire_B,R,P,L,L,5,A
        + fire_B,R,P,L,L,5,L + fire_B,R,P,L,L,5,N  = 0
 c1184: fire_B,R,P,L,N,1,P + fire_B,R,P,L,N,1,D + fire_B,R,P,L,N,1,W
        + fire_B,R,P,L,N,1,C + fire_B,R,P,L,N,1,I + fire_B,R,P,L,N,1,A
        + fire_B,R,P,L,N,1,L + fire_B,R,P,L,N,1,N  = 0
 c1185: fire_B,R,P,L,N,2,P + fire_B,R,P,L,N,2,D + fire_B,R,P,L,N,2,W
        + fire_B,R,P,L,N,2,C + fire_B,R,P,L,N,2,I + fire_B,R,P,L,N,2,A
        + fire_B,R,P,L,N,2,L + fire_B,R,P,L,N,2,N  = 0
 c1186: fire_B,R,P,L,N,3,P + fire_B,R,P,L,N,3,D + fire_B,R,P,L,N,3,W
        + fire_B,R,P,L,N,3,C + fire_B,R,P,L,N,3,I + fire_B,R,P,L,N,3,A
        + fire_B,R,P,L,N,3,L + fire_B,R,P,L,N,3,N  = 0
 c1187: fire_B,R,P,L,N,4,P + fire_B,R,P,L,N,4,D + fire_B,R,P,L,N,4,W
        + fire_B,R,P,L,N,4,C + fire_B,R,P,L,N,4,I + fire_B,R,P,L,N,4,A
        + fire_B,R,P,L,N,4,L + fire_B,R,P,L,N,4,N  = 0
 c1188: fire_B,R,P,L,N,5,P + fire_B,R,P,L,N,5,D + fire_B,R,P,L,N,5,W
        + fire_B,R,P,L,N,5,C + fire_B,R,P,L,N,5,I + fire_B,R,P,L,N,5,A
        + fire_B,R,P,L,N,5,L + fire_B,R,P,L,N,5,N  = 0
 c1189: fire_B,R,P,C,P,1,P + fire_B,R,P,C,P,1,D + fire_B,R,P,C,P,1,W
        + fire_B,R,P,C,P,1,C + fire_B,R,P,C,P,1,I + fire_B,R,P,C,P,1,A
        + fire_B,R,P,C,P,1,L + fire_B,R,P,C,P,1,N  = 0
 c1190: - 0.002 xEAr_B,R,P,C,P,2,1 + fire_B,R,P,C,P,2,P + fire_B,R,P,C,P,2,D
        + fire_B,R,P,C,P,2,W + fire_B,R,P,C,P,2,C + fire_B,R,P,C,P,2,I
        + fire_B,R,P,C,P,2,A + fire_B,R,P,C,P,2,L + fire_B,R,P,C,P,2,N  = 0
 c1191: - 0.002 xEAr_B,R,P,C,P,3,1 - 0.002 xEAr_B,R,P,C,P,3,2
        + fire_B,R,P,C,P,3,P + fire_B,R,P,C,P,3,D + fire_B,R,P,C,P,3,W
        + fire_B,R,P,C,P,3,C + fire_B,R,P,C,P,3,I + fire_B,R,P,C,P,3,A
        + fire_B,R,P,C,P,3,L + fire_B,R,P,C,P,3,N  = 0
 c1192: - 0.002 xEAr_B,R,P,C,P,4,1 - 0.002 xEAr_B,R,P,C,P,4,2
        - 0.002 xEAr_B,R,P,C,P,4,3 + fire_B,R,P,C,P,4,P + fire_B,R,P,C,P,4,D
        + fire_B,R,P,C,P,4,W + fire_B,R,P,C,P,4,C + fire_B,R,P,C,P,4,I
        + fire_B,R,P,C,P,4,A + fire_B,R,P,C,P,4,L + fire_B,R,P,C,P,4,N  = 0
 c1193: - 0.002 xEAr_B,R,P,C,P,5,1 - 0.002 xEAr_B,R,P,C,P,5,2
        - 0.002 xEAr_B,R,P,C,P,5,3 - 0.002 xEAr_B,R,P,C,P,5,4
        + fire_B,R,P,C,P,5,P + fire_B,R,P,C,P,5,D + fire_B,R,P,C,P,5,W
        + fire_B,R,P,C,P,5,C + fire_B,R,P,C,P,5,I + fire_B,R,P,C,P,5,A
        + fire_B,R,P,C,P,5,L + fire_B,R,P,C,P,5,N  = 0
 c1194: fire_B,R,P,C,D,1,P + fire_B,R,P,C,D,1,D + fire_B,R,P,C,D,1,W
        + fire_B,R,P,C,D,1,C + fire_B,R,P,C,D,1,I + fire_B,R,P,C,D,1,A
        + fire_B,R,P,C,D,1,L + fire_B,R,P,C,D,1,N  = 0
 c1195: - 0.002 xEAr_B,R,P,C,D,2,1 + fire_B,R,P,C,D,2,P + fire_B,R,P,C,D,2,D
        + fire_B,R,P,C,D,2,W + fire_B,R,P,C,D,2,C + fire_B,R,P,C,D,2,I
        + fire_B,R,P,C,D,2,A + fire_B,R,P,C,D,2,L + fire_B,R,P,C,D,2,N  = 0
 c1196: - 0.002 xEAr_B,R,P,C,D,3,1 - 0.002 xEAr_B,R,P,C,D,3,2
        + fire_B,R,P,C,D,3,P + fire_B,R,P,C,D,3,D + fire_B,R,P,C,D,3,W
        + fire_B,R,P,C,D,3,C + fire_B,R,P,C,D,3,I + fire_B,R,P,C,D,3,A
        + fire_B,R,P,C,D,3,L + fire_B,R,P,C,D,3,N  = 0
 c1197: - 0.002 xEAr_B,R,P,C,D,4,1 - 0.002 xEAr_B,R,P,C,D,4,2
        - 0.002 xEAr_B,R,P,C,D,4,3 + fire_B,R,P,C,D,4,P + fire_B,R,P,C,D,4,D
        + fire_B,R,P,C,D,4,W + fire_B,R,P,C,D,4,C + fire_B,R,P,C,D,4,I
        + fire_B,R,P,C,D,4,A + fire_B,R,P,C,D,4,L + fire_B,R,P,C,D,4,N  = 0
 c1198: - 0.002 xEAr_B,R,P,C,D,5,1 - 0.002 xEAr_B,R,P,C,D,5,2
        - 0.002 xEAr_B,R,P,C,D,5,3 - 0.002 xEAr_B,R,P,C,D,5,4
        + fire_B,R,P,C,D,5,P + fire_B,R,P,C,D,5,D + fire_B,R,P,C,D,5,W
        + fire_B,R,P,C,D,5,C + fire_B,R,P,C,D,5,I + fire_B,R,P,C,D,5,A
        + fire_B,R,P,C,D,5,L + fire_B,R,P,C,D,5,N  = 0
 c1199: fire_B,R,P,C,W,1,P + fire_B,R,P,C,W,1,D + fire_B,R,P,C,W,1,W
        + fire_B,R,P,C,W,1,C + fire_B,R,P,C,W,1,I + fire_B,R,P,C,W,1,A
        + fire_B,R,P,C,W,1,L + fire_B,R,P,C,W,1,N  = 0
 c1200: - 0.23 xEAr_B,R,P,C,W,2,1 + fire_B,R,P,C,W,2,P + fire_B,R,P,C,W,2,D
        + fire_B,R,P,C,W,2,W + fire_B,R,P,C,W,2,C + fire_B,R,P,C,W,2,I
        + fire_B,R,P,C,W,2,A + fire_B,R,P,C,W,2,L + fire_B,R,P,C,W,2,N  = 0
 c1201: - 0.23 xEAr_B,R,P,C,W,3,1 - 0.002 xEAr_B,R,P,C,W,3,2
        + fire_B,R,P,C,W,3,P + fire_B,R,P,C,W,3,D + fire_B,R,P,C,W,3,W
        + fire_B,R,P,C,W,3,C + fire_B,R,P,C,W,3,I + fire_B,R,P,C,W,3,A
        + fire_B,R,P,C,W,3,L + fire_B,R,P,C,W,3,N  = 0
 c1202: - 0.23 xEAr_B,R,P,C,W,4,1 - 0.002 xEAr_B,R,P,C,W,4,2
        - 0.002 xEAr_B,R,P,C,W,4,3 + fire_B,R,P,C,W,4,P + fire_B,R,P,C,W,4,D
        + fire_B,R,P,C,W,4,W + fire_B,R,P,C,W,4,C + fire_B,R,P,C,W,4,I
        + fire_B,R,P,C,W,4,A + fire_B,R,P,C,W,4,L + fire_B,R,P,C,W,4,N  = 0
 c1203: - 0.23 xEAr_B,R,P,C,W,5,1 - 0.002 xEAr_B,R,P,C,W,5,2
        - 0.002 xEAr_B,R,P,C,W,5,3 - 0.002 xEAr_B,R,P,C,W,5,4
        + fire_B,R,P,C,W,5,P + fire_B,R,P,C,W,5,D + fire_B,R,P,C,W,5,W
        + fire_B,R,P,C,W,5,C + fire_B,R,P,C,W,5,I + fire_B,R,P,C,W,5,A
        + fire_B,R,P,C,W,5,L + fire_B,R,P,C,W,5,N  = 0
 c1204: fire_B,R,P,C,C,1,P + fire_B,R,P,C,C,1,D + fire_B,R,P,C,C,1,W
        + fire_B,R,P,C,C,1,C + fire_B,R,P,C,C,1,I + fire_B,R,P,C,C,1,A
        + fire_B,R,P,C,C,1,L + fire_B,R,P,C,C,1,N  = 0
 c1205: - 0.002 xEAr_B,R,P,C,C,2,1 + fire_B,R,P,C,C,2,P + fire_B,R,P,C,C,2,D
        + fire_B,R,P,C,C,2,W + fire_B,R,P,C,C,2,C + fire_B,R,P,C,C,2,I
        + fire_B,R,P,C,C,2,A + fire_B,R,P,C,C,2,L + fire_B,R,P,C,C,2,N  = 0
 c1206: - 0.002 xEAr_B,R,P,C,C,3,1 - 0.002 xEAr_B,R,P,C,C,3,2
        + fire_B,R,P,C,C,3,P + fire_B,R,P,C,C,3,D + fire_B,R,P,C,C,3,W
        + fire_B,R,P,C,C,3,C + fire_B,R,P,C,C,3,I + fire_B,R,P,C,C,3,A
        + fire_B,R,P,C,C,3,L + fire_B,R,P,C,C,3,N  = 0
 c1207: - 0.002 xEAr_B,R,P,C,C,4,1 - 0.002 xEAr_B,R,P,C,C,4,2
        - 0.002 xEAr_B,R,P,C,C,4,3 + fire_B,R,P,C,C,4,P + fire_B,R,P,C,C,4,D
        + fire_B,R,P,C,C,4,W + fire_B,R,P,C,C,4,C + fire_B,R,P,C,C,4,I
        + fire_B,R,P,C,C,4,A + fire_B,R,P,C,C,4,L + fire_B,R,P,C,C,4,N  = 0
 c1208: - 0.002 xEAr_B,R,P,C,C,5,1 - 0.002 xEAr_B,R,P,C,C,5,2
        - 0.002 xEAr_B,R,P,C,C,5,3 - 0.002 xEAr_B,R,P,C,C,5,4
        + fire_B,R,P,C,C,5,P + fire_B,R,P,C,C,5,D + fire_B,R,P,C,C,5,W
        + fire_B,R,P,C,C,5,C + fire_B,R,P,C,C,5,I + fire_B,R,P,C,C,5,A
        + fire_B,R,P,C,C,5,L + fire_B,R,P,C,C,5,N  = 0
 c1209: fire_B,R,P,C,I,1,P + fire_B,R,P,C,I,1,D + fire_B,R,P,C,I,1,W
        + fire_B,R,P,C,I,1,C + fire_B,R,P,C,I,1,I + fire_B,R,P,C,I,1,A
        + fire_B,R,P,C,I,1,L + fire_B,R,P,C,I,1,N  = 0
 c1210: - 0.002 xEAr_B,R,P,C,I,2,1 + fire_B,R,P,C,I,2,P + fire_B,R,P,C,I,2,D
        + fire_B,R,P,C,I,2,W + fire_B,R,P,C,I,2,C + fire_B,R,P,C,I,2,I
        + fire_B,R,P,C,I,2,A + fire_B,R,P,C,I,2,L + fire_B,R,P,C,I,2,N  = 0
 c1211: - 0.002 xEAr_B,R,P,C,I,3,1 - 0.002 xEAr_B,R,P,C,I,3,2
        + fire_B,R,P,C,I,3,P + fire_B,R,P,C,I,3,D + fire_B,R,P,C,I,3,W
        + fire_B,R,P,C,I,3,C + fire_B,R,P,C,I,3,I + fire_B,R,P,C,I,3,A
        + fire_B,R,P,C,I,3,L + fire_B,R,P,C,I,3,N  = 0
 c1212: - 0.002 xEAr_B,R,P,C,I,4,1 - 0.002 xEAr_B,R,P,C,I,4,2
        - 0.002 xEAr_B,R,P,C,I,4,3 + fire_B,R,P,C,I,4,P + fire_B,R,P,C,I,4,D
        + fire_B,R,P,C,I,4,W + fire_B,R,P,C,I,4,C + fire_B,R,P,C,I,4,I
        + fire_B,R,P,C,I,4,A + fire_B,R,P,C,I,4,L + fire_B,R,P,C,I,4,N  = 0
 c1213: - 0.002 xEAr_B,R,P,C,I,5,1 - 0.002 xEAr_B,R,P,C,I,5,2
        - 0.002 xEAr_B,R,P,C,I,5,3 - 0.002 xEAr_B,R,P,C,I,5,4
        + fire_B,R,P,C,I,5,P + fire_B,R,P,C,I,5,D + fire_B,R,P,C,I,5,W
        + fire_B,R,P,C,I,5,C + fire_B,R,P,C,I,5,I + fire_B,R,P,C,I,5,A
        + fire_B,R,P,C,I,5,L + fire_B,R,P,C,I,5,N  = 0
 c1214: - 0.002 xNG_B,R,P,C,A,L,1 - 0.002 xPB_B,R,P,C,A,L,0,1
        - 0.002 xPB_B,R,P,C,A,L,1,1 - 0.002 xPB_B,R,P,C,A,L,2,1
        - 0.002 xPB_B,R,P,C,A,L,3,1 - 0.002 xPB_B,R,P,C,A,L,4,1
        - 0.002 xGS_B,R,P,C,A,L,0,1 - 0.002 xGS_B,R,P,C,A,L,1,1
        - 0.002 xGS_B,R,P,C,A,L,2,1 - 0.002 xGS_B,R,P,C,A,L,3,1
        - 0.002 xGS_B,R,P,C,A,L,4,1 - 0.002 xEAe_B,R,P,C,A,L,1
        + fire_B,R,P,C,A,1,P + fire_B,R,P,C,A,1,D + fire_B,R,P,C,A,1,W
        + fire_B,R,P,C,A,1,C + fire_B,R,P,C,A,1,I + fire_B,R,P,C,A,1,A
        + fire_B,R,P,C,A,1,L + fire_B,R,P,C,A,1,N  = 0
 c1215: - 0.002 xNG_B,R,P,C,A,L,2 - 0.002 xPB_B,R,P,C,A,L,0,2
        - 0.002 xPB_B,R,P,C,A,L,1,2 - 0.002 xPB_B,R,P,C,A,L,2,2
        - 0.002 xPB_B,R,P,C,A,L,3,2 - 0.002 xPB_B,R,P,C,A,L,4,2
        - 0.002 xGS_B,R,P,C,A,L,0,2 - 0.002 xGS_B,R,P,C,A,L,1,2
        - 0.002 xGS_B,R,P,C,A,L,2,2 - 0.002 xGS_B,R,P,C,A,L,3,2
        - 0.002 xGS_B,R,P,C,A,L,4,2 - 0.002 xEAe_B,R,P,C,A,L,2
        - 0.002 xEAr_B,R,P,C,A,2,1 + fire_B,R,P,C,A,2,P + fire_B,R,P,C,A,2,D
        + fire_B,R,P,C,A,2,W + fire_B,R,P,C,A,2,C + fire_B,R,P,C,A,2,I
        + fire_B,R,P,C,A,2,A + fire_B,R,P,C,A,2,L + fire_B,R,P,C,A,2,N  = 0
 c1216: - 0.002 xNG_B,R,P,C,A,L,3 - 0.002 xPB_B,R,P,C,A,L,0,3
        - 0.002 xPB_B,R,P,C,A,L,1,3 - 0.002 xPB_B,R,P,C,A,L,2,3
        - 0.002 xPB_B,R,P,C,A,L,3,3 - 0.002 xPB_B,R,P,C,A,L,4,3
        - 0.002 xGS_B,R,P,C,A,L,0,3 - 0.002 xGS_B,R,P,C,A,L,1,3
        - 0.002 xGS_B,R,P,C,A,L,2,3 - 0.002 xGS_B,R,P,C,A,L,3,3
        - 0.002 xGS_B,R,P,C,A,L,4,3 - 0.002 xEAe_B,R,P,C,A,L,3
        - 0.002 xEAr_B,R,P,C,A,3,1 - 0.002 xEAr_B,R,P,C,A,3,2
        + fire_B,R,P,C,A,3,P + fire_B,R,P,C,A,3,D + fire_B,R,P,C,A,3,W
        + fire_B,R,P,C,A,3,C + fire_B,R,P,C,A,3,I + fire_B,R,P,C,A,3,A
        + fire_B,R,P,C,A,3,L + fire_B,R,P,C,A,3,N  = 0
 c1217: - 0.002 xNG_B,R,P,C,A,L,4 - 0.002 xPB_B,R,P,C,A,L,0,4
        - 0.002 xPB_B,R,P,C,A,L,1,4 - 0.002 xPB_B,R,P,C,A,L,2,4
        - 0.002 xPB_B,R,P,C,A,L,3,4 - 0.002 xPB_B,R,P,C,A,L,4,4
        - 0.002 xGS_B,R,P,C,A,L,0,4 - 0.002 xGS_B,R,P,C,A,L,1,4
        - 0.002 xGS_B,R,P,C,A,L,2,4 - 0.002 xGS_B,R,P,C,A,L,3,4
        - 0.002 xGS_B,R,P,C,A,L,4,4 - 0.002 xEAe_B,R,P,C,A,L,4
        - 0.002 xEAr_B,R,P,C,A,4,1 - 0.002 xEAr_B,R,P,C,A,4,2
        - 0.002 xEAr_B,R,P,C,A,4,3 + fire_B,R,P,C,A,4,P + fire_B,R,P,C,A,4,D
        + fire_B,R,P,C,A,4,W + fire_B,R,P,C,A,4,C + fire_B,R,P,C,A,4,I
        + fire_B,R,P,C,A,4,A + fire_B,R,P,C,A,4,L + fire_B,R,P,C,A,4,N  = 0
 c1218: - 0.002 xNG_B,R,P,C,A,L,5 - 0.002 xPB_B,R,P,C,A,L,0,5
        - 0.002 xPB_B,R,P,C,A,L,1,5 - 0.002 xPB_B,R,P,C,A,L,2,5
        - 0.002 xPB_B,R,P,C,A,L,3,5 - 0.002 xPB_B,R,P,C,A,L,4,5
        - 0.002 xGS_B,R,P,C,A,L,0,5 - 0.002 xGS_B,R,P,C,A,L,1,5
        - 0.002 xGS_B,R,P,C,A,L,2,5 - 0.002 xGS_B,R,P,C,A,L,3,5
        - 0.002 xGS_B,R,P,C,A,L,4,5 - 0.002 xEAe_B,R,P,C,A,L,5
        - 0.002 xEAr_B,R,P,C,A,5,1 - 0.002 xEAr_B,R,P,C,A,5,2
        - 0.002 xEAr_B,R,P,C,A,5,3 - 0.002 xEAr_B,R,P,C,A,5,4
        + fire_B,R,P,C,A,5,P + fire_B,R,P,C,A,5,D + fire_B,R,P,C,A,5,W
        + fire_B,R,P,C,A,5,C + fire_B,R,P,C,A,5,I + fire_B,R,P,C,A,5,A
        + fire_B,R,P,C,A,5,L + fire_B,R,P,C,A,5,N  = 0
 c1219: fire_B,R,P,C,L,1,P + fire_B,R,P,C,L,1,D + fire_B,R,P,C,L,1,W
        + fire_B,R,P,C,L,1,C + fire_B,R,P,C,L,1,I + fire_B,R,P,C,L,1,A
        + fire_B,R,P,C,L,1,L + fire_B,R,P,C,L,1,N  = 0
 c1220: - 0.002 xEAr_B,R,P,C,L,2,1 + fire_B,R,P,C,L,2,P + fire_B,R,P,C,L,2,D
        + fire_B,R,P,C,L,2,W + fire_B,R,P,C,L,2,C + fire_B,R,P,C,L,2,I
        + fire_B,R,P,C,L,2,A + fire_B,R,P,C,L,2,L + fire_B,R,P,C,L,2,N  = 0
 c1221: - 0.002 xEAr_B,R,P,C,L,3,1 - 0.002 xEAr_B,R,P,C,L,3,2
        + fire_B,R,P,C,L,3,P + fire_B,R,P,C,L,3,D + fire_B,R,P,C,L,3,W
        + fire_B,R,P,C,L,3,C + fire_B,R,P,C,L,3,I + fire_B,R,P,C,L,3,A
        + fire_B,R,P,C,L,3,L + fire_B,R,P,C,L,3,N  = 0
 c1222: - 0.002 xEAr_B,R,P,C,L,4,1 - 0.002 xEAr_B,R,P,C,L,4,2
        - 0.002 xEAr_B,R,P,C,L,4,3 + fire_B,R,P,C,L,4,P + fire_B,R,P,C,L,4,D
        + fire_B,R,P,C,L,4,W + fire_B,R,P,C,L,4,C + fire_B,R,P,C,L,4,I
        + fire_B,R,P,C,L,4,A + fire_B,R,P,C,L,4,L + fire_B,R,P,C,L,4,N  = 0
 c1223: - 0.002 xEAr_B,R,P,C,L,5,1 - 0.002 xEAr_B,R,P,C,L,5,2
        - 0.002 xEAr_B,R,P,C,L,5,3 - 0.002 xEAr_B,R,P,C,L,5,4
        + fire_B,R,P,C,L,5,P + fire_B,R,P,C,L,5,D + fire_B,R,P,C,L,5,W
        + fire_B,R,P,C,L,5,C + fire_B,R,P,C,L,5,I + fire_B,R,P,C,L,5,A
        + fire_B,R,P,C,L,5,L + fire_B,R,P,C,L,5,N  = 0
 c1224: fire_B,R,P,C,N,1,P + fire_B,R,P,C,N,1,D + fire_B,R,P,C,N,1,W
        + fire_B,R,P,C,N,1,C + fire_B,R,P,C,N,1,I + fire_B,R,P,C,N,1,A
        + fire_B,R,P,C,N,1,L + fire_B,R,P,C,N,1,N  = 0
 c1225: fire_B,R,P,C,N,2,P + fire_B,R,P,C,N,2,D + fire_B,R,P,C,N,2,W
        + fire_B,R,P,C,N,2,C + fire_B,R,P,C,N,2,I + fire_B,R,P,C,N,2,A
        + fire_B,R,P,C,N,2,L + fire_B,R,P,C,N,2,N  = 0
 c1226: fire_B,R,P,C,N,3,P + fire_B,R,P,C,N,3,D + fire_B,R,P,C,N,3,W
        + fire_B,R,P,C,N,3,C + fire_B,R,P,C,N,3,I + fire_B,R,P,C,N,3,A
        + fire_B,R,P,C,N,3,L + fire_B,R,P,C,N,3,N  = 0
 c1227: fire_B,R,P,C,N,4,P + fire_B,R,P,C,N,4,D + fire_B,R,P,C,N,4,W
        + fire_B,R,P,C,N,4,C + fire_B,R,P,C,N,4,I + fire_B,R,P,C,N,4,A
        + fire_B,R,P,C,N,4,L + fire_B,R,P,C,N,4,N  = 0
 c1228: fire_B,R,P,C,N,5,P + fire_B,R,P,C,N,5,D + fire_B,R,P,C,N,5,W
        + fire_B,R,P,C,N,5,C + fire_B,R,P,C,N,5,I + fire_B,R,P,C,N,5,A
        + fire_B,R,P,C,N,5,L + fire_B,R,P,C,N,5,N  = 0
 c1229: fire_B,R,P,R,P,1,P + fire_B,R,P,R,P,1,D + fire_B,R,P,R,P,1,W
        + fire_B,R,P,R,P,1,C + fire_B,R,P,R,P,1,I + fire_B,R,P,R,P,1,A
        + fire_B,R,P,R,P,1,L + fire_B,R,P,R,P,1,N  = 0
 c1230: - 0.002 xEAr_B,R,P,R,P,2,1 + fire_B,R,P,R,P,2,P + fire_B,R,P,R,P,2,D
        + fire_B,R,P,R,P,2,W + fire_B,R,P,R,P,2,C + fire_B,R,P,R,P,2,I
        + fire_B,R,P,R,P,2,A + fire_B,R,P,R,P,2,L + fire_B,R,P,R,P,2,N  = 0
 c1231: - 0.002 xEAr_B,R,P,R,P,3,1 - 0.002 xEAr_B,R,P,R,P,3,2
        + fire_B,R,P,R,P,3,P + fire_B,R,P,R,P,3,D + fire_B,R,P,R,P,3,W
        + fire_B,R,P,R,P,3,C + fire_B,R,P,R,P,3,I + fire_B,R,P,R,P,3,A
        + fire_B,R,P,R,P,3,L + fire_B,R,P,R,P,3,N  = 0
 c1232: - 0.002 xEAr_B,R,P,R,P,4,1 - 0.002 xEAr_B,R,P,R,P,4,2
        - 0.002 xEAr_B,R,P,R,P,4,3 + fire_B,R,P,R,P,4,P + fire_B,R,P,R,P,4,D
        + fire_B,R,P,R,P,4,W + fire_B,R,P,R,P,4,C + fire_B,R,P,R,P,4,I
        + fire_B,R,P,R,P,4,A + fire_B,R,P,R,P,4,L + fire_B,R,P,R,P,4,N  = 0
 c1233: - 0.002 xEAr_B,R,P,R,P,5,1 - 0.002 xEAr_B,R,P,R,P,5,2
        - 0.002 xEAr_B,R,P,R,P,5,3 - 0.002 xEAr_B,R,P,R,P,5,4
        + fire_B,R,P,R,P,5,P + fire_B,R,P,R,P,5,D + fire_B,R,P,R,P,5,W
        + fire_B,R,P,R,P,5,C + fire_B,R,P,R,P,5,I + fire_B,R,P,R,P,5,A
        + fire_B,R,P,R,P,5,L + fire_B,R,P,R,P,5,N  = 0
 c1234: fire_B,R,P,R,D,1,P + fire_B,R,P,R,D,1,D + fire_B,R,P,R,D,1,W
        + fire_B,R,P,R,D,1,C + fire_B,R,P,R,D,1,I + fire_B,R,P,R,D,1,A
        + fire_B,R,P,R,D,1,L + fire_B,R,P,R,D,1,N  = 0
 c1235: - 0.002 xEAr_B,R,P,R,D,2,1 + fire_B,R,P,R,D,2,P + fire_B,R,P,R,D,2,D
        + fire_B,R,P,R,D,2,W + fire_B,R,P,R,D,2,C + fire_B,R,P,R,D,2,I
        + fire_B,R,P,R,D,2,A + fire_B,R,P,R,D,2,L + fire_B,R,P,R,D,2,N  = 0
 c1236: - 0.002 xEAr_B,R,P,R,D,3,1 - 0.002 xEAr_B,R,P,R,D,3,2
        + fire_B,R,P,R,D,3,P + fire_B,R,P,R,D,3,D + fire_B,R,P,R,D,3,W
        + fire_B,R,P,R,D,3,C + fire_B,R,P,R,D,3,I + fire_B,R,P,R,D,3,A
        + fire_B,R,P,R,D,3,L + fire_B,R,P,R,D,3,N  = 0
 c1237: - 0.002 xEAr_B,R,P,R,D,4,1 - 0.002 xEAr_B,R,P,R,D,4,2
        - 0.002 xEAr_B,R,P,R,D,4,3 + fire_B,R,P,R,D,4,P + fire_B,R,P,R,D,4,D
        + fire_B,R,P,R,D,4,W + fire_B,R,P,R,D,4,C + fire_B,R,P,R,D,4,I
        + fire_B,R,P,R,D,4,A + fire_B,R,P,R,D,4,L + fire_B,R,P,R,D,4,N  = 0
 c1238: - 0.002 xEAr_B,R,P,R,D,5,1 - 0.002 xEAr_B,R,P,R,D,5,2
        - 0.002 xEAr_B,R,P,R,D,5,3 - 0.002 xEAr_B,R,P,R,D,5,4
        + fire_B,R,P,R,D,5,P + fire_B,R,P,R,D,5,D + fire_B,R,P,R,D,5,W
        + fire_B,R,P,R,D,5,C + fire_B,R,P,R,D,5,I + fire_B,R,P,R,D,5,A
        + fire_B,R,P,R,D,5,L + fire_B,R,P,R,D,5,N  = 0
 c1239: fire_B,R,P,R,W,1,P + fire_B,R,P,R,W,1,D + fire_B,R,P,R,W,1,W
        + fire_B,R,P,R,W,1,C + fire_B,R,P,R,W,1,I + fire_B,R,P,R,W,1,A
        + fire_B,R,P,R,W,1,L + fire_B,R,P,R,W,1,N  = 0
 c1240: - 0.23 xEAr_B,R,P,R,W,2,1 + fire_B,R,P,R,W,2,P + fire_B,R,P,R,W,2,D
        + fire_B,R,P,R,W,2,W + fire_B,R,P,R,W,2,C + fire_B,R,P,R,W,2,I
        + fire_B,R,P,R,W,2,A + fire_B,R,P,R,W,2,L + fire_B,R,P,R,W,2,N  = 0
 c1241: - 0.23 xEAr_B,R,P,R,W,3,1 - 0.002 xEAr_B,R,P,R,W,3,2
        + fire_B,R,P,R,W,3,P + fire_B,R,P,R,W,3,D + fire_B,R,P,R,W,3,W
        + fire_B,R,P,R,W,3,C + fire_B,R,P,R,W,3,I + fire_B,R,P,R,W,3,A
        + fire_B,R,P,R,W,3,L + fire_B,R,P,R,W,3,N  = 0
 c1242: - 0.23 xEAr_B,R,P,R,W,4,1 - 0.002 xEAr_B,R,P,R,W,4,2
        - 0.002 xEAr_B,R,P,R,W,4,3 + fire_B,R,P,R,W,4,P + fire_B,R,P,R,W,4,D
        + fire_B,R,P,R,W,4,W + fire_B,R,P,R,W,4,C + fire_B,R,P,R,W,4,I
        + fire_B,R,P,R,W,4,A + fire_B,R,P,R,W,4,L + fire_B,R,P,R,W,4,N  = 0
 c1243: - 0.23 xEAr_B,R,P,R,W,5,1 - 0.002 xEAr_B,R,P,R,W,5,2
        - 0.002 xEAr_B,R,P,R,W,5,3 - 0.002 xEAr_B,R,P,R,W,5,4
        + fire_B,R,P,R,W,5,P + fire_B,R,P,R,W,5,D + fire_B,R,P,R,W,5,W
        + fire_B,R,P,R,W,5,C + fire_B,R,P,R,W,5,I + fire_B,R,P,R,W,5,A
        + fire_B,R,P,R,W,5,L + fire_B,R,P,R,W,5,N  = 0
 c1244: fire_B,R,P,R,C,1,P + fire_B,R,P,R,C,1,D + fire_B,R,P,R,C,1,W
        + fire_B,R,P,R,C,1,C + fire_B,R,P,R,C,1,I + fire_B,R,P,R,C,1,A
        + fire_B,R,P,R,C,1,L + fire_B,R,P,R,C,1,N  = 0
 c1245: - 0.002 xEAr_B,R,P,R,C,2,1 + fire_B,R,P,R,C,2,P + fire_B,R,P,R,C,2,D
        + fire_B,R,P,R,C,2,W + fire_B,R,P,R,C,2,C + fire_B,R,P,R,C,2,I
        + fire_B,R,P,R,C,2,A + fire_B,R,P,R,C,2,L + fire_B,R,P,R,C,2,N  = 0
 c1246: - 0.002 xEAr_B,R,P,R,C,3,1 - 0.002 xEAr_B,R,P,R,C,3,2
        + fire_B,R,P,R,C,3,P + fire_B,R,P,R,C,3,D + fire_B,R,P,R,C,3,W
        + fire_B,R,P,R,C,3,C + fire_B,R,P,R,C,3,I + fire_B,R,P,R,C,3,A
        + fire_B,R,P,R,C,3,L + fire_B,R,P,R,C,3,N  = 0
 c1247: - 0.002 xEAr_B,R,P,R,C,4,1 - 0.002 xEAr_B,R,P,R,C,4,2
        - 0.002 xEAr_B,R,P,R,C,4,3 + fire_B,R,P,R,C,4,P + fire_B,R,P,R,C,4,D
        + fire_B,R,P,R,C,4,W + fire_B,R,P,R,C,4,C + fire_B,R,P,R,C,4,I
        + fire_B,R,P,R,C,4,A + fire_B,R,P,R,C,4,L + fire_B,R,P,R,C,4,N  = 0
 c1248: - 0.002 xEAr_B,R,P,R,C,5,1 - 0.002 xEAr_B,R,P,R,C,5,2
        - 0.002 xEAr_B,R,P,R,C,5,3 - 0.002 xEAr_B,R,P,R,C,5,4
        + fire_B,R,P,R,C,5,P + fire_B,R,P,R,C,5,D + fire_B,R,P,R,C,5,W
        + fire_B,R,P,R,C,5,C + fire_B,R,P,R,C,5,I + fire_B,R,P,R,C,5,A
        + fire_B,R,P,R,C,5,L + fire_B,R,P,R,C,5,N  = 0
 c1249: fire_B,R,P,R,I,1,P + fire_B,R,P,R,I,1,D + fire_B,R,P,R,I,1,W
        + fire_B,R,P,R,I,1,C + fire_B,R,P,R,I,1,I + fire_B,R,P,R,I,1,A
        + fire_B,R,P,R,I,1,L + fire_B,R,P,R,I,1,N  = 0
 c1250: - 0.002 xEAr_B,R,P,R,I,2,1 + fire_B,R,P,R,I,2,P + fire_B,R,P,R,I,2,D
        + fire_B,R,P,R,I,2,W + fire_B,R,P,R,I,2,C + fire_B,R,P,R,I,2,I
        + fire_B,R,P,R,I,2,A + fire_B,R,P,R,I,2,L + fire_B,R,P,R,I,2,N  = 0
 c1251: - 0.002 xEAr_B,R,P,R,I,3,1 - 0.002 xEAr_B,R,P,R,I,3,2
        + fire_B,R,P,R,I,3,P + fire_B,R,P,R,I,3,D + fire_B,R,P,R,I,3,W
        + fire_B,R,P,R,I,3,C + fire_B,R,P,R,I,3,I + fire_B,R,P,R,I,3,A
        + fire_B,R,P,R,I,3,L + fire_B,R,P,R,I,3,N  = 0
 c1252: - 0.002 xEAr_B,R,P,R,I,4,1 - 0.002 xEAr_B,R,P,R,I,4,2
        - 0.002 xEAr_B,R,P,R,I,4,3 + fire_B,R,P,R,I,4,P + fire_B,R,P,R,I,4,D
        + fire_B,R,P,R,I,4,W + fire_B,R,P,R,I,4,C + fire_B,R,P,R,I,4,I
        + fire_B,R,P,R,I,4,A + fire_B,R,P,R,I,4,L + fire_B,R,P,R,I,4,N  = 0
 c1253: - 0.002 xEAr_B,R,P,R,I,5,1 - 0.002 xEAr_B,R,P,R,I,5,2
        - 0.002 xEAr_B,R,P,R,I,5,3 - 0.002 xEAr_B,R,P,R,I,5,4
        + fire_B,R,P,R,I,5,P + fire_B,R,P,R,I,5,D + fire_B,R,P,R,I,5,W
        + fire_B,R,P,R,I,5,C + fire_B,R,P,R,I,5,I + fire_B,R,P,R,I,5,A
        + fire_B,R,P,R,I,5,L + fire_B,R,P,R,I,5,N  = 0
 c1254: - 0.002 xNG_B,R,P,R,A,L,1 - 0.002 xPB_B,R,P,R,A,L,0,1
        - 0.002 xPB_B,R,P,R,A,L,1,1 - 0.002 xPB_B,R,P,R,A,L,2,1
        - 0.002 xPB_B,R,P,R,A,L,3,1 - 0.002 xPB_B,R,P,R,A,L,4,1
        - 0.002 xGS_B,R,P,R,A,L,0,1 - 0.002 xGS_B,R,P,R,A,L,1,1
        - 0.002 xGS_B,R,P,R,A,L,2,1 - 0.002 xGS_B,R,P,R,A,L,3,1
        - 0.002 xGS_B,R,P,R,A,L,4,1 - 0.002 xEAe_B,R,P,R,A,L,1
        + fire_B,R,P,R,A,1,P + fire_B,R,P,R,A,1,D + fire_B,R,P,R,A,1,W
        + fire_B,R,P,R,A,1,C + fire_B,R,P,R,A,1,I + fire_B,R,P,R,A,1,A
        + fire_B,R,P,R,A,1,L + fire_B,R,P,R,A,1,N  = 0
 c1255: - 0.002 xNG_B,R,P,R,A,L,2 - 0.002 xPB_B,R,P,R,A,L,0,2
        - 0.002 xPB_B,R,P,R,A,L,1,2 - 0.002 xPB_B,R,P,R,A,L,2,2
        - 0.002 xPB_B,R,P,R,A,L,3,2 - 0.002 xPB_B,R,P,R,A,L,4,2
        - 0.002 xGS_B,R,P,R,A,L,0,2 - 0.002 xGS_B,R,P,R,A,L,1,2
        - 0.002 xGS_B,R,P,R,A,L,2,2 - 0.002 xGS_B,R,P,R,A,L,3,2
        - 0.002 xGS_B,R,P,R,A,L,4,2 - 0.002 xEAe_B,R,P,R,A,L,2
        - 0.002 xEAr_B,R,P,R,A,2,1 + fire_B,R,P,R,A,2,P + fire_B,R,P,R,A,2,D
        + fire_B,R,P,R,A,2,W + fire_B,R,P,R,A,2,C + fire_B,R,P,R,A,2,I
        + fire_B,R,P,R,A,2,A + fire_B,R,P,R,A,2,L + fire_B,R,P,R,A,2,N  = 0
 c1256: - 0.002 xNG_B,R,P,R,A,L,3 - 0.002 xPB_B,R,P,R,A,L,0,3
        - 0.002 xPB_B,R,P,R,A,L,1,3 - 0.002 xPB_B,R,P,R,A,L,2,3
        - 0.002 xPB_B,R,P,R,A,L,3,3 - 0.002 xPB_B,R,P,R,A,L,4,3
        - 0.002 xGS_B,R,P,R,A,L,0,3 - 0.002 xGS_B,R,P,R,A,L,1,3
        - 0.002 xGS_B,R,P,R,A,L,2,3 - 0.002 xGS_B,R,P,R,A,L,3,3
        - 0.002 xGS_B,R,P,R,A,L,4,3 - 0.002 xEAe_B,R,P,R,A,L,3
        - 0.002 xEAr_B,R,P,R,A,3,1 - 0.002 xEAr_B,R,P,R,A,3,2
        + fire_B,R,P,R,A,3,P + fire_B,R,P,R,A,3,D + fire_B,R,P,R,A,3,W
        + fire_B,R,P,R,A,3,C + fire_B,R,P,R,A,3,I + fire_B,R,P,R,A,3,A
        + fire_B,R,P,R,A,3,L + fire_B,R,P,R,A,3,N  = 0
 c1257: - 0.002 xNG_B,R,P,R,A,L,4 - 0.002 xPB_B,R,P,R,A,L,0,4
        - 0.002 xPB_B,R,P,R,A,L,1,4 - 0.002 xPB_B,R,P,R,A,L,2,4
        - 0.002 xPB_B,R,P,R,A,L,3,4 - 0.002 xPB_B,R,P,R,A,L,4,4
        - 0.002 xGS_B,R,P,R,A,L,0,4 - 0.002 xGS_B,R,P,R,A,L,1,4
        - 0.002 xGS_B,R,P,R,A,L,2,4 - 0.002 xGS_B,R,P,R,A,L,3,4
        - 0.002 xGS_B,R,P,R,A,L,4,4 - 0.002 xEAe_B,R,P,R,A,L,4
        - 0.002 xEAr_B,R,P,R,A,4,1 - 0.002 xEAr_B,R,P,R,A,4,2
        - 0.002 xEAr_B,R,P,R,A,4,3 + fire_B,R,P,R,A,4,P + fire_B,R,P,R,A,4,D
        + fire_B,R,P,R,A,4,W + fire_B,R,P,R,A,4,C + fire_B,R,P,R,A,4,I
        + fire_B,R,P,R,A,4,A + fire_B,R,P,R,A,4,L + fire_B,R,P,R,A,4,N  = 0
 c1258: - 0.002 xNG_B,R,P,R,A,L,5 - 0.002 xPB_B,R,P,R,A,L,0,5
        - 0.002 xPB_B,R,P,R,A,L,1,5 - 0.002 xPB_B,R,P,R,A,L,2,5
        - 0.002 xPB_B,R,P,R,A,L,3,5 - 0.002 xPB_B,R,P,R,A,L,4,5
        - 0.002 xGS_B,R,P,R,A,L,0,5 - 0.002 xGS_B,R,P,R,A,L,1,5
        - 0.002 xGS_B,R,P,R,A,L,2,5 - 0.002 xGS_B,R,P,R,A,L,3,5
        - 0.002 xGS_B,R,P,R,A,L,4,5 - 0.002 xEAe_B,R,P,R,A,L,5
        - 0.002 xEAr_B,R,P,R,A,5,1 - 0.002 xEAr_B,R,P,R,A,5,2
        - 0.002 xEAr_B,R,P,R,A,5,3 - 0.002 xEAr_B,R,P,R,A,5,4
        + fire_B,R,P,R,A,5,P + fire_B,R,P,R,A,5,D + fire_B,R,P,R,A,5,W
        + fire_B,R,P,R,A,5,C + fire_B,R,P,R,A,5,I + fire_B,R,P,R,A,5,A
        + fire_B,R,P,R,A,5,L + fire_B,R,P,R,A,5,N  = 0
 c1259: fire_B,R,P,R,L,1,P + fire_B,R,P,R,L,1,D + fire_B,R,P,R,L,1,W
        + fire_B,R,P,R,L,1,C + fire_B,R,P,R,L,1,I + fire_B,R,P,R,L,1,A
        + fire_B,R,P,R,L,1,L + fire_B,R,P,R,L,1,N  = 0
 c1260: - 0.002 xEAr_B,R,P,R,L,2,1 + fire_B,R,P,R,L,2,P + fire_B,R,P,R,L,2,D
        + fire_B,R,P,R,L,2,W + fire_B,R,P,R,L,2,C + fire_B,R,P,R,L,2,I
        + fire_B,R,P,R,L,2,A + fire_B,R,P,R,L,2,L + fire_B,R,P,R,L,2,N  = 0
 c1261: - 0.002 xEAr_B,R,P,R,L,3,1 - 0.002 xEAr_B,R,P,R,L,3,2
        + fire_B,R,P,R,L,3,P + fire_B,R,P,R,L,3,D + fire_B,R,P,R,L,3,W
        + fire_B,R,P,R,L,3,C + fire_B,R,P,R,L,3,I + fire_B,R,P,R,L,3,A
        + fire_B,R,P,R,L,3,L + fire_B,R,P,R,L,3,N  = 0
 c1262: - 0.002 xEAr_B,R,P,R,L,4,1 - 0.002 xEAr_B,R,P,R,L,4,2
        - 0.002 xEAr_B,R,P,R,L,4,3 + fire_B,R,P,R,L,4,P + fire_B,R,P,R,L,4,D
        + fire_B,R,P,R,L,4,W + fire_B,R,P,R,L,4,C + fire_B,R,P,R,L,4,I
        + fire_B,R,P,R,L,4,A + fire_B,R,P,R,L,4,L + fire_B,R,P,R,L,4,N  = 0
 c1263: - 0.002 xEAr_B,R,P,R,L,5,1 - 0.002 xEAr_B,R,P,R,L,5,2
        - 0.002 xEAr_B,R,P,R,L,5,3 - 0.002 xEAr_B,R,P,R,L,5,4
        + fire_B,R,P,R,L,5,P + fire_B,R,P,R,L,5,D + fire_B,R,P,R,L,5,W
        + fire_B,R,P,R,L,5,C + fire_B,R,P,R,L,5,I + fire_B,R,P,R,L,5,A
        + fire_B,R,P,R,L,5,L + fire_B,R,P,R,L,5,N  = 0
 c1264: fire_B,R,P,R,N,1,P + fire_B,R,P,R,N,1,D + fire_B,R,P,R,N,1,W
        + fire_B,R,P,R,N,1,C + fire_B,R,P,R,N,1,I + fire_B,R,P,R,N,1,A
        + fire_B,R,P,R,N,1,L + fire_B,R,P,R,N,1,N  = 0
 c1265: fire_B,R,P,R,N,2,P + fire_B,R,P,R,N,2,D + fire_B,R,P,R,N,2,W
        + fire_B,R,P,R,N,2,C + fire_B,R,P,R,N,2,I + fire_B,R,P,R,N,2,A
        + fire_B,R,P,R,N,2,L + fire_B,R,P,R,N,2,N  = 0
 c1266: fire_B,R,P,R,N,3,P + fire_B,R,P,R,N,3,D + fire_B,R,P,R,N,3,W
        + fire_B,R,P,R,N,3,C + fire_B,R,P,R,N,3,I + fire_B,R,P,R,N,3,A
        + fire_B,R,P,R,N,3,L + fire_B,R,P,R,N,3,N  = 0
 c1267: fire_B,R,P,R,N,4,P + fire_B,R,P,R,N,4,D + fire_B,R,P,R,N,4,W
        + fire_B,R,P,R,N,4,C + fire_B,R,P,R,N,4,I + fire_B,R,P,R,N,4,A
        + fire_B,R,P,R,N,4,L + fire_B,R,P,R,N,4,N  = 0
 c1268: fire_B,R,P,R,N,5,P + fire_B,R,P,R,N,5,D + fire_B,R,P,R,N,5,W
        + fire_B,R,P,R,N,5,C + fire_B,R,P,R,N,5,I + fire_B,R,P,R,N,5,A
        + fire_B,R,P,R,N,5,L + fire_B,R,P,R,N,5,N  = 0
 c1269: fire_B,N,N,L,P,1,P + fire_B,N,N,L,P,1,D + fire_B,N,N,L,P,1,W
        + fire_B,N,N,L,P,1,C + fire_B,N,N,L,P,1,I + fire_B,N,N,L,P,1,A
        + fire_B,N,N,L,P,1,L + fire_B,N,N,L,P,1,N  = 0
 c1270: - 0.002 xEAr_B,N,N,L,P,2,1 + fire_B,N,N,L,P,2,P + fire_B,N,N,L,P,2,D
        + fire_B,N,N,L,P,2,W + fire_B,N,N,L,P,2,C + fire_B,N,N,L,P,2,I
        + fire_B,N,N,L,P,2,A + fire_B,N,N,L,P,2,L + fire_B,N,N,L,P,2,N  = 0
 c1271: - 0.002 xEAr_B,N,N,L,P,3,1 - 0.002 xEAr_B,N,N,L,P,3,2
        + fire_B,N,N,L,P,3,P + fire_B,N,N,L,P,3,D + fire_B,N,N,L,P,3,W
        + fire_B,N,N,L,P,3,C + fire_B,N,N,L,P,3,I + fire_B,N,N,L,P,3,A
        + fire_B,N,N,L,P,3,L + fire_B,N,N,L,P,3,N  = 0
 c1272: - 0.002 xEAr_B,N,N,L,P,4,1 - 0.002 xEAr_B,N,N,L,P,4,2
        - 0.002 xEAr_B,N,N,L,P,4,3 + fire_B,N,N,L,P,4,P + fire_B,N,N,L,P,4,D
        + fire_B,N,N,L,P,4,W + fire_B,N,N,L,P,4,C + fire_B,N,N,L,P,4,I
        + fire_B,N,N,L,P,4,A + fire_B,N,N,L,P,4,L + fire_B,N,N,L,P,4,N  = 0
 c1273: - 0.002 xEAr_B,N,N,L,P,5,1 - 0.002 xEAr_B,N,N,L,P,5,2
        - 0.002 xEAr_B,N,N,L,P,5,3 - 0.002 xEAr_B,N,N,L,P,5,4
        + fire_B,N,N,L,P,5,P + fire_B,N,N,L,P,5,D + fire_B,N,N,L,P,5,W
        + fire_B,N,N,L,P,5,C + fire_B,N,N,L,P,5,I + fire_B,N,N,L,P,5,A
        + fire_B,N,N,L,P,5,L + fire_B,N,N,L,P,5,N  = 0
 c1274: fire_B,N,N,L,D,1,P + fire_B,N,N,L,D,1,D + fire_B,N,N,L,D,1,W
        + fire_B,N,N,L,D,1,C + fire_B,N,N,L,D,1,I + fire_B,N,N,L,D,1,A
        + fire_B,N,N,L,D,1,L + fire_B,N,N,L,D,1,N  = 0
 c1275: - 0.002 xEAr_B,N,N,L,D,2,1 + fire_B,N,N,L,D,2,P + fire_B,N,N,L,D,2,D
        + fire_B,N,N,L,D,2,W + fire_B,N,N,L,D,2,C + fire_B,N,N,L,D,2,I
        + fire_B,N,N,L,D,2,A + fire_B,N,N,L,D,2,L + fire_B,N,N,L,D,2,N  = 0
 c1276: - 0.002 xEAr_B,N,N,L,D,3,1 - 0.002 xEAr_B,N,N,L,D,3,2
        + fire_B,N,N,L,D,3,P + fire_B,N,N,L,D,3,D + fire_B,N,N,L,D,3,W
        + fire_B,N,N,L,D,3,C + fire_B,N,N,L,D,3,I + fire_B,N,N,L,D,3,A
        + fire_B,N,N,L,D,3,L + fire_B,N,N,L,D,3,N  = 0
 c1277: - 0.002 xEAr_B,N,N,L,D,4,1 - 0.002 xEAr_B,N,N,L,D,4,2
        - 0.002 xEAr_B,N,N,L,D,4,3 + fire_B,N,N,L,D,4,P + fire_B,N,N,L,D,4,D
        + fire_B,N,N,L,D,4,W + fire_B,N,N,L,D,4,C + fire_B,N,N,L,D,4,I
        + fire_B,N,N,L,D,4,A + fire_B,N,N,L,D,4,L + fire_B,N,N,L,D,4,N  = 0
 c1278: - 0.002 xEAr_B,N,N,L,D,5,1 - 0.002 xEAr_B,N,N,L,D,5,2
        - 0.002 xEAr_B,N,N,L,D,5,3 - 0.002 xEAr_B,N,N,L,D,5,4
        + fire_B,N,N,L,D,5,P + fire_B,N,N,L,D,5,D + fire_B,N,N,L,D,5,W
        + fire_B,N,N,L,D,5,C + fire_B,N,N,L,D,5,I + fire_B,N,N,L,D,5,A
        + fire_B,N,N,L,D,5,L + fire_B,N,N,L,D,5,N  = 0
 c1279: fire_B,N,N,L,W,1,P + fire_B,N,N,L,W,1,D + fire_B,N,N,L,W,1,W
        + fire_B,N,N,L,W,1,C + fire_B,N,N,L,W,1,I + fire_B,N,N,L,W,1,A
        + fire_B,N,N,L,W,1,L + fire_B,N,N,L,W,1,N  = 0
 c1280: - 0.23 xEAr_B,N,N,L,W,2,1 + fire_B,N,N,L,W,2,P + fire_B,N,N,L,W,2,D
        + fire_B,N,N,L,W,2,W + fire_B,N,N,L,W,2,C + fire_B,N,N,L,W,2,I
        + fire_B,N,N,L,W,2,A + fire_B,N,N,L,W,2,L + fire_B,N,N,L,W,2,N  = 0
 c1281: - 0.23 xEAr_B,N,N,L,W,3,1 - 0.002 xEAr_B,N,N,L,W,3,2
        + fire_B,N,N,L,W,3,P + fire_B,N,N,L,W,3,D + fire_B,N,N,L,W,3,W
        + fire_B,N,N,L,W,3,C + fire_B,N,N,L,W,3,I + fire_B,N,N,L,W,3,A
        + fire_B,N,N,L,W,3,L + fire_B,N,N,L,W,3,N  = 0
 c1282: - 0.23 xEAr_B,N,N,L,W,4,1 - 0.002 xEAr_B,N,N,L,W,4,2
        - 0.002 xEAr_B,N,N,L,W,4,3 + fire_B,N,N,L,W,4,P + fire_B,N,N,L,W,4,D
        + fire_B,N,N,L,W,4,W + fire_B,N,N,L,W,4,C + fire_B,N,N,L,W,4,I
        + fire_B,N,N,L,W,4,A + fire_B,N,N,L,W,4,L + fire_B,N,N,L,W,4,N  = 0
 c1283: - 0.23 xEAr_B,N,N,L,W,5,1 - 0.002 xEAr_B,N,N,L,W,5,2
        - 0.002 xEAr_B,N,N,L,W,5,3 - 0.002 xEAr_B,N,N,L,W,5,4
        + fire_B,N,N,L,W,5,P + fire_B,N,N,L,W,5,D + fire_B,N,N,L,W,5,W
        + fire_B,N,N,L,W,5,C + fire_B,N,N,L,W,5,I + fire_B,N,N,L,W,5,A
        + fire_B,N,N,L,W,5,L + fire_B,N,N,L,W,5,N  = 0
 c1284: fire_B,N,N,L,C,1,P + fire_B,N,N,L,C,1,D + fire_B,N,N,L,C,1,W
        + fire_B,N,N,L,C,1,C + fire_B,N,N,L,C,1,I + fire_B,N,N,L,C,1,A
        + fire_B,N,N,L,C,1,L + fire_B,N,N,L,C,1,N  = 0
 c1285: - 0.002 xEAr_B,N,N,L,C,2,1 + fire_B,N,N,L,C,2,P + fire_B,N,N,L,C,2,D
        + fire_B,N,N,L,C,2,W + fire_B,N,N,L,C,2,C + fire_B,N,N,L,C,2,I
        + fire_B,N,N,L,C,2,A + fire_B,N,N,L,C,2,L + fire_B,N,N,L,C,2,N  = 0
 c1286: - 0.002 xEAr_B,N,N,L,C,3,1 - 0.002 xEAr_B,N,N,L,C,3,2
        + fire_B,N,N,L,C,3,P + fire_B,N,N,L,C,3,D + fire_B,N,N,L,C,3,W
        + fire_B,N,N,L,C,3,C + fire_B,N,N,L,C,3,I + fire_B,N,N,L,C,3,A
        + fire_B,N,N,L,C,3,L + fire_B,N,N,L,C,3,N  = 0
 c1287: - 0.002 xEAr_B,N,N,L,C,4,1 - 0.002 xEAr_B,N,N,L,C,4,2
        - 0.002 xEAr_B,N,N,L,C,4,3 + fire_B,N,N,L,C,4,P + fire_B,N,N,L,C,4,D
        + fire_B,N,N,L,C,4,W + fire_B,N,N,L,C,4,C + fire_B,N,N,L,C,4,I
        + fire_B,N,N,L,C,4,A + fire_B,N,N,L,C,4,L + fire_B,N,N,L,C,4,N  = 0
 c1288: - 0.002 xEAr_B,N,N,L,C,5,1 - 0.002 xEAr_B,N,N,L,C,5,2
        - 0.002 xEAr_B,N,N,L,C,5,3 - 0.002 xEAr_B,N,N,L,C,5,4
        + fire_B,N,N,L,C,5,P + fire_B,N,N,L,C,5,D + fire_B,N,N,L,C,5,W
        + fire_B,N,N,L,C,5,C + fire_B,N,N,L,C,5,I + fire_B,N,N,L,C,5,A
        + fire_B,N,N,L,C,5,L + fire_B,N,N,L,C,5,N  = 0
 c1289: fire_B,N,N,L,I,1,P + fire_B,N,N,L,I,1,D + fire_B,N,N,L,I,1,W
        + fire_B,N,N,L,I,1,C + fire_B,N,N,L,I,1,I + fire_B,N,N,L,I,1,A
        + fire_B,N,N,L,I,1,L + fire_B,N,N,L,I,1,N  = 0
 c1290: - 0.002 xEAr_B,N,N,L,I,2,1 + fire_B,N,N,L,I,2,P + fire_B,N,N,L,I,2,D
        + fire_B,N,N,L,I,2,W + fire_B,N,N,L,I,2,C + fire_B,N,N,L,I,2,I
        + fire_B,N,N,L,I,2,A + fire_B,N,N,L,I,2,L + fire_B,N,N,L,I,2,N  = 0
 c1291: - 0.002 xEAr_B,N,N,L,I,3,1 - 0.002 xEAr_B,N,N,L,I,3,2
        + fire_B,N,N,L,I,3,P + fire_B,N,N,L,I,3,D + fire_B,N,N,L,I,3,W
        + fire_B,N,N,L,I,3,C + fire_B,N,N,L,I,3,I + fire_B,N,N,L,I,3,A
        + fire_B,N,N,L,I,3,L + fire_B,N,N,L,I,3,N  = 0
 c1292: - 0.002 xEAr_B,N,N,L,I,4,1 - 0.002 xEAr_B,N,N,L,I,4,2
        - 0.002 xEAr_B,N,N,L,I,4,3 + fire_B,N,N,L,I,4,P + fire_B,N,N,L,I,4,D
        + fire_B,N,N,L,I,4,W + fire_B,N,N,L,I,4,C + fire_B,N,N,L,I,4,I
        + fire_B,N,N,L,I,4,A + fire_B,N,N,L,I,4,L + fire_B,N,N,L,I,4,N  = 0
 c1293: - 0.002 xEAr_B,N,N,L,I,5,1 - 0.002 xEAr_B,N,N,L,I,5,2
        - 0.002 xEAr_B,N,N,L,I,5,3 - 0.002 xEAr_B,N,N,L,I,5,4
        + fire_B,N,N,L,I,5,P + fire_B,N,N,L,I,5,D + fire_B,N,N,L,I,5,W
        + fire_B,N,N,L,I,5,C + fire_B,N,N,L,I,5,I + fire_B,N,N,L,I,5,A
        + fire_B,N,N,L,I,5,L + fire_B,N,N,L,I,5,N  = 0
 c1294: - 0.002 xNG_B,N,N,L,A,L,1 - 0.002 xPB_B,N,N,L,A,L,0,1
        - 0.002 xPB_B,N,N,L,A,L,1,1 - 0.002 xPB_B,N,N,L,A,L,2,1
        - 0.002 xPB_B,N,N,L,A,L,3,1 - 0.002 xPB_B,N,N,L,A,L,4,1
        - 0.002 xGS_B,N,N,L,A,L,0,1 - 0.002 xGS_B,N,N,L,A,L,1,1
        - 0.002 xGS_B,N,N,L,A,L,2,1 - 0.002 xGS_B,N,N,L,A,L,3,1
        - 0.002 xGS_B,N,N,L,A,L,4,1 - 0.002 xEAe_B,N,N,L,A,L,1
        + fire_B,N,N,L,A,1,P + fire_B,N,N,L,A,1,D + fire_B,N,N,L,A,1,W
        + fire_B,N,N,L,A,1,C + fire_B,N,N,L,A,1,I + fire_B,N,N,L,A,1,A
        + fire_B,N,N,L,A,1,L + fire_B,N,N,L,A,1,N  = 0
 c1295: - 0.002 xNG_B,N,N,L,A,L,2 - 0.002 xPB_B,N,N,L,A,L,0,2
        - 0.002 xPB_B,N,N,L,A,L,1,2 - 0.002 xPB_B,N,N,L,A,L,2,2
        - 0.002 xPB_B,N,N,L,A,L,3,2 - 0.002 xPB_B,N,N,L,A,L,4,2
        - 0.002 xGS_B,N,N,L,A,L,0,2 - 0.002 xGS_B,N,N,L,A,L,1,2
        - 0.002 xGS_B,N,N,L,A,L,2,2 - 0.002 xGS_B,N,N,L,A,L,3,2
        - 0.002 xGS_B,N,N,L,A,L,4,2 - 0.002 xEAe_B,N,N,L,A,L,2
        - 0.002 xEAr_B,N,N,L,A,2,1 + fire_B,N,N,L,A,2,P + fire_B,N,N,L,A,2,D
        + fire_B,N,N,L,A,2,W + fire_B,N,N,L,A,2,C + fire_B,N,N,L,A,2,I
        + fire_B,N,N,L,A,2,A + fire_B,N,N,L,A,2,L + fire_B,N,N,L,A,2,N  = 0
 c1296: - 0.002 xNG_B,N,N,L,A,L,3 - 0.002 xPB_B,N,N,L,A,L,0,3
        - 0.002 xPB_B,N,N,L,A,L,1,3 - 0.002 xPB_B,N,N,L,A,L,2,3
        - 0.002 xPB_B,N,N,L,A,L,3,3 - 0.002 xPB_B,N,N,L,A,L,4,3
        - 0.002 xGS_B,N,N,L,A,L,0,3 - 0.002 xGS_B,N,N,L,A,L,1,3
        - 0.002 xGS_B,N,N,L,A,L,2,3 - 0.002 xGS_B,N,N,L,A,L,3,3
        - 0.002 xGS_B,N,N,L,A,L,4,3 - 0.002 xEAe_B,N,N,L,A,L,3
        - 0.002 xEAr_B,N,N,L,A,3,1 - 0.002 xEAr_B,N,N,L,A,3,2
        + fire_B,N,N,L,A,3,P + fire_B,N,N,L,A,3,D + fire_B,N,N,L,A,3,W
        + fire_B,N,N,L,A,3,C + fire_B,N,N,L,A,3,I + fire_B,N,N,L,A,3,A
        + fire_B,N,N,L,A,3,L + fire_B,N,N,L,A,3,N  = 0
 c1297: - 0.002 xNG_B,N,N,L,A,L,4 - 0.002 xPB_B,N,N,L,A,L,0,4
        - 0.002 xPB_B,N,N,L,A,L,1,4 - 0.002 xPB_B,N,N,L,A,L,2,4
        - 0.002 xPB_B,N,N,L,A,L,3,4 - 0.002 xPB_B,N,N,L,A,L,4,4
        - 0.002 xGS_B,N,N,L,A,L,0,4 - 0.002 xGS_B,N,N,L,A,L,1,4
        - 0.002 xGS_B,N,N,L,A,L,2,4 - 0.002 xGS_B,N,N,L,A,L,3,4
        - 0.002 xGS_B,N,N,L,A,L,4,4 - 0.002 xEAe_B,N,N,L,A,L,4
        - 0.002 xEAr_B,N,N,L,A,4,1 - 0.002 xEAr_B,N,N,L,A,4,2
        - 0.002 xEAr_B,N,N,L,A,4,3 + fire_B,N,N,L,A,4,P + fire_B,N,N,L,A,4,D
        + fire_B,N,N,L,A,4,W + fire_B,N,N,L,A,4,C + fire_B,N,N,L,A,4,I
        + fire_B,N,N,L,A,4,A + fire_B,N,N,L,A,4,L + fire_B,N,N,L,A,4,N  = 0
 c1298: - 0.002 xNG_B,N,N,L,A,L,5 - 0.002 xPB_B,N,N,L,A,L,0,5
        - 0.002 xPB_B,N,N,L,A,L,1,5 - 0.002 xPB_B,N,N,L,A,L,2,5
        - 0.002 xPB_B,N,N,L,A,L,3,5 - 0.002 xPB_B,N,N,L,A,L,4,5
        - 0.002 xGS_B,N,N,L,A,L,0,5 - 0.002 xGS_B,N,N,L,A,L,1,5
        - 0.002 xGS_B,N,N,L,A,L,2,5 - 0.002 xGS_B,N,N,L,A,L,3,5
        - 0.002 xGS_B,N,N,L,A,L,4,5 - 0.002 xEAe_B,N,N,L,A,L,5
        - 0.002 xEAr_B,N,N,L,A,5,1 - 0.002 xEAr_B,N,N,L,A,5,2
        - 0.002 xEAr_B,N,N,L,A,5,3 - 0.002 xEAr_B,N,N,L,A,5,4
        + fire_B,N,N,L,A,5,P + fire_B,N,N,L,A,5,D + fire_B,N,N,L,A,5,W
        + fire_B,N,N,L,A,5,C + fire_B,N,N,L,A,5,I + fire_B,N,N,L,A,5,A
        + fire_B,N,N,L,A,5,L + fire_B,N,N,L,A,5,N  = 0
 c1299: fire_B,N,N,L,L,1,P + fire_B,N,N,L,L,1,D + fire_B,N,N,L,L,1,W
        + fire_B,N,N,L,L,1,C + fire_B,N,N,L,L,1,I + fire_B,N,N,L,L,1,A
        + fire_B,N,N,L,L,1,L + fire_B,N,N,L,L,1,N  = 0
 c1300: - 0.002 xEAr_B,N,N,L,L,2,1 + fire_B,N,N,L,L,2,P + fire_B,N,N,L,L,2,D
        + fire_B,N,N,L,L,2,W + fire_B,N,N,L,L,2,C + fire_B,N,N,L,L,2,I
        + fire_B,N,N,L,L,2,A + fire_B,N,N,L,L,2,L + fire_B,N,N,L,L,2,N  = 0
 c1301: - 0.002 xEAr_B,N,N,L,L,3,1 - 0.002 xEAr_B,N,N,L,L,3,2
        + fire_B,N,N,L,L,3,P + fire_B,N,N,L,L,3,D + fire_B,N,N,L,L,3,W
        + fire_B,N,N,L,L,3,C + fire_B,N,N,L,L,3,I + fire_B,N,N,L,L,3,A
        + fire_B,N,N,L,L,3,L + fire_B,N,N,L,L,3,N  = 0
 c1302: - 0.002 xEAr_B,N,N,L,L,4,1 - 0.002 xEAr_B,N,N,L,L,4,2
        - 0.002 xEAr_B,N,N,L,L,4,3 + fire_B,N,N,L,L,4,P + fire_B,N,N,L,L,4,D
        + fire_B,N,N,L,L,4,W + fire_B,N,N,L,L,4,C + fire_B,N,N,L,L,4,I
        + fire_B,N,N,L,L,4,A + fire_B,N,N,L,L,4,L + fire_B,N,N,L,L,4,N  = 0
 c1303: - 0.002 xEAr_B,N,N,L,L,5,1 - 0.002 xEAr_B,N,N,L,L,5,2
        - 0.002 xEAr_B,N,N,L,L,5,3 - 0.002 xEAr_B,N,N,L,L,5,4
        + fire_B,N,N,L,L,5,P + fire_B,N,N,L,L,5,D + fire_B,N,N,L,L,5,W
        + fire_B,N,N,L,L,5,C + fire_B,N,N,L,L,5,I + fire_B,N,N,L,L,5,A
        + fire_B,N,N,L,L,5,L + fire_B,N,N,L,L,5,N  = 0
 c1304: fire_B,N,N,L,N,1,P + fire_B,N,N,L,N,1,D + fire_B,N,N,L,N,1,W
        + fire_B,N,N,L,N,1,C + fire_B,N,N,L,N,1,I + fire_B,N,N,L,N,1,A
        + fire_B,N,N,L,N,1,L + fire_B,N,N,L,N,1,N  = 0
 c1305: fire_B,N,N,L,N,2,P + fire_B,N,N,L,N,2,D + fire_B,N,N,L,N,2,W
        + fire_B,N,N,L,N,2,C + fire_B,N,N,L,N,2,I + fire_B,N,N,L,N,2,A
        + fire_B,N,N,L,N,2,L + fire_B,N,N,L,N,2,N  = 0
 c1306: fire_B,N,N,L,N,3,P + fire_B,N,N,L,N,3,D + fire_B,N,N,L,N,3,W
        + fire_B,N,N,L,N,3,C + fire_B,N,N,L,N,3,I + fire_B,N,N,L,N,3,A
        + fire_B,N,N,L,N,3,L + fire_B,N,N,L,N,3,N  = 0
 c1307: fire_B,N,N,L,N,4,P + fire_B,N,N,L,N,4,D + fire_B,N,N,L,N,4,W
        + fire_B,N,N,L,N,4,C + fire_B,N,N,L,N,4,I + fire_B,N,N,L,N,4,A
        + fire_B,N,N,L,N,4,L + fire_B,N,N,L,N,4,N  = 0
 c1308: fire_B,N,N,L,N,5,P + fire_B,N,N,L,N,5,D + fire_B,N,N,L,N,5,W
        + fire_B,N,N,L,N,5,C + fire_B,N,N,L,N,5,I + fire_B,N,N,L,N,5,A
        + fire_B,N,N,L,N,5,L + fire_B,N,N,L,N,5,N  = 0
 c1309: fire_B,N,N,C,P,1,P + fire_B,N,N,C,P,1,D + fire_B,N,N,C,P,1,W
        + fire_B,N,N,C,P,1,C + fire_B,N,N,C,P,1,I + fire_B,N,N,C,P,1,A
        + fire_B,N,N,C,P,1,L + fire_B,N,N,C,P,1,N  = 0
 c1310: - 0.002 xEAr_B,N,N,C,P,2,1 + fire_B,N,N,C,P,2,P + fire_B,N,N,C,P,2,D
        + fire_B,N,N,C,P,2,W + fire_B,N,N,C,P,2,C + fire_B,N,N,C,P,2,I
        + fire_B,N,N,C,P,2,A + fire_B,N,N,C,P,2,L + fire_B,N,N,C,P,2,N  = 0
 c1311: - 0.002 xEAr_B,N,N,C,P,3,1 - 0.002 xEAr_B,N,N,C,P,3,2
        + fire_B,N,N,C,P,3,P + fire_B,N,N,C,P,3,D + fire_B,N,N,C,P,3,W
        + fire_B,N,N,C,P,3,C + fire_B,N,N,C,P,3,I + fire_B,N,N,C,P,3,A
        + fire_B,N,N,C,P,3,L + fire_B,N,N,C,P,3,N  = 0
 c1312: - 0.002 xEAr_B,N,N,C,P,4,1 - 0.002 xEAr_B,N,N,C,P,4,2
        - 0.002 xEAr_B,N,N,C,P,4,3 + fire_B,N,N,C,P,4,P + fire_B,N,N,C,P,4,D
        + fire_B,N,N,C,P,4,W + fire_B,N,N,C,P,4,C + fire_B,N,N,C,P,4,I
        + fire_B,N,N,C,P,4,A + fire_B,N,N,C,P,4,L + fire_B,N,N,C,P,4,N  = 0
 c1313: - 0.002 xEAr_B,N,N,C,P,5,1 - 0.002 xEAr_B,N,N,C,P,5,2
        - 0.002 xEAr_B,N,N,C,P,5,3 - 0.002 xEAr_B,N,N,C,P,5,4
        + fire_B,N,N,C,P,5,P + fire_B,N,N,C,P,5,D + fire_B,N,N,C,P,5,W
        + fire_B,N,N,C,P,5,C + fire_B,N,N,C,P,5,I + fire_B,N,N,C,P,5,A
        + fire_B,N,N,C,P,5,L + fire_B,N,N,C,P,5,N  = 0
 c1314: fire_B,N,N,C,D,1,P + fire_B,N,N,C,D,1,D + fire_B,N,N,C,D,1,W
        + fire_B,N,N,C,D,1,C + fire_B,N,N,C,D,1,I + fire_B,N,N,C,D,1,A
        + fire_B,N,N,C,D,1,L + fire_B,N,N,C,D,1,N  = 0
 c1315: - 0.002 xEAr_B,N,N,C,D,2,1 + fire_B,N,N,C,D,2,P + fire_B,N,N,C,D,2,D
        + fire_B,N,N,C,D,2,W + fire_B,N,N,C,D,2,C + fire_B,N,N,C,D,2,I
        + fire_B,N,N,C,D,2,A + fire_B,N,N,C,D,2,L + fire_B,N,N,C,D,2,N  = 0
 c1316: - 0.002 xEAr_B,N,N,C,D,3,1 - 0.002 xEAr_B,N,N,C,D,3,2
        + fire_B,N,N,C,D,3,P + fire_B,N,N,C,D,3,D + fire_B,N,N,C,D,3,W
        + fire_B,N,N,C,D,3,C + fire_B,N,N,C,D,3,I + fire_B,N,N,C,D,3,A
        + fire_B,N,N,C,D,3,L + fire_B,N,N,C,D,3,N  = 0
 c1317: - 0.002 xEAr_B,N,N,C,D,4,1 - 0.002 xEAr_B,N,N,C,D,4,2
        - 0.002 xEAr_B,N,N,C,D,4,3 + fire_B,N,N,C,D,4,P + fire_B,N,N,C,D,4,D
        + fire_B,N,N,C,D,4,W + fire_B,N,N,C,D,4,C + fire_B,N,N,C,D,4,I
        + fire_B,N,N,C,D,4,A + fire_B,N,N,C,D,4,L + fire_B,N,N,C,D,4,N  = 0
 c1318: - 0.002 xEAr_B,N,N,C,D,5,1 - 0.002 xEAr_B,N,N,C,D,5,2
        - 0.002 xEAr_B,N,N,C,D,5,3 - 0.002 xEAr_B,N,N,C,D,5,4
        + fire_B,N,N,C,D,5,P + fire_B,N,N,C,D,5,D + fire_B,N,N,C,D,5,W
        + fire_B,N,N,C,D,5,C + fire_B,N,N,C,D,5,I + fire_B,N,N,C,D,5,A
        + fire_B,N,N,C,D,5,L + fire_B,N,N,C,D,5,N  = 0
 c1319: fire_B,N,N,C,W,1,P + fire_B,N,N,C,W,1,D + fire_B,N,N,C,W,1,W
        + fire_B,N,N,C,W,1,C + fire_B,N,N,C,W,1,I + fire_B,N,N,C,W,1,A
        + fire_B,N,N,C,W,1,L + fire_B,N,N,C,W,1,N  = 0
 c1320: - 0.23 xEAr_B,N,N,C,W,2,1 + fire_B,N,N,C,W,2,P + fire_B,N,N,C,W,2,D
        + fire_B,N,N,C,W,2,W + fire_B,N,N,C,W,2,C + fire_B,N,N,C,W,2,I
        + fire_B,N,N,C,W,2,A + fire_B,N,N,C,W,2,L + fire_B,N,N,C,W,2,N  = 0
 c1321: - 0.23 xEAr_B,N,N,C,W,3,1 - 0.002 xEAr_B,N,N,C,W,3,2
        + fire_B,N,N,C,W,3,P + fire_B,N,N,C,W,3,D + fire_B,N,N,C,W,3,W
        + fire_B,N,N,C,W,3,C + fire_B,N,N,C,W,3,I + fire_B,N,N,C,W,3,A
        + fire_B,N,N,C,W,3,L + fire_B,N,N,C,W,3,N  = 0
 c1322: - 0.23 xEAr_B,N,N,C,W,4,1 - 0.002 xEAr_B,N,N,C,W,4,2
        - 0.002 xEAr_B,N,N,C,W,4,3 + fire_B,N,N,C,W,4,P + fire_B,N,N,C,W,4,D
        + fire_B,N,N,C,W,4,W + fire_B,N,N,C,W,4,C + fire_B,N,N,C,W,4,I
        + fire_B,N,N,C,W,4,A + fire_B,N,N,C,W,4,L + fire_B,N,N,C,W,4,N  = 0
 c1323: - 0.23 xEAr_B,N,N,C,W,5,1 - 0.002 xEAr_B,N,N,C,W,5,2
        - 0.002 xEAr_B,N,N,C,W,5,3 - 0.002 xEAr_B,N,N,C,W,5,4
        + fire_B,N,N,C,W,5,P + fire_B,N,N,C,W,5,D + fire_B,N,N,C,W,5,W
        + fire_B,N,N,C,W,5,C + fire_B,N,N,C,W,5,I + fire_B,N,N,C,W,5,A
        + fire_B,N,N,C,W,5,L + fire_B,N,N,C,W,5,N  = 0
 c1324: fire_B,N,N,C,C,1,P + fire_B,N,N,C,C,1,D + fire_B,N,N,C,C,1,W
        + fire_B,N,N,C,C,1,C + fire_B,N,N,C,C,1,I + fire_B,N,N,C,C,1,A
        + fire_B,N,N,C,C,1,L + fire_B,N,N,C,C,1,N  = 0
 c1325: - 0.002 xEAr_B,N,N,C,C,2,1 + fire_B,N,N,C,C,2,P + fire_B,N,N,C,C,2,D
        + fire_B,N,N,C,C,2,W + fire_B,N,N,C,C,2,C + fire_B,N,N,C,C,2,I
        + fire_B,N,N,C,C,2,A + fire_B,N,N,C,C,2,L + fire_B,N,N,C,C,2,N  = 0
 c1326: - 0.002 xEAr_B,N,N,C,C,3,1 - 0.002 xEAr_B,N,N,C,C,3,2
        + fire_B,N,N,C,C,3,P + fire_B,N,N,C,C,3,D + fire_B,N,N,C,C,3,W
        + fire_B,N,N,C,C,3,C + fire_B,N,N,C,C,3,I + fire_B,N,N,C,C,3,A
        + fire_B,N,N,C,C,3,L + fire_B,N,N,C,C,3,N  = 0
 c1327: - 0.002 xEAr_B,N,N,C,C,4,1 - 0.002 xEAr_B,N,N,C,C,4,2
        - 0.002 xEAr_B,N,N,C,C,4,3 + fire_B,N,N,C,C,4,P + fire_B,N,N,C,C,4,D
        + fire_B,N,N,C,C,4,W + fire_B,N,N,C,C,4,C + fire_B,N,N,C,C,4,I
        + fire_B,N,N,C,C,4,A + fire_B,N,N,C,C,4,L + fire_B,N,N,C,C,4,N  = 0
 c1328: - 0.002 xEAr_B,N,N,C,C,5,1 - 0.002 xEAr_B,N,N,C,C,5,2
        - 0.002 xEAr_B,N,N,C,C,5,3 - 0.002 xEAr_B,N,N,C,C,5,4
        + fire_B,N,N,C,C,5,P + fire_B,N,N,C,C,5,D + fire_B,N,N,C,C,5,W
        + fire_B,N,N,C,C,5,C + fire_B,N,N,C,C,5,I + fire_B,N,N,C,C,5,A
        + fire_B,N,N,C,C,5,L + fire_B,N,N,C,C,5,N  = 0
 c1329: fire_B,N,N,C,I,1,P + fire_B,N,N,C,I,1,D + fire_B,N,N,C,I,1,W
        + fire_B,N,N,C,I,1,C + fire_B,N,N,C,I,1,I + fire_B,N,N,C,I,1,A
        + fire_B,N,N,C,I,1,L + fire_B,N,N,C,I,1,N  = 0
 c1330: - 0.002 xEAr_B,N,N,C,I,2,1 + fire_B,N,N,C,I,2,P + fire_B,N,N,C,I,2,D
        + fire_B,N,N,C,I,2,W + fire_B,N,N,C,I,2,C + fire_B,N,N,C,I,2,I
        + fire_B,N,N,C,I,2,A + fire_B,N,N,C,I,2,L + fire_B,N,N,C,I,2,N  = 0
 c1331: - 0.002 xEAr_B,N,N,C,I,3,1 - 0.002 xEAr_B,N,N,C,I,3,2
        + fire_B,N,N,C,I,3,P + fire_B,N,N,C,I,3,D + fire_B,N,N,C,I,3,W
        + fire_B,N,N,C,I,3,C + fire_B,N,N,C,I,3,I + fire_B,N,N,C,I,3,A
        + fire_B,N,N,C,I,3,L + fire_B,N,N,C,I,3,N  = 0
 c1332: - 0.002 xEAr_B,N,N,C,I,4,1 - 0.002 xEAr_B,N,N,C,I,4,2
        - 0.002 xEAr_B,N,N,C,I,4,3 + fire_B,N,N,C,I,4,P + fire_B,N,N,C,I,4,D
        + fire_B,N,N,C,I,4,W + fire_B,N,N,C,I,4,C + fire_B,N,N,C,I,4,I
        + fire_B,N,N,C,I,4,A + fire_B,N,N,C,I,4,L + fire_B,N,N,C,I,4,N  = 0
 c1333: - 0.002 xEAr_B,N,N,C,I,5,1 - 0.002 xEAr_B,N,N,C,I,5,2
        - 0.002 xEAr_B,N,N,C,I,5,3 - 0.002 xEAr_B,N,N,C,I,5,4
        + fire_B,N,N,C,I,5,P + fire_B,N,N,C,I,5,D + fire_B,N,N,C,I,5,W
        + fire_B,N,N,C,I,5,C + fire_B,N,N,C,I,5,I + fire_B,N,N,C,I,5,A
        + fire_B,N,N,C,I,5,L + fire_B,N,N,C,I,5,N  = 0
 c1334: - 0.002 xNG_B,N,N,C,A,L,1 - 0.002 xPB_B,N,N,C,A,L,0,1
        - 0.002 xPB_B,N,N,C,A,L,1,1 - 0.002 xPB_B,N,N,C,A,L,2,1
        - 0.002 xPB_B,N,N,C,A,L,3,1 - 0.002 xPB_B,N,N,C,A,L,4,1
        - 0.002 xGS_B,N,N,C,A,L,0,1 - 0.002 xGS_B,N,N,C,A,L,1,1
        - 0.002 xGS_B,N,N,C,A,L,2,1 - 0.002 xGS_B,N,N,C,A,L,3,1
        - 0.002 xGS_B,N,N,C,A,L,4,1 - 0.002 xEAe_B,N,N,C,A,L,1
        + fire_B,N,N,C,A,1,P + fire_B,N,N,C,A,1,D + fire_B,N,N,C,A,1,W
        + fire_B,N,N,C,A,1,C + fire_B,N,N,C,A,1,I + fire_B,N,N,C,A,1,A
        + fire_B,N,N,C,A,1,L + fire_B,N,N,C,A,1,N  = 0
 c1335: - 0.002 xNG_B,N,N,C,A,L,2 - 0.002 xPB_B,N,N,C,A,L,0,2
        - 0.002 xPB_B,N,N,C,A,L,1,2 - 0.002 xPB_B,N,N,C,A,L,2,2
        - 0.002 xPB_B,N,N,C,A,L,3,2 - 0.002 xPB_B,N,N,C,A,L,4,2
        - 0.002 xGS_B,N,N,C,A,L,0,2 - 0.002 xGS_B,N,N,C,A,L,1,2
        - 0.002 xGS_B,N,N,C,A,L,2,2 - 0.002 xGS_B,N,N,C,A,L,3,2
        - 0.002 xGS_B,N,N,C,A,L,4,2 - 0.002 xEAe_B,N,N,C,A,L,2
        - 0.002 xEAr_B,N,N,C,A,2,1 + fire_B,N,N,C,A,2,P + fire_B,N,N,C,A,2,D
        + fire_B,N,N,C,A,2,W + fire_B,N,N,C,A,2,C + fire_B,N,N,C,A,2,I
        + fire_B,N,N,C,A,2,A + fire_B,N,N,C,A,2,L + fire_B,N,N,C,A,2,N  = 0
 c1336: - 0.002 xNG_B,N,N,C,A,L,3 - 0.002 xPB_B,N,N,C,A,L,0,3
        - 0.002 xPB_B,N,N,C,A,L,1,3 - 0.002 xPB_B,N,N,C,A,L,2,3
        - 0.002 xPB_B,N,N,C,A,L,3,3 - 0.002 xPB_B,N,N,C,A,L,4,3
        - 0.002 xGS_B,N,N,C,A,L,0,3 - 0.002 xGS_B,N,N,C,A,L,1,3
        - 0.002 xGS_B,N,N,C,A,L,2,3 - 0.002 xGS_B,N,N,C,A,L,3,3
        - 0.002 xGS_B,N,N,C,A,L,4,3 - 0.002 xEAe_B,N,N,C,A,L,3
        - 0.002 xEAr_B,N,N,C,A,3,1 - 0.002 xEAr_B,N,N,C,A,3,2
        + fire_B,N,N,C,A,3,P + fire_B,N,N,C,A,3,D + fire_B,N,N,C,A,3,W
        + fire_B,N,N,C,A,3,C + fire_B,N,N,C,A,3,I + fire_B,N,N,C,A,3,A
        + fire_B,N,N,C,A,3,L + fire_B,N,N,C,A,3,N  = 0
 c1337: - 0.002 xNG_B,N,N,C,A,L,4 - 0.002 xPB_B,N,N,C,A,L,0,4
        - 0.002 xPB_B,N,N,C,A,L,1,4 - 0.002 xPB_B,N,N,C,A,L,2,4
        - 0.002 xPB_B,N,N,C,A,L,3,4 - 0.002 xPB_B,N,N,C,A,L,4,4
        - 0.002 xGS_B,N,N,C,A,L,0,4 - 0.002 xGS_B,N,N,C,A,L,1,4
        - 0.002 xGS_B,N,N,C,A,L,2,4 - 0.002 xGS_B,N,N,C,A,L,3,4
        - 0.002 xGS_B,N,N,C,A,L,4,4 - 0.002 xEAe_B,N,N,C,A,L,4
        - 0.002 xEAr_B,N,N,C,A,4,1 - 0.002 xEAr_B,N,N,C,A,4,2
        - 0.002 xEAr_B,N,N,C,A,4,3 + fire_B,N,N,C,A,4,P + fire_B,N,N,C,A,4,D
        + fire_B,N,N,C,A,4,W + fire_B,N,N,C,A,4,C + fire_B,N,N,C,A,4,I
        + fire_B,N,N,C,A,4,A + fire_B,N,N,C,A,4,L + fire_B,N,N,C,A,4,N  = 0
 c1338: - 0.002 xNG_B,N,N,C,A,L,5 - 0.002 xPB_B,N,N,C,A,L,0,5
        - 0.002 xPB_B,N,N,C,A,L,1,5 - 0.002 xPB_B,N,N,C,A,L,2,5
        - 0.002 xPB_B,N,N,C,A,L,3,5 - 0.002 xPB_B,N,N,C,A,L,4,5
        - 0.002 xGS_B,N,N,C,A,L,0,5 - 0.002 xGS_B,N,N,C,A,L,1,5
        - 0.002 xGS_B,N,N,C,A,L,2,5 - 0.002 xGS_B,N,N,C,A,L,3,5
        - 0.002 xGS_B,N,N,C,A,L,4,5 - 0.002 xEAe_B,N,N,C,A,L,5
        - 0.002 xEAr_B,N,N,C,A,5,1 - 0.002 xEAr_B,N,N,C,A,5,2
        - 0.002 xEAr_B,N,N,C,A,5,3 - 0.002 xEAr_B,N,N,C,A,5,4
        + fire_B,N,N,C,A,5,P + fire_B,N,N,C,A,5,D + fire_B,N,N,C,A,5,W
        + fire_B,N,N,C,A,5,C + fire_B,N,N,C,A,5,I + fire_B,N,N,C,A,5,A
        + fire_B,N,N,C,A,5,L + fire_B,N,N,C,A,5,N  = 0
 c1339: fire_B,N,N,C,L,1,P + fire_B,N,N,C,L,1,D + fire_B,N,N,C,L,1,W
        + fire_B,N,N,C,L,1,C + fire_B,N,N,C,L,1,I + fire_B,N,N,C,L,1,A
        + fire_B,N,N,C,L,1,L + fire_B,N,N,C,L,1,N  = 0
 c1340: - 0.002 xEAr_B,N,N,C,L,2,1 + fire_B,N,N,C,L,2,P + fire_B,N,N,C,L,2,D
        + fire_B,N,N,C,L,2,W + fire_B,N,N,C,L,2,C + fire_B,N,N,C,L,2,I
        + fire_B,N,N,C,L,2,A + fire_B,N,N,C,L,2,L + fire_B,N,N,C,L,2,N  = 0
 c1341: - 0.002 xEAr_B,N,N,C,L,3,1 - 0.002 xEAr_B,N,N,C,L,3,2
        + fire_B,N,N,C,L,3,P + fire_B,N,N,C,L,3,D + fire_B,N,N,C,L,3,W
        + fire_B,N,N,C,L,3,C + fire_B,N,N,C,L,3,I + fire_B,N,N,C,L,3,A
        + fire_B,N,N,C,L,3,L + fire_B,N,N,C,L,3,N  = 0
 c1342: - 0.002 xEAr_B,N,N,C,L,4,1 - 0.002 xEAr_B,N,N,C,L,4,2
        - 0.002 xEAr_B,N,N,C,L,4,3 + fire_B,N,N,C,L,4,P + fire_B,N,N,C,L,4,D
        + fire_B,N,N,C,L,4,W + fire_B,N,N,C,L,4,C + fire_B,N,N,C,L,4,I
        + fire_B,N,N,C,L,4,A + fire_B,N,N,C,L,4,L + fire_B,N,N,C,L,4,N  = 0
 c1343: - 0.002 xEAr_B,N,N,C,L,5,1 - 0.002 xEAr_B,N,N,C,L,5,2
        - 0.002 xEAr_B,N,N,C,L,5,3 - 0.002 xEAr_B,N,N,C,L,5,4
        + fire_B,N,N,C,L,5,P + fire_B,N,N,C,L,5,D + fire_B,N,N,C,L,5,W
        + fire_B,N,N,C,L,5,C + fire_B,N,N,C,L,5,I + fire_B,N,N,C,L,5,A
        + fire_B,N,N,C,L,5,L + fire_B,N,N,C,L,5,N  = 0
 c1344: fire_B,N,N,C,N,1,P + fire_B,N,N,C,N,1,D + fire_B,N,N,C,N,1,W
        + fire_B,N,N,C,N,1,C + fire_B,N,N,C,N,1,I + fire_B,N,N,C,N,1,A
        + fire_B,N,N,C,N,1,L + fire_B,N,N,C,N,1,N  = 0
 c1345: fire_B,N,N,C,N,2,P + fire_B,N,N,C,N,2,D + fire_B,N,N,C,N,2,W
        + fire_B,N,N,C,N,2,C + fire_B,N,N,C,N,2,I + fire_B,N,N,C,N,2,A
        + fire_B,N,N,C,N,2,L + fire_B,N,N,C,N,2,N  = 0
 c1346: fire_B,N,N,C,N,3,P + fire_B,N,N,C,N,3,D + fire_B,N,N,C,N,3,W
        + fire_B,N,N,C,N,3,C + fire_B,N,N,C,N,3,I + fire_B,N,N,C,N,3,A
        + fire_B,N,N,C,N,3,L + fire_B,N,N,C,N,3,N  = 0
 c1347: fire_B,N,N,C,N,4,P + fire_B,N,N,C,N,4,D + fire_B,N,N,C,N,4,W
        + fire_B,N,N,C,N,4,C + fire_B,N,N,C,N,4,I + fire_B,N,N,C,N,4,A
        + fire_B,N,N,C,N,4,L + fire_B,N,N,C,N,4,N  = 0
 c1348: fire_B,N,N,C,N,5,P + fire_B,N,N,C,N,5,D + fire_B,N,N,C,N,5,W
        + fire_B,N,N,C,N,5,C + fire_B,N,N,C,N,5,I + fire_B,N,N,C,N,5,A
        + fire_B,N,N,C,N,5,L + fire_B,N,N,C,N,5,N  = 0
 c1349: fire_B,N,P,R,P,1,P + fire_B,N,P,R,P,1,D + fire_B,N,P,R,P,1,W
        + fire_B,N,P,R,P,1,C + fire_B,N,P,R,P,1,I + fire_B,N,P,R,P,1,A
        + fire_B,N,P,R,P,1,L + fire_B,N,P,R,P,1,N  = 0
 c1350: - 0.002 xEAr_B,N,P,R,P,2,1 + fire_B,N,P,R,P,2,P + fire_B,N,P,R,P,2,D
        + fire_B,N,P,R,P,2,W + fire_B,N,P,R,P,2,C + fire_B,N,P,R,P,2,I
        + fire_B,N,P,R,P,2,A + fire_B,N,P,R,P,2,L + fire_B,N,P,R,P,2,N  = 0
 c1351: - 0.002 xEAr_B,N,P,R,P,3,1 - 0.002 xEAr_B,N,P,R,P,3,2
        + fire_B,N,P,R,P,3,P + fire_B,N,P,R,P,3,D + fire_B,N,P,R,P,3,W
        + fire_B,N,P,R,P,3,C + fire_B,N,P,R,P,3,I + fire_B,N,P,R,P,3,A
        + fire_B,N,P,R,P,3,L + fire_B,N,P,R,P,3,N  = 0
 c1352: - 0.002 xEAr_B,N,P,R,P,4,1 - 0.002 xEAr_B,N,P,R,P,4,2
        - 0.002 xEAr_B,N,P,R,P,4,3 + fire_B,N,P,R,P,4,P + fire_B,N,P,R,P,4,D
        + fire_B,N,P,R,P,4,W + fire_B,N,P,R,P,4,C + fire_B,N,P,R,P,4,I
        + fire_B,N,P,R,P,4,A + fire_B,N,P,R,P,4,L + fire_B,N,P,R,P,4,N  = 0
 c1353: - 0.002 xEAr_B,N,P,R,P,5,1 - 0.002 xEAr_B,N,P,R,P,5,2
        - 0.002 xEAr_B,N,P,R,P,5,3 - 0.002 xEAr_B,N,P,R,P,5,4
        + fire_B,N,P,R,P,5,P + fire_B,N,P,R,P,5,D + fire_B,N,P,R,P,5,W
        + fire_B,N,P,R,P,5,C + fire_B,N,P,R,P,5,I + fire_B,N,P,R,P,5,A
        + fire_B,N,P,R,P,5,L + fire_B,N,P,R,P,5,N  = 0
 c1354: fire_B,N,P,R,D,1,P + fire_B,N,P,R,D,1,D + fire_B,N,P,R,D,1,W
        + fire_B,N,P,R,D,1,C + fire_B,N,P,R,D,1,I + fire_B,N,P,R,D,1,A
        + fire_B,N,P,R,D,1,L + fire_B,N,P,R,D,1,N  = 0
 c1355: - 0.002 xEAr_B,N,P,R,D,2,1 + fire_B,N,P,R,D,2,P + fire_B,N,P,R,D,2,D
        + fire_B,N,P,R,D,2,W + fire_B,N,P,R,D,2,C + fire_B,N,P,R,D,2,I
        + fire_B,N,P,R,D,2,A + fire_B,N,P,R,D,2,L + fire_B,N,P,R,D,2,N  = 0
 c1356: - 0.002 xEAr_B,N,P,R,D,3,1 - 0.002 xEAr_B,N,P,R,D,3,2
        + fire_B,N,P,R,D,3,P + fire_B,N,P,R,D,3,D + fire_B,N,P,R,D,3,W
        + fire_B,N,P,R,D,3,C + fire_B,N,P,R,D,3,I + fire_B,N,P,R,D,3,A
        + fire_B,N,P,R,D,3,L + fire_B,N,P,R,D,3,N  = 0
 c1357: - 0.002 xEAr_B,N,P,R,D,4,1 - 0.002 xEAr_B,N,P,R,D,4,2
        - 0.002 xEAr_B,N,P,R,D,4,3 + fire_B,N,P,R,D,4,P + fire_B,N,P,R,D,4,D
        + fire_B,N,P,R,D,4,W + fire_B,N,P,R,D,4,C + fire_B,N,P,R,D,4,I
        + fire_B,N,P,R,D,4,A + fire_B,N,P,R,D,4,L + fire_B,N,P,R,D,4,N  = 0
 c1358: - 0.002 xEAr_B,N,P,R,D,5,1 - 0.002 xEAr_B,N,P,R,D,5,2
        - 0.002 xEAr_B,N,P,R,D,5,3 - 0.002 xEAr_B,N,P,R,D,5,4
        + fire_B,N,P,R,D,5,P + fire_B,N,P,R,D,5,D + fire_B,N,P,R,D,5,W
        + fire_B,N,P,R,D,5,C + fire_B,N,P,R,D,5,I + fire_B,N,P,R,D,5,A
        + fire_B,N,P,R,D,5,L + fire_B,N,P,R,D,5,N  = 0
 c1359: fire_B,N,P,R,W,1,P + fire_B,N,P,R,W,1,D + fire_B,N,P,R,W,1,W
        + fire_B,N,P,R,W,1,C + fire_B,N,P,R,W,1,I + fire_B,N,P,R,W,1,A
        + fire_B,N,P,R,W,1,L + fire_B,N,P,R,W,1,N  = 0
 c1360: - 0.23 xEAr_B,N,P,R,W,2,1 + fire_B,N,P,R,W,2,P + fire_B,N,P,R,W,2,D
        + fire_B,N,P,R,W,2,W + fire_B,N,P,R,W,2,C + fire_B,N,P,R,W,2,I
        + fire_B,N,P,R,W,2,A + fire_B,N,P,R,W,2,L + fire_B,N,P,R,W,2,N  = 0
 c1361: - 0.23 xEAr_B,N,P,R,W,3,1 - 0.002 xEAr_B,N,P,R,W,3,2
        + fire_B,N,P,R,W,3,P + fire_B,N,P,R,W,3,D + fire_B,N,P,R,W,3,W
        + fire_B,N,P,R,W,3,C + fire_B,N,P,R,W,3,I + fire_B,N,P,R,W,3,A
        + fire_B,N,P,R,W,3,L + fire_B,N,P,R,W,3,N  = 0
 c1362: - 0.23 xEAr_B,N,P,R,W,4,1 - 0.002 xEAr_B,N,P,R,W,4,2
        - 0.002 xEAr_B,N,P,R,W,4,3 + fire_B,N,P,R,W,4,P + fire_B,N,P,R,W,4,D
        + fire_B,N,P,R,W,4,W + fire_B,N,P,R,W,4,C + fire_B,N,P,R,W,4,I
        + fire_B,N,P,R,W,4,A + fire_B,N,P,R,W,4,L + fire_B,N,P,R,W,4,N  = 0
 c1363: - 0.23 xEAr_B,N,P,R,W,5,1 - 0.002 xEAr_B,N,P,R,W,5,2
        - 0.002 xEAr_B,N,P,R,W,5,3 - 0.002 xEAr_B,N,P,R,W,5,4
        + fire_B,N,P,R,W,5,P + fire_B,N,P,R,W,5,D + fire_B,N,P,R,W,5,W
        + fire_B,N,P,R,W,5,C + fire_B,N,P,R,W,5,I + fire_B,N,P,R,W,5,A
        + fire_B,N,P,R,W,5,L + fire_B,N,P,R,W,5,N  = 0
 c1364: fire_B,N,P,R,C,1,P + fire_B,N,P,R,C,1,D + fire_B,N,P,R,C,1,W
        + fire_B,N,P,R,C,1,C + fire_B,N,P,R,C,1,I + fire_B,N,P,R,C,1,A
        + fire_B,N,P,R,C,1,L + fire_B,N,P,R,C,1,N  = 0
 c1365: - 0.002 xEAr_B,N,P,R,C,2,1 + fire_B,N,P,R,C,2,P + fire_B,N,P,R,C,2,D
        + fire_B,N,P,R,C,2,W + fire_B,N,P,R,C,2,C + fire_B,N,P,R,C,2,I
        + fire_B,N,P,R,C,2,A + fire_B,N,P,R,C,2,L + fire_B,N,P,R,C,2,N  = 0
 c1366: - 0.002 xEAr_B,N,P,R,C,3,1 - 0.002 xEAr_B,N,P,R,C,3,2
        + fire_B,N,P,R,C,3,P + fire_B,N,P,R,C,3,D + fire_B,N,P,R,C,3,W
        + fire_B,N,P,R,C,3,C + fire_B,N,P,R,C,3,I + fire_B,N,P,R,C,3,A
        + fire_B,N,P,R,C,3,L + fire_B,N,P,R,C,3,N  = 0
 c1367: - 0.002 xEAr_B,N,P,R,C,4,1 - 0.002 xEAr_B,N,P,R,C,4,2
        - 0.002 xEAr_B,N,P,R,C,4,3 + fire_B,N,P,R,C,4,P + fire_B,N,P,R,C,4,D
        + fire_B,N,P,R,C,4,W + fire_B,N,P,R,C,4,C + fire_B,N,P,R,C,4,I
        + fire_B,N,P,R,C,4,A + fire_B,N,P,R,C,4,L + fire_B,N,P,R,C,4,N  = 0
 c1368: - 0.002 xEAr_B,N,P,R,C,5,1 - 0.002 xEAr_B,N,P,R,C,5,2
        - 0.002 xEAr_B,N,P,R,C,5,3 - 0.002 xEAr_B,N,P,R,C,5,4
        + fire_B,N,P,R,C,5,P + fire_B,N,P,R,C,5,D + fire_B,N,P,R,C,5,W
        + fire_B,N,P,R,C,5,C + fire_B,N,P,R,C,5,I + fire_B,N,P,R,C,5,A
        + fire_B,N,P,R,C,5,L + fire_B,N,P,R,C,5,N  = 0
 c1369: fire_B,N,P,R,I,1,P + fire_B,N,P,R,I,1,D + fire_B,N,P,R,I,1,W
        + fire_B,N,P,R,I,1,C + fire_B,N,P,R,I,1,I + fire_B,N,P,R,I,1,A
        + fire_B,N,P,R,I,1,L + fire_B,N,P,R,I,1,N  = 0
 c1370: - 0.002 xEAr_B,N,P,R,I,2,1 + fire_B,N,P,R,I,2,P + fire_B,N,P,R,I,2,D
        + fire_B,N,P,R,I,2,W + fire_B,N,P,R,I,2,C + fire_B,N,P,R,I,2,I
        + fire_B,N,P,R,I,2,A + fire_B,N,P,R,I,2,L + fire_B,N,P,R,I,2,N  = 0
 c1371: - 0.002 xEAr_B,N,P,R,I,3,1 - 0.002 xEAr_B,N,P,R,I,3,2
        + fire_B,N,P,R,I,3,P + fire_B,N,P,R,I,3,D + fire_B,N,P,R,I,3,W
        + fire_B,N,P,R,I,3,C + fire_B,N,P,R,I,3,I + fire_B,N,P,R,I,3,A
        + fire_B,N,P,R,I,3,L + fire_B,N,P,R,I,3,N  = 0
 c1372: - 0.002 xEAr_B,N,P,R,I,4,1 - 0.002 xEAr_B,N,P,R,I,4,2
        - 0.002 xEAr_B,N,P,R,I,4,3 + fire_B,N,P,R,I,4,P + fire_B,N,P,R,I,4,D
        + fire_B,N,P,R,I,4,W + fire_B,N,P,R,I,4,C + fire_B,N,P,R,I,4,I
        + fire_B,N,P,R,I,4,A + fire_B,N,P,R,I,4,L + fire_B,N,P,R,I,4,N  = 0
 c1373: - 0.002 xEAr_B,N,P,R,I,5,1 - 0.002 xEAr_B,N,P,R,I,5,2
        - 0.002 xEAr_B,N,P,R,I,5,3 - 0.002 xEAr_B,N,P,R,I,5,4
        + fire_B,N,P,R,I,5,P + fire_B,N,P,R,I,5,D + fire_B,N,P,R,I,5,W
        + fire_B,N,P,R,I,5,C + fire_B,N,P,R,I,5,I + fire_B,N,P,R,I,5,A
        + fire_B,N,P,R,I,5,L + fire_B,N,P,R,I,5,N  = 0
 c1374: - 0.002 xNG_B,N,P,R,A,L,1 - 0.002 xPB_B,N,P,R,A,L,0,1
        - 0.002 xPB_B,N,P,R,A,L,1,1 - 0.002 xPB_B,N,P,R,A,L,2,1
        - 0.002 xPB_B,N,P,R,A,L,3,1 - 0.002 xPB_B,N,P,R,A,L,4,1
        - 0.002 xGS_B,N,P,R,A,L,0,1 - 0.002 xGS_B,N,P,R,A,L,1,1
        - 0.002 xGS_B,N,P,R,A,L,2,1 - 0.002 xGS_B,N,P,R,A,L,3,1
        - 0.002 xGS_B,N,P,R,A,L,4,1 - 0.002 xEAe_B,N,P,R,A,L,1
        + fire_B,N,P,R,A,1,P + fire_B,N,P,R,A,1,D + fire_B,N,P,R,A,1,W
        + fire_B,N,P,R,A,1,C + fire_B,N,P,R,A,1,I + fire_B,N,P,R,A,1,A
        + fire_B,N,P,R,A,1,L + fire_B,N,P,R,A,1,N  = 0
 c1375: - 0.002 xNG_B,N,P,R,A,L,2 - 0.002 xPB_B,N,P,R,A,L,0,2
        - 0.002 xPB_B,N,P,R,A,L,1,2 - 0.002 xPB_B,N,P,R,A,L,2,2
        - 0.002 xPB_B,N,P,R,A,L,3,2 - 0.002 xPB_B,N,P,R,A,L,4,2
        - 0.002 xGS_B,N,P,R,A,L,0,2 - 0.002 xGS_B,N,P,R,A,L,1,2
        - 0.002 xGS_B,N,P,R,A,L,2,2 - 0.002 xGS_B,N,P,R,A,L,3,2
        - 0.002 xGS_B,N,P,R,A,L,4,2 - 0.002 xEAe_B,N,P,R,A,L,2
        - 0.002 xEAr_B,N,P,R,A,2,1 + fire_B,N,P,R,A,2,P + fire_B,N,P,R,A,2,D
        + fire_B,N,P,R,A,2,W + fire_B,N,P,R,A,2,C + fire_B,N,P,R,A,2,I
        + fire_B,N,P,R,A,2,A + fire_B,N,P,R,A,2,L + fire_B,N,P,R,A,2,N  = 0
 c1376: - 0.002 xNG_B,N,P,R,A,L,3 - 0.002 xPB_B,N,P,R,A,L,0,3
        - 0.002 xPB_B,N,P,R,A,L,1,3 - 0.002 xPB_B,N,P,R,A,L,2,3
        - 0.002 xPB_B,N,P,R,A,L,3,3 - 0.002 xPB_B,N,P,R,A,L,4,3
        - 0.002 xGS_B,N,P,R,A,L,0,3 - 0.002 xGS_B,N,P,R,A,L,1,3
        - 0.002 xGS_B,N,P,R,A,L,2,3 - 0.002 xGS_B,N,P,R,A,L,3,3
        - 0.002 xGS_B,N,P,R,A,L,4,3 - 0.002 xEAe_B,N,P,R,A,L,3
        - 0.002 xEAr_B,N,P,R,A,3,1 - 0.002 xEAr_B,N,P,R,A,3,2
        + fire_B,N,P,R,A,3,P + fire_B,N,P,R,A,3,D + fire_B,N,P,R,A,3,W
        + fire_B,N,P,R,A,3,C + fire_B,N,P,R,A,3,I + fire_B,N,P,R,A,3,A
        + fire_B,N,P,R,A,3,L + fire_B,N,P,R,A,3,N  = 0
 c1377: - 0.002 xNG_B,N,P,R,A,L,4 - 0.002 xPB_B,N,P,R,A,L,0,4
        - 0.002 xPB_B,N,P,R,A,L,1,4 - 0.002 xPB_B,N,P,R,A,L,2,4
        - 0.002 xPB_B,N,P,R,A,L,3,4 - 0.002 xPB_B,N,P,R,A,L,4,4
        - 0.002 xGS_B,N,P,R,A,L,0,4 - 0.002 xGS_B,N,P,R,A,L,1,4
        - 0.002 xGS_B,N,P,R,A,L,2,4 - 0.002 xGS_B,N,P,R,A,L,3,4
        - 0.002 xGS_B,N,P,R,A,L,4,4 - 0.002 xEAe_B,N,P,R,A,L,4
        - 0.002 xEAr_B,N,P,R,A,4,1 - 0.002 xEAr_B,N,P,R,A,4,2
        - 0.002 xEAr_B,N,P,R,A,4,3 + fire_B,N,P,R,A,4,P + fire_B,N,P,R,A,4,D
        + fire_B,N,P,R,A,4,W + fire_B,N,P,R,A,4,C + fire_B,N,P,R,A,4,I
        + fire_B,N,P,R,A,4,A + fire_B,N,P,R,A,4,L + fire_B,N,P,R,A,4,N  = 0
 c1378: - 0.002 xNG_B,N,P,R,A,L,5 - 0.002 xPB_B,N,P,R,A,L,0,5
        - 0.002 xPB_B,N,P,R,A,L,1,5 - 0.002 xPB_B,N,P,R,A,L,2,5
        - 0.002 xPB_B,N,P,R,A,L,3,5 - 0.002 xPB_B,N,P,R,A,L,4,5
        - 0.002 xGS_B,N,P,R,A,L,0,5 - 0.002 xGS_B,N,P,R,A,L,1,5
        - 0.002 xGS_B,N,P,R,A,L,2,5 - 0.002 xGS_B,N,P,R,A,L,3,5
        - 0.002 xGS_B,N,P,R,A,L,4,5 - 0.002 xEAe_B,N,P,R,A,L,5
        - 0.002 xEAr_B,N,P,R,A,5,1 - 0.002 xEAr_B,N,P,R,A,5,2
        - 0.002 xEAr_B,N,P,R,A,5,3 - 0.002 xEAr_B,N,P,R,A,5,4
        + fire_B,N,P,R,A,5,P + fire_B,N,P,R,A,5,D + fire_B,N,P,R,A,5,W
        + fire_B,N,P,R,A,5,C + fire_B,N,P,R,A,5,I + fire_B,N,P,R,A,5,A
        + fire_B,N,P,R,A,5,L + fire_B,N,P,R,A,5,N  = 0
 c1379: fire_B,N,P,R,L,1,P + fire_B,N,P,R,L,1,D + fire_B,N,P,R,L,1,W
        + fire_B,N,P,R,L,1,C + fire_B,N,P,R,L,1,I + fire_B,N,P,R,L,1,A
        + fire_B,N,P,R,L,1,L + fire_B,N,P,R,L,1,N  = 0
 c1380: - 0.002 xEAr_B,N,P,R,L,2,1 + fire_B,N,P,R,L,2,P + fire_B,N,P,R,L,2,D
        + fire_B,N,P,R,L,2,W + fire_B,N,P,R,L,2,C + fire_B,N,P,R,L,2,I
        + fire_B,N,P,R,L,2,A + fire_B,N,P,R,L,2,L + fire_B,N,P,R,L,2,N  = 0
 c1381: - 0.002 xEAr_B,N,P,R,L,3,1 - 0.002 xEAr_B,N,P,R,L,3,2
        + fire_B,N,P,R,L,3,P + fire_B,N,P,R,L,3,D + fire_B,N,P,R,L,3,W
        + fire_B,N,P,R,L,3,C + fire_B,N,P,R,L,3,I + fire_B,N,P,R,L,3,A
        + fire_B,N,P,R,L,3,L + fire_B,N,P,R,L,3,N  = 0
 c1382: - 0.002 xEAr_B,N,P,R,L,4,1 - 0.002 xEAr_B,N,P,R,L,4,2
        - 0.002 xEAr_B,N,P,R,L,4,3 + fire_B,N,P,R,L,4,P + fire_B,N,P,R,L,4,D
        + fire_B,N,P,R,L,4,W + fire_B,N,P,R,L,4,C + fire_B,N,P,R,L,4,I
        + fire_B,N,P,R,L,4,A + fire_B,N,P,R,L,4,L + fire_B,N,P,R,L,4,N  = 0
 c1383: - 0.002 xEAr_B,N,P,R,L,5,1 - 0.002 xEAr_B,N,P,R,L,5,2
        - 0.002 xEAr_B,N,P,R,L,5,3 - 0.002 xEAr_B,N,P,R,L,5,4
        + fire_B,N,P,R,L,5,P + fire_B,N,P,R,L,5,D + fire_B,N,P,R,L,5,W
        + fire_B,N,P,R,L,5,C + fire_B,N,P,R,L,5,I + fire_B,N,P,R,L,5,A
        + fire_B,N,P,R,L,5,L + fire_B,N,P,R,L,5,N  = 0
 c1384: fire_B,N,P,R,N,1,P + fire_B,N,P,R,N,1,D + fire_B,N,P,R,N,1,W
        + fire_B,N,P,R,N,1,C + fire_B,N,P,R,N,1,I + fire_B,N,P,R,N,1,A
        + fire_B,N,P,R,N,1,L + fire_B,N,P,R,N,1,N  = 0
 c1385: fire_B,N,P,R,N,2,P + fire_B,N,P,R,N,2,D + fire_B,N,P,R,N,2,W
        + fire_B,N,P,R,N,2,C + fire_B,N,P,R,N,2,I + fire_B,N,P,R,N,2,A
        + fire_B,N,P,R,N,2,L + fire_B,N,P,R,N,2,N  = 0
 c1386: fire_B,N,P,R,N,3,P + fire_B,N,P,R,N,3,D + fire_B,N,P,R,N,3,W
        + fire_B,N,P,R,N,3,C + fire_B,N,P,R,N,3,I + fire_B,N,P,R,N,3,A
        + fire_B,N,P,R,N,3,L + fire_B,N,P,R,N,3,N  = 0
 c1387: fire_B,N,P,R,N,4,P + fire_B,N,P,R,N,4,D + fire_B,N,P,R,N,4,W
        + fire_B,N,P,R,N,4,C + fire_B,N,P,R,N,4,I + fire_B,N,P,R,N,4,A
        + fire_B,N,P,R,N,4,L + fire_B,N,P,R,N,4,N  = 0
 c1388: fire_B,N,P,R,N,5,P + fire_B,N,P,R,N,5,D + fire_B,N,P,R,N,5,W
        + fire_B,N,P,R,N,5,C + fire_B,N,P,R,N,5,I + fire_B,N,P,R,N,5,A
        + fire_B,N,P,R,N,5,L + fire_B,N,P,R,N,5,N  = 0
 c1389: fire_C,N,N,L,P,1,P + fire_C,N,N,L,P,1,D + fire_C,N,N,L,P,1,W
        + fire_C,N,N,L,P,1,C + fire_C,N,N,L,P,1,I + fire_C,N,N,L,P,1,A
        + fire_C,N,N,L,P,1,L + fire_C,N,N,L,P,1,N  = 0
 c1390: - 0.002 xEAr_C,N,N,L,P,2,1 + fire_C,N,N,L,P,2,P + fire_C,N,N,L,P,2,D
        + fire_C,N,N,L,P,2,W + fire_C,N,N,L,P,2,C + fire_C,N,N,L,P,2,I
        + fire_C,N,N,L,P,2,A + fire_C,N,N,L,P,2,L + fire_C,N,N,L,P,2,N  = 0
 c1391: - 0.002 xEAr_C,N,N,L,P,3,1 - 0.002 xEAr_C,N,N,L,P,3,2
        + fire_C,N,N,L,P,3,P + fire_C,N,N,L,P,3,D + fire_C,N,N,L,P,3,W
        + fire_C,N,N,L,P,3,C + fire_C,N,N,L,P,3,I + fire_C,N,N,L,P,3,A
        + fire_C,N,N,L,P,3,L + fire_C,N,N,L,P,3,N  = 0
 c1392: - 0.002 xEAr_C,N,N,L,P,4,1 - 0.002 xEAr_C,N,N,L,P,4,2
        - 0.002 xEAr_C,N,N,L,P,4,3 + fire_C,N,N,L,P,4,P + fire_C,N,N,L,P,4,D
        + fire_C,N,N,L,P,4,W + fire_C,N,N,L,P,4,C + fire_C,N,N,L,P,4,I
        + fire_C,N,N,L,P,4,A + fire_C,N,N,L,P,4,L + fire_C,N,N,L,P,4,N  = 0
 c1393: - 0.002 xEAr_C,N,N,L,P,5,1 - 0.002 xEAr_C,N,N,L,P,5,2
        - 0.002 xEAr_C,N,N,L,P,5,3 - 0.002 xEAr_C,N,N,L,P,5,4
        + fire_C,N,N,L,P,5,P + fire_C,N,N,L,P,5,D + fire_C,N,N,L,P,5,W
        + fire_C,N,N,L,P,5,C + fire_C,N,N,L,P,5,I + fire_C,N,N,L,P,5,A
        + fire_C,N,N,L,P,5,L + fire_C,N,N,L,P,5,N  = 0
 c1394: fire_C,N,N,L,D,1,P + fire_C,N,N,L,D,1,D + fire_C,N,N,L,D,1,W
        + fire_C,N,N,L,D,1,C + fire_C,N,N,L,D,1,I + fire_C,N,N,L,D,1,A
        + fire_C,N,N,L,D,1,L + fire_C,N,N,L,D,1,N  = 0
 c1395: - 0.002 xEAr_C,N,N,L,D,2,1 + fire_C,N,N,L,D,2,P + fire_C,N,N,L,D,2,D
        + fire_C,N,N,L,D,2,W + fire_C,N,N,L,D,2,C + fire_C,N,N,L,D,2,I
        + fire_C,N,N,L,D,2,A + fire_C,N,N,L,D,2,L + fire_C,N,N,L,D,2,N  = 0
 c1396: - 0.002 xEAr_C,N,N,L,D,3,1 - 0.002 xEAr_C,N,N,L,D,3,2
        + fire_C,N,N,L,D,3,P + fire_C,N,N,L,D,3,D + fire_C,N,N,L,D,3,W
        + fire_C,N,N,L,D,3,C + fire_C,N,N,L,D,3,I + fire_C,N,N,L,D,3,A
        + fire_C,N,N,L,D,3,L + fire_C,N,N,L,D,3,N  = 0
 c1397: - 0.002 xEAr_C,N,N,L,D,4,1 - 0.002 xEAr_C,N,N,L,D,4,2
        - 0.002 xEAr_C,N,N,L,D,4,3 + fire_C,N,N,L,D,4,P + fire_C,N,N,L,D,4,D
        + fire_C,N,N,L,D,4,W + fire_C,N,N,L,D,4,C + fire_C,N,N,L,D,4,I
        + fire_C,N,N,L,D,4,A + fire_C,N,N,L,D,4,L + fire_C,N,N,L,D,4,N  = 0
 c1398: - 0.002 xEAr_C,N,N,L,D,5,1 - 0.002 xEAr_C,N,N,L,D,5,2
        - 0.002 xEAr_C,N,N,L,D,5,3 - 0.002 xEAr_C,N,N,L,D,5,4
        + fire_C,N,N,L,D,5,P + fire_C,N,N,L,D,5,D + fire_C,N,N,L,D,5,W
        + fire_C,N,N,L,D,5,C + fire_C,N,N,L,D,5,I + fire_C,N,N,L,D,5,A
        + fire_C,N,N,L,D,5,L + fire_C,N,N,L,D,5,N  = 0
 c1399: fire_C,N,N,L,W,1,P + fire_C,N,N,L,W,1,D + fire_C,N,N,L,W,1,W
        + fire_C,N,N,L,W,1,C + fire_C,N,N,L,W,1,I + fire_C,N,N,L,W,1,A
        + fire_C,N,N,L,W,1,L + fire_C,N,N,L,W,1,N  = 0
 c1400: - 0.23 xEAr_C,N,N,L,W,2,1 + fire_C,N,N,L,W,2,P + fire_C,N,N,L,W,2,D
        + fire_C,N,N,L,W,2,W + fire_C,N,N,L,W,2,C + fire_C,N,N,L,W,2,I
        + fire_C,N,N,L,W,2,A + fire_C,N,N,L,W,2,L + fire_C,N,N,L,W,2,N  = 0
 c1401: - 0.23 xEAr_C,N,N,L,W,3,1 - 0.002 xEAr_C,N,N,L,W,3,2
        + fire_C,N,N,L,W,3,P + fire_C,N,N,L,W,3,D + fire_C,N,N,L,W,3,W
        + fire_C,N,N,L,W,3,C + fire_C,N,N,L,W,3,I + fire_C,N,N,L,W,3,A
        + fire_C,N,N,L,W,3,L + fire_C,N,N,L,W,3,N  = 0
 c1402: - 0.23 xEAr_C,N,N,L,W,4,1 - 0.002 xEAr_C,N,N,L,W,4,2
        - 0.002 xEAr_C,N,N,L,W,4,3 + fire_C,N,N,L,W,4,P + fire_C,N,N,L,W,4,D
        + fire_C,N,N,L,W,4,W + fire_C,N,N,L,W,4,C + fire_C,N,N,L,W,4,I
        + fire_C,N,N,L,W,4,A + fire_C,N,N,L,W,4,L + fire_C,N,N,L,W,4,N  = 0
 c1403: - 0.23 xEAr_C,N,N,L,W,5,1 - 0.002 xEAr_C,N,N,L,W,5,2
        - 0.002 xEAr_C,N,N,L,W,5,3 - 0.002 xEAr_C,N,N,L,W,5,4
        + fire_C,N,N,L,W,5,P + fire_C,N,N,L,W,5,D + fire_C,N,N,L,W,5,W
        + fire_C,N,N,L,W,5,C + fire_C,N,N,L,W,5,I + fire_C,N,N,L,W,5,A
        + fire_C,N,N,L,W,5,L + fire_C,N,N,L,W,5,N  = 0
 c1404: fire_C,N,N,L,C,1,P + fire_C,N,N,L,C,1,D + fire_C,N,N,L,C,1,W
        + fire_C,N,N,L,C,1,C + fire_C,N,N,L,C,1,I + fire_C,N,N,L,C,1,A
        + fire_C,N,N,L,C,1,L + fire_C,N,N,L,C,1,N  = 0
 c1405: - 0.002 xEAr_C,N,N,L,C,2,1 + fire_C,N,N,L,C,2,P + fire_C,N,N,L,C,2,D
        + fire_C,N,N,L,C,2,W + fire_C,N,N,L,C,2,C + fire_C,N,N,L,C,2,I
        + fire_C,N,N,L,C,2,A + fire_C,N,N,L,C,2,L + fire_C,N,N,L,C,2,N  = 0
 c1406: - 0.002 xEAr_C,N,N,L,C,3,1 - 0.002 xEAr_C,N,N,L,C,3,2
        + fire_C,N,N,L,C,3,P + fire_C,N,N,L,C,3,D + fire_C,N,N,L,C,3,W
        + fire_C,N,N,L,C,3,C + fire_C,N,N,L,C,3,I + fire_C,N,N,L,C,3,A
        + fire_C,N,N,L,C,3,L + fire_C,N,N,L,C,3,N  = 0
 c1407: - 0.002 xEAr_C,N,N,L,C,4,1 - 0.002 xEAr_C,N,N,L,C,4,2
        - 0.002 xEAr_C,N,N,L,C,4,3 + fire_C,N,N,L,C,4,P + fire_C,N,N,L,C,4,D
        + fire_C,N,N,L,C,4,W + fire_C,N,N,L,C,4,C + fire_C,N,N,L,C,4,I
        + fire_C,N,N,L,C,4,A + fire_C,N,N,L,C,4,L + fire_C,N,N,L,C,4,N  = 0
 c1408: - 0.002 xEAr_C,N,N,L,C,5,1 - 0.002 xEAr_C,N,N,L,C,5,2
        - 0.002 xEAr_C,N,N,L,C,5,3 - 0.002 xEAr_C,N,N,L,C,5,4
        + fire_C,N,N,L,C,5,P + fire_C,N,N,L,C,5,D + fire_C,N,N,L,C,5,W
        + fire_C,N,N,L,C,5,C + fire_C,N,N,L,C,5,I + fire_C,N,N,L,C,5,A
        + fire_C,N,N,L,C,5,L + fire_C,N,N,L,C,5,N  = 0
 c1409: fire_C,N,N,L,I,1,P + fire_C,N,N,L,I,1,D + fire_C,N,N,L,I,1,W
        + fire_C,N,N,L,I,1,C + fire_C,N,N,L,I,1,I + fire_C,N,N,L,I,1,A
        + fire_C,N,N,L,I,1,L + fire_C,N,N,L,I,1,N  = 0
 c1410: - 0.002 xEAr_C,N,N,L,I,2,1 + fire_C,N,N,L,I,2,P + fire_C,N,N,L,I,2,D
        + fire_C,N,N,L,I,2,W + fire_C,N,N,L,I,2,C + fire_C,N,N,L,I,2,I
        + fire_C,N,N,L,I,2,A + fire_C,N,N,L,I,2,L + fire_C,N,N,L,I,2,N  = 0
 c1411: - 0.002 xEAr_C,N,N,L,I,3,1 - 0.002 xEAr_C,N,N,L,I,3,2
        + fire_C,N,N,L,I,3,P + fire_C,N,N,L,I,3,D + fire_C,N,N,L,I,3,W
        + fire_C,N,N,L,I,3,C + fire_C,N,N,L,I,3,I + fire_C,N,N,L,I,3,A
        + fire_C,N,N,L,I,3,L + fire_C,N,N,L,I,3,N  = 0
 c1412: - 0.002 xEAr_C,N,N,L,I,4,1 - 0.002 xEAr_C,N,N,L,I,4,2
        - 0.002 xEAr_C,N,N,L,I,4,3 + fire_C,N,N,L,I,4,P + fire_C,N,N,L,I,4,D
        + fire_C,N,N,L,I,4,W + fire_C,N,N,L,I,4,C + fire_C,N,N,L,I,4,I
        + fire_C,N,N,L,I,4,A + fire_C,N,N,L,I,4,L + fire_C,N,N,L,I,4,N  = 0
 c1413: - 0.002 xEAr_C,N,N,L,I,5,1 - 0.002 xEAr_C,N,N,L,I,5,2
        - 0.002 xEAr_C,N,N,L,I,5,3 - 0.002 xEAr_C,N,N,L,I,5,4
        + fire_C,N,N,L,I,5,P + fire_C,N,N,L,I,5,D + fire_C,N,N,L,I,5,W
        + fire_C,N,N,L,I,5,C + fire_C,N,N,L,I,5,I + fire_C,N,N,L,I,5,A
        + fire_C,N,N,L,I,5,L + fire_C,N,N,L,I,5,N  = 0
 c1414: - 0.002 xNG_C,N,N,L,A,L,1 - 0.002 xPB_C,N,N,L,A,L,0,1
        - 0.002 xPB_C,N,N,L,A,L,1,1 - 0.002 xPB_C,N,N,L,A,L,2,1
        - 0.002 xPB_C,N,N,L,A,L,3,1 - 0.002 xPB_C,N,N,L,A,L,4,1
        - 0.002 xGS_C,N,N,L,A,L,0,1 - 0.002 xGS_C,N,N,L,A,L,1,1
        - 0.002 xGS_C,N,N,L,A,L,2,1 - 0.002 xGS_C,N,N,L,A,L,3,1
        - 0.002 xGS_C,N,N,L,A,L,4,1 - 0.002 xEAe_C,N,N,L,A,L,1
        + fire_C,N,N,L,A,1,P + fire_C,N,N,L,A,1,D + fire_C,N,N,L,A,1,W
        + fire_C,N,N,L,A,1,C + fire_C,N,N,L,A,1,I + fire_C,N,N,L,A,1,A
        + fire_C,N,N,L,A,1,L + fire_C,N,N,L,A,1,N  = 0
 c1415: - 0.002 xNG_C,N,N,L,A,L,2 - 0.002 xPB_C,N,N,L,A,L,0,2
        - 0.002 xPB_C,N,N,L,A,L,1,2 - 0.002 xPB_C,N,N,L,A,L,2,2
        - 0.002 xPB_C,N,N,L,A,L,3,2 - 0.002 xPB_C,N,N,L,A,L,4,2
        - 0.002 xGS_C,N,N,L,A,L,0,2 - 0.002 xGS_C,N,N,L,A,L,1,2
        - 0.002 xGS_C,N,N,L,A,L,2,2 - 0.002 xGS_C,N,N,L,A,L,3,2
        - 0.002 xGS_C,N,N,L,A,L,4,2 - 0.002 xEAe_C,N,N,L,A,L,2
        - 0.002 xEAr_C,N,N,L,A,2,1 + fire_C,N,N,L,A,2,P + fire_C,N,N,L,A,2,D
        + fire_C,N,N,L,A,2,W + fire_C,N,N,L,A,2,C + fire_C,N,N,L,A,2,I
        + fire_C,N,N,L,A,2,A + fire_C,N,N,L,A,2,L + fire_C,N,N,L,A,2,N  = 0
 c1416: - 0.002 xNG_C,N,N,L,A,L,3 - 0.002 xPB_C,N,N,L,A,L,0,3
        - 0.002 xPB_C,N,N,L,A,L,1,3 - 0.002 xPB_C,N,N,L,A,L,2,3
        - 0.002 xPB_C,N,N,L,A,L,3,3 - 0.002 xPB_C,N,N,L,A,L,4,3
        - 0.002 xGS_C,N,N,L,A,L,0,3 - 0.002 xGS_C,N,N,L,A,L,1,3
        - 0.002 xGS_C,N,N,L,A,L,2,3 - 0.002 xGS_C,N,N,L,A,L,3,3
        - 0.002 xGS_C,N,N,L,A,L,4,3 - 0.002 xEAe_C,N,N,L,A,L,3
        - 0.002 xEAr_C,N,N,L,A,3,1 - 0.002 xEAr_C,N,N,L,A,3,2
        + fire_C,N,N,L,A,3,P + fire_C,N,N,L,A,3,D + fire_C,N,N,L,A,3,W
        + fire_C,N,N,L,A,3,C + fire_C,N,N,L,A,3,I + fire_C,N,N,L,A,3,A
        + fire_C,N,N,L,A,3,L + fire_C,N,N,L,A,3,N  = 0
 c1417: - 0.002 xNG_C,N,N,L,A,L,4 - 0.002 xPB_C,N,N,L,A,L,0,4
        - 0.002 xPB_C,N,N,L,A,L,1,4 - 0.002 xPB_C,N,N,L,A,L,2,4
        - 0.002 xPB_C,N,N,L,A,L,3,4 - 0.002 xPB_C,N,N,L,A,L,4,4
        - 0.002 xGS_C,N,N,L,A,L,0,4 - 0.002 xGS_C,N,N,L,A,L,1,4
        - 0.002 xGS_C,N,N,L,A,L,2,4 - 0.002 xGS_C,N,N,L,A,L,3,4
        - 0.002 xGS_C,N,N,L,A,L,4,4 - 0.002 xEAe_C,N,N,L,A,L,4
        - 0.002 xEAr_C,N,N,L,A,4,1 - 0.002 xEAr_C,N,N,L,A,4,2
        - 0.002 xEAr_C,N,N,L,A,4,3 + fire_C,N,N,L,A,4,P + fire_C,N,N,L,A,4,D
        + fire_C,N,N,L,A,4,W + fire_C,N,N,L,A,4,C + fire_C,N,N,L,A,4,I
        + fire_C,N,N,L,A,4,A + fire_C,N,N,L,A,4,L + fire_C,N,N,L,A,4,N  = 0
 c1418: - 0.002 xNG_C,N,N,L,A,L,5 - 0.002 xPB_C,N,N,L,A,L,0,5
        - 0.002 xPB_C,N,N,L,A,L,1,5 - 0.002 xPB_C,N,N,L,A,L,2,5
        - 0.002 xPB_C,N,N,L,A,L,3,5 - 0.002 xPB_C,N,N,L,A,L,4,5
        - 0.002 xGS_C,N,N,L,A,L,0,5 - 0.002 xGS_C,N,N,L,A,L,1,5
        - 0.002 xGS_C,N,N,L,A,L,2,5 - 0.002 xGS_C,N,N,L,A,L,3,5
        - 0.002 xGS_C,N,N,L,A,L,4,5 - 0.002 xEAe_C,N,N,L,A,L,5
        - 0.002 xEAr_C,N,N,L,A,5,1 - 0.002 xEAr_C,N,N,L,A,5,2
        - 0.002 xEAr_C,N,N,L,A,5,3 - 0.002 xEAr_C,N,N,L,A,5,4
        + fire_C,N,N,L,A,5,P + fire_C,N,N,L,A,5,D + fire_C,N,N,L,A,5,W
        + fire_C,N,N,L,A,5,C + fire_C,N,N,L,A,5,I + fire_C,N,N,L,A,5,A
        + fire_C,N,N,L,A,5,L + fire_C,N,N,L,A,5,N  = 0
 c1419: fire_C,N,N,L,L,1,P + fire_C,N,N,L,L,1,D + fire_C,N,N,L,L,1,W
        + fire_C,N,N,L,L,1,C + fire_C,N,N,L,L,1,I + fire_C,N,N,L,L,1,A
        + fire_C,N,N,L,L,1,L + fire_C,N,N,L,L,1,N  = 0
 c1420: - 0.002 xEAr_C,N,N,L,L,2,1 + fire_C,N,N,L,L,2,P + fire_C,N,N,L,L,2,D
        + fire_C,N,N,L,L,2,W + fire_C,N,N,L,L,2,C + fire_C,N,N,L,L,2,I
        + fire_C,N,N,L,L,2,A + fire_C,N,N,L,L,2,L + fire_C,N,N,L,L,2,N  = 0
 c1421: - 0.002 xEAr_C,N,N,L,L,3,1 - 0.002 xEAr_C,N,N,L,L,3,2
        + fire_C,N,N,L,L,3,P + fire_C,N,N,L,L,3,D + fire_C,N,N,L,L,3,W
        + fire_C,N,N,L,L,3,C + fire_C,N,N,L,L,3,I + fire_C,N,N,L,L,3,A
        + fire_C,N,N,L,L,3,L + fire_C,N,N,L,L,3,N  = 0
 c1422: - 0.002 xEAr_C,N,N,L,L,4,1 - 0.002 xEAr_C,N,N,L,L,4,2
        - 0.002 xEAr_C,N,N,L,L,4,3 + fire_C,N,N,L,L,4,P + fire_C,N,N,L,L,4,D
        + fire_C,N,N,L,L,4,W + fire_C,N,N,L,L,4,C + fire_C,N,N,L,L,4,I
        + fire_C,N,N,L,L,4,A + fire_C,N,N,L,L,4,L + fire_C,N,N,L,L,4,N  = 0
 c1423: - 0.002 xEAr_C,N,N,L,L,5,1 - 0.002 xEAr_C,N,N,L,L,5,2
        - 0.002 xEAr_C,N,N,L,L,5,3 - 0.002 xEAr_C,N,N,L,L,5,4
        + fire_C,N,N,L,L,5,P + fire_C,N,N,L,L,5,D + fire_C,N,N,L,L,5,W
        + fire_C,N,N,L,L,5,C + fire_C,N,N,L,L,5,I + fire_C,N,N,L,L,5,A
        + fire_C,N,N,L,L,5,L + fire_C,N,N,L,L,5,N  = 0
 c1424: fire_C,N,N,L,N,1,P + fire_C,N,N,L,N,1,D + fire_C,N,N,L,N,1,W
        + fire_C,N,N,L,N,1,C + fire_C,N,N,L,N,1,I + fire_C,N,N,L,N,1,A
        + fire_C,N,N,L,N,1,L + fire_C,N,N,L,N,1,N  = 0
 c1425: fire_C,N,N,L,N,2,P + fire_C,N,N,L,N,2,D + fire_C,N,N,L,N,2,W
        + fire_C,N,N,L,N,2,C + fire_C,N,N,L,N,2,I + fire_C,N,N,L,N,2,A
        + fire_C,N,N,L,N,2,L + fire_C,N,N,L,N,2,N  = 0
 c1426: fire_C,N,N,L,N,3,P + fire_C,N,N,L,N,3,D + fire_C,N,N,L,N,3,W
        + fire_C,N,N,L,N,3,C + fire_C,N,N,L,N,3,I + fire_C,N,N,L,N,3,A
        + fire_C,N,N,L,N,3,L + fire_C,N,N,L,N,3,N  = 0
 c1427: fire_C,N,N,L,N,4,P + fire_C,N,N,L,N,4,D + fire_C,N,N,L,N,4,W
        + fire_C,N,N,L,N,4,C + fire_C,N,N,L,N,4,I + fire_C,N,N,L,N,4,A
        + fire_C,N,N,L,N,4,L + fire_C,N,N,L,N,4,N  = 0
 c1428: fire_C,N,N,L,N,5,P + fire_C,N,N,L,N,5,D + fire_C,N,N,L,N,5,W
        + fire_C,N,N,L,N,5,C + fire_C,N,N,L,N,5,I + fire_C,N,N,L,N,5,A
        + fire_C,N,N,L,N,5,L + fire_C,N,N,L,N,5,N  = 0
 c1429: fire_C,N,N,H,P,1,P + fire_C,N,N,H,P,1,D + fire_C,N,N,H,P,1,W
        + fire_C,N,N,H,P,1,C + fire_C,N,N,H,P,1,I + fire_C,N,N,H,P,1,A
        + fire_C,N,N,H,P,1,L + fire_C,N,N,H,P,1,N  = 0
 c1430: - 0.002 xEAr_C,N,N,H,P,2,1 + fire_C,N,N,H,P,2,P + fire_C,N,N,H,P,2,D
        + fire_C,N,N,H,P,2,W + fire_C,N,N,H,P,2,C + fire_C,N,N,H,P,2,I
        + fire_C,N,N,H,P,2,A + fire_C,N,N,H,P,2,L + fire_C,N,N,H,P,2,N  = 0
 c1431: - 0.002 xEAr_C,N,N,H,P,3,1 - 0.002 xEAr_C,N,N,H,P,3,2
        + fire_C,N,N,H,P,3,P + fire_C,N,N,H,P,3,D + fire_C,N,N,H,P,3,W
        + fire_C,N,N,H,P,3,C + fire_C,N,N,H,P,3,I + fire_C,N,N,H,P,3,A
        + fire_C,N,N,H,P,3,L + fire_C,N,N,H,P,3,N  = 0
 c1432: - 0.002 xEAr_C,N,N,H,P,4,1 - 0.002 xEAr_C,N,N,H,P,4,2
        - 0.002 xEAr_C,N,N,H,P,4,3 + fire_C,N,N,H,P,4,P + fire_C,N,N,H,P,4,D
        + fire_C,N,N,H,P,4,W + fire_C,N,N,H,P,4,C + fire_C,N,N,H,P,4,I
        + fire_C,N,N,H,P,4,A + fire_C,N,N,H,P,4,L + fire_C,N,N,H,P,4,N  = 0
 c1433: - 0.002 xEAr_C,N,N,H,P,5,1 - 0.002 xEAr_C,N,N,H,P,5,2
        - 0.002 xEAr_C,N,N,H,P,5,3 - 0.002 xEAr_C,N,N,H,P,5,4
        + fire_C,N,N,H,P,5,P + fire_C,N,N,H,P,5,D + fire_C,N,N,H,P,5,W
        + fire_C,N,N,H,P,5,C + fire_C,N,N,H,P,5,I + fire_C,N,N,H,P,5,A
        + fire_C,N,N,H,P,5,L + fire_C,N,N,H,P,5,N  = 0
 c1434: fire_C,N,N,H,D,1,P + fire_C,N,N,H,D,1,D + fire_C,N,N,H,D,1,W
        + fire_C,N,N,H,D,1,C + fire_C,N,N,H,D,1,I + fire_C,N,N,H,D,1,A
        + fire_C,N,N,H,D,1,L + fire_C,N,N,H,D,1,N  = 0
 c1435: - 0.002 xEAr_C,N,N,H,D,2,1 + fire_C,N,N,H,D,2,P + fire_C,N,N,H,D,2,D
        + fire_C,N,N,H,D,2,W + fire_C,N,N,H,D,2,C + fire_C,N,N,H,D,2,I
        + fire_C,N,N,H,D,2,A + fire_C,N,N,H,D,2,L + fire_C,N,N,H,D,2,N  = 0
 c1436: - 0.002 xEAr_C,N,N,H,D,3,1 - 0.002 xEAr_C,N,N,H,D,3,2
        + fire_C,N,N,H,D,3,P + fire_C,N,N,H,D,3,D + fire_C,N,N,H,D,3,W
        + fire_C,N,N,H,D,3,C + fire_C,N,N,H,D,3,I + fire_C,N,N,H,D,3,A
        + fire_C,N,N,H,D,3,L + fire_C,N,N,H,D,3,N  = 0
 c1437: - 0.002 xEAr_C,N,N,H,D,4,1 - 0.002 xEAr_C,N,N,H,D,4,2
        - 0.002 xEAr_C,N,N,H,D,4,3 + fire_C,N,N,H,D,4,P + fire_C,N,N,H,D,4,D
        + fire_C,N,N,H,D,4,W + fire_C,N,N,H,D,4,C + fire_C,N,N,H,D,4,I
        + fire_C,N,N,H,D,4,A + fire_C,N,N,H,D,4,L + fire_C,N,N,H,D,4,N  = 0
 c1438: - 0.002 xEAr_C,N,N,H,D,5,1 - 0.002 xEAr_C,N,N,H,D,5,2
        - 0.002 xEAr_C,N,N,H,D,5,3 - 0.002 xEAr_C,N,N,H,D,5,4
        + fire_C,N,N,H,D,5,P + fire_C,N,N,H,D,5,D + fire_C,N,N,H,D,5,W
        + fire_C,N,N,H,D,5,C + fire_C,N,N,H,D,5,I + fire_C,N,N,H,D,5,A
        + fire_C,N,N,H,D,5,L + fire_C,N,N,H,D,5,N  = 0
 c1439: fire_C,N,N,H,W,1,P + fire_C,N,N,H,W,1,D + fire_C,N,N,H,W,1,W
        + fire_C,N,N,H,W,1,C + fire_C,N,N,H,W,1,I + fire_C,N,N,H,W,1,A
        + fire_C,N,N,H,W,1,L + fire_C,N,N,H,W,1,N  = 0
 c1440: - 0.23 xEAr_C,N,N,H,W,2,1 + fire_C,N,N,H,W,2,P + fire_C,N,N,H,W,2,D
        + fire_C,N,N,H,W,2,W + fire_C,N,N,H,W,2,C + fire_C,N,N,H,W,2,I
        + fire_C,N,N,H,W,2,A + fire_C,N,N,H,W,2,L + fire_C,N,N,H,W,2,N  = 0
 c1441: - 0.23 xEAr_C,N,N,H,W,3,1 - 0.002 xEAr_C,N,N,H,W,3,2
        + fire_C,N,N,H,W,3,P + fire_C,N,N,H,W,3,D + fire_C,N,N,H,W,3,W
        + fire_C,N,N,H,W,3,C + fire_C,N,N,H,W,3,I + fire_C,N,N,H,W,3,A
        + fire_C,N,N,H,W,3,L + fire_C,N,N,H,W,3,N  = 0
 c1442: - 0.23 xEAr_C,N,N,H,W,4,1 - 0.002 xEAr_C,N,N,H,W,4,2
        - 0.002 xEAr_C,N,N,H,W,4,3 + fire_C,N,N,H,W,4,P + fire_C,N,N,H,W,4,D
        + fire_C,N,N,H,W,4,W + fire_C,N,N,H,W,4,C + fire_C,N,N,H,W,4,I
        + fire_C,N,N,H,W,4,A + fire_C,N,N,H,W,4,L + fire_C,N,N,H,W,4,N  = 0
 c1443: - 0.23 xEAr_C,N,N,H,W,5,1 - 0.002 xEAr_C,N,N,H,W,5,2
        - 0.002 xEAr_C,N,N,H,W,5,3 - 0.002 xEAr_C,N,N,H,W,5,4
        + fire_C,N,N,H,W,5,P + fire_C,N,N,H,W,5,D + fire_C,N,N,H,W,5,W
        + fire_C,N,N,H,W,5,C + fire_C,N,N,H,W,5,I + fire_C,N,N,H,W,5,A
        + fire_C,N,N,H,W,5,L + fire_C,N,N,H,W,5,N  = 0
 c1444: fire_C,N,N,H,C,1,P + fire_C,N,N,H,C,1,D + fire_C,N,N,H,C,1,W
        + fire_C,N,N,H,C,1,C + fire_C,N,N,H,C,1,I + fire_C,N,N,H,C,1,A
        + fire_C,N,N,H,C,1,L + fire_C,N,N,H,C,1,N  = 0
 c1445: - 0.002 xEAr_C,N,N,H,C,2,1 + fire_C,N,N,H,C,2,P + fire_C,N,N,H,C,2,D
        + fire_C,N,N,H,C,2,W + fire_C,N,N,H,C,2,C + fire_C,N,N,H,C,2,I
        + fire_C,N,N,H,C,2,A + fire_C,N,N,H,C,2,L + fire_C,N,N,H,C,2,N  = 0
 c1446: - 0.002 xEAr_C,N,N,H,C,3,1 - 0.002 xEAr_C,N,N,H,C,3,2
        + fire_C,N,N,H,C,3,P + fire_C,N,N,H,C,3,D + fire_C,N,N,H,C,3,W
        + fire_C,N,N,H,C,3,C + fire_C,N,N,H,C,3,I + fire_C,N,N,H,C,3,A
        + fire_C,N,N,H,C,3,L + fire_C,N,N,H,C,3,N  = 0
 c1447: - 0.002 xEAr_C,N,N,H,C,4,1 - 0.002 xEAr_C,N,N,H,C,4,2
        - 0.002 xEAr_C,N,N,H,C,4,3 + fire_C,N,N,H,C,4,P + fire_C,N,N,H,C,4,D
        + fire_C,N,N,H,C,4,W + fire_C,N,N,H,C,4,C + fire_C,N,N,H,C,4,I
        + fire_C,N,N,H,C,4,A + fire_C,N,N,H,C,4,L + fire_C,N,N,H,C,4,N  = 0
 c1448: - 0.002 xEAr_C,N,N,H,C,5,1 - 0.002 xEAr_C,N,N,H,C,5,2
        - 0.002 xEAr_C,N,N,H,C,5,3 - 0.002 xEAr_C,N,N,H,C,5,4
        + fire_C,N,N,H,C,5,P + fire_C,N,N,H,C,5,D + fire_C,N,N,H,C,5,W
        + fire_C,N,N,H,C,5,C + fire_C,N,N,H,C,5,I + fire_C,N,N,H,C,5,A
        + fire_C,N,N,H,C,5,L + fire_C,N,N,H,C,5,N  = 0
 c1449: fire_C,N,N,H,I,1,P + fire_C,N,N,H,I,1,D + fire_C,N,N,H,I,1,W
        + fire_C,N,N,H,I,1,C + fire_C,N,N,H,I,1,I + fire_C,N,N,H,I,1,A
        + fire_C,N,N,H,I,1,L + fire_C,N,N,H,I,1,N  = 0
 c1450: - 0.002 xEAr_C,N,N,H,I,2,1 + fire_C,N,N,H,I,2,P + fire_C,N,N,H,I,2,D
        + fire_C,N,N,H,I,2,W + fire_C,N,N,H,I,2,C + fire_C,N,N,H,I,2,I
        + fire_C,N,N,H,I,2,A + fire_C,N,N,H,I,2,L + fire_C,N,N,H,I,2,N  = 0
 c1451: - 0.002 xEAr_C,N,N,H,I,3,1 - 0.002 xEAr_C,N,N,H,I,3,2
        + fire_C,N,N,H,I,3,P + fire_C,N,N,H,I,3,D + fire_C,N,N,H,I,3,W
        + fire_C,N,N,H,I,3,C + fire_C,N,N,H,I,3,I + fire_C,N,N,H,I,3,A
        + fire_C,N,N,H,I,3,L + fire_C,N,N,H,I,3,N  = 0
 c1452: - 0.002 xEAr_C,N,N,H,I,4,1 - 0.002 xEAr_C,N,N,H,I,4,2
        - 0.002 xEAr_C,N,N,H,I,4,3 + fire_C,N,N,H,I,4,P + fire_C,N,N,H,I,4,D
        + fire_C,N,N,H,I,4,W + fire_C,N,N,H,I,4,C + fire_C,N,N,H,I,4,I
        + fire_C,N,N,H,I,4,A + fire_C,N,N,H,I,4,L + fire_C,N,N,H,I,4,N  = 0
 c1453: - 0.002 xEAr_C,N,N,H,I,5,1 - 0.002 xEAr_C,N,N,H,I,5,2
        - 0.002 xEAr_C,N,N,H,I,5,3 - 0.002 xEAr_C,N,N,H,I,5,4
        + fire_C,N,N,H,I,5,P + fire_C,N,N,H,I,5,D + fire_C,N,N,H,I,5,W
        + fire_C,N,N,H,I,5,C + fire_C,N,N,H,I,5,I + fire_C,N,N,H,I,5,A
        + fire_C,N,N,H,I,5,L + fire_C,N,N,H,I,5,N  = 0
 c1454: - 0.002 xNG_C,N,N,H,A,L,1 - 0.002 xPB_C,N,N,H,A,L,0,1
        - 0.002 xPB_C,N,N,H,A,L,1,1 - 0.002 xPB_C,N,N,H,A,L,2,1
        - 0.002 xPB_C,N,N,H,A,L,3,1 - 0.002 xPB_C,N,N,H,A,L,4,1
        - 0.002 xGS_C,N,N,H,A,L,0,1 - 0.002 xGS_C,N,N,H,A,L,1,1
        - 0.002 xGS_C,N,N,H,A,L,2,1 - 0.002 xGS_C,N,N,H,A,L,3,1
        - 0.002 xGS_C,N,N,H,A,L,4,1 - 0.002 xEAe_C,N,N,H,A,L,1
        + fire_C,N,N,H,A,1,P + fire_C,N,N,H,A,1,D + fire_C,N,N,H,A,1,W
        + fire_C,N,N,H,A,1,C + fire_C,N,N,H,A,1,I + fire_C,N,N,H,A,1,A
        + fire_C,N,N,H,A,1,L + fire_C,N,N,H,A,1,N  = 0
 c1455: - 0.002 xNG_C,N,N,H,A,L,2 - 0.002 xPB_C,N,N,H,A,L,0,2
        - 0.002 xPB_C,N,N,H,A,L,1,2 - 0.002 xPB_C,N,N,H,A,L,2,2
        - 0.002 xPB_C,N,N,H,A,L,3,2 - 0.002 xPB_C,N,N,H,A,L,4,2
        - 0.002 xGS_C,N,N,H,A,L,0,2 - 0.002 xGS_C,N,N,H,A,L,1,2
        - 0.002 xGS_C,N,N,H,A,L,2,2 - 0.002 xGS_C,N,N,H,A,L,3,2
        - 0.002 xGS_C,N,N,H,A,L,4,2 - 0.002 xEAe_C,N,N,H,A,L,2
        - 0.002 xEAr_C,N,N,H,A,2,1 + fire_C,N,N,H,A,2,P + fire_C,N,N,H,A,2,D
        + fire_C,N,N,H,A,2,W + fire_C,N,N,H,A,2,C + fire_C,N,N,H,A,2,I
        + fire_C,N,N,H,A,2,A + fire_C,N,N,H,A,2,L + fire_C,N,N,H,A,2,N  = 0
 c1456: - 0.002 xNG_C,N,N,H,A,L,3 - 0.002 xPB_C,N,N,H,A,L,0,3
        - 0.002 xPB_C,N,N,H,A,L,1,3 - 0.002 xPB_C,N,N,H,A,L,2,3
        - 0.002 xPB_C,N,N,H,A,L,3,3 - 0.002 xPB_C,N,N,H,A,L,4,3
        - 0.002 xGS_C,N,N,H,A,L,0,3 - 0.002 xGS_C,N,N,H,A,L,1,3
        - 0.002 xGS_C,N,N,H,A,L,2,3 - 0.002 xGS_C,N,N,H,A,L,3,3
        - 0.002 xGS_C,N,N,H,A,L,4,3 - 0.002 xEAe_C,N,N,H,A,L,3
        - 0.002 xEAr_C,N,N,H,A,3,1 - 0.002 xEAr_C,N,N,H,A,3,2
        + fire_C,N,N,H,A,3,P + fire_C,N,N,H,A,3,D + fire_C,N,N,H,A,3,W
        + fire_C,N,N,H,A,3,C + fire_C,N,N,H,A,3,I + fire_C,N,N,H,A,3,A
        + fire_C,N,N,H,A,3,L + fire_C,N,N,H,A,3,N  = 0
 c1457: - 0.002 xNG_C,N,N,H,A,L,4 - 0.002 xPB_C,N,N,H,A,L,0,4
        - 0.002 xPB_C,N,N,H,A,L,1,4 - 0.002 xPB_C,N,N,H,A,L,2,4
        - 0.002 xPB_C,N,N,H,A,L,3,4 - 0.002 xPB_C,N,N,H,A,L,4,4
        - 0.002 xGS_C,N,N,H,A,L,0,4 - 0.002 xGS_C,N,N,H,A,L,1,4
        - 0.002 xGS_C,N,N,H,A,L,2,4 - 0.002 xGS_C,N,N,H,A,L,3,4
        - 0.002 xGS_C,N,N,H,A,L,4,4 - 0.002 xEAe_C,N,N,H,A,L,4
        - 0.002 xEAr_C,N,N,H,A,4,1 - 0.002 xEAr_C,N,N,H,A,4,2
        - 0.002 xEAr_C,N,N,H,A,4,3 + fire_C,N,N,H,A,4,P + fire_C,N,N,H,A,4,D
        + fire_C,N,N,H,A,4,W + fire_C,N,N,H,A,4,C + fire_C,N,N,H,A,4,I
        + fire_C,N,N,H,A,4,A + fire_C,N,N,H,A,4,L + fire_C,N,N,H,A,4,N  = 0
 c1458: - 0.002 xNG_C,N,N,H,A,L,5 - 0.002 xPB_C,N,N,H,A,L,0,5
        - 0.002 xPB_C,N,N,H,A,L,1,5 - 0.002 xPB_C,N,N,H,A,L,2,5
        - 0.002 xPB_C,N,N,H,A,L,3,5 - 0.002 xPB_C,N,N,H,A,L,4,5
        - 0.002 xGS_C,N,N,H,A,L,0,5 - 0.002 xGS_C,N,N,H,A,L,1,5
        - 0.002 xGS_C,N,N,H,A,L,2,5 - 0.002 xGS_C,N,N,H,A,L,3,5
        - 0.002 xGS_C,N,N,H,A,L,4,5 - 0.002 xEAe_C,N,N,H,A,L,5
        - 0.002 xEAr_C,N,N,H,A,5,1 - 0.002 xEAr_C,N,N,H,A,5,2
        - 0.002 xEAr_C,N,N,H,A,5,3 - 0.002 xEAr_C,N,N,H,A,5,4
        + fire_C,N,N,H,A,5,P + fire_C,N,N,H,A,5,D + fire_C,N,N,H,A,5,W
        + fire_C,N,N,H,A,5,C + fire_C,N,N,H,A,5,I + fire_C,N,N,H,A,5,A
        + fire_C,N,N,H,A,5,L + fire_C,N,N,H,A,5,N  = 0
 c1459: fire_C,N,N,H,L,1,P + fire_C,N,N,H,L,1,D + fire_C,N,N,H,L,1,W
        + fire_C,N,N,H,L,1,C + fire_C,N,N,H,L,1,I + fire_C,N,N,H,L,1,A
        + fire_C,N,N,H,L,1,L + fire_C,N,N,H,L,1,N  = 0
 c1460: - 0.002 xEAr_C,N,N,H,L,2,1 + fire_C,N,N,H,L,2,P + fire_C,N,N,H,L,2,D
        + fire_C,N,N,H,L,2,W + fire_C,N,N,H,L,2,C + fire_C,N,N,H,L,2,I
        + fire_C,N,N,H,L,2,A + fire_C,N,N,H,L,2,L + fire_C,N,N,H,L,2,N  = 0
 c1461: - 0.002 xEAr_C,N,N,H,L,3,1 - 0.002 xEAr_C,N,N,H,L,3,2
        + fire_C,N,N,H,L,3,P + fire_C,N,N,H,L,3,D + fire_C,N,N,H,L,3,W
        + fire_C,N,N,H,L,3,C + fire_C,N,N,H,L,3,I + fire_C,N,N,H,L,3,A
        + fire_C,N,N,H,L,3,L + fire_C,N,N,H,L,3,N  = 0
 c1462: - 0.002 xEAr_C,N,N,H,L,4,1 - 0.002 xEAr_C,N,N,H,L,4,2
        - 0.002 xEAr_C,N,N,H,L,4,3 + fire_C,N,N,H,L,4,P + fire_C,N,N,H,L,4,D
        + fire_C,N,N,H,L,4,W + fire_C,N,N,H,L,4,C + fire_C,N,N,H,L,4,I
        + fire_C,N,N,H,L,4,A + fire_C,N,N,H,L,4,L + fire_C,N,N,H,L,4,N  = 0
 c1463: - 0.002 xEAr_C,N,N,H,L,5,1 - 0.002 xEAr_C,N,N,H,L,5,2
        - 0.002 xEAr_C,N,N,H,L,5,3 - 0.002 xEAr_C,N,N,H,L,5,4
        + fire_C,N,N,H,L,5,P + fire_C,N,N,H,L,5,D + fire_C,N,N,H,L,5,W
        + fire_C,N,N,H,L,5,C + fire_C,N,N,H,L,5,I + fire_C,N,N,H,L,5,A
        + fire_C,N,N,H,L,5,L + fire_C,N,N,H,L,5,N  = 0
 c1464: fire_C,N,N,H,N,1,P + fire_C,N,N,H,N,1,D + fire_C,N,N,H,N,1,W
        + fire_C,N,N,H,N,1,C + fire_C,N,N,H,N,1,I + fire_C,N,N,H,N,1,A
        + fire_C,N,N,H,N,1,L + fire_C,N,N,H,N,1,N  = 0
 c1465: fire_C,N,N,H,N,2,P + fire_C,N,N,H,N,2,D + fire_C,N,N,H,N,2,W
        + fire_C,N,N,H,N,2,C + fire_C,N,N,H,N,2,I + fire_C,N,N,H,N,2,A
        + fire_C,N,N,H,N,2,L + fire_C,N,N,H,N,2,N  = 0
 c1466: fire_C,N,N,H,N,3,P + fire_C,N,N,H,N,3,D + fire_C,N,N,H,N,3,W
        + fire_C,N,N,H,N,3,C + fire_C,N,N,H,N,3,I + fire_C,N,N,H,N,3,A
        + fire_C,N,N,H,N,3,L + fire_C,N,N,H,N,3,N  = 0
 c1467: fire_C,N,N,H,N,4,P + fire_C,N,N,H,N,4,D + fire_C,N,N,H,N,4,W
        + fire_C,N,N,H,N,4,C + fire_C,N,N,H,N,4,I + fire_C,N,N,H,N,4,A
        + fire_C,N,N,H,N,4,L + fire_C,N,N,H,N,4,N  = 0
 c1468: fire_C,N,N,H,N,5,P + fire_C,N,N,H,N,5,D + fire_C,N,N,H,N,5,W
        + fire_C,N,N,H,N,5,C + fire_C,N,N,H,N,5,I + fire_C,N,N,H,N,5,A
        + fire_C,N,N,H,N,5,L + fire_C,N,N,H,N,5,N  = 0
 c1469: fire_C,N,N,C,P,1,P + fire_C,N,N,C,P,1,D + fire_C,N,N,C,P,1,W
        + fire_C,N,N,C,P,1,C + fire_C,N,N,C,P,1,I + fire_C,N,N,C,P,1,A
        + fire_C,N,N,C,P,1,L + fire_C,N,N,C,P,1,N  = 0
 c1470: - 0.002 xEAr_C,N,N,C,P,2,1 + fire_C,N,N,C,P,2,P + fire_C,N,N,C,P,2,D
        + fire_C,N,N,C,P,2,W + fire_C,N,N,C,P,2,C + fire_C,N,N,C,P,2,I
        + fire_C,N,N,C,P,2,A + fire_C,N,N,C,P,2,L + fire_C,N,N,C,P,2,N  = 0
 c1471: - 0.002 xEAr_C,N,N,C,P,3,1 - 0.002 xEAr_C,N,N,C,P,3,2
        + fire_C,N,N,C,P,3,P + fire_C,N,N,C,P,3,D + fire_C,N,N,C,P,3,W
        + fire_C,N,N,C,P,3,C + fire_C,N,N,C,P,3,I + fire_C,N,N,C,P,3,A
        + fire_C,N,N,C,P,3,L + fire_C,N,N,C,P,3,N  = 0
 c1472: - 0.002 xEAr_C,N,N,C,P,4,1 - 0.002 xEAr_C,N,N,C,P,4,2
        - 0.002 xEAr_C,N,N,C,P,4,3 + fire_C,N,N,C,P,4,P + fire_C,N,N,C,P,4,D
        + fire_C,N,N,C,P,4,W + fire_C,N,N,C,P,4,C + fire_C,N,N,C,P,4,I
        + fire_C,N,N,C,P,4,A + fire_C,N,N,C,P,4,L + fire_C,N,N,C,P,4,N  = 0
 c1473: - 0.002 xEAr_C,N,N,C,P,5,1 - 0.002 xEAr_C,N,N,C,P,5,2
        - 0.002 xEAr_C,N,N,C,P,5,3 - 0.002 xEAr_C,N,N,C,P,5,4
        + fire_C,N,N,C,P,5,P + fire_C,N,N,C,P,5,D + fire_C,N,N,C,P,5,W
        + fire_C,N,N,C,P,5,C + fire_C,N,N,C,P,5,I + fire_C,N,N,C,P,5,A
        + fire_C,N,N,C,P,5,L + fire_C,N,N,C,P,5,N  = 0
 c1474: fire_C,N,N,C,D,1,P + fire_C,N,N,C,D,1,D + fire_C,N,N,C,D,1,W
        + fire_C,N,N,C,D,1,C + fire_C,N,N,C,D,1,I + fire_C,N,N,C,D,1,A
        + fire_C,N,N,C,D,1,L + fire_C,N,N,C,D,1,N  = 0
 c1475: - 0.002 xEAr_C,N,N,C,D,2,1 + fire_C,N,N,C,D,2,P + fire_C,N,N,C,D,2,D
        + fire_C,N,N,C,D,2,W + fire_C,N,N,C,D,2,C + fire_C,N,N,C,D,2,I
        + fire_C,N,N,C,D,2,A + fire_C,N,N,C,D,2,L + fire_C,N,N,C,D,2,N  = 0
 c1476: - 0.002 xEAr_C,N,N,C,D,3,1 - 0.002 xEAr_C,N,N,C,D,3,2
        + fire_C,N,N,C,D,3,P + fire_C,N,N,C,D,3,D + fire_C,N,N,C,D,3,W
        + fire_C,N,N,C,D,3,C + fire_C,N,N,C,D,3,I + fire_C,N,N,C,D,3,A
        + fire_C,N,N,C,D,3,L + fire_C,N,N,C,D,3,N  = 0
 c1477: - 0.002 xEAr_C,N,N,C,D,4,1 - 0.002 xEAr_C,N,N,C,D,4,2
        - 0.002 xEAr_C,N,N,C,D,4,3 + fire_C,N,N,C,D,4,P + fire_C,N,N,C,D,4,D
        + fire_C,N,N,C,D,4,W + fire_C,N,N,C,D,4,C + fire_C,N,N,C,D,4,I
        + fire_C,N,N,C,D,4,A + fire_C,N,N,C,D,4,L + fire_C,N,N,C,D,4,N  = 0
 c1478: - 0.002 xEAr_C,N,N,C,D,5,1 - 0.002 xEAr_C,N,N,C,D,5,2
        - 0.002 xEAr_C,N,N,C,D,5,3 - 0.002 xEAr_C,N,N,C,D,5,4
        + fire_C,N,N,C,D,5,P + fire_C,N,N,C,D,5,D + fire_C,N,N,C,D,5,W
        + fire_C,N,N,C,D,5,C + fire_C,N,N,C,D,5,I + fire_C,N,N,C,D,5,A
        + fire_C,N,N,C,D,5,L + fire_C,N,N,C,D,5,N  = 0
 c1479: fire_C,N,N,C,W,1,P + fire_C,N,N,C,W,1,D + fire_C,N,N,C,W,1,W
        + fire_C,N,N,C,W,1,C + fire_C,N,N,C,W,1,I + fire_C,N,N,C,W,1,A
        + fire_C,N,N,C,W,1,L + fire_C,N,N,C,W,1,N  = 0
 c1480: - 0.23 xEAr_C,N,N,C,W,2,1 + fire_C,N,N,C,W,2,P + fire_C,N,N,C,W,2,D
        + fire_C,N,N,C,W,2,W + fire_C,N,N,C,W,2,C + fire_C,N,N,C,W,2,I
        + fire_C,N,N,C,W,2,A + fire_C,N,N,C,W,2,L + fire_C,N,N,C,W,2,N  = 0
 c1481: - 0.23 xEAr_C,N,N,C,W,3,1 - 0.002 xEAr_C,N,N,C,W,3,2
        + fire_C,N,N,C,W,3,P + fire_C,N,N,C,W,3,D + fire_C,N,N,C,W,3,W
        + fire_C,N,N,C,W,3,C + fire_C,N,N,C,W,3,I + fire_C,N,N,C,W,3,A
        + fire_C,N,N,C,W,3,L + fire_C,N,N,C,W,3,N  = 0
 c1482: - 0.23 xEAr_C,N,N,C,W,4,1 - 0.002 xEAr_C,N,N,C,W,4,2
        - 0.002 xEAr_C,N,N,C,W,4,3 + fire_C,N,N,C,W,4,P + fire_C,N,N,C,W,4,D
        + fire_C,N,N,C,W,4,W + fire_C,N,N,C,W,4,C + fire_C,N,N,C,W,4,I
        + fire_C,N,N,C,W,4,A + fire_C,N,N,C,W,4,L + fire_C,N,N,C,W,4,N  = 0
 c1483: - 0.23 xEAr_C,N,N,C,W,5,1 - 0.002 xEAr_C,N,N,C,W,5,2
        - 0.002 xEAr_C,N,N,C,W,5,3 - 0.002 xEAr_C,N,N,C,W,5,4
        + fire_C,N,N,C,W,5,P + fire_C,N,N,C,W,5,D + fire_C,N,N,C,W,5,W
        + fire_C,N,N,C,W,5,C + fire_C,N,N,C,W,5,I + fire_C,N,N,C,W,5,A
        + fire_C,N,N,C,W,5,L + fire_C,N,N,C,W,5,N  = 0
 c1484: fire_C,N,N,C,C,1,P + fire_C,N,N,C,C,1,D + fire_C,N,N,C,C,1,W
        + fire_C,N,N,C,C,1,C + fire_C,N,N,C,C,1,I + fire_C,N,N,C,C,1,A
        + fire_C,N,N,C,C,1,L + fire_C,N,N,C,C,1,N  = 0
 c1485: - 0.002 xEAr_C,N,N,C,C,2,1 + fire_C,N,N,C,C,2,P + fire_C,N,N,C,C,2,D
        + fire_C,N,N,C,C,2,W + fire_C,N,N,C,C,2,C + fire_C,N,N,C,C,2,I
        + fire_C,N,N,C,C,2,A + fire_C,N,N,C,C,2,L + fire_C,N,N,C,C,2,N  = 0
 c1486: - 0.002 xEAr_C,N,N,C,C,3,1 - 0.002 xEAr_C,N,N,C,C,3,2
        + fire_C,N,N,C,C,3,P + fire_C,N,N,C,C,3,D + fire_C,N,N,C,C,3,W
        + fire_C,N,N,C,C,3,C + fire_C,N,N,C,C,3,I + fire_C,N,N,C,C,3,A
        + fire_C,N,N,C,C,3,L + fire_C,N,N,C,C,3,N  = 0
 c1487: - 0.002 xEAr_C,N,N,C,C,4,1 - 0.002 xEAr_C,N,N,C,C,4,2
        - 0.002 xEAr_C,N,N,C,C,4,3 + fire_C,N,N,C,C,4,P + fire_C,N,N,C,C,4,D
        + fire_C,N,N,C,C,4,W + fire_C,N,N,C,C,4,C + fire_C,N,N,C,C,4,I
        + fire_C,N,N,C,C,4,A + fire_C,N,N,C,C,4,L + fire_C,N,N,C,C,4,N  = 0
 c1488: - 0.002 xEAr_C,N,N,C,C,5,1 - 0.002 xEAr_C,N,N,C,C,5,2
        - 0.002 xEAr_C,N,N,C,C,5,3 - 0.002 xEAr_C,N,N,C,C,5,4
        + fire_C,N,N,C,C,5,P + fire_C,N,N,C,C,5,D + fire_C,N,N,C,C,5,W
        + fire_C,N,N,C,C,5,C + fire_C,N,N,C,C,5,I + fire_C,N,N,C,C,5,A
        + fire_C,N,N,C,C,5,L + fire_C,N,N,C,C,5,N  = 0
 c1489: fire_C,N,N,C,I,1,P + fire_C,N,N,C,I,1,D + fire_C,N,N,C,I,1,W
        + fire_C,N,N,C,I,1,C + fire_C,N,N,C,I,1,I + fire_C,N,N,C,I,1,A
        + fire_C,N,N,C,I,1,L + fire_C,N,N,C,I,1,N  = 0
 c1490: - 0.002 xEAr_C,N,N,C,I,2,1 + fire_C,N,N,C,I,2,P + fire_C,N,N,C,I,2,D
        + fire_C,N,N,C,I,2,W + fire_C,N,N,C,I,2,C + fire_C,N,N,C,I,2,I
        + fire_C,N,N,C,I,2,A + fire_C,N,N,C,I,2,L + fire_C,N,N,C,I,2,N  = 0
 c1491: - 0.002 xEAr_C,N,N,C,I,3,1 - 0.002 xEAr_C,N,N,C,I,3,2
        + fire_C,N,N,C,I,3,P + fire_C,N,N,C,I,3,D + fire_C,N,N,C,I,3,W
        + fire_C,N,N,C,I,3,C + fire_C,N,N,C,I,3,I + fire_C,N,N,C,I,3,A
        + fire_C,N,N,C,I,3,L + fire_C,N,N,C,I,3,N  = 0
 c1492: - 0.002 xEAr_C,N,N,C,I,4,1 - 0.002 xEAr_C,N,N,C,I,4,2
        - 0.002 xEAr_C,N,N,C,I,4,3 + fire_C,N,N,C,I,4,P + fire_C,N,N,C,I,4,D
        + fire_C,N,N,C,I,4,W + fire_C,N,N,C,I,4,C + fire_C,N,N,C,I,4,I
        + fire_C,N,N,C,I,4,A + fire_C,N,N,C,I,4,L + fire_C,N,N,C,I,4,N  = 0
 c1493: - 0.002 xEAr_C,N,N,C,I,5,1 - 0.002 xEAr_C,N,N,C,I,5,2
        - 0.002 xEAr_C,N,N,C,I,5,3 - 0.002 xEAr_C,N,N,C,I,5,4
        + fire_C,N,N,C,I,5,P + fire_C,N,N,C,I,5,D + fire_C,N,N,C,I,5,W
        + fire_C,N,N,C,I,5,C + fire_C,N,N,C,I,5,I + fire_C,N,N,C,I,5,A
        + fire_C,N,N,C,I,5,L + fire_C,N,N,C,I,5,N  = 0
 c1494: - 0.002 xNG_C,N,N,C,A,L,1 - 0.002 xPB_C,N,N,C,A,L,0,1
        - 0.002 xPB_C,N,N,C,A,L,1,1 - 0.002 xPB_C,N,N,C,A,L,2,1
        - 0.002 xPB_C,N,N,C,A,L,3,1 - 0.002 xPB_C,N,N,C,A,L,4,1
        - 0.002 xGS_C,N,N,C,A,L,0,1 - 0.002 xGS_C,N,N,C,A,L,1,1
        - 0.002 xGS_C,N,N,C,A,L,2,1 - 0.002 xGS_C,N,N,C,A,L,3,1
        - 0.002 xGS_C,N,N,C,A,L,4,1 - 0.002 xEAe_C,N,N,C,A,L,1
        + fire_C,N,N,C,A,1,P + fire_C,N,N,C,A,1,D + fire_C,N,N,C,A,1,W
        + fire_C,N,N,C,A,1,C + fire_C,N,N,C,A,1,I + fire_C,N,N,C,A,1,A
        + fire_C,N,N,C,A,1,L + fire_C,N,N,C,A,1,N  = 0
 c1495: - 0.002 xNG_C,N,N,C,A,L,2 - 0.002 xPB_C,N,N,C,A,L,0,2
        - 0.002 xPB_C,N,N,C,A,L,1,2 - 0.002 xPB_C,N,N,C,A,L,2,2
        - 0.002 xPB_C,N,N,C,A,L,3,2 - 0.002 xPB_C,N,N,C,A,L,4,2
        - 0.002 xGS_C,N,N,C,A,L,0,2 - 0.002 xGS_C,N,N,C,A,L,1,2
        - 0.002 xGS_C,N,N,C,A,L,2,2 - 0.002 xGS_C,N,N,C,A,L,3,2
        - 0.002 xGS_C,N,N,C,A,L,4,2 - 0.002 xEAe_C,N,N,C,A,L,2
        - 0.002 xEAr_C,N,N,C,A,2,1 + fire_C,N,N,C,A,2,P + fire_C,N,N,C,A,2,D
        + fire_C,N,N,C,A,2,W + fire_C,N,N,C,A,2,C + fire_C,N,N,C,A,2,I
        + fire_C,N,N,C,A,2,A + fire_C,N,N,C,A,2,L + fire_C,N,N,C,A,2,N  = 0
 c1496: - 0.002 xNG_C,N,N,C,A,L,3 - 0.002 xPB_C,N,N,C,A,L,0,3
        - 0.002 xPB_C,N,N,C,A,L,1,3 - 0.002 xPB_C,N,N,C,A,L,2,3
        - 0.002 xPB_C,N,N,C,A,L,3,3 - 0.002 xPB_C,N,N,C,A,L,4,3
        - 0.002 xGS_C,N,N,C,A,L,0,3 - 0.002 xGS_C,N,N,C,A,L,1,3
        - 0.002 xGS_C,N,N,C,A,L,2,3 - 0.002 xGS_C,N,N,C,A,L,3,3
        - 0.002 xGS_C,N,N,C,A,L,4,3 - 0.002 xEAe_C,N,N,C,A,L,3
        - 0.002 xEAr_C,N,N,C,A,3,1 - 0.002 xEAr_C,N,N,C,A,3,2
        + fire_C,N,N,C,A,3,P + fire_C,N,N,C,A,3,D + fire_C,N,N,C,A,3,W
        + fire_C,N,N,C,A,3,C + fire_C,N,N,C,A,3,I + fire_C,N,N,C,A,3,A
        + fire_C,N,N,C,A,3,L + fire_C,N,N,C,A,3,N  = 0
 c1497: - 0.002 xNG_C,N,N,C,A,L,4 - 0.002 xPB_C,N,N,C,A,L,0,4
        - 0.002 xPB_C,N,N,C,A,L,1,4 - 0.002 xPB_C,N,N,C,A,L,2,4
        - 0.002 xPB_C,N,N,C,A,L,3,4 - 0.002 xPB_C,N,N,C,A,L,4,4
        - 0.002 xGS_C,N,N,C,A,L,0,4 - 0.002 xGS_C,N,N,C,A,L,1,4
        - 0.002 xGS_C,N,N,C,A,L,2,4 - 0.002 xGS_C,N,N,C,A,L,3,4
        - 0.002 xGS_C,N,N,C,A,L,4,4 - 0.002 xEAe_C,N,N,C,A,L,4
        - 0.002 xEAr_C,N,N,C,A,4,1 - 0.002 xEAr_C,N,N,C,A,4,2
        - 0.002 xEAr_C,N,N,C,A,4,3 + fire_C,N,N,C,A,4,P + fire_C,N,N,C,A,4,D
        + fire_C,N,N,C,A,4,W + fire_C,N,N,C,A,4,C + fire_C,N,N,C,A,4,I
        + fire_C,N,N,C,A,4,A + fire_C,N,N,C,A,4,L + fire_C,N,N,C,A,4,N  = 0
 c1498: - 0.002 xNG_C,N,N,C,A,L,5 - 0.002 xPB_C,N,N,C,A,L,0,5
        - 0.002 xPB_C,N,N,C,A,L,1,5 - 0.002 xPB_C,N,N,C,A,L,2,5
        - 0.002 xPB_C,N,N,C,A,L,3,5 - 0.002 xPB_C,N,N,C,A,L,4,5
        - 0.002 xGS_C,N,N,C,A,L,0,5 - 0.002 xGS_C,N,N,C,A,L,1,5
        - 0.002 xGS_C,N,N,C,A,L,2,5 - 0.002 xGS_C,N,N,C,A,L,3,5
        - 0.002 xGS_C,N,N,C,A,L,4,5 - 0.002 xEAe_C,N,N,C,A,L,5
        - 0.002 xEAr_C,N,N,C,A,5,1 - 0.002 xEAr_C,N,N,C,A,5,2
        - 0.002 xEAr_C,N,N,C,A,5,3 - 0.002 xEAr_C,N,N,C,A,5,4
        + fire_C,N,N,C,A,5,P + fire_C,N,N,C,A,5,D + fire_C,N,N,C,A,5,W
        + fire_C,N,N,C,A,5,C + fire_C,N,N,C,A,5,I + fire_C,N,N,C,A,5,A
        + fire_C,N,N,C,A,5,L + fire_C,N,N,C,A,5,N  = 0
 c1499: fire_C,N,N,C,L,1,P + fire_C,N,N,C,L,1,D + fire_C,N,N,C,L,1,W
        + fire_C,N,N,C,L,1,C + fire_C,N,N,C,L,1,I + fire_C,N,N,C,L,1,A
        + fire_C,N,N,C,L,1,L + fire_C,N,N,C,L,1,N  = 0
 c1500: - 0.002 xEAr_C,N,N,C,L,2,1 + fire_C,N,N,C,L,2,P + fire_C,N,N,C,L,2,D
        + fire_C,N,N,C,L,2,W + fire_C,N,N,C,L,2,C + fire_C,N,N,C,L,2,I
        + fire_C,N,N,C,L,2,A + fire_C,N,N,C,L,2,L + fire_C,N,N,C,L,2,N  = 0
 c1501: - 0.002 xEAr_C,N,N,C,L,3,1 - 0.002 xEAr_C,N,N,C,L,3,2
        + fire_C,N,N,C,L,3,P + fire_C,N,N,C,L,3,D + fire_C,N,N,C,L,3,W
        + fire_C,N,N,C,L,3,C + fire_C,N,N,C,L,3,I + fire_C,N,N,C,L,3,A
        + fire_C,N,N,C,L,3,L + fire_C,N,N,C,L,3,N  = 0
 c1502: - 0.002 xEAr_C,N,N,C,L,4,1 - 0.002 xEAr_C,N,N,C,L,4,2
        - 0.002 xEAr_C,N,N,C,L,4,3 + fire_C,N,N,C,L,4,P + fire_C,N,N,C,L,4,D
        + fire_C,N,N,C,L,4,W + fire_C,N,N,C,L,4,C + fire_C,N,N,C,L,4,I
        + fire_C,N,N,C,L,4,A + fire_C,N,N,C,L,4,L + fire_C,N,N,C,L,4,N  = 0
 c1503: - 0.002 xEAr_C,N,N,C,L,5,1 - 0.002 xEAr_C,N,N,C,L,5,2
        - 0.002 xEAr_C,N,N,C,L,5,3 - 0.002 xEAr_C,N,N,C,L,5,4
        + fire_C,N,N,C,L,5,P + fire_C,N,N,C,L,5,D + fire_C,N,N,C,L,5,W
        + fire_C,N,N,C,L,5,C + fire_C,N,N,C,L,5,I + fire_C,N,N,C,L,5,A
        + fire_C,N,N,C,L,5,L + fire_C,N,N,C,L,5,N  = 0
 c1504: fire_C,N,N,C,N,1,P + fire_C,N,N,C,N,1,D + fire_C,N,N,C,N,1,W
        + fire_C,N,N,C,N,1,C + fire_C,N,N,C,N,1,I + fire_C,N,N,C,N,1,A
        + fire_C,N,N,C,N,1,L + fire_C,N,N,C,N,1,N  = 0
 c1505: fire_C,N,N,C,N,2,P + fire_C,N,N,C,N,2,D + fire_C,N,N,C,N,2,W
        + fire_C,N,N,C,N,2,C + fire_C,N,N,C,N,2,I + fire_C,N,N,C,N,2,A
        + fire_C,N,N,C,N,2,L + fire_C,N,N,C,N,2,N  = 0
 c1506: fire_C,N,N,C,N,3,P + fire_C,N,N,C,N,3,D + fire_C,N,N,C,N,3,W
        + fire_C,N,N,C,N,3,C + fire_C,N,N,C,N,3,I + fire_C,N,N,C,N,3,A
        + fire_C,N,N,C,N,3,L + fire_C,N,N,C,N,3,N  = 0
 c1507: fire_C,N,N,C,N,4,P + fire_C,N,N,C,N,4,D + fire_C,N,N,C,N,4,W
        + fire_C,N,N,C,N,4,C + fire_C,N,N,C,N,4,I + fire_C,N,N,C,N,4,A
        + fire_C,N,N,C,N,4,L + fire_C,N,N,C,N,4,N  = 0
 c1508: fire_C,N,N,C,N,5,P + fire_C,N,N,C,N,5,D + fire_C,N,N,C,N,5,W
        + fire_C,N,N,C,N,5,C + fire_C,N,N,C,N,5,I + fire_C,N,N,C,N,5,A
        + fire_C,N,N,C,N,5,L + fire_C,N,N,C,N,5,N  = 0
 c1509: fire_C,N,N,R,P,1,P + fire_C,N,N,R,P,1,D + fire_C,N,N,R,P,1,W
        + fire_C,N,N,R,P,1,C + fire_C,N,N,R,P,1,I + fire_C,N,N,R,P,1,A
        + fire_C,N,N,R,P,1,L + fire_C,N,N,R,P,1,N  = 0
 c1510: - 0.002 xEAr_C,N,N,R,P,2,1 + fire_C,N,N,R,P,2,P + fire_C,N,N,R,P,2,D
        + fire_C,N,N,R,P,2,W + fire_C,N,N,R,P,2,C + fire_C,N,N,R,P,2,I
        + fire_C,N,N,R,P,2,A + fire_C,N,N,R,P,2,L + fire_C,N,N,R,P,2,N  = 0
 c1511: - 0.002 xEAr_C,N,N,R,P,3,1 - 0.002 xEAr_C,N,N,R,P,3,2
        + fire_C,N,N,R,P,3,P + fire_C,N,N,R,P,3,D + fire_C,N,N,R,P,3,W
        + fire_C,N,N,R,P,3,C + fire_C,N,N,R,P,3,I + fire_C,N,N,R,P,3,A
        + fire_C,N,N,R,P,3,L + fire_C,N,N,R,P,3,N  = 0
 c1512: - 0.002 xEAr_C,N,N,R,P,4,1 - 0.002 xEAr_C,N,N,R,P,4,2
        - 0.002 xEAr_C,N,N,R,P,4,3 + fire_C,N,N,R,P,4,P + fire_C,N,N,R,P,4,D
        + fire_C,N,N,R,P,4,W + fire_C,N,N,R,P,4,C + fire_C,N,N,R,P,4,I
        + fire_C,N,N,R,P,4,A + fire_C,N,N,R,P,4,L + fire_C,N,N,R,P,4,N  = 0
 c1513: - 0.002 xEAr_C,N,N,R,P,5,1 - 0.002 xEAr_C,N,N,R,P,5,2
        - 0.002 xEAr_C,N,N,R,P,5,3 - 0.002 xEAr_C,N,N,R,P,5,4
        + fire_C,N,N,R,P,5,P + fire_C,N,N,R,P,5,D + fire_C,N,N,R,P,5,W
        + fire_C,N,N,R,P,5,C + fire_C,N,N,R,P,5,I + fire_C,N,N,R,P,5,A
        + fire_C,N,N,R,P,5,L + fire_C,N,N,R,P,5,N  = 0
 c1514: fire_C,N,N,R,D,1,P + fire_C,N,N,R,D,1,D + fire_C,N,N,R,D,1,W
        + fire_C,N,N,R,D,1,C + fire_C,N,N,R,D,1,I + fire_C,N,N,R,D,1,A
        + fire_C,N,N,R,D,1,L + fire_C,N,N,R,D,1,N  = 0
 c1515: - 0.002 xEAr_C,N,N,R,D,2,1 + fire_C,N,N,R,D,2,P + fire_C,N,N,R,D,2,D
        + fire_C,N,N,R,D,2,W + fire_C,N,N,R,D,2,C + fire_C,N,N,R,D,2,I
        + fire_C,N,N,R,D,2,A + fire_C,N,N,R,D,2,L + fire_C,N,N,R,D,2,N  = 0
 c1516: - 0.002 xEAr_C,N,N,R,D,3,1 - 0.002 xEAr_C,N,N,R,D,3,2
        + fire_C,N,N,R,D,3,P + fire_C,N,N,R,D,3,D + fire_C,N,N,R,D,3,W
        + fire_C,N,N,R,D,3,C + fire_C,N,N,R,D,3,I + fire_C,N,N,R,D,3,A
        + fire_C,N,N,R,D,3,L + fire_C,N,N,R,D,3,N  = 0
 c1517: - 0.002 xEAr_C,N,N,R,D,4,1 - 0.002 xEAr_C,N,N,R,D,4,2
        - 0.002 xEAr_C,N,N,R,D,4,3 + fire_C,N,N,R,D,4,P + fire_C,N,N,R,D,4,D
        + fire_C,N,N,R,D,4,W + fire_C,N,N,R,D,4,C + fire_C,N,N,R,D,4,I
        + fire_C,N,N,R,D,4,A + fire_C,N,N,R,D,4,L + fire_C,N,N,R,D,4,N  = 0
 c1518: - 0.002 xEAr_C,N,N,R,D,5,1 - 0.002 xEAr_C,N,N,R,D,5,2
        - 0.002 xEAr_C,N,N,R,D,5,3 - 0.002 xEAr_C,N,N,R,D,5,4
        + fire_C,N,N,R,D,5,P + fire_C,N,N,R,D,5,D + fire_C,N,N,R,D,5,W
        + fire_C,N,N,R,D,5,C + fire_C,N,N,R,D,5,I + fire_C,N,N,R,D,5,A
        + fire_C,N,N,R,D,5,L + fire_C,N,N,R,D,5,N  = 0
 c1519: fire_C,N,N,R,W,1,P + fire_C,N,N,R,W,1,D + fire_C,N,N,R,W,1,W
        + fire_C,N,N,R,W,1,C + fire_C,N,N,R,W,1,I + fire_C,N,N,R,W,1,A
        + fire_C,N,N,R,W,1,L + fire_C,N,N,R,W,1,N  = 0
 c1520: - 0.23 xEAr_C,N,N,R,W,2,1 + fire_C,N,N,R,W,2,P + fire_C,N,N,R,W,2,D
        + fire_C,N,N,R,W,2,W + fire_C,N,N,R,W,2,C + fire_C,N,N,R,W,2,I
        + fire_C,N,N,R,W,2,A + fire_C,N,N,R,W,2,L + fire_C,N,N,R,W,2,N  = 0
 c1521: - 0.23 xEAr_C,N,N,R,W,3,1 - 0.002 xEAr_C,N,N,R,W,3,2
        + fire_C,N,N,R,W,3,P + fire_C,N,N,R,W,3,D + fire_C,N,N,R,W,3,W
        + fire_C,N,N,R,W,3,C + fire_C,N,N,R,W,3,I + fire_C,N,N,R,W,3,A
        + fire_C,N,N,R,W,3,L + fire_C,N,N,R,W,3,N  = 0
 c1522: - 0.23 xEAr_C,N,N,R,W,4,1 - 0.002 xEAr_C,N,N,R,W,4,2
        - 0.002 xEAr_C,N,N,R,W,4,3 + fire_C,N,N,R,W,4,P + fire_C,N,N,R,W,4,D
        + fire_C,N,N,R,W,4,W + fire_C,N,N,R,W,4,C + fire_C,N,N,R,W,4,I
        + fire_C,N,N,R,W,4,A + fire_C,N,N,R,W,4,L + fire_C,N,N,R,W,4,N  = 0
 c1523: - 0.23 xEAr_C,N,N,R,W,5,1 - 0.002 xEAr_C,N,N,R,W,5,2
        - 0.002 xEAr_C,N,N,R,W,5,3 - 0.002 xEAr_C,N,N,R,W,5,4
        + fire_C,N,N,R,W,5,P + fire_C,N,N,R,W,5,D + fire_C,N,N,R,W,5,W
        + fire_C,N,N,R,W,5,C + fire_C,N,N,R,W,5,I + fire_C,N,N,R,W,5,A
        + fire_C,N,N,R,W,5,L + fire_C,N,N,R,W,5,N  = 0
 c1524: fire_C,N,N,R,C,1,P + fire_C,N,N,R,C,1,D + fire_C,N,N,R,C,1,W
        + fire_C,N,N,R,C,1,C + fire_C,N,N,R,C,1,I + fire_C,N,N,R,C,1,A
        + fire_C,N,N,R,C,1,L + fire_C,N,N,R,C,1,N  = 0
 c1525: - 0.002 xEAr_C,N,N,R,C,2,1 + fire_C,N,N,R,C,2,P + fire_C,N,N,R,C,2,D
        + fire_C,N,N,R,C,2,W + fire_C,N,N,R,C,2,C + fire_C,N,N,R,C,2,I
        + fire_C,N,N,R,C,2,A + fire_C,N,N,R,C,2,L + fire_C,N,N,R,C,2,N  = 0
 c1526: - 0.002 xEAr_C,N,N,R,C,3,1 - 0.002 xEAr_C,N,N,R,C,3,2
        + fire_C,N,N,R,C,3,P + fire_C,N,N,R,C,3,D + fire_C,N,N,R,C,3,W
        + fire_C,N,N,R,C,3,C + fire_C,N,N,R,C,3,I + fire_C,N,N,R,C,3,A
        + fire_C,N,N,R,C,3,L + fire_C,N,N,R,C,3,N  = 0
 c1527: - 0.002 xEAr_C,N,N,R,C,4,1 - 0.002 xEAr_C,N,N,R,C,4,2
        - 0.002 xEAr_C,N,N,R,C,4,3 + fire_C,N,N,R,C,4,P + fire_C,N,N,R,C,4,D
        + fire_C,N,N,R,C,4,W + fire_C,N,N,R,C,4,C + fire_C,N,N,R,C,4,I
        + fire_C,N,N,R,C,4,A + fire_C,N,N,R,C,4,L + fire_C,N,N,R,C,4,N  = 0
 c1528: - 0.002 xEAr_C,N,N,R,C,5,1 - 0.002 xEAr_C,N,N,R,C,5,2
        - 0.002 xEAr_C,N,N,R,C,5,3 - 0.002 xEAr_C,N,N,R,C,5,4
        + fire_C,N,N,R,C,5,P + fire_C,N,N,R,C,5,D + fire_C,N,N,R,C,5,W
        + fire_C,N,N,R,C,5,C + fire_C,N,N,R,C,5,I + fire_C,N,N,R,C,5,A
        + fire_C,N,N,R,C,5,L + fire_C,N,N,R,C,5,N  = 0
 c1529: fire_C,N,N,R,I,1,P + fire_C,N,N,R,I,1,D + fire_C,N,N,R,I,1,W
        + fire_C,N,N,R,I,1,C + fire_C,N,N,R,I,1,I + fire_C,N,N,R,I,1,A
        + fire_C,N,N,R,I,1,L + fire_C,N,N,R,I,1,N  = 0
 c1530: - 0.002 xEAr_C,N,N,R,I,2,1 + fire_C,N,N,R,I,2,P + fire_C,N,N,R,I,2,D
        + fire_C,N,N,R,I,2,W + fire_C,N,N,R,I,2,C + fire_C,N,N,R,I,2,I
        + fire_C,N,N,R,I,2,A + fire_C,N,N,R,I,2,L + fire_C,N,N,R,I,2,N  = 0
 c1531: - 0.002 xEAr_C,N,N,R,I,3,1 - 0.002 xEAr_C,N,N,R,I,3,2
        + fire_C,N,N,R,I,3,P + fire_C,N,N,R,I,3,D + fire_C,N,N,R,I,3,W
        + fire_C,N,N,R,I,3,C + fire_C,N,N,R,I,3,I + fire_C,N,N,R,I,3,A
        + fire_C,N,N,R,I,3,L + fire_C,N,N,R,I,3,N  = 0
 c1532: - 0.002 xEAr_C,N,N,R,I,4,1 - 0.002 xEAr_C,N,N,R,I,4,2
        - 0.002 xEAr_C,N,N,R,I,4,3 + fire_C,N,N,R,I,4,P + fire_C,N,N,R,I,4,D
        + fire_C,N,N,R,I,4,W + fire_C,N,N,R,I,4,C + fire_C,N,N,R,I,4,I
        + fire_C,N,N,R,I,4,A + fire_C,N,N,R,I,4,L + fire_C,N,N,R,I,4,N  = 0
 c1533: - 0.002 xEAr_C,N,N,R,I,5,1 - 0.002 xEAr_C,N,N,R,I,5,2
        - 0.002 xEAr_C,N,N,R,I,5,3 - 0.002 xEAr_C,N,N,R,I,5,4
        + fire_C,N,N,R,I,5,P + fire_C,N,N,R,I,5,D + fire_C,N,N,R,I,5,W
        + fire_C,N,N,R,I,5,C + fire_C,N,N,R,I,5,I + fire_C,N,N,R,I,5,A
        + fire_C,N,N,R,I,5,L + fire_C,N,N,R,I,5,N  = 0
 c1534: - 0.002 xNG_C,N,N,R,A,L,1 - 0.002 xPB_C,N,N,R,A,L,0,1
        - 0.002 xPB_C,N,N,R,A,L,1,1 - 0.002 xPB_C,N,N,R,A,L,2,1
        - 0.002 xPB_C,N,N,R,A,L,3,1 - 0.002 xPB_C,N,N,R,A,L,4,1
        - 0.002 xGS_C,N,N,R,A,L,0,1 - 0.002 xGS_C,N,N,R,A,L,1,1
        - 0.002 xGS_C,N,N,R,A,L,2,1 - 0.002 xGS_C,N,N,R,A,L,3,1
        - 0.002 xGS_C,N,N,R,A,L,4,1 - 0.002 xEAe_C,N,N,R,A,L,1
        + fire_C,N,N,R,A,1,P + fire_C,N,N,R,A,1,D + fire_C,N,N,R,A,1,W
        + fire_C,N,N,R,A,1,C + fire_C,N,N,R,A,1,I + fire_C,N,N,R,A,1,A
        + fire_C,N,N,R,A,1,L + fire_C,N,N,R,A,1,N  = 0
 c1535: - 0.002 xNG_C,N,N,R,A,L,2 - 0.002 xPB_C,N,N,R,A,L,0,2
        - 0.002 xPB_C,N,N,R,A,L,1,2 - 0.002 xPB_C,N,N,R,A,L,2,2
        - 0.002 xPB_C,N,N,R,A,L,3,2 - 0.002 xPB_C,N,N,R,A,L,4,2
        - 0.002 xGS_C,N,N,R,A,L,0,2 - 0.002 xGS_C,N,N,R,A,L,1,2
        - 0.002 xGS_C,N,N,R,A,L,2,2 - 0.002 xGS_C,N,N,R,A,L,3,2
        - 0.002 xGS_C,N,N,R,A,L,4,2 - 0.002 xEAe_C,N,N,R,A,L,2
        - 0.002 xEAr_C,N,N,R,A,2,1 + fire_C,N,N,R,A,2,P + fire_C,N,N,R,A,2,D
        + fire_C,N,N,R,A,2,W + fire_C,N,N,R,A,2,C + fire_C,N,N,R,A,2,I
        + fire_C,N,N,R,A,2,A + fire_C,N,N,R,A,2,L + fire_C,N,N,R,A,2,N  = 0
 c1536: - 0.002 xNG_C,N,N,R,A,L,3 - 0.002 xPB_C,N,N,R,A,L,0,3
        - 0.002 xPB_C,N,N,R,A,L,1,3 - 0.002 xPB_C,N,N,R,A,L,2,3
        - 0.002 xPB_C,N,N,R,A,L,3,3 - 0.002 xPB_C,N,N,R,A,L,4,3
        - 0.002 xGS_C,N,N,R,A,L,0,3 - 0.002 xGS_C,N,N,R,A,L,1,3
        - 0.002 xGS_C,N,N,R,A,L,2,3 - 0.002 xGS_C,N,N,R,A,L,3,3
        - 0.002 xGS_C,N,N,R,A,L,4,3 - 0.002 xEAe_C,N,N,R,A,L,3
        - 0.002 xEAr_C,N,N,R,A,3,1 - 0.002 xEAr_C,N,N,R,A,3,2
        + fire_C,N,N,R,A,3,P + fire_C,N,N,R,A,3,D + fire_C,N,N,R,A,3,W
        + fire_C,N,N,R,A,3,C + fire_C,N,N,R,A,3,I + fire_C,N,N,R,A,3,A
        + fire_C,N,N,R,A,3,L + fire_C,N,N,R,A,3,N  = 0
 c1537: - 0.002 xNG_C,N,N,R,A,L,4 - 0.002 xPB_C,N,N,R,A,L,0,4
        - 0.002 xPB_C,N,N,R,A,L,1,4 - 0.002 xPB_C,N,N,R,A,L,2,4
        - 0.002 xPB_C,N,N,R,A,L,3,4 - 0.002 xPB_C,N,N,R,A,L,4,4
        - 0.002 xGS_C,N,N,R,A,L,0,4 - 0.002 xGS_C,N,N,R,A,L,1,4
        - 0.002 xGS_C,N,N,R,A,L,2,4 - 0.002 xGS_C,N,N,R,A,L,3,4
        - 0.002 xGS_C,N,N,R,A,L,4,4 - 0.002 xEAe_C,N,N,R,A,L,4
        - 0.002 xEAr_C,N,N,R,A,4,1 - 0.002 xEAr_C,N,N,R,A,4,2
        - 0.002 xEAr_C,N,N,R,A,4,3 + fire_C,N,N,R,A,4,P + fire_C,N,N,R,A,4,D
        + fire_C,N,N,R,A,4,W + fire_C,N,N,R,A,4,C + fire_C,N,N,R,A,4,I
        + fire_C,N,N,R,A,4,A + fire_C,N,N,R,A,4,L + fire_C,N,N,R,A,4,N  = 0
 c1538: - 0.002 xNG_C,N,N,R,A,L,5 - 0.002 xPB_C,N,N,R,A,L,0,5
        - 0.002 xPB_C,N,N,R,A,L,1,5 - 0.002 xPB_C,N,N,R,A,L,2,5
        - 0.002 xPB_C,N,N,R,A,L,3,5 - 0.002 xPB_C,N,N,R,A,L,4,5
        - 0.002 xGS_C,N,N,R,A,L,0,5 - 0.002 xGS_C,N,N,R,A,L,1,5
        - 0.002 xGS_C,N,N,R,A,L,2,5 - 0.002 xGS_C,N,N,R,A,L,3,5
        - 0.002 xGS_C,N,N,R,A,L,4,5 - 0.002 xEAe_C,N,N,R,A,L,5
        - 0.002 xEAr_C,N,N,R,A,5,1 - 0.002 xEAr_C,N,N,R,A,5,2
        - 0.002 xEAr_C,N,N,R,A,5,3 - 0.002 xEAr_C,N,N,R,A,5,4
        + fire_C,N,N,R,A,5,P + fire_C,N,N,R,A,5,D + fire_C,N,N,R,A,5,W
        + fire_C,N,N,R,A,5,C + fire_C,N,N,R,A,5,I + fire_C,N,N,R,A,5,A
        + fire_C,N,N,R,A,5,L + fire_C,N,N,R,A,5,N  = 0
 c1539: fire_C,N,N,R,L,1,P + fire_C,N,N,R,L,1,D + fire_C,N,N,R,L,1,W
        + fire_C,N,N,R,L,1,C + fire_C,N,N,R,L,1,I + fire_C,N,N,R,L,1,A
        + fire_C,N,N,R,L,1,L + fire_C,N,N,R,L,1,N  = 0
 c1540: - 0.002 xEAr_C,N,N,R,L,2,1 + fire_C,N,N,R,L,2,P + fire_C,N,N,R,L,2,D
        + fire_C,N,N,R,L,2,W + fire_C,N,N,R,L,2,C + fire_C,N,N,R,L,2,I
        + fire_C,N,N,R,L,2,A + fire_C,N,N,R,L,2,L + fire_C,N,N,R,L,2,N  = 0
 c1541: - 0.002 xEAr_C,N,N,R,L,3,1 - 0.002 xEAr_C,N,N,R,L,3,2
        + fire_C,N,N,R,L,3,P + fire_C,N,N,R,L,3,D + fire_C,N,N,R,L,3,W
        + fire_C,N,N,R,L,3,C + fire_C,N,N,R,L,3,I + fire_C,N,N,R,L,3,A
        + fire_C,N,N,R,L,3,L + fire_C,N,N,R,L,3,N  = 0
 c1542: - 0.002 xEAr_C,N,N,R,L,4,1 - 0.002 xEAr_C,N,N,R,L,4,2
        - 0.002 xEAr_C,N,N,R,L,4,3 + fire_C,N,N,R,L,4,P + fire_C,N,N,R,L,4,D
        + fire_C,N,N,R,L,4,W + fire_C,N,N,R,L,4,C + fire_C,N,N,R,L,4,I
        + fire_C,N,N,R,L,4,A + fire_C,N,N,R,L,4,L + fire_C,N,N,R,L,4,N  = 0
 c1543: - 0.002 xEAr_C,N,N,R,L,5,1 - 0.002 xEAr_C,N,N,R,L,5,2
        - 0.002 xEAr_C,N,N,R,L,5,3 - 0.002 xEAr_C,N,N,R,L,5,4
        + fire_C,N,N,R,L,5,P + fire_C,N,N,R,L,5,D + fire_C,N,N,R,L,5,W
        + fire_C,N,N,R,L,5,C + fire_C,N,N,R,L,5,I + fire_C,N,N,R,L,5,A
        + fire_C,N,N,R,L,5,L + fire_C,N,N,R,L,5,N  = 0
 c1544: fire_C,N,N,R,N,1,P + fire_C,N,N,R,N,1,D + fire_C,N,N,R,N,1,W
        + fire_C,N,N,R,N,1,C + fire_C,N,N,R,N,1,I + fire_C,N,N,R,N,1,A
        + fire_C,N,N,R,N,1,L + fire_C,N,N,R,N,1,N  = 0
 c1545: fire_C,N,N,R,N,2,P + fire_C,N,N,R,N,2,D + fire_C,N,N,R,N,2,W
        + fire_C,N,N,R,N,2,C + fire_C,N,N,R,N,2,I + fire_C,N,N,R,N,2,A
        + fire_C,N,N,R,N,2,L + fire_C,N,N,R,N,2,N  = 0
 c1546: fire_C,N,N,R,N,3,P + fire_C,N,N,R,N,3,D + fire_C,N,N,R,N,3,W
        + fire_C,N,N,R,N,3,C + fire_C,N,N,R,N,3,I + fire_C,N,N,R,N,3,A
        + fire_C,N,N,R,N,3,L + fire_C,N,N,R,N,3,N  = 0
 c1547: fire_C,N,N,R,N,4,P + fire_C,N,N,R,N,4,D + fire_C,N,N,R,N,4,W
        + fire_C,N,N,R,N,4,C + fire_C,N,N,R,N,4,I + fire_C,N,N,R,N,4,A
        + fire_C,N,N,R,N,4,L + fire_C,N,N,R,N,4,N  = 0
 c1548: fire_C,N,N,R,N,5,P + fire_C,N,N,R,N,5,D + fire_C,N,N,R,N,5,W
        + fire_C,N,N,R,N,5,C + fire_C,N,N,R,N,5,I + fire_C,N,N,R,N,5,A
        + fire_C,N,N,R,N,5,L + fire_C,N,N,R,N,5,N  = 0
 c1549: fire_C,N,O,L,P,1,P + fire_C,N,O,L,P,1,D + fire_C,N,O,L,P,1,W
        + fire_C,N,O,L,P,1,C + fire_C,N,O,L,P,1,I + fire_C,N,O,L,P,1,A
        + fire_C,N,O,L,P,1,L + fire_C,N,O,L,P,1,N  = 0
 c1550: - 0.002 xEAr_C,N,O,L,P,2,1 + fire_C,N,O,L,P,2,P + fire_C,N,O,L,P,2,D
        + fire_C,N,O,L,P,2,W + fire_C,N,O,L,P,2,C + fire_C,N,O,L,P,2,I
        + fire_C,N,O,L,P,2,A + fire_C,N,O,L,P,2,L + fire_C,N,O,L,P,2,N  = 0
 c1551: - 0.002 xEAr_C,N,O,L,P,3,1 - 0.002 xEAr_C,N,O,L,P,3,2
        + fire_C,N,O,L,P,3,P + fire_C,N,O,L,P,3,D + fire_C,N,O,L,P,3,W
        + fire_C,N,O,L,P,3,C + fire_C,N,O,L,P,3,I + fire_C,N,O,L,P,3,A
        + fire_C,N,O,L,P,3,L + fire_C,N,O,L,P,3,N  = 0
 c1552: - 0.002 xEAr_C,N,O,L,P,4,1 - 0.002 xEAr_C,N,O,L,P,4,2
        - 0.002 xEAr_C,N,O,L,P,4,3 + fire_C,N,O,L,P,4,P + fire_C,N,O,L,P,4,D
        + fire_C,N,O,L,P,4,W + fire_C,N,O,L,P,4,C + fire_C,N,O,L,P,4,I
        + fire_C,N,O,L,P,4,A + fire_C,N,O,L,P,4,L + fire_C,N,O,L,P,4,N  = 0
 c1553: - 0.002 xEAr_C,N,O,L,P,5,1 - 0.002 xEAr_C,N,O,L,P,5,2
        - 0.002 xEAr_C,N,O,L,P,5,3 - 0.002 xEAr_C,N,O,L,P,5,4
        + fire_C,N,O,L,P,5,P + fire_C,N,O,L,P,5,D + fire_C,N,O,L,P,5,W
        + fire_C,N,O,L,P,5,C + fire_C,N,O,L,P,5,I + fire_C,N,O,L,P,5,A
        + fire_C,N,O,L,P,5,L + fire_C,N,O,L,P,5,N  = 0
 c1554: fire_C,N,O,L,D,1,P + fire_C,N,O,L,D,1,D + fire_C,N,O,L,D,1,W
        + fire_C,N,O,L,D,1,C + fire_C,N,O,L,D,1,I + fire_C,N,O,L,D,1,A
        + fire_C,N,O,L,D,1,L + fire_C,N,O,L,D,1,N  = 0
 c1555: - 0.002 xEAr_C,N,O,L,D,2,1 + fire_C,N,O,L,D,2,P + fire_C,N,O,L,D,2,D
        + fire_C,N,O,L,D,2,W + fire_C,N,O,L,D,2,C + fire_C,N,O,L,D,2,I
        + fire_C,N,O,L,D,2,A + fire_C,N,O,L,D,2,L + fire_C,N,O,L,D,2,N  = 0
 c1556: - 0.002 xEAr_C,N,O,L,D,3,1 - 0.002 xEAr_C,N,O,L,D,3,2
        + fire_C,N,O,L,D,3,P + fire_C,N,O,L,D,3,D + fire_C,N,O,L,D,3,W
        + fire_C,N,O,L,D,3,C + fire_C,N,O,L,D,3,I + fire_C,N,O,L,D,3,A
        + fire_C,N,O,L,D,3,L + fire_C,N,O,L,D,3,N  = 0
 c1557: - 0.002 xEAr_C,N,O,L,D,4,1 - 0.002 xEAr_C,N,O,L,D,4,2
        - 0.002 xEAr_C,N,O,L,D,4,3 + fire_C,N,O,L,D,4,P + fire_C,N,O,L,D,4,D
        + fire_C,N,O,L,D,4,W + fire_C,N,O,L,D,4,C + fire_C,N,O,L,D,4,I
        + fire_C,N,O,L,D,4,A + fire_C,N,O,L,D,4,L + fire_C,N,O,L,D,4,N  = 0
 c1558: - 0.002 xEAr_C,N,O,L,D,5,1 - 0.002 xEAr_C,N,O,L,D,5,2
        - 0.002 xEAr_C,N,O,L,D,5,3 - 0.002 xEAr_C,N,O,L,D,5,4
        + fire_C,N,O,L,D,5,P + fire_C,N,O,L,D,5,D + fire_C,N,O,L,D,5,W
        + fire_C,N,O,L,D,5,C + fire_C,N,O,L,D,5,I + fire_C,N,O,L,D,5,A
        + fire_C,N,O,L,D,5,L + fire_C,N,O,L,D,5,N  = 0
 c1559: fire_C,N,O,L,W,1,P + fire_C,N,O,L,W,1,D + fire_C,N,O,L,W,1,W
        + fire_C,N,O,L,W,1,C + fire_C,N,O,L,W,1,I + fire_C,N,O,L,W,1,A
        + fire_C,N,O,L,W,1,L + fire_C,N,O,L,W,1,N  = 0
 c1560: - 0.23 xEAr_C,N,O,L,W,2,1 + fire_C,N,O,L,W,2,P + fire_C,N,O,L,W,2,D
        + fire_C,N,O,L,W,2,W + fire_C,N,O,L,W,2,C + fire_C,N,O,L,W,2,I
        + fire_C,N,O,L,W,2,A + fire_C,N,O,L,W,2,L + fire_C,N,O,L,W,2,N  = 0
 c1561: - 0.23 xEAr_C,N,O,L,W,3,1 - 0.002 xEAr_C,N,O,L,W,3,2
        + fire_C,N,O,L,W,3,P + fire_C,N,O,L,W,3,D + fire_C,N,O,L,W,3,W
        + fire_C,N,O,L,W,3,C + fire_C,N,O,L,W,3,I + fire_C,N,O,L,W,3,A
        + fire_C,N,O,L,W,3,L + fire_C,N,O,L,W,3,N  = 0
 c1562: - 0.23 xEAr_C,N,O,L,W,4,1 - 0.002 xEAr_C,N,O,L,W,4,2
        - 0.002 xEAr_C,N,O,L,W,4,3 + fire_C,N,O,L,W,4,P + fire_C,N,O,L,W,4,D
        + fire_C,N,O,L,W,4,W + fire_C,N,O,L,W,4,C + fire_C,N,O,L,W,4,I
        + fire_C,N,O,L,W,4,A + fire_C,N,O,L,W,4,L + fire_C,N,O,L,W,4,N  = 0
 c1563: - 0.23 xEAr_C,N,O,L,W,5,1 - 0.002 xEAr_C,N,O,L,W,5,2
        - 0.002 xEAr_C,N,O,L,W,5,3 - 0.002 xEAr_C,N,O,L,W,5,4
        + fire_C,N,O,L,W,5,P + fire_C,N,O,L,W,5,D + fire_C,N,O,L,W,5,W
        + fire_C,N,O,L,W,5,C + fire_C,N,O,L,W,5,I + fire_C,N,O,L,W,5,A
        + fire_C,N,O,L,W,5,L + fire_C,N,O,L,W,5,N  = 0
 c1564: fire_C,N,O,L,C,1,P + fire_C,N,O,L,C,1,D + fire_C,N,O,L,C,1,W
        + fire_C,N,O,L,C,1,C + fire_C,N,O,L,C,1,I + fire_C,N,O,L,C,1,A
        + fire_C,N,O,L,C,1,L + fire_C,N,O,L,C,1,N  = 0
 c1565: - 0.002 xEAr_C,N,O,L,C,2,1 + fire_C,N,O,L,C,2,P + fire_C,N,O,L,C,2,D
        + fire_C,N,O,L,C,2,W + fire_C,N,O,L,C,2,C + fire_C,N,O,L,C,2,I
        + fire_C,N,O,L,C,2,A + fire_C,N,O,L,C,2,L + fire_C,N,O,L,C,2,N  = 0
 c1566: - 0.002 xEAr_C,N,O,L,C,3,1 - 0.002 xEAr_C,N,O,L,C,3,2
        + fire_C,N,O,L,C,3,P + fire_C,N,O,L,C,3,D + fire_C,N,O,L,C,3,W
        + fire_C,N,O,L,C,3,C + fire_C,N,O,L,C,3,I + fire_C,N,O,L,C,3,A
        + fire_C,N,O,L,C,3,L + fire_C,N,O,L,C,3,N  = 0
 c1567: - 0.002 xEAr_C,N,O,L,C,4,1 - 0.002 xEAr_C,N,O,L,C,4,2
        - 0.002 xEAr_C,N,O,L,C,4,3 + fire_C,N,O,L,C,4,P + fire_C,N,O,L,C,4,D
        + fire_C,N,O,L,C,4,W + fire_C,N,O,L,C,4,C + fire_C,N,O,L,C,4,I
        + fire_C,N,O,L,C,4,A + fire_C,N,O,L,C,4,L + fire_C,N,O,L,C,4,N  = 0
 c1568: - 0.002 xEAr_C,N,O,L,C,5,1 - 0.002 xEAr_C,N,O,L,C,5,2
        - 0.002 xEAr_C,N,O,L,C,5,3 - 0.002 xEAr_C,N,O,L,C,5,4
        + fire_C,N,O,L,C,5,P + fire_C,N,O,L,C,5,D + fire_C,N,O,L,C,5,W
        + fire_C,N,O,L,C,5,C + fire_C,N,O,L,C,5,I + fire_C,N,O,L,C,5,A
        + fire_C,N,O,L,C,5,L + fire_C,N,O,L,C,5,N  = 0
 c1569: fire_C,N,O,L,I,1,P + fire_C,N,O,L,I,1,D + fire_C,N,O,L,I,1,W
        + fire_C,N,O,L,I,1,C + fire_C,N,O,L,I,1,I + fire_C,N,O,L,I,1,A
        + fire_C,N,O,L,I,1,L + fire_C,N,O,L,I,1,N  = 0
 c1570: - 0.002 xEAr_C,N,O,L,I,2,1 + fire_C,N,O,L,I,2,P + fire_C,N,O,L,I,2,D
        + fire_C,N,O,L,I,2,W + fire_C,N,O,L,I,2,C + fire_C,N,O,L,I,2,I
        + fire_C,N,O,L,I,2,A + fire_C,N,O,L,I,2,L + fire_C,N,O,L,I,2,N  = 0
 c1571: - 0.002 xEAr_C,N,O,L,I,3,1 - 0.002 xEAr_C,N,O,L,I,3,2
        + fire_C,N,O,L,I,3,P + fire_C,N,O,L,I,3,D + fire_C,N,O,L,I,3,W
        + fire_C,N,O,L,I,3,C + fire_C,N,O,L,I,3,I + fire_C,N,O,L,I,3,A
        + fire_C,N,O,L,I,3,L + fire_C,N,O,L,I,3,N  = 0
 c1572: - 0.002 xEAr_C,N,O,L,I,4,1 - 0.002 xEAr_C,N,O,L,I,4,2
        - 0.002 xEAr_C,N,O,L,I,4,3 + fire_C,N,O,L,I,4,P + fire_C,N,O,L,I,4,D
        + fire_C,N,O,L,I,4,W + fire_C,N,O,L,I,4,C + fire_C,N,O,L,I,4,I
        + fire_C,N,O,L,I,4,A + fire_C,N,O,L,I,4,L + fire_C,N,O,L,I,4,N  = 0
 c1573: - 0.002 xEAr_C,N,O,L,I,5,1 - 0.002 xEAr_C,N,O,L,I,5,2
        - 0.002 xEAr_C,N,O,L,I,5,3 - 0.002 xEAr_C,N,O,L,I,5,4
        + fire_C,N,O,L,I,5,P + fire_C,N,O,L,I,5,D + fire_C,N,O,L,I,5,W
        + fire_C,N,O,L,I,5,C + fire_C,N,O,L,I,5,I + fire_C,N,O,L,I,5,A
        + fire_C,N,O,L,I,5,L + fire_C,N,O,L,I,5,N  = 0
 c1574: - 0.002 xNG_C,N,O,L,A,L,1 - 0.002 xPB_C,N,O,L,A,L,0,1
        - 0.002 xPB_C,N,O,L,A,L,1,1 - 0.002 xPB_C,N,O,L,A,L,2,1
        - 0.002 xPB_C,N,O,L,A,L,3,1 - 0.002 xPB_C,N,O,L,A,L,4,1
        - 0.002 xGS_C,N,O,L,A,L,0,1 - 0.002 xGS_C,N,O,L,A,L,1,1
        - 0.002 xGS_C,N,O,L,A,L,2,1 - 0.002 xGS_C,N,O,L,A,L,3,1
        - 0.002 xGS_C,N,O,L,A,L,4,1 - 0.002 xEAe_C,N,O,L,A,L,1
        + fire_C,N,O,L,A,1,P + fire_C,N,O,L,A,1,D + fire_C,N,O,L,A,1,W
        + fire_C,N,O,L,A,1,C + fire_C,N,O,L,A,1,I + fire_C,N,O,L,A,1,A
        + fire_C,N,O,L,A,1,L + fire_C,N,O,L,A,1,N  = 0
 c1575: - 0.002 xNG_C,N,O,L,A,L,2 - 0.002 xPB_C,N,O,L,A,L,0,2
        - 0.002 xPB_C,N,O,L,A,L,1,2 - 0.002 xPB_C,N,O,L,A,L,2,2
        - 0.002 xPB_C,N,O,L,A,L,3,2 - 0.002 xPB_C,N,O,L,A,L,4,2
        - 0.002 xGS_C,N,O,L,A,L,0,2 - 0.002 xGS_C,N,O,L,A,L,1,2
        - 0.002 xGS_C,N,O,L,A,L,2,2 - 0.002 xGS_C,N,O,L,A,L,3,2
        - 0.002 xGS_C,N,O,L,A,L,4,2 - 0.002 xEAe_C,N,O,L,A,L,2
        - 0.002 xEAr_C,N,O,L,A,2,1 + fire_C,N,O,L,A,2,P + fire_C,N,O,L,A,2,D
        + fire_C,N,O,L,A,2,W + fire_C,N,O,L,A,2,C + fire_C,N,O,L,A,2,I
        + fire_C,N,O,L,A,2,A + fire_C,N,O,L,A,2,L + fire_C,N,O,L,A,2,N  = 0
 c1576: - 0.002 xNG_C,N,O,L,A,L,3 - 0.002 xPB_C,N,O,L,A,L,0,3
        - 0.002 xPB_C,N,O,L,A,L,1,3 - 0.002 xPB_C,N,O,L,A,L,2,3
        - 0.002 xPB_C,N,O,L,A,L,3,3 - 0.002 xPB_C,N,O,L,A,L,4,3
        - 0.002 xGS_C,N,O,L,A,L,0,3 - 0.002 xGS_C,N,O,L,A,L,1,3
        - 0.002 xGS_C,N,O,L,A,L,2,3 - 0.002 xGS_C,N,O,L,A,L,3,3
        - 0.002 xGS_C,N,O,L,A,L,4,3 - 0.002 xEAe_C,N,O,L,A,L,3
        - 0.002 xEAr_C,N,O,L,A,3,1 - 0.002 xEAr_C,N,O,L,A,3,2
        + fire_C,N,O,L,A,3,P + fire_C,N,O,L,A,3,D + fire_C,N,O,L,A,3,W
        + fire_C,N,O,L,A,3,C + fire_C,N,O,L,A,3,I + fire_C,N,O,L,A,3,A
        + fire_C,N,O,L,A,3,L + fire_C,N,O,L,A,3,N  = 0
 c1577: - 0.002 xNG_C,N,O,L,A,L,4 - 0.002 xPB_C,N,O,L,A,L,0,4
        - 0.002 xPB_C,N,O,L,A,L,1,4 - 0.002 xPB_C,N,O,L,A,L,2,4
        - 0.002 xPB_C,N,O,L,A,L,3,4 - 0.002 xPB_C,N,O,L,A,L,4,4
        - 0.002 xGS_C,N,O,L,A,L,0,4 - 0.002 xGS_C,N,O,L,A,L,1,4
        - 0.002 xGS_C,N,O,L,A,L,2,4 - 0.002 xGS_C,N,O,L,A,L,3,4
        - 0.002 xGS_C,N,O,L,A,L,4,4 - 0.002 xEAe_C,N,O,L,A,L,4
        - 0.002 xEAr_C,N,O,L,A,4,1 - 0.002 xEAr_C,N,O,L,A,4,2
        - 0.002 xEAr_C,N,O,L,A,4,3 + fire_C,N,O,L,A,4,P + fire_C,N,O,L,A,4,D
        + fire_C,N,O,L,A,4,W + fire_C,N,O,L,A,4,C + fire_C,N,O,L,A,4,I
        + fire_C,N,O,L,A,4,A + fire_C,N,O,L,A,4,L + fire_C,N,O,L,A,4,N  = 0
 c1578: - 0.002 xNG_C,N,O,L,A,L,5 - 0.002 xPB_C,N,O,L,A,L,0,5
        - 0.002 xPB_C,N,O,L,A,L,1,5 - 0.002 xPB_C,N,O,L,A,L,2,5
        - 0.002 xPB_C,N,O,L,A,L,3,5 - 0.002 xPB_C,N,O,L,A,L,4,5
        - 0.002 xGS_C,N,O,L,A,L,0,5 - 0.002 xGS_C,N,O,L,A,L,1,5
        - 0.002 xGS_C,N,O,L,A,L,2,5 - 0.002 xGS_C,N,O,L,A,L,3,5
        - 0.002 xGS_C,N,O,L,A,L,4,5 - 0.002 xEAe_C,N,O,L,A,L,5
        - 0.002 xEAr_C,N,O,L,A,5,1 - 0.002 xEAr_C,N,O,L,A,5,2
        - 0.002 xEAr_C,N,O,L,A,5,3 - 0.002 xEAr_C,N,O,L,A,5,4
        + fire_C,N,O,L,A,5,P + fire_C,N,O,L,A,5,D + fire_C,N,O,L,A,5,W
        + fire_C,N,O,L,A,5,C + fire_C,N,O,L,A,5,I + fire_C,N,O,L,A,5,A
        + fire_C,N,O,L,A,5,L + fire_C,N,O,L,A,5,N  = 0
 c1579: fire_C,N,O,L,L,1,P + fire_C,N,O,L,L,1,D + fire_C,N,O,L,L,1,W
        + fire_C,N,O,L,L,1,C + fire_C,N,O,L,L,1,I + fire_C,N,O,L,L,1,A
        + fire_C,N,O,L,L,1,L + fire_C,N,O,L,L,1,N  = 0
 c1580: - 0.002 xEAr_C,N,O,L,L,2,1 + fire_C,N,O,L,L,2,P + fire_C,N,O,L,L,2,D
        + fire_C,N,O,L,L,2,W + fire_C,N,O,L,L,2,C + fire_C,N,O,L,L,2,I
        + fire_C,N,O,L,L,2,A + fire_C,N,O,L,L,2,L + fire_C,N,O,L,L,2,N  = 0
 c1581: - 0.002 xEAr_C,N,O,L,L,3,1 - 0.002 xEAr_C,N,O,L,L,3,2
        + fire_C,N,O,L,L,3,P + fire_C,N,O,L,L,3,D + fire_C,N,O,L,L,3,W
        + fire_C,N,O,L,L,3,C + fire_C,N,O,L,L,3,I + fire_C,N,O,L,L,3,A
        + fire_C,N,O,L,L,3,L + fire_C,N,O,L,L,3,N  = 0
 c1582: - 0.002 xEAr_C,N,O,L,L,4,1 - 0.002 xEAr_C,N,O,L,L,4,2
        - 0.002 xEAr_C,N,O,L,L,4,3 + fire_C,N,O,L,L,4,P + fire_C,N,O,L,L,4,D
        + fire_C,N,O,L,L,4,W + fire_C,N,O,L,L,4,C + fire_C,N,O,L,L,4,I
        + fire_C,N,O,L,L,4,A + fire_C,N,O,L,L,4,L + fire_C,N,O,L,L,4,N  = 0
 c1583: - 0.002 xEAr_C,N,O,L,L,5,1 - 0.002 xEAr_C,N,O,L,L,5,2
        - 0.002 xEAr_C,N,O,L,L,5,3 - 0.002 xEAr_C,N,O,L,L,5,4
        + fire_C,N,O,L,L,5,P + fire_C,N,O,L,L,5,D + fire_C,N,O,L,L,5,W
        + fire_C,N,O,L,L,5,C + fire_C,N,O,L,L,5,I + fire_C,N,O,L,L,5,A
        + fire_C,N,O,L,L,5,L + fire_C,N,O,L,L,5,N  = 0
 c1584: fire_C,N,O,L,N,1,P + fire_C,N,O,L,N,1,D + fire_C,N,O,L,N,1,W
        + fire_C,N,O,L,N,1,C + fire_C,N,O,L,N,1,I + fire_C,N,O,L,N,1,A
        + fire_C,N,O,L,N,1,L + fire_C,N,O,L,N,1,N  = 0
 c1585: fire_C,N,O,L,N,2,P + fire_C,N,O,L,N,2,D + fire_C,N,O,L,N,2,W
        + fire_C,N,O,L,N,2,C + fire_C,N,O,L,N,2,I + fire_C,N,O,L,N,2,A
        + fire_C,N,O,L,N,2,L + fire_C,N,O,L,N,2,N  = 0
 c1586: fire_C,N,O,L,N,3,P + fire_C,N,O,L,N,3,D + fire_C,N,O,L,N,3,W
        + fire_C,N,O,L,N,3,C + fire_C,N,O,L,N,3,I + fire_C,N,O,L,N,3,A
        + fire_C,N,O,L,N,3,L + fire_C,N,O,L,N,3,N  = 0
 c1587: fire_C,N,O,L,N,4,P + fire_C,N,O,L,N,4,D + fire_C,N,O,L,N,4,W
        + fire_C,N,O,L,N,4,C + fire_C,N,O,L,N,4,I + fire_C,N,O,L,N,4,A
        + fire_C,N,O,L,N,4,L + fire_C,N,O,L,N,4,N  = 0
 c1588: fire_C,N,O,L,N,5,P + fire_C,N,O,L,N,5,D + fire_C,N,O,L,N,5,W
        + fire_C,N,O,L,N,5,C + fire_C,N,O,L,N,5,I + fire_C,N,O,L,N,5,A
        + fire_C,N,O,L,N,5,L + fire_C,N,O,L,N,5,N  = 0
 c1589: fire_C,N,O,H,P,1,P + fire_C,N,O,H,P,1,D + fire_C,N,O,H,P,1,W
        + fire_C,N,O,H,P,1,C + fire_C,N,O,H,P,1,I + fire_C,N,O,H,P,1,A
        + fire_C,N,O,H,P,1,L + fire_C,N,O,H,P,1,N  = 0
 c1590: - 0.002 xEAr_C,N,O,H,P,2,1 + fire_C,N,O,H,P,2,P + fire_C,N,O,H,P,2,D
        + fire_C,N,O,H,P,2,W + fire_C,N,O,H,P,2,C + fire_C,N,O,H,P,2,I
        + fire_C,N,O,H,P,2,A + fire_C,N,O,H,P,2,L + fire_C,N,O,H,P,2,N  = 0
 c1591: - 0.002 xEAr_C,N,O,H,P,3,1 - 0.002 xEAr_C,N,O,H,P,3,2
        + fire_C,N,O,H,P,3,P + fire_C,N,O,H,P,3,D + fire_C,N,O,H,P,3,W
        + fire_C,N,O,H,P,3,C + fire_C,N,O,H,P,3,I + fire_C,N,O,H,P,3,A
        + fire_C,N,O,H,P,3,L + fire_C,N,O,H,P,3,N  = 0
 c1592: - 0.002 xEAr_C,N,O,H,P,4,1 - 0.002 xEAr_C,N,O,H,P,4,2
        - 0.002 xEAr_C,N,O,H,P,4,3 + fire_C,N,O,H,P,4,P + fire_C,N,O,H,P,4,D
        + fire_C,N,O,H,P,4,W + fire_C,N,O,H,P,4,C + fire_C,N,O,H,P,4,I
        + fire_C,N,O,H,P,4,A + fire_C,N,O,H,P,4,L + fire_C,N,O,H,P,4,N  = 0
 c1593: - 0.002 xEAr_C,N,O,H,P,5,1 - 0.002 xEAr_C,N,O,H,P,5,2
        - 0.002 xEAr_C,N,O,H,P,5,3 - 0.002 xEAr_C,N,O,H,P,5,4
        + fire_C,N,O,H,P,5,P + fire_C,N,O,H,P,5,D + fire_C,N,O,H,P,5,W
        + fire_C,N,O,H,P,5,C + fire_C,N,O,H,P,5,I + fire_C,N,O,H,P,5,A
        + fire_C,N,O,H,P,5,L + fire_C,N,O,H,P,5,N  = 0
 c1594: fire_C,N,O,H,D,1,P + fire_C,N,O,H,D,1,D + fire_C,N,O,H,D,1,W
        + fire_C,N,O,H,D,1,C + fire_C,N,O,H,D,1,I + fire_C,N,O,H,D,1,A
        + fire_C,N,O,H,D,1,L + fire_C,N,O,H,D,1,N  = 0
 c1595: - 0.002 xEAr_C,N,O,H,D,2,1 + fire_C,N,O,H,D,2,P + fire_C,N,O,H,D,2,D
        + fire_C,N,O,H,D,2,W + fire_C,N,O,H,D,2,C + fire_C,N,O,H,D,2,I
        + fire_C,N,O,H,D,2,A + fire_C,N,O,H,D,2,L + fire_C,N,O,H,D,2,N  = 0
 c1596: - 0.002 xEAr_C,N,O,H,D,3,1 - 0.002 xEAr_C,N,O,H,D,3,2
        + fire_C,N,O,H,D,3,P + fire_C,N,O,H,D,3,D + fire_C,N,O,H,D,3,W
        + fire_C,N,O,H,D,3,C + fire_C,N,O,H,D,3,I + fire_C,N,O,H,D,3,A
        + fire_C,N,O,H,D,3,L + fire_C,N,O,H,D,3,N  = 0
 c1597: - 0.002 xEAr_C,N,O,H,D,4,1 - 0.002 xEAr_C,N,O,H,D,4,2
        - 0.002 xEAr_C,N,O,H,D,4,3 + fire_C,N,O,H,D,4,P + fire_C,N,O,H,D,4,D
        + fire_C,N,O,H,D,4,W + fire_C,N,O,H,D,4,C + fire_C,N,O,H,D,4,I
        + fire_C,N,O,H,D,4,A + fire_C,N,O,H,D,4,L + fire_C,N,O,H,D,4,N  = 0
 c1598: - 0.002 xEAr_C,N,O,H,D,5,1 - 0.002 xEAr_C,N,O,H,D,5,2
        - 0.002 xEAr_C,N,O,H,D,5,3 - 0.002 xEAr_C,N,O,H,D,5,4
        + fire_C,N,O,H,D,5,P + fire_C,N,O,H,D,5,D + fire_C,N,O,H,D,5,W
        + fire_C,N,O,H,D,5,C + fire_C,N,O,H,D,5,I + fire_C,N,O,H,D,5,A
        + fire_C,N,O,H,D,5,L + fire_C,N,O,H,D,5,N  = 0
 c1599: fire_C,N,O,H,W,1,P + fire_C,N,O,H,W,1,D + fire_C,N,O,H,W,1,W
        + fire_C,N,O,H,W,1,C + fire_C,N,O,H,W,1,I + fire_C,N,O,H,W,1,A
        + fire_C,N,O,H,W,1,L + fire_C,N,O,H,W,1,N  = 0
 c1600: - 0.23 xEAr_C,N,O,H,W,2,1 + fire_C,N,O,H,W,2,P + fire_C,N,O,H,W,2,D
        + fire_C,N,O,H,W,2,W + fire_C,N,O,H,W,2,C + fire_C,N,O,H,W,2,I
        + fire_C,N,O,H,W,2,A + fire_C,N,O,H,W,2,L + fire_C,N,O,H,W,2,N  = 0
 c1601: - 0.23 xEAr_C,N,O,H,W,3,1 - 0.002 xEAr_C,N,O,H,W,3,2
        + fire_C,N,O,H,W,3,P + fire_C,N,O,H,W,3,D + fire_C,N,O,H,W,3,W
        + fire_C,N,O,H,W,3,C + fire_C,N,O,H,W,3,I + fire_C,N,O,H,W,3,A
        + fire_C,N,O,H,W,3,L + fire_C,N,O,H,W,3,N  = 0
 c1602: - 0.23 xEAr_C,N,O,H,W,4,1 - 0.002 xEAr_C,N,O,H,W,4,2
        - 0.002 xEAr_C,N,O,H,W,4,3 + fire_C,N,O,H,W,4,P + fire_C,N,O,H,W,4,D
        + fire_C,N,O,H,W,4,W + fire_C,N,O,H,W,4,C + fire_C,N,O,H,W,4,I
        + fire_C,N,O,H,W,4,A + fire_C,N,O,H,W,4,L + fire_C,N,O,H,W,4,N  = 0
 c1603: - 0.23 xEAr_C,N,O,H,W,5,1 - 0.002 xEAr_C,N,O,H,W,5,2
        - 0.002 xEAr_C,N,O,H,W,5,3 - 0.002 xEAr_C,N,O,H,W,5,4
        + fire_C,N,O,H,W,5,P + fire_C,N,O,H,W,5,D + fire_C,N,O,H,W,5,W
        + fire_C,N,O,H,W,5,C + fire_C,N,O,H,W,5,I + fire_C,N,O,H,W,5,A
        + fire_C,N,O,H,W,5,L + fire_C,N,O,H,W,5,N  = 0
 c1604: fire_C,N,O,H,C,1,P + fire_C,N,O,H,C,1,D + fire_C,N,O,H,C,1,W
        + fire_C,N,O,H,C,1,C + fire_C,N,O,H,C,1,I + fire_C,N,O,H,C,1,A
        + fire_C,N,O,H,C,1,L + fire_C,N,O,H,C,1,N  = 0
 c1605: - 0.002 xEAr_C,N,O,H,C,2,1 + fire_C,N,O,H,C,2,P + fire_C,N,O,H,C,2,D
        + fire_C,N,O,H,C,2,W + fire_C,N,O,H,C,2,C + fire_C,N,O,H,C,2,I
        + fire_C,N,O,H,C,2,A + fire_C,N,O,H,C,2,L + fire_C,N,O,H,C,2,N  = 0
 c1606: - 0.002 xEAr_C,N,O,H,C,3,1 - 0.002 xEAr_C,N,O,H,C,3,2
        + fire_C,N,O,H,C,3,P + fire_C,N,O,H,C,3,D + fire_C,N,O,H,C,3,W
        + fire_C,N,O,H,C,3,C + fire_C,N,O,H,C,3,I + fire_C,N,O,H,C,3,A
        + fire_C,N,O,H,C,3,L + fire_C,N,O,H,C,3,N  = 0
 c1607: - 0.002 xEAr_C,N,O,H,C,4,1 - 0.002 xEAr_C,N,O,H,C,4,2
        - 0.002 xEAr_C,N,O,H,C,4,3 + fire_C,N,O,H,C,4,P + fire_C,N,O,H,C,4,D
        + fire_C,N,O,H,C,4,W + fire_C,N,O,H,C,4,C + fire_C,N,O,H,C,4,I
        + fire_C,N,O,H,C,4,A + fire_C,N,O,H,C,4,L + fire_C,N,O,H,C,4,N  = 0
 c1608: - 0.002 xEAr_C,N,O,H,C,5,1 - 0.002 xEAr_C,N,O,H,C,5,2
        - 0.002 xEAr_C,N,O,H,C,5,3 - 0.002 xEAr_C,N,O,H,C,5,4
        + fire_C,N,O,H,C,5,P + fire_C,N,O,H,C,5,D + fire_C,N,O,H,C,5,W
        + fire_C,N,O,H,C,5,C + fire_C,N,O,H,C,5,I + fire_C,N,O,H,C,5,A
        + fire_C,N,O,H,C,5,L + fire_C,N,O,H,C,5,N  = 0
 c1609: fire_C,N,O,H,I,1,P + fire_C,N,O,H,I,1,D + fire_C,N,O,H,I,1,W
        + fire_C,N,O,H,I,1,C + fire_C,N,O,H,I,1,I + fire_C,N,O,H,I,1,A
        + fire_C,N,O,H,I,1,L + fire_C,N,O,H,I,1,N  = 0
 c1610: - 0.002 xEAr_C,N,O,H,I,2,1 + fire_C,N,O,H,I,2,P + fire_C,N,O,H,I,2,D
        + fire_C,N,O,H,I,2,W + fire_C,N,O,H,I,2,C + fire_C,N,O,H,I,2,I
        + fire_C,N,O,H,I,2,A + fire_C,N,O,H,I,2,L + fire_C,N,O,H,I,2,N  = 0
 c1611: - 0.002 xEAr_C,N,O,H,I,3,1 - 0.002 xEAr_C,N,O,H,I,3,2
        + fire_C,N,O,H,I,3,P + fire_C,N,O,H,I,3,D + fire_C,N,O,H,I,3,W
        + fire_C,N,O,H,I,3,C + fire_C,N,O,H,I,3,I + fire_C,N,O,H,I,3,A
        + fire_C,N,O,H,I,3,L + fire_C,N,O,H,I,3,N  = 0
 c1612: - 0.002 xEAr_C,N,O,H,I,4,1 - 0.002 xEAr_C,N,O,H,I,4,2
        - 0.002 xEAr_C,N,O,H,I,4,3 + fire_C,N,O,H,I,4,P + fire_C,N,O,H,I,4,D
        + fire_C,N,O,H,I,4,W + fire_C,N,O,H,I,4,C + fire_C,N,O,H,I,4,I
        + fire_C,N,O,H,I,4,A + fire_C,N,O,H,I,4,L + fire_C,N,O,H,I,4,N  = 0
 c1613: - 0.002 xEAr_C,N,O,H,I,5,1 - 0.002 xEAr_C,N,O,H,I,5,2
        - 0.002 xEAr_C,N,O,H,I,5,3 - 0.002 xEAr_C,N,O,H,I,5,4
        + fire_C,N,O,H,I,5,P + fire_C,N,O,H,I,5,D + fire_C,N,O,H,I,5,W
        + fire_C,N,O,H,I,5,C + fire_C,N,O,H,I,5,I + fire_C,N,O,H,I,5,A
        + fire_C,N,O,H,I,5,L + fire_C,N,O,H,I,5,N  = 0
 c1614: - 0.002 xNG_C,N,O,H,A,L,1 - 0.002 xPB_C,N,O,H,A,L,0,1
        - 0.002 xPB_C,N,O,H,A,L,1,1 - 0.002 xPB_C,N,O,H,A,L,2,1
        - 0.002 xPB_C,N,O,H,A,L,3,1 - 0.002 xPB_C,N,O,H,A,L,4,1
        - 0.002 xGS_C,N,O,H,A,L,0,1 - 0.002 xGS_C,N,O,H,A,L,1,1
        - 0.002 xGS_C,N,O,H,A,L,2,1 - 0.002 xGS_C,N,O,H,A,L,3,1
        - 0.002 xGS_C,N,O,H,A,L,4,1 - 0.002 xEAe_C,N,O,H,A,L,1
        + fire_C,N,O,H,A,1,P + fire_C,N,O,H,A,1,D + fire_C,N,O,H,A,1,W
        + fire_C,N,O,H,A,1,C + fire_C,N,O,H,A,1,I + fire_C,N,O,H,A,1,A
        + fire_C,N,O,H,A,1,L + fire_C,N,O,H,A,1,N  = 0
 c1615: - 0.002 xNG_C,N,O,H,A,L,2 - 0.002 xPB_C,N,O,H,A,L,0,2
        - 0.002 xPB_C,N,O,H,A,L,1,2 - 0.002 xPB_C,N,O,H,A,L,2,2
        - 0.002 xPB_C,N,O,H,A,L,3,2 - 0.002 xPB_C,N,O,H,A,L,4,2
        - 0.002 xGS_C,N,O,H,A,L,0,2 - 0.002 xGS_C,N,O,H,A,L,1,2
        - 0.002 xGS_C,N,O,H,A,L,2,2 - 0.002 xGS_C,N,O,H,A,L,3,2
        - 0.002 xGS_C,N,O,H,A,L,4,2 - 0.002 xEAe_C,N,O,H,A,L,2
        - 0.002 xEAr_C,N,O,H,A,2,1 + fire_C,N,O,H,A,2,P + fire_C,N,O,H,A,2,D
        + fire_C,N,O,H,A,2,W + fire_C,N,O,H,A,2,C + fire_C,N,O,H,A,2,I
        + fire_C,N,O,H,A,2,A + fire_C,N,O,H,A,2,L + fire_C,N,O,H,A,2,N  = 0
 c1616: - 0.002 xNG_C,N,O,H,A,L,3 - 0.002 xPB_C,N,O,H,A,L,0,3
        - 0.002 xPB_C,N,O,H,A,L,1,3 - 0.002 xPB_C,N,O,H,A,L,2,3
        - 0.002 xPB_C,N,O,H,A,L,3,3 - 0.002 xPB_C,N,O,H,A,L,4,3
        - 0.002 xGS_C,N,O,H,A,L,0,3 - 0.002 xGS_C,N,O,H,A,L,1,3
        - 0.002 xGS_C,N,O,H,A,L,2,3 - 0.002 xGS_C,N,O,H,A,L,3,3
        - 0.002 xGS_C,N,O,H,A,L,4,3 - 0.002 xEAe_C,N,O,H,A,L,3
        - 0.002 xEAr_C,N,O,H,A,3,1 - 0.002 xEAr_C,N,O,H,A,3,2
        + fire_C,N,O,H,A,3,P + fire_C,N,O,H,A,3,D + fire_C,N,O,H,A,3,W
        + fire_C,N,O,H,A,3,C + fire_C,N,O,H,A,3,I + fire_C,N,O,H,A,3,A
        + fire_C,N,O,H,A,3,L + fire_C,N,O,H,A,3,N  = 0
 c1617: - 0.002 xNG_C,N,O,H,A,L,4 - 0.002 xPB_C,N,O,H,A,L,0,4
        - 0.002 xPB_C,N,O,H,A,L,1,4 - 0.002 xPB_C,N,O,H,A,L,2,4
        - 0.002 xPB_C,N,O,H,A,L,3,4 - 0.002 xPB_C,N,O,H,A,L,4,4
        - 0.002 xGS_C,N,O,H,A,L,0,4 - 0.002 xGS_C,N,O,H,A,L,1,4
        - 0.002 xGS_C,N,O,H,A,L,2,4 - 0.002 xGS_C,N,O,H,A,L,3,4
        - 0.002 xGS_C,N,O,H,A,L,4,4 - 0.002 xEAe_C,N,O,H,A,L,4
        - 0.002 xEAr_C,N,O,H,A,4,1 - 0.002 xEAr_C,N,O,H,A,4,2
        - 0.002 xEAr_C,N,O,H,A,4,3 + fire_C,N,O,H,A,4,P + fire_C,N,O,H,A,4,D
        + fire_C,N,O,H,A,4,W + fire_C,N,O,H,A,4,C + fire_C,N,O,H,A,4,I
        + fire_C,N,O,H,A,4,A + fire_C,N,O,H,A,4,L + fire_C,N,O,H,A,4,N  = 0
 c1618: - 0.002 xNG_C,N,O,H,A,L,5 - 0.002 xPB_C,N,O,H,A,L,0,5
        - 0.002 xPB_C,N,O,H,A,L,1,5 - 0.002 xPB_C,N,O,H,A,L,2,5
        - 0.002 xPB_C,N,O,H,A,L,3,5 - 0.002 xPB_C,N,O,H,A,L,4,5
        - 0.002 xGS_C,N,O,H,A,L,0,5 - 0.002 xGS_C,N,O,H,A,L,1,5
        - 0.002 xGS_C,N,O,H,A,L,2,5 - 0.002 xGS_C,N,O,H,A,L,3,5
        - 0.002 xGS_C,N,O,H,A,L,4,5 - 0.002 xEAe_C,N,O,H,A,L,5
        - 0.002 xEAr_C,N,O,H,A,5,1 - 0.002 xEAr_C,N,O,H,A,5,2
        - 0.002 xEAr_C,N,O,H,A,5,3 - 0.002 xEAr_C,N,O,H,A,5,4
        + fire_C,N,O,H,A,5,P + fire_C,N,O,H,A,5,D + fire_C,N,O,H,A,5,W
        + fire_C,N,O,H,A,5,C + fire_C,N,O,H,A,5,I + fire_C,N,O,H,A,5,A
        + fire_C,N,O,H,A,5,L + fire_C,N,O,H,A,5,N  = 0
 c1619: fire_C,N,O,H,L,1,P + fire_C,N,O,H,L,1,D + fire_C,N,O,H,L,1,W
        + fire_C,N,O,H,L,1,C + fire_C,N,O,H,L,1,I + fire_C,N,O,H,L,1,A
        + fire_C,N,O,H,L,1,L + fire_C,N,O,H,L,1,N  = 0
 c1620: - 0.002 xEAr_C,N,O,H,L,2,1 + fire_C,N,O,H,L,2,P + fire_C,N,O,H,L,2,D
        + fire_C,N,O,H,L,2,W + fire_C,N,O,H,L,2,C + fire_C,N,O,H,L,2,I
        + fire_C,N,O,H,L,2,A + fire_C,N,O,H,L,2,L + fire_C,N,O,H,L,2,N  = 0
 c1621: - 0.002 xEAr_C,N,O,H,L,3,1 - 0.002 xEAr_C,N,O,H,L,3,2
        + fire_C,N,O,H,L,3,P + fire_C,N,O,H,L,3,D + fire_C,N,O,H,L,3,W
        + fire_C,N,O,H,L,3,C + fire_C,N,O,H,L,3,I + fire_C,N,O,H,L,3,A
        + fire_C,N,O,H,L,3,L + fire_C,N,O,H,L,3,N  = 0
 c1622: - 0.002 xEAr_C,N,O,H,L,4,1 - 0.002 xEAr_C,N,O,H,L,4,2
        - 0.002 xEAr_C,N,O,H,L,4,3 + fire_C,N,O,H,L,4,P + fire_C,N,O,H,L,4,D
        + fire_C,N,O,H,L,4,W + fire_C,N,O,H,L,4,C + fire_C,N,O,H,L,4,I
        + fire_C,N,O,H,L,4,A + fire_C,N,O,H,L,4,L + fire_C,N,O,H,L,4,N  = 0
 c1623: - 0.002 xEAr_C,N,O,H,L,5,1 - 0.002 xEAr_C,N,O,H,L,5,2
        - 0.002 xEAr_C,N,O,H,L,5,3 - 0.002 xEAr_C,N,O,H,L,5,4
        + fire_C,N,O,H,L,5,P + fire_C,N,O,H,L,5,D + fire_C,N,O,H,L,5,W
        + fire_C,N,O,H,L,5,C + fire_C,N,O,H,L,5,I + fire_C,N,O,H,L,5,A
        + fire_C,N,O,H,L,5,L + fire_C,N,O,H,L,5,N  = 0
 c1624: fire_C,N,O,H,N,1,P + fire_C,N,O,H,N,1,D + fire_C,N,O,H,N,1,W
        + fire_C,N,O,H,N,1,C + fire_C,N,O,H,N,1,I + fire_C,N,O,H,N,1,A
        + fire_C,N,O,H,N,1,L + fire_C,N,O,H,N,1,N  = 0
 c1625: fire_C,N,O,H,N,2,P + fire_C,N,O,H,N,2,D + fire_C,N,O,H,N,2,W
        + fire_C,N,O,H,N,2,C + fire_C,N,O,H,N,2,I + fire_C,N,O,H,N,2,A
        + fire_C,N,O,H,N,2,L + fire_C,N,O,H,N,2,N  = 0
 c1626: fire_C,N,O,H,N,3,P + fire_C,N,O,H,N,3,D + fire_C,N,O,H,N,3,W
        + fire_C,N,O,H,N,3,C + fire_C,N,O,H,N,3,I + fire_C,N,O,H,N,3,A
        + fire_C,N,O,H,N,3,L + fire_C,N,O,H,N,3,N  = 0
 c1627: fire_C,N,O,H,N,4,P + fire_C,N,O,H,N,4,D + fire_C,N,O,H,N,4,W
        + fire_C,N,O,H,N,4,C + fire_C,N,O,H,N,4,I + fire_C,N,O,H,N,4,A
        + fire_C,N,O,H,N,4,L + fire_C,N,O,H,N,4,N  = 0
 c1628: fire_C,N,O,H,N,5,P + fire_C,N,O,H,N,5,D + fire_C,N,O,H,N,5,W
        + fire_C,N,O,H,N,5,C + fire_C,N,O,H,N,5,I + fire_C,N,O,H,N,5,A
        + fire_C,N,O,H,N,5,L + fire_C,N,O,H,N,5,N  = 0
 c1629: fire_C,N,O,C,P,1,P + fire_C,N,O,C,P,1,D + fire_C,N,O,C,P,1,W
        + fire_C,N,O,C,P,1,C + fire_C,N,O,C,P,1,I + fire_C,N,O,C,P,1,A
        + fire_C,N,O,C,P,1,L + fire_C,N,O,C,P,1,N  = 0
 c1630: - 0.002 xEAr_C,N,O,C,P,2,1 + fire_C,N,O,C,P,2,P + fire_C,N,O,C,P,2,D
        + fire_C,N,O,C,P,2,W + fire_C,N,O,C,P,2,C + fire_C,N,O,C,P,2,I
        + fire_C,N,O,C,P,2,A + fire_C,N,O,C,P,2,L + fire_C,N,O,C,P,2,N  = 0
 c1631: - 0.002 xEAr_C,N,O,C,P,3,1 - 0.002 xEAr_C,N,O,C,P,3,2
        + fire_C,N,O,C,P,3,P + fire_C,N,O,C,P,3,D + fire_C,N,O,C,P,3,W
        + fire_C,N,O,C,P,3,C + fire_C,N,O,C,P,3,I + fire_C,N,O,C,P,3,A
        + fire_C,N,O,C,P,3,L + fire_C,N,O,C,P,3,N  = 0
 c1632: - 0.002 xEAr_C,N,O,C,P,4,1 - 0.002 xEAr_C,N,O,C,P,4,2
        - 0.002 xEAr_C,N,O,C,P,4,3 + fire_C,N,O,C,P,4,P + fire_C,N,O,C,P,4,D
        + fire_C,N,O,C,P,4,W + fire_C,N,O,C,P,4,C + fire_C,N,O,C,P,4,I
        + fire_C,N,O,C,P,4,A + fire_C,N,O,C,P,4,L + fire_C,N,O,C,P,4,N  = 0
 c1633: - 0.002 xEAr_C,N,O,C,P,5,1 - 0.002 xEAr_C,N,O,C,P,5,2
        - 0.002 xEAr_C,N,O,C,P,5,3 - 0.002 xEAr_C,N,O,C,P,5,4
        + fire_C,N,O,C,P,5,P + fire_C,N,O,C,P,5,D + fire_C,N,O,C,P,5,W
        + fire_C,N,O,C,P,5,C + fire_C,N,O,C,P,5,I + fire_C,N,O,C,P,5,A
        + fire_C,N,O,C,P,5,L + fire_C,N,O,C,P,5,N  = 0
 c1634: fire_C,N,O,C,D,1,P + fire_C,N,O,C,D,1,D + fire_C,N,O,C,D,1,W
        + fire_C,N,O,C,D,1,C + fire_C,N,O,C,D,1,I + fire_C,N,O,C,D,1,A
        + fire_C,N,O,C,D,1,L + fire_C,N,O,C,D,1,N  = 0
 c1635: - 0.002 xEAr_C,N,O,C,D,2,1 + fire_C,N,O,C,D,2,P + fire_C,N,O,C,D,2,D
        + fire_C,N,O,C,D,2,W + fire_C,N,O,C,D,2,C + fire_C,N,O,C,D,2,I
        + fire_C,N,O,C,D,2,A + fire_C,N,O,C,D,2,L + fire_C,N,O,C,D,2,N  = 0
 c1636: - 0.002 xEAr_C,N,O,C,D,3,1 - 0.002 xEAr_C,N,O,C,D,3,2
        + fire_C,N,O,C,D,3,P + fire_C,N,O,C,D,3,D + fire_C,N,O,C,D,3,W
        + fire_C,N,O,C,D,3,C + fire_C,N,O,C,D,3,I + fire_C,N,O,C,D,3,A
        + fire_C,N,O,C,D,3,L + fire_C,N,O,C,D,3,N  = 0
 c1637: - 0.002 xEAr_C,N,O,C,D,4,1 - 0.002 xEAr_C,N,O,C,D,4,2
        - 0.002 xEAr_C,N,O,C,D,4,3 + fire_C,N,O,C,D,4,P + fire_C,N,O,C,D,4,D
        + fire_C,N,O,C,D,4,W + fire_C,N,O,C,D,4,C + fire_C,N,O,C,D,4,I
        + fire_C,N,O,C,D,4,A + fire_C,N,O,C,D,4,L + fire_C,N,O,C,D,4,N  = 0
 c1638: - 0.002 xEAr_C,N,O,C,D,5,1 - 0.002 xEAr_C,N,O,C,D,5,2
        - 0.002 xEAr_C,N,O,C,D,5,3 - 0.002 xEAr_C,N,O,C,D,5,4
        + fire_C,N,O,C,D,5,P + fire_C,N,O,C,D,5,D + fire_C,N,O,C,D,5,W
        + fire_C,N,O,C,D,5,C + fire_C,N,O,C,D,5,I + fire_C,N,O,C,D,5,A
        + fire_C,N,O,C,D,5,L + fire_C,N,O,C,D,5,N  = 0
 c1639: fire_C,N,O,C,W,1,P + fire_C,N,O,C,W,1,D + fire_C,N,O,C,W,1,W
        + fire_C,N,O,C,W,1,C + fire_C,N,O,C,W,1,I + fire_C,N,O,C,W,1,A
        + fire_C,N,O,C,W,1,L + fire_C,N,O,C,W,1,N  = 0
 c1640: - 0.23 xEAr_C,N,O,C,W,2,1 + fire_C,N,O,C,W,2,P + fire_C,N,O,C,W,2,D
        + fire_C,N,O,C,W,2,W + fire_C,N,O,C,W,2,C + fire_C,N,O,C,W,2,I
        + fire_C,N,O,C,W,2,A + fire_C,N,O,C,W,2,L + fire_C,N,O,C,W,2,N  = 0
 c1641: - 0.23 xEAr_C,N,O,C,W,3,1 - 0.002 xEAr_C,N,O,C,W,3,2
        + fire_C,N,O,C,W,3,P + fire_C,N,O,C,W,3,D + fire_C,N,O,C,W,3,W
        + fire_C,N,O,C,W,3,C + fire_C,N,O,C,W,3,I + fire_C,N,O,C,W,3,A
        + fire_C,N,O,C,W,3,L + fire_C,N,O,C,W,3,N  = 0
 c1642: - 0.23 xEAr_C,N,O,C,W,4,1 - 0.002 xEAr_C,N,O,C,W,4,2
        - 0.002 xEAr_C,N,O,C,W,4,3 + fire_C,N,O,C,W,4,P + fire_C,N,O,C,W,4,D
        + fire_C,N,O,C,W,4,W + fire_C,N,O,C,W,4,C + fire_C,N,O,C,W,4,I
        + fire_C,N,O,C,W,4,A + fire_C,N,O,C,W,4,L + fire_C,N,O,C,W,4,N  = 0
 c1643: - 0.23 xEAr_C,N,O,C,W,5,1 - 0.002 xEAr_C,N,O,C,W,5,2
        - 0.002 xEAr_C,N,O,C,W,5,3 - 0.002 xEAr_C,N,O,C,W,5,4
        + fire_C,N,O,C,W,5,P + fire_C,N,O,C,W,5,D + fire_C,N,O,C,W,5,W
        + fire_C,N,O,C,W,5,C + fire_C,N,O,C,W,5,I + fire_C,N,O,C,W,5,A
        + fire_C,N,O,C,W,5,L + fire_C,N,O,C,W,5,N  = 0
 c1644: fire_C,N,O,C,C,1,P + fire_C,N,O,C,C,1,D + fire_C,N,O,C,C,1,W
        + fire_C,N,O,C,C,1,C + fire_C,N,O,C,C,1,I + fire_C,N,O,C,C,1,A
        + fire_C,N,O,C,C,1,L + fire_C,N,O,C,C,1,N  = 0
 c1645: - 0.002 xEAr_C,N,O,C,C,2,1 + fire_C,N,O,C,C,2,P + fire_C,N,O,C,C,2,D
        + fire_C,N,O,C,C,2,W + fire_C,N,O,C,C,2,C + fire_C,N,O,C,C,2,I
        + fire_C,N,O,C,C,2,A + fire_C,N,O,C,C,2,L + fire_C,N,O,C,C,2,N  = 0
 c1646: - 0.002 xEAr_C,N,O,C,C,3,1 - 0.002 xEAr_C,N,O,C,C,3,2
        + fire_C,N,O,C,C,3,P + fire_C,N,O,C,C,3,D + fire_C,N,O,C,C,3,W
        + fire_C,N,O,C,C,3,C + fire_C,N,O,C,C,3,I + fire_C,N,O,C,C,3,A
        + fire_C,N,O,C,C,3,L + fire_C,N,O,C,C,3,N  = 0
 c1647: - 0.002 xEAr_C,N,O,C,C,4,1 - 0.002 xEAr_C,N,O,C,C,4,2
        - 0.002 xEAr_C,N,O,C,C,4,3 + fire_C,N,O,C,C,4,P + fire_C,N,O,C,C,4,D
        + fire_C,N,O,C,C,4,W + fire_C,N,O,C,C,4,C + fire_C,N,O,C,C,4,I
        + fire_C,N,O,C,C,4,A + fire_C,N,O,C,C,4,L + fire_C,N,O,C,C,4,N  = 0
 c1648: - 0.002 xEAr_C,N,O,C,C,5,1 - 0.002 xEAr_C,N,O,C,C,5,2
        - 0.002 xEAr_C,N,O,C,C,5,3 - 0.002 xEAr_C,N,O,C,C,5,4
        + fire_C,N,O,C,C,5,P + fire_C,N,O,C,C,5,D + fire_C,N,O,C,C,5,W
        + fire_C,N,O,C,C,5,C + fire_C,N,O,C,C,5,I + fire_C,N,O,C,C,5,A
        + fire_C,N,O,C,C,5,L + fire_C,N,O,C,C,5,N  = 0
 c1649: fire_C,N,O,C,I,1,P + fire_C,N,O,C,I,1,D + fire_C,N,O,C,I,1,W
        + fire_C,N,O,C,I,1,C + fire_C,N,O,C,I,1,I + fire_C,N,O,C,I,1,A
        + fire_C,N,O,C,I,1,L + fire_C,N,O,C,I,1,N  = 0
 c1650: - 0.002 xEAr_C,N,O,C,I,2,1 + fire_C,N,O,C,I,2,P + fire_C,N,O,C,I,2,D
        + fire_C,N,O,C,I,2,W + fire_C,N,O,C,I,2,C + fire_C,N,O,C,I,2,I
        + fire_C,N,O,C,I,2,A + fire_C,N,O,C,I,2,L + fire_C,N,O,C,I,2,N  = 0
 c1651: - 0.002 xEAr_C,N,O,C,I,3,1 - 0.002 xEAr_C,N,O,C,I,3,2
        + fire_C,N,O,C,I,3,P + fire_C,N,O,C,I,3,D + fire_C,N,O,C,I,3,W
        + fire_C,N,O,C,I,3,C + fire_C,N,O,C,I,3,I + fire_C,N,O,C,I,3,A
        + fire_C,N,O,C,I,3,L + fire_C,N,O,C,I,3,N  = 0
 c1652: - 0.002 xEAr_C,N,O,C,I,4,1 - 0.002 xEAr_C,N,O,C,I,4,2
        - 0.002 xEAr_C,N,O,C,I,4,3 + fire_C,N,O,C,I,4,P + fire_C,N,O,C,I,4,D
        + fire_C,N,O,C,I,4,W + fire_C,N,O,C,I,4,C + fire_C,N,O,C,I,4,I
        + fire_C,N,O,C,I,4,A + fire_C,N,O,C,I,4,L + fire_C,N,O,C,I,4,N  = 0
 c1653: - 0.002 xEAr_C,N,O,C,I,5,1 - 0.002 xEAr_C,N,O,C,I,5,2
        - 0.002 xEAr_C,N,O,C,I,5,3 - 0.002 xEAr_C,N,O,C,I,5,4
        + fire_C,N,O,C,I,5,P + fire_C,N,O,C,I,5,D + fire_C,N,O,C,I,5,W
        + fire_C,N,O,C,I,5,C + fire_C,N,O,C,I,5,I + fire_C,N,O,C,I,5,A
        + fire_C,N,O,C,I,5,L + fire_C,N,O,C,I,5,N  = 0
 c1654: - 0.002 xNG_C,N,O,C,A,L,1 - 0.002 xPB_C,N,O,C,A,L,0,1
        - 0.002 xPB_C,N,O,C,A,L,1,1 - 0.002 xPB_C,N,O,C,A,L,2,1
        - 0.002 xPB_C,N,O,C,A,L,3,1 - 0.002 xPB_C,N,O,C,A,L,4,1
        - 0.002 xGS_C,N,O,C,A,L,0,1 - 0.002 xGS_C,N,O,C,A,L,1,1
        - 0.002 xGS_C,N,O,C,A,L,2,1 - 0.002 xGS_C,N,O,C,A,L,3,1
        - 0.002 xGS_C,N,O,C,A,L,4,1 - 0.002 xEAe_C,N,O,C,A,L,1
        + fire_C,N,O,C,A,1,P + fire_C,N,O,C,A,1,D + fire_C,N,O,C,A,1,W
        + fire_C,N,O,C,A,1,C + fire_C,N,O,C,A,1,I + fire_C,N,O,C,A,1,A
        + fire_C,N,O,C,A,1,L + fire_C,N,O,C,A,1,N  = 0
 c1655: - 0.002 xNG_C,N,O,C,A,L,2 - 0.002 xPB_C,N,O,C,A,L,0,2
        - 0.002 xPB_C,N,O,C,A,L,1,2 - 0.002 xPB_C,N,O,C,A,L,2,2
        - 0.002 xPB_C,N,O,C,A,L,3,2 - 0.002 xPB_C,N,O,C,A,L,4,2
        - 0.002 xGS_C,N,O,C,A,L,0,2 - 0.002 xGS_C,N,O,C,A,L,1,2
        - 0.002 xGS_C,N,O,C,A,L,2,2 - 0.002 xGS_C,N,O,C,A,L,3,2
        - 0.002 xGS_C,N,O,C,A,L,4,2 - 0.002 xEAe_C,N,O,C,A,L,2
        - 0.002 xEAr_C,N,O,C,A,2,1 + fire_C,N,O,C,A,2,P + fire_C,N,O,C,A,2,D
        + fire_C,N,O,C,A,2,W + fire_C,N,O,C,A,2,C + fire_C,N,O,C,A,2,I
        + fire_C,N,O,C,A,2,A + fire_C,N,O,C,A,2,L + fire_C,N,O,C,A,2,N  = 0
 c1656: - 0.002 xNG_C,N,O,C,A,L,3 - 0.002 xPB_C,N,O,C,A,L,0,3
        - 0.002 xPB_C,N,O,C,A,L,1,3 - 0.002 xPB_C,N,O,C,A,L,2,3
        - 0.002 xPB_C,N,O,C,A,L,3,3 - 0.002 xPB_C,N,O,C,A,L,4,3
        - 0.002 xGS_C,N,O,C,A,L,0,3 - 0.002 xGS_C,N,O,C,A,L,1,3
        - 0.002 xGS_C,N,O,C,A,L,2,3 - 0.002 xGS_C,N,O,C,A,L,3,3
        - 0.002 xGS_C,N,O,C,A,L,4,3 - 0.002 xEAe_C,N,O,C,A,L,3
        - 0.002 xEAr_C,N,O,C,A,3,1 - 0.002 xEAr_C,N,O,C,A,3,2
        + fire_C,N,O,C,A,3,P + fire_C,N,O,C,A,3,D + fire_C,N,O,C,A,3,W
        + fire_C,N,O,C,A,3,C + fire_C,N,O,C,A,3,I + fire_C,N,O,C,A,3,A
        + fire_C,N,O,C,A,3,L + fire_C,N,O,C,A,3,N  = 0
 c1657: - 0.002 xNG_C,N,O,C,A,L,4 - 0.002 xPB_C,N,O,C,A,L,0,4
        - 0.002 xPB_C,N,O,C,A,L,1,4 - 0.002 xPB_C,N,O,C,A,L,2,4
        - 0.002 xPB_C,N,O,C,A,L,3,4 - 0.002 xPB_C,N,O,C,A,L,4,4
        - 0.002 xGS_C,N,O,C,A,L,0,4 - 0.002 xGS_C,N,O,C,A,L,1,4
        - 0.002 xGS_C,N,O,C,A,L,2,4 - 0.002 xGS_C,N,O,C,A,L,3,4
        - 0.002 xGS_C,N,O,C,A,L,4,4 - 0.002 xEAe_C,N,O,C,A,L,4
        - 0.002 xEAr_C,N,O,C,A,4,1 - 0.002 xEAr_C,N,O,C,A,4,2
        - 0.002 xEAr_C,N,O,C,A,4,3 + fire_C,N,O,C,A,4,P + fire_C,N,O,C,A,4,D
        + fire_C,N,O,C,A,4,W + fire_C,N,O,C,A,4,C + fire_C,N,O,C,A,4,I
        + fire_C,N,O,C,A,4,A + fire_C,N,O,C,A,4,L + fire_C,N,O,C,A,4,N  = 0
 c1658: - 0.002 xNG_C,N,O,C,A,L,5 - 0.002 xPB_C,N,O,C,A,L,0,5
        - 0.002 xPB_C,N,O,C,A,L,1,5 - 0.002 xPB_C,N,O,C,A,L,2,5
        - 0.002 xPB_C,N,O,C,A,L,3,5 - 0.002 xPB_C,N,O,C,A,L,4,5
        - 0.002 xGS_C,N,O,C,A,L,0,5 - 0.002 xGS_C,N,O,C,A,L,1,5
        - 0.002 xGS_C,N,O,C,A,L,2,5 - 0.002 xGS_C,N,O,C,A,L,3,5
        - 0.002 xGS_C,N,O,C,A,L,4,5 - 0.002 xEAe_C,N,O,C,A,L,5
        - 0.002 xEAr_C,N,O,C,A,5,1 - 0.002 xEAr_C,N,O,C,A,5,2
        - 0.002 xEAr_C,N,O,C,A,5,3 - 0.002 xEAr_C,N,O,C,A,5,4
        + fire_C,N,O,C,A,5,P + fire_C,N,O,C,A,5,D + fire_C,N,O,C,A,5,W
        + fire_C,N,O,C,A,5,C + fire_C,N,O,C,A,5,I + fire_C,N,O,C,A,5,A
        + fire_C,N,O,C,A,5,L + fire_C,N,O,C,A,5,N  = 0
 c1659: fire_C,N,O,C,L,1,P + fire_C,N,O,C,L,1,D + fire_C,N,O,C,L,1,W
        + fire_C,N,O,C,L,1,C + fire_C,N,O,C,L,1,I + fire_C,N,O,C,L,1,A
        + fire_C,N,O,C,L,1,L + fire_C,N,O,C,L,1,N  = 0
 c1660: - 0.002 xEAr_C,N,O,C,L,2,1 + fire_C,N,O,C,L,2,P + fire_C,N,O,C,L,2,D
        + fire_C,N,O,C,L,2,W + fire_C,N,O,C,L,2,C + fire_C,N,O,C,L,2,I
        + fire_C,N,O,C,L,2,A + fire_C,N,O,C,L,2,L + fire_C,N,O,C,L,2,N  = 0
 c1661: - 0.002 xEAr_C,N,O,C,L,3,1 - 0.002 xEAr_C,N,O,C,L,3,2
        + fire_C,N,O,C,L,3,P + fire_C,N,O,C,L,3,D + fire_C,N,O,C,L,3,W
        + fire_C,N,O,C,L,3,C + fire_C,N,O,C,L,3,I + fire_C,N,O,C,L,3,A
        + fire_C,N,O,C,L,3,L + fire_C,N,O,C,L,3,N  = 0
 c1662: - 0.002 xEAr_C,N,O,C,L,4,1 - 0.002 xEAr_C,N,O,C,L,4,2
        - 0.002 xEAr_C,N,O,C,L,4,3 + fire_C,N,O,C,L,4,P + fire_C,N,O,C,L,4,D
        + fire_C,N,O,C,L,4,W + fire_C,N,O,C,L,4,C + fire_C,N,O,C,L,4,I
        + fire_C,N,O,C,L,4,A + fire_C,N,O,C,L,4,L + fire_C,N,O,C,L,4,N  = 0
 c1663: - 0.002 xEAr_C,N,O,C,L,5,1 - 0.002 xEAr_C,N,O,C,L,5,2
        - 0.002 xEAr_C,N,O,C,L,5,3 - 0.002 xEAr_C,N,O,C,L,5,4
        + fire_C,N,O,C,L,5,P + fire_C,N,O,C,L,5,D + fire_C,N,O,C,L,5,W
        + fire_C,N,O,C,L,5,C + fire_C,N,O,C,L,5,I + fire_C,N,O,C,L,5,A
        + fire_C,N,O,C,L,5,L + fire_C,N,O,C,L,5,N  = 0
 c1664: fire_C,N,O,C,N,1,P + fire_C,N,O,C,N,1,D + fire_C,N,O,C,N,1,W
        + fire_C,N,O,C,N,1,C + fire_C,N,O,C,N,1,I + fire_C,N,O,C,N,1,A
        + fire_C,N,O,C,N,1,L + fire_C,N,O,C,N,1,N  = 0
 c1665: fire_C,N,O,C,N,2,P + fire_C,N,O,C,N,2,D + fire_C,N,O,C,N,2,W
        + fire_C,N,O,C,N,2,C + fire_C,N,O,C,N,2,I + fire_C,N,O,C,N,2,A
        + fire_C,N,O,C,N,2,L + fire_C,N,O,C,N,2,N  = 0
 c1666: fire_C,N,O,C,N,3,P + fire_C,N,O,C,N,3,D + fire_C,N,O,C,N,3,W
        + fire_C,N,O,C,N,3,C + fire_C,N,O,C,N,3,I + fire_C,N,O,C,N,3,A
        + fire_C,N,O,C,N,3,L + fire_C,N,O,C,N,3,N  = 0
 c1667: fire_C,N,O,C,N,4,P + fire_C,N,O,C,N,4,D + fire_C,N,O,C,N,4,W
        + fire_C,N,O,C,N,4,C + fire_C,N,O,C,N,4,I + fire_C,N,O,C,N,4,A
        + fire_C,N,O,C,N,4,L + fire_C,N,O,C,N,4,N  = 0
 c1668: fire_C,N,O,C,N,5,P + fire_C,N,O,C,N,5,D + fire_C,N,O,C,N,5,W
        + fire_C,N,O,C,N,5,C + fire_C,N,O,C,N,5,I + fire_C,N,O,C,N,5,A
        + fire_C,N,O,C,N,5,L + fire_C,N,O,C,N,5,N  = 0
 c1669: fire_C,N,O,R,P,1,P + fire_C,N,O,R,P,1,D + fire_C,N,O,R,P,1,W
        + fire_C,N,O,R,P,1,C + fire_C,N,O,R,P,1,I + fire_C,N,O,R,P,1,A
        + fire_C,N,O,R,P,1,L + fire_C,N,O,R,P,1,N  = 0
 c1670: - 0.002 xEAr_C,N,O,R,P,2,1 + fire_C,N,O,R,P,2,P + fire_C,N,O,R,P,2,D
        + fire_C,N,O,R,P,2,W + fire_C,N,O,R,P,2,C + fire_C,N,O,R,P,2,I
        + fire_C,N,O,R,P,2,A + fire_C,N,O,R,P,2,L + fire_C,N,O,R,P,2,N  = 0
 c1671: - 0.002 xEAr_C,N,O,R,P,3,1 - 0.002 xEAr_C,N,O,R,P,3,2
        + fire_C,N,O,R,P,3,P + fire_C,N,O,R,P,3,D + fire_C,N,O,R,P,3,W
        + fire_C,N,O,R,P,3,C + fire_C,N,O,R,P,3,I + fire_C,N,O,R,P,3,A
        + fire_C,N,O,R,P,3,L + fire_C,N,O,R,P,3,N  = 0
 c1672: - 0.002 xEAr_C,N,O,R,P,4,1 - 0.002 xEAr_C,N,O,R,P,4,2
        - 0.002 xEAr_C,N,O,R,P,4,3 + fire_C,N,O,R,P,4,P + fire_C,N,O,R,P,4,D
        + fire_C,N,O,R,P,4,W + fire_C,N,O,R,P,4,C + fire_C,N,O,R,P,4,I
        + fire_C,N,O,R,P,4,A + fire_C,N,O,R,P,4,L + fire_C,N,O,R,P,4,N  = 0
 c1673: - 0.002 xEAr_C,N,O,R,P,5,1 - 0.002 xEAr_C,N,O,R,P,5,2
        - 0.002 xEAr_C,N,O,R,P,5,3 - 0.002 xEAr_C,N,O,R,P,5,4
        + fire_C,N,O,R,P,5,P + fire_C,N,O,R,P,5,D + fire_C,N,O,R,P,5,W
        + fire_C,N,O,R,P,5,C + fire_C,N,O,R,P,5,I + fire_C,N,O,R,P,5,A
        + fire_C,N,O,R,P,5,L + fire_C,N,O,R,P,5,N  = 0
 c1674: fire_C,N,O,R,D,1,P + fire_C,N,O,R,D,1,D + fire_C,N,O,R,D,1,W
        + fire_C,N,O,R,D,1,C + fire_C,N,O,R,D,1,I + fire_C,N,O,R,D,1,A
        + fire_C,N,O,R,D,1,L + fire_C,N,O,R,D,1,N  = 0
 c1675: - 0.002 xEAr_C,N,O,R,D,2,1 + fire_C,N,O,R,D,2,P + fire_C,N,O,R,D,2,D
        + fire_C,N,O,R,D,2,W + fire_C,N,O,R,D,2,C + fire_C,N,O,R,D,2,I
        + fire_C,N,O,R,D,2,A + fire_C,N,O,R,D,2,L + fire_C,N,O,R,D,2,N  = 0
 c1676: - 0.002 xEAr_C,N,O,R,D,3,1 - 0.002 xEAr_C,N,O,R,D,3,2
        + fire_C,N,O,R,D,3,P + fire_C,N,O,R,D,3,D + fire_C,N,O,R,D,3,W
        + fire_C,N,O,R,D,3,C + fire_C,N,O,R,D,3,I + fire_C,N,O,R,D,3,A
        + fire_C,N,O,R,D,3,L + fire_C,N,O,R,D,3,N  = 0
 c1677: - 0.002 xEAr_C,N,O,R,D,4,1 - 0.002 xEAr_C,N,O,R,D,4,2
        - 0.002 xEAr_C,N,O,R,D,4,3 + fire_C,N,O,R,D,4,P + fire_C,N,O,R,D,4,D
        + fire_C,N,O,R,D,4,W + fire_C,N,O,R,D,4,C + fire_C,N,O,R,D,4,I
        + fire_C,N,O,R,D,4,A + fire_C,N,O,R,D,4,L + fire_C,N,O,R,D,4,N  = 0
 c1678: - 0.002 xEAr_C,N,O,R,D,5,1 - 0.002 xEAr_C,N,O,R,D,5,2
        - 0.002 xEAr_C,N,O,R,D,5,3 - 0.002 xEAr_C,N,O,R,D,5,4
        + fire_C,N,O,R,D,5,P + fire_C,N,O,R,D,5,D + fire_C,N,O,R,D,5,W
        + fire_C,N,O,R,D,5,C + fire_C,N,O,R,D,5,I + fire_C,N,O,R,D,5,A
        + fire_C,N,O,R,D,5,L + fire_C,N,O,R,D,5,N  = 0
 c1679: fire_C,N,O,R,W,1,P + fire_C,N,O,R,W,1,D + fire_C,N,O,R,W,1,W
        + fire_C,N,O,R,W,1,C + fire_C,N,O,R,W,1,I + fire_C,N,O,R,W,1,A
        + fire_C,N,O,R,W,1,L + fire_C,N,O,R,W,1,N  = 0
 c1680: - 0.23 xEAr_C,N,O,R,W,2,1 + fire_C,N,O,R,W,2,P + fire_C,N,O,R,W,2,D
        + fire_C,N,O,R,W,2,W + fire_C,N,O,R,W,2,C + fire_C,N,O,R,W,2,I
        + fire_C,N,O,R,W,2,A + fire_C,N,O,R,W,2,L + fire_C,N,O,R,W,2,N  = 0
 c1681: - 0.23 xEAr_C,N,O,R,W,3,1 - 0.002 xEAr_C,N,O,R,W,3,2
        + fire_C,N,O,R,W,3,P + fire_C,N,O,R,W,3,D + fire_C,N,O,R,W,3,W
        + fire_C,N,O,R,W,3,C + fire_C,N,O,R,W,3,I + fire_C,N,O,R,W,3,A
        + fire_C,N,O,R,W,3,L + fire_C,N,O,R,W,3,N  = 0
 c1682: - 0.23 xEAr_C,N,O,R,W,4,1 - 0.002 xEAr_C,N,O,R,W,4,2
        - 0.002 xEAr_C,N,O,R,W,4,3 + fire_C,N,O,R,W,4,P + fire_C,N,O,R,W,4,D
        + fire_C,N,O,R,W,4,W + fire_C,N,O,R,W,4,C + fire_C,N,O,R,W,4,I
        + fire_C,N,O,R,W,4,A + fire_C,N,O,R,W,4,L + fire_C,N,O,R,W,4,N  = 0
 c1683: - 0.23 xEAr_C,N,O,R,W,5,1 - 0.002 xEAr_C,N,O,R,W,5,2
        - 0.002 xEAr_C,N,O,R,W,5,3 - 0.002 xEAr_C,N,O,R,W,5,4
        + fire_C,N,O,R,W,5,P + fire_C,N,O,R,W,5,D + fire_C,N,O,R,W,5,W
        + fire_C,N,O,R,W,5,C + fire_C,N,O,R,W,5,I + fire_C,N,O,R,W,5,A
        + fire_C,N,O,R,W,5,L + fire_C,N,O,R,W,5,N  = 0
 c1684: fire_C,N,O,R,C,1,P + fire_C,N,O,R,C,1,D + fire_C,N,O,R,C,1,W
        + fire_C,N,O,R,C,1,C + fire_C,N,O,R,C,1,I + fire_C,N,O,R,C,1,A
        + fire_C,N,O,R,C,1,L + fire_C,N,O,R,C,1,N  = 0
 c1685: - 0.002 xEAr_C,N,O,R,C,2,1 + fire_C,N,O,R,C,2,P + fire_C,N,O,R,C,2,D
        + fire_C,N,O,R,C,2,W + fire_C,N,O,R,C,2,C + fire_C,N,O,R,C,2,I
        + fire_C,N,O,R,C,2,A + fire_C,N,O,R,C,2,L + fire_C,N,O,R,C,2,N  = 0
 c1686: - 0.002 xEAr_C,N,O,R,C,3,1 - 0.002 xEAr_C,N,O,R,C,3,2
        + fire_C,N,O,R,C,3,P + fire_C,N,O,R,C,3,D + fire_C,N,O,R,C,3,W
        + fire_C,N,O,R,C,3,C + fire_C,N,O,R,C,3,I + fire_C,N,O,R,C,3,A
        + fire_C,N,O,R,C,3,L + fire_C,N,O,R,C,3,N  = 0
 c1687: - 0.002 xEAr_C,N,O,R,C,4,1 - 0.002 xEAr_C,N,O,R,C,4,2
        - 0.002 xEAr_C,N,O,R,C,4,3 + fire_C,N,O,R,C,4,P + fire_C,N,O,R,C,4,D
        + fire_C,N,O,R,C,4,W + fire_C,N,O,R,C,4,C + fire_C,N,O,R,C,4,I
        + fire_C,N,O,R,C,4,A + fire_C,N,O,R,C,4,L + fire_C,N,O,R,C,4,N  = 0
 c1688: - 0.002 xEAr_C,N,O,R,C,5,1 - 0.002 xEAr_C,N,O,R,C,5,2
        - 0.002 xEAr_C,N,O,R,C,5,3 - 0.002 xEAr_C,N,O,R,C,5,4
        + fire_C,N,O,R,C,5,P + fire_C,N,O,R,C,5,D + fire_C,N,O,R,C,5,W
        + fire_C,N,O,R,C,5,C + fire_C,N,O,R,C,5,I + fire_C,N,O,R,C,5,A
        + fire_C,N,O,R,C,5,L + fire_C,N,O,R,C,5,N  = 0
 c1689: fire_C,N,O,R,I,1,P + fire_C,N,O,R,I,1,D + fire_C,N,O,R,I,1,W
        + fire_C,N,O,R,I,1,C + fire_C,N,O,R,I,1,I + fire_C,N,O,R,I,1,A
        + fire_C,N,O,R,I,1,L + fire_C,N,O,R,I,1,N  = 0
 c1690: - 0.002 xEAr_C,N,O,R,I,2,1 + fire_C,N,O,R,I,2,P + fire_C,N,O,R,I,2,D
        + fire_C,N,O,R,I,2,W + fire_C,N,O,R,I,2,C + fire_C,N,O,R,I,2,I
        + fire_C,N,O,R,I,2,A + fire_C,N,O,R,I,2,L + fire_C,N,O,R,I,2,N  = 0
 c1691: - 0.002 xEAr_C,N,O,R,I,3,1 - 0.002 xEAr_C,N,O,R,I,3,2
        + fire_C,N,O,R,I,3,P + fire_C,N,O,R,I,3,D + fire_C,N,O,R,I,3,W
        + fire_C,N,O,R,I,3,C + fire_C,N,O,R,I,3,I + fire_C,N,O,R,I,3,A
        + fire_C,N,O,R,I,3,L + fire_C,N,O,R,I,3,N  = 0
 c1692: - 0.002 xEAr_C,N,O,R,I,4,1 - 0.002 xEAr_C,N,O,R,I,4,2
        - 0.002 xEAr_C,N,O,R,I,4,3 + fire_C,N,O,R,I,4,P + fire_C,N,O,R,I,4,D
        + fire_C,N,O,R,I,4,W + fire_C,N,O,R,I,4,C + fire_C,N,O,R,I,4,I
        + fire_C,N,O,R,I,4,A + fire_C,N,O,R,I,4,L + fire_C,N,O,R,I,4,N  = 0
 c1693: - 0.002 xEAr_C,N,O,R,I,5,1 - 0.002 xEAr_C,N,O,R,I,5,2
        - 0.002 xEAr_C,N,O,R,I,5,3 - 0.002 xEAr_C,N,O,R,I,5,4
        + fire_C,N,O,R,I,5,P + fire_C,N,O,R,I,5,D + fire_C,N,O,R,I,5,W
        + fire_C,N,O,R,I,5,C + fire_C,N,O,R,I,5,I + fire_C,N,O,R,I,5,A
        + fire_C,N,O,R,I,5,L + fire_C,N,O,R,I,5,N  = 0
 c1694: - 0.002 xNG_C,N,O,R,A,L,1 - 0.002 xPB_C,N,O,R,A,L,0,1
        - 0.002 xPB_C,N,O,R,A,L,1,1 - 0.002 xPB_C,N,O,R,A,L,2,1
        - 0.002 xPB_C,N,O,R,A,L,3,1 - 0.002 xPB_C,N,O,R,A,L,4,1
        - 0.002 xGS_C,N,O,R,A,L,0,1 - 0.002 xGS_C,N,O,R,A,L,1,1
        - 0.002 xGS_C,N,O,R,A,L,2,1 - 0.002 xGS_C,N,O,R,A,L,3,1
        - 0.002 xGS_C,N,O,R,A,L,4,1 - 0.002 xEAe_C,N,O,R,A,L,1
        + fire_C,N,O,R,A,1,P + fire_C,N,O,R,A,1,D + fire_C,N,O,R,A,1,W
        + fire_C,N,O,R,A,1,C + fire_C,N,O,R,A,1,I + fire_C,N,O,R,A,1,A
        + fire_C,N,O,R,A,1,L + fire_C,N,O,R,A,1,N  = 0
 c1695: - 0.002 xNG_C,N,O,R,A,L,2 - 0.002 xPB_C,N,O,R,A,L,0,2
        - 0.002 xPB_C,N,O,R,A,L,1,2 - 0.002 xPB_C,N,O,R,A,L,2,2
        - 0.002 xPB_C,N,O,R,A,L,3,2 - 0.002 xPB_C,N,O,R,A,L,4,2
        - 0.002 xGS_C,N,O,R,A,L,0,2 - 0.002 xGS_C,N,O,R,A,L,1,2
        - 0.002 xGS_C,N,O,R,A,L,2,2 - 0.002 xGS_C,N,O,R,A,L,3,2
        - 0.002 xGS_C,N,O,R,A,L,4,2 - 0.002 xEAe_C,N,O,R,A,L,2
        - 0.002 xEAr_C,N,O,R,A,2,1 + fire_C,N,O,R,A,2,P + fire_C,N,O,R,A,2,D
        + fire_C,N,O,R,A,2,W + fire_C,N,O,R,A,2,C + fire_C,N,O,R,A,2,I
        + fire_C,N,O,R,A,2,A + fire_C,N,O,R,A,2,L + fire_C,N,O,R,A,2,N  = 0
 c1696: - 0.002 xNG_C,N,O,R,A,L,3 - 0.002 xPB_C,N,O,R,A,L,0,3
        - 0.002 xPB_C,N,O,R,A,L,1,3 - 0.002 xPB_C,N,O,R,A,L,2,3
        - 0.002 xPB_C,N,O,R,A,L,3,3 - 0.002 xPB_C,N,O,R,A,L,4,3
        - 0.002 xGS_C,N,O,R,A,L,0,3 - 0.002 xGS_C,N,O,R,A,L,1,3
        - 0.002 xGS_C,N,O,R,A,L,2,3 - 0.002 xGS_C,N,O,R,A,L,3,3
        - 0.002 xGS_C,N,O,R,A,L,4,3 - 0.002 xEAe_C,N,O,R,A,L,3
        - 0.002 xEAr_C,N,O,R,A,3,1 - 0.002 xEAr_C,N,O,R,A,3,2
        + fire_C,N,O,R,A,3,P + fire_C,N,O,R,A,3,D + fire_C,N,O,R,A,3,W
        + fire_C,N,O,R,A,3,C + fire_C,N,O,R,A,3,I + fire_C,N,O,R,A,3,A
        + fire_C,N,O,R,A,3,L + fire_C,N,O,R,A,3,N  = 0
 c1697: - 0.002 xNG_C,N,O,R,A,L,4 - 0.002 xPB_C,N,O,R,A,L,0,4
        - 0.002 xPB_C,N,O,R,A,L,1,4 - 0.002 xPB_C,N,O,R,A,L,2,4
        - 0.002 xPB_C,N,O,R,A,L,3,4 - 0.002 xPB_C,N,O,R,A,L,4,4
        - 0.002 xGS_C,N,O,R,A,L,0,4 - 0.002 xGS_C,N,O,R,A,L,1,4
        - 0.002 xGS_C,N,O,R,A,L,2,4 - 0.002 xGS_C,N,O,R,A,L,3,4
        - 0.002 xGS_C,N,O,R,A,L,4,4 - 0.002 xEAe_C,N,O,R,A,L,4
        - 0.002 xEAr_C,N,O,R,A,4,1 - 0.002 xEAr_C,N,O,R,A,4,2
        - 0.002 xEAr_C,N,O,R,A,4,3 + fire_C,N,O,R,A,4,P + fire_C,N,O,R,A,4,D
        + fire_C,N,O,R,A,4,W + fire_C,N,O,R,A,4,C + fire_C,N,O,R,A,4,I
        + fire_C,N,O,R,A,4,A + fire_C,N,O,R,A,4,L + fire_C,N,O,R,A,4,N  = 0
 c1698: - 0.002 xNG_C,N,O,R,A,L,5 - 0.002 xPB_C,N,O,R,A,L,0,5
        - 0.002 xPB_C,N,O,R,A,L,1,5 - 0.002 xPB_C,N,O,R,A,L,2,5
        - 0.002 xPB_C,N,O,R,A,L,3,5 - 0.002 xPB_C,N,O,R,A,L,4,5
        - 0.002 xGS_C,N,O,R,A,L,0,5 - 0.002 xGS_C,N,O,R,A,L,1,5
        - 0.002 xGS_C,N,O,R,A,L,2,5 - 0.002 xGS_C,N,O,R,A,L,3,5
        - 0.002 xGS_C,N,O,R,A,L,4,5 - 0.002 xEAe_C,N,O,R,A,L,5
        - 0.002 xEAr_C,N,O,R,A,5,1 - 0.002 xEAr_C,N,O,R,A,5,2
        - 0.002 xEAr_C,N,O,R,A,5,3 - 0.002 xEAr_C,N,O,R,A,5,4
        + fire_C,N,O,R,A,5,P + fire_C,N,O,R,A,5,D + fire_C,N,O,R,A,5,W
        + fire_C,N,O,R,A,5,C + fire_C,N,O,R,A,5,I + fire_C,N,O,R,A,5,A
        + fire_C,N,O,R,A,5,L + fire_C,N,O,R,A,5,N  = 0
 c1699: fire_C,N,O,R,L,1,P + fire_C,N,O,R,L,1,D + fire_C,N,O,R,L,1,W
        + fire_C,N,O,R,L,1,C + fire_C,N,O,R,L,1,I + fire_C,N,O,R,L,1,A
        + fire_C,N,O,R,L,1,L + fire_C,N,O,R,L,1,N  = 0
 c1700: - 0.002 xEAr_C,N,O,R,L,2,1 + fire_C,N,O,R,L,2,P + fire_C,N,O,R,L,2,D
        + fire_C,N,O,R,L,2,W + fire_C,N,O,R,L,2,C + fire_C,N,O,R,L,2,I
        + fire_C,N,O,R,L,2,A + fire_C,N,O,R,L,2,L + fire_C,N,O,R,L,2,N  = 0
 c1701: - 0.002 xEAr_C,N,O,R,L,3,1 - 0.002 xEAr_C,N,O,R,L,3,2
        + fire_C,N,O,R,L,3,P + fire_C,N,O,R,L,3,D + fire_C,N,O,R,L,3,W
        + fire_C,N,O,R,L,3,C + fire_C,N,O,R,L,3,I + fire_C,N,O,R,L,3,A
        + fire_C,N,O,R,L,3,L + fire_C,N,O,R,L,3,N  = 0
 c1702: - 0.002 xEAr_C,N,O,R,L,4,1 - 0.002 xEAr_C,N,O,R,L,4,2
        - 0.002 xEAr_C,N,O,R,L,4,3 + fire_C,N,O,R,L,4,P + fire_C,N,O,R,L,4,D
        + fire_C,N,O,R,L,4,W + fire_C,N,O,R,L,4,C + fire_C,N,O,R,L,4,I
        + fire_C,N,O,R,L,4,A + fire_C,N,O,R,L,4,L + fire_C,N,O,R,L,4,N  = 0
 c1703: - 0.002 xEAr_C,N,O,R,L,5,1 - 0.002 xEAr_C,N,O,R,L,5,2
        - 0.002 xEAr_C,N,O,R,L,5,3 - 0.002 xEAr_C,N,O,R,L,5,4
        + fire_C,N,O,R,L,5,P + fire_C,N,O,R,L,5,D + fire_C,N,O,R,L,5,W
        + fire_C,N,O,R,L,5,C + fire_C,N,O,R,L,5,I + fire_C,N,O,R,L,5,A
        + fire_C,N,O,R,L,5,L + fire_C,N,O,R,L,5,N  = 0
 c1704: fire_C,N,O,R,N,1,P + fire_C,N,O,R,N,1,D + fire_C,N,O,R,N,1,W
        + fire_C,N,O,R,N,1,C + fire_C,N,O,R,N,1,I + fire_C,N,O,R,N,1,A
        + fire_C,N,O,R,N,1,L + fire_C,N,O,R,N,1,N  = 0
 c1705: fire_C,N,O,R,N,2,P + fire_C,N,O,R,N,2,D + fire_C,N,O,R,N,2,W
        + fire_C,N,O,R,N,2,C + fire_C,N,O,R,N,2,I + fire_C,N,O,R,N,2,A
        + fire_C,N,O,R,N,2,L + fire_C,N,O,R,N,2,N  = 0
 c1706: fire_C,N,O,R,N,3,P + fire_C,N,O,R,N,3,D + fire_C,N,O,R,N,3,W
        + fire_C,N,O,R,N,3,C + fire_C,N,O,R,N,3,I + fire_C,N,O,R,N,3,A
        + fire_C,N,O,R,N,3,L + fire_C,N,O,R,N,3,N  = 0
 c1707: fire_C,N,O,R,N,4,P + fire_C,N,O,R,N,4,D + fire_C,N,O,R,N,4,W
        + fire_C,N,O,R,N,4,C + fire_C,N,O,R,N,4,I + fire_C,N,O,R,N,4,A
        + fire_C,N,O,R,N,4,L + fire_C,N,O,R,N,4,N  = 0
 c1708: fire_C,N,O,R,N,5,P + fire_C,N,O,R,N,5,D + fire_C,N,O,R,N,5,W
        + fire_C,N,O,R,N,5,C + fire_C,N,O,R,N,5,I + fire_C,N,O,R,N,5,A
        + fire_C,N,O,R,N,5,L + fire_C,N,O,R,N,5,N  = 0
 c1709: 0.9 fire_B,R,N,L,P,1,P - 0.1 fire_B,R,N,L,P,1,D
        - 0.1 fire_B,R,N,L,P,1,W - 0.1 fire_B,R,N,L,P,1,C
        - 0.1 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        - 0.1 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1710: - 0.1 fire_B,R,N,L,P,1,P + 0.9 fire_B,R,N,L,P,1,D
        - 0.1 fire_B,R,N,L,P,1,W - 0.1 fire_B,R,N,L,P,1,C
        - 0.1 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        - 0.1 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1711: - 0.1 fire_B,R,N,L,P,1,P - 0.1 fire_B,R,N,L,P,1,D
        + 0.9 fire_B,R,N,L,P,1,W - 0.1 fire_B,R,N,L,P,1,C
        - 0.1 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        - 0.1 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1712: - 0.1 fire_B,R,N,L,P,1,P - 0.1 fire_B,R,N,L,P,1,D
        - 0.1 fire_B,R,N,L,P,1,W + 0.9 fire_B,R,N,L,P,1,C
        - 0.1 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        - 0.1 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1713: - 0.1 fire_B,R,N,L,P,1,P - 0.1 fire_B,R,N,L,P,1,D
        - 0.1 fire_B,R,N,L,P,1,W - 0.1 fire_B,R,N,L,P,1,C
        + 0.9 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        - 0.1 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1714: - 0.2 fire_B,R,N,L,P,1,P - 0.2 fire_B,R,N,L,P,1,D
        - 0.2 fire_B,R,N,L,P,1,W - 0.2 fire_B,R,N,L,P,1,C
        - 0.2 fire_B,R,N,L,P,1,I + 0.8 fire_B,R,N,L,P,1,A
        - 0.2 fire_B,R,N,L,P,1,L - 0.2 fire_B,R,N,L,P,1,N  = 0
 c1715: - 0.1 fire_B,R,N,L,P,1,P - 0.1 fire_B,R,N,L,P,1,D
        - 0.1 fire_B,R,N,L,P,1,W - 0.1 fire_B,R,N,L,P,1,C
        - 0.1 fire_B,R,N,L,P,1,I - 0.1 fire_B,R,N,L,P,1,A
        + 0.9 fire_B,R,N,L,P,1,L - 0.1 fire_B,R,N,L,P,1,N  = 0
 c1716: - 0.2 fire_B,R,N,L,P,1,P - 0.2 fire_B,R,N,L,P,1,D
        - 0.2 fire_B,R,N,L,P,1,W - 0.2 fire_B,R,N,L,P,1,C
        - 0.2 fire_B,R,N,L,P,1,I - 0.2 fire_B,R,N,L,P,1,A
        - 0.2 fire_B,R,N,L,P,1,L + 0.8 fire_B,R,N,L,P,1,N  = 0
 c1717: 0.9 fire_B,R,N,L,P,2,P - 0.1 fire_B,R,N,L,P,2,D
        - 0.1 fire_B,R,N,L,P,2,W - 0.1 fire_B,R,N,L,P,2,C
        - 0.1 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        - 0.1 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1718: - 0.1 fire_B,R,N,L,P,2,P + 0.9 fire_B,R,N,L,P,2,D
        - 0.1 fire_B,R,N,L,P,2,W - 0.1 fire_B,R,N,L,P,2,C
        - 0.1 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        - 0.1 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1719: - 0.1 fire_B,R,N,L,P,2,P - 0.1 fire_B,R,N,L,P,2,D
        + 0.9 fire_B,R,N,L,P,2,W - 0.1 fire_B,R,N,L,P,2,C
        - 0.1 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        - 0.1 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1720: - 0.1 fire_B,R,N,L,P,2,P - 0.1 fire_B,R,N,L,P,2,D
        - 0.1 fire_B,R,N,L,P,2,W + 0.9 fire_B,R,N,L,P,2,C
        - 0.1 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        - 0.1 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1721: - 0.1 fire_B,R,N,L,P,2,P - 0.1 fire_B,R,N,L,P,2,D
        - 0.1 fire_B,R,N,L,P,2,W - 0.1 fire_B,R,N,L,P,2,C
        + 0.9 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        - 0.1 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1722: - 0.2 fire_B,R,N,L,P,2,P - 0.2 fire_B,R,N,L,P,2,D
        - 0.2 fire_B,R,N,L,P,2,W - 0.2 fire_B,R,N,L,P,2,C
        - 0.2 fire_B,R,N,L,P,2,I + 0.8 fire_B,R,N,L,P,2,A
        - 0.2 fire_B,R,N,L,P,2,L - 0.2 fire_B,R,N,L,P,2,N  = 0
 c1723: - 0.1 fire_B,R,N,L,P,2,P - 0.1 fire_B,R,N,L,P,2,D
        - 0.1 fire_B,R,N,L,P,2,W - 0.1 fire_B,R,N,L,P,2,C
        - 0.1 fire_B,R,N,L,P,2,I - 0.1 fire_B,R,N,L,P,2,A
        + 0.9 fire_B,R,N,L,P,2,L - 0.1 fire_B,R,N,L,P,2,N  = 0
 c1724: - 0.2 fire_B,R,N,L,P,2,P - 0.2 fire_B,R,N,L,P,2,D
        - 0.2 fire_B,R,N,L,P,2,W - 0.2 fire_B,R,N,L,P,2,C
        - 0.2 fire_B,R,N,L,P,2,I - 0.2 fire_B,R,N,L,P,2,A
        - 0.2 fire_B,R,N,L,P,2,L + 0.8 fire_B,R,N,L,P,2,N  = 0
 c1725: 0.9 fire_B,R,N,L,P,3,P - 0.1 fire_B,R,N,L,P,3,D
        - 0.1 fire_B,R,N,L,P,3,W - 0.1 fire_B,R,N,L,P,3,C
        - 0.1 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        - 0.1 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1726: - 0.1 fire_B,R,N,L,P,3,P + 0.9 fire_B,R,N,L,P,3,D
        - 0.1 fire_B,R,N,L,P,3,W - 0.1 fire_B,R,N,L,P,3,C
        - 0.1 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        - 0.1 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1727: - 0.1 fire_B,R,N,L,P,3,P - 0.1 fire_B,R,N,L,P,3,D
        + 0.9 fire_B,R,N,L,P,3,W - 0.1 fire_B,R,N,L,P,3,C
        - 0.1 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        - 0.1 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1728: - 0.1 fire_B,R,N,L,P,3,P - 0.1 fire_B,R,N,L,P,3,D
        - 0.1 fire_B,R,N,L,P,3,W + 0.9 fire_B,R,N,L,P,3,C
        - 0.1 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        - 0.1 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1729: - 0.1 fire_B,R,N,L,P,3,P - 0.1 fire_B,R,N,L,P,3,D
        - 0.1 fire_B,R,N,L,P,3,W - 0.1 fire_B,R,N,L,P,3,C
        + 0.9 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        - 0.1 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1730: - 0.2 fire_B,R,N,L,P,3,P - 0.2 fire_B,R,N,L,P,3,D
        - 0.2 fire_B,R,N,L,P,3,W - 0.2 fire_B,R,N,L,P,3,C
        - 0.2 fire_B,R,N,L,P,3,I + 0.8 fire_B,R,N,L,P,3,A
        - 0.2 fire_B,R,N,L,P,3,L - 0.2 fire_B,R,N,L,P,3,N  = 0
 c1731: - 0.1 fire_B,R,N,L,P,3,P - 0.1 fire_B,R,N,L,P,3,D
        - 0.1 fire_B,R,N,L,P,3,W - 0.1 fire_B,R,N,L,P,3,C
        - 0.1 fire_B,R,N,L,P,3,I - 0.1 fire_B,R,N,L,P,3,A
        + 0.9 fire_B,R,N,L,P,3,L - 0.1 fire_B,R,N,L,P,3,N  = 0
 c1732: - 0.2 fire_B,R,N,L,P,3,P - 0.2 fire_B,R,N,L,P,3,D
        - 0.2 fire_B,R,N,L,P,3,W - 0.2 fire_B,R,N,L,P,3,C
        - 0.2 fire_B,R,N,L,P,3,I - 0.2 fire_B,R,N,L,P,3,A
        - 0.2 fire_B,R,N,L,P,3,L + 0.8 fire_B,R,N,L,P,3,N  = 0
 c1733: 0.9 fire_B,R,N,L,P,4,P - 0.1 fire_B,R,N,L,P,4,D
        - 0.1 fire_B,R,N,L,P,4,W - 0.1 fire_B,R,N,L,P,4,C
        - 0.1 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        - 0.1 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1734: - 0.1 fire_B,R,N,L,P,4,P + 0.9 fire_B,R,N,L,P,4,D
        - 0.1 fire_B,R,N,L,P,4,W - 0.1 fire_B,R,N,L,P,4,C
        - 0.1 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        - 0.1 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1735: - 0.1 fire_B,R,N,L,P,4,P - 0.1 fire_B,R,N,L,P,4,D
        + 0.9 fire_B,R,N,L,P,4,W - 0.1 fire_B,R,N,L,P,4,C
        - 0.1 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        - 0.1 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1736: - 0.1 fire_B,R,N,L,P,4,P - 0.1 fire_B,R,N,L,P,4,D
        - 0.1 fire_B,R,N,L,P,4,W + 0.9 fire_B,R,N,L,P,4,C
        - 0.1 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        - 0.1 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1737: - 0.1 fire_B,R,N,L,P,4,P - 0.1 fire_B,R,N,L,P,4,D
        - 0.1 fire_B,R,N,L,P,4,W - 0.1 fire_B,R,N,L,P,4,C
        + 0.9 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        - 0.1 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1738: - 0.2 fire_B,R,N,L,P,4,P - 0.2 fire_B,R,N,L,P,4,D
        - 0.2 fire_B,R,N,L,P,4,W - 0.2 fire_B,R,N,L,P,4,C
        - 0.2 fire_B,R,N,L,P,4,I + 0.8 fire_B,R,N,L,P,4,A
        - 0.2 fire_B,R,N,L,P,4,L - 0.2 fire_B,R,N,L,P,4,N  = 0
 c1739: - 0.1 fire_B,R,N,L,P,4,P - 0.1 fire_B,R,N,L,P,4,D
        - 0.1 fire_B,R,N,L,P,4,W - 0.1 fire_B,R,N,L,P,4,C
        - 0.1 fire_B,R,N,L,P,4,I - 0.1 fire_B,R,N,L,P,4,A
        + 0.9 fire_B,R,N,L,P,4,L - 0.1 fire_B,R,N,L,P,4,N  = 0
 c1740: - 0.2 fire_B,R,N,L,P,4,P - 0.2 fire_B,R,N,L,P,4,D
        - 0.2 fire_B,R,N,L,P,4,W - 0.2 fire_B,R,N,L,P,4,C
        - 0.2 fire_B,R,N,L,P,4,I - 0.2 fire_B,R,N,L,P,4,A
        - 0.2 fire_B,R,N,L,P,4,L + 0.8 fire_B,R,N,L,P,4,N  = 0
 c1741: 0.9 fire_B,R,N,L,P,5,P - 0.1 fire_B,R,N,L,P,5,D
        - 0.1 fire_B,R,N,L,P,5,W - 0.1 fire_B,R,N,L,P,5,C
        - 0.1 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        - 0.1 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1742: - 0.1 fire_B,R,N,L,P,5,P + 0.9 fire_B,R,N,L,P,5,D
        - 0.1 fire_B,R,N,L,P,5,W - 0.1 fire_B,R,N,L,P,5,C
        - 0.1 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        - 0.1 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1743: - 0.1 fire_B,R,N,L,P,5,P - 0.1 fire_B,R,N,L,P,5,D
        + 0.9 fire_B,R,N,L,P,5,W - 0.1 fire_B,R,N,L,P,5,C
        - 0.1 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        - 0.1 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1744: - 0.1 fire_B,R,N,L,P,5,P - 0.1 fire_B,R,N,L,P,5,D
        - 0.1 fire_B,R,N,L,P,5,W + 0.9 fire_B,R,N,L,P,5,C
        - 0.1 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        - 0.1 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1745: - 0.1 fire_B,R,N,L,P,5,P - 0.1 fire_B,R,N,L,P,5,D
        - 0.1 fire_B,R,N,L,P,5,W - 0.1 fire_B,R,N,L,P,5,C
        + 0.9 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        - 0.1 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1746: - 0.2 fire_B,R,N,L,P,5,P - 0.2 fire_B,R,N,L,P,5,D
        - 0.2 fire_B,R,N,L,P,5,W - 0.2 fire_B,R,N,L,P,5,C
        - 0.2 fire_B,R,N,L,P,5,I + 0.8 fire_B,R,N,L,P,5,A
        - 0.2 fire_B,R,N,L,P,5,L - 0.2 fire_B,R,N,L,P,5,N  = 0
 c1747: - 0.1 fire_B,R,N,L,P,5,P - 0.1 fire_B,R,N,L,P,5,D
        - 0.1 fire_B,R,N,L,P,5,W - 0.1 fire_B,R,N,L,P,5,C
        - 0.1 fire_B,R,N,L,P,5,I - 0.1 fire_B,R,N,L,P,5,A
        + 0.9 fire_B,R,N,L,P,5,L - 0.1 fire_B,R,N,L,P,5,N  = 0
 c1748: - 0.2 fire_B,R,N,L,P,5,P - 0.2 fire_B,R,N,L,P,5,D
        - 0.2 fire_B,R,N,L,P,5,W - 0.2 fire_B,R,N,L,P,5,C
        - 0.2 fire_B,R,N,L,P,5,I - 0.2 fire_B,R,N,L,P,5,A
        - 0.2 fire_B,R,N,L,P,5,L + 0.8 fire_B,R,N,L,P,5,N  = 0
 c1749: 0.5 fire_B,R,N,L,D,1,P - 0.5 fire_B,R,N,L,D,1,D
        - 0.5 fire_B,R,N,L,D,1,W - 0.5 fire_B,R,N,L,D,1,C
        - 0.5 fire_B,R,N,L,D,1,I - 0.5 fire_B,R,N,L,D,1,A
        - 0.5 fire_B,R,N,L,D,1,L - 0.5 fire_B,R,N,L,D,1,N  = 0
 c1750: - 0.5 fire_B,R,N,L,D,1,P + 0.5 fire_B,R,N,L,D,1,D
        - 0.5 fire_B,R,N,L,D,1,W - 0.5 fire_B,R,N,L,D,1,C
        - 0.5 fire_B,R,N,L,D,1,I - 0.5 fire_B,R,N,L,D,1,A
        - 0.5 fire_B,R,N,L,D,1,L - 0.5 fire_B,R,N,L,D,1,N  = 0
 c1751: fire_B,R,N,L,D,1,W  = 0
 c1752: fire_B,R,N,L,D,1,C  = 0
 c1753: fire_B,R,N,L,D,1,I  = 0
 c1754: fire_B,R,N,L,D,1,A  = 0
 c1755: fire_B,R,N,L,D,1,L  = 0
 c1756: fire_B,R,N,L,D,1,N  = 0
 c1757: 0.5 fire_B,R,N,L,D,2,P - 0.5 fire_B,R,N,L,D,2,D
        - 0.5 fire_B,R,N,L,D,2,W - 0.5 fire_B,R,N,L,D,2,C
        - 0.5 fire_B,R,N,L,D,2,I - 0.5 fire_B,R,N,L,D,2,A
        - 0.5 fire_B,R,N,L,D,2,L - 0.5 fire_B,R,N,L,D,2,N  = 0
 c1758: - 0.5 fire_B,R,N,L,D,2,P + 0.5 fire_B,R,N,L,D,2,D
        - 0.5 fire_B,R,N,L,D,2,W - 0.5 fire_B,R,N,L,D,2,C
        - 0.5 fire_B,R,N,L,D,2,I - 0.5 fire_B,R,N,L,D,2,A
        - 0.5 fire_B,R,N,L,D,2,L - 0.5 fire_B,R,N,L,D,2,N  = 0
 c1759: fire_B,R,N,L,D,2,W  = 0
 c1760: fire_B,R,N,L,D,2,C  = 0
 c1761: fire_B,R,N,L,D,2,I  = 0
 c1762: fire_B,R,N,L,D,2,A  = 0
 c1763: fire_B,R,N,L,D,2,L  = 0
 c1764: fire_B,R,N,L,D,2,N  = 0
 c1765: 0.5 fire_B,R,N,L,D,3,P - 0.5 fire_B,R,N,L,D,3,D
        - 0.5 fire_B,R,N,L,D,3,W - 0.5 fire_B,R,N,L,D,3,C
        - 0.5 fire_B,R,N,L,D,3,I - 0.5 fire_B,R,N,L,D,3,A
        - 0.5 fire_B,R,N,L,D,3,L - 0.5 fire_B,R,N,L,D,3,N  = 0
 c1766: - 0.5 fire_B,R,N,L,D,3,P + 0.5 fire_B,R,N,L,D,3,D
        - 0.5 fire_B,R,N,L,D,3,W - 0.5 fire_B,R,N,L,D,3,C
        - 0.5 fire_B,R,N,L,D,3,I - 0.5 fire_B,R,N,L,D,3,A
        - 0.5 fire_B,R,N,L,D,3,L - 0.5 fire_B,R,N,L,D,3,N  = 0
 c1767: fire_B,R,N,L,D,3,W  = 0
 c1768: fire_B,R,N,L,D,3,C  = 0
 c1769: fire_B,R,N,L,D,3,I  = 0
 c1770: fire_B,R,N,L,D,3,A  = 0
 c1771: fire_B,R,N,L,D,3,L  = 0
 c1772: fire_B,R,N,L,D,3,N  = 0
 c1773: 0.5 fire_B,R,N,L,D,4,P - 0.5 fire_B,R,N,L,D,4,D
        - 0.5 fire_B,R,N,L,D,4,W - 0.5 fire_B,R,N,L,D,4,C
        - 0.5 fire_B,R,N,L,D,4,I - 0.5 fire_B,R,N,L,D,4,A
        - 0.5 fire_B,R,N,L,D,4,L - 0.5 fire_B,R,N,L,D,4,N  = 0
 c1774: - 0.5 fire_B,R,N,L,D,4,P + 0.5 fire_B,R,N,L,D,4,D
        - 0.5 fire_B,R,N,L,D,4,W - 0.5 fire_B,R,N,L,D,4,C
        - 0.5 fire_B,R,N,L,D,4,I - 0.5 fire_B,R,N,L,D,4,A
        - 0.5 fire_B,R,N,L,D,4,L - 0.5 fire_B,R,N,L,D,4,N  = 0
 c1775: fire_B,R,N,L,D,4,W  = 0
 c1776: fire_B,R,N,L,D,4,C  = 0
 c1777: fire_B,R,N,L,D,4,I  = 0
 c1778: fire_B,R,N,L,D,4,A  = 0
 c1779: fire_B,R,N,L,D,4,L  = 0
 c1780: fire_B,R,N,L,D,4,N  = 0
 c1781: 0.5 fire_B,R,N,L,D,5,P - 0.5 fire_B,R,N,L,D,5,D
        - 0.5 fire_B,R,N,L,D,5,W - 0.5 fire_B,R,N,L,D,5,C
        - 0.5 fire_B,R,N,L,D,5,I - 0.5 fire_B,R,N,L,D,5,A
        - 0.5 fire_B,R,N,L,D,5,L - 0.5 fire_B,R,N,L,D,5,N  = 0
 c1782: - 0.5 fire_B,R,N,L,D,5,P + 0.5 fire_B,R,N,L,D,5,D
        - 0.5 fire_B,R,N,L,D,5,W - 0.5 fire_B,R,N,L,D,5,C
        - 0.5 fire_B,R,N,L,D,5,I - 0.5 fire_B,R,N,L,D,5,A
        - 0.5 fire_B,R,N,L,D,5,L - 0.5 fire_B,R,N,L,D,5,N  = 0
 c1783: fire_B,R,N,L,D,5,W  = 0
 c1784: fire_B,R,N,L,D,5,C  = 0
 c1785: fire_B,R,N,L,D,5,I  = 0
 c1786: fire_B,R,N,L,D,5,A  = 0
 c1787: fire_B,R,N,L,D,5,L  = 0
 c1788: fire_B,R,N,L,D,5,N  = 0
 c1789: fire_B,R,N,L,W,1,P  = 0
 c1790: fire_B,R,N,L,W,1,D  = 0
 c1791: - fire_B,R,N,L,W,1,P - fire_B,R,N,L,W,1,D - fire_B,R,N,L,W,1,C
        - fire_B,R,N,L,W,1,I - fire_B,R,N,L,W,1,A - fire_B,R,N,L,W,1,L
        - fire_B,R,N,L,W,1,N  = 0
 c1792: fire_B,R,N,L,W,1,C  = 0
 c1793: fire_B,R,N,L,W,1,I  = 0
 c1794: fire_B,R,N,L,W,1,A  = 0
 c1795: fire_B,R,N,L,W,1,L  = 0
 c1796: fire_B,R,N,L,W,1,N  = 0
 c1797: fire_B,R,N,L,W,2,P  = 0
 c1798: fire_B,R,N,L,W,2,D  = 0
 c1799: - fire_B,R,N,L,W,2,P - fire_B,R,N,L,W,2,D - fire_B,R,N,L,W,2,C
        - fire_B,R,N,L,W,2,I - fire_B,R,N,L,W,2,A - fire_B,R,N,L,W,2,L
        - fire_B,R,N,L,W,2,N  = 0
 c1800: fire_B,R,N,L,W,2,C  = 0
 c1801: fire_B,R,N,L,W,2,I  = 0
 c1802: fire_B,R,N,L,W,2,A  = 0
 c1803: fire_B,R,N,L,W,2,L  = 0
 c1804: fire_B,R,N,L,W,2,N  = 0
 c1805: fire_B,R,N,L,W,3,P  = 0
 c1806: fire_B,R,N,L,W,3,D  = 0
 c1807: - fire_B,R,N,L,W,3,P - fire_B,R,N,L,W,3,D - fire_B,R,N,L,W,3,C
        - fire_B,R,N,L,W,3,I - fire_B,R,N,L,W,3,A - fire_B,R,N,L,W,3,L
        - fire_B,R,N,L,W,3,N  = 0
 c1808: fire_B,R,N,L,W,3,C  = 0
 c1809: fire_B,R,N,L,W,3,I  = 0
 c1810: fire_B,R,N,L,W,3,A  = 0
 c1811: fire_B,R,N,L,W,3,L  = 0
 c1812: fire_B,R,N,L,W,3,N  = 0
 c1813: fire_B,R,N,L,W,4,P  = 0
 c1814: fire_B,R,N,L,W,4,D  = 0
 c1815: - fire_B,R,N,L,W,4,P - fire_B,R,N,L,W,4,D - fire_B,R,N,L,W,4,C
        - fire_B,R,N,L,W,4,I - fire_B,R,N,L,W,4,A - fire_B,R,N,L,W,4,L
        - fire_B,R,N,L,W,4,N  = 0
 c1816: fire_B,R,N,L,W,4,C  = 0
 c1817: fire_B,R,N,L,W,4,I  = 0
 c1818: fire_B,R,N,L,W,4,A  = 0
 c1819: fire_B,R,N,L,W,4,L  = 0
 c1820: fire_B,R,N,L,W,4,N  = 0
 c1821: fire_B,R,N,L,W,5,P  = 0
 c1822: fire_B,R,N,L,W,5,D  = 0
 c1823: - fire_B,R,N,L,W,5,P - fire_B,R,N,L,W,5,D - fire_B,R,N,L,W,5,C
        - fire_B,R,N,L,W,5,I - fire_B,R,N,L,W,5,A - fire_B,R,N,L,W,5,L
        - fire_B,R,N,L,W,5,N  = 0
 c1824: fire_B,R,N,L,W,5,C  = 0
 c1825: fire_B,R,N,L,W,5,I  = 0
 c1826: fire_B,R,N,L,W,5,A  = 0
 c1827: fire_B,R,N,L,W,5,L  = 0
 c1828: fire_B,R,N,L,W,5,N  = 0
 c1829: fire_B,R,N,L,C,1,P  = 0
 c1830: fire_B,R,N,L,C,1,D  = 0
 c1831: fire_B,R,N,L,C,1,W  = 0
 c1832: - fire_B,R,N,L,C,1,P - fire_B,R,N,L,C,1,D - fire_B,R,N,L,C,1,W
        - fire_B,R,N,L,C,1,I - fire_B,R,N,L,C,1,A - fire_B,R,N,L,C,1,L
        - fire_B,R,N,L,C,1,N  = 0
 c1833: fire_B,R,N,L,C,1,I  = 0
 c1834: fire_B,R,N,L,C,1,A  = 0
 c1835: fire_B,R,N,L,C,1,L  = 0
 c1836: fire_B,R,N,L,C,1,N  = 0
 c1837: fire_B,R,N,L,C,2,P  = 0
 c1838: fire_B,R,N,L,C,2,D  = 0
 c1839: fire_B,R,N,L,C,2,W  = 0
 c1840: - fire_B,R,N,L,C,2,P - fire_B,R,N,L,C,2,D - fire_B,R,N,L,C,2,W
        - fire_B,R,N,L,C,2,I - fire_B,R,N,L,C,2,A - fire_B,R,N,L,C,2,L
        - fire_B,R,N,L,C,2,N  = 0
 c1841: fire_B,R,N,L,C,2,I  = 0
 c1842: fire_B,R,N,L,C,2,A  = 0
 c1843: fire_B,R,N,L,C,2,L  = 0
 c1844: fire_B,R,N,L,C,2,N  = 0
 c1845: fire_B,R,N,L,C,3,P  = 0
 c1846: fire_B,R,N,L,C,3,D  = 0
 c1847: fire_B,R,N,L,C,3,W  = 0
 c1848: - fire_B,R,N,L,C,3,P - fire_B,R,N,L,C,3,D - fire_B,R,N,L,C,3,W
        - fire_B,R,N,L,C,3,I - fire_B,R,N,L,C,3,A - fire_B,R,N,L,C,3,L
        - fire_B,R,N,L,C,3,N  = 0
 c1849: fire_B,R,N,L,C,3,I  = 0
 c1850: fire_B,R,N,L,C,3,A  = 0
 c1851: fire_B,R,N,L,C,3,L  = 0
 c1852: fire_B,R,N,L,C,3,N  = 0
 c1853: fire_B,R,N,L,C,4,P  = 0
 c1854: fire_B,R,N,L,C,4,D  = 0
 c1855: fire_B,R,N,L,C,4,W  = 0
 c1856: - fire_B,R,N,L,C,4,P - fire_B,R,N,L,C,4,D - fire_B,R,N,L,C,4,W
        - fire_B,R,N,L,C,4,I - fire_B,R,N,L,C,4,A - fire_B,R,N,L,C,4,L
        - fire_B,R,N,L,C,4,N  = 0
 c1857: fire_B,R,N,L,C,4,I  = 0
 c1858: fire_B,R,N,L,C,4,A  = 0
 c1859: fire_B,R,N,L,C,4,L  = 0
 c1860: fire_B,R,N,L,C,4,N  = 0
 c1861: fire_B,R,N,L,C,5,P  = 0
 c1862: fire_B,R,N,L,C,5,D  = 0
 c1863: fire_B,R,N,L,C,5,W  = 0
 c1864: - fire_B,R,N,L,C,5,P - fire_B,R,N,L,C,5,D - fire_B,R,N,L,C,5,W
        - fire_B,R,N,L,C,5,I - fire_B,R,N,L,C,5,A - fire_B,R,N,L,C,5,L
        - fire_B,R,N,L,C,5,N  = 0
 c1865: fire_B,R,N,L,C,5,I  = 0
 c1866: fire_B,R,N,L,C,5,A  = 0
 c1867: fire_B,R,N,L,C,5,L  = 0
 c1868: fire_B,R,N,L,C,5,N  = 0
 c1869: fire_B,R,N,L,I,1,P  = 0
 c1870: fire_B,R,N,L,I,1,D  = 0
 c1871: fire_B,R,N,L,I,1,W  = 0
 c1872: fire_B,R,N,L,I,1,C  = 0
 c1873: - fire_B,R,N,L,I,1,P - fire_B,R,N,L,I,1,D - fire_B,R,N,L,I,1,W
        - fire_B,R,N,L,I,1,C - fire_B,R,N,L,I,1,A - fire_B,R,N,L,I,1,L
        - fire_B,R,N,L,I,1,N  = 0
 c1874: fire_B,R,N,L,I,1,A  = 0
 c1875: fire_B,R,N,L,I,1,L  = 0
 c1876: fire_B,R,N,L,I,1,N  = 0
 c1877: fire_B,R,N,L,I,2,P  = 0
 c1878: fire_B,R,N,L,I,2,D  = 0
 c1879: fire_B,R,N,L,I,2,W  = 0
 c1880: fire_B,R,N,L,I,2,C  = 0
 c1881: - fire_B,R,N,L,I,2,P - fire_B,R,N,L,I,2,D - fire_B,R,N,L,I,2,W
        - fire_B,R,N,L,I,2,C - fire_B,R,N,L,I,2,A - fire_B,R,N,L,I,2,L
        - fire_B,R,N,L,I,2,N  = 0
 c1882: fire_B,R,N,L,I,2,A  = 0
 c1883: fire_B,R,N,L,I,2,L  = 0
 c1884: fire_B,R,N,L,I,2,N  = 0
 c1885: fire_B,R,N,L,I,3,P  = 0
 c1886: fire_B,R,N,L,I,3,D  = 0
 c1887: fire_B,R,N,L,I,3,W  = 0
 c1888: fire_B,R,N,L,I,3,C  = 0
 c1889: - fire_B,R,N,L,I,3,P - fire_B,R,N,L,I,3,D - fire_B,R,N,L,I,3,W
        - fire_B,R,N,L,I,3,C - fire_B,R,N,L,I,3,A - fire_B,R,N,L,I,3,L
        - fire_B,R,N,L,I,3,N  = 0
 c1890: fire_B,R,N,L,I,3,A  = 0
 c1891: fire_B,R,N,L,I,3,L  = 0
 c1892: fire_B,R,N,L,I,3,N  = 0
 c1893: fire_B,R,N,L,I,4,P  = 0
 c1894: fire_B,R,N,L,I,4,D  = 0
 c1895: fire_B,R,N,L,I,4,W  = 0
 c1896: fire_B,R,N,L,I,4,C  = 0
 c1897: - fire_B,R,N,L,I,4,P - fire_B,R,N,L,I,4,D - fire_B,R,N,L,I,4,W
        - fire_B,R,N,L,I,4,C - fire_B,R,N,L,I,4,A - fire_B,R,N,L,I,4,L
        - fire_B,R,N,L,I,4,N  = 0
 c1898: fire_B,R,N,L,I,4,A  = 0
 c1899: fire_B,R,N,L,I,4,L  = 0
 c1900: fire_B,R,N,L,I,4,N  = 0
 c1901: fire_B,R,N,L,I,5,P  = 0
 c1902: fire_B,R,N,L,I,5,D  = 0
 c1903: fire_B,R,N,L,I,5,W  = 0
 c1904: fire_B,R,N,L,I,5,C  = 0
 c1905: - fire_B,R,N,L,I,5,P - fire_B,R,N,L,I,5,D - fire_B,R,N,L,I,5,W
        - fire_B,R,N,L,I,5,C - fire_B,R,N,L,I,5,A - fire_B,R,N,L,I,5,L
        - fire_B,R,N,L,I,5,N  = 0
 c1906: fire_B,R,N,L,I,5,A  = 0
 c1907: fire_B,R,N,L,I,5,L  = 0
 c1908: fire_B,R,N,L,I,5,N  = 0
 c1909: fire_B,R,N,L,A,1,P  = 0
 c1910: fire_B,R,N,L,A,1,D  = 0
 c1911: fire_B,R,N,L,A,1,W  = 0
 c1912: fire_B,R,N,L,A,1,C  = 0
 c1913: fire_B,R,N,L,A,1,I  = 0
 c1914: - fire_B,R,N,L,A,1,P - fire_B,R,N,L,A,1,D - fire_B,R,N,L,A,1,W
        - fire_B,R,N,L,A,1,C - fire_B,R,N,L,A,1,I - fire_B,R,N,L,A,1,L
        - fire_B,R,N,L,A,1,N  = 0
 c1915: fire_B,R,N,L,A,1,L  = 0
 c1916: fire_B,R,N,L,A,1,N  = 0
 c1917: fire_B,R,N,L,A,2,P  = 0
 c1918: fire_B,R,N,L,A,2,D  = 0
 c1919: fire_B,R,N,L,A,2,W  = 0
 c1920: fire_B,R,N,L,A,2,C  = 0
 c1921: fire_B,R,N,L,A,2,I  = 0
 c1922: - fire_B,R,N,L,A,2,P - fire_B,R,N,L,A,2,D - fire_B,R,N,L,A,2,W
        - fire_B,R,N,L,A,2,C - fire_B,R,N,L,A,2,I - fire_B,R,N,L,A,2,L
        - fire_B,R,N,L,A,2,N  = 0
 c1923: fire_B,R,N,L,A,2,L  = 0
 c1924: fire_B,R,N,L,A,2,N  = 0
 c1925: fire_B,R,N,L,A,3,P  = 0
 c1926: fire_B,R,N,L,A,3,D  = 0
 c1927: fire_B,R,N,L,A,3,W  = 0
 c1928: fire_B,R,N,L,A,3,C  = 0
 c1929: fire_B,R,N,L,A,3,I  = 0
 c1930: - fire_B,R,N,L,A,3,P - fire_B,R,N,L,A,3,D - fire_B,R,N,L,A,3,W
        - fire_B,R,N,L,A,3,C - fire_B,R,N,L,A,3,I - fire_B,R,N,L,A,3,L
        - fire_B,R,N,L,A,3,N  = 0
 c1931: fire_B,R,N,L,A,3,L  = 0
 c1932: fire_B,R,N,L,A,3,N  = 0
 c1933: fire_B,R,N,L,A,4,P  = 0
 c1934: fire_B,R,N,L,A,4,D  = 0
 c1935: fire_B,R,N,L,A,4,W  = 0
 c1936: fire_B,R,N,L,A,4,C  = 0
 c1937: fire_B,R,N,L,A,4,I  = 0
 c1938: - fire_B,R,N,L,A,4,P - fire_B,R,N,L,A,4,D - fire_B,R,N,L,A,4,W
        - fire_B,R,N,L,A,4,C - fire_B,R,N,L,A,4,I - fire_B,R,N,L,A,4,L
        - fire_B,R,N,L,A,4,N  = 0
 c1939: fire_B,R,N,L,A,4,L  = 0
 c1940: fire_B,R,N,L,A,4,N  = 0
 c1941: fire_B,R,N,L,A,5,P  = 0
 c1942: fire_B,R,N,L,A,5,D  = 0
 c1943: fire_B,R,N,L,A,5,W  = 0
 c1944: fire_B,R,N,L,A,5,C  = 0
 c1945: fire_B,R,N,L,A,5,I  = 0
 c1946: - fire_B,R,N,L,A,5,P - fire_B,R,N,L,A,5,D - fire_B,R,N,L,A,5,W
        - fire_B,R,N,L,A,5,C - fire_B,R,N,L,A,5,I - fire_B,R,N,L,A,5,L
        - fire_B,R,N,L,A,5,N  = 0
 c1947: fire_B,R,N,L,A,5,L  = 0
 c1948: fire_B,R,N,L,A,5,N  = 0
 c1949: fire_B,R,N,L,L,1,P  = 0
 c1950: fire_B,R,N,L,L,1,D  = 0
 c1951: fire_B,R,N,L,L,1,W  = 0
 c1952: fire_B,R,N,L,L,1,C  = 0
 c1953: fire_B,R,N,L,L,1,I  = 0
 c1954: fire_B,R,N,L,L,1,A  = 0
 c1955: - fire_B,R,N,L,L,1,P - fire_B,R,N,L,L,1,D - fire_B,R,N,L,L,1,W
        - fire_B,R,N,L,L,1,C - fire_B,R,N,L,L,1,I - fire_B,R,N,L,L,1,A
        - fire_B,R,N,L,L,1,N  = 0
 c1956: fire_B,R,N,L,L,1,N  = 0
 c1957: fire_B,R,N,L,L,2,P  = 0
 c1958: fire_B,R,N,L,L,2,D  = 0
 c1959: fire_B,R,N,L,L,2,W  = 0
 c1960: fire_B,R,N,L,L,2,C  = 0
 c1961: fire_B,R,N,L,L,2,I  = 0
 c1962: fire_B,R,N,L,L,2,A  = 0
 c1963: - fire_B,R,N,L,L,2,P - fire_B,R,N,L,L,2,D - fire_B,R,N,L,L,2,W
        - fire_B,R,N,L,L,2,C - fire_B,R,N,L,L,2,I - fire_B,R,N,L,L,2,A
        - fire_B,R,N,L,L,2,N  = 0
 c1964: fire_B,R,N,L,L,2,N  = 0
 c1965: fire_B,R,N,L,L,3,P  = 0
 c1966: fire_B,R,N,L,L,3,D  = 0
 c1967: fire_B,R,N,L,L,3,W  = 0
 c1968: fire_B,R,N,L,L,3,C  = 0
 c1969: fire_B,R,N,L,L,3,I  = 0
 c1970: fire_B,R,N,L,L,3,A  = 0
 c1971: - fire_B,R,N,L,L,3,P - fire_B,R,N,L,L,3,D - fire_B,R,N,L,L,3,W
        - fire_B,R,N,L,L,3,C - fire_B,R,N,L,L,3,I - fire_B,R,N,L,L,3,A
        - fire_B,R,N,L,L,3,N  = 0
 c1972: fire_B,R,N,L,L,3,N  = 0
 c1973: fire_B,R,N,L,L,4,P  = 0
 c1974: fire_B,R,N,L,L,4,D  = 0
 c1975: fire_B,R,N,L,L,4,W  = 0
 c1976: fire_B,R,N,L,L,4,C  = 0
 c1977: fire_B,R,N,L,L,4,I  = 0
 c1978: fire_B,R,N,L,L,4,A  = 0
 c1979: - fire_B,R,N,L,L,4,P - fire_B,R,N,L,L,4,D - fire_B,R,N,L,L,4,W
        - fire_B,R,N,L,L,4,C - fire_B,R,N,L,L,4,I - fire_B,R,N,L,L,4,A
        - fire_B,R,N,L,L,4,N  = 0
 c1980: fire_B,R,N,L,L,4,N  = 0
 c1981: fire_B,R,N,L,L,5,P  = 0
 c1982: fire_B,R,N,L,L,5,D  = 0
 c1983: fire_B,R,N,L,L,5,W  = 0
 c1984: fire_B,R,N,L,L,5,C  = 0
 c1985: fire_B,R,N,L,L,5,I  = 0
 c1986: fire_B,R,N,L,L,5,A  = 0
 c1987: - fire_B,R,N,L,L,5,P - fire_B,R,N,L,L,5,D - fire_B,R,N,L,L,5,W
        - fire_B,R,N,L,L,5,C - fire_B,R,N,L,L,5,I - fire_B,R,N,L,L,5,A
        - fire_B,R,N,L,L,5,N  = 0
 c1988: fire_B,R,N,L,L,5,N  = 0
 c1989: fire_B,R,N,L,N,1,P  = 0
 c1990: fire_B,R,N,L,N,1,D  = 0
 c1991: fire_B,R,N,L,N,1,W  = 0
 c1992: fire_B,R,N,L,N,1,C  = 0
 c1993: fire_B,R,N,L,N,1,I  = 0
 c1994: fire_B,R,N,L,N,1,A  = 0
 c1995: fire_B,R,N,L,N,1,L  = 0
 c1996: - fire_B,R,N,L,N,1,P - fire_B,R,N,L,N,1,D - fire_B,R,N,L,N,1,W
        - fire_B,R,N,L,N,1,C - fire_B,R,N,L,N,1,I - fire_B,R,N,L,N,1,A
        - fire_B,R,N,L,N,1,L  = 0
 c1997: fire_B,R,N,L,N,2,P  = 0
 c1998: fire_B,R,N,L,N,2,D  = 0
 c1999: fire_B,R,N,L,N,2,W  = 0
 c2000: fire_B,R,N,L,N,2,C  = 0
 c2001: fire_B,R,N,L,N,2,I  = 0
 c2002: fire_B,R,N,L,N,2,A  = 0
 c2003: fire_B,R,N,L,N,2,L  = 0
 c2004: - fire_B,R,N,L,N,2,P - fire_B,R,N,L,N,2,D - fire_B,R,N,L,N,2,W
        - fire_B,R,N,L,N,2,C - fire_B,R,N,L,N,2,I - fire_B,R,N,L,N,2,A
        - fire_B,R,N,L,N,2,L  = 0
 c2005: fire_B,R,N,L,N,3,P  = 0
 c2006: fire_B,R,N,L,N,3,D  = 0
 c2007: fire_B,R,N,L,N,3,W  = 0
 c2008: fire_B,R,N,L,N,3,C  = 0
 c2009: fire_B,R,N,L,N,3,I  = 0
 c2010: fire_B,R,N,L,N,3,A  = 0
 c2011: fire_B,R,N,L,N,3,L  = 0
 c2012: - fire_B,R,N,L,N,3,P - fire_B,R,N,L,N,3,D - fire_B,R,N,L,N,3,W
        - fire_B,R,N,L,N,3,C - fire_B,R,N,L,N,3,I - fire_B,R,N,L,N,3,A
        - fire_B,R,N,L,N,3,L  = 0
 c2013: fire_B,R,N,L,N,4,P  = 0
 c2014: fire_B,R,N,L,N,4,D  = 0
 c2015: fire_B,R,N,L,N,4,W  = 0
 c2016: fire_B,R,N,L,N,4,C  = 0
 c2017: fire_B,R,N,L,N,4,I  = 0
 c2018: fire_B,R,N,L,N,4,A  = 0
 c2019: fire_B,R,N,L,N,4,L  = 0
 c2020: - fire_B,R,N,L,N,4,P - fire_B,R,N,L,N,4,D - fire_B,R,N,L,N,4,W
        - fire_B,R,N,L,N,4,C - fire_B,R,N,L,N,4,I - fire_B,R,N,L,N,4,A
        - fire_B,R,N,L,N,4,L  = 0
 c2021: fire_B,R,N,L,N,5,P  = 0
 c2022: fire_B,R,N,L,N,5,D  = 0
 c2023: fire_B,R,N,L,N,5,W  = 0
 c2024: fire_B,R,N,L,N,5,C  = 0
 c2025: fire_B,R,N,L,N,5,I  = 0
 c2026: fire_B,R,N,L,N,5,A  = 0
 c2027: fire_B,R,N,L,N,5,L  = 0
 c2028: - fire_B,R,N,L,N,5,P - fire_B,R,N,L,N,5,D - fire_B,R,N,L,N,5,W
        - fire_B,R,N,L,N,5,C - fire_B,R,N,L,N,5,I - fire_B,R,N,L,N,5,A
        - fire_B,R,N,L,N,5,L  = 0
 c2029: 0.9 fire_B,R,N,C,P,1,P - 0.1 fire_B,R,N,C,P,1,D
        - 0.1 fire_B,R,N,C,P,1,W - 0.1 fire_B,R,N,C,P,1,C
        - 0.1 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        - 0.1 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2030: - 0.1 fire_B,R,N,C,P,1,P + 0.9 fire_B,R,N,C,P,1,D
        - 0.1 fire_B,R,N,C,P,1,W - 0.1 fire_B,R,N,C,P,1,C
        - 0.1 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        - 0.1 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2031: - 0.1 fire_B,R,N,C,P,1,P - 0.1 fire_B,R,N,C,P,1,D
        + 0.9 fire_B,R,N,C,P,1,W - 0.1 fire_B,R,N,C,P,1,C
        - 0.1 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        - 0.1 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2032: - 0.1 fire_B,R,N,C,P,1,P - 0.1 fire_B,R,N,C,P,1,D
        - 0.1 fire_B,R,N,C,P,1,W + 0.9 fire_B,R,N,C,P,1,C
        - 0.1 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        - 0.1 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2033: - 0.1 fire_B,R,N,C,P,1,P - 0.1 fire_B,R,N,C,P,1,D
        - 0.1 fire_B,R,N,C,P,1,W - 0.1 fire_B,R,N,C,P,1,C
        + 0.9 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        - 0.1 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2034: - 0.2 fire_B,R,N,C,P,1,P - 0.2 fire_B,R,N,C,P,1,D
        - 0.2 fire_B,R,N,C,P,1,W - 0.2 fire_B,R,N,C,P,1,C
        - 0.2 fire_B,R,N,C,P,1,I + 0.8 fire_B,R,N,C,P,1,A
        - 0.2 fire_B,R,N,C,P,1,L - 0.2 fire_B,R,N,C,P,1,N  = 0
 c2035: - 0.1 fire_B,R,N,C,P,1,P - 0.1 fire_B,R,N,C,P,1,D
        - 0.1 fire_B,R,N,C,P,1,W - 0.1 fire_B,R,N,C,P,1,C
        - 0.1 fire_B,R,N,C,P,1,I - 0.1 fire_B,R,N,C,P,1,A
        + 0.9 fire_B,R,N,C,P,1,L - 0.1 fire_B,R,N,C,P,1,N  = 0
 c2036: - 0.2 fire_B,R,N,C,P,1,P - 0.2 fire_B,R,N,C,P,1,D
        - 0.2 fire_B,R,N,C,P,1,W - 0.2 fire_B,R,N,C,P,1,C
        - 0.2 fire_B,R,N,C,P,1,I - 0.2 fire_B,R,N,C,P,1,A
        - 0.2 fire_B,R,N,C,P,1,L + 0.8 fire_B,R,N,C,P,1,N  = 0
 c2037: 0.9 fire_B,R,N,C,P,2,P - 0.1 fire_B,R,N,C,P,2,D
        - 0.1 fire_B,R,N,C,P,2,W - 0.1 fire_B,R,N,C,P,2,C
        - 0.1 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        - 0.1 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2038: - 0.1 fire_B,R,N,C,P,2,P + 0.9 fire_B,R,N,C,P,2,D
        - 0.1 fire_B,R,N,C,P,2,W - 0.1 fire_B,R,N,C,P,2,C
        - 0.1 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        - 0.1 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2039: - 0.1 fire_B,R,N,C,P,2,P - 0.1 fire_B,R,N,C,P,2,D
        + 0.9 fire_B,R,N,C,P,2,W - 0.1 fire_B,R,N,C,P,2,C
        - 0.1 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        - 0.1 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2040: - 0.1 fire_B,R,N,C,P,2,P - 0.1 fire_B,R,N,C,P,2,D
        - 0.1 fire_B,R,N,C,P,2,W + 0.9 fire_B,R,N,C,P,2,C
        - 0.1 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        - 0.1 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2041: - 0.1 fire_B,R,N,C,P,2,P - 0.1 fire_B,R,N,C,P,2,D
        - 0.1 fire_B,R,N,C,P,2,W - 0.1 fire_B,R,N,C,P,2,C
        + 0.9 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        - 0.1 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2042: - 0.2 fire_B,R,N,C,P,2,P - 0.2 fire_B,R,N,C,P,2,D
        - 0.2 fire_B,R,N,C,P,2,W - 0.2 fire_B,R,N,C,P,2,C
        - 0.2 fire_B,R,N,C,P,2,I + 0.8 fire_B,R,N,C,P,2,A
        - 0.2 fire_B,R,N,C,P,2,L - 0.2 fire_B,R,N,C,P,2,N  = 0
 c2043: - 0.1 fire_B,R,N,C,P,2,P - 0.1 fire_B,R,N,C,P,2,D
        - 0.1 fire_B,R,N,C,P,2,W - 0.1 fire_B,R,N,C,P,2,C
        - 0.1 fire_B,R,N,C,P,2,I - 0.1 fire_B,R,N,C,P,2,A
        + 0.9 fire_B,R,N,C,P,2,L - 0.1 fire_B,R,N,C,P,2,N  = 0
 c2044: - 0.2 fire_B,R,N,C,P,2,P - 0.2 fire_B,R,N,C,P,2,D
        - 0.2 fire_B,R,N,C,P,2,W - 0.2 fire_B,R,N,C,P,2,C
        - 0.2 fire_B,R,N,C,P,2,I - 0.2 fire_B,R,N,C,P,2,A
        - 0.2 fire_B,R,N,C,P,2,L + 0.8 fire_B,R,N,C,P,2,N  = 0
 c2045: 0.9 fire_B,R,N,C,P,3,P - 0.1 fire_B,R,N,C,P,3,D
        - 0.1 fire_B,R,N,C,P,3,W - 0.1 fire_B,R,N,C,P,3,C
        - 0.1 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        - 0.1 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2046: - 0.1 fire_B,R,N,C,P,3,P + 0.9 fire_B,R,N,C,P,3,D
        - 0.1 fire_B,R,N,C,P,3,W - 0.1 fire_B,R,N,C,P,3,C
        - 0.1 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        - 0.1 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2047: - 0.1 fire_B,R,N,C,P,3,P - 0.1 fire_B,R,N,C,P,3,D
        + 0.9 fire_B,R,N,C,P,3,W - 0.1 fire_B,R,N,C,P,3,C
        - 0.1 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        - 0.1 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2048: - 0.1 fire_B,R,N,C,P,3,P - 0.1 fire_B,R,N,C,P,3,D
        - 0.1 fire_B,R,N,C,P,3,W + 0.9 fire_B,R,N,C,P,3,C
        - 0.1 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        - 0.1 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2049: - 0.1 fire_B,R,N,C,P,3,P - 0.1 fire_B,R,N,C,P,3,D
        - 0.1 fire_B,R,N,C,P,3,W - 0.1 fire_B,R,N,C,P,3,C
        + 0.9 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        - 0.1 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2050: - 0.2 fire_B,R,N,C,P,3,P - 0.2 fire_B,R,N,C,P,3,D
        - 0.2 fire_B,R,N,C,P,3,W - 0.2 fire_B,R,N,C,P,3,C
        - 0.2 fire_B,R,N,C,P,3,I + 0.8 fire_B,R,N,C,P,3,A
        - 0.2 fire_B,R,N,C,P,3,L - 0.2 fire_B,R,N,C,P,3,N  = 0
 c2051: - 0.1 fire_B,R,N,C,P,3,P - 0.1 fire_B,R,N,C,P,3,D
        - 0.1 fire_B,R,N,C,P,3,W - 0.1 fire_B,R,N,C,P,3,C
        - 0.1 fire_B,R,N,C,P,3,I - 0.1 fire_B,R,N,C,P,3,A
        + 0.9 fire_B,R,N,C,P,3,L - 0.1 fire_B,R,N,C,P,3,N  = 0
 c2052: - 0.2 fire_B,R,N,C,P,3,P - 0.2 fire_B,R,N,C,P,3,D
        - 0.2 fire_B,R,N,C,P,3,W - 0.2 fire_B,R,N,C,P,3,C
        - 0.2 fire_B,R,N,C,P,3,I - 0.2 fire_B,R,N,C,P,3,A
        - 0.2 fire_B,R,N,C,P,3,L + 0.8 fire_B,R,N,C,P,3,N  = 0
 c2053: 0.9 fire_B,R,N,C,P,4,P - 0.1 fire_B,R,N,C,P,4,D
        - 0.1 fire_B,R,N,C,P,4,W - 0.1 fire_B,R,N,C,P,4,C
        - 0.1 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        - 0.1 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2054: - 0.1 fire_B,R,N,C,P,4,P + 0.9 fire_B,R,N,C,P,4,D
        - 0.1 fire_B,R,N,C,P,4,W - 0.1 fire_B,R,N,C,P,4,C
        - 0.1 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        - 0.1 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2055: - 0.1 fire_B,R,N,C,P,4,P - 0.1 fire_B,R,N,C,P,4,D
        + 0.9 fire_B,R,N,C,P,4,W - 0.1 fire_B,R,N,C,P,4,C
        - 0.1 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        - 0.1 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2056: - 0.1 fire_B,R,N,C,P,4,P - 0.1 fire_B,R,N,C,P,4,D
        - 0.1 fire_B,R,N,C,P,4,W + 0.9 fire_B,R,N,C,P,4,C
        - 0.1 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        - 0.1 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2057: - 0.1 fire_B,R,N,C,P,4,P - 0.1 fire_B,R,N,C,P,4,D
        - 0.1 fire_B,R,N,C,P,4,W - 0.1 fire_B,R,N,C,P,4,C
        + 0.9 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        - 0.1 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2058: - 0.2 fire_B,R,N,C,P,4,P - 0.2 fire_B,R,N,C,P,4,D
        - 0.2 fire_B,R,N,C,P,4,W - 0.2 fire_B,R,N,C,P,4,C
        - 0.2 fire_B,R,N,C,P,4,I + 0.8 fire_B,R,N,C,P,4,A
        - 0.2 fire_B,R,N,C,P,4,L - 0.2 fire_B,R,N,C,P,4,N  = 0
 c2059: - 0.1 fire_B,R,N,C,P,4,P - 0.1 fire_B,R,N,C,P,4,D
        - 0.1 fire_B,R,N,C,P,4,W - 0.1 fire_B,R,N,C,P,4,C
        - 0.1 fire_B,R,N,C,P,4,I - 0.1 fire_B,R,N,C,P,4,A
        + 0.9 fire_B,R,N,C,P,4,L - 0.1 fire_B,R,N,C,P,4,N  = 0
 c2060: - 0.2 fire_B,R,N,C,P,4,P - 0.2 fire_B,R,N,C,P,4,D
        - 0.2 fire_B,R,N,C,P,4,W - 0.2 fire_B,R,N,C,P,4,C
        - 0.2 fire_B,R,N,C,P,4,I - 0.2 fire_B,R,N,C,P,4,A
        - 0.2 fire_B,R,N,C,P,4,L + 0.8 fire_B,R,N,C,P,4,N  = 0
 c2061: 0.9 fire_B,R,N,C,P,5,P - 0.1 fire_B,R,N,C,P,5,D
        - 0.1 fire_B,R,N,C,P,5,W - 0.1 fire_B,R,N,C,P,5,C
        - 0.1 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        - 0.1 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2062: - 0.1 fire_B,R,N,C,P,5,P + 0.9 fire_B,R,N,C,P,5,D
        - 0.1 fire_B,R,N,C,P,5,W - 0.1 fire_B,R,N,C,P,5,C
        - 0.1 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        - 0.1 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2063: - 0.1 fire_B,R,N,C,P,5,P - 0.1 fire_B,R,N,C,P,5,D
        + 0.9 fire_B,R,N,C,P,5,W - 0.1 fire_B,R,N,C,P,5,C
        - 0.1 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        - 0.1 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2064: - 0.1 fire_B,R,N,C,P,5,P - 0.1 fire_B,R,N,C,P,5,D
        - 0.1 fire_B,R,N,C,P,5,W + 0.9 fire_B,R,N,C,P,5,C
        - 0.1 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        - 0.1 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2065: - 0.1 fire_B,R,N,C,P,5,P - 0.1 fire_B,R,N,C,P,5,D
        - 0.1 fire_B,R,N,C,P,5,W - 0.1 fire_B,R,N,C,P,5,C
        + 0.9 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        - 0.1 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2066: - 0.2 fire_B,R,N,C,P,5,P - 0.2 fire_B,R,N,C,P,5,D
        - 0.2 fire_B,R,N,C,P,5,W - 0.2 fire_B,R,N,C,P,5,C
        - 0.2 fire_B,R,N,C,P,5,I + 0.8 fire_B,R,N,C,P,5,A
        - 0.2 fire_B,R,N,C,P,5,L - 0.2 fire_B,R,N,C,P,5,N  = 0
 c2067: - 0.1 fire_B,R,N,C,P,5,P - 0.1 fire_B,R,N,C,P,5,D
        - 0.1 fire_B,R,N,C,P,5,W - 0.1 fire_B,R,N,C,P,5,C
        - 0.1 fire_B,R,N,C,P,5,I - 0.1 fire_B,R,N,C,P,5,A
        + 0.9 fire_B,R,N,C,P,5,L - 0.1 fire_B,R,N,C,P,5,N  = 0
 c2068: - 0.2 fire_B,R,N,C,P,5,P - 0.2 fire_B,R,N,C,P,5,D
        - 0.2 fire_B,R,N,C,P,5,W - 0.2 fire_B,R,N,C,P,5,C
        - 0.2 fire_B,R,N,C,P,5,I - 0.2 fire_B,R,N,C,P,5,A
        - 0.2 fire_B,R,N,C,P,5,L + 0.8 fire_B,R,N,C,P,5,N  = 0
 c2069: 0.5 fire_B,R,N,C,D,1,P - 0.5 fire_B,R,N,C,D,1,D
        - 0.5 fire_B,R,N,C,D,1,W - 0.5 fire_B,R,N,C,D,1,C
        - 0.5 fire_B,R,N,C,D,1,I - 0.5 fire_B,R,N,C,D,1,A
        - 0.5 fire_B,R,N,C,D,1,L - 0.5 fire_B,R,N,C,D,1,N  = 0
 c2070: - 0.5 fire_B,R,N,C,D,1,P + 0.5 fire_B,R,N,C,D,1,D
        - 0.5 fire_B,R,N,C,D,1,W - 0.5 fire_B,R,N,C,D,1,C
        - 0.5 fire_B,R,N,C,D,1,I - 0.5 fire_B,R,N,C,D,1,A
        - 0.5 fire_B,R,N,C,D,1,L - 0.5 fire_B,R,N,C,D,1,N  = 0
 c2071: fire_B,R,N,C,D,1,W  = 0
 c2072: fire_B,R,N,C,D,1,C  = 0
 c2073: fire_B,R,N,C,D,1,I  = 0
 c2074: fire_B,R,N,C,D,1,A  = 0
 c2075: fire_B,R,N,C,D,1,L  = 0
 c2076: fire_B,R,N,C,D,1,N  = 0
 c2077: 0.5 fire_B,R,N,C,D,2,P - 0.5 fire_B,R,N,C,D,2,D
        - 0.5 fire_B,R,N,C,D,2,W - 0.5 fire_B,R,N,C,D,2,C
        - 0.5 fire_B,R,N,C,D,2,I - 0.5 fire_B,R,N,C,D,2,A
        - 0.5 fire_B,R,N,C,D,2,L - 0.5 fire_B,R,N,C,D,2,N  = 0
 c2078: - 0.5 fire_B,R,N,C,D,2,P + 0.5 fire_B,R,N,C,D,2,D
        - 0.5 fire_B,R,N,C,D,2,W - 0.5 fire_B,R,N,C,D,2,C
        - 0.5 fire_B,R,N,C,D,2,I - 0.5 fire_B,R,N,C,D,2,A
        - 0.5 fire_B,R,N,C,D,2,L - 0.5 fire_B,R,N,C,D,2,N  = 0
 c2079: fire_B,R,N,C,D,2,W  = 0
 c2080: fire_B,R,N,C,D,2,C  = 0
 c2081: fire_B,R,N,C,D,2,I  = 0
 c2082: fire_B,R,N,C,D,2,A  = 0
 c2083: fire_B,R,N,C,D,2,L  = 0
 c2084: fire_B,R,N,C,D,2,N  = 0
 c2085: 0.5 fire_B,R,N,C,D,3,P - 0.5 fire_B,R,N,C,D,3,D
        - 0.5 fire_B,R,N,C,D,3,W - 0.5 fire_B,R,N,C,D,3,C
        - 0.5 fire_B,R,N,C,D,3,I - 0.5 fire_B,R,N,C,D,3,A
        - 0.5 fire_B,R,N,C,D,3,L - 0.5 fire_B,R,N,C,D,3,N  = 0
 c2086: - 0.5 fire_B,R,N,C,D,3,P + 0.5 fire_B,R,N,C,D,3,D
        - 0.5 fire_B,R,N,C,D,3,W - 0.5 fire_B,R,N,C,D,3,C
        - 0.5 fire_B,R,N,C,D,3,I - 0.5 fire_B,R,N,C,D,3,A
        - 0.5 fire_B,R,N,C,D,3,L - 0.5 fire_B,R,N,C,D,3,N  = 0
 c2087: fire_B,R,N,C,D,3,W  = 0
 c2088: fire_B,R,N,C,D,3,C  = 0
 c2089: fire_B,R,N,C,D,3,I  = 0
 c2090: fire_B,R,N,C,D,3,A  = 0
 c2091: fire_B,R,N,C,D,3,L  = 0
 c2092: fire_B,R,N,C,D,3,N  = 0
 c2093: 0.5 fire_B,R,N,C,D,4,P - 0.5 fire_B,R,N,C,D,4,D
        - 0.5 fire_B,R,N,C,D,4,W - 0.5 fire_B,R,N,C,D,4,C
        - 0.5 fire_B,R,N,C,D,4,I - 0.5 fire_B,R,N,C,D,4,A
        - 0.5 fire_B,R,N,C,D,4,L - 0.5 fire_B,R,N,C,D,4,N  = 0
 c2094: - 0.5 fire_B,R,N,C,D,4,P + 0.5 fire_B,R,N,C,D,4,D
        - 0.5 fire_B,R,N,C,D,4,W - 0.5 fire_B,R,N,C,D,4,C
        - 0.5 fire_B,R,N,C,D,4,I - 0.5 fire_B,R,N,C,D,4,A
        - 0.5 fire_B,R,N,C,D,4,L - 0.5 fire_B,R,N,C,D,4,N  = 0
 c2095: fire_B,R,N,C,D,4,W  = 0
 c2096: fire_B,R,N,C,D,4,C  = 0
 c2097: fire_B,R,N,C,D,4,I  = 0
 c2098: fire_B,R,N,C,D,4,A  = 0
 c2099: fire_B,R,N,C,D,4,L  = 0
 c2100: fire_B,R,N,C,D,4,N  = 0
 c2101: 0.5 fire_B,R,N,C,D,5,P - 0.5 fire_B,R,N,C,D,5,D
        - 0.5 fire_B,R,N,C,D,5,W - 0.5 fire_B,R,N,C,D,5,C
        - 0.5 fire_B,R,N,C,D,5,I - 0.5 fire_B,R,N,C,D,5,A
        - 0.5 fire_B,R,N,C,D,5,L - 0.5 fire_B,R,N,C,D,5,N  = 0
 c2102: - 0.5 fire_B,R,N,C,D,5,P + 0.5 fire_B,R,N,C,D,5,D
        - 0.5 fire_B,R,N,C,D,5,W - 0.5 fire_B,R,N,C,D,5,C
        - 0.5 fire_B,R,N,C,D,5,I - 0.5 fire_B,R,N,C,D,5,A
        - 0.5 fire_B,R,N,C,D,5,L - 0.5 fire_B,R,N,C,D,5,N  = 0
 c2103: fire_B,R,N,C,D,5,W  = 0
 c2104: fire_B,R,N,C,D,5,C  = 0
 c2105: fire_B,R,N,C,D,5,I  = 0
 c2106: fire_B,R,N,C,D,5,A  = 0
 c2107: fire_B,R,N,C,D,5,L  = 0
 c2108: fire_B,R,N,C,D,5,N  = 0
 c2109: fire_B,R,N,C,W,1,P  = 0
 c2110: fire_B,R,N,C,W,1,D  = 0
 c2111: - fire_B,R,N,C,W,1,P - fire_B,R,N,C,W,1,D - fire_B,R,N,C,W,1,C
        - fire_B,R,N,C,W,1,I - fire_B,R,N,C,W,1,A - fire_B,R,N,C,W,1,L
        - fire_B,R,N,C,W,1,N  = 0
 c2112: fire_B,R,N,C,W,1,C  = 0
 c2113: fire_B,R,N,C,W,1,I  = 0
 c2114: fire_B,R,N,C,W,1,A  = 0
 c2115: fire_B,R,N,C,W,1,L  = 0
 c2116: fire_B,R,N,C,W,1,N  = 0
 c2117: fire_B,R,N,C,W,2,P  = 0
 c2118: fire_B,R,N,C,W,2,D  = 0
 c2119: - fire_B,R,N,C,W,2,P - fire_B,R,N,C,W,2,D - fire_B,R,N,C,W,2,C
        - fire_B,R,N,C,W,2,I - fire_B,R,N,C,W,2,A - fire_B,R,N,C,W,2,L
        - fire_B,R,N,C,W,2,N  = 0
 c2120: fire_B,R,N,C,W,2,C  = 0
 c2121: fire_B,R,N,C,W,2,I  = 0
 c2122: fire_B,R,N,C,W,2,A  = 0
 c2123: fire_B,R,N,C,W,2,L  = 0
 c2124: fire_B,R,N,C,W,2,N  = 0
 c2125: fire_B,R,N,C,W,3,P  = 0
 c2126: fire_B,R,N,C,W,3,D  = 0
 c2127: - fire_B,R,N,C,W,3,P - fire_B,R,N,C,W,3,D - fire_B,R,N,C,W,3,C
        - fire_B,R,N,C,W,3,I - fire_B,R,N,C,W,3,A - fire_B,R,N,C,W,3,L
        - fire_B,R,N,C,W,3,N  = 0
 c2128: fire_B,R,N,C,W,3,C  = 0
 c2129: fire_B,R,N,C,W,3,I  = 0
 c2130: fire_B,R,N,C,W,3,A  = 0
 c2131: fire_B,R,N,C,W,3,L  = 0
 c2132: fire_B,R,N,C,W,3,N  = 0
 c2133: fire_B,R,N,C,W,4,P  = 0
 c2134: fire_B,R,N,C,W,4,D  = 0
 c2135: - fire_B,R,N,C,W,4,P - fire_B,R,N,C,W,4,D - fire_B,R,N,C,W,4,C
        - fire_B,R,N,C,W,4,I - fire_B,R,N,C,W,4,A - fire_B,R,N,C,W,4,L
        - fire_B,R,N,C,W,4,N  = 0
 c2136: fire_B,R,N,C,W,4,C  = 0
 c2137: fire_B,R,N,C,W,4,I  = 0
 c2138: fire_B,R,N,C,W,4,A  = 0
 c2139: fire_B,R,N,C,W,4,L  = 0
 c2140: fire_B,R,N,C,W,4,N  = 0
 c2141: fire_B,R,N,C,W,5,P  = 0
 c2142: fire_B,R,N,C,W,5,D  = 0
 c2143: - fire_B,R,N,C,W,5,P - fire_B,R,N,C,W,5,D - fire_B,R,N,C,W,5,C
        - fire_B,R,N,C,W,5,I - fire_B,R,N,C,W,5,A - fire_B,R,N,C,W,5,L
        - fire_B,R,N,C,W,5,N  = 0
 c2144: fire_B,R,N,C,W,5,C  = 0
 c2145: fire_B,R,N,C,W,5,I  = 0
 c2146: fire_B,R,N,C,W,5,A  = 0
 c2147: fire_B,R,N,C,W,5,L  = 0
 c2148: fire_B,R,N,C,W,5,N  = 0
 c2149: fire_B,R,N,C,C,1,P  = 0
 c2150: fire_B,R,N,C,C,1,D  = 0
 c2151: fire_B,R,N,C,C,1,W  = 0
 c2152: - fire_B,R,N,C,C,1,P - fire_B,R,N,C,C,1,D - fire_B,R,N,C,C,1,W
        - fire_B,R,N,C,C,1,I - fire_B,R,N,C,C,1,A - fire_B,R,N,C,C,1,L
        - fire_B,R,N,C,C,1,N  = 0
 c2153: fire_B,R,N,C,C,1,I  = 0
 c2154: fire_B,R,N,C,C,1,A  = 0
 c2155: fire_B,R,N,C,C,1,L  = 0
 c2156: fire_B,R,N,C,C,1,N  = 0
 c2157: fire_B,R,N,C,C,2,P  = 0
 c2158: fire_B,R,N,C,C,2,D  = 0
 c2159: fire_B,R,N,C,C,2,W  = 0
 c2160: - fire_B,R,N,C,C,2,P - fire_B,R,N,C,C,2,D - fire_B,R,N,C,C,2,W
        - fire_B,R,N,C,C,2,I - fire_B,R,N,C,C,2,A - fire_B,R,N,C,C,2,L
        - fire_B,R,N,C,C,2,N  = 0
 c2161: fire_B,R,N,C,C,2,I  = 0
 c2162: fire_B,R,N,C,C,2,A  = 0
 c2163: fire_B,R,N,C,C,2,L  = 0
 c2164: fire_B,R,N,C,C,2,N  = 0
 c2165: fire_B,R,N,C,C,3,P  = 0
 c2166: fire_B,R,N,C,C,3,D  = 0
 c2167: fire_B,R,N,C,C,3,W  = 0
 c2168: - fire_B,R,N,C,C,3,P - fire_B,R,N,C,C,3,D - fire_B,R,N,C,C,3,W
        - fire_B,R,N,C,C,3,I - fire_B,R,N,C,C,3,A - fire_B,R,N,C,C,3,L
        - fire_B,R,N,C,C,3,N  = 0
 c2169: fire_B,R,N,C,C,3,I  = 0
 c2170: fire_B,R,N,C,C,3,A  = 0
 c2171: fire_B,R,N,C,C,3,L  = 0
 c2172: fire_B,R,N,C,C,3,N  = 0
 c2173: fire_B,R,N,C,C,4,P  = 0
 c2174: fire_B,R,N,C,C,4,D  = 0
 c2175: fire_B,R,N,C,C,4,W  = 0
 c2176: - fire_B,R,N,C,C,4,P - fire_B,R,N,C,C,4,D - fire_B,R,N,C,C,4,W
        - fire_B,R,N,C,C,4,I - fire_B,R,N,C,C,4,A - fire_B,R,N,C,C,4,L
        - fire_B,R,N,C,C,4,N  = 0
 c2177: fire_B,R,N,C,C,4,I  = 0
 c2178: fire_B,R,N,C,C,4,A  = 0
 c2179: fire_B,R,N,C,C,4,L  = 0
 c2180: fire_B,R,N,C,C,4,N  = 0
 c2181: fire_B,R,N,C,C,5,P  = 0
 c2182: fire_B,R,N,C,C,5,D  = 0
 c2183: fire_B,R,N,C,C,5,W  = 0
 c2184: - fire_B,R,N,C,C,5,P - fire_B,R,N,C,C,5,D - fire_B,R,N,C,C,5,W
        - fire_B,R,N,C,C,5,I - fire_B,R,N,C,C,5,A - fire_B,R,N,C,C,5,L
        - fire_B,R,N,C,C,5,N  = 0
 c2185: fire_B,R,N,C,C,5,I  = 0
 c2186: fire_B,R,N,C,C,5,A  = 0
 c2187: fire_B,R,N,C,C,5,L  = 0
 c2188: fire_B,R,N,C,C,5,N  = 0
 c2189: fire_B,R,N,C,I,1,P  = 0
 c2190: fire_B,R,N,C,I,1,D  = 0
 c2191: fire_B,R,N,C,I,1,W  = 0
 c2192: fire_B,R,N,C,I,1,C  = 0
 c2193: - fire_B,R,N,C,I,1,P - fire_B,R,N,C,I,1,D - fire_B,R,N,C,I,1,W
        - fire_B,R,N,C,I,1,C - fire_B,R,N,C,I,1,A - fire_B,R,N,C,I,1,L
        - fire_B,R,N,C,I,1,N  = 0
 c2194: fire_B,R,N,C,I,1,A  = 0
 c2195: fire_B,R,N,C,I,1,L  = 0
 c2196: fire_B,R,N,C,I,1,N  = 0
 c2197: fire_B,R,N,C,I,2,P  = 0
 c2198: fire_B,R,N,C,I,2,D  = 0
 c2199: fire_B,R,N,C,I,2,W  = 0
 c2200: fire_B,R,N,C,I,2,C  = 0
 c2201: - fire_B,R,N,C,I,2,P - fire_B,R,N,C,I,2,D - fire_B,R,N,C,I,2,W
        - fire_B,R,N,C,I,2,C - fire_B,R,N,C,I,2,A - fire_B,R,N,C,I,2,L
        - fire_B,R,N,C,I,2,N  = 0
 c2202: fire_B,R,N,C,I,2,A  = 0
 c2203: fire_B,R,N,C,I,2,L  = 0
 c2204: fire_B,R,N,C,I,2,N  = 0
 c2205: fire_B,R,N,C,I,3,P  = 0
 c2206: fire_B,R,N,C,I,3,D  = 0
 c2207: fire_B,R,N,C,I,3,W  = 0
 c2208: fire_B,R,N,C,I,3,C  = 0
 c2209: - fire_B,R,N,C,I,3,P - fire_B,R,N,C,I,3,D - fire_B,R,N,C,I,3,W
        - fire_B,R,N,C,I,3,C - fire_B,R,N,C,I,3,A - fire_B,R,N,C,I,3,L
        - fire_B,R,N,C,I,3,N  = 0
 c2210: fire_B,R,N,C,I,3,A  = 0
 c2211: fire_B,R,N,C,I,3,L  = 0
 c2212: fire_B,R,N,C,I,3,N  = 0
 c2213: fire_B,R,N,C,I,4,P  = 0
 c2214: fire_B,R,N,C,I,4,D  = 0
 c2215: fire_B,R,N,C,I,4,W  = 0
 c2216: fire_B,R,N,C,I,4,C  = 0
 c2217: - fire_B,R,N,C,I,4,P - fire_B,R,N,C,I,4,D - fire_B,R,N,C,I,4,W
        - fire_B,R,N,C,I,4,C - fire_B,R,N,C,I,4,A - fire_B,R,N,C,I,4,L
        - fire_B,R,N,C,I,4,N  = 0
 c2218: fire_B,R,N,C,I,4,A  = 0
 c2219: fire_B,R,N,C,I,4,L  = 0
 c2220: fire_B,R,N,C,I,4,N  = 0
 c2221: fire_B,R,N,C,I,5,P  = 0
 c2222: fire_B,R,N,C,I,5,D  = 0
 c2223: fire_B,R,N,C,I,5,W  = 0
 c2224: fire_B,R,N,C,I,5,C  = 0
 c2225: - fire_B,R,N,C,I,5,P - fire_B,R,N,C,I,5,D - fire_B,R,N,C,I,5,W
        - fire_B,R,N,C,I,5,C - fire_B,R,N,C,I,5,A - fire_B,R,N,C,I,5,L
        - fire_B,R,N,C,I,5,N  = 0
 c2226: fire_B,R,N,C,I,5,A  = 0
 c2227: fire_B,R,N,C,I,5,L  = 0
 c2228: fire_B,R,N,C,I,5,N  = 0
 c2229: fire_B,R,N,C,A,1,P  = 0
 c2230: fire_B,R,N,C,A,1,D  = 0
 c2231: fire_B,R,N,C,A,1,W  = 0
 c2232: fire_B,R,N,C,A,1,C  = 0
 c2233: fire_B,R,N,C,A,1,I  = 0
 c2234: - fire_B,R,N,C,A,1,P - fire_B,R,N,C,A,1,D - fire_B,R,N,C,A,1,W
        - fire_B,R,N,C,A,1,C - fire_B,R,N,C,A,1,I - fire_B,R,N,C,A,1,L
        - fire_B,R,N,C,A,1,N  = 0
 c2235: fire_B,R,N,C,A,1,L  = 0
 c2236: fire_B,R,N,C,A,1,N  = 0
 c2237: fire_B,R,N,C,A,2,P  = 0
 c2238: fire_B,R,N,C,A,2,D  = 0
 c2239: fire_B,R,N,C,A,2,W  = 0
 c2240: fire_B,R,N,C,A,2,C  = 0
 c2241: fire_B,R,N,C,A,2,I  = 0
 c2242: - fire_B,R,N,C,A,2,P - fire_B,R,N,C,A,2,D - fire_B,R,N,C,A,2,W
        - fire_B,R,N,C,A,2,C - fire_B,R,N,C,A,2,I - fire_B,R,N,C,A,2,L
        - fire_B,R,N,C,A,2,N  = 0
 c2243: fire_B,R,N,C,A,2,L  = 0
 c2244: fire_B,R,N,C,A,2,N  = 0
 c2245: fire_B,R,N,C,A,3,P  = 0
 c2246: fire_B,R,N,C,A,3,D  = 0
 c2247: fire_B,R,N,C,A,3,W  = 0
 c2248: fire_B,R,N,C,A,3,C  = 0
 c2249: fire_B,R,N,C,A,3,I  = 0
 c2250: - fire_B,R,N,C,A,3,P - fire_B,R,N,C,A,3,D - fire_B,R,N,C,A,3,W
        - fire_B,R,N,C,A,3,C - fire_B,R,N,C,A,3,I - fire_B,R,N,C,A,3,L
        - fire_B,R,N,C,A,3,N  = 0
 c2251: fire_B,R,N,C,A,3,L  = 0
 c2252: fire_B,R,N,C,A,3,N  = 0
 c2253: fire_B,R,N,C,A,4,P  = 0
 c2254: fire_B,R,N,C,A,4,D  = 0
 c2255: fire_B,R,N,C,A,4,W  = 0
 c2256: fire_B,R,N,C,A,4,C  = 0
 c2257: fire_B,R,N,C,A,4,I  = 0
 c2258: - fire_B,R,N,C,A,4,P - fire_B,R,N,C,A,4,D - fire_B,R,N,C,A,4,W
        - fire_B,R,N,C,A,4,C - fire_B,R,N,C,A,4,I - fire_B,R,N,C,A,4,L
        - fire_B,R,N,C,A,4,N  = 0
 c2259: fire_B,R,N,C,A,4,L  = 0
 c2260: fire_B,R,N,C,A,4,N  = 0
 c2261: fire_B,R,N,C,A,5,P  = 0
 c2262: fire_B,R,N,C,A,5,D  = 0
 c2263: fire_B,R,N,C,A,5,W  = 0
 c2264: fire_B,R,N,C,A,5,C  = 0
 c2265: fire_B,R,N,C,A,5,I  = 0
 c2266: - fire_B,R,N,C,A,5,P - fire_B,R,N,C,A,5,D - fire_B,R,N,C,A,5,W
        - fire_B,R,N,C,A,5,C - fire_B,R,N,C,A,5,I - fire_B,R,N,C,A,5,L
        - fire_B,R,N,C,A,5,N  = 0
 c2267: fire_B,R,N,C,A,5,L  = 0
 c2268: fire_B,R,N,C,A,5,N  = 0
 c2269: fire_B,R,N,C,L,1,P  = 0
 c2270: fire_B,R,N,C,L,1,D  = 0
 c2271: fire_B,R,N,C,L,1,W  = 0
 c2272: fire_B,R,N,C,L,1,C  = 0
 c2273: fire_B,R,N,C,L,1,I  = 0
 c2274: fire_B,R,N,C,L,1,A  = 0
 c2275: - fire_B,R,N,C,L,1,P - fire_B,R,N,C,L,1,D - fire_B,R,N,C,L,1,W
        - fire_B,R,N,C,L,1,C - fire_B,R,N,C,L,1,I - fire_B,R,N,C,L,1,A
        - fire_B,R,N,C,L,1,N  = 0
 c2276: fire_B,R,N,C,L,1,N  = 0
 c2277: fire_B,R,N,C,L,2,P  = 0
 c2278: fire_B,R,N,C,L,2,D  = 0
 c2279: fire_B,R,N,C,L,2,W  = 0
 c2280: fire_B,R,N,C,L,2,C  = 0
 c2281: fire_B,R,N,C,L,2,I  = 0
 c2282: fire_B,R,N,C,L,2,A  = 0
 c2283: - fire_B,R,N,C,L,2,P - fire_B,R,N,C,L,2,D - fire_B,R,N,C,L,2,W
        - fire_B,R,N,C,L,2,C - fire_B,R,N,C,L,2,I - fire_B,R,N,C,L,2,A
        - fire_B,R,N,C,L,2,N  = 0
 c2284: fire_B,R,N,C,L,2,N  = 0
 c2285: fire_B,R,N,C,L,3,P  = 0
 c2286: fire_B,R,N,C,L,3,D  = 0
 c2287: fire_B,R,N,C,L,3,W  = 0
 c2288: fire_B,R,N,C,L,3,C  = 0
 c2289: fire_B,R,N,C,L,3,I  = 0
 c2290: fire_B,R,N,C,L,3,A  = 0
 c2291: - fire_B,R,N,C,L,3,P - fire_B,R,N,C,L,3,D - fire_B,R,N,C,L,3,W
        - fire_B,R,N,C,L,3,C - fire_B,R,N,C,L,3,I - fire_B,R,N,C,L,3,A
        - fire_B,R,N,C,L,3,N  = 0
 c2292: fire_B,R,N,C,L,3,N  = 0
 c2293: fire_B,R,N,C,L,4,P  = 0
 c2294: fire_B,R,N,C,L,4,D  = 0
 c2295: fire_B,R,N,C,L,4,W  = 0
 c2296: fire_B,R,N,C,L,4,C  = 0
 c2297: fire_B,R,N,C,L,4,I  = 0
 c2298: fire_B,R,N,C,L,4,A  = 0
 c2299: - fire_B,R,N,C,L,4,P - fire_B,R,N,C,L,4,D - fire_B,R,N,C,L,4,W
        - fire_B,R,N,C,L,4,C - fire_B,R,N,C,L,4,I - fire_B,R,N,C,L,4,A
        - fire_B,R,N,C,L,4,N  = 0
 c2300: fire_B,R,N,C,L,4,N  = 0
 c2301: fire_B,R,N,C,L,5,P  = 0
 c2302: fire_B,R,N,C,L,5,D  = 0
 c2303: fire_B,R,N,C,L,5,W  = 0
 c2304: fire_B,R,N,C,L,5,C  = 0
 c2305: fire_B,R,N,C,L,5,I  = 0
 c2306: fire_B,R,N,C,L,5,A  = 0
 c2307: - fire_B,R,N,C,L,5,P - fire_B,R,N,C,L,5,D - fire_B,R,N,C,L,5,W
        - fire_B,R,N,C,L,5,C - fire_B,R,N,C,L,5,I - fire_B,R,N,C,L,5,A
        - fire_B,R,N,C,L,5,N  = 0
 c2308: fire_B,R,N,C,L,5,N  = 0
 c2309: fire_B,R,N,C,N,1,P  = 0
 c2310: fire_B,R,N,C,N,1,D  = 0
 c2311: fire_B,R,N,C,N,1,W  = 0
 c2312: fire_B,R,N,C,N,1,C  = 0
 c2313: fire_B,R,N,C,N,1,I  = 0
 c2314: fire_B,R,N,C,N,1,A  = 0
 c2315: fire_B,R,N,C,N,1,L  = 0
 c2316: - fire_B,R,N,C,N,1,P - fire_B,R,N,C,N,1,D - fire_B,R,N,C,N,1,W
        - fire_B,R,N,C,N,1,C - fire_B,R,N,C,N,1,I - fire_B,R,N,C,N,1,A
        - fire_B,R,N,C,N,1,L  = 0
 c2317: fire_B,R,N,C,N,2,P  = 0
 c2318: fire_B,R,N,C,N,2,D  = 0
 c2319: fire_B,R,N,C,N,2,W  = 0
 c2320: fire_B,R,N,C,N,2,C  = 0
 c2321: fire_B,R,N,C,N,2,I  = 0
 c2322: fire_B,R,N,C,N,2,A  = 0
 c2323: fire_B,R,N,C,N,2,L  = 0
 c2324: - fire_B,R,N,C,N,2,P - fire_B,R,N,C,N,2,D - fire_B,R,N,C,N,2,W
        - fire_B,R,N,C,N,2,C - fire_B,R,N,C,N,2,I - fire_B,R,N,C,N,2,A
        - fire_B,R,N,C,N,2,L  = 0
 c2325: fire_B,R,N,C,N,3,P  = 0
 c2326: fire_B,R,N,C,N,3,D  = 0
 c2327: fire_B,R,N,C,N,3,W  = 0
 c2328: fire_B,R,N,C,N,3,C  = 0
 c2329: fire_B,R,N,C,N,3,I  = 0
 c2330: fire_B,R,N,C,N,3,A  = 0
 c2331: fire_B,R,N,C,N,3,L  = 0
 c2332: - fire_B,R,N,C,N,3,P - fire_B,R,N,C,N,3,D - fire_B,R,N,C,N,3,W
        - fire_B,R,N,C,N,3,C - fire_B,R,N,C,N,3,I - fire_B,R,N,C,N,3,A
        - fire_B,R,N,C,N,3,L  = 0
 c2333: fire_B,R,N,C,N,4,P  = 0
 c2334: fire_B,R,N,C,N,4,D  = 0
 c2335: fire_B,R,N,C,N,4,W  = 0
 c2336: fire_B,R,N,C,N,4,C  = 0
 c2337: fire_B,R,N,C,N,4,I  = 0
 c2338: fire_B,R,N,C,N,4,A  = 0
 c2339: fire_B,R,N,C,N,4,L  = 0
 c2340: - fire_B,R,N,C,N,4,P - fire_B,R,N,C,N,4,D - fire_B,R,N,C,N,4,W
        - fire_B,R,N,C,N,4,C - fire_B,R,N,C,N,4,I - fire_B,R,N,C,N,4,A
        - fire_B,R,N,C,N,4,L  = 0
 c2341: fire_B,R,N,C,N,5,P  = 0
 c2342: fire_B,R,N,C,N,5,D  = 0
 c2343: fire_B,R,N,C,N,5,W  = 0
 c2344: fire_B,R,N,C,N,5,C  = 0
 c2345: fire_B,R,N,C,N,5,I  = 0
 c2346: fire_B,R,N,C,N,5,A  = 0
 c2347: fire_B,R,N,C,N,5,L  = 0
 c2348: - fire_B,R,N,C,N,5,P - fire_B,R,N,C,N,5,D - fire_B,R,N,C,N,5,W
        - fire_B,R,N,C,N,5,C - fire_B,R,N,C,N,5,I - fire_B,R,N,C,N,5,A
        - fire_B,R,N,C,N,5,L  = 0
 c2349: 0.9 fire_B,R,N,R,P,1,P - 0.1 fire_B,R,N,R,P,1,D
        - 0.1 fire_B,R,N,R,P,1,W - 0.1 fire_B,R,N,R,P,1,C
        - 0.1 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        - 0.1 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2350: - 0.1 fire_B,R,N,R,P,1,P + 0.9 fire_B,R,N,R,P,1,D
        - 0.1 fire_B,R,N,R,P,1,W - 0.1 fire_B,R,N,R,P,1,C
        - 0.1 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        - 0.1 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2351: - 0.1 fire_B,R,N,R,P,1,P - 0.1 fire_B,R,N,R,P,1,D
        + 0.9 fire_B,R,N,R,P,1,W - 0.1 fire_B,R,N,R,P,1,C
        - 0.1 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        - 0.1 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2352: - 0.1 fire_B,R,N,R,P,1,P - 0.1 fire_B,R,N,R,P,1,D
        - 0.1 fire_B,R,N,R,P,1,W + 0.9 fire_B,R,N,R,P,1,C
        - 0.1 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        - 0.1 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2353: - 0.1 fire_B,R,N,R,P,1,P - 0.1 fire_B,R,N,R,P,1,D
        - 0.1 fire_B,R,N,R,P,1,W - 0.1 fire_B,R,N,R,P,1,C
        + 0.9 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        - 0.1 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2354: - 0.2 fire_B,R,N,R,P,1,P - 0.2 fire_B,R,N,R,P,1,D
        - 0.2 fire_B,R,N,R,P,1,W - 0.2 fire_B,R,N,R,P,1,C
        - 0.2 fire_B,R,N,R,P,1,I + 0.8 fire_B,R,N,R,P,1,A
        - 0.2 fire_B,R,N,R,P,1,L - 0.2 fire_B,R,N,R,P,1,N  = 0
 c2355: - 0.1 fire_B,R,N,R,P,1,P - 0.1 fire_B,R,N,R,P,1,D
        - 0.1 fire_B,R,N,R,P,1,W - 0.1 fire_B,R,N,R,P,1,C
        - 0.1 fire_B,R,N,R,P,1,I - 0.1 fire_B,R,N,R,P,1,A
        + 0.9 fire_B,R,N,R,P,1,L - 0.1 fire_B,R,N,R,P,1,N  = 0
 c2356: - 0.2 fire_B,R,N,R,P,1,P - 0.2 fire_B,R,N,R,P,1,D
        - 0.2 fire_B,R,N,R,P,1,W - 0.2 fire_B,R,N,R,P,1,C
        - 0.2 fire_B,R,N,R,P,1,I - 0.2 fire_B,R,N,R,P,1,A
        - 0.2 fire_B,R,N,R,P,1,L + 0.8 fire_B,R,N,R,P,1,N  = 0
 c2357: 0.9 fire_B,R,N,R,P,2,P - 0.1 fire_B,R,N,R,P,2,D
        - 0.1 fire_B,R,N,R,P,2,W - 0.1 fire_B,R,N,R,P,2,C
        - 0.1 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        - 0.1 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2358: - 0.1 fire_B,R,N,R,P,2,P + 0.9 fire_B,R,N,R,P,2,D
        - 0.1 fire_B,R,N,R,P,2,W - 0.1 fire_B,R,N,R,P,2,C
        - 0.1 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        - 0.1 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2359: - 0.1 fire_B,R,N,R,P,2,P - 0.1 fire_B,R,N,R,P,2,D
        + 0.9 fire_B,R,N,R,P,2,W - 0.1 fire_B,R,N,R,P,2,C
        - 0.1 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        - 0.1 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2360: - 0.1 fire_B,R,N,R,P,2,P - 0.1 fire_B,R,N,R,P,2,D
        - 0.1 fire_B,R,N,R,P,2,W + 0.9 fire_B,R,N,R,P,2,C
        - 0.1 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        - 0.1 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2361: - 0.1 fire_B,R,N,R,P,2,P - 0.1 fire_B,R,N,R,P,2,D
        - 0.1 fire_B,R,N,R,P,2,W - 0.1 fire_B,R,N,R,P,2,C
        + 0.9 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        - 0.1 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2362: - 0.2 fire_B,R,N,R,P,2,P - 0.2 fire_B,R,N,R,P,2,D
        - 0.2 fire_B,R,N,R,P,2,W - 0.2 fire_B,R,N,R,P,2,C
        - 0.2 fire_B,R,N,R,P,2,I + 0.8 fire_B,R,N,R,P,2,A
        - 0.2 fire_B,R,N,R,P,2,L - 0.2 fire_B,R,N,R,P,2,N  = 0
 c2363: - 0.1 fire_B,R,N,R,P,2,P - 0.1 fire_B,R,N,R,P,2,D
        - 0.1 fire_B,R,N,R,P,2,W - 0.1 fire_B,R,N,R,P,2,C
        - 0.1 fire_B,R,N,R,P,2,I - 0.1 fire_B,R,N,R,P,2,A
        + 0.9 fire_B,R,N,R,P,2,L - 0.1 fire_B,R,N,R,P,2,N  = 0
 c2364: - 0.2 fire_B,R,N,R,P,2,P - 0.2 fire_B,R,N,R,P,2,D
        - 0.2 fire_B,R,N,R,P,2,W - 0.2 fire_B,R,N,R,P,2,C
        - 0.2 fire_B,R,N,R,P,2,I - 0.2 fire_B,R,N,R,P,2,A
        - 0.2 fire_B,R,N,R,P,2,L + 0.8 fire_B,R,N,R,P,2,N  = 0
 c2365: 0.9 fire_B,R,N,R,P,3,P - 0.1 fire_B,R,N,R,P,3,D
        - 0.1 fire_B,R,N,R,P,3,W - 0.1 fire_B,R,N,R,P,3,C
        - 0.1 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        - 0.1 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2366: - 0.1 fire_B,R,N,R,P,3,P + 0.9 fire_B,R,N,R,P,3,D
        - 0.1 fire_B,R,N,R,P,3,W - 0.1 fire_B,R,N,R,P,3,C
        - 0.1 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        - 0.1 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2367: - 0.1 fire_B,R,N,R,P,3,P - 0.1 fire_B,R,N,R,P,3,D
        + 0.9 fire_B,R,N,R,P,3,W - 0.1 fire_B,R,N,R,P,3,C
        - 0.1 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        - 0.1 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2368: - 0.1 fire_B,R,N,R,P,3,P - 0.1 fire_B,R,N,R,P,3,D
        - 0.1 fire_B,R,N,R,P,3,W + 0.9 fire_B,R,N,R,P,3,C
        - 0.1 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        - 0.1 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2369: - 0.1 fire_B,R,N,R,P,3,P - 0.1 fire_B,R,N,R,P,3,D
        - 0.1 fire_B,R,N,R,P,3,W - 0.1 fire_B,R,N,R,P,3,C
        + 0.9 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        - 0.1 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2370: - 0.2 fire_B,R,N,R,P,3,P - 0.2 fire_B,R,N,R,P,3,D
        - 0.2 fire_B,R,N,R,P,3,W - 0.2 fire_B,R,N,R,P,3,C
        - 0.2 fire_B,R,N,R,P,3,I + 0.8 fire_B,R,N,R,P,3,A
        - 0.2 fire_B,R,N,R,P,3,L - 0.2 fire_B,R,N,R,P,3,N  = 0
 c2371: - 0.1 fire_B,R,N,R,P,3,P - 0.1 fire_B,R,N,R,P,3,D
        - 0.1 fire_B,R,N,R,P,3,W - 0.1 fire_B,R,N,R,P,3,C
        - 0.1 fire_B,R,N,R,P,3,I - 0.1 fire_B,R,N,R,P,3,A
        + 0.9 fire_B,R,N,R,P,3,L - 0.1 fire_B,R,N,R,P,3,N  = 0
 c2372: - 0.2 fire_B,R,N,R,P,3,P - 0.2 fire_B,R,N,R,P,3,D
        - 0.2 fire_B,R,N,R,P,3,W - 0.2 fire_B,R,N,R,P,3,C
        - 0.2 fire_B,R,N,R,P,3,I - 0.2 fire_B,R,N,R,P,3,A
        - 0.2 fire_B,R,N,R,P,3,L + 0.8 fire_B,R,N,R,P,3,N  = 0
 c2373: 0.9 fire_B,R,N,R,P,4,P - 0.1 fire_B,R,N,R,P,4,D
        - 0.1 fire_B,R,N,R,P,4,W - 0.1 fire_B,R,N,R,P,4,C
        - 0.1 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        - 0.1 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2374: - 0.1 fire_B,R,N,R,P,4,P + 0.9 fire_B,R,N,R,P,4,D
        - 0.1 fire_B,R,N,R,P,4,W - 0.1 fire_B,R,N,R,P,4,C
        - 0.1 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        - 0.1 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2375: - 0.1 fire_B,R,N,R,P,4,P - 0.1 fire_B,R,N,R,P,4,D
        + 0.9 fire_B,R,N,R,P,4,W - 0.1 fire_B,R,N,R,P,4,C
        - 0.1 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        - 0.1 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2376: - 0.1 fire_B,R,N,R,P,4,P - 0.1 fire_B,R,N,R,P,4,D
        - 0.1 fire_B,R,N,R,P,4,W + 0.9 fire_B,R,N,R,P,4,C
        - 0.1 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        - 0.1 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2377: - 0.1 fire_B,R,N,R,P,4,P - 0.1 fire_B,R,N,R,P,4,D
        - 0.1 fire_B,R,N,R,P,4,W - 0.1 fire_B,R,N,R,P,4,C
        + 0.9 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        - 0.1 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2378: - 0.2 fire_B,R,N,R,P,4,P - 0.2 fire_B,R,N,R,P,4,D
        - 0.2 fire_B,R,N,R,P,4,W - 0.2 fire_B,R,N,R,P,4,C
        - 0.2 fire_B,R,N,R,P,4,I + 0.8 fire_B,R,N,R,P,4,A
        - 0.2 fire_B,R,N,R,P,4,L - 0.2 fire_B,R,N,R,P,4,N  = 0
 c2379: - 0.1 fire_B,R,N,R,P,4,P - 0.1 fire_B,R,N,R,P,4,D
        - 0.1 fire_B,R,N,R,P,4,W - 0.1 fire_B,R,N,R,P,4,C
        - 0.1 fire_B,R,N,R,P,4,I - 0.1 fire_B,R,N,R,P,4,A
        + 0.9 fire_B,R,N,R,P,4,L - 0.1 fire_B,R,N,R,P,4,N  = 0
 c2380: - 0.2 fire_B,R,N,R,P,4,P - 0.2 fire_B,R,N,R,P,4,D
        - 0.2 fire_B,R,N,R,P,4,W - 0.2 fire_B,R,N,R,P,4,C
        - 0.2 fire_B,R,N,R,P,4,I - 0.2 fire_B,R,N,R,P,4,A
        - 0.2 fire_B,R,N,R,P,4,L + 0.8 fire_B,R,N,R,P,4,N  = 0
 c2381: 0.9 fire_B,R,N,R,P,5,P - 0.1 fire_B,R,N,R,P,5,D
        - 0.1 fire_B,R,N,R,P,5,W - 0.1 fire_B,R,N,R,P,5,C
        - 0.1 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        - 0.1 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2382: - 0.1 fire_B,R,N,R,P,5,P + 0.9 fire_B,R,N,R,P,5,D
        - 0.1 fire_B,R,N,R,P,5,W - 0.1 fire_B,R,N,R,P,5,C
        - 0.1 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        - 0.1 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2383: - 0.1 fire_B,R,N,R,P,5,P - 0.1 fire_B,R,N,R,P,5,D
        + 0.9 fire_B,R,N,R,P,5,W - 0.1 fire_B,R,N,R,P,5,C
        - 0.1 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        - 0.1 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2384: - 0.1 fire_B,R,N,R,P,5,P - 0.1 fire_B,R,N,R,P,5,D
        - 0.1 fire_B,R,N,R,P,5,W + 0.9 fire_B,R,N,R,P,5,C
        - 0.1 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        - 0.1 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2385: - 0.1 fire_B,R,N,R,P,5,P - 0.1 fire_B,R,N,R,P,5,D
        - 0.1 fire_B,R,N,R,P,5,W - 0.1 fire_B,R,N,R,P,5,C
        + 0.9 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        - 0.1 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2386: - 0.2 fire_B,R,N,R,P,5,P - 0.2 fire_B,R,N,R,P,5,D
        - 0.2 fire_B,R,N,R,P,5,W - 0.2 fire_B,R,N,R,P,5,C
        - 0.2 fire_B,R,N,R,P,5,I + 0.8 fire_B,R,N,R,P,5,A
        - 0.2 fire_B,R,N,R,P,5,L - 0.2 fire_B,R,N,R,P,5,N  = 0
 c2387: - 0.1 fire_B,R,N,R,P,5,P - 0.1 fire_B,R,N,R,P,5,D
        - 0.1 fire_B,R,N,R,P,5,W - 0.1 fire_B,R,N,R,P,5,C
        - 0.1 fire_B,R,N,R,P,5,I - 0.1 fire_B,R,N,R,P,5,A
        + 0.9 fire_B,R,N,R,P,5,L - 0.1 fire_B,R,N,R,P,5,N  = 0
 c2388: - 0.2 fire_B,R,N,R,P,5,P - 0.2 fire_B,R,N,R,P,5,D
        - 0.2 fire_B,R,N,R,P,5,W - 0.2 fire_B,R,N,R,P,5,C
        - 0.2 fire_B,R,N,R,P,5,I - 0.2 fire_B,R,N,R,P,5,A
        - 0.2 fire_B,R,N,R,P,5,L + 0.8 fire_B,R,N,R,P,5,N  = 0
 c2389: 0.5 fire_B,R,N,R,D,1,P - 0.5 fire_B,R,N,R,D,1,D
        - 0.5 fire_B,R,N,R,D,1,W - 0.5 fire_B,R,N,R,D,1,C
        - 0.5 fire_B,R,N,R,D,1,I - 0.5 fire_B,R,N,R,D,1,A
        - 0.5 fire_B,R,N,R,D,1,L - 0.5 fire_B,R,N,R,D,1,N  = 0
 c2390: - 0.5 fire_B,R,N,R,D,1,P + 0.5 fire_B,R,N,R,D,1,D
        - 0.5 fire_B,R,N,R,D,1,W - 0.5 fire_B,R,N,R,D,1,C
        - 0.5 fire_B,R,N,R,D,1,I - 0.5 fire_B,R,N,R,D,1,A
        - 0.5 fire_B,R,N,R,D,1,L - 0.5 fire_B,R,N,R,D,1,N  = 0
 c2391: fire_B,R,N,R,D,1,W  = 0
 c2392: fire_B,R,N,R,D,1,C  = 0
 c2393: fire_B,R,N,R,D,1,I  = 0
 c2394: fire_B,R,N,R,D,1,A  = 0
 c2395: fire_B,R,N,R,D,1,L  = 0
 c2396: fire_B,R,N,R,D,1,N  = 0
 c2397: 0.5 fire_B,R,N,R,D,2,P - 0.5 fire_B,R,N,R,D,2,D
        - 0.5 fire_B,R,N,R,D,2,W - 0.5 fire_B,R,N,R,D,2,C
        - 0.5 fire_B,R,N,R,D,2,I - 0.5 fire_B,R,N,R,D,2,A
        - 0.5 fire_B,R,N,R,D,2,L - 0.5 fire_B,R,N,R,D,2,N  = 0
 c2398: - 0.5 fire_B,R,N,R,D,2,P + 0.5 fire_B,R,N,R,D,2,D
        - 0.5 fire_B,R,N,R,D,2,W - 0.5 fire_B,R,N,R,D,2,C
        - 0.5 fire_B,R,N,R,D,2,I - 0.5 fire_B,R,N,R,D,2,A
        - 0.5 fire_B,R,N,R,D,2,L - 0.5 fire_B,R,N,R,D,2,N  = 0
 c2399: fire_B,R,N,R,D,2,W  = 0
 c2400: fire_B,R,N,R,D,2,C  = 0
 c2401: fire_B,R,N,R,D,2,I  = 0
 c2402: fire_B,R,N,R,D,2,A  = 0
 c2403: fire_B,R,N,R,D,2,L  = 0
 c2404: fire_B,R,N,R,D,2,N  = 0
 c2405: 0.5 fire_B,R,N,R,D,3,P - 0.5 fire_B,R,N,R,D,3,D
        - 0.5 fire_B,R,N,R,D,3,W - 0.5 fire_B,R,N,R,D,3,C
        - 0.5 fire_B,R,N,R,D,3,I - 0.5 fire_B,R,N,R,D,3,A
        - 0.5 fire_B,R,N,R,D,3,L - 0.5 fire_B,R,N,R,D,3,N  = 0
 c2406: - 0.5 fire_B,R,N,R,D,3,P + 0.5 fire_B,R,N,R,D,3,D
        - 0.5 fire_B,R,N,R,D,3,W - 0.5 fire_B,R,N,R,D,3,C
        - 0.5 fire_B,R,N,R,D,3,I - 0.5 fire_B,R,N,R,D,3,A
        - 0.5 fire_B,R,N,R,D,3,L - 0.5 fire_B,R,N,R,D,3,N  = 0
 c2407: fire_B,R,N,R,D,3,W  = 0
 c2408: fire_B,R,N,R,D,3,C  = 0
 c2409: fire_B,R,N,R,D,3,I  = 0
 c2410: fire_B,R,N,R,D,3,A  = 0
 c2411: fire_B,R,N,R,D,3,L  = 0
 c2412: fire_B,R,N,R,D,3,N  = 0
 c2413: 0.5 fire_B,R,N,R,D,4,P - 0.5 fire_B,R,N,R,D,4,D
        - 0.5 fire_B,R,N,R,D,4,W - 0.5 fire_B,R,N,R,D,4,C
        - 0.5 fire_B,R,N,R,D,4,I - 0.5 fire_B,R,N,R,D,4,A
        - 0.5 fire_B,R,N,R,D,4,L - 0.5 fire_B,R,N,R,D,4,N  = 0
 c2414: - 0.5 fire_B,R,N,R,D,4,P + 0.5 fire_B,R,N,R,D,4,D
        - 0.5 fire_B,R,N,R,D,4,W - 0.5 fire_B,R,N,R,D,4,C
        - 0.5 fire_B,R,N,R,D,4,I - 0.5 fire_B,R,N,R,D,4,A
        - 0.5 fire_B,R,N,R,D,4,L - 0.5 fire_B,R,N,R,D,4,N  = 0
 c2415: fire_B,R,N,R,D,4,W  = 0
 c2416: fire_B,R,N,R,D,4,C  = 0
 c2417: fire_B,R,N,R,D,4,I  = 0
 c2418: fire_B,R,N,R,D,4,A  = 0
 c2419: fire_B,R,N,R,D,4,L  = 0
 c2420: fire_B,R,N,R,D,4,N  = 0
 c2421: 0.5 fire_B,R,N,R,D,5,P - 0.5 fire_B,R,N,R,D,5,D
        - 0.5 fire_B,R,N,R,D,5,W - 0.5 fire_B,R,N,R,D,5,C
        - 0.5 fire_B,R,N,R,D,5,I - 0.5 fire_B,R,N,R,D,5,A
        - 0.5 fire_B,R,N,R,D,5,L - 0.5 fire_B,R,N,R,D,5,N  = 0
 c2422: - 0.5 fire_B,R,N,R,D,5,P + 0.5 fire_B,R,N,R,D,5,D
        - 0.5 fire_B,R,N,R,D,5,W - 0.5 fire_B,R,N,R,D,5,C
        - 0.5 fire_B,R,N,R,D,5,I - 0.5 fire_B,R,N,R,D,5,A
        - 0.5 fire_B,R,N,R,D,5,L - 0.5 fire_B,R,N,R,D,5,N  = 0
 c2423: fire_B,R,N,R,D,5,W  = 0
 c2424: fire_B,R,N,R,D,5,C  = 0
 c2425: fire_B,R,N,R,D,5,I  = 0
 c2426: fire_B,R,N,R,D,5,A  = 0
 c2427: fire_B,R,N,R,D,5,L  = 0
 c2428: fire_B,R,N,R,D,5,N  = 0
 c2429: fire_B,R,N,R,W,1,P  = 0
 c2430: fire_B,R,N,R,W,1,D  = 0
 c2431: - fire_B,R,N,R,W,1,P - fire_B,R,N,R,W,1,D - fire_B,R,N,R,W,1,C
        - fire_B,R,N,R,W,1,I - fire_B,R,N,R,W,1,A - fire_B,R,N,R,W,1,L
        - fire_B,R,N,R,W,1,N  = 0
 c2432: fire_B,R,N,R,W,1,C  = 0
 c2433: fire_B,R,N,R,W,1,I  = 0
 c2434: fire_B,R,N,R,W,1,A  = 0
 c2435: fire_B,R,N,R,W,1,L  = 0
 c2436: fire_B,R,N,R,W,1,N  = 0
 c2437: fire_B,R,N,R,W,2,P  = 0
 c2438: fire_B,R,N,R,W,2,D  = 0
 c2439: - fire_B,R,N,R,W,2,P - fire_B,R,N,R,W,2,D - fire_B,R,N,R,W,2,C
        - fire_B,R,N,R,W,2,I - fire_B,R,N,R,W,2,A - fire_B,R,N,R,W,2,L
        - fire_B,R,N,R,W,2,N  = 0
 c2440: fire_B,R,N,R,W,2,C  = 0
 c2441: fire_B,R,N,R,W,2,I  = 0
 c2442: fire_B,R,N,R,W,2,A  = 0
 c2443: fire_B,R,N,R,W,2,L  = 0
 c2444: fire_B,R,N,R,W,2,N  = 0
 c2445: fire_B,R,N,R,W,3,P  = 0
 c2446: fire_B,R,N,R,W,3,D  = 0
 c2447: - fire_B,R,N,R,W,3,P - fire_B,R,N,R,W,3,D - fire_B,R,N,R,W,3,C
        - fire_B,R,N,R,W,3,I - fire_B,R,N,R,W,3,A - fire_B,R,N,R,W,3,L
        - fire_B,R,N,R,W,3,N  = 0
 c2448: fire_B,R,N,R,W,3,C  = 0
 c2449: fire_B,R,N,R,W,3,I  = 0
 c2450: fire_B,R,N,R,W,3,A  = 0
 c2451: fire_B,R,N,R,W,3,L  = 0
 c2452: fire_B,R,N,R,W,3,N  = 0
 c2453: fire_B,R,N,R,W,4,P  = 0
 c2454: fire_B,R,N,R,W,4,D  = 0
 c2455: - fire_B,R,N,R,W,4,P - fire_B,R,N,R,W,4,D - fire_B,R,N,R,W,4,C
        - fire_B,R,N,R,W,4,I - fire_B,R,N,R,W,4,A - fire_B,R,N,R,W,4,L
        - fire_B,R,N,R,W,4,N  = 0
 c2456: fire_B,R,N,R,W,4,C  = 0
 c2457: fire_B,R,N,R,W,4,I  = 0
 c2458: fire_B,R,N,R,W,4,A  = 0
 c2459: fire_B,R,N,R,W,4,L  = 0
 c2460: fire_B,R,N,R,W,4,N  = 0
 c2461: fire_B,R,N,R,W,5,P  = 0
 c2462: fire_B,R,N,R,W,5,D  = 0
 c2463: - fire_B,R,N,R,W,5,P - fire_B,R,N,R,W,5,D - fire_B,R,N,R,W,5,C
        - fire_B,R,N,R,W,5,I - fire_B,R,N,R,W,5,A - fire_B,R,N,R,W,5,L
        - fire_B,R,N,R,W,5,N  = 0
 c2464: fire_B,R,N,R,W,5,C  = 0
 c2465: fire_B,R,N,R,W,5,I  = 0
 c2466: fire_B,R,N,R,W,5,A  = 0
 c2467: fire_B,R,N,R,W,5,L  = 0
 c2468: fire_B,R,N,R,W,5,N  = 0
 c2469: fire_B,R,N,R,C,1,P  = 0
 c2470: fire_B,R,N,R,C,1,D  = 0
 c2471: fire_B,R,N,R,C,1,W  = 0
 c2472: - fire_B,R,N,R,C,1,P - fire_B,R,N,R,C,1,D - fire_B,R,N,R,C,1,W
        - fire_B,R,N,R,C,1,I - fire_B,R,N,R,C,1,A - fire_B,R,N,R,C,1,L
        - fire_B,R,N,R,C,1,N  = 0
 c2473: fire_B,R,N,R,C,1,I  = 0
 c2474: fire_B,R,N,R,C,1,A  = 0
 c2475: fire_B,R,N,R,C,1,L  = 0
 c2476: fire_B,R,N,R,C,1,N  = 0
 c2477: fire_B,R,N,R,C,2,P  = 0
 c2478: fire_B,R,N,R,C,2,D  = 0
 c2479: fire_B,R,N,R,C,2,W  = 0
 c2480: - fire_B,R,N,R,C,2,P - fire_B,R,N,R,C,2,D - fire_B,R,N,R,C,2,W
        - fire_B,R,N,R,C,2,I - fire_B,R,N,R,C,2,A - fire_B,R,N,R,C,2,L
        - fire_B,R,N,R,C,2,N  = 0
 c2481: fire_B,R,N,R,C,2,I  = 0
 c2482: fire_B,R,N,R,C,2,A  = 0
 c2483: fire_B,R,N,R,C,2,L  = 0
 c2484: fire_B,R,N,R,C,2,N  = 0
 c2485: fire_B,R,N,R,C,3,P  = 0
 c2486: fire_B,R,N,R,C,3,D  = 0
 c2487: fire_B,R,N,R,C,3,W  = 0
 c2488: - fire_B,R,N,R,C,3,P - fire_B,R,N,R,C,3,D - fire_B,R,N,R,C,3,W
        - fire_B,R,N,R,C,3,I - fire_B,R,N,R,C,3,A - fire_B,R,N,R,C,3,L
        - fire_B,R,N,R,C,3,N  = 0
 c2489: fire_B,R,N,R,C,3,I  = 0
 c2490: fire_B,R,N,R,C,3,A  = 0
 c2491: fire_B,R,N,R,C,3,L  = 0
 c2492: fire_B,R,N,R,C,3,N  = 0
 c2493: fire_B,R,N,R,C,4,P  = 0
 c2494: fire_B,R,N,R,C,4,D  = 0
 c2495: fire_B,R,N,R,C,4,W  = 0
 c2496: - fire_B,R,N,R,C,4,P - fire_B,R,N,R,C,4,D - fire_B,R,N,R,C,4,W
        - fire_B,R,N,R,C,4,I - fire_B,R,N,R,C,4,A - fire_B,R,N,R,C,4,L
        - fire_B,R,N,R,C,4,N  = 0
 c2497: fire_B,R,N,R,C,4,I  = 0
 c2498: fire_B,R,N,R,C,4,A  = 0
 c2499: fire_B,R,N,R,C,4,L  = 0
 c2500: fire_B,R,N,R,C,4,N  = 0
 c2501: fire_B,R,N,R,C,5,P  = 0
 c2502: fire_B,R,N,R,C,5,D  = 0
 c2503: fire_B,R,N,R,C,5,W  = 0
 c2504: - fire_B,R,N,R,C,5,P - fire_B,R,N,R,C,5,D - fire_B,R,N,R,C,5,W
        - fire_B,R,N,R,C,5,I - fire_B,R,N,R,C,5,A - fire_B,R,N,R,C,5,L
        - fire_B,R,N,R,C,5,N  = 0
 c2505: fire_B,R,N,R,C,5,I  = 0
 c2506: fire_B,R,N,R,C,5,A  = 0
 c2507: fire_B,R,N,R,C,5,L  = 0
 c2508: fire_B,R,N,R,C,5,N  = 0
 c2509: fire_B,R,N,R,I,1,P  = 0
 c2510: fire_B,R,N,R,I,1,D  = 0
 c2511: fire_B,R,N,R,I,1,W  = 0
 c2512: fire_B,R,N,R,I,1,C  = 0
 c2513: - fire_B,R,N,R,I,1,P - fire_B,R,N,R,I,1,D - fire_B,R,N,R,I,1,W
        - fire_B,R,N,R,I,1,C - fire_B,R,N,R,I,1,A - fire_B,R,N,R,I,1,L
        - fire_B,R,N,R,I,1,N  = 0
 c2514: fire_B,R,N,R,I,1,A  = 0
 c2515: fire_B,R,N,R,I,1,L  = 0
 c2516: fire_B,R,N,R,I,1,N  = 0
 c2517: fire_B,R,N,R,I,2,P  = 0
 c2518: fire_B,R,N,R,I,2,D  = 0
 c2519: fire_B,R,N,R,I,2,W  = 0
 c2520: fire_B,R,N,R,I,2,C  = 0
 c2521: - fire_B,R,N,R,I,2,P - fire_B,R,N,R,I,2,D - fire_B,R,N,R,I,2,W
        - fire_B,R,N,R,I,2,C - fire_B,R,N,R,I,2,A - fire_B,R,N,R,I,2,L
        - fire_B,R,N,R,I,2,N  = 0
 c2522: fire_B,R,N,R,I,2,A  = 0
 c2523: fire_B,R,N,R,I,2,L  = 0
 c2524: fire_B,R,N,R,I,2,N  = 0
 c2525: fire_B,R,N,R,I,3,P  = 0
 c2526: fire_B,R,N,R,I,3,D  = 0
 c2527: fire_B,R,N,R,I,3,W  = 0
 c2528: fire_B,R,N,R,I,3,C  = 0
 c2529: - fire_B,R,N,R,I,3,P - fire_B,R,N,R,I,3,D - fire_B,R,N,R,I,3,W
        - fire_B,R,N,R,I,3,C - fire_B,R,N,R,I,3,A - fire_B,R,N,R,I,3,L
        - fire_B,R,N,R,I,3,N  = 0
 c2530: fire_B,R,N,R,I,3,A  = 0
 c2531: fire_B,R,N,R,I,3,L  = 0
 c2532: fire_B,R,N,R,I,3,N  = 0
 c2533: fire_B,R,N,R,I,4,P  = 0
 c2534: fire_B,R,N,R,I,4,D  = 0
 c2535: fire_B,R,N,R,I,4,W  = 0
 c2536: fire_B,R,N,R,I,4,C  = 0
 c2537: - fire_B,R,N,R,I,4,P - fire_B,R,N,R,I,4,D - fire_B,R,N,R,I,4,W
        - fire_B,R,N,R,I,4,C - fire_B,R,N,R,I,4,A - fire_B,R,N,R,I,4,L
        - fire_B,R,N,R,I,4,N  = 0
 c2538: fire_B,R,N,R,I,4,A  = 0
 c2539: fire_B,R,N,R,I,4,L  = 0
 c2540: fire_B,R,N,R,I,4,N  = 0
 c2541: fire_B,R,N,R,I,5,P  = 0
 c2542: fire_B,R,N,R,I,5,D  = 0
 c2543: fire_B,R,N,R,I,5,W  = 0
 c2544: fire_B,R,N,R,I,5,C  = 0
 c2545: - fire_B,R,N,R,I,5,P - fire_B,R,N,R,I,5,D - fire_B,R,N,R,I,5,W
        - fire_B,R,N,R,I,5,C - fire_B,R,N,R,I,5,A - fire_B,R,N,R,I,5,L
        - fire_B,R,N,R,I,5,N  = 0
 c2546: fire_B,R,N,R,I,5,A  = 0
 c2547: fire_B,R,N,R,I,5,L  = 0
 c2548: fire_B,R,N,R,I,5,N  = 0
 c2549: fire_B,R,N,R,A,1,P  = 0
 c2550: fire_B,R,N,R,A,1,D  = 0
 c2551: fire_B,R,N,R,A,1,W  = 0
 c2552: fire_B,R,N,R,A,1,C  = 0
 c2553: fire_B,R,N,R,A,1,I  = 0
 c2554: - fire_B,R,N,R,A,1,P - fire_B,R,N,R,A,1,D - fire_B,R,N,R,A,1,W
        - fire_B,R,N,R,A,1,C - fire_B,R,N,R,A,1,I - fire_B,R,N,R,A,1,L
        - fire_B,R,N,R,A,1,N  = 0
 c2555: fire_B,R,N,R,A,1,L  = 0
 c2556: fire_B,R,N,R,A,1,N  = 0
 c2557: fire_B,R,N,R,A,2,P  = 0
 c2558: fire_B,R,N,R,A,2,D  = 0
 c2559: fire_B,R,N,R,A,2,W  = 0
 c2560: fire_B,R,N,R,A,2,C  = 0
 c2561: fire_B,R,N,R,A,2,I  = 0
 c2562: - fire_B,R,N,R,A,2,P - fire_B,R,N,R,A,2,D - fire_B,R,N,R,A,2,W
        - fire_B,R,N,R,A,2,C - fire_B,R,N,R,A,2,I - fire_B,R,N,R,A,2,L
        - fire_B,R,N,R,A,2,N  = 0
 c2563: fire_B,R,N,R,A,2,L  = 0
 c2564: fire_B,R,N,R,A,2,N  = 0
 c2565: fire_B,R,N,R,A,3,P  = 0
 c2566: fire_B,R,N,R,A,3,D  = 0
 c2567: fire_B,R,N,R,A,3,W  = 0
 c2568: fire_B,R,N,R,A,3,C  = 0
 c2569: fire_B,R,N,R,A,3,I  = 0
 c2570: - fire_B,R,N,R,A,3,P - fire_B,R,N,R,A,3,D - fire_B,R,N,R,A,3,W
        - fire_B,R,N,R,A,3,C - fire_B,R,N,R,A,3,I - fire_B,R,N,R,A,3,L
        - fire_B,R,N,R,A,3,N  = 0
 c2571: fire_B,R,N,R,A,3,L  = 0
 c2572: fire_B,R,N,R,A,3,N  = 0
 c2573: fire_B,R,N,R,A,4,P  = 0
 c2574: fire_B,R,N,R,A,4,D  = 0
 c2575: fire_B,R,N,R,A,4,W  = 0
 c2576: fire_B,R,N,R,A,4,C  = 0
 c2577: fire_B,R,N,R,A,4,I  = 0
 c2578: - fire_B,R,N,R,A,4,P - fire_B,R,N,R,A,4,D - fire_B,R,N,R,A,4,W
        - fire_B,R,N,R,A,4,C - fire_B,R,N,R,A,4,I - fire_B,R,N,R,A,4,L
        - fire_B,R,N,R,A,4,N  = 0
 c2579: fire_B,R,N,R,A,4,L  = 0
 c2580: fire_B,R,N,R,A,4,N  = 0
 c2581: fire_B,R,N,R,A,5,P  = 0
 c2582: fire_B,R,N,R,A,5,D  = 0
 c2583: fire_B,R,N,R,A,5,W  = 0
 c2584: fire_B,R,N,R,A,5,C  = 0
 c2585: fire_B,R,N,R,A,5,I  = 0
 c2586: - fire_B,R,N,R,A,5,P - fire_B,R,N,R,A,5,D - fire_B,R,N,R,A,5,W
        - fire_B,R,N,R,A,5,C - fire_B,R,N,R,A,5,I - fire_B,R,N,R,A,5,L
        - fire_B,R,N,R,A,5,N  = 0
 c2587: fire_B,R,N,R,A,5,L  = 0
 c2588: fire_B,R,N,R,A,5,N  = 0
 c2589: fire_B,R,N,R,L,1,P  = 0
 c2590: fire_B,R,N,R,L,1,D  = 0
 c2591: fire_B,R,N,R,L,1,W  = 0
 c2592: fire_B,R,N,R,L,1,C  = 0
 c2593: fire_B,R,N,R,L,1,I  = 0
 c2594: fire_B,R,N,R,L,1,A  = 0
 c2595: - fire_B,R,N,R,L,1,P - fire_B,R,N,R,L,1,D - fire_B,R,N,R,L,1,W
        - fire_B,R,N,R,L,1,C - fire_B,R,N,R,L,1,I - fire_B,R,N,R,L,1,A
        - fire_B,R,N,R,L,1,N  = 0
 c2596: fire_B,R,N,R,L,1,N  = 0
 c2597: fire_B,R,N,R,L,2,P  = 0
 c2598: fire_B,R,N,R,L,2,D  = 0
 c2599: fire_B,R,N,R,L,2,W  = 0
 c2600: fire_B,R,N,R,L,2,C  = 0
 c2601: fire_B,R,N,R,L,2,I  = 0
 c2602: fire_B,R,N,R,L,2,A  = 0
 c2603: - fire_B,R,N,R,L,2,P - fire_B,R,N,R,L,2,D - fire_B,R,N,R,L,2,W
        - fire_B,R,N,R,L,2,C - fire_B,R,N,R,L,2,I - fire_B,R,N,R,L,2,A
        - fire_B,R,N,R,L,2,N  = 0
 c2604: fire_B,R,N,R,L,2,N  = 0
 c2605: fire_B,R,N,R,L,3,P  = 0
 c2606: fire_B,R,N,R,L,3,D  = 0
 c2607: fire_B,R,N,R,L,3,W  = 0
 c2608: fire_B,R,N,R,L,3,C  = 0
 c2609: fire_B,R,N,R,L,3,I  = 0
 c2610: fire_B,R,N,R,L,3,A  = 0
 c2611: - fire_B,R,N,R,L,3,P - fire_B,R,N,R,L,3,D - fire_B,R,N,R,L,3,W
        - fire_B,R,N,R,L,3,C - fire_B,R,N,R,L,3,I - fire_B,R,N,R,L,3,A
        - fire_B,R,N,R,L,3,N  = 0
 c2612: fire_B,R,N,R,L,3,N  = 0
 c2613: fire_B,R,N,R,L,4,P  = 0
 c2614: fire_B,R,N,R,L,4,D  = 0
 c2615: fire_B,R,N,R,L,4,W  = 0
 c2616: fire_B,R,N,R,L,4,C  = 0
 c2617: fire_B,R,N,R,L,4,I  = 0
 c2618: fire_B,R,N,R,L,4,A  = 0
 c2619: - fire_B,R,N,R,L,4,P - fire_B,R,N,R,L,4,D - fire_B,R,N,R,L,4,W
        - fire_B,R,N,R,L,4,C - fire_B,R,N,R,L,4,I - fire_B,R,N,R,L,4,A
        - fire_B,R,N,R,L,4,N  = 0
 c2620: fire_B,R,N,R,L,4,N  = 0
 c2621: fire_B,R,N,R,L,5,P  = 0
 c2622: fire_B,R,N,R,L,5,D  = 0
 c2623: fire_B,R,N,R,L,5,W  = 0
 c2624: fire_B,R,N,R,L,5,C  = 0
 c2625: fire_B,R,N,R,L,5,I  = 0
 c2626: fire_B,R,N,R,L,5,A  = 0
 c2627: - fire_B,R,N,R,L,5,P - fire_B,R,N,R,L,5,D - fire_B,R,N,R,L,5,W
        - fire_B,R,N,R,L,5,C - fire_B,R,N,R,L,5,I - fire_B,R,N,R,L,5,A
        - fire_B,R,N,R,L,5,N  = 0
 c2628: fire_B,R,N,R,L,5,N  = 0
 c2629: fire_B,R,N,R,N,1,P  = 0
 c2630: fire_B,R,N,R,N,1,D  = 0
 c2631: fire_B,R,N,R,N,1,W  = 0
 c2632: fire_B,R,N,R,N,1,C  = 0
 c2633: fire_B,R,N,R,N,1,I  = 0
 c2634: fire_B,R,N,R,N,1,A  = 0
 c2635: fire_B,R,N,R,N,1,L  = 0
 c2636: - fire_B,R,N,R,N,1,P - fire_B,R,N,R,N,1,D - fire_B,R,N,R,N,1,W
        - fire_B,R,N,R,N,1,C - fire_B,R,N,R,N,1,I - fire_B,R,N,R,N,1,A
        - fire_B,R,N,R,N,1,L  = 0
 c2637: fire_B,R,N,R,N,2,P  = 0
 c2638: fire_B,R,N,R,N,2,D  = 0
 c2639: fire_B,R,N,R,N,2,W  = 0
 c2640: fire_B,R,N,R,N,2,C  = 0
 c2641: fire_B,R,N,R,N,2,I  = 0
 c2642: fire_B,R,N,R,N,2,A  = 0
 c2643: fire_B,R,N,R,N,2,L  = 0
 c2644: - fire_B,R,N,R,N,2,P - fire_B,R,N,R,N,2,D - fire_B,R,N,R,N,2,W
        - fire_B,R,N,R,N,2,C - fire_B,R,N,R,N,2,I - fire_B,R,N,R,N,2,A
        - fire_B,R,N,R,N,2,L  = 0
 c2645: fire_B,R,N,R,N,3,P  = 0
 c2646: fire_B,R,N,R,N,3,D  = 0
 c2647: fire_B,R,N,R,N,3,W  = 0
 c2648: fire_B,R,N,R,N,3,C  = 0
 c2649: fire_B,R,N,R,N,3,I  = 0
 c2650: fire_B,R,N,R,N,3,A  = 0
 c2651: fire_B,R,N,R,N,3,L  = 0
 c2652: - fire_B,R,N,R,N,3,P - fire_B,R,N,R,N,3,D - fire_B,R,N,R,N,3,W
        - fire_B,R,N,R,N,3,C - fire_B,R,N,R,N,3,I - fire_B,R,N,R,N,3,A
        - fire_B,R,N,R,N,3,L  = 0
 c2653: fire_B,R,N,R,N,4,P  = 0
 c2654: fire_B,R,N,R,N,4,D  = 0
 c2655: fire_B,R,N,R,N,4,W  = 0
 c2656: fire_B,R,N,R,N,4,C  = 0
 c2657: fire_B,R,N,R,N,4,I  = 0
 c2658: fire_B,R,N,R,N,4,A  = 0
 c2659: fire_B,R,N,R,N,4,L  = 0
 c2660: - fire_B,R,N,R,N,4,P - fire_B,R,N,R,N,4,D - fire_B,R,N,R,N,4,W
        - fire_B,R,N,R,N,4,C - fire_B,R,N,R,N,4,I - fire_B,R,N,R,N,4,A
        - fire_B,R,N,R,N,4,L  = 0
 c2661: fire_B,R,N,R,N,5,P  = 0
 c2662: fire_B,R,N,R,N,5,D  = 0
 c2663: fire_B,R,N,R,N,5,W  = 0
 c2664: fire_B,R,N,R,N,5,C  = 0
 c2665: fire_B,R,N,R,N,5,I  = 0
 c2666: fire_B,R,N,R,N,5,A  = 0
 c2667: fire_B,R,N,R,N,5,L  = 0
 c2668: - fire_B,R,N,R,N,5,P - fire_B,R,N,R,N,5,D - fire_B,R,N,R,N,5,W
        - fire_B,R,N,R,N,5,C - fire_B,R,N,R,N,5,I - fire_B,R,N,R,N,5,A
        - fire_B,R,N,R,N,5,L  = 0
 c2669: 0.9 fire_B,R,P,L,P,1,P - 0.1 fire_B,R,P,L,P,1,D
        - 0.1 fire_B,R,P,L,P,1,W - 0.1 fire_B,R,P,L,P,1,C
        - 0.1 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        - 0.1 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2670: - 0.1 fire_B,R,P,L,P,1,P + 0.9 fire_B,R,P,L,P,1,D
        - 0.1 fire_B,R,P,L,P,1,W - 0.1 fire_B,R,P,L,P,1,C
        - 0.1 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        - 0.1 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2671: - 0.1 fire_B,R,P,L,P,1,P - 0.1 fire_B,R,P,L,P,1,D
        + 0.9 fire_B,R,P,L,P,1,W - 0.1 fire_B,R,P,L,P,1,C
        - 0.1 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        - 0.1 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2672: - 0.1 fire_B,R,P,L,P,1,P - 0.1 fire_B,R,P,L,P,1,D
        - 0.1 fire_B,R,P,L,P,1,W + 0.9 fire_B,R,P,L,P,1,C
        - 0.1 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        - 0.1 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2673: - 0.1 fire_B,R,P,L,P,1,P - 0.1 fire_B,R,P,L,P,1,D
        - 0.1 fire_B,R,P,L,P,1,W - 0.1 fire_B,R,P,L,P,1,C
        + 0.9 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        - 0.1 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2674: - 0.2 fire_B,R,P,L,P,1,P - 0.2 fire_B,R,P,L,P,1,D
        - 0.2 fire_B,R,P,L,P,1,W - 0.2 fire_B,R,P,L,P,1,C
        - 0.2 fire_B,R,P,L,P,1,I + 0.8 fire_B,R,P,L,P,1,A
        - 0.2 fire_B,R,P,L,P,1,L - 0.2 fire_B,R,P,L,P,1,N  = 0
 c2675: - 0.1 fire_B,R,P,L,P,1,P - 0.1 fire_B,R,P,L,P,1,D
        - 0.1 fire_B,R,P,L,P,1,W - 0.1 fire_B,R,P,L,P,1,C
        - 0.1 fire_B,R,P,L,P,1,I - 0.1 fire_B,R,P,L,P,1,A
        + 0.9 fire_B,R,P,L,P,1,L - 0.1 fire_B,R,P,L,P,1,N  = 0
 c2676: - 0.2 fire_B,R,P,L,P,1,P - 0.2 fire_B,R,P,L,P,1,D
        - 0.2 fire_B,R,P,L,P,1,W - 0.2 fire_B,R,P,L,P,1,C
        - 0.2 fire_B,R,P,L,P,1,I - 0.2 fire_B,R,P,L,P,1,A
        - 0.2 fire_B,R,P,L,P,1,L + 0.8 fire_B,R,P,L,P,1,N  = 0
 c2677: 0.9 fire_B,R,P,L,P,2,P - 0.1 fire_B,R,P,L,P,2,D
        - 0.1 fire_B,R,P,L,P,2,W - 0.1 fire_B,R,P,L,P,2,C
        - 0.1 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        - 0.1 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2678: - 0.1 fire_B,R,P,L,P,2,P + 0.9 fire_B,R,P,L,P,2,D
        - 0.1 fire_B,R,P,L,P,2,W - 0.1 fire_B,R,P,L,P,2,C
        - 0.1 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        - 0.1 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2679: - 0.1 fire_B,R,P,L,P,2,P - 0.1 fire_B,R,P,L,P,2,D
        + 0.9 fire_B,R,P,L,P,2,W - 0.1 fire_B,R,P,L,P,2,C
        - 0.1 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        - 0.1 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2680: - 0.1 fire_B,R,P,L,P,2,P - 0.1 fire_B,R,P,L,P,2,D
        - 0.1 fire_B,R,P,L,P,2,W + 0.9 fire_B,R,P,L,P,2,C
        - 0.1 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        - 0.1 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2681: - 0.1 fire_B,R,P,L,P,2,P - 0.1 fire_B,R,P,L,P,2,D
        - 0.1 fire_B,R,P,L,P,2,W - 0.1 fire_B,R,P,L,P,2,C
        + 0.9 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        - 0.1 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2682: - 0.2 fire_B,R,P,L,P,2,P - 0.2 fire_B,R,P,L,P,2,D
        - 0.2 fire_B,R,P,L,P,2,W - 0.2 fire_B,R,P,L,P,2,C
        - 0.2 fire_B,R,P,L,P,2,I + 0.8 fire_B,R,P,L,P,2,A
        - 0.2 fire_B,R,P,L,P,2,L - 0.2 fire_B,R,P,L,P,2,N  = 0
 c2683: - 0.1 fire_B,R,P,L,P,2,P - 0.1 fire_B,R,P,L,P,2,D
        - 0.1 fire_B,R,P,L,P,2,W - 0.1 fire_B,R,P,L,P,2,C
        - 0.1 fire_B,R,P,L,P,2,I - 0.1 fire_B,R,P,L,P,2,A
        + 0.9 fire_B,R,P,L,P,2,L - 0.1 fire_B,R,P,L,P,2,N  = 0
 c2684: - 0.2 fire_B,R,P,L,P,2,P - 0.2 fire_B,R,P,L,P,2,D
        - 0.2 fire_B,R,P,L,P,2,W - 0.2 fire_B,R,P,L,P,2,C
        - 0.2 fire_B,R,P,L,P,2,I - 0.2 fire_B,R,P,L,P,2,A
        - 0.2 fire_B,R,P,L,P,2,L + 0.8 fire_B,R,P,L,P,2,N  = 0
 c2685: 0.9 fire_B,R,P,L,P,3,P - 0.1 fire_B,R,P,L,P,3,D
        - 0.1 fire_B,R,P,L,P,3,W - 0.1 fire_B,R,P,L,P,3,C
        - 0.1 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        - 0.1 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2686: - 0.1 fire_B,R,P,L,P,3,P + 0.9 fire_B,R,P,L,P,3,D
        - 0.1 fire_B,R,P,L,P,3,W - 0.1 fire_B,R,P,L,P,3,C
        - 0.1 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        - 0.1 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2687: - 0.1 fire_B,R,P,L,P,3,P - 0.1 fire_B,R,P,L,P,3,D
        + 0.9 fire_B,R,P,L,P,3,W - 0.1 fire_B,R,P,L,P,3,C
        - 0.1 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        - 0.1 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2688: - 0.1 fire_B,R,P,L,P,3,P - 0.1 fire_B,R,P,L,P,3,D
        - 0.1 fire_B,R,P,L,P,3,W + 0.9 fire_B,R,P,L,P,3,C
        - 0.1 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        - 0.1 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2689: - 0.1 fire_B,R,P,L,P,3,P - 0.1 fire_B,R,P,L,P,3,D
        - 0.1 fire_B,R,P,L,P,3,W - 0.1 fire_B,R,P,L,P,3,C
        + 0.9 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        - 0.1 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2690: - 0.2 fire_B,R,P,L,P,3,P - 0.2 fire_B,R,P,L,P,3,D
        - 0.2 fire_B,R,P,L,P,3,W - 0.2 fire_B,R,P,L,P,3,C
        - 0.2 fire_B,R,P,L,P,3,I + 0.8 fire_B,R,P,L,P,3,A
        - 0.2 fire_B,R,P,L,P,3,L - 0.2 fire_B,R,P,L,P,3,N  = 0
 c2691: - 0.1 fire_B,R,P,L,P,3,P - 0.1 fire_B,R,P,L,P,3,D
        - 0.1 fire_B,R,P,L,P,3,W - 0.1 fire_B,R,P,L,P,3,C
        - 0.1 fire_B,R,P,L,P,3,I - 0.1 fire_B,R,P,L,P,3,A
        + 0.9 fire_B,R,P,L,P,3,L - 0.1 fire_B,R,P,L,P,3,N  = 0
 c2692: - 0.2 fire_B,R,P,L,P,3,P - 0.2 fire_B,R,P,L,P,3,D
        - 0.2 fire_B,R,P,L,P,3,W - 0.2 fire_B,R,P,L,P,3,C
        - 0.2 fire_B,R,P,L,P,3,I - 0.2 fire_B,R,P,L,P,3,A
        - 0.2 fire_B,R,P,L,P,3,L + 0.8 fire_B,R,P,L,P,3,N  = 0
 c2693: 0.9 fire_B,R,P,L,P,4,P - 0.1 fire_B,R,P,L,P,4,D
        - 0.1 fire_B,R,P,L,P,4,W - 0.1 fire_B,R,P,L,P,4,C
        - 0.1 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        - 0.1 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2694: - 0.1 fire_B,R,P,L,P,4,P + 0.9 fire_B,R,P,L,P,4,D
        - 0.1 fire_B,R,P,L,P,4,W - 0.1 fire_B,R,P,L,P,4,C
        - 0.1 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        - 0.1 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2695: - 0.1 fire_B,R,P,L,P,4,P - 0.1 fire_B,R,P,L,P,4,D
        + 0.9 fire_B,R,P,L,P,4,W - 0.1 fire_B,R,P,L,P,4,C
        - 0.1 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        - 0.1 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2696: - 0.1 fire_B,R,P,L,P,4,P - 0.1 fire_B,R,P,L,P,4,D
        - 0.1 fire_B,R,P,L,P,4,W + 0.9 fire_B,R,P,L,P,4,C
        - 0.1 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        - 0.1 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2697: - 0.1 fire_B,R,P,L,P,4,P - 0.1 fire_B,R,P,L,P,4,D
        - 0.1 fire_B,R,P,L,P,4,W - 0.1 fire_B,R,P,L,P,4,C
        + 0.9 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        - 0.1 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2698: - 0.2 fire_B,R,P,L,P,4,P - 0.2 fire_B,R,P,L,P,4,D
        - 0.2 fire_B,R,P,L,P,4,W - 0.2 fire_B,R,P,L,P,4,C
        - 0.2 fire_B,R,P,L,P,4,I + 0.8 fire_B,R,P,L,P,4,A
        - 0.2 fire_B,R,P,L,P,4,L - 0.2 fire_B,R,P,L,P,4,N  = 0
 c2699: - 0.1 fire_B,R,P,L,P,4,P - 0.1 fire_B,R,P,L,P,4,D
        - 0.1 fire_B,R,P,L,P,4,W - 0.1 fire_B,R,P,L,P,4,C
        - 0.1 fire_B,R,P,L,P,4,I - 0.1 fire_B,R,P,L,P,4,A
        + 0.9 fire_B,R,P,L,P,4,L - 0.1 fire_B,R,P,L,P,4,N  = 0
 c2700: - 0.2 fire_B,R,P,L,P,4,P - 0.2 fire_B,R,P,L,P,4,D
        - 0.2 fire_B,R,P,L,P,4,W - 0.2 fire_B,R,P,L,P,4,C
        - 0.2 fire_B,R,P,L,P,4,I - 0.2 fire_B,R,P,L,P,4,A
        - 0.2 fire_B,R,P,L,P,4,L + 0.8 fire_B,R,P,L,P,4,N  = 0
 c2701: 0.9 fire_B,R,P,L,P,5,P - 0.1 fire_B,R,P,L,P,5,D
        - 0.1 fire_B,R,P,L,P,5,W - 0.1 fire_B,R,P,L,P,5,C
        - 0.1 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        - 0.1 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2702: - 0.1 fire_B,R,P,L,P,5,P + 0.9 fire_B,R,P,L,P,5,D
        - 0.1 fire_B,R,P,L,P,5,W - 0.1 fire_B,R,P,L,P,5,C
        - 0.1 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        - 0.1 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2703: - 0.1 fire_B,R,P,L,P,5,P - 0.1 fire_B,R,P,L,P,5,D
        + 0.9 fire_B,R,P,L,P,5,W - 0.1 fire_B,R,P,L,P,5,C
        - 0.1 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        - 0.1 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2704: - 0.1 fire_B,R,P,L,P,5,P - 0.1 fire_B,R,P,L,P,5,D
        - 0.1 fire_B,R,P,L,P,5,W + 0.9 fire_B,R,P,L,P,5,C
        - 0.1 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        - 0.1 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2705: - 0.1 fire_B,R,P,L,P,5,P - 0.1 fire_B,R,P,L,P,5,D
        - 0.1 fire_B,R,P,L,P,5,W - 0.1 fire_B,R,P,L,P,5,C
        + 0.9 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        - 0.1 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2706: - 0.2 fire_B,R,P,L,P,5,P - 0.2 fire_B,R,P,L,P,5,D
        - 0.2 fire_B,R,P,L,P,5,W - 0.2 fire_B,R,P,L,P,5,C
        - 0.2 fire_B,R,P,L,P,5,I + 0.8 fire_B,R,P,L,P,5,A
        - 0.2 fire_B,R,P,L,P,5,L - 0.2 fire_B,R,P,L,P,5,N  = 0
 c2707: - 0.1 fire_B,R,P,L,P,5,P - 0.1 fire_B,R,P,L,P,5,D
        - 0.1 fire_B,R,P,L,P,5,W - 0.1 fire_B,R,P,L,P,5,C
        - 0.1 fire_B,R,P,L,P,5,I - 0.1 fire_B,R,P,L,P,5,A
        + 0.9 fire_B,R,P,L,P,5,L - 0.1 fire_B,R,P,L,P,5,N  = 0
 c2708: - 0.2 fire_B,R,P,L,P,5,P - 0.2 fire_B,R,P,L,P,5,D
        - 0.2 fire_B,R,P,L,P,5,W - 0.2 fire_B,R,P,L,P,5,C
        - 0.2 fire_B,R,P,L,P,5,I - 0.2 fire_B,R,P,L,P,5,A
        - 0.2 fire_B,R,P,L,P,5,L + 0.8 fire_B,R,P,L,P,5,N  = 0
 c2709: 0.5 fire_B,R,P,L,D,1,P - 0.5 fire_B,R,P,L,D,1,D
        - 0.5 fire_B,R,P,L,D,1,W - 0.5 fire_B,R,P,L,D,1,C
        - 0.5 fire_B,R,P,L,D,1,I - 0.5 fire_B,R,P,L,D,1,A
        - 0.5 fire_B,R,P,L,D,1,L - 0.5 fire_B,R,P,L,D,1,N  = 0
 c2710: - 0.5 fire_B,R,P,L,D,1,P + 0.5 fire_B,R,P,L,D,1,D
        - 0.5 fire_B,R,P,L,D,1,W - 0.5 fire_B,R,P,L,D,1,C
        - 0.5 fire_B,R,P,L,D,1,I - 0.5 fire_B,R,P,L,D,1,A
        - 0.5 fire_B,R,P,L,D,1,L - 0.5 fire_B,R,P,L,D,1,N  = 0
 c2711: fire_B,R,P,L,D,1,W  = 0
 c2712: fire_B,R,P,L,D,1,C  = 0
 c2713: fire_B,R,P,L,D,1,I  = 0
 c2714: fire_B,R,P,L,D,1,A  = 0
 c2715: fire_B,R,P,L,D,1,L  = 0
 c2716: fire_B,R,P,L,D,1,N  = 0
 c2717: 0.5 fire_B,R,P,L,D,2,P - 0.5 fire_B,R,P,L,D,2,D
        - 0.5 fire_B,R,P,L,D,2,W - 0.5 fire_B,R,P,L,D,2,C
        - 0.5 fire_B,R,P,L,D,2,I - 0.5 fire_B,R,P,L,D,2,A
        - 0.5 fire_B,R,P,L,D,2,L - 0.5 fire_B,R,P,L,D,2,N  = 0
 c2718: - 0.5 fire_B,R,P,L,D,2,P + 0.5 fire_B,R,P,L,D,2,D
        - 0.5 fire_B,R,P,L,D,2,W - 0.5 fire_B,R,P,L,D,2,C
        - 0.5 fire_B,R,P,L,D,2,I - 0.5 fire_B,R,P,L,D,2,A
        - 0.5 fire_B,R,P,L,D,2,L - 0.5 fire_B,R,P,L,D,2,N  = 0
 c2719: fire_B,R,P,L,D,2,W  = 0
 c2720: fire_B,R,P,L,D,2,C  = 0
 c2721: fire_B,R,P,L,D,2,I  = 0
 c2722: fire_B,R,P,L,D,2,A  = 0
 c2723: fire_B,R,P,L,D,2,L  = 0
 c2724: fire_B,R,P,L,D,2,N  = 0
 c2725: 0.5 fire_B,R,P,L,D,3,P - 0.5 fire_B,R,P,L,D,3,D
        - 0.5 fire_B,R,P,L,D,3,W - 0.5 fire_B,R,P,L,D,3,C
        - 0.5 fire_B,R,P,L,D,3,I - 0.5 fire_B,R,P,L,D,3,A
        - 0.5 fire_B,R,P,L,D,3,L - 0.5 fire_B,R,P,L,D,3,N  = 0
 c2726: - 0.5 fire_B,R,P,L,D,3,P + 0.5 fire_B,R,P,L,D,3,D
        - 0.5 fire_B,R,P,L,D,3,W - 0.5 fire_B,R,P,L,D,3,C
        - 0.5 fire_B,R,P,L,D,3,I - 0.5 fire_B,R,P,L,D,3,A
        - 0.5 fire_B,R,P,L,D,3,L - 0.5 fire_B,R,P,L,D,3,N  = 0
 c2727: fire_B,R,P,L,D,3,W  = 0
 c2728: fire_B,R,P,L,D,3,C  = 0
 c2729: fire_B,R,P,L,D,3,I  = 0
 c2730: fire_B,R,P,L,D,3,A  = 0
 c2731: fire_B,R,P,L,D,3,L  = 0
 c2732: fire_B,R,P,L,D,3,N  = 0
 c2733: 0.5 fire_B,R,P,L,D,4,P - 0.5 fire_B,R,P,L,D,4,D
        - 0.5 fire_B,R,P,L,D,4,W - 0.5 fire_B,R,P,L,D,4,C
        - 0.5 fire_B,R,P,L,D,4,I - 0.5 fire_B,R,P,L,D,4,A
        - 0.5 fire_B,R,P,L,D,4,L - 0.5 fire_B,R,P,L,D,4,N  = 0
 c2734: - 0.5 fire_B,R,P,L,D,4,P + 0.5 fire_B,R,P,L,D,4,D
        - 0.5 fire_B,R,P,L,D,4,W - 0.5 fire_B,R,P,L,D,4,C
        - 0.5 fire_B,R,P,L,D,4,I - 0.5 fire_B,R,P,L,D,4,A
        - 0.5 fire_B,R,P,L,D,4,L - 0.5 fire_B,R,P,L,D,4,N  = 0
 c2735: fire_B,R,P,L,D,4,W  = 0
 c2736: fire_B,R,P,L,D,4,C  = 0
 c2737: fire_B,R,P,L,D,4,I  = 0
 c2738: fire_B,R,P,L,D,4,A  = 0
 c2739: fire_B,R,P,L,D,4,L  = 0
 c2740: fire_B,R,P,L,D,4,N  = 0
 c2741: 0.5 fire_B,R,P,L,D,5,P - 0.5 fire_B,R,P,L,D,5,D
        - 0.5 fire_B,R,P,L,D,5,W - 0.5 fire_B,R,P,L,D,5,C
        - 0.5 fire_B,R,P,L,D,5,I - 0.5 fire_B,R,P,L,D,5,A
        - 0.5 fire_B,R,P,L,D,5,L - 0.5 fire_B,R,P,L,D,5,N  = 0
 c2742: - 0.5 fire_B,R,P,L,D,5,P + 0.5 fire_B,R,P,L,D,5,D
        - 0.5 fire_B,R,P,L,D,5,W - 0.5 fire_B,R,P,L,D,5,C
        - 0.5 fire_B,R,P,L,D,5,I - 0.5 fire_B,R,P,L,D,5,A
        - 0.5 fire_B,R,P,L,D,5,L - 0.5 fire_B,R,P,L,D,5,N  = 0
 c2743: fire_B,R,P,L,D,5,W  = 0
 c2744: fire_B,R,P,L,D,5,C  = 0
 c2745: fire_B,R,P,L,D,5,I  = 0
 c2746: fire_B,R,P,L,D,5,A  = 0
 c2747: fire_B,R,P,L,D,5,L  = 0
 c2748: fire_B,R,P,L,D,5,N  = 0
 c2749: fire_B,R,P,L,W,1,P  = 0
 c2750: fire_B,R,P,L,W,1,D  = 0
 c2751: - fire_B,R,P,L,W,1,P - fire_B,R,P,L,W,1,D - fire_B,R,P,L,W,1,C
        - fire_B,R,P,L,W,1,I - fire_B,R,P,L,W,1,A - fire_B,R,P,L,W,1,L
        - fire_B,R,P,L,W,1,N  = 0
 c2752: fire_B,R,P,L,W,1,C  = 0
 c2753: fire_B,R,P,L,W,1,I  = 0
 c2754: fire_B,R,P,L,W,1,A  = 0
 c2755: fire_B,R,P,L,W,1,L  = 0
 c2756: fire_B,R,P,L,W,1,N  = 0
 c2757: fire_B,R,P,L,W,2,P  = 0
 c2758: fire_B,R,P,L,W,2,D  = 0
 c2759: - fire_B,R,P,L,W,2,P - fire_B,R,P,L,W,2,D - fire_B,R,P,L,W,2,C
        - fire_B,R,P,L,W,2,I - fire_B,R,P,L,W,2,A - fire_B,R,P,L,W,2,L
        - fire_B,R,P,L,W,2,N  = 0
 c2760: fire_B,R,P,L,W,2,C  = 0
 c2761: fire_B,R,P,L,W,2,I  = 0
 c2762: fire_B,R,P,L,W,2,A  = 0
 c2763: fire_B,R,P,L,W,2,L  = 0
 c2764: fire_B,R,P,L,W,2,N  = 0
 c2765: fire_B,R,P,L,W,3,P  = 0
 c2766: fire_B,R,P,L,W,3,D  = 0
 c2767: - fire_B,R,P,L,W,3,P - fire_B,R,P,L,W,3,D - fire_B,R,P,L,W,3,C
        - fire_B,R,P,L,W,3,I - fire_B,R,P,L,W,3,A - fire_B,R,P,L,W,3,L
        - fire_B,R,P,L,W,3,N  = 0
 c2768: fire_B,R,P,L,W,3,C  = 0
 c2769: fire_B,R,P,L,W,3,I  = 0
 c2770: fire_B,R,P,L,W,3,A  = 0
 c2771: fire_B,R,P,L,W,3,L  = 0
 c2772: fire_B,R,P,L,W,3,N  = 0
 c2773: fire_B,R,P,L,W,4,P  = 0
 c2774: fire_B,R,P,L,W,4,D  = 0
 c2775: - fire_B,R,P,L,W,4,P - fire_B,R,P,L,W,4,D - fire_B,R,P,L,W,4,C
        - fire_B,R,P,L,W,4,I - fire_B,R,P,L,W,4,A - fire_B,R,P,L,W,4,L
        - fire_B,R,P,L,W,4,N  = 0
 c2776: fire_B,R,P,L,W,4,C  = 0
 c2777: fire_B,R,P,L,W,4,I  = 0
 c2778: fire_B,R,P,L,W,4,A  = 0
 c2779: fire_B,R,P,L,W,4,L  = 0
 c2780: fire_B,R,P,L,W,4,N  = 0
 c2781: fire_B,R,P,L,W,5,P  = 0
 c2782: fire_B,R,P,L,W,5,D  = 0
 c2783: - fire_B,R,P,L,W,5,P - fire_B,R,P,L,W,5,D - fire_B,R,P,L,W,5,C
        - fire_B,R,P,L,W,5,I - fire_B,R,P,L,W,5,A - fire_B,R,P,L,W,5,L
        - fire_B,R,P,L,W,5,N  = 0
 c2784: fire_B,R,P,L,W,5,C  = 0
 c2785: fire_B,R,P,L,W,5,I  = 0
 c2786: fire_B,R,P,L,W,5,A  = 0
 c2787: fire_B,R,P,L,W,5,L  = 0
 c2788: fire_B,R,P,L,W,5,N  = 0
 c2789: fire_B,R,P,L,C,1,P  = 0
 c2790: fire_B,R,P,L,C,1,D  = 0
 c2791: fire_B,R,P,L,C,1,W  = 0
 c2792: - fire_B,R,P,L,C,1,P - fire_B,R,P,L,C,1,D - fire_B,R,P,L,C,1,W
        - fire_B,R,P,L,C,1,I - fire_B,R,P,L,C,1,A - fire_B,R,P,L,C,1,L
        - fire_B,R,P,L,C,1,N  = 0
 c2793: fire_B,R,P,L,C,1,I  = 0
 c2794: fire_B,R,P,L,C,1,A  = 0
 c2795: fire_B,R,P,L,C,1,L  = 0
 c2796: fire_B,R,P,L,C,1,N  = 0
 c2797: fire_B,R,P,L,C,2,P  = 0
 c2798: fire_B,R,P,L,C,2,D  = 0
 c2799: fire_B,R,P,L,C,2,W  = 0
 c2800: - fire_B,R,P,L,C,2,P - fire_B,R,P,L,C,2,D - fire_B,R,P,L,C,2,W
        - fire_B,R,P,L,C,2,I - fire_B,R,P,L,C,2,A - fire_B,R,P,L,C,2,L
        - fire_B,R,P,L,C,2,N  = 0
 c2801: fire_B,R,P,L,C,2,I  = 0
 c2802: fire_B,R,P,L,C,2,A  = 0
 c2803: fire_B,R,P,L,C,2,L  = 0
 c2804: fire_B,R,P,L,C,2,N  = 0
 c2805: fire_B,R,P,L,C,3,P  = 0
 c2806: fire_B,R,P,L,C,3,D  = 0
 c2807: fire_B,R,P,L,C,3,W  = 0
 c2808: - fire_B,R,P,L,C,3,P - fire_B,R,P,L,C,3,D - fire_B,R,P,L,C,3,W
        - fire_B,R,P,L,C,3,I - fire_B,R,P,L,C,3,A - fire_B,R,P,L,C,3,L
        - fire_B,R,P,L,C,3,N  = 0
 c2809: fire_B,R,P,L,C,3,I  = 0
 c2810: fire_B,R,P,L,C,3,A  = 0
 c2811: fire_B,R,P,L,C,3,L  = 0
 c2812: fire_B,R,P,L,C,3,N  = 0
 c2813: fire_B,R,P,L,C,4,P  = 0
 c2814: fire_B,R,P,L,C,4,D  = 0
 c2815: fire_B,R,P,L,C,4,W  = 0
 c2816: - fire_B,R,P,L,C,4,P - fire_B,R,P,L,C,4,D - fire_B,R,P,L,C,4,W
        - fire_B,R,P,L,C,4,I - fire_B,R,P,L,C,4,A - fire_B,R,P,L,C,4,L
        - fire_B,R,P,L,C,4,N  = 0
 c2817: fire_B,R,P,L,C,4,I  = 0
 c2818: fire_B,R,P,L,C,4,A  = 0
 c2819: fire_B,R,P,L,C,4,L  = 0
 c2820: fire_B,R,P,L,C,4,N  = 0
 c2821: fire_B,R,P,L,C,5,P  = 0
 c2822: fire_B,R,P,L,C,5,D  = 0
 c2823: fire_B,R,P,L,C,5,W  = 0
 c2824: - fire_B,R,P,L,C,5,P - fire_B,R,P,L,C,5,D - fire_B,R,P,L,C,5,W
        - fire_B,R,P,L,C,5,I - fire_B,R,P,L,C,5,A - fire_B,R,P,L,C,5,L
        - fire_B,R,P,L,C,5,N  = 0
 c2825: fire_B,R,P,L,C,5,I  = 0
 c2826: fire_B,R,P,L,C,5,A  = 0
 c2827: fire_B,R,P,L,C,5,L  = 0
 c2828: fire_B,R,P,L,C,5,N  = 0
 c2829: fire_B,R,P,L,I,1,P  = 0
 c2830: fire_B,R,P,L,I,1,D  = 0
 c2831: fire_B,R,P,L,I,1,W  = 0
 c2832: fire_B,R,P,L,I,1,C  = 0
 c2833: - fire_B,R,P,L,I,1,P - fire_B,R,P,L,I,1,D - fire_B,R,P,L,I,1,W
        - fire_B,R,P,L,I,1,C - fire_B,R,P,L,I,1,A - fire_B,R,P,L,I,1,L
        - fire_B,R,P,L,I,1,N  = 0
 c2834: fire_B,R,P,L,I,1,A  = 0
 c2835: fire_B,R,P,L,I,1,L  = 0
 c2836: fire_B,R,P,L,I,1,N  = 0
 c2837: fire_B,R,P,L,I,2,P  = 0
 c2838: fire_B,R,P,L,I,2,D  = 0
 c2839: fire_B,R,P,L,I,2,W  = 0
 c2840: fire_B,R,P,L,I,2,C  = 0
 c2841: - fire_B,R,P,L,I,2,P - fire_B,R,P,L,I,2,D - fire_B,R,P,L,I,2,W
        - fire_B,R,P,L,I,2,C - fire_B,R,P,L,I,2,A - fire_B,R,P,L,I,2,L
        - fire_B,R,P,L,I,2,N  = 0
 c2842: fire_B,R,P,L,I,2,A  = 0
 c2843: fire_B,R,P,L,I,2,L  = 0
 c2844: fire_B,R,P,L,I,2,N  = 0
 c2845: fire_B,R,P,L,I,3,P  = 0
 c2846: fire_B,R,P,L,I,3,D  = 0
 c2847: fire_B,R,P,L,I,3,W  = 0
 c2848: fire_B,R,P,L,I,3,C  = 0
 c2849: - fire_B,R,P,L,I,3,P - fire_B,R,P,L,I,3,D - fire_B,R,P,L,I,3,W
        - fire_B,R,P,L,I,3,C - fire_B,R,P,L,I,3,A - fire_B,R,P,L,I,3,L
        - fire_B,R,P,L,I,3,N  = 0
 c2850: fire_B,R,P,L,I,3,A  = 0
 c2851: fire_B,R,P,L,I,3,L  = 0
 c2852: fire_B,R,P,L,I,3,N  = 0
 c2853: fire_B,R,P,L,I,4,P  = 0
 c2854: fire_B,R,P,L,I,4,D  = 0
 c2855: fire_B,R,P,L,I,4,W  = 0
 c2856: fire_B,R,P,L,I,4,C  = 0
 c2857: - fire_B,R,P,L,I,4,P - fire_B,R,P,L,I,4,D - fire_B,R,P,L,I,4,W
        - fire_B,R,P,L,I,4,C - fire_B,R,P,L,I,4,A - fire_B,R,P,L,I,4,L
        - fire_B,R,P,L,I,4,N  = 0
 c2858: fire_B,R,P,L,I,4,A  = 0
 c2859: fire_B,R,P,L,I,4,L  = 0
 c2860: fire_B,R,P,L,I,4,N  = 0
 c2861: fire_B,R,P,L,I,5,P  = 0
 c2862: fire_B,R,P,L,I,5,D  = 0
 c2863: fire_B,R,P,L,I,5,W  = 0
 c2864: fire_B,R,P,L,I,5,C  = 0
 c2865: - fire_B,R,P,L,I,5,P - fire_B,R,P,L,I,5,D - fire_B,R,P,L,I,5,W
        - fire_B,R,P,L,I,5,C - fire_B,R,P,L,I,5,A - fire_B,R,P,L,I,5,L
        - fire_B,R,P,L,I,5,N  = 0
 c2866: fire_B,R,P,L,I,5,A  = 0
 c2867: fire_B,R,P,L,I,5,L  = 0
 c2868: fire_B,R,P,L,I,5,N  = 0
 c2869: fire_B,R,P,L,A,1,P  = 0
 c2870: fire_B,R,P,L,A,1,D  = 0
 c2871: fire_B,R,P,L,A,1,W  = 0
 c2872: fire_B,R,P,L,A,1,C  = 0
 c2873: fire_B,R,P,L,A,1,I  = 0
 c2874: - fire_B,R,P,L,A,1,P - fire_B,R,P,L,A,1,D - fire_B,R,P,L,A,1,W
        - fire_B,R,P,L,A,1,C - fire_B,R,P,L,A,1,I - fire_B,R,P,L,A,1,L
        - fire_B,R,P,L,A,1,N  = 0
 c2875: fire_B,R,P,L,A,1,L  = 0
 c2876: fire_B,R,P,L,A,1,N  = 0
 c2877: fire_B,R,P,L,A,2,P  = 0
 c2878: fire_B,R,P,L,A,2,D  = 0
 c2879: fire_B,R,P,L,A,2,W  = 0
 c2880: fire_B,R,P,L,A,2,C  = 0
 c2881: fire_B,R,P,L,A,2,I  = 0
 c2882: - fire_B,R,P,L,A,2,P - fire_B,R,P,L,A,2,D - fire_B,R,P,L,A,2,W
        - fire_B,R,P,L,A,2,C - fire_B,R,P,L,A,2,I - fire_B,R,P,L,A,2,L
        - fire_B,R,P,L,A,2,N  = 0
 c2883: fire_B,R,P,L,A,2,L  = 0
 c2884: fire_B,R,P,L,A,2,N  = 0
 c2885: fire_B,R,P,L,A,3,P  = 0
 c2886: fire_B,R,P,L,A,3,D  = 0
 c2887: fire_B,R,P,L,A,3,W  = 0
 c2888: fire_B,R,P,L,A,3,C  = 0
 c2889: fire_B,R,P,L,A,3,I  = 0
 c2890: - fire_B,R,P,L,A,3,P - fire_B,R,P,L,A,3,D - fire_B,R,P,L,A,3,W
        - fire_B,R,P,L,A,3,C - fire_B,R,P,L,A,3,I - fire_B,R,P,L,A,3,L
        - fire_B,R,P,L,A,3,N  = 0
 c2891: fire_B,R,P,L,A,3,L  = 0
 c2892: fire_B,R,P,L,A,3,N  = 0
 c2893: fire_B,R,P,L,A,4,P  = 0
 c2894: fire_B,R,P,L,A,4,D  = 0
 c2895: fire_B,R,P,L,A,4,W  = 0
 c2896: fire_B,R,P,L,A,4,C  = 0
 c2897: fire_B,R,P,L,A,4,I  = 0
 c2898: - fire_B,R,P,L,A,4,P - fire_B,R,P,L,A,4,D - fire_B,R,P,L,A,4,W
        - fire_B,R,P,L,A,4,C - fire_B,R,P,L,A,4,I - fire_B,R,P,L,A,4,L
        - fire_B,R,P,L,A,4,N  = 0
 c2899: fire_B,R,P,L,A,4,L  = 0
 c2900: fire_B,R,P,L,A,4,N  = 0
 c2901: fire_B,R,P,L,A,5,P  = 0
 c2902: fire_B,R,P,L,A,5,D  = 0
 c2903: fire_B,R,P,L,A,5,W  = 0
 c2904: fire_B,R,P,L,A,5,C  = 0
 c2905: fire_B,R,P,L,A,5,I  = 0
 c2906: - fire_B,R,P,L,A,5,P - fire_B,R,P,L,A,5,D - fire_B,R,P,L,A,5,W
        - fire_B,R,P,L,A,5,C - fire_B,R,P,L,A,5,I - fire_B,R,P,L,A,5,L
        - fire_B,R,P,L,A,5,N  = 0
 c2907: fire_B,R,P,L,A,5,L  = 0
 c2908: fire_B,R,P,L,A,5,N  = 0
 c2909: fire_B,R,P,L,L,1,P  = 0
 c2910: fire_B,R,P,L,L,1,D  = 0
 c2911: fire_B,R,P,L,L,1,W  = 0
 c2912: fire_B,R,P,L,L,1,C  = 0
 c2913: fire_B,R,P,L,L,1,I  = 0
 c2914: fire_B,R,P,L,L,1,A  = 0
 c2915: - fire_B,R,P,L,L,1,P - fire_B,R,P,L,L,1,D - fire_B,R,P,L,L,1,W
        - fire_B,R,P,L,L,1,C - fire_B,R,P,L,L,1,I - fire_B,R,P,L,L,1,A
        - fire_B,R,P,L,L,1,N  = 0
 c2916: fire_B,R,P,L,L,1,N  = 0
 c2917: fire_B,R,P,L,L,2,P  = 0
 c2918: fire_B,R,P,L,L,2,D  = 0
 c2919: fire_B,R,P,L,L,2,W  = 0
 c2920: fire_B,R,P,L,L,2,C  = 0
 c2921: fire_B,R,P,L,L,2,I  = 0
 c2922: fire_B,R,P,L,L,2,A  = 0
 c2923: - fire_B,R,P,L,L,2,P - fire_B,R,P,L,L,2,D - fire_B,R,P,L,L,2,W
        - fire_B,R,P,L,L,2,C - fire_B,R,P,L,L,2,I - fire_B,R,P,L,L,2,A
        - fire_B,R,P,L,L,2,N  = 0
 c2924: fire_B,R,P,L,L,2,N  = 0
 c2925: fire_B,R,P,L,L,3,P  = 0
 c2926: fire_B,R,P,L,L,3,D  = 0
 c2927: fire_B,R,P,L,L,3,W  = 0
 c2928: fire_B,R,P,L,L,3,C  = 0
 c2929: fire_B,R,P,L,L,3,I  = 0
 c2930: fire_B,R,P,L,L,3,A  = 0
 c2931: - fire_B,R,P,L,L,3,P - fire_B,R,P,L,L,3,D - fire_B,R,P,L,L,3,W
        - fire_B,R,P,L,L,3,C - fire_B,R,P,L,L,3,I - fire_B,R,P,L,L,3,A
        - fire_B,R,P,L,L,3,N  = 0
 c2932: fire_B,R,P,L,L,3,N  = 0
 c2933: fire_B,R,P,L,L,4,P  = 0
 c2934: fire_B,R,P,L,L,4,D  = 0
 c2935: fire_B,R,P,L,L,4,W  = 0
 c2936: fire_B,R,P,L,L,4,C  = 0
 c2937: fire_B,R,P,L,L,4,I  = 0
 c2938: fire_B,R,P,L,L,4,A  = 0
 c2939: - fire_B,R,P,L,L,4,P - fire_B,R,P,L,L,4,D - fire_B,R,P,L,L,4,W
        - fire_B,R,P,L,L,4,C - fire_B,R,P,L,L,4,I - fire_B,R,P,L,L,4,A
        - fire_B,R,P,L,L,4,N  = 0
 c2940: fire_B,R,P,L,L,4,N  = 0
 c2941: fire_B,R,P,L,L,5,P  = 0
 c2942: fire_B,R,P,L,L,5,D  = 0
 c2943: fire_B,R,P,L,L,5,W  = 0
 c2944: fire_B,R,P,L,L,5,C  = 0
 c2945: fire_B,R,P,L,L,5,I  = 0
 c2946: fire_B,R,P,L,L,5,A  = 0
 c2947: - fire_B,R,P,L,L,5,P - fire_B,R,P,L,L,5,D - fire_B,R,P,L,L,5,W
        - fire_B,R,P,L,L,5,C - fire_B,R,P,L,L,5,I - fire_B,R,P,L,L,5,A
        - fire_B,R,P,L,L,5,N  = 0
 c2948: fire_B,R,P,L,L,5,N  = 0
 c2949: fire_B,R,P,L,N,1,P  = 0
 c2950: fire_B,R,P,L,N,1,D  = 0
 c2951: fire_B,R,P,L,N,1,W  = 0
 c2952: fire_B,R,P,L,N,1,C  = 0
 c2953: fire_B,R,P,L,N,1,I  = 0
 c2954: fire_B,R,P,L,N,1,A  = 0
 c2955: fire_B,R,P,L,N,1,L  = 0
 c2956: - fire_B,R,P,L,N,1,P - fire_B,R,P,L,N,1,D - fire_B,R,P,L,N,1,W
        - fire_B,R,P,L,N,1,C - fire_B,R,P,L,N,1,I - fire_B,R,P,L,N,1,A
        - fire_B,R,P,L,N,1,L  = 0
 c2957: fire_B,R,P,L,N,2,P  = 0
 c2958: fire_B,R,P,L,N,2,D  = 0
 c2959: fire_B,R,P,L,N,2,W  = 0
 c2960: fire_B,R,P,L,N,2,C  = 0
 c2961: fire_B,R,P,L,N,2,I  = 0
 c2962: fire_B,R,P,L,N,2,A  = 0
 c2963: fire_B,R,P,L,N,2,L  = 0
 c2964: - fire_B,R,P,L,N,2,P - fire_B,R,P,L,N,2,D - fire_B,R,P,L,N,2,W
        - fire_B,R,P,L,N,2,C - fire_B,R,P,L,N,2,I - fire_B,R,P,L,N,2,A
        - fire_B,R,P,L,N,2,L  = 0
 c2965: fire_B,R,P,L,N,3,P  = 0
 c2966: fire_B,R,P,L,N,3,D  = 0
 c2967: fire_B,R,P,L,N,3,W  = 0
 c2968: fire_B,R,P,L,N,3,C  = 0
 c2969: fire_B,R,P,L,N,3,I  = 0
 c2970: fire_B,R,P,L,N,3,A  = 0
 c2971: fire_B,R,P,L,N,3,L  = 0
 c2972: - fire_B,R,P,L,N,3,P - fire_B,R,P,L,N,3,D - fire_B,R,P,L,N,3,W
        - fire_B,R,P,L,N,3,C - fire_B,R,P,L,N,3,I - fire_B,R,P,L,N,3,A
        - fire_B,R,P,L,N,3,L  = 0
 c2973: fire_B,R,P,L,N,4,P  = 0
 c2974: fire_B,R,P,L,N,4,D  = 0
 c2975: fire_B,R,P,L,N,4,W  = 0
 c2976: fire_B,R,P,L,N,4,C  = 0
 c2977: fire_B,R,P,L,N,4,I  = 0
 c2978: fire_B,R,P,L,N,4,A  = 0
 c2979: fire_B,R,P,L,N,4,L  = 0
 c2980: - fire_B,R,P,L,N,4,P - fire_B,R,P,L,N,4,D - fire_B,R,P,L,N,4,W
        - fire_B,R,P,L,N,4,C - fire_B,R,P,L,N,4,I - fire_B,R,P,L,N,4,A
        - fire_B,R,P,L,N,4,L  = 0
 c2981: fire_B,R,P,L,N,5,P  = 0
 c2982: fire_B,R,P,L,N,5,D  = 0
 c2983: fire_B,R,P,L,N,5,W  = 0
 c2984: fire_B,R,P,L,N,5,C  = 0
 c2985: fire_B,R,P,L,N,5,I  = 0
 c2986: fire_B,R,P,L,N,5,A  = 0
 c2987: fire_B,R,P,L,N,5,L  = 0
 c2988: - fire_B,R,P,L,N,5,P - fire_B,R,P,L,N,5,D - fire_B,R,P,L,N,5,W
        - fire_B,R,P,L,N,5,C - fire_B,R,P,L,N,5,I - fire_B,R,P,L,N,5,A
        - fire_B,R,P,L,N,5,L  = 0
 c2989: 0.9 fire_B,R,P,C,P,1,P - 0.1 fire_B,R,P,C,P,1,D
        - 0.1 fire_B,R,P,C,P,1,W - 0.1 fire_B,R,P,C,P,1,C
        - 0.1 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        - 0.1 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2990: - 0.1 fire_B,R,P,C,P,1,P + 0.9 fire_B,R,P,C,P,1,D
        - 0.1 fire_B,R,P,C,P,1,W - 0.1 fire_B,R,P,C,P,1,C
        - 0.1 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        - 0.1 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2991: - 0.1 fire_B,R,P,C,P,1,P - 0.1 fire_B,R,P,C,P,1,D
        + 0.9 fire_B,R,P,C,P,1,W - 0.1 fire_B,R,P,C,P,1,C
        - 0.1 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        - 0.1 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2992: - 0.1 fire_B,R,P,C,P,1,P - 0.1 fire_B,R,P,C,P,1,D
        - 0.1 fire_B,R,P,C,P,1,W + 0.9 fire_B,R,P,C,P,1,C
        - 0.1 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        - 0.1 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2993: - 0.1 fire_B,R,P,C,P,1,P - 0.1 fire_B,R,P,C,P,1,D
        - 0.1 fire_B,R,P,C,P,1,W - 0.1 fire_B,R,P,C,P,1,C
        + 0.9 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        - 0.1 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2994: - 0.2 fire_B,R,P,C,P,1,P - 0.2 fire_B,R,P,C,P,1,D
        - 0.2 fire_B,R,P,C,P,1,W - 0.2 fire_B,R,P,C,P,1,C
        - 0.2 fire_B,R,P,C,P,1,I + 0.8 fire_B,R,P,C,P,1,A
        - 0.2 fire_B,R,P,C,P,1,L - 0.2 fire_B,R,P,C,P,1,N  = 0
 c2995: - 0.1 fire_B,R,P,C,P,1,P - 0.1 fire_B,R,P,C,P,1,D
        - 0.1 fire_B,R,P,C,P,1,W - 0.1 fire_B,R,P,C,P,1,C
        - 0.1 fire_B,R,P,C,P,1,I - 0.1 fire_B,R,P,C,P,1,A
        + 0.9 fire_B,R,P,C,P,1,L - 0.1 fire_B,R,P,C,P,1,N  = 0
 c2996: - 0.2 fire_B,R,P,C,P,1,P - 0.2 fire_B,R,P,C,P,1,D
        - 0.2 fire_B,R,P,C,P,1,W - 0.2 fire_B,R,P,C,P,1,C
        - 0.2 fire_B,R,P,C,P,1,I - 0.2 fire_B,R,P,C,P,1,A
        - 0.2 fire_B,R,P,C,P,1,L + 0.8 fire_B,R,P,C,P,1,N  = 0
 c2997: 0.9 fire_B,R,P,C,P,2,P - 0.1 fire_B,R,P,C,P,2,D
        - 0.1 fire_B,R,P,C,P,2,W - 0.1 fire_B,R,P,C,P,2,C
        - 0.1 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        - 0.1 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c2998: - 0.1 fire_B,R,P,C,P,2,P + 0.9 fire_B,R,P,C,P,2,D
        - 0.1 fire_B,R,P,C,P,2,W - 0.1 fire_B,R,P,C,P,2,C
        - 0.1 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        - 0.1 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c2999: - 0.1 fire_B,R,P,C,P,2,P - 0.1 fire_B,R,P,C,P,2,D
        + 0.9 fire_B,R,P,C,P,2,W - 0.1 fire_B,R,P,C,P,2,C
        - 0.1 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        - 0.1 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c3000: - 0.1 fire_B,R,P,C,P,2,P - 0.1 fire_B,R,P,C,P,2,D
        - 0.1 fire_B,R,P,C,P,2,W + 0.9 fire_B,R,P,C,P,2,C
        - 0.1 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        - 0.1 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c3001: - 0.1 fire_B,R,P,C,P,2,P - 0.1 fire_B,R,P,C,P,2,D
        - 0.1 fire_B,R,P,C,P,2,W - 0.1 fire_B,R,P,C,P,2,C
        + 0.9 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        - 0.1 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c3002: - 0.2 fire_B,R,P,C,P,2,P - 0.2 fire_B,R,P,C,P,2,D
        - 0.2 fire_B,R,P,C,P,2,W - 0.2 fire_B,R,P,C,P,2,C
        - 0.2 fire_B,R,P,C,P,2,I + 0.8 fire_B,R,P,C,P,2,A
        - 0.2 fire_B,R,P,C,P,2,L - 0.2 fire_B,R,P,C,P,2,N  = 0
 c3003: - 0.1 fire_B,R,P,C,P,2,P - 0.1 fire_B,R,P,C,P,2,D
        - 0.1 fire_B,R,P,C,P,2,W - 0.1 fire_B,R,P,C,P,2,C
        - 0.1 fire_B,R,P,C,P,2,I - 0.1 fire_B,R,P,C,P,2,A
        + 0.9 fire_B,R,P,C,P,2,L - 0.1 fire_B,R,P,C,P,2,N  = 0
 c3004: - 0.2 fire_B,R,P,C,P,2,P - 0.2 fire_B,R,P,C,P,2,D
        - 0.2 fire_B,R,P,C,P,2,W - 0.2 fire_B,R,P,C,P,2,C
        - 0.2 fire_B,R,P,C,P,2,I - 0.2 fire_B,R,P,C,P,2,A
        - 0.2 fire_B,R,P,C,P,2,L + 0.8 fire_B,R,P,C,P,2,N  = 0
 c3005: 0.9 fire_B,R,P,C,P,3,P - 0.1 fire_B,R,P,C,P,3,D
        - 0.1 fire_B,R,P,C,P,3,W - 0.1 fire_B,R,P,C,P,3,C
        - 0.1 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        - 0.1 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3006: - 0.1 fire_B,R,P,C,P,3,P + 0.9 fire_B,R,P,C,P,3,D
        - 0.1 fire_B,R,P,C,P,3,W - 0.1 fire_B,R,P,C,P,3,C
        - 0.1 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        - 0.1 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3007: - 0.1 fire_B,R,P,C,P,3,P - 0.1 fire_B,R,P,C,P,3,D
        + 0.9 fire_B,R,P,C,P,3,W - 0.1 fire_B,R,P,C,P,3,C
        - 0.1 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        - 0.1 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3008: - 0.1 fire_B,R,P,C,P,3,P - 0.1 fire_B,R,P,C,P,3,D
        - 0.1 fire_B,R,P,C,P,3,W + 0.9 fire_B,R,P,C,P,3,C
        - 0.1 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        - 0.1 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3009: - 0.1 fire_B,R,P,C,P,3,P - 0.1 fire_B,R,P,C,P,3,D
        - 0.1 fire_B,R,P,C,P,3,W - 0.1 fire_B,R,P,C,P,3,C
        + 0.9 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        - 0.1 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3010: - 0.2 fire_B,R,P,C,P,3,P - 0.2 fire_B,R,P,C,P,3,D
        - 0.2 fire_B,R,P,C,P,3,W - 0.2 fire_B,R,P,C,P,3,C
        - 0.2 fire_B,R,P,C,P,3,I + 0.8 fire_B,R,P,C,P,3,A
        - 0.2 fire_B,R,P,C,P,3,L - 0.2 fire_B,R,P,C,P,3,N  = 0
 c3011: - 0.1 fire_B,R,P,C,P,3,P - 0.1 fire_B,R,P,C,P,3,D
        - 0.1 fire_B,R,P,C,P,3,W - 0.1 fire_B,R,P,C,P,3,C
        - 0.1 fire_B,R,P,C,P,3,I - 0.1 fire_B,R,P,C,P,3,A
        + 0.9 fire_B,R,P,C,P,3,L - 0.1 fire_B,R,P,C,P,3,N  = 0
 c3012: - 0.2 fire_B,R,P,C,P,3,P - 0.2 fire_B,R,P,C,P,3,D
        - 0.2 fire_B,R,P,C,P,3,W - 0.2 fire_B,R,P,C,P,3,C
        - 0.2 fire_B,R,P,C,P,3,I - 0.2 fire_B,R,P,C,P,3,A
        - 0.2 fire_B,R,P,C,P,3,L + 0.8 fire_B,R,P,C,P,3,N  = 0
 c3013: 0.9 fire_B,R,P,C,P,4,P - 0.1 fire_B,R,P,C,P,4,D
        - 0.1 fire_B,R,P,C,P,4,W - 0.1 fire_B,R,P,C,P,4,C
        - 0.1 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        - 0.1 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3014: - 0.1 fire_B,R,P,C,P,4,P + 0.9 fire_B,R,P,C,P,4,D
        - 0.1 fire_B,R,P,C,P,4,W - 0.1 fire_B,R,P,C,P,4,C
        - 0.1 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        - 0.1 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3015: - 0.1 fire_B,R,P,C,P,4,P - 0.1 fire_B,R,P,C,P,4,D
        + 0.9 fire_B,R,P,C,P,4,W - 0.1 fire_B,R,P,C,P,4,C
        - 0.1 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        - 0.1 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3016: - 0.1 fire_B,R,P,C,P,4,P - 0.1 fire_B,R,P,C,P,4,D
        - 0.1 fire_B,R,P,C,P,4,W + 0.9 fire_B,R,P,C,P,4,C
        - 0.1 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        - 0.1 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3017: - 0.1 fire_B,R,P,C,P,4,P - 0.1 fire_B,R,P,C,P,4,D
        - 0.1 fire_B,R,P,C,P,4,W - 0.1 fire_B,R,P,C,P,4,C
        + 0.9 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        - 0.1 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3018: - 0.2 fire_B,R,P,C,P,4,P - 0.2 fire_B,R,P,C,P,4,D
        - 0.2 fire_B,R,P,C,P,4,W - 0.2 fire_B,R,P,C,P,4,C
        - 0.2 fire_B,R,P,C,P,4,I + 0.8 fire_B,R,P,C,P,4,A
        - 0.2 fire_B,R,P,C,P,4,L - 0.2 fire_B,R,P,C,P,4,N  = 0
 c3019: - 0.1 fire_B,R,P,C,P,4,P - 0.1 fire_B,R,P,C,P,4,D
        - 0.1 fire_B,R,P,C,P,4,W - 0.1 fire_B,R,P,C,P,4,C
        - 0.1 fire_B,R,P,C,P,4,I - 0.1 fire_B,R,P,C,P,4,A
        + 0.9 fire_B,R,P,C,P,4,L - 0.1 fire_B,R,P,C,P,4,N  = 0
 c3020: - 0.2 fire_B,R,P,C,P,4,P - 0.2 fire_B,R,P,C,P,4,D
        - 0.2 fire_B,R,P,C,P,4,W - 0.2 fire_B,R,P,C,P,4,C
        - 0.2 fire_B,R,P,C,P,4,I - 0.2 fire_B,R,P,C,P,4,A
        - 0.2 fire_B,R,P,C,P,4,L + 0.8 fire_B,R,P,C,P,4,N  = 0
 c3021: 0.9 fire_B,R,P,C,P,5,P - 0.1 fire_B,R,P,C,P,5,D
        - 0.1 fire_B,R,P,C,P,5,W - 0.1 fire_B,R,P,C,P,5,C
        - 0.1 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        - 0.1 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3022: - 0.1 fire_B,R,P,C,P,5,P + 0.9 fire_B,R,P,C,P,5,D
        - 0.1 fire_B,R,P,C,P,5,W - 0.1 fire_B,R,P,C,P,5,C
        - 0.1 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        - 0.1 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3023: - 0.1 fire_B,R,P,C,P,5,P - 0.1 fire_B,R,P,C,P,5,D
        + 0.9 fire_B,R,P,C,P,5,W - 0.1 fire_B,R,P,C,P,5,C
        - 0.1 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        - 0.1 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3024: - 0.1 fire_B,R,P,C,P,5,P - 0.1 fire_B,R,P,C,P,5,D
        - 0.1 fire_B,R,P,C,P,5,W + 0.9 fire_B,R,P,C,P,5,C
        - 0.1 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        - 0.1 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3025: - 0.1 fire_B,R,P,C,P,5,P - 0.1 fire_B,R,P,C,P,5,D
        - 0.1 fire_B,R,P,C,P,5,W - 0.1 fire_B,R,P,C,P,5,C
        + 0.9 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        - 0.1 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3026: - 0.2 fire_B,R,P,C,P,5,P - 0.2 fire_B,R,P,C,P,5,D
        - 0.2 fire_B,R,P,C,P,5,W - 0.2 fire_B,R,P,C,P,5,C
        - 0.2 fire_B,R,P,C,P,5,I + 0.8 fire_B,R,P,C,P,5,A
        - 0.2 fire_B,R,P,C,P,5,L - 0.2 fire_B,R,P,C,P,5,N  = 0
 c3027: - 0.1 fire_B,R,P,C,P,5,P - 0.1 fire_B,R,P,C,P,5,D
        - 0.1 fire_B,R,P,C,P,5,W - 0.1 fire_B,R,P,C,P,5,C
        - 0.1 fire_B,R,P,C,P,5,I - 0.1 fire_B,R,P,C,P,5,A
        + 0.9 fire_B,R,P,C,P,5,L - 0.1 fire_B,R,P,C,P,5,N  = 0
 c3028: - 0.2 fire_B,R,P,C,P,5,P - 0.2 fire_B,R,P,C,P,5,D
        - 0.2 fire_B,R,P,C,P,5,W - 0.2 fire_B,R,P,C,P,5,C
        - 0.2 fire_B,R,P,C,P,5,I - 0.2 fire_B,R,P,C,P,5,A
        - 0.2 fire_B,R,P,C,P,5,L + 0.8 fire_B,R,P,C,P,5,N  = 0
 c3029: 0.5 fire_B,R,P,C,D,1,P - 0.5 fire_B,R,P,C,D,1,D
        - 0.5 fire_B,R,P,C,D,1,W - 0.5 fire_B,R,P,C,D,1,C
        - 0.5 fire_B,R,P,C,D,1,I - 0.5 fire_B,R,P,C,D,1,A
        - 0.5 fire_B,R,P,C,D,1,L - 0.5 fire_B,R,P,C,D,1,N  = 0
 c3030: - 0.5 fire_B,R,P,C,D,1,P + 0.5 fire_B,R,P,C,D,1,D
        - 0.5 fire_B,R,P,C,D,1,W - 0.5 fire_B,R,P,C,D,1,C
        - 0.5 fire_B,R,P,C,D,1,I - 0.5 fire_B,R,P,C,D,1,A
        - 0.5 fire_B,R,P,C,D,1,L - 0.5 fire_B,R,P,C,D,1,N  = 0
 c3031: fire_B,R,P,C,D,1,W  = 0
 c3032: fire_B,R,P,C,D,1,C  = 0
 c3033: fire_B,R,P,C,D,1,I  = 0
 c3034: fire_B,R,P,C,D,1,A  = 0
 c3035: fire_B,R,P,C,D,1,L  = 0
 c3036: fire_B,R,P,C,D,1,N  = 0
 c3037: 0.5 fire_B,R,P,C,D,2,P - 0.5 fire_B,R,P,C,D,2,D
        - 0.5 fire_B,R,P,C,D,2,W - 0.5 fire_B,R,P,C,D,2,C
        - 0.5 fire_B,R,P,C,D,2,I - 0.5 fire_B,R,P,C,D,2,A
        - 0.5 fire_B,R,P,C,D,2,L - 0.5 fire_B,R,P,C,D,2,N  = 0
 c3038: - 0.5 fire_B,R,P,C,D,2,P + 0.5 fire_B,R,P,C,D,2,D
        - 0.5 fire_B,R,P,C,D,2,W - 0.5 fire_B,R,P,C,D,2,C
        - 0.5 fire_B,R,P,C,D,2,I - 0.5 fire_B,R,P,C,D,2,A
        - 0.5 fire_B,R,P,C,D,2,L - 0.5 fire_B,R,P,C,D,2,N  = 0
 c3039: fire_B,R,P,C,D,2,W  = 0
 c3040: fire_B,R,P,C,D,2,C  = 0
 c3041: fire_B,R,P,C,D,2,I  = 0
 c3042: fire_B,R,P,C,D,2,A  = 0
 c3043: fire_B,R,P,C,D,2,L  = 0
 c3044: fire_B,R,P,C,D,2,N  = 0
 c3045: 0.5 fire_B,R,P,C,D,3,P - 0.5 fire_B,R,P,C,D,3,D
        - 0.5 fire_B,R,P,C,D,3,W - 0.5 fire_B,R,P,C,D,3,C
        - 0.5 fire_B,R,P,C,D,3,I - 0.5 fire_B,R,P,C,D,3,A
        - 0.5 fire_B,R,P,C,D,3,L - 0.5 fire_B,R,P,C,D,3,N  = 0
 c3046: - 0.5 fire_B,R,P,C,D,3,P + 0.5 fire_B,R,P,C,D,3,D
        - 0.5 fire_B,R,P,C,D,3,W - 0.5 fire_B,R,P,C,D,3,C
        - 0.5 fire_B,R,P,C,D,3,I - 0.5 fire_B,R,P,C,D,3,A
        - 0.5 fire_B,R,P,C,D,3,L - 0.5 fire_B,R,P,C,D,3,N  = 0
 c3047: fire_B,R,P,C,D,3,W  = 0
 c3048: fire_B,R,P,C,D,3,C  = 0
 c3049: fire_B,R,P,C,D,3,I  = 0
 c3050: fire_B,R,P,C,D,3,A  = 0
 c3051: fire_B,R,P,C,D,3,L  = 0
 c3052: fire_B,R,P,C,D,3,N  = 0
 c3053: 0.5 fire_B,R,P,C,D,4,P - 0.5 fire_B,R,P,C,D,4,D
        - 0.5 fire_B,R,P,C,D,4,W - 0.5 fire_B,R,P,C,D,4,C
        - 0.5 fire_B,R,P,C,D,4,I - 0.5 fire_B,R,P,C,D,4,A
        - 0.5 fire_B,R,P,C,D,4,L - 0.5 fire_B,R,P,C,D,4,N  = 0
 c3054: - 0.5 fire_B,R,P,C,D,4,P + 0.5 fire_B,R,P,C,D,4,D
        - 0.5 fire_B,R,P,C,D,4,W - 0.5 fire_B,R,P,C,D,4,C
        - 0.5 fire_B,R,P,C,D,4,I - 0.5 fire_B,R,P,C,D,4,A
        - 0.5 fire_B,R,P,C,D,4,L - 0.5 fire_B,R,P,C,D,4,N  = 0
 c3055: fire_B,R,P,C,D,4,W  = 0
 c3056: fire_B,R,P,C,D,4,C  = 0
 c3057: fire_B,R,P,C,D,4,I  = 0
 c3058: fire_B,R,P,C,D,4,A  = 0
 c3059: fire_B,R,P,C,D,4,L  = 0
 c3060: fire_B,R,P,C,D,4,N  = 0
 c3061: 0.5 fire_B,R,P,C,D,5,P - 0.5 fire_B,R,P,C,D,5,D
        - 0.5 fire_B,R,P,C,D,5,W - 0.5 fire_B,R,P,C,D,5,C
        - 0.5 fire_B,R,P,C,D,5,I - 0.5 fire_B,R,P,C,D,5,A
        - 0.5 fire_B,R,P,C,D,5,L - 0.5 fire_B,R,P,C,D,5,N  = 0
 c3062: - 0.5 fire_B,R,P,C,D,5,P + 0.5 fire_B,R,P,C,D,5,D
        - 0.5 fire_B,R,P,C,D,5,W - 0.5 fire_B,R,P,C,D,5,C
        - 0.5 fire_B,R,P,C,D,5,I - 0.5 fire_B,R,P,C,D,5,A
        - 0.5 fire_B,R,P,C,D,5,L - 0.5 fire_B,R,P,C,D,5,N  = 0
 c3063: fire_B,R,P,C,D,5,W  = 0
 c3064: fire_B,R,P,C,D,5,C  = 0
 c3065: fire_B,R,P,C,D,5,I  = 0
 c3066: fire_B,R,P,C,D,5,A  = 0
 c3067: fire_B,R,P,C,D,5,L  = 0
 c3068: fire_B,R,P,C,D,5,N  = 0
 c3069: fire_B,R,P,C,W,1,P  = 0
 c3070: fire_B,R,P,C,W,1,D  = 0
 c3071: - fire_B,R,P,C,W,1,P - fire_B,R,P,C,W,1,D - fire_B,R,P,C,W,1,C
        - fire_B,R,P,C,W,1,I - fire_B,R,P,C,W,1,A - fire_B,R,P,C,W,1,L
        - fire_B,R,P,C,W,1,N  = 0
 c3072: fire_B,R,P,C,W,1,C  = 0
 c3073: fire_B,R,P,C,W,1,I  = 0
 c3074: fire_B,R,P,C,W,1,A  = 0
 c3075: fire_B,R,P,C,W,1,L  = 0
 c3076: fire_B,R,P,C,W,1,N  = 0
 c3077: fire_B,R,P,C,W,2,P  = 0
 c3078: fire_B,R,P,C,W,2,D  = 0
 c3079: - fire_B,R,P,C,W,2,P - fire_B,R,P,C,W,2,D - fire_B,R,P,C,W,2,C
        - fire_B,R,P,C,W,2,I - fire_B,R,P,C,W,2,A - fire_B,R,P,C,W,2,L
        - fire_B,R,P,C,W,2,N  = 0
 c3080: fire_B,R,P,C,W,2,C  = 0
 c3081: fire_B,R,P,C,W,2,I  = 0
 c3082: fire_B,R,P,C,W,2,A  = 0
 c3083: fire_B,R,P,C,W,2,L  = 0
 c3084: fire_B,R,P,C,W,2,N  = 0
 c3085: fire_B,R,P,C,W,3,P  = 0
 c3086: fire_B,R,P,C,W,3,D  = 0
 c3087: - fire_B,R,P,C,W,3,P - fire_B,R,P,C,W,3,D - fire_B,R,P,C,W,3,C
        - fire_B,R,P,C,W,3,I - fire_B,R,P,C,W,3,A - fire_B,R,P,C,W,3,L
        - fire_B,R,P,C,W,3,N  = 0
 c3088: fire_B,R,P,C,W,3,C  = 0
 c3089: fire_B,R,P,C,W,3,I  = 0
 c3090: fire_B,R,P,C,W,3,A  = 0
 c3091: fire_B,R,P,C,W,3,L  = 0
 c3092: fire_B,R,P,C,W,3,N  = 0
 c3093: fire_B,R,P,C,W,4,P  = 0
 c3094: fire_B,R,P,C,W,4,D  = 0
 c3095: - fire_B,R,P,C,W,4,P - fire_B,R,P,C,W,4,D - fire_B,R,P,C,W,4,C
        - fire_B,R,P,C,W,4,I - fire_B,R,P,C,W,4,A - fire_B,R,P,C,W,4,L
        - fire_B,R,P,C,W,4,N  = 0
 c3096: fire_B,R,P,C,W,4,C  = 0
 c3097: fire_B,R,P,C,W,4,I  = 0
 c3098: fire_B,R,P,C,W,4,A  = 0
 c3099: fire_B,R,P,C,W,4,L  = 0
 c3100: fire_B,R,P,C,W,4,N  = 0
 c3101: fire_B,R,P,C,W,5,P  = 0
 c3102: fire_B,R,P,C,W,5,D  = 0
 c3103: - fire_B,R,P,C,W,5,P - fire_B,R,P,C,W,5,D - fire_B,R,P,C,W,5,C
        - fire_B,R,P,C,W,5,I - fire_B,R,P,C,W,5,A - fire_B,R,P,C,W,5,L
        - fire_B,R,P,C,W,5,N  = 0
 c3104: fire_B,R,P,C,W,5,C  = 0
 c3105: fire_B,R,P,C,W,5,I  = 0
 c3106: fire_B,R,P,C,W,5,A  = 0
 c3107: fire_B,R,P,C,W,5,L  = 0
 c3108: fire_B,R,P,C,W,5,N  = 0
 c3109: fire_B,R,P,C,C,1,P  = 0
 c3110: fire_B,R,P,C,C,1,D  = 0
 c3111: fire_B,R,P,C,C,1,W  = 0
 c3112: - fire_B,R,P,C,C,1,P - fire_B,R,P,C,C,1,D - fire_B,R,P,C,C,1,W
        - fire_B,R,P,C,C,1,I - fire_B,R,P,C,C,1,A - fire_B,R,P,C,C,1,L
        - fire_B,R,P,C,C,1,N  = 0
 c3113: fire_B,R,P,C,C,1,I  = 0
 c3114: fire_B,R,P,C,C,1,A  = 0
 c3115: fire_B,R,P,C,C,1,L  = 0
 c3116: fire_B,R,P,C,C,1,N  = 0
 c3117: fire_B,R,P,C,C,2,P  = 0
 c3118: fire_B,R,P,C,C,2,D  = 0
 c3119: fire_B,R,P,C,C,2,W  = 0
 c3120: - fire_B,R,P,C,C,2,P - fire_B,R,P,C,C,2,D - fire_B,R,P,C,C,2,W
        - fire_B,R,P,C,C,2,I - fire_B,R,P,C,C,2,A - fire_B,R,P,C,C,2,L
        - fire_B,R,P,C,C,2,N  = 0
 c3121: fire_B,R,P,C,C,2,I  = 0
 c3122: fire_B,R,P,C,C,2,A  = 0
 c3123: fire_B,R,P,C,C,2,L  = 0
 c3124: fire_B,R,P,C,C,2,N  = 0
 c3125: fire_B,R,P,C,C,3,P  = 0
 c3126: fire_B,R,P,C,C,3,D  = 0
 c3127: fire_B,R,P,C,C,3,W  = 0
 c3128: - fire_B,R,P,C,C,3,P - fire_B,R,P,C,C,3,D - fire_B,R,P,C,C,3,W
        - fire_B,R,P,C,C,3,I - fire_B,R,P,C,C,3,A - fire_B,R,P,C,C,3,L
        - fire_B,R,P,C,C,3,N  = 0
 c3129: fire_B,R,P,C,C,3,I  = 0
 c3130: fire_B,R,P,C,C,3,A  = 0
 c3131: fire_B,R,P,C,C,3,L  = 0
 c3132: fire_B,R,P,C,C,3,N  = 0
 c3133: fire_B,R,P,C,C,4,P  = 0
 c3134: fire_B,R,P,C,C,4,D  = 0
 c3135: fire_B,R,P,C,C,4,W  = 0
 c3136: - fire_B,R,P,C,C,4,P - fire_B,R,P,C,C,4,D - fire_B,R,P,C,C,4,W
        - fire_B,R,P,C,C,4,I - fire_B,R,P,C,C,4,A - fire_B,R,P,C,C,4,L
        - fire_B,R,P,C,C,4,N  = 0
 c3137: fire_B,R,P,C,C,4,I  = 0
 c3138: fire_B,R,P,C,C,4,A  = 0
 c3139: fire_B,R,P,C,C,4,L  = 0
 c3140: fire_B,R,P,C,C,4,N  = 0
 c3141: fire_B,R,P,C,C,5,P  = 0
 c3142: fire_B,R,P,C,C,5,D  = 0
 c3143: fire_B,R,P,C,C,5,W  = 0
 c3144: - fire_B,R,P,C,C,5,P - fire_B,R,P,C,C,5,D - fire_B,R,P,C,C,5,W
        - fire_B,R,P,C,C,5,I - fire_B,R,P,C,C,5,A - fire_B,R,P,C,C,5,L
        - fire_B,R,P,C,C,5,N  = 0
 c3145: fire_B,R,P,C,C,5,I  = 0
 c3146: fire_B,R,P,C,C,5,A  = 0
 c3147: fire_B,R,P,C,C,5,L  = 0
 c3148: fire_B,R,P,C,C,5,N  = 0
 c3149: fire_B,R,P,C,I,1,P  = 0
 c3150: fire_B,R,P,C,I,1,D  = 0
 c3151: fire_B,R,P,C,I,1,W  = 0
 c3152: fire_B,R,P,C,I,1,C  = 0
 c3153: - fire_B,R,P,C,I,1,P - fire_B,R,P,C,I,1,D - fire_B,R,P,C,I,1,W
        - fire_B,R,P,C,I,1,C - fire_B,R,P,C,I,1,A - fire_B,R,P,C,I,1,L
        - fire_B,R,P,C,I,1,N  = 0
 c3154: fire_B,R,P,C,I,1,A  = 0
 c3155: fire_B,R,P,C,I,1,L  = 0
 c3156: fire_B,R,P,C,I,1,N  = 0
 c3157: fire_B,R,P,C,I,2,P  = 0
 c3158: fire_B,R,P,C,I,2,D  = 0
 c3159: fire_B,R,P,C,I,2,W  = 0
 c3160: fire_B,R,P,C,I,2,C  = 0
 c3161: - fire_B,R,P,C,I,2,P - fire_B,R,P,C,I,2,D - fire_B,R,P,C,I,2,W
        - fire_B,R,P,C,I,2,C - fire_B,R,P,C,I,2,A - fire_B,R,P,C,I,2,L
        - fire_B,R,P,C,I,2,N  = 0
 c3162: fire_B,R,P,C,I,2,A  = 0
 c3163: fire_B,R,P,C,I,2,L  = 0
 c3164: fire_B,R,P,C,I,2,N  = 0
 c3165: fire_B,R,P,C,I,3,P  = 0
 c3166: fire_B,R,P,C,I,3,D  = 0
 c3167: fire_B,R,P,C,I,3,W  = 0
 c3168: fire_B,R,P,C,I,3,C  = 0
 c3169: - fire_B,R,P,C,I,3,P - fire_B,R,P,C,I,3,D - fire_B,R,P,C,I,3,W
        - fire_B,R,P,C,I,3,C - fire_B,R,P,C,I,3,A - fire_B,R,P,C,I,3,L
        - fire_B,R,P,C,I,3,N  = 0
 c3170: fire_B,R,P,C,I,3,A  = 0
 c3171: fire_B,R,P,C,I,3,L  = 0
 c3172: fire_B,R,P,C,I,3,N  = 0
 c3173: fire_B,R,P,C,I,4,P  = 0
 c3174: fire_B,R,P,C,I,4,D  = 0
 c3175: fire_B,R,P,C,I,4,W  = 0
 c3176: fire_B,R,P,C,I,4,C  = 0
 c3177: - fire_B,R,P,C,I,4,P - fire_B,R,P,C,I,4,D - fire_B,R,P,C,I,4,W
        - fire_B,R,P,C,I,4,C - fire_B,R,P,C,I,4,A - fire_B,R,P,C,I,4,L
        - fire_B,R,P,C,I,4,N  = 0
 c3178: fire_B,R,P,C,I,4,A  = 0
 c3179: fire_B,R,P,C,I,4,L  = 0
 c3180: fire_B,R,P,C,I,4,N  = 0
 c3181: fire_B,R,P,C,I,5,P  = 0
 c3182: fire_B,R,P,C,I,5,D  = 0
 c3183: fire_B,R,P,C,I,5,W  = 0
 c3184: fire_B,R,P,C,I,5,C  = 0
 c3185: - fire_B,R,P,C,I,5,P - fire_B,R,P,C,I,5,D - fire_B,R,P,C,I,5,W
        - fire_B,R,P,C,I,5,C - fire_B,R,P,C,I,5,A - fire_B,R,P,C,I,5,L
        - fire_B,R,P,C,I,5,N  = 0
 c3186: fire_B,R,P,C,I,5,A  = 0
 c3187: fire_B,R,P,C,I,5,L  = 0
 c3188: fire_B,R,P,C,I,5,N  = 0
 c3189: fire_B,R,P,C,A,1,P  = 0
 c3190: fire_B,R,P,C,A,1,D  = 0
 c3191: fire_B,R,P,C,A,1,W  = 0
 c3192: fire_B,R,P,C,A,1,C  = 0
 c3193: fire_B,R,P,C,A,1,I  = 0
 c3194: - fire_B,R,P,C,A,1,P - fire_B,R,P,C,A,1,D - fire_B,R,P,C,A,1,W
        - fire_B,R,P,C,A,1,C - fire_B,R,P,C,A,1,I - fire_B,R,P,C,A,1,L
        - fire_B,R,P,C,A,1,N  = 0
 c3195: fire_B,R,P,C,A,1,L  = 0
 c3196: fire_B,R,P,C,A,1,N  = 0
 c3197: fire_B,R,P,C,A,2,P  = 0
 c3198: fire_B,R,P,C,A,2,D  = 0
 c3199: fire_B,R,P,C,A,2,W  = 0
 c3200: fire_B,R,P,C,A,2,C  = 0
 c3201: fire_B,R,P,C,A,2,I  = 0
 c3202: - fire_B,R,P,C,A,2,P - fire_B,R,P,C,A,2,D - fire_B,R,P,C,A,2,W
        - fire_B,R,P,C,A,2,C - fire_B,R,P,C,A,2,I - fire_B,R,P,C,A,2,L
        - fire_B,R,P,C,A,2,N  = 0
 c3203: fire_B,R,P,C,A,2,L  = 0
 c3204: fire_B,R,P,C,A,2,N  = 0
 c3205: fire_B,R,P,C,A,3,P  = 0
 c3206: fire_B,R,P,C,A,3,D  = 0
 c3207: fire_B,R,P,C,A,3,W  = 0
 c3208: fire_B,R,P,C,A,3,C  = 0
 c3209: fire_B,R,P,C,A,3,I  = 0
 c3210: - fire_B,R,P,C,A,3,P - fire_B,R,P,C,A,3,D - fire_B,R,P,C,A,3,W
        - fire_B,R,P,C,A,3,C - fire_B,R,P,C,A,3,I - fire_B,R,P,C,A,3,L
        - fire_B,R,P,C,A,3,N  = 0
 c3211: fire_B,R,P,C,A,3,L  = 0
 c3212: fire_B,R,P,C,A,3,N  = 0
 c3213: fire_B,R,P,C,A,4,P  = 0
 c3214: fire_B,R,P,C,A,4,D  = 0
 c3215: fire_B,R,P,C,A,4,W  = 0
 c3216: fire_B,R,P,C,A,4,C  = 0
 c3217: fire_B,R,P,C,A,4,I  = 0
 c3218: - fire_B,R,P,C,A,4,P - fire_B,R,P,C,A,4,D - fire_B,R,P,C,A,4,W
        - fire_B,R,P,C,A,4,C - fire_B,R,P,C,A,4,I - fire_B,R,P,C,A,4,L
        - fire_B,R,P,C,A,4,N  = 0
 c3219: fire_B,R,P,C,A,4,L  = 0
 c3220: fire_B,R,P,C,A,4,N  = 0
 c3221: fire_B,R,P,C,A,5,P  = 0
 c3222: fire_B,R,P,C,A,5,D  = 0
 c3223: fire_B,R,P,C,A,5,W  = 0
 c3224: fire_B,R,P,C,A,5,C  = 0
 c3225: fire_B,R,P,C,A,5,I  = 0
 c3226: - fire_B,R,P,C,A,5,P - fire_B,R,P,C,A,5,D - fire_B,R,P,C,A,5,W
        - fire_B,R,P,C,A,5,C - fire_B,R,P,C,A,5,I - fire_B,R,P,C,A,5,L
        - fire_B,R,P,C,A,5,N  = 0
 c3227: fire_B,R,P,C,A,5,L  = 0
 c3228: fire_B,R,P,C,A,5,N  = 0
 c3229: fire_B,R,P,C,L,1,P  = 0
 c3230: fire_B,R,P,C,L,1,D  = 0
 c3231: fire_B,R,P,C,L,1,W  = 0
 c3232: fire_B,R,P,C,L,1,C  = 0
 c3233: fire_B,R,P,C,L,1,I  = 0
 c3234: fire_B,R,P,C,L,1,A  = 0
 c3235: - fire_B,R,P,C,L,1,P - fire_B,R,P,C,L,1,D - fire_B,R,P,C,L,1,W
        - fire_B,R,P,C,L,1,C - fire_B,R,P,C,L,1,I - fire_B,R,P,C,L,1,A
        - fire_B,R,P,C,L,1,N  = 0
 c3236: fire_B,R,P,C,L,1,N  = 0
 c3237: fire_B,R,P,C,L,2,P  = 0
 c3238: fire_B,R,P,C,L,2,D  = 0
 c3239: fire_B,R,P,C,L,2,W  = 0
 c3240: fire_B,R,P,C,L,2,C  = 0
 c3241: fire_B,R,P,C,L,2,I  = 0
 c3242: fire_B,R,P,C,L,2,A  = 0
 c3243: - fire_B,R,P,C,L,2,P - fire_B,R,P,C,L,2,D - fire_B,R,P,C,L,2,W
        - fire_B,R,P,C,L,2,C - fire_B,R,P,C,L,2,I - fire_B,R,P,C,L,2,A
        - fire_B,R,P,C,L,2,N  = 0
 c3244: fire_B,R,P,C,L,2,N  = 0
 c3245: fire_B,R,P,C,L,3,P  = 0
 c3246: fire_B,R,P,C,L,3,D  = 0
 c3247: fire_B,R,P,C,L,3,W  = 0
 c3248: fire_B,R,P,C,L,3,C  = 0
 c3249: fire_B,R,P,C,L,3,I  = 0
 c3250: fire_B,R,P,C,L,3,A  = 0
 c3251: - fire_B,R,P,C,L,3,P - fire_B,R,P,C,L,3,D - fire_B,R,P,C,L,3,W
        - fire_B,R,P,C,L,3,C - fire_B,R,P,C,L,3,I - fire_B,R,P,C,L,3,A
        - fire_B,R,P,C,L,3,N  = 0
 c3252: fire_B,R,P,C,L,3,N  = 0
 c3253: fire_B,R,P,C,L,4,P  = 0
 c3254: fire_B,R,P,C,L,4,D  = 0
 c3255: fire_B,R,P,C,L,4,W  = 0
 c3256: fire_B,R,P,C,L,4,C  = 0
 c3257: fire_B,R,P,C,L,4,I  = 0
 c3258: fire_B,R,P,C,L,4,A  = 0
 c3259: - fire_B,R,P,C,L,4,P - fire_B,R,P,C,L,4,D - fire_B,R,P,C,L,4,W
        - fire_B,R,P,C,L,4,C - fire_B,R,P,C,L,4,I - fire_B,R,P,C,L,4,A
        - fire_B,R,P,C,L,4,N  = 0
 c3260: fire_B,R,P,C,L,4,N  = 0
 c3261: fire_B,R,P,C,L,5,P  = 0
 c3262: fire_B,R,P,C,L,5,D  = 0
 c3263: fire_B,R,P,C,L,5,W  = 0
 c3264: fire_B,R,P,C,L,5,C  = 0
 c3265: fire_B,R,P,C,L,5,I  = 0
 c3266: fire_B,R,P,C,L,5,A  = 0
 c3267: - fire_B,R,P,C,L,5,P - fire_B,R,P,C,L,5,D - fire_B,R,P,C,L,5,W
        - fire_B,R,P,C,L,5,C - fire_B,R,P,C,L,5,I - fire_B,R,P,C,L,5,A
        - fire_B,R,P,C,L,5,N  = 0
 c3268: fire_B,R,P,C,L,5,N  = 0
 c3269: fire_B,R,P,C,N,1,P  = 0
 c3270: fire_B,R,P,C,N,1,D  = 0
 c3271: fire_B,R,P,C,N,1,W  = 0
 c3272: fire_B,R,P,C,N,1,C  = 0
 c3273: fire_B,R,P,C,N,1,I  = 0
 c3274: fire_B,R,P,C,N,1,A  = 0
 c3275: fire_B,R,P,C,N,1,L  = 0
 c3276: - fire_B,R,P,C,N,1,P - fire_B,R,P,C,N,1,D - fire_B,R,P,C,N,1,W
        - fire_B,R,P,C,N,1,C - fire_B,R,P,C,N,1,I - fire_B,R,P,C,N,1,A
        - fire_B,R,P,C,N,1,L  = 0
 c3277: fire_B,R,P,C,N,2,P  = 0
 c3278: fire_B,R,P,C,N,2,D  = 0
 c3279: fire_B,R,P,C,N,2,W  = 0
 c3280: fire_B,R,P,C,N,2,C  = 0
 c3281: fire_B,R,P,C,N,2,I  = 0
 c3282: fire_B,R,P,C,N,2,A  = 0
 c3283: fire_B,R,P,C,N,2,L  = 0
 c3284: - fire_B,R,P,C,N,2,P - fire_B,R,P,C,N,2,D - fire_B,R,P,C,N,2,W
        - fire_B,R,P,C,N,2,C - fire_B,R,P,C,N,2,I - fire_B,R,P,C,N,2,A
        - fire_B,R,P,C,N,2,L  = 0
 c3285: fire_B,R,P,C,N,3,P  = 0
 c3286: fire_B,R,P,C,N,3,D  = 0
 c3287: fire_B,R,P,C,N,3,W  = 0
 c3288: fire_B,R,P,C,N,3,C  = 0
 c3289: fire_B,R,P,C,N,3,I  = 0
 c3290: fire_B,R,P,C,N,3,A  = 0
 c3291: fire_B,R,P,C,N,3,L  = 0
 c3292: - fire_B,R,P,C,N,3,P - fire_B,R,P,C,N,3,D - fire_B,R,P,C,N,3,W
        - fire_B,R,P,C,N,3,C - fire_B,R,P,C,N,3,I - fire_B,R,P,C,N,3,A
        - fire_B,R,P,C,N,3,L  = 0
 c3293: fire_B,R,P,C,N,4,P  = 0
 c3294: fire_B,R,P,C,N,4,D  = 0
 c3295: fire_B,R,P,C,N,4,W  = 0
 c3296: fire_B,R,P,C,N,4,C  = 0
 c3297: fire_B,R,P,C,N,4,I  = 0
 c3298: fire_B,R,P,C,N,4,A  = 0
 c3299: fire_B,R,P,C,N,4,L  = 0
 c3300: - fire_B,R,P,C,N,4,P - fire_B,R,P,C,N,4,D - fire_B,R,P,C,N,4,W
        - fire_B,R,P,C,N,4,C - fire_B,R,P,C,N,4,I - fire_B,R,P,C,N,4,A
        - fire_B,R,P,C,N,4,L  = 0
 c3301: fire_B,R,P,C,N,5,P  = 0
 c3302: fire_B,R,P,C,N,5,D  = 0
 c3303: fire_B,R,P,C,N,5,W  = 0
 c3304: fire_B,R,P,C,N,5,C  = 0
 c3305: fire_B,R,P,C,N,5,I  = 0
 c3306: fire_B,R,P,C,N,5,A  = 0
 c3307: fire_B,R,P,C,N,5,L  = 0
 c3308: - fire_B,R,P,C,N,5,P - fire_B,R,P,C,N,5,D - fire_B,R,P,C,N,5,W
        - fire_B,R,P,C,N,5,C - fire_B,R,P,C,N,5,I - fire_B,R,P,C,N,5,A
        - fire_B,R,P,C,N,5,L  = 0
 c3309: 0.9 fire_B,R,P,R,P,1,P - 0.1 fire_B,R,P,R,P,1,D
        - 0.1 fire_B,R,P,R,P,1,W - 0.1 fire_B,R,P,R,P,1,C
        - 0.1 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        - 0.1 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3310: - 0.1 fire_B,R,P,R,P,1,P + 0.9 fire_B,R,P,R,P,1,D
        - 0.1 fire_B,R,P,R,P,1,W - 0.1 fire_B,R,P,R,P,1,C
        - 0.1 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        - 0.1 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3311: - 0.1 fire_B,R,P,R,P,1,P - 0.1 fire_B,R,P,R,P,1,D
        + 0.9 fire_B,R,P,R,P,1,W - 0.1 fire_B,R,P,R,P,1,C
        - 0.1 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        - 0.1 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3312: - 0.1 fire_B,R,P,R,P,1,P - 0.1 fire_B,R,P,R,P,1,D
        - 0.1 fire_B,R,P,R,P,1,W + 0.9 fire_B,R,P,R,P,1,C
        - 0.1 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        - 0.1 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3313: - 0.1 fire_B,R,P,R,P,1,P - 0.1 fire_B,R,P,R,P,1,D
        - 0.1 fire_B,R,P,R,P,1,W - 0.1 fire_B,R,P,R,P,1,C
        + 0.9 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        - 0.1 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3314: - 0.2 fire_B,R,P,R,P,1,P - 0.2 fire_B,R,P,R,P,1,D
        - 0.2 fire_B,R,P,R,P,1,W - 0.2 fire_B,R,P,R,P,1,C
        - 0.2 fire_B,R,P,R,P,1,I + 0.8 fire_B,R,P,R,P,1,A
        - 0.2 fire_B,R,P,R,P,1,L - 0.2 fire_B,R,P,R,P,1,N  = 0
 c3315: - 0.1 fire_B,R,P,R,P,1,P - 0.1 fire_B,R,P,R,P,1,D
        - 0.1 fire_B,R,P,R,P,1,W - 0.1 fire_B,R,P,R,P,1,C
        - 0.1 fire_B,R,P,R,P,1,I - 0.1 fire_B,R,P,R,P,1,A
        + 0.9 fire_B,R,P,R,P,1,L - 0.1 fire_B,R,P,R,P,1,N  = 0
 c3316: - 0.2 fire_B,R,P,R,P,1,P - 0.2 fire_B,R,P,R,P,1,D
        - 0.2 fire_B,R,P,R,P,1,W - 0.2 fire_B,R,P,R,P,1,C
        - 0.2 fire_B,R,P,R,P,1,I - 0.2 fire_B,R,P,R,P,1,A
        - 0.2 fire_B,R,P,R,P,1,L + 0.8 fire_B,R,P,R,P,1,N  = 0
 c3317: 0.9 fire_B,R,P,R,P,2,P - 0.1 fire_B,R,P,R,P,2,D
        - 0.1 fire_B,R,P,R,P,2,W - 0.1 fire_B,R,P,R,P,2,C
        - 0.1 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        - 0.1 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3318: - 0.1 fire_B,R,P,R,P,2,P + 0.9 fire_B,R,P,R,P,2,D
        - 0.1 fire_B,R,P,R,P,2,W - 0.1 fire_B,R,P,R,P,2,C
        - 0.1 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        - 0.1 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3319: - 0.1 fire_B,R,P,R,P,2,P - 0.1 fire_B,R,P,R,P,2,D
        + 0.9 fire_B,R,P,R,P,2,W - 0.1 fire_B,R,P,R,P,2,C
        - 0.1 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        - 0.1 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3320: - 0.1 fire_B,R,P,R,P,2,P - 0.1 fire_B,R,P,R,P,2,D
        - 0.1 fire_B,R,P,R,P,2,W + 0.9 fire_B,R,P,R,P,2,C
        - 0.1 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        - 0.1 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3321: - 0.1 fire_B,R,P,R,P,2,P - 0.1 fire_B,R,P,R,P,2,D
        - 0.1 fire_B,R,P,R,P,2,W - 0.1 fire_B,R,P,R,P,2,C
        + 0.9 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        - 0.1 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3322: - 0.2 fire_B,R,P,R,P,2,P - 0.2 fire_B,R,P,R,P,2,D
        - 0.2 fire_B,R,P,R,P,2,W - 0.2 fire_B,R,P,R,P,2,C
        - 0.2 fire_B,R,P,R,P,2,I + 0.8 fire_B,R,P,R,P,2,A
        - 0.2 fire_B,R,P,R,P,2,L - 0.2 fire_B,R,P,R,P,2,N  = 0
 c3323: - 0.1 fire_B,R,P,R,P,2,P - 0.1 fire_B,R,P,R,P,2,D
        - 0.1 fire_B,R,P,R,P,2,W - 0.1 fire_B,R,P,R,P,2,C
        - 0.1 fire_B,R,P,R,P,2,I - 0.1 fire_B,R,P,R,P,2,A
        + 0.9 fire_B,R,P,R,P,2,L - 0.1 fire_B,R,P,R,P,2,N  = 0
 c3324: - 0.2 fire_B,R,P,R,P,2,P - 0.2 fire_B,R,P,R,P,2,D
        - 0.2 fire_B,R,P,R,P,2,W - 0.2 fire_B,R,P,R,P,2,C
        - 0.2 fire_B,R,P,R,P,2,I - 0.2 fire_B,R,P,R,P,2,A
        - 0.2 fire_B,R,P,R,P,2,L + 0.8 fire_B,R,P,R,P,2,N  = 0
 c3325: 0.9 fire_B,R,P,R,P,3,P - 0.1 fire_B,R,P,R,P,3,D
        - 0.1 fire_B,R,P,R,P,3,W - 0.1 fire_B,R,P,R,P,3,C
        - 0.1 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        - 0.1 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3326: - 0.1 fire_B,R,P,R,P,3,P + 0.9 fire_B,R,P,R,P,3,D
        - 0.1 fire_B,R,P,R,P,3,W - 0.1 fire_B,R,P,R,P,3,C
        - 0.1 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        - 0.1 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3327: - 0.1 fire_B,R,P,R,P,3,P - 0.1 fire_B,R,P,R,P,3,D
        + 0.9 fire_B,R,P,R,P,3,W - 0.1 fire_B,R,P,R,P,3,C
        - 0.1 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        - 0.1 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3328: - 0.1 fire_B,R,P,R,P,3,P - 0.1 fire_B,R,P,R,P,3,D
        - 0.1 fire_B,R,P,R,P,3,W + 0.9 fire_B,R,P,R,P,3,C
        - 0.1 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        - 0.1 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3329: - 0.1 fire_B,R,P,R,P,3,P - 0.1 fire_B,R,P,R,P,3,D
        - 0.1 fire_B,R,P,R,P,3,W - 0.1 fire_B,R,P,R,P,3,C
        + 0.9 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        - 0.1 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3330: - 0.2 fire_B,R,P,R,P,3,P - 0.2 fire_B,R,P,R,P,3,D
        - 0.2 fire_B,R,P,R,P,3,W - 0.2 fire_B,R,P,R,P,3,C
        - 0.2 fire_B,R,P,R,P,3,I + 0.8 fire_B,R,P,R,P,3,A
        - 0.2 fire_B,R,P,R,P,3,L - 0.2 fire_B,R,P,R,P,3,N  = 0
 c3331: - 0.1 fire_B,R,P,R,P,3,P - 0.1 fire_B,R,P,R,P,3,D
        - 0.1 fire_B,R,P,R,P,3,W - 0.1 fire_B,R,P,R,P,3,C
        - 0.1 fire_B,R,P,R,P,3,I - 0.1 fire_B,R,P,R,P,3,A
        + 0.9 fire_B,R,P,R,P,3,L - 0.1 fire_B,R,P,R,P,3,N  = 0
 c3332: - 0.2 fire_B,R,P,R,P,3,P - 0.2 fire_B,R,P,R,P,3,D
        - 0.2 fire_B,R,P,R,P,3,W - 0.2 fire_B,R,P,R,P,3,C
        - 0.2 fire_B,R,P,R,P,3,I - 0.2 fire_B,R,P,R,P,3,A
        - 0.2 fire_B,R,P,R,P,3,L + 0.8 fire_B,R,P,R,P,3,N  = 0
 c3333: 0.9 fire_B,R,P,R,P,4,P - 0.1 fire_B,R,P,R,P,4,D
        - 0.1 fire_B,R,P,R,P,4,W - 0.1 fire_B,R,P,R,P,4,C
        - 0.1 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        - 0.1 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3334: - 0.1 fire_B,R,P,R,P,4,P + 0.9 fire_B,R,P,R,P,4,D
        - 0.1 fire_B,R,P,R,P,4,W - 0.1 fire_B,R,P,R,P,4,C
        - 0.1 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        - 0.1 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3335: - 0.1 fire_B,R,P,R,P,4,P - 0.1 fire_B,R,P,R,P,4,D
        + 0.9 fire_B,R,P,R,P,4,W - 0.1 fire_B,R,P,R,P,4,C
        - 0.1 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        - 0.1 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3336: - 0.1 fire_B,R,P,R,P,4,P - 0.1 fire_B,R,P,R,P,4,D
        - 0.1 fire_B,R,P,R,P,4,W + 0.9 fire_B,R,P,R,P,4,C
        - 0.1 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        - 0.1 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3337: - 0.1 fire_B,R,P,R,P,4,P - 0.1 fire_B,R,P,R,P,4,D
        - 0.1 fire_B,R,P,R,P,4,W - 0.1 fire_B,R,P,R,P,4,C
        + 0.9 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        - 0.1 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3338: - 0.2 fire_B,R,P,R,P,4,P - 0.2 fire_B,R,P,R,P,4,D
        - 0.2 fire_B,R,P,R,P,4,W - 0.2 fire_B,R,P,R,P,4,C
        - 0.2 fire_B,R,P,R,P,4,I + 0.8 fire_B,R,P,R,P,4,A
        - 0.2 fire_B,R,P,R,P,4,L - 0.2 fire_B,R,P,R,P,4,N  = 0
 c3339: - 0.1 fire_B,R,P,R,P,4,P - 0.1 fire_B,R,P,R,P,4,D
        - 0.1 fire_B,R,P,R,P,4,W - 0.1 fire_B,R,P,R,P,4,C
        - 0.1 fire_B,R,P,R,P,4,I - 0.1 fire_B,R,P,R,P,4,A
        + 0.9 fire_B,R,P,R,P,4,L - 0.1 fire_B,R,P,R,P,4,N  = 0
 c3340: - 0.2 fire_B,R,P,R,P,4,P - 0.2 fire_B,R,P,R,P,4,D
        - 0.2 fire_B,R,P,R,P,4,W - 0.2 fire_B,R,P,R,P,4,C
        - 0.2 fire_B,R,P,R,P,4,I - 0.2 fire_B,R,P,R,P,4,A
        - 0.2 fire_B,R,P,R,P,4,L + 0.8 fire_B,R,P,R,P,4,N  = 0
 c3341: 0.9 fire_B,R,P,R,P,5,P - 0.1 fire_B,R,P,R,P,5,D
        - 0.1 fire_B,R,P,R,P,5,W - 0.1 fire_B,R,P,R,P,5,C
        - 0.1 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        - 0.1 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3342: - 0.1 fire_B,R,P,R,P,5,P + 0.9 fire_B,R,P,R,P,5,D
        - 0.1 fire_B,R,P,R,P,5,W - 0.1 fire_B,R,P,R,P,5,C
        - 0.1 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        - 0.1 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3343: - 0.1 fire_B,R,P,R,P,5,P - 0.1 fire_B,R,P,R,P,5,D
        + 0.9 fire_B,R,P,R,P,5,W - 0.1 fire_B,R,P,R,P,5,C
        - 0.1 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        - 0.1 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3344: - 0.1 fire_B,R,P,R,P,5,P - 0.1 fire_B,R,P,R,P,5,D
        - 0.1 fire_B,R,P,R,P,5,W + 0.9 fire_B,R,P,R,P,5,C
        - 0.1 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        - 0.1 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3345: - 0.1 fire_B,R,P,R,P,5,P - 0.1 fire_B,R,P,R,P,5,D
        - 0.1 fire_B,R,P,R,P,5,W - 0.1 fire_B,R,P,R,P,5,C
        + 0.9 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        - 0.1 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3346: - 0.2 fire_B,R,P,R,P,5,P - 0.2 fire_B,R,P,R,P,5,D
        - 0.2 fire_B,R,P,R,P,5,W - 0.2 fire_B,R,P,R,P,5,C
        - 0.2 fire_B,R,P,R,P,5,I + 0.8 fire_B,R,P,R,P,5,A
        - 0.2 fire_B,R,P,R,P,5,L - 0.2 fire_B,R,P,R,P,5,N  = 0
 c3347: - 0.1 fire_B,R,P,R,P,5,P - 0.1 fire_B,R,P,R,P,5,D
        - 0.1 fire_B,R,P,R,P,5,W - 0.1 fire_B,R,P,R,P,5,C
        - 0.1 fire_B,R,P,R,P,5,I - 0.1 fire_B,R,P,R,P,5,A
        + 0.9 fire_B,R,P,R,P,5,L - 0.1 fire_B,R,P,R,P,5,N  = 0
 c3348: - 0.2 fire_B,R,P,R,P,5,P - 0.2 fire_B,R,P,R,P,5,D
        - 0.2 fire_B,R,P,R,P,5,W - 0.2 fire_B,R,P,R,P,5,C
        - 0.2 fire_B,R,P,R,P,5,I - 0.2 fire_B,R,P,R,P,5,A
        - 0.2 fire_B,R,P,R,P,5,L + 0.8 fire_B,R,P,R,P,5,N  = 0
 c3349: 0.5 fire_B,R,P,R,D,1,P - 0.5 fire_B,R,P,R,D,1,D
        - 0.5 fire_B,R,P,R,D,1,W - 0.5 fire_B,R,P,R,D,1,C
        - 0.5 fire_B,R,P,R,D,1,I - 0.5 fire_B,R,P,R,D,1,A
        - 0.5 fire_B,R,P,R,D,1,L - 0.5 fire_B,R,P,R,D,1,N  = 0
 c3350: - 0.5 fire_B,R,P,R,D,1,P + 0.5 fire_B,R,P,R,D,1,D
        - 0.5 fire_B,R,P,R,D,1,W - 0.5 fire_B,R,P,R,D,1,C
        - 0.5 fire_B,R,P,R,D,1,I - 0.5 fire_B,R,P,R,D,1,A
        - 0.5 fire_B,R,P,R,D,1,L - 0.5 fire_B,R,P,R,D,1,N  = 0
 c3351: fire_B,R,P,R,D,1,W  = 0
 c3352: fire_B,R,P,R,D,1,C  = 0
 c3353: fire_B,R,P,R,D,1,I  = 0
 c3354: fire_B,R,P,R,D,1,A  = 0
 c3355: fire_B,R,P,R,D,1,L  = 0
 c3356: fire_B,R,P,R,D,1,N  = 0
 c3357: 0.5 fire_B,R,P,R,D,2,P - 0.5 fire_B,R,P,R,D,2,D
        - 0.5 fire_B,R,P,R,D,2,W - 0.5 fire_B,R,P,R,D,2,C
        - 0.5 fire_B,R,P,R,D,2,I - 0.5 fire_B,R,P,R,D,2,A
        - 0.5 fire_B,R,P,R,D,2,L - 0.5 fire_B,R,P,R,D,2,N  = 0
 c3358: - 0.5 fire_B,R,P,R,D,2,P + 0.5 fire_B,R,P,R,D,2,D
        - 0.5 fire_B,R,P,R,D,2,W - 0.5 fire_B,R,P,R,D,2,C
        - 0.5 fire_B,R,P,R,D,2,I - 0.5 fire_B,R,P,R,D,2,A
        - 0.5 fire_B,R,P,R,D,2,L - 0.5 fire_B,R,P,R,D,2,N  = 0
 c3359: fire_B,R,P,R,D,2,W  = 0
 c3360: fire_B,R,P,R,D,2,C  = 0
 c3361: fire_B,R,P,R,D,2,I  = 0
 c3362: fire_B,R,P,R,D,2,A  = 0
 c3363: fire_B,R,P,R,D,2,L  = 0
 c3364: fire_B,R,P,R,D,2,N  = 0
 c3365: 0.5 fire_B,R,P,R,D,3,P - 0.5 fire_B,R,P,R,D,3,D
        - 0.5 fire_B,R,P,R,D,3,W - 0.5 fire_B,R,P,R,D,3,C
        - 0.5 fire_B,R,P,R,D,3,I - 0.5 fire_B,R,P,R,D,3,A
        - 0.5 fire_B,R,P,R,D,3,L - 0.5 fire_B,R,P,R,D,3,N  = 0
 c3366: - 0.5 fire_B,R,P,R,D,3,P + 0.5 fire_B,R,P,R,D,3,D
        - 0.5 fire_B,R,P,R,D,3,W - 0.5 fire_B,R,P,R,D,3,C
        - 0.5 fire_B,R,P,R,D,3,I - 0.5 fire_B,R,P,R,D,3,A
        - 0.5 fire_B,R,P,R,D,3,L - 0.5 fire_B,R,P,R,D,3,N  = 0
 c3367: fire_B,R,P,R,D,3,W  = 0
 c3368: fire_B,R,P,R,D,3,C  = 0
 c3369: fire_B,R,P,R,D,3,I  = 0
 c3370: fire_B,R,P,R,D,3,A  = 0
 c3371: fire_B,R,P,R,D,3,L  = 0
 c3372: fire_B,R,P,R,D,3,N  = 0
 c3373: 0.5 fire_B,R,P,R,D,4,P - 0.5 fire_B,R,P,R,D,4,D
        - 0.5 fire_B,R,P,R,D,4,W - 0.5 fire_B,R,P,R,D,4,C
        - 0.5 fire_B,R,P,R,D,4,I - 0.5 fire_B,R,P,R,D,4,A
        - 0.5 fire_B,R,P,R,D,4,L - 0.5 fire_B,R,P,R,D,4,N  = 0
 c3374: - 0.5 fire_B,R,P,R,D,4,P + 0.5 fire_B,R,P,R,D,4,D
        - 0.5 fire_B,R,P,R,D,4,W - 0.5 fire_B,R,P,R,D,4,C
        - 0.5 fire_B,R,P,R,D,4,I - 0.5 fire_B,R,P,R,D,4,A
        - 0.5 fire_B,R,P,R,D,4,L - 0.5 fire_B,R,P,R,D,4,N  = 0
 c3375: fire_B,R,P,R,D,4,W  = 0
 c3376: fire_B,R,P,R,D,4,C  = 0
 c3377: fire_B,R,P,R,D,4,I  = 0
 c3378: fire_B,R,P,R,D,4,A  = 0
 c3379: fire_B,R,P,R,D,4,L  = 0
 c3380: fire_B,R,P,R,D,4,N  = 0
 c3381: 0.5 fire_B,R,P,R,D,5,P - 0.5 fire_B,R,P,R,D,5,D
        - 0.5 fire_B,R,P,R,D,5,W - 0.5 fire_B,R,P,R,D,5,C
        - 0.5 fire_B,R,P,R,D,5,I - 0.5 fire_B,R,P,R,D,5,A
        - 0.5 fire_B,R,P,R,D,5,L - 0.5 fire_B,R,P,R,D,5,N  = 0
 c3382: - 0.5 fire_B,R,P,R,D,5,P + 0.5 fire_B,R,P,R,D,5,D
        - 0.5 fire_B,R,P,R,D,5,W - 0.5 fire_B,R,P,R,D,5,C
        - 0.5 fire_B,R,P,R,D,5,I - 0.5 fire_B,R,P,R,D,5,A
        - 0.5 fire_B,R,P,R,D,5,L - 0.5 fire_B,R,P,R,D,5,N  = 0
 c3383: fire_B,R,P,R,D,5,W  = 0
 c3384: fire_B,R,P,R,D,5,C  = 0
 c3385: fire_B,R,P,R,D,5,I  = 0
 c3386: fire_B,R,P,R,D,5,A  = 0
 c3387: fire_B,R,P,R,D,5,L  = 0
 c3388: fire_B,R,P,R,D,5,N  = 0
 c3389: fire_B,R,P,R,W,1,P  = 0
 c3390: fire_B,R,P,R,W,1,D  = 0
 c3391: - fire_B,R,P,R,W,1,P - fire_B,R,P,R,W,1,D - fire_B,R,P,R,W,1,C
        - fire_B,R,P,R,W,1,I - fire_B,R,P,R,W,1,A - fire_B,R,P,R,W,1,L
        - fire_B,R,P,R,W,1,N  = 0
 c3392: fire_B,R,P,R,W,1,C  = 0
 c3393: fire_B,R,P,R,W,1,I  = 0
 c3394: fire_B,R,P,R,W,1,A  = 0
 c3395: fire_B,R,P,R,W,1,L  = 0
 c3396: fire_B,R,P,R,W,1,N  = 0
 c3397: fire_B,R,P,R,W,2,P  = 0
 c3398: fire_B,R,P,R,W,2,D  = 0
 c3399: - fire_B,R,P,R,W,2,P - fire_B,R,P,R,W,2,D - fire_B,R,P,R,W,2,C
        - fire_B,R,P,R,W,2,I - fire_B,R,P,R,W,2,A - fire_B,R,P,R,W,2,L
        - fire_B,R,P,R,W,2,N  = 0
 c3400: fire_B,R,P,R,W,2,C  = 0
 c3401: fire_B,R,P,R,W,2,I  = 0
 c3402: fire_B,R,P,R,W,2,A  = 0
 c3403: fire_B,R,P,R,W,2,L  = 0
 c3404: fire_B,R,P,R,W,2,N  = 0
 c3405: fire_B,R,P,R,W,3,P  = 0
 c3406: fire_B,R,P,R,W,3,D  = 0
 c3407: - fire_B,R,P,R,W,3,P - fire_B,R,P,R,W,3,D - fire_B,R,P,R,W,3,C
        - fire_B,R,P,R,W,3,I - fire_B,R,P,R,W,3,A - fire_B,R,P,R,W,3,L
        - fire_B,R,P,R,W,3,N  = 0
 c3408: fire_B,R,P,R,W,3,C  = 0
 c3409: fire_B,R,P,R,W,3,I  = 0
 c3410: fire_B,R,P,R,W,3,A  = 0
 c3411: fire_B,R,P,R,W,3,L  = 0
 c3412: fire_B,R,P,R,W,3,N  = 0
 c3413: fire_B,R,P,R,W,4,P  = 0
 c3414: fire_B,R,P,R,W,4,D  = 0
 c3415: - fire_B,R,P,R,W,4,P - fire_B,R,P,R,W,4,D - fire_B,R,P,R,W,4,C
        - fire_B,R,P,R,W,4,I - fire_B,R,P,R,W,4,A - fire_B,R,P,R,W,4,L
        - fire_B,R,P,R,W,4,N  = 0
 c3416: fire_B,R,P,R,W,4,C  = 0
 c3417: fire_B,R,P,R,W,4,I  = 0
 c3418: fire_B,R,P,R,W,4,A  = 0
 c3419: fire_B,R,P,R,W,4,L  = 0
 c3420: fire_B,R,P,R,W,4,N  = 0
 c3421: fire_B,R,P,R,W,5,P  = 0
 c3422: fire_B,R,P,R,W,5,D  = 0
 c3423: - fire_B,R,P,R,W,5,P - fire_B,R,P,R,W,5,D - fire_B,R,P,R,W,5,C
        - fire_B,R,P,R,W,5,I - fire_B,R,P,R,W,5,A - fire_B,R,P,R,W,5,L
        - fire_B,R,P,R,W,5,N  = 0
 c3424: fire_B,R,P,R,W,5,C  = 0
 c3425: fire_B,R,P,R,W,5,I  = 0
 c3426: fire_B,R,P,R,W,5,A  = 0
 c3427: fire_B,R,P,R,W,5,L  = 0
 c3428: fire_B,R,P,R,W,5,N  = 0
 c3429: fire_B,R,P,R,C,1,P  = 0
 c3430: fire_B,R,P,R,C,1,D  = 0
 c3431: fire_B,R,P,R,C,1,W  = 0
 c3432: - fire_B,R,P,R,C,1,P - fire_B,R,P,R,C,1,D - fire_B,R,P,R,C,1,W
        - fire_B,R,P,R,C,1,I - fire_B,R,P,R,C,1,A - fire_B,R,P,R,C,1,L
        - fire_B,R,P,R,C,1,N  = 0
 c3433: fire_B,R,P,R,C,1,I  = 0
 c3434: fire_B,R,P,R,C,1,A  = 0
 c3435: fire_B,R,P,R,C,1,L  = 0
 c3436: fire_B,R,P,R,C,1,N  = 0
 c3437: fire_B,R,P,R,C,2,P  = 0
 c3438: fire_B,R,P,R,C,2,D  = 0
 c3439: fire_B,R,P,R,C,2,W  = 0
 c3440: - fire_B,R,P,R,C,2,P - fire_B,R,P,R,C,2,D - fire_B,R,P,R,C,2,W
        - fire_B,R,P,R,C,2,I - fire_B,R,P,R,C,2,A - fire_B,R,P,R,C,2,L
        - fire_B,R,P,R,C,2,N  = 0
 c3441: fire_B,R,P,R,C,2,I  = 0
 c3442: fire_B,R,P,R,C,2,A  = 0
 c3443: fire_B,R,P,R,C,2,L  = 0
 c3444: fire_B,R,P,R,C,2,N  = 0
 c3445: fire_B,R,P,R,C,3,P  = 0
 c3446: fire_B,R,P,R,C,3,D  = 0
 c3447: fire_B,R,P,R,C,3,W  = 0
 c3448: - fire_B,R,P,R,C,3,P - fire_B,R,P,R,C,3,D - fire_B,R,P,R,C,3,W
        - fire_B,R,P,R,C,3,I - fire_B,R,P,R,C,3,A - fire_B,R,P,R,C,3,L
        - fire_B,R,P,R,C,3,N  = 0
 c3449: fire_B,R,P,R,C,3,I  = 0
 c3450: fire_B,R,P,R,C,3,A  = 0
 c3451: fire_B,R,P,R,C,3,L  = 0
 c3452: fire_B,R,P,R,C,3,N  = 0
 c3453: fire_B,R,P,R,C,4,P  = 0
 c3454: fire_B,R,P,R,C,4,D  = 0
 c3455: fire_B,R,P,R,C,4,W  = 0
 c3456: - fire_B,R,P,R,C,4,P - fire_B,R,P,R,C,4,D - fire_B,R,P,R,C,4,W
        - fire_B,R,P,R,C,4,I - fire_B,R,P,R,C,4,A - fire_B,R,P,R,C,4,L
        - fire_B,R,P,R,C,4,N  = 0
 c3457: fire_B,R,P,R,C,4,I  = 0
 c3458: fire_B,R,P,R,C,4,A  = 0
 c3459: fire_B,R,P,R,C,4,L  = 0
 c3460: fire_B,R,P,R,C,4,N  = 0
 c3461: fire_B,R,P,R,C,5,P  = 0
 c3462: fire_B,R,P,R,C,5,D  = 0
 c3463: fire_B,R,P,R,C,5,W  = 0
 c3464: - fire_B,R,P,R,C,5,P - fire_B,R,P,R,C,5,D - fire_B,R,P,R,C,5,W
        - fire_B,R,P,R,C,5,I - fire_B,R,P,R,C,5,A - fire_B,R,P,R,C,5,L
        - fire_B,R,P,R,C,5,N  = 0
 c3465: fire_B,R,P,R,C,5,I  = 0
 c3466: fire_B,R,P,R,C,5,A  = 0
 c3467: fire_B,R,P,R,C,5,L  = 0
 c3468: fire_B,R,P,R,C,5,N  = 0
 c3469: fire_B,R,P,R,I,1,P  = 0
 c3470: fire_B,R,P,R,I,1,D  = 0
 c3471: fire_B,R,P,R,I,1,W  = 0
 c3472: fire_B,R,P,R,I,1,C  = 0
 c3473: - fire_B,R,P,R,I,1,P - fire_B,R,P,R,I,1,D - fire_B,R,P,R,I,1,W
        - fire_B,R,P,R,I,1,C - fire_B,R,P,R,I,1,A - fire_B,R,P,R,I,1,L
        - fire_B,R,P,R,I,1,N  = 0
 c3474: fire_B,R,P,R,I,1,A  = 0
 c3475: fire_B,R,P,R,I,1,L  = 0
 c3476: fire_B,R,P,R,I,1,N  = 0
 c3477: fire_B,R,P,R,I,2,P  = 0
 c3478: fire_B,R,P,R,I,2,D  = 0
 c3479: fire_B,R,P,R,I,2,W  = 0
 c3480: fire_B,R,P,R,I,2,C  = 0
 c3481: - fire_B,R,P,R,I,2,P - fire_B,R,P,R,I,2,D - fire_B,R,P,R,I,2,W
        - fire_B,R,P,R,I,2,C - fire_B,R,P,R,I,2,A - fire_B,R,P,R,I,2,L
        - fire_B,R,P,R,I,2,N  = 0
 c3482: fire_B,R,P,R,I,2,A  = 0
 c3483: fire_B,R,P,R,I,2,L  = 0
 c3484: fire_B,R,P,R,I,2,N  = 0
 c3485: fire_B,R,P,R,I,3,P  = 0
 c3486: fire_B,R,P,R,I,3,D  = 0
 c3487: fire_B,R,P,R,I,3,W  = 0
 c3488: fire_B,R,P,R,I,3,C  = 0
 c3489: - fire_B,R,P,R,I,3,P - fire_B,R,P,R,I,3,D - fire_B,R,P,R,I,3,W
        - fire_B,R,P,R,I,3,C - fire_B,R,P,R,I,3,A - fire_B,R,P,R,I,3,L
        - fire_B,R,P,R,I,3,N  = 0
 c3490: fire_B,R,P,R,I,3,A  = 0
 c3491: fire_B,R,P,R,I,3,L  = 0
 c3492: fire_B,R,P,R,I,3,N  = 0
 c3493: fire_B,R,P,R,I,4,P  = 0
 c3494: fire_B,R,P,R,I,4,D  = 0
 c3495: fire_B,R,P,R,I,4,W  = 0
 c3496: fire_B,R,P,R,I,4,C  = 0
 c3497: - fire_B,R,P,R,I,4,P - fire_B,R,P,R,I,4,D - fire_B,R,P,R,I,4,W
        - fire_B,R,P,R,I,4,C - fire_B,R,P,R,I,4,A - fire_B,R,P,R,I,4,L
        - fire_B,R,P,R,I,4,N  = 0
 c3498: fire_B,R,P,R,I,4,A  = 0
 c3499: fire_B,R,P,R,I,4,L  = 0
 c3500: fire_B,R,P,R,I,4,N  = 0
 c3501: fire_B,R,P,R,I,5,P  = 0
 c3502: fire_B,R,P,R,I,5,D  = 0
 c3503: fire_B,R,P,R,I,5,W  = 0
 c3504: fire_B,R,P,R,I,5,C  = 0
 c3505: - fire_B,R,P,R,I,5,P - fire_B,R,P,R,I,5,D - fire_B,R,P,R,I,5,W
        - fire_B,R,P,R,I,5,C - fire_B,R,P,R,I,5,A - fire_B,R,P,R,I,5,L
        - fire_B,R,P,R,I,5,N  = 0
 c3506: fire_B,R,P,R,I,5,A  = 0
 c3507: fire_B,R,P,R,I,5,L  = 0
 c3508: fire_B,R,P,R,I,5,N  = 0
 c3509: fire_B,R,P,R,A,1,P  = 0
 c3510: fire_B,R,P,R,A,1,D  = 0
 c3511: fire_B,R,P,R,A,1,W  = 0
 c3512: fire_B,R,P,R,A,1,C  = 0
 c3513: fire_B,R,P,R,A,1,I  = 0
 c3514: - fire_B,R,P,R,A,1,P - fire_B,R,P,R,A,1,D - fire_B,R,P,R,A,1,W
        - fire_B,R,P,R,A,1,C - fire_B,R,P,R,A,1,I - fire_B,R,P,R,A,1,L
        - fire_B,R,P,R,A,1,N  = 0
 c3515: fire_B,R,P,R,A,1,L  = 0
 c3516: fire_B,R,P,R,A,1,N  = 0
 c3517: fire_B,R,P,R,A,2,P  = 0
 c3518: fire_B,R,P,R,A,2,D  = 0
 c3519: fire_B,R,P,R,A,2,W  = 0
 c3520: fire_B,R,P,R,A,2,C  = 0
 c3521: fire_B,R,P,R,A,2,I  = 0
 c3522: - fire_B,R,P,R,A,2,P - fire_B,R,P,R,A,2,D - fire_B,R,P,R,A,2,W
        - fire_B,R,P,R,A,2,C - fire_B,R,P,R,A,2,I - fire_B,R,P,R,A,2,L
        - fire_B,R,P,R,A,2,N  = 0
 c3523: fire_B,R,P,R,A,2,L  = 0
 c3524: fire_B,R,P,R,A,2,N  = 0
 c3525: fire_B,R,P,R,A,3,P  = 0
 c3526: fire_B,R,P,R,A,3,D  = 0
 c3527: fire_B,R,P,R,A,3,W  = 0
 c3528: fire_B,R,P,R,A,3,C  = 0
 c3529: fire_B,R,P,R,A,3,I  = 0
 c3530: - fire_B,R,P,R,A,3,P - fire_B,R,P,R,A,3,D - fire_B,R,P,R,A,3,W
        - fire_B,R,P,R,A,3,C - fire_B,R,P,R,A,3,I - fire_B,R,P,R,A,3,L
        - fire_B,R,P,R,A,3,N  = 0
 c3531: fire_B,R,P,R,A,3,L  = 0
 c3532: fire_B,R,P,R,A,3,N  = 0
 c3533: fire_B,R,P,R,A,4,P  = 0
 c3534: fire_B,R,P,R,A,4,D  = 0
 c3535: fire_B,R,P,R,A,4,W  = 0
 c3536: fire_B,R,P,R,A,4,C  = 0
 c3537: fire_B,R,P,R,A,4,I  = 0
 c3538: - fire_B,R,P,R,A,4,P - fire_B,R,P,R,A,4,D - fire_B,R,P,R,A,4,W
        - fire_B,R,P,R,A,4,C - fire_B,R,P,R,A,4,I - fire_B,R,P,R,A,4,L
        - fire_B,R,P,R,A,4,N  = 0
 c3539: fire_B,R,P,R,A,4,L  = 0
 c3540: fire_B,R,P,R,A,4,N  = 0
 c3541: fire_B,R,P,R,A,5,P  = 0
 c3542: fire_B,R,P,R,A,5,D  = 0
 c3543: fire_B,R,P,R,A,5,W  = 0
 c3544: fire_B,R,P,R,A,5,C  = 0
 c3545: fire_B,R,P,R,A,5,I  = 0
 c3546: - fire_B,R,P,R,A,5,P - fire_B,R,P,R,A,5,D - fire_B,R,P,R,A,5,W
        - fire_B,R,P,R,A,5,C - fire_B,R,P,R,A,5,I - fire_B,R,P,R,A,5,L
        - fire_B,R,P,R,A,5,N  = 0
 c3547: fire_B,R,P,R,A,5,L  = 0
 c3548: fire_B,R,P,R,A,5,N  = 0
 c3549: fire_B,R,P,R,L,1,P  = 0
 c3550: fire_B,R,P,R,L,1,D  = 0
 c3551: fire_B,R,P,R,L,1,W  = 0
 c3552: fire_B,R,P,R,L,1,C  = 0
 c3553: fire_B,R,P,R,L,1,I  = 0
 c3554: fire_B,R,P,R,L,1,A  = 0
 c3555: - fire_B,R,P,R,L,1,P - fire_B,R,P,R,L,1,D - fire_B,R,P,R,L,1,W
        - fire_B,R,P,R,L,1,C - fire_B,R,P,R,L,1,I - fire_B,R,P,R,L,1,A
        - fire_B,R,P,R,L,1,N  = 0
 c3556: fire_B,R,P,R,L,1,N  = 0
 c3557: fire_B,R,P,R,L,2,P  = 0
 c3558: fire_B,R,P,R,L,2,D  = 0
 c3559: fire_B,R,P,R,L,2,W  = 0
 c3560: fire_B,R,P,R,L,2,C  = 0
 c3561: fire_B,R,P,R,L,2,I  = 0
 c3562: fire_B,R,P,R,L,2,A  = 0
 c3563: - fire_B,R,P,R,L,2,P - fire_B,R,P,R,L,2,D - fire_B,R,P,R,L,2,W
        - fire_B,R,P,R,L,2,C - fire_B,R,P,R,L,2,I - fire_B,R,P,R,L,2,A
        - fire_B,R,P,R,L,2,N  = 0
 c3564: fire_B,R,P,R,L,2,N  = 0
 c3565: fire_B,R,P,R,L,3,P  = 0
 c3566: fire_B,R,P,R,L,3,D  = 0
 c3567: fire_B,R,P,R,L,3,W  = 0
 c3568: fire_B,R,P,R,L,3,C  = 0
 c3569: fire_B,R,P,R,L,3,I  = 0
 c3570: fire_B,R,P,R,L,3,A  = 0
 c3571: - fire_B,R,P,R,L,3,P - fire_B,R,P,R,L,3,D - fire_B,R,P,R,L,3,W
        - fire_B,R,P,R,L,3,C - fire_B,R,P,R,L,3,I - fire_B,R,P,R,L,3,A
        - fire_B,R,P,R,L,3,N  = 0
 c3572: fire_B,R,P,R,L,3,N  = 0
 c3573: fire_B,R,P,R,L,4,P  = 0
 c3574: fire_B,R,P,R,L,4,D  = 0
 c3575: fire_B,R,P,R,L,4,W  = 0
 c3576: fire_B,R,P,R,L,4,C  = 0
 c3577: fire_B,R,P,R,L,4,I  = 0
 c3578: fire_B,R,P,R,L,4,A  = 0
 c3579: - fire_B,R,P,R,L,4,P - fire_B,R,P,R,L,4,D - fire_B,R,P,R,L,4,W
        - fire_B,R,P,R,L,4,C - fire_B,R,P,R,L,4,I - fire_B,R,P,R,L,4,A
        - fire_B,R,P,R,L,4,N  = 0
 c3580: fire_B,R,P,R,L,4,N  = 0
 c3581: fire_B,R,P,R,L,5,P  = 0
 c3582: fire_B,R,P,R,L,5,D  = 0
 c3583: fire_B,R,P,R,L,5,W  = 0
 c3584: fire_B,R,P,R,L,5,C  = 0
 c3585: fire_B,R,P,R,L,5,I  = 0
 c3586: fire_B,R,P,R,L,5,A  = 0
 c3587: - fire_B,R,P,R,L,5,P - fire_B,R,P,R,L,5,D - fire_B,R,P,R,L,5,W
        - fire_B,R,P,R,L,5,C - fire_B,R,P,R,L,5,I - fire_B,R,P,R,L,5,A
        - fire_B,R,P,R,L,5,N  = 0
 c3588: fire_B,R,P,R,L,5,N  = 0
 c3589: fire_B,R,P,R,N,1,P  = 0
 c3590: fire_B,R,P,R,N,1,D  = 0
 c3591: fire_B,R,P,R,N,1,W  = 0
 c3592: fire_B,R,P,R,N,1,C  = 0
 c3593: fire_B,R,P,R,N,1,I  = 0
 c3594: fire_B,R,P,R,N,1,A  = 0
 c3595: fire_B,R,P,R,N,1,L  = 0
 c3596: - fire_B,R,P,R,N,1,P - fire_B,R,P,R,N,1,D - fire_B,R,P,R,N,1,W
        - fire_B,R,P,R,N,1,C - fire_B,R,P,R,N,1,I - fire_B,R,P,R,N,1,A
        - fire_B,R,P,R,N,1,L  = 0
 c3597: fire_B,R,P,R,N,2,P  = 0
 c3598: fire_B,R,P,R,N,2,D  = 0
 c3599: fire_B,R,P,R,N,2,W  = 0
 c3600: fire_B,R,P,R,N,2,C  = 0
 c3601: fire_B,R,P,R,N,2,I  = 0
 c3602: fire_B,R,P,R,N,2,A  = 0
 c3603: fire_B,R,P,R,N,2,L  = 0
 c3604: - fire_B,R,P,R,N,2,P - fire_B,R,P,R,N,2,D - fire_B,R,P,R,N,2,W
        - fire_B,R,P,R,N,2,C - fire_B,R,P,R,N,2,I - fire_B,R,P,R,N,2,A
        - fire_B,R,P,R,N,2,L  = 0
 c3605: fire_B,R,P,R,N,3,P  = 0
 c3606: fire_B,R,P,R,N,3,D  = 0
 c3607: fire_B,R,P,R,N,3,W  = 0
 c3608: fire_B,R,P,R,N,3,C  = 0
 c3609: fire_B,R,P,R,N,3,I  = 0
 c3610: fire_B,R,P,R,N,3,A  = 0
 c3611: fire_B,R,P,R,N,3,L  = 0
 c3612: - fire_B,R,P,R,N,3,P - fire_B,R,P,R,N,3,D - fire_B,R,P,R,N,3,W
        - fire_B,R,P,R,N,3,C - fire_B,R,P,R,N,3,I - fire_B,R,P,R,N,3,A
        - fire_B,R,P,R,N,3,L  = 0
 c3613: fire_B,R,P,R,N,4,P  = 0
 c3614: fire_B,R,P,R,N,4,D  = 0
 c3615: fire_B,R,P,R,N,4,W  = 0
 c3616: fire_B,R,P,R,N,4,C  = 0
 c3617: fire_B,R,P,R,N,4,I  = 0
 c3618: fire_B,R,P,R,N,4,A  = 0
 c3619: fire_B,R,P,R,N,4,L  = 0
 c3620: - fire_B,R,P,R,N,4,P - fire_B,R,P,R,N,4,D - fire_B,R,P,R,N,4,W
        - fire_B,R,P,R,N,4,C - fire_B,R,P,R,N,4,I - fire_B,R,P,R,N,4,A
        - fire_B,R,P,R,N,4,L  = 0
 c3621: fire_B,R,P,R,N,5,P  = 0
 c3622: fire_B,R,P,R,N,5,D  = 0
 c3623: fire_B,R,P,R,N,5,W  = 0
 c3624: fire_B,R,P,R,N,5,C  = 0
 c3625: fire_B,R,P,R,N,5,I  = 0
 c3626: fire_B,R,P,R,N,5,A  = 0
 c3627: fire_B,R,P,R,N,5,L  = 0
 c3628: - fire_B,R,P,R,N,5,P - fire_B,R,P,R,N,5,D - fire_B,R,P,R,N,5,W
        - fire_B,R,P,R,N,5,C - fire_B,R,P,R,N,5,I - fire_B,R,P,R,N,5,A
        - fire_B,R,P,R,N,5,L  = 0
 c3629: 0.9 fire_B,N,N,L,P,1,P - 0.1 fire_B,N,N,L,P,1,D
        - 0.1 fire_B,N,N,L,P,1,W - 0.1 fire_B,N,N,L,P,1,C
        - 0.1 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        - 0.1 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3630: - 0.1 fire_B,N,N,L,P,1,P + 0.9 fire_B,N,N,L,P,1,D
        - 0.1 fire_B,N,N,L,P,1,W - 0.1 fire_B,N,N,L,P,1,C
        - 0.1 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        - 0.1 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3631: - 0.1 fire_B,N,N,L,P,1,P - 0.1 fire_B,N,N,L,P,1,D
        + 0.9 fire_B,N,N,L,P,1,W - 0.1 fire_B,N,N,L,P,1,C
        - 0.1 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        - 0.1 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3632: - 0.1 fire_B,N,N,L,P,1,P - 0.1 fire_B,N,N,L,P,1,D
        - 0.1 fire_B,N,N,L,P,1,W + 0.9 fire_B,N,N,L,P,1,C
        - 0.1 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        - 0.1 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3633: - 0.1 fire_B,N,N,L,P,1,P - 0.1 fire_B,N,N,L,P,1,D
        - 0.1 fire_B,N,N,L,P,1,W - 0.1 fire_B,N,N,L,P,1,C
        + 0.9 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        - 0.1 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3634: - 0.2 fire_B,N,N,L,P,1,P - 0.2 fire_B,N,N,L,P,1,D
        - 0.2 fire_B,N,N,L,P,1,W - 0.2 fire_B,N,N,L,P,1,C
        - 0.2 fire_B,N,N,L,P,1,I + 0.8 fire_B,N,N,L,P,1,A
        - 0.2 fire_B,N,N,L,P,1,L - 0.2 fire_B,N,N,L,P,1,N  = 0
 c3635: - 0.1 fire_B,N,N,L,P,1,P - 0.1 fire_B,N,N,L,P,1,D
        - 0.1 fire_B,N,N,L,P,1,W - 0.1 fire_B,N,N,L,P,1,C
        - 0.1 fire_B,N,N,L,P,1,I - 0.1 fire_B,N,N,L,P,1,A
        + 0.9 fire_B,N,N,L,P,1,L - 0.1 fire_B,N,N,L,P,1,N  = 0
 c3636: - 0.2 fire_B,N,N,L,P,1,P - 0.2 fire_B,N,N,L,P,1,D
        - 0.2 fire_B,N,N,L,P,1,W - 0.2 fire_B,N,N,L,P,1,C
        - 0.2 fire_B,N,N,L,P,1,I - 0.2 fire_B,N,N,L,P,1,A
        - 0.2 fire_B,N,N,L,P,1,L + 0.8 fire_B,N,N,L,P,1,N  = 0
 c3637: 0.9 fire_B,N,N,L,P,2,P - 0.1 fire_B,N,N,L,P,2,D
        - 0.1 fire_B,N,N,L,P,2,W - 0.1 fire_B,N,N,L,P,2,C
        - 0.1 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        - 0.1 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3638: - 0.1 fire_B,N,N,L,P,2,P + 0.9 fire_B,N,N,L,P,2,D
        - 0.1 fire_B,N,N,L,P,2,W - 0.1 fire_B,N,N,L,P,2,C
        - 0.1 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        - 0.1 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3639: - 0.1 fire_B,N,N,L,P,2,P - 0.1 fire_B,N,N,L,P,2,D
        + 0.9 fire_B,N,N,L,P,2,W - 0.1 fire_B,N,N,L,P,2,C
        - 0.1 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        - 0.1 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3640: - 0.1 fire_B,N,N,L,P,2,P - 0.1 fire_B,N,N,L,P,2,D
        - 0.1 fire_B,N,N,L,P,2,W + 0.9 fire_B,N,N,L,P,2,C
        - 0.1 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        - 0.1 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3641: - 0.1 fire_B,N,N,L,P,2,P - 0.1 fire_B,N,N,L,P,2,D
        - 0.1 fire_B,N,N,L,P,2,W - 0.1 fire_B,N,N,L,P,2,C
        + 0.9 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        - 0.1 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3642: - 0.2 fire_B,N,N,L,P,2,P - 0.2 fire_B,N,N,L,P,2,D
        - 0.2 fire_B,N,N,L,P,2,W - 0.2 fire_B,N,N,L,P,2,C
        - 0.2 fire_B,N,N,L,P,2,I + 0.8 fire_B,N,N,L,P,2,A
        - 0.2 fire_B,N,N,L,P,2,L - 0.2 fire_B,N,N,L,P,2,N  = 0
 c3643: - 0.1 fire_B,N,N,L,P,2,P - 0.1 fire_B,N,N,L,P,2,D
        - 0.1 fire_B,N,N,L,P,2,W - 0.1 fire_B,N,N,L,P,2,C
        - 0.1 fire_B,N,N,L,P,2,I - 0.1 fire_B,N,N,L,P,2,A
        + 0.9 fire_B,N,N,L,P,2,L - 0.1 fire_B,N,N,L,P,2,N  = 0
 c3644: - 0.2 fire_B,N,N,L,P,2,P - 0.2 fire_B,N,N,L,P,2,D
        - 0.2 fire_B,N,N,L,P,2,W - 0.2 fire_B,N,N,L,P,2,C
        - 0.2 fire_B,N,N,L,P,2,I - 0.2 fire_B,N,N,L,P,2,A
        - 0.2 fire_B,N,N,L,P,2,L + 0.8 fire_B,N,N,L,P,2,N  = 0
 c3645: 0.9 fire_B,N,N,L,P,3,P - 0.1 fire_B,N,N,L,P,3,D
        - 0.1 fire_B,N,N,L,P,3,W - 0.1 fire_B,N,N,L,P,3,C
        - 0.1 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        - 0.1 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3646: - 0.1 fire_B,N,N,L,P,3,P + 0.9 fire_B,N,N,L,P,3,D
        - 0.1 fire_B,N,N,L,P,3,W - 0.1 fire_B,N,N,L,P,3,C
        - 0.1 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        - 0.1 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3647: - 0.1 fire_B,N,N,L,P,3,P - 0.1 fire_B,N,N,L,P,3,D
        + 0.9 fire_B,N,N,L,P,3,W - 0.1 fire_B,N,N,L,P,3,C
        - 0.1 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        - 0.1 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3648: - 0.1 fire_B,N,N,L,P,3,P - 0.1 fire_B,N,N,L,P,3,D
        - 0.1 fire_B,N,N,L,P,3,W + 0.9 fire_B,N,N,L,P,3,C
        - 0.1 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        - 0.1 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3649: - 0.1 fire_B,N,N,L,P,3,P - 0.1 fire_B,N,N,L,P,3,D
        - 0.1 fire_B,N,N,L,P,3,W - 0.1 fire_B,N,N,L,P,3,C
        + 0.9 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        - 0.1 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3650: - 0.2 fire_B,N,N,L,P,3,P - 0.2 fire_B,N,N,L,P,3,D
        - 0.2 fire_B,N,N,L,P,3,W - 0.2 fire_B,N,N,L,P,3,C
        - 0.2 fire_B,N,N,L,P,3,I + 0.8 fire_B,N,N,L,P,3,A
        - 0.2 fire_B,N,N,L,P,3,L - 0.2 fire_B,N,N,L,P,3,N  = 0
 c3651: - 0.1 fire_B,N,N,L,P,3,P - 0.1 fire_B,N,N,L,P,3,D
        - 0.1 fire_B,N,N,L,P,3,W - 0.1 fire_B,N,N,L,P,3,C
        - 0.1 fire_B,N,N,L,P,3,I - 0.1 fire_B,N,N,L,P,3,A
        + 0.9 fire_B,N,N,L,P,3,L - 0.1 fire_B,N,N,L,P,3,N  = 0
 c3652: - 0.2 fire_B,N,N,L,P,3,P - 0.2 fire_B,N,N,L,P,3,D
        - 0.2 fire_B,N,N,L,P,3,W - 0.2 fire_B,N,N,L,P,3,C
        - 0.2 fire_B,N,N,L,P,3,I - 0.2 fire_B,N,N,L,P,3,A
        - 0.2 fire_B,N,N,L,P,3,L + 0.8 fire_B,N,N,L,P,3,N  = 0
 c3653: 0.9 fire_B,N,N,L,P,4,P - 0.1 fire_B,N,N,L,P,4,D
        - 0.1 fire_B,N,N,L,P,4,W - 0.1 fire_B,N,N,L,P,4,C
        - 0.1 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        - 0.1 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3654: - 0.1 fire_B,N,N,L,P,4,P + 0.9 fire_B,N,N,L,P,4,D
        - 0.1 fire_B,N,N,L,P,4,W - 0.1 fire_B,N,N,L,P,4,C
        - 0.1 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        - 0.1 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3655: - 0.1 fire_B,N,N,L,P,4,P - 0.1 fire_B,N,N,L,P,4,D
        + 0.9 fire_B,N,N,L,P,4,W - 0.1 fire_B,N,N,L,P,4,C
        - 0.1 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        - 0.1 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3656: - 0.1 fire_B,N,N,L,P,4,P - 0.1 fire_B,N,N,L,P,4,D
        - 0.1 fire_B,N,N,L,P,4,W + 0.9 fire_B,N,N,L,P,4,C
        - 0.1 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        - 0.1 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3657: - 0.1 fire_B,N,N,L,P,4,P - 0.1 fire_B,N,N,L,P,4,D
        - 0.1 fire_B,N,N,L,P,4,W - 0.1 fire_B,N,N,L,P,4,C
        + 0.9 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        - 0.1 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3658: - 0.2 fire_B,N,N,L,P,4,P - 0.2 fire_B,N,N,L,P,4,D
        - 0.2 fire_B,N,N,L,P,4,W - 0.2 fire_B,N,N,L,P,4,C
        - 0.2 fire_B,N,N,L,P,4,I + 0.8 fire_B,N,N,L,P,4,A
        - 0.2 fire_B,N,N,L,P,4,L - 0.2 fire_B,N,N,L,P,4,N  = 0
 c3659: - 0.1 fire_B,N,N,L,P,4,P - 0.1 fire_B,N,N,L,P,4,D
        - 0.1 fire_B,N,N,L,P,4,W - 0.1 fire_B,N,N,L,P,4,C
        - 0.1 fire_B,N,N,L,P,4,I - 0.1 fire_B,N,N,L,P,4,A
        + 0.9 fire_B,N,N,L,P,4,L - 0.1 fire_B,N,N,L,P,4,N  = 0
 c3660: - 0.2 fire_B,N,N,L,P,4,P - 0.2 fire_B,N,N,L,P,4,D
        - 0.2 fire_B,N,N,L,P,4,W - 0.2 fire_B,N,N,L,P,4,C
        - 0.2 fire_B,N,N,L,P,4,I - 0.2 fire_B,N,N,L,P,4,A
        - 0.2 fire_B,N,N,L,P,4,L + 0.8 fire_B,N,N,L,P,4,N  = 0
 c3661: 0.9 fire_B,N,N,L,P,5,P - 0.1 fire_B,N,N,L,P,5,D
        - 0.1 fire_B,N,N,L,P,5,W - 0.1 fire_B,N,N,L,P,5,C
        - 0.1 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        - 0.1 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3662: - 0.1 fire_B,N,N,L,P,5,P + 0.9 fire_B,N,N,L,P,5,D
        - 0.1 fire_B,N,N,L,P,5,W - 0.1 fire_B,N,N,L,P,5,C
        - 0.1 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        - 0.1 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3663: - 0.1 fire_B,N,N,L,P,5,P - 0.1 fire_B,N,N,L,P,5,D
        + 0.9 fire_B,N,N,L,P,5,W - 0.1 fire_B,N,N,L,P,5,C
        - 0.1 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        - 0.1 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3664: - 0.1 fire_B,N,N,L,P,5,P - 0.1 fire_B,N,N,L,P,5,D
        - 0.1 fire_B,N,N,L,P,5,W + 0.9 fire_B,N,N,L,P,5,C
        - 0.1 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        - 0.1 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3665: - 0.1 fire_B,N,N,L,P,5,P - 0.1 fire_B,N,N,L,P,5,D
        - 0.1 fire_B,N,N,L,P,5,W - 0.1 fire_B,N,N,L,P,5,C
        + 0.9 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        - 0.1 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3666: - 0.2 fire_B,N,N,L,P,5,P - 0.2 fire_B,N,N,L,P,5,D
        - 0.2 fire_B,N,N,L,P,5,W - 0.2 fire_B,N,N,L,P,5,C
        - 0.2 fire_B,N,N,L,P,5,I + 0.8 fire_B,N,N,L,P,5,A
        - 0.2 fire_B,N,N,L,P,5,L - 0.2 fire_B,N,N,L,P,5,N  = 0
 c3667: - 0.1 fire_B,N,N,L,P,5,P - 0.1 fire_B,N,N,L,P,5,D
        - 0.1 fire_B,N,N,L,P,5,W - 0.1 fire_B,N,N,L,P,5,C
        - 0.1 fire_B,N,N,L,P,5,I - 0.1 fire_B,N,N,L,P,5,A
        + 0.9 fire_B,N,N,L,P,5,L - 0.1 fire_B,N,N,L,P,5,N  = 0
 c3668: - 0.2 fire_B,N,N,L,P,5,P - 0.2 fire_B,N,N,L,P,5,D
        - 0.2 fire_B,N,N,L,P,5,W - 0.2 fire_B,N,N,L,P,5,C
        - 0.2 fire_B,N,N,L,P,5,I - 0.2 fire_B,N,N,L,P,5,A
        - 0.2 fire_B,N,N,L,P,5,L + 0.8 fire_B,N,N,L,P,5,N  = 0
 c3669: 0.5 fire_B,N,N,L,D,1,P - 0.5 fire_B,N,N,L,D,1,D
        - 0.5 fire_B,N,N,L,D,1,W - 0.5 fire_B,N,N,L,D,1,C
        - 0.5 fire_B,N,N,L,D,1,I - 0.5 fire_B,N,N,L,D,1,A
        - 0.5 fire_B,N,N,L,D,1,L - 0.5 fire_B,N,N,L,D,1,N  = 0
 c3670: - 0.5 fire_B,N,N,L,D,1,P + 0.5 fire_B,N,N,L,D,1,D
        - 0.5 fire_B,N,N,L,D,1,W - 0.5 fire_B,N,N,L,D,1,C
        - 0.5 fire_B,N,N,L,D,1,I - 0.5 fire_B,N,N,L,D,1,A
        - 0.5 fire_B,N,N,L,D,1,L - 0.5 fire_B,N,N,L,D,1,N  = 0
 c3671: fire_B,N,N,L,D,1,W  = 0
 c3672: fire_B,N,N,L,D,1,C  = 0
 c3673: fire_B,N,N,L,D,1,I  = 0
 c3674: fire_B,N,N,L,D,1,A  = 0
 c3675: fire_B,N,N,L,D,1,L  = 0
 c3676: fire_B,N,N,L,D,1,N  = 0
 c3677: 0.5 fire_B,N,N,L,D,2,P - 0.5 fire_B,N,N,L,D,2,D
        - 0.5 fire_B,N,N,L,D,2,W - 0.5 fire_B,N,N,L,D,2,C
        - 0.5 fire_B,N,N,L,D,2,I - 0.5 fire_B,N,N,L,D,2,A
        - 0.5 fire_B,N,N,L,D,2,L - 0.5 fire_B,N,N,L,D,2,N  = 0
 c3678: - 0.5 fire_B,N,N,L,D,2,P + 0.5 fire_B,N,N,L,D,2,D
        - 0.5 fire_B,N,N,L,D,2,W - 0.5 fire_B,N,N,L,D,2,C
        - 0.5 fire_B,N,N,L,D,2,I - 0.5 fire_B,N,N,L,D,2,A
        - 0.5 fire_B,N,N,L,D,2,L - 0.5 fire_B,N,N,L,D,2,N  = 0
 c3679: fire_B,N,N,L,D,2,W  = 0
 c3680: fire_B,N,N,L,D,2,C  = 0
 c3681: fire_B,N,N,L,D,2,I  = 0
 c3682: fire_B,N,N,L,D,2,A  = 0
 c3683: fire_B,N,N,L,D,2,L  = 0
 c3684: fire_B,N,N,L,D,2,N  = 0
 c3685: 0.5 fire_B,N,N,L,D,3,P - 0.5 fire_B,N,N,L,D,3,D
        - 0.5 fire_B,N,N,L,D,3,W - 0.5 fire_B,N,N,L,D,3,C
        - 0.5 fire_B,N,N,L,D,3,I - 0.5 fire_B,N,N,L,D,3,A
        - 0.5 fire_B,N,N,L,D,3,L - 0.5 fire_B,N,N,L,D,3,N  = 0
 c3686: - 0.5 fire_B,N,N,L,D,3,P + 0.5 fire_B,N,N,L,D,3,D
        - 0.5 fire_B,N,N,L,D,3,W - 0.5 fire_B,N,N,L,D,3,C
        - 0.5 fire_B,N,N,L,D,3,I - 0.5 fire_B,N,N,L,D,3,A
        - 0.5 fire_B,N,N,L,D,3,L - 0.5 fire_B,N,N,L,D,3,N  = 0
 c3687: fire_B,N,N,L,D,3,W  = 0
 c3688: fire_B,N,N,L,D,3,C  = 0
 c3689: fire_B,N,N,L,D,3,I  = 0
 c3690: fire_B,N,N,L,D,3,A  = 0
 c3691: fire_B,N,N,L,D,3,L  = 0
 c3692: fire_B,N,N,L,D,3,N  = 0
 c3693: 0.5 fire_B,N,N,L,D,4,P - 0.5 fire_B,N,N,L,D,4,D
        - 0.5 fire_B,N,N,L,D,4,W - 0.5 fire_B,N,N,L,D,4,C
        - 0.5 fire_B,N,N,L,D,4,I - 0.5 fire_B,N,N,L,D,4,A
        - 0.5 fire_B,N,N,L,D,4,L - 0.5 fire_B,N,N,L,D,4,N  = 0
 c3694: - 0.5 fire_B,N,N,L,D,4,P + 0.5 fire_B,N,N,L,D,4,D
        - 0.5 fire_B,N,N,L,D,4,W - 0.5 fire_B,N,N,L,D,4,C
        - 0.5 fire_B,N,N,L,D,4,I - 0.5 fire_B,N,N,L,D,4,A
        - 0.5 fire_B,N,N,L,D,4,L - 0.5 fire_B,N,N,L,D,4,N  = 0
 c3695: fire_B,N,N,L,D,4,W  = 0
 c3696: fire_B,N,N,L,D,4,C  = 0
 c3697: fire_B,N,N,L,D,4,I  = 0
 c3698: fire_B,N,N,L,D,4,A  = 0
 c3699: fire_B,N,N,L,D,4,L  = 0
 c3700: fire_B,N,N,L,D,4,N  = 0
 c3701: 0.5 fire_B,N,N,L,D,5,P - 0.5 fire_B,N,N,L,D,5,D
        - 0.5 fire_B,N,N,L,D,5,W - 0.5 fire_B,N,N,L,D,5,C
        - 0.5 fire_B,N,N,L,D,5,I - 0.5 fire_B,N,N,L,D,5,A
        - 0.5 fire_B,N,N,L,D,5,L - 0.5 fire_B,N,N,L,D,5,N  = 0
 c3702: - 0.5 fire_B,N,N,L,D,5,P + 0.5 fire_B,N,N,L,D,5,D
        - 0.5 fire_B,N,N,L,D,5,W - 0.5 fire_B,N,N,L,D,5,C
        - 0.5 fire_B,N,N,L,D,5,I - 0.5 fire_B,N,N,L,D,5,A
        - 0.5 fire_B,N,N,L,D,5,L - 0.5 fire_B,N,N,L,D,5,N  = 0
 c3703: fire_B,N,N,L,D,5,W  = 0
 c3704: fire_B,N,N,L,D,5,C  = 0
 c3705: fire_B,N,N,L,D,5,I  = 0
 c3706: fire_B,N,N,L,D,5,A  = 0
 c3707: fire_B,N,N,L,D,5,L  = 0
 c3708: fire_B,N,N,L,D,5,N  = 0
 c3709: fire_B,N,N,L,W,1,P  = 0
 c3710: fire_B,N,N,L,W,1,D  = 0
 c3711: - fire_B,N,N,L,W,1,P - fire_B,N,N,L,W,1,D - fire_B,N,N,L,W,1,C
        - fire_B,N,N,L,W,1,I - fire_B,N,N,L,W,1,A - fire_B,N,N,L,W,1,L
        - fire_B,N,N,L,W,1,N  = 0
 c3712: fire_B,N,N,L,W,1,C  = 0
 c3713: fire_B,N,N,L,W,1,I  = 0
 c3714: fire_B,N,N,L,W,1,A  = 0
 c3715: fire_B,N,N,L,W,1,L  = 0
 c3716: fire_B,N,N,L,W,1,N  = 0
 c3717: fire_B,N,N,L,W,2,P  = 0
 c3718: fire_B,N,N,L,W,2,D  = 0
 c3719: - fire_B,N,N,L,W,2,P - fire_B,N,N,L,W,2,D - fire_B,N,N,L,W,2,C
        - fire_B,N,N,L,W,2,I - fire_B,N,N,L,W,2,A - fire_B,N,N,L,W,2,L
        - fire_B,N,N,L,W,2,N  = 0
 c3720: fire_B,N,N,L,W,2,C  = 0
 c3721: fire_B,N,N,L,W,2,I  = 0
 c3722: fire_B,N,N,L,W,2,A  = 0
 c3723: fire_B,N,N,L,W,2,L  = 0
 c3724: fire_B,N,N,L,W,2,N  = 0
 c3725: fire_B,N,N,L,W,3,P  = 0
 c3726: fire_B,N,N,L,W,3,D  = 0
 c3727: - fire_B,N,N,L,W,3,P - fire_B,N,N,L,W,3,D - fire_B,N,N,L,W,3,C
        - fire_B,N,N,L,W,3,I - fire_B,N,N,L,W,3,A - fire_B,N,N,L,W,3,L
        - fire_B,N,N,L,W,3,N  = 0
 c3728: fire_B,N,N,L,W,3,C  = 0
 c3729: fire_B,N,N,L,W,3,I  = 0
 c3730: fire_B,N,N,L,W,3,A  = 0
 c3731: fire_B,N,N,L,W,3,L  = 0
 c3732: fire_B,N,N,L,W,3,N  = 0
 c3733: fire_B,N,N,L,W,4,P  = 0
 c3734: fire_B,N,N,L,W,4,D  = 0
 c3735: - fire_B,N,N,L,W,4,P - fire_B,N,N,L,W,4,D - fire_B,N,N,L,W,4,C
        - fire_B,N,N,L,W,4,I - fire_B,N,N,L,W,4,A - fire_B,N,N,L,W,4,L
        - fire_B,N,N,L,W,4,N  = 0
 c3736: fire_B,N,N,L,W,4,C  = 0
 c3737: fire_B,N,N,L,W,4,I  = 0
 c3738: fire_B,N,N,L,W,4,A  = 0
 c3739: fire_B,N,N,L,W,4,L  = 0
 c3740: fire_B,N,N,L,W,4,N  = 0
 c3741: fire_B,N,N,L,W,5,P  = 0
 c3742: fire_B,N,N,L,W,5,D  = 0
 c3743: - fire_B,N,N,L,W,5,P - fire_B,N,N,L,W,5,D - fire_B,N,N,L,W,5,C
        - fire_B,N,N,L,W,5,I - fire_B,N,N,L,W,5,A - fire_B,N,N,L,W,5,L
        - fire_B,N,N,L,W,5,N  = 0
 c3744: fire_B,N,N,L,W,5,C  = 0
 c3745: fire_B,N,N,L,W,5,I  = 0
 c3746: fire_B,N,N,L,W,5,A  = 0
 c3747: fire_B,N,N,L,W,5,L  = 0
 c3748: fire_B,N,N,L,W,5,N  = 0
 c3749: fire_B,N,N,L,C,1,P  = 0
 c3750: fire_B,N,N,L,C,1,D  = 0
 c3751: fire_B,N,N,L,C,1,W  = 0
 c3752: - fire_B,N,N,L,C,1,P - fire_B,N,N,L,C,1,D - fire_B,N,N,L,C,1,W
        - fire_B,N,N,L,C,1,I - fire_B,N,N,L,C,1,A - fire_B,N,N,L,C,1,L
        - fire_B,N,N,L,C,1,N  = 0
 c3753: fire_B,N,N,L,C,1,I  = 0
 c3754: fire_B,N,N,L,C,1,A  = 0
 c3755: fire_B,N,N,L,C,1,L  = 0
 c3756: fire_B,N,N,L,C,1,N  = 0
 c3757: fire_B,N,N,L,C,2,P  = 0
 c3758: fire_B,N,N,L,C,2,D  = 0
 c3759: fire_B,N,N,L,C,2,W  = 0
 c3760: - fire_B,N,N,L,C,2,P - fire_B,N,N,L,C,2,D - fire_B,N,N,L,C,2,W
        - fire_B,N,N,L,C,2,I - fire_B,N,N,L,C,2,A - fire_B,N,N,L,C,2,L
        - fire_B,N,N,L,C,2,N  = 0
 c3761: fire_B,N,N,L,C,2,I  = 0
 c3762: fire_B,N,N,L,C,2,A  = 0
 c3763: fire_B,N,N,L,C,2,L  = 0
 c3764: fire_B,N,N,L,C,2,N  = 0
 c3765: fire_B,N,N,L,C,3,P  = 0
 c3766: fire_B,N,N,L,C,3,D  = 0
 c3767: fire_B,N,N,L,C,3,W  = 0
 c3768: - fire_B,N,N,L,C,3,P - fire_B,N,N,L,C,3,D - fire_B,N,N,L,C,3,W
        - fire_B,N,N,L,C,3,I - fire_B,N,N,L,C,3,A - fire_B,N,N,L,C,3,L
        - fire_B,N,N,L,C,3,N  = 0
 c3769: fire_B,N,N,L,C,3,I  = 0
 c3770: fire_B,N,N,L,C,3,A  = 0
 c3771: fire_B,N,N,L,C,3,L  = 0
 c3772: fire_B,N,N,L,C,3,N  = 0
 c3773: fire_B,N,N,L,C,4,P  = 0
 c3774: fire_B,N,N,L,C,4,D  = 0
 c3775: fire_B,N,N,L,C,4,W  = 0
 c3776: - fire_B,N,N,L,C,4,P - fire_B,N,N,L,C,4,D - fire_B,N,N,L,C,4,W
        - fire_B,N,N,L,C,4,I - fire_B,N,N,L,C,4,A - fire_B,N,N,L,C,4,L
        - fire_B,N,N,L,C,4,N  = 0
 c3777: fire_B,N,N,L,C,4,I  = 0
 c3778: fire_B,N,N,L,C,4,A  = 0
 c3779: fire_B,N,N,L,C,4,L  = 0
 c3780: fire_B,N,N,L,C,4,N  = 0
 c3781: fire_B,N,N,L,C,5,P  = 0
 c3782: fire_B,N,N,L,C,5,D  = 0
 c3783: fire_B,N,N,L,C,5,W  = 0
 c3784: - fire_B,N,N,L,C,5,P - fire_B,N,N,L,C,5,D - fire_B,N,N,L,C,5,W
        - fire_B,N,N,L,C,5,I - fire_B,N,N,L,C,5,A - fire_B,N,N,L,C,5,L
        - fire_B,N,N,L,C,5,N  = 0
 c3785: fire_B,N,N,L,C,5,I  = 0
 c3786: fire_B,N,N,L,C,5,A  = 0
 c3787: fire_B,N,N,L,C,5,L  = 0
 c3788: fire_B,N,N,L,C,5,N  = 0
 c3789: fire_B,N,N,L,I,1,P  = 0
 c3790: fire_B,N,N,L,I,1,D  = 0
 c3791: fire_B,N,N,L,I,1,W  = 0
 c3792: fire_B,N,N,L,I,1,C  = 0
 c3793: - fire_B,N,N,L,I,1,P - fire_B,N,N,L,I,1,D - fire_B,N,N,L,I,1,W
        - fire_B,N,N,L,I,1,C - fire_B,N,N,L,I,1,A - fire_B,N,N,L,I,1,L
        - fire_B,N,N,L,I,1,N  = 0
 c3794: fire_B,N,N,L,I,1,A  = 0
 c3795: fire_B,N,N,L,I,1,L  = 0
 c3796: fire_B,N,N,L,I,1,N  = 0
 c3797: fire_B,N,N,L,I,2,P  = 0
 c3798: fire_B,N,N,L,I,2,D  = 0
 c3799: fire_B,N,N,L,I,2,W  = 0
 c3800: fire_B,N,N,L,I,2,C  = 0
 c3801: - fire_B,N,N,L,I,2,P - fire_B,N,N,L,I,2,D - fire_B,N,N,L,I,2,W
        - fire_B,N,N,L,I,2,C - fire_B,N,N,L,I,2,A - fire_B,N,N,L,I,2,L
        - fire_B,N,N,L,I,2,N  = 0
 c3802: fire_B,N,N,L,I,2,A  = 0
 c3803: fire_B,N,N,L,I,2,L  = 0
 c3804: fire_B,N,N,L,I,2,N  = 0
 c3805: fire_B,N,N,L,I,3,P  = 0
 c3806: fire_B,N,N,L,I,3,D  = 0
 c3807: fire_B,N,N,L,I,3,W  = 0
 c3808: fire_B,N,N,L,I,3,C  = 0
 c3809: - fire_B,N,N,L,I,3,P - fire_B,N,N,L,I,3,D - fire_B,N,N,L,I,3,W
        - fire_B,N,N,L,I,3,C - fire_B,N,N,L,I,3,A - fire_B,N,N,L,I,3,L
        - fire_B,N,N,L,I,3,N  = 0
 c3810: fire_B,N,N,L,I,3,A  = 0
 c3811: fire_B,N,N,L,I,3,L  = 0
 c3812: fire_B,N,N,L,I,3,N  = 0
 c3813: fire_B,N,N,L,I,4,P  = 0
 c3814: fire_B,N,N,L,I,4,D  = 0
 c3815: fire_B,N,N,L,I,4,W  = 0
 c3816: fire_B,N,N,L,I,4,C  = 0
 c3817: - fire_B,N,N,L,I,4,P - fire_B,N,N,L,I,4,D - fire_B,N,N,L,I,4,W
        - fire_B,N,N,L,I,4,C - fire_B,N,N,L,I,4,A - fire_B,N,N,L,I,4,L
        - fire_B,N,N,L,I,4,N  = 0
 c3818: fire_B,N,N,L,I,4,A  = 0
 c3819: fire_B,N,N,L,I,4,L  = 0
 c3820: fire_B,N,N,L,I,4,N  = 0
 c3821: fire_B,N,N,L,I,5,P  = 0
 c3822: fire_B,N,N,L,I,5,D  = 0
 c3823: fire_B,N,N,L,I,5,W  = 0
 c3824: fire_B,N,N,L,I,5,C  = 0
 c3825: - fire_B,N,N,L,I,5,P - fire_B,N,N,L,I,5,D - fire_B,N,N,L,I,5,W
        - fire_B,N,N,L,I,5,C - fire_B,N,N,L,I,5,A - fire_B,N,N,L,I,5,L
        - fire_B,N,N,L,I,5,N  = 0
 c3826: fire_B,N,N,L,I,5,A  = 0
 c3827: fire_B,N,N,L,I,5,L  = 0
 c3828: fire_B,N,N,L,I,5,N  = 0
 c3829: fire_B,N,N,L,A,1,P  = 0
 c3830: fire_B,N,N,L,A,1,D  = 0
 c3831: fire_B,N,N,L,A,1,W  = 0
 c3832: fire_B,N,N,L,A,1,C  = 0
 c3833: fire_B,N,N,L,A,1,I  = 0
 c3834: - fire_B,N,N,L,A,1,P - fire_B,N,N,L,A,1,D - fire_B,N,N,L,A,1,W
        - fire_B,N,N,L,A,1,C - fire_B,N,N,L,A,1,I - fire_B,N,N,L,A,1,L
        - fire_B,N,N,L,A,1,N  = 0
 c3835: fire_B,N,N,L,A,1,L  = 0
 c3836: fire_B,N,N,L,A,1,N  = 0
 c3837: fire_B,N,N,L,A,2,P  = 0
 c3838: fire_B,N,N,L,A,2,D  = 0
 c3839: fire_B,N,N,L,A,2,W  = 0
 c3840: fire_B,N,N,L,A,2,C  = 0
 c3841: fire_B,N,N,L,A,2,I  = 0
 c3842: - fire_B,N,N,L,A,2,P - fire_B,N,N,L,A,2,D - fire_B,N,N,L,A,2,W
        - fire_B,N,N,L,A,2,C - fire_B,N,N,L,A,2,I - fire_B,N,N,L,A,2,L
        - fire_B,N,N,L,A,2,N  = 0
 c3843: fire_B,N,N,L,A,2,L  = 0
 c3844: fire_B,N,N,L,A,2,N  = 0
 c3845: fire_B,N,N,L,A,3,P  = 0
 c3846: fire_B,N,N,L,A,3,D  = 0
 c3847: fire_B,N,N,L,A,3,W  = 0
 c3848: fire_B,N,N,L,A,3,C  = 0
 c3849: fire_B,N,N,L,A,3,I  = 0
 c3850: - fire_B,N,N,L,A,3,P - fire_B,N,N,L,A,3,D - fire_B,N,N,L,A,3,W
        - fire_B,N,N,L,A,3,C - fire_B,N,N,L,A,3,I - fire_B,N,N,L,A,3,L
        - fire_B,N,N,L,A,3,N  = 0
 c3851: fire_B,N,N,L,A,3,L  = 0
 c3852: fire_B,N,N,L,A,3,N  = 0
 c3853: fire_B,N,N,L,A,4,P  = 0
 c3854: fire_B,N,N,L,A,4,D  = 0
 c3855: fire_B,N,N,L,A,4,W  = 0
 c3856: fire_B,N,N,L,A,4,C  = 0
 c3857: fire_B,N,N,L,A,4,I  = 0
 c3858: - fire_B,N,N,L,A,4,P - fire_B,N,N,L,A,4,D - fire_B,N,N,L,A,4,W
        - fire_B,N,N,L,A,4,C - fire_B,N,N,L,A,4,I - fire_B,N,N,L,A,4,L
        - fire_B,N,N,L,A,4,N  = 0
 c3859: fire_B,N,N,L,A,4,L  = 0
 c3860: fire_B,N,N,L,A,4,N  = 0
 c3861: fire_B,N,N,L,A,5,P  = 0
 c3862: fire_B,N,N,L,A,5,D  = 0
 c3863: fire_B,N,N,L,A,5,W  = 0
 c3864: fire_B,N,N,L,A,5,C  = 0
 c3865: fire_B,N,N,L,A,5,I  = 0
 c3866: - fire_B,N,N,L,A,5,P - fire_B,N,N,L,A,5,D - fire_B,N,N,L,A,5,W
        - fire_B,N,N,L,A,5,C - fire_B,N,N,L,A,5,I - fire_B,N,N,L,A,5,L
        - fire_B,N,N,L,A,5,N  = 0
 c3867: fire_B,N,N,L,A,5,L  = 0
 c3868: fire_B,N,N,L,A,5,N  = 0
 c3869: fire_B,N,N,L,L,1,P  = 0
 c3870: fire_B,N,N,L,L,1,D  = 0
 c3871: fire_B,N,N,L,L,1,W  = 0
 c3872: fire_B,N,N,L,L,1,C  = 0
 c3873: fire_B,N,N,L,L,1,I  = 0
 c3874: fire_B,N,N,L,L,1,A  = 0
 c3875: - fire_B,N,N,L,L,1,P - fire_B,N,N,L,L,1,D - fire_B,N,N,L,L,1,W
        - fire_B,N,N,L,L,1,C - fire_B,N,N,L,L,1,I - fire_B,N,N,L,L,1,A
        - fire_B,N,N,L,L,1,N  = 0
 c3876: fire_B,N,N,L,L,1,N  = 0
 c3877: fire_B,N,N,L,L,2,P  = 0
 c3878: fire_B,N,N,L,L,2,D  = 0
 c3879: fire_B,N,N,L,L,2,W  = 0
 c3880: fire_B,N,N,L,L,2,C  = 0
 c3881: fire_B,N,N,L,L,2,I  = 0
 c3882: fire_B,N,N,L,L,2,A  = 0
 c3883: - fire_B,N,N,L,L,2,P - fire_B,N,N,L,L,2,D - fire_B,N,N,L,L,2,W
        - fire_B,N,N,L,L,2,C - fire_B,N,N,L,L,2,I - fire_B,N,N,L,L,2,A
        - fire_B,N,N,L,L,2,N  = 0
 c3884: fire_B,N,N,L,L,2,N  = 0
 c3885: fire_B,N,N,L,L,3,P  = 0
 c3886: fire_B,N,N,L,L,3,D  = 0
 c3887: fire_B,N,N,L,L,3,W  = 0
 c3888: fire_B,N,N,L,L,3,C  = 0
 c3889: fire_B,N,N,L,L,3,I  = 0
 c3890: fire_B,N,N,L,L,3,A  = 0
 c3891: - fire_B,N,N,L,L,3,P - fire_B,N,N,L,L,3,D - fire_B,N,N,L,L,3,W
        - fire_B,N,N,L,L,3,C - fire_B,N,N,L,L,3,I - fire_B,N,N,L,L,3,A
        - fire_B,N,N,L,L,3,N  = 0
 c3892: fire_B,N,N,L,L,3,N  = 0
 c3893: fire_B,N,N,L,L,4,P  = 0
 c3894: fire_B,N,N,L,L,4,D  = 0
 c3895: fire_B,N,N,L,L,4,W  = 0
 c3896: fire_B,N,N,L,L,4,C  = 0
 c3897: fire_B,N,N,L,L,4,I  = 0
 c3898: fire_B,N,N,L,L,4,A  = 0
 c3899: - fire_B,N,N,L,L,4,P - fire_B,N,N,L,L,4,D - fire_B,N,N,L,L,4,W
        - fire_B,N,N,L,L,4,C - fire_B,N,N,L,L,4,I - fire_B,N,N,L,L,4,A
        - fire_B,N,N,L,L,4,N  = 0
 c3900: fire_B,N,N,L,L,4,N  = 0
 c3901: fire_B,N,N,L,L,5,P  = 0
 c3902: fire_B,N,N,L,L,5,D  = 0
 c3903: fire_B,N,N,L,L,5,W  = 0
 c3904: fire_B,N,N,L,L,5,C  = 0
 c3905: fire_B,N,N,L,L,5,I  = 0
 c3906: fire_B,N,N,L,L,5,A  = 0
 c3907: - fire_B,N,N,L,L,5,P - fire_B,N,N,L,L,5,D - fire_B,N,N,L,L,5,W
        - fire_B,N,N,L,L,5,C - fire_B,N,N,L,L,5,I - fire_B,N,N,L,L,5,A
        - fire_B,N,N,L,L,5,N  = 0
 c3908: fire_B,N,N,L,L,5,N  = 0
 c3909: fire_B,N,N,L,N,1,P  = 0
 c3910: fire_B,N,N,L,N,1,D  = 0
 c3911: fire_B,N,N,L,N,1,W  = 0
 c3912: fire_B,N,N,L,N,1,C  = 0
 c3913: fire_B,N,N,L,N,1,I  = 0
 c3914: fire_B,N,N,L,N,1,A  = 0
 c3915: fire_B,N,N,L,N,1,L  = 0
 c3916: - fire_B,N,N,L,N,1,P - fire_B,N,N,L,N,1,D - fire_B,N,N,L,N,1,W
        - fire_B,N,N,L,N,1,C - fire_B,N,N,L,N,1,I - fire_B,N,N,L,N,1,A
        - fire_B,N,N,L,N,1,L  = 0
 c3917: fire_B,N,N,L,N,2,P  = 0
 c3918: fire_B,N,N,L,N,2,D  = 0
 c3919: fire_B,N,N,L,N,2,W  = 0
 c3920: fire_B,N,N,L,N,2,C  = 0
 c3921: fire_B,N,N,L,N,2,I  = 0
 c3922: fire_B,N,N,L,N,2,A  = 0
 c3923: fire_B,N,N,L,N,2,L  = 0
 c3924: - fire_B,N,N,L,N,2,P - fire_B,N,N,L,N,2,D - fire_B,N,N,L,N,2,W
        - fire_B,N,N,L,N,2,C - fire_B,N,N,L,N,2,I - fire_B,N,N,L,N,2,A
        - fire_B,N,N,L,N,2,L  = 0
 c3925: fire_B,N,N,L,N,3,P  = 0
 c3926: fire_B,N,N,L,N,3,D  = 0
 c3927: fire_B,N,N,L,N,3,W  = 0
 c3928: fire_B,N,N,L,N,3,C  = 0
 c3929: fire_B,N,N,L,N,3,I  = 0
 c3930: fire_B,N,N,L,N,3,A  = 0
 c3931: fire_B,N,N,L,N,3,L  = 0
 c3932: - fire_B,N,N,L,N,3,P - fire_B,N,N,L,N,3,D - fire_B,N,N,L,N,3,W
        - fire_B,N,N,L,N,3,C - fire_B,N,N,L,N,3,I - fire_B,N,N,L,N,3,A
        - fire_B,N,N,L,N,3,L  = 0
 c3933: fire_B,N,N,L,N,4,P  = 0
 c3934: fire_B,N,N,L,N,4,D  = 0
 c3935: fire_B,N,N,L,N,4,W  = 0
 c3936: fire_B,N,N,L,N,4,C  = 0
 c3937: fire_B,N,N,L,N,4,I  = 0
 c3938: fire_B,N,N,L,N,4,A  = 0
 c3939: fire_B,N,N,L,N,4,L  = 0
 c3940: - fire_B,N,N,L,N,4,P - fire_B,N,N,L,N,4,D - fire_B,N,N,L,N,4,W
        - fire_B,N,N,L,N,4,C - fire_B,N,N,L,N,4,I - fire_B,N,N,L,N,4,A
        - fire_B,N,N,L,N,4,L  = 0
 c3941: fire_B,N,N,L,N,5,P  = 0
 c3942: fire_B,N,N,L,N,5,D  = 0
 c3943: fire_B,N,N,L,N,5,W  = 0
 c3944: fire_B,N,N,L,N,5,C  = 0
 c3945: fire_B,N,N,L,N,5,I  = 0
 c3946: fire_B,N,N,L,N,5,A  = 0
 c3947: fire_B,N,N,L,N,5,L  = 0
 c3948: - fire_B,N,N,L,N,5,P - fire_B,N,N,L,N,5,D - fire_B,N,N,L,N,5,W
        - fire_B,N,N,L,N,5,C - fire_B,N,N,L,N,5,I - fire_B,N,N,L,N,5,A
        - fire_B,N,N,L,N,5,L  = 0
 c3949: 0.9 fire_B,N,N,C,P,1,P - 0.1 fire_B,N,N,C,P,1,D
        - 0.1 fire_B,N,N,C,P,1,W - 0.1 fire_B,N,N,C,P,1,C
        - 0.1 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        - 0.1 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3950: - 0.1 fire_B,N,N,C,P,1,P + 0.9 fire_B,N,N,C,P,1,D
        - 0.1 fire_B,N,N,C,P,1,W - 0.1 fire_B,N,N,C,P,1,C
        - 0.1 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        - 0.1 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3951: - 0.1 fire_B,N,N,C,P,1,P - 0.1 fire_B,N,N,C,P,1,D
        + 0.9 fire_B,N,N,C,P,1,W - 0.1 fire_B,N,N,C,P,1,C
        - 0.1 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        - 0.1 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3952: - 0.1 fire_B,N,N,C,P,1,P - 0.1 fire_B,N,N,C,P,1,D
        - 0.1 fire_B,N,N,C,P,1,W + 0.9 fire_B,N,N,C,P,1,C
        - 0.1 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        - 0.1 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3953: - 0.1 fire_B,N,N,C,P,1,P - 0.1 fire_B,N,N,C,P,1,D
        - 0.1 fire_B,N,N,C,P,1,W - 0.1 fire_B,N,N,C,P,1,C
        + 0.9 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        - 0.1 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3954: - 0.2 fire_B,N,N,C,P,1,P - 0.2 fire_B,N,N,C,P,1,D
        - 0.2 fire_B,N,N,C,P,1,W - 0.2 fire_B,N,N,C,P,1,C
        - 0.2 fire_B,N,N,C,P,1,I + 0.8 fire_B,N,N,C,P,1,A
        - 0.2 fire_B,N,N,C,P,1,L - 0.2 fire_B,N,N,C,P,1,N  = 0
 c3955: - 0.1 fire_B,N,N,C,P,1,P - 0.1 fire_B,N,N,C,P,1,D
        - 0.1 fire_B,N,N,C,P,1,W - 0.1 fire_B,N,N,C,P,1,C
        - 0.1 fire_B,N,N,C,P,1,I - 0.1 fire_B,N,N,C,P,1,A
        + 0.9 fire_B,N,N,C,P,1,L - 0.1 fire_B,N,N,C,P,1,N  = 0
 c3956: - 0.2 fire_B,N,N,C,P,1,P - 0.2 fire_B,N,N,C,P,1,D
        - 0.2 fire_B,N,N,C,P,1,W - 0.2 fire_B,N,N,C,P,1,C
        - 0.2 fire_B,N,N,C,P,1,I - 0.2 fire_B,N,N,C,P,1,A
        - 0.2 fire_B,N,N,C,P,1,L + 0.8 fire_B,N,N,C,P,1,N  = 0
 c3957: 0.9 fire_B,N,N,C,P,2,P - 0.1 fire_B,N,N,C,P,2,D
        - 0.1 fire_B,N,N,C,P,2,W - 0.1 fire_B,N,N,C,P,2,C
        - 0.1 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        - 0.1 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3958: - 0.1 fire_B,N,N,C,P,2,P + 0.9 fire_B,N,N,C,P,2,D
        - 0.1 fire_B,N,N,C,P,2,W - 0.1 fire_B,N,N,C,P,2,C
        - 0.1 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        - 0.1 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3959: - 0.1 fire_B,N,N,C,P,2,P - 0.1 fire_B,N,N,C,P,2,D
        + 0.9 fire_B,N,N,C,P,2,W - 0.1 fire_B,N,N,C,P,2,C
        - 0.1 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        - 0.1 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3960: - 0.1 fire_B,N,N,C,P,2,P - 0.1 fire_B,N,N,C,P,2,D
        - 0.1 fire_B,N,N,C,P,2,W + 0.9 fire_B,N,N,C,P,2,C
        - 0.1 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        - 0.1 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3961: - 0.1 fire_B,N,N,C,P,2,P - 0.1 fire_B,N,N,C,P,2,D
        - 0.1 fire_B,N,N,C,P,2,W - 0.1 fire_B,N,N,C,P,2,C
        + 0.9 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        - 0.1 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3962: - 0.2 fire_B,N,N,C,P,2,P - 0.2 fire_B,N,N,C,P,2,D
        - 0.2 fire_B,N,N,C,P,2,W - 0.2 fire_B,N,N,C,P,2,C
        - 0.2 fire_B,N,N,C,P,2,I + 0.8 fire_B,N,N,C,P,2,A
        - 0.2 fire_B,N,N,C,P,2,L - 0.2 fire_B,N,N,C,P,2,N  = 0
 c3963: - 0.1 fire_B,N,N,C,P,2,P - 0.1 fire_B,N,N,C,P,2,D
        - 0.1 fire_B,N,N,C,P,2,W - 0.1 fire_B,N,N,C,P,2,C
        - 0.1 fire_B,N,N,C,P,2,I - 0.1 fire_B,N,N,C,P,2,A
        + 0.9 fire_B,N,N,C,P,2,L - 0.1 fire_B,N,N,C,P,2,N  = 0
 c3964: - 0.2 fire_B,N,N,C,P,2,P - 0.2 fire_B,N,N,C,P,2,D
        - 0.2 fire_B,N,N,C,P,2,W - 0.2 fire_B,N,N,C,P,2,C
        - 0.2 fire_B,N,N,C,P,2,I - 0.2 fire_B,N,N,C,P,2,A
        - 0.2 fire_B,N,N,C,P,2,L + 0.8 fire_B,N,N,C,P,2,N  = 0
 c3965: 0.9 fire_B,N,N,C,P,3,P - 0.1 fire_B,N,N,C,P,3,D
        - 0.1 fire_B,N,N,C,P,3,W - 0.1 fire_B,N,N,C,P,3,C
        - 0.1 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        - 0.1 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3966: - 0.1 fire_B,N,N,C,P,3,P + 0.9 fire_B,N,N,C,P,3,D
        - 0.1 fire_B,N,N,C,P,3,W - 0.1 fire_B,N,N,C,P,3,C
        - 0.1 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        - 0.1 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3967: - 0.1 fire_B,N,N,C,P,3,P - 0.1 fire_B,N,N,C,P,3,D
        + 0.9 fire_B,N,N,C,P,3,W - 0.1 fire_B,N,N,C,P,3,C
        - 0.1 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        - 0.1 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3968: - 0.1 fire_B,N,N,C,P,3,P - 0.1 fire_B,N,N,C,P,3,D
        - 0.1 fire_B,N,N,C,P,3,W + 0.9 fire_B,N,N,C,P,3,C
        - 0.1 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        - 0.1 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3969: - 0.1 fire_B,N,N,C,P,3,P - 0.1 fire_B,N,N,C,P,3,D
        - 0.1 fire_B,N,N,C,P,3,W - 0.1 fire_B,N,N,C,P,3,C
        + 0.9 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        - 0.1 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3970: - 0.2 fire_B,N,N,C,P,3,P - 0.2 fire_B,N,N,C,P,3,D
        - 0.2 fire_B,N,N,C,P,3,W - 0.2 fire_B,N,N,C,P,3,C
        - 0.2 fire_B,N,N,C,P,3,I + 0.8 fire_B,N,N,C,P,3,A
        - 0.2 fire_B,N,N,C,P,3,L - 0.2 fire_B,N,N,C,P,3,N  = 0
 c3971: - 0.1 fire_B,N,N,C,P,3,P - 0.1 fire_B,N,N,C,P,3,D
        - 0.1 fire_B,N,N,C,P,3,W - 0.1 fire_B,N,N,C,P,3,C
        - 0.1 fire_B,N,N,C,P,3,I - 0.1 fire_B,N,N,C,P,3,A
        + 0.9 fire_B,N,N,C,P,3,L - 0.1 fire_B,N,N,C,P,3,N  = 0
 c3972: - 0.2 fire_B,N,N,C,P,3,P - 0.2 fire_B,N,N,C,P,3,D
        - 0.2 fire_B,N,N,C,P,3,W - 0.2 fire_B,N,N,C,P,3,C
        - 0.2 fire_B,N,N,C,P,3,I - 0.2 fire_B,N,N,C,P,3,A
        - 0.2 fire_B,N,N,C,P,3,L + 0.8 fire_B,N,N,C,P,3,N  = 0
 c3973: 0.9 fire_B,N,N,C,P,4,P - 0.1 fire_B,N,N,C,P,4,D
        - 0.1 fire_B,N,N,C,P,4,W - 0.1 fire_B,N,N,C,P,4,C
        - 0.1 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        - 0.1 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3974: - 0.1 fire_B,N,N,C,P,4,P + 0.9 fire_B,N,N,C,P,4,D
        - 0.1 fire_B,N,N,C,P,4,W - 0.1 fire_B,N,N,C,P,4,C
        - 0.1 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        - 0.1 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3975: - 0.1 fire_B,N,N,C,P,4,P - 0.1 fire_B,N,N,C,P,4,D
        + 0.9 fire_B,N,N,C,P,4,W - 0.1 fire_B,N,N,C,P,4,C
        - 0.1 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        - 0.1 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3976: - 0.1 fire_B,N,N,C,P,4,P - 0.1 fire_B,N,N,C,P,4,D
        - 0.1 fire_B,N,N,C,P,4,W + 0.9 fire_B,N,N,C,P,4,C
        - 0.1 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        - 0.1 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3977: - 0.1 fire_B,N,N,C,P,4,P - 0.1 fire_B,N,N,C,P,4,D
        - 0.1 fire_B,N,N,C,P,4,W - 0.1 fire_B,N,N,C,P,4,C
        + 0.9 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        - 0.1 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3978: - 0.2 fire_B,N,N,C,P,4,P - 0.2 fire_B,N,N,C,P,4,D
        - 0.2 fire_B,N,N,C,P,4,W - 0.2 fire_B,N,N,C,P,4,C
        - 0.2 fire_B,N,N,C,P,4,I + 0.8 fire_B,N,N,C,P,4,A
        - 0.2 fire_B,N,N,C,P,4,L - 0.2 fire_B,N,N,C,P,4,N  = 0
 c3979: - 0.1 fire_B,N,N,C,P,4,P - 0.1 fire_B,N,N,C,P,4,D
        - 0.1 fire_B,N,N,C,P,4,W - 0.1 fire_B,N,N,C,P,4,C
        - 0.1 fire_B,N,N,C,P,4,I - 0.1 fire_B,N,N,C,P,4,A
        + 0.9 fire_B,N,N,C,P,4,L - 0.1 fire_B,N,N,C,P,4,N  = 0
 c3980: - 0.2 fire_B,N,N,C,P,4,P - 0.2 fire_B,N,N,C,P,4,D
        - 0.2 fire_B,N,N,C,P,4,W - 0.2 fire_B,N,N,C,P,4,C
        - 0.2 fire_B,N,N,C,P,4,I - 0.2 fire_B,N,N,C,P,4,A
        - 0.2 fire_B,N,N,C,P,4,L + 0.8 fire_B,N,N,C,P,4,N  = 0
 c3981: 0.9 fire_B,N,N,C,P,5,P - 0.1 fire_B,N,N,C,P,5,D
        - 0.1 fire_B,N,N,C,P,5,W - 0.1 fire_B,N,N,C,P,5,C
        - 0.1 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        - 0.1 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3982: - 0.1 fire_B,N,N,C,P,5,P + 0.9 fire_B,N,N,C,P,5,D
        - 0.1 fire_B,N,N,C,P,5,W - 0.1 fire_B,N,N,C,P,5,C
        - 0.1 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        - 0.1 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3983: - 0.1 fire_B,N,N,C,P,5,P - 0.1 fire_B,N,N,C,P,5,D
        + 0.9 fire_B,N,N,C,P,5,W - 0.1 fire_B,N,N,C,P,5,C
        - 0.1 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        - 0.1 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3984: - 0.1 fire_B,N,N,C,P,5,P - 0.1 fire_B,N,N,C,P,5,D
        - 0.1 fire_B,N,N,C,P,5,W + 0.9 fire_B,N,N,C,P,5,C
        - 0.1 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        - 0.1 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3985: - 0.1 fire_B,N,N,C,P,5,P - 0.1 fire_B,N,N,C,P,5,D
        - 0.1 fire_B,N,N,C,P,5,W - 0.1 fire_B,N,N,C,P,5,C
        + 0.9 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        - 0.1 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3986: - 0.2 fire_B,N,N,C,P,5,P - 0.2 fire_B,N,N,C,P,5,D
        - 0.2 fire_B,N,N,C,P,5,W - 0.2 fire_B,N,N,C,P,5,C
        - 0.2 fire_B,N,N,C,P,5,I + 0.8 fire_B,N,N,C,P,5,A
        - 0.2 fire_B,N,N,C,P,5,L - 0.2 fire_B,N,N,C,P,5,N  = 0
 c3987: - 0.1 fire_B,N,N,C,P,5,P - 0.1 fire_B,N,N,C,P,5,D
        - 0.1 fire_B,N,N,C,P,5,W - 0.1 fire_B,N,N,C,P,5,C
        - 0.1 fire_B,N,N,C,P,5,I - 0.1 fire_B,N,N,C,P,5,A
        + 0.9 fire_B,N,N,C,P,5,L - 0.1 fire_B,N,N,C,P,5,N  = 0
 c3988: - 0.2 fire_B,N,N,C,P,5,P - 0.2 fire_B,N,N,C,P,5,D
        - 0.2 fire_B,N,N,C,P,5,W - 0.2 fire_B,N,N,C,P,5,C
        - 0.2 fire_B,N,N,C,P,5,I - 0.2 fire_B,N,N,C,P,5,A
        - 0.2 fire_B,N,N,C,P,5,L + 0.8 fire_B,N,N,C,P,5,N  = 0
 c3989: 0.5 fire_B,N,N,C,D,1,P - 0.5 fire_B,N,N,C,D,1,D
        - 0.5 fire_B,N,N,C,D,1,W - 0.5 fire_B,N,N,C,D,1,C
        - 0.5 fire_B,N,N,C,D,1,I - 0.5 fire_B,N,N,C,D,1,A
        - 0.5 fire_B,N,N,C,D,1,L - 0.5 fire_B,N,N,C,D,1,N  = 0
 c3990: - 0.5 fire_B,N,N,C,D,1,P + 0.5 fire_B,N,N,C,D,1,D
        - 0.5 fire_B,N,N,C,D,1,W - 0.5 fire_B,N,N,C,D,1,C
        - 0.5 fire_B,N,N,C,D,1,I - 0.5 fire_B,N,N,C,D,1,A
        - 0.5 fire_B,N,N,C,D,1,L - 0.5 fire_B,N,N,C,D,1,N  = 0
 c3991: fire_B,N,N,C,D,1,W  = 0
 c3992: fire_B,N,N,C,D,1,C  = 0
 c3993: fire_B,N,N,C,D,1,I  = 0
 c3994: fire_B,N,N,C,D,1,A  = 0
 c3995: fire_B,N,N,C,D,1,L  = 0
 c3996: fire_B,N,N,C,D,1,N  = 0
 c3997: 0.5 fire_B,N,N,C,D,2,P - 0.5 fire_B,N,N,C,D,2,D
        - 0.5 fire_B,N,N,C,D,2,W - 0.5 fire_B,N,N,C,D,2,C
        - 0.5 fire_B,N,N,C,D,2,I - 0.5 fire_B,N,N,C,D,2,A
        - 0.5 fire_B,N,N,C,D,2,L - 0.5 fire_B,N,N,C,D,2,N  = 0
 c3998: - 0.5 fire_B,N,N,C,D,2,P + 0.5 fire_B,N,N,C,D,2,D
        - 0.5 fire_B,N,N,C,D,2,W - 0.5 fire_B,N,N,C,D,2,C
        - 0.5 fire_B,N,N,C,D,2,I - 0.5 fire_B,N,N,C,D,2,A
        - 0.5 fire_B,N,N,C,D,2,L - 0.5 fire_B,N,N,C,D,2,N  = 0
 c3999: fire_B,N,N,C,D,2,W  = 0
 c4000: fire_B,N,N,C,D,2,C  = 0
 c4001: fire_B,N,N,C,D,2,I  = 0
 c4002: fire_B,N,N,C,D,2,A  = 0
 c4003: fire_B,N,N,C,D,2,L  = 0
 c4004: fire_B,N,N,C,D,2,N  = 0
 c4005: 0.5 fire_B,N,N,C,D,3,P - 0.5 fire_B,N,N,C,D,3,D
        - 0.5 fire_B,N,N,C,D,3,W - 0.5 fire_B,N,N,C,D,3,C
        - 0.5 fire_B,N,N,C,D,3,I - 0.5 fire_B,N,N,C,D,3,A
        - 0.5 fire_B,N,N,C,D,3,L - 0.5 fire_B,N,N,C,D,3,N  = 0
 c4006: - 0.5 fire_B,N,N,C,D,3,P + 0.5 fire_B,N,N,C,D,3,D
        - 0.5 fire_B,N,N,C,D,3,W - 0.5 fire_B,N,N,C,D,3,C
        - 0.5 fire_B,N,N,C,D,3,I - 0.5 fire_B,N,N,C,D,3,A
        - 0.5 fire_B,N,N,C,D,3,L - 0.5 fire_B,N,N,C,D,3,N  = 0
 c4007: fire_B,N,N,C,D,3,W  = 0
 c4008: fire_B,N,N,C,D,3,C  = 0
 c4009: fire_B,N,N,C,D,3,I  = 0
 c4010: fire_B,N,N,C,D,3,A  = 0
 c4011: fire_B,N,N,C,D,3,L  = 0
 c4012: fire_B,N,N,C,D,3,N  = 0
 c4013: 0.5 fire_B,N,N,C,D,4,P - 0.5 fire_B,N,N,C,D,4,D
        - 0.5 fire_B,N,N,C,D,4,W - 0.5 fire_B,N,N,C,D,4,C
        - 0.5 fire_B,N,N,C,D,4,I - 0.5 fire_B,N,N,C,D,4,A
        - 0.5 fire_B,N,N,C,D,4,L - 0.5 fire_B,N,N,C,D,4,N  = 0
 c4014: - 0.5 fire_B,N,N,C,D,4,P + 0.5 fire_B,N,N,C,D,4,D
        - 0.5 fire_B,N,N,C,D,4,W - 0.5 fire_B,N,N,C,D,4,C
        - 0.5 fire_B,N,N,C,D,4,I - 0.5 fire_B,N,N,C,D,4,A
        - 0.5 fire_B,N,N,C,D,4,L - 0.5 fire_B,N,N,C,D,4,N  = 0
 c4015: fire_B,N,N,C,D,4,W  = 0
 c4016: fire_B,N,N,C,D,4,C  = 0
 c4017: fire_B,N,N,C,D,4,I  = 0
 c4018: fire_B,N,N,C,D,4,A  = 0
 c4019: fire_B,N,N,C,D,4,L  = 0
 c4020: fire_B,N,N,C,D,4,N  = 0
 c4021: 0.5 fire_B,N,N,C,D,5,P - 0.5 fire_B,N,N,C,D,5,D
        - 0.5 fire_B,N,N,C,D,5,W - 0.5 fire_B,N,N,C,D,5,C
        - 0.5 fire_B,N,N,C,D,5,I - 0.5 fire_B,N,N,C,D,5,A
        - 0.5 fire_B,N,N,C,D,5,L - 0.5 fire_B,N,N,C,D,5,N  = 0
 c4022: - 0.5 fire_B,N,N,C,D,5,P + 0.5 fire_B,N,N,C,D,5,D
        - 0.5 fire_B,N,N,C,D,5,W - 0.5 fire_B,N,N,C,D,5,C
        - 0.5 fire_B,N,N,C,D,5,I - 0.5 fire_B,N,N,C,D,5,A
        - 0.5 fire_B,N,N,C,D,5,L - 0.5 fire_B,N,N,C,D,5,N  = 0
 c4023: fire_B,N,N,C,D,5,W  = 0
 c4024: fire_B,N,N,C,D,5,C  = 0
 c4025: fire_B,N,N,C,D,5,I  = 0
 c4026: fire_B,N,N,C,D,5,A  = 0
 c4027: fire_B,N,N,C,D,5,L  = 0
 c4028: fire_B,N,N,C,D,5,N  = 0
 c4029: fire_B,N,N,C,W,1,P  = 0
 c4030: fire_B,N,N,C,W,1,D  = 0
 c4031: - fire_B,N,N,C,W,1,P - fire_B,N,N,C,W,1,D - fire_B,N,N,C,W,1,C
        - fire_B,N,N,C,W,1,I - fire_B,N,N,C,W,1,A - fire_B,N,N,C,W,1,L
        - fire_B,N,N,C,W,1,N  = 0
 c4032: fire_B,N,N,C,W,1,C  = 0
 c4033: fire_B,N,N,C,W,1,I  = 0
 c4034: fire_B,N,N,C,W,1,A  = 0
 c4035: fire_B,N,N,C,W,1,L  = 0
 c4036: fire_B,N,N,C,W,1,N  = 0
 c4037: fire_B,N,N,C,W,2,P  = 0
 c4038: fire_B,N,N,C,W,2,D  = 0
 c4039: - fire_B,N,N,C,W,2,P - fire_B,N,N,C,W,2,D - fire_B,N,N,C,W,2,C
        - fire_B,N,N,C,W,2,I - fire_B,N,N,C,W,2,A - fire_B,N,N,C,W,2,L
        - fire_B,N,N,C,W,2,N  = 0
 c4040: fire_B,N,N,C,W,2,C  = 0
 c4041: fire_B,N,N,C,W,2,I  = 0
 c4042: fire_B,N,N,C,W,2,A  = 0
 c4043: fire_B,N,N,C,W,2,L  = 0
 c4044: fire_B,N,N,C,W,2,N  = 0
 c4045: fire_B,N,N,C,W,3,P  = 0
 c4046: fire_B,N,N,C,W,3,D  = 0
 c4047: - fire_B,N,N,C,W,3,P - fire_B,N,N,C,W,3,D - fire_B,N,N,C,W,3,C
        - fire_B,N,N,C,W,3,I - fire_B,N,N,C,W,3,A - fire_B,N,N,C,W,3,L
        - fire_B,N,N,C,W,3,N  = 0
 c4048: fire_B,N,N,C,W,3,C  = 0
 c4049: fire_B,N,N,C,W,3,I  = 0
 c4050: fire_B,N,N,C,W,3,A  = 0
 c4051: fire_B,N,N,C,W,3,L  = 0
 c4052: fire_B,N,N,C,W,3,N  = 0
 c4053: fire_B,N,N,C,W,4,P  = 0
 c4054: fire_B,N,N,C,W,4,D  = 0
 c4055: - fire_B,N,N,C,W,4,P - fire_B,N,N,C,W,4,D - fire_B,N,N,C,W,4,C
        - fire_B,N,N,C,W,4,I - fire_B,N,N,C,W,4,A - fire_B,N,N,C,W,4,L
        - fire_B,N,N,C,W,4,N  = 0
 c4056: fire_B,N,N,C,W,4,C  = 0
 c4057: fire_B,N,N,C,W,4,I  = 0
 c4058: fire_B,N,N,C,W,4,A  = 0
 c4059: fire_B,N,N,C,W,4,L  = 0
 c4060: fire_B,N,N,C,W,4,N  = 0
 c4061: fire_B,N,N,C,W,5,P  = 0
 c4062: fire_B,N,N,C,W,5,D  = 0
 c4063: - fire_B,N,N,C,W,5,P - fire_B,N,N,C,W,5,D - fire_B,N,N,C,W,5,C
        - fire_B,N,N,C,W,5,I - fire_B,N,N,C,W,5,A - fire_B,N,N,C,W,5,L
        - fire_B,N,N,C,W,5,N  = 0
 c4064: fire_B,N,N,C,W,5,C  = 0
 c4065: fire_B,N,N,C,W,5,I  = 0
 c4066: fire_B,N,N,C,W,5,A  = 0
 c4067: fire_B,N,N,C,W,5,L  = 0
 c4068: fire_B,N,N,C,W,5,N  = 0
 c4069: fire_B,N,N,C,C,1,P  = 0
 c4070: fire_B,N,N,C,C,1,D  = 0
 c4071: fire_B,N,N,C,C,1,W  = 0
 c4072: - fire_B,N,N,C,C,1,P - fire_B,N,N,C,C,1,D - fire_B,N,N,C,C,1,W
        - fire_B,N,N,C,C,1,I - fire_B,N,N,C,C,1,A - fire_B,N,N,C,C,1,L
        - fire_B,N,N,C,C,1,N  = 0
 c4073: fire_B,N,N,C,C,1,I  = 0
 c4074: fire_B,N,N,C,C,1,A  = 0
 c4075: fire_B,N,N,C,C,1,L  = 0
 c4076: fire_B,N,N,C,C,1,N  = 0
 c4077: fire_B,N,N,C,C,2,P  = 0
 c4078: fire_B,N,N,C,C,2,D  = 0
 c4079: fire_B,N,N,C,C,2,W  = 0
 c4080: - fire_B,N,N,C,C,2,P - fire_B,N,N,C,C,2,D - fire_B,N,N,C,C,2,W
        - fire_B,N,N,C,C,2,I - fire_B,N,N,C,C,2,A - fire_B,N,N,C,C,2,L
        - fire_B,N,N,C,C,2,N  = 0
 c4081: fire_B,N,N,C,C,2,I  = 0
 c4082: fire_B,N,N,C,C,2,A  = 0
 c4083: fire_B,N,N,C,C,2,L  = 0
 c4084: fire_B,N,N,C,C,2,N  = 0
 c4085: fire_B,N,N,C,C,3,P  = 0
 c4086: fire_B,N,N,C,C,3,D  = 0
 c4087: fire_B,N,N,C,C,3,W  = 0
 c4088: - fire_B,N,N,C,C,3,P - fire_B,N,N,C,C,3,D - fire_B,N,N,C,C,3,W
        - fire_B,N,N,C,C,3,I - fire_B,N,N,C,C,3,A - fire_B,N,N,C,C,3,L
        - fire_B,N,N,C,C,3,N  = 0
 c4089: fire_B,N,N,C,C,3,I  = 0
 c4090: fire_B,N,N,C,C,3,A  = 0
 c4091: fire_B,N,N,C,C,3,L  = 0
 c4092: fire_B,N,N,C,C,3,N  = 0
 c4093: fire_B,N,N,C,C,4,P  = 0
 c4094: fire_B,N,N,C,C,4,D  = 0
 c4095: fire_B,N,N,C,C,4,W  = 0
 c4096: - fire_B,N,N,C,C,4,P - fire_B,N,N,C,C,4,D - fire_B,N,N,C,C,4,W
        - fire_B,N,N,C,C,4,I - fire_B,N,N,C,C,4,A - fire_B,N,N,C,C,4,L
        - fire_B,N,N,C,C,4,N  = 0
 c4097: fire_B,N,N,C,C,4,I  = 0
 c4098: fire_B,N,N,C,C,4,A  = 0
 c4099: fire_B,N,N,C,C,4,L  = 0
 c4100: fire_B,N,N,C,C,4,N  = 0
 c4101: fire_B,N,N,C,C,5,P  = 0
 c4102: fire_B,N,N,C,C,5,D  = 0
 c4103: fire_B,N,N,C,C,5,W  = 0
 c4104: - fire_B,N,N,C,C,5,P - fire_B,N,N,C,C,5,D - fire_B,N,N,C,C,5,W
        - fire_B,N,N,C,C,5,I - fire_B,N,N,C,C,5,A - fire_B,N,N,C,C,5,L
        - fire_B,N,N,C,C,5,N  = 0
 c4105: fire_B,N,N,C,C,5,I  = 0
 c4106: fire_B,N,N,C,C,5,A  = 0
 c4107: fire_B,N,N,C,C,5,L  = 0
 c4108: fire_B,N,N,C,C,5,N  = 0
 c4109: fire_B,N,N,C,I,1,P  = 0
 c4110: fire_B,N,N,C,I,1,D  = 0
 c4111: fire_B,N,N,C,I,1,W  = 0
 c4112: fire_B,N,N,C,I,1,C  = 0
 c4113: - fire_B,N,N,C,I,1,P - fire_B,N,N,C,I,1,D - fire_B,N,N,C,I,1,W
        - fire_B,N,N,C,I,1,C - fire_B,N,N,C,I,1,A - fire_B,N,N,C,I,1,L
        - fire_B,N,N,C,I,1,N  = 0
 c4114: fire_B,N,N,C,I,1,A  = 0
 c4115: fire_B,N,N,C,I,1,L  = 0
 c4116: fire_B,N,N,C,I,1,N  = 0
 c4117: fire_B,N,N,C,I,2,P  = 0
 c4118: fire_B,N,N,C,I,2,D  = 0
 c4119: fire_B,N,N,C,I,2,W  = 0
 c4120: fire_B,N,N,C,I,2,C  = 0
 c4121: - fire_B,N,N,C,I,2,P - fire_B,N,N,C,I,2,D - fire_B,N,N,C,I,2,W
        - fire_B,N,N,C,I,2,C - fire_B,N,N,C,I,2,A - fire_B,N,N,C,I,2,L
        - fire_B,N,N,C,I,2,N  = 0
 c4122: fire_B,N,N,C,I,2,A  = 0
 c4123: fire_B,N,N,C,I,2,L  = 0
 c4124: fire_B,N,N,C,I,2,N  = 0
 c4125: fire_B,N,N,C,I,3,P  = 0
 c4126: fire_B,N,N,C,I,3,D  = 0
 c4127: fire_B,N,N,C,I,3,W  = 0
 c4128: fire_B,N,N,C,I,3,C  = 0
 c4129: - fire_B,N,N,C,I,3,P - fire_B,N,N,C,I,3,D - fire_B,N,N,C,I,3,W
        - fire_B,N,N,C,I,3,C - fire_B,N,N,C,I,3,A - fire_B,N,N,C,I,3,L
        - fire_B,N,N,C,I,3,N  = 0
 c4130: fire_B,N,N,C,I,3,A  = 0
 c4131: fire_B,N,N,C,I,3,L  = 0
 c4132: fire_B,N,N,C,I,3,N  = 0
 c4133: fire_B,N,N,C,I,4,P  = 0
 c4134: fire_B,N,N,C,I,4,D  = 0
 c4135: fire_B,N,N,C,I,4,W  = 0
 c4136: fire_B,N,N,C,I,4,C  = 0
 c4137: - fire_B,N,N,C,I,4,P - fire_B,N,N,C,I,4,D - fire_B,N,N,C,I,4,W
        - fire_B,N,N,C,I,4,C - fire_B,N,N,C,I,4,A - fire_B,N,N,C,I,4,L
        - fire_B,N,N,C,I,4,N  = 0
 c4138: fire_B,N,N,C,I,4,A  = 0
 c4139: fire_B,N,N,C,I,4,L  = 0
 c4140: fire_B,N,N,C,I,4,N  = 0
 c4141: fire_B,N,N,C,I,5,P  = 0
 c4142: fire_B,N,N,C,I,5,D  = 0
 c4143: fire_B,N,N,C,I,5,W  = 0
 c4144: fire_B,N,N,C,I,5,C  = 0
 c4145: - fire_B,N,N,C,I,5,P - fire_B,N,N,C,I,5,D - fire_B,N,N,C,I,5,W
        - fire_B,N,N,C,I,5,C - fire_B,N,N,C,I,5,A - fire_B,N,N,C,I,5,L
        - fire_B,N,N,C,I,5,N  = 0
 c4146: fire_B,N,N,C,I,5,A  = 0
 c4147: fire_B,N,N,C,I,5,L  = 0
 c4148: fire_B,N,N,C,I,5,N  = 0
 c4149: fire_B,N,N,C,A,1,P  = 0
 c4150: fire_B,N,N,C,A,1,D  = 0
 c4151: fire_B,N,N,C,A,1,W  = 0
 c4152: fire_B,N,N,C,A,1,C  = 0
 c4153: fire_B,N,N,C,A,1,I  = 0
 c4154: - fire_B,N,N,C,A,1,P - fire_B,N,N,C,A,1,D - fire_B,N,N,C,A,1,W
        - fire_B,N,N,C,A,1,C - fire_B,N,N,C,A,1,I - fire_B,N,N,C,A,1,L
        - fire_B,N,N,C,A,1,N  = 0
 c4155: fire_B,N,N,C,A,1,L  = 0
 c4156: fire_B,N,N,C,A,1,N  = 0
 c4157: fire_B,N,N,C,A,2,P  = 0
 c4158: fire_B,N,N,C,A,2,D  = 0
 c4159: fire_B,N,N,C,A,2,W  = 0
 c4160: fire_B,N,N,C,A,2,C  = 0
 c4161: fire_B,N,N,C,A,2,I  = 0
 c4162: - fire_B,N,N,C,A,2,P - fire_B,N,N,C,A,2,D - fire_B,N,N,C,A,2,W
        - fire_B,N,N,C,A,2,C - fire_B,N,N,C,A,2,I - fire_B,N,N,C,A,2,L
        - fire_B,N,N,C,A,2,N  = 0
 c4163: fire_B,N,N,C,A,2,L  = 0
 c4164: fire_B,N,N,C,A,2,N  = 0
 c4165: fire_B,N,N,C,A,3,P  = 0
 c4166: fire_B,N,N,C,A,3,D  = 0
 c4167: fire_B,N,N,C,A,3,W  = 0
 c4168: fire_B,N,N,C,A,3,C  = 0
 c4169: fire_B,N,N,C,A,3,I  = 0
 c4170: - fire_B,N,N,C,A,3,P - fire_B,N,N,C,A,3,D - fire_B,N,N,C,A,3,W
        - fire_B,N,N,C,A,3,C - fire_B,N,N,C,A,3,I - fire_B,N,N,C,A,3,L
        - fire_B,N,N,C,A,3,N  = 0
 c4171: fire_B,N,N,C,A,3,L  = 0
 c4172: fire_B,N,N,C,A,3,N  = 0
 c4173: fire_B,N,N,C,A,4,P  = 0
 c4174: fire_B,N,N,C,A,4,D  = 0
 c4175: fire_B,N,N,C,A,4,W  = 0
 c4176: fire_B,N,N,C,A,4,C  = 0
 c4177: fire_B,N,N,C,A,4,I  = 0
 c4178: - fire_B,N,N,C,A,4,P - fire_B,N,N,C,A,4,D - fire_B,N,N,C,A,4,W
        - fire_B,N,N,C,A,4,C - fire_B,N,N,C,A,4,I - fire_B,N,N,C,A,4,L
        - fire_B,N,N,C,A,4,N  = 0
 c4179: fire_B,N,N,C,A,4,L  = 0
 c4180: fire_B,N,N,C,A,4,N  = 0
 c4181: fire_B,N,N,C,A,5,P  = 0
 c4182: fire_B,N,N,C,A,5,D  = 0
 c4183: fire_B,N,N,C,A,5,W  = 0
 c4184: fire_B,N,N,C,A,5,C  = 0
 c4185: fire_B,N,N,C,A,5,I  = 0
 c4186: - fire_B,N,N,C,A,5,P - fire_B,N,N,C,A,5,D - fire_B,N,N,C,A,5,W
        - fire_B,N,N,C,A,5,C - fire_B,N,N,C,A,5,I - fire_B,N,N,C,A,5,L
        - fire_B,N,N,C,A,5,N  = 0
 c4187: fire_B,N,N,C,A,5,L  = 0
 c4188: fire_B,N,N,C,A,5,N  = 0
 c4189: fire_B,N,N,C,L,1,P  = 0
 c4190: fire_B,N,N,C,L,1,D  = 0
 c4191: fire_B,N,N,C,L,1,W  = 0
 c4192: fire_B,N,N,C,L,1,C  = 0
 c4193: fire_B,N,N,C,L,1,I  = 0
 c4194: fire_B,N,N,C,L,1,A  = 0
 c4195: - fire_B,N,N,C,L,1,P - fire_B,N,N,C,L,1,D - fire_B,N,N,C,L,1,W
        - fire_B,N,N,C,L,1,C - fire_B,N,N,C,L,1,I - fire_B,N,N,C,L,1,A
        - fire_B,N,N,C,L,1,N  = 0
 c4196: fire_B,N,N,C,L,1,N  = 0
 c4197: fire_B,N,N,C,L,2,P  = 0
 c4198: fire_B,N,N,C,L,2,D  = 0
 c4199: fire_B,N,N,C,L,2,W  = 0
 c4200: fire_B,N,N,C,L,2,C  = 0
 c4201: fire_B,N,N,C,L,2,I  = 0
 c4202: fire_B,N,N,C,L,2,A  = 0
 c4203: - fire_B,N,N,C,L,2,P - fire_B,N,N,C,L,2,D - fire_B,N,N,C,L,2,W
        - fire_B,N,N,C,L,2,C - fire_B,N,N,C,L,2,I - fire_B,N,N,C,L,2,A
        - fire_B,N,N,C,L,2,N  = 0
 c4204: fire_B,N,N,C,L,2,N  = 0
 c4205: fire_B,N,N,C,L,3,P  = 0
 c4206: fire_B,N,N,C,L,3,D  = 0
 c4207: fire_B,N,N,C,L,3,W  = 0
 c4208: fire_B,N,N,C,L,3,C  = 0
 c4209: fire_B,N,N,C,L,3,I  = 0
 c4210: fire_B,N,N,C,L,3,A  = 0
 c4211: - fire_B,N,N,C,L,3,P - fire_B,N,N,C,L,3,D - fire_B,N,N,C,L,3,W
        - fire_B,N,N,C,L,3,C - fire_B,N,N,C,L,3,I - fire_B,N,N,C,L,3,A
        - fire_B,N,N,C,L,3,N  = 0
 c4212: fire_B,N,N,C,L,3,N  = 0
 c4213: fire_B,N,N,C,L,4,P  = 0
 c4214: fire_B,N,N,C,L,4,D  = 0
 c4215: fire_B,N,N,C,L,4,W  = 0
 c4216: fire_B,N,N,C,L,4,C  = 0
 c4217: fire_B,N,N,C,L,4,I  = 0
 c4218: fire_B,N,N,C,L,4,A  = 0
 c4219: - fire_B,N,N,C,L,4,P - fire_B,N,N,C,L,4,D - fire_B,N,N,C,L,4,W
        - fire_B,N,N,C,L,4,C - fire_B,N,N,C,L,4,I - fire_B,N,N,C,L,4,A
        - fire_B,N,N,C,L,4,N  = 0
 c4220: fire_B,N,N,C,L,4,N  = 0
 c4221: fire_B,N,N,C,L,5,P  = 0
 c4222: fire_B,N,N,C,L,5,D  = 0
 c4223: fire_B,N,N,C,L,5,W  = 0
 c4224: fire_B,N,N,C,L,5,C  = 0
 c4225: fire_B,N,N,C,L,5,I  = 0
 c4226: fire_B,N,N,C,L,5,A  = 0
 c4227: - fire_B,N,N,C,L,5,P - fire_B,N,N,C,L,5,D - fire_B,N,N,C,L,5,W
        - fire_B,N,N,C,L,5,C - fire_B,N,N,C,L,5,I - fire_B,N,N,C,L,5,A
        - fire_B,N,N,C,L,5,N  = 0
 c4228: fire_B,N,N,C,L,5,N  = 0
 c4229: fire_B,N,N,C,N,1,P  = 0
 c4230: fire_B,N,N,C,N,1,D  = 0
 c4231: fire_B,N,N,C,N,1,W  = 0
 c4232: fire_B,N,N,C,N,1,C  = 0
 c4233: fire_B,N,N,C,N,1,I  = 0
 c4234: fire_B,N,N,C,N,1,A  = 0
 c4235: fire_B,N,N,C,N,1,L  = 0
 c4236: - fire_B,N,N,C,N,1,P - fire_B,N,N,C,N,1,D - fire_B,N,N,C,N,1,W
        - fire_B,N,N,C,N,1,C - fire_B,N,N,C,N,1,I - fire_B,N,N,C,N,1,A
        - fire_B,N,N,C,N,1,L  = 0
 c4237: fire_B,N,N,C,N,2,P  = 0
 c4238: fire_B,N,N,C,N,2,D  = 0
 c4239: fire_B,N,N,C,N,2,W  = 0
 c4240: fire_B,N,N,C,N,2,C  = 0
 c4241: fire_B,N,N,C,N,2,I  = 0
 c4242: fire_B,N,N,C,N,2,A  = 0
 c4243: fire_B,N,N,C,N,2,L  = 0
 c4244: - fire_B,N,N,C,N,2,P - fire_B,N,N,C,N,2,D - fire_B,N,N,C,N,2,W
        - fire_B,N,N,C,N,2,C - fire_B,N,N,C,N,2,I - fire_B,N,N,C,N,2,A
        - fire_B,N,N,C,N,2,L  = 0
 c4245: fire_B,N,N,C,N,3,P  = 0
 c4246: fire_B,N,N,C,N,3,D  = 0
 c4247: fire_B,N,N,C,N,3,W  = 0
 c4248: fire_B,N,N,C,N,3,C  = 0
 c4249: fire_B,N,N,C,N,3,I  = 0
 c4250: fire_B,N,N,C,N,3,A  = 0
 c4251: fire_B,N,N,C,N,3,L  = 0
 c4252: - fire_B,N,N,C,N,3,P - fire_B,N,N,C,N,3,D - fire_B,N,N,C,N,3,W
        - fire_B,N,N,C,N,3,C - fire_B,N,N,C,N,3,I - fire_B,N,N,C,N,3,A
        - fire_B,N,N,C,N,3,L  = 0
 c4253: fire_B,N,N,C,N,4,P  = 0
 c4254: fire_B,N,N,C,N,4,D  = 0
 c4255: fire_B,N,N,C,N,4,W  = 0
 c4256: fire_B,N,N,C,N,4,C  = 0
 c4257: fire_B,N,N,C,N,4,I  = 0
 c4258: fire_B,N,N,C,N,4,A  = 0
 c4259: fire_B,N,N,C,N,4,L  = 0
 c4260: - fire_B,N,N,C,N,4,P - fire_B,N,N,C,N,4,D - fire_B,N,N,C,N,4,W
        - fire_B,N,N,C,N,4,C - fire_B,N,N,C,N,4,I - fire_B,N,N,C,N,4,A
        - fire_B,N,N,C,N,4,L  = 0
 c4261: fire_B,N,N,C,N,5,P  = 0
 c4262: fire_B,N,N,C,N,5,D  = 0
 c4263: fire_B,N,N,C,N,5,W  = 0
 c4264: fire_B,N,N,C,N,5,C  = 0
 c4265: fire_B,N,N,C,N,5,I  = 0
 c4266: fire_B,N,N,C,N,5,A  = 0
 c4267: fire_B,N,N,C,N,5,L  = 0
 c4268: - fire_B,N,N,C,N,5,P - fire_B,N,N,C,N,5,D - fire_B,N,N,C,N,5,W
        - fire_B,N,N,C,N,5,C - fire_B,N,N,C,N,5,I - fire_B,N,N,C,N,5,A
        - fire_B,N,N,C,N,5,L  = 0
 c4269: 0.9 fire_B,N,P,R,P,1,P - 0.1 fire_B,N,P,R,P,1,D
        - 0.1 fire_B,N,P,R,P,1,W - 0.1 fire_B,N,P,R,P,1,C
        - 0.1 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        - 0.1 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4270: - 0.1 fire_B,N,P,R,P,1,P + 0.9 fire_B,N,P,R,P,1,D
        - 0.1 fire_B,N,P,R,P,1,W - 0.1 fire_B,N,P,R,P,1,C
        - 0.1 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        - 0.1 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4271: - 0.1 fire_B,N,P,R,P,1,P - 0.1 fire_B,N,P,R,P,1,D
        + 0.9 fire_B,N,P,R,P,1,W - 0.1 fire_B,N,P,R,P,1,C
        - 0.1 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        - 0.1 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4272: - 0.1 fire_B,N,P,R,P,1,P - 0.1 fire_B,N,P,R,P,1,D
        - 0.1 fire_B,N,P,R,P,1,W + 0.9 fire_B,N,P,R,P,1,C
        - 0.1 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        - 0.1 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4273: - 0.1 fire_B,N,P,R,P,1,P - 0.1 fire_B,N,P,R,P,1,D
        - 0.1 fire_B,N,P,R,P,1,W - 0.1 fire_B,N,P,R,P,1,C
        + 0.9 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        - 0.1 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4274: - 0.2 fire_B,N,P,R,P,1,P - 0.2 fire_B,N,P,R,P,1,D
        - 0.2 fire_B,N,P,R,P,1,W - 0.2 fire_B,N,P,R,P,1,C
        - 0.2 fire_B,N,P,R,P,1,I + 0.8 fire_B,N,P,R,P,1,A
        - 0.2 fire_B,N,P,R,P,1,L - 0.2 fire_B,N,P,R,P,1,N  = 0
 c4275: - 0.1 fire_B,N,P,R,P,1,P - 0.1 fire_B,N,P,R,P,1,D
        - 0.1 fire_B,N,P,R,P,1,W - 0.1 fire_B,N,P,R,P,1,C
        - 0.1 fire_B,N,P,R,P,1,I - 0.1 fire_B,N,P,R,P,1,A
        + 0.9 fire_B,N,P,R,P,1,L - 0.1 fire_B,N,P,R,P,1,N  = 0
 c4276: - 0.2 fire_B,N,P,R,P,1,P - 0.2 fire_B,N,P,R,P,1,D
        - 0.2 fire_B,N,P,R,P,1,W - 0.2 fire_B,N,P,R,P,1,C
        - 0.2 fire_B,N,P,R,P,1,I - 0.2 fire_B,N,P,R,P,1,A
        - 0.2 fire_B,N,P,R,P,1,L + 0.8 fire_B,N,P,R,P,1,N  = 0
 c4277: 0.9 fire_B,N,P,R,P,2,P - 0.1 fire_B,N,P,R,P,2,D
        - 0.1 fire_B,N,P,R,P,2,W - 0.1 fire_B,N,P,R,P,2,C
        - 0.1 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        - 0.1 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4278: - 0.1 fire_B,N,P,R,P,2,P + 0.9 fire_B,N,P,R,P,2,D
        - 0.1 fire_B,N,P,R,P,2,W - 0.1 fire_B,N,P,R,P,2,C
        - 0.1 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        - 0.1 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4279: - 0.1 fire_B,N,P,R,P,2,P - 0.1 fire_B,N,P,R,P,2,D
        + 0.9 fire_B,N,P,R,P,2,W - 0.1 fire_B,N,P,R,P,2,C
        - 0.1 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        - 0.1 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4280: - 0.1 fire_B,N,P,R,P,2,P - 0.1 fire_B,N,P,R,P,2,D
        - 0.1 fire_B,N,P,R,P,2,W + 0.9 fire_B,N,P,R,P,2,C
        - 0.1 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        - 0.1 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4281: - 0.1 fire_B,N,P,R,P,2,P - 0.1 fire_B,N,P,R,P,2,D
        - 0.1 fire_B,N,P,R,P,2,W - 0.1 fire_B,N,P,R,P,2,C
        + 0.9 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        - 0.1 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4282: - 0.2 fire_B,N,P,R,P,2,P - 0.2 fire_B,N,P,R,P,2,D
        - 0.2 fire_B,N,P,R,P,2,W - 0.2 fire_B,N,P,R,P,2,C
        - 0.2 fire_B,N,P,R,P,2,I + 0.8 fire_B,N,P,R,P,2,A
        - 0.2 fire_B,N,P,R,P,2,L - 0.2 fire_B,N,P,R,P,2,N  = 0
 c4283: - 0.1 fire_B,N,P,R,P,2,P - 0.1 fire_B,N,P,R,P,2,D
        - 0.1 fire_B,N,P,R,P,2,W - 0.1 fire_B,N,P,R,P,2,C
        - 0.1 fire_B,N,P,R,P,2,I - 0.1 fire_B,N,P,R,P,2,A
        + 0.9 fire_B,N,P,R,P,2,L - 0.1 fire_B,N,P,R,P,2,N  = 0
 c4284: - 0.2 fire_B,N,P,R,P,2,P - 0.2 fire_B,N,P,R,P,2,D
        - 0.2 fire_B,N,P,R,P,2,W - 0.2 fire_B,N,P,R,P,2,C
        - 0.2 fire_B,N,P,R,P,2,I - 0.2 fire_B,N,P,R,P,2,A
        - 0.2 fire_B,N,P,R,P,2,L + 0.8 fire_B,N,P,R,P,2,N  = 0
 c4285: 0.9 fire_B,N,P,R,P,3,P - 0.1 fire_B,N,P,R,P,3,D
        - 0.1 fire_B,N,P,R,P,3,W - 0.1 fire_B,N,P,R,P,3,C
        - 0.1 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        - 0.1 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4286: - 0.1 fire_B,N,P,R,P,3,P + 0.9 fire_B,N,P,R,P,3,D
        - 0.1 fire_B,N,P,R,P,3,W - 0.1 fire_B,N,P,R,P,3,C
        - 0.1 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        - 0.1 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4287: - 0.1 fire_B,N,P,R,P,3,P - 0.1 fire_B,N,P,R,P,3,D
        + 0.9 fire_B,N,P,R,P,3,W - 0.1 fire_B,N,P,R,P,3,C
        - 0.1 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        - 0.1 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4288: - 0.1 fire_B,N,P,R,P,3,P - 0.1 fire_B,N,P,R,P,3,D
        - 0.1 fire_B,N,P,R,P,3,W + 0.9 fire_B,N,P,R,P,3,C
        - 0.1 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        - 0.1 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4289: - 0.1 fire_B,N,P,R,P,3,P - 0.1 fire_B,N,P,R,P,3,D
        - 0.1 fire_B,N,P,R,P,3,W - 0.1 fire_B,N,P,R,P,3,C
        + 0.9 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        - 0.1 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4290: - 0.2 fire_B,N,P,R,P,3,P - 0.2 fire_B,N,P,R,P,3,D
        - 0.2 fire_B,N,P,R,P,3,W - 0.2 fire_B,N,P,R,P,3,C
        - 0.2 fire_B,N,P,R,P,3,I + 0.8 fire_B,N,P,R,P,3,A
        - 0.2 fire_B,N,P,R,P,3,L - 0.2 fire_B,N,P,R,P,3,N  = 0
 c4291: - 0.1 fire_B,N,P,R,P,3,P - 0.1 fire_B,N,P,R,P,3,D
        - 0.1 fire_B,N,P,R,P,3,W - 0.1 fire_B,N,P,R,P,3,C
        - 0.1 fire_B,N,P,R,P,3,I - 0.1 fire_B,N,P,R,P,3,A
        + 0.9 fire_B,N,P,R,P,3,L - 0.1 fire_B,N,P,R,P,3,N  = 0
 c4292: - 0.2 fire_B,N,P,R,P,3,P - 0.2 fire_B,N,P,R,P,3,D
        - 0.2 fire_B,N,P,R,P,3,W - 0.2 fire_B,N,P,R,P,3,C
        - 0.2 fire_B,N,P,R,P,3,I - 0.2 fire_B,N,P,R,P,3,A
        - 0.2 fire_B,N,P,R,P,3,L + 0.8 fire_B,N,P,R,P,3,N  = 0
 c4293: 0.9 fire_B,N,P,R,P,4,P - 0.1 fire_B,N,P,R,P,4,D
        - 0.1 fire_B,N,P,R,P,4,W - 0.1 fire_B,N,P,R,P,4,C
        - 0.1 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        - 0.1 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4294: - 0.1 fire_B,N,P,R,P,4,P + 0.9 fire_B,N,P,R,P,4,D
        - 0.1 fire_B,N,P,R,P,4,W - 0.1 fire_B,N,P,R,P,4,C
        - 0.1 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        - 0.1 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4295: - 0.1 fire_B,N,P,R,P,4,P - 0.1 fire_B,N,P,R,P,4,D
        + 0.9 fire_B,N,P,R,P,4,W - 0.1 fire_B,N,P,R,P,4,C
        - 0.1 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        - 0.1 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4296: - 0.1 fire_B,N,P,R,P,4,P - 0.1 fire_B,N,P,R,P,4,D
        - 0.1 fire_B,N,P,R,P,4,W + 0.9 fire_B,N,P,R,P,4,C
        - 0.1 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        - 0.1 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4297: - 0.1 fire_B,N,P,R,P,4,P - 0.1 fire_B,N,P,R,P,4,D
        - 0.1 fire_B,N,P,R,P,4,W - 0.1 fire_B,N,P,R,P,4,C
        + 0.9 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        - 0.1 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4298: - 0.2 fire_B,N,P,R,P,4,P - 0.2 fire_B,N,P,R,P,4,D
        - 0.2 fire_B,N,P,R,P,4,W - 0.2 fire_B,N,P,R,P,4,C
        - 0.2 fire_B,N,P,R,P,4,I + 0.8 fire_B,N,P,R,P,4,A
        - 0.2 fire_B,N,P,R,P,4,L - 0.2 fire_B,N,P,R,P,4,N  = 0
 c4299: - 0.1 fire_B,N,P,R,P,4,P - 0.1 fire_B,N,P,R,P,4,D
        - 0.1 fire_B,N,P,R,P,4,W - 0.1 fire_B,N,P,R,P,4,C
        - 0.1 fire_B,N,P,R,P,4,I - 0.1 fire_B,N,P,R,P,4,A
        + 0.9 fire_B,N,P,R,P,4,L - 0.1 fire_B,N,P,R,P,4,N  = 0
 c4300: - 0.2 fire_B,N,P,R,P,4,P - 0.2 fire_B,N,P,R,P,4,D
        - 0.2 fire_B,N,P,R,P,4,W - 0.2 fire_B,N,P,R,P,4,C
        - 0.2 fire_B,N,P,R,P,4,I - 0.2 fire_B,N,P,R,P,4,A
        - 0.2 fire_B,N,P,R,P,4,L + 0.8 fire_B,N,P,R,P,4,N  = 0
 c4301: 0.9 fire_B,N,P,R,P,5,P - 0.1 fire_B,N,P,R,P,5,D
        - 0.1 fire_B,N,P,R,P,5,W - 0.1 fire_B,N,P,R,P,5,C
        - 0.1 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        - 0.1 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4302: - 0.1 fire_B,N,P,R,P,5,P + 0.9 fire_B,N,P,R,P,5,D
        - 0.1 fire_B,N,P,R,P,5,W - 0.1 fire_B,N,P,R,P,5,C
        - 0.1 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        - 0.1 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4303: - 0.1 fire_B,N,P,R,P,5,P - 0.1 fire_B,N,P,R,P,5,D
        + 0.9 fire_B,N,P,R,P,5,W - 0.1 fire_B,N,P,R,P,5,C
        - 0.1 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        - 0.1 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4304: - 0.1 fire_B,N,P,R,P,5,P - 0.1 fire_B,N,P,R,P,5,D
        - 0.1 fire_B,N,P,R,P,5,W + 0.9 fire_B,N,P,R,P,5,C
        - 0.1 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        - 0.1 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4305: - 0.1 fire_B,N,P,R,P,5,P - 0.1 fire_B,N,P,R,P,5,D
        - 0.1 fire_B,N,P,R,P,5,W - 0.1 fire_B,N,P,R,P,5,C
        + 0.9 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        - 0.1 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4306: - 0.2 fire_B,N,P,R,P,5,P - 0.2 fire_B,N,P,R,P,5,D
        - 0.2 fire_B,N,P,R,P,5,W - 0.2 fire_B,N,P,R,P,5,C
        - 0.2 fire_B,N,P,R,P,5,I + 0.8 fire_B,N,P,R,P,5,A
        - 0.2 fire_B,N,P,R,P,5,L - 0.2 fire_B,N,P,R,P,5,N  = 0
 c4307: - 0.1 fire_B,N,P,R,P,5,P - 0.1 fire_B,N,P,R,P,5,D
        - 0.1 fire_B,N,P,R,P,5,W - 0.1 fire_B,N,P,R,P,5,C
        - 0.1 fire_B,N,P,R,P,5,I - 0.1 fire_B,N,P,R,P,5,A
        + 0.9 fire_B,N,P,R,P,5,L - 0.1 fire_B,N,P,R,P,5,N  = 0
 c4308: - 0.2 fire_B,N,P,R,P,5,P - 0.2 fire_B,N,P,R,P,5,D
        - 0.2 fire_B,N,P,R,P,5,W - 0.2 fire_B,N,P,R,P,5,C
        - 0.2 fire_B,N,P,R,P,5,I - 0.2 fire_B,N,P,R,P,5,A
        - 0.2 fire_B,N,P,R,P,5,L + 0.8 fire_B,N,P,R,P,5,N  = 0
 c4309: 0.5 fire_B,N,P,R,D,1,P - 0.5 fire_B,N,P,R,D,1,D
        - 0.5 fire_B,N,P,R,D,1,W - 0.5 fire_B,N,P,R,D,1,C
        - 0.5 fire_B,N,P,R,D,1,I - 0.5 fire_B,N,P,R,D,1,A
        - 0.5 fire_B,N,P,R,D,1,L - 0.5 fire_B,N,P,R,D,1,N  = 0
 c4310: - 0.5 fire_B,N,P,R,D,1,P + 0.5 fire_B,N,P,R,D,1,D
        - 0.5 fire_B,N,P,R,D,1,W - 0.5 fire_B,N,P,R,D,1,C
        - 0.5 fire_B,N,P,R,D,1,I - 0.5 fire_B,N,P,R,D,1,A
        - 0.5 fire_B,N,P,R,D,1,L - 0.5 fire_B,N,P,R,D,1,N  = 0
 c4311: fire_B,N,P,R,D,1,W  = 0
 c4312: fire_B,N,P,R,D,1,C  = 0
 c4313: fire_B,N,P,R,D,1,I  = 0
 c4314: fire_B,N,P,R,D,1,A  = 0
 c4315: fire_B,N,P,R,D,1,L  = 0
 c4316: fire_B,N,P,R,D,1,N  = 0
 c4317: 0.5 fire_B,N,P,R,D,2,P - 0.5 fire_B,N,P,R,D,2,D
        - 0.5 fire_B,N,P,R,D,2,W - 0.5 fire_B,N,P,R,D,2,C
        - 0.5 fire_B,N,P,R,D,2,I - 0.5 fire_B,N,P,R,D,2,A
        - 0.5 fire_B,N,P,R,D,2,L - 0.5 fire_B,N,P,R,D,2,N  = 0
 c4318: - 0.5 fire_B,N,P,R,D,2,P + 0.5 fire_B,N,P,R,D,2,D
        - 0.5 fire_B,N,P,R,D,2,W - 0.5 fire_B,N,P,R,D,2,C
        - 0.5 fire_B,N,P,R,D,2,I - 0.5 fire_B,N,P,R,D,2,A
        - 0.5 fire_B,N,P,R,D,2,L - 0.5 fire_B,N,P,R,D,2,N  = 0
 c4319: fire_B,N,P,R,D,2,W  = 0
 c4320: fire_B,N,P,R,D,2,C  = 0
 c4321: fire_B,N,P,R,D,2,I  = 0
 c4322: fire_B,N,P,R,D,2,A  = 0
 c4323: fire_B,N,P,R,D,2,L  = 0
 c4324: fire_B,N,P,R,D,2,N  = 0
 c4325: 0.5 fire_B,N,P,R,D,3,P - 0.5 fire_B,N,P,R,D,3,D
        - 0.5 fire_B,N,P,R,D,3,W - 0.5 fire_B,N,P,R,D,3,C
        - 0.5 fire_B,N,P,R,D,3,I - 0.5 fire_B,N,P,R,D,3,A
        - 0.5 fire_B,N,P,R,D,3,L - 0.5 fire_B,N,P,R,D,3,N  = 0
 c4326: - 0.5 fire_B,N,P,R,D,3,P + 0.5 fire_B,N,P,R,D,3,D
        - 0.5 fire_B,N,P,R,D,3,W - 0.5 fire_B,N,P,R,D,3,C
        - 0.5 fire_B,N,P,R,D,3,I - 0.5 fire_B,N,P,R,D,3,A
        - 0.5 fire_B,N,P,R,D,3,L - 0.5 fire_B,N,P,R,D,3,N  = 0
 c4327: fire_B,N,P,R,D,3,W  = 0
 c4328: fire_B,N,P,R,D,3,C  = 0
 c4329: fire_B,N,P,R,D,3,I  = 0
 c4330: fire_B,N,P,R,D,3,A  = 0
 c4331: fire_B,N,P,R,D,3,L  = 0
 c4332: fire_B,N,P,R,D,3,N  = 0
 c4333: 0.5 fire_B,N,P,R,D,4,P - 0.5 fire_B,N,P,R,D,4,D
        - 0.5 fire_B,N,P,R,D,4,W - 0.5 fire_B,N,P,R,D,4,C
        - 0.5 fire_B,N,P,R,D,4,I - 0.5 fire_B,N,P,R,D,4,A
        - 0.5 fire_B,N,P,R,D,4,L - 0.5 fire_B,N,P,R,D,4,N  = 0
 c4334: - 0.5 fire_B,N,P,R,D,4,P + 0.5 fire_B,N,P,R,D,4,D
        - 0.5 fire_B,N,P,R,D,4,W - 0.5 fire_B,N,P,R,D,4,C
        - 0.5 fire_B,N,P,R,D,4,I - 0.5 fire_B,N,P,R,D,4,A
        - 0.5 fire_B,N,P,R,D,4,L - 0.5 fire_B,N,P,R,D,4,N  = 0
 c4335: fire_B,N,P,R,D,4,W  = 0
 c4336: fire_B,N,P,R,D,4,C  = 0
 c4337: fire_B,N,P,R,D,4,I  = 0
 c4338: fire_B,N,P,R,D,4,A  = 0
 c4339: fire_B,N,P,R,D,4,L  = 0
 c4340: fire_B,N,P,R,D,4,N  = 0
 c4341: 0.5 fire_B,N,P,R,D,5,P - 0.5 fire_B,N,P,R,D,5,D
        - 0.5 fire_B,N,P,R,D,5,W - 0.5 fire_B,N,P,R,D,5,C
        - 0.5 fire_B,N,P,R,D,5,I - 0.5 fire_B,N,P,R,D,5,A
        - 0.5 fire_B,N,P,R,D,5,L - 0.5 fire_B,N,P,R,D,5,N  = 0
 c4342: - 0.5 fire_B,N,P,R,D,5,P + 0.5 fire_B,N,P,R,D,5,D
        - 0.5 fire_B,N,P,R,D,5,W - 0.5 fire_B,N,P,R,D,5,C
        - 0.5 fire_B,N,P,R,D,5,I - 0.5 fire_B,N,P,R,D,5,A
        - 0.5 fire_B,N,P,R,D,5,L - 0.5 fire_B,N,P,R,D,5,N  = 0
 c4343: fire_B,N,P,R,D,5,W  = 0
 c4344: fire_B,N,P,R,D,5,C  = 0
 c4345: fire_B,N,P,R,D,5,I  = 0
 c4346: fire_B,N,P,R,D,5,A  = 0
 c4347: fire_B,N,P,R,D,5,L  = 0
 c4348: fire_B,N,P,R,D,5,N  = 0
 c4349: fire_B,N,P,R,W,1,P  = 0
 c4350: fire_B,N,P,R,W,1,D  = 0
 c4351: - fire_B,N,P,R,W,1,P - fire_B,N,P,R,W,1,D - fire_B,N,P,R,W,1,C
        - fire_B,N,P,R,W,1,I - fire_B,N,P,R,W,1,A - fire_B,N,P,R,W,1,L
        - fire_B,N,P,R,W,1,N  = 0
 c4352: fire_B,N,P,R,W,1,C  = 0
 c4353: fire_B,N,P,R,W,1,I  = 0
 c4354: fire_B,N,P,R,W,1,A  = 0
 c4355: fire_B,N,P,R,W,1,L  = 0
 c4356: fire_B,N,P,R,W,1,N  = 0
 c4357: fire_B,N,P,R,W,2,P  = 0
 c4358: fire_B,N,P,R,W,2,D  = 0
 c4359: - fire_B,N,P,R,W,2,P - fire_B,N,P,R,W,2,D - fire_B,N,P,R,W,2,C
        - fire_B,N,P,R,W,2,I - fire_B,N,P,R,W,2,A - fire_B,N,P,R,W,2,L
        - fire_B,N,P,R,W,2,N  = 0
 c4360: fire_B,N,P,R,W,2,C  = 0
 c4361: fire_B,N,P,R,W,2,I  = 0
 c4362: fire_B,N,P,R,W,2,A  = 0
 c4363: fire_B,N,P,R,W,2,L  = 0
 c4364: fire_B,N,P,R,W,2,N  = 0
 c4365: fire_B,N,P,R,W,3,P  = 0
 c4366: fire_B,N,P,R,W,3,D  = 0
 c4367: - fire_B,N,P,R,W,3,P - fire_B,N,P,R,W,3,D - fire_B,N,P,R,W,3,C
        - fire_B,N,P,R,W,3,I - fire_B,N,P,R,W,3,A - fire_B,N,P,R,W,3,L
        - fire_B,N,P,R,W,3,N  = 0
 c4368: fire_B,N,P,R,W,3,C  = 0
 c4369: fire_B,N,P,R,W,3,I  = 0
 c4370: fire_B,N,P,R,W,3,A  = 0
 c4371: fire_B,N,P,R,W,3,L  = 0
 c4372: fire_B,N,P,R,W,3,N  = 0
 c4373: fire_B,N,P,R,W,4,P  = 0
 c4374: fire_B,N,P,R,W,4,D  = 0
 c4375: - fire_B,N,P,R,W,4,P - fire_B,N,P,R,W,4,D - fire_B,N,P,R,W,4,C
        - fire_B,N,P,R,W,4,I - fire_B,N,P,R,W,4,A - fire_B,N,P,R,W,4,L
        - fire_B,N,P,R,W,4,N  = 0
 c4376: fire_B,N,P,R,W,4,C  = 0
 c4377: fire_B,N,P,R,W,4,I  = 0
 c4378: fire_B,N,P,R,W,4,A  = 0
 c4379: fire_B,N,P,R,W,4,L  = 0
 c4380: fire_B,N,P,R,W,4,N  = 0
 c4381: fire_B,N,P,R,W,5,P  = 0
 c4382: fire_B,N,P,R,W,5,D  = 0
 c4383: - fire_B,N,P,R,W,5,P - fire_B,N,P,R,W,5,D - fire_B,N,P,R,W,5,C
        - fire_B,N,P,R,W,5,I - fire_B,N,P,R,W,5,A - fire_B,N,P,R,W,5,L
        - fire_B,N,P,R,W,5,N  = 0
 c4384: fire_B,N,P,R,W,5,C  = 0
 c4385: fire_B,N,P,R,W,5,I  = 0
 c4386: fire_B,N,P,R,W,5,A  = 0
 c4387: fire_B,N,P,R,W,5,L  = 0
 c4388: fire_B,N,P,R,W,5,N  = 0
 c4389: fire_B,N,P,R,C,1,P  = 0
 c4390: fire_B,N,P,R,C,1,D  = 0
 c4391: fire_B,N,P,R,C,1,W  = 0
 c4392: - fire_B,N,P,R,C,1,P - fire_B,N,P,R,C,1,D - fire_B,N,P,R,C,1,W
        - fire_B,N,P,R,C,1,I - fire_B,N,P,R,C,1,A - fire_B,N,P,R,C,1,L
        - fire_B,N,P,R,C,1,N  = 0
 c4393: fire_B,N,P,R,C,1,I  = 0
 c4394: fire_B,N,P,R,C,1,A  = 0
 c4395: fire_B,N,P,R,C,1,L  = 0
 c4396: fire_B,N,P,R,C,1,N  = 0
 c4397: fire_B,N,P,R,C,2,P  = 0
 c4398: fire_B,N,P,R,C,2,D  = 0
 c4399: fire_B,N,P,R,C,2,W  = 0
 c4400: - fire_B,N,P,R,C,2,P - fire_B,N,P,R,C,2,D - fire_B,N,P,R,C,2,W
        - fire_B,N,P,R,C,2,I - fire_B,N,P,R,C,2,A - fire_B,N,P,R,C,2,L
        - fire_B,N,P,R,C,2,N  = 0
 c4401: fire_B,N,P,R,C,2,I  = 0
 c4402: fire_B,N,P,R,C,2,A  = 0
 c4403: fire_B,N,P,R,C,2,L  = 0
 c4404: fire_B,N,P,R,C,2,N  = 0
 c4405: fire_B,N,P,R,C,3,P  = 0
 c4406: fire_B,N,P,R,C,3,D  = 0
 c4407: fire_B,N,P,R,C,3,W  = 0
 c4408: - fire_B,N,P,R,C,3,P - fire_B,N,P,R,C,3,D - fire_B,N,P,R,C,3,W
        - fire_B,N,P,R,C,3,I - fire_B,N,P,R,C,3,A - fire_B,N,P,R,C,3,L
        - fire_B,N,P,R,C,3,N  = 0
 c4409: fire_B,N,P,R,C,3,I  = 0
 c4410: fire_B,N,P,R,C,3,A  = 0
 c4411: fire_B,N,P,R,C,3,L  = 0
 c4412: fire_B,N,P,R,C,3,N  = 0
 c4413: fire_B,N,P,R,C,4,P  = 0
 c4414: fire_B,N,P,R,C,4,D  = 0
 c4415: fire_B,N,P,R,C,4,W  = 0
 c4416: - fire_B,N,P,R,C,4,P - fire_B,N,P,R,C,4,D - fire_B,N,P,R,C,4,W
        - fire_B,N,P,R,C,4,I - fire_B,N,P,R,C,4,A - fire_B,N,P,R,C,4,L
        - fire_B,N,P,R,C,4,N  = 0
 c4417: fire_B,N,P,R,C,4,I  = 0
 c4418: fire_B,N,P,R,C,4,A  = 0
 c4419: fire_B,N,P,R,C,4,L  = 0
 c4420: fire_B,N,P,R,C,4,N  = 0
 c4421: fire_B,N,P,R,C,5,P  = 0
 c4422: fire_B,N,P,R,C,5,D  = 0
 c4423: fire_B,N,P,R,C,5,W  = 0
 c4424: - fire_B,N,P,R,C,5,P - fire_B,N,P,R,C,5,D - fire_B,N,P,R,C,5,W
        - fire_B,N,P,R,C,5,I - fire_B,N,P,R,C,5,A - fire_B,N,P,R,C,5,L
        - fire_B,N,P,R,C,5,N  = 0
 c4425: fire_B,N,P,R,C,5,I  = 0
 c4426: fire_B,N,P,R,C,5,A  = 0
 c4427: fire_B,N,P,R,C,5,L  = 0
 c4428: fire_B,N,P,R,C,5,N  = 0
 c4429: fire_B,N,P,R,I,1,P  = 0
 c4430: fire_B,N,P,R,I,1,D  = 0
 c4431: fire_B,N,P,R,I,1,W  = 0
 c4432: fire_B,N,P,R,I,1,C  = 0
 c4433: - fire_B,N,P,R,I,1,P - fire_B,N,P,R,I,1,D - fire_B,N,P,R,I,1,W
        - fire_B,N,P,R,I,1,C - fire_B,N,P,R,I,1,A - fire_B,N,P,R,I,1,L
        - fire_B,N,P,R,I,1,N  = 0
 c4434: fire_B,N,P,R,I,1,A  = 0
 c4435: fire_B,N,P,R,I,1,L  = 0
 c4436: fire_B,N,P,R,I,1,N  = 0
 c4437: fire_B,N,P,R,I,2,P  = 0
 c4438: fire_B,N,P,R,I,2,D  = 0
 c4439: fire_B,N,P,R,I,2,W  = 0
 c4440: fire_B,N,P,R,I,2,C  = 0
 c4441: - fire_B,N,P,R,I,2,P - fire_B,N,P,R,I,2,D - fire_B,N,P,R,I,2,W
        - fire_B,N,P,R,I,2,C - fire_B,N,P,R,I,2,A - fire_B,N,P,R,I,2,L
        - fire_B,N,P,R,I,2,N  = 0
 c4442: fire_B,N,P,R,I,2,A  = 0
 c4443: fire_B,N,P,R,I,2,L  = 0
 c4444: fire_B,N,P,R,I,2,N  = 0
 c4445: fire_B,N,P,R,I,3,P  = 0
 c4446: fire_B,N,P,R,I,3,D  = 0
 c4447: fire_B,N,P,R,I,3,W  = 0
 c4448: fire_B,N,P,R,I,3,C  = 0
 c4449: - fire_B,N,P,R,I,3,P - fire_B,N,P,R,I,3,D - fire_B,N,P,R,I,3,W
        - fire_B,N,P,R,I,3,C - fire_B,N,P,R,I,3,A - fire_B,N,P,R,I,3,L
        - fire_B,N,P,R,I,3,N  = 0
 c4450: fire_B,N,P,R,I,3,A  = 0
 c4451: fire_B,N,P,R,I,3,L  = 0
 c4452: fire_B,N,P,R,I,3,N  = 0
 c4453: fire_B,N,P,R,I,4,P  = 0
 c4454: fire_B,N,P,R,I,4,D  = 0
 c4455: fire_B,N,P,R,I,4,W  = 0
 c4456: fire_B,N,P,R,I,4,C  = 0
 c4457: - fire_B,N,P,R,I,4,P - fire_B,N,P,R,I,4,D - fire_B,N,P,R,I,4,W
        - fire_B,N,P,R,I,4,C - fire_B,N,P,R,I,4,A - fire_B,N,P,R,I,4,L
        - fire_B,N,P,R,I,4,N  = 0
 c4458: fire_B,N,P,R,I,4,A  = 0
 c4459: fire_B,N,P,R,I,4,L  = 0
 c4460: fire_B,N,P,R,I,4,N  = 0
 c4461: fire_B,N,P,R,I,5,P  = 0
 c4462: fire_B,N,P,R,I,5,D  = 0
 c4463: fire_B,N,P,R,I,5,W  = 0
 c4464: fire_B,N,P,R,I,5,C  = 0
 c4465: - fire_B,N,P,R,I,5,P - fire_B,N,P,R,I,5,D - fire_B,N,P,R,I,5,W
        - fire_B,N,P,R,I,5,C - fire_B,N,P,R,I,5,A - fire_B,N,P,R,I,5,L
        - fire_B,N,P,R,I,5,N  = 0
 c4466: fire_B,N,P,R,I,5,A  = 0
 c4467: fire_B,N,P,R,I,5,L  = 0
 c4468: fire_B,N,P,R,I,5,N  = 0
 c4469: fire_B,N,P,R,A,1,P  = 0
 c4470: fire_B,N,P,R,A,1,D  = 0
 c4471: fire_B,N,P,R,A,1,W  = 0
 c4472: fire_B,N,P,R,A,1,C  = 0
 c4473: fire_B,N,P,R,A,1,I  = 0
 c4474: - fire_B,N,P,R,A,1,P - fire_B,N,P,R,A,1,D - fire_B,N,P,R,A,1,W
        - fire_B,N,P,R,A,1,C - fire_B,N,P,R,A,1,I - fire_B,N,P,R,A,1,L
        - fire_B,N,P,R,A,1,N  = 0
 c4475: fire_B,N,P,R,A,1,L  = 0
 c4476: fire_B,N,P,R,A,1,N  = 0
 c4477: fire_B,N,P,R,A,2,P  = 0
 c4478: fire_B,N,P,R,A,2,D  = 0
 c4479: fire_B,N,P,R,A,2,W  = 0
 c4480: fire_B,N,P,R,A,2,C  = 0
 c4481: fire_B,N,P,R,A,2,I  = 0
 c4482: - fire_B,N,P,R,A,2,P - fire_B,N,P,R,A,2,D - fire_B,N,P,R,A,2,W
        - fire_B,N,P,R,A,2,C - fire_B,N,P,R,A,2,I - fire_B,N,P,R,A,2,L
        - fire_B,N,P,R,A,2,N  = 0
 c4483: fire_B,N,P,R,A,2,L  = 0
 c4484: fire_B,N,P,R,A,2,N  = 0
 c4485: fire_B,N,P,R,A,3,P  = 0
 c4486: fire_B,N,P,R,A,3,D  = 0
 c4487: fire_B,N,P,R,A,3,W  = 0
 c4488: fire_B,N,P,R,A,3,C  = 0
 c4489: fire_B,N,P,R,A,3,I  = 0
 c4490: - fire_B,N,P,R,A,3,P - fire_B,N,P,R,A,3,D - fire_B,N,P,R,A,3,W
        - fire_B,N,P,R,A,3,C - fire_B,N,P,R,A,3,I - fire_B,N,P,R,A,3,L
        - fire_B,N,P,R,A,3,N  = 0
 c4491: fire_B,N,P,R,A,3,L  = 0
 c4492: fire_B,N,P,R,A,3,N  = 0
 c4493: fire_B,N,P,R,A,4,P  = 0
 c4494: fire_B,N,P,R,A,4,D  = 0
 c4495: fire_B,N,P,R,A,4,W  = 0
 c4496: fire_B,N,P,R,A,4,C  = 0
 c4497: fire_B,N,P,R,A,4,I  = 0
 c4498: - fire_B,N,P,R,A,4,P - fire_B,N,P,R,A,4,D - fire_B,N,P,R,A,4,W
        - fire_B,N,P,R,A,4,C - fire_B,N,P,R,A,4,I - fire_B,N,P,R,A,4,L
        - fire_B,N,P,R,A,4,N  = 0
 c4499: fire_B,N,P,R,A,4,L  = 0
 c4500: fire_B,N,P,R,A,4,N  = 0
 c4501: fire_B,N,P,R,A,5,P  = 0
 c4502: fire_B,N,P,R,A,5,D  = 0
 c4503: fire_B,N,P,R,A,5,W  = 0
 c4504: fire_B,N,P,R,A,5,C  = 0
 c4505: fire_B,N,P,R,A,5,I  = 0
 c4506: - fire_B,N,P,R,A,5,P - fire_B,N,P,R,A,5,D - fire_B,N,P,R,A,5,W
        - fire_B,N,P,R,A,5,C - fire_B,N,P,R,A,5,I - fire_B,N,P,R,A,5,L
        - fire_B,N,P,R,A,5,N  = 0
 c4507: fire_B,N,P,R,A,5,L  = 0
 c4508: fire_B,N,P,R,A,5,N  = 0
 c4509: fire_B,N,P,R,L,1,P  = 0
 c4510: fire_B,N,P,R,L,1,D  = 0
 c4511: fire_B,N,P,R,L,1,W  = 0
 c4512: fire_B,N,P,R,L,1,C  = 0
 c4513: fire_B,N,P,R,L,1,I  = 0
 c4514: fire_B,N,P,R,L,1,A  = 0
 c4515: - fire_B,N,P,R,L,1,P - fire_B,N,P,R,L,1,D - fire_B,N,P,R,L,1,W
        - fire_B,N,P,R,L,1,C - fire_B,N,P,R,L,1,I - fire_B,N,P,R,L,1,A
        - fire_B,N,P,R,L,1,N  = 0
 c4516: fire_B,N,P,R,L,1,N  = 0
 c4517: fire_B,N,P,R,L,2,P  = 0
 c4518: fire_B,N,P,R,L,2,D  = 0
 c4519: fire_B,N,P,R,L,2,W  = 0
 c4520: fire_B,N,P,R,L,2,C  = 0
 c4521: fire_B,N,P,R,L,2,I  = 0
 c4522: fire_B,N,P,R,L,2,A  = 0
 c4523: - fire_B,N,P,R,L,2,P - fire_B,N,P,R,L,2,D - fire_B,N,P,R,L,2,W
        - fire_B,N,P,R,L,2,C - fire_B,N,P,R,L,2,I - fire_B,N,P,R,L,2,A
        - fire_B,N,P,R,L,2,N  = 0
 c4524: fire_B,N,P,R,L,2,N  = 0
 c4525: fire_B,N,P,R,L,3,P  = 0
 c4526: fire_B,N,P,R,L,3,D  = 0
 c4527: fire_B,N,P,R,L,3,W  = 0
 c4528: fire_B,N,P,R,L,3,C  = 0
 c4529: fire_B,N,P,R,L,3,I  = 0
 c4530: fire_B,N,P,R,L,3,A  = 0
 c4531: - fire_B,N,P,R,L,3,P - fire_B,N,P,R,L,3,D - fire_B,N,P,R,L,3,W
        - fire_B,N,P,R,L,3,C - fire_B,N,P,R,L,3,I - fire_B,N,P,R,L,3,A
        - fire_B,N,P,R,L,3,N  = 0
 c4532: fire_B,N,P,R,L,3,N  = 0
 c4533: fire_B,N,P,R,L,4,P  = 0
 c4534: fire_B,N,P,R,L,4,D  = 0
 c4535: fire_B,N,P,R,L,4,W  = 0
 c4536: fire_B,N,P,R,L,4,C  = 0
 c4537: fire_B,N,P,R,L,4,I  = 0
 c4538: fire_B,N,P,R,L,4,A  = 0
 c4539: - fire_B,N,P,R,L,4,P - fire_B,N,P,R,L,4,D - fire_B,N,P,R,L,4,W
        - fire_B,N,P,R,L,4,C - fire_B,N,P,R,L,4,I - fire_B,N,P,R,L,4,A
        - fire_B,N,P,R,L,4,N  = 0
 c4540: fire_B,N,P,R,L,4,N  = 0
 c4541: fire_B,N,P,R,L,5,P  = 0
 c4542: fire_B,N,P,R,L,5,D  = 0
 c4543: fire_B,N,P,R,L,5,W  = 0
 c4544: fire_B,N,P,R,L,5,C  = 0
 c4545: fire_B,N,P,R,L,5,I  = 0
 c4546: fire_B,N,P,R,L,5,A  = 0
 c4547: - fire_B,N,P,R,L,5,P - fire_B,N,P,R,L,5,D - fire_B,N,P,R,L,5,W
        - fire_B,N,P,R,L,5,C - fire_B,N,P,R,L,5,I - fire_B,N,P,R,L,5,A
        - fire_B,N,P,R,L,5,N  = 0
 c4548: fire_B,N,P,R,L,5,N  = 0
 c4549: fire_B,N,P,R,N,1,P  = 0
 c4550: fire_B,N,P,R,N,1,D  = 0
 c4551: fire_B,N,P,R,N,1,W  = 0
 c4552: fire_B,N,P,R,N,1,C  = 0
 c4553: fire_B,N,P,R,N,1,I  = 0
 c4554: fire_B,N,P,R,N,1,A  = 0
 c4555: fire_B,N,P,R,N,1,L  = 0
 c4556: - fire_B,N,P,R,N,1,P - fire_B,N,P,R,N,1,D - fire_B,N,P,R,N,1,W
        - fire_B,N,P,R,N,1,C - fire_B,N,P,R,N,1,I - fire_B,N,P,R,N,1,A
        - fire_B,N,P,R,N,1,L  = 0
 c4557: fire_B,N,P,R,N,2,P  = 0
 c4558: fire_B,N,P,R,N,2,D  = 0
 c4559: fire_B,N,P,R,N,2,W  = 0
 c4560: fire_B,N,P,R,N,2,C  = 0
 c4561: fire_B,N,P,R,N,2,I  = 0
 c4562: fire_B,N,P,R,N,2,A  = 0
 c4563: fire_B,N,P,R,N,2,L  = 0
 c4564: - fire_B,N,P,R,N,2,P - fire_B,N,P,R,N,2,D - fire_B,N,P,R,N,2,W
        - fire_B,N,P,R,N,2,C - fire_B,N,P,R,N,2,I - fire_B,N,P,R,N,2,A
        - fire_B,N,P,R,N,2,L  = 0
 c4565: fire_B,N,P,R,N,3,P  = 0
 c4566: fire_B,N,P,R,N,3,D  = 0
 c4567: fire_B,N,P,R,N,3,W  = 0
 c4568: fire_B,N,P,R,N,3,C  = 0
 c4569: fire_B,N,P,R,N,3,I  = 0
 c4570: fire_B,N,P,R,N,3,A  = 0
 c4571: fire_B,N,P,R,N,3,L  = 0
 c4572: - fire_B,N,P,R,N,3,P - fire_B,N,P,R,N,3,D - fire_B,N,P,R,N,3,W
        - fire_B,N,P,R,N,3,C - fire_B,N,P,R,N,3,I - fire_B,N,P,R,N,3,A
        - fire_B,N,P,R,N,3,L  = 0
 c4573: fire_B,N,P,R,N,4,P  = 0
 c4574: fire_B,N,P,R,N,4,D  = 0
 c4575: fire_B,N,P,R,N,4,W  = 0
 c4576: fire_B,N,P,R,N,4,C  = 0
 c4577: fire_B,N,P,R,N,4,I  = 0
 c4578: fire_B,N,P,R,N,4,A  = 0
 c4579: fire_B,N,P,R,N,4,L  = 0
 c4580: - fire_B,N,P,R,N,4,P - fire_B,N,P,R,N,4,D - fire_B,N,P,R,N,4,W
        - fire_B,N,P,R,N,4,C - fire_B,N,P,R,N,4,I - fire_B,N,P,R,N,4,A
        - fire_B,N,P,R,N,4,L  = 0
 c4581: fire_B,N,P,R,N,5,P  = 0
 c4582: fire_B,N,P,R,N,5,D  = 0
 c4583: fire_B,N,P,R,N,5,W  = 0
 c4584: fire_B,N,P,R,N,5,C  = 0
 c4585: fire_B,N,P,R,N,5,I  = 0
 c4586: fire_B,N,P,R,N,5,A  = 0
 c4587: fire_B,N,P,R,N,5,L  = 0
 c4588: - fire_B,N,P,R,N,5,P - fire_B,N,P,R,N,5,D - fire_B,N,P,R,N,5,W
        - fire_B,N,P,R,N,5,C - fire_B,N,P,R,N,5,I - fire_B,N,P,R,N,5,A
        - fire_B,N,P,R,N,5,L  = 0
 c4589: 0.9 fire_C,N,N,L,P,1,P - 0.1 fire_C,N,N,L,P,1,D
        - 0.1 fire_C,N,N,L,P,1,W - 0.1 fire_C,N,N,L,P,1,C
        - 0.1 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        - 0.1 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4590: - 0.1 fire_C,N,N,L,P,1,P + 0.9 fire_C,N,N,L,P,1,D
        - 0.1 fire_C,N,N,L,P,1,W - 0.1 fire_C,N,N,L,P,1,C
        - 0.1 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        - 0.1 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4591: - 0.1 fire_C,N,N,L,P,1,P - 0.1 fire_C,N,N,L,P,1,D
        + 0.9 fire_C,N,N,L,P,1,W - 0.1 fire_C,N,N,L,P,1,C
        - 0.1 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        - 0.1 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4592: - 0.1 fire_C,N,N,L,P,1,P - 0.1 fire_C,N,N,L,P,1,D
        - 0.1 fire_C,N,N,L,P,1,W + 0.9 fire_C,N,N,L,P,1,C
        - 0.1 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        - 0.1 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4593: - 0.1 fire_C,N,N,L,P,1,P - 0.1 fire_C,N,N,L,P,1,D
        - 0.1 fire_C,N,N,L,P,1,W - 0.1 fire_C,N,N,L,P,1,C
        + 0.9 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        - 0.1 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4594: - 0.2 fire_C,N,N,L,P,1,P - 0.2 fire_C,N,N,L,P,1,D
        - 0.2 fire_C,N,N,L,P,1,W - 0.2 fire_C,N,N,L,P,1,C
        - 0.2 fire_C,N,N,L,P,1,I + 0.8 fire_C,N,N,L,P,1,A
        - 0.2 fire_C,N,N,L,P,1,L - 0.2 fire_C,N,N,L,P,1,N  = 0
 c4595: - 0.1 fire_C,N,N,L,P,1,P - 0.1 fire_C,N,N,L,P,1,D
        - 0.1 fire_C,N,N,L,P,1,W - 0.1 fire_C,N,N,L,P,1,C
        - 0.1 fire_C,N,N,L,P,1,I - 0.1 fire_C,N,N,L,P,1,A
        + 0.9 fire_C,N,N,L,P,1,L - 0.1 fire_C,N,N,L,P,1,N  = 0
 c4596: - 0.2 fire_C,N,N,L,P,1,P - 0.2 fire_C,N,N,L,P,1,D
        - 0.2 fire_C,N,N,L,P,1,W - 0.2 fire_C,N,N,L,P,1,C
        - 0.2 fire_C,N,N,L,P,1,I - 0.2 fire_C,N,N,L,P,1,A
        - 0.2 fire_C,N,N,L,P,1,L + 0.8 fire_C,N,N,L,P,1,N  = 0
 c4597: 0.9 fire_C,N,N,L,P,2,P - 0.1 fire_C,N,N,L,P,2,D
        - 0.1 fire_C,N,N,L,P,2,W - 0.1 fire_C,N,N,L,P,2,C
        - 0.1 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        - 0.1 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4598: - 0.1 fire_C,N,N,L,P,2,P + 0.9 fire_C,N,N,L,P,2,D
        - 0.1 fire_C,N,N,L,P,2,W - 0.1 fire_C,N,N,L,P,2,C
        - 0.1 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        - 0.1 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4599: - 0.1 fire_C,N,N,L,P,2,P - 0.1 fire_C,N,N,L,P,2,D
        + 0.9 fire_C,N,N,L,P,2,W - 0.1 fire_C,N,N,L,P,2,C
        - 0.1 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        - 0.1 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4600: - 0.1 fire_C,N,N,L,P,2,P - 0.1 fire_C,N,N,L,P,2,D
        - 0.1 fire_C,N,N,L,P,2,W + 0.9 fire_C,N,N,L,P,2,C
        - 0.1 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        - 0.1 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4601: - 0.1 fire_C,N,N,L,P,2,P - 0.1 fire_C,N,N,L,P,2,D
        - 0.1 fire_C,N,N,L,P,2,W - 0.1 fire_C,N,N,L,P,2,C
        + 0.9 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        - 0.1 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4602: - 0.2 fire_C,N,N,L,P,2,P - 0.2 fire_C,N,N,L,P,2,D
        - 0.2 fire_C,N,N,L,P,2,W - 0.2 fire_C,N,N,L,P,2,C
        - 0.2 fire_C,N,N,L,P,2,I + 0.8 fire_C,N,N,L,P,2,A
        - 0.2 fire_C,N,N,L,P,2,L - 0.2 fire_C,N,N,L,P,2,N  = 0
 c4603: - 0.1 fire_C,N,N,L,P,2,P - 0.1 fire_C,N,N,L,P,2,D
        - 0.1 fire_C,N,N,L,P,2,W - 0.1 fire_C,N,N,L,P,2,C
        - 0.1 fire_C,N,N,L,P,2,I - 0.1 fire_C,N,N,L,P,2,A
        + 0.9 fire_C,N,N,L,P,2,L - 0.1 fire_C,N,N,L,P,2,N  = 0
 c4604: - 0.2 fire_C,N,N,L,P,2,P - 0.2 fire_C,N,N,L,P,2,D
        - 0.2 fire_C,N,N,L,P,2,W - 0.2 fire_C,N,N,L,P,2,C
        - 0.2 fire_C,N,N,L,P,2,I - 0.2 fire_C,N,N,L,P,2,A
        - 0.2 fire_C,N,N,L,P,2,L + 0.8 fire_C,N,N,L,P,2,N  = 0
 c4605: 0.9 fire_C,N,N,L,P,3,P - 0.1 fire_C,N,N,L,P,3,D
        - 0.1 fire_C,N,N,L,P,3,W - 0.1 fire_C,N,N,L,P,3,C
        - 0.1 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        - 0.1 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4606: - 0.1 fire_C,N,N,L,P,3,P + 0.9 fire_C,N,N,L,P,3,D
        - 0.1 fire_C,N,N,L,P,3,W - 0.1 fire_C,N,N,L,P,3,C
        - 0.1 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        - 0.1 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4607: - 0.1 fire_C,N,N,L,P,3,P - 0.1 fire_C,N,N,L,P,3,D
        + 0.9 fire_C,N,N,L,P,3,W - 0.1 fire_C,N,N,L,P,3,C
        - 0.1 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        - 0.1 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4608: - 0.1 fire_C,N,N,L,P,3,P - 0.1 fire_C,N,N,L,P,3,D
        - 0.1 fire_C,N,N,L,P,3,W + 0.9 fire_C,N,N,L,P,3,C
        - 0.1 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        - 0.1 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4609: - 0.1 fire_C,N,N,L,P,3,P - 0.1 fire_C,N,N,L,P,3,D
        - 0.1 fire_C,N,N,L,P,3,W - 0.1 fire_C,N,N,L,P,3,C
        + 0.9 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        - 0.1 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4610: - 0.2 fire_C,N,N,L,P,3,P - 0.2 fire_C,N,N,L,P,3,D
        - 0.2 fire_C,N,N,L,P,3,W - 0.2 fire_C,N,N,L,P,3,C
        - 0.2 fire_C,N,N,L,P,3,I + 0.8 fire_C,N,N,L,P,3,A
        - 0.2 fire_C,N,N,L,P,3,L - 0.2 fire_C,N,N,L,P,3,N  = 0
 c4611: - 0.1 fire_C,N,N,L,P,3,P - 0.1 fire_C,N,N,L,P,3,D
        - 0.1 fire_C,N,N,L,P,3,W - 0.1 fire_C,N,N,L,P,3,C
        - 0.1 fire_C,N,N,L,P,3,I - 0.1 fire_C,N,N,L,P,3,A
        + 0.9 fire_C,N,N,L,P,3,L - 0.1 fire_C,N,N,L,P,3,N  = 0
 c4612: - 0.2 fire_C,N,N,L,P,3,P - 0.2 fire_C,N,N,L,P,3,D
        - 0.2 fire_C,N,N,L,P,3,W - 0.2 fire_C,N,N,L,P,3,C
        - 0.2 fire_C,N,N,L,P,3,I - 0.2 fire_C,N,N,L,P,3,A
        - 0.2 fire_C,N,N,L,P,3,L + 0.8 fire_C,N,N,L,P,3,N  = 0
 c4613: 0.9 fire_C,N,N,L,P,4,P - 0.1 fire_C,N,N,L,P,4,D
        - 0.1 fire_C,N,N,L,P,4,W - 0.1 fire_C,N,N,L,P,4,C
        - 0.1 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        - 0.1 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4614: - 0.1 fire_C,N,N,L,P,4,P + 0.9 fire_C,N,N,L,P,4,D
        - 0.1 fire_C,N,N,L,P,4,W - 0.1 fire_C,N,N,L,P,4,C
        - 0.1 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        - 0.1 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4615: - 0.1 fire_C,N,N,L,P,4,P - 0.1 fire_C,N,N,L,P,4,D
        + 0.9 fire_C,N,N,L,P,4,W - 0.1 fire_C,N,N,L,P,4,C
        - 0.1 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        - 0.1 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4616: - 0.1 fire_C,N,N,L,P,4,P - 0.1 fire_C,N,N,L,P,4,D
        - 0.1 fire_C,N,N,L,P,4,W + 0.9 fire_C,N,N,L,P,4,C
        - 0.1 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        - 0.1 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4617: - 0.1 fire_C,N,N,L,P,4,P - 0.1 fire_C,N,N,L,P,4,D
        - 0.1 fire_C,N,N,L,P,4,W - 0.1 fire_C,N,N,L,P,4,C
        + 0.9 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        - 0.1 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4618: - 0.2 fire_C,N,N,L,P,4,P - 0.2 fire_C,N,N,L,P,4,D
        - 0.2 fire_C,N,N,L,P,4,W - 0.2 fire_C,N,N,L,P,4,C
        - 0.2 fire_C,N,N,L,P,4,I + 0.8 fire_C,N,N,L,P,4,A
        - 0.2 fire_C,N,N,L,P,4,L - 0.2 fire_C,N,N,L,P,4,N  = 0
 c4619: - 0.1 fire_C,N,N,L,P,4,P - 0.1 fire_C,N,N,L,P,4,D
        - 0.1 fire_C,N,N,L,P,4,W - 0.1 fire_C,N,N,L,P,4,C
        - 0.1 fire_C,N,N,L,P,4,I - 0.1 fire_C,N,N,L,P,4,A
        + 0.9 fire_C,N,N,L,P,4,L - 0.1 fire_C,N,N,L,P,4,N  = 0
 c4620: - 0.2 fire_C,N,N,L,P,4,P - 0.2 fire_C,N,N,L,P,4,D
        - 0.2 fire_C,N,N,L,P,4,W - 0.2 fire_C,N,N,L,P,4,C
        - 0.2 fire_C,N,N,L,P,4,I - 0.2 fire_C,N,N,L,P,4,A
        - 0.2 fire_C,N,N,L,P,4,L + 0.8 fire_C,N,N,L,P,4,N  = 0
 c4621: 0.9 fire_C,N,N,L,P,5,P - 0.1 fire_C,N,N,L,P,5,D
        - 0.1 fire_C,N,N,L,P,5,W - 0.1 fire_C,N,N,L,P,5,C
        - 0.1 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        - 0.1 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4622: - 0.1 fire_C,N,N,L,P,5,P + 0.9 fire_C,N,N,L,P,5,D
        - 0.1 fire_C,N,N,L,P,5,W - 0.1 fire_C,N,N,L,P,5,C
        - 0.1 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        - 0.1 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4623: - 0.1 fire_C,N,N,L,P,5,P - 0.1 fire_C,N,N,L,P,5,D
        + 0.9 fire_C,N,N,L,P,5,W - 0.1 fire_C,N,N,L,P,5,C
        - 0.1 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        - 0.1 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4624: - 0.1 fire_C,N,N,L,P,5,P - 0.1 fire_C,N,N,L,P,5,D
        - 0.1 fire_C,N,N,L,P,5,W + 0.9 fire_C,N,N,L,P,5,C
        - 0.1 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        - 0.1 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4625: - 0.1 fire_C,N,N,L,P,5,P - 0.1 fire_C,N,N,L,P,5,D
        - 0.1 fire_C,N,N,L,P,5,W - 0.1 fire_C,N,N,L,P,5,C
        + 0.9 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        - 0.1 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4626: - 0.2 fire_C,N,N,L,P,5,P - 0.2 fire_C,N,N,L,P,5,D
        - 0.2 fire_C,N,N,L,P,5,W - 0.2 fire_C,N,N,L,P,5,C
        - 0.2 fire_C,N,N,L,P,5,I + 0.8 fire_C,N,N,L,P,5,A
        - 0.2 fire_C,N,N,L,P,5,L - 0.2 fire_C,N,N,L,P,5,N  = 0
 c4627: - 0.1 fire_C,N,N,L,P,5,P - 0.1 fire_C,N,N,L,P,5,D
        - 0.1 fire_C,N,N,L,P,5,W - 0.1 fire_C,N,N,L,P,5,C
        - 0.1 fire_C,N,N,L,P,5,I - 0.1 fire_C,N,N,L,P,5,A
        + 0.9 fire_C,N,N,L,P,5,L - 0.1 fire_C,N,N,L,P,5,N  = 0
 c4628: - 0.2 fire_C,N,N,L,P,5,P - 0.2 fire_C,N,N,L,P,5,D
        - 0.2 fire_C,N,N,L,P,5,W - 0.2 fire_C,N,N,L,P,5,C
        - 0.2 fire_C,N,N,L,P,5,I - 0.2 fire_C,N,N,L,P,5,A
        - 0.2 fire_C,N,N,L,P,5,L + 0.8 fire_C,N,N,L,P,5,N  = 0
 c4629: 0.5 fire_C,N,N,L,D,1,P - 0.5 fire_C,N,N,L,D,1,D
        - 0.5 fire_C,N,N,L,D,1,W - 0.5 fire_C,N,N,L,D,1,C
        - 0.5 fire_C,N,N,L,D,1,I - 0.5 fire_C,N,N,L,D,1,A
        - 0.5 fire_C,N,N,L,D,1,L - 0.5 fire_C,N,N,L,D,1,N  = 0
 c4630: - 0.5 fire_C,N,N,L,D,1,P + 0.5 fire_C,N,N,L,D,1,D
        - 0.5 fire_C,N,N,L,D,1,W - 0.5 fire_C,N,N,L,D,1,C
        - 0.5 fire_C,N,N,L,D,1,I - 0.5 fire_C,N,N,L,D,1,A
        - 0.5 fire_C,N,N,L,D,1,L - 0.5 fire_C,N,N,L,D,1,N  = 0
 c4631: fire_C,N,N,L,D,1,W  = 0
 c4632: fire_C,N,N,L,D,1,C  = 0
 c4633: fire_C,N,N,L,D,1,I  = 0
 c4634: fire_C,N,N,L,D,1,A  = 0
 c4635: fire_C,N,N,L,D,1,L  = 0
 c4636: fire_C,N,N,L,D,1,N  = 0
 c4637: 0.5 fire_C,N,N,L,D,2,P - 0.5 fire_C,N,N,L,D,2,D
        - 0.5 fire_C,N,N,L,D,2,W - 0.5 fire_C,N,N,L,D,2,C
        - 0.5 fire_C,N,N,L,D,2,I - 0.5 fire_C,N,N,L,D,2,A
        - 0.5 fire_C,N,N,L,D,2,L - 0.5 fire_C,N,N,L,D,2,N  = 0
 c4638: - 0.5 fire_C,N,N,L,D,2,P + 0.5 fire_C,N,N,L,D,2,D
        - 0.5 fire_C,N,N,L,D,2,W - 0.5 fire_C,N,N,L,D,2,C
        - 0.5 fire_C,N,N,L,D,2,I - 0.5 fire_C,N,N,L,D,2,A
        - 0.5 fire_C,N,N,L,D,2,L - 0.5 fire_C,N,N,L,D,2,N  = 0
 c4639: fire_C,N,N,L,D,2,W  = 0
 c4640: fire_C,N,N,L,D,2,C  = 0
 c4641: fire_C,N,N,L,D,2,I  = 0
 c4642: fire_C,N,N,L,D,2,A  = 0
 c4643: fire_C,N,N,L,D,2,L  = 0
 c4644: fire_C,N,N,L,D,2,N  = 0
 c4645: 0.5 fire_C,N,N,L,D,3,P - 0.5 fire_C,N,N,L,D,3,D
        - 0.5 fire_C,N,N,L,D,3,W - 0.5 fire_C,N,N,L,D,3,C
        - 0.5 fire_C,N,N,L,D,3,I - 0.5 fire_C,N,N,L,D,3,A
        - 0.5 fire_C,N,N,L,D,3,L - 0.5 fire_C,N,N,L,D,3,N  = 0
 c4646: - 0.5 fire_C,N,N,L,D,3,P + 0.5 fire_C,N,N,L,D,3,D
        - 0.5 fire_C,N,N,L,D,3,W - 0.5 fire_C,N,N,L,D,3,C
        - 0.5 fire_C,N,N,L,D,3,I - 0.5 fire_C,N,N,L,D,3,A
        - 0.5 fire_C,N,N,L,D,3,L - 0.5 fire_C,N,N,L,D,3,N  = 0
 c4647: fire_C,N,N,L,D,3,W  = 0
 c4648: fire_C,N,N,L,D,3,C  = 0
 c4649: fire_C,N,N,L,D,3,I  = 0
 c4650: fire_C,N,N,L,D,3,A  = 0
 c4651: fire_C,N,N,L,D,3,L  = 0
 c4652: fire_C,N,N,L,D,3,N  = 0
 c4653: 0.5 fire_C,N,N,L,D,4,P - 0.5 fire_C,N,N,L,D,4,D
        - 0.5 fire_C,N,N,L,D,4,W - 0.5 fire_C,N,N,L,D,4,C
        - 0.5 fire_C,N,N,L,D,4,I - 0.5 fire_C,N,N,L,D,4,A
        - 0.5 fire_C,N,N,L,D,4,L - 0.5 fire_C,N,N,L,D,4,N  = 0
 c4654: - 0.5 fire_C,N,N,L,D,4,P + 0.5 fire_C,N,N,L,D,4,D
        - 0.5 fire_C,N,N,L,D,4,W - 0.5 fire_C,N,N,L,D,4,C
        - 0.5 fire_C,N,N,L,D,4,I - 0.5 fire_C,N,N,L,D,4,A
        - 0.5 fire_C,N,N,L,D,4,L - 0.5 fire_C,N,N,L,D,4,N  = 0
 c4655: fire_C,N,N,L,D,4,W  = 0
 c4656: fire_C,N,N,L,D,4,C  = 0
 c4657: fire_C,N,N,L,D,4,I  = 0
 c4658: fire_C,N,N,L,D,4,A  = 0
 c4659: fire_C,N,N,L,D,4,L  = 0
 c4660: fire_C,N,N,L,D,4,N  = 0
 c4661: 0.5 fire_C,N,N,L,D,5,P - 0.5 fire_C,N,N,L,D,5,D
        - 0.5 fire_C,N,N,L,D,5,W - 0.5 fire_C,N,N,L,D,5,C
        - 0.5 fire_C,N,N,L,D,5,I - 0.5 fire_C,N,N,L,D,5,A
        - 0.5 fire_C,N,N,L,D,5,L - 0.5 fire_C,N,N,L,D,5,N  = 0
 c4662: - 0.5 fire_C,N,N,L,D,5,P + 0.5 fire_C,N,N,L,D,5,D
        - 0.5 fire_C,N,N,L,D,5,W - 0.5 fire_C,N,N,L,D,5,C
        - 0.5 fire_C,N,N,L,D,5,I - 0.5 fire_C,N,N,L,D,5,A
        - 0.5 fire_C,N,N,L,D,5,L - 0.5 fire_C,N,N,L,D,5,N  = 0
 c4663: fire_C,N,N,L,D,5,W  = 0
 c4664: fire_C,N,N,L,D,5,C  = 0
 c4665: fire_C,N,N,L,D,5,I  = 0
 c4666: fire_C,N,N,L,D,5,A  = 0
 c4667: fire_C,N,N,L,D,5,L  = 0
 c4668: fire_C,N,N,L,D,5,N  = 0
 c4669: fire_C,N,N,L,W,1,P  = 0
 c4670: fire_C,N,N,L,W,1,D  = 0
 c4671: - fire_C,N,N,L,W,1,P - fire_C,N,N,L,W,1,D - fire_C,N,N,L,W,1,C
        - fire_C,N,N,L,W,1,I - fire_C,N,N,L,W,1,A - fire_C,N,N,L,W,1,L
        - fire_C,N,N,L,W,1,N  = 0
 c4672: fire_C,N,N,L,W,1,C  = 0
 c4673: fire_C,N,N,L,W,1,I  = 0
 c4674: fire_C,N,N,L,W,1,A  = 0
 c4675: fire_C,N,N,L,W,1,L  = 0
 c4676: fire_C,N,N,L,W,1,N  = 0
 c4677: fire_C,N,N,L,W,2,P  = 0
 c4678: fire_C,N,N,L,W,2,D  = 0
 c4679: - fire_C,N,N,L,W,2,P - fire_C,N,N,L,W,2,D - fire_C,N,N,L,W,2,C
        - fire_C,N,N,L,W,2,I - fire_C,N,N,L,W,2,A - fire_C,N,N,L,W,2,L
        - fire_C,N,N,L,W,2,N  = 0
 c4680: fire_C,N,N,L,W,2,C  = 0
 c4681: fire_C,N,N,L,W,2,I  = 0
 c4682: fire_C,N,N,L,W,2,A  = 0
 c4683: fire_C,N,N,L,W,2,L  = 0
 c4684: fire_C,N,N,L,W,2,N  = 0
 c4685: fire_C,N,N,L,W,3,P  = 0
 c4686: fire_C,N,N,L,W,3,D  = 0
 c4687: - fire_C,N,N,L,W,3,P - fire_C,N,N,L,W,3,D - fire_C,N,N,L,W,3,C
        - fire_C,N,N,L,W,3,I - fire_C,N,N,L,W,3,A - fire_C,N,N,L,W,3,L
        - fire_C,N,N,L,W,3,N  = 0
 c4688: fire_C,N,N,L,W,3,C  = 0
 c4689: fire_C,N,N,L,W,3,I  = 0
 c4690: fire_C,N,N,L,W,3,A  = 0
 c4691: fire_C,N,N,L,W,3,L  = 0
 c4692: fire_C,N,N,L,W,3,N  = 0
 c4693: fire_C,N,N,L,W,4,P  = 0
 c4694: fire_C,N,N,L,W,4,D  = 0
 c4695: - fire_C,N,N,L,W,4,P - fire_C,N,N,L,W,4,D - fire_C,N,N,L,W,4,C
        - fire_C,N,N,L,W,4,I - fire_C,N,N,L,W,4,A - fire_C,N,N,L,W,4,L
        - fire_C,N,N,L,W,4,N  = 0
 c4696: fire_C,N,N,L,W,4,C  = 0
 c4697: fire_C,N,N,L,W,4,I  = 0
 c4698: fire_C,N,N,L,W,4,A  = 0
 c4699: fire_C,N,N,L,W,4,L  = 0
 c4700: fire_C,N,N,L,W,4,N  = 0
 c4701: fire_C,N,N,L,W,5,P  = 0
 c4702: fire_C,N,N,L,W,5,D  = 0
 c4703: - fire_C,N,N,L,W,5,P - fire_C,N,N,L,W,5,D - fire_C,N,N,L,W,5,C
        - fire_C,N,N,L,W,5,I - fire_C,N,N,L,W,5,A - fire_C,N,N,L,W,5,L
        - fire_C,N,N,L,W,5,N  = 0
 c4704: fire_C,N,N,L,W,5,C  = 0
 c4705: fire_C,N,N,L,W,5,I  = 0
 c4706: fire_C,N,N,L,W,5,A  = 0
 c4707: fire_C,N,N,L,W,5,L  = 0
 c4708: fire_C,N,N,L,W,5,N  = 0
 c4709: fire_C,N,N,L,C,1,P  = 0
 c4710: fire_C,N,N,L,C,1,D  = 0
 c4711: fire_C,N,N,L,C,1,W  = 0
 c4712: - fire_C,N,N,L,C,1,P - fire_C,N,N,L,C,1,D - fire_C,N,N,L,C,1,W
        - fire_C,N,N,L,C,1,I - fire_C,N,N,L,C,1,A - fire_C,N,N,L,C,1,L
        - fire_C,N,N,L,C,1,N  = 0
 c4713: fire_C,N,N,L,C,1,I  = 0
 c4714: fire_C,N,N,L,C,1,A  = 0
 c4715: fire_C,N,N,L,C,1,L  = 0
 c4716: fire_C,N,N,L,C,1,N  = 0
 c4717: fire_C,N,N,L,C,2,P  = 0
 c4718: fire_C,N,N,L,C,2,D  = 0
 c4719: fire_C,N,N,L,C,2,W  = 0
 c4720: - fire_C,N,N,L,C,2,P - fire_C,N,N,L,C,2,D - fire_C,N,N,L,C,2,W
        - fire_C,N,N,L,C,2,I - fire_C,N,N,L,C,2,A - fire_C,N,N,L,C,2,L
        - fire_C,N,N,L,C,2,N  = 0
 c4721: fire_C,N,N,L,C,2,I  = 0
 c4722: fire_C,N,N,L,C,2,A  = 0
 c4723: fire_C,N,N,L,C,2,L  = 0
 c4724: fire_C,N,N,L,C,2,N  = 0
 c4725: fire_C,N,N,L,C,3,P  = 0
 c4726: fire_C,N,N,L,C,3,D  = 0
 c4727: fire_C,N,N,L,C,3,W  = 0
 c4728: - fire_C,N,N,L,C,3,P - fire_C,N,N,L,C,3,D - fire_C,N,N,L,C,3,W
        - fire_C,N,N,L,C,3,I - fire_C,N,N,L,C,3,A - fire_C,N,N,L,C,3,L
        - fire_C,N,N,L,C,3,N  = 0
 c4729: fire_C,N,N,L,C,3,I  = 0
 c4730: fire_C,N,N,L,C,3,A  = 0
 c4731: fire_C,N,N,L,C,3,L  = 0
 c4732: fire_C,N,N,L,C,3,N  = 0
 c4733: fire_C,N,N,L,C,4,P  = 0
 c4734: fire_C,N,N,L,C,4,D  = 0
 c4735: fire_C,N,N,L,C,4,W  = 0
 c4736: - fire_C,N,N,L,C,4,P - fire_C,N,N,L,C,4,D - fire_C,N,N,L,C,4,W
        - fire_C,N,N,L,C,4,I - fire_C,N,N,L,C,4,A - fire_C,N,N,L,C,4,L
        - fire_C,N,N,L,C,4,N  = 0
 c4737: fire_C,N,N,L,C,4,I  = 0
 c4738: fire_C,N,N,L,C,4,A  = 0
 c4739: fire_C,N,N,L,C,4,L  = 0
 c4740: fire_C,N,N,L,C,4,N  = 0
 c4741: fire_C,N,N,L,C,5,P  = 0
 c4742: fire_C,N,N,L,C,5,D  = 0
 c4743: fire_C,N,N,L,C,5,W  = 0
 c4744: - fire_C,N,N,L,C,5,P - fire_C,N,N,L,C,5,D - fire_C,N,N,L,C,5,W
        - fire_C,N,N,L,C,5,I - fire_C,N,N,L,C,5,A - fire_C,N,N,L,C,5,L
        - fire_C,N,N,L,C,5,N  = 0
 c4745: fire_C,N,N,L,C,5,I  = 0
 c4746: fire_C,N,N,L,C,5,A  = 0
 c4747: fire_C,N,N,L,C,5,L  = 0
 c4748: fire_C,N,N,L,C,5,N  = 0
 c4749: fire_C,N,N,L,I,1,P  = 0
 c4750: fire_C,N,N,L,I,1,D  = 0
 c4751: fire_C,N,N,L,I,1,W  = 0
 c4752: fire_C,N,N,L,I,1,C  = 0
 c4753: - fire_C,N,N,L,I,1,P - fire_C,N,N,L,I,1,D - fire_C,N,N,L,I,1,W
        - fire_C,N,N,L,I,1,C - fire_C,N,N,L,I,1,A - fire_C,N,N,L,I,1,L
        - fire_C,N,N,L,I,1,N  = 0
 c4754: fire_C,N,N,L,I,1,A  = 0
 c4755: fire_C,N,N,L,I,1,L  = 0
 c4756: fire_C,N,N,L,I,1,N  = 0
 c4757: fire_C,N,N,L,I,2,P  = 0
 c4758: fire_C,N,N,L,I,2,D  = 0
 c4759: fire_C,N,N,L,I,2,W  = 0
 c4760: fire_C,N,N,L,I,2,C  = 0
 c4761: - fire_C,N,N,L,I,2,P - fire_C,N,N,L,I,2,D - fire_C,N,N,L,I,2,W
        - fire_C,N,N,L,I,2,C - fire_C,N,N,L,I,2,A - fire_C,N,N,L,I,2,L
        - fire_C,N,N,L,I,2,N  = 0
 c4762: fire_C,N,N,L,I,2,A  = 0
 c4763: fire_C,N,N,L,I,2,L  = 0
 c4764: fire_C,N,N,L,I,2,N  = 0
 c4765: fire_C,N,N,L,I,3,P  = 0
 c4766: fire_C,N,N,L,I,3,D  = 0
 c4767: fire_C,N,N,L,I,3,W  = 0
 c4768: fire_C,N,N,L,I,3,C  = 0
 c4769: - fire_C,N,N,L,I,3,P - fire_C,N,N,L,I,3,D - fire_C,N,N,L,I,3,W
        - fire_C,N,N,L,I,3,C - fire_C,N,N,L,I,3,A - fire_C,N,N,L,I,3,L
        - fire_C,N,N,L,I,3,N  = 0
 c4770: fire_C,N,N,L,I,3,A  = 0
 c4771: fire_C,N,N,L,I,3,L  = 0
 c4772: fire_C,N,N,L,I,3,N  = 0
 c4773: fire_C,N,N,L,I,4,P  = 0
 c4774: fire_C,N,N,L,I,4,D  = 0
 c4775: fire_C,N,N,L,I,4,W  = 0
 c4776: fire_C,N,N,L,I,4,C  = 0
 c4777: - fire_C,N,N,L,I,4,P - fire_C,N,N,L,I,4,D - fire_C,N,N,L,I,4,W
        - fire_C,N,N,L,I,4,C - fire_C,N,N,L,I,4,A - fire_C,N,N,L,I,4,L
        - fire_C,N,N,L,I,4,N  = 0
 c4778: fire_C,N,N,L,I,4,A  = 0
 c4779: fire_C,N,N,L,I,4,L  = 0
 c4780: fire_C,N,N,L,I,4,N  = 0
 c4781: fire_C,N,N,L,I,5,P  = 0
 c4782: fire_C,N,N,L,I,5,D  = 0
 c4783: fire_C,N,N,L,I,5,W  = 0
 c4784: fire_C,N,N,L,I,5,C  = 0
 c4785: - fire_C,N,N,L,I,5,P - fire_C,N,N,L,I,5,D - fire_C,N,N,L,I,5,W
        - fire_C,N,N,L,I,5,C - fire_C,N,N,L,I,5,A - fire_C,N,N,L,I,5,L
        - fire_C,N,N,L,I,5,N  = 0
 c4786: fire_C,N,N,L,I,5,A  = 0
 c4787: fire_C,N,N,L,I,5,L  = 0
 c4788: fire_C,N,N,L,I,5,N  = 0
 c4789: fire_C,N,N,L,A,1,P  = 0
 c4790: fire_C,N,N,L,A,1,D  = 0
 c4791: fire_C,N,N,L,A,1,W  = 0
 c4792: fire_C,N,N,L,A,1,C  = 0
 c4793: fire_C,N,N,L,A,1,I  = 0
 c4794: - fire_C,N,N,L,A,1,P - fire_C,N,N,L,A,1,D - fire_C,N,N,L,A,1,W
        - fire_C,N,N,L,A,1,C - fire_C,N,N,L,A,1,I - fire_C,N,N,L,A,1,L
        - fire_C,N,N,L,A,1,N  = 0
 c4795: fire_C,N,N,L,A,1,L  = 0
 c4796: fire_C,N,N,L,A,1,N  = 0
 c4797: fire_C,N,N,L,A,2,P  = 0
 c4798: fire_C,N,N,L,A,2,D  = 0
 c4799: fire_C,N,N,L,A,2,W  = 0
 c4800: fire_C,N,N,L,A,2,C  = 0
 c4801: fire_C,N,N,L,A,2,I  = 0
 c4802: - fire_C,N,N,L,A,2,P - fire_C,N,N,L,A,2,D - fire_C,N,N,L,A,2,W
        - fire_C,N,N,L,A,2,C - fire_C,N,N,L,A,2,I - fire_C,N,N,L,A,2,L
        - fire_C,N,N,L,A,2,N  = 0
 c4803: fire_C,N,N,L,A,2,L  = 0
 c4804: fire_C,N,N,L,A,2,N  = 0
 c4805: fire_C,N,N,L,A,3,P  = 0
 c4806: fire_C,N,N,L,A,3,D  = 0
 c4807: fire_C,N,N,L,A,3,W  = 0
 c4808: fire_C,N,N,L,A,3,C  = 0
 c4809: fire_C,N,N,L,A,3,I  = 0
 c4810: - fire_C,N,N,L,A,3,P - fire_C,N,N,L,A,3,D - fire_C,N,N,L,A,3,W
        - fire_C,N,N,L,A,3,C - fire_C,N,N,L,A,3,I - fire_C,N,N,L,A,3,L
        - fire_C,N,N,L,A,3,N  = 0
 c4811: fire_C,N,N,L,A,3,L  = 0
 c4812: fire_C,N,N,L,A,3,N  = 0
 c4813: fire_C,N,N,L,A,4,P  = 0
 c4814: fire_C,N,N,L,A,4,D  = 0
 c4815: fire_C,N,N,L,A,4,W  = 0
 c4816: fire_C,N,N,L,A,4,C  = 0
 c4817: fire_C,N,N,L,A,4,I  = 0
 c4818: - fire_C,N,N,L,A,4,P - fire_C,N,N,L,A,4,D - fire_C,N,N,L,A,4,W
        - fire_C,N,N,L,A,4,C - fire_C,N,N,L,A,4,I - fire_C,N,N,L,A,4,L
        - fire_C,N,N,L,A,4,N  = 0
 c4819: fire_C,N,N,L,A,4,L  = 0
 c4820: fire_C,N,N,L,A,4,N  = 0
 c4821: fire_C,N,N,L,A,5,P  = 0
 c4822: fire_C,N,N,L,A,5,D  = 0
 c4823: fire_C,N,N,L,A,5,W  = 0
 c4824: fire_C,N,N,L,A,5,C  = 0
 c4825: fire_C,N,N,L,A,5,I  = 0
 c4826: - fire_C,N,N,L,A,5,P - fire_C,N,N,L,A,5,D - fire_C,N,N,L,A,5,W
        - fire_C,N,N,L,A,5,C - fire_C,N,N,L,A,5,I - fire_C,N,N,L,A,5,L
        - fire_C,N,N,L,A,5,N  = 0
 c4827: fire_C,N,N,L,A,5,L  = 0
 c4828: fire_C,N,N,L,A,5,N  = 0
 c4829: fire_C,N,N,L,L,1,P  = 0
 c4830: fire_C,N,N,L,L,1,D  = 0
 c4831: fire_C,N,N,L,L,1,W  = 0
 c4832: fire_C,N,N,L,L,1,C  = 0
 c4833: fire_C,N,N,L,L,1,I  = 0
 c4834: fire_C,N,N,L,L,1,A  = 0
 c4835: - fire_C,N,N,L,L,1,P - fire_C,N,N,L,L,1,D - fire_C,N,N,L,L,1,W
        - fire_C,N,N,L,L,1,C - fire_C,N,N,L,L,1,I - fire_C,N,N,L,L,1,A
        - fire_C,N,N,L,L,1,N  = 0
 c4836: fire_C,N,N,L,L,1,N  = 0
 c4837: fire_C,N,N,L,L,2,P  = 0
 c4838: fire_C,N,N,L,L,2,D  = 0
 c4839: fire_C,N,N,L,L,2,W  = 0
 c4840: fire_C,N,N,L,L,2,C  = 0
 c4841: fire_C,N,N,L,L,2,I  = 0
 c4842: fire_C,N,N,L,L,2,A  = 0
 c4843: - fire_C,N,N,L,L,2,P - fire_C,N,N,L,L,2,D - fire_C,N,N,L,L,2,W
        - fire_C,N,N,L,L,2,C - fire_C,N,N,L,L,2,I - fire_C,N,N,L,L,2,A
        - fire_C,N,N,L,L,2,N  = 0
 c4844: fire_C,N,N,L,L,2,N  = 0
 c4845: fire_C,N,N,L,L,3,P  = 0
 c4846: fire_C,N,N,L,L,3,D  = 0
 c4847: fire_C,N,N,L,L,3,W  = 0
 c4848: fire_C,N,N,L,L,3,C  = 0
 c4849: fire_C,N,N,L,L,3,I  = 0
 c4850: fire_C,N,N,L,L,3,A  = 0
 c4851: - fire_C,N,N,L,L,3,P - fire_C,N,N,L,L,3,D - fire_C,N,N,L,L,3,W
        - fire_C,N,N,L,L,3,C - fire_C,N,N,L,L,3,I - fire_C,N,N,L,L,3,A
        - fire_C,N,N,L,L,3,N  = 0
 c4852: fire_C,N,N,L,L,3,N  = 0
 c4853: fire_C,N,N,L,L,4,P  = 0
 c4854: fire_C,N,N,L,L,4,D  = 0
 c4855: fire_C,N,N,L,L,4,W  = 0
 c4856: fire_C,N,N,L,L,4,C  = 0
 c4857: fire_C,N,N,L,L,4,I  = 0
 c4858: fire_C,N,N,L,L,4,A  = 0
 c4859: - fire_C,N,N,L,L,4,P - fire_C,N,N,L,L,4,D - fire_C,N,N,L,L,4,W
        - fire_C,N,N,L,L,4,C - fire_C,N,N,L,L,4,I - fire_C,N,N,L,L,4,A
        - fire_C,N,N,L,L,4,N  = 0
 c4860: fire_C,N,N,L,L,4,N  = 0
 c4861: fire_C,N,N,L,L,5,P  = 0
 c4862: fire_C,N,N,L,L,5,D  = 0
 c4863: fire_C,N,N,L,L,5,W  = 0
 c4864: fire_C,N,N,L,L,5,C  = 0
 c4865: fire_C,N,N,L,L,5,I  = 0
 c4866: fire_C,N,N,L,L,5,A  = 0
 c4867: - fire_C,N,N,L,L,5,P - fire_C,N,N,L,L,5,D - fire_C,N,N,L,L,5,W
        - fire_C,N,N,L,L,5,C - fire_C,N,N,L,L,5,I - fire_C,N,N,L,L,5,A
        - fire_C,N,N,L,L,5,N  = 0
 c4868: fire_C,N,N,L,L,5,N  = 0
 c4869: fire_C,N,N,L,N,1,P  = 0
 c4870: fire_C,N,N,L,N,1,D  = 0
 c4871: fire_C,N,N,L,N,1,W  = 0
 c4872: fire_C,N,N,L,N,1,C  = 0
 c4873: fire_C,N,N,L,N,1,I  = 0
 c4874: fire_C,N,N,L,N,1,A  = 0
 c4875: fire_C,N,N,L,N,1,L  = 0
 c4876: - fire_C,N,N,L,N,1,P - fire_C,N,N,L,N,1,D - fire_C,N,N,L,N,1,W
        - fire_C,N,N,L,N,1,C - fire_C,N,N,L,N,1,I - fire_C,N,N,L,N,1,A
        - fire_C,N,N,L,N,1,L  = 0
 c4877: fire_C,N,N,L,N,2,P  = 0
 c4878: fire_C,N,N,L,N,2,D  = 0
 c4879: fire_C,N,N,L,N,2,W  = 0
 c4880: fire_C,N,N,L,N,2,C  = 0
 c4881: fire_C,N,N,L,N,2,I  = 0
 c4882: fire_C,N,N,L,N,2,A  = 0
 c4883: fire_C,N,N,L,N,2,L  = 0
 c4884: - fire_C,N,N,L,N,2,P - fire_C,N,N,L,N,2,D - fire_C,N,N,L,N,2,W
        - fire_C,N,N,L,N,2,C - fire_C,N,N,L,N,2,I - fire_C,N,N,L,N,2,A
        - fire_C,N,N,L,N,2,L  = 0
 c4885: fire_C,N,N,L,N,3,P  = 0
 c4886: fire_C,N,N,L,N,3,D  = 0
 c4887: fire_C,N,N,L,N,3,W  = 0
 c4888: fire_C,N,N,L,N,3,C  = 0
 c4889: fire_C,N,N,L,N,3,I  = 0
 c4890: fire_C,N,N,L,N,3,A  = 0
 c4891: fire_C,N,N,L,N,3,L  = 0
 c4892: - fire_C,N,N,L,N,3,P - fire_C,N,N,L,N,3,D - fire_C,N,N,L,N,3,W
        - fire_C,N,N,L,N,3,C - fire_C,N,N,L,N,3,I - fire_C,N,N,L,N,3,A
        - fire_C,N,N,L,N,3,L  = 0
 c4893: fire_C,N,N,L,N,4,P  = 0
 c4894: fire_C,N,N,L,N,4,D  = 0
 c4895: fire_C,N,N,L,N,4,W  = 0
 c4896: fire_C,N,N,L,N,4,C  = 0
 c4897: fire_C,N,N,L,N,4,I  = 0
 c4898: fire_C,N,N,L,N,4,A  = 0
 c4899: fire_C,N,N,L,N,4,L  = 0
 c4900: - fire_C,N,N,L,N,4,P - fire_C,N,N,L,N,4,D - fire_C,N,N,L,N,4,W
        - fire_C,N,N,L,N,4,C - fire_C,N,N,L,N,4,I - fire_C,N,N,L,N,4,A
        - fire_C,N,N,L,N,4,L  = 0
 c4901: fire_C,N,N,L,N,5,P  = 0
 c4902: fire_C,N,N,L,N,5,D  = 0
 c4903: fire_C,N,N,L,N,5,W  = 0
 c4904: fire_C,N,N,L,N,5,C  = 0
 c4905: fire_C,N,N,L,N,5,I  = 0
 c4906: fire_C,N,N,L,N,5,A  = 0
 c4907: fire_C,N,N,L,N,5,L  = 0
 c4908: - fire_C,N,N,L,N,5,P - fire_C,N,N,L,N,5,D - fire_C,N,N,L,N,5,W
        - fire_C,N,N,L,N,5,C - fire_C,N,N,L,N,5,I - fire_C,N,N,L,N,5,A
        - fire_C,N,N,L,N,5,L  = 0
 c4909: 0.9 fire_C,N,N,H,P,1,P - 0.1 fire_C,N,N,H,P,1,D
        - 0.1 fire_C,N,N,H,P,1,W - 0.1 fire_C,N,N,H,P,1,C
        - 0.1 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        - 0.1 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4910: - 0.1 fire_C,N,N,H,P,1,P + 0.9 fire_C,N,N,H,P,1,D
        - 0.1 fire_C,N,N,H,P,1,W - 0.1 fire_C,N,N,H,P,1,C
        - 0.1 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        - 0.1 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4911: - 0.1 fire_C,N,N,H,P,1,P - 0.1 fire_C,N,N,H,P,1,D
        + 0.9 fire_C,N,N,H,P,1,W - 0.1 fire_C,N,N,H,P,1,C
        - 0.1 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        - 0.1 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4912: - 0.1 fire_C,N,N,H,P,1,P - 0.1 fire_C,N,N,H,P,1,D
        - 0.1 fire_C,N,N,H,P,1,W + 0.9 fire_C,N,N,H,P,1,C
        - 0.1 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        - 0.1 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4913: - 0.1 fire_C,N,N,H,P,1,P - 0.1 fire_C,N,N,H,P,1,D
        - 0.1 fire_C,N,N,H,P,1,W - 0.1 fire_C,N,N,H,P,1,C
        + 0.9 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        - 0.1 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4914: - 0.2 fire_C,N,N,H,P,1,P - 0.2 fire_C,N,N,H,P,1,D
        - 0.2 fire_C,N,N,H,P,1,W - 0.2 fire_C,N,N,H,P,1,C
        - 0.2 fire_C,N,N,H,P,1,I + 0.8 fire_C,N,N,H,P,1,A
        - 0.2 fire_C,N,N,H,P,1,L - 0.2 fire_C,N,N,H,P,1,N  = 0
 c4915: - 0.1 fire_C,N,N,H,P,1,P - 0.1 fire_C,N,N,H,P,1,D
        - 0.1 fire_C,N,N,H,P,1,W - 0.1 fire_C,N,N,H,P,1,C
        - 0.1 fire_C,N,N,H,P,1,I - 0.1 fire_C,N,N,H,P,1,A
        + 0.9 fire_C,N,N,H,P,1,L - 0.1 fire_C,N,N,H,P,1,N  = 0
 c4916: - 0.2 fire_C,N,N,H,P,1,P - 0.2 fire_C,N,N,H,P,1,D
        - 0.2 fire_C,N,N,H,P,1,W - 0.2 fire_C,N,N,H,P,1,C
        - 0.2 fire_C,N,N,H,P,1,I - 0.2 fire_C,N,N,H,P,1,A
        - 0.2 fire_C,N,N,H,P,1,L + 0.8 fire_C,N,N,H,P,1,N  = 0
 c4917: 0.9 fire_C,N,N,H,P,2,P - 0.1 fire_C,N,N,H,P,2,D
        - 0.1 fire_C,N,N,H,P,2,W - 0.1 fire_C,N,N,H,P,2,C
        - 0.1 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        - 0.1 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4918: - 0.1 fire_C,N,N,H,P,2,P + 0.9 fire_C,N,N,H,P,2,D
        - 0.1 fire_C,N,N,H,P,2,W - 0.1 fire_C,N,N,H,P,2,C
        - 0.1 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        - 0.1 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4919: - 0.1 fire_C,N,N,H,P,2,P - 0.1 fire_C,N,N,H,P,2,D
        + 0.9 fire_C,N,N,H,P,2,W - 0.1 fire_C,N,N,H,P,2,C
        - 0.1 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        - 0.1 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4920: - 0.1 fire_C,N,N,H,P,2,P - 0.1 fire_C,N,N,H,P,2,D
        - 0.1 fire_C,N,N,H,P,2,W + 0.9 fire_C,N,N,H,P,2,C
        - 0.1 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        - 0.1 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4921: - 0.1 fire_C,N,N,H,P,2,P - 0.1 fire_C,N,N,H,P,2,D
        - 0.1 fire_C,N,N,H,P,2,W - 0.1 fire_C,N,N,H,P,2,C
        + 0.9 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        - 0.1 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4922: - 0.2 fire_C,N,N,H,P,2,P - 0.2 fire_C,N,N,H,P,2,D
        - 0.2 fire_C,N,N,H,P,2,W - 0.2 fire_C,N,N,H,P,2,C
        - 0.2 fire_C,N,N,H,P,2,I + 0.8 fire_C,N,N,H,P,2,A
        - 0.2 fire_C,N,N,H,P,2,L - 0.2 fire_C,N,N,H,P,2,N  = 0
 c4923: - 0.1 fire_C,N,N,H,P,2,P - 0.1 fire_C,N,N,H,P,2,D
        - 0.1 fire_C,N,N,H,P,2,W - 0.1 fire_C,N,N,H,P,2,C
        - 0.1 fire_C,N,N,H,P,2,I - 0.1 fire_C,N,N,H,P,2,A
        + 0.9 fire_C,N,N,H,P,2,L - 0.1 fire_C,N,N,H,P,2,N  = 0
 c4924: - 0.2 fire_C,N,N,H,P,2,P - 0.2 fire_C,N,N,H,P,2,D
        - 0.2 fire_C,N,N,H,P,2,W - 0.2 fire_C,N,N,H,P,2,C
        - 0.2 fire_C,N,N,H,P,2,I - 0.2 fire_C,N,N,H,P,2,A
        - 0.2 fire_C,N,N,H,P,2,L + 0.8 fire_C,N,N,H,P,2,N  = 0
 c4925: 0.9 fire_C,N,N,H,P,3,P - 0.1 fire_C,N,N,H,P,3,D
        - 0.1 fire_C,N,N,H,P,3,W - 0.1 fire_C,N,N,H,P,3,C
        - 0.1 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        - 0.1 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4926: - 0.1 fire_C,N,N,H,P,3,P + 0.9 fire_C,N,N,H,P,3,D
        - 0.1 fire_C,N,N,H,P,3,W - 0.1 fire_C,N,N,H,P,3,C
        - 0.1 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        - 0.1 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4927: - 0.1 fire_C,N,N,H,P,3,P - 0.1 fire_C,N,N,H,P,3,D
        + 0.9 fire_C,N,N,H,P,3,W - 0.1 fire_C,N,N,H,P,3,C
        - 0.1 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        - 0.1 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4928: - 0.1 fire_C,N,N,H,P,3,P - 0.1 fire_C,N,N,H,P,3,D
        - 0.1 fire_C,N,N,H,P,3,W + 0.9 fire_C,N,N,H,P,3,C
        - 0.1 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        - 0.1 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4929: - 0.1 fire_C,N,N,H,P,3,P - 0.1 fire_C,N,N,H,P,3,D
        - 0.1 fire_C,N,N,H,P,3,W - 0.1 fire_C,N,N,H,P,3,C
        + 0.9 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        - 0.1 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4930: - 0.2 fire_C,N,N,H,P,3,P - 0.2 fire_C,N,N,H,P,3,D
        - 0.2 fire_C,N,N,H,P,3,W - 0.2 fire_C,N,N,H,P,3,C
        - 0.2 fire_C,N,N,H,P,3,I + 0.8 fire_C,N,N,H,P,3,A
        - 0.2 fire_C,N,N,H,P,3,L - 0.2 fire_C,N,N,H,P,3,N  = 0
 c4931: - 0.1 fire_C,N,N,H,P,3,P - 0.1 fire_C,N,N,H,P,3,D
        - 0.1 fire_C,N,N,H,P,3,W - 0.1 fire_C,N,N,H,P,3,C
        - 0.1 fire_C,N,N,H,P,3,I - 0.1 fire_C,N,N,H,P,3,A
        + 0.9 fire_C,N,N,H,P,3,L - 0.1 fire_C,N,N,H,P,3,N  = 0
 c4932: - 0.2 fire_C,N,N,H,P,3,P - 0.2 fire_C,N,N,H,P,3,D
        - 0.2 fire_C,N,N,H,P,3,W - 0.2 fire_C,N,N,H,P,3,C
        - 0.2 fire_C,N,N,H,P,3,I - 0.2 fire_C,N,N,H,P,3,A
        - 0.2 fire_C,N,N,H,P,3,L + 0.8 fire_C,N,N,H,P,3,N  = 0
 c4933: 0.9 fire_C,N,N,H,P,4,P - 0.1 fire_C,N,N,H,P,4,D
        - 0.1 fire_C,N,N,H,P,4,W - 0.1 fire_C,N,N,H,P,4,C
        - 0.1 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        - 0.1 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4934: - 0.1 fire_C,N,N,H,P,4,P + 0.9 fire_C,N,N,H,P,4,D
        - 0.1 fire_C,N,N,H,P,4,W - 0.1 fire_C,N,N,H,P,4,C
        - 0.1 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        - 0.1 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4935: - 0.1 fire_C,N,N,H,P,4,P - 0.1 fire_C,N,N,H,P,4,D
        + 0.9 fire_C,N,N,H,P,4,W - 0.1 fire_C,N,N,H,P,4,C
        - 0.1 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        - 0.1 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4936: - 0.1 fire_C,N,N,H,P,4,P - 0.1 fire_C,N,N,H,P,4,D
        - 0.1 fire_C,N,N,H,P,4,W + 0.9 fire_C,N,N,H,P,4,C
        - 0.1 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        - 0.1 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4937: - 0.1 fire_C,N,N,H,P,4,P - 0.1 fire_C,N,N,H,P,4,D
        - 0.1 fire_C,N,N,H,P,4,W - 0.1 fire_C,N,N,H,P,4,C
        + 0.9 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        - 0.1 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4938: - 0.2 fire_C,N,N,H,P,4,P - 0.2 fire_C,N,N,H,P,4,D
        - 0.2 fire_C,N,N,H,P,4,W - 0.2 fire_C,N,N,H,P,4,C
        - 0.2 fire_C,N,N,H,P,4,I + 0.8 fire_C,N,N,H,P,4,A
        - 0.2 fire_C,N,N,H,P,4,L - 0.2 fire_C,N,N,H,P,4,N  = 0
 c4939: - 0.1 fire_C,N,N,H,P,4,P - 0.1 fire_C,N,N,H,P,4,D
        - 0.1 fire_C,N,N,H,P,4,W - 0.1 fire_C,N,N,H,P,4,C
        - 0.1 fire_C,N,N,H,P,4,I - 0.1 fire_C,N,N,H,P,4,A
        + 0.9 fire_C,N,N,H,P,4,L - 0.1 fire_C,N,N,H,P,4,N  = 0
 c4940: - 0.2 fire_C,N,N,H,P,4,P - 0.2 fire_C,N,N,H,P,4,D
        - 0.2 fire_C,N,N,H,P,4,W - 0.2 fire_C,N,N,H,P,4,C
        - 0.2 fire_C,N,N,H,P,4,I - 0.2 fire_C,N,N,H,P,4,A
        - 0.2 fire_C,N,N,H,P,4,L + 0.8 fire_C,N,N,H,P,4,N  = 0
 c4941: 0.9 fire_C,N,N,H,P,5,P - 0.1 fire_C,N,N,H,P,5,D
        - 0.1 fire_C,N,N,H,P,5,W - 0.1 fire_C,N,N,H,P,5,C
        - 0.1 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        - 0.1 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4942: - 0.1 fire_C,N,N,H,P,5,P + 0.9 fire_C,N,N,H,P,5,D
        - 0.1 fire_C,N,N,H,P,5,W - 0.1 fire_C,N,N,H,P,5,C
        - 0.1 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        - 0.1 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4943: - 0.1 fire_C,N,N,H,P,5,P - 0.1 fire_C,N,N,H,P,5,D
        + 0.9 fire_C,N,N,H,P,5,W - 0.1 fire_C,N,N,H,P,5,C
        - 0.1 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        - 0.1 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4944: - 0.1 fire_C,N,N,H,P,5,P - 0.1 fire_C,N,N,H,P,5,D
        - 0.1 fire_C,N,N,H,P,5,W + 0.9 fire_C,N,N,H,P,5,C
        - 0.1 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        - 0.1 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4945: - 0.1 fire_C,N,N,H,P,5,P - 0.1 fire_C,N,N,H,P,5,D
        - 0.1 fire_C,N,N,H,P,5,W - 0.1 fire_C,N,N,H,P,5,C
        + 0.9 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        - 0.1 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4946: - 0.2 fire_C,N,N,H,P,5,P - 0.2 fire_C,N,N,H,P,5,D
        - 0.2 fire_C,N,N,H,P,5,W - 0.2 fire_C,N,N,H,P,5,C
        - 0.2 fire_C,N,N,H,P,5,I + 0.8 fire_C,N,N,H,P,5,A
        - 0.2 fire_C,N,N,H,P,5,L - 0.2 fire_C,N,N,H,P,5,N  = 0
 c4947: - 0.1 fire_C,N,N,H,P,5,P - 0.1 fire_C,N,N,H,P,5,D
        - 0.1 fire_C,N,N,H,P,5,W - 0.1 fire_C,N,N,H,P,5,C
        - 0.1 fire_C,N,N,H,P,5,I - 0.1 fire_C,N,N,H,P,5,A
        + 0.9 fire_C,N,N,H,P,5,L - 0.1 fire_C,N,N,H,P,5,N  = 0
 c4948: - 0.2 fire_C,N,N,H,P,5,P - 0.2 fire_C,N,N,H,P,5,D
        - 0.2 fire_C,N,N,H,P,5,W - 0.2 fire_C,N,N,H,P,5,C
        - 0.2 fire_C,N,N,H,P,5,I - 0.2 fire_C,N,N,H,P,5,A
        - 0.2 fire_C,N,N,H,P,5,L + 0.8 fire_C,N,N,H,P,5,N  = 0
 c4949: 0.5 fire_C,N,N,H,D,1,P - 0.5 fire_C,N,N,H,D,1,D
        - 0.5 fire_C,N,N,H,D,1,W - 0.5 fire_C,N,N,H,D,1,C
        - 0.5 fire_C,N,N,H,D,1,I - 0.5 fire_C,N,N,H,D,1,A
        - 0.5 fire_C,N,N,H,D,1,L - 0.5 fire_C,N,N,H,D,1,N  = 0
 c4950: - 0.5 fire_C,N,N,H,D,1,P + 0.5 fire_C,N,N,H,D,1,D
        - 0.5 fire_C,N,N,H,D,1,W - 0.5 fire_C,N,N,H,D,1,C
        - 0.5 fire_C,N,N,H,D,1,I - 0.5 fire_C,N,N,H,D,1,A
        - 0.5 fire_C,N,N,H,D,1,L - 0.5 fire_C,N,N,H,D,1,N  = 0
 c4951: fire_C,N,N,H,D,1,W  = 0
 c4952: fire_C,N,N,H,D,1,C  = 0
 c4953: fire_C,N,N,H,D,1,I  = 0
 c4954: fire_C,N,N,H,D,1,A  = 0
 c4955: fire_C,N,N,H,D,1,L  = 0
 c4956: fire_C,N,N,H,D,1,N  = 0
 c4957: 0.5 fire_C,N,N,H,D,2,P - 0.5 fire_C,N,N,H,D,2,D
        - 0.5 fire_C,N,N,H,D,2,W - 0.5 fire_C,N,N,H,D,2,C
        - 0.5 fire_C,N,N,H,D,2,I - 0.5 fire_C,N,N,H,D,2,A
        - 0.5 fire_C,N,N,H,D,2,L - 0.5 fire_C,N,N,H,D,2,N  = 0
 c4958: - 0.5 fire_C,N,N,H,D,2,P + 0.5 fire_C,N,N,H,D,2,D
        - 0.5 fire_C,N,N,H,D,2,W - 0.5 fire_C,N,N,H,D,2,C
        - 0.5 fire_C,N,N,H,D,2,I - 0.5 fire_C,N,N,H,D,2,A
        - 0.5 fire_C,N,N,H,D,2,L - 0.5 fire_C,N,N,H,D,2,N  = 0
 c4959: fire_C,N,N,H,D,2,W  = 0
 c4960: fire_C,N,N,H,D,2,C  = 0
 c4961: fire_C,N,N,H,D,2,I  = 0
 c4962: fire_C,N,N,H,D,2,A  = 0
 c4963: fire_C,N,N,H,D,2,L  = 0
 c4964: fire_C,N,N,H,D,2,N  = 0
 c4965: 0.5 fire_C,N,N,H,D,3,P - 0.5 fire_C,N,N,H,D,3,D
        - 0.5 fire_C,N,N,H,D,3,W - 0.5 fire_C,N,N,H,D,3,C
        - 0.5 fire_C,N,N,H,D,3,I - 0.5 fire_C,N,N,H,D,3,A
        - 0.5 fire_C,N,N,H,D,3,L - 0.5 fire_C,N,N,H,D,3,N  = 0
 c4966: - 0.5 fire_C,N,N,H,D,3,P + 0.5 fire_C,N,N,H,D,3,D
        - 0.5 fire_C,N,N,H,D,3,W - 0.5 fire_C,N,N,H,D,3,C
        - 0.5 fire_C,N,N,H,D,3,I - 0.5 fire_C,N,N,H,D,3,A
        - 0.5 fire_C,N,N,H,D,3,L - 0.5 fire_C,N,N,H,D,3,N  = 0
 c4967: fire_C,N,N,H,D,3,W  = 0
 c4968: fire_C,N,N,H,D,3,C  = 0
 c4969: fire_C,N,N,H,D,3,I  = 0
 c4970: fire_C,N,N,H,D,3,A  = 0
 c4971: fire_C,N,N,H,D,3,L  = 0
 c4972: fire_C,N,N,H,D,3,N  = 0
 c4973: 0.5 fire_C,N,N,H,D,4,P - 0.5 fire_C,N,N,H,D,4,D
        - 0.5 fire_C,N,N,H,D,4,W - 0.5 fire_C,N,N,H,D,4,C
        - 0.5 fire_C,N,N,H,D,4,I - 0.5 fire_C,N,N,H,D,4,A
        - 0.5 fire_C,N,N,H,D,4,L - 0.5 fire_C,N,N,H,D,4,N  = 0
 c4974: - 0.5 fire_C,N,N,H,D,4,P + 0.5 fire_C,N,N,H,D,4,D
        - 0.5 fire_C,N,N,H,D,4,W - 0.5 fire_C,N,N,H,D,4,C
        - 0.5 fire_C,N,N,H,D,4,I - 0.5 fire_C,N,N,H,D,4,A
        - 0.5 fire_C,N,N,H,D,4,L - 0.5 fire_C,N,N,H,D,4,N  = 0
 c4975: fire_C,N,N,H,D,4,W  = 0
 c4976: fire_C,N,N,H,D,4,C  = 0
 c4977: fire_C,N,N,H,D,4,I  = 0
 c4978: fire_C,N,N,H,D,4,A  = 0
 c4979: fire_C,N,N,H,D,4,L  = 0
 c4980: fire_C,N,N,H,D,4,N  = 0
 c4981: 0.5 fire_C,N,N,H,D,5,P - 0.5 fire_C,N,N,H,D,5,D
        - 0.5 fire_C,N,N,H,D,5,W - 0.5 fire_C,N,N,H,D,5,C
        - 0.5 fire_C,N,N,H,D,5,I - 0.5 fire_C,N,N,H,D,5,A
        - 0.5 fire_C,N,N,H,D,5,L - 0.5 fire_C,N,N,H,D,5,N  = 0
 c4982: - 0.5 fire_C,N,N,H,D,5,P + 0.5 fire_C,N,N,H,D,5,D
        - 0.5 fire_C,N,N,H,D,5,W - 0.5 fire_C,N,N,H,D,5,C
        - 0.5 fire_C,N,N,H,D,5,I - 0.5 fire_C,N,N,H,D,5,A
        - 0.5 fire_C,N,N,H,D,5,L - 0.5 fire_C,N,N,H,D,5,N  = 0
 c4983: fire_C,N,N,H,D,5,W  = 0
 c4984: fire_C,N,N,H,D,5,C  = 0
 c4985: fire_C,N,N,H,D,5,I  = 0
 c4986: fire_C,N,N,H,D,5,A  = 0
 c4987: fire_C,N,N,H,D,5,L  = 0
 c4988: fire_C,N,N,H,D,5,N  = 0
 c4989: fire_C,N,N,H,W,1,P  = 0
 c4990: fire_C,N,N,H,W,1,D  = 0
 c4991: - fire_C,N,N,H,W,1,P - fire_C,N,N,H,W,1,D - fire_C,N,N,H,W,1,C
        - fire_C,N,N,H,W,1,I - fire_C,N,N,H,W,1,A - fire_C,N,N,H,W,1,L
        - fire_C,N,N,H,W,1,N  = 0
 c4992: fire_C,N,N,H,W,1,C  = 0
 c4993: fire_C,N,N,H,W,1,I  = 0
 c4994: fire_C,N,N,H,W,1,A  = 0
 c4995: fire_C,N,N,H,W,1,L  = 0
 c4996: fire_C,N,N,H,W,1,N  = 0
 c4997: fire_C,N,N,H,W,2,P  = 0
 c4998: fire_C,N,N,H,W,2,D  = 0
 c4999: - fire_C,N,N,H,W,2,P - fire_C,N,N,H,W,2,D - fire_C,N,N,H,W,2,C
        - fire_C,N,N,H,W,2,I - fire_C,N,N,H,W,2,A - fire_C,N,N,H,W,2,L
        - fire_C,N,N,H,W,2,N  = 0
 c5000: fire_C,N,N,H,W,2,C  = 0
 c5001: fire_C,N,N,H,W,2,I  = 0
 c5002: fire_C,N,N,H,W,2,A  = 0
 c5003: fire_C,N,N,H,W,2,L  = 0
 c5004: fire_C,N,N,H,W,2,N  = 0
 c5005: fire_C,N,N,H,W,3,P  = 0
 c5006: fire_C,N,N,H,W,3,D  = 0
 c5007: - fire_C,N,N,H,W,3,P - fire_C,N,N,H,W,3,D - fire_C,N,N,H,W,3,C
        - fire_C,N,N,H,W,3,I - fire_C,N,N,H,W,3,A - fire_C,N,N,H,W,3,L
        - fire_C,N,N,H,W,3,N  = 0
 c5008: fire_C,N,N,H,W,3,C  = 0
 c5009: fire_C,N,N,H,W,3,I  = 0
 c5010: fire_C,N,N,H,W,3,A  = 0
 c5011: fire_C,N,N,H,W,3,L  = 0
 c5012: fire_C,N,N,H,W,3,N  = 0
 c5013: fire_C,N,N,H,W,4,P  = 0
 c5014: fire_C,N,N,H,W,4,D  = 0
 c5015: - fire_C,N,N,H,W,4,P - fire_C,N,N,H,W,4,D - fire_C,N,N,H,W,4,C
        - fire_C,N,N,H,W,4,I - fire_C,N,N,H,W,4,A - fire_C,N,N,H,W,4,L
        - fire_C,N,N,H,W,4,N  = 0
 c5016: fire_C,N,N,H,W,4,C  = 0
 c5017: fire_C,N,N,H,W,4,I  = 0
 c5018: fire_C,N,N,H,W,4,A  = 0
 c5019: fire_C,N,N,H,W,4,L  = 0
 c5020: fire_C,N,N,H,W,4,N  = 0
 c5021: fire_C,N,N,H,W,5,P  = 0
 c5022: fire_C,N,N,H,W,5,D  = 0
 c5023: - fire_C,N,N,H,W,5,P - fire_C,N,N,H,W,5,D - fire_C,N,N,H,W,5,C
        - fire_C,N,N,H,W,5,I - fire_C,N,N,H,W,5,A - fire_C,N,N,H,W,5,L
        - fire_C,N,N,H,W,5,N  = 0
 c5024: fire_C,N,N,H,W,5,C  = 0
 c5025: fire_C,N,N,H,W,5,I  = 0
 c5026: fire_C,N,N,H,W,5,A  = 0
 c5027: fire_C,N,N,H,W,5,L  = 0
 c5028: fire_C,N,N,H,W,5,N  = 0
 c5029: fire_C,N,N,H,C,1,P  = 0
 c5030: fire_C,N,N,H,C,1,D  = 0
 c5031: fire_C,N,N,H,C,1,W  = 0
 c5032: - fire_C,N,N,H,C,1,P - fire_C,N,N,H,C,1,D - fire_C,N,N,H,C,1,W
        - fire_C,N,N,H,C,1,I - fire_C,N,N,H,C,1,A - fire_C,N,N,H,C,1,L
        - fire_C,N,N,H,C,1,N  = 0
 c5033: fire_C,N,N,H,C,1,I  = 0
 c5034: fire_C,N,N,H,C,1,A  = 0
 c5035: fire_C,N,N,H,C,1,L  = 0
 c5036: fire_C,N,N,H,C,1,N  = 0
 c5037: fire_C,N,N,H,C,2,P  = 0
 c5038: fire_C,N,N,H,C,2,D  = 0
 c5039: fire_C,N,N,H,C,2,W  = 0
 c5040: - fire_C,N,N,H,C,2,P - fire_C,N,N,H,C,2,D - fire_C,N,N,H,C,2,W
        - fire_C,N,N,H,C,2,I - fire_C,N,N,H,C,2,A - fire_C,N,N,H,C,2,L
        - fire_C,N,N,H,C,2,N  = 0
 c5041: fire_C,N,N,H,C,2,I  = 0
 c5042: fire_C,N,N,H,C,2,A  = 0
 c5043: fire_C,N,N,H,C,2,L  = 0
 c5044: fire_C,N,N,H,C,2,N  = 0
 c5045: fire_C,N,N,H,C,3,P  = 0
 c5046: fire_C,N,N,H,C,3,D  = 0
 c5047: fire_C,N,N,H,C,3,W  = 0
 c5048: - fire_C,N,N,H,C,3,P - fire_C,N,N,H,C,3,D - fire_C,N,N,H,C,3,W
        - fire_C,N,N,H,C,3,I - fire_C,N,N,H,C,3,A - fire_C,N,N,H,C,3,L
        - fire_C,N,N,H,C,3,N  = 0
 c5049: fire_C,N,N,H,C,3,I  = 0
 c5050: fire_C,N,N,H,C,3,A  = 0
 c5051: fire_C,N,N,H,C,3,L  = 0
 c5052: fire_C,N,N,H,C,3,N  = 0
 c5053: fire_C,N,N,H,C,4,P  = 0
 c5054: fire_C,N,N,H,C,4,D  = 0
 c5055: fire_C,N,N,H,C,4,W  = 0
 c5056: - fire_C,N,N,H,C,4,P - fire_C,N,N,H,C,4,D - fire_C,N,N,H,C,4,W
        - fire_C,N,N,H,C,4,I - fire_C,N,N,H,C,4,A - fire_C,N,N,H,C,4,L
        - fire_C,N,N,H,C,4,N  = 0
 c5057: fire_C,N,N,H,C,4,I  = 0
 c5058: fire_C,N,N,H,C,4,A  = 0
 c5059: fire_C,N,N,H,C,4,L  = 0
 c5060: fire_C,N,N,H,C,4,N  = 0
 c5061: fire_C,N,N,H,C,5,P  = 0
 c5062: fire_C,N,N,H,C,5,D  = 0
 c5063: fire_C,N,N,H,C,5,W  = 0
 c5064: - fire_C,N,N,H,C,5,P - fire_C,N,N,H,C,5,D - fire_C,N,N,H,C,5,W
        - fire_C,N,N,H,C,5,I - fire_C,N,N,H,C,5,A - fire_C,N,N,H,C,5,L
        - fire_C,N,N,H,C,5,N  = 0
 c5065: fire_C,N,N,H,C,5,I  = 0
 c5066: fire_C,N,N,H,C,5,A  = 0
 c5067: fire_C,N,N,H,C,5,L  = 0
 c5068: fire_C,N,N,H,C,5,N  = 0
 c5069: fire_C,N,N,H,I,1,P  = 0
 c5070: fire_C,N,N,H,I,1,D  = 0
 c5071: fire_C,N,N,H,I,1,W  = 0
 c5072: fire_C,N,N,H,I,1,C  = 0
 c5073: - fire_C,N,N,H,I,1,P - fire_C,N,N,H,I,1,D - fire_C,N,N,H,I,1,W
        - fire_C,N,N,H,I,1,C - fire_C,N,N,H,I,1,A - fire_C,N,N,H,I,1,L
        - fire_C,N,N,H,I,1,N  = 0
 c5074: fire_C,N,N,H,I,1,A  = 0
 c5075: fire_C,N,N,H,I,1,L  = 0
 c5076: fire_C,N,N,H,I,1,N  = 0
 c5077: fire_C,N,N,H,I,2,P  = 0
 c5078: fire_C,N,N,H,I,2,D  = 0
 c5079: fire_C,N,N,H,I,2,W  = 0
 c5080: fire_C,N,N,H,I,2,C  = 0
 c5081: - fire_C,N,N,H,I,2,P - fire_C,N,N,H,I,2,D - fire_C,N,N,H,I,2,W
        - fire_C,N,N,H,I,2,C - fire_C,N,N,H,I,2,A - fire_C,N,N,H,I,2,L
        - fire_C,N,N,H,I,2,N  = 0
 c5082: fire_C,N,N,H,I,2,A  = 0
 c5083: fire_C,N,N,H,I,2,L  = 0
 c5084: fire_C,N,N,H,I,2,N  = 0
 c5085: fire_C,N,N,H,I,3,P  = 0
 c5086: fire_C,N,N,H,I,3,D  = 0
 c5087: fire_C,N,N,H,I,3,W  = 0
 c5088: fire_C,N,N,H,I,3,C  = 0
 c5089: - fire_C,N,N,H,I,3,P - fire_C,N,N,H,I,3,D - fire_C,N,N,H,I,3,W
        - fire_C,N,N,H,I,3,C - fire_C,N,N,H,I,3,A - fire_C,N,N,H,I,3,L
        - fire_C,N,N,H,I,3,N  = 0
 c5090: fire_C,N,N,H,I,3,A  = 0
 c5091: fire_C,N,N,H,I,3,L  = 0
 c5092: fire_C,N,N,H,I,3,N  = 0
 c5093: fire_C,N,N,H,I,4,P  = 0
 c5094: fire_C,N,N,H,I,4,D  = 0
 c5095: fire_C,N,N,H,I,4,W  = 0
 c5096: fire_C,N,N,H,I,4,C  = 0
 c5097: - fire_C,N,N,H,I,4,P - fire_C,N,N,H,I,4,D - fire_C,N,N,H,I,4,W
        - fire_C,N,N,H,I,4,C - fire_C,N,N,H,I,4,A - fire_C,N,N,H,I,4,L
        - fire_C,N,N,H,I,4,N  = 0
 c5098: fire_C,N,N,H,I,4,A  = 0
 c5099: fire_C,N,N,H,I,4,L  = 0
 c5100: fire_C,N,N,H,I,4,N  = 0
 c5101: fire_C,N,N,H,I,5,P  = 0
 c5102: fire_C,N,N,H,I,5,D  = 0
 c5103: fire_C,N,N,H,I,5,W  = 0
 c5104: fire_C,N,N,H,I,5,C  = 0
 c5105: - fire_C,N,N,H,I,5,P - fire_C,N,N,H,I,5,D - fire_C,N,N,H,I,5,W
        - fire_C,N,N,H,I,5,C - fire_C,N,N,H,I,5,A - fire_C,N,N,H,I,5,L
        - fire_C,N,N,H,I,5,N  = 0
 c5106: fire_C,N,N,H,I,5,A  = 0
 c5107: fire_C,N,N,H,I,5,L  = 0
 c5108: fire_C,N,N,H,I,5,N  = 0
 c5109: fire_C,N,N,H,A,1,P  = 0
 c5110: fire_C,N,N,H,A,1,D  = 0
 c5111: fire_C,N,N,H,A,1,W  = 0
 c5112: fire_C,N,N,H,A,1,C  = 0
 c5113: fire_C,N,N,H,A,1,I  = 0
 c5114: - fire_C,N,N,H,A,1,P - fire_C,N,N,H,A,1,D - fire_C,N,N,H,A,1,W
        - fire_C,N,N,H,A,1,C - fire_C,N,N,H,A,1,I - fire_C,N,N,H,A,1,L
        - fire_C,N,N,H,A,1,N  = 0
 c5115: fire_C,N,N,H,A,1,L  = 0
 c5116: fire_C,N,N,H,A,1,N  = 0
 c5117: fire_C,N,N,H,A,2,P  = 0
 c5118: fire_C,N,N,H,A,2,D  = 0
 c5119: fire_C,N,N,H,A,2,W  = 0
 c5120: fire_C,N,N,H,A,2,C  = 0
 c5121: fire_C,N,N,H,A,2,I  = 0
 c5122: - fire_C,N,N,H,A,2,P - fire_C,N,N,H,A,2,D - fire_C,N,N,H,A,2,W
        - fire_C,N,N,H,A,2,C - fire_C,N,N,H,A,2,I - fire_C,N,N,H,A,2,L
        - fire_C,N,N,H,A,2,N  = 0
 c5123: fire_C,N,N,H,A,2,L  = 0
 c5124: fire_C,N,N,H,A,2,N  = 0
 c5125: fire_C,N,N,H,A,3,P  = 0
 c5126: fire_C,N,N,H,A,3,D  = 0
 c5127: fire_C,N,N,H,A,3,W  = 0
 c5128: fire_C,N,N,H,A,3,C  = 0
 c5129: fire_C,N,N,H,A,3,I  = 0
 c5130: - fire_C,N,N,H,A,3,P - fire_C,N,N,H,A,3,D - fire_C,N,N,H,A,3,W
        - fire_C,N,N,H,A,3,C - fire_C,N,N,H,A,3,I - fire_C,N,N,H,A,3,L
        - fire_C,N,N,H,A,3,N  = 0
 c5131: fire_C,N,N,H,A,3,L  = 0
 c5132: fire_C,N,N,H,A,3,N  = 0
 c5133: fire_C,N,N,H,A,4,P  = 0
 c5134: fire_C,N,N,H,A,4,D  = 0
 c5135: fire_C,N,N,H,A,4,W  = 0
 c5136: fire_C,N,N,H,A,4,C  = 0
 c5137: fire_C,N,N,H,A,4,I  = 0
 c5138: - fire_C,N,N,H,A,4,P - fire_C,N,N,H,A,4,D - fire_C,N,N,H,A,4,W
        - fire_C,N,N,H,A,4,C - fire_C,N,N,H,A,4,I - fire_C,N,N,H,A,4,L
        - fire_C,N,N,H,A,4,N  = 0
 c5139: fire_C,N,N,H,A,4,L  = 0
 c5140: fire_C,N,N,H,A,4,N  = 0
 c5141: fire_C,N,N,H,A,5,P  = 0
 c5142: fire_C,N,N,H,A,5,D  = 0
 c5143: fire_C,N,N,H,A,5,W  = 0
 c5144: fire_C,N,N,H,A,5,C  = 0
 c5145: fire_C,N,N,H,A,5,I  = 0
 c5146: - fire_C,N,N,H,A,5,P - fire_C,N,N,H,A,5,D - fire_C,N,N,H,A,5,W
        - fire_C,N,N,H,A,5,C - fire_C,N,N,H,A,5,I - fire_C,N,N,H,A,5,L
        - fire_C,N,N,H,A,5,N  = 0
 c5147: fire_C,N,N,H,A,5,L  = 0
 c5148: fire_C,N,N,H,A,5,N  = 0
 c5149: fire_C,N,N,H,L,1,P  = 0
 c5150: fire_C,N,N,H,L,1,D  = 0
 c5151: fire_C,N,N,H,L,1,W  = 0
 c5152: fire_C,N,N,H,L,1,C  = 0
 c5153: fire_C,N,N,H,L,1,I  = 0
 c5154: fire_C,N,N,H,L,1,A  = 0
 c5155: - fire_C,N,N,H,L,1,P - fire_C,N,N,H,L,1,D - fire_C,N,N,H,L,1,W
        - fire_C,N,N,H,L,1,C - fire_C,N,N,H,L,1,I - fire_C,N,N,H,L,1,A
        - fire_C,N,N,H,L,1,N  = 0
 c5156: fire_C,N,N,H,L,1,N  = 0
 c5157: fire_C,N,N,H,L,2,P  = 0
 c5158: fire_C,N,N,H,L,2,D  = 0
 c5159: fire_C,N,N,H,L,2,W  = 0
 c5160: fire_C,N,N,H,L,2,C  = 0
 c5161: fire_C,N,N,H,L,2,I  = 0
 c5162: fire_C,N,N,H,L,2,A  = 0
 c5163: - fire_C,N,N,H,L,2,P - fire_C,N,N,H,L,2,D - fire_C,N,N,H,L,2,W
        - fire_C,N,N,H,L,2,C - fire_C,N,N,H,L,2,I - fire_C,N,N,H,L,2,A
        - fire_C,N,N,H,L,2,N  = 0
 c5164: fire_C,N,N,H,L,2,N  = 0
 c5165: fire_C,N,N,H,L,3,P  = 0
 c5166: fire_C,N,N,H,L,3,D  = 0
 c5167: fire_C,N,N,H,L,3,W  = 0
 c5168: fire_C,N,N,H,L,3,C  = 0
 c5169: fire_C,N,N,H,L,3,I  = 0
 c5170: fire_C,N,N,H,L,3,A  = 0
 c5171: - fire_C,N,N,H,L,3,P - fire_C,N,N,H,L,3,D - fire_C,N,N,H,L,3,W
        - fire_C,N,N,H,L,3,C - fire_C,N,N,H,L,3,I - fire_C,N,N,H,L,3,A
        - fire_C,N,N,H,L,3,N  = 0
 c5172: fire_C,N,N,H,L,3,N  = 0
 c5173: fire_C,N,N,H,L,4,P  = 0
 c5174: fire_C,N,N,H,L,4,D  = 0
 c5175: fire_C,N,N,H,L,4,W  = 0
 c5176: fire_C,N,N,H,L,4,C  = 0
 c5177: fire_C,N,N,H,L,4,I  = 0
 c5178: fire_C,N,N,H,L,4,A  = 0
 c5179: - fire_C,N,N,H,L,4,P - fire_C,N,N,H,L,4,D - fire_C,N,N,H,L,4,W
        - fire_C,N,N,H,L,4,C - fire_C,N,N,H,L,4,I - fire_C,N,N,H,L,4,A
        - fire_C,N,N,H,L,4,N  = 0
 c5180: fire_C,N,N,H,L,4,N  = 0
 c5181: fire_C,N,N,H,L,5,P  = 0
 c5182: fire_C,N,N,H,L,5,D  = 0
 c5183: fire_C,N,N,H,L,5,W  = 0
 c5184: fire_C,N,N,H,L,5,C  = 0
 c5185: fire_C,N,N,H,L,5,I  = 0
 c5186: fire_C,N,N,H,L,5,A  = 0
 c5187: - fire_C,N,N,H,L,5,P - fire_C,N,N,H,L,5,D - fire_C,N,N,H,L,5,W
        - fire_C,N,N,H,L,5,C - fire_C,N,N,H,L,5,I - fire_C,N,N,H,L,5,A
        - fire_C,N,N,H,L,5,N  = 0
 c5188: fire_C,N,N,H,L,5,N  = 0
 c5189: fire_C,N,N,H,N,1,P  = 0
 c5190: fire_C,N,N,H,N,1,D  = 0
 c5191: fire_C,N,N,H,N,1,W  = 0
 c5192: fire_C,N,N,H,N,1,C  = 0
 c5193: fire_C,N,N,H,N,1,I  = 0
 c5194: fire_C,N,N,H,N,1,A  = 0
 c5195: fire_C,N,N,H,N,1,L  = 0
 c5196: - fire_C,N,N,H,N,1,P - fire_C,N,N,H,N,1,D - fire_C,N,N,H,N,1,W
        - fire_C,N,N,H,N,1,C - fire_C,N,N,H,N,1,I - fire_C,N,N,H,N,1,A
        - fire_C,N,N,H,N,1,L  = 0
 c5197: fire_C,N,N,H,N,2,P  = 0
 c5198: fire_C,N,N,H,N,2,D  = 0
 c5199: fire_C,N,N,H,N,2,W  = 0
 c5200: fire_C,N,N,H,N,2,C  = 0
 c5201: fire_C,N,N,H,N,2,I  = 0
 c5202: fire_C,N,N,H,N,2,A  = 0
 c5203: fire_C,N,N,H,N,2,L  = 0
 c5204: - fire_C,N,N,H,N,2,P - fire_C,N,N,H,N,2,D - fire_C,N,N,H,N,2,W
        - fire_C,N,N,H,N,2,C - fire_C,N,N,H,N,2,I - fire_C,N,N,H,N,2,A
        - fire_C,N,N,H,N,2,L  = 0
 c5205: fire_C,N,N,H,N,3,P  = 0
 c5206: fire_C,N,N,H,N,3,D  = 0
 c5207: fire_C,N,N,H,N,3,W  = 0
 c5208: fire_C,N,N,H,N,3,C  = 0
 c5209: fire_C,N,N,H,N,3,I  = 0
 c5210: fire_C,N,N,H,N,3,A  = 0
 c5211: fire_C,N,N,H,N,3,L  = 0
 c5212: - fire_C,N,N,H,N,3,P - fire_C,N,N,H,N,3,D - fire_C,N,N,H,N,3,W
        - fire_C,N,N,H,N,3,C - fire_C,N,N,H,N,3,I - fire_C,N,N,H,N,3,A
        - fire_C,N,N,H,N,3,L  = 0
 c5213: fire_C,N,N,H,N,4,P  = 0
 c5214: fire_C,N,N,H,N,4,D  = 0
 c5215: fire_C,N,N,H,N,4,W  = 0
 c5216: fire_C,N,N,H,N,4,C  = 0
 c5217: fire_C,N,N,H,N,4,I  = 0
 c5218: fire_C,N,N,H,N,4,A  = 0
 c5219: fire_C,N,N,H,N,4,L  = 0
 c5220: - fire_C,N,N,H,N,4,P - fire_C,N,N,H,N,4,D - fire_C,N,N,H,N,4,W
        - fire_C,N,N,H,N,4,C - fire_C,N,N,H,N,4,I - fire_C,N,N,H,N,4,A
        - fire_C,N,N,H,N,4,L  = 0
 c5221: fire_C,N,N,H,N,5,P  = 0
 c5222: fire_C,N,N,H,N,5,D  = 0
 c5223: fire_C,N,N,H,N,5,W  = 0
 c5224: fire_C,N,N,H,N,5,C  = 0
 c5225: fire_C,N,N,H,N,5,I  = 0
 c5226: fire_C,N,N,H,N,5,A  = 0
 c5227: fire_C,N,N,H,N,5,L  = 0
 c5228: - fire_C,N,N,H,N,5,P - fire_C,N,N,H,N,5,D - fire_C,N,N,H,N,5,W
        - fire_C,N,N,H,N,5,C - fire_C,N,N,H,N,5,I - fire_C,N,N,H,N,5,A
        - fire_C,N,N,H,N,5,L  = 0
 c5229: 0.9 fire_C,N,N,C,P,1,P - 0.1 fire_C,N,N,C,P,1,D
        - 0.1 fire_C,N,N,C,P,1,W - 0.1 fire_C,N,N,C,P,1,C
        - 0.1 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        - 0.1 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5230: - 0.1 fire_C,N,N,C,P,1,P + 0.9 fire_C,N,N,C,P,1,D
        - 0.1 fire_C,N,N,C,P,1,W - 0.1 fire_C,N,N,C,P,1,C
        - 0.1 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        - 0.1 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5231: - 0.1 fire_C,N,N,C,P,1,P - 0.1 fire_C,N,N,C,P,1,D
        + 0.9 fire_C,N,N,C,P,1,W - 0.1 fire_C,N,N,C,P,1,C
        - 0.1 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        - 0.1 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5232: - 0.1 fire_C,N,N,C,P,1,P - 0.1 fire_C,N,N,C,P,1,D
        - 0.1 fire_C,N,N,C,P,1,W + 0.9 fire_C,N,N,C,P,1,C
        - 0.1 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        - 0.1 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5233: - 0.1 fire_C,N,N,C,P,1,P - 0.1 fire_C,N,N,C,P,1,D
        - 0.1 fire_C,N,N,C,P,1,W - 0.1 fire_C,N,N,C,P,1,C
        + 0.9 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        - 0.1 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5234: - 0.2 fire_C,N,N,C,P,1,P - 0.2 fire_C,N,N,C,P,1,D
        - 0.2 fire_C,N,N,C,P,1,W - 0.2 fire_C,N,N,C,P,1,C
        - 0.2 fire_C,N,N,C,P,1,I + 0.8 fire_C,N,N,C,P,1,A
        - 0.2 fire_C,N,N,C,P,1,L - 0.2 fire_C,N,N,C,P,1,N  = 0
 c5235: - 0.1 fire_C,N,N,C,P,1,P - 0.1 fire_C,N,N,C,P,1,D
        - 0.1 fire_C,N,N,C,P,1,W - 0.1 fire_C,N,N,C,P,1,C
        - 0.1 fire_C,N,N,C,P,1,I - 0.1 fire_C,N,N,C,P,1,A
        + 0.9 fire_C,N,N,C,P,1,L - 0.1 fire_C,N,N,C,P,1,N  = 0
 c5236: - 0.2 fire_C,N,N,C,P,1,P - 0.2 fire_C,N,N,C,P,1,D
        - 0.2 fire_C,N,N,C,P,1,W - 0.2 fire_C,N,N,C,P,1,C
        - 0.2 fire_C,N,N,C,P,1,I - 0.2 fire_C,N,N,C,P,1,A
        - 0.2 fire_C,N,N,C,P,1,L + 0.8 fire_C,N,N,C,P,1,N  = 0
 c5237: 0.9 fire_C,N,N,C,P,2,P - 0.1 fire_C,N,N,C,P,2,D
        - 0.1 fire_C,N,N,C,P,2,W - 0.1 fire_C,N,N,C,P,2,C
        - 0.1 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        - 0.1 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5238: - 0.1 fire_C,N,N,C,P,2,P + 0.9 fire_C,N,N,C,P,2,D
        - 0.1 fire_C,N,N,C,P,2,W - 0.1 fire_C,N,N,C,P,2,C
        - 0.1 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        - 0.1 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5239: - 0.1 fire_C,N,N,C,P,2,P - 0.1 fire_C,N,N,C,P,2,D
        + 0.9 fire_C,N,N,C,P,2,W - 0.1 fire_C,N,N,C,P,2,C
        - 0.1 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        - 0.1 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5240: - 0.1 fire_C,N,N,C,P,2,P - 0.1 fire_C,N,N,C,P,2,D
        - 0.1 fire_C,N,N,C,P,2,W + 0.9 fire_C,N,N,C,P,2,C
        - 0.1 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        - 0.1 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5241: - 0.1 fire_C,N,N,C,P,2,P - 0.1 fire_C,N,N,C,P,2,D
        - 0.1 fire_C,N,N,C,P,2,W - 0.1 fire_C,N,N,C,P,2,C
        + 0.9 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        - 0.1 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5242: - 0.2 fire_C,N,N,C,P,2,P - 0.2 fire_C,N,N,C,P,2,D
        - 0.2 fire_C,N,N,C,P,2,W - 0.2 fire_C,N,N,C,P,2,C
        - 0.2 fire_C,N,N,C,P,2,I + 0.8 fire_C,N,N,C,P,2,A
        - 0.2 fire_C,N,N,C,P,2,L - 0.2 fire_C,N,N,C,P,2,N  = 0
 c5243: - 0.1 fire_C,N,N,C,P,2,P - 0.1 fire_C,N,N,C,P,2,D
        - 0.1 fire_C,N,N,C,P,2,W - 0.1 fire_C,N,N,C,P,2,C
        - 0.1 fire_C,N,N,C,P,2,I - 0.1 fire_C,N,N,C,P,2,A
        + 0.9 fire_C,N,N,C,P,2,L - 0.1 fire_C,N,N,C,P,2,N  = 0
 c5244: - 0.2 fire_C,N,N,C,P,2,P - 0.2 fire_C,N,N,C,P,2,D
        - 0.2 fire_C,N,N,C,P,2,W - 0.2 fire_C,N,N,C,P,2,C
        - 0.2 fire_C,N,N,C,P,2,I - 0.2 fire_C,N,N,C,P,2,A
        - 0.2 fire_C,N,N,C,P,2,L + 0.8 fire_C,N,N,C,P,2,N  = 0
 c5245: 0.9 fire_C,N,N,C,P,3,P - 0.1 fire_C,N,N,C,P,3,D
        - 0.1 fire_C,N,N,C,P,3,W - 0.1 fire_C,N,N,C,P,3,C
        - 0.1 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        - 0.1 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5246: - 0.1 fire_C,N,N,C,P,3,P + 0.9 fire_C,N,N,C,P,3,D
        - 0.1 fire_C,N,N,C,P,3,W - 0.1 fire_C,N,N,C,P,3,C
        - 0.1 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        - 0.1 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5247: - 0.1 fire_C,N,N,C,P,3,P - 0.1 fire_C,N,N,C,P,3,D
        + 0.9 fire_C,N,N,C,P,3,W - 0.1 fire_C,N,N,C,P,3,C
        - 0.1 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        - 0.1 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5248: - 0.1 fire_C,N,N,C,P,3,P - 0.1 fire_C,N,N,C,P,3,D
        - 0.1 fire_C,N,N,C,P,3,W + 0.9 fire_C,N,N,C,P,3,C
        - 0.1 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        - 0.1 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5249: - 0.1 fire_C,N,N,C,P,3,P - 0.1 fire_C,N,N,C,P,3,D
        - 0.1 fire_C,N,N,C,P,3,W - 0.1 fire_C,N,N,C,P,3,C
        + 0.9 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        - 0.1 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5250: - 0.2 fire_C,N,N,C,P,3,P - 0.2 fire_C,N,N,C,P,3,D
        - 0.2 fire_C,N,N,C,P,3,W - 0.2 fire_C,N,N,C,P,3,C
        - 0.2 fire_C,N,N,C,P,3,I + 0.8 fire_C,N,N,C,P,3,A
        - 0.2 fire_C,N,N,C,P,3,L - 0.2 fire_C,N,N,C,P,3,N  = 0
 c5251: - 0.1 fire_C,N,N,C,P,3,P - 0.1 fire_C,N,N,C,P,3,D
        - 0.1 fire_C,N,N,C,P,3,W - 0.1 fire_C,N,N,C,P,3,C
        - 0.1 fire_C,N,N,C,P,3,I - 0.1 fire_C,N,N,C,P,3,A
        + 0.9 fire_C,N,N,C,P,3,L - 0.1 fire_C,N,N,C,P,3,N  = 0
 c5252: - 0.2 fire_C,N,N,C,P,3,P - 0.2 fire_C,N,N,C,P,3,D
        - 0.2 fire_C,N,N,C,P,3,W - 0.2 fire_C,N,N,C,P,3,C
        - 0.2 fire_C,N,N,C,P,3,I - 0.2 fire_C,N,N,C,P,3,A
        - 0.2 fire_C,N,N,C,P,3,L + 0.8 fire_C,N,N,C,P,3,N  = 0
 c5253: 0.9 fire_C,N,N,C,P,4,P - 0.1 fire_C,N,N,C,P,4,D
        - 0.1 fire_C,N,N,C,P,4,W - 0.1 fire_C,N,N,C,P,4,C
        - 0.1 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        - 0.1 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5254: - 0.1 fire_C,N,N,C,P,4,P + 0.9 fire_C,N,N,C,P,4,D
        - 0.1 fire_C,N,N,C,P,4,W - 0.1 fire_C,N,N,C,P,4,C
        - 0.1 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        - 0.1 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5255: - 0.1 fire_C,N,N,C,P,4,P - 0.1 fire_C,N,N,C,P,4,D
        + 0.9 fire_C,N,N,C,P,4,W - 0.1 fire_C,N,N,C,P,4,C
        - 0.1 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        - 0.1 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5256: - 0.1 fire_C,N,N,C,P,4,P - 0.1 fire_C,N,N,C,P,4,D
        - 0.1 fire_C,N,N,C,P,4,W + 0.9 fire_C,N,N,C,P,4,C
        - 0.1 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        - 0.1 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5257: - 0.1 fire_C,N,N,C,P,4,P - 0.1 fire_C,N,N,C,P,4,D
        - 0.1 fire_C,N,N,C,P,4,W - 0.1 fire_C,N,N,C,P,4,C
        + 0.9 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        - 0.1 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5258: - 0.2 fire_C,N,N,C,P,4,P - 0.2 fire_C,N,N,C,P,4,D
        - 0.2 fire_C,N,N,C,P,4,W - 0.2 fire_C,N,N,C,P,4,C
        - 0.2 fire_C,N,N,C,P,4,I + 0.8 fire_C,N,N,C,P,4,A
        - 0.2 fire_C,N,N,C,P,4,L - 0.2 fire_C,N,N,C,P,4,N  = 0
 c5259: - 0.1 fire_C,N,N,C,P,4,P - 0.1 fire_C,N,N,C,P,4,D
        - 0.1 fire_C,N,N,C,P,4,W - 0.1 fire_C,N,N,C,P,4,C
        - 0.1 fire_C,N,N,C,P,4,I - 0.1 fire_C,N,N,C,P,4,A
        + 0.9 fire_C,N,N,C,P,4,L - 0.1 fire_C,N,N,C,P,4,N  = 0
 c5260: - 0.2 fire_C,N,N,C,P,4,P - 0.2 fire_C,N,N,C,P,4,D
        - 0.2 fire_C,N,N,C,P,4,W - 0.2 fire_C,N,N,C,P,4,C
        - 0.2 fire_C,N,N,C,P,4,I - 0.2 fire_C,N,N,C,P,4,A
        - 0.2 fire_C,N,N,C,P,4,L + 0.8 fire_C,N,N,C,P,4,N  = 0
 c5261: 0.9 fire_C,N,N,C,P,5,P - 0.1 fire_C,N,N,C,P,5,D
        - 0.1 fire_C,N,N,C,P,5,W - 0.1 fire_C,N,N,C,P,5,C
        - 0.1 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        - 0.1 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5262: - 0.1 fire_C,N,N,C,P,5,P + 0.9 fire_C,N,N,C,P,5,D
        - 0.1 fire_C,N,N,C,P,5,W - 0.1 fire_C,N,N,C,P,5,C
        - 0.1 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        - 0.1 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5263: - 0.1 fire_C,N,N,C,P,5,P - 0.1 fire_C,N,N,C,P,5,D
        + 0.9 fire_C,N,N,C,P,5,W - 0.1 fire_C,N,N,C,P,5,C
        - 0.1 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        - 0.1 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5264: - 0.1 fire_C,N,N,C,P,5,P - 0.1 fire_C,N,N,C,P,5,D
        - 0.1 fire_C,N,N,C,P,5,W + 0.9 fire_C,N,N,C,P,5,C
        - 0.1 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        - 0.1 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5265: - 0.1 fire_C,N,N,C,P,5,P - 0.1 fire_C,N,N,C,P,5,D
        - 0.1 fire_C,N,N,C,P,5,W - 0.1 fire_C,N,N,C,P,5,C
        + 0.9 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        - 0.1 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5266: - 0.2 fire_C,N,N,C,P,5,P - 0.2 fire_C,N,N,C,P,5,D
        - 0.2 fire_C,N,N,C,P,5,W - 0.2 fire_C,N,N,C,P,5,C
        - 0.2 fire_C,N,N,C,P,5,I + 0.8 fire_C,N,N,C,P,5,A
        - 0.2 fire_C,N,N,C,P,5,L - 0.2 fire_C,N,N,C,P,5,N  = 0
 c5267: - 0.1 fire_C,N,N,C,P,5,P - 0.1 fire_C,N,N,C,P,5,D
        - 0.1 fire_C,N,N,C,P,5,W - 0.1 fire_C,N,N,C,P,5,C
        - 0.1 fire_C,N,N,C,P,5,I - 0.1 fire_C,N,N,C,P,5,A
        + 0.9 fire_C,N,N,C,P,5,L - 0.1 fire_C,N,N,C,P,5,N  = 0
 c5268: - 0.2 fire_C,N,N,C,P,5,P - 0.2 fire_C,N,N,C,P,5,D
        - 0.2 fire_C,N,N,C,P,5,W - 0.2 fire_C,N,N,C,P,5,C
        - 0.2 fire_C,N,N,C,P,5,I - 0.2 fire_C,N,N,C,P,5,A
        - 0.2 fire_C,N,N,C,P,5,L + 0.8 fire_C,N,N,C,P,5,N  = 0
 c5269: 0.5 fire_C,N,N,C,D,1,P - 0.5 fire_C,N,N,C,D,1,D
        - 0.5 fire_C,N,N,C,D,1,W - 0.5 fire_C,N,N,C,D,1,C
        - 0.5 fire_C,N,N,C,D,1,I - 0.5 fire_C,N,N,C,D,1,A
        - 0.5 fire_C,N,N,C,D,1,L - 0.5 fire_C,N,N,C,D,1,N  = 0
 c5270: - 0.5 fire_C,N,N,C,D,1,P + 0.5 fire_C,N,N,C,D,1,D
        - 0.5 fire_C,N,N,C,D,1,W - 0.5 fire_C,N,N,C,D,1,C
        - 0.5 fire_C,N,N,C,D,1,I - 0.5 fire_C,N,N,C,D,1,A
        - 0.5 fire_C,N,N,C,D,1,L - 0.5 fire_C,N,N,C,D,1,N  = 0
 c5271: fire_C,N,N,C,D,1,W  = 0
 c5272: fire_C,N,N,C,D,1,C  = 0
 c5273: fire_C,N,N,C,D,1,I  = 0
 c5274: fire_C,N,N,C,D,1,A  = 0
 c5275: fire_C,N,N,C,D,1,L  = 0
 c5276: fire_C,N,N,C,D,1,N  = 0
 c5277: 0.5 fire_C,N,N,C,D,2,P - 0.5 fire_C,N,N,C,D,2,D
        - 0.5 fire_C,N,N,C,D,2,W - 0.5 fire_C,N,N,C,D,2,C
        - 0.5 fire_C,N,N,C,D,2,I - 0.5 fire_C,N,N,C,D,2,A
        - 0.5 fire_C,N,N,C,D,2,L - 0.5 fire_C,N,N,C,D,2,N  = 0
 c5278: - 0.5 fire_C,N,N,C,D,2,P + 0.5 fire_C,N,N,C,D,2,D
        - 0.5 fire_C,N,N,C,D,2,W - 0.5 fire_C,N,N,C,D,2,C
        - 0.5 fire_C,N,N,C,D,2,I - 0.5 fire_C,N,N,C,D,2,A
        - 0.5 fire_C,N,N,C,D,2,L - 0.5 fire_C,N,N,C,D,2,N  = 0
 c5279: fire_C,N,N,C,D,2,W  = 0
 c5280: fire_C,N,N,C,D,2,C  = 0
 c5281: fire_C,N,N,C,D,2,I  = 0
 c5282: fire_C,N,N,C,D,2,A  = 0
 c5283: fire_C,N,N,C,D,2,L  = 0
 c5284: fire_C,N,N,C,D,2,N  = 0
 c5285: 0.5 fire_C,N,N,C,D,3,P - 0.5 fire_C,N,N,C,D,3,D
        - 0.5 fire_C,N,N,C,D,3,W - 0.5 fire_C,N,N,C,D,3,C
        - 0.5 fire_C,N,N,C,D,3,I - 0.5 fire_C,N,N,C,D,3,A
        - 0.5 fire_C,N,N,C,D,3,L - 0.5 fire_C,N,N,C,D,3,N  = 0
 c5286: - 0.5 fire_C,N,N,C,D,3,P + 0.5 fire_C,N,N,C,D,3,D
        - 0.5 fire_C,N,N,C,D,3,W - 0.5 fire_C,N,N,C,D,3,C
        - 0.5 fire_C,N,N,C,D,3,I - 0.5 fire_C,N,N,C,D,3,A
        - 0.5 fire_C,N,N,C,D,3,L - 0.5 fire_C,N,N,C,D,3,N  = 0
 c5287: fire_C,N,N,C,D,3,W  = 0
 c5288: fire_C,N,N,C,D,3,C  = 0
 c5289: fire_C,N,N,C,D,3,I  = 0
 c5290: fire_C,N,N,C,D,3,A  = 0
 c5291: fire_C,N,N,C,D,3,L  = 0
 c5292: fire_C,N,N,C,D,3,N  = 0
 c5293: 0.5 fire_C,N,N,C,D,4,P - 0.5 fire_C,N,N,C,D,4,D
        - 0.5 fire_C,N,N,C,D,4,W - 0.5 fire_C,N,N,C,D,4,C
        - 0.5 fire_C,N,N,C,D,4,I - 0.5 fire_C,N,N,C,D,4,A
        - 0.5 fire_C,N,N,C,D,4,L - 0.5 fire_C,N,N,C,D,4,N  = 0
 c5294: - 0.5 fire_C,N,N,C,D,4,P + 0.5 fire_C,N,N,C,D,4,D
        - 0.5 fire_C,N,N,C,D,4,W - 0.5 fire_C,N,N,C,D,4,C
        - 0.5 fire_C,N,N,C,D,4,I - 0.5 fire_C,N,N,C,D,4,A
        - 0.5 fire_C,N,N,C,D,4,L - 0.5 fire_C,N,N,C,D,4,N  = 0
 c5295: fire_C,N,N,C,D,4,W  = 0
 c5296: fire_C,N,N,C,D,4,C  = 0
 c5297: fire_C,N,N,C,D,4,I  = 0
 c5298: fire_C,N,N,C,D,4,A  = 0
 c5299: fire_C,N,N,C,D,4,L  = 0
 c5300: fire_C,N,N,C,D,4,N  = 0
 c5301: 0.5 fire_C,N,N,C,D,5,P - 0.5 fire_C,N,N,C,D,5,D
        - 0.5 fire_C,N,N,C,D,5,W - 0.5 fire_C,N,N,C,D,5,C
        - 0.5 fire_C,N,N,C,D,5,I - 0.5 fire_C,N,N,C,D,5,A
        - 0.5 fire_C,N,N,C,D,5,L - 0.5 fire_C,N,N,C,D,5,N  = 0
 c5302: - 0.5 fire_C,N,N,C,D,5,P + 0.5 fire_C,N,N,C,D,5,D
        - 0.5 fire_C,N,N,C,D,5,W - 0.5 fire_C,N,N,C,D,5,C
        - 0.5 fire_C,N,N,C,D,5,I - 0.5 fire_C,N,N,C,D,5,A
        - 0.5 fire_C,N,N,C,D,5,L - 0.5 fire_C,N,N,C,D,5,N  = 0
 c5303: fire_C,N,N,C,D,5,W  = 0
 c5304: fire_C,N,N,C,D,5,C  = 0
 c5305: fire_C,N,N,C,D,5,I  = 0
 c5306: fire_C,N,N,C,D,5,A  = 0
 c5307: fire_C,N,N,C,D,5,L  = 0
 c5308: fire_C,N,N,C,D,5,N  = 0
 c5309: fire_C,N,N,C,W,1,P  = 0
 c5310: fire_C,N,N,C,W,1,D  = 0
 c5311: - fire_C,N,N,C,W,1,P - fire_C,N,N,C,W,1,D - fire_C,N,N,C,W,1,C
        - fire_C,N,N,C,W,1,I - fire_C,N,N,C,W,1,A - fire_C,N,N,C,W,1,L
        - fire_C,N,N,C,W,1,N  = 0
 c5312: fire_C,N,N,C,W,1,C  = 0
 c5313: fire_C,N,N,C,W,1,I  = 0
 c5314: fire_C,N,N,C,W,1,A  = 0
 c5315: fire_C,N,N,C,W,1,L  = 0
 c5316: fire_C,N,N,C,W,1,N  = 0
 c5317: fire_C,N,N,C,W,2,P  = 0
 c5318: fire_C,N,N,C,W,2,D  = 0
 c5319: - fire_C,N,N,C,W,2,P - fire_C,N,N,C,W,2,D - fire_C,N,N,C,W,2,C
        - fire_C,N,N,C,W,2,I - fire_C,N,N,C,W,2,A - fire_C,N,N,C,W,2,L
        - fire_C,N,N,C,W,2,N  = 0
 c5320: fire_C,N,N,C,W,2,C  = 0
 c5321: fire_C,N,N,C,W,2,I  = 0
 c5322: fire_C,N,N,C,W,2,A  = 0
 c5323: fire_C,N,N,C,W,2,L  = 0
 c5324: fire_C,N,N,C,W,2,N  = 0
 c5325: fire_C,N,N,C,W,3,P  = 0
 c5326: fire_C,N,N,C,W,3,D  = 0
 c5327: - fire_C,N,N,C,W,3,P - fire_C,N,N,C,W,3,D - fire_C,N,N,C,W,3,C
        - fire_C,N,N,C,W,3,I - fire_C,N,N,C,W,3,A - fire_C,N,N,C,W,3,L
        - fire_C,N,N,C,W,3,N  = 0
 c5328: fire_C,N,N,C,W,3,C  = 0
 c5329: fire_C,N,N,C,W,3,I  = 0
 c5330: fire_C,N,N,C,W,3,A  = 0
 c5331: fire_C,N,N,C,W,3,L  = 0
 c5332: fire_C,N,N,C,W,3,N  = 0
 c5333: fire_C,N,N,C,W,4,P  = 0
 c5334: fire_C,N,N,C,W,4,D  = 0
 c5335: - fire_C,N,N,C,W,4,P - fire_C,N,N,C,W,4,D - fire_C,N,N,C,W,4,C
        - fire_C,N,N,C,W,4,I - fire_C,N,N,C,W,4,A - fire_C,N,N,C,W,4,L
        - fire_C,N,N,C,W,4,N  = 0
 c5336: fire_C,N,N,C,W,4,C  = 0
 c5337: fire_C,N,N,C,W,4,I  = 0
 c5338: fire_C,N,N,C,W,4,A  = 0
 c5339: fire_C,N,N,C,W,4,L  = 0
 c5340: fire_C,N,N,C,W,4,N  = 0
 c5341: fire_C,N,N,C,W,5,P  = 0
 c5342: fire_C,N,N,C,W,5,D  = 0
 c5343: - fire_C,N,N,C,W,5,P - fire_C,N,N,C,W,5,D - fire_C,N,N,C,W,5,C
        - fire_C,N,N,C,W,5,I - fire_C,N,N,C,W,5,A - fire_C,N,N,C,W,5,L
        - fire_C,N,N,C,W,5,N  = 0
 c5344: fire_C,N,N,C,W,5,C  = 0
 c5345: fire_C,N,N,C,W,5,I  = 0
 c5346: fire_C,N,N,C,W,5,A  = 0
 c5347: fire_C,N,N,C,W,5,L  = 0
 c5348: fire_C,N,N,C,W,5,N  = 0
 c5349: fire_C,N,N,C,C,1,P  = 0
 c5350: fire_C,N,N,C,C,1,D  = 0
 c5351: fire_C,N,N,C,C,1,W  = 0
 c5352: - fire_C,N,N,C,C,1,P - fire_C,N,N,C,C,1,D - fire_C,N,N,C,C,1,W
        - fire_C,N,N,C,C,1,I - fire_C,N,N,C,C,1,A - fire_C,N,N,C,C,1,L
        - fire_C,N,N,C,C,1,N  = 0
 c5353: fire_C,N,N,C,C,1,I  = 0
 c5354: fire_C,N,N,C,C,1,A  = 0
 c5355: fire_C,N,N,C,C,1,L  = 0
 c5356: fire_C,N,N,C,C,1,N  = 0
 c5357: fire_C,N,N,C,C,2,P  = 0
 c5358: fire_C,N,N,C,C,2,D  = 0
 c5359: fire_C,N,N,C,C,2,W  = 0
 c5360: - fire_C,N,N,C,C,2,P - fire_C,N,N,C,C,2,D - fire_C,N,N,C,C,2,W
        - fire_C,N,N,C,C,2,I - fire_C,N,N,C,C,2,A - fire_C,N,N,C,C,2,L
        - fire_C,N,N,C,C,2,N  = 0
 c5361: fire_C,N,N,C,C,2,I  = 0
 c5362: fire_C,N,N,C,C,2,A  = 0
 c5363: fire_C,N,N,C,C,2,L  = 0
 c5364: fire_C,N,N,C,C,2,N  = 0
 c5365: fire_C,N,N,C,C,3,P  = 0
 c5366: fire_C,N,N,C,C,3,D  = 0
 c5367: fire_C,N,N,C,C,3,W  = 0
 c5368: - fire_C,N,N,C,C,3,P - fire_C,N,N,C,C,3,D - fire_C,N,N,C,C,3,W
        - fire_C,N,N,C,C,3,I - fire_C,N,N,C,C,3,A - fire_C,N,N,C,C,3,L
        - fire_C,N,N,C,C,3,N  = 0
 c5369: fire_C,N,N,C,C,3,I  = 0
 c5370: fire_C,N,N,C,C,3,A  = 0
 c5371: fire_C,N,N,C,C,3,L  = 0
 c5372: fire_C,N,N,C,C,3,N  = 0
 c5373: fire_C,N,N,C,C,4,P  = 0
 c5374: fire_C,N,N,C,C,4,D  = 0
 c5375: fire_C,N,N,C,C,4,W  = 0
 c5376: - fire_C,N,N,C,C,4,P - fire_C,N,N,C,C,4,D - fire_C,N,N,C,C,4,W
        - fire_C,N,N,C,C,4,I - fire_C,N,N,C,C,4,A - fire_C,N,N,C,C,4,L
        - fire_C,N,N,C,C,4,N  = 0
 c5377: fire_C,N,N,C,C,4,I  = 0
 c5378: fire_C,N,N,C,C,4,A  = 0
 c5379: fire_C,N,N,C,C,4,L  = 0
 c5380: fire_C,N,N,C,C,4,N  = 0
 c5381: fire_C,N,N,C,C,5,P  = 0
 c5382: fire_C,N,N,C,C,5,D  = 0
 c5383: fire_C,N,N,C,C,5,W  = 0
 c5384: - fire_C,N,N,C,C,5,P - fire_C,N,N,C,C,5,D - fire_C,N,N,C,C,5,W
        - fire_C,N,N,C,C,5,I - fire_C,N,N,C,C,5,A - fire_C,N,N,C,C,5,L
        - fire_C,N,N,C,C,5,N  = 0
 c5385: fire_C,N,N,C,C,5,I  = 0
 c5386: fire_C,N,N,C,C,5,A  = 0
 c5387: fire_C,N,N,C,C,5,L  = 0
 c5388: fire_C,N,N,C,C,5,N  = 0
 c5389: fire_C,N,N,C,I,1,P  = 0
 c5390: fire_C,N,N,C,I,1,D  = 0
 c5391: fire_C,N,N,C,I,1,W  = 0
 c5392: fire_C,N,N,C,I,1,C  = 0
 c5393: - fire_C,N,N,C,I,1,P - fire_C,N,N,C,I,1,D - fire_C,N,N,C,I,1,W
        - fire_C,N,N,C,I,1,C - fire_C,N,N,C,I,1,A - fire_C,N,N,C,I,1,L
        - fire_C,N,N,C,I,1,N  = 0
 c5394: fire_C,N,N,C,I,1,A  = 0
 c5395: fire_C,N,N,C,I,1,L  = 0
 c5396: fire_C,N,N,C,I,1,N  = 0
 c5397: fire_C,N,N,C,I,2,P  = 0
 c5398: fire_C,N,N,C,I,2,D  = 0
 c5399: fire_C,N,N,C,I,2,W  = 0
 c5400: fire_C,N,N,C,I,2,C  = 0
 c5401: - fire_C,N,N,C,I,2,P - fire_C,N,N,C,I,2,D - fire_C,N,N,C,I,2,W
        - fire_C,N,N,C,I,2,C - fire_C,N,N,C,I,2,A - fire_C,N,N,C,I,2,L
        - fire_C,N,N,C,I,2,N  = 0
 c5402: fire_C,N,N,C,I,2,A  = 0
 c5403: fire_C,N,N,C,I,2,L  = 0
 c5404: fire_C,N,N,C,I,2,N  = 0
 c5405: fire_C,N,N,C,I,3,P  = 0
 c5406: fire_C,N,N,C,I,3,D  = 0
 c5407: fire_C,N,N,C,I,3,W  = 0
 c5408: fire_C,N,N,C,I,3,C  = 0
 c5409: - fire_C,N,N,C,I,3,P - fire_C,N,N,C,I,3,D - fire_C,N,N,C,I,3,W
        - fire_C,N,N,C,I,3,C - fire_C,N,N,C,I,3,A - fire_C,N,N,C,I,3,L
        - fire_C,N,N,C,I,3,N  = 0
 c5410: fire_C,N,N,C,I,3,A  = 0
 c5411: fire_C,N,N,C,I,3,L  = 0
 c5412: fire_C,N,N,C,I,3,N  = 0
 c5413: fire_C,N,N,C,I,4,P  = 0
 c5414: fire_C,N,N,C,I,4,D  = 0
 c5415: fire_C,N,N,C,I,4,W  = 0
 c5416: fire_C,N,N,C,I,4,C  = 0
 c5417: - fire_C,N,N,C,I,4,P - fire_C,N,N,C,I,4,D - fire_C,N,N,C,I,4,W
        - fire_C,N,N,C,I,4,C - fire_C,N,N,C,I,4,A - fire_C,N,N,C,I,4,L
        - fire_C,N,N,C,I,4,N  = 0
 c5418: fire_C,N,N,C,I,4,A  = 0
 c5419: fire_C,N,N,C,I,4,L  = 0
 c5420: fire_C,N,N,C,I,4,N  = 0
 c5421: fire_C,N,N,C,I,5,P  = 0
 c5422: fire_C,N,N,C,I,5,D  = 0
 c5423: fire_C,N,N,C,I,5,W  = 0
 c5424: fire_C,N,N,C,I,5,C  = 0
 c5425: - fire_C,N,N,C,I,5,P - fire_C,N,N,C,I,5,D - fire_C,N,N,C,I,5,W
        - fire_C,N,N,C,I,5,C - fire_C,N,N,C,I,5,A - fire_C,N,N,C,I,5,L
        - fire_C,N,N,C,I,5,N  = 0
 c5426: fire_C,N,N,C,I,5,A  = 0
 c5427: fire_C,N,N,C,I,5,L  = 0
 c5428: fire_C,N,N,C,I,5,N  = 0
 c5429: fire_C,N,N,C,A,1,P  = 0
 c5430: fire_C,N,N,C,A,1,D  = 0
 c5431: fire_C,N,N,C,A,1,W  = 0
 c5432: fire_C,N,N,C,A,1,C  = 0
 c5433: fire_C,N,N,C,A,1,I  = 0
 c5434: - fire_C,N,N,C,A,1,P - fire_C,N,N,C,A,1,D - fire_C,N,N,C,A,1,W
        - fire_C,N,N,C,A,1,C - fire_C,N,N,C,A,1,I - fire_C,N,N,C,A,1,L
        - fire_C,N,N,C,A,1,N  = 0
 c5435: fire_C,N,N,C,A,1,L  = 0
 c5436: fire_C,N,N,C,A,1,N  = 0
 c5437: fire_C,N,N,C,A,2,P  = 0
 c5438: fire_C,N,N,C,A,2,D  = 0
 c5439: fire_C,N,N,C,A,2,W  = 0
 c5440: fire_C,N,N,C,A,2,C  = 0
 c5441: fire_C,N,N,C,A,2,I  = 0
 c5442: - fire_C,N,N,C,A,2,P - fire_C,N,N,C,A,2,D - fire_C,N,N,C,A,2,W
        - fire_C,N,N,C,A,2,C - fire_C,N,N,C,A,2,I - fire_C,N,N,C,A,2,L
        - fire_C,N,N,C,A,2,N  = 0
 c5443: fire_C,N,N,C,A,2,L  = 0
 c5444: fire_C,N,N,C,A,2,N  = 0
 c5445: fire_C,N,N,C,A,3,P  = 0
 c5446: fire_C,N,N,C,A,3,D  = 0
 c5447: fire_C,N,N,C,A,3,W  = 0
 c5448: fire_C,N,N,C,A,3,C  = 0
 c5449: fire_C,N,N,C,A,3,I  = 0
 c5450: - fire_C,N,N,C,A,3,P - fire_C,N,N,C,A,3,D - fire_C,N,N,C,A,3,W
        - fire_C,N,N,C,A,3,C - fire_C,N,N,C,A,3,I - fire_C,N,N,C,A,3,L
        - fire_C,N,N,C,A,3,N  = 0
 c5451: fire_C,N,N,C,A,3,L  = 0
 c5452: fire_C,N,N,C,A,3,N  = 0
 c5453: fire_C,N,N,C,A,4,P  = 0
 c5454: fire_C,N,N,C,A,4,D  = 0
 c5455: fire_C,N,N,C,A,4,W  = 0
 c5456: fire_C,N,N,C,A,4,C  = 0
 c5457: fire_C,N,N,C,A,4,I  = 0
 c5458: - fire_C,N,N,C,A,4,P - fire_C,N,N,C,A,4,D - fire_C,N,N,C,A,4,W
        - fire_C,N,N,C,A,4,C - fire_C,N,N,C,A,4,I - fire_C,N,N,C,A,4,L
        - fire_C,N,N,C,A,4,N  = 0
 c5459: fire_C,N,N,C,A,4,L  = 0
 c5460: fire_C,N,N,C,A,4,N  = 0
 c5461: fire_C,N,N,C,A,5,P  = 0
 c5462: fire_C,N,N,C,A,5,D  = 0
 c5463: fire_C,N,N,C,A,5,W  = 0
 c5464: fire_C,N,N,C,A,5,C  = 0
 c5465: fire_C,N,N,C,A,5,I  = 0
 c5466: - fire_C,N,N,C,A,5,P - fire_C,N,N,C,A,5,D - fire_C,N,N,C,A,5,W
        - fire_C,N,N,C,A,5,C - fire_C,N,N,C,A,5,I - fire_C,N,N,C,A,5,L
        - fire_C,N,N,C,A,5,N  = 0
 c5467: fire_C,N,N,C,A,5,L  = 0
 c5468: fire_C,N,N,C,A,5,N  = 0
 c5469: fire_C,N,N,C,L,1,P  = 0
 c5470: fire_C,N,N,C,L,1,D  = 0
 c5471: fire_C,N,N,C,L,1,W  = 0
 c5472: fire_C,N,N,C,L,1,C  = 0
 c5473: fire_C,N,N,C,L,1,I  = 0
 c5474: fire_C,N,N,C,L,1,A  = 0
 c5475: - fire_C,N,N,C,L,1,P - fire_C,N,N,C,L,1,D - fire_C,N,N,C,L,1,W
        - fire_C,N,N,C,L,1,C - fire_C,N,N,C,L,1,I - fire_C,N,N,C,L,1,A
        - fire_C,N,N,C,L,1,N  = 0
 c5476: fire_C,N,N,C,L,1,N  = 0
 c5477: fire_C,N,N,C,L,2,P  = 0
 c5478: fire_C,N,N,C,L,2,D  = 0
 c5479: fire_C,N,N,C,L,2,W  = 0
 c5480: fire_C,N,N,C,L,2,C  = 0
 c5481: fire_C,N,N,C,L,2,I  = 0
 c5482: fire_C,N,N,C,L,2,A  = 0
 c5483: - fire_C,N,N,C,L,2,P - fire_C,N,N,C,L,2,D - fire_C,N,N,C,L,2,W
        - fire_C,N,N,C,L,2,C - fire_C,N,N,C,L,2,I - fire_C,N,N,C,L,2,A
        - fire_C,N,N,C,L,2,N  = 0
 c5484: fire_C,N,N,C,L,2,N  = 0
 c5485: fire_C,N,N,C,L,3,P  = 0
 c5486: fire_C,N,N,C,L,3,D  = 0
 c5487: fire_C,N,N,C,L,3,W  = 0
 c5488: fire_C,N,N,C,L,3,C  = 0
 c5489: fire_C,N,N,C,L,3,I  = 0
 c5490: fire_C,N,N,C,L,3,A  = 0
 c5491: - fire_C,N,N,C,L,3,P - fire_C,N,N,C,L,3,D - fire_C,N,N,C,L,3,W
        - fire_C,N,N,C,L,3,C - fire_C,N,N,C,L,3,I - fire_C,N,N,C,L,3,A
        - fire_C,N,N,C,L,3,N  = 0
 c5492: fire_C,N,N,C,L,3,N  = 0
 c5493: fire_C,N,N,C,L,4,P  = 0
 c5494: fire_C,N,N,C,L,4,D  = 0
 c5495: fire_C,N,N,C,L,4,W  = 0
 c5496: fire_C,N,N,C,L,4,C  = 0
 c5497: fire_C,N,N,C,L,4,I  = 0
 c5498: fire_C,N,N,C,L,4,A  = 0
 c5499: - fire_C,N,N,C,L,4,P - fire_C,N,N,C,L,4,D - fire_C,N,N,C,L,4,W
        - fire_C,N,N,C,L,4,C - fire_C,N,N,C,L,4,I - fire_C,N,N,C,L,4,A
        - fire_C,N,N,C,L,4,N  = 0
 c5500: fire_C,N,N,C,L,4,N  = 0
 c5501: fire_C,N,N,C,L,5,P  = 0
 c5502: fire_C,N,N,C,L,5,D  = 0
 c5503: fire_C,N,N,C,L,5,W  = 0
 c5504: fire_C,N,N,C,L,5,C  = 0
 c5505: fire_C,N,N,C,L,5,I  = 0
 c5506: fire_C,N,N,C,L,5,A  = 0
 c5507: - fire_C,N,N,C,L,5,P - fire_C,N,N,C,L,5,D - fire_C,N,N,C,L,5,W
        - fire_C,N,N,C,L,5,C - fire_C,N,N,C,L,5,I - fire_C,N,N,C,L,5,A
        - fire_C,N,N,C,L,5,N  = 0
 c5508: fire_C,N,N,C,L,5,N  = 0
 c5509: fire_C,N,N,C,N,1,P  = 0
 c5510: fire_C,N,N,C,N,1,D  = 0
 c5511: fire_C,N,N,C,N,1,W  = 0
 c5512: fire_C,N,N,C,N,1,C  = 0
 c5513: fire_C,N,N,C,N,1,I  = 0
 c5514: fire_C,N,N,C,N,1,A  = 0
 c5515: fire_C,N,N,C,N,1,L  = 0
 c5516: - fire_C,N,N,C,N,1,P - fire_C,N,N,C,N,1,D - fire_C,N,N,C,N,1,W
        - fire_C,N,N,C,N,1,C - fire_C,N,N,C,N,1,I - fire_C,N,N,C,N,1,A
        - fire_C,N,N,C,N,1,L  = 0
 c5517: fire_C,N,N,C,N,2,P  = 0
 c5518: fire_C,N,N,C,N,2,D  = 0
 c5519: fire_C,N,N,C,N,2,W  = 0
 c5520: fire_C,N,N,C,N,2,C  = 0
 c5521: fire_C,N,N,C,N,2,I  = 0
 c5522: fire_C,N,N,C,N,2,A  = 0
 c5523: fire_C,N,N,C,N,2,L  = 0
 c5524: - fire_C,N,N,C,N,2,P - fire_C,N,N,C,N,2,D - fire_C,N,N,C,N,2,W
        - fire_C,N,N,C,N,2,C - fire_C,N,N,C,N,2,I - fire_C,N,N,C,N,2,A
        - fire_C,N,N,C,N,2,L  = 0
 c5525: fire_C,N,N,C,N,3,P  = 0
 c5526: fire_C,N,N,C,N,3,D  = 0
 c5527: fire_C,N,N,C,N,3,W  = 0
 c5528: fire_C,N,N,C,N,3,C  = 0
 c5529: fire_C,N,N,C,N,3,I  = 0
 c5530: fire_C,N,N,C,N,3,A  = 0
 c5531: fire_C,N,N,C,N,3,L  = 0
 c5532: - fire_C,N,N,C,N,3,P - fire_C,N,N,C,N,3,D - fire_C,N,N,C,N,3,W
        - fire_C,N,N,C,N,3,C - fire_C,N,N,C,N,3,I - fire_C,N,N,C,N,3,A
        - fire_C,N,N,C,N,3,L  = 0
 c5533: fire_C,N,N,C,N,4,P  = 0
 c5534: fire_C,N,N,C,N,4,D  = 0
 c5535: fire_C,N,N,C,N,4,W  = 0
 c5536: fire_C,N,N,C,N,4,C  = 0
 c5537: fire_C,N,N,C,N,4,I  = 0
 c5538: fire_C,N,N,C,N,4,A  = 0
 c5539: fire_C,N,N,C,N,4,L  = 0
 c5540: - fire_C,N,N,C,N,4,P - fire_C,N,N,C,N,4,D - fire_C,N,N,C,N,4,W
        - fire_C,N,N,C,N,4,C - fire_C,N,N,C,N,4,I - fire_C,N,N,C,N,4,A
        - fire_C,N,N,C,N,4,L  = 0
 c5541: fire_C,N,N,C,N,5,P  = 0
 c5542: fire_C,N,N,C,N,5,D  = 0
 c5543: fire_C,N,N,C,N,5,W  = 0
 c5544: fire_C,N,N,C,N,5,C  = 0
 c5545: fire_C,N,N,C,N,5,I  = 0
 c5546: fire_C,N,N,C,N,5,A  = 0
 c5547: fire_C,N,N,C,N,5,L  = 0
 c5548: - fire_C,N,N,C,N,5,P - fire_C,N,N,C,N,5,D - fire_C,N,N,C,N,5,W
        - fire_C,N,N,C,N,5,C - fire_C,N,N,C,N,5,I - fire_C,N,N,C,N,5,A
        - fire_C,N,N,C,N,5,L  = 0
 c5549: 0.9 fire_C,N,N,R,P,1,P - 0.1 fire_C,N,N,R,P,1,D
        - 0.1 fire_C,N,N,R,P,1,W - 0.1 fire_C,N,N,R,P,1,C
        - 0.1 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        - 0.1 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5550: - 0.1 fire_C,N,N,R,P,1,P + 0.9 fire_C,N,N,R,P,1,D
        - 0.1 fire_C,N,N,R,P,1,W - 0.1 fire_C,N,N,R,P,1,C
        - 0.1 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        - 0.1 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5551: - 0.1 fire_C,N,N,R,P,1,P - 0.1 fire_C,N,N,R,P,1,D
        + 0.9 fire_C,N,N,R,P,1,W - 0.1 fire_C,N,N,R,P,1,C
        - 0.1 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        - 0.1 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5552: - 0.1 fire_C,N,N,R,P,1,P - 0.1 fire_C,N,N,R,P,1,D
        - 0.1 fire_C,N,N,R,P,1,W + 0.9 fire_C,N,N,R,P,1,C
        - 0.1 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        - 0.1 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5553: - 0.1 fire_C,N,N,R,P,1,P - 0.1 fire_C,N,N,R,P,1,D
        - 0.1 fire_C,N,N,R,P,1,W - 0.1 fire_C,N,N,R,P,1,C
        + 0.9 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        - 0.1 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5554: - 0.2 fire_C,N,N,R,P,1,P - 0.2 fire_C,N,N,R,P,1,D
        - 0.2 fire_C,N,N,R,P,1,W - 0.2 fire_C,N,N,R,P,1,C
        - 0.2 fire_C,N,N,R,P,1,I + 0.8 fire_C,N,N,R,P,1,A
        - 0.2 fire_C,N,N,R,P,1,L - 0.2 fire_C,N,N,R,P,1,N  = 0
 c5555: - 0.1 fire_C,N,N,R,P,1,P - 0.1 fire_C,N,N,R,P,1,D
        - 0.1 fire_C,N,N,R,P,1,W - 0.1 fire_C,N,N,R,P,1,C
        - 0.1 fire_C,N,N,R,P,1,I - 0.1 fire_C,N,N,R,P,1,A
        + 0.9 fire_C,N,N,R,P,1,L - 0.1 fire_C,N,N,R,P,1,N  = 0
 c5556: - 0.2 fire_C,N,N,R,P,1,P - 0.2 fire_C,N,N,R,P,1,D
        - 0.2 fire_C,N,N,R,P,1,W - 0.2 fire_C,N,N,R,P,1,C
        - 0.2 fire_C,N,N,R,P,1,I - 0.2 fire_C,N,N,R,P,1,A
        - 0.2 fire_C,N,N,R,P,1,L + 0.8 fire_C,N,N,R,P,1,N  = 0
 c5557: 0.9 fire_C,N,N,R,P,2,P - 0.1 fire_C,N,N,R,P,2,D
        - 0.1 fire_C,N,N,R,P,2,W - 0.1 fire_C,N,N,R,P,2,C
        - 0.1 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        - 0.1 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5558: - 0.1 fire_C,N,N,R,P,2,P + 0.9 fire_C,N,N,R,P,2,D
        - 0.1 fire_C,N,N,R,P,2,W - 0.1 fire_C,N,N,R,P,2,C
        - 0.1 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        - 0.1 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5559: - 0.1 fire_C,N,N,R,P,2,P - 0.1 fire_C,N,N,R,P,2,D
        + 0.9 fire_C,N,N,R,P,2,W - 0.1 fire_C,N,N,R,P,2,C
        - 0.1 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        - 0.1 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5560: - 0.1 fire_C,N,N,R,P,2,P - 0.1 fire_C,N,N,R,P,2,D
        - 0.1 fire_C,N,N,R,P,2,W + 0.9 fire_C,N,N,R,P,2,C
        - 0.1 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        - 0.1 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5561: - 0.1 fire_C,N,N,R,P,2,P - 0.1 fire_C,N,N,R,P,2,D
        - 0.1 fire_C,N,N,R,P,2,W - 0.1 fire_C,N,N,R,P,2,C
        + 0.9 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        - 0.1 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5562: - 0.2 fire_C,N,N,R,P,2,P - 0.2 fire_C,N,N,R,P,2,D
        - 0.2 fire_C,N,N,R,P,2,W - 0.2 fire_C,N,N,R,P,2,C
        - 0.2 fire_C,N,N,R,P,2,I + 0.8 fire_C,N,N,R,P,2,A
        - 0.2 fire_C,N,N,R,P,2,L - 0.2 fire_C,N,N,R,P,2,N  = 0
 c5563: - 0.1 fire_C,N,N,R,P,2,P - 0.1 fire_C,N,N,R,P,2,D
        - 0.1 fire_C,N,N,R,P,2,W - 0.1 fire_C,N,N,R,P,2,C
        - 0.1 fire_C,N,N,R,P,2,I - 0.1 fire_C,N,N,R,P,2,A
        + 0.9 fire_C,N,N,R,P,2,L - 0.1 fire_C,N,N,R,P,2,N  = 0
 c5564: - 0.2 fire_C,N,N,R,P,2,P - 0.2 fire_C,N,N,R,P,2,D
        - 0.2 fire_C,N,N,R,P,2,W - 0.2 fire_C,N,N,R,P,2,C
        - 0.2 fire_C,N,N,R,P,2,I - 0.2 fire_C,N,N,R,P,2,A
        - 0.2 fire_C,N,N,R,P,2,L + 0.8 fire_C,N,N,R,P,2,N  = 0
 c5565: 0.9 fire_C,N,N,R,P,3,P - 0.1 fire_C,N,N,R,P,3,D
        - 0.1 fire_C,N,N,R,P,3,W - 0.1 fire_C,N,N,R,P,3,C
        - 0.1 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        - 0.1 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5566: - 0.1 fire_C,N,N,R,P,3,P + 0.9 fire_C,N,N,R,P,3,D
        - 0.1 fire_C,N,N,R,P,3,W - 0.1 fire_C,N,N,R,P,3,C
        - 0.1 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        - 0.1 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5567: - 0.1 fire_C,N,N,R,P,3,P - 0.1 fire_C,N,N,R,P,3,D
        + 0.9 fire_C,N,N,R,P,3,W - 0.1 fire_C,N,N,R,P,3,C
        - 0.1 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        - 0.1 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5568: - 0.1 fire_C,N,N,R,P,3,P - 0.1 fire_C,N,N,R,P,3,D
        - 0.1 fire_C,N,N,R,P,3,W + 0.9 fire_C,N,N,R,P,3,C
        - 0.1 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        - 0.1 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5569: - 0.1 fire_C,N,N,R,P,3,P - 0.1 fire_C,N,N,R,P,3,D
        - 0.1 fire_C,N,N,R,P,3,W - 0.1 fire_C,N,N,R,P,3,C
        + 0.9 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        - 0.1 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5570: - 0.2 fire_C,N,N,R,P,3,P - 0.2 fire_C,N,N,R,P,3,D
        - 0.2 fire_C,N,N,R,P,3,W - 0.2 fire_C,N,N,R,P,3,C
        - 0.2 fire_C,N,N,R,P,3,I + 0.8 fire_C,N,N,R,P,3,A
        - 0.2 fire_C,N,N,R,P,3,L - 0.2 fire_C,N,N,R,P,3,N  = 0
 c5571: - 0.1 fire_C,N,N,R,P,3,P - 0.1 fire_C,N,N,R,P,3,D
        - 0.1 fire_C,N,N,R,P,3,W - 0.1 fire_C,N,N,R,P,3,C
        - 0.1 fire_C,N,N,R,P,3,I - 0.1 fire_C,N,N,R,P,3,A
        + 0.9 fire_C,N,N,R,P,3,L - 0.1 fire_C,N,N,R,P,3,N  = 0
 c5572: - 0.2 fire_C,N,N,R,P,3,P - 0.2 fire_C,N,N,R,P,3,D
        - 0.2 fire_C,N,N,R,P,3,W - 0.2 fire_C,N,N,R,P,3,C
        - 0.2 fire_C,N,N,R,P,3,I - 0.2 fire_C,N,N,R,P,3,A
        - 0.2 fire_C,N,N,R,P,3,L + 0.8 fire_C,N,N,R,P,3,N  = 0
 c5573: 0.9 fire_C,N,N,R,P,4,P - 0.1 fire_C,N,N,R,P,4,D
        - 0.1 fire_C,N,N,R,P,4,W - 0.1 fire_C,N,N,R,P,4,C
        - 0.1 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        - 0.1 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5574: - 0.1 fire_C,N,N,R,P,4,P + 0.9 fire_C,N,N,R,P,4,D
        - 0.1 fire_C,N,N,R,P,4,W - 0.1 fire_C,N,N,R,P,4,C
        - 0.1 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        - 0.1 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5575: - 0.1 fire_C,N,N,R,P,4,P - 0.1 fire_C,N,N,R,P,4,D
        + 0.9 fire_C,N,N,R,P,4,W - 0.1 fire_C,N,N,R,P,4,C
        - 0.1 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        - 0.1 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5576: - 0.1 fire_C,N,N,R,P,4,P - 0.1 fire_C,N,N,R,P,4,D
        - 0.1 fire_C,N,N,R,P,4,W + 0.9 fire_C,N,N,R,P,4,C
        - 0.1 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        - 0.1 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5577: - 0.1 fire_C,N,N,R,P,4,P - 0.1 fire_C,N,N,R,P,4,D
        - 0.1 fire_C,N,N,R,P,4,W - 0.1 fire_C,N,N,R,P,4,C
        + 0.9 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        - 0.1 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5578: - 0.2 fire_C,N,N,R,P,4,P - 0.2 fire_C,N,N,R,P,4,D
        - 0.2 fire_C,N,N,R,P,4,W - 0.2 fire_C,N,N,R,P,4,C
        - 0.2 fire_C,N,N,R,P,4,I + 0.8 fire_C,N,N,R,P,4,A
        - 0.2 fire_C,N,N,R,P,4,L - 0.2 fire_C,N,N,R,P,4,N  = 0
 c5579: - 0.1 fire_C,N,N,R,P,4,P - 0.1 fire_C,N,N,R,P,4,D
        - 0.1 fire_C,N,N,R,P,4,W - 0.1 fire_C,N,N,R,P,4,C
        - 0.1 fire_C,N,N,R,P,4,I - 0.1 fire_C,N,N,R,P,4,A
        + 0.9 fire_C,N,N,R,P,4,L - 0.1 fire_C,N,N,R,P,4,N  = 0
 c5580: - 0.2 fire_C,N,N,R,P,4,P - 0.2 fire_C,N,N,R,P,4,D
        - 0.2 fire_C,N,N,R,P,4,W - 0.2 fire_C,N,N,R,P,4,C
        - 0.2 fire_C,N,N,R,P,4,I - 0.2 fire_C,N,N,R,P,4,A
        - 0.2 fire_C,N,N,R,P,4,L + 0.8 fire_C,N,N,R,P,4,N  = 0
 c5581: 0.9 fire_C,N,N,R,P,5,P - 0.1 fire_C,N,N,R,P,5,D
        - 0.1 fire_C,N,N,R,P,5,W - 0.1 fire_C,N,N,R,P,5,C
        - 0.1 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        - 0.1 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5582: - 0.1 fire_C,N,N,R,P,5,P + 0.9 fire_C,N,N,R,P,5,D
        - 0.1 fire_C,N,N,R,P,5,W - 0.1 fire_C,N,N,R,P,5,C
        - 0.1 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        - 0.1 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5583: - 0.1 fire_C,N,N,R,P,5,P - 0.1 fire_C,N,N,R,P,5,D
        + 0.9 fire_C,N,N,R,P,5,W - 0.1 fire_C,N,N,R,P,5,C
        - 0.1 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        - 0.1 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5584: - 0.1 fire_C,N,N,R,P,5,P - 0.1 fire_C,N,N,R,P,5,D
        - 0.1 fire_C,N,N,R,P,5,W + 0.9 fire_C,N,N,R,P,5,C
        - 0.1 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        - 0.1 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5585: - 0.1 fire_C,N,N,R,P,5,P - 0.1 fire_C,N,N,R,P,5,D
        - 0.1 fire_C,N,N,R,P,5,W - 0.1 fire_C,N,N,R,P,5,C
        + 0.9 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        - 0.1 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5586: - 0.2 fire_C,N,N,R,P,5,P - 0.2 fire_C,N,N,R,P,5,D
        - 0.2 fire_C,N,N,R,P,5,W - 0.2 fire_C,N,N,R,P,5,C
        - 0.2 fire_C,N,N,R,P,5,I + 0.8 fire_C,N,N,R,P,5,A
        - 0.2 fire_C,N,N,R,P,5,L - 0.2 fire_C,N,N,R,P,5,N  = 0
 c5587: - 0.1 fire_C,N,N,R,P,5,P - 0.1 fire_C,N,N,R,P,5,D
        - 0.1 fire_C,N,N,R,P,5,W - 0.1 fire_C,N,N,R,P,5,C
        - 0.1 fire_C,N,N,R,P,5,I - 0.1 fire_C,N,N,R,P,5,A
        + 0.9 fire_C,N,N,R,P,5,L - 0.1 fire_C,N,N,R,P,5,N  = 0
 c5588: - 0.2 fire_C,N,N,R,P,5,P - 0.2 fire_C,N,N,R,P,5,D
        - 0.2 fire_C,N,N,R,P,5,W - 0.2 fire_C,N,N,R,P,5,C
        - 0.2 fire_C,N,N,R,P,5,I - 0.2 fire_C,N,N,R,P,5,A
        - 0.2 fire_C,N,N,R,P,5,L + 0.8 fire_C,N,N,R,P,5,N  = 0
 c5589: 0.5 fire_C,N,N,R,D,1,P - 0.5 fire_C,N,N,R,D,1,D
        - 0.5 fire_C,N,N,R,D,1,W - 0.5 fire_C,N,N,R,D,1,C
        - 0.5 fire_C,N,N,R,D,1,I - 0.5 fire_C,N,N,R,D,1,A
        - 0.5 fire_C,N,N,R,D,1,L - 0.5 fire_C,N,N,R,D,1,N  = 0
 c5590: - 0.5 fire_C,N,N,R,D,1,P + 0.5 fire_C,N,N,R,D,1,D
        - 0.5 fire_C,N,N,R,D,1,W - 0.5 fire_C,N,N,R,D,1,C
        - 0.5 fire_C,N,N,R,D,1,I - 0.5 fire_C,N,N,R,D,1,A
        - 0.5 fire_C,N,N,R,D,1,L - 0.5 fire_C,N,N,R,D,1,N  = 0
 c5591: fire_C,N,N,R,D,1,W  = 0
 c5592: fire_C,N,N,R,D,1,C  = 0
 c5593: fire_C,N,N,R,D,1,I  = 0
 c5594: fire_C,N,N,R,D,1,A  = 0
 c5595: fire_C,N,N,R,D,1,L  = 0
 c5596: fire_C,N,N,R,D,1,N  = 0
 c5597: 0.5 fire_C,N,N,R,D,2,P - 0.5 fire_C,N,N,R,D,2,D
        - 0.5 fire_C,N,N,R,D,2,W - 0.5 fire_C,N,N,R,D,2,C
        - 0.5 fire_C,N,N,R,D,2,I - 0.5 fire_C,N,N,R,D,2,A
        - 0.5 fire_C,N,N,R,D,2,L - 0.5 fire_C,N,N,R,D,2,N  = 0
 c5598: - 0.5 fire_C,N,N,R,D,2,P + 0.5 fire_C,N,N,R,D,2,D
        - 0.5 fire_C,N,N,R,D,2,W - 0.5 fire_C,N,N,R,D,2,C
        - 0.5 fire_C,N,N,R,D,2,I - 0.5 fire_C,N,N,R,D,2,A
        - 0.5 fire_C,N,N,R,D,2,L - 0.5 fire_C,N,N,R,D,2,N  = 0
 c5599: fire_C,N,N,R,D,2,W  = 0
 c5600: fire_C,N,N,R,D,2,C  = 0
 c5601: fire_C,N,N,R,D,2,I  = 0
 c5602: fire_C,N,N,R,D,2,A  = 0
 c5603: fire_C,N,N,R,D,2,L  = 0
 c5604: fire_C,N,N,R,D,2,N  = 0
 c5605: 0.5 fire_C,N,N,R,D,3,P - 0.5 fire_C,N,N,R,D,3,D
        - 0.5 fire_C,N,N,R,D,3,W - 0.5 fire_C,N,N,R,D,3,C
        - 0.5 fire_C,N,N,R,D,3,I - 0.5 fire_C,N,N,R,D,3,A
        - 0.5 fire_C,N,N,R,D,3,L - 0.5 fire_C,N,N,R,D,3,N  = 0
 c5606: - 0.5 fire_C,N,N,R,D,3,P + 0.5 fire_C,N,N,R,D,3,D
        - 0.5 fire_C,N,N,R,D,3,W - 0.5 fire_C,N,N,R,D,3,C
        - 0.5 fire_C,N,N,R,D,3,I - 0.5 fire_C,N,N,R,D,3,A
        - 0.5 fire_C,N,N,R,D,3,L - 0.5 fire_C,N,N,R,D,3,N  = 0
 c5607: fire_C,N,N,R,D,3,W  = 0
 c5608: fire_C,N,N,R,D,3,C  = 0
 c5609: fire_C,N,N,R,D,3,I  = 0
 c5610: fire_C,N,N,R,D,3,A  = 0
 c5611: fire_C,N,N,R,D,3,L  = 0
 c5612: fire_C,N,N,R,D,3,N  = 0
 c5613: 0.5 fire_C,N,N,R,D,4,P - 0.5 fire_C,N,N,R,D,4,D
        - 0.5 fire_C,N,N,R,D,4,W - 0.5 fire_C,N,N,R,D,4,C
        - 0.5 fire_C,N,N,R,D,4,I - 0.5 fire_C,N,N,R,D,4,A
        - 0.5 fire_C,N,N,R,D,4,L - 0.5 fire_C,N,N,R,D,4,N  = 0
 c5614: - 0.5 fire_C,N,N,R,D,4,P + 0.5 fire_C,N,N,R,D,4,D
        - 0.5 fire_C,N,N,R,D,4,W - 0.5 fire_C,N,N,R,D,4,C
        - 0.5 fire_C,N,N,R,D,4,I - 0.5 fire_C,N,N,R,D,4,A
        - 0.5 fire_C,N,N,R,D,4,L - 0.5 fire_C,N,N,R,D,4,N  = 0
 c5615: fire_C,N,N,R,D,4,W  = 0
 c5616: fire_C,N,N,R,D,4,C  = 0
 c5617: fire_C,N,N,R,D,4,I  = 0
 c5618: fire_C,N,N,R,D,4,A  = 0
 c5619: fire_C,N,N,R,D,4,L  = 0
 c5620: fire_C,N,N,R,D,4,N  = 0
 c5621: 0.5 fire_C,N,N,R,D,5,P - 0.5 fire_C,N,N,R,D,5,D
        - 0.5 fire_C,N,N,R,D,5,W - 0.5 fire_C,N,N,R,D,5,C
        - 0.5 fire_C,N,N,R,D,5,I - 0.5 fire_C,N,N,R,D,5,A
        - 0.5 fire_C,N,N,R,D,5,L - 0.5 fire_C,N,N,R,D,5,N  = 0
 c5622: - 0.5 fire_C,N,N,R,D,5,P + 0.5 fire_C,N,N,R,D,5,D
        - 0.5 fire_C,N,N,R,D,5,W - 0.5 fire_C,N,N,R,D,5,C
        - 0.5 fire_C,N,N,R,D,5,I - 0.5 fire_C,N,N,R,D,5,A
        - 0.5 fire_C,N,N,R,D,5,L - 0.5 fire_C,N,N,R,D,5,N  = 0
 c5623: fire_C,N,N,R,D,5,W  = 0
 c5624: fire_C,N,N,R,D,5,C  = 0
 c5625: fire_C,N,N,R,D,5,I  = 0
 c5626: fire_C,N,N,R,D,5,A  = 0
 c5627: fire_C,N,N,R,D,5,L  = 0
 c5628: fire_C,N,N,R,D,5,N  = 0
 c5629: fire_C,N,N,R,W,1,P  = 0
 c5630: fire_C,N,N,R,W,1,D  = 0
 c5631: - fire_C,N,N,R,W,1,P - fire_C,N,N,R,W,1,D - fire_C,N,N,R,W,1,C
        - fire_C,N,N,R,W,1,I - fire_C,N,N,R,W,1,A - fire_C,N,N,R,W,1,L
        - fire_C,N,N,R,W,1,N  = 0
 c5632: fire_C,N,N,R,W,1,C  = 0
 c5633: fire_C,N,N,R,W,1,I  = 0
 c5634: fire_C,N,N,R,W,1,A  = 0
 c5635: fire_C,N,N,R,W,1,L  = 0
 c5636: fire_C,N,N,R,W,1,N  = 0
 c5637: fire_C,N,N,R,W,2,P  = 0
 c5638: fire_C,N,N,R,W,2,D  = 0
 c5639: - fire_C,N,N,R,W,2,P - fire_C,N,N,R,W,2,D - fire_C,N,N,R,W,2,C
        - fire_C,N,N,R,W,2,I - fire_C,N,N,R,W,2,A - fire_C,N,N,R,W,2,L
        - fire_C,N,N,R,W,2,N  = 0
 c5640: fire_C,N,N,R,W,2,C  = 0
 c5641: fire_C,N,N,R,W,2,I  = 0
 c5642: fire_C,N,N,R,W,2,A  = 0
 c5643: fire_C,N,N,R,W,2,L  = 0
 c5644: fire_C,N,N,R,W,2,N  = 0
 c5645: fire_C,N,N,R,W,3,P  = 0
 c5646: fire_C,N,N,R,W,3,D  = 0
 c5647: - fire_C,N,N,R,W,3,P - fire_C,N,N,R,W,3,D - fire_C,N,N,R,W,3,C
        - fire_C,N,N,R,W,3,I - fire_C,N,N,R,W,3,A - fire_C,N,N,R,W,3,L
        - fire_C,N,N,R,W,3,N  = 0
 c5648: fire_C,N,N,R,W,3,C  = 0
 c5649: fire_C,N,N,R,W,3,I  = 0
 c5650: fire_C,N,N,R,W,3,A  = 0
 c5651: fire_C,N,N,R,W,3,L  = 0
 c5652: fire_C,N,N,R,W,3,N  = 0
 c5653: fire_C,N,N,R,W,4,P  = 0
 c5654: fire_C,N,N,R,W,4,D  = 0
 c5655: - fire_C,N,N,R,W,4,P - fire_C,N,N,R,W,4,D - fire_C,N,N,R,W,4,C
        - fire_C,N,N,R,W,4,I - fire_C,N,N,R,W,4,A - fire_C,N,N,R,W,4,L
        - fire_C,N,N,R,W,4,N  = 0
 c5656: fire_C,N,N,R,W,4,C  = 0
 c5657: fire_C,N,N,R,W,4,I  = 0
 c5658: fire_C,N,N,R,W,4,A  = 0
 c5659: fire_C,N,N,R,W,4,L  = 0
 c5660: fire_C,N,N,R,W,4,N  = 0
 c5661: fire_C,N,N,R,W,5,P  = 0
 c5662: fire_C,N,N,R,W,5,D  = 0
 c5663: - fire_C,N,N,R,W,5,P - fire_C,N,N,R,W,5,D - fire_C,N,N,R,W,5,C
        - fire_C,N,N,R,W,5,I - fire_C,N,N,R,W,5,A - fire_C,N,N,R,W,5,L
        - fire_C,N,N,R,W,5,N  = 0
 c5664: fire_C,N,N,R,W,5,C  = 0
 c5665: fire_C,N,N,R,W,5,I  = 0
 c5666: fire_C,N,N,R,W,5,A  = 0
 c5667: fire_C,N,N,R,W,5,L  = 0
 c5668: fire_C,N,N,R,W,5,N  = 0
 c5669: fire_C,N,N,R,C,1,P  = 0
 c5670: fire_C,N,N,R,C,1,D  = 0
 c5671: fire_C,N,N,R,C,1,W  = 0
 c5672: - fire_C,N,N,R,C,1,P - fire_C,N,N,R,C,1,D - fire_C,N,N,R,C,1,W
        - fire_C,N,N,R,C,1,I - fire_C,N,N,R,C,1,A - fire_C,N,N,R,C,1,L
        - fire_C,N,N,R,C,1,N  = 0
 c5673: fire_C,N,N,R,C,1,I  = 0
 c5674: fire_C,N,N,R,C,1,A  = 0
 c5675: fire_C,N,N,R,C,1,L  = 0
 c5676: fire_C,N,N,R,C,1,N  = 0
 c5677: fire_C,N,N,R,C,2,P  = 0
 c5678: fire_C,N,N,R,C,2,D  = 0
 c5679: fire_C,N,N,R,C,2,W  = 0
 c5680: - fire_C,N,N,R,C,2,P - fire_C,N,N,R,C,2,D - fire_C,N,N,R,C,2,W
        - fire_C,N,N,R,C,2,I - fire_C,N,N,R,C,2,A - fire_C,N,N,R,C,2,L
        - fire_C,N,N,R,C,2,N  = 0
 c5681: fire_C,N,N,R,C,2,I  = 0
 c5682: fire_C,N,N,R,C,2,A  = 0
 c5683: fire_C,N,N,R,C,2,L  = 0
 c5684: fire_C,N,N,R,C,2,N  = 0
 c5685: fire_C,N,N,R,C,3,P  = 0
 c5686: fire_C,N,N,R,C,3,D  = 0
 c5687: fire_C,N,N,R,C,3,W  = 0
 c5688: - fire_C,N,N,R,C,3,P - fire_C,N,N,R,C,3,D - fire_C,N,N,R,C,3,W
        - fire_C,N,N,R,C,3,I - fire_C,N,N,R,C,3,A - fire_C,N,N,R,C,3,L
        - fire_C,N,N,R,C,3,N  = 0
 c5689: fire_C,N,N,R,C,3,I  = 0
 c5690: fire_C,N,N,R,C,3,A  = 0
 c5691: fire_C,N,N,R,C,3,L  = 0
 c5692: fire_C,N,N,R,C,3,N  = 0
 c5693: fire_C,N,N,R,C,4,P  = 0
 c5694: fire_C,N,N,R,C,4,D  = 0
 c5695: fire_C,N,N,R,C,4,W  = 0
 c5696: - fire_C,N,N,R,C,4,P - fire_C,N,N,R,C,4,D - fire_C,N,N,R,C,4,W
        - fire_C,N,N,R,C,4,I - fire_C,N,N,R,C,4,A - fire_C,N,N,R,C,4,L
        - fire_C,N,N,R,C,4,N  = 0
 c5697: fire_C,N,N,R,C,4,I  = 0
 c5698: fire_C,N,N,R,C,4,A  = 0
 c5699: fire_C,N,N,R,C,4,L  = 0
 c5700: fire_C,N,N,R,C,4,N  = 0
 c5701: fire_C,N,N,R,C,5,P  = 0
 c5702: fire_C,N,N,R,C,5,D  = 0
 c5703: fire_C,N,N,R,C,5,W  = 0
 c5704: - fire_C,N,N,R,C,5,P - fire_C,N,N,R,C,5,D - fire_C,N,N,R,C,5,W
        - fire_C,N,N,R,C,5,I - fire_C,N,N,R,C,5,A - fire_C,N,N,R,C,5,L
        - fire_C,N,N,R,C,5,N  = 0
 c5705: fire_C,N,N,R,C,5,I  = 0
 c5706: fire_C,N,N,R,C,5,A  = 0
 c5707: fire_C,N,N,R,C,5,L  = 0
 c5708: fire_C,N,N,R,C,5,N  = 0
 c5709: fire_C,N,N,R,I,1,P  = 0
 c5710: fire_C,N,N,R,I,1,D  = 0
 c5711: fire_C,N,N,R,I,1,W  = 0
 c5712: fire_C,N,N,R,I,1,C  = 0
 c5713: - fire_C,N,N,R,I,1,P - fire_C,N,N,R,I,1,D - fire_C,N,N,R,I,1,W
        - fire_C,N,N,R,I,1,C - fire_C,N,N,R,I,1,A - fire_C,N,N,R,I,1,L
        - fire_C,N,N,R,I,1,N  = 0
 c5714: fire_C,N,N,R,I,1,A  = 0
 c5715: fire_C,N,N,R,I,1,L  = 0
 c5716: fire_C,N,N,R,I,1,N  = 0
 c5717: fire_C,N,N,R,I,2,P  = 0
 c5718: fire_C,N,N,R,I,2,D  = 0
 c5719: fire_C,N,N,R,I,2,W  = 0
 c5720: fire_C,N,N,R,I,2,C  = 0
 c5721: - fire_C,N,N,R,I,2,P - fire_C,N,N,R,I,2,D - fire_C,N,N,R,I,2,W
        - fire_C,N,N,R,I,2,C - fire_C,N,N,R,I,2,A - fire_C,N,N,R,I,2,L
        - fire_C,N,N,R,I,2,N  = 0
 c5722: fire_C,N,N,R,I,2,A  = 0
 c5723: fire_C,N,N,R,I,2,L  = 0
 c5724: fire_C,N,N,R,I,2,N  = 0
 c5725: fire_C,N,N,R,I,3,P  = 0
 c5726: fire_C,N,N,R,I,3,D  = 0
 c5727: fire_C,N,N,R,I,3,W  = 0
 c5728: fire_C,N,N,R,I,3,C  = 0
 c5729: - fire_C,N,N,R,I,3,P - fire_C,N,N,R,I,3,D - fire_C,N,N,R,I,3,W
        - fire_C,N,N,R,I,3,C - fire_C,N,N,R,I,3,A - fire_C,N,N,R,I,3,L
        - fire_C,N,N,R,I,3,N  = 0
 c5730: fire_C,N,N,R,I,3,A  = 0
 c5731: fire_C,N,N,R,I,3,L  = 0
 c5732: fire_C,N,N,R,I,3,N  = 0
 c5733: fire_C,N,N,R,I,4,P  = 0
 c5734: fire_C,N,N,R,I,4,D  = 0
 c5735: fire_C,N,N,R,I,4,W  = 0
 c5736: fire_C,N,N,R,I,4,C  = 0
 c5737: - fire_C,N,N,R,I,4,P - fire_C,N,N,R,I,4,D - fire_C,N,N,R,I,4,W
        - fire_C,N,N,R,I,4,C - fire_C,N,N,R,I,4,A - fire_C,N,N,R,I,4,L
        - fire_C,N,N,R,I,4,N  = 0
 c5738: fire_C,N,N,R,I,4,A  = 0
 c5739: fire_C,N,N,R,I,4,L  = 0
 c5740: fire_C,N,N,R,I,4,N  = 0
 c5741: fire_C,N,N,R,I,5,P  = 0
 c5742: fire_C,N,N,R,I,5,D  = 0
 c5743: fire_C,N,N,R,I,5,W  = 0
 c5744: fire_C,N,N,R,I,5,C  = 0
 c5745: - fire_C,N,N,R,I,5,P - fire_C,N,N,R,I,5,D - fire_C,N,N,R,I,5,W
        - fire_C,N,N,R,I,5,C - fire_C,N,N,R,I,5,A - fire_C,N,N,R,I,5,L
        - fire_C,N,N,R,I,5,N  = 0
 c5746: fire_C,N,N,R,I,5,A  = 0
 c5747: fire_C,N,N,R,I,5,L  = 0
 c5748: fire_C,N,N,R,I,5,N  = 0
 c5749: fire_C,N,N,R,A,1,P  = 0
 c5750: fire_C,N,N,R,A,1,D  = 0
 c5751: fire_C,N,N,R,A,1,W  = 0
 c5752: fire_C,N,N,R,A,1,C  = 0
 c5753: fire_C,N,N,R,A,1,I  = 0
 c5754: - fire_C,N,N,R,A,1,P - fire_C,N,N,R,A,1,D - fire_C,N,N,R,A,1,W
        - fire_C,N,N,R,A,1,C - fire_C,N,N,R,A,1,I - fire_C,N,N,R,A,1,L
        - fire_C,N,N,R,A,1,N  = 0
 c5755: fire_C,N,N,R,A,1,L  = 0
 c5756: fire_C,N,N,R,A,1,N  = 0
 c5757: fire_C,N,N,R,A,2,P  = 0
 c5758: fire_C,N,N,R,A,2,D  = 0
 c5759: fire_C,N,N,R,A,2,W  = 0
 c5760: fire_C,N,N,R,A,2,C  = 0
 c5761: fire_C,N,N,R,A,2,I  = 0
 c5762: - fire_C,N,N,R,A,2,P - fire_C,N,N,R,A,2,D - fire_C,N,N,R,A,2,W
        - fire_C,N,N,R,A,2,C - fire_C,N,N,R,A,2,I - fire_C,N,N,R,A,2,L
        - fire_C,N,N,R,A,2,N  = 0
 c5763: fire_C,N,N,R,A,2,L  = 0
 c5764: fire_C,N,N,R,A,2,N  = 0
 c5765: fire_C,N,N,R,A,3,P  = 0
 c5766: fire_C,N,N,R,A,3,D  = 0
 c5767: fire_C,N,N,R,A,3,W  = 0
 c5768: fire_C,N,N,R,A,3,C  = 0
 c5769: fire_C,N,N,R,A,3,I  = 0
 c5770: - fire_C,N,N,R,A,3,P - fire_C,N,N,R,A,3,D - fire_C,N,N,R,A,3,W
        - fire_C,N,N,R,A,3,C - fire_C,N,N,R,A,3,I - fire_C,N,N,R,A,3,L
        - fire_C,N,N,R,A,3,N  = 0
 c5771: fire_C,N,N,R,A,3,L  = 0
 c5772: fire_C,N,N,R,A,3,N  = 0
 c5773: fire_C,N,N,R,A,4,P  = 0
 c5774: fire_C,N,N,R,A,4,D  = 0
 c5775: fire_C,N,N,R,A,4,W  = 0
 c5776: fire_C,N,N,R,A,4,C  = 0
 c5777: fire_C,N,N,R,A,4,I  = 0
 c5778: - fire_C,N,N,R,A,4,P - fire_C,N,N,R,A,4,D - fire_C,N,N,R,A,4,W
        - fire_C,N,N,R,A,4,C - fire_C,N,N,R,A,4,I - fire_C,N,N,R,A,4,L
        - fire_C,N,N,R,A,4,N  = 0
 c5779: fire_C,N,N,R,A,4,L  = 0
 c5780: fire_C,N,N,R,A,4,N  = 0
 c5781: fire_C,N,N,R,A,5,P  = 0
 c5782: fire_C,N,N,R,A,5,D  = 0
 c5783: fire_C,N,N,R,A,5,W  = 0
 c5784: fire_C,N,N,R,A,5,C  = 0
 c5785: fire_C,N,N,R,A,5,I  = 0
 c5786: - fire_C,N,N,R,A,5,P - fire_C,N,N,R,A,5,D - fire_C,N,N,R,A,5,W
        - fire_C,N,N,R,A,5,C - fire_C,N,N,R,A,5,I - fire_C,N,N,R,A,5,L
        - fire_C,N,N,R,A,5,N  = 0
 c5787: fire_C,N,N,R,A,5,L  = 0
 c5788: fire_C,N,N,R,A,5,N  = 0
 c5789: fire_C,N,N,R,L,1,P  = 0
 c5790: fire_C,N,N,R,L,1,D  = 0
 c5791: fire_C,N,N,R,L,1,W  = 0
 c5792: fire_C,N,N,R,L,1,C  = 0
 c5793: fire_C,N,N,R,L,1,I  = 0
 c5794: fire_C,N,N,R,L,1,A  = 0
 c5795: - fire_C,N,N,R,L,1,P - fire_C,N,N,R,L,1,D - fire_C,N,N,R,L,1,W
        - fire_C,N,N,R,L,1,C - fire_C,N,N,R,L,1,I - fire_C,N,N,R,L,1,A
        - fire_C,N,N,R,L,1,N  = 0
 c5796: fire_C,N,N,R,L,1,N  = 0
 c5797: fire_C,N,N,R,L,2,P  = 0
 c5798: fire_C,N,N,R,L,2,D  = 0
 c5799: fire_C,N,N,R,L,2,W  = 0
 c5800: fire_C,N,N,R,L,2,C  = 0
 c5801: fire_C,N,N,R,L,2,I  = 0
 c5802: fire_C,N,N,R,L,2,A  = 0
 c5803: - fire_C,N,N,R,L,2,P - fire_C,N,N,R,L,2,D - fire_C,N,N,R,L,2,W
        - fire_C,N,N,R,L,2,C - fire_C,N,N,R,L,2,I - fire_C,N,N,R,L,2,A
        - fire_C,N,N,R,L,2,N  = 0
 c5804: fire_C,N,N,R,L,2,N  = 0
 c5805: fire_C,N,N,R,L,3,P  = 0
 c5806: fire_C,N,N,R,L,3,D  = 0
 c5807: fire_C,N,N,R,L,3,W  = 0
 c5808: fire_C,N,N,R,L,3,C  = 0
 c5809: fire_C,N,N,R,L,3,I  = 0
 c5810: fire_C,N,N,R,L,3,A  = 0
 c5811: - fire_C,N,N,R,L,3,P - fire_C,N,N,R,L,3,D - fire_C,N,N,R,L,3,W
        - fire_C,N,N,R,L,3,C - fire_C,N,N,R,L,3,I - fire_C,N,N,R,L,3,A
        - fire_C,N,N,R,L,3,N  = 0
 c5812: fire_C,N,N,R,L,3,N  = 0
 c5813: fire_C,N,N,R,L,4,P  = 0
 c5814: fire_C,N,N,R,L,4,D  = 0
 c5815: fire_C,N,N,R,L,4,W  = 0
 c5816: fire_C,N,N,R,L,4,C  = 0
 c5817: fire_C,N,N,R,L,4,I  = 0
 c5818: fire_C,N,N,R,L,4,A  = 0
 c5819: - fire_C,N,N,R,L,4,P - fire_C,N,N,R,L,4,D - fire_C,N,N,R,L,4,W
        - fire_C,N,N,R,L,4,C - fire_C,N,N,R,L,4,I - fire_C,N,N,R,L,4,A
        - fire_C,N,N,R,L,4,N  = 0
 c5820: fire_C,N,N,R,L,4,N  = 0
 c5821: fire_C,N,N,R,L,5,P  = 0
 c5822: fire_C,N,N,R,L,5,D  = 0
 c5823: fire_C,N,N,R,L,5,W  = 0
 c5824: fire_C,N,N,R,L,5,C  = 0
 c5825: fire_C,N,N,R,L,5,I  = 0
 c5826: fire_C,N,N,R,L,5,A  = 0
 c5827: - fire_C,N,N,R,L,5,P - fire_C,N,N,R,L,5,D - fire_C,N,N,R,L,5,W
        - fire_C,N,N,R,L,5,C - fire_C,N,N,R,L,5,I - fire_C,N,N,R,L,5,A
        - fire_C,N,N,R,L,5,N  = 0
 c5828: fire_C,N,N,R,L,5,N  = 0
 c5829: fire_C,N,N,R,N,1,P  = 0
 c5830: fire_C,N,N,R,N,1,D  = 0
 c5831: fire_C,N,N,R,N,1,W  = 0
 c5832: fire_C,N,N,R,N,1,C  = 0
 c5833: fire_C,N,N,R,N,1,I  = 0
 c5834: fire_C,N,N,R,N,1,A  = 0
 c5835: fire_C,N,N,R,N,1,L  = 0
 c5836: - fire_C,N,N,R,N,1,P - fire_C,N,N,R,N,1,D - fire_C,N,N,R,N,1,W
        - fire_C,N,N,R,N,1,C - fire_C,N,N,R,N,1,I - fire_C,N,N,R,N,1,A
        - fire_C,N,N,R,N,1,L  = 0
 c5837: fire_C,N,N,R,N,2,P  = 0
 c5838: fire_C,N,N,R,N,2,D  = 0
 c5839: fire_C,N,N,R,N,2,W  = 0
 c5840: fire_C,N,N,R,N,2,C  = 0
 c5841: fire_C,N,N,R,N,2,I  = 0
 c5842: fire_C,N,N,R,N,2,A  = 0
 c5843: fire_C,N,N,R,N,2,L  = 0
 c5844: - fire_C,N,N,R,N,2,P - fire_C,N,N,R,N,2,D - fire_C,N,N,R,N,2,W
        - fire_C,N,N,R,N,2,C - fire_C,N,N,R,N,2,I - fire_C,N,N,R,N,2,A
        - fire_C,N,N,R,N,2,L  = 0
 c5845: fire_C,N,N,R,N,3,P  = 0
 c5846: fire_C,N,N,R,N,3,D  = 0
 c5847: fire_C,N,N,R,N,3,W  = 0
 c5848: fire_C,N,N,R,N,3,C  = 0
 c5849: fire_C,N,N,R,N,3,I  = 0
 c5850: fire_C,N,N,R,N,3,A  = 0
 c5851: fire_C,N,N,R,N,3,L  = 0
 c5852: - fire_C,N,N,R,N,3,P - fire_C,N,N,R,N,3,D - fire_C,N,N,R,N,3,W
        - fire_C,N,N,R,N,3,C - fire_C,N,N,R,N,3,I - fire_C,N,N,R,N,3,A
        - fire_C,N,N,R,N,3,L  = 0
 c5853: fire_C,N,N,R,N,4,P  = 0
 c5854: fire_C,N,N,R,N,4,D  = 0
 c5855: fire_C,N,N,R,N,4,W  = 0
 c5856: fire_C,N,N,R,N,4,C  = 0
 c5857: fire_C,N,N,R,N,4,I  = 0
 c5858: fire_C,N,N,R,N,4,A  = 0
 c5859: fire_C,N,N,R,N,4,L  = 0
 c5860: - fire_C,N,N,R,N,4,P - fire_C,N,N,R,N,4,D - fire_C,N,N,R,N,4,W
        - fire_C,N,N,R,N,4,C - fire_C,N,N,R,N,4,I - fire_C,N,N,R,N,4,A
        - fire_C,N,N,R,N,4,L  = 0
 c5861: fire_C,N,N,R,N,5,P  = 0
 c5862: fire_C,N,N,R,N,5,D  = 0
 c5863: fire_C,N,N,R,N,5,W  = 0
 c5864: fire_C,N,N,R,N,5,C  = 0
 c5865: fire_C,N,N,R,N,5,I  = 0
 c5866: fire_C,N,N,R,N,5,A  = 0
 c5867: fire_C,N,N,R,N,5,L  = 0
 c5868: - fire_C,N,N,R,N,5,P - fire_C,N,N,R,N,5,D - fire_C,N,N,R,N,5,W
        - fire_C,N,N,R,N,5,C - fire_C,N,N,R,N,5,I - fire_C,N,N,R,N,5,A
        - fire_C,N,N,R,N,5,L  = 0
 c5869: 0.9 fire_C,N,O,L,P,1,P - 0.1 fire_C,N,O,L,P,1,D
        - 0.1 fire_C,N,O,L,P,1,W - 0.1 fire_C,N,O,L,P,1,C
        - 0.1 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        - 0.1 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5870: - 0.1 fire_C,N,O,L,P,1,P + 0.9 fire_C,N,O,L,P,1,D
        - 0.1 fire_C,N,O,L,P,1,W - 0.1 fire_C,N,O,L,P,1,C
        - 0.1 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        - 0.1 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5871: - 0.1 fire_C,N,O,L,P,1,P - 0.1 fire_C,N,O,L,P,1,D
        + 0.9 fire_C,N,O,L,P,1,W - 0.1 fire_C,N,O,L,P,1,C
        - 0.1 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        - 0.1 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5872: - 0.1 fire_C,N,O,L,P,1,P - 0.1 fire_C,N,O,L,P,1,D
        - 0.1 fire_C,N,O,L,P,1,W + 0.9 fire_C,N,O,L,P,1,C
        - 0.1 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        - 0.1 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5873: - 0.1 fire_C,N,O,L,P,1,P - 0.1 fire_C,N,O,L,P,1,D
        - 0.1 fire_C,N,O,L,P,1,W - 0.1 fire_C,N,O,L,P,1,C
        + 0.9 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        - 0.1 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5874: - 0.2 fire_C,N,O,L,P,1,P - 0.2 fire_C,N,O,L,P,1,D
        - 0.2 fire_C,N,O,L,P,1,W - 0.2 fire_C,N,O,L,P,1,C
        - 0.2 fire_C,N,O,L,P,1,I + 0.8 fire_C,N,O,L,P,1,A
        - 0.2 fire_C,N,O,L,P,1,L - 0.2 fire_C,N,O,L,P,1,N  = 0
 c5875: - 0.1 fire_C,N,O,L,P,1,P - 0.1 fire_C,N,O,L,P,1,D
        - 0.1 fire_C,N,O,L,P,1,W - 0.1 fire_C,N,O,L,P,1,C
        - 0.1 fire_C,N,O,L,P,1,I - 0.1 fire_C,N,O,L,P,1,A
        + 0.9 fire_C,N,O,L,P,1,L - 0.1 fire_C,N,O,L,P,1,N  = 0
 c5876: - 0.2 fire_C,N,O,L,P,1,P - 0.2 fire_C,N,O,L,P,1,D
        - 0.2 fire_C,N,O,L,P,1,W - 0.2 fire_C,N,O,L,P,1,C
        - 0.2 fire_C,N,O,L,P,1,I - 0.2 fire_C,N,O,L,P,1,A
        - 0.2 fire_C,N,O,L,P,1,L + 0.8 fire_C,N,O,L,P,1,N  = 0
 c5877: 0.9 fire_C,N,O,L,P,2,P - 0.1 fire_C,N,O,L,P,2,D
        - 0.1 fire_C,N,O,L,P,2,W - 0.1 fire_C,N,O,L,P,2,C
        - 0.1 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        - 0.1 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5878: - 0.1 fire_C,N,O,L,P,2,P + 0.9 fire_C,N,O,L,P,2,D
        - 0.1 fire_C,N,O,L,P,2,W - 0.1 fire_C,N,O,L,P,2,C
        - 0.1 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        - 0.1 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5879: - 0.1 fire_C,N,O,L,P,2,P - 0.1 fire_C,N,O,L,P,2,D
        + 0.9 fire_C,N,O,L,P,2,W - 0.1 fire_C,N,O,L,P,2,C
        - 0.1 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        - 0.1 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5880: - 0.1 fire_C,N,O,L,P,2,P - 0.1 fire_C,N,O,L,P,2,D
        - 0.1 fire_C,N,O,L,P,2,W + 0.9 fire_C,N,O,L,P,2,C
        - 0.1 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        - 0.1 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5881: - 0.1 fire_C,N,O,L,P,2,P - 0.1 fire_C,N,O,L,P,2,D
        - 0.1 fire_C,N,O,L,P,2,W - 0.1 fire_C,N,O,L,P,2,C
        + 0.9 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        - 0.1 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5882: - 0.2 fire_C,N,O,L,P,2,P - 0.2 fire_C,N,O,L,P,2,D
        - 0.2 fire_C,N,O,L,P,2,W - 0.2 fire_C,N,O,L,P,2,C
        - 0.2 fire_C,N,O,L,P,2,I + 0.8 fire_C,N,O,L,P,2,A
        - 0.2 fire_C,N,O,L,P,2,L - 0.2 fire_C,N,O,L,P,2,N  = 0
 c5883: - 0.1 fire_C,N,O,L,P,2,P - 0.1 fire_C,N,O,L,P,2,D
        - 0.1 fire_C,N,O,L,P,2,W - 0.1 fire_C,N,O,L,P,2,C
        - 0.1 fire_C,N,O,L,P,2,I - 0.1 fire_C,N,O,L,P,2,A
        + 0.9 fire_C,N,O,L,P,2,L - 0.1 fire_C,N,O,L,P,2,N  = 0
 c5884: - 0.2 fire_C,N,O,L,P,2,P - 0.2 fire_C,N,O,L,P,2,D
        - 0.2 fire_C,N,O,L,P,2,W - 0.2 fire_C,N,O,L,P,2,C
        - 0.2 fire_C,N,O,L,P,2,I - 0.2 fire_C,N,O,L,P,2,A
        - 0.2 fire_C,N,O,L,P,2,L + 0.8 fire_C,N,O,L,P,2,N  = 0
 c5885: 0.9 fire_C,N,O,L,P,3,P - 0.1 fire_C,N,O,L,P,3,D
        - 0.1 fire_C,N,O,L,P,3,W - 0.1 fire_C,N,O,L,P,3,C
        - 0.1 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        - 0.1 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5886: - 0.1 fire_C,N,O,L,P,3,P + 0.9 fire_C,N,O,L,P,3,D
        - 0.1 fire_C,N,O,L,P,3,W - 0.1 fire_C,N,O,L,P,3,C
        - 0.1 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        - 0.1 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5887: - 0.1 fire_C,N,O,L,P,3,P - 0.1 fire_C,N,O,L,P,3,D
        + 0.9 fire_C,N,O,L,P,3,W - 0.1 fire_C,N,O,L,P,3,C
        - 0.1 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        - 0.1 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5888: - 0.1 fire_C,N,O,L,P,3,P - 0.1 fire_C,N,O,L,P,3,D
        - 0.1 fire_C,N,O,L,P,3,W + 0.9 fire_C,N,O,L,P,3,C
        - 0.1 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        - 0.1 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5889: - 0.1 fire_C,N,O,L,P,3,P - 0.1 fire_C,N,O,L,P,3,D
        - 0.1 fire_C,N,O,L,P,3,W - 0.1 fire_C,N,O,L,P,3,C
        + 0.9 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        - 0.1 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5890: - 0.2 fire_C,N,O,L,P,3,P - 0.2 fire_C,N,O,L,P,3,D
        - 0.2 fire_C,N,O,L,P,3,W - 0.2 fire_C,N,O,L,P,3,C
        - 0.2 fire_C,N,O,L,P,3,I + 0.8 fire_C,N,O,L,P,3,A
        - 0.2 fire_C,N,O,L,P,3,L - 0.2 fire_C,N,O,L,P,3,N  = 0
 c5891: - 0.1 fire_C,N,O,L,P,3,P - 0.1 fire_C,N,O,L,P,3,D
        - 0.1 fire_C,N,O,L,P,3,W - 0.1 fire_C,N,O,L,P,3,C
        - 0.1 fire_C,N,O,L,P,3,I - 0.1 fire_C,N,O,L,P,3,A
        + 0.9 fire_C,N,O,L,P,3,L - 0.1 fire_C,N,O,L,P,3,N  = 0
 c5892: - 0.2 fire_C,N,O,L,P,3,P - 0.2 fire_C,N,O,L,P,3,D
        - 0.2 fire_C,N,O,L,P,3,W - 0.2 fire_C,N,O,L,P,3,C
        - 0.2 fire_C,N,O,L,P,3,I - 0.2 fire_C,N,O,L,P,3,A
        - 0.2 fire_C,N,O,L,P,3,L + 0.8 fire_C,N,O,L,P,3,N  = 0
 c5893: 0.9 fire_C,N,O,L,P,4,P - 0.1 fire_C,N,O,L,P,4,D
        - 0.1 fire_C,N,O,L,P,4,W - 0.1 fire_C,N,O,L,P,4,C
        - 0.1 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        - 0.1 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5894: - 0.1 fire_C,N,O,L,P,4,P + 0.9 fire_C,N,O,L,P,4,D
        - 0.1 fire_C,N,O,L,P,4,W - 0.1 fire_C,N,O,L,P,4,C
        - 0.1 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        - 0.1 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5895: - 0.1 fire_C,N,O,L,P,4,P - 0.1 fire_C,N,O,L,P,4,D
        + 0.9 fire_C,N,O,L,P,4,W - 0.1 fire_C,N,O,L,P,4,C
        - 0.1 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        - 0.1 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5896: - 0.1 fire_C,N,O,L,P,4,P - 0.1 fire_C,N,O,L,P,4,D
        - 0.1 fire_C,N,O,L,P,4,W + 0.9 fire_C,N,O,L,P,4,C
        - 0.1 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        - 0.1 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5897: - 0.1 fire_C,N,O,L,P,4,P - 0.1 fire_C,N,O,L,P,4,D
        - 0.1 fire_C,N,O,L,P,4,W - 0.1 fire_C,N,O,L,P,4,C
        + 0.9 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        - 0.1 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5898: - 0.2 fire_C,N,O,L,P,4,P - 0.2 fire_C,N,O,L,P,4,D
        - 0.2 fire_C,N,O,L,P,4,W - 0.2 fire_C,N,O,L,P,4,C
        - 0.2 fire_C,N,O,L,P,4,I + 0.8 fire_C,N,O,L,P,4,A
        - 0.2 fire_C,N,O,L,P,4,L - 0.2 fire_C,N,O,L,P,4,N  = 0
 c5899: - 0.1 fire_C,N,O,L,P,4,P - 0.1 fire_C,N,O,L,P,4,D
        - 0.1 fire_C,N,O,L,P,4,W - 0.1 fire_C,N,O,L,P,4,C
        - 0.1 fire_C,N,O,L,P,4,I - 0.1 fire_C,N,O,L,P,4,A
        + 0.9 fire_C,N,O,L,P,4,L - 0.1 fire_C,N,O,L,P,4,N  = 0
 c5900: - 0.2 fire_C,N,O,L,P,4,P - 0.2 fire_C,N,O,L,P,4,D
        - 0.2 fire_C,N,O,L,P,4,W - 0.2 fire_C,N,O,L,P,4,C
        - 0.2 fire_C,N,O,L,P,4,I - 0.2 fire_C,N,O,L,P,4,A
        - 0.2 fire_C,N,O,L,P,4,L + 0.8 fire_C,N,O,L,P,4,N  = 0
 c5901: 0.9 fire_C,N,O,L,P,5,P - 0.1 fire_C,N,O,L,P,5,D
        - 0.1 fire_C,N,O,L,P,5,W - 0.1 fire_C,N,O,L,P,5,C
        - 0.1 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        - 0.1 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5902: - 0.1 fire_C,N,O,L,P,5,P + 0.9 fire_C,N,O,L,P,5,D
        - 0.1 fire_C,N,O,L,P,5,W - 0.1 fire_C,N,O,L,P,5,C
        - 0.1 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        - 0.1 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5903: - 0.1 fire_C,N,O,L,P,5,P - 0.1 fire_C,N,O,L,P,5,D
        + 0.9 fire_C,N,O,L,P,5,W - 0.1 fire_C,N,O,L,P,5,C
        - 0.1 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        - 0.1 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5904: - 0.1 fire_C,N,O,L,P,5,P - 0.1 fire_C,N,O,L,P,5,D
        - 0.1 fire_C,N,O,L,P,5,W + 0.9 fire_C,N,O,L,P,5,C
        - 0.1 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        - 0.1 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5905: - 0.1 fire_C,N,O,L,P,5,P - 0.1 fire_C,N,O,L,P,5,D
        - 0.1 fire_C,N,O,L,P,5,W - 0.1 fire_C,N,O,L,P,5,C
        + 0.9 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        - 0.1 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5906: - 0.2 fire_C,N,O,L,P,5,P - 0.2 fire_C,N,O,L,P,5,D
        - 0.2 fire_C,N,O,L,P,5,W - 0.2 fire_C,N,O,L,P,5,C
        - 0.2 fire_C,N,O,L,P,5,I + 0.8 fire_C,N,O,L,P,5,A
        - 0.2 fire_C,N,O,L,P,5,L - 0.2 fire_C,N,O,L,P,5,N  = 0
 c5907: - 0.1 fire_C,N,O,L,P,5,P - 0.1 fire_C,N,O,L,P,5,D
        - 0.1 fire_C,N,O,L,P,5,W - 0.1 fire_C,N,O,L,P,5,C
        - 0.1 fire_C,N,O,L,P,5,I - 0.1 fire_C,N,O,L,P,5,A
        + 0.9 fire_C,N,O,L,P,5,L - 0.1 fire_C,N,O,L,P,5,N  = 0
 c5908: - 0.2 fire_C,N,O,L,P,5,P - 0.2 fire_C,N,O,L,P,5,D
        - 0.2 fire_C,N,O,L,P,5,W - 0.2 fire_C,N,O,L,P,5,C
        - 0.2 fire_C,N,O,L,P,5,I - 0.2 fire_C,N,O,L,P,5,A
        - 0.2 fire_C,N,O,L,P,5,L + 0.8 fire_C,N,O,L,P,5,N  = 0
 c5909: 0.5 fire_C,N,O,L,D,1,P - 0.5 fire_C,N,O,L,D,1,D
        - 0.5 fire_C,N,O,L,D,1,W - 0.5 fire_C,N,O,L,D,1,C
        - 0.5 fire_C,N,O,L,D,1,I - 0.5 fire_C,N,O,L,D,1,A
        - 0.5 fire_C,N,O,L,D,1,L - 0.5 fire_C,N,O,L,D,1,N  = 0
 c5910: - 0.5 fire_C,N,O,L,D,1,P + 0.5 fire_C,N,O,L,D,1,D
        - 0.5 fire_C,N,O,L,D,1,W - 0.5 fire_C,N,O,L,D,1,C
        - 0.5 fire_C,N,O,L,D,1,I - 0.5 fire_C,N,O,L,D,1,A
        - 0.5 fire_C,N,O,L,D,1,L - 0.5 fire_C,N,O,L,D,1,N  = 0
 c5911: fire_C,N,O,L,D,1,W  = 0
 c5912: fire_C,N,O,L,D,1,C  = 0
 c5913: fire_C,N,O,L,D,1,I  = 0
 c5914: fire_C,N,O,L,D,1,A  = 0
 c5915: fire_C,N,O,L,D,1,L  = 0
 c5916: fire_C,N,O,L,D,1,N  = 0
 c5917: 0.5 fire_C,N,O,L,D,2,P - 0.5 fire_C,N,O,L,D,2,D
        - 0.5 fire_C,N,O,L,D,2,W - 0.5 fire_C,N,O,L,D,2,C
        - 0.5 fire_C,N,O,L,D,2,I - 0.5 fire_C,N,O,L,D,2,A
        - 0.5 fire_C,N,O,L,D,2,L - 0.5 fire_C,N,O,L,D,2,N  = 0
 c5918: - 0.5 fire_C,N,O,L,D,2,P + 0.5 fire_C,N,O,L,D,2,D
        - 0.5 fire_C,N,O,L,D,2,W - 0.5 fire_C,N,O,L,D,2,C
        - 0.5 fire_C,N,O,L,D,2,I - 0.5 fire_C,N,O,L,D,2,A
        - 0.5 fire_C,N,O,L,D,2,L - 0.5 fire_C,N,O,L,D,2,N  = 0
 c5919: fire_C,N,O,L,D,2,W  = 0
 c5920: fire_C,N,O,L,D,2,C  = 0
 c5921: fire_C,N,O,L,D,2,I  = 0
 c5922: fire_C,N,O,L,D,2,A  = 0
 c5923: fire_C,N,O,L,D,2,L  = 0
 c5924: fire_C,N,O,L,D,2,N  = 0
 c5925: 0.5 fire_C,N,O,L,D,3,P - 0.5 fire_C,N,O,L,D,3,D
        - 0.5 fire_C,N,O,L,D,3,W - 0.5 fire_C,N,O,L,D,3,C
        - 0.5 fire_C,N,O,L,D,3,I - 0.5 fire_C,N,O,L,D,3,A
        - 0.5 fire_C,N,O,L,D,3,L - 0.5 fire_C,N,O,L,D,3,N  = 0
 c5926: - 0.5 fire_C,N,O,L,D,3,P + 0.5 fire_C,N,O,L,D,3,D
        - 0.5 fire_C,N,O,L,D,3,W - 0.5 fire_C,N,O,L,D,3,C
        - 0.5 fire_C,N,O,L,D,3,I - 0.5 fire_C,N,O,L,D,3,A
        - 0.5 fire_C,N,O,L,D,3,L - 0.5 fire_C,N,O,L,D,3,N  = 0
 c5927: fire_C,N,O,L,D,3,W  = 0
 c5928: fire_C,N,O,L,D,3,C  = 0
 c5929: fire_C,N,O,L,D,3,I  = 0
 c5930: fire_C,N,O,L,D,3,A  = 0
 c5931: fire_C,N,O,L,D,3,L  = 0
 c5932: fire_C,N,O,L,D,3,N  = 0
 c5933: 0.5 fire_C,N,O,L,D,4,P - 0.5 fire_C,N,O,L,D,4,D
        - 0.5 fire_C,N,O,L,D,4,W - 0.5 fire_C,N,O,L,D,4,C
        - 0.5 fire_C,N,O,L,D,4,I - 0.5 fire_C,N,O,L,D,4,A
        - 0.5 fire_C,N,O,L,D,4,L - 0.5 fire_C,N,O,L,D,4,N  = 0
 c5934: - 0.5 fire_C,N,O,L,D,4,P + 0.5 fire_C,N,O,L,D,4,D
        - 0.5 fire_C,N,O,L,D,4,W - 0.5 fire_C,N,O,L,D,4,C
        - 0.5 fire_C,N,O,L,D,4,I - 0.5 fire_C,N,O,L,D,4,A
        - 0.5 fire_C,N,O,L,D,4,L - 0.5 fire_C,N,O,L,D,4,N  = 0
 c5935: fire_C,N,O,L,D,4,W  = 0
 c5936: fire_C,N,O,L,D,4,C  = 0
 c5937: fire_C,N,O,L,D,4,I  = 0
 c5938: fire_C,N,O,L,D,4,A  = 0
 c5939: fire_C,N,O,L,D,4,L  = 0
 c5940: fire_C,N,O,L,D,4,N  = 0
 c5941: 0.5 fire_C,N,O,L,D,5,P - 0.5 fire_C,N,O,L,D,5,D
        - 0.5 fire_C,N,O,L,D,5,W - 0.5 fire_C,N,O,L,D,5,C
        - 0.5 fire_C,N,O,L,D,5,I - 0.5 fire_C,N,O,L,D,5,A
        - 0.5 fire_C,N,O,L,D,5,L - 0.5 fire_C,N,O,L,D,5,N  = 0
 c5942: - 0.5 fire_C,N,O,L,D,5,P + 0.5 fire_C,N,O,L,D,5,D
        - 0.5 fire_C,N,O,L,D,5,W - 0.5 fire_C,N,O,L,D,5,C
        - 0.5 fire_C,N,O,L,D,5,I - 0.5 fire_C,N,O,L,D,5,A
        - 0.5 fire_C,N,O,L,D,5,L - 0.5 fire_C,N,O,L,D,5,N  = 0
 c5943: fire_C,N,O,L,D,5,W  = 0
 c5944: fire_C,N,O,L,D,5,C  = 0
 c5945: fire_C,N,O,L,D,5,I  = 0
 c5946: fire_C,N,O,L,D,5,A  = 0
 c5947: fire_C,N,O,L,D,5,L  = 0
 c5948: fire_C,N,O,L,D,5,N  = 0
 c5949: fire_C,N,O,L,W,1,P  = 0
 c5950: fire_C,N,O,L,W,1,D  = 0
 c5951: - fire_C,N,O,L,W,1,P - fire_C,N,O,L,W,1,D - fire_C,N,O,L,W,1,C
        - fire_C,N,O,L,W,1,I - fire_C,N,O,L,W,1,A - fire_C,N,O,L,W,1,L
        - fire_C,N,O,L,W,1,N  = 0
 c5952: fire_C,N,O,L,W,1,C  = 0
 c5953: fire_C,N,O,L,W,1,I  = 0
 c5954: fire_C,N,O,L,W,1,A  = 0
 c5955: fire_C,N,O,L,W,1,L  = 0
 c5956: fire_C,N,O,L,W,1,N  = 0
 c5957: fire_C,N,O,L,W,2,P  = 0
 c5958: fire_C,N,O,L,W,2,D  = 0
 c5959: - fire_C,N,O,L,W,2,P - fire_C,N,O,L,W,2,D - fire_C,N,O,L,W,2,C
        - fire_C,N,O,L,W,2,I - fire_C,N,O,L,W,2,A - fire_C,N,O,L,W,2,L
        - fire_C,N,O,L,W,2,N  = 0
 c5960: fire_C,N,O,L,W,2,C  = 0
 c5961: fire_C,N,O,L,W,2,I  = 0
 c5962: fire_C,N,O,L,W,2,A  = 0
 c5963: fire_C,N,O,L,W,2,L  = 0
 c5964: fire_C,N,O,L,W,2,N  = 0
 c5965: fire_C,N,O,L,W,3,P  = 0
 c5966: fire_C,N,O,L,W,3,D  = 0
 c5967: - fire_C,N,O,L,W,3,P - fire_C,N,O,L,W,3,D - fire_C,N,O,L,W,3,C
        - fire_C,N,O,L,W,3,I - fire_C,N,O,L,W,3,A - fire_C,N,O,L,W,3,L
        - fire_C,N,O,L,W,3,N  = 0
 c5968: fire_C,N,O,L,W,3,C  = 0
 c5969: fire_C,N,O,L,W,3,I  = 0
 c5970: fire_C,N,O,L,W,3,A  = 0
 c5971: fire_C,N,O,L,W,3,L  = 0
 c5972: fire_C,N,O,L,W,3,N  = 0
 c5973: fire_C,N,O,L,W,4,P  = 0
 c5974: fire_C,N,O,L,W,4,D  = 0
 c5975: - fire_C,N,O,L,W,4,P - fire_C,N,O,L,W,4,D - fire_C,N,O,L,W,4,C
        - fire_C,N,O,L,W,4,I - fire_C,N,O,L,W,4,A - fire_C,N,O,L,W,4,L
        - fire_C,N,O,L,W,4,N  = 0
 c5976: fire_C,N,O,L,W,4,C  = 0
 c5977: fire_C,N,O,L,W,4,I  = 0
 c5978: fire_C,N,O,L,W,4,A  = 0
 c5979: fire_C,N,O,L,W,4,L  = 0
 c5980: fire_C,N,O,L,W,4,N  = 0
 c5981: fire_C,N,O,L,W,5,P  = 0
 c5982: fire_C,N,O,L,W,5,D  = 0
 c5983: - fire_C,N,O,L,W,5,P - fire_C,N,O,L,W,5,D - fire_C,N,O,L,W,5,C
        - fire_C,N,O,L,W,5,I - fire_C,N,O,L,W,5,A - fire_C,N,O,L,W,5,L
        - fire_C,N,O,L,W,5,N  = 0
 c5984: fire_C,N,O,L,W,5,C  = 0
 c5985: fire_C,N,O,L,W,5,I  = 0
 c5986: fire_C,N,O,L,W,5,A  = 0
 c5987: fire_C,N,O,L,W,5,L  = 0
 c5988: fire_C,N,O,L,W,5,N  = 0
 c5989: fire_C,N,O,L,C,1,P  = 0
 c5990: fire_C,N,O,L,C,1,D  = 0
 c5991: fire_C,N,O,L,C,1,W  = 0
 c5992: - fire_C,N,O,L,C,1,P - fire_C,N,O,L,C,1,D - fire_C,N,O,L,C,1,W
        - fire_C,N,O,L,C,1,I - fire_C,N,O,L,C,1,A - fire_C,N,O,L,C,1,L
        - fire_C,N,O,L,C,1,N  = 0
 c5993: fire_C,N,O,L,C,1,I  = 0
 c5994: fire_C,N,O,L,C,1,A  = 0
 c5995: fire_C,N,O,L,C,1,L  = 0
 c5996: fire_C,N,O,L,C,1,N  = 0
 c5997: fire_C,N,O,L,C,2,P  = 0
 c5998: fire_C,N,O,L,C,2,D  = 0
 c5999: fire_C,N,O,L,C,2,W  = 0
 c6000: - fire_C,N,O,L,C,2,P - fire_C,N,O,L,C,2,D - fire_C,N,O,L,C,2,W
        - fire_C,N,O,L,C,2,I - fire_C,N,O,L,C,2,A - fire_C,N,O,L,C,2,L
        - fire_C,N,O,L,C,2,N  = 0
 c6001: fire_C,N,O,L,C,2,I  = 0
 c6002: fire_C,N,O,L,C,2,A  = 0
 c6003: fire_C,N,O,L,C,2,L  = 0
 c6004: fire_C,N,O,L,C,2,N  = 0
 c6005: fire_C,N,O,L,C,3,P  = 0
 c6006: fire_C,N,O,L,C,3,D  = 0
 c6007: fire_C,N,O,L,C,3,W  = 0
 c6008: - fire_C,N,O,L,C,3,P - fire_C,N,O,L,C,3,D - fire_C,N,O,L,C,3,W
        - fire_C,N,O,L,C,3,I - fire_C,N,O,L,C,3,A - fire_C,N,O,L,C,3,L
        - fire_C,N,O,L,C,3,N  = 0
 c6009: fire_C,N,O,L,C,3,I  = 0
 c6010: fire_C,N,O,L,C,3,A  = 0
 c6011: fire_C,N,O,L,C,3,L  = 0
 c6012: fire_C,N,O,L,C,3,N  = 0
 c6013: fire_C,N,O,L,C,4,P  = 0
 c6014: fire_C,N,O,L,C,4,D  = 0
 c6015: fire_C,N,O,L,C,4,W  = 0
 c6016: - fire_C,N,O,L,C,4,P - fire_C,N,O,L,C,4,D - fire_C,N,O,L,C,4,W
        - fire_C,N,O,L,C,4,I - fire_C,N,O,L,C,4,A - fire_C,N,O,L,C,4,L
        - fire_C,N,O,L,C,4,N  = 0
 c6017: fire_C,N,O,L,C,4,I  = 0
 c6018: fire_C,N,O,L,C,4,A  = 0
 c6019: fire_C,N,O,L,C,4,L  = 0
 c6020: fire_C,N,O,L,C,4,N  = 0
 c6021: fire_C,N,O,L,C,5,P  = 0
 c6022: fire_C,N,O,L,C,5,D  = 0
 c6023: fire_C,N,O,L,C,5,W  = 0
 c6024: - fire_C,N,O,L,C,5,P - fire_C,N,O,L,C,5,D - fire_C,N,O,L,C,5,W
        - fire_C,N,O,L,C,5,I - fire_C,N,O,L,C,5,A - fire_C,N,O,L,C,5,L
        - fire_C,N,O,L,C,5,N  = 0
 c6025: fire_C,N,O,L,C,5,I  = 0
 c6026: fire_C,N,O,L,C,5,A  = 0
 c6027: fire_C,N,O,L,C,5,L  = 0
 c6028: fire_C,N,O,L,C,5,N  = 0
 c6029: fire_C,N,O,L,I,1,P  = 0
 c6030: fire_C,N,O,L,I,1,D  = 0
 c6031: fire_C,N,O,L,I,1,W  = 0
 c6032: fire_C,N,O,L,I,1,C  = 0
 c6033: - fire_C,N,O,L,I,1,P - fire_C,N,O,L,I,1,D - fire_C,N,O,L,I,1,W
        - fire_C,N,O,L,I,1,C - fire_C,N,O,L,I,1,A - fire_C,N,O,L,I,1,L
        - fire_C,N,O,L,I,1,N  = 0
 c6034: fire_C,N,O,L,I,1,A  = 0
 c6035: fire_C,N,O,L,I,1,L  = 0
 c6036: fire_C,N,O,L,I,1,N  = 0
 c6037: fire_C,N,O,L,I,2,P  = 0
 c6038: fire_C,N,O,L,I,2,D  = 0
 c6039: fire_C,N,O,L,I,2,W  = 0
 c6040: fire_C,N,O,L,I,2,C  = 0
 c6041: - fire_C,N,O,L,I,2,P - fire_C,N,O,L,I,2,D - fire_C,N,O,L,I,2,W
        - fire_C,N,O,L,I,2,C - fire_C,N,O,L,I,2,A - fire_C,N,O,L,I,2,L
        - fire_C,N,O,L,I,2,N  = 0
 c6042: fire_C,N,O,L,I,2,A  = 0
 c6043: fire_C,N,O,L,I,2,L  = 0
 c6044: fire_C,N,O,L,I,2,N  = 0
 c6045: fire_C,N,O,L,I,3,P  = 0
 c6046: fire_C,N,O,L,I,3,D  = 0
 c6047: fire_C,N,O,L,I,3,W  = 0
 c6048: fire_C,N,O,L,I,3,C  = 0
 c6049: - fire_C,N,O,L,I,3,P - fire_C,N,O,L,I,3,D - fire_C,N,O,L,I,3,W
        - fire_C,N,O,L,I,3,C - fire_C,N,O,L,I,3,A - fire_C,N,O,L,I,3,L
        - fire_C,N,O,L,I,3,N  = 0
 c6050: fire_C,N,O,L,I,3,A  = 0
 c6051: fire_C,N,O,L,I,3,L  = 0
 c6052: fire_C,N,O,L,I,3,N  = 0
 c6053: fire_C,N,O,L,I,4,P  = 0
 c6054: fire_C,N,O,L,I,4,D  = 0
 c6055: fire_C,N,O,L,I,4,W  = 0
 c6056: fire_C,N,O,L,I,4,C  = 0
 c6057: - fire_C,N,O,L,I,4,P - fire_C,N,O,L,I,4,D - fire_C,N,O,L,I,4,W
        - fire_C,N,O,L,I,4,C - fire_C,N,O,L,I,4,A - fire_C,N,O,L,I,4,L
        - fire_C,N,O,L,I,4,N  = 0
 c6058: fire_C,N,O,L,I,4,A  = 0
 c6059: fire_C,N,O,L,I,4,L  = 0
 c6060: fire_C,N,O,L,I,4,N  = 0
 c6061: fire_C,N,O,L,I,5,P  = 0
 c6062: fire_C,N,O,L,I,5,D  = 0
 c6063: fire_C,N,O,L,I,5,W  = 0
 c6064: fire_C,N,O,L,I,5,C  = 0
 c6065: - fire_C,N,O,L,I,5,P - fire_C,N,O,L,I,5,D - fire_C,N,O,L,I,5,W
        - fire_C,N,O,L,I,5,C - fire_C,N,O,L,I,5,A - fire_C,N,O,L,I,5,L
        - fire_C,N,O,L,I,5,N  = 0
 c6066: fire_C,N,O,L,I,5,A  = 0
 c6067: fire_C,N,O,L,I,5,L  = 0
 c6068: fire_C,N,O,L,I,5,N  = 0
 c6069: fire_C,N,O,L,A,1,P  = 0
 c6070: fire_C,N,O,L,A,1,D  = 0
 c6071: fire_C,N,O,L,A,1,W  = 0
 c6072: fire_C,N,O,L,A,1,C  = 0
 c6073: fire_C,N,O,L,A,1,I  = 0
 c6074: - fire_C,N,O,L,A,1,P - fire_C,N,O,L,A,1,D - fire_C,N,O,L,A,1,W
        - fire_C,N,O,L,A,1,C - fire_C,N,O,L,A,1,I - fire_C,N,O,L,A,1,L
        - fire_C,N,O,L,A,1,N  = 0
 c6075: fire_C,N,O,L,A,1,L  = 0
 c6076: fire_C,N,O,L,A,1,N  = 0
 c6077: fire_C,N,O,L,A,2,P  = 0
 c6078: fire_C,N,O,L,A,2,D  = 0
 c6079: fire_C,N,O,L,A,2,W  = 0
 c6080: fire_C,N,O,L,A,2,C  = 0
 c6081: fire_C,N,O,L,A,2,I  = 0
 c6082: - fire_C,N,O,L,A,2,P - fire_C,N,O,L,A,2,D - fire_C,N,O,L,A,2,W
        - fire_C,N,O,L,A,2,C - fire_C,N,O,L,A,2,I - fire_C,N,O,L,A,2,L
        - fire_C,N,O,L,A,2,N  = 0
 c6083: fire_C,N,O,L,A,2,L  = 0
 c6084: fire_C,N,O,L,A,2,N  = 0
 c6085: fire_C,N,O,L,A,3,P  = 0
 c6086: fire_C,N,O,L,A,3,D  = 0
 c6087: fire_C,N,O,L,A,3,W  = 0
 c6088: fire_C,N,O,L,A,3,C  = 0
 c6089: fire_C,N,O,L,A,3,I  = 0
 c6090: - fire_C,N,O,L,A,3,P - fire_C,N,O,L,A,3,D - fire_C,N,O,L,A,3,W
        - fire_C,N,O,L,A,3,C - fire_C,N,O,L,A,3,I - fire_C,N,O,L,A,3,L
        - fire_C,N,O,L,A,3,N  = 0
 c6091: fire_C,N,O,L,A,3,L  = 0
 c6092: fire_C,N,O,L,A,3,N  = 0
 c6093: fire_C,N,O,L,A,4,P  = 0
 c6094: fire_C,N,O,L,A,4,D  = 0
 c6095: fire_C,N,O,L,A,4,W  = 0
 c6096: fire_C,N,O,L,A,4,C  = 0
 c6097: fire_C,N,O,L,A,4,I  = 0
 c6098: - fire_C,N,O,L,A,4,P - fire_C,N,O,L,A,4,D - fire_C,N,O,L,A,4,W
        - fire_C,N,O,L,A,4,C - fire_C,N,O,L,A,4,I - fire_C,N,O,L,A,4,L
        - fire_C,N,O,L,A,4,N  = 0
 c6099: fire_C,N,O,L,A,4,L  = 0
 c6100: fire_C,N,O,L,A,4,N  = 0
 c6101: fire_C,N,O,L,A,5,P  = 0
 c6102: fire_C,N,O,L,A,5,D  = 0
 c6103: fire_C,N,O,L,A,5,W  = 0
 c6104: fire_C,N,O,L,A,5,C  = 0
 c6105: fire_C,N,O,L,A,5,I  = 0
 c6106: - fire_C,N,O,L,A,5,P - fire_C,N,O,L,A,5,D - fire_C,N,O,L,A,5,W
        - fire_C,N,O,L,A,5,C - fire_C,N,O,L,A,5,I - fire_C,N,O,L,A,5,L
        - fire_C,N,O,L,A,5,N  = 0
 c6107: fire_C,N,O,L,A,5,L  = 0
 c6108: fire_C,N,O,L,A,5,N  = 0
 c6109: fire_C,N,O,L,L,1,P  = 0
 c6110: fire_C,N,O,L,L,1,D  = 0
 c6111: fire_C,N,O,L,L,1,W  = 0
 c6112: fire_C,N,O,L,L,1,C  = 0
 c6113: fire_C,N,O,L,L,1,I  = 0
 c6114: fire_C,N,O,L,L,1,A  = 0
 c6115: - fire_C,N,O,L,L,1,P - fire_C,N,O,L,L,1,D - fire_C,N,O,L,L,1,W
        - fire_C,N,O,L,L,1,C - fire_C,N,O,L,L,1,I - fire_C,N,O,L,L,1,A
        - fire_C,N,O,L,L,1,N  = 0
 c6116: fire_C,N,O,L,L,1,N  = 0
 c6117: fire_C,N,O,L,L,2,P  = 0
 c6118: fire_C,N,O,L,L,2,D  = 0
 c6119: fire_C,N,O,L,L,2,W  = 0
 c6120: fire_C,N,O,L,L,2,C  = 0
 c6121: fire_C,N,O,L,L,2,I  = 0
 c6122: fire_C,N,O,L,L,2,A  = 0
 c6123: - fire_C,N,O,L,L,2,P - fire_C,N,O,L,L,2,D - fire_C,N,O,L,L,2,W
        - fire_C,N,O,L,L,2,C - fire_C,N,O,L,L,2,I - fire_C,N,O,L,L,2,A
        - fire_C,N,O,L,L,2,N  = 0
 c6124: fire_C,N,O,L,L,2,N  = 0
 c6125: fire_C,N,O,L,L,3,P  = 0
 c6126: fire_C,N,O,L,L,3,D  = 0
 c6127: fire_C,N,O,L,L,3,W  = 0
 c6128: fire_C,N,O,L,L,3,C  = 0
 c6129: fire_C,N,O,L,L,3,I  = 0
 c6130: fire_C,N,O,L,L,3,A  = 0
 c6131: - fire_C,N,O,L,L,3,P - fire_C,N,O,L,L,3,D - fire_C,N,O,L,L,3,W
        - fire_C,N,O,L,L,3,C - fire_C,N,O,L,L,3,I - fire_C,N,O,L,L,3,A
        - fire_C,N,O,L,L,3,N  = 0
 c6132: fire_C,N,O,L,L,3,N  = 0
 c6133: fire_C,N,O,L,L,4,P  = 0
 c6134: fire_C,N,O,L,L,4,D  = 0
 c6135: fire_C,N,O,L,L,4,W  = 0
 c6136: fire_C,N,O,L,L,4,C  = 0
 c6137: fire_C,N,O,L,L,4,I  = 0
 c6138: fire_C,N,O,L,L,4,A  = 0
 c6139: - fire_C,N,O,L,L,4,P - fire_C,N,O,L,L,4,D - fire_C,N,O,L,L,4,W
        - fire_C,N,O,L,L,4,C - fire_C,N,O,L,L,4,I - fire_C,N,O,L,L,4,A
        - fire_C,N,O,L,L,4,N  = 0
 c6140: fire_C,N,O,L,L,4,N  = 0
 c6141: fire_C,N,O,L,L,5,P  = 0
 c6142: fire_C,N,O,L,L,5,D  = 0
 c6143: fire_C,N,O,L,L,5,W  = 0
 c6144: fire_C,N,O,L,L,5,C  = 0
 c6145: fire_C,N,O,L,L,5,I  = 0
 c6146: fire_C,N,O,L,L,5,A  = 0
 c6147: - fire_C,N,O,L,L,5,P - fire_C,N,O,L,L,5,D - fire_C,N,O,L,L,5,W
        - fire_C,N,O,L,L,5,C - fire_C,N,O,L,L,5,I - fire_C,N,O,L,L,5,A
        - fire_C,N,O,L,L,5,N  = 0
 c6148: fire_C,N,O,L,L,5,N  = 0
 c6149: fire_C,N,O,L,N,1,P  = 0
 c6150: fire_C,N,O,L,N,1,D  = 0
 c6151: fire_C,N,O,L,N,1,W  = 0
 c6152: fire_C,N,O,L,N,1,C  = 0
 c6153: fire_C,N,O,L,N,1,I  = 0
 c6154: fire_C,N,O,L,N,1,A  = 0
 c6155: fire_C,N,O,L,N,1,L  = 0
 c6156: - fire_C,N,O,L,N,1,P - fire_C,N,O,L,N,1,D - fire_C,N,O,L,N,1,W
        - fire_C,N,O,L,N,1,C - fire_C,N,O,L,N,1,I - fire_C,N,O,L,N,1,A
        - fire_C,N,O,L,N,1,L  = 0
 c6157: fire_C,N,O,L,N,2,P  = 0
 c6158: fire_C,N,O,L,N,2,D  = 0
 c6159: fire_C,N,O,L,N,2,W  = 0
 c6160: fire_C,N,O,L,N,2,C  = 0
 c6161: fire_C,N,O,L,N,2,I  = 0
 c6162: fire_C,N,O,L,N,2,A  = 0
 c6163: fire_C,N,O,L,N,2,L  = 0
 c6164: - fire_C,N,O,L,N,2,P - fire_C,N,O,L,N,2,D - fire_C,N,O,L,N,2,W
        - fire_C,N,O,L,N,2,C - fire_C,N,O,L,N,2,I - fire_C,N,O,L,N,2,A
        - fire_C,N,O,L,N,2,L  = 0
 c6165: fire_C,N,O,L,N,3,P  = 0
 c6166: fire_C,N,O,L,N,3,D  = 0
 c6167: fire_C,N,O,L,N,3,W  = 0
 c6168: fire_C,N,O,L,N,3,C  = 0
 c6169: fire_C,N,O,L,N,3,I  = 0
 c6170: fire_C,N,O,L,N,3,A  = 0
 c6171: fire_C,N,O,L,N,3,L  = 0
 c6172: - fire_C,N,O,L,N,3,P - fire_C,N,O,L,N,3,D - fire_C,N,O,L,N,3,W
        - fire_C,N,O,L,N,3,C - fire_C,N,O,L,N,3,I - fire_C,N,O,L,N,3,A
        - fire_C,N,O,L,N,3,L  = 0
 c6173: fire_C,N,O,L,N,4,P  = 0
 c6174: fire_C,N,O,L,N,4,D  = 0
 c6175: fire_C,N,O,L,N,4,W  = 0
 c6176: fire_C,N,O,L,N,4,C  = 0
 c6177: fire_C,N,O,L,N,4,I  = 0
 c6178: fire_C,N,O,L,N,4,A  = 0
 c6179: fire_C,N,O,L,N,4,L  = 0
 c6180: - fire_C,N,O,L,N,4,P - fire_C,N,O,L,N,4,D - fire_C,N,O,L,N,4,W
        - fire_C,N,O,L,N,4,C - fire_C,N,O,L,N,4,I - fire_C,N,O,L,N,4,A
        - fire_C,N,O,L,N,4,L  = 0
 c6181: fire_C,N,O,L,N,5,P  = 0
 c6182: fire_C,N,O,L,N,5,D  = 0
 c6183: fire_C,N,O,L,N,5,W  = 0
 c6184: fire_C,N,O,L,N,5,C  = 0
 c6185: fire_C,N,O,L,N,5,I  = 0
 c6186: fire_C,N,O,L,N,5,A  = 0
 c6187: fire_C,N,O,L,N,5,L  = 0
 c6188: - fire_C,N,O,L,N,5,P - fire_C,N,O,L,N,5,D - fire_C,N,O,L,N,5,W
        - fire_C,N,O,L,N,5,C - fire_C,N,O,L,N,5,I - fire_C,N,O,L,N,5,A
        - fire_C,N,O,L,N,5,L  = 0
 c6189: 0.9 fire_C,N,O,H,P,1,P - 0.1 fire_C,N,O,H,P,1,D
        - 0.1 fire_C,N,O,H,P,1,W - 0.1 fire_C,N,O,H,P,1,C
        - 0.1 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        - 0.1 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6190: - 0.1 fire_C,N,O,H,P,1,P + 0.9 fire_C,N,O,H,P,1,D
        - 0.1 fire_C,N,O,H,P,1,W - 0.1 fire_C,N,O,H,P,1,C
        - 0.1 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        - 0.1 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6191: - 0.1 fire_C,N,O,H,P,1,P - 0.1 fire_C,N,O,H,P,1,D
        + 0.9 fire_C,N,O,H,P,1,W - 0.1 fire_C,N,O,H,P,1,C
        - 0.1 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        - 0.1 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6192: - 0.1 fire_C,N,O,H,P,1,P - 0.1 fire_C,N,O,H,P,1,D
        - 0.1 fire_C,N,O,H,P,1,W + 0.9 fire_C,N,O,H,P,1,C
        - 0.1 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        - 0.1 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6193: - 0.1 fire_C,N,O,H,P,1,P - 0.1 fire_C,N,O,H,P,1,D
        - 0.1 fire_C,N,O,H,P,1,W - 0.1 fire_C,N,O,H,P,1,C
        + 0.9 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        - 0.1 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6194: - 0.2 fire_C,N,O,H,P,1,P - 0.2 fire_C,N,O,H,P,1,D
        - 0.2 fire_C,N,O,H,P,1,W - 0.2 fire_C,N,O,H,P,1,C
        - 0.2 fire_C,N,O,H,P,1,I + 0.8 fire_C,N,O,H,P,1,A
        - 0.2 fire_C,N,O,H,P,1,L - 0.2 fire_C,N,O,H,P,1,N  = 0
 c6195: - 0.1 fire_C,N,O,H,P,1,P - 0.1 fire_C,N,O,H,P,1,D
        - 0.1 fire_C,N,O,H,P,1,W - 0.1 fire_C,N,O,H,P,1,C
        - 0.1 fire_C,N,O,H,P,1,I - 0.1 fire_C,N,O,H,P,1,A
        + 0.9 fire_C,N,O,H,P,1,L - 0.1 fire_C,N,O,H,P,1,N  = 0
 c6196: - 0.2 fire_C,N,O,H,P,1,P - 0.2 fire_C,N,O,H,P,1,D
        - 0.2 fire_C,N,O,H,P,1,W - 0.2 fire_C,N,O,H,P,1,C
        - 0.2 fire_C,N,O,H,P,1,I - 0.2 fire_C,N,O,H,P,1,A
        - 0.2 fire_C,N,O,H,P,1,L + 0.8 fire_C,N,O,H,P,1,N  = 0
 c6197: 0.9 fire_C,N,O,H,P,2,P - 0.1 fire_C,N,O,H,P,2,D
        - 0.1 fire_C,N,O,H,P,2,W - 0.1 fire_C,N,O,H,P,2,C
        - 0.1 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        - 0.1 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6198: - 0.1 fire_C,N,O,H,P,2,P + 0.9 fire_C,N,O,H,P,2,D
        - 0.1 fire_C,N,O,H,P,2,W - 0.1 fire_C,N,O,H,P,2,C
        - 0.1 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        - 0.1 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6199: - 0.1 fire_C,N,O,H,P,2,P - 0.1 fire_C,N,O,H,P,2,D
        + 0.9 fire_C,N,O,H,P,2,W - 0.1 fire_C,N,O,H,P,2,C
        - 0.1 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        - 0.1 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6200: - 0.1 fire_C,N,O,H,P,2,P - 0.1 fire_C,N,O,H,P,2,D
        - 0.1 fire_C,N,O,H,P,2,W + 0.9 fire_C,N,O,H,P,2,C
        - 0.1 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        - 0.1 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6201: - 0.1 fire_C,N,O,H,P,2,P - 0.1 fire_C,N,O,H,P,2,D
        - 0.1 fire_C,N,O,H,P,2,W - 0.1 fire_C,N,O,H,P,2,C
        + 0.9 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        - 0.1 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6202: - 0.2 fire_C,N,O,H,P,2,P - 0.2 fire_C,N,O,H,P,2,D
        - 0.2 fire_C,N,O,H,P,2,W - 0.2 fire_C,N,O,H,P,2,C
        - 0.2 fire_C,N,O,H,P,2,I + 0.8 fire_C,N,O,H,P,2,A
        - 0.2 fire_C,N,O,H,P,2,L - 0.2 fire_C,N,O,H,P,2,N  = 0
 c6203: - 0.1 fire_C,N,O,H,P,2,P - 0.1 fire_C,N,O,H,P,2,D
        - 0.1 fire_C,N,O,H,P,2,W - 0.1 fire_C,N,O,H,P,2,C
        - 0.1 fire_C,N,O,H,P,2,I - 0.1 fire_C,N,O,H,P,2,A
        + 0.9 fire_C,N,O,H,P,2,L - 0.1 fire_C,N,O,H,P,2,N  = 0
 c6204: - 0.2 fire_C,N,O,H,P,2,P - 0.2 fire_C,N,O,H,P,2,D
        - 0.2 fire_C,N,O,H,P,2,W - 0.2 fire_C,N,O,H,P,2,C
        - 0.2 fire_C,N,O,H,P,2,I - 0.2 fire_C,N,O,H,P,2,A
        - 0.2 fire_C,N,O,H,P,2,L + 0.8 fire_C,N,O,H,P,2,N  = 0
 c6205: 0.9 fire_C,N,O,H,P,3,P - 0.1 fire_C,N,O,H,P,3,D
        - 0.1 fire_C,N,O,H,P,3,W - 0.1 fire_C,N,O,H,P,3,C
        - 0.1 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        - 0.1 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6206: - 0.1 fire_C,N,O,H,P,3,P + 0.9 fire_C,N,O,H,P,3,D
        - 0.1 fire_C,N,O,H,P,3,W - 0.1 fire_C,N,O,H,P,3,C
        - 0.1 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        - 0.1 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6207: - 0.1 fire_C,N,O,H,P,3,P - 0.1 fire_C,N,O,H,P,3,D
        + 0.9 fire_C,N,O,H,P,3,W - 0.1 fire_C,N,O,H,P,3,C
        - 0.1 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        - 0.1 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6208: - 0.1 fire_C,N,O,H,P,3,P - 0.1 fire_C,N,O,H,P,3,D
        - 0.1 fire_C,N,O,H,P,3,W + 0.9 fire_C,N,O,H,P,3,C
        - 0.1 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        - 0.1 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6209: - 0.1 fire_C,N,O,H,P,3,P - 0.1 fire_C,N,O,H,P,3,D
        - 0.1 fire_C,N,O,H,P,3,W - 0.1 fire_C,N,O,H,P,3,C
        + 0.9 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        - 0.1 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6210: - 0.2 fire_C,N,O,H,P,3,P - 0.2 fire_C,N,O,H,P,3,D
        - 0.2 fire_C,N,O,H,P,3,W - 0.2 fire_C,N,O,H,P,3,C
        - 0.2 fire_C,N,O,H,P,3,I + 0.8 fire_C,N,O,H,P,3,A
        - 0.2 fire_C,N,O,H,P,3,L - 0.2 fire_C,N,O,H,P,3,N  = 0
 c6211: - 0.1 fire_C,N,O,H,P,3,P - 0.1 fire_C,N,O,H,P,3,D
        - 0.1 fire_C,N,O,H,P,3,W - 0.1 fire_C,N,O,H,P,3,C
        - 0.1 fire_C,N,O,H,P,3,I - 0.1 fire_C,N,O,H,P,3,A
        + 0.9 fire_C,N,O,H,P,3,L - 0.1 fire_C,N,O,H,P,3,N  = 0
 c6212: - 0.2 fire_C,N,O,H,P,3,P - 0.2 fire_C,N,O,H,P,3,D
        - 0.2 fire_C,N,O,H,P,3,W - 0.2 fire_C,N,O,H,P,3,C
        - 0.2 fire_C,N,O,H,P,3,I - 0.2 fire_C,N,O,H,P,3,A
        - 0.2 fire_C,N,O,H,P,3,L + 0.8 fire_C,N,O,H,P,3,N  = 0
 c6213: 0.9 fire_C,N,O,H,P,4,P - 0.1 fire_C,N,O,H,P,4,D
        - 0.1 fire_C,N,O,H,P,4,W - 0.1 fire_C,N,O,H,P,4,C
        - 0.1 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        - 0.1 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6214: - 0.1 fire_C,N,O,H,P,4,P + 0.9 fire_C,N,O,H,P,4,D
        - 0.1 fire_C,N,O,H,P,4,W - 0.1 fire_C,N,O,H,P,4,C
        - 0.1 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        - 0.1 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6215: - 0.1 fire_C,N,O,H,P,4,P - 0.1 fire_C,N,O,H,P,4,D
        + 0.9 fire_C,N,O,H,P,4,W - 0.1 fire_C,N,O,H,P,4,C
        - 0.1 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        - 0.1 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6216: - 0.1 fire_C,N,O,H,P,4,P - 0.1 fire_C,N,O,H,P,4,D
        - 0.1 fire_C,N,O,H,P,4,W + 0.9 fire_C,N,O,H,P,4,C
        - 0.1 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        - 0.1 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6217: - 0.1 fire_C,N,O,H,P,4,P - 0.1 fire_C,N,O,H,P,4,D
        - 0.1 fire_C,N,O,H,P,4,W - 0.1 fire_C,N,O,H,P,4,C
        + 0.9 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        - 0.1 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6218: - 0.2 fire_C,N,O,H,P,4,P - 0.2 fire_C,N,O,H,P,4,D
        - 0.2 fire_C,N,O,H,P,4,W - 0.2 fire_C,N,O,H,P,4,C
        - 0.2 fire_C,N,O,H,P,4,I + 0.8 fire_C,N,O,H,P,4,A
        - 0.2 fire_C,N,O,H,P,4,L - 0.2 fire_C,N,O,H,P,4,N  = 0
 c6219: - 0.1 fire_C,N,O,H,P,4,P - 0.1 fire_C,N,O,H,P,4,D
        - 0.1 fire_C,N,O,H,P,4,W - 0.1 fire_C,N,O,H,P,4,C
        - 0.1 fire_C,N,O,H,P,4,I - 0.1 fire_C,N,O,H,P,4,A
        + 0.9 fire_C,N,O,H,P,4,L - 0.1 fire_C,N,O,H,P,4,N  = 0
 c6220: - 0.2 fire_C,N,O,H,P,4,P - 0.2 fire_C,N,O,H,P,4,D
        - 0.2 fire_C,N,O,H,P,4,W - 0.2 fire_C,N,O,H,P,4,C
        - 0.2 fire_C,N,O,H,P,4,I - 0.2 fire_C,N,O,H,P,4,A
        - 0.2 fire_C,N,O,H,P,4,L + 0.8 fire_C,N,O,H,P,4,N  = 0
 c6221: 0.9 fire_C,N,O,H,P,5,P - 0.1 fire_C,N,O,H,P,5,D
        - 0.1 fire_C,N,O,H,P,5,W - 0.1 fire_C,N,O,H,P,5,C
        - 0.1 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        - 0.1 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6222: - 0.1 fire_C,N,O,H,P,5,P + 0.9 fire_C,N,O,H,P,5,D
        - 0.1 fire_C,N,O,H,P,5,W - 0.1 fire_C,N,O,H,P,5,C
        - 0.1 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        - 0.1 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6223: - 0.1 fire_C,N,O,H,P,5,P - 0.1 fire_C,N,O,H,P,5,D
        + 0.9 fire_C,N,O,H,P,5,W - 0.1 fire_C,N,O,H,P,5,C
        - 0.1 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        - 0.1 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6224: - 0.1 fire_C,N,O,H,P,5,P - 0.1 fire_C,N,O,H,P,5,D
        - 0.1 fire_C,N,O,H,P,5,W + 0.9 fire_C,N,O,H,P,5,C
        - 0.1 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        - 0.1 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6225: - 0.1 fire_C,N,O,H,P,5,P - 0.1 fire_C,N,O,H,P,5,D
        - 0.1 fire_C,N,O,H,P,5,W - 0.1 fire_C,N,O,H,P,5,C
        + 0.9 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        - 0.1 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6226: - 0.2 fire_C,N,O,H,P,5,P - 0.2 fire_C,N,O,H,P,5,D
        - 0.2 fire_C,N,O,H,P,5,W - 0.2 fire_C,N,O,H,P,5,C
        - 0.2 fire_C,N,O,H,P,5,I + 0.8 fire_C,N,O,H,P,5,A
        - 0.2 fire_C,N,O,H,P,5,L - 0.2 fire_C,N,O,H,P,5,N  = 0
 c6227: - 0.1 fire_C,N,O,H,P,5,P - 0.1 fire_C,N,O,H,P,5,D
        - 0.1 fire_C,N,O,H,P,5,W - 0.1 fire_C,N,O,H,P,5,C
        - 0.1 fire_C,N,O,H,P,5,I - 0.1 fire_C,N,O,H,P,5,A
        + 0.9 fire_C,N,O,H,P,5,L - 0.1 fire_C,N,O,H,P,5,N  = 0
 c6228: - 0.2 fire_C,N,O,H,P,5,P - 0.2 fire_C,N,O,H,P,5,D
        - 0.2 fire_C,N,O,H,P,5,W - 0.2 fire_C,N,O,H,P,5,C
        - 0.2 fire_C,N,O,H,P,5,I - 0.2 fire_C,N,O,H,P,5,A
        - 0.2 fire_C,N,O,H,P,5,L + 0.8 fire_C,N,O,H,P,5,N  = 0
 c6229: 0.5 fire_C,N,O,H,D,1,P - 0.5 fire_C,N,O,H,D,1,D
        - 0.5 fire_C,N,O,H,D,1,W - 0.5 fire_C,N,O,H,D,1,C
        - 0.5 fire_C,N,O,H,D,1,I - 0.5 fire_C,N,O,H,D,1,A
        - 0.5 fire_C,N,O,H,D,1,L - 0.5 fire_C,N,O,H,D,1,N  = 0
 c6230: - 0.5 fire_C,N,O,H,D,1,P + 0.5 fire_C,N,O,H,D,1,D
        - 0.5 fire_C,N,O,H,D,1,W - 0.5 fire_C,N,O,H,D,1,C
        - 0.5 fire_C,N,O,H,D,1,I - 0.5 fire_C,N,O,H,D,1,A
        - 0.5 fire_C,N,O,H,D,1,L - 0.5 fire_C,N,O,H,D,1,N  = 0
 c6231: fire_C,N,O,H,D,1,W  = 0
 c6232: fire_C,N,O,H,D,1,C  = 0
 c6233: fire_C,N,O,H,D,1,I  = 0
 c6234: fire_C,N,O,H,D,1,A  = 0
 c6235: fire_C,N,O,H,D,1,L  = 0
 c6236: fire_C,N,O,H,D,1,N  = 0
 c6237: 0.5 fire_C,N,O,H,D,2,P - 0.5 fire_C,N,O,H,D,2,D
        - 0.5 fire_C,N,O,H,D,2,W - 0.5 fire_C,N,O,H,D,2,C
        - 0.5 fire_C,N,O,H,D,2,I - 0.5 fire_C,N,O,H,D,2,A
        - 0.5 fire_C,N,O,H,D,2,L - 0.5 fire_C,N,O,H,D,2,N  = 0
 c6238: - 0.5 fire_C,N,O,H,D,2,P + 0.5 fire_C,N,O,H,D,2,D
        - 0.5 fire_C,N,O,H,D,2,W - 0.5 fire_C,N,O,H,D,2,C
        - 0.5 fire_C,N,O,H,D,2,I - 0.5 fire_C,N,O,H,D,2,A
        - 0.5 fire_C,N,O,H,D,2,L - 0.5 fire_C,N,O,H,D,2,N  = 0
 c6239: fire_C,N,O,H,D,2,W  = 0
 c6240: fire_C,N,O,H,D,2,C  = 0
 c6241: fire_C,N,O,H,D,2,I  = 0
 c6242: fire_C,N,O,H,D,2,A  = 0
 c6243: fire_C,N,O,H,D,2,L  = 0
 c6244: fire_C,N,O,H,D,2,N  = 0
 c6245: 0.5 fire_C,N,O,H,D,3,P - 0.5 fire_C,N,O,H,D,3,D
        - 0.5 fire_C,N,O,H,D,3,W - 0.5 fire_C,N,O,H,D,3,C
        - 0.5 fire_C,N,O,H,D,3,I - 0.5 fire_C,N,O,H,D,3,A
        - 0.5 fire_C,N,O,H,D,3,L - 0.5 fire_C,N,O,H,D,3,N  = 0
 c6246: - 0.5 fire_C,N,O,H,D,3,P + 0.5 fire_C,N,O,H,D,3,D
        - 0.5 fire_C,N,O,H,D,3,W - 0.5 fire_C,N,O,H,D,3,C
        - 0.5 fire_C,N,O,H,D,3,I - 0.5 fire_C,N,O,H,D,3,A
        - 0.5 fire_C,N,O,H,D,3,L - 0.5 fire_C,N,O,H,D,3,N  = 0
 c6247: fire_C,N,O,H,D,3,W  = 0
 c6248: fire_C,N,O,H,D,3,C  = 0
 c6249: fire_C,N,O,H,D,3,I  = 0
 c6250: fire_C,N,O,H,D,3,A  = 0
 c6251: fire_C,N,O,H,D,3,L  = 0
 c6252: fire_C,N,O,H,D,3,N  = 0
 c6253: 0.5 fire_C,N,O,H,D,4,P - 0.5 fire_C,N,O,H,D,4,D
        - 0.5 fire_C,N,O,H,D,4,W - 0.5 fire_C,N,O,H,D,4,C
        - 0.5 fire_C,N,O,H,D,4,I - 0.5 fire_C,N,O,H,D,4,A
        - 0.5 fire_C,N,O,H,D,4,L - 0.5 fire_C,N,O,H,D,4,N  = 0
 c6254: - 0.5 fire_C,N,O,H,D,4,P + 0.5 fire_C,N,O,H,D,4,D
        - 0.5 fire_C,N,O,H,D,4,W - 0.5 fire_C,N,O,H,D,4,C
        - 0.5 fire_C,N,O,H,D,4,I - 0.5 fire_C,N,O,H,D,4,A
        - 0.5 fire_C,N,O,H,D,4,L - 0.5 fire_C,N,O,H,D,4,N  = 0
 c6255: fire_C,N,O,H,D,4,W  = 0
 c6256: fire_C,N,O,H,D,4,C  = 0
 c6257: fire_C,N,O,H,D,4,I  = 0
 c6258: fire_C,N,O,H,D,4,A  = 0
 c6259: fire_C,N,O,H,D,4,L  = 0
 c6260: fire_C,N,O,H,D,4,N  = 0
 c6261: 0.5 fire_C,N,O,H,D,5,P - 0.5 fire_C,N,O,H,D,5,D
        - 0.5 fire_C,N,O,H,D,5,W - 0.5 fire_C,N,O,H,D,5,C
        - 0.5 fire_C,N,O,H,D,5,I - 0.5 fire_C,N,O,H,D,5,A
        - 0.5 fire_C,N,O,H,D,5,L - 0.5 fire_C,N,O,H,D,5,N  = 0
 c6262: - 0.5 fire_C,N,O,H,D,5,P + 0.5 fire_C,N,O,H,D,5,D
        - 0.5 fire_C,N,O,H,D,5,W - 0.5 fire_C,N,O,H,D,5,C
        - 0.5 fire_C,N,O,H,D,5,I - 0.5 fire_C,N,O,H,D,5,A
        - 0.5 fire_C,N,O,H,D,5,L - 0.5 fire_C,N,O,H,D,5,N  = 0
 c6263: fire_C,N,O,H,D,5,W  = 0
 c6264: fire_C,N,O,H,D,5,C  = 0
 c6265: fire_C,N,O,H,D,5,I  = 0
 c6266: fire_C,N,O,H,D,5,A  = 0
 c6267: fire_C,N,O,H,D,5,L  = 0
 c6268: fire_C,N,O,H,D,5,N  = 0
 c6269: fire_C,N,O,H,W,1,P  = 0
 c6270: fire_C,N,O,H,W,1,D  = 0
 c6271: - fire_C,N,O,H,W,1,P - fire_C,N,O,H,W,1,D - fire_C,N,O,H,W,1,C
        - fire_C,N,O,H,W,1,I - fire_C,N,O,H,W,1,A - fire_C,N,O,H,W,1,L
        - fire_C,N,O,H,W,1,N  = 0
 c6272: fire_C,N,O,H,W,1,C  = 0
 c6273: fire_C,N,O,H,W,1,I  = 0
 c6274: fire_C,N,O,H,W,1,A  = 0
 c6275: fire_C,N,O,H,W,1,L  = 0
 c6276: fire_C,N,O,H,W,1,N  = 0
 c6277: fire_C,N,O,H,W,2,P  = 0
 c6278: fire_C,N,O,H,W,2,D  = 0
 c6279: - fire_C,N,O,H,W,2,P - fire_C,N,O,H,W,2,D - fire_C,N,O,H,W,2,C
        - fire_C,N,O,H,W,2,I - fire_C,N,O,H,W,2,A - fire_C,N,O,H,W,2,L
        - fire_C,N,O,H,W,2,N  = 0
 c6280: fire_C,N,O,H,W,2,C  = 0
 c6281: fire_C,N,O,H,W,2,I  = 0
 c6282: fire_C,N,O,H,W,2,A  = 0
 c6283: fire_C,N,O,H,W,2,L  = 0
 c6284: fire_C,N,O,H,W,2,N  = 0
 c6285: fire_C,N,O,H,W,3,P  = 0
 c6286: fire_C,N,O,H,W,3,D  = 0
 c6287: - fire_C,N,O,H,W,3,P - fire_C,N,O,H,W,3,D - fire_C,N,O,H,W,3,C
        - fire_C,N,O,H,W,3,I - fire_C,N,O,H,W,3,A - fire_C,N,O,H,W,3,L
        - fire_C,N,O,H,W,3,N  = 0
 c6288: fire_C,N,O,H,W,3,C  = 0
 c6289: fire_C,N,O,H,W,3,I  = 0
 c6290: fire_C,N,O,H,W,3,A  = 0
 c6291: fire_C,N,O,H,W,3,L  = 0
 c6292: fire_C,N,O,H,W,3,N  = 0
 c6293: fire_C,N,O,H,W,4,P  = 0
 c6294: fire_C,N,O,H,W,4,D  = 0
 c6295: - fire_C,N,O,H,W,4,P - fire_C,N,O,H,W,4,D - fire_C,N,O,H,W,4,C
        - fire_C,N,O,H,W,4,I - fire_C,N,O,H,W,4,A - fire_C,N,O,H,W,4,L
        - fire_C,N,O,H,W,4,N  = 0
 c6296: fire_C,N,O,H,W,4,C  = 0
 c6297: fire_C,N,O,H,W,4,I  = 0
 c6298: fire_C,N,O,H,W,4,A  = 0
 c6299: fire_C,N,O,H,W,4,L  = 0
 c6300: fire_C,N,O,H,W,4,N  = 0
 c6301: fire_C,N,O,H,W,5,P  = 0
 c6302: fire_C,N,O,H,W,5,D  = 0
 c6303: - fire_C,N,O,H,W,5,P - fire_C,N,O,H,W,5,D - fire_C,N,O,H,W,5,C
        - fire_C,N,O,H,W,5,I - fire_C,N,O,H,W,5,A - fire_C,N,O,H,W,5,L
        - fire_C,N,O,H,W,5,N  = 0
 c6304: fire_C,N,O,H,W,5,C  = 0
 c6305: fire_C,N,O,H,W,5,I  = 0
 c6306: fire_C,N,O,H,W,5,A  = 0
 c6307: fire_C,N,O,H,W,5,L  = 0
 c6308: fire_C,N,O,H,W,5,N  = 0
 c6309: fire_C,N,O,H,C,1,P  = 0
 c6310: fire_C,N,O,H,C,1,D  = 0
 c6311: fire_C,N,O,H,C,1,W  = 0
 c6312: - fire_C,N,O,H,C,1,P - fire_C,N,O,H,C,1,D - fire_C,N,O,H,C,1,W
        - fire_C,N,O,H,C,1,I - fire_C,N,O,H,C,1,A - fire_C,N,O,H,C,1,L
        - fire_C,N,O,H,C,1,N  = 0
 c6313: fire_C,N,O,H,C,1,I  = 0
 c6314: fire_C,N,O,H,C,1,A  = 0
 c6315: fire_C,N,O,H,C,1,L  = 0
 c6316: fire_C,N,O,H,C,1,N  = 0
 c6317: fire_C,N,O,H,C,2,P  = 0
 c6318: fire_C,N,O,H,C,2,D  = 0
 c6319: fire_C,N,O,H,C,2,W  = 0
 c6320: - fire_C,N,O,H,C,2,P - fire_C,N,O,H,C,2,D - fire_C,N,O,H,C,2,W
        - fire_C,N,O,H,C,2,I - fire_C,N,O,H,C,2,A - fire_C,N,O,H,C,2,L
        - fire_C,N,O,H,C,2,N  = 0
 c6321: fire_C,N,O,H,C,2,I  = 0
 c6322: fire_C,N,O,H,C,2,A  = 0
 c6323: fire_C,N,O,H,C,2,L  = 0
 c6324: fire_C,N,O,H,C,2,N  = 0
 c6325: fire_C,N,O,H,C,3,P  = 0
 c6326: fire_C,N,O,H,C,3,D  = 0
 c6327: fire_C,N,O,H,C,3,W  = 0
 c6328: - fire_C,N,O,H,C,3,P - fire_C,N,O,H,C,3,D - fire_C,N,O,H,C,3,W
        - fire_C,N,O,H,C,3,I - fire_C,N,O,H,C,3,A - fire_C,N,O,H,C,3,L
        - fire_C,N,O,H,C,3,N  = 0
 c6329: fire_C,N,O,H,C,3,I  = 0
 c6330: fire_C,N,O,H,C,3,A  = 0
 c6331: fire_C,N,O,H,C,3,L  = 0
 c6332: fire_C,N,O,H,C,3,N  = 0
 c6333: fire_C,N,O,H,C,4,P  = 0
 c6334: fire_C,N,O,H,C,4,D  = 0
 c6335: fire_C,N,O,H,C,4,W  = 0
 c6336: - fire_C,N,O,H,C,4,P - fire_C,N,O,H,C,4,D - fire_C,N,O,H,C,4,W
        - fire_C,N,O,H,C,4,I - fire_C,N,O,H,C,4,A - fire_C,N,O,H,C,4,L
        - fire_C,N,O,H,C,4,N  = 0
 c6337: fire_C,N,O,H,C,4,I  = 0
 c6338: fire_C,N,O,H,C,4,A  = 0
 c6339: fire_C,N,O,H,C,4,L  = 0
 c6340: fire_C,N,O,H,C,4,N  = 0
 c6341: fire_C,N,O,H,C,5,P  = 0
 c6342: fire_C,N,O,H,C,5,D  = 0
 c6343: fire_C,N,O,H,C,5,W  = 0
 c6344: - fire_C,N,O,H,C,5,P - fire_C,N,O,H,C,5,D - fire_C,N,O,H,C,5,W
        - fire_C,N,O,H,C,5,I - fire_C,N,O,H,C,5,A - fire_C,N,O,H,C,5,L
        - fire_C,N,O,H,C,5,N  = 0
 c6345: fire_C,N,O,H,C,5,I  = 0
 c6346: fire_C,N,O,H,C,5,A  = 0
 c6347: fire_C,N,O,H,C,5,L  = 0
 c6348: fire_C,N,O,H,C,5,N  = 0
 c6349: fire_C,N,O,H,I,1,P  = 0
 c6350: fire_C,N,O,H,I,1,D  = 0
 c6351: fire_C,N,O,H,I,1,W  = 0
 c6352: fire_C,N,O,H,I,1,C  = 0
 c6353: - fire_C,N,O,H,I,1,P - fire_C,N,O,H,I,1,D - fire_C,N,O,H,I,1,W
        - fire_C,N,O,H,I,1,C - fire_C,N,O,H,I,1,A - fire_C,N,O,H,I,1,L
        - fire_C,N,O,H,I,1,N  = 0
 c6354: fire_C,N,O,H,I,1,A  = 0
 c6355: fire_C,N,O,H,I,1,L  = 0
 c6356: fire_C,N,O,H,I,1,N  = 0
 c6357: fire_C,N,O,H,I,2,P  = 0
 c6358: fire_C,N,O,H,I,2,D  = 0
 c6359: fire_C,N,O,H,I,2,W  = 0
 c6360: fire_C,N,O,H,I,2,C  = 0
 c6361: - fire_C,N,O,H,I,2,P - fire_C,N,O,H,I,2,D - fire_C,N,O,H,I,2,W
        - fire_C,N,O,H,I,2,C - fire_C,N,O,H,I,2,A - fire_C,N,O,H,I,2,L
        - fire_C,N,O,H,I,2,N  = 0
 c6362: fire_C,N,O,H,I,2,A  = 0
 c6363: fire_C,N,O,H,I,2,L  = 0
 c6364: fire_C,N,O,H,I,2,N  = 0
 c6365: fire_C,N,O,H,I,3,P  = 0
 c6366: fire_C,N,O,H,I,3,D  = 0
 c6367: fire_C,N,O,H,I,3,W  = 0
 c6368: fire_C,N,O,H,I,3,C  = 0
 c6369: - fire_C,N,O,H,I,3,P - fire_C,N,O,H,I,3,D - fire_C,N,O,H,I,3,W
        - fire_C,N,O,H,I,3,C - fire_C,N,O,H,I,3,A - fire_C,N,O,H,I,3,L
        - fire_C,N,O,H,I,3,N  = 0
 c6370: fire_C,N,O,H,I,3,A  = 0
 c6371: fire_C,N,O,H,I,3,L  = 0
 c6372: fire_C,N,O,H,I,3,N  = 0
 c6373: fire_C,N,O,H,I,4,P  = 0
 c6374: fire_C,N,O,H,I,4,D  = 0
 c6375: fire_C,N,O,H,I,4,W  = 0
 c6376: fire_C,N,O,H,I,4,C  = 0
 c6377: - fire_C,N,O,H,I,4,P - fire_C,N,O,H,I,4,D - fire_C,N,O,H,I,4,W
        - fire_C,N,O,H,I,4,C - fire_C,N,O,H,I,4,A - fire_C,N,O,H,I,4,L
        - fire_C,N,O,H,I,4,N  = 0
 c6378: fire_C,N,O,H,I,4,A  = 0
 c6379: fire_C,N,O,H,I,4,L  = 0
 c6380: fire_C,N,O,H,I,4,N  = 0
 c6381: fire_C,N,O,H,I,5,P  = 0
 c6382: fire_C,N,O,H,I,5,D  = 0
 c6383: fire_C,N,O,H,I,5,W  = 0
 c6384: fire_C,N,O,H,I,5,C  = 0
 c6385: - fire_C,N,O,H,I,5,P - fire_C,N,O,H,I,5,D - fire_C,N,O,H,I,5,W
        - fire_C,N,O,H,I,5,C - fire_C,N,O,H,I,5,A - fire_C,N,O,H,I,5,L
        - fire_C,N,O,H,I,5,N  = 0
 c6386: fire_C,N,O,H,I,5,A  = 0
 c6387: fire_C,N,O,H,I,5,L  = 0
 c6388: fire_C,N,O,H,I,5,N  = 0
 c6389: fire_C,N,O,H,A,1,P  = 0
 c6390: fire_C,N,O,H,A,1,D  = 0
 c6391: fire_C,N,O,H,A,1,W  = 0
 c6392: fire_C,N,O,H,A,1,C  = 0
 c6393: fire_C,N,O,H,A,1,I  = 0
 c6394: - fire_C,N,O,H,A,1,P - fire_C,N,O,H,A,1,D - fire_C,N,O,H,A,1,W
        - fire_C,N,O,H,A,1,C - fire_C,N,O,H,A,1,I - fire_C,N,O,H,A,1,L
        - fire_C,N,O,H,A,1,N  = 0
 c6395: fire_C,N,O,H,A,1,L  = 0
 c6396: fire_C,N,O,H,A,1,N  = 0
 c6397: fire_C,N,O,H,A,2,P  = 0
 c6398: fire_C,N,O,H,A,2,D  = 0
 c6399: fire_C,N,O,H,A,2,W  = 0
 c6400: fire_C,N,O,H,A,2,C  = 0
 c6401: fire_C,N,O,H,A,2,I  = 0
 c6402: - fire_C,N,O,H,A,2,P - fire_C,N,O,H,A,2,D - fire_C,N,O,H,A,2,W
        - fire_C,N,O,H,A,2,C - fire_C,N,O,H,A,2,I - fire_C,N,O,H,A,2,L
        - fire_C,N,O,H,A,2,N  = 0
 c6403: fire_C,N,O,H,A,2,L  = 0
 c6404: fire_C,N,O,H,A,2,N  = 0
 c6405: fire_C,N,O,H,A,3,P  = 0
 c6406: fire_C,N,O,H,A,3,D  = 0
 c6407: fire_C,N,O,H,A,3,W  = 0
 c6408: fire_C,N,O,H,A,3,C  = 0
 c6409: fire_C,N,O,H,A,3,I  = 0
 c6410: - fire_C,N,O,H,A,3,P - fire_C,N,O,H,A,3,D - fire_C,N,O,H,A,3,W
        - fire_C,N,O,H,A,3,C - fire_C,N,O,H,A,3,I - fire_C,N,O,H,A,3,L
        - fire_C,N,O,H,A,3,N  = 0
 c6411: fire_C,N,O,H,A,3,L  = 0
 c6412: fire_C,N,O,H,A,3,N  = 0
 c6413: fire_C,N,O,H,A,4,P  = 0
 c6414: fire_C,N,O,H,A,4,D  = 0
 c6415: fire_C,N,O,H,A,4,W  = 0
 c6416: fire_C,N,O,H,A,4,C  = 0
 c6417: fire_C,N,O,H,A,4,I  = 0
 c6418: - fire_C,N,O,H,A,4,P - fire_C,N,O,H,A,4,D - fire_C,N,O,H,A,4,W
        - fire_C,N,O,H,A,4,C - fire_C,N,O,H,A,4,I - fire_C,N,O,H,A,4,L
        - fire_C,N,O,H,A,4,N  = 0
 c6419: fire_C,N,O,H,A,4,L  = 0
 c6420: fire_C,N,O,H,A,4,N  = 0
 c6421: fire_C,N,O,H,A,5,P  = 0
 c6422: fire_C,N,O,H,A,5,D  = 0
 c6423: fire_C,N,O,H,A,5,W  = 0
 c6424: fire_C,N,O,H,A,5,C  = 0
 c6425: fire_C,N,O,H,A,5,I  = 0
 c6426: - fire_C,N,O,H,A,5,P - fire_C,N,O,H,A,5,D - fire_C,N,O,H,A,5,W
        - fire_C,N,O,H,A,5,C - fire_C,N,O,H,A,5,I - fire_C,N,O,H,A,5,L
        - fire_C,N,O,H,A,5,N  = 0
 c6427: fire_C,N,O,H,A,5,L  = 0
 c6428: fire_C,N,O,H,A,5,N  = 0
 c6429: fire_C,N,O,H,L,1,P  = 0
 c6430: fire_C,N,O,H,L,1,D  = 0
 c6431: fire_C,N,O,H,L,1,W  = 0
 c6432: fire_C,N,O,H,L,1,C  = 0
 c6433: fire_C,N,O,H,L,1,I  = 0
 c6434: fire_C,N,O,H,L,1,A  = 0
 c6435: - fire_C,N,O,H,L,1,P - fire_C,N,O,H,L,1,D - fire_C,N,O,H,L,1,W
        - fire_C,N,O,H,L,1,C - fire_C,N,O,H,L,1,I - fire_C,N,O,H,L,1,A
        - fire_C,N,O,H,L,1,N  = 0
 c6436: fire_C,N,O,H,L,1,N  = 0
 c6437: fire_C,N,O,H,L,2,P  = 0
 c6438: fire_C,N,O,H,L,2,D  = 0
 c6439: fire_C,N,O,H,L,2,W  = 0
 c6440: fire_C,N,O,H,L,2,C  = 0
 c6441: fire_C,N,O,H,L,2,I  = 0
 c6442: fire_C,N,O,H,L,2,A  = 0
 c6443: - fire_C,N,O,H,L,2,P - fire_C,N,O,H,L,2,D - fire_C,N,O,H,L,2,W
        - fire_C,N,O,H,L,2,C - fire_C,N,O,H,L,2,I - fire_C,N,O,H,L,2,A
        - fire_C,N,O,H,L,2,N  = 0
 c6444: fire_C,N,O,H,L,2,N  = 0
 c6445: fire_C,N,O,H,L,3,P  = 0
 c6446: fire_C,N,O,H,L,3,D  = 0
 c6447: fire_C,N,O,H,L,3,W  = 0
 c6448: fire_C,N,O,H,L,3,C  = 0
 c6449: fire_C,N,O,H,L,3,I  = 0
 c6450: fire_C,N,O,H,L,3,A  = 0
 c6451: - fire_C,N,O,H,L,3,P - fire_C,N,O,H,L,3,D - fire_C,N,O,H,L,3,W
        - fire_C,N,O,H,L,3,C - fire_C,N,O,H,L,3,I - fire_C,N,O,H,L,3,A
        - fire_C,N,O,H,L,3,N  = 0
 c6452: fire_C,N,O,H,L,3,N  = 0
 c6453: fire_C,N,O,H,L,4,P  = 0
 c6454: fire_C,N,O,H,L,4,D  = 0
 c6455: fire_C,N,O,H,L,4,W  = 0
 c6456: fire_C,N,O,H,L,4,C  = 0
 c6457: fire_C,N,O,H,L,4,I  = 0
 c6458: fire_C,N,O,H,L,4,A  = 0
 c6459: - fire_C,N,O,H,L,4,P - fire_C,N,O,H,L,4,D - fire_C,N,O,H,L,4,W
        - fire_C,N,O,H,L,4,C - fire_C,N,O,H,L,4,I - fire_C,N,O,H,L,4,A
        - fire_C,N,O,H,L,4,N  = 0
 c6460: fire_C,N,O,H,L,4,N  = 0
 c6461: fire_C,N,O,H,L,5,P  = 0
 c6462: fire_C,N,O,H,L,5,D  = 0
 c6463: fire_C,N,O,H,L,5,W  = 0
 c6464: fire_C,N,O,H,L,5,C  = 0
 c6465: fire_C,N,O,H,L,5,I  = 0
 c6466: fire_C,N,O,H,L,5,A  = 0
 c6467: - fire_C,N,O,H,L,5,P - fire_C,N,O,H,L,5,D - fire_C,N,O,H,L,5,W
        - fire_C,N,O,H,L,5,C - fire_C,N,O,H,L,5,I - fire_C,N,O,H,L,5,A
        - fire_C,N,O,H,L,5,N  = 0
 c6468: fire_C,N,O,H,L,5,N  = 0
 c6469: fire_C,N,O,H,N,1,P  = 0
 c6470: fire_C,N,O,H,N,1,D  = 0
 c6471: fire_C,N,O,H,N,1,W  = 0
 c6472: fire_C,N,O,H,N,1,C  = 0
 c6473: fire_C,N,O,H,N,1,I  = 0
 c6474: fire_C,N,O,H,N,1,A  = 0
 c6475: fire_C,N,O,H,N,1,L  = 0
 c6476: - fire_C,N,O,H,N,1,P - fire_C,N,O,H,N,1,D - fire_C,N,O,H,N,1,W
        - fire_C,N,O,H,N,1,C - fire_C,N,O,H,N,1,I - fire_C,N,O,H,N,1,A
        - fire_C,N,O,H,N,1,L  = 0
 c6477: fire_C,N,O,H,N,2,P  = 0
 c6478: fire_C,N,O,H,N,2,D  = 0
 c6479: fire_C,N,O,H,N,2,W  = 0
 c6480: fire_C,N,O,H,N,2,C  = 0
 c6481: fire_C,N,O,H,N,2,I  = 0
 c6482: fire_C,N,O,H,N,2,A  = 0
 c6483: fire_C,N,O,H,N,2,L  = 0
 c6484: - fire_C,N,O,H,N,2,P - fire_C,N,O,H,N,2,D - fire_C,N,O,H,N,2,W
        - fire_C,N,O,H,N,2,C - fire_C,N,O,H,N,2,I - fire_C,N,O,H,N,2,A
        - fire_C,N,O,H,N,2,L  = 0
 c6485: fire_C,N,O,H,N,3,P  = 0
 c6486: fire_C,N,O,H,N,3,D  = 0
 c6487: fire_C,N,O,H,N,3,W  = 0
 c6488: fire_C,N,O,H,N,3,C  = 0
 c6489: fire_C,N,O,H,N,3,I  = 0
 c6490: fire_C,N,O,H,N,3,A  = 0
 c6491: fire_C,N,O,H,N,3,L  = 0
 c6492: - fire_C,N,O,H,N,3,P - fire_C,N,O,H,N,3,D - fire_C,N,O,H,N,3,W
        - fire_C,N,O,H,N,3,C - fire_C,N,O,H,N,3,I - fire_C,N,O,H,N,3,A
        - fire_C,N,O,H,N,3,L  = 0
 c6493: fire_C,N,O,H,N,4,P  = 0
 c6494: fire_C,N,O,H,N,4,D  = 0
 c6495: fire_C,N,O,H,N,4,W  = 0
 c6496: fire_C,N,O,H,N,4,C  = 0
 c6497: fire_C,N,O,H,N,4,I  = 0
 c6498: fire_C,N,O,H,N,4,A  = 0
 c6499: fire_C,N,O,H,N,4,L  = 0
 c6500: - fire_C,N,O,H,N,4,P - fire_C,N,O,H,N,4,D - fire_C,N,O,H,N,4,W
        - fire_C,N,O,H,N,4,C - fire_C,N,O,H,N,4,I - fire_C,N,O,H,N,4,A
        - fire_C,N,O,H,N,4,L  = 0
 c6501: fire_C,N,O,H,N,5,P  = 0
 c6502: fire_C,N,O,H,N,5,D  = 0
 c6503: fire_C,N,O,H,N,5,W  = 0
 c6504: fire_C,N,O,H,N,5,C  = 0
 c6505: fire_C,N,O,H,N,5,I  = 0
 c6506: fire_C,N,O,H,N,5,A  = 0
 c6507: fire_C,N,O,H,N,5,L  = 0
 c6508: - fire_C,N,O,H,N,5,P - fire_C,N,O,H,N,5,D - fire_C,N,O,H,N,5,W
        - fire_C,N,O,H,N,5,C - fire_C,N,O,H,N,5,I - fire_C,N,O,H,N,5,A
        - fire_C,N,O,H,N,5,L  = 0
 c6509: 0.9 fire_C,N,O,C,P,1,P - 0.1 fire_C,N,O,C,P,1,D
        - 0.1 fire_C,N,O,C,P,1,W - 0.1 fire_C,N,O,C,P,1,C
        - 0.1 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        - 0.1 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6510: - 0.1 fire_C,N,O,C,P,1,P + 0.9 fire_C,N,O,C,P,1,D
        - 0.1 fire_C,N,O,C,P,1,W - 0.1 fire_C,N,O,C,P,1,C
        - 0.1 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        - 0.1 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6511: - 0.1 fire_C,N,O,C,P,1,P - 0.1 fire_C,N,O,C,P,1,D
        + 0.9 fire_C,N,O,C,P,1,W - 0.1 fire_C,N,O,C,P,1,C
        - 0.1 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        - 0.1 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6512: - 0.1 fire_C,N,O,C,P,1,P - 0.1 fire_C,N,O,C,P,1,D
        - 0.1 fire_C,N,O,C,P,1,W + 0.9 fire_C,N,O,C,P,1,C
        - 0.1 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        - 0.1 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6513: - 0.1 fire_C,N,O,C,P,1,P - 0.1 fire_C,N,O,C,P,1,D
        - 0.1 fire_C,N,O,C,P,1,W - 0.1 fire_C,N,O,C,P,1,C
        + 0.9 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        - 0.1 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6514: - 0.2 fire_C,N,O,C,P,1,P - 0.2 fire_C,N,O,C,P,1,D
        - 0.2 fire_C,N,O,C,P,1,W - 0.2 fire_C,N,O,C,P,1,C
        - 0.2 fire_C,N,O,C,P,1,I + 0.8 fire_C,N,O,C,P,1,A
        - 0.2 fire_C,N,O,C,P,1,L - 0.2 fire_C,N,O,C,P,1,N  = 0
 c6515: - 0.1 fire_C,N,O,C,P,1,P - 0.1 fire_C,N,O,C,P,1,D
        - 0.1 fire_C,N,O,C,P,1,W - 0.1 fire_C,N,O,C,P,1,C
        - 0.1 fire_C,N,O,C,P,1,I - 0.1 fire_C,N,O,C,P,1,A
        + 0.9 fire_C,N,O,C,P,1,L - 0.1 fire_C,N,O,C,P,1,N  = 0
 c6516: - 0.2 fire_C,N,O,C,P,1,P - 0.2 fire_C,N,O,C,P,1,D
        - 0.2 fire_C,N,O,C,P,1,W - 0.2 fire_C,N,O,C,P,1,C
        - 0.2 fire_C,N,O,C,P,1,I - 0.2 fire_C,N,O,C,P,1,A
        - 0.2 fire_C,N,O,C,P,1,L + 0.8 fire_C,N,O,C,P,1,N  = 0
 c6517: 0.9 fire_C,N,O,C,P,2,P - 0.1 fire_C,N,O,C,P,2,D
        - 0.1 fire_C,N,O,C,P,2,W - 0.1 fire_C,N,O,C,P,2,C
        - 0.1 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        - 0.1 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6518: - 0.1 fire_C,N,O,C,P,2,P + 0.9 fire_C,N,O,C,P,2,D
        - 0.1 fire_C,N,O,C,P,2,W - 0.1 fire_C,N,O,C,P,2,C
        - 0.1 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        - 0.1 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6519: - 0.1 fire_C,N,O,C,P,2,P - 0.1 fire_C,N,O,C,P,2,D
        + 0.9 fire_C,N,O,C,P,2,W - 0.1 fire_C,N,O,C,P,2,C
        - 0.1 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        - 0.1 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6520: - 0.1 fire_C,N,O,C,P,2,P - 0.1 fire_C,N,O,C,P,2,D
        - 0.1 fire_C,N,O,C,P,2,W + 0.9 fire_C,N,O,C,P,2,C
        - 0.1 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        - 0.1 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6521: - 0.1 fire_C,N,O,C,P,2,P - 0.1 fire_C,N,O,C,P,2,D
        - 0.1 fire_C,N,O,C,P,2,W - 0.1 fire_C,N,O,C,P,2,C
        + 0.9 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        - 0.1 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6522: - 0.2 fire_C,N,O,C,P,2,P - 0.2 fire_C,N,O,C,P,2,D
        - 0.2 fire_C,N,O,C,P,2,W - 0.2 fire_C,N,O,C,P,2,C
        - 0.2 fire_C,N,O,C,P,2,I + 0.8 fire_C,N,O,C,P,2,A
        - 0.2 fire_C,N,O,C,P,2,L - 0.2 fire_C,N,O,C,P,2,N  = 0
 c6523: - 0.1 fire_C,N,O,C,P,2,P - 0.1 fire_C,N,O,C,P,2,D
        - 0.1 fire_C,N,O,C,P,2,W - 0.1 fire_C,N,O,C,P,2,C
        - 0.1 fire_C,N,O,C,P,2,I - 0.1 fire_C,N,O,C,P,2,A
        + 0.9 fire_C,N,O,C,P,2,L - 0.1 fire_C,N,O,C,P,2,N  = 0
 c6524: - 0.2 fire_C,N,O,C,P,2,P - 0.2 fire_C,N,O,C,P,2,D
        - 0.2 fire_C,N,O,C,P,2,W - 0.2 fire_C,N,O,C,P,2,C
        - 0.2 fire_C,N,O,C,P,2,I - 0.2 fire_C,N,O,C,P,2,A
        - 0.2 fire_C,N,O,C,P,2,L + 0.8 fire_C,N,O,C,P,2,N  = 0
 c6525: 0.9 fire_C,N,O,C,P,3,P - 0.1 fire_C,N,O,C,P,3,D
        - 0.1 fire_C,N,O,C,P,3,W - 0.1 fire_C,N,O,C,P,3,C
        - 0.1 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        - 0.1 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6526: - 0.1 fire_C,N,O,C,P,3,P + 0.9 fire_C,N,O,C,P,3,D
        - 0.1 fire_C,N,O,C,P,3,W - 0.1 fire_C,N,O,C,P,3,C
        - 0.1 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        - 0.1 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6527: - 0.1 fire_C,N,O,C,P,3,P - 0.1 fire_C,N,O,C,P,3,D
        + 0.9 fire_C,N,O,C,P,3,W - 0.1 fire_C,N,O,C,P,3,C
        - 0.1 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        - 0.1 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6528: - 0.1 fire_C,N,O,C,P,3,P - 0.1 fire_C,N,O,C,P,3,D
        - 0.1 fire_C,N,O,C,P,3,W + 0.9 fire_C,N,O,C,P,3,C
        - 0.1 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        - 0.1 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6529: - 0.1 fire_C,N,O,C,P,3,P - 0.1 fire_C,N,O,C,P,3,D
        - 0.1 fire_C,N,O,C,P,3,W - 0.1 fire_C,N,O,C,P,3,C
        + 0.9 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        - 0.1 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6530: - 0.2 fire_C,N,O,C,P,3,P - 0.2 fire_C,N,O,C,P,3,D
        - 0.2 fire_C,N,O,C,P,3,W - 0.2 fire_C,N,O,C,P,3,C
        - 0.2 fire_C,N,O,C,P,3,I + 0.8 fire_C,N,O,C,P,3,A
        - 0.2 fire_C,N,O,C,P,3,L - 0.2 fire_C,N,O,C,P,3,N  = 0
 c6531: - 0.1 fire_C,N,O,C,P,3,P - 0.1 fire_C,N,O,C,P,3,D
        - 0.1 fire_C,N,O,C,P,3,W - 0.1 fire_C,N,O,C,P,3,C
        - 0.1 fire_C,N,O,C,P,3,I - 0.1 fire_C,N,O,C,P,3,A
        + 0.9 fire_C,N,O,C,P,3,L - 0.1 fire_C,N,O,C,P,3,N  = 0
 c6532: - 0.2 fire_C,N,O,C,P,3,P - 0.2 fire_C,N,O,C,P,3,D
        - 0.2 fire_C,N,O,C,P,3,W - 0.2 fire_C,N,O,C,P,3,C
        - 0.2 fire_C,N,O,C,P,3,I - 0.2 fire_C,N,O,C,P,3,A
        - 0.2 fire_C,N,O,C,P,3,L + 0.8 fire_C,N,O,C,P,3,N  = 0
 c6533: 0.9 fire_C,N,O,C,P,4,P - 0.1 fire_C,N,O,C,P,4,D
        - 0.1 fire_C,N,O,C,P,4,W - 0.1 fire_C,N,O,C,P,4,C
        - 0.1 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        - 0.1 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6534: - 0.1 fire_C,N,O,C,P,4,P + 0.9 fire_C,N,O,C,P,4,D
        - 0.1 fire_C,N,O,C,P,4,W - 0.1 fire_C,N,O,C,P,4,C
        - 0.1 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        - 0.1 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6535: - 0.1 fire_C,N,O,C,P,4,P - 0.1 fire_C,N,O,C,P,4,D
        + 0.9 fire_C,N,O,C,P,4,W - 0.1 fire_C,N,O,C,P,4,C
        - 0.1 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        - 0.1 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6536: - 0.1 fire_C,N,O,C,P,4,P - 0.1 fire_C,N,O,C,P,4,D
        - 0.1 fire_C,N,O,C,P,4,W + 0.9 fire_C,N,O,C,P,4,C
        - 0.1 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        - 0.1 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6537: - 0.1 fire_C,N,O,C,P,4,P - 0.1 fire_C,N,O,C,P,4,D
        - 0.1 fire_C,N,O,C,P,4,W - 0.1 fire_C,N,O,C,P,4,C
        + 0.9 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        - 0.1 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6538: - 0.2 fire_C,N,O,C,P,4,P - 0.2 fire_C,N,O,C,P,4,D
        - 0.2 fire_C,N,O,C,P,4,W - 0.2 fire_C,N,O,C,P,4,C
        - 0.2 fire_C,N,O,C,P,4,I + 0.8 fire_C,N,O,C,P,4,A
        - 0.2 fire_C,N,O,C,P,4,L - 0.2 fire_C,N,O,C,P,4,N  = 0
 c6539: - 0.1 fire_C,N,O,C,P,4,P - 0.1 fire_C,N,O,C,P,4,D
        - 0.1 fire_C,N,O,C,P,4,W - 0.1 fire_C,N,O,C,P,4,C
        - 0.1 fire_C,N,O,C,P,4,I - 0.1 fire_C,N,O,C,P,4,A
        + 0.9 fire_C,N,O,C,P,4,L - 0.1 fire_C,N,O,C,P,4,N  = 0
 c6540: - 0.2 fire_C,N,O,C,P,4,P - 0.2 fire_C,N,O,C,P,4,D
        - 0.2 fire_C,N,O,C,P,4,W - 0.2 fire_C,N,O,C,P,4,C
        - 0.2 fire_C,N,O,C,P,4,I - 0.2 fire_C,N,O,C,P,4,A
        - 0.2 fire_C,N,O,C,P,4,L + 0.8 fire_C,N,O,C,P,4,N  = 0
 c6541: 0.9 fire_C,N,O,C,P,5,P - 0.1 fire_C,N,O,C,P,5,D
        - 0.1 fire_C,N,O,C,P,5,W - 0.1 fire_C,N,O,C,P,5,C
        - 0.1 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        - 0.1 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6542: - 0.1 fire_C,N,O,C,P,5,P + 0.9 fire_C,N,O,C,P,5,D
        - 0.1 fire_C,N,O,C,P,5,W - 0.1 fire_C,N,O,C,P,5,C
        - 0.1 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        - 0.1 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6543: - 0.1 fire_C,N,O,C,P,5,P - 0.1 fire_C,N,O,C,P,5,D
        + 0.9 fire_C,N,O,C,P,5,W - 0.1 fire_C,N,O,C,P,5,C
        - 0.1 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        - 0.1 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6544: - 0.1 fire_C,N,O,C,P,5,P - 0.1 fire_C,N,O,C,P,5,D
        - 0.1 fire_C,N,O,C,P,5,W + 0.9 fire_C,N,O,C,P,5,C
        - 0.1 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        - 0.1 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6545: - 0.1 fire_C,N,O,C,P,5,P - 0.1 fire_C,N,O,C,P,5,D
        - 0.1 fire_C,N,O,C,P,5,W - 0.1 fire_C,N,O,C,P,5,C
        + 0.9 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        - 0.1 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6546: - 0.2 fire_C,N,O,C,P,5,P - 0.2 fire_C,N,O,C,P,5,D
        - 0.2 fire_C,N,O,C,P,5,W - 0.2 fire_C,N,O,C,P,5,C
        - 0.2 fire_C,N,O,C,P,5,I + 0.8 fire_C,N,O,C,P,5,A
        - 0.2 fire_C,N,O,C,P,5,L - 0.2 fire_C,N,O,C,P,5,N  = 0
 c6547: - 0.1 fire_C,N,O,C,P,5,P - 0.1 fire_C,N,O,C,P,5,D
        - 0.1 fire_C,N,O,C,P,5,W - 0.1 fire_C,N,O,C,P,5,C
        - 0.1 fire_C,N,O,C,P,5,I - 0.1 fire_C,N,O,C,P,5,A
        + 0.9 fire_C,N,O,C,P,5,L - 0.1 fire_C,N,O,C,P,5,N  = 0
 c6548: - 0.2 fire_C,N,O,C,P,5,P - 0.2 fire_C,N,O,C,P,5,D
        - 0.2 fire_C,N,O,C,P,5,W - 0.2 fire_C,N,O,C,P,5,C
        - 0.2 fire_C,N,O,C,P,5,I - 0.2 fire_C,N,O,C,P,5,A
        - 0.2 fire_C,N,O,C,P,5,L + 0.8 fire_C,N,O,C,P,5,N  = 0
 c6549: 0.5 fire_C,N,O,C,D,1,P - 0.5 fire_C,N,O,C,D,1,D
        - 0.5 fire_C,N,O,C,D,1,W - 0.5 fire_C,N,O,C,D,1,C
        - 0.5 fire_C,N,O,C,D,1,I - 0.5 fire_C,N,O,C,D,1,A
        - 0.5 fire_C,N,O,C,D,1,L - 0.5 fire_C,N,O,C,D,1,N  = 0
 c6550: - 0.5 fire_C,N,O,C,D,1,P + 0.5 fire_C,N,O,C,D,1,D
        - 0.5 fire_C,N,O,C,D,1,W - 0.5 fire_C,N,O,C,D,1,C
        - 0.5 fire_C,N,O,C,D,1,I - 0.5 fire_C,N,O,C,D,1,A
        - 0.5 fire_C,N,O,C,D,1,L - 0.5 fire_C,N,O,C,D,1,N  = 0
 c6551: fire_C,N,O,C,D,1,W  = 0
 c6552: fire_C,N,O,C,D,1,C  = 0
 c6553: fire_C,N,O,C,D,1,I  = 0
 c6554: fire_C,N,O,C,D,1,A  = 0
 c6555: fire_C,N,O,C,D,1,L  = 0
 c6556: fire_C,N,O,C,D,1,N  = 0
 c6557: 0.5 fire_C,N,O,C,D,2,P - 0.5 fire_C,N,O,C,D,2,D
        - 0.5 fire_C,N,O,C,D,2,W - 0.5 fire_C,N,O,C,D,2,C
        - 0.5 fire_C,N,O,C,D,2,I - 0.5 fire_C,N,O,C,D,2,A
        - 0.5 fire_C,N,O,C,D,2,L - 0.5 fire_C,N,O,C,D,2,N  = 0
 c6558: - 0.5 fire_C,N,O,C,D,2,P + 0.5 fire_C,N,O,C,D,2,D
        - 0.5 fire_C,N,O,C,D,2,W - 0.5 fire_C,N,O,C,D,2,C
        - 0.5 fire_C,N,O,C,D,2,I - 0.5 fire_C,N,O,C,D,2,A
        - 0.5 fire_C,N,O,C,D,2,L - 0.5 fire_C,N,O,C,D,2,N  = 0
 c6559: fire_C,N,O,C,D,2,W  = 0
 c6560: fire_C,N,O,C,D,2,C  = 0
 c6561: fire_C,N,O,C,D,2,I  = 0
 c6562: fire_C,N,O,C,D,2,A  = 0
 c6563: fire_C,N,O,C,D,2,L  = 0
 c6564: fire_C,N,O,C,D,2,N  = 0
 c6565: 0.5 fire_C,N,O,C,D,3,P - 0.5 fire_C,N,O,C,D,3,D
        - 0.5 fire_C,N,O,C,D,3,W - 0.5 fire_C,N,O,C,D,3,C
        - 0.5 fire_C,N,O,C,D,3,I - 0.5 fire_C,N,O,C,D,3,A
        - 0.5 fire_C,N,O,C,D,3,L - 0.5 fire_C,N,O,C,D,3,N  = 0
 c6566: - 0.5 fire_C,N,O,C,D,3,P + 0.5 fire_C,N,O,C,D,3,D
        - 0.5 fire_C,N,O,C,D,3,W - 0.5 fire_C,N,O,C,D,3,C
        - 0.5 fire_C,N,O,C,D,3,I - 0.5 fire_C,N,O,C,D,3,A
        - 0.5 fire_C,N,O,C,D,3,L - 0.5 fire_C,N,O,C,D,3,N  = 0
 c6567: fire_C,N,O,C,D,3,W  = 0
 c6568: fire_C,N,O,C,D,3,C  = 0
 c6569: fire_C,N,O,C,D,3,I  = 0
 c6570: fire_C,N,O,C,D,3,A  = 0
 c6571: fire_C,N,O,C,D,3,L  = 0
 c6572: fire_C,N,O,C,D,3,N  = 0
 c6573: 0.5 fire_C,N,O,C,D,4,P - 0.5 fire_C,N,O,C,D,4,D
        - 0.5 fire_C,N,O,C,D,4,W - 0.5 fire_C,N,O,C,D,4,C
        - 0.5 fire_C,N,O,C,D,4,I - 0.5 fire_C,N,O,C,D,4,A
        - 0.5 fire_C,N,O,C,D,4,L - 0.5 fire_C,N,O,C,D,4,N  = 0
 c6574: - 0.5 fire_C,N,O,C,D,4,P + 0.5 fire_C,N,O,C,D,4,D
        - 0.5 fire_C,N,O,C,D,4,W - 0.5 fire_C,N,O,C,D,4,C
        - 0.5 fire_C,N,O,C,D,4,I - 0.5 fire_C,N,O,C,D,4,A
        - 0.5 fire_C,N,O,C,D,4,L - 0.5 fire_C,N,O,C,D,4,N  = 0
 c6575: fire_C,N,O,C,D,4,W  = 0
 c6576: fire_C,N,O,C,D,4,C  = 0
 c6577: fire_C,N,O,C,D,4,I  = 0
 c6578: fire_C,N,O,C,D,4,A  = 0
 c6579: fire_C,N,O,C,D,4,L  = 0
 c6580: fire_C,N,O,C,D,4,N  = 0
 c6581: 0.5 fire_C,N,O,C,D,5,P - 0.5 fire_C,N,O,C,D,5,D
        - 0.5 fire_C,N,O,C,D,5,W - 0.5 fire_C,N,O,C,D,5,C
        - 0.5 fire_C,N,O,C,D,5,I - 0.5 fire_C,N,O,C,D,5,A
        - 0.5 fire_C,N,O,C,D,5,L - 0.5 fire_C,N,O,C,D,5,N  = 0
 c6582: - 0.5 fire_C,N,O,C,D,5,P + 0.5 fire_C,N,O,C,D,5,D
        - 0.5 fire_C,N,O,C,D,5,W - 0.5 fire_C,N,O,C,D,5,C
        - 0.5 fire_C,N,O,C,D,5,I - 0.5 fire_C,N,O,C,D,5,A
        - 0.5 fire_C,N,O,C,D,5,L - 0.5 fire_C,N,O,C,D,5,N  = 0
 c6583: fire_C,N,O,C,D,5,W  = 0
 c6584: fire_C,N,O,C,D,5,C  = 0
 c6585: fire_C,N,O,C,D,5,I  = 0
 c6586: fire_C,N,O,C,D,5,A  = 0
 c6587: fire_C,N,O,C,D,5,L  = 0
 c6588: fire_C,N,O,C,D,5,N  = 0
 c6589: fire_C,N,O,C,W,1,P  = 0
 c6590: fire_C,N,O,C,W,1,D  = 0
 c6591: - fire_C,N,O,C,W,1,P - fire_C,N,O,C,W,1,D - fire_C,N,O,C,W,1,C
        - fire_C,N,O,C,W,1,I - fire_C,N,O,C,W,1,A - fire_C,N,O,C,W,1,L
        - fire_C,N,O,C,W,1,N  = 0
 c6592: fire_C,N,O,C,W,1,C  = 0
 c6593: fire_C,N,O,C,W,1,I  = 0
 c6594: fire_C,N,O,C,W,1,A  = 0
 c6595: fire_C,N,O,C,W,1,L  = 0
 c6596: fire_C,N,O,C,W,1,N  = 0
 c6597: fire_C,N,O,C,W,2,P  = 0
 c6598: fire_C,N,O,C,W,2,D  = 0
 c6599: - fire_C,N,O,C,W,2,P - fire_C,N,O,C,W,2,D - fire_C,N,O,C,W,2,C
        - fire_C,N,O,C,W,2,I - fire_C,N,O,C,W,2,A - fire_C,N,O,C,W,2,L
        - fire_C,N,O,C,W,2,N  = 0
 c6600: fire_C,N,O,C,W,2,C  = 0
 c6601: fire_C,N,O,C,W,2,I  = 0
 c6602: fire_C,N,O,C,W,2,A  = 0
 c6603: fire_C,N,O,C,W,2,L  = 0
 c6604: fire_C,N,O,C,W,2,N  = 0
 c6605: fire_C,N,O,C,W,3,P  = 0
 c6606: fire_C,N,O,C,W,3,D  = 0
 c6607: - fire_C,N,O,C,W,3,P - fire_C,N,O,C,W,3,D - fire_C,N,O,C,W,3,C
        - fire_C,N,O,C,W,3,I - fire_C,N,O,C,W,3,A - fire_C,N,O,C,W,3,L
        - fire_C,N,O,C,W,3,N  = 0
 c6608: fire_C,N,O,C,W,3,C  = 0
 c6609: fire_C,N,O,C,W,3,I  = 0
 c6610: fire_C,N,O,C,W,3,A  = 0
 c6611: fire_C,N,O,C,W,3,L  = 0
 c6612: fire_C,N,O,C,W,3,N  = 0
 c6613: fire_C,N,O,C,W,4,P  = 0
 c6614: fire_C,N,O,C,W,4,D  = 0
 c6615: - fire_C,N,O,C,W,4,P - fire_C,N,O,C,W,4,D - fire_C,N,O,C,W,4,C
        - fire_C,N,O,C,W,4,I - fire_C,N,O,C,W,4,A - fire_C,N,O,C,W,4,L
        - fire_C,N,O,C,W,4,N  = 0
 c6616: fire_C,N,O,C,W,4,C  = 0
 c6617: fire_C,N,O,C,W,4,I  = 0
 c6618: fire_C,N,O,C,W,4,A  = 0
 c6619: fire_C,N,O,C,W,4,L  = 0
 c6620: fire_C,N,O,C,W,4,N  = 0
 c6621: fire_C,N,O,C,W,5,P  = 0
 c6622: fire_C,N,O,C,W,5,D  = 0
 c6623: - fire_C,N,O,C,W,5,P - fire_C,N,O,C,W,5,D - fire_C,N,O,C,W,5,C
        - fire_C,N,O,C,W,5,I - fire_C,N,O,C,W,5,A - fire_C,N,O,C,W,5,L
        - fire_C,N,O,C,W,5,N  = 0
 c6624: fire_C,N,O,C,W,5,C  = 0
 c6625: fire_C,N,O,C,W,5,I  = 0
 c6626: fire_C,N,O,C,W,5,A  = 0
 c6627: fire_C,N,O,C,W,5,L  = 0
 c6628: fire_C,N,O,C,W,5,N  = 0
 c6629: fire_C,N,O,C,C,1,P  = 0
 c6630: fire_C,N,O,C,C,1,D  = 0
 c6631: fire_C,N,O,C,C,1,W  = 0
 c6632: - fire_C,N,O,C,C,1,P - fire_C,N,O,C,C,1,D - fire_C,N,O,C,C,1,W
        - fire_C,N,O,C,C,1,I - fire_C,N,O,C,C,1,A - fire_C,N,O,C,C,1,L
        - fire_C,N,O,C,C,1,N  = 0
 c6633: fire_C,N,O,C,C,1,I  = 0
 c6634: fire_C,N,O,C,C,1,A  = 0
 c6635: fire_C,N,O,C,C,1,L  = 0
 c6636: fire_C,N,O,C,C,1,N  = 0
 c6637: fire_C,N,O,C,C,2,P  = 0
 c6638: fire_C,N,O,C,C,2,D  = 0
 c6639: fire_C,N,O,C,C,2,W  = 0
 c6640: - fire_C,N,O,C,C,2,P - fire_C,N,O,C,C,2,D - fire_C,N,O,C,C,2,W
        - fire_C,N,O,C,C,2,I - fire_C,N,O,C,C,2,A - fire_C,N,O,C,C,2,L
        - fire_C,N,O,C,C,2,N  = 0
 c6641: fire_C,N,O,C,C,2,I  = 0
 c6642: fire_C,N,O,C,C,2,A  = 0
 c6643: fire_C,N,O,C,C,2,L  = 0
 c6644: fire_C,N,O,C,C,2,N  = 0
 c6645: fire_C,N,O,C,C,3,P  = 0
 c6646: fire_C,N,O,C,C,3,D  = 0
 c6647: fire_C,N,O,C,C,3,W  = 0
 c6648: - fire_C,N,O,C,C,3,P - fire_C,N,O,C,C,3,D - fire_C,N,O,C,C,3,W
        - fire_C,N,O,C,C,3,I - fire_C,N,O,C,C,3,A - fire_C,N,O,C,C,3,L
        - fire_C,N,O,C,C,3,N  = 0
 c6649: fire_C,N,O,C,C,3,I  = 0
 c6650: fire_C,N,O,C,C,3,A  = 0
 c6651: fire_C,N,O,C,C,3,L  = 0
 c6652: fire_C,N,O,C,C,3,N  = 0
 c6653: fire_C,N,O,C,C,4,P  = 0
 c6654: fire_C,N,O,C,C,4,D  = 0
 c6655: fire_C,N,O,C,C,4,W  = 0
 c6656: - fire_C,N,O,C,C,4,P - fire_C,N,O,C,C,4,D - fire_C,N,O,C,C,4,W
        - fire_C,N,O,C,C,4,I - fire_C,N,O,C,C,4,A - fire_C,N,O,C,C,4,L
        - fire_C,N,O,C,C,4,N  = 0
 c6657: fire_C,N,O,C,C,4,I  = 0
 c6658: fire_C,N,O,C,C,4,A  = 0
 c6659: fire_C,N,O,C,C,4,L  = 0
 c6660: fire_C,N,O,C,C,4,N  = 0
 c6661: fire_C,N,O,C,C,5,P  = 0
 c6662: fire_C,N,O,C,C,5,D  = 0
 c6663: fire_C,N,O,C,C,5,W  = 0
 c6664: - fire_C,N,O,C,C,5,P - fire_C,N,O,C,C,5,D - fire_C,N,O,C,C,5,W
        - fire_C,N,O,C,C,5,I - fire_C,N,O,C,C,5,A - fire_C,N,O,C,C,5,L
        - fire_C,N,O,C,C,5,N  = 0
 c6665: fire_C,N,O,C,C,5,I  = 0
 c6666: fire_C,N,O,C,C,5,A  = 0
 c6667: fire_C,N,O,C,C,5,L  = 0
 c6668: fire_C,N,O,C,C,5,N  = 0
 c6669: fire_C,N,O,C,I,1,P  = 0
 c6670: fire_C,N,O,C,I,1,D  = 0
 c6671: fire_C,N,O,C,I,1,W  = 0
 c6672: fire_C,N,O,C,I,1,C  = 0
 c6673: - fire_C,N,O,C,I,1,P - fire_C,N,O,C,I,1,D - fire_C,N,O,C,I,1,W
        - fire_C,N,O,C,I,1,C - fire_C,N,O,C,I,1,A - fire_C,N,O,C,I,1,L
        - fire_C,N,O,C,I,1,N  = 0
 c6674: fire_C,N,O,C,I,1,A  = 0
 c6675: fire_C,N,O,C,I,1,L  = 0
 c6676: fire_C,N,O,C,I,1,N  = 0
 c6677: fire_C,N,O,C,I,2,P  = 0
 c6678: fire_C,N,O,C,I,2,D  = 0
 c6679: fire_C,N,O,C,I,2,W  = 0
 c6680: fire_C,N,O,C,I,2,C  = 0
 c6681: - fire_C,N,O,C,I,2,P - fire_C,N,O,C,I,2,D - fire_C,N,O,C,I,2,W
        - fire_C,N,O,C,I,2,C - fire_C,N,O,C,I,2,A - fire_C,N,O,C,I,2,L
        - fire_C,N,O,C,I,2,N  = 0
 c6682: fire_C,N,O,C,I,2,A  = 0
 c6683: fire_C,N,O,C,I,2,L  = 0
 c6684: fire_C,N,O,C,I,2,N  = 0
 c6685: fire_C,N,O,C,I,3,P  = 0
 c6686: fire_C,N,O,C,I,3,D  = 0
 c6687: fire_C,N,O,C,I,3,W  = 0
 c6688: fire_C,N,O,C,I,3,C  = 0
 c6689: - fire_C,N,O,C,I,3,P - fire_C,N,O,C,I,3,D - fire_C,N,O,C,I,3,W
        - fire_C,N,O,C,I,3,C - fire_C,N,O,C,I,3,A - fire_C,N,O,C,I,3,L
        - fire_C,N,O,C,I,3,N  = 0
 c6690: fire_C,N,O,C,I,3,A  = 0
 c6691: fire_C,N,O,C,I,3,L  = 0
 c6692: fire_C,N,O,C,I,3,N  = 0
 c6693: fire_C,N,O,C,I,4,P  = 0
 c6694: fire_C,N,O,C,I,4,D  = 0
 c6695: fire_C,N,O,C,I,4,W  = 0
 c6696: fire_C,N,O,C,I,4,C  = 0
 c6697: - fire_C,N,O,C,I,4,P - fire_C,N,O,C,I,4,D - fire_C,N,O,C,I,4,W
        - fire_C,N,O,C,I,4,C - fire_C,N,O,C,I,4,A - fire_C,N,O,C,I,4,L
        - fire_C,N,O,C,I,4,N  = 0
 c6698: fire_C,N,O,C,I,4,A  = 0
 c6699: fire_C,N,O,C,I,4,L  = 0
 c6700: fire_C,N,O,C,I,4,N  = 0
 c6701: fire_C,N,O,C,I,5,P  = 0
 c6702: fire_C,N,O,C,I,5,D  = 0
 c6703: fire_C,N,O,C,I,5,W  = 0
 c6704: fire_C,N,O,C,I,5,C  = 0
 c6705: - fire_C,N,O,C,I,5,P - fire_C,N,O,C,I,5,D - fire_C,N,O,C,I,5,W
        - fire_C,N,O,C,I,5,C - fire_C,N,O,C,I,5,A - fire_C,N,O,C,I,5,L
        - fire_C,N,O,C,I,5,N  = 0
 c6706: fire_C,N,O,C,I,5,A  = 0
 c6707: fire_C,N,O,C,I,5,L  = 0
 c6708: fire_C,N,O,C,I,5,N  = 0
 c6709: fire_C,N,O,C,A,1,P  = 0
 c6710: fire_C,N,O,C,A,1,D  = 0
 c6711: fire_C,N,O,C,A,1,W  = 0
 c6712: fire_C,N,O,C,A,1,C  = 0
 c6713: fire_C,N,O,C,A,1,I  = 0
 c6714: - fire_C,N,O,C,A,1,P - fire_C,N,O,C,A,1,D - fire_C,N,O,C,A,1,W
        - fire_C,N,O,C,A,1,C - fire_C,N,O,C,A,1,I - fire_C,N,O,C,A,1,L
        - fire_C,N,O,C,A,1,N  = 0
 c6715: fire_C,N,O,C,A,1,L  = 0
 c6716: fire_C,N,O,C,A,1,N  = 0
 c6717: fire_C,N,O,C,A,2,P  = 0
 c6718: fire_C,N,O,C,A,2,D  = 0
 c6719: fire_C,N,O,C,A,2,W  = 0
 c6720: fire_C,N,O,C,A,2,C  = 0
 c6721: fire_C,N,O,C,A,2,I  = 0
 c6722: - fire_C,N,O,C,A,2,P - fire_C,N,O,C,A,2,D - fire_C,N,O,C,A,2,W
        - fire_C,N,O,C,A,2,C - fire_C,N,O,C,A,2,I - fire_C,N,O,C,A,2,L
        - fire_C,N,O,C,A,2,N  = 0
 c6723: fire_C,N,O,C,A,2,L  = 0
 c6724: fire_C,N,O,C,A,2,N  = 0
 c6725: fire_C,N,O,C,A,3,P  = 0
 c6726: fire_C,N,O,C,A,3,D  = 0
 c6727: fire_C,N,O,C,A,3,W  = 0
 c6728: fire_C,N,O,C,A,3,C  = 0
 c6729: fire_C,N,O,C,A,3,I  = 0
 c6730: - fire_C,N,O,C,A,3,P - fire_C,N,O,C,A,3,D - fire_C,N,O,C,A,3,W
        - fire_C,N,O,C,A,3,C - fire_C,N,O,C,A,3,I - fire_C,N,O,C,A,3,L
        - fire_C,N,O,C,A,3,N  = 0
 c6731: fire_C,N,O,C,A,3,L  = 0
 c6732: fire_C,N,O,C,A,3,N  = 0
 c6733: fire_C,N,O,C,A,4,P  = 0
 c6734: fire_C,N,O,C,A,4,D  = 0
 c6735: fire_C,N,O,C,A,4,W  = 0
 c6736: fire_C,N,O,C,A,4,C  = 0
 c6737: fire_C,N,O,C,A,4,I  = 0
 c6738: - fire_C,N,O,C,A,4,P - fire_C,N,O,C,A,4,D - fire_C,N,O,C,A,4,W
        - fire_C,N,O,C,A,4,C - fire_C,N,O,C,A,4,I - fire_C,N,O,C,A,4,L
        - fire_C,N,O,C,A,4,N  = 0
 c6739: fire_C,N,O,C,A,4,L  = 0
 c6740: fire_C,N,O,C,A,4,N  = 0
 c6741: fire_C,N,O,C,A,5,P  = 0
 c6742: fire_C,N,O,C,A,5,D  = 0
 c6743: fire_C,N,O,C,A,5,W  = 0
 c6744: fire_C,N,O,C,A,5,C  = 0
 c6745: fire_C,N,O,C,A,5,I  = 0
 c6746: - fire_C,N,O,C,A,5,P - fire_C,N,O,C,A,5,D - fire_C,N,O,C,A,5,W
        - fire_C,N,O,C,A,5,C - fire_C,N,O,C,A,5,I - fire_C,N,O,C,A,5,L
        - fire_C,N,O,C,A,5,N  = 0
 c6747: fire_C,N,O,C,A,5,L  = 0
 c6748: fire_C,N,O,C,A,5,N  = 0
 c6749: fire_C,N,O,C,L,1,P  = 0
 c6750: fire_C,N,O,C,L,1,D  = 0
 c6751: fire_C,N,O,C,L,1,W  = 0
 c6752: fire_C,N,O,C,L,1,C  = 0
 c6753: fire_C,N,O,C,L,1,I  = 0
 c6754: fire_C,N,O,C,L,1,A  = 0
 c6755: - fire_C,N,O,C,L,1,P - fire_C,N,O,C,L,1,D - fire_C,N,O,C,L,1,W
        - fire_C,N,O,C,L,1,C - fire_C,N,O,C,L,1,I - fire_C,N,O,C,L,1,A
        - fire_C,N,O,C,L,1,N  = 0
 c6756: fire_C,N,O,C,L,1,N  = 0
 c6757: fire_C,N,O,C,L,2,P  = 0
 c6758: fire_C,N,O,C,L,2,D  = 0
 c6759: fire_C,N,O,C,L,2,W  = 0
 c6760: fire_C,N,O,C,L,2,C  = 0
 c6761: fire_C,N,O,C,L,2,I  = 0
 c6762: fire_C,N,O,C,L,2,A  = 0
 c6763: - fire_C,N,O,C,L,2,P - fire_C,N,O,C,L,2,D - fire_C,N,O,C,L,2,W
        - fire_C,N,O,C,L,2,C - fire_C,N,O,C,L,2,I - fire_C,N,O,C,L,2,A
        - fire_C,N,O,C,L,2,N  = 0
 c6764: fire_C,N,O,C,L,2,N  = 0
 c6765: fire_C,N,O,C,L,3,P  = 0
 c6766: fire_C,N,O,C,L,3,D  = 0
 c6767: fire_C,N,O,C,L,3,W  = 0
 c6768: fire_C,N,O,C,L,3,C  = 0
 c6769: fire_C,N,O,C,L,3,I  = 0
 c6770: fire_C,N,O,C,L,3,A  = 0
 c6771: - fire_C,N,O,C,L,3,P - fire_C,N,O,C,L,3,D - fire_C,N,O,C,L,3,W
        - fire_C,N,O,C,L,3,C - fire_C,N,O,C,L,3,I - fire_C,N,O,C,L,3,A
        - fire_C,N,O,C,L,3,N  = 0
 c6772: fire_C,N,O,C,L,3,N  = 0
 c6773: fire_C,N,O,C,L,4,P  = 0
 c6774: fire_C,N,O,C,L,4,D  = 0
 c6775: fire_C,N,O,C,L,4,W  = 0
 c6776: fire_C,N,O,C,L,4,C  = 0
 c6777: fire_C,N,O,C,L,4,I  = 0
 c6778: fire_C,N,O,C,L,4,A  = 0
 c6779: - fire_C,N,O,C,L,4,P - fire_C,N,O,C,L,4,D - fire_C,N,O,C,L,4,W
        - fire_C,N,O,C,L,4,C - fire_C,N,O,C,L,4,I - fire_C,N,O,C,L,4,A
        - fire_C,N,O,C,L,4,N  = 0
 c6780: fire_C,N,O,C,L,4,N  = 0
 c6781: fire_C,N,O,C,L,5,P  = 0
 c6782: fire_C,N,O,C,L,5,D  = 0
 c6783: fire_C,N,O,C,L,5,W  = 0
 c6784: fire_C,N,O,C,L,5,C  = 0
 c6785: fire_C,N,O,C,L,5,I  = 0
 c6786: fire_C,N,O,C,L,5,A  = 0
 c6787: - fire_C,N,O,C,L,5,P - fire_C,N,O,C,L,5,D - fire_C,N,O,C,L,5,W
        - fire_C,N,O,C,L,5,C - fire_C,N,O,C,L,5,I - fire_C,N,O,C,L,5,A
        - fire_C,N,O,C,L,5,N  = 0
 c6788: fire_C,N,O,C,L,5,N  = 0
 c6789: fire_C,N,O,C,N,1,P  = 0
 c6790: fire_C,N,O,C,N,1,D  = 0
 c6791: fire_C,N,O,C,N,1,W  = 0
 c6792: fire_C,N,O,C,N,1,C  = 0
 c6793: fire_C,N,O,C,N,1,I  = 0
 c6794: fire_C,N,O,C,N,1,A  = 0
 c6795: fire_C,N,O,C,N,1,L  = 0
 c6796: - fire_C,N,O,C,N,1,P - fire_C,N,O,C,N,1,D - fire_C,N,O,C,N,1,W
        - fire_C,N,O,C,N,1,C - fire_C,N,O,C,N,1,I - fire_C,N,O,C,N,1,A
        - fire_C,N,O,C,N,1,L  = 0
 c6797: fire_C,N,O,C,N,2,P  = 0
 c6798: fire_C,N,O,C,N,2,D  = 0
 c6799: fire_C,N,O,C,N,2,W  = 0
 c6800: fire_C,N,O,C,N,2,C  = 0
 c6801: fire_C,N,O,C,N,2,I  = 0
 c6802: fire_C,N,O,C,N,2,A  = 0
 c6803: fire_C,N,O,C,N,2,L  = 0
 c6804: - fire_C,N,O,C,N,2,P - fire_C,N,O,C,N,2,D - fire_C,N,O,C,N,2,W
        - fire_C,N,O,C,N,2,C - fire_C,N,O,C,N,2,I - fire_C,N,O,C,N,2,A
        - fire_C,N,O,C,N,2,L  = 0
 c6805: fire_C,N,O,C,N,3,P  = 0
 c6806: fire_C,N,O,C,N,3,D  = 0
 c6807: fire_C,N,O,C,N,3,W  = 0
 c6808: fire_C,N,O,C,N,3,C  = 0
 c6809: fire_C,N,O,C,N,3,I  = 0
 c6810: fire_C,N,O,C,N,3,A  = 0
 c6811: fire_C,N,O,C,N,3,L  = 0
 c6812: - fire_C,N,O,C,N,3,P - fire_C,N,O,C,N,3,D - fire_C,N,O,C,N,3,W
        - fire_C,N,O,C,N,3,C - fire_C,N,O,C,N,3,I - fire_C,N,O,C,N,3,A
        - fire_C,N,O,C,N,3,L  = 0
 c6813: fire_C,N,O,C,N,4,P  = 0
 c6814: fire_C,N,O,C,N,4,D  = 0
 c6815: fire_C,N,O,C,N,4,W  = 0
 c6816: fire_C,N,O,C,N,4,C  = 0
 c6817: fire_C,N,O,C,N,4,I  = 0
 c6818: fire_C,N,O,C,N,4,A  = 0
 c6819: fire_C,N,O,C,N,4,L  = 0
 c6820: - fire_C,N,O,C,N,4,P - fire_C,N,O,C,N,4,D - fire_C,N,O,C,N,4,W
        - fire_C,N,O,C,N,4,C - fire_C,N,O,C,N,4,I - fire_C,N,O,C,N,4,A
        - fire_C,N,O,C,N,4,L  = 0
 c6821: fire_C,N,O,C,N,5,P  = 0
 c6822: fire_C,N,O,C,N,5,D  = 0
 c6823: fire_C,N,O,C,N,5,W  = 0
 c6824: fire_C,N,O,C,N,5,C  = 0
 c6825: fire_C,N,O,C,N,5,I  = 0
 c6826: fire_C,N,O,C,N,5,A  = 0
 c6827: fire_C,N,O,C,N,5,L  = 0
 c6828: - fire_C,N,O,C,N,5,P - fire_C,N,O,C,N,5,D - fire_C,N,O,C,N,5,W
        - fire_C,N,O,C,N,5,C - fire_C,N,O,C,N,5,I - fire_C,N,O,C,N,5,A
        - fire_C,N,O,C,N,5,L  = 0
 c6829: 0.9 fire_C,N,O,R,P,1,P - 0.1 fire_C,N,O,R,P,1,D
        - 0.1 fire_C,N,O,R,P,1,W - 0.1 fire_C,N,O,R,P,1,C
        - 0.1 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        - 0.1 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6830: - 0.1 fire_C,N,O,R,P,1,P + 0.9 fire_C,N,O,R,P,1,D
        - 0.1 fire_C,N,O,R,P,1,W - 0.1 fire_C,N,O,R,P,1,C
        - 0.1 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        - 0.1 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6831: - 0.1 fire_C,N,O,R,P,1,P - 0.1 fire_C,N,O,R,P,1,D
        + 0.9 fire_C,N,O,R,P,1,W - 0.1 fire_C,N,O,R,P,1,C
        - 0.1 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        - 0.1 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6832: - 0.1 fire_C,N,O,R,P,1,P - 0.1 fire_C,N,O,R,P,1,D
        - 0.1 fire_C,N,O,R,P,1,W + 0.9 fire_C,N,O,R,P,1,C
        - 0.1 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        - 0.1 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6833: - 0.1 fire_C,N,O,R,P,1,P - 0.1 fire_C,N,O,R,P,1,D
        - 0.1 fire_C,N,O,R,P,1,W - 0.1 fire_C,N,O,R,P,1,C
        + 0.9 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        - 0.1 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6834: - 0.2 fire_C,N,O,R,P,1,P - 0.2 fire_C,N,O,R,P,1,D
        - 0.2 fire_C,N,O,R,P,1,W - 0.2 fire_C,N,O,R,P,1,C
        - 0.2 fire_C,N,O,R,P,1,I + 0.8 fire_C,N,O,R,P,1,A
        - 0.2 fire_C,N,O,R,P,1,L - 0.2 fire_C,N,O,R,P,1,N  = 0
 c6835: - 0.1 fire_C,N,O,R,P,1,P - 0.1 fire_C,N,O,R,P,1,D
        - 0.1 fire_C,N,O,R,P,1,W - 0.1 fire_C,N,O,R,P,1,C
        - 0.1 fire_C,N,O,R,P,1,I - 0.1 fire_C,N,O,R,P,1,A
        + 0.9 fire_C,N,O,R,P,1,L - 0.1 fire_C,N,O,R,P,1,N  = 0
 c6836: - 0.2 fire_C,N,O,R,P,1,P - 0.2 fire_C,N,O,R,P,1,D
        - 0.2 fire_C,N,O,R,P,1,W - 0.2 fire_C,N,O,R,P,1,C
        - 0.2 fire_C,N,O,R,P,1,I - 0.2 fire_C,N,O,R,P,1,A
        - 0.2 fire_C,N,O,R,P,1,L + 0.8 fire_C,N,O,R,P,1,N  = 0
 c6837: 0.9 fire_C,N,O,R,P,2,P - 0.1 fire_C,N,O,R,P,2,D
        - 0.1 fire_C,N,O,R,P,2,W - 0.1 fire_C,N,O,R,P,2,C
        - 0.1 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        - 0.1 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6838: - 0.1 fire_C,N,O,R,P,2,P + 0.9 fire_C,N,O,R,P,2,D
        - 0.1 fire_C,N,O,R,P,2,W - 0.1 fire_C,N,O,R,P,2,C
        - 0.1 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        - 0.1 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6839: - 0.1 fire_C,N,O,R,P,2,P - 0.1 fire_C,N,O,R,P,2,D
        + 0.9 fire_C,N,O,R,P,2,W - 0.1 fire_C,N,O,R,P,2,C
        - 0.1 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        - 0.1 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6840: - 0.1 fire_C,N,O,R,P,2,P - 0.1 fire_C,N,O,R,P,2,D
        - 0.1 fire_C,N,O,R,P,2,W + 0.9 fire_C,N,O,R,P,2,C
        - 0.1 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        - 0.1 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6841: - 0.1 fire_C,N,O,R,P,2,P - 0.1 fire_C,N,O,R,P,2,D
        - 0.1 fire_C,N,O,R,P,2,W - 0.1 fire_C,N,O,R,P,2,C
        + 0.9 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        - 0.1 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6842: - 0.2 fire_C,N,O,R,P,2,P - 0.2 fire_C,N,O,R,P,2,D
        - 0.2 fire_C,N,O,R,P,2,W - 0.2 fire_C,N,O,R,P,2,C
        - 0.2 fire_C,N,O,R,P,2,I + 0.8 fire_C,N,O,R,P,2,A
        - 0.2 fire_C,N,O,R,P,2,L - 0.2 fire_C,N,O,R,P,2,N  = 0
 c6843: - 0.1 fire_C,N,O,R,P,2,P - 0.1 fire_C,N,O,R,P,2,D
        - 0.1 fire_C,N,O,R,P,2,W - 0.1 fire_C,N,O,R,P,2,C
        - 0.1 fire_C,N,O,R,P,2,I - 0.1 fire_C,N,O,R,P,2,A
        + 0.9 fire_C,N,O,R,P,2,L - 0.1 fire_C,N,O,R,P,2,N  = 0
 c6844: - 0.2 fire_C,N,O,R,P,2,P - 0.2 fire_C,N,O,R,P,2,D
        - 0.2 fire_C,N,O,R,P,2,W - 0.2 fire_C,N,O,R,P,2,C
        - 0.2 fire_C,N,O,R,P,2,I - 0.2 fire_C,N,O,R,P,2,A
        - 0.2 fire_C,N,O,R,P,2,L + 0.8 fire_C,N,O,R,P,2,N  = 0
 c6845: 0.9 fire_C,N,O,R,P,3,P - 0.1 fire_C,N,O,R,P,3,D
        - 0.1 fire_C,N,O,R,P,3,W - 0.1 fire_C,N,O,R,P,3,C
        - 0.1 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        - 0.1 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6846: - 0.1 fire_C,N,O,R,P,3,P + 0.9 fire_C,N,O,R,P,3,D
        - 0.1 fire_C,N,O,R,P,3,W - 0.1 fire_C,N,O,R,P,3,C
        - 0.1 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        - 0.1 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6847: - 0.1 fire_C,N,O,R,P,3,P - 0.1 fire_C,N,O,R,P,3,D
        + 0.9 fire_C,N,O,R,P,3,W - 0.1 fire_C,N,O,R,P,3,C
        - 0.1 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        - 0.1 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6848: - 0.1 fire_C,N,O,R,P,3,P - 0.1 fire_C,N,O,R,P,3,D
        - 0.1 fire_C,N,O,R,P,3,W + 0.9 fire_C,N,O,R,P,3,C
        - 0.1 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        - 0.1 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6849: - 0.1 fire_C,N,O,R,P,3,P - 0.1 fire_C,N,O,R,P,3,D
        - 0.1 fire_C,N,O,R,P,3,W - 0.1 fire_C,N,O,R,P,3,C
        + 0.9 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        - 0.1 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6850: - 0.2 fire_C,N,O,R,P,3,P - 0.2 fire_C,N,O,R,P,3,D
        - 0.2 fire_C,N,O,R,P,3,W - 0.2 fire_C,N,O,R,P,3,C
        - 0.2 fire_C,N,O,R,P,3,I + 0.8 fire_C,N,O,R,P,3,A
        - 0.2 fire_C,N,O,R,P,3,L - 0.2 fire_C,N,O,R,P,3,N  = 0
 c6851: - 0.1 fire_C,N,O,R,P,3,P - 0.1 fire_C,N,O,R,P,3,D
        - 0.1 fire_C,N,O,R,P,3,W - 0.1 fire_C,N,O,R,P,3,C
        - 0.1 fire_C,N,O,R,P,3,I - 0.1 fire_C,N,O,R,P,3,A
        + 0.9 fire_C,N,O,R,P,3,L - 0.1 fire_C,N,O,R,P,3,N  = 0
 c6852: - 0.2 fire_C,N,O,R,P,3,P - 0.2 fire_C,N,O,R,P,3,D
        - 0.2 fire_C,N,O,R,P,3,W - 0.2 fire_C,N,O,R,P,3,C
        - 0.2 fire_C,N,O,R,P,3,I - 0.2 fire_C,N,O,R,P,3,A
        - 0.2 fire_C,N,O,R,P,3,L + 0.8 fire_C,N,O,R,P,3,N  = 0
 c6853: 0.9 fire_C,N,O,R,P,4,P - 0.1 fire_C,N,O,R,P,4,D
        - 0.1 fire_C,N,O,R,P,4,W - 0.1 fire_C,N,O,R,P,4,C
        - 0.1 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        - 0.1 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6854: - 0.1 fire_C,N,O,R,P,4,P + 0.9 fire_C,N,O,R,P,4,D
        - 0.1 fire_C,N,O,R,P,4,W - 0.1 fire_C,N,O,R,P,4,C
        - 0.1 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        - 0.1 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6855: - 0.1 fire_C,N,O,R,P,4,P - 0.1 fire_C,N,O,R,P,4,D
        + 0.9 fire_C,N,O,R,P,4,W - 0.1 fire_C,N,O,R,P,4,C
        - 0.1 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        - 0.1 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6856: - 0.1 fire_C,N,O,R,P,4,P - 0.1 fire_C,N,O,R,P,4,D
        - 0.1 fire_C,N,O,R,P,4,W + 0.9 fire_C,N,O,R,P,4,C
        - 0.1 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        - 0.1 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6857: - 0.1 fire_C,N,O,R,P,4,P - 0.1 fire_C,N,O,R,P,4,D
        - 0.1 fire_C,N,O,R,P,4,W - 0.1 fire_C,N,O,R,P,4,C
        + 0.9 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        - 0.1 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6858: - 0.2 fire_C,N,O,R,P,4,P - 0.2 fire_C,N,O,R,P,4,D
        - 0.2 fire_C,N,O,R,P,4,W - 0.2 fire_C,N,O,R,P,4,C
        - 0.2 fire_C,N,O,R,P,4,I + 0.8 fire_C,N,O,R,P,4,A
        - 0.2 fire_C,N,O,R,P,4,L - 0.2 fire_C,N,O,R,P,4,N  = 0
 c6859: - 0.1 fire_C,N,O,R,P,4,P - 0.1 fire_C,N,O,R,P,4,D
        - 0.1 fire_C,N,O,R,P,4,W - 0.1 fire_C,N,O,R,P,4,C
        - 0.1 fire_C,N,O,R,P,4,I - 0.1 fire_C,N,O,R,P,4,A
        + 0.9 fire_C,N,O,R,P,4,L - 0.1 fire_C,N,O,R,P,4,N  = 0
 c6860: - 0.2 fire_C,N,O,R,P,4,P - 0.2 fire_C,N,O,R,P,4,D
        - 0.2 fire_C,N,O,R,P,4,W - 0.2 fire_C,N,O,R,P,4,C
        - 0.2 fire_C,N,O,R,P,4,I - 0.2 fire_C,N,O,R,P,4,A
        - 0.2 fire_C,N,O,R,P,4,L + 0.8 fire_C,N,O,R,P,4,N  = 0
 c6861: 0.9 fire_C,N,O,R,P,5,P - 0.1 fire_C,N,O,R,P,5,D
        - 0.1 fire_C,N,O,R,P,5,W - 0.1 fire_C,N,O,R,P,5,C
        - 0.1 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        - 0.1 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6862: - 0.1 fire_C,N,O,R,P,5,P + 0.9 fire_C,N,O,R,P,5,D
        - 0.1 fire_C,N,O,R,P,5,W - 0.1 fire_C,N,O,R,P,5,C
        - 0.1 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        - 0.1 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6863: - 0.1 fire_C,N,O,R,P,5,P - 0.1 fire_C,N,O,R,P,5,D
        + 0.9 fire_C,N,O,R,P,5,W - 0.1 fire_C,N,O,R,P,5,C
        - 0.1 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        - 0.1 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6864: - 0.1 fire_C,N,O,R,P,5,P - 0.1 fire_C,N,O,R,P,5,D
        - 0.1 fire_C,N,O,R,P,5,W + 0.9 fire_C,N,O,R,P,5,C
        - 0.1 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        - 0.1 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6865: - 0.1 fire_C,N,O,R,P,5,P - 0.1 fire_C,N,O,R,P,5,D
        - 0.1 fire_C,N,O,R,P,5,W - 0.1 fire_C,N,O,R,P,5,C
        + 0.9 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        - 0.1 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6866: - 0.2 fire_C,N,O,R,P,5,P - 0.2 fire_C,N,O,R,P,5,D
        - 0.2 fire_C,N,O,R,P,5,W - 0.2 fire_C,N,O,R,P,5,C
        - 0.2 fire_C,N,O,R,P,5,I + 0.8 fire_C,N,O,R,P,5,A
        - 0.2 fire_C,N,O,R,P,5,L - 0.2 fire_C,N,O,R,P,5,N  = 0
 c6867: - 0.1 fire_C,N,O,R,P,5,P - 0.1 fire_C,N,O,R,P,5,D
        - 0.1 fire_C,N,O,R,P,5,W - 0.1 fire_C,N,O,R,P,5,C
        - 0.1 fire_C,N,O,R,P,5,I - 0.1 fire_C,N,O,R,P,5,A
        + 0.9 fire_C,N,O,R,P,5,L - 0.1 fire_C,N,O,R,P,5,N  = 0
 c6868: - 0.2 fire_C,N,O,R,P,5,P - 0.2 fire_C,N,O,R,P,5,D
        - 0.2 fire_C,N,O,R,P,5,W - 0.2 fire_C,N,O,R,P,5,C
        - 0.2 fire_C,N,O,R,P,5,I - 0.2 fire_C,N,O,R,P,5,A
        - 0.2 fire_C,N,O,R,P,5,L + 0.8 fire_C,N,O,R,P,5,N  = 0
 c6869: 0.5 fire_C,N,O,R,D,1,P - 0.5 fire_C,N,O,R,D,1,D
        - 0.5 fire_C,N,O,R,D,1,W - 0.5 fire_C,N,O,R,D,1,C
        - 0.5 fire_C,N,O,R,D,1,I - 0.5 fire_C,N,O,R,D,1,A
        - 0.5 fire_C,N,O,R,D,1,L - 0.5 fire_C,N,O,R,D,1,N  = 0
 c6870: - 0.5 fire_C,N,O,R,D,1,P + 0.5 fire_C,N,O,R,D,1,D
        - 0.5 fire_C,N,O,R,D,1,W - 0.5 fire_C,N,O,R,D,1,C
        - 0.5 fire_C,N,O,R,D,1,I - 0.5 fire_C,N,O,R,D,1,A
        - 0.5 fire_C,N,O,R,D,1,L - 0.5 fire_C,N,O,R,D,1,N  = 0
 c6871: fire_C,N,O,R,D,1,W  = 0
 c6872: fire_C,N,O,R,D,1,C  = 0
 c6873: fire_C,N,O,R,D,1,I  = 0
 c6874: fire_C,N,O,R,D,1,A  = 0
 c6875: fire_C,N,O,R,D,1,L  = 0
 c6876: fire_C,N,O,R,D,1,N  = 0
 c6877: 0.5 fire_C,N,O,R,D,2,P - 0.5 fire_C,N,O,R,D,2,D
        - 0.5 fire_C,N,O,R,D,2,W - 0.5 fire_C,N,O,R,D,2,C
        - 0.5 fire_C,N,O,R,D,2,I - 0.5 fire_C,N,O,R,D,2,A
        - 0.5 fire_C,N,O,R,D,2,L - 0.5 fire_C,N,O,R,D,2,N  = 0
 c6878: - 0.5 fire_C,N,O,R,D,2,P + 0.5 fire_C,N,O,R,D,2,D
        - 0.5 fire_C,N,O,R,D,2,W - 0.5 fire_C,N,O,R,D,2,C
        - 0.5 fire_C,N,O,R,D,2,I - 0.5 fire_C,N,O,R,D,2,A
        - 0.5 fire_C,N,O,R,D,2,L - 0.5 fire_C,N,O,R,D,2,N  = 0
 c6879: fire_C,N,O,R,D,2,W  = 0
 c6880: fire_C,N,O,R,D,2,C  = 0
 c6881: fire_C,N,O,R,D,2,I  = 0
 c6882: fire_C,N,O,R,D,2,A  = 0
 c6883: fire_C,N,O,R,D,2,L  = 0
 c6884: fire_C,N,O,R,D,2,N  = 0
 c6885: 0.5 fire_C,N,O,R,D,3,P - 0.5 fire_C,N,O,R,D,3,D
        - 0.5 fire_C,N,O,R,D,3,W - 0.5 fire_C,N,O,R,D,3,C
        - 0.5 fire_C,N,O,R,D,3,I - 0.5 fire_C,N,O,R,D,3,A
        - 0.5 fire_C,N,O,R,D,3,L - 0.5 fire_C,N,O,R,D,3,N  = 0
 c6886: - 0.5 fire_C,N,O,R,D,3,P + 0.5 fire_C,N,O,R,D,3,D
        - 0.5 fire_C,N,O,R,D,3,W - 0.5 fire_C,N,O,R,D,3,C
        - 0.5 fire_C,N,O,R,D,3,I - 0.5 fire_C,N,O,R,D,3,A
        - 0.5 fire_C,N,O,R,D,3,L - 0.5 fire_C,N,O,R,D,3,N  = 0
 c6887: fire_C,N,O,R,D,3,W  = 0
 c6888: fire_C,N,O,R,D,3,C  = 0
 c6889: fire_C,N,O,R,D,3,I  = 0
 c6890: fire_C,N,O,R,D,3,A  = 0
 c6891: fire_C,N,O,R,D,3,L  = 0
 c6892: fire_C,N,O,R,D,3,N  = 0
 c6893: 0.5 fire_C,N,O,R,D,4,P - 0.5 fire_C,N,O,R,D,4,D
        - 0.5 fire_C,N,O,R,D,4,W - 0.5 fire_C,N,O,R,D,4,C
        - 0.5 fire_C,N,O,R,D,4,I - 0.5 fire_C,N,O,R,D,4,A
        - 0.5 fire_C,N,O,R,D,4,L - 0.5 fire_C,N,O,R,D,4,N  = 0
 c6894: - 0.5 fire_C,N,O,R,D,4,P + 0.5 fire_C,N,O,R,D,4,D
        - 0.5 fire_C,N,O,R,D,4,W - 0.5 fire_C,N,O,R,D,4,C
        - 0.5 fire_C,N,O,R,D,4,I - 0.5 fire_C,N,O,R,D,4,A
        - 0.5 fire_C,N,O,R,D,4,L - 0.5 fire_C,N,O,R,D,4,N  = 0
 c6895: fire_C,N,O,R,D,4,W  = 0
 c6896: fire_C,N,O,R,D,4,C  = 0
 c6897: fire_C,N,O,R,D,4,I  = 0
 c6898: fire_C,N,O,R,D,4,A  = 0
 c6899: fire_C,N,O,R,D,4,L  = 0
 c6900: fire_C,N,O,R,D,4,N  = 0
 c6901: 0.5 fire_C,N,O,R,D,5,P - 0.5 fire_C,N,O,R,D,5,D
        - 0.5 fire_C,N,O,R,D,5,W - 0.5 fire_C,N,O,R,D,5,C
        - 0.5 fire_C,N,O,R,D,5,I - 0.5 fire_C,N,O,R,D,5,A
        - 0.5 fire_C,N,O,R,D,5,L - 0.5 fire_C,N,O,R,D,5,N  = 0
 c6902: - 0.5 fire_C,N,O,R,D,5,P + 0.5 fire_C,N,O,R,D,5,D
        - 0.5 fire_C,N,O,R,D,5,W - 0.5 fire_C,N,O,R,D,5,C
        - 0.5 fire_C,N,O,R,D,5,I - 0.5 fire_C,N,O,R,D,5,A
        - 0.5 fire_C,N,O,R,D,5,L - 0.5 fire_C,N,O,R,D,5,N  = 0
 c6903: fire_C,N,O,R,D,5,W  = 0
 c6904: fire_C,N,O,R,D,5,C  = 0
 c6905: fire_C,N,O,R,D,5,I  = 0
 c6906: fire_C,N,O,R,D,5,A  = 0
 c6907: fire_C,N,O,R,D,5,L  = 0
 c6908: fire_C,N,O,R,D,5,N  = 0
 c6909: fire_C,N,O,R,W,1,P  = 0
 c6910: fire_C,N,O,R,W,1,D  = 0
 c6911: - fire_C,N,O,R,W,1,P - fire_C,N,O,R,W,1,D - fire_C,N,O,R,W,1,C
        - fire_C,N,O,R,W,1,I - fire_C,N,O,R,W,1,A - fire_C,N,O,R,W,1,L
        - fire_C,N,O,R,W,1,N  = 0
 c6912: fire_C,N,O,R,W,1,C  = 0
 c6913: fire_C,N,O,R,W,1,I  = 0
 c6914: fire_C,N,O,R,W,1,A  = 0
 c6915: fire_C,N,O,R,W,1,L  = 0
 c6916: fire_C,N,O,R,W,1,N  = 0
 c6917: fire_C,N,O,R,W,2,P  = 0
 c6918: fire_C,N,O,R,W,2,D  = 0
 c6919: - fire_C,N,O,R,W,2,P - fire_C,N,O,R,W,2,D - fire_C,N,O,R,W,2,C
        - fire_C,N,O,R,W,2,I - fire_C,N,O,R,W,2,A - fire_C,N,O,R,W,2,L
        - fire_C,N,O,R,W,2,N  = 0
 c6920: fire_C,N,O,R,W,2,C  = 0
 c6921: fire_C,N,O,R,W,2,I  = 0
 c6922: fire_C,N,O,R,W,2,A  = 0
 c6923: fire_C,N,O,R,W,2,L  = 0
 c6924: fire_C,N,O,R,W,2,N  = 0
 c6925: fire_C,N,O,R,W,3,P  = 0
 c6926: fire_C,N,O,R,W,3,D  = 0
 c6927: - fire_C,N,O,R,W,3,P - fire_C,N,O,R,W,3,D - fire_C,N,O,R,W,3,C
        - fire_C,N,O,R,W,3,I - fire_C,N,O,R,W,3,A - fire_C,N,O,R,W,3,L
        - fire_C,N,O,R,W,3,N  = 0
 c6928: fire_C,N,O,R,W,3,C  = 0
 c6929: fire_C,N,O,R,W,3,I  = 0
 c6930: fire_C,N,O,R,W,3,A  = 0
 c6931: fire_C,N,O,R,W,3,L  = 0
 c6932: fire_C,N,O,R,W,3,N  = 0
 c6933: fire_C,N,O,R,W,4,P  = 0
 c6934: fire_C,N,O,R,W,4,D  = 0
 c6935: - fire_C,N,O,R,W,4,P - fire_C,N,O,R,W,4,D - fire_C,N,O,R,W,4,C
        - fire_C,N,O,R,W,4,I - fire_C,N,O,R,W,4,A - fire_C,N,O,R,W,4,L
        - fire_C,N,O,R,W,4,N  = 0
 c6936: fire_C,N,O,R,W,4,C  = 0
 c6937: fire_C,N,O,R,W,4,I  = 0
 c6938: fire_C,N,O,R,W,4,A  = 0
 c6939: fire_C,N,O,R,W,4,L  = 0
 c6940: fire_C,N,O,R,W,4,N  = 0
 c6941: fire_C,N,O,R,W,5,P  = 0
 c6942: fire_C,N,O,R,W,5,D  = 0
 c6943: - fire_C,N,O,R,W,5,P - fire_C,N,O,R,W,5,D - fire_C,N,O,R,W,5,C
        - fire_C,N,O,R,W,5,I - fire_C,N,O,R,W,5,A - fire_C,N,O,R,W,5,L
        - fire_C,N,O,R,W,5,N  = 0
 c6944: fire_C,N,O,R,W,5,C  = 0
 c6945: fire_C,N,O,R,W,5,I  = 0
 c6946: fire_C,N,O,R,W,5,A  = 0
 c6947: fire_C,N,O,R,W,5,L  = 0
 c6948: fire_C,N,O,R,W,5,N  = 0
 c6949: fire_C,N,O,R,C,1,P  = 0
 c6950: fire_C,N,O,R,C,1,D  = 0
 c6951: fire_C,N,O,R,C,1,W  = 0
 c6952: - fire_C,N,O,R,C,1,P - fire_C,N,O,R,C,1,D - fire_C,N,O,R,C,1,W
        - fire_C,N,O,R,C,1,I - fire_C,N,O,R,C,1,A - fire_C,N,O,R,C,1,L
        - fire_C,N,O,R,C,1,N  = 0
 c6953: fire_C,N,O,R,C,1,I  = 0
 c6954: fire_C,N,O,R,C,1,A  = 0
 c6955: fire_C,N,O,R,C,1,L  = 0
 c6956: fire_C,N,O,R,C,1,N  = 0
 c6957: fire_C,N,O,R,C,2,P  = 0
 c6958: fire_C,N,O,R,C,2,D  = 0
 c6959: fire_C,N,O,R,C,2,W  = 0
 c6960: - fire_C,N,O,R,C,2,P - fire_C,N,O,R,C,2,D - fire_C,N,O,R,C,2,W
        - fire_C,N,O,R,C,2,I - fire_C,N,O,R,C,2,A - fire_C,N,O,R,C,2,L
        - fire_C,N,O,R,C,2,N  = 0
 c6961: fire_C,N,O,R,C,2,I  = 0
 c6962: fire_C,N,O,R,C,2,A  = 0
 c6963: fire_C,N,O,R,C,2,L  = 0
 c6964: fire_C,N,O,R,C,2,N  = 0
 c6965: fire_C,N,O,R,C,3,P  = 0
 c6966: fire_C,N,O,R,C,3,D  = 0
 c6967: fire_C,N,O,R,C,3,W  = 0
 c6968: - fire_C,N,O,R,C,3,P - fire_C,N,O,R,C,3,D - fire_C,N,O,R,C,3,W
        - fire_C,N,O,R,C,3,I - fire_C,N,O,R,C,3,A - fire_C,N,O,R,C,3,L
        - fire_C,N,O,R,C,3,N  = 0
 c6969: fire_C,N,O,R,C,3,I  = 0
 c6970: fire_C,N,O,R,C,3,A  = 0
 c6971: fire_C,N,O,R,C,3,L  = 0
 c6972: fire_C,N,O,R,C,3,N  = 0
 c6973: fire_C,N,O,R,C,4,P  = 0
 c6974: fire_C,N,O,R,C,4,D  = 0
 c6975: fire_C,N,O,R,C,4,W  = 0
 c6976: - fire_C,N,O,R,C,4,P - fire_C,N,O,R,C,4,D - fire_C,N,O,R,C,4,W
        - fire_C,N,O,R,C,4,I - fire_C,N,O,R,C,4,A - fire_C,N,O,R,C,4,L
        - fire_C,N,O,R,C,4,N  = 0
 c6977: fire_C,N,O,R,C,4,I  = 0
 c6978: fire_C,N,O,R,C,4,A  = 0
 c6979: fire_C,N,O,R,C,4,L  = 0
 c6980: fire_C,N,O,R,C,4,N  = 0
 c6981: fire_C,N,O,R,C,5,P  = 0
 c6982: fire_C,N,O,R,C,5,D  = 0
 c6983: fire_C,N,O,R,C,5,W  = 0
 c6984: - fire_C,N,O,R,C,5,P - fire_C,N,O,R,C,5,D - fire_C,N,O,R,C,5,W
        - fire_C,N,O,R,C,5,I - fire_C,N,O,R,C,5,A - fire_C,N,O,R,C,5,L
        - fire_C,N,O,R,C,5,N  = 0
 c6985: fire_C,N,O,R,C,5,I  = 0
 c6986: fire_C,N,O,R,C,5,A  = 0
 c6987: fire_C,N,O,R,C,5,L  = 0
 c6988: fire_C,N,O,R,C,5,N  = 0
 c6989: fire_C,N,O,R,I,1,P  = 0
 c6990: fire_C,N,O,R,I,1,D  = 0
 c6991: fire_C,N,O,R,I,1,W  = 0
 c6992: fire_C,N,O,R,I,1,C  = 0
 c6993: - fire_C,N,O,R,I,1,P - fire_C,N,O,R,I,1,D - fire_C,N,O,R,I,1,W
        - fire_C,N,O,R,I,1,C - fire_C,N,O,R,I,1,A - fire_C,N,O,R,I,1,L
        - fire_C,N,O,R,I,1,N  = 0
 c6994: fire_C,N,O,R,I,1,A  = 0
 c6995: fire_C,N,O,R,I,1,L  = 0
 c6996: fire_C,N,O,R,I,1,N  = 0
 c6997: fire_C,N,O,R,I,2,P  = 0
 c6998: fire_C,N,O,R,I,2,D  = 0
 c6999: fire_C,N,O,R,I,2,W  = 0
 c7000: fire_C,N,O,R,I,2,C  = 0
 c7001: - fire_C,N,O,R,I,2,P - fire_C,N,O,R,I,2,D - fire_C,N,O,R,I,2,W
        - fire_C,N,O,R,I,2,C - fire_C,N,O,R,I,2,A - fire_C,N,O,R,I,2,L
        - fire_C,N,O,R,I,2,N  = 0
 c7002: fire_C,N,O,R,I,2,A  = 0
 c7003: fire_C,N,O,R,I,2,L  = 0
 c7004: fire_C,N,O,R,I,2,N  = 0
 c7005: fire_C,N,O,R,I,3,P  = 0
 c7006: fire_C,N,O,R,I,3,D  = 0
 c7007: fire_C,N,O,R,I,3,W  = 0
 c7008: fire_C,N,O,R,I,3,C  = 0
 c7009: - fire_C,N,O,R,I,3,P - fire_C,N,O,R,I,3,D - fire_C,N,O,R,I,3,W
        - fire_C,N,O,R,I,3,C - fire_C,N,O,R,I,3,A - fire_C,N,O,R,I,3,L
        - fire_C,N,O,R,I,3,N  = 0
 c7010: fire_C,N,O,R,I,3,A  = 0
 c7011: fire_C,N,O,R,I,3,L  = 0
 c7012: fire_C,N,O,R,I,3,N  = 0
 c7013: fire_C,N,O,R,I,4,P  = 0
 c7014: fire_C,N,O,R,I,4,D  = 0
 c7015: fire_C,N,O,R,I,4,W  = 0
 c7016: fire_C,N,O,R,I,4,C  = 0
 c7017: - fire_C,N,O,R,I,4,P - fire_C,N,O,R,I,4,D - fire_C,N,O,R,I,4,W
        - fire_C,N,O,R,I,4,C - fire_C,N,O,R,I,4,A - fire_C,N,O,R,I,4,L
        - fire_C,N,O,R,I,4,N  = 0
 c7018: fire_C,N,O,R,I,4,A  = 0
 c7019: fire_C,N,O,R,I,4,L  = 0
 c7020: fire_C,N,O,R,I,4,N  = 0
 c7021: fire_C,N,O,R,I,5,P  = 0
 c7022: fire_C,N,O,R,I,5,D  = 0
 c7023: fire_C,N,O,R,I,5,W  = 0
 c7024: fire_C,N,O,R,I,5,C  = 0
 c7025: - fire_C,N,O,R,I,5,P - fire_C,N,O,R,I,5,D - fire_C,N,O,R,I,5,W
        - fire_C,N,O,R,I,5,C - fire_C,N,O,R,I,5,A - fire_C,N,O,R,I,5,L
        - fire_C,N,O,R,I,5,N  = 0
 c7026: fire_C,N,O,R,I,5,A  = 0
 c7027: fire_C,N,O,R,I,5,L  = 0
 c7028: fire_C,N,O,R,I,5,N  = 0
 c7029: fire_C,N,O,R,A,1,P  = 0
 c7030: fire_C,N,O,R,A,1,D  = 0
 c7031: fire_C,N,O,R,A,1,W  = 0
 c7032: fire_C,N,O,R,A,1,C  = 0
 c7033: fire_C,N,O,R,A,1,I  = 0
 c7034: - fire_C,N,O,R,A,1,P - fire_C,N,O,R,A,1,D - fire_C,N,O,R,A,1,W
        - fire_C,N,O,R,A,1,C - fire_C,N,O,R,A,1,I - fire_C,N,O,R,A,1,L
        - fire_C,N,O,R,A,1,N  = 0
 c7035: fire_C,N,O,R,A,1,L  = 0
 c7036: fire_C,N,O,R,A,1,N  = 0
 c7037: fire_C,N,O,R,A,2,P  = 0
 c7038: fire_C,N,O,R,A,2,D  = 0
 c7039: fire_C,N,O,R,A,2,W  = 0
 c7040: fire_C,N,O,R,A,2,C  = 0
 c7041: fire_C,N,O,R,A,2,I  = 0
 c7042: - fire_C,N,O,R,A,2,P - fire_C,N,O,R,A,2,D - fire_C,N,O,R,A,2,W
        - fire_C,N,O,R,A,2,C - fire_C,N,O,R,A,2,I - fire_C,N,O,R,A,2,L
        - fire_C,N,O,R,A,2,N  = 0
 c7043: fire_C,N,O,R,A,2,L  = 0
 c7044: fire_C,N,O,R,A,2,N  = 0
 c7045: fire_C,N,O,R,A,3,P  = 0
 c7046: fire_C,N,O,R,A,3,D  = 0
 c7047: fire_C,N,O,R,A,3,W  = 0
 c7048: fire_C,N,O,R,A,3,C  = 0
 c7049: fire_C,N,O,R,A,3,I  = 0
 c7050: - fire_C,N,O,R,A,3,P - fire_C,N,O,R,A,3,D - fire_C,N,O,R,A,3,W
        - fire_C,N,O,R,A,3,C - fire_C,N,O,R,A,3,I - fire_C,N,O,R,A,3,L
        - fire_C,N,O,R,A,3,N  = 0
 c7051: fire_C,N,O,R,A,3,L  = 0
 c7052: fire_C,N,O,R,A,3,N  = 0
 c7053: fire_C,N,O,R,A,4,P  = 0
 c7054: fire_C,N,O,R,A,4,D  = 0
 c7055: fire_C,N,O,R,A,4,W  = 0
 c7056: fire_C,N,O,R,A,4,C  = 0
 c7057: fire_C,N,O,R,A,4,I  = 0
 c7058: - fire_C,N,O,R,A,4,P - fire_C,N,O,R,A,4,D - fire_C,N,O,R,A,4,W
        - fire_C,N,O,R,A,4,C - fire_C,N,O,R,A,4,I - fire_C,N,O,R,A,4,L
        - fire_C,N,O,R,A,4,N  = 0
 c7059: fire_C,N,O,R,A,4,L  = 0
 c7060: fire_C,N,O,R,A,4,N  = 0
 c7061: fire_C,N,O,R,A,5,P  = 0
 c7062: fire_C,N,O,R,A,5,D  = 0
 c7063: fire_C,N,O,R,A,5,W  = 0
 c7064: fire_C,N,O,R,A,5,C  = 0
 c7065: fire_C,N,O,R,A,5,I  = 0
 c7066: - fire_C,N,O,R,A,5,P - fire_C,N,O,R,A,5,D - fire_C,N,O,R,A,5,W
        - fire_C,N,O,R,A,5,C - fire_C,N,O,R,A,5,I - fire_C,N,O,R,A,5,L
        - fire_C,N,O,R,A,5,N  = 0
 c7067: fire_C,N,O,R,A,5,L  = 0
 c7068: fire_C,N,O,R,A,5,N  = 0
 c7069: fire_C,N,O,R,L,1,P  = 0
 c7070: fire_C,N,O,R,L,1,D  = 0
 c7071: fire_C,N,O,R,L,1,W  = 0
 c7072: fire_C,N,O,R,L,1,C  = 0
 c7073: fire_C,N,O,R,L,1,I  = 0
 c7074: fire_C,N,O,R,L,1,A  = 0
 c7075: - fire_C,N,O,R,L,1,P - fire_C,N,O,R,L,1,D - fire_C,N,O,R,L,1,W
        - fire_C,N,O,R,L,1,C - fire_C,N,O,R,L,1,I - fire_C,N,O,R,L,1,A
        - fire_C,N,O,R,L,1,N  = 0
 c7076: fire_C,N,O,R,L,1,N  = 0
 c7077: fire_C,N,O,R,L,2,P  = 0
 c7078: fire_C,N,O,R,L,2,D  = 0
 c7079: fire_C,N,O,R,L,2,W  = 0
 c7080: fire_C,N,O,R,L,2,C  = 0
 c7081: fire_C,N,O,R,L,2,I  = 0
 c7082: fire_C,N,O,R,L,2,A  = 0
 c7083: - fire_C,N,O,R,L,2,P - fire_C,N,O,R,L,2,D - fire_C,N,O,R,L,2,W
        - fire_C,N,O,R,L,2,C - fire_C,N,O,R,L,2,I - fire_C,N,O,R,L,2,A
        - fire_C,N,O,R,L,2,N  = 0
 c7084: fire_C,N,O,R,L,2,N  = 0
 c7085: fire_C,N,O,R,L,3,P  = 0
 c7086: fire_C,N,O,R,L,3,D  = 0
 c7087: fire_C,N,O,R,L,3,W  = 0
 c7088: fire_C,N,O,R,L,3,C  = 0
 c7089: fire_C,N,O,R,L,3,I  = 0
 c7090: fire_C,N,O,R,L,3,A  = 0
 c7091: - fire_C,N,O,R,L,3,P - fire_C,N,O,R,L,3,D - fire_C,N,O,R,L,3,W
        - fire_C,N,O,R,L,3,C - fire_C,N,O,R,L,3,I - fire_C,N,O,R,L,3,A
        - fire_C,N,O,R,L,3,N  = 0
 c7092: fire_C,N,O,R,L,3,N  = 0
 c7093: fire_C,N,O,R,L,4,P  = 0
 c7094: fire_C,N,O,R,L,4,D  = 0
 c7095: fire_C,N,O,R,L,4,W  = 0
 c7096: fire_C,N,O,R,L,4,C  = 0
 c7097: fire_C,N,O,R,L,4,I  = 0
 c7098: fire_C,N,O,R,L,4,A  = 0
 c7099: - fire_C,N,O,R,L,4,P - fire_C,N,O,R,L,4,D - fire_C,N,O,R,L,4,W
        - fire_C,N,O,R,L,4,C - fire_C,N,O,R,L,4,I - fire_C,N,O,R,L,4,A
        - fire_C,N,O,R,L,4,N  = 0
 c7100: fire_C,N,O,R,L,4,N  = 0
 c7101: fire_C,N,O,R,L,5,P  = 0
 c7102: fire_C,N,O,R,L,5,D  = 0
 c7103: fire_C,N,O,R,L,5,W  = 0
 c7104: fire_C,N,O,R,L,5,C  = 0
 c7105: fire_C,N,O,R,L,5,I  = 0
 c7106: fire_C,N,O,R,L,5,A  = 0
 c7107: - fire_C,N,O,R,L,5,P - fire_C,N,O,R,L,5,D - fire_C,N,O,R,L,5,W
        - fire_C,N,O,R,L,5,C - fire_C,N,O,R,L,5,I - fire_C,N,O,R,L,5,A
        - fire_C,N,O,R,L,5,N  = 0
 c7108: fire_C,N,O,R,L,5,N  = 0
 c7109: fire_C,N,O,R,N,1,P  = 0
 c7110: fire_C,N,O,R,N,1,D  = 0
 c7111: fire_C,N,O,R,N,1,W  = 0
 c7112: fire_C,N,O,R,N,1,C  = 0
 c7113: fire_C,N,O,R,N,1,I  = 0
 c7114: fire_C,N,O,R,N,1,A  = 0
 c7115: fire_C,N,O,R,N,1,L  = 0
 c7116: - fire_C,N,O,R,N,1,P - fire_C,N,O,R,N,1,D - fire_C,N,O,R,N,1,W
        - fire_C,N,O,R,N,1,C - fire_C,N,O,R,N,1,I - fire_C,N,O,R,N,1,A
        - fire_C,N,O,R,N,1,L  = 0
 c7117: fire_C,N,O,R,N,2,P  = 0
 c7118: fire_C,N,O,R,N,2,D  = 0
 c7119: fire_C,N,O,R,N,2,W  = 0
 c7120: fire_C,N,O,R,N,2,C  = 0
 c7121: fire_C,N,O,R,N,2,I  = 0
 c7122: fire_C,N,O,R,N,2,A  = 0
 c7123: fire_C,N,O,R,N,2,L  = 0
 c7124: - fire_C,N,O,R,N,2,P - fire_C,N,O,R,N,2,D - fire_C,N,O,R,N,2,W
        - fire_C,N,O,R,N,2,C - fire_C,N,O,R,N,2,I - fire_C,N,O,R,N,2,A
        - fire_C,N,O,R,N,2,L  = 0
 c7125: fire_C,N,O,R,N,3,P  = 0
 c7126: fire_C,N,O,R,N,3,D  = 0
 c7127: fire_C,N,O,R,N,3,W  = 0
 c7128: fire_C,N,O,R,N,3,C  = 0
 c7129: fire_C,N,O,R,N,3,I  = 0
 c7130: fire_C,N,O,R,N,3,A  = 0
 c7131: fire_C,N,O,R,N,3,L  = 0
 c7132: - fire_C,N,O,R,N,3,P - fire_C,N,O,R,N,3,D - fire_C,N,O,R,N,3,W
        - fire_C,N,O,R,N,3,C - fire_C,N,O,R,N,3,I - fire_C,N,O,R,N,3,A
        - fire_C,N,O,R,N,3,L  = 0
 c7133: fire_C,N,O,R,N,4,P  = 0
 c7134: fire_C,N,O,R,N,4,D  = 0
 c7135: fire_C,N,O,R,N,4,W  = 0
 c7136: fire_C,N,O,R,N,4,C  = 0
 c7137: fire_C,N,O,R,N,4,I  = 0
 c7138: fire_C,N,O,R,N,4,A  = 0
 c7139: fire_C,N,O,R,N,4,L  = 0
 c7140: - fire_C,N,O,R,N,4,P - fire_C,N,O,R,N,4,D - fire_C,N,O,R,N,4,W
        - fire_C,N,O,R,N,4,C - fire_C,N,O,R,N,4,I - fire_C,N,O,R,N,4,A
        - fire_C,N,O,R,N,4,L  = 0
 c7141: fire_C,N,O,R,N,5,P  = 0
 c7142: fire_C,N,O,R,N,5,D  = 0
 c7143: fire_C,N,O,R,N,5,W  = 0
 c7144: fire_C,N,O,R,N,5,C  = 0
 c7145: fire_C,N,O,R,N,5,I  = 0
 c7146: fire_C,N,O,R,N,5,A  = 0
 c7147: fire_C,N,O,R,N,5,L  = 0
 c7148: - fire_C,N,O,R,N,5,P - fire_C,N,O,R,N,5,D - fire_C,N,O,R,N,5,W
        - fire_C,N,O,R,N,5,C - fire_C,N,O,R,N,5,I - fire_C,N,O,R,N,5,A
        - fire_C,N,O,R,N,5,L  = 0
 c7149: - xEAr_B,R,N,L,P,2,1 + fire_B,R,N,L,P,1,P + fire_B,R,N,L,D,1,P
        + fire_B,R,N,L,C,1,P + fire_B,R,N,L,A,1,P  = 0
 c7150: - xEAr_B,R,N,L,D,2,1 + fire_B,R,N,L,P,1,D + fire_B,R,N,L,D,1,D
        + fire_B,R,N,L,W,1,D + fire_B,R,N,L,C,1,D  = 0
 c7151: - xEAr_B,R,N,L,W,2,1 + fire_B,R,N,L,W,1,W  = 0
 c7152: - xEAr_B,R,N,L,C,2,1 + fire_B,R,N,L,W,1,C + fire_B,R,N,L,C,1,C  = 0
 c7153: - xEAr_B,R,N,L,I,2,1 + fire_B,R,N,L,I,1,I  = 0
 c7154: - xEAr_B,R,N,L,A,2,1 + fire_B,R,N,L,A,1,A  = 0
 c7155: - xEAr_B,R,N,L,L,2,1 + fire_B,R,N,L,L,1,L  = 0
 c7156: - xEAr_B,R,N,L,N,2,1 + fire_B,R,N,L,N,1,N  = 0
 c7157: - xEAr_B,R,N,L,P,3,1 + fire_B,R,N,L,P,2,P + fire_B,R,N,L,D,2,P
        + fire_B,R,N,L,C,2,P + fire_B,R,N,L,A,2,P  = 0
 c7158: - xEAr_B,R,N,L,D,3,1 + fire_B,R,N,L,P,2,D + fire_B,R,N,L,D,2,D
        + fire_B,R,N,L,W,2,D + fire_B,R,N,L,C,2,D  = 0
 c7159: - xEAr_B,R,N,L,W,3,1 + fire_B,R,N,L,W,2,W  = 0
 c7160: - xEAr_B,R,N,L,C,3,1 + fire_B,R,N,L,W,2,C + fire_B,R,N,L,C,2,C  = 0
 c7161: - xEAr_B,R,N,L,I,3,1 + fire_B,R,N,L,I,2,I  = 0
 c7162: - xEAr_B,R,N,L,A,3,1 + fire_B,R,N,L,A,2,A  = 0
 c7163: - xEAr_B,R,N,L,L,3,1 + fire_B,R,N,L,L,2,L  = 0
 c7164: - xEAr_B,R,N,L,N,3,1 + fire_B,R,N,L,N,2,N  = 0
 c7165: - xEAr_B,R,N,L,P,4,1 + fire_B,R,N,L,P,3,P + fire_B,R,N,L,D,3,P
        + fire_B,R,N,L,C,3,P + fire_B,R,N,L,A,3,P  = 0
 c7166: - xEAr_B,R,N,L,D,4,1 + fire_B,R,N,L,P,3,D + fire_B,R,N,L,D,3,D
        + fire_B,R,N,L,W,3,D + fire_B,R,N,L,C,3,D  = 0
 c7167: - xEAr_B,R,N,L,W,4,1 + fire_B,R,N,L,W,3,W  = 0
 c7168: - xEAr_B,R,N,L,C,4,1 + fire_B,R,N,L,W,3,C + fire_B,R,N,L,C,3,C  = 0
 c7169: - xEAr_B,R,N,L,I,4,1 + fire_B,R,N,L,I,3,I  = 0
 c7170: - xEAr_B,R,N,L,A,4,1 + fire_B,R,N,L,A,3,A  = 0
 c7171: - xEAr_B,R,N,L,L,4,1 + fire_B,R,N,L,L,3,L  = 0
 c7172: - xEAr_B,R,N,L,N,4,1 + fire_B,R,N,L,N,3,N  = 0
 c7173: - xEAr_B,R,N,L,P,5,1 + fire_B,R,N,L,P,4,P + fire_B,R,N,L,D,4,P
        + fire_B,R,N,L,C,4,P + fire_B,R,N,L,A,4,P  = 0
 c7174: - xEAr_B,R,N,L,D,5,1 + fire_B,R,N,L,P,4,D + fire_B,R,N,L,D,4,D
        + fire_B,R,N,L,W,4,D + fire_B,R,N,L,C,4,D  = 0
 c7175: - xEAr_B,R,N,L,W,5,1 + fire_B,R,N,L,W,4,W  = 0
 c7176: - xEAr_B,R,N,L,C,5,1 + fire_B,R,N,L,W,4,C + fire_B,R,N,L,C,4,C  = 0
 c7177: - xEAr_B,R,N,L,I,5,1 + fire_B,R,N,L,I,4,I  = 0
 c7178: - xEAr_B,R,N,L,A,5,1 + fire_B,R,N,L,A,4,A  = 0
 c7179: - xEAr_B,R,N,L,L,5,1 + fire_B,R,N,L,L,4,L  = 0
 c7180: - xEAr_B,R,N,L,N,5,1 + fire_B,R,N,L,N,4,N  = 0
 c7181: - xEAr_B,R,N,C,P,2,1 + fire_B,R,N,C,P,1,P + fire_B,R,N,C,D,1,P
        + fire_B,R,N,C,C,1,P + fire_B,R,N,C,A,1,P  = 0
 c7182: - xEAr_B,R,N,C,D,2,1 + fire_B,R,N,C,P,1,D + fire_B,R,N,C,D,1,D
        + fire_B,R,N,C,W,1,D + fire_B,R,N,C,C,1,D  = 0
 c7183: - xEAr_B,R,N,C,W,2,1 + fire_B,R,N,C,W,1,W  = 0
 c7184: - xEAr_B,R,N,C,C,2,1 + fire_B,R,N,C,W,1,C + fire_B,R,N,C,C,1,C  = 0
 c7185: - xEAr_B,R,N,C,I,2,1 + fire_B,R,N,C,I,1,I  = 0
 c7186: - xEAr_B,R,N,C,A,2,1 + fire_B,R,N,C,A,1,A  = 0
 c7187: - xEAr_B,R,N,C,L,2,1 + fire_B,R,N,C,L,1,L  = 0
 c7188: - xEAr_B,R,N,C,N,2,1 + fire_B,R,N,C,N,1,N  = 0
 c7189: - xEAr_B,R,N,C,P,3,1 + fire_B,R,N,C,P,2,P + fire_B,R,N,C,D,2,P
        + fire_B,R,N,C,C,2,P + fire_B,R,N,C,A,2,P  = 0
 c7190: - xEAr_B,R,N,C,D,3,1 + fire_B,R,N,C,P,2,D + fire_B,R,N,C,D,2,D
        + fire_B,R,N,C,W,2,D + fire_B,R,N,C,C,2,D  = 0
 c7191: - xEAr_B,R,N,C,W,3,1 + fire_B,R,N,C,W,2,W  = 0
 c7192: - xEAr_B,R,N,C,C,3,1 + fire_B,R,N,C,W,2,C + fire_B,R,N,C,C,2,C  = 0
 c7193: - xEAr_B,R,N,C,I,3,1 + fire_B,R,N,C,I,2,I  = 0
 c7194: - xEAr_B,R,N,C,A,3,1 + fire_B,R,N,C,A,2,A  = 0
 c7195: - xEAr_B,R,N,C,L,3,1 + fire_B,R,N,C,L,2,L  = 0
 c7196: - xEAr_B,R,N,C,N,3,1 + fire_B,R,N,C,N,2,N  = 0
 c7197: - xEAr_B,R,N,C,P,4,1 + fire_B,R,N,C,P,3,P + fire_B,R,N,C,D,3,P
        + fire_B,R,N,C,C,3,P + fire_B,R,N,C,A,3,P  = 0
 c7198: - xEAr_B,R,N,C,D,4,1 + fire_B,R,N,C,P,3,D + fire_B,R,N,C,D,3,D
        + fire_B,R,N,C,W,3,D + fire_B,R,N,C,C,3,D  = 0
 c7199: - xEAr_B,R,N,C,W,4,1 + fire_B,R,N,C,W,3,W  = 0
 c7200: - xEAr_B,R,N,C,C,4,1 + fire_B,R,N,C,W,3,C + fire_B,R,N,C,C,3,C  = 0
 c7201: - xEAr_B,R,N,C,I,4,1 + fire_B,R,N,C,I,3,I  = 0
 c7202: - xEAr_B,R,N,C,A,4,1 + fire_B,R,N,C,A,3,A  = 0
 c7203: - xEAr_B,R,N,C,L,4,1 + fire_B,R,N,C,L,3,L  = 0
 c7204: - xEAr_B,R,N,C,N,4,1 + fire_B,R,N,C,N,3,N  = 0
 c7205: - xEAr_B,R,N,C,P,5,1 + fire_B,R,N,C,P,4,P + fire_B,R,N,C,D,4,P
        + fire_B,R,N,C,C,4,P + fire_B,R,N,C,A,4,P  = 0
 c7206: - xEAr_B,R,N,C,D,5,1 + fire_B,R,N,C,P,4,D + fire_B,R,N,C,D,4,D
        + fire_B,R,N,C,W,4,D + fire_B,R,N,C,C,4,D  = 0
 c7207: - xEAr_B,R,N,C,W,5,1 + fire_B,R,N,C,W,4,W  = 0
 c7208: - xEAr_B,R,N,C,C,5,1 + fire_B,R,N,C,W,4,C + fire_B,R,N,C,C,4,C  = 0
 c7209: - xEAr_B,R,N,C,I,5,1 + fire_B,R,N,C,I,4,I  = 0
 c7210: - xEAr_B,R,N,C,A,5,1 + fire_B,R,N,C,A,4,A  = 0
 c7211: - xEAr_B,R,N,C,L,5,1 + fire_B,R,N,C,L,4,L  = 0
 c7212: - xEAr_B,R,N,C,N,5,1 + fire_B,R,N,C,N,4,N  = 0
 c7213: - xEAr_B,R,N,R,P,2,1 + fire_B,R,N,R,P,1,P + fire_B,R,N,R,D,1,P
        + fire_B,R,N,R,C,1,P + fire_B,R,N,R,A,1,P  = 0
 c7214: - xEAr_B,R,N,R,D,2,1 + fire_B,R,N,R,P,1,D + fire_B,R,N,R,D,1,D
        + fire_B,R,N,R,W,1,D + fire_B,R,N,R,C,1,D  = 0
 c7215: - xEAr_B,R,N,R,W,2,1 + fire_B,R,N,R,W,1,W  = 0
 c7216: - xEAr_B,R,N,R,C,2,1 + fire_B,R,N,R,W,1,C + fire_B,R,N,R,C,1,C  = 0
 c7217: - xEAr_B,R,N,R,I,2,1 + fire_B,R,N,R,I,1,I  = 0
 c7218: - xEAr_B,R,N,R,A,2,1 + fire_B,R,N,R,A,1,A  = 0
 c7219: - xEAr_B,R,N,R,L,2,1 + fire_B,R,N,R,L,1,L  = 0
 c7220: - xEAr_B,R,N,R,N,2,1 + fire_B,R,N,R,N,1,N  = 0
 c7221: - xEAr_B,R,N,R,P,3,1 + fire_B,R,N,R,P,2,P + fire_B,R,N,R,D,2,P
        + fire_B,R,N,R,C,2,P + fire_B,R,N,R,A,2,P  = 0
 c7222: - xEAr_B,R,N,R,D,3,1 + fire_B,R,N,R,P,2,D + fire_B,R,N,R,D,2,D
        + fire_B,R,N,R,W,2,D + fire_B,R,N,R,C,2,D  = 0
 c7223: - xEAr_B,R,N,R,W,3,1 + fire_B,R,N,R,W,2,W  = 0
 c7224: - xEAr_B,R,N,R,C,3,1 + fire_B,R,N,R,W,2,C + fire_B,R,N,R,C,2,C  = 0
 c7225: - xEAr_B,R,N,R,I,3,1 + fire_B,R,N,R,I,2,I  = 0
 c7226: - xEAr_B,R,N,R,A,3,1 + fire_B,R,N,R,A,2,A  = 0
 c7227: - xEAr_B,R,N,R,L,3,1 + fire_B,R,N,R,L,2,L  = 0
 c7228: - xEAr_B,R,N,R,N,3,1 + fire_B,R,N,R,N,2,N  = 0
 c7229: - xEAr_B,R,N,R,P,4,1 + fire_B,R,N,R,P,3,P + fire_B,R,N,R,D,3,P
        + fire_B,R,N,R,C,3,P + fire_B,R,N,R,A,3,P  = 0
 c7230: - xEAr_B,R,N,R,D,4,1 + fire_B,R,N,R,P,3,D + fire_B,R,N,R,D,3,D
        + fire_B,R,N,R,W,3,D + fire_B,R,N,R,C,3,D  = 0
 c7231: - xEAr_B,R,N,R,W,4,1 + fire_B,R,N,R,W,3,W  = 0
 c7232: - xEAr_B,R,N,R,C,4,1 + fire_B,R,N,R,W,3,C + fire_B,R,N,R,C,3,C  = 0
 c7233: - xEAr_B,R,N,R,I,4,1 + fire_B,R,N,R,I,3,I  = 0
 c7234: - xEAr_B,R,N,R,A,4,1 + fire_B,R,N,R,A,3,A  = 0
 c7235: - xEAr_B,R,N,R,L,4,1 + fire_B,R,N,R,L,3,L  = 0
 c7236: - xEAr_B,R,N,R,N,4,1 + fire_B,R,N,R,N,3,N  = 0
 c7237: - xEAr_B,R,N,R,P,5,1 + fire_B,R,N,R,P,4,P + fire_B,R,N,R,D,4,P
        + fire_B,R,N,R,C,4,P + fire_B,R,N,R,A,4,P  = 0
 c7238: - xEAr_B,R,N,R,D,5,1 + fire_B,R,N,R,P,4,D + fire_B,R,N,R,D,4,D
        + fire_B,R,N,R,W,4,D + fire_B,R,N,R,C,4,D  = 0
 c7239: - xEAr_B,R,N,R,W,5,1 + fire_B,R,N,R,W,4,W  = 0
 c7240: - xEAr_B,R,N,R,C,5,1 + fire_B,R,N,R,W,4,C + fire_B,R,N,R,C,4,C  = 0
 c7241: - xEAr_B,R,N,R,I,5,1 + fire_B,R,N,R,I,4,I  = 0
 c7242: - xEAr_B,R,N,R,A,5,1 + fire_B,R,N,R,A,4,A  = 0
 c7243: - xEAr_B,R,N,R,L,5,1 + fire_B,R,N,R,L,4,L  = 0
 c7244: - xEAr_B,R,N,R,N,5,1 + fire_B,R,N,R,N,4,N  = 0
 c7245: - xEAr_B,R,P,L,P,2,1 + fire_B,R,P,L,P,1,P + fire_B,R,P,L,D,1,P
        + fire_B,R,P,L,C,1,P + fire_B,R,P,L,A,1,P  = 0
 c7246: - xEAr_B,R,P,L,D,2,1 + fire_B,R,P,L,P,1,D + fire_B,R,P,L,D,1,D
        + fire_B,R,P,L,W,1,D + fire_B,R,P,L,C,1,D  = 0
 c7247: - xEAr_B,R,P,L,W,2,1 + fire_B,R,P,L,W,1,W  = 0
 c7248: - xEAr_B,R,P,L,C,2,1 + fire_B,R,P,L,W,1,C + fire_B,R,P,L,C,1,C  = 0
 c7249: - xEAr_B,R,P,L,I,2,1 + fire_B,R,P,L,I,1,I  = 0
 c7250: - xEAr_B,R,P,L,A,2,1 + fire_B,R,P,L,A,1,A  = 0
 c7251: - xEAr_B,R,P,L,L,2,1 + fire_B,R,P,L,L,1,L  = 0
 c7252: - xEAr_B,R,P,L,N,2,1 + fire_B,R,P,L,N,1,N  = 0
 c7253: - xEAr_B,R,P,L,P,3,1 + fire_B,R,P,L,P,2,P + fire_B,R,P,L,D,2,P
        + fire_B,R,P,L,C,2,P + fire_B,R,P,L,A,2,P  = 0
 c7254: - xEAr_B,R,P,L,D,3,1 + fire_B,R,P,L,P,2,D + fire_B,R,P,L,D,2,D
        + fire_B,R,P,L,W,2,D + fire_B,R,P,L,C,2,D  = 0
 c7255: - xEAr_B,R,P,L,W,3,1 + fire_B,R,P,L,W,2,W  = 0
 c7256: - xEAr_B,R,P,L,C,3,1 + fire_B,R,P,L,W,2,C + fire_B,R,P,L,C,2,C  = 0
 c7257: - xEAr_B,R,P,L,I,3,1 + fire_B,R,P,L,I,2,I  = 0
 c7258: - xEAr_B,R,P,L,A,3,1 + fire_B,R,P,L,A,2,A  = 0
 c7259: - xEAr_B,R,P,L,L,3,1 + fire_B,R,P,L,L,2,L  = 0
 c7260: - xEAr_B,R,P,L,N,3,1 + fire_B,R,P,L,N,2,N  = 0
 c7261: - xEAr_B,R,P,L,P,4,1 + fire_B,R,P,L,P,3,P + fire_B,R,P,L,D,3,P
        + fire_B,R,P,L,C,3,P + fire_B,R,P,L,A,3,P  = 0
 c7262: - xEAr_B,R,P,L,D,4,1 + fire_B,R,P,L,P,3,D + fire_B,R,P,L,D,3,D
        + fire_B,R,P,L,W,3,D + fire_B,R,P,L,C,3,D  = 0
 c7263: - xEAr_B,R,P,L,W,4,1 + fire_B,R,P,L,W,3,W  = 0
 c7264: - xEAr_B,R,P,L,C,4,1 + fire_B,R,P,L,W,3,C + fire_B,R,P,L,C,3,C  = 0
 c7265: - xEAr_B,R,P,L,I,4,1 + fire_B,R,P,L,I,3,I  = 0
 c7266: - xEAr_B,R,P,L,A,4,1 + fire_B,R,P,L,A,3,A  = 0
 c7267: - xEAr_B,R,P,L,L,4,1 + fire_B,R,P,L,L,3,L  = 0
 c7268: - xEAr_B,R,P,L,N,4,1 + fire_B,R,P,L,N,3,N  = 0
 c7269: - xEAr_B,R,P,L,P,5,1 + fire_B,R,P,L,P,4,P + fire_B,R,P,L,D,4,P
        + fire_B,R,P,L,C,4,P + fire_B,R,P,L,A,4,P  = 0
 c7270: - xEAr_B,R,P,L,D,5,1 + fire_B,R,P,L,P,4,D + fire_B,R,P,L,D,4,D
        + fire_B,R,P,L,W,4,D + fire_B,R,P,L,C,4,D  = 0
 c7271: - xEAr_B,R,P,L,W,5,1 + fire_B,R,P,L,W,4,W  = 0
 c7272: - xEAr_B,R,P,L,C,5,1 + fire_B,R,P,L,W,4,C + fire_B,R,P,L,C,4,C  = 0
 c7273: - xEAr_B,R,P,L,I,5,1 + fire_B,R,P,L,I,4,I  = 0
 c7274: - xEAr_B,R,P,L,A,5,1 + fire_B,R,P,L,A,4,A  = 0
 c7275: - xEAr_B,R,P,L,L,5,1 + fire_B,R,P,L,L,4,L  = 0
 c7276: - xEAr_B,R,P,L,N,5,1 + fire_B,R,P,L,N,4,N  = 0
 c7277: - xEAr_B,R,P,C,P,2,1 + fire_B,R,P,C,P,1,P + fire_B,R,P,C,D,1,P
        + fire_B,R,P,C,C,1,P + fire_B,R,P,C,A,1,P  = 0
 c7278: - xEAr_B,R,P,C,D,2,1 + fire_B,R,P,C,P,1,D + fire_B,R,P,C,D,1,D
        + fire_B,R,P,C,W,1,D + fire_B,R,P,C,C,1,D  = 0
 c7279: - xEAr_B,R,P,C,W,2,1 + fire_B,R,P,C,W,1,W  = 0
 c7280: - xEAr_B,R,P,C,C,2,1 + fire_B,R,P,C,W,1,C + fire_B,R,P,C,C,1,C  = 0
 c7281: - xEAr_B,R,P,C,I,2,1 + fire_B,R,P,C,I,1,I  = 0
 c7282: - xEAr_B,R,P,C,A,2,1 + fire_B,R,P,C,A,1,A  = 0
 c7283: - xEAr_B,R,P,C,L,2,1 + fire_B,R,P,C,L,1,L  = 0
 c7284: - xEAr_B,R,P,C,N,2,1 + fire_B,R,P,C,N,1,N  = 0
 c7285: - xEAr_B,R,P,C,P,3,1 + fire_B,R,P,C,P,2,P + fire_B,R,P,C,D,2,P
        + fire_B,R,P,C,C,2,P + fire_B,R,P,C,A,2,P  = 0
 c7286: - xEAr_B,R,P,C,D,3,1 + fire_B,R,P,C,P,2,D + fire_B,R,P,C,D,2,D
        + fire_B,R,P,C,W,2,D + fire_B,R,P,C,C,2,D  = 0
 c7287: - xEAr_B,R,P,C,W,3,1 + fire_B,R,P,C,W,2,W  = 0
 c7288: - xEAr_B,R,P,C,C,3,1 + fire_B,R,P,C,W,2,C + fire_B,R,P,C,C,2,C  = 0
 c7289: - xEAr_B,R,P,C,I,3,1 + fire_B,R,P,C,I,2,I  = 0
 c7290: - xEAr_B,R,P,C,A,3,1 + fire_B,R,P,C,A,2,A  = 0
 c7291: - xEAr_B,R,P,C,L,3,1 + fire_B,R,P,C,L,2,L  = 0
 c7292: - xEAr_B,R,P,C,N,3,1 + fire_B,R,P,C,N,2,N  = 0
 c7293: - xEAr_B,R,P,C,P,4,1 + fire_B,R,P,C,P,3,P + fire_B,R,P,C,D,3,P
        + fire_B,R,P,C,C,3,P + fire_B,R,P,C,A,3,P  = 0
 c7294: - xEAr_B,R,P,C,D,4,1 + fire_B,R,P,C,P,3,D + fire_B,R,P,C,D,3,D
        + fire_B,R,P,C,W,3,D + fire_B,R,P,C,C,3,D  = 0
 c7295: - xEAr_B,R,P,C,W,4,1 + fire_B,R,P,C,W,3,W  = 0
 c7296: - xEAr_B,R,P,C,C,4,1 + fire_B,R,P,C,W,3,C + fire_B,R,P,C,C,3,C  = 0
 c7297: - xEAr_B,R,P,C,I,4,1 + fire_B,R,P,C,I,3,I  = 0
 c7298: - xEAr_B,R,P,C,A,4,1 + fire_B,R,P,C,A,3,A  = 0
 c7299: - xEAr_B,R,P,C,L,4,1 + fire_B,R,P,C,L,3,L  = 0
 c7300: - xEAr_B,R,P,C,N,4,1 + fire_B,R,P,C,N,3,N  = 0
 c7301: - xEAr_B,R,P,C,P,5,1 + fire_B,R,P,C,P,4,P + fire_B,R,P,C,D,4,P
        + fire_B,R,P,C,C,4,P + fire_B,R,P,C,A,4,P  = 0
 c7302: - xEAr_B,R,P,C,D,5,1 + fire_B,R,P,C,P,4,D + fire_B,R,P,C,D,4,D
        + fire_B,R,P,C,W,4,D + fire_B,R,P,C,C,4,D  = 0
 c7303: - xEAr_B,R,P,C,W,5,1 + fire_B,R,P,C,W,4,W  = 0
 c7304: - xEAr_B,R,P,C,C,5,1 + fire_B,R,P,C,W,4,C + fire_B,R,P,C,C,4,C  = 0
 c7305: - xEAr_B,R,P,C,I,5,1 + fire_B,R,P,C,I,4,I  = 0
 c7306: - xEAr_B,R,P,C,A,5,1 + fire_B,R,P,C,A,4,A  = 0
 c7307: - xEAr_B,R,P,C,L,5,1 + fire_B,R,P,C,L,4,L  = 0
 c7308: - xEAr_B,R,P,C,N,5,1 + fire_B,R,P,C,N,4,N  = 0
 c7309: - xEAr_B,R,P,R,P,2,1 + fire_B,R,P,R,P,1,P + fire_B,R,P,R,D,1,P
        + fire_B,R,P,R,C,1,P + fire_B,R,P,R,A,1,P  = 0
 c7310: - xEAr_B,R,P,R,D,2,1 + fire_B,R,P,R,P,1,D + fire_B,R,P,R,D,1,D
        + fire_B,R,P,R,W,1,D + fire_B,R,P,R,C,1,D  = 0
 c7311: - xEAr_B,R,P,R,W,2,1 + fire_B,R,P,R,W,1,W  = 0
 c7312: - xEAr_B,R,P,R,C,2,1 + fire_B,R,P,R,W,1,C + fire_B,R,P,R,C,1,C  = 0
 c7313: - xEAr_B,R,P,R,I,2,1 + fire_B,R,P,R,I,1,I  = 0
 c7314: - xEAr_B,R,P,R,A,2,1 + fire_B,R,P,R,A,1,A  = 0
 c7315: - xEAr_B,R,P,R,L,2,1 + fire_B,R,P,R,L,1,L  = 0
 c7316: - xEAr_B,R,P,R,N,2,1 + fire_B,R,P,R,N,1,N  = 0
 c7317: - xEAr_B,R,P,R,P,3,1 + fire_B,R,P,R,P,2,P + fire_B,R,P,R,D,2,P
        + fire_B,R,P,R,C,2,P + fire_B,R,P,R,A,2,P  = 0
 c7318: - xEAr_B,R,P,R,D,3,1 + fire_B,R,P,R,P,2,D + fire_B,R,P,R,D,2,D
        + fire_B,R,P,R,W,2,D + fire_B,R,P,R,C,2,D  = 0
 c7319: - xEAr_B,R,P,R,W,3,1 + fire_B,R,P,R,W,2,W  = 0
 c7320: - xEAr_B,R,P,R,C,3,1 + fire_B,R,P,R,W,2,C + fire_B,R,P,R,C,2,C  = 0
 c7321: - xEAr_B,R,P,R,I,3,1 + fire_B,R,P,R,I,2,I  = 0
 c7322: - xEAr_B,R,P,R,A,3,1 + fire_B,R,P,R,A,2,A  = 0
 c7323: - xEAr_B,R,P,R,L,3,1 + fire_B,R,P,R,L,2,L  = 0
 c7324: - xEAr_B,R,P,R,N,3,1 + fire_B,R,P,R,N,2,N  = 0
 c7325: - xEAr_B,R,P,R,P,4,1 + fire_B,R,P,R,P,3,P + fire_B,R,P,R,D,3,P
        + fire_B,R,P,R,C,3,P + fire_B,R,P,R,A,3,P  = 0
 c7326: - xEAr_B,R,P,R,D,4,1 + fire_B,R,P,R,P,3,D + fire_B,R,P,R,D,3,D
        + fire_B,R,P,R,W,3,D + fire_B,R,P,R,C,3,D  = 0
 c7327: - xEAr_B,R,P,R,W,4,1 + fire_B,R,P,R,W,3,W  = 0
 c7328: - xEAr_B,R,P,R,C,4,1 + fire_B,R,P,R,W,3,C + fire_B,R,P,R,C,3,C  = 0
 c7329: - xEAr_B,R,P,R,I,4,1 + fire_B,R,P,R,I,3,I  = 0
 c7330: - xEAr_B,R,P,R,A,4,1 + fire_B,R,P,R,A,3,A  = 0
 c7331: - xEAr_B,R,P,R,L,4,1 + fire_B,R,P,R,L,3,L  = 0
 c7332: - xEAr_B,R,P,R,N,4,1 + fire_B,R,P,R,N,3,N  = 0
 c7333: - xEAr_B,R,P,R,P,5,1 + fire_B,R,P,R,P,4,P + fire_B,R,P,R,D,4,P
        + fire_B,R,P,R,C,4,P + fire_B,R,P,R,A,4,P  = 0
 c7334: - xEAr_B,R,P,R,D,5,1 + fire_B,R,P,R,P,4,D + fire_B,R,P,R,D,4,D
        + fire_B,R,P,R,W,4,D + fire_B,R,P,R,C,4,D  = 0
 c7335: - xEAr_B,R,P,R,W,5,1 + fire_B,R,P,R,W,4,W  = 0
 c7336: - xEAr_B,R,P,R,C,5,1 + fire_B,R,P,R,W,4,C + fire_B,R,P,R,C,4,C  = 0
 c7337: - xEAr_B,R,P,R,I,5,1 + fire_B,R,P,R,I,4,I  = 0
 c7338: - xEAr_B,R,P,R,A,5,1 + fire_B,R,P,R,A,4,A  = 0
 c7339: - xEAr_B,R,P,R,L,5,1 + fire_B,R,P,R,L,4,L  = 0
 c7340: - xEAr_B,R,P,R,N,5,1 + fire_B,R,P,R,N,4,N  = 0
 c7341: - xEAr_B,N,N,L,P,2,1 + fire_B,N,N,L,P,1,P + fire_B,N,N,L,D,1,P
        + fire_B,N,N,L,C,1,P + fire_B,N,N,L,A,1,P  = 0
 c7342: - xEAr_B,N,N,L,D,2,1 + fire_B,N,N,L,P,1,D + fire_B,N,N,L,D,1,D
        + fire_B,N,N,L,W,1,D + fire_B,N,N,L,C,1,D  = 0
 c7343: - xEAr_B,N,N,L,W,2,1 + fire_B,N,N,L,W,1,W  = 0
 c7344: - xEAr_B,N,N,L,C,2,1 + fire_B,N,N,L,W,1,C + fire_B,N,N,L,C,1,C  = 0
 c7345: - xEAr_B,N,N,L,I,2,1 + fire_B,N,N,L,I,1,I  = 0
 c7346: - xEAr_B,N,N,L,A,2,1 + fire_B,N,N,L,A,1,A  = 0
 c7347: - xEAr_B,N,N,L,L,2,1 + fire_B,N,N,L,L,1,L  = 0
 c7348: - xEAr_B,N,N,L,N,2,1 + fire_B,N,N,L,N,1,N  = 0
 c7349: - xEAr_B,N,N,L,P,3,1 + fire_B,N,N,L,P,2,P + fire_B,N,N,L,D,2,P
        + fire_B,N,N,L,C,2,P + fire_B,N,N,L,A,2,P  = 0
 c7350: - xEAr_B,N,N,L,D,3,1 + fire_B,N,N,L,P,2,D + fire_B,N,N,L,D,2,D
        + fire_B,N,N,L,W,2,D + fire_B,N,N,L,C,2,D  = 0
 c7351: - xEAr_B,N,N,L,W,3,1 + fire_B,N,N,L,W,2,W  = 0
 c7352: - xEAr_B,N,N,L,C,3,1 + fire_B,N,N,L,W,2,C + fire_B,N,N,L,C,2,C  = 0
 c7353: - xEAr_B,N,N,L,I,3,1 + fire_B,N,N,L,I,2,I  = 0
 c7354: - xEAr_B,N,N,L,A,3,1 + fire_B,N,N,L,A,2,A  = 0
 c7355: - xEAr_B,N,N,L,L,3,1 + fire_B,N,N,L,L,2,L  = 0
 c7356: - xEAr_B,N,N,L,N,3,1 + fire_B,N,N,L,N,2,N  = 0
 c7357: - xEAr_B,N,N,L,P,4,1 + fire_B,N,N,L,P,3,P + fire_B,N,N,L,D,3,P
        + fire_B,N,N,L,C,3,P + fire_B,N,N,L,A,3,P  = 0
 c7358: - xEAr_B,N,N,L,D,4,1 + fire_B,N,N,L,P,3,D + fire_B,N,N,L,D,3,D
        + fire_B,N,N,L,W,3,D + fire_B,N,N,L,C,3,D  = 0
 c7359: - xEAr_B,N,N,L,W,4,1 + fire_B,N,N,L,W,3,W  = 0
 c7360: - xEAr_B,N,N,L,C,4,1 + fire_B,N,N,L,W,3,C + fire_B,N,N,L,C,3,C  = 0
 c7361: - xEAr_B,N,N,L,I,4,1 + fire_B,N,N,L,I,3,I  = 0
 c7362: - xEAr_B,N,N,L,A,4,1 + fire_B,N,N,L,A,3,A  = 0
 c7363: - xEAr_B,N,N,L,L,4,1 + fire_B,N,N,L,L,3,L  = 0
 c7364: - xEAr_B,N,N,L,N,4,1 + fire_B,N,N,L,N,3,N  = 0
 c7365: - xEAr_B,N,N,L,P,5,1 + fire_B,N,N,L,P,4,P + fire_B,N,N,L,D,4,P
        + fire_B,N,N,L,C,4,P + fire_B,N,N,L,A,4,P  = 0
 c7366: - xEAr_B,N,N,L,D,5,1 + fire_B,N,N,L,P,4,D + fire_B,N,N,L,D,4,D
        + fire_B,N,N,L,W,4,D + fire_B,N,N,L,C,4,D  = 0
 c7367: - xEAr_B,N,N,L,W,5,1 + fire_B,N,N,L,W,4,W  = 0
 c7368: - xEAr_B,N,N,L,C,5,1 + fire_B,N,N,L,W,4,C + fire_B,N,N,L,C,4,C  = 0
 c7369: - xEAr_B,N,N,L,I,5,1 + fire_B,N,N,L,I,4,I  = 0
 c7370: - xEAr_B,N,N,L,A,5,1 + fire_B,N,N,L,A,4,A  = 0
 c7371: - xEAr_B,N,N,L,L,5,1 + fire_B,N,N,L,L,4,L  = 0
 c7372: - xEAr_B,N,N,L,N,5,1 + fire_B,N,N,L,N,4,N  = 0
 c7373: - xEAr_B,N,N,C,P,2,1 + fire_B,N,N,C,P,1,P + fire_B,N,N,C,D,1,P
        + fire_B,N,N,C,C,1,P + fire_B,N,N,C,A,1,P  = 0
 c7374: - xEAr_B,N,N,C,D,2,1 + fire_B,N,N,C,P,1,D + fire_B,N,N,C,D,1,D
        + fire_B,N,N,C,W,1,D + fire_B,N,N,C,C,1,D  = 0
 c7375: - xEAr_B,N,N,C,W,2,1 + fire_B,N,N,C,W,1,W  = 0
 c7376: - xEAr_B,N,N,C,C,2,1 + fire_B,N,N,C,W,1,C + fire_B,N,N,C,C,1,C  = 0
 c7377: - xEAr_B,N,N,C,I,2,1 + fire_B,N,N,C,I,1,I  = 0
 c7378: - xEAr_B,N,N,C,A,2,1 + fire_B,N,N,C,A,1,A  = 0
 c7379: - xEAr_B,N,N,C,L,2,1 + fire_B,N,N,C,L,1,L  = 0
 c7380: - xEAr_B,N,N,C,N,2,1 + fire_B,N,N,C,N,1,N  = 0
 c7381: - xEAr_B,N,N,C,P,3,1 + fire_B,N,N,C,P,2,P + fire_B,N,N,C,D,2,P
        + fire_B,N,N,C,C,2,P + fire_B,N,N,C,A,2,P  = 0
 c7382: - xEAr_B,N,N,C,D,3,1 + fire_B,N,N,C,P,2,D + fire_B,N,N,C,D,2,D
        + fire_B,N,N,C,W,2,D + fire_B,N,N,C,C,2,D  = 0
 c7383: - xEAr_B,N,N,C,W,3,1 + fire_B,N,N,C,W,2,W  = 0
 c7384: - xEAr_B,N,N,C,C,3,1 + fire_B,N,N,C,W,2,C + fire_B,N,N,C,C,2,C  = 0
 c7385: - xEAr_B,N,N,C,I,3,1 + fire_B,N,N,C,I,2,I  = 0
 c7386: - xEAr_B,N,N,C,A,3,1 + fire_B,N,N,C,A,2,A  = 0
 c7387: - xEAr_B,N,N,C,L,3,1 + fire_B,N,N,C,L,2,L  = 0
 c7388: - xEAr_B,N,N,C,N,3,1 + fire_B,N,N,C,N,2,N  = 0
 c7389: - xEAr_B,N,N,C,P,4,1 + fire_B,N,N,C,P,3,P + fire_B,N,N,C,D,3,P
        + fire_B,N,N,C,C,3,P + fire_B,N,N,C,A,3,P  = 0
 c7390: - xEAr_B,N,N,C,D,4,1 + fire_B,N,N,C,P,3,D + fire_B,N,N,C,D,3,D
        + fire_B,N,N,C,W,3,D + fire_B,N,N,C,C,3,D  = 0
 c7391: - xEAr_B,N,N,C,W,4,1 + fire_B,N,N,C,W,3,W  = 0
 c7392: - xEAr_B,N,N,C,C,4,1 + fire_B,N,N,C,W,3,C + fire_B,N,N,C,C,3,C  = 0
 c7393: - xEAr_B,N,N,C,I,4,1 + fire_B,N,N,C,I,3,I  = 0
 c7394: - xEAr_B,N,N,C,A,4,1 + fire_B,N,N,C,A,3,A  = 0
 c7395: - xEAr_B,N,N,C,L,4,1 + fire_B,N,N,C,L,3,L  = 0
 c7396: - xEAr_B,N,N,C,N,4,1 + fire_B,N,N,C,N,3,N  = 0
 c7397: - xEAr_B,N,N,C,P,5,1 + fire_B,N,N,C,P,4,P + fire_B,N,N,C,D,4,P
        + fire_B,N,N,C,C,4,P + fire_B,N,N,C,A,4,P  = 0
 c7398: - xEAr_B,N,N,C,D,5,1 + fire_B,N,N,C,P,4,D + fire_B,N,N,C,D,4,D
        + fire_B,N,N,C,W,4,D + fire_B,N,N,C,C,4,D  = 0
 c7399: - xEAr_B,N,N,C,W,5,1 + fire_B,N,N,C,W,4,W  = 0
 c7400: - xEAr_B,N,N,C,C,5,1 + fire_B,N,N,C,W,4,C + fire_B,N,N,C,C,4,C  = 0
 c7401: - xEAr_B,N,N,C,I,5,1 + fire_B,N,N,C,I,4,I  = 0
 c7402: - xEAr_B,N,N,C,A,5,1 + fire_B,N,N,C,A,4,A  = 0
 c7403: - xEAr_B,N,N,C,L,5,1 + fire_B,N,N,C,L,4,L  = 0
 c7404: - xEAr_B,N,N,C,N,5,1 + fire_B,N,N,C,N,4,N  = 0
 c7405: - xEAr_B,N,P,R,P,2,1 + fire_B,N,P,R,P,1,P + fire_B,N,P,R,D,1,P
        + fire_B,N,P,R,C,1,P + fire_B,N,P,R,A,1,P  = 0
 c7406: - xEAr_B,N,P,R,D,2,1 + fire_B,N,P,R,P,1,D + fire_B,N,P,R,D,1,D
        + fire_B,N,P,R,W,1,D + fire_B,N,P,R,C,1,D  = 0
 c7407: - xEAr_B,N,P,R,W,2,1 + fire_B,N,P,R,W,1,W  = 0
 c7408: - xEAr_B,N,P,R,C,2,1 + fire_B,N,P,R,W,1,C + fire_B,N,P,R,C,1,C  = 0
 c7409: - xEAr_B,N,P,R,I,2,1 + fire_B,N,P,R,I,1,I  = 0
 c7410: - xEAr_B,N,P,R,A,2,1 + fire_B,N,P,R,A,1,A  = 0
 c7411: - xEAr_B,N,P,R,L,2,1 + fire_B,N,P,R,L,1,L  = 0
 c7412: - xEAr_B,N,P,R,N,2,1 + fire_B,N,P,R,N,1,N  = 0
 c7413: - xEAr_B,N,P,R,P,3,1 + fire_B,N,P,R,P,2,P + fire_B,N,P,R,D,2,P
        + fire_B,N,P,R,C,2,P + fire_B,N,P,R,A,2,P  = 0
 c7414: - xEAr_B,N,P,R,D,3,1 + fire_B,N,P,R,P,2,D + fire_B,N,P,R,D,2,D
        + fire_B,N,P,R,W,2,D + fire_B,N,P,R,C,2,D  = 0
 c7415: - xEAr_B,N,P,R,W,3,1 + fire_B,N,P,R,W,2,W  = 0
 c7416: - xEAr_B,N,P,R,C,3,1 + fire_B,N,P,R,W,2,C + fire_B,N,P,R,C,2,C  = 0
 c7417: - xEAr_B,N,P,R,I,3,1 + fire_B,N,P,R,I,2,I  = 0
 c7418: - xEAr_B,N,P,R,A,3,1 + fire_B,N,P,R,A,2,A  = 0
 c7419: - xEAr_B,N,P,R,L,3,1 + fire_B,N,P,R,L,2,L  = 0
 c7420: - xEAr_B,N,P,R,N,3,1 + fire_B,N,P,R,N,2,N  = 0
 c7421: - xEAr_B,N,P,R,P,4,1 + fire_B,N,P,R,P,3,P + fire_B,N,P,R,D,3,P
        + fire_B,N,P,R,C,3,P + fire_B,N,P,R,A,3,P  = 0
 c7422: - xEAr_B,N,P,R,D,4,1 + fire_B,N,P,R,P,3,D + fire_B,N,P,R,D,3,D
        + fire_B,N,P,R,W,3,D + fire_B,N,P,R,C,3,D  = 0
 c7423: - xEAr_B,N,P,R,W,4,1 + fire_B,N,P,R,W,3,W  = 0
 c7424: - xEAr_B,N,P,R,C,4,1 + fire_B,N,P,R,W,3,C + fire_B,N,P,R,C,3,C  = 0
 c7425: - xEAr_B,N,P,R,I,4,1 + fire_B,N,P,R,I,3,I  = 0
 c7426: - xEAr_B,N,P,R,A,4,1 + fire_B,N,P,R,A,3,A  = 0
 c7427: - xEAr_B,N,P,R,L,4,1 + fire_B,N,P,R,L,3,L  = 0
 c7428: - xEAr_B,N,P,R,N,4,1 + fire_B,N,P,R,N,3,N  = 0
 c7429: - xEAr_B,N,P,R,P,5,1 + fire_B,N,P,R,P,4,P + fire_B,N,P,R,D,4,P
        + fire_B,N,P,R,C,4,P + fire_B,N,P,R,A,4,P  = 0
 c7430: - xEAr_B,N,P,R,D,5,1 + fire_B,N,P,R,P,4,D + fire_B,N,P,R,D,4,D
        + fire_B,N,P,R,W,4,D + fire_B,N,P,R,C,4,D  = 0
 c7431: - xEAr_B,N,P,R,W,5,1 + fire_B,N,P,R,W,4,W  = 0
 c7432: - xEAr_B,N,P,R,C,5,1 + fire_B,N,P,R,W,4,C + fire_B,N,P,R,C,4,C  = 0
 c7433: - xEAr_B,N,P,R,I,5,1 + fire_B,N,P,R,I,4,I  = 0
 c7434: - xEAr_B,N,P,R,A,5,1 + fire_B,N,P,R,A,4,A  = 0
 c7435: - xEAr_B,N,P,R,L,5,1 + fire_B,N,P,R,L,4,L  = 0
 c7436: - xEAr_B,N,P,R,N,5,1 + fire_B,N,P,R,N,4,N  = 0
 c7437: - xEAr_C,N,N,L,P,2,1 + fire_C,N,N,L,P,1,P + fire_C,N,N,L,D,1,P
        + fire_C,N,N,L,C,1,P + fire_C,N,N,L,A,1,P  = 0
 c7438: - xEAr_C,N,N,L,D,2,1 + fire_C,N,N,L,P,1,D + fire_C,N,N,L,D,1,D
        + fire_C,N,N,L,W,1,D + fire_C,N,N,L,C,1,D  = 0
 c7439: - xEAr_C,N,N,L,W,2,1 + fire_C,N,N,L,W,1,W  = 0
 c7440: - xEAr_C,N,N,L,C,2,1 + fire_C,N,N,L,W,1,C + fire_C,N,N,L,C,1,C  = 0
 c7441: - xEAr_C,N,N,L,I,2,1 + fire_C,N,N,L,I,1,I  = 0
 c7442: - xEAr_C,N,N,L,A,2,1 + fire_C,N,N,L,A,1,A  = 0
 c7443: - xEAr_C,N,N,L,L,2,1 + fire_C,N,N,L,L,1,L  = 0
 c7444: - xEAr_C,N,N,L,N,2,1 + fire_C,N,N,L,N,1,N  = 0
 c7445: - xEAr_C,N,N,L,P,3,1 + fire_C,N,N,L,P,2,P + fire_C,N,N,L,D,2,P
        + fire_C,N,N,L,C,2,P + fire_C,N,N,L,A,2,P  = 0
 c7446: - xEAr_C,N,N,L,D,3,1 + fire_C,N,N,L,P,2,D + fire_C,N,N,L,D,2,D
        + fire_C,N,N,L,W,2,D + fire_C,N,N,L,C,2,D  = 0
 c7447: - xEAr_C,N,N,L,W,3,1 + fire_C,N,N,L,W,2,W  = 0
 c7448: - xEAr_C,N,N,L,C,3,1 + fire_C,N,N,L,W,2,C + fire_C,N,N,L,C,2,C  = 0
 c7449: - xEAr_C,N,N,L,I,3,1 + fire_C,N,N,L,I,2,I  = 0
 c7450: - xEAr_C,N,N,L,A,3,1 + fire_C,N,N,L,A,2,A  = 0
 c7451: - xEAr_C,N,N,L,L,3,1 + fire_C,N,N,L,L,2,L  = 0
 c7452: - xEAr_C,N,N,L,N,3,1 + fire_C,N,N,L,N,2,N  = 0
 c7453: - xEAr_C,N,N,L,P,4,1 + fire_C,N,N,L,P,3,P + fire_C,N,N,L,D,3,P
        + fire_C,N,N,L,C,3,P + fire_C,N,N,L,A,3,P  = 0
 c7454: - xEAr_C,N,N,L,D,4,1 + fire_C,N,N,L,P,3,D + fire_C,N,N,L,D,3,D
        + fire_C,N,N,L,W,3,D + fire_C,N,N,L,C,3,D  = 0
 c7455: - xEAr_C,N,N,L,W,4,1 + fire_C,N,N,L,W,3,W  = 0
 c7456: - xEAr_C,N,N,L,C,4,1 + fire_C,N,N,L,W,3,C + fire_C,N,N,L,C,3,C  = 0
 c7457: - xEAr_C,N,N,L,I,4,1 + fire_C,N,N,L,I,3,I  = 0
 c7458: - xEAr_C,N,N,L,A,4,1 + fire_C,N,N,L,A,3,A  = 0
 c7459: - xEAr_C,N,N,L,L,4,1 + fire_C,N,N,L,L,3,L  = 0
 c7460: - xEAr_C,N,N,L,N,4,1 + fire_C,N,N,L,N,3,N  = 0
 c7461: - xEAr_C,N,N,L,P,5,1 + fire_C,N,N,L,P,4,P + fire_C,N,N,L,D,4,P
        + fire_C,N,N,L,C,4,P + fire_C,N,N,L,A,4,P  = 0
 c7462: - xEAr_C,N,N,L,D,5,1 + fire_C,N,N,L,P,4,D + fire_C,N,N,L,D,4,D
        + fire_C,N,N,L,W,4,D + fire_C,N,N,L,C,4,D  = 0
 c7463: - xEAr_C,N,N,L,W,5,1 + fire_C,N,N,L,W,4,W  = 0
 c7464: - xEAr_C,N,N,L,C,5,1 + fire_C,N,N,L,W,4,C + fire_C,N,N,L,C,4,C  = 0
 c7465: - xEAr_C,N,N,L,I,5,1 + fire_C,N,N,L,I,4,I  = 0
 c7466: - xEAr_C,N,N,L,A,5,1 + fire_C,N,N,L,A,4,A  = 0
 c7467: - xEAr_C,N,N,L,L,5,1 + fire_C,N,N,L,L,4,L  = 0
 c7468: - xEAr_C,N,N,L,N,5,1 + fire_C,N,N,L,N,4,N  = 0
 c7469: - xEAr_C,N,N,H,P,2,1 + fire_C,N,N,H,P,1,P + fire_C,N,N,H,D,1,P
        + fire_C,N,N,H,C,1,P + fire_C,N,N,H,A,1,P  = 0
 c7470: - xEAr_C,N,N,H,D,2,1 + fire_C,N,N,H,P,1,D + fire_C,N,N,H,D,1,D
        + fire_C,N,N,H,W,1,D + fire_C,N,N,H,C,1,D  = 0
 c7471: - xEAr_C,N,N,H,W,2,1 + fire_C,N,N,H,W,1,W  = 0
 c7472: - xEAr_C,N,N,H,C,2,1 + fire_C,N,N,H,W,1,C + fire_C,N,N,H,C,1,C  = 0
 c7473: - xEAr_C,N,N,H,I,2,1 + fire_C,N,N,H,I,1,I  = 0
 c7474: - xEAr_C,N,N,H,A,2,1 + fire_C,N,N,H,A,1,A  = 0
 c7475: - xEAr_C,N,N,H,L,2,1 + fire_C,N,N,H,L,1,L  = 0
 c7476: - xEAr_C,N,N,H,N,2,1 + fire_C,N,N,H,N,1,N  = 0
 c7477: - xEAr_C,N,N,H,P,3,1 + fire_C,N,N,H,P,2,P + fire_C,N,N,H,D,2,P
        + fire_C,N,N,H,C,2,P + fire_C,N,N,H,A,2,P  = 0
 c7478: - xEAr_C,N,N,H,D,3,1 + fire_C,N,N,H,P,2,D + fire_C,N,N,H,D,2,D
        + fire_C,N,N,H,W,2,D + fire_C,N,N,H,C,2,D  = 0
 c7479: - xEAr_C,N,N,H,W,3,1 + fire_C,N,N,H,W,2,W  = 0
 c7480: - xEAr_C,N,N,H,C,3,1 + fire_C,N,N,H,W,2,C + fire_C,N,N,H,C,2,C  = 0
 c7481: - xEAr_C,N,N,H,I,3,1 + fire_C,N,N,H,I,2,I  = 0
 c7482: - xEAr_C,N,N,H,A,3,1 + fire_C,N,N,H,A,2,A  = 0
 c7483: - xEAr_C,N,N,H,L,3,1 + fire_C,N,N,H,L,2,L  = 0
 c7484: - xEAr_C,N,N,H,N,3,1 + fire_C,N,N,H,N,2,N  = 0
 c7485: - xEAr_C,N,N,H,P,4,1 + fire_C,N,N,H,P,3,P + fire_C,N,N,H,D,3,P
        + fire_C,N,N,H,C,3,P + fire_C,N,N,H,A,3,P  = 0
 c7486: - xEAr_C,N,N,H,D,4,1 + fire_C,N,N,H,P,3,D + fire_C,N,N,H,D,3,D
        + fire_C,N,N,H,W,3,D + fire_C,N,N,H,C,3,D  = 0
 c7487: - xEAr_C,N,N,H,W,4,1 + fire_C,N,N,H,W,3,W  = 0
 c7488: - xEAr_C,N,N,H,C,4,1 + fire_C,N,N,H,W,3,C + fire_C,N,N,H,C,3,C  = 0
 c7489: - xEAr_C,N,N,H,I,4,1 + fire_C,N,N,H,I,3,I  = 0
 c7490: - xEAr_C,N,N,H,A,4,1 + fire_C,N,N,H,A,3,A  = 0
 c7491: - xEAr_C,N,N,H,L,4,1 + fire_C,N,N,H,L,3,L  = 0
 c7492: - xEAr_C,N,N,H,N,4,1 + fire_C,N,N,H,N,3,N  = 0
 c7493: - xEAr_C,N,N,H,P,5,1 + fire_C,N,N,H,P,4,P + fire_C,N,N,H,D,4,P
        + fire_C,N,N,H,C,4,P + fire_C,N,N,H,A,4,P  = 0
 c7494: - xEAr_C,N,N,H,D,5,1 + fire_C,N,N,H,P,4,D + fire_C,N,N,H,D,4,D
        + fire_C,N,N,H,W,4,D + fire_C,N,N,H,C,4,D  = 0
 c7495: - xEAr_C,N,N,H,W,5,1 + fire_C,N,N,H,W,4,W  = 0
 c7496: - xEAr_C,N,N,H,C,5,1 + fire_C,N,N,H,W,4,C + fire_C,N,N,H,C,4,C  = 0
 c7497: - xEAr_C,N,N,H,I,5,1 + fire_C,N,N,H,I,4,I  = 0
 c7498: - xEAr_C,N,N,H,A,5,1 + fire_C,N,N,H,A,4,A  = 0
 c7499: - xEAr_C,N,N,H,L,5,1 + fire_C,N,N,H,L,4,L  = 0
 c7500: - xEAr_C,N,N,H,N,5,1 + fire_C,N,N,H,N,4,N  = 0
 c7501: - xEAr_C,N,N,C,P,2,1 + fire_C,N,N,C,P,1,P + fire_C,N,N,C,D,1,P
        + fire_C,N,N,C,C,1,P + fire_C,N,N,C,A,1,P  = 0
 c7502: - xEAr_C,N,N,C,D,2,1 + fire_C,N,N,C,P,1,D + fire_C,N,N,C,D,1,D
        + fire_C,N,N,C,W,1,D + fire_C,N,N,C,C,1,D  = 0
 c7503: - xEAr_C,N,N,C,W,2,1 + fire_C,N,N,C,W,1,W  = 0
 c7504: - xEAr_C,N,N,C,C,2,1 + fire_C,N,N,C,W,1,C + fire_C,N,N,C,C,1,C  = 0
 c7505: - xEAr_C,N,N,C,I,2,1 + fire_C,N,N,C,I,1,I  = 0
 c7506: - xEAr_C,N,N,C,A,2,1 + fire_C,N,N,C,A,1,A  = 0
 c7507: - xEAr_C,N,N,C,L,2,1 + fire_C,N,N,C,L,1,L  = 0
 c7508: - xEAr_C,N,N,C,N,2,1 + fire_C,N,N,C,N,1,N  = 0
 c7509: - xEAr_C,N,N,C,P,3,1 + fire_C,N,N,C,P,2,P + fire_C,N,N,C,D,2,P
        + fire_C,N,N,C,C,2,P + fire_C,N,N,C,A,2,P  = 0
 c7510: - xEAr_C,N,N,C,D,3,1 + fire_C,N,N,C,P,2,D + fire_C,N,N,C,D,2,D
        + fire_C,N,N,C,W,2,D + fire_C,N,N,C,C,2,D  = 0
 c7511: - xEAr_C,N,N,C,W,3,1 + fire_C,N,N,C,W,2,W  = 0
 c7512: - xEAr_C,N,N,C,C,3,1 + fire_C,N,N,C,W,2,C + fire_C,N,N,C,C,2,C  = 0
 c7513: - xEAr_C,N,N,C,I,3,1 + fire_C,N,N,C,I,2,I  = 0
 c7514: - xEAr_C,N,N,C,A,3,1 + fire_C,N,N,C,A,2,A  = 0
 c7515: - xEAr_C,N,N,C,L,3,1 + fire_C,N,N,C,L,2,L  = 0
 c7516: - xEAr_C,N,N,C,N,3,1 + fire_C,N,N,C,N,2,N  = 0
 c7517: - xEAr_C,N,N,C,P,4,1 + fire_C,N,N,C,P,3,P + fire_C,N,N,C,D,3,P
        + fire_C,N,N,C,C,3,P + fire_C,N,N,C,A,3,P  = 0
 c7518: - xEAr_C,N,N,C,D,4,1 + fire_C,N,N,C,P,3,D + fire_C,N,N,C,D,3,D
        + fire_C,N,N,C,W,3,D + fire_C,N,N,C,C,3,D  = 0
 c7519: - xEAr_C,N,N,C,W,4,1 + fire_C,N,N,C,W,3,W  = 0
 c7520: - xEAr_C,N,N,C,C,4,1 + fire_C,N,N,C,W,3,C + fire_C,N,N,C,C,3,C  = 0
 c7521: - xEAr_C,N,N,C,I,4,1 + fire_C,N,N,C,I,3,I  = 0
 c7522: - xEAr_C,N,N,C,A,4,1 + fire_C,N,N,C,A,3,A  = 0
 c7523: - xEAr_C,N,N,C,L,4,1 + fire_C,N,N,C,L,3,L  = 0
 c7524: - xEAr_C,N,N,C,N,4,1 + fire_C,N,N,C,N,3,N  = 0
 c7525: - xEAr_C,N,N,C,P,5,1 + fire_C,N,N,C,P,4,P + fire_C,N,N,C,D,4,P
        + fire_C,N,N,C,C,4,P + fire_C,N,N,C,A,4,P  = 0
 c7526: - xEAr_C,N,N,C,D,5,1 + fire_C,N,N,C,P,4,D + fire_C,N,N,C,D,4,D
        + fire_C,N,N,C,W,4,D + fire_C,N,N,C,C,4,D  = 0
 c7527: - xEAr_C,N,N,C,W,5,1 + fire_C,N,N,C,W,4,W  = 0
 c7528: - xEAr_C,N,N,C,C,5,1 + fire_C,N,N,C,W,4,C + fire_C,N,N,C,C,4,C  = 0
 c7529: - xEAr_C,N,N,C,I,5,1 + fire_C,N,N,C,I,4,I  = 0
 c7530: - xEAr_C,N,N,C,A,5,1 + fire_C,N,N,C,A,4,A  = 0
 c7531: - xEAr_C,N,N,C,L,5,1 + fire_C,N,N,C,L,4,L  = 0
 c7532: - xEAr_C,N,N,C,N,5,1 + fire_C,N,N,C,N,4,N  = 0
 c7533: - xEAr_C,N,N,R,P,2,1 + fire_C,N,N,R,P,1,P + fire_C,N,N,R,D,1,P
        + fire_C,N,N,R,C,1,P + fire_C,N,N,R,A,1,P  = 0
 c7534: - xEAr_C,N,N,R,D,2,1 + fire_C,N,N,R,P,1,D + fire_C,N,N,R,D,1,D
        + fire_C,N,N,R,W,1,D + fire_C,N,N,R,C,1,D  = 0
 c7535: - xEAr_C,N,N,R,W,2,1 + fire_C,N,N,R,W,1,W  = 0
 c7536: - xEAr_C,N,N,R,C,2,1 + fire_C,N,N,R,W,1,C + fire_C,N,N,R,C,1,C  = 0
 c7537: - xEAr_C,N,N,R,I,2,1 + fire_C,N,N,R,I,1,I  = 0
 c7538: - xEAr_C,N,N,R,A,2,1 + fire_C,N,N,R,A,1,A  = 0
 c7539: - xEAr_C,N,N,R,L,2,1 + fire_C,N,N,R,L,1,L  = 0
 c7540: - xEAr_C,N,N,R,N,2,1 + fire_C,N,N,R,N,1,N  = 0
 c7541: - xEAr_C,N,N,R,P,3,1 + fire_C,N,N,R,P,2,P + fire_C,N,N,R,D,2,P
        + fire_C,N,N,R,C,2,P + fire_C,N,N,R,A,2,P  = 0
 c7542: - xEAr_C,N,N,R,D,3,1 + fire_C,N,N,R,P,2,D + fire_C,N,N,R,D,2,D
        + fire_C,N,N,R,W,2,D + fire_C,N,N,R,C,2,D  = 0
 c7543: - xEAr_C,N,N,R,W,3,1 + fire_C,N,N,R,W,2,W  = 0
 c7544: - xEAr_C,N,N,R,C,3,1 + fire_C,N,N,R,W,2,C + fire_C,N,N,R,C,2,C  = 0
 c7545: - xEAr_C,N,N,R,I,3,1 + fire_C,N,N,R,I,2,I  = 0
 c7546: - xEAr_C,N,N,R,A,3,1 + fire_C,N,N,R,A,2,A  = 0
 c7547: - xEAr_C,N,N,R,L,3,1 + fire_C,N,N,R,L,2,L  = 0
 c7548: - xEAr_C,N,N,R,N,3,1 + fire_C,N,N,R,N,2,N  = 0
 c7549: - xEAr_C,N,N,R,P,4,1 + fire_C,N,N,R,P,3,P + fire_C,N,N,R,D,3,P
        + fire_C,N,N,R,C,3,P + fire_C,N,N,R,A,3,P  = 0
 c7550: - xEAr_C,N,N,R,D,4,1 + fire_C,N,N,R,P,3,D + fire_C,N,N,R,D,3,D
        + fire_C,N,N,R,W,3,D + fire_C,N,N,R,C,3,D  = 0
 c7551: - xEAr_C,N,N,R,W,4,1 + fire_C,N,N,R,W,3,W  = 0
 c7552: - xEAr_C,N,N,R,C,4,1 + fire_C,N,N,R,W,3,C + fire_C,N,N,R,C,3,C  = 0
 c7553: - xEAr_C,N,N,R,I,4,1 + fire_C,N,N,R,I,3,I  = 0
 c7554: - xEAr_C,N,N,R,A,4,1 + fire_C,N,N,R,A,3,A  = 0
 c7555: - xEAr_C,N,N,R,L,4,1 + fire_C,N,N,R,L,3,L  = 0
 c7556: - xEAr_C,N,N,R,N,4,1 + fire_C,N,N,R,N,3,N  = 0
 c7557: - xEAr_C,N,N,R,P,5,1 + fire_C,N,N,R,P,4,P + fire_C,N,N,R,D,4,P
        + fire_C,N,N,R,C,4,P + fire_C,N,N,R,A,4,P  = 0
 c7558: - xEAr_C,N,N,R,D,5,1 + fire_C,N,N,R,P,4,D + fire_C,N,N,R,D,4,D
        + fire_C,N,N,R,W,4,D + fire_C,N,N,R,C,4,D  = 0
 c7559: - xEAr_C,N,N,R,W,5,1 + fire_C,N,N,R,W,4,W  = 0
 c7560: - xEAr_C,N,N,R,C,5,1 + fire_C,N,N,R,W,4,C + fire_C,N,N,R,C,4,C  = 0
 c7561: - xEAr_C,N,N,R,I,5,1 + fire_C,N,N,R,I,4,I  = 0
 c7562: - xEAr_C,N,N,R,A,5,1 + fire_C,N,N,R,A,4,A  = 0
 c7563: - xEAr_C,N,N,R,L,5,1 + fire_C,N,N,R,L,4,L  = 0
 c7564: - xEAr_C,N,N,R,N,5,1 + fire_C,N,N,R,N,4,N  = 0
 c7565: - xEAr_C,N,O,L,P,2,1 + fire_C,N,O,L,P,1,P + fire_C,N,O,L,D,1,P
        + fire_C,N,O,L,C,1,P + fire_C,N,O,L,A,1,P  = 0
 c7566: - xEAr_C,N,O,L,D,2,1 + fire_C,N,O,L,P,1,D + fire_C,N,O,L,D,1,D
        + fire_C,N,O,L,W,1,D + fire_C,N,O,L,C,1,D  = 0
 c7567: - xEAr_C,N,O,L,W,2,1 + fire_C,N,O,L,W,1,W  = 0
 c7568: - xEAr_C,N,O,L,C,2,1 + fire_C,N,O,L,W,1,C + fire_C,N,O,L,C,1,C  = 0
 c7569: - xEAr_C,N,O,L,I,2,1 + fire_C,N,O,L,I,1,I  = 0
 c7570: - xEAr_C,N,O,L,A,2,1 + fire_C,N,O,L,A,1,A  = 0
 c7571: - xEAr_C,N,O,L,L,2,1 + fire_C,N,O,L,L,1,L  = 0
 c7572: - xEAr_C,N,O,L,N,2,1 + fire_C,N,O,L,N,1,N  = 0
 c7573: - xEAr_C,N,O,L,P,3,1 + fire_C,N,O,L,P,2,P + fire_C,N,O,L,D,2,P
        + fire_C,N,O,L,C,2,P + fire_C,N,O,L,A,2,P  = 0
 c7574: - xEAr_C,N,O,L,D,3,1 + fire_C,N,O,L,P,2,D + fire_C,N,O,L,D,2,D
        + fire_C,N,O,L,W,2,D + fire_C,N,O,L,C,2,D  = 0
 c7575: - xEAr_C,N,O,L,W,3,1 + fire_C,N,O,L,W,2,W  = 0
 c7576: - xEAr_C,N,O,L,C,3,1 + fire_C,N,O,L,W,2,C + fire_C,N,O,L,C,2,C  = 0
 c7577: - xEAr_C,N,O,L,I,3,1 + fire_C,N,O,L,I,2,I  = 0
 c7578: - xEAr_C,N,O,L,A,3,1 + fire_C,N,O,L,A,2,A  = 0
 c7579: - xEAr_C,N,O,L,L,3,1 + fire_C,N,O,L,L,2,L  = 0
 c7580: - xEAr_C,N,O,L,N,3,1 + fire_C,N,O,L,N,2,N  = 0
 c7581: - xEAr_C,N,O,L,P,4,1 + fire_C,N,O,L,P,3,P + fire_C,N,O,L,D,3,P
        + fire_C,N,O,L,C,3,P + fire_C,N,O,L,A,3,P  = 0
 c7582: - xEAr_C,N,O,L,D,4,1 + fire_C,N,O,L,P,3,D + fire_C,N,O,L,D,3,D
        + fire_C,N,O,L,W,3,D + fire_C,N,O,L,C,3,D  = 0
 c7583: - xEAr_C,N,O,L,W,4,1 + fire_C,N,O,L,W,3,W  = 0
 c7584: - xEAr_C,N,O,L,C,4,1 + fire_C,N,O,L,W,3,C + fire_C,N,O,L,C,3,C  = 0
 c7585: - xEAr_C,N,O,L,I,4,1 + fire_C,N,O,L,I,3,I  = 0
 c7586: - xEAr_C,N,O,L,A,4,1 + fire_C,N,O,L,A,3,A  = 0
 c7587: - xEAr_C,N,O,L,L,4,1 + fire_C,N,O,L,L,3,L  = 0
 c7588: - xEAr_C,N,O,L,N,4,1 + fire_C,N,O,L,N,3,N  = 0
 c7589: - xEAr_C,N,O,L,P,5,1 + fire_C,N,O,L,P,4,P + fire_C,N,O,L,D,4,P
        + fire_C,N,O,L,C,4,P + fire_C,N,O,L,A,4,P  = 0
 c7590: - xEAr_C,N,O,L,D,5,1 + fire_C,N,O,L,P,4,D + fire_C,N,O,L,D,4,D
        + fire_C,N,O,L,W,4,D + fire_C,N,O,L,C,4,D  = 0
 c7591: - xEAr_C,N,O,L,W,5,1 + fire_C,N,O,L,W,4,W  = 0
 c7592: - xEAr_C,N,O,L,C,5,1 + fire_C,N,O,L,W,4,C + fire_C,N,O,L,C,4,C  = 0
 c7593: - xEAr_C,N,O,L,I,5,1 + fire_C,N,O,L,I,4,I  = 0
 c7594: - xEAr_C,N,O,L,A,5,1 + fire_C,N,O,L,A,4,A  = 0
 c7595: - xEAr_C,N,O,L,L,5,1 + fire_C,N,O,L,L,4,L  = 0
 c7596: - xEAr_C,N,O,L,N,5,1 + fire_C,N,O,L,N,4,N  = 0
 c7597: - xEAr_C,N,O,H,P,2,1 + fire_C,N,O,H,P,1,P + fire_C,N,O,H,D,1,P
        + fire_C,N,O,H,C,1,P + fire_C,N,O,H,A,1,P  = 0
 c7598: - xEAr_C,N,O,H,D,2,1 + fire_C,N,O,H,P,1,D + fire_C,N,O,H,D,1,D
        + fire_C,N,O,H,W,1,D + fire_C,N,O,H,C,1,D  = 0
 c7599: - xEAr_C,N,O,H,W,2,1 + fire_C,N,O,H,W,1,W  = 0
 c7600: - xEAr_C,N,O,H,C,2,1 + fire_C,N,O,H,W,1,C + fire_C,N,O,H,C,1,C  = 0
 c7601: - xEAr_C,N,O,H,I,2,1 + fire_C,N,O,H,I,1,I  = 0
 c7602: - xEAr_C,N,O,H,A,2,1 + fire_C,N,O,H,A,1,A  = 0
 c7603: - xEAr_C,N,O,H,L,2,1 + fire_C,N,O,H,L,1,L  = 0
 c7604: - xEAr_C,N,O,H,N,2,1 + fire_C,N,O,H,N,1,N  = 0
 c7605: - xEAr_C,N,O,H,P,3,1 + fire_C,N,O,H,P,2,P + fire_C,N,O,H,D,2,P
        + fire_C,N,O,H,C,2,P + fire_C,N,O,H,A,2,P  = 0
 c7606: - xEAr_C,N,O,H,D,3,1 + fire_C,N,O,H,P,2,D + fire_C,N,O,H,D,2,D
        + fire_C,N,O,H,W,2,D + fire_C,N,O,H,C,2,D  = 0
 c7607: - xEAr_C,N,O,H,W,3,1 + fire_C,N,O,H,W,2,W  = 0
 c7608: - xEAr_C,N,O,H,C,3,1 + fire_C,N,O,H,W,2,C + fire_C,N,O,H,C,2,C  = 0
 c7609: - xEAr_C,N,O,H,I,3,1 + fire_C,N,O,H,I,2,I  = 0
 c7610: - xEAr_C,N,O,H,A,3,1 + fire_C,N,O,H,A,2,A  = 0
 c7611: - xEAr_C,N,O,H,L,3,1 + fire_C,N,O,H,L,2,L  = 0
 c7612: - xEAr_C,N,O,H,N,3,1 + fire_C,N,O,H,N,2,N  = 0
 c7613: - xEAr_C,N,O,H,P,4,1 + fire_C,N,O,H,P,3,P + fire_C,N,O,H,D,3,P
        + fire_C,N,O,H,C,3,P + fire_C,N,O,H,A,3,P  = 0
 c7614: - xEAr_C,N,O,H,D,4,1 + fire_C,N,O,H,P,3,D + fire_C,N,O,H,D,3,D
        + fire_C,N,O,H,W,3,D + fire_C,N,O,H,C,3,D  = 0
 c7615: - xEAr_C,N,O,H,W,4,1 + fire_C,N,O,H,W,3,W  = 0
 c7616: - xEAr_C,N,O,H,C,4,1 + fire_C,N,O,H,W,3,C + fire_C,N,O,H,C,3,C  = 0
 c7617: - xEAr_C,N,O,H,I,4,1 + fire_C,N,O,H,I,3,I  = 0
 c7618: - xEAr_C,N,O,H,A,4,1 + fire_C,N,O,H,A,3,A  = 0
 c7619: - xEAr_C,N,O,H,L,4,1 + fire_C,N,O,H,L,3,L  = 0
 c7620: - xEAr_C,N,O,H,N,4,1 + fire_C,N,O,H,N,3,N  = 0
 c7621: - xEAr_C,N,O,H,P,5,1 + fire_C,N,O,H,P,4,P + fire_C,N,O,H,D,4,P
        + fire_C,N,O,H,C,4,P + fire_C,N,O,H,A,4,P  = 0
 c7622: - xEAr_C,N,O,H,D,5,1 + fire_C,N,O,H,P,4,D + fire_C,N,O,H,D,4,D
        + fire_C,N,O,H,W,4,D + fire_C,N,O,H,C,4,D  = 0
 c7623: - xEAr_C,N,O,H,W,5,1 + fire_C,N,O,H,W,4,W  = 0
 c7624: - xEAr_C,N,O,H,C,5,1 + fire_C,N,O,H,W,4,C + fire_C,N,O,H,C,4,C  = 0
 c7625: - xEAr_C,N,O,H,I,5,1 + fire_C,N,O,H,I,4,I  = 0
 c7626: - xEAr_C,N,O,H,A,5,1 + fire_C,N,O,H,A,4,A  = 0
 c7627: - xEAr_C,N,O,H,L,5,1 + fire_C,N,O,H,L,4,L  = 0
 c7628: - xEAr_C,N,O,H,N,5,1 + fire_C,N,O,H,N,4,N  = 0
 c7629: - xEAr_C,N,O,C,P,2,1 + fire_C,N,O,C,P,1,P + fire_C,N,O,C,D,1,P
        + fire_C,N,O,C,C,1,P + fire_C,N,O,C,A,1,P  = 0
 c7630: - xEAr_C,N,O,C,D,2,1 + fire_C,N,O,C,P,1,D + fire_C,N,O,C,D,1,D
        + fire_C,N,O,C,W,1,D + fire_C,N,O,C,C,1,D  = 0
 c7631: - xEAr_C,N,O,C,W,2,1 + fire_C,N,O,C,W,1,W  = 0
 c7632: - xEAr_C,N,O,C,C,2,1 + fire_C,N,O,C,W,1,C + fire_C,N,O,C,C,1,C  = 0
 c7633: - xEAr_C,N,O,C,I,2,1 + fire_C,N,O,C,I,1,I  = 0
 c7634: - xEAr_C,N,O,C,A,2,1 + fire_C,N,O,C,A,1,A  = 0
 c7635: - xEAr_C,N,O,C,L,2,1 + fire_C,N,O,C,L,1,L  = 0
 c7636: - xEAr_C,N,O,C,N,2,1 + fire_C,N,O,C,N,1,N  = 0
 c7637: - xEAr_C,N,O,C,P,3,1 + fire_C,N,O,C,P,2,P + fire_C,N,O,C,D,2,P
        + fire_C,N,O,C,C,2,P + fire_C,N,O,C,A,2,P  = 0
 c7638: - xEAr_C,N,O,C,D,3,1 + fire_C,N,O,C,P,2,D + fire_C,N,O,C,D,2,D
        + fire_C,N,O,C,W,2,D + fire_C,N,O,C,C,2,D  = 0
 c7639: - xEAr_C,N,O,C,W,3,1 + fire_C,N,O,C,W,2,W  = 0
 c7640: - xEAr_C,N,O,C,C,3,1 + fire_C,N,O,C,W,2,C + fire_C,N,O,C,C,2,C  = 0
 c7641: - xEAr_C,N,O,C,I,3,1 + fire_C,N,O,C,I,2,I  = 0
 c7642: - xEAr_C,N,O,C,A,3,1 + fire_C,N,O,C,A,2,A  = 0
 c7643: - xEAr_C,N,O,C,L,3,1 + fire_C,N,O,C,L,2,L  = 0
 c7644: - xEAr_C,N,O,C,N,3,1 + fire_C,N,O,C,N,2,N  = 0
 c7645: - xEAr_C,N,O,C,P,4,1 + fire_C,N,O,C,P,3,P + fire_C,N,O,C,D,3,P
        + fire_C,N,O,C,C,3,P + fire_C,N,O,C,A,3,P  = 0
 c7646: - xEAr_C,N,O,C,D,4,1 + fire_C,N,O,C,P,3,D + fire_C,N,O,C,D,3,D
        + fire_C,N,O,C,W,3,D + fire_C,N,O,C,C,3,D  = 0
 c7647: - xEAr_C,N,O,C,W,4,1 + fire_C,N,O,C,W,3,W  = 0
 c7648: - xEAr_C,N,O,C,C,4,1 + fire_C,N,O,C,W,3,C + fire_C,N,O,C,C,3,C  = 0
 c7649: - xEAr_C,N,O,C,I,4,1 + fire_C,N,O,C,I,3,I  = 0
 c7650: - xEAr_C,N,O,C,A,4,1 + fire_C,N,O,C,A,3,A  = 0
 c7651: - xEAr_C,N,O,C,L,4,1 + fire_C,N,O,C,L,3,L  = 0
 c7652: - xEAr_C,N,O,C,N,4,1 + fire_C,N,O,C,N,3,N  = 0
 c7653: - xEAr_C,N,O,C,P,5,1 + fire_C,N,O,C,P,4,P + fire_C,N,O,C,D,4,P
        + fire_C,N,O,C,C,4,P + fire_C,N,O,C,A,4,P  = 0
 c7654: - xEAr_C,N,O,C,D,5,1 + fire_C,N,O,C,P,4,D + fire_C,N,O,C,D,4,D
        + fire_C,N,O,C,W,4,D + fire_C,N,O,C,C,4,D  = 0
 c7655: - xEAr_C,N,O,C,W,5,1 + fire_C,N,O,C,W,4,W  = 0
 c7656: - xEAr_C,N,O,C,C,5,1 + fire_C,N,O,C,W,4,C + fire_C,N,O,C,C,4,C  = 0
 c7657: - xEAr_C,N,O,C,I,5,1 + fire_C,N,O,C,I,4,I  = 0
 c7658: - xEAr_C,N,O,C,A,5,1 + fire_C,N,O,C,A,4,A  = 0
 c7659: - xEAr_C,N,O,C,L,5,1 + fire_C,N,O,C,L,4,L  = 0
 c7660: - xEAr_C,N,O,C,N,5,1 + fire_C,N,O,C,N,4,N  = 0
 c7661: - xEAr_C,N,O,R,P,2,1 + fire_C,N,O,R,P,1,P + fire_C,N,O,R,D,1,P
        + fire_C,N,O,R,C,1,P + fire_C,N,O,R,A,1,P  = 0
 c7662: - xEAr_C,N,O,R,D,2,1 + fire_C,N,O,R,P,1,D + fire_C,N,O,R,D,1,D
        + fire_C,N,O,R,W,1,D + fire_C,N,O,R,C,1,D  = 0
 c7663: - xEAr_C,N,O,R,W,2,1 + fire_C,N,O,R,W,1,W  = 0
 c7664: - xEAr_C,N,O,R,C,2,1 + fire_C,N,O,R,W,1,C + fire_C,N,O,R,C,1,C  = 0
 c7665: - xEAr_C,N,O,R,I,2,1 + fire_C,N,O,R,I,1,I  = 0
 c7666: - xEAr_C,N,O,R,A,2,1 + fire_C,N,O,R,A,1,A  = 0
 c7667: - xEAr_C,N,O,R,L,2,1 + fire_C,N,O,R,L,1,L  = 0
 c7668: - xEAr_C,N,O,R,N,2,1 + fire_C,N,O,R,N,1,N  = 0
 c7669: - xEAr_C,N,O,R,P,3,1 + fire_C,N,O,R,P,2,P + fire_C,N,O,R,D,2,P
        + fire_C,N,O,R,C,2,P + fire_C,N,O,R,A,2,P  = 0
 c7670: - xEAr_C,N,O,R,D,3,1 + fire_C,N,O,R,P,2,D + fire_C,N,O,R,D,2,D
        + fire_C,N,O,R,W,2,D + fire_C,N,O,R,C,2,D  = 0
 c7671: - xEAr_C,N,O,R,W,3,1 + fire_C,N,O,R,W,2,W  = 0
 c7672: - xEAr_C,N,O,R,C,3,1 + fire_C,N,O,R,W,2,C + fire_C,N,O,R,C,2,C  = 0
 c7673: - xEAr_C,N,O,R,I,3,1 + fire_C,N,O,R,I,2,I  = 0
 c7674: - xEAr_C,N,O,R,A,3,1 + fire_C,N,O,R,A,2,A  = 0
 c7675: - xEAr_C,N,O,R,L,3,1 + fire_C,N,O,R,L,2,L  = 0
 c7676: - xEAr_C,N,O,R,N,3,1 + fire_C,N,O,R,N,2,N  = 0
 c7677: - xEAr_C,N,O,R,P,4,1 + fire_C,N,O,R,P,3,P + fire_C,N,O,R,D,3,P
        + fire_C,N,O,R,C,3,P + fire_C,N,O,R,A,3,P  = 0
 c7678: - xEAr_C,N,O,R,D,4,1 + fire_C,N,O,R,P,3,D + fire_C,N,O,R,D,3,D
        + fire_C,N,O,R,W,3,D + fire_C,N,O,R,C,3,D  = 0
 c7679: - xEAr_C,N,O,R,W,4,1 + fire_C,N,O,R,W,3,W  = 0
 c7680: - xEAr_C,N,O,R,C,4,1 + fire_C,N,O,R,W,3,C + fire_C,N,O,R,C,3,C  = 0
 c7681: - xEAr_C,N,O,R,I,4,1 + fire_C,N,O,R,I,3,I  = 0
 c7682: - xEAr_C,N,O,R,A,4,1 + fire_C,N,O,R,A,3,A  = 0
 c7683: - xEAr_C,N,O,R,L,4,1 + fire_C,N,O,R,L,3,L  = 0
 c7684: - xEAr_C,N,O,R,N,4,1 + fire_C,N,O,R,N,3,N  = 0
 c7685: - xEAr_C,N,O,R,P,5,1 + fire_C,N,O,R,P,4,P + fire_C,N,O,R,D,4,P
        + fire_C,N,O,R,C,4,P + fire_C,N,O,R,A,4,P  = 0
 c7686: - xEAr_C,N,O,R,D,5,1 + fire_C,N,O,R,P,4,D + fire_C,N,O,R,D,4,D
        + fire_C,N,O,R,W,4,D + fire_C,N,O,R,C,4,D  = 0
 c7687: - xEAr_C,N,O,R,W,5,1 + fire_C,N,O,R,W,4,W  = 0
 c7688: - xEAr_C,N,O,R,C,5,1 + fire_C,N,O,R,W,4,C + fire_C,N,O,R,C,4,C  = 0
 c7689: - xEAr_C,N,O,R,I,5,1 + fire_C,N,O,R,I,4,I  = 0
 c7690: - xEAr_C,N,O,R,A,5,1 + fire_C,N,O,R,A,4,A  = 0
 c7691: - xEAr_C,N,O,R,L,5,1 + fire_C,N,O,R,L,4,L  = 0
 c7692: - xEAr_C,N,O,R,N,5,1 + fire_C,N,O,R,N,4,N  = 0
 c7693: 0.998 xEAr_B,R,N,L,P,2,1 - xEAr_B,R,N,L,P,3,2  = 0
 c7694: 0.998 xEAr_B,R,N,L,P,3,1 - xEAr_B,R,N,L,P,4,2  = 0
 c7695: 0.998 xEAr_B,R,N,L,P,3,2 - xEAr_B,R,N,L,P,4,3  = 0
 c7696: 0.998 xEAr_B,R,N,L,P,4,1 - xEAr_B,R,N,L,P,5,2  = 0
 c7697: 0.998 xEAr_B,R,N,L,P,4,2 - xEAr_B,R,N,L,P,5,3  = 0
 c7698: 0.998 xEAr_B,R,N,L,P,4,3 - xEAr_B,R,N,L,P,5,4  = 0
 c7699: 0.998 xEAr_B,R,N,L,D,2,1 - xEAr_B,R,N,L,D,3,2  = 0
 c7700: 0.998 xEAr_B,R,N,L,D,3,1 - xEAr_B,R,N,L,D,4,2  = 0
 c7701: 0.998 xEAr_B,R,N,L,D,3,2 - xEAr_B,R,N,L,D,4,3  = 0
 c7702: 0.998 xEAr_B,R,N,L,D,4,1 - xEAr_B,R,N,L,D,5,2  = 0
 c7703: 0.998 xEAr_B,R,N,L,D,4,2 - xEAr_B,R,N,L,D,5,3  = 0
 c7704: 0.998 xEAr_B,R,N,L,D,4,3 - xEAr_B,R,N,L,D,5,4  = 0
 c7705: 0.77 xEAr_B,R,N,L,W,2,1 - xEAr_B,R,N,L,W,3,2  = 0
 c7706: 0.77 xEAr_B,R,N,L,W,3,1 - xEAr_B,R,N,L,W,4,2  = 0
 c7707: 0.998 xEAr_B,R,N,L,W,3,2 - xEAr_B,R,N,L,W,4,3  = 0
 c7708: 0.77 xEAr_B,R,N,L,W,4,1 - xEAr_B,R,N,L,W,5,2  = 0
 c7709: 0.998 xEAr_B,R,N,L,W,4,2 - xEAr_B,R,N,L,W,5,3  = 0
 c7710: 0.998 xEAr_B,R,N,L,W,4,3 - xEAr_B,R,N,L,W,5,4  = 0
 c7711: 0.998 xEAr_B,R,N,L,C,2,1 - xEAr_B,R,N,L,C,3,2  = 0
 c7712: 0.998 xEAr_B,R,N,L,C,3,1 - xEAr_B,R,N,L,C,4,2  = 0
 c7713: 0.998 xEAr_B,R,N,L,C,3,2 - xEAr_B,R,N,L,C,4,3  = 0
 c7714: 0.998 xEAr_B,R,N,L,C,4,1 - xEAr_B,R,N,L,C,5,2  = 0
 c7715: 0.998 xEAr_B,R,N,L,C,4,2 - xEAr_B,R,N,L,C,5,3  = 0
 c7716: 0.998 xEAr_B,R,N,L,C,4,3 - xEAr_B,R,N,L,C,5,4  = 0
 c7717: 0.998 xEAr_B,R,N,L,I,2,1 - xEAr_B,R,N,L,I,3,2  = 0
 c7718: 0.998 xEAr_B,R,N,L,I,3,1 - xEAr_B,R,N,L,I,4,2  = 0
 c7719: 0.998 xEAr_B,R,N,L,I,3,2 - xEAr_B,R,N,L,I,4,3  = 0
 c7720: 0.998 xEAr_B,R,N,L,I,4,1 - xEAr_B,R,N,L,I,5,2  = 0
 c7721: 0.998 xEAr_B,R,N,L,I,4,2 - xEAr_B,R,N,L,I,5,3  = 0
 c7722: 0.998 xEAr_B,R,N,L,I,4,3 - xEAr_B,R,N,L,I,5,4  = 0
 c7723: 0.998 xEAr_B,R,N,L,A,2,1 - xEAr_B,R,N,L,A,3,2  = 0
 c7724: 0.998 xEAr_B,R,N,L,A,3,1 - xEAr_B,R,N,L,A,4,2  = 0
 c7725: 0.998 xEAr_B,R,N,L,A,3,2 - xEAr_B,R,N,L,A,4,3  = 0
 c7726: 0.998 xEAr_B,R,N,L,A,4,1 - xEAr_B,R,N,L,A,5,2  = 0
 c7727: 0.998 xEAr_B,R,N,L,A,4,2 - xEAr_B,R,N,L,A,5,3  = 0
 c7728: 0.998 xEAr_B,R,N,L,A,4,3 - xEAr_B,R,N,L,A,5,4  = 0
 c7729: 0.998 xEAr_B,R,N,L,L,2,1 - xEAr_B,R,N,L,L,3,2  = 0
 c7730: 0.998 xEAr_B,R,N,L,L,3,1 - xEAr_B,R,N,L,L,4,2  = 0
 c7731: 0.998 xEAr_B,R,N,L,L,3,2 - xEAr_B,R,N,L,L,4,3  = 0
 c7732: 0.998 xEAr_B,R,N,L,L,4,1 - xEAr_B,R,N,L,L,5,2  = 0
 c7733: 0.998 xEAr_B,R,N,L,L,4,2 - xEAr_B,R,N,L,L,5,3  = 0
 c7734: 0.998 xEAr_B,R,N,L,L,4,3 - xEAr_B,R,N,L,L,5,4  = 0
 c7735: xEAr_B,R,N,L,N,2,1 - xEAr_B,R,N,L,N,3,2  = 0
 c7736: xEAr_B,R,N,L,N,3,1 - xEAr_B,R,N,L,N,4,2  = 0
 c7737: xEAr_B,R,N,L,N,3,2 - xEAr_B,R,N,L,N,4,3  = 0
 c7738: xEAr_B,R,N,L,N,4,1 - xEAr_B,R,N,L,N,5,2  = 0
 c7739: xEAr_B,R,N,L,N,4,2 - xEAr_B,R,N,L,N,5,3  = 0
 c7740: xEAr_B,R,N,L,N,4,3 - xEAr_B,R,N,L,N,5,4  = 0
 c7741: 0.998 xEAr_B,R,N,C,P,2,1 - xEAr_B,R,N,C,P,3,2  = 0
 c7742: 0.998 xEAr_B,R,N,C,P,3,1 - xEAr_B,R,N,C,P,4,2  = 0
 c7743: 0.998 xEAr_B,R,N,C,P,3,2 - xEAr_B,R,N,C,P,4,3  = 0
 c7744: 0.998 xEAr_B,R,N,C,P,4,1 - xEAr_B,R,N,C,P,5,2  = 0
 c7745: 0.998 xEAr_B,R,N,C,P,4,2 - xEAr_B,R,N,C,P,5,3  = 0
 c7746: 0.998 xEAr_B,R,N,C,P,4,3 - xEAr_B,R,N,C,P,5,4  = 0
 c7747: 0.998 xEAr_B,R,N,C,D,2,1 - xEAr_B,R,N,C,D,3,2  = 0
 c7748: 0.998 xEAr_B,R,N,C,D,3,1 - xEAr_B,R,N,C,D,4,2  = 0
 c7749: 0.998 xEAr_B,R,N,C,D,3,2 - xEAr_B,R,N,C,D,4,3  = 0
 c7750: 0.998 xEAr_B,R,N,C,D,4,1 - xEAr_B,R,N,C,D,5,2  = 0
 c7751: 0.998 xEAr_B,R,N,C,D,4,2 - xEAr_B,R,N,C,D,5,3  = 0
 c7752: 0.998 xEAr_B,R,N,C,D,4,3 - xEAr_B,R,N,C,D,5,4  = 0
 c7753: 0.77 xEAr_B,R,N,C,W,2,1 - xEAr_B,R,N,C,W,3,2  = 0
 c7754: 0.77 xEAr_B,R,N,C,W,3,1 - xEAr_B,R,N,C,W,4,2  = 0
 c7755: 0.998 xEAr_B,R,N,C,W,3,2 - xEAr_B,R,N,C,W,4,3  = 0
 c7756: 0.77 xEAr_B,R,N,C,W,4,1 - xEAr_B,R,N,C,W,5,2  = 0
 c7757: 0.998 xEAr_B,R,N,C,W,4,2 - xEAr_B,R,N,C,W,5,3  = 0
 c7758: 0.998 xEAr_B,R,N,C,W,4,3 - xEAr_B,R,N,C,W,5,4  = 0
 c7759: 0.998 xEAr_B,R,N,C,C,2,1 - xEAr_B,R,N,C,C,3,2  = 0
 c7760: 0.998 xEAr_B,R,N,C,C,3,1 - xEAr_B,R,N,C,C,4,2  = 0
 c7761: 0.998 xEAr_B,R,N,C,C,3,2 - xEAr_B,R,N,C,C,4,3  = 0
 c7762: 0.998 xEAr_B,R,N,C,C,4,1 - xEAr_B,R,N,C,C,5,2  = 0
 c7763: 0.998 xEAr_B,R,N,C,C,4,2 - xEAr_B,R,N,C,C,5,3  = 0
 c7764: 0.998 xEAr_B,R,N,C,C,4,3 - xEAr_B,R,N,C,C,5,4  = 0
 c7765: 0.998 xEAr_B,R,N,C,I,2,1 - xEAr_B,R,N,C,I,3,2  = 0
 c7766: 0.998 xEAr_B,R,N,C,I,3,1 - xEAr_B,R,N,C,I,4,2  = 0
 c7767: 0.998 xEAr_B,R,N,C,I,3,2 - xEAr_B,R,N,C,I,4,3  = 0
 c7768: 0.998 xEAr_B,R,N,C,I,4,1 - xEAr_B,R,N,C,I,5,2  = 0
 c7769: 0.998 xEAr_B,R,N,C,I,4,2 - xEAr_B,R,N,C,I,5,3  = 0
 c7770: 0.998 xEAr_B,R,N,C,I,4,3 - xEAr_B,R,N,C,I,5,4  = 0
 c7771: 0.998 xEAr_B,R,N,C,A,2,1 - xEAr_B,R,N,C,A,3,2  = 0
 c7772: 0.998 xEAr_B,R,N,C,A,3,1 - xEAr_B,R,N,C,A,4,2  = 0
 c7773: 0.998 xEAr_B,R,N,C,A,3,2 - xEAr_B,R,N,C,A,4,3  = 0
 c7774: 0.998 xEAr_B,R,N,C,A,4,1 - xEAr_B,R,N,C,A,5,2  = 0
 c7775: 0.998 xEAr_B,R,N,C,A,4,2 - xEAr_B,R,N,C,A,5,3  = 0
 c7776: 0.998 xEAr_B,R,N,C,A,4,3 - xEAr_B,R,N,C,A,5,4  = 0
 c7777: 0.998 xEAr_B,R,N,C,L,2,1 - xEAr_B,R,N,C,L,3,2  = 0
 c7778: 0.998 xEAr_B,R,N,C,L,3,1 - xEAr_B,R,N,C,L,4,2  = 0
 c7779: 0.998 xEAr_B,R,N,C,L,3,2 - xEAr_B,R,N,C,L,4,3  = 0
 c7780: 0.998 xEAr_B,R,N,C,L,4,1 - xEAr_B,R,N,C,L,5,2  = 0
 c7781: 0.998 xEAr_B,R,N,C,L,4,2 - xEAr_B,R,N,C,L,5,3  = 0
 c7782: 0.998 xEAr_B,R,N,C,L,4,3 - xEAr_B,R,N,C,L,5,4  = 0
 c7783: xEAr_B,R,N,C,N,2,1 - xEAr_B,R,N,C,N,3,2  = 0
 c7784: xEAr_B,R,N,C,N,3,1 - xEAr_B,R,N,C,N,4,2  = 0
 c7785: xEAr_B,R,N,C,N,3,2 - xEAr_B,R,N,C,N,4,3  = 0
 c7786: xEAr_B,R,N,C,N,4,1 - xEAr_B,R,N,C,N,5,2  = 0
 c7787: xEAr_B,R,N,C,N,4,2 - xEAr_B,R,N,C,N,5,3  = 0
 c7788: xEAr_B,R,N,C,N,4,3 - xEAr_B,R,N,C,N,5,4  = 0
 c7789: 0.998 xEAr_B,R,N,R,P,2,1 - xEAr_B,R,N,R,P,3,2  = 0
 c7790: 0.998 xEAr_B,R,N,R,P,3,1 - xEAr_B,R,N,R,P,4,2  = 0
 c7791: 0.998 xEAr_B,R,N,R,P,3,2 - xEAr_B,R,N,R,P,4,3  = 0
 c7792: 0.998 xEAr_B,R,N,R,P,4,1 - xEAr_B,R,N,R,P,5,2  = 0
 c7793: 0.998 xEAr_B,R,N,R,P,4,2 - xEAr_B,R,N,R,P,5,3  = 0
 c7794: 0.998 xEAr_B,R,N,R,P,4,3 - xEAr_B,R,N,R,P,5,4  = 0
 c7795: 0.998 xEAr_B,R,N,R,D,2,1 - xEAr_B,R,N,R,D,3,2  = 0
 c7796: 0.998 xEAr_B,R,N,R,D,3,1 - xEAr_B,R,N,R,D,4,2  = 0
 c7797: 0.998 xEAr_B,R,N,R,D,3,2 - xEAr_B,R,N,R,D,4,3  = 0
 c7798: 0.998 xEAr_B,R,N,R,D,4,1 - xEAr_B,R,N,R,D,5,2  = 0
 c7799: 0.998 xEAr_B,R,N,R,D,4,2 - xEAr_B,R,N,R,D,5,3  = 0
 c7800: 0.998 xEAr_B,R,N,R,D,4,3 - xEAr_B,R,N,R,D,5,4  = 0
 c7801: 0.77 xEAr_B,R,N,R,W,2,1 - xEAr_B,R,N,R,W,3,2  = 0
 c7802: 0.77 xEAr_B,R,N,R,W,3,1 - xEAr_B,R,N,R,W,4,2  = 0
 c7803: 0.998 xEAr_B,R,N,R,W,3,2 - xEAr_B,R,N,R,W,4,3  = 0
 c7804: 0.77 xEAr_B,R,N,R,W,4,1 - xEAr_B,R,N,R,W,5,2  = 0
 c7805: 0.998 xEAr_B,R,N,R,W,4,2 - xEAr_B,R,N,R,W,5,3  = 0
 c7806: 0.998 xEAr_B,R,N,R,W,4,3 - xEAr_B,R,N,R,W,5,4  = 0
 c7807: 0.998 xEAr_B,R,N,R,C,2,1 - xEAr_B,R,N,R,C,3,2  = 0
 c7808: 0.998 xEAr_B,R,N,R,C,3,1 - xEAr_B,R,N,R,C,4,2  = 0
 c7809: 0.998 xEAr_B,R,N,R,C,3,2 - xEAr_B,R,N,R,C,4,3  = 0
 c7810: 0.998 xEAr_B,R,N,R,C,4,1 - xEAr_B,R,N,R,C,5,2  = 0
 c7811: 0.998 xEAr_B,R,N,R,C,4,2 - xEAr_B,R,N,R,C,5,3  = 0
 c7812: 0.998 xEAr_B,R,N,R,C,4,3 - xEAr_B,R,N,R,C,5,4  = 0
 c7813: 0.998 xEAr_B,R,N,R,I,2,1 - xEAr_B,R,N,R,I,3,2  = 0
 c7814: 0.998 xEAr_B,R,N,R,I,3,1 - xEAr_B,R,N,R,I,4,2  = 0
 c7815: 0.998 xEAr_B,R,N,R,I,3,2 - xEAr_B,R,N,R,I,4,3  = 0
 c7816: 0.998 xEAr_B,R,N,R,I,4,1 - xEAr_B,R,N,R,I,5,2  = 0
 c7817: 0.998 xEAr_B,R,N,R,I,4,2 - xEAr_B,R,N,R,I,5,3  = 0
 c7818: 0.998 xEAr_B,R,N,R,I,4,3 - xEAr_B,R,N,R,I,5,4  = 0
 c7819: 0.998 xEAr_B,R,N,R,A,2,1 - xEAr_B,R,N,R,A,3,2  = 0
 c7820: 0.998 xEAr_B,R,N,R,A,3,1 - xEAr_B,R,N,R,A,4,2  = 0
 c7821: 0.998 xEAr_B,R,N,R,A,3,2 - xEAr_B,R,N,R,A,4,3  = 0
 c7822: 0.998 xEAr_B,R,N,R,A,4,1 - xEAr_B,R,N,R,A,5,2  = 0
 c7823: 0.998 xEAr_B,R,N,R,A,4,2 - xEAr_B,R,N,R,A,5,3  = 0
 c7824: 0.998 xEAr_B,R,N,R,A,4,3 - xEAr_B,R,N,R,A,5,4  = 0
 c7825: 0.998 xEAr_B,R,N,R,L,2,1 - xEAr_B,R,N,R,L,3,2  = 0
 c7826: 0.998 xEAr_B,R,N,R,L,3,1 - xEAr_B,R,N,R,L,4,2  = 0
 c7827: 0.998 xEAr_B,R,N,R,L,3,2 - xEAr_B,R,N,R,L,4,3  = 0
 c7828: 0.998 xEAr_B,R,N,R,L,4,1 - xEAr_B,R,N,R,L,5,2  = 0
 c7829: 0.998 xEAr_B,R,N,R,L,4,2 - xEAr_B,R,N,R,L,5,3  = 0
 c7830: 0.998 xEAr_B,R,N,R,L,4,3 - xEAr_B,R,N,R,L,5,4  = 0
 c7831: xEAr_B,R,N,R,N,2,1 - xEAr_B,R,N,R,N,3,2  = 0
 c7832: xEAr_B,R,N,R,N,3,1 - xEAr_B,R,N,R,N,4,2  = 0
 c7833: xEAr_B,R,N,R,N,3,2 - xEAr_B,R,N,R,N,4,3  = 0
 c7834: xEAr_B,R,N,R,N,4,1 - xEAr_B,R,N,R,N,5,2  = 0
 c7835: xEAr_B,R,N,R,N,4,2 - xEAr_B,R,N,R,N,5,3  = 0
 c7836: xEAr_B,R,N,R,N,4,3 - xEAr_B,R,N,R,N,5,4  = 0
 c7837: 0.998 xEAr_B,R,P,L,P,2,1 - xEAr_B,R,P,L,P,3,2  = 0
 c7838: 0.998 xEAr_B,R,P,L,P,3,1 - xEAr_B,R,P,L,P,4,2  = 0
 c7839: 0.998 xEAr_B,R,P,L,P,3,2 - xEAr_B,R,P,L,P,4,3  = 0
 c7840: 0.998 xEAr_B,R,P,L,P,4,1 - xEAr_B,R,P,L,P,5,2  = 0
 c7841: 0.998 xEAr_B,R,P,L,P,4,2 - xEAr_B,R,P,L,P,5,3  = 0
 c7842: 0.998 xEAr_B,R,P,L,P,4,3 - xEAr_B,R,P,L,P,5,4  = 0
 c7843: 0.998 xEAr_B,R,P,L,D,2,1 - xEAr_B,R,P,L,D,3,2  = 0
 c7844: 0.998 xEAr_B,R,P,L,D,3,1 - xEAr_B,R,P,L,D,4,2  = 0
 c7845: 0.998 xEAr_B,R,P,L,D,3,2 - xEAr_B,R,P,L,D,4,3  = 0
 c7846: 0.998 xEAr_B,R,P,L,D,4,1 - xEAr_B,R,P,L,D,5,2  = 0
 c7847: 0.998 xEAr_B,R,P,L,D,4,2 - xEAr_B,R,P,L,D,5,3  = 0
 c7848: 0.998 xEAr_B,R,P,L,D,4,3 - xEAr_B,R,P,L,D,5,4  = 0
 c7849: 0.77 xEAr_B,R,P,L,W,2,1 - xEAr_B,R,P,L,W,3,2  = 0
 c7850: 0.77 xEAr_B,R,P,L,W,3,1 - xEAr_B,R,P,L,W,4,2  = 0
 c7851: 0.998 xEAr_B,R,P,L,W,3,2 - xEAr_B,R,P,L,W,4,3  = 0
 c7852: 0.77 xEAr_B,R,P,L,W,4,1 - xEAr_B,R,P,L,W,5,2  = 0
 c7853: 0.998 xEAr_B,R,P,L,W,4,2 - xEAr_B,R,P,L,W,5,3  = 0
 c7854: 0.998 xEAr_B,R,P,L,W,4,3 - xEAr_B,R,P,L,W,5,4  = 0
 c7855: 0.998 xEAr_B,R,P,L,C,2,1 - xEAr_B,R,P,L,C,3,2  = 0
 c7856: 0.998 xEAr_B,R,P,L,C,3,1 - xEAr_B,R,P,L,C,4,2  = 0
 c7857: 0.998 xEAr_B,R,P,L,C,3,2 - xEAr_B,R,P,L,C,4,3  = 0
 c7858: 0.998 xEAr_B,R,P,L,C,4,1 - xEAr_B,R,P,L,C,5,2  = 0
 c7859: 0.998 xEAr_B,R,P,L,C,4,2 - xEAr_B,R,P,L,C,5,3  = 0
 c7860: 0.998 xEAr_B,R,P,L,C,4,3 - xEAr_B,R,P,L,C,5,4  = 0
 c7861: 0.998 xEAr_B,R,P,L,I,2,1 - xEAr_B,R,P,L,I,3,2  = 0
 c7862: 0.998 xEAr_B,R,P,L,I,3,1 - xEAr_B,R,P,L,I,4,2  = 0
 c7863: 0.998 xEAr_B,R,P,L,I,3,2 - xEAr_B,R,P,L,I,4,3  = 0
 c7864: 0.998 xEAr_B,R,P,L,I,4,1 - xEAr_B,R,P,L,I,5,2  = 0
 c7865: 0.998 xEAr_B,R,P,L,I,4,2 - xEAr_B,R,P,L,I,5,3  = 0
 c7866: 0.998 xEAr_B,R,P,L,I,4,3 - xEAr_B,R,P,L,I,5,4  = 0
 c7867: 0.998 xEAr_B,R,P,L,A,2,1 - xEAr_B,R,P,L,A,3,2  = 0
 c7868: 0.998 xEAr_B,R,P,L,A,3,1 - xEAr_B,R,P,L,A,4,2  = 0
 c7869: 0.998 xEAr_B,R,P,L,A,3,2 - xEAr_B,R,P,L,A,4,3  = 0
 c7870: 0.998 xEAr_B,R,P,L,A,4,1 - xEAr_B,R,P,L,A,5,2  = 0
 c7871: 0.998 xEAr_B,R,P,L,A,4,2 - xEAr_B,R,P,L,A,5,3  = 0
 c7872: 0.998 xEAr_B,R,P,L,A,4,3 - xEAr_B,R,P,L,A,5,4  = 0
 c7873: 0.998 xEAr_B,R,P,L,L,2,1 - xEAr_B,R,P,L,L,3,2  = 0
 c7874: 0.998 xEAr_B,R,P,L,L,3,1 - xEAr_B,R,P,L,L,4,2  = 0
 c7875: 0.998 xEAr_B,R,P,L,L,3,2 - xEAr_B,R,P,L,L,4,3  = 0
 c7876: 0.998 xEAr_B,R,P,L,L,4,1 - xEAr_B,R,P,L,L,5,2  = 0
 c7877: 0.998 xEAr_B,R,P,L,L,4,2 - xEAr_B,R,P,L,L,5,3  = 0
 c7878: 0.998 xEAr_B,R,P,L,L,4,3 - xEAr_B,R,P,L,L,5,4  = 0
 c7879: xEAr_B,R,P,L,N,2,1 - xEAr_B,R,P,L,N,3,2  = 0
 c7880: xEAr_B,R,P,L,N,3,1 - xEAr_B,R,P,L,N,4,2  = 0
 c7881: xEAr_B,R,P,L,N,3,2 - xEAr_B,R,P,L,N,4,3  = 0
 c7882: xEAr_B,R,P,L,N,4,1 - xEAr_B,R,P,L,N,5,2  = 0
 c7883: xEAr_B,R,P,L,N,4,2 - xEAr_B,R,P,L,N,5,3  = 0
 c7884: xEAr_B,R,P,L,N,4,3 - xEAr_B,R,P,L,N,5,4  = 0
 c7885: 0.998 xEAr_B,R,P,C,P,2,1 - xEAr_B,R,P,C,P,3,2  = 0
 c7886: 0.998 xEAr_B,R,P,C,P,3,1 - xEAr_B,R,P,C,P,4,2  = 0
 c7887: 0.998 xEAr_B,R,P,C,P,3,2 - xEAr_B,R,P,C,P,4,3  = 0
 c7888: 0.998 xEAr_B,R,P,C,P,4,1 - xEAr_B,R,P,C,P,5,2  = 0
 c7889: 0.998 xEAr_B,R,P,C,P,4,2 - xEAr_B,R,P,C,P,5,3  = 0
 c7890: 0.998 xEAr_B,R,P,C,P,4,3 - xEAr_B,R,P,C,P,5,4  = 0
 c7891: 0.998 xEAr_B,R,P,C,D,2,1 - xEAr_B,R,P,C,D,3,2  = 0
 c7892: 0.998 xEAr_B,R,P,C,D,3,1 - xEAr_B,R,P,C,D,4,2  = 0
 c7893: 0.998 xEAr_B,R,P,C,D,3,2 - xEAr_B,R,P,C,D,4,3  = 0
 c7894: 0.998 xEAr_B,R,P,C,D,4,1 - xEAr_B,R,P,C,D,5,2  = 0
 c7895: 0.998 xEAr_B,R,P,C,D,4,2 - xEAr_B,R,P,C,D,5,3  = 0
 c7896: 0.998 xEAr_B,R,P,C,D,4,3 - xEAr_B,R,P,C,D,5,4  = 0
 c7897: 0.77 xEAr_B,R,P,C,W,2,1 - xEAr_B,R,P,C,W,3,2  = 0
 c7898: 0.77 xEAr_B,R,P,C,W,3,1 - xEAr_B,R,P,C,W,4,2  = 0
 c7899: 0.998 xEAr_B,R,P,C,W,3,2 - xEAr_B,R,P,C,W,4,3  = 0
 c7900: 0.77 xEAr_B,R,P,C,W,4,1 - xEAr_B,R,P,C,W,5,2  = 0
 c7901: 0.998 xEAr_B,R,P,C,W,4,2 - xEAr_B,R,P,C,W,5,3  = 0
 c7902: 0.998 xEAr_B,R,P,C,W,4,3 - xEAr_B,R,P,C,W,5,4  = 0
 c7903: 0.998 xEAr_B,R,P,C,C,2,1 - xEAr_B,R,P,C,C,3,2  = 0
 c7904: 0.998 xEAr_B,R,P,C,C,3,1 - xEAr_B,R,P,C,C,4,2  = 0
 c7905: 0.998 xEAr_B,R,P,C,C,3,2 - xEAr_B,R,P,C,C,4,3  = 0
 c7906: 0.998 xEAr_B,R,P,C,C,4,1 - xEAr_B,R,P,C,C,5,2  = 0
 c7907: 0.998 xEAr_B,R,P,C,C,4,2 - xEAr_B,R,P,C,C,5,3  = 0
 c7908: 0.998 xEAr_B,R,P,C,C,4,3 - xEAr_B,R,P,C,C,5,4  = 0
 c7909: 0.998 xEAr_B,R,P,C,I,2,1 - xEAr_B,R,P,C,I,3,2  = 0
 c7910: 0.998 xEAr_B,R,P,C,I,3,1 - xEAr_B,R,P,C,I,4,2  = 0
 c7911: 0.998 xEAr_B,R,P,C,I,3,2 - xEAr_B,R,P,C,I,4,3  = 0
 c7912: 0.998 xEAr_B,R,P,C,I,4,1 - xEAr_B,R,P,C,I,5,2  = 0
 c7913: 0.998 xEAr_B,R,P,C,I,4,2 - xEAr_B,R,P,C,I,5,3  = 0
 c7914: 0.998 xEAr_B,R,P,C,I,4,3 - xEAr_B,R,P,C,I,5,4  = 0
 c7915: 0.998 xEAr_B,R,P,C,A,2,1 - xEAr_B,R,P,C,A,3,2  = 0
 c7916: 0.998 xEAr_B,R,P,C,A,3,1 - xEAr_B,R,P,C,A,4,2  = 0
 c7917: 0.998 xEAr_B,R,P,C,A,3,2 - xEAr_B,R,P,C,A,4,3  = 0
 c7918: 0.998 xEAr_B,R,P,C,A,4,1 - xEAr_B,R,P,C,A,5,2  = 0
 c7919: 0.998 xEAr_B,R,P,C,A,4,2 - xEAr_B,R,P,C,A,5,3  = 0
 c7920: 0.998 xEAr_B,R,P,C,A,4,3 - xEAr_B,R,P,C,A,5,4  = 0
 c7921: 0.998 xEAr_B,R,P,C,L,2,1 - xEAr_B,R,P,C,L,3,2  = 0
 c7922: 0.998 xEAr_B,R,P,C,L,3,1 - xEAr_B,R,P,C,L,4,2  = 0
 c7923: 0.998 xEAr_B,R,P,C,L,3,2 - xEAr_B,R,P,C,L,4,3  = 0
 c7924: 0.998 xEAr_B,R,P,C,L,4,1 - xEAr_B,R,P,C,L,5,2  = 0
 c7925: 0.998 xEAr_B,R,P,C,L,4,2 - xEAr_B,R,P,C,L,5,3  = 0
 c7926: 0.998 xEAr_B,R,P,C,L,4,3 - xEAr_B,R,P,C,L,5,4  = 0
 c7927: xEAr_B,R,P,C,N,2,1 - xEAr_B,R,P,C,N,3,2  = 0
 c7928: xEAr_B,R,P,C,N,3,1 - xEAr_B,R,P,C,N,4,2  = 0
 c7929: xEAr_B,R,P,C,N,3,2 - xEAr_B,R,P,C,N,4,3  = 0
 c7930: xEAr_B,R,P,C,N,4,1 - xEAr_B,R,P,C,N,5,2  = 0
 c7931: xEAr_B,R,P,C,N,4,2 - xEAr_B,R,P,C,N,5,3  = 0
 c7932: xEAr_B,R,P,C,N,4,3 - xEAr_B,R,P,C,N,5,4  = 0
 c7933: 0.998 xEAr_B,R,P,R,P,2,1 - xEAr_B,R,P,R,P,3,2  = 0
 c7934: 0.998 xEAr_B,R,P,R,P,3,1 - xEAr_B,R,P,R,P,4,2  = 0
 c7935: 0.998 xEAr_B,R,P,R,P,3,2 - xEAr_B,R,P,R,P,4,3  = 0
 c7936: 0.998 xEAr_B,R,P,R,P,4,1 - xEAr_B,R,P,R,P,5,2  = 0
 c7937: 0.998 xEAr_B,R,P,R,P,4,2 - xEAr_B,R,P,R,P,5,3  = 0
 c7938: 0.998 xEAr_B,R,P,R,P,4,3 - xEAr_B,R,P,R,P,5,4  = 0
 c7939: 0.998 xEAr_B,R,P,R,D,2,1 - xEAr_B,R,P,R,D,3,2  = 0
 c7940: 0.998 xEAr_B,R,P,R,D,3,1 - xEAr_B,R,P,R,D,4,2  = 0
 c7941: 0.998 xEAr_B,R,P,R,D,3,2 - xEAr_B,R,P,R,D,4,3  = 0
 c7942: 0.998 xEAr_B,R,P,R,D,4,1 - xEAr_B,R,P,R,D,5,2  = 0
 c7943: 0.998 xEAr_B,R,P,R,D,4,2 - xEAr_B,R,P,R,D,5,3  = 0
 c7944: 0.998 xEAr_B,R,P,R,D,4,3 - xEAr_B,R,P,R,D,5,4  = 0
 c7945: 0.77 xEAr_B,R,P,R,W,2,1 - xEAr_B,R,P,R,W,3,2  = 0
 c7946: 0.77 xEAr_B,R,P,R,W,3,1 - xEAr_B,R,P,R,W,4,2  = 0
 c7947: 0.998 xEAr_B,R,P,R,W,3,2 - xEAr_B,R,P,R,W,4,3  = 0
 c7948: 0.77 xEAr_B,R,P,R,W,4,1 - xEAr_B,R,P,R,W,5,2  = 0
 c7949: 0.998 xEAr_B,R,P,R,W,4,2 - xEAr_B,R,P,R,W,5,3  = 0
 c7950: 0.998 xEAr_B,R,P,R,W,4,3 - xEAr_B,R,P,R,W,5,4  = 0
 c7951: 0.998 xEAr_B,R,P,R,C,2,1 - xEAr_B,R,P,R,C,3,2  = 0
 c7952: 0.998 xEAr_B,R,P,R,C,3,1 - xEAr_B,R,P,R,C,4,2  = 0
 c7953: 0.998 xEAr_B,R,P,R,C,3,2 - xEAr_B,R,P,R,C,4,3  = 0
 c7954: 0.998 xEAr_B,R,P,R,C,4,1 - xEAr_B,R,P,R,C,5,2  = 0
 c7955: 0.998 xEAr_B,R,P,R,C,4,2 - xEAr_B,R,P,R,C,5,3  = 0
 c7956: 0.998 xEAr_B,R,P,R,C,4,3 - xEAr_B,R,P,R,C,5,4  = 0
 c7957: 0.998 xEAr_B,R,P,R,I,2,1 - xEAr_B,R,P,R,I,3,2  = 0
 c7958: 0.998 xEAr_B,R,P,R,I,3,1 - xEAr_B,R,P,R,I,4,2  = 0
 c7959: 0.998 xEAr_B,R,P,R,I,3,2 - xEAr_B,R,P,R,I,4,3  = 0
 c7960: 0.998 xEAr_B,R,P,R,I,4,1 - xEAr_B,R,P,R,I,5,2  = 0
 c7961: 0.998 xEAr_B,R,P,R,I,4,2 - xEAr_B,R,P,R,I,5,3  = 0
 c7962: 0.998 xEAr_B,R,P,R,I,4,3 - xEAr_B,R,P,R,I,5,4  = 0
 c7963: 0.998 xEAr_B,R,P,R,A,2,1 - xEAr_B,R,P,R,A,3,2  = 0
 c7964: 0.998 xEAr_B,R,P,R,A,3,1 - xEAr_B,R,P,R,A,4,2  = 0
 c7965: 0.998 xEAr_B,R,P,R,A,3,2 - xEAr_B,R,P,R,A,4,3  = 0
 c7966: 0.998 xEAr_B,R,P,R,A,4,1 - xEAr_B,R,P,R,A,5,2  = 0
 c7967: 0.998 xEAr_B,R,P,R,A,4,2 - xEAr_B,R,P,R,A,5,3  = 0
 c7968: 0.998 xEAr_B,R,P,R,A,4,3 - xEAr_B,R,P,R,A,5,4  = 0
 c7969: 0.998 xEAr_B,R,P,R,L,2,1 - xEAr_B,R,P,R,L,3,2  = 0
 c7970: 0.998 xEAr_B,R,P,R,L,3,1 - xEAr_B,R,P,R,L,4,2  = 0
 c7971: 0.998 xEAr_B,R,P,R,L,3,2 - xEAr_B,R,P,R,L,4,3  = 0
 c7972: 0.998 xEAr_B,R,P,R,L,4,1 - xEAr_B,R,P,R,L,5,2  = 0
 c7973: 0.998 xEAr_B,R,P,R,L,4,2 - xEAr_B,R,P,R,L,5,3  = 0
 c7974: 0.998 xEAr_B,R,P,R,L,4,3 - xEAr_B,R,P,R,L,5,4  = 0
 c7975: xEAr_B,R,P,R,N,2,1 - xEAr_B,R,P,R,N,3,2  = 0
 c7976: xEAr_B,R,P,R,N,3,1 - xEAr_B,R,P,R,N,4,2  = 0
 c7977: xEAr_B,R,P,R,N,3,2 - xEAr_B,R,P,R,N,4,3  = 0
 c7978: xEAr_B,R,P,R,N,4,1 - xEAr_B,R,P,R,N,5,2  = 0
 c7979: xEAr_B,R,P,R,N,4,2 - xEAr_B,R,P,R,N,5,3  = 0
 c7980: xEAr_B,R,P,R,N,4,3 - xEAr_B,R,P,R,N,5,4  = 0
 c7981: 0.998 xEAr_B,N,N,L,P,2,1 - xEAr_B,N,N,L,P,3,2  = 0
 c7982: 0.998 xEAr_B,N,N,L,P,3,1 - xEAr_B,N,N,L,P,4,2  = 0
 c7983: 0.998 xEAr_B,N,N,L,P,3,2 - xEAr_B,N,N,L,P,4,3  = 0
 c7984: 0.998 xEAr_B,N,N,L,P,4,1 - xEAr_B,N,N,L,P,5,2  = 0
 c7985: 0.998 xEAr_B,N,N,L,P,4,2 - xEAr_B,N,N,L,P,5,3  = 0
 c7986: 0.998 xEAr_B,N,N,L,P,4,3 - xEAr_B,N,N,L,P,5,4  = 0
 c7987: 0.998 xEAr_B,N,N,L,D,2,1 - xEAr_B,N,N,L,D,3,2  = 0
 c7988: 0.998 xEAr_B,N,N,L,D,3,1 - xEAr_B,N,N,L,D,4,2  = 0
 c7989: 0.998 xEAr_B,N,N,L,D,3,2 - xEAr_B,N,N,L,D,4,3  = 0
 c7990: 0.998 xEAr_B,N,N,L,D,4,1 - xEAr_B,N,N,L,D,5,2  = 0
 c7991: 0.998 xEAr_B,N,N,L,D,4,2 - xEAr_B,N,N,L,D,5,3  = 0
 c7992: 0.998 xEAr_B,N,N,L,D,4,3 - xEAr_B,N,N,L,D,5,4  = 0
 c7993: 0.77 xEAr_B,N,N,L,W,2,1 - xEAr_B,N,N,L,W,3,2  = 0
 c7994: 0.77 xEAr_B,N,N,L,W,3,1 - xEAr_B,N,N,L,W,4,2  = 0
 c7995: 0.998 xEAr_B,N,N,L,W,3,2 - xEAr_B,N,N,L,W,4,3  = 0
 c7996: 0.77 xEAr_B,N,N,L,W,4,1 - xEAr_B,N,N,L,W,5,2  = 0
 c7997: 0.998 xEAr_B,N,N,L,W,4,2 - xEAr_B,N,N,L,W,5,3  = 0
 c7998: 0.998 xEAr_B,N,N,L,W,4,3 - xEAr_B,N,N,L,W,5,4  = 0
 c7999: 0.998 xEAr_B,N,N,L,C,2,1 - xEAr_B,N,N,L,C,3,2  = 0
 c8000: 0.998 xEAr_B,N,N,L,C,3,1 - xEAr_B,N,N,L,C,4,2  = 0
 c8001: 0.998 xEAr_B,N,N,L,C,3,2 - xEAr_B,N,N,L,C,4,3  = 0
 c8002: 0.998 xEAr_B,N,N,L,C,4,1 - xEAr_B,N,N,L,C,5,2  = 0
 c8003: 0.998 xEAr_B,N,N,L,C,4,2 - xEAr_B,N,N,L,C,5,3  = 0
 c8004: 0.998 xEAr_B,N,N,L,C,4,3 - xEAr_B,N,N,L,C,5,4  = 0
 c8005: 0.998 xEAr_B,N,N,L,I,2,1 - xEAr_B,N,N,L,I,3,2  = 0
 c8006: 0.998 xEAr_B,N,N,L,I,3,1 - xEAr_B,N,N,L,I,4,2  = 0
 c8007: 0.998 xEAr_B,N,N,L,I,3,2 - xEAr_B,N,N,L,I,4,3  = 0
 c8008: 0.998 xEAr_B,N,N,L,I,4,1 - xEAr_B,N,N,L,I,5,2  = 0
 c8009: 0.998 xEAr_B,N,N,L,I,4,2 - xEAr_B,N,N,L,I,5,3  = 0
 c8010: 0.998 xEAr_B,N,N,L,I,4,3 - xEAr_B,N,N,L,I,5,4  = 0
 c8011: 0.998 xEAr_B,N,N,L,A,2,1 - xEAr_B,N,N,L,A,3,2  = 0
 c8012: 0.998 xEAr_B,N,N,L,A,3,1 - xEAr_B,N,N,L,A,4,2  = 0
 c8013: 0.998 xEAr_B,N,N,L,A,3,2 - xEAr_B,N,N,L,A,4,3  = 0
 c8014: 0.998 xEAr_B,N,N,L,A,4,1 - xEAr_B,N,N,L,A,5,2  = 0
 c8015: 0.998 xEAr_B,N,N,L,A,4,2 - xEAr_B,N,N,L,A,5,3  = 0
 c8016: 0.998 xEAr_B,N,N,L,A,4,3 - xEAr_B,N,N,L,A,5,4  = 0
 c8017: 0.998 xEAr_B,N,N,L,L,2,1 - xEAr_B,N,N,L,L,3,2  = 0
 c8018: 0.998 xEAr_B,N,N,L,L,3,1 - xEAr_B,N,N,L,L,4,2  = 0
 c8019: 0.998 xEAr_B,N,N,L,L,3,2 - xEAr_B,N,N,L,L,4,3  = 0
 c8020: 0.998 xEAr_B,N,N,L,L,4,1 - xEAr_B,N,N,L,L,5,2  = 0
 c8021: 0.998 xEAr_B,N,N,L,L,4,2 - xEAr_B,N,N,L,L,5,3  = 0
 c8022: 0.998 xEAr_B,N,N,L,L,4,3 - xEAr_B,N,N,L,L,5,4  = 0
 c8023: xEAr_B,N,N,L,N,2,1 - xEAr_B,N,N,L,N,3,2  = 0
 c8024: xEAr_B,N,N,L,N,3,1 - xEAr_B,N,N,L,N,4,2  = 0
 c8025: xEAr_B,N,N,L,N,3,2 - xEAr_B,N,N,L,N,4,3  = 0
 c8026: xEAr_B,N,N,L,N,4,1 - xEAr_B,N,N,L,N,5,2  = 0
 c8027: xEAr_B,N,N,L,N,4,2 - xEAr_B,N,N,L,N,5,3  = 0
 c8028: xEAr_B,N,N,L,N,4,3 - xEAr_B,N,N,L,N,5,4  = 0
 c8029: 0.998 xEAr_B,N,N,C,P,2,1 - xEAr_B,N,N,C,P,3,2  = 0
 c8030: 0.998 xEAr_B,N,N,C,P,3,1 - xEAr_B,N,N,C,P,4,2  = 0
 c8031: 0.998 xEAr_B,N,N,C,P,3,2 - xEAr_B,N,N,C,P,4,3  = 0
 c8032: 0.998 xEAr_B,N,N,C,P,4,1 - xEAr_B,N,N,C,P,5,2  = 0
 c8033: 0.998 xEAr_B,N,N,C,P,4,2 - xEAr_B,N,N,C,P,5,3  = 0
 c8034: 0.998 xEAr_B,N,N,C,P,4,3 - xEAr_B,N,N,C,P,5,4  = 0
 c8035: 0.998 xEAr_B,N,N,C,D,2,1 - xEAr_B,N,N,C,D,3,2  = 0
 c8036: 0.998 xEAr_B,N,N,C,D,3,1 - xEAr_B,N,N,C,D,4,2  = 0
 c8037: 0.998 xEAr_B,N,N,C,D,3,2 - xEAr_B,N,N,C,D,4,3  = 0
 c8038: 0.998 xEAr_B,N,N,C,D,4,1 - xEAr_B,N,N,C,D,5,2  = 0
 c8039: 0.998 xEAr_B,N,N,C,D,4,2 - xEAr_B,N,N,C,D,5,3  = 0
 c8040: 0.998 xEAr_B,N,N,C,D,4,3 - xEAr_B,N,N,C,D,5,4  = 0
 c8041: 0.77 xEAr_B,N,N,C,W,2,1 - xEAr_B,N,N,C,W,3,2  = 0
 c8042: 0.77 xEAr_B,N,N,C,W,3,1 - xEAr_B,N,N,C,W,4,2  = 0
 c8043: 0.998 xEAr_B,N,N,C,W,3,2 - xEAr_B,N,N,C,W,4,3  = 0
 c8044: 0.77 xEAr_B,N,N,C,W,4,1 - xEAr_B,N,N,C,W,5,2  = 0
 c8045: 0.998 xEAr_B,N,N,C,W,4,2 - xEAr_B,N,N,C,W,5,3  = 0
 c8046: 0.998 xEAr_B,N,N,C,W,4,3 - xEAr_B,N,N,C,W,5,4  = 0
 c8047: 0.998 xEAr_B,N,N,C,C,2,1 - xEAr_B,N,N,C,C,3,2  = 0
 c8048: 0.998 xEAr_B,N,N,C,C,3,1 - xEAr_B,N,N,C,C,4,2  = 0
 c8049: 0.998 xEAr_B,N,N,C,C,3,2 - xEAr_B,N,N,C,C,4,3  = 0
 c8050: 0.998 xEAr_B,N,N,C,C,4,1 - xEAr_B,N,N,C,C,5,2  = 0
 c8051: 0.998 xEAr_B,N,N,C,C,4,2 - xEAr_B,N,N,C,C,5,3  = 0
 c8052: 0.998 xEAr_B,N,N,C,C,4,3 - xEAr_B,N,N,C,C,5,4  = 0
 c8053: 0.998 xEAr_B,N,N,C,I,2,1 - xEAr_B,N,N,C,I,3,2  = 0
 c8054: 0.998 xEAr_B,N,N,C,I,3,1 - xEAr_B,N,N,C,I,4,2  = 0
 c8055: 0.998 xEAr_B,N,N,C,I,3,2 - xEAr_B,N,N,C,I,4,3  = 0
 c8056: 0.998 xEAr_B,N,N,C,I,4,1 - xEAr_B,N,N,C,I,5,2  = 0
 c8057: 0.998 xEAr_B,N,N,C,I,4,2 - xEAr_B,N,N,C,I,5,3  = 0
 c8058: 0.998 xEAr_B,N,N,C,I,4,3 - xEAr_B,N,N,C,I,5,4  = 0
 c8059: 0.998 xEAr_B,N,N,C,A,2,1 - xEAr_B,N,N,C,A,3,2  = 0
 c8060: 0.998 xEAr_B,N,N,C,A,3,1 - xEAr_B,N,N,C,A,4,2  = 0
 c8061: 0.998 xEAr_B,N,N,C,A,3,2 - xEAr_B,N,N,C,A,4,3  = 0
 c8062: 0.998 xEAr_B,N,N,C,A,4,1 - xEAr_B,N,N,C,A,5,2  = 0
 c8063: 0.998 xEAr_B,N,N,C,A,4,2 - xEAr_B,N,N,C,A,5,3  = 0
 c8064: 0.998 xEAr_B,N,N,C,A,4,3 - xEAr_B,N,N,C,A,5,4  = 0
 c8065: 0.998 xEAr_B,N,N,C,L,2,1 - xEAr_B,N,N,C,L,3,2  = 0
 c8066: 0.998 xEAr_B,N,N,C,L,3,1 - xEAr_B,N,N,C,L,4,2  = 0
 c8067: 0.998 xEAr_B,N,N,C,L,3,2 - xEAr_B,N,N,C,L,4,3  = 0
 c8068: 0.998 xEAr_B,N,N,C,L,4,1 - xEAr_B,N,N,C,L,5,2  = 0
 c8069: 0.998 xEAr_B,N,N,C,L,4,2 - xEAr_B,N,N,C,L,5,3  = 0
 c8070: 0.998 xEAr_B,N,N,C,L,4,3 - xEAr_B,N,N,C,L,5,4  = 0
 c8071: xEAr_B,N,N,C,N,2,1 - xEAr_B,N,N,C,N,3,2  = 0
 c8072: xEAr_B,N,N,C,N,3,1 - xEAr_B,N,N,C,N,4,2  = 0
 c8073: xEAr_B,N,N,C,N,3,2 - xEAr_B,N,N,C,N,4,3  = 0
 c8074: xEAr_B,N,N,C,N,4,1 - xEAr_B,N,N,C,N,5,2  = 0
 c8075: xEAr_B,N,N,C,N,4,2 - xEAr_B,N,N,C,N,5,3  = 0
 c8076: xEAr_B,N,N,C,N,4,3 - xEAr_B,N,N,C,N,5,4  = 0
 c8077: 0.998 xEAr_B,N,P,R,P,2,1 - xEAr_B,N,P,R,P,3,2  = 0
 c8078: 0.998 xEAr_B,N,P,R,P,3,1 - xEAr_B,N,P,R,P,4,2  = 0
 c8079: 0.998 xEAr_B,N,P,R,P,3,2 - xEAr_B,N,P,R,P,4,3  = 0
 c8080: 0.998 xEAr_B,N,P,R,P,4,1 - xEAr_B,N,P,R,P,5,2  = 0
 c8081: 0.998 xEAr_B,N,P,R,P,4,2 - xEAr_B,N,P,R,P,5,3  = 0
 c8082: 0.998 xEAr_B,N,P,R,P,4,3 - xEAr_B,N,P,R,P,5,4  = 0
 c8083: 0.998 xEAr_B,N,P,R,D,2,1 - xEAr_B,N,P,R,D,3,2  = 0
 c8084: 0.998 xEAr_B,N,P,R,D,3,1 - xEAr_B,N,P,R,D,4,2  = 0
 c8085: 0.998 xEAr_B,N,P,R,D,3,2 - xEAr_B,N,P,R,D,4,3  = 0
 c8086: 0.998 xEAr_B,N,P,R,D,4,1 - xEAr_B,N,P,R,D,5,2  = 0
 c8087: 0.998 xEAr_B,N,P,R,D,4,2 - xEAr_B,N,P,R,D,5,3  = 0
 c8088: 0.998 xEAr_B,N,P,R,D,4,3 - xEAr_B,N,P,R,D,5,4  = 0
 c8089: 0.77 xEAr_B,N,P,R,W,2,1 - xEAr_B,N,P,R,W,3,2  = 0
 c8090: 0.77 xEAr_B,N,P,R,W,3,1 - xEAr_B,N,P,R,W,4,2  = 0
 c8091: 0.998 xEAr_B,N,P,R,W,3,2 - xEAr_B,N,P,R,W,4,3  = 0
 c8092: 0.77 xEAr_B,N,P,R,W,4,1 - xEAr_B,N,P,R,W,5,2  = 0
 c8093: 0.998 xEAr_B,N,P,R,W,4,2 - xEAr_B,N,P,R,W,5,3  = 0
 c8094: 0.998 xEAr_B,N,P,R,W,4,3 - xEAr_B,N,P,R,W,5,4  = 0
 c8095: 0.998 xEAr_B,N,P,R,C,2,1 - xEAr_B,N,P,R,C,3,2  = 0
 c8096: 0.998 xEAr_B,N,P,R,C,3,1 - xEAr_B,N,P,R,C,4,2  = 0
 c8097: 0.998 xEAr_B,N,P,R,C,3,2 - xEAr_B,N,P,R,C,4,3  = 0
 c8098: 0.998 xEAr_B,N,P,R,C,4,1 - xEAr_B,N,P,R,C,5,2  = 0
 c8099: 0.998 xEAr_B,N,P,R,C,4,2 - xEAr_B,N,P,R,C,5,3  = 0
 c8100: 0.998 xEAr_B,N,P,R,C,4,3 - xEAr_B,N,P,R,C,5,4  = 0
 c8101: 0.998 xEAr_B,N,P,R,I,2,1 - xEAr_B,N,P,R,I,3,2  = 0
 c8102: 0.998 xEAr_B,N,P,R,I,3,1 - xEAr_B,N,P,R,I,4,2  = 0
 c8103: 0.998 xEAr_B,N,P,R,I,3,2 - xEAr_B,N,P,R,I,4,3  = 0
 c8104: 0.998 xEAr_B,N,P,R,I,4,1 - xEAr_B,N,P,R,I,5,2  = 0
 c8105: 0.998 xEAr_B,N,P,R,I,4,2 - xEAr_B,N,P,R,I,5,3  = 0
 c8106: 0.998 xEAr_B,N,P,R,I,4,3 - xEAr_B,N,P,R,I,5,4  = 0
 c8107: 0.998 xEAr_B,N,P,R,A,2,1 - xEAr_B,N,P,R,A,3,2  = 0
 c8108: 0.998 xEAr_B,N,P,R,A,3,1 - xEAr_B,N,P,R,A,4,2  = 0
 c8109: 0.998 xEAr_B,N,P,R,A,3,2 - xEAr_B,N,P,R,A,4,3  = 0
 c8110: 0.998 xEAr_B,N,P,R,A,4,1 - xEAr_B,N,P,R,A,5,2  = 0
 c8111: 0.998 xEAr_B,N,P,R,A,4,2 - xEAr_B,N,P,R,A,5,3  = 0
 c8112: 0.998 xEAr_B,N,P,R,A,4,3 - xEAr_B,N,P,R,A,5,4  = 0
 c8113: 0.998 xEAr_B,N,P,R,L,2,1 - xEAr_B,N,P,R,L,3,2  = 0
 c8114: 0.998 xEAr_B,N,P,R,L,3,1 - xEAr_B,N,P,R,L,4,2  = 0
 c8115: 0.998 xEAr_B,N,P,R,L,3,2 - xEAr_B,N,P,R,L,4,3  = 0
 c8116: 0.998 xEAr_B,N,P,R,L,4,1 - xEAr_B,N,P,R,L,5,2  = 0
 c8117: 0.998 xEAr_B,N,P,R,L,4,2 - xEAr_B,N,P,R,L,5,3  = 0
 c8118: 0.998 xEAr_B,N,P,R,L,4,3 - xEAr_B,N,P,R,L,5,4  = 0
 c8119: xEAr_B,N,P,R,N,2,1 - xEAr_B,N,P,R,N,3,2  = 0
 c8120: xEAr_B,N,P,R,N,3,1 - xEAr_B,N,P,R,N,4,2  = 0
 c8121: xEAr_B,N,P,R,N,3,2 - xEAr_B,N,P,R,N,4,3  = 0
 c8122: xEAr_B,N,P,R,N,4,1 - xEAr_B,N,P,R,N,5,2  = 0
 c8123: xEAr_B,N,P,R,N,4,2 - xEAr_B,N,P,R,N,5,3  = 0
 c8124: xEAr_B,N,P,R,N,4,3 - xEAr_B,N,P,R,N,5,4  = 0
 c8125: 0.998 xEAr_C,N,N,L,P,2,1 - xEAr_C,N,N,L,P,3,2  = 0
 c8126: 0.998 xEAr_C,N,N,L,P,3,1 - xEAr_C,N,N,L,P,4,2  = 0
 c8127: 0.998 xEAr_C,N,N,L,P,3,2 - xEAr_C,N,N,L,P,4,3  = 0
 c8128: 0.998 xEAr_C,N,N,L,P,4,1 - xEAr_C,N,N,L,P,5,2  = 0
 c8129: 0.998 xEAr_C,N,N,L,P,4,2 - xEAr_C,N,N,L,P,5,3  = 0
 c8130: 0.998 xEAr_C,N,N,L,P,4,3 - xEAr_C,N,N,L,P,5,4  = 0
 c8131: 0.998 xEAr_C,N,N,L,D,2,1 - xEAr_C,N,N,L,D,3,2  = 0
 c8132: 0.998 xEAr_C,N,N,L,D,3,1 - xEAr_C,N,N,L,D,4,2  = 0
 c8133: 0.998 xEAr_C,N,N,L,D,3,2 - xEAr_C,N,N,L,D,4,3  = 0
 c8134: 0.998 xEAr_C,N,N,L,D,4,1 - xEAr_C,N,N,L,D,5,2  = 0
 c8135: 0.998 xEAr_C,N,N,L,D,4,2 - xEAr_C,N,N,L,D,5,3  = 0
 c8136: 0.998 xEAr_C,N,N,L,D,4,3 - xEAr_C,N,N,L,D,5,4  = 0
 c8137: 0.77 xEAr_C,N,N,L,W,2,1 - xEAr_C,N,N,L,W,3,2  = 0
 c8138: 0.77 xEAr_C,N,N,L,W,3,1 - xEAr_C,N,N,L,W,4,2  = 0
 c8139: 0.998 xEAr_C,N,N,L,W,3,2 - xEAr_C,N,N,L,W,4,3  = 0
 c8140: 0.77 xEAr_C,N,N,L,W,4,1 - xEAr_C,N,N,L,W,5,2  = 0
 c8141: 0.998 xEAr_C,N,N,L,W,4,2 - xEAr_C,N,N,L,W,5,3  = 0
 c8142: 0.998 xEAr_C,N,N,L,W,4,3 - xEAr_C,N,N,L,W,5,4  = 0
 c8143: 0.998 xEAr_C,N,N,L,C,2,1 - xEAr_C,N,N,L,C,3,2  = 0
 c8144: 0.998 xEAr_C,N,N,L,C,3,1 - xEAr_C,N,N,L,C,4,2  = 0
 c8145: 0.998 xEAr_C,N,N,L,C,3,2 - xEAr_C,N,N,L,C,4,3  = 0
 c8146: 0.998 xEAr_C,N,N,L,C,4,1 - xEAr_C,N,N,L,C,5,2  = 0
 c8147: 0.998 xEAr_C,N,N,L,C,4,2 - xEAr_C,N,N,L,C,5,3  = 0
 c8148: 0.998 xEAr_C,N,N,L,C,4,3 - xEAr_C,N,N,L,C,5,4  = 0
 c8149: 0.998 xEAr_C,N,N,L,I,2,1 - xEAr_C,N,N,L,I,3,2  = 0
 c8150: 0.998 xEAr_C,N,N,L,I,3,1 - xEAr_C,N,N,L,I,4,2  = 0
 c8151: 0.998 xEAr_C,N,N,L,I,3,2 - xEAr_C,N,N,L,I,4,3  = 0
 c8152: 0.998 xEAr_C,N,N,L,I,4,1 - xEAr_C,N,N,L,I,5,2  = 0
 c8153: 0.998 xEAr_C,N,N,L,I,4,2 - xEAr_C,N,N,L,I,5,3  = 0
 c8154: 0.998 xEAr_C,N,N,L,I,4,3 - xEAr_C,N,N,L,I,5,4  = 0
 c8155: 0.998 xEAr_C,N,N,L,A,2,1 - xEAr_C,N,N,L,A,3,2  = 0
 c8156: 0.998 xEAr_C,N,N,L,A,3,1 - xEAr_C,N,N,L,A,4,2  = 0
 c8157: 0.998 xEAr_C,N,N,L,A,3,2 - xEAr_C,N,N,L,A,4,3  = 0
 c8158: 0.998 xEAr_C,N,N,L,A,4,1 - xEAr_C,N,N,L,A,5,2  = 0
 c8159: 0.998 xEAr_C,N,N,L,A,4,2 - xEAr_C,N,N,L,A,5,3  = 0
 c8160: 0.998 xEAr_C,N,N,L,A,4,3 - xEAr_C,N,N,L,A,5,4  = 0
 c8161: 0.998 xEAr_C,N,N,L,L,2,1 - xEAr_C,N,N,L,L,3,2  = 0
 c8162: 0.998 xEAr_C,N,N,L,L,3,1 - xEAr_C,N,N,L,L,4,2  = 0
 c8163: 0.998 xEAr_C,N,N,L,L,3,2 - xEAr_C,N,N,L,L,4,3  = 0
 c8164: 0.998 xEAr_C,N,N,L,L,4,1 - xEAr_C,N,N,L,L,5,2  = 0
 c8165: 0.998 xEAr_C,N,N,L,L,4,2 - xEAr_C,N,N,L,L,5,3  = 0
 c8166: 0.998 xEAr_C,N,N,L,L,4,3 - xEAr_C,N,N,L,L,5,4  = 0
 c8167: xEAr_C,N,N,L,N,2,1 - xEAr_C,N,N,L,N,3,2  = 0
 c8168: xEAr_C,N,N,L,N,3,1 - xEAr_C,N,N,L,N,4,2  = 0
 c8169: xEAr_C,N,N,L,N,3,2 - xEAr_C,N,N,L,N,4,3  = 0
 c8170: xEAr_C,N,N,L,N,4,1 - xEAr_C,N,N,L,N,5,2  = 0
 c8171: xEAr_C,N,N,L,N,4,2 - xEAr_C,N,N,L,N,5,3  = 0
 c8172: xEAr_C,N,N,L,N,4,3 - xEAr_C,N,N,L,N,5,4  = 0
 c8173: 0.998 xEAr_C,N,N,H,P,2,1 - xEAr_C,N,N,H,P,3,2  = 0
 c8174: 0.998 xEAr_C,N,N,H,P,3,1 - xEAr_C,N,N,H,P,4,2  = 0
 c8175: 0.998 xEAr_C,N,N,H,P,3,2 - xEAr_C,N,N,H,P,4,3  = 0
 c8176: 0.998 xEAr_C,N,N,H,P,4,1 - xEAr_C,N,N,H,P,5,2  = 0
 c8177: 0.998 xEAr_C,N,N,H,P,4,2 - xEAr_C,N,N,H,P,5,3  = 0
 c8178: 0.998 xEAr_C,N,N,H,P,4,3 - xEAr_C,N,N,H,P,5,4  = 0
 c8179: 0.998 xEAr_C,N,N,H,D,2,1 - xEAr_C,N,N,H,D,3,2  = 0
 c8180: 0.998 xEAr_C,N,N,H,D,3,1 - xEAr_C,N,N,H,D,4,2  = 0
 c8181: 0.998 xEAr_C,N,N,H,D,3,2 - xEAr_C,N,N,H,D,4,3  = 0
 c8182: 0.998 xEAr_C,N,N,H,D,4,1 - xEAr_C,N,N,H,D,5,2  = 0
 c8183: 0.998 xEAr_C,N,N,H,D,4,2 - xEAr_C,N,N,H,D,5,3  = 0
 c8184: 0.998 xEAr_C,N,N,H,D,4,3 - xEAr_C,N,N,H,D,5,4  = 0
 c8185: 0.77 xEAr_C,N,N,H,W,2,1 - xEAr_C,N,N,H,W,3,2  = 0
 c8186: 0.77 xEAr_C,N,N,H,W,3,1 - xEAr_C,N,N,H,W,4,2  = 0
 c8187: 0.998 xEAr_C,N,N,H,W,3,2 - xEAr_C,N,N,H,W,4,3  = 0
 c8188: 0.77 xEAr_C,N,N,H,W,4,1 - xEAr_C,N,N,H,W,5,2  = 0
 c8189: 0.998 xEAr_C,N,N,H,W,4,2 - xEAr_C,N,N,H,W,5,3  = 0
 c8190: 0.998 xEAr_C,N,N,H,W,4,3 - xEAr_C,N,N,H,W,5,4  = 0
 c8191: 0.998 xEAr_C,N,N,H,C,2,1 - xEAr_C,N,N,H,C,3,2  = 0
 c8192: 0.998 xEAr_C,N,N,H,C,3,1 - xEAr_C,N,N,H,C,4,2  = 0
 c8193: 0.998 xEAr_C,N,N,H,C,3,2 - xEAr_C,N,N,H,C,4,3  = 0
 c8194: 0.998 xEAr_C,N,N,H,C,4,1 - xEAr_C,N,N,H,C,5,2  = 0
 c8195: 0.998 xEAr_C,N,N,H,C,4,2 - xEAr_C,N,N,H,C,5,3  = 0
 c8196: 0.998 xEAr_C,N,N,H,C,4,3 - xEAr_C,N,N,H,C,5,4  = 0
 c8197: 0.998 xEAr_C,N,N,H,I,2,1 - xEAr_C,N,N,H,I,3,2  = 0
 c8198: 0.998 xEAr_C,N,N,H,I,3,1 - xEAr_C,N,N,H,I,4,2  = 0
 c8199: 0.998 xEAr_C,N,N,H,I,3,2 - xEAr_C,N,N,H,I,4,3  = 0
 c8200: 0.998 xEAr_C,N,N,H,I,4,1 - xEAr_C,N,N,H,I,5,2  = 0
 c8201: 0.998 xEAr_C,N,N,H,I,4,2 - xEAr_C,N,N,H,I,5,3  = 0
 c8202: 0.998 xEAr_C,N,N,H,I,4,3 - xEAr_C,N,N,H,I,5,4  = 0
 c8203: 0.998 xEAr_C,N,N,H,A,2,1 - xEAr_C,N,N,H,A,3,2  = 0
 c8204: 0.998 xEAr_C,N,N,H,A,3,1 - xEAr_C,N,N,H,A,4,2  = 0
 c8205: 0.998 xEAr_C,N,N,H,A,3,2 - xEAr_C,N,N,H,A,4,3  = 0
 c8206: 0.998 xEAr_C,N,N,H,A,4,1 - xEAr_C,N,N,H,A,5,2  = 0
 c8207: 0.998 xEAr_C,N,N,H,A,4,2 - xEAr_C,N,N,H,A,5,3  = 0
 c8208: 0.998 xEAr_C,N,N,H,A,4,3 - xEAr_C,N,N,H,A,5,4  = 0
 c8209: 0.998 xEAr_C,N,N,H,L,2,1 - xEAr_C,N,N,H,L,3,2  = 0
 c8210: 0.998 xEAr_C,N,N,H,L,3,1 - xEAr_C,N,N,H,L,4,2  = 0
 c8211: 0.998 xEAr_C,N,N,H,L,3,2 - xEAr_C,N,N,H,L,4,3  = 0
 c8212: 0.998 xEAr_C,N,N,H,L,4,1 - xEAr_C,N,N,H,L,5,2  = 0
 c8213: 0.998 xEAr_C,N,N,H,L,4,2 - xEAr_C,N,N,H,L,5,3  = 0
 c8214: 0.998 xEAr_C,N,N,H,L,4,3 - xEAr_C,N,N,H,L,5,4  = 0
 c8215: xEAr_C,N,N,H,N,2,1 - xEAr_C,N,N,H,N,3,2  = 0
 c8216: xEAr_C,N,N,H,N,3,1 - xEAr_C,N,N,H,N,4,2  = 0
 c8217: xEAr_C,N,N,H,N,3,2 - xEAr_C,N,N,H,N,4,3  = 0
 c8218: xEAr_C,N,N,H,N,4,1 - xEAr_C,N,N,H,N,5,2  = 0
 c8219: xEAr_C,N,N,H,N,4,2 - xEAr_C,N,N,H,N,5,3  = 0
 c8220: xEAr_C,N,N,H,N,4,3 - xEAr_C,N,N,H,N,5,4  = 0
 c8221: 0.998 xEAr_C,N,N,C,P,2,1 - xEAr_C,N,N,C,P,3,2  = 0
 c8222: 0.998 xEAr_C,N,N,C,P,3,1 - xEAr_C,N,N,C,P,4,2  = 0
 c8223: 0.998 xEAr_C,N,N,C,P,3,2 - xEAr_C,N,N,C,P,4,3  = 0
 c8224: 0.998 xEAr_C,N,N,C,P,4,1 - xEAr_C,N,N,C,P,5,2  = 0
 c8225: 0.998 xEAr_C,N,N,C,P,4,2 - xEAr_C,N,N,C,P,5,3  = 0
 c8226: 0.998 xEAr_C,N,N,C,P,4,3 - xEAr_C,N,N,C,P,5,4  = 0
 c8227: 0.998 xEAr_C,N,N,C,D,2,1 - xEAr_C,N,N,C,D,3,2  = 0
 c8228: 0.998 xEAr_C,N,N,C,D,3,1 - xEAr_C,N,N,C,D,4,2  = 0
 c8229: 0.998 xEAr_C,N,N,C,D,3,2 - xEAr_C,N,N,C,D,4,3  = 0
 c8230: 0.998 xEAr_C,N,N,C,D,4,1 - xEAr_C,N,N,C,D,5,2  = 0
 c8231: 0.998 xEAr_C,N,N,C,D,4,2 - xEAr_C,N,N,C,D,5,3  = 0
 c8232: 0.998 xEAr_C,N,N,C,D,4,3 - xEAr_C,N,N,C,D,5,4  = 0
 c8233: 0.77 xEAr_C,N,N,C,W,2,1 - xEAr_C,N,N,C,W,3,2  = 0
 c8234: 0.77 xEAr_C,N,N,C,W,3,1 - xEAr_C,N,N,C,W,4,2  = 0
 c8235: 0.998 xEAr_C,N,N,C,W,3,2 - xEAr_C,N,N,C,W,4,3  = 0
 c8236: 0.77 xEAr_C,N,N,C,W,4,1 - xEAr_C,N,N,C,W,5,2  = 0
 c8237: 0.998 xEAr_C,N,N,C,W,4,2 - xEAr_C,N,N,C,W,5,3  = 0
 c8238: 0.998 xEAr_C,N,N,C,W,4,3 - xEAr_C,N,N,C,W,5,4  = 0
 c8239: 0.998 xEAr_C,N,N,C,C,2,1 - xEAr_C,N,N,C,C,3,2  = 0
 c8240: 0.998 xEAr_C,N,N,C,C,3,1 - xEAr_C,N,N,C,C,4,2  = 0
 c8241: 0.998 xEAr_C,N,N,C,C,3,2 - xEAr_C,N,N,C,C,4,3  = 0
 c8242: 0.998 xEAr_C,N,N,C,C,4,1 - xEAr_C,N,N,C,C,5,2  = 0
 c8243: 0.998 xEAr_C,N,N,C,C,4,2 - xEAr_C,N,N,C,C,5,3  = 0
 c8244: 0.998 xEAr_C,N,N,C,C,4,3 - xEAr_C,N,N,C,C,5,4  = 0
 c8245: 0.998 xEAr_C,N,N,C,I,2,1 - xEAr_C,N,N,C,I,3,2  = 0
 c8246: 0.998 xEAr_C,N,N,C,I,3,1 - xEAr_C,N,N,C,I,4,2  = 0
 c8247: 0.998 xEAr_C,N,N,C,I,3,2 - xEAr_C,N,N,C,I,4,3  = 0
 c8248: 0.998 xEAr_C,N,N,C,I,4,1 - xEAr_C,N,N,C,I,5,2  = 0
 c8249: 0.998 xEAr_C,N,N,C,I,4,2 - xEAr_C,N,N,C,I,5,3  = 0
 c8250: 0.998 xEAr_C,N,N,C,I,4,3 - xEAr_C,N,N,C,I,5,4  = 0
 c8251: 0.998 xEAr_C,N,N,C,A,2,1 - xEAr_C,N,N,C,A,3,2  = 0
 c8252: 0.998 xEAr_C,N,N,C,A,3,1 - xEAr_C,N,N,C,A,4,2  = 0
 c8253: 0.998 xEAr_C,N,N,C,A,3,2 - xEAr_C,N,N,C,A,4,3  = 0
 c8254: 0.998 xEAr_C,N,N,C,A,4,1 - xEAr_C,N,N,C,A,5,2  = 0
 c8255: 0.998 xEAr_C,N,N,C,A,4,2 - xEAr_C,N,N,C,A,5,3  = 0
 c8256: 0.998 xEAr_C,N,N,C,A,4,3 - xEAr_C,N,N,C,A,5,4  = 0
 c8257: 0.998 xEAr_C,N,N,C,L,2,1 - xEAr_C,N,N,C,L,3,2  = 0
 c8258: 0.998 xEAr_C,N,N,C,L,3,1 - xEAr_C,N,N,C,L,4,2  = 0
 c8259: 0.998 xEAr_C,N,N,C,L,3,2 - xEAr_C,N,N,C,L,4,3  = 0
 c8260: 0.998 xEAr_C,N,N,C,L,4,1 - xEAr_C,N,N,C,L,5,2  = 0
 c8261: 0.998 xEAr_C,N,N,C,L,4,2 - xEAr_C,N,N,C,L,5,3  = 0
 c8262: 0.998 xEAr_C,N,N,C,L,4,3 - xEAr_C,N,N,C,L,5,4  = 0
 c8263: xEAr_C,N,N,C,N,2,1 - xEAr_C,N,N,C,N,3,2  = 0
 c8264: xEAr_C,N,N,C,N,3,1 - xEAr_C,N,N,C,N,4,2  = 0
 c8265: xEAr_C,N,N,C,N,3,2 - xEAr_C,N,N,C,N,4,3  = 0
 c8266: xEAr_C,N,N,C,N,4,1 - xEAr_C,N,N,C,N,5,2  = 0
 c8267: xEAr_C,N,N,C,N,4,2 - xEAr_C,N,N,C,N,5,3  = 0
 c8268: xEAr_C,N,N,C,N,4,3 - xEAr_C,N,N,C,N,5,4  = 0
 c8269: 0.998 xEAr_C,N,N,R,P,2,1 - xEAr_C,N,N,R,P,3,2  = 0
 c8270: 0.998 xEAr_C,N,N,R,P,3,1 - xEAr_C,N,N,R,P,4,2  = 0
 c8271: 0.998 xEAr_C,N,N,R,P,3,2 - xEAr_C,N,N,R,P,4,3  = 0
 c8272: 0.998 xEAr_C,N,N,R,P,4,1 - xEAr_C,N,N,R,P,5,2  = 0
 c8273: 0.998 xEAr_C,N,N,R,P,4,2 - xEAr_C,N,N,R,P,5,3  = 0
 c8274: 0.998 xEAr_C,N,N,R,P,4,3 - xEAr_C,N,N,R,P,5,4  = 0
 c8275: 0.998 xEAr_C,N,N,R,D,2,1 - xEAr_C,N,N,R,D,3,2  = 0
 c8276: 0.998 xEAr_C,N,N,R,D,3,1 - xEAr_C,N,N,R,D,4,2  = 0
 c8277: 0.998 xEAr_C,N,N,R,D,3,2 - xEAr_C,N,N,R,D,4,3  = 0
 c8278: 0.998 xEAr_C,N,N,R,D,4,1 - xEAr_C,N,N,R,D,5,2  = 0
 c8279: 0.998 xEAr_C,N,N,R,D,4,2 - xEAr_C,N,N,R,D,5,3  = 0
 c8280: 0.998 xEAr_C,N,N,R,D,4,3 - xEAr_C,N,N,R,D,5,4  = 0
 c8281: 0.77 xEAr_C,N,N,R,W,2,1 - xEAr_C,N,N,R,W,3,2  = 0
 c8282: 0.77 xEAr_C,N,N,R,W,3,1 - xEAr_C,N,N,R,W,4,2  = 0
 c8283: 0.998 xEAr_C,N,N,R,W,3,2 - xEAr_C,N,N,R,W,4,3  = 0
 c8284: 0.77 xEAr_C,N,N,R,W,4,1 - xEAr_C,N,N,R,W,5,2  = 0
 c8285: 0.998 xEAr_C,N,N,R,W,4,2 - xEAr_C,N,N,R,W,5,3  = 0
 c8286: 0.998 xEAr_C,N,N,R,W,4,3 - xEAr_C,N,N,R,W,5,4  = 0
 c8287: 0.998 xEAr_C,N,N,R,C,2,1 - xEAr_C,N,N,R,C,3,2  = 0
 c8288: 0.998 xEAr_C,N,N,R,C,3,1 - xEAr_C,N,N,R,C,4,2  = 0
 c8289: 0.998 xEAr_C,N,N,R,C,3,2 - xEAr_C,N,N,R,C,4,3  = 0
 c8290: 0.998 xEAr_C,N,N,R,C,4,1 - xEAr_C,N,N,R,C,5,2  = 0
 c8291: 0.998 xEAr_C,N,N,R,C,4,2 - xEAr_C,N,N,R,C,5,3  = 0
 c8292: 0.998 xEAr_C,N,N,R,C,4,3 - xEAr_C,N,N,R,C,5,4  = 0
 c8293: 0.998 xEAr_C,N,N,R,I,2,1 - xEAr_C,N,N,R,I,3,2  = 0
 c8294: 0.998 xEAr_C,N,N,R,I,3,1 - xEAr_C,N,N,R,I,4,2  = 0
 c8295: 0.998 xEAr_C,N,N,R,I,3,2 - xEAr_C,N,N,R,I,4,3  = 0
 c8296: 0.998 xEAr_C,N,N,R,I,4,1 - xEAr_C,N,N,R,I,5,2  = 0
 c8297: 0.998 xEAr_C,N,N,R,I,4,2 - xEAr_C,N,N,R,I,5,3  = 0
 c8298: 0.998 xEAr_C,N,N,R,I,4,3 - xEAr_C,N,N,R,I,5,4  = 0
 c8299: 0.998 xEAr_C,N,N,R,A,2,1 - xEAr_C,N,N,R,A,3,2  = 0
 c8300: 0.998 xEAr_C,N,N,R,A,3,1 - xEAr_C,N,N,R,A,4,2  = 0
 c8301: 0.998 xEAr_C,N,N,R,A,3,2 - xEAr_C,N,N,R,A,4,3  = 0
 c8302: 0.998 xEAr_C,N,N,R,A,4,1 - xEAr_C,N,N,R,A,5,2  = 0
 c8303: 0.998 xEAr_C,N,N,R,A,4,2 - xEAr_C,N,N,R,A,5,3  = 0
 c8304: 0.998 xEAr_C,N,N,R,A,4,3 - xEAr_C,N,N,R,A,5,4  = 0
 c8305: 0.998 xEAr_C,N,N,R,L,2,1 - xEAr_C,N,N,R,L,3,2  = 0
 c8306: 0.998 xEAr_C,N,N,R,L,3,1 - xEAr_C,N,N,R,L,4,2  = 0
 c8307: 0.998 xEAr_C,N,N,R,L,3,2 - xEAr_C,N,N,R,L,4,3  = 0
 c8308: 0.998 xEAr_C,N,N,R,L,4,1 - xEAr_C,N,N,R,L,5,2  = 0
 c8309: 0.998 xEAr_C,N,N,R,L,4,2 - xEAr_C,N,N,R,L,5,3  = 0
 c8310: 0.998 xEAr_C,N,N,R,L,4,3 - xEAr_C,N,N,R,L,5,4  = 0
 c8311: xEAr_C,N,N,R,N,2,1 - xEAr_C,N,N,R,N,3,2  = 0
 c8312: xEAr_C,N,N,R,N,3,1 - xEAr_C,N,N,R,N,4,2  = 0
 c8313: xEAr_C,N,N,R,N,3,2 - xEAr_C,N,N,R,N,4,3  = 0
 c8314: xEAr_C,N,N,R,N,4,1 - xEAr_C,N,N,R,N,5,2  = 0
 c8315: xEAr_C,N,N,R,N,4,2 - xEAr_C,N,N,R,N,5,3  = 0
 c8316: xEAr_C,N,N,R,N,4,3 - xEAr_C,N,N,R,N,5,4  = 0
 c8317: 0.998 xEAr_C,N,O,L,P,2,1 - xEAr_C,N,O,L,P,3,2  = 0
 c8318: 0.998 xEAr_C,N,O,L,P,3,1 - xEAr_C,N,O,L,P,4,2  = 0
 c8319: 0.998 xEAr_C,N,O,L,P,3,2 - xEAr_C,N,O,L,P,4,3  = 0
 c8320: 0.998 xEAr_C,N,O,L,P,4,1 - xEAr_C,N,O,L,P,5,2  = 0
 c8321: 0.998 xEAr_C,N,O,L,P,4,2 - xEAr_C,N,O,L,P,5,3  = 0
 c8322: 0.998 xEAr_C,N,O,L,P,4,3 - xEAr_C,N,O,L,P,5,4  = 0
 c8323: 0.998 xEAr_C,N,O,L,D,2,1 - xEAr_C,N,O,L,D,3,2  = 0
 c8324: 0.998 xEAr_C,N,O,L,D,3,1 - xEAr_C,N,O,L,D,4,2  = 0
 c8325: 0.998 xEAr_C,N,O,L,D,3,2 - xEAr_C,N,O,L,D,4,3  = 0
 c8326: 0.998 xEAr_C,N,O,L,D,4,1 - xEAr_C,N,O,L,D,5,2  = 0
 c8327: 0.998 xEAr_C,N,O,L,D,4,2 - xEAr_C,N,O,L,D,5,3  = 0
 c8328: 0.998 xEAr_C,N,O,L,D,4,3 - xEAr_C,N,O,L,D,5,4  = 0
 c8329: 0.77 xEAr_C,N,O,L,W,2,1 - xEAr_C,N,O,L,W,3,2  = 0
 c8330: 0.77 xEAr_C,N,O,L,W,3,1 - xEAr_C,N,O,L,W,4,2  = 0
 c8331: 0.998 xEAr_C,N,O,L,W,3,2 - xEAr_C,N,O,L,W,4,3  = 0
 c8332: 0.77 xEAr_C,N,O,L,W,4,1 - xEAr_C,N,O,L,W,5,2  = 0
 c8333: 0.998 xEAr_C,N,O,L,W,4,2 - xEAr_C,N,O,L,W,5,3  = 0
 c8334: 0.998 xEAr_C,N,O,L,W,4,3 - xEAr_C,N,O,L,W,5,4  = 0
 c8335: 0.998 xEAr_C,N,O,L,C,2,1 - xEAr_C,N,O,L,C,3,2  = 0
 c8336: 0.998 xEAr_C,N,O,L,C,3,1 - xEAr_C,N,O,L,C,4,2  = 0
 c8337: 0.998 xEAr_C,N,O,L,C,3,2 - xEAr_C,N,O,L,C,4,3  = 0
 c8338: 0.998 xEAr_C,N,O,L,C,4,1 - xEAr_C,N,O,L,C,5,2  = 0
 c8339: 0.998 xEAr_C,N,O,L,C,4,2 - xEAr_C,N,O,L,C,5,3  = 0
 c8340: 0.998 xEAr_C,N,O,L,C,4,3 - xEAr_C,N,O,L,C,5,4  = 0
 c8341: 0.998 xEAr_C,N,O,L,I,2,1 - xEAr_C,N,O,L,I,3,2  = 0
 c8342: 0.998 xEAr_C,N,O,L,I,3,1 - xEAr_C,N,O,L,I,4,2  = 0
 c8343: 0.998 xEAr_C,N,O,L,I,3,2 - xEAr_C,N,O,L,I,4,3  = 0
 c8344: 0.998 xEAr_C,N,O,L,I,4,1 - xEAr_C,N,O,L,I,5,2  = 0
 c8345: 0.998 xEAr_C,N,O,L,I,4,2 - xEAr_C,N,O,L,I,5,3  = 0
 c8346: 0.998 xEAr_C,N,O,L,I,4,3 - xEAr_C,N,O,L,I,5,4  = 0
 c8347: 0.998 xEAr_C,N,O,L,A,2,1 - xEAr_C,N,O,L,A,3,2  = 0
 c8348: 0.998 xEAr_C,N,O,L,A,3,1 - xEAr_C,N,O,L,A,4,2  = 0
 c8349: 0.998 xEAr_C,N,O,L,A,3,2 - xEAr_C,N,O,L,A,4,3  = 0
 c8350: 0.998 xEAr_C,N,O,L,A,4,1 - xEAr_C,N,O,L,A,5,2  = 0
 c8351: 0.998 xEAr_C,N,O,L,A,4,2 - xEAr_C,N,O,L,A,5,3  = 0
 c8352: 0.998 xEAr_C,N,O,L,A,4,3 - xEAr_C,N,O,L,A,5,4  = 0
 c8353: 0.998 xEAr_C,N,O,L,L,2,1 - xEAr_C,N,O,L,L,3,2  = 0
 c8354: 0.998 xEAr_C,N,O,L,L,3,1 - xEAr_C,N,O,L,L,4,2  = 0
 c8355: 0.998 xEAr_C,N,O,L,L,3,2 - xEAr_C,N,O,L,L,4,3  = 0
 c8356: 0.998 xEAr_C,N,O,L,L,4,1 - xEAr_C,N,O,L,L,5,2  = 0
 c8357: 0.998 xEAr_C,N,O,L,L,4,2 - xEAr_C,N,O,L,L,5,3  = 0
 c8358: 0.998 xEAr_C,N,O,L,L,4,3 - xEAr_C,N,O,L,L,5,4  = 0
 c8359: xEAr_C,N,O,L,N,2,1 - xEAr_C,N,O,L,N,3,2  = 0
 c8360: xEAr_C,N,O,L,N,3,1 - xEAr_C,N,O,L,N,4,2  = 0
 c8361: xEAr_C,N,O,L,N,3,2 - xEAr_C,N,O,L,N,4,3  = 0
 c8362: xEAr_C,N,O,L,N,4,1 - xEAr_C,N,O,L,N,5,2  = 0
 c8363: xEAr_C,N,O,L,N,4,2 - xEAr_C,N,O,L,N,5,3  = 0
 c8364: xEAr_C,N,O,L,N,4,3 - xEAr_C,N,O,L,N,5,4  = 0
 c8365: 0.998 xEAr_C,N,O,H,P,2,1 - xEAr_C,N,O,H,P,3,2  = 0
 c8366: 0.998 xEAr_C,N,O,H,P,3,1 - xEAr_C,N,O,H,P,4,2  = 0
 c8367: 0.998 xEAr_C,N,O,H,P,3,2 - xEAr_C,N,O,H,P,4,3  = 0
 c8368: 0.998 xEAr_C,N,O,H,P,4,1 - xEAr_C,N,O,H,P,5,2  = 0
 c8369: 0.998 xEAr_C,N,O,H,P,4,2 - xEAr_C,N,O,H,P,5,3  = 0
 c8370: 0.998 xEAr_C,N,O,H,P,4,3 - xEAr_C,N,O,H,P,5,4  = 0
 c8371: 0.998 xEAr_C,N,O,H,D,2,1 - xEAr_C,N,O,H,D,3,2  = 0
 c8372: 0.998 xEAr_C,N,O,H,D,3,1 - xEAr_C,N,O,H,D,4,2  = 0
 c8373: 0.998 xEAr_C,N,O,H,D,3,2 - xEAr_C,N,O,H,D,4,3  = 0
 c8374: 0.998 xEAr_C,N,O,H,D,4,1 - xEAr_C,N,O,H,D,5,2  = 0
 c8375: 0.998 xEAr_C,N,O,H,D,4,2 - xEAr_C,N,O,H,D,5,3  = 0
 c8376: 0.998 xEAr_C,N,O,H,D,4,3 - xEAr_C,N,O,H,D,5,4  = 0
 c8377: 0.77 xEAr_C,N,O,H,W,2,1 - xEAr_C,N,O,H,W,3,2  = 0
 c8378: 0.77 xEAr_C,N,O,H,W,3,1 - xEAr_C,N,O,H,W,4,2  = 0
 c8379: 0.998 xEAr_C,N,O,H,W,3,2 - xEAr_C,N,O,H,W,4,3  = 0
 c8380: 0.77 xEAr_C,N,O,H,W,4,1 - xEAr_C,N,O,H,W,5,2  = 0
 c8381: 0.998 xEAr_C,N,O,H,W,4,2 - xEAr_C,N,O,H,W,5,3  = 0
 c8382: 0.998 xEAr_C,N,O,H,W,4,3 - xEAr_C,N,O,H,W,5,4  = 0
 c8383: 0.998 xEAr_C,N,O,H,C,2,1 - xEAr_C,N,O,H,C,3,2  = 0
 c8384: 0.998 xEAr_C,N,O,H,C,3,1 - xEAr_C,N,O,H,C,4,2  = 0
 c8385: 0.998 xEAr_C,N,O,H,C,3,2 - xEAr_C,N,O,H,C,4,3  = 0
 c8386: 0.998 xEAr_C,N,O,H,C,4,1 - xEAr_C,N,O,H,C,5,2  = 0
 c8387: 0.998 xEAr_C,N,O,H,C,4,2 - xEAr_C,N,O,H,C,5,3  = 0
 c8388: 0.998 xEAr_C,N,O,H,C,4,3 - xEAr_C,N,O,H,C,5,4  = 0
 c8389: 0.998 xEAr_C,N,O,H,I,2,1 - xEAr_C,N,O,H,I,3,2  = 0
 c8390: 0.998 xEAr_C,N,O,H,I,3,1 - xEAr_C,N,O,H,I,4,2  = 0
 c8391: 0.998 xEAr_C,N,O,H,I,3,2 - xEAr_C,N,O,H,I,4,3  = 0
 c8392: 0.998 xEAr_C,N,O,H,I,4,1 - xEAr_C,N,O,H,I,5,2  = 0
 c8393: 0.998 xEAr_C,N,O,H,I,4,2 - xEAr_C,N,O,H,I,5,3  = 0
 c8394: 0.998 xEAr_C,N,O,H,I,4,3 - xEAr_C,N,O,H,I,5,4  = 0
 c8395: 0.998 xEAr_C,N,O,H,A,2,1 - xEAr_C,N,O,H,A,3,2  = 0
 c8396: 0.998 xEAr_C,N,O,H,A,3,1 - xEAr_C,N,O,H,A,4,2  = 0
 c8397: 0.998 xEAr_C,N,O,H,A,3,2 - xEAr_C,N,O,H,A,4,3  = 0
 c8398: 0.998 xEAr_C,N,O,H,A,4,1 - xEAr_C,N,O,H,A,5,2  = 0
 c8399: 0.998 xEAr_C,N,O,H,A,4,2 - xEAr_C,N,O,H,A,5,3  = 0
 c8400: 0.998 xEAr_C,N,O,H,A,4,3 - xEAr_C,N,O,H,A,5,4  = 0
 c8401: 0.998 xEAr_C,N,O,H,L,2,1 - xEAr_C,N,O,H,L,3,2  = 0
 c8402: 0.998 xEAr_C,N,O,H,L,3,1 - xEAr_C,N,O,H,L,4,2  = 0
 c8403: 0.998 xEAr_C,N,O,H,L,3,2 - xEAr_C,N,O,H,L,4,3  = 0
 c8404: 0.998 xEAr_C,N,O,H,L,4,1 - xEAr_C,N,O,H,L,5,2  = 0
 c8405: 0.998 xEAr_C,N,O,H,L,4,2 - xEAr_C,N,O,H,L,5,3  = 0
 c8406: 0.998 xEAr_C,N,O,H,L,4,3 - xEAr_C,N,O,H,L,5,4  = 0
 c8407: xEAr_C,N,O,H,N,2,1 - xEAr_C,N,O,H,N,3,2  = 0
 c8408: xEAr_C,N,O,H,N,3,1 - xEAr_C,N,O,H,N,4,2  = 0
 c8409: xEAr_C,N,O,H,N,3,2 - xEAr_C,N,O,H,N,4,3  = 0
 c8410: xEAr_C,N,O,H,N,4,1 - xEAr_C,N,O,H,N,5,2  = 0
 c8411: xEAr_C,N,O,H,N,4,2 - xEAr_C,N,O,H,N,5,3  = 0
 c8412: xEAr_C,N,O,H,N,4,3 - xEAr_C,N,O,H,N,5,4  = 0
 c8413: 0.998 xEAr_C,N,O,C,P,2,1 - xEAr_C,N,O,C,P,3,2  = 0
 c8414: 0.998 xEAr_C,N,O,C,P,3,1 - xEAr_C,N,O,C,P,4,2  = 0
 c8415: 0.998 xEAr_C,N,O,C,P,3,2 - xEAr_C,N,O,C,P,4,3  = 0
 c8416: 0.998 xEAr_C,N,O,C,P,4,1 - xEAr_C,N,O,C,P,5,2  = 0
 c8417: 0.998 xEAr_C,N,O,C,P,4,2 - xEAr_C,N,O,C,P,5,3  = 0
 c8418: 0.998 xEAr_C,N,O,C,P,4,3 - xEAr_C,N,O,C,P,5,4  = 0
 c8419: 0.998 xEAr_C,N,O,C,D,2,1 - xEAr_C,N,O,C,D,3,2  = 0
 c8420: 0.998 xEAr_C,N,O,C,D,3,1 - xEAr_C,N,O,C,D,4,2  = 0
 c8421: 0.998 xEAr_C,N,O,C,D,3,2 - xEAr_C,N,O,C,D,4,3  = 0
 c8422: 0.998 xEAr_C,N,O,C,D,4,1 - xEAr_C,N,O,C,D,5,2  = 0
 c8423: 0.998 xEAr_C,N,O,C,D,4,2 - xEAr_C,N,O,C,D,5,3  = 0
 c8424: 0.998 xEAr_C,N,O,C,D,4,3 - xEAr_C,N,O,C,D,5,4  = 0
 c8425: 0.77 xEAr_C,N,O,C,W,2,1 - xEAr_C,N,O,C,W,3,2  = 0
 c8426: 0.77 xEAr_C,N,O,C,W,3,1 - xEAr_C,N,O,C,W,4,2  = 0
 c8427: 0.998 xEAr_C,N,O,C,W,3,2 - xEAr_C,N,O,C,W,4,3  = 0
 c8428: 0.77 xEAr_C,N,O,C,W,4,1 - xEAr_C,N,O,C,W,5,2  = 0
 c8429: 0.998 xEAr_C,N,O,C,W,4,2 - xEAr_C,N,O,C,W,5,3  = 0
 c8430: 0.998 xEAr_C,N,O,C,W,4,3 - xEAr_C,N,O,C,W,5,4  = 0
 c8431: 0.998 xEAr_C,N,O,C,C,2,1 - xEAr_C,N,O,C,C,3,2  = 0
 c8432: 0.998 xEAr_C,N,O,C,C,3,1 - xEAr_C,N,O,C,C,4,2  = 0
 c8433: 0.998 xEAr_C,N,O,C,C,3,2 - xEAr_C,N,O,C,C,4,3  = 0
 c8434: 0.998 xEAr_C,N,O,C,C,4,1 - xEAr_C,N,O,C,C,5,2  = 0
 c8435: 0.998 xEAr_C,N,O,C,C,4,2 - xEAr_C,N,O,C,C,5,3  = 0
 c8436: 0.998 xEAr_C,N,O,C,C,4,3 - xEAr_C,N,O,C,C,5,4  = 0
 c8437: 0.998 xEAr_C,N,O,C,I,2,1 - xEAr_C,N,O,C,I,3,2  = 0
 c8438: 0.998 xEAr_C,N,O,C,I,3,1 - xEAr_C,N,O,C,I,4,2  = 0
 c8439: 0.998 xEAr_C,N,O,C,I,3,2 - xEAr_C,N,O,C,I,4,3  = 0
 c8440: 0.998 xEAr_C,N,O,C,I,4,1 - xEAr_C,N,O,C,I,5,2  = 0
 c8441: 0.998 xEAr_C,N,O,C,I,4,2 - xEAr_C,N,O,C,I,5,3  = 0
 c8442: 0.998 xEAr_C,N,O,C,I,4,3 - xEAr_C,N,O,C,I,5,4  = 0
 c8443: 0.998 xEAr_C,N,O,C,A,2,1 - xEAr_C,N,O,C,A,3,2  = 0
 c8444: 0.998 xEAr_C,N,O,C,A,3,1 - xEAr_C,N,O,C,A,4,2  = 0
 c8445: 0.998 xEAr_C,N,O,C,A,3,2 - xEAr_C,N,O,C,A,4,3  = 0
 c8446: 0.998 xEAr_C,N,O,C,A,4,1 - xEAr_C,N,O,C,A,5,2  = 0
 c8447: 0.998 xEAr_C,N,O,C,A,4,2 - xEAr_C,N,O,C,A,5,3  = 0
 c8448: 0.998 xEAr_C,N,O,C,A,4,3 - xEAr_C,N,O,C,A,5,4  = 0
 c8449: 0.998 xEAr_C,N,O,C,L,2,1 - xEAr_C,N,O,C,L,3,2  = 0
 c8450: 0.998 xEAr_C,N,O,C,L,3,1 - xEAr_C,N,O,C,L,4,2  = 0
 c8451: 0.998 xEAr_C,N,O,C,L,3,2 - xEAr_C,N,O,C,L,4,3  = 0
 c8452: 0.998 xEAr_C,N,O,C,L,4,1 - xEAr_C,N,O,C,L,5,2  = 0
 c8453: 0.998 xEAr_C,N,O,C,L,4,2 - xEAr_C,N,O,C,L,5,3  = 0
 c8454: 0.998 xEAr_C,N,O,C,L,4,3 - xEAr_C,N,O,C,L,5,4  = 0
 c8455: xEAr_C,N,O,C,N,2,1 - xEAr_C,N,O,C,N,3,2  = 0
 c8456: xEAr_C,N,O,C,N,3,1 - xEAr_C,N,O,C,N,4,2  = 0
 c8457: xEAr_C,N,O,C,N,3,2 - xEAr_C,N,O,C,N,4,3  = 0
 c8458: xEAr_C,N,O,C,N,4,1 - xEAr_C,N,O,C,N,5,2  = 0
 c8459: xEAr_C,N,O,C,N,4,2 - xEAr_C,N,O,C,N,5,3  = 0
 c8460: xEAr_C,N,O,C,N,4,3 - xEAr_C,N,O,C,N,5,4  = 0
 c8461: 0.998 xEAr_C,N,O,R,P,2,1 - xEAr_C,N,O,R,P,3,2  = 0
 c8462: 0.998 xEAr_C,N,O,R,P,3,1 - xEAr_C,N,O,R,P,4,2  = 0
 c8463: 0.998 xEAr_C,N,O,R,P,3,2 - xEAr_C,N,O,R,P,4,3  = 0
 c8464: 0.998 xEAr_C,N,O,R,P,4,1 - xEAr_C,N,O,R,P,5,2  = 0
 c8465: 0.998 xEAr_C,N,O,R,P,4,2 - xEAr_C,N,O,R,P,5,3  = 0
 c8466: 0.998 xEAr_C,N,O,R,P,4,3 - xEAr_C,N,O,R,P,5,4  = 0
 c8467: 0.998 xEAr_C,N,O,R,D,2,1 - xEAr_C,N,O,R,D,3,2  = 0
 c8468: 0.998 xEAr_C,N,O,R,D,3,1 - xEAr_C,N,O,R,D,4,2  = 0
 c8469: 0.998 xEAr_C,N,O,R,D,3,2 - xEAr_C,N,O,R,D,4,3  = 0
 c8470: 0.998 xEAr_C,N,O,R,D,4,1 - xEAr_C,N,O,R,D,5,2  = 0
 c8471: 0.998 xEAr_C,N,O,R,D,4,2 - xEAr_C,N,O,R,D,5,3  = 0
 c8472: 0.998 xEAr_C,N,O,R,D,4,3 - xEAr_C,N,O,R,D,5,4  = 0
 c8473: 0.77 xEAr_C,N,O,R,W,2,1 - xEAr_C,N,O,R,W,3,2  = 0
 c8474: 0.77 xEAr_C,N,O,R,W,3,1 - xEAr_C,N,O,R,W,4,2  = 0
 c8475: 0.998 xEAr_C,N,O,R,W,3,2 - xEAr_C,N,O,R,W,4,3  = 0
 c8476: 0.77 xEAr_C,N,O,R,W,4,1 - xEAr_C,N,O,R,W,5,2  = 0
 c8477: 0.998 xEAr_C,N,O,R,W,4,2 - xEAr_C,N,O,R,W,5,3  = 0
 c8478: 0.998 xEAr_C,N,O,R,W,4,3 - xEAr_C,N,O,R,W,5,4  = 0
 c8479: 0.998 xEAr_C,N,O,R,C,2,1 - xEAr_C,N,O,R,C,3,2  = 0
 c8480: 0.998 xEAr_C,N,O,R,C,3,1 - xEAr_C,N,O,R,C,4,2  = 0
 c8481: 0.998 xEAr_C,N,O,R,C,3,2 - xEAr_C,N,O,R,C,4,3  = 0
 c8482: 0.998 xEAr_C,N,O,R,C,4,1 - xEAr_C,N,O,R,C,5,2  = 0
 c8483: 0.998 xEAr_C,N,O,R,C,4,2 - xEAr_C,N,O,R,C,5,3  = 0
 c8484: 0.998 xEAr_C,N,O,R,C,4,3 - xEAr_C,N,O,R,C,5,4  = 0
 c8485: 0.998 xEAr_C,N,O,R,I,2,1 - xEAr_C,N,O,R,I,3,2  = 0
 c8486: 0.998 xEAr_C,N,O,R,I,3,1 - xEAr_C,N,O,R,I,4,2  = 0
 c8487: 0.998 xEAr_C,N,O,R,I,3,2 - xEAr_C,N,O,R,I,4,3  = 0
 c8488: 0.998 xEAr_C,N,O,R,I,4,1 - xEAr_C,N,O,R,I,5,2  = 0
 c8489: 0.998 xEAr_C,N,O,R,I,4,2 - xEAr_C,N,O,R,I,5,3  = 0
 c8490: 0.998 xEAr_C,N,O,R,I,4,3 - xEAr_C,N,O,R,I,5,4  = 0
 c8491: 0.998 xEAr_C,N,O,R,A,2,1 - xEAr_C,N,O,R,A,3,2  = 0
 c8492: 0.998 xEAr_C,N,O,R,A,3,1 - xEAr_C,N,O,R,A,4,2  = 0
 c8493: 0.998 xEAr_C,N,O,R,A,3,2 - xEAr_C,N,O,R,A,4,3  = 0
 c8494: 0.998 xEAr_C,N,O,R,A,4,1 - xEAr_C,N,O,R,A,5,2  = 0
 c8495: 0.998 xEAr_C,N,O,R,A,4,2 - xEAr_C,N,O,R,A,5,3  = 0
 c8496: 0.998 xEAr_C,N,O,R,A,4,3 - xEAr_C,N,O,R,A,5,4  = 0
 c8497: 0.998 xEAr_C,N,O,R,L,2,1 - xEAr_C,N,O,R,L,3,2  = 0
 c8498: 0.998 xEAr_C,N,O,R,L,3,1 - xEAr_C,N,O,R,L,4,2  = 0
 c8499: 0.998 xEAr_C,N,O,R,L,3,2 - xEAr_C,N,O,R,L,4,3  = 0
 c8500: 0.998 xEAr_C,N,O,R,L,4,1 - xEAr_C,N,O,R,L,5,2  = 0
 c8501: 0.998 xEAr_C,N,O,R,L,4,2 - xEAr_C,N,O,R,L,5,3  = 0
 c8502: 0.998 xEAr_C,N,O,R,L,4,3 - xEAr_C,N,O,R,L,5,4  = 0
 c8503: xEAr_C,N,O,R,N,2,1 - xEAr_C,N,O,R,N,3,2  = 0
 c8504: xEAr_C,N,O,R,N,3,1 - xEAr_C,N,O,R,N,4,2  = 0
 c8505: xEAr_C,N,O,R,N,3,2 - xEAr_C,N,O,R,N,4,3  = 0
 c8506: xEAr_C,N,O,R,N,4,1 - xEAr_C,N,O,R,N,5,2  = 0
 c8507: xEAr_C,N,O,R,N,4,2 - xEAr_C,N,O,R,N,5,3  = 0
 c8508: xEAr_C,N,O,R,N,4,3 - xEAr_C,N,O,R,N,5,4  = 0
 c8509: - y_0 + xNG_B,R,N,L,A,L,1 + xNG_B,R,N,C,A,L,1 + xNG_B,R,N,R,A,L,1
        + xNG_B,R,P,L,A,L,1 + xNG_B,R,P,C,A,L,1 + xNG_B,R,P,R,A,L,1
        + xNG_B,N,N,L,A,L,1 + xNG_B,N,N,C,A,L,1 + xNG_B,N,P,R,A,L,1
        + xNG_C,N,N,L,A,L,1 + xNG_C,N,N,H,A,L,1 + xNG_C,N,N,C,A,L,1
        + xNG_C,N,N,R,A,L,1 + xNG_C,N,O,L,A,L,1 + xNG_C,N,O,H,A,L,1
        + xNG_C,N,O,C,A,L,1 + xNG_C,N,O,R,A,L,1  = 0
 c8510: - y_1 + xPB_B,R,N,L,A,L,0,1 + xPB_B,R,N,L,A,L,1,1 + xPB_B,R,N,L,A,L,2,1
        + xPB_B,R,N,L,A,L,3,1 + xPB_B,R,N,L,A,L,4,1 + xPB_B,R,N,C,A,L,0,1
        + xPB_B,R,N,C,A,L,1,1 + xPB_B,R,N,C,A,L,2,1 + xPB_B,R,N,C,A,L,3,1
        + xPB_B,R,N,C,A,L,4,1 + xPB_B,R,N,R,A,L,0,1 + xPB_B,R,N,R,A,L,1,1
        + xPB_B,R,N,R,A,L,2,1 + xPB_B,R,N,R,A,L,3,1 + xPB_B,R,N,R,A,L,4,1
        + xPB_B,R,P,L,A,L,0,1 + xPB_B,R,P,L,A,L,1,1 + xPB_B,R,P,L,A,L,2,1
        + xPB_B,R,P,L,A,L,3,1 + xPB_B,R,P,L,A,L,4,1 + xPB_B,R,P,C,A,L,0,1
        + xPB_B,R,P,C,A,L,1,1 + xPB_B,R,P,C,A,L,2,1 + xPB_B,R,P,C,A,L,3,1
        + xPB_B,R,P,C,A,L,4,1 + xPB_B,R,P,R,A,L,0,1 + xPB_B,R,P,R,A,L,1,1
        + xPB_B,R,P,R,A,L,2,1 + xPB_B,R,P,R,A,L,3,1 + xPB_B,R,P,R,A,L,4,1
        + xPB_B,N,N,L,A,L,0,1 + xPB_B,N,N,L,A,L,1,1 + xPB_B,N,N,L,A,L,2,1
        + xPB_B,N,N,L,A,L,3,1 + xPB_B,N,N,L,A,L,4,1 + xPB_B,N,N,C,A,L,0,1
        + xPB_B,N,N,C,A,L,1,1 + xPB_B,N,N,C,A,L,2,1 + xPB_B,N,N,C,A,L,3,1
        + xPB_B,N,N,C,A,L,4,1 + xPB_B,N,P,R,A,L,0,1 + xPB_B,N,P,R,A,L,1,1
        + xPB_B,N,P,R,A,L,2,1 + xPB_B,N,P,R,A,L,3,1 + xPB_B,N,P,R,A,L,4,1
        + xPB_C,N,N,L,A,L,0,1 + xPB_C,N,N,L,A,L,1,1 + xPB_C,N,N,L,A,L,2,1
        + xPB_C,N,N,L,A,L,3,1 + xPB_C,N,N,L,A,L,4,1 + xPB_C,N,N,H,A,L,0,1
        + xPB_C,N,N,H,A,L,1,1 + xPB_C,N,N,H,A,L,2,1 + xPB_C,N,N,H,A,L,3,1
        + xPB_C,N,N,H,A,L,4,1 + xPB_C,N,N,C,A,L,0,1 + xPB_C,N,N,C,A,L,1,1
        + xPB_C,N,N,C,A,L,2,1 + xPB_C,N,N,C,A,L,3,1 + xPB_C,N,N,C,A,L,4,1
        + xPB_C,N,N,R,A,L,0,1 + xPB_C,N,N,R,A,L,1,1 + xPB_C,N,N,R,A,L,2,1
        + xPB_C,N,N,R,A,L,3,1 + xPB_C,N,N,R,A,L,4,1 + xPB_C,N,O,L,A,L,0,1
        + xPB_C,N,O,L,A,L,1,1 + xPB_C,N,O,L,A,L,2,1 + xPB_C,N,O,L,A,L,3,1
        + xPB_C,N,O,L,A,L,4,1 + xPB_C,N,O,H,A,L,0,1 + xPB_C,N,O,H,A,L,1,1
        + xPB_C,N,O,H,A,L,2,1 + xPB_C,N,O,H,A,L,3,1 + xPB_C,N,O,H,A,L,4,1
        + xPB_C,N,O,C,A,L,0,1 + xPB_C,N,O,C,A,L,1,1 + xPB_C,N,O,C,A,L,2,1
        + xPB_C,N,O,C,A,L,3,1 + xPB_C,N,O,C,A,L,4,1 + xPB_C,N,O,R,A,L,0,1
        + xPB_C,N,O,R,A,L,1,1 + xPB_C,N,O,R,A,L,2,1 + xPB_C,N,O,R,A,L,3,1
        + xPB_C,N,O,R,A,L,4,1  = 0
 c8511: - y_2 + xGS_B,R,N,L,A,L,0,1 + xGS_B,R,N,L,A,L,1,1 + xGS_B,R,N,L,A,L,2,1
        + xGS_B,R,N,L,A,L,3,1 + xGS_B,R,N,L,A,L,4,1 + xGS_B,R,N,C,A,L,0,1
        + xGS_B,R,N,C,A,L,1,1 + xGS_B,R,N,C,A,L,2,1 + xGS_B,R,N,C,A,L,3,1
        + xGS_B,R,N,C,A,L,4,1 + xGS_B,R,N,R,A,L,0,1 + xGS_B,R,N,R,A,L,1,1
        + xGS_B,R,N,R,A,L,2,1 + xGS_B,R,N,R,A,L,3,1 + xGS_B,R,N,R,A,L,4,1
        + xGS_B,R,P,L,A,L,0,1 + xGS_B,R,P,L,A,L,1,1 + xGS_B,R,P,L,A,L,2,1
        + xGS_B,R,P,L,A,L,3,1 + xGS_B,R,P,L,A,L,4,1 + xGS_B,R,P,C,A,L,0,1
        + xGS_B,R,P,C,A,L,1,1 + xGS_B,R,P,C,A,L,2,1 + xGS_B,R,P,C,A,L,3,1
        + xGS_B,R,P,C,A,L,4,1 + xGS_B,R,P,R,A,L,0,1 + xGS_B,R,P,R,A,L,1,1
        + xGS_B,R,P,R,A,L,2,1 + xGS_B,R,P,R,A,L,3,1 + xGS_B,R,P,R,A,L,4,1
        + xGS_B,N,N,L,A,L,0,1 + xGS_B,N,N,L,A,L,1,1 + xGS_B,N,N,L,A,L,2,1
        + xGS_B,N,N,L,A,L,3,1 + xGS_B,N,N,L,A,L,4,1 + xGS_B,N,N,C,A,L,0,1
        + xGS_B,N,N,C,A,L,1,1 + xGS_B,N,N,C,A,L,2,1 + xGS_B,N,N,C,A,L,3,1
        + xGS_B,N,N,C,A,L,4,1 + xGS_B,N,P,R,A,L,0,1 + xGS_B,N,P,R,A,L,1,1
        + xGS_B,N,P,R,A,L,2,1 + xGS_B,N,P,R,A,L,3,1 + xGS_B,N,P,R,A,L,4,1
        + xGS_C,N,N,L,A,L,0,1 + xGS_C,N,N,L,A,L,1,1 + xGS_C,N,N,L,A,L,2,1
        + xGS_C,N,N,L,A,L,3,1 + xGS_C,N,N,L,A,L,4,1 + xGS_C,N,N,H,A,L,0,1
        + xGS_C,N,N,H,A,L,1,1 + xGS_C,N,N,H,A,L,2,1 + xGS_C,N,N,H,A,L,3,1
        + xGS_C,N,N,H,A,L,4,1 + xGS_C,N,N,C,A,L,0,1 + xGS_C,N,N,C,A,L,1,1
        + xGS_C,N,N,C,A,L,2,1 + xGS_C,N,N,C,A,L,3,1 + xGS_C,N,N,C,A,L,4,1
        + xGS_C,N,N,R,A,L,0,1 + xGS_C,N,N,R,A,L,1,1 + xGS_C,N,N,R,A,L,2,1
        + xGS_C,N,N,R,A,L,3,1 + xGS_C,N,N,R,A,L,4,1 + xGS_C,N,O,L,A,L,0,1
        + xGS_C,N,O,L,A,L,1,1 + xGS_C,N,O,L,A,L,2,1 + xGS_C,N,O,L,A,L,3,1
        + xGS_C,N,O,L,A,L,4,1 + xGS_C,N,O,H,A,L,0,1 + xGS_C,N,O,H,A,L,1,1
        + xGS_C,N,O,H,A,L,2,1 + xGS_C,N,O,H,A,L,3,1 + xGS_C,N,O,H,A,L,4,1
        + xGS_C,N,O,C,A,L,0,1 + xGS_C,N,O,C,A,L,1,1 + xGS_C,N,O,C,A,L,2,1
        + xGS_C,N,O,C,A,L,3,1 + xGS_C,N,O,C,A,L,4,1 + xGS_C,N,O,R,A,L,0,1
        + xGS_C,N,O,R,A,L,1,1 + xGS_C,N,O,R,A,L,2,1 + xGS_C,N,O,R,A,L,3,1
        + xGS_C,N,O,R,A,L,4,1  = 0
 c8512: - y_3  = 0
Bounds
 0 <= l_0 <= 500000
 0 <= l_1 <= 500000
 0 <= l_2 <= 500000
 0 <= l_3 <= 10000000
 0 <= Rgc5 <= 500000
 0 <= Rgc6 <= 500000
 0 <= Rgc7 <= 500000
End
