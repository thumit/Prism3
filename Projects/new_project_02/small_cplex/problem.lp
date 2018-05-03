\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj:
Subject To
 c1:   x_A,N,C,B,D,G,0 + x_A,N,C,B,D,G,1 + x_A,N,C,B,D,G,2 + x_A,N,C,B,D,G,3
       + x_A,N,C,B,D,G,4 + x_A,N,C,B,D,G,5  = 20
 c2:   x_A,N,C,B,D,I,0 + x_A,N,C,B,D,I,1 + x_A,N,C,B,D,I,2 + x_A,N,C,B,D,I,3
       + x_A,N,C,B,D,I,4 + x_A,N,C,B,D,I,5  = 191
 c3:   x_A,N,C,B,D,G,0 - xNG_E_A,N,C,B,D,G,0,1  = 0
 c4:   x_A,N,C,B,D,I,0 - xNG_E_A,N,C,B,D,I,0,1  = 0
 c5:   xNG_E_A,N,C,B,D,G,0,1 - xNG_E_A,N,C,B,D,G,0,2  = 0
 c6:   xNG_E_A,N,C,B,D,G,0,2 - xNG_E_A,N,C,B,D,G,0,3  = 0
 c7:   xNG_E_A,N,C,B,D,G,0,3 - xNG_E_A,N,C,B,D,G,0,4  = 0
 c8:   xNG_E_A,N,C,B,D,G,0,4 - xNG_E_A,N,C,B,D,G,0,5  = 0
 c9:   xNG_E_A,N,C,B,D,I,0,1 - xNG_E_A,N,C,B,D,I,0,2  = 0
 c10:  xNG_E_A,N,C,B,D,I,0,2 - xNG_E_A,N,C,B,D,I,0,3  = 0
 c11:  xNG_E_A,N,C,B,D,I,0,3 - xNG_E_A,N,C,B,D,I,0,4  = 0
 c12:  xNG_E_A,N,C,B,D,I,0,4 - xNG_E_A,N,C,B,D,I,0,5  = 0
 c13:  x_A,N,C,B,D,G,1 - xPB_E_A,N,C,B,D,G,0,1 - xPB_E_A,N,C,B,D,G,1,1
       - xPB_E_A,N,C,B,D,G,2,1  = 0
 c14:  x_A,N,C,B,D,I,1 - xPB_E_A,N,C,B,D,I,0,1 - xPB_E_A,N,C,B,D,I,1,1
       - xPB_E_A,N,C,B,D,I,2,1  = 0
 c15:  xPB_E_A,N,C,B,D,G,0,1 - xPB_E_A,N,C,B,D,G,0,2  = 0
 c16:  xPB_E_A,N,C,B,D,G,0,2 - xPB_E_A,N,C,B,D,G,0,3  = 0
 c17:  xPB_E_A,N,C,B,D,G,0,3 - xPB_E_A,N,C,B,D,G,0,4  = 0
 c18:  xPB_E_A,N,C,B,D,G,0,4 - xPB_E_A,N,C,B,D,G,0,5  = 0
 c19:  xPB_E_A,N,C,B,D,G,1,1 - xPB_E_A,N,C,B,D,G,1,2  = 0
 c20:  xPB_E_A,N,C,B,D,G,1,2 - xPB_E_A,N,C,B,D,G,1,3  = 0
 c21:  xPB_E_A,N,C,B,D,G,1,3 - xPB_E_A,N,C,B,D,G,1,4  = 0
 c22:  xPB_E_A,N,C,B,D,G,1,4 - xPB_E_A,N,C,B,D,G,1,5  = 0
 c23:  xPB_E_A,N,C,B,D,G,2,1 - xPB_E_A,N,C,B,D,G,2,2  = 0
 c24:  xPB_E_A,N,C,B,D,G,2,2 - xPB_E_A,N,C,B,D,G,2,3  = 0
 c25:  xPB_E_A,N,C,B,D,G,2,3 - xPB_E_A,N,C,B,D,G,2,4  = 0
 c26:  xPB_E_A,N,C,B,D,G,2,4 - xPB_E_A,N,C,B,D,G,2,5  = 0
 c27:  xPB_E_A,N,C,B,D,I,0,1 - xPB_E_A,N,C,B,D,I,0,2  = 0
 c28:  xPB_E_A,N,C,B,D,I,0,2 - xPB_E_A,N,C,B,D,I,0,3  = 0
 c29:  xPB_E_A,N,C,B,D,I,0,3 - xPB_E_A,N,C,B,D,I,0,4  = 0
 c30:  xPB_E_A,N,C,B,D,I,0,4 - xPB_E_A,N,C,B,D,I,0,5  = 0
 c31:  xPB_E_A,N,C,B,D,I,1,1 - xPB_E_A,N,C,B,D,I,1,2  = 0
 c32:  xPB_E_A,N,C,B,D,I,1,2 - xPB_E_A,N,C,B,D,I,1,3  = 0
 c33:  xPB_E_A,N,C,B,D,I,1,3 - xPB_E_A,N,C,B,D,I,1,4  = 0
 c34:  xPB_E_A,N,C,B,D,I,1,4 - xPB_E_A,N,C,B,D,I,1,5  = 0
 c35:  xPB_E_A,N,C,B,D,I,2,1 - xPB_E_A,N,C,B,D,I,2,2  = 0
 c36:  xPB_E_A,N,C,B,D,I,2,2 - xPB_E_A,N,C,B,D,I,2,3  = 0
 c37:  xPB_E_A,N,C,B,D,I,2,3 - xPB_E_A,N,C,B,D,I,2,4  = 0
 c38:  xPB_E_A,N,C,B,D,I,2,4 - xPB_E_A,N,C,B,D,I,2,5  = 0
 c39:  x_A,N,C,B,D,G,2 - xGS_E_A,N,C,B,D,G,0,1  = 0
 c40:  x_A,N,C,B,D,I,2 - xGS_E_A,N,C,B,D,I,0,1 - xGS_E_A,N,C,B,D,I,1,1
       - xGS_E_A,N,C,B,D,I,2,1 - xGS_E_A,N,C,B,D,I,3,1  = 0
 c41:  xGS_E_A,N,C,B,D,G,0,1 - xGS_E_A,N,C,B,D,G,0,2  = 0
 c42:  xGS_E_A,N,C,B,D,G,0,2 - xGS_E_A,N,C,B,D,G,0,3  = 0
 c43:  xGS_E_A,N,C,B,D,G,0,3 - xGS_E_A,N,C,B,D,G,0,4  = 0
 c44:  xGS_E_A,N,C,B,D,G,0,4 - xGS_E_A,N,C,B,D,G,0,5  = 0
 c45:  xGS_E_A,N,C,B,D,I,0,1 - xGS_E_A,N,C,B,D,I,0,2  = 0
 c46:  xGS_E_A,N,C,B,D,I,0,2 - xGS_E_A,N,C,B,D,I,0,3  = 0
 c47:  xGS_E_A,N,C,B,D,I,0,3 - xGS_E_A,N,C,B,D,I,0,4  = 0
 c48:  xGS_E_A,N,C,B,D,I,0,4 - xGS_E_A,N,C,B,D,I,0,5  = 0
 c49:  xGS_E_A,N,C,B,D,I,1,1 - xGS_E_A,N,C,B,D,I,1,2  = 0
 c50:  xGS_E_A,N,C,B,D,I,1,2 - xGS_E_A,N,C,B,D,I,1,3  = 0
 c51:  xGS_E_A,N,C,B,D,I,1,3 - xGS_E_A,N,C,B,D,I,1,4  = 0
 c52:  xGS_E_A,N,C,B,D,I,1,4 - xGS_E_A,N,C,B,D,I,1,5  = 0
 c53:  xGS_E_A,N,C,B,D,I,2,1 - xGS_E_A,N,C,B,D,I,2,2  = 0
 c54:  xGS_E_A,N,C,B,D,I,2,2 - xGS_E_A,N,C,B,D,I,2,3  = 0
 c55:  xGS_E_A,N,C,B,D,I,2,3 - xGS_E_A,N,C,B,D,I,2,4  = 0
 c56:  xGS_E_A,N,C,B,D,I,2,4 - xGS_E_A,N,C,B,D,I,2,5  = 0
 c57:  xGS_E_A,N,C,B,D,I,3,1 - xGS_E_A,N,C,B,D,I,3,2  = 0
 c58:  xGS_E_A,N,C,B,D,I,3,2 - xGS_E_A,N,C,B,D,I,3,3  = 0
 c59:  xGS_E_A,N,C,B,D,I,3,3 - xGS_E_A,N,C,B,D,I,3,4  = 0
 c60:  xGS_E_A,N,C,B,D,I,3,4 - xGS_E_A,N,C,B,D,I,3,5  = 0
 c61:  x_A,N,C,B,D,G,4 - xMS_E_A,N,C,B,D,G,0,1 - xMS_E_A,N,C,B,D,G,1,1
       - xMS_E_A,N,C,B,D,G,2,1 - xMS_E_A,N,C,B,D,G,3,1 - xMS_E_A,N,C,B,D,G,4,1
        = 0
 c62:  x_A,N,C,B,D,I,4 - xMS_E_A,N,C,B,D,I,0,1 - xMS_E_A,N,C,B,D,I,1,1
       - xMS_E_A,N,C,B,D,I,2,1 - xMS_E_A,N,C,B,D,I,3,1 - xMS_E_A,N,C,B,D,I,4,1
        = 0
 c63:  xMS_E_A,N,C,B,D,G,0,1 - xMS_E_A,N,C,B,D,G,0,2  = 0
 c64:  xMS_E_A,N,C,B,D,G,0,2 - xMS_E_A,N,C,B,D,G,0,3  = 0
 c65:  xMS_E_A,N,C,B,D,G,0,3 - xMS_E_A,N,C,B,D,G,0,4  = 0
 c66:  xMS_E_A,N,C,B,D,G,0,4 - xMS_E_A,N,C,B,D,G,0,5  = 0
 c67:  xMS_E_A,N,C,B,D,G,1,1 - xMS_E_A,N,C,B,D,G,1,2  = 0
 c68:  xMS_E_A,N,C,B,D,G,1,2 - xMS_E_A,N,C,B,D,G,1,3  = 0
 c69:  xMS_E_A,N,C,B,D,G,1,3 - xMS_E_A,N,C,B,D,G,1,4  = 0
 c70:  xMS_E_A,N,C,B,D,G,1,4 - xMS_E_A,N,C,B,D,G,1,5  = 0
 c71:  xMS_E_A,N,C,B,D,G,2,1 - xMS_E_A,N,C,B,D,G,2,2  = 0
 c72:  xMS_E_A,N,C,B,D,G,2,2 - xMS_E_A,N,C,B,D,G,2,3  = 0
 c73:  xMS_E_A,N,C,B,D,G,2,3 - xMS_E_A,N,C,B,D,G,2,4  = 0
 c74:  xMS_E_A,N,C,B,D,G,2,4 - xMS_E_A,N,C,B,D,G,2,5  = 0
 c75:  xMS_E_A,N,C,B,D,G,3,1 - xMS_E_A,N,C,B,D,G,3,2  = 0
 c76:  xMS_E_A,N,C,B,D,G,3,2 - xMS_E_A,N,C,B,D,G,3,3  = 0
 c77:  xMS_E_A,N,C,B,D,G,3,3 - xMS_E_A,N,C,B,D,G,3,4  = 0
 c78:  xMS_E_A,N,C,B,D,G,3,4 - xMS_E_A,N,C,B,D,G,3,5  = 0
 c79:  xMS_E_A,N,C,B,D,G,4,1 - xMS_E_A,N,C,B,D,G,4,2  = 0
 c80:  xMS_E_A,N,C,B,D,G,4,2 - xMS_E_A,N,C,B,D,G,4,3  = 0
 c81:  xMS_E_A,N,C,B,D,G,4,3 - xMS_E_A,N,C,B,D,G,4,4  = 0
 c82:  xMS_E_A,N,C,B,D,G,4,4 - xMS_E_A,N,C,B,D,G,4,5  = 0
 c83:  xMS_E_A,N,C,B,D,I,0,1 - xMS_E_A,N,C,B,D,I,0,2  = 0
 c84:  xMS_E_A,N,C,B,D,I,0,2 - xMS_E_A,N,C,B,D,I,0,3  = 0
 c85:  xMS_E_A,N,C,B,D,I,0,3 - xMS_E_A,N,C,B,D,I,0,4  = 0
 c86:  xMS_E_A,N,C,B,D,I,0,4 - xMS_E_A,N,C,B,D,I,0,5  = 0
 c87:  xMS_E_A,N,C,B,D,I,1,1 - xMS_E_A,N,C,B,D,I,1,2  = 0
 c88:  xMS_E_A,N,C,B,D,I,1,2 - xMS_E_A,N,C,B,D,I,1,3  = 0
 c89:  xMS_E_A,N,C,B,D,I,1,3 - xMS_E_A,N,C,B,D,I,1,4  = 0
 c90:  xMS_E_A,N,C,B,D,I,1,4 - xMS_E_A,N,C,B,D,I,1,5  = 0
 c91:  xMS_E_A,N,C,B,D,I,2,1 - xMS_E_A,N,C,B,D,I,2,2  = 0
 c92:  xMS_E_A,N,C,B,D,I,2,2 - xMS_E_A,N,C,B,D,I,2,3  = 0
 c93:  xMS_E_A,N,C,B,D,I,2,3 - xMS_E_A,N,C,B,D,I,2,4  = 0
 c94:  xMS_E_A,N,C,B,D,I,2,4 - xMS_E_A,N,C,B,D,I,2,5  = 0
 c95:  xMS_E_A,N,C,B,D,I,3,1 - xMS_E_A,N,C,B,D,I,3,2  = 0
 c96:  xMS_E_A,N,C,B,D,I,3,2 - xMS_E_A,N,C,B,D,I,3,3  = 0
 c97:  xMS_E_A,N,C,B,D,I,3,3 - xMS_E_A,N,C,B,D,I,3,4  = 0
 c98:  xMS_E_A,N,C,B,D,I,3,4 - xMS_E_A,N,C,B,D,I,3,5  = 0
 c99:  xMS_E_A,N,C,B,D,I,4,1 - xMS_E_A,N,C,B,D,I,4,2  = 0
 c100: xMS_E_A,N,C,B,D,I,4,2 - xMS_E_A,N,C,B,D,I,4,3  = 0
 c101: xMS_E_A,N,C,B,D,I,4,3 - xMS_E_A,N,C,B,D,I,4,4  = 0
 c102: xMS_E_A,N,C,B,D,I,4,4 - xMS_E_A,N,C,B,D,I,4,5  = 0
 c103: fire_A,N,C,B,B,1,B  = 0
 c104: fire_A,N,C,B,B,1,C  = 0
 c105: fire_A,N,C,B,B,1,D  = 0
 c106: fire_A,N,C,B,B,1,E  = 0
 c107: fire_A,N,C,B,B,1,F  = 0
 c108: fire_A,N,C,B,B,1,G  = 0
 c109: fire_A,N,C,B,B,1,H  = 0
 c110: fire_A,N,C,B,B,1,I  = 0
 c111: fire_A,N,C,B,B,2,B  = 0
 c112: fire_A,N,C,B,B,2,C  = 0
 c113: fire_A,N,C,B,B,2,D  = 0
 c114: fire_A,N,C,B,B,2,E  = 0
 c115: fire_A,N,C,B,B,2,F  = 0
 c116: fire_A,N,C,B,B,2,G  = 0
 c117: fire_A,N,C,B,B,2,H  = 0
 c118: fire_A,N,C,B,B,2,I  = 0
 c119: fire_A,N,C,B,B,3,B  = 0
 c120: fire_A,N,C,B,B,3,C  = 0
 c121: fire_A,N,C,B,B,3,D  = 0
 c122: fire_A,N,C,B,B,3,E  = 0
 c123: fire_A,N,C,B,B,3,F  = 0
 c124: fire_A,N,C,B,B,3,G  = 0
 c125: fire_A,N,C,B,B,3,H  = 0
 c126: fire_A,N,C,B,B,3,I  = 0
 c127: fire_A,N,C,B,B,4,B  = 0
 c128: fire_A,N,C,B,B,4,C  = 0
 c129: fire_A,N,C,B,B,4,D  = 0
 c130: fire_A,N,C,B,B,4,E  = 0
 c131: fire_A,N,C,B,B,4,F  = 0
 c132: fire_A,N,C,B,B,4,G  = 0
 c133: fire_A,N,C,B,B,4,H  = 0
 c134: fire_A,N,C,B,B,4,I  = 0
 c135: fire_A,N,C,B,B,5,B  = 0
 c136: fire_A,N,C,B,B,5,C  = 0
 c137: fire_A,N,C,B,B,5,D  = 0
 c138: fire_A,N,C,B,B,5,E  = 0
 c139: fire_A,N,C,B,B,5,F  = 0
 c140: fire_A,N,C,B,B,5,G  = 0
 c141: fire_A,N,C,B,B,5,H  = 0
 c142: fire_A,N,C,B,B,5,I  = 0
 c143: fire_A,N,C,B,C,1,B  = 0
 c144: fire_A,N,C,B,C,1,C  = 0
 c145: fire_A,N,C,B,C,1,D  = 0
 c146: fire_A,N,C,B,C,1,E  = 0
 c147: fire_A,N,C,B,C,1,F  = 0
 c148: fire_A,N,C,B,C,1,G  = 0
 c149: fire_A,N,C,B,C,1,H  = 0
 c150: fire_A,N,C,B,C,1,I  = 0
 c151: fire_A,N,C,B,C,2,B  = 0
 c152: fire_A,N,C,B,C,2,C  = 0
 c153: fire_A,N,C,B,C,2,D  = 0
 c154: fire_A,N,C,B,C,2,E  = 0
 c155: fire_A,N,C,B,C,2,F  = 0
 c156: fire_A,N,C,B,C,2,G  = 0
 c157: fire_A,N,C,B,C,2,H  = 0
 c158: fire_A,N,C,B,C,2,I  = 0
 c159: fire_A,N,C,B,C,3,B  = 0
 c160: fire_A,N,C,B,C,3,C  = 0
 c161: fire_A,N,C,B,C,3,D  = 0
 c162: fire_A,N,C,B,C,3,E  = 0
 c163: fire_A,N,C,B,C,3,F  = 0
 c164: fire_A,N,C,B,C,3,G  = 0
 c165: fire_A,N,C,B,C,3,H  = 0
 c166: fire_A,N,C,B,C,3,I  = 0
 c167: fire_A,N,C,B,C,4,B  = 0
 c168: fire_A,N,C,B,C,4,C  = 0
 c169: fire_A,N,C,B,C,4,D  = 0
 c170: fire_A,N,C,B,C,4,E  = 0
 c171: fire_A,N,C,B,C,4,F  = 0
 c172: fire_A,N,C,B,C,4,G  = 0
 c173: fire_A,N,C,B,C,4,H  = 0
 c174: fire_A,N,C,B,C,4,I  = 0
 c175: fire_A,N,C,B,C,5,B  = 0
 c176: fire_A,N,C,B,C,5,C  = 0
 c177: fire_A,N,C,B,C,5,D  = 0
 c178: fire_A,N,C,B,C,5,E  = 0
 c179: fire_A,N,C,B,C,5,F  = 0
 c180: fire_A,N,C,B,C,5,G  = 0
 c181: fire_A,N,C,B,C,5,H  = 0
 c182: fire_A,N,C,B,C,5,I  = 0
 c183: fire_A,N,C,B,D,1,B  = 0
 c184: fire_A,N,C,B,D,1,C  = 0
 c185: fire_A,N,C,B,D,1,D  = 0
 c186: fire_A,N,C,B,D,1,E  = 0
 c187: fire_A,N,C,B,D,1,F  = 0
 c188: fire_A,N,C,B,D,1,G  = 0
 c189: fire_A,N,C,B,D,1,H  = 0
 c190: fire_A,N,C,B,D,1,I  = 0
 c191: fire_A,N,C,B,D,2,B  = 0
 c192: fire_A,N,C,B,D,2,C  = 0
 c193: fire_A,N,C,B,D,2,D  = 0
 c194: fire_A,N,C,B,D,2,E  = 0
 c195: fire_A,N,C,B,D,2,F  = 0
 c196: fire_A,N,C,B,D,2,G  = 0
 c197: fire_A,N,C,B,D,2,H  = 0
 c198: fire_A,N,C,B,D,2,I  = 0
 c199: fire_A,N,C,B,D,3,B  = 0
 c200: fire_A,N,C,B,D,3,C  = 0
 c201: fire_A,N,C,B,D,3,D  = 0
 c202: fire_A,N,C,B,D,3,E  = 0
 c203: fire_A,N,C,B,D,3,F  = 0
 c204: fire_A,N,C,B,D,3,G  = 0
 c205: fire_A,N,C,B,D,3,H  = 0
 c206: fire_A,N,C,B,D,3,I  = 0
 c207: fire_A,N,C,B,D,4,B  = 0
 c208: fire_A,N,C,B,D,4,C  = 0
 c209: fire_A,N,C,B,D,4,D  = 0
 c210: fire_A,N,C,B,D,4,E  = 0
 c211: fire_A,N,C,B,D,4,F  = 0
 c212: fire_A,N,C,B,D,4,G  = 0
 c213: fire_A,N,C,B,D,4,H  = 0
 c214: fire_A,N,C,B,D,4,I  = 0
 c215: fire_A,N,C,B,D,5,B  = 0
 c216: fire_A,N,C,B,D,5,C  = 0
 c217: fire_A,N,C,B,D,5,D  = 0
 c218: fire_A,N,C,B,D,5,E  = 0
 c219: fire_A,N,C,B,D,5,F  = 0
 c220: fire_A,N,C,B,D,5,G  = 0
 c221: fire_A,N,C,B,D,5,H  = 0
 c222: fire_A,N,C,B,D,5,I  = 0
 c223: fire_A,N,C,B,E,1,B  = 0
 c224: fire_A,N,C,B,E,1,C  = 0
 c225: fire_A,N,C,B,E,1,D  = 0
 c226: fire_A,N,C,B,E,1,E  = 0
 c227: fire_A,N,C,B,E,1,F  = 0
 c228: fire_A,N,C,B,E,1,G  = 0
 c229: fire_A,N,C,B,E,1,H  = 0
 c230: fire_A,N,C,B,E,1,I  = 0
 c231: fire_A,N,C,B,E,2,B  = 0
 c232: fire_A,N,C,B,E,2,C  = 0
 c233: fire_A,N,C,B,E,2,D  = 0
 c234: fire_A,N,C,B,E,2,E  = 0
 c235: fire_A,N,C,B,E,2,F  = 0
 c236: fire_A,N,C,B,E,2,G  = 0
 c237: fire_A,N,C,B,E,2,H  = 0
 c238: fire_A,N,C,B,E,2,I  = 0
 c239: fire_A,N,C,B,E,3,B  = 0
 c240: fire_A,N,C,B,E,3,C  = 0
 c241: fire_A,N,C,B,E,3,D  = 0
 c242: fire_A,N,C,B,E,3,E  = 0
 c243: fire_A,N,C,B,E,3,F  = 0
 c244: fire_A,N,C,B,E,3,G  = 0
 c245: fire_A,N,C,B,E,3,H  = 0
 c246: fire_A,N,C,B,E,3,I  = 0
 c247: fire_A,N,C,B,E,4,B  = 0
 c248: fire_A,N,C,B,E,4,C  = 0
 c249: fire_A,N,C,B,E,4,D  = 0
 c250: fire_A,N,C,B,E,4,E  = 0
 c251: fire_A,N,C,B,E,4,F  = 0
 c252: fire_A,N,C,B,E,4,G  = 0
 c253: fire_A,N,C,B,E,4,H  = 0
 c254: fire_A,N,C,B,E,4,I  = 0
 c255: fire_A,N,C,B,E,5,B  = 0
 c256: fire_A,N,C,B,E,5,C  = 0
 c257: fire_A,N,C,B,E,5,D  = 0
 c258: fire_A,N,C,B,E,5,E  = 0
 c259: fire_A,N,C,B,E,5,F  = 0
 c260: fire_A,N,C,B,E,5,G  = 0
 c261: fire_A,N,C,B,E,5,H  = 0
 c262: fire_A,N,C,B,E,5,I  = 0
 c263: fire_A,N,C,B,F,1,B  = 0
 c264: fire_A,N,C,B,F,1,C  = 0
 c265: fire_A,N,C,B,F,1,D  = 0
 c266: fire_A,N,C,B,F,1,E  = 0
 c267: fire_A,N,C,B,F,1,F  = 0
 c268: fire_A,N,C,B,F,1,G  = 0
 c269: fire_A,N,C,B,F,1,H  = 0
 c270: fire_A,N,C,B,F,1,I  = 0
 c271: fire_A,N,C,B,F,2,B  = 0
 c272: fire_A,N,C,B,F,2,C  = 0
 c273: fire_A,N,C,B,F,2,D  = 0
 c274: fire_A,N,C,B,F,2,E  = 0
 c275: fire_A,N,C,B,F,2,F  = 0
 c276: fire_A,N,C,B,F,2,G  = 0
 c277: fire_A,N,C,B,F,2,H  = 0
 c278: fire_A,N,C,B,F,2,I  = 0
 c279: fire_A,N,C,B,F,3,B  = 0
 c280: fire_A,N,C,B,F,3,C  = 0
 c281: fire_A,N,C,B,F,3,D  = 0
 c282: fire_A,N,C,B,F,3,E  = 0
 c283: fire_A,N,C,B,F,3,F  = 0
 c284: fire_A,N,C,B,F,3,G  = 0
 c285: fire_A,N,C,B,F,3,H  = 0
 c286: fire_A,N,C,B,F,3,I  = 0
 c287: fire_A,N,C,B,F,4,B  = 0
 c288: fire_A,N,C,B,F,4,C  = 0
 c289: fire_A,N,C,B,F,4,D  = 0
 c290: fire_A,N,C,B,F,4,E  = 0
 c291: fire_A,N,C,B,F,4,F  = 0
 c292: fire_A,N,C,B,F,4,G  = 0
 c293: fire_A,N,C,B,F,4,H  = 0
 c294: fire_A,N,C,B,F,4,I  = 0
 c295: fire_A,N,C,B,F,5,B  = 0
 c296: fire_A,N,C,B,F,5,C  = 0
 c297: fire_A,N,C,B,F,5,D  = 0
 c298: fire_A,N,C,B,F,5,E  = 0
 c299: fire_A,N,C,B,F,5,F  = 0
 c300: fire_A,N,C,B,F,5,G  = 0
 c301: fire_A,N,C,B,F,5,H  = 0
 c302: fire_A,N,C,B,F,5,I  = 0
 c303: fire_A,N,C,B,G,1,B  = 0
 c304: fire_A,N,C,B,G,1,C  = 0
 c305: fire_A,N,C,B,G,1,D  = 0
 c306: fire_A,N,C,B,G,1,E  = 0
 c307: fire_A,N,C,B,G,1,F  = 0
 c308: fire_A,N,C,B,G,1,G  = 0
 c309: fire_A,N,C,B,G,1,H  = 0
 c310: fire_A,N,C,B,G,1,I  = 0
 c311: fire_A,N,C,B,G,2,B  = 0
 c312: fire_A,N,C,B,G,2,C  = 0
 c313: fire_A,N,C,B,G,2,D  = 0
 c314: fire_A,N,C,B,G,2,E  = 0
 c315: fire_A,N,C,B,G,2,F  = 0
 c316: fire_A,N,C,B,G,2,G  = 0
 c317: fire_A,N,C,B,G,2,H  = 0
 c318: fire_A,N,C,B,G,2,I  = 0
 c319: fire_A,N,C,B,G,3,B  = 0
 c320: fire_A,N,C,B,G,3,C  = 0
 c321: fire_A,N,C,B,G,3,D  = 0
 c322: fire_A,N,C,B,G,3,E  = 0
 c323: fire_A,N,C,B,G,3,F  = 0
 c324: fire_A,N,C,B,G,3,G  = 0
 c325: fire_A,N,C,B,G,3,H  = 0
 c326: fire_A,N,C,B,G,3,I  = 0
 c327: fire_A,N,C,B,G,4,B  = 0
 c328: fire_A,N,C,B,G,4,C  = 0
 c329: fire_A,N,C,B,G,4,D  = 0
 c330: fire_A,N,C,B,G,4,E  = 0
 c331: fire_A,N,C,B,G,4,F  = 0
 c332: fire_A,N,C,B,G,4,G  = 0
 c333: fire_A,N,C,B,G,4,H  = 0
 c334: fire_A,N,C,B,G,4,I  = 0
 c335: fire_A,N,C,B,G,5,B  = 0
 c336: fire_A,N,C,B,G,5,C  = 0
 c337: fire_A,N,C,B,G,5,D  = 0
 c338: fire_A,N,C,B,G,5,E  = 0
 c339: fire_A,N,C,B,G,5,F  = 0
 c340: fire_A,N,C,B,G,5,G  = 0
 c341: fire_A,N,C,B,G,5,H  = 0
 c342: fire_A,N,C,B,G,5,I  = 0
 c343: fire_A,N,C,B,H,1,B  = 0
 c344: fire_A,N,C,B,H,1,C  = 0
 c345: fire_A,N,C,B,H,1,D  = 0
 c346: fire_A,N,C,B,H,1,E  = 0
 c347: fire_A,N,C,B,H,1,F  = 0
 c348: fire_A,N,C,B,H,1,G  = 0
 c349: fire_A,N,C,B,H,1,H  = 0
 c350: fire_A,N,C,B,H,1,I  = 0
 c351: fire_A,N,C,B,H,2,B  = 0
 c352: fire_A,N,C,B,H,2,C  = 0
 c353: fire_A,N,C,B,H,2,D  = 0
 c354: fire_A,N,C,B,H,2,E  = 0
 c355: fire_A,N,C,B,H,2,F  = 0
 c356: fire_A,N,C,B,H,2,G  = 0
 c357: fire_A,N,C,B,H,2,H  = 0
 c358: fire_A,N,C,B,H,2,I  = 0
 c359: fire_A,N,C,B,H,3,B  = 0
 c360: fire_A,N,C,B,H,3,C  = 0
 c361: fire_A,N,C,B,H,3,D  = 0
 c362: fire_A,N,C,B,H,3,E  = 0
 c363: fire_A,N,C,B,H,3,F  = 0
 c364: fire_A,N,C,B,H,3,G  = 0
 c365: fire_A,N,C,B,H,3,H  = 0
 c366: fire_A,N,C,B,H,3,I  = 0
 c367: fire_A,N,C,B,H,4,B  = 0
 c368: fire_A,N,C,B,H,4,C  = 0
 c369: fire_A,N,C,B,H,4,D  = 0
 c370: fire_A,N,C,B,H,4,E  = 0
 c371: fire_A,N,C,B,H,4,F  = 0
 c372: fire_A,N,C,B,H,4,G  = 0
 c373: fire_A,N,C,B,H,4,H  = 0
 c374: fire_A,N,C,B,H,4,I  = 0
 c375: fire_A,N,C,B,H,5,B  = 0
 c376: fire_A,N,C,B,H,5,C  = 0
 c377: fire_A,N,C,B,H,5,D  = 0
 c378: fire_A,N,C,B,H,5,E  = 0
 c379: fire_A,N,C,B,H,5,F  = 0
 c380: fire_A,N,C,B,H,5,G  = 0
 c381: fire_A,N,C,B,H,5,H  = 0
 c382: fire_A,N,C,B,H,5,I  = 0
 c383: fire_A,N,C,B,I,1,B  = 0
 c384: fire_A,N,C,B,I,1,C  = 0
 c385: fire_A,N,C,B,I,1,D  = 0
 c386: fire_A,N,C,B,I,1,E  = 0
 c387: fire_A,N,C,B,I,1,F  = 0
 c388: fire_A,N,C,B,I,1,G  = 0
 c389: fire_A,N,C,B,I,1,H  = 0
 c390: fire_A,N,C,B,I,1,I  = 0
 c391: fire_A,N,C,B,I,2,B  = 0
 c392: fire_A,N,C,B,I,2,C  = 0
 c393: fire_A,N,C,B,I,2,D  = 0
 c394: fire_A,N,C,B,I,2,E  = 0
 c395: fire_A,N,C,B,I,2,F  = 0
 c396: fire_A,N,C,B,I,2,G  = 0
 c397: fire_A,N,C,B,I,2,H  = 0
 c398: fire_A,N,C,B,I,2,I  = 0
 c399: fire_A,N,C,B,I,3,B  = 0
 c400: fire_A,N,C,B,I,3,C  = 0
 c401: fire_A,N,C,B,I,3,D  = 0
 c402: fire_A,N,C,B,I,3,E  = 0
 c403: fire_A,N,C,B,I,3,F  = 0
 c404: fire_A,N,C,B,I,3,G  = 0
 c405: fire_A,N,C,B,I,3,H  = 0
 c406: fire_A,N,C,B,I,3,I  = 0
 c407: fire_A,N,C,B,I,4,B  = 0
 c408: fire_A,N,C,B,I,4,C  = 0
 c409: fire_A,N,C,B,I,4,D  = 0
 c410: fire_A,N,C,B,I,4,E  = 0
 c411: fire_A,N,C,B,I,4,F  = 0
 c412: fire_A,N,C,B,I,4,G  = 0
 c413: fire_A,N,C,B,I,4,H  = 0
 c414: fire_A,N,C,B,I,4,I  = 0
 c415: fire_A,N,C,B,I,5,B  = 0
 c416: fire_A,N,C,B,I,5,C  = 0
 c417: fire_A,N,C,B,I,5,D  = 0
 c418: fire_A,N,C,B,I,5,E  = 0
 c419: fire_A,N,C,B,I,5,F  = 0
 c420: fire_A,N,C,B,I,5,G  = 0
 c421: fire_A,N,C,B,I,5,H  = 0
 c422: fire_A,N,C,B,I,5,I  = 0
 c423: - xNG_R_A,N,C,B,B,0,2,1 - xPB_R_A,N,C,B,B,0,2,1 - xPB_R_A,N,C,B,B,1,2,1
       - xPB_R_A,N,C,B,B,2,2,1 - xGS_R_A,N,C,B,B,0,2,1 - xGS_R_A,N,C,B,B,1,2,1
       - xGS_R_A,N,C,B,B,2,2,1 - xGS_R_A,N,C,B,B,3,2,1 + fire_A,N,C,B,B,1,B
       + fire_A,N,C,B,C,1,B + fire_A,N,C,B,D,1,B + fire_A,N,C,B,E,1,B
       + fire_A,N,C,B,F,1,B + fire_A,N,C,B,G,1,B + fire_A,N,C,B,H,1,B
       + fire_A,N,C,B,I,1,B  = 0
 c424: - xNG_R_A,N,C,B,C,0,2,1 - xPB_R_A,N,C,B,C,0,2,1 - xPB_R_A,N,C,B,C,1,2,1
       - xPB_R_A,N,C,B,C,2,2,1 - xGS_R_A,N,C,B,C,0,2,1 - xGS_R_A,N,C,B,C,1,2,1
       - xGS_R_A,N,C,B,C,2,2,1 - xGS_R_A,N,C,B,C,3,2,1 + fire_A,N,C,B,B,1,C
       + fire_A,N,C,B,C,1,C + fire_A,N,C,B,D,1,C + fire_A,N,C,B,E,1,C
       + fire_A,N,C,B,F,1,C + fire_A,N,C,B,G,1,C + fire_A,N,C,B,H,1,C
       + fire_A,N,C,B,I,1,C  = 0
 c425: - xNG_R_A,N,C,B,D,0,2,1 - xPB_R_A,N,C,B,D,0,2,1 - xGS_R_A,N,C,B,D,0,2,1
       - xGS_R_A,N,C,B,D,1,2,1 - xGS_R_A,N,C,B,D,2,2,1 - xGS_R_A,N,C,B,D,3,2,1
       + fire_A,N,C,B,B,1,D + fire_A,N,C,B,C,1,D + fire_A,N,C,B,D,1,D
       + fire_A,N,C,B,E,1,D + fire_A,N,C,B,F,1,D + fire_A,N,C,B,G,1,D
       + fire_A,N,C,B,H,1,D + fire_A,N,C,B,I,1,D  = 0
 c426: - xNG_R_A,N,C,B,E,0,2,1 - xPB_R_A,N,C,B,E,0,2,1 - xPB_R_A,N,C,B,E,1,2,1
       - xPB_R_A,N,C,B,E,2,2,1 + fire_A,N,C,B,B,1,E + fire_A,N,C,B,C,1,E
       + fire_A,N,C,B,D,1,E + fire_A,N,C,B,E,1,E + fire_A,N,C,B,F,1,E
       + fire_A,N,C,B,G,1,E + fire_A,N,C,B,H,1,E + fire_A,N,C,B,I,1,E  = 0
 c427: - xNG_R_A,N,C,B,F,0,2,1 - xPB_R_A,N,C,B,F,0,2,1 - xPB_R_A,N,C,B,F,1,2,1
       - xPB_R_A,N,C,B,F,2,2,1 - xGS_R_A,N,C,B,F,0,2,1 - xGS_R_A,N,C,B,F,1,2,1
       - xGS_R_A,N,C,B,F,2,2,1 - xGS_R_A,N,C,B,F,3,2,1 + fire_A,N,C,B,B,1,F
       + fire_A,N,C,B,C,1,F + fire_A,N,C,B,D,1,F + fire_A,N,C,B,E,1,F
       + fire_A,N,C,B,F,1,F + fire_A,N,C,B,G,1,F + fire_A,N,C,B,H,1,F
       + fire_A,N,C,B,I,1,F  = 0
 c428: - xNG_R_A,N,C,B,G,0,2,1 + fire_A,N,C,B,B,1,G + fire_A,N,C,B,C,1,G
       + fire_A,N,C,B,D,1,G + fire_A,N,C,B,E,1,G + fire_A,N,C,B,F,1,G
       + fire_A,N,C,B,G,1,G + fire_A,N,C,B,H,1,G + fire_A,N,C,B,I,1,G  = 0
 c429: - xNG_R_A,N,C,B,H,0,2,1 - xPB_R_A,N,C,B,H,0,2,1 - xPB_R_A,N,C,B,H,1,2,1
       - xPB_R_A,N,C,B,H,2,2,1 - xGS_R_A,N,C,B,H,0,2,1 - xGS_R_A,N,C,B,H,1,2,1
       - xGS_R_A,N,C,B,H,2,2,1 - xGS_R_A,N,C,B,H,3,2,1 + fire_A,N,C,B,B,1,H
       + fire_A,N,C,B,C,1,H + fire_A,N,C,B,D,1,H + fire_A,N,C,B,E,1,H
       + fire_A,N,C,B,F,1,H + fire_A,N,C,B,G,1,H + fire_A,N,C,B,H,1,H
       + fire_A,N,C,B,I,1,H  = 0
 c430: - xNG_R_A,N,C,B,I,0,2,1 - xPB_R_A,N,C,B,I,0,2,1 - xPB_R_A,N,C,B,I,1,2,1
       - xPB_R_A,N,C,B,I,2,2,1 - xGS_R_A,N,C,B,I,0,2,1 - xGS_R_A,N,C,B,I,1,2,1
       - xGS_R_A,N,C,B,I,2,2,1 - xGS_R_A,N,C,B,I,3,2,1 + fire_A,N,C,B,B,1,I
       + fire_A,N,C,B,C,1,I + fire_A,N,C,B,D,1,I + fire_A,N,C,B,E,1,I
       + fire_A,N,C,B,F,1,I + fire_A,N,C,B,G,1,I + fire_A,N,C,B,H,1,I
       + fire_A,N,C,B,I,1,I  = 0
 c431: - xNG_R_A,N,C,B,B,0,3,1 - xPB_R_A,N,C,B,B,0,3,1 - xPB_R_A,N,C,B,B,1,3,1
       - xPB_R_A,N,C,B,B,2,3,1 - xGS_R_A,N,C,B,B,0,3,1 - xGS_R_A,N,C,B,B,1,3,1
       - xGS_R_A,N,C,B,B,2,3,1 - xGS_R_A,N,C,B,B,3,3,1 + fire_A,N,C,B,B,2,B
       + fire_A,N,C,B,C,2,B + fire_A,N,C,B,D,2,B + fire_A,N,C,B,E,2,B
       + fire_A,N,C,B,F,2,B + fire_A,N,C,B,G,2,B + fire_A,N,C,B,H,2,B
       + fire_A,N,C,B,I,2,B  = 0
 c432: - xNG_R_A,N,C,B,C,0,3,1 - xPB_R_A,N,C,B,C,0,3,1 - xPB_R_A,N,C,B,C,1,3,1
       - xPB_R_A,N,C,B,C,2,3,1 - xGS_R_A,N,C,B,C,0,3,1 - xGS_R_A,N,C,B,C,1,3,1
       - xGS_R_A,N,C,B,C,2,3,1 - xGS_R_A,N,C,B,C,3,3,1 + fire_A,N,C,B,B,2,C
       + fire_A,N,C,B,C,2,C + fire_A,N,C,B,D,2,C + fire_A,N,C,B,E,2,C
       + fire_A,N,C,B,F,2,C + fire_A,N,C,B,G,2,C + fire_A,N,C,B,H,2,C
       + fire_A,N,C,B,I,2,C  = 0
 c433: - xNG_R_A,N,C,B,D,0,3,1 - xPB_R_A,N,C,B,D,0,3,1 - xGS_R_A,N,C,B,D,0,3,1
       - xGS_R_A,N,C,B,D,1,3,1 - xGS_R_A,N,C,B,D,2,3,1 - xGS_R_A,N,C,B,D,3,3,1
       + fire_A,N,C,B,B,2,D + fire_A,N,C,B,C,2,D + fire_A,N,C,B,D,2,D
       + fire_A,N,C,B,E,2,D + fire_A,N,C,B,F,2,D + fire_A,N,C,B,G,2,D
       + fire_A,N,C,B,H,2,D + fire_A,N,C,B,I,2,D  = 0
 c434: - xNG_R_A,N,C,B,E,0,3,1 - xPB_R_A,N,C,B,E,0,3,1 - xPB_R_A,N,C,B,E,1,3,1
       - xPB_R_A,N,C,B,E,2,3,1 + fire_A,N,C,B,B,2,E + fire_A,N,C,B,C,2,E
       + fire_A,N,C,B,D,2,E + fire_A,N,C,B,E,2,E + fire_A,N,C,B,F,2,E
       + fire_A,N,C,B,G,2,E + fire_A,N,C,B,H,2,E + fire_A,N,C,B,I,2,E  = 0
 c435: - xNG_R_A,N,C,B,F,0,3,1 - xPB_R_A,N,C,B,F,0,3,1 - xPB_R_A,N,C,B,F,1,3,1
       - xPB_R_A,N,C,B,F,2,3,1 - xGS_R_A,N,C,B,F,0,3,1 - xGS_R_A,N,C,B,F,1,3,1
       - xGS_R_A,N,C,B,F,2,3,1 - xGS_R_A,N,C,B,F,3,3,1 + fire_A,N,C,B,B,2,F
       + fire_A,N,C,B,C,2,F + fire_A,N,C,B,D,2,F + fire_A,N,C,B,E,2,F
       + fire_A,N,C,B,F,2,F + fire_A,N,C,B,G,2,F + fire_A,N,C,B,H,2,F
       + fire_A,N,C,B,I,2,F  = 0
 c436: - xNG_R_A,N,C,B,G,0,3,1 + fire_A,N,C,B,B,2,G + fire_A,N,C,B,C,2,G
       + fire_A,N,C,B,D,2,G + fire_A,N,C,B,E,2,G + fire_A,N,C,B,F,2,G
       + fire_A,N,C,B,G,2,G + fire_A,N,C,B,H,2,G + fire_A,N,C,B,I,2,G  = 0
 c437: - xNG_R_A,N,C,B,H,0,3,1 - xPB_R_A,N,C,B,H,0,3,1 - xPB_R_A,N,C,B,H,1,3,1
       - xPB_R_A,N,C,B,H,2,3,1 - xGS_R_A,N,C,B,H,0,3,1 - xGS_R_A,N,C,B,H,1,3,1
       - xGS_R_A,N,C,B,H,2,3,1 - xGS_R_A,N,C,B,H,3,3,1 + fire_A,N,C,B,B,2,H
       + fire_A,N,C,B,C,2,H + fire_A,N,C,B,D,2,H + fire_A,N,C,B,E,2,H
       + fire_A,N,C,B,F,2,H + fire_A,N,C,B,G,2,H + fire_A,N,C,B,H,2,H
       + fire_A,N,C,B,I,2,H  = 0
 c438: - xNG_R_A,N,C,B,I,0,3,1 - xPB_R_A,N,C,B,I,0,3,1 - xPB_R_A,N,C,B,I,1,3,1
       - xPB_R_A,N,C,B,I,2,3,1 - xGS_R_A,N,C,B,I,0,3,1 - xGS_R_A,N,C,B,I,1,3,1
       - xGS_R_A,N,C,B,I,2,3,1 - xGS_R_A,N,C,B,I,3,3,1 + fire_A,N,C,B,B,2,I
       + fire_A,N,C,B,C,2,I + fire_A,N,C,B,D,2,I + fire_A,N,C,B,E,2,I
       + fire_A,N,C,B,F,2,I + fire_A,N,C,B,G,2,I + fire_A,N,C,B,H,2,I
       + fire_A,N,C,B,I,2,I  = 0
 c439: - xNG_R_A,N,C,B,B,0,4,1 - xPB_R_A,N,C,B,B,0,4,1 - xPB_R_A,N,C,B,B,1,4,1
       - xPB_R_A,N,C,B,B,2,4,1 - xGS_R_A,N,C,B,B,0,4,1 - xGS_R_A,N,C,B,B,1,4,1
       - xGS_R_A,N,C,B,B,2,4,1 - xGS_R_A,N,C,B,B,3,4,1 + fire_A,N,C,B,B,3,B
       + fire_A,N,C,B,C,3,B + fire_A,N,C,B,D,3,B + fire_A,N,C,B,E,3,B
       + fire_A,N,C,B,F,3,B + fire_A,N,C,B,G,3,B + fire_A,N,C,B,H,3,B
       + fire_A,N,C,B,I,3,B  = 0
 c440: - xNG_R_A,N,C,B,C,0,4,1 - xPB_R_A,N,C,B,C,0,4,1 - xPB_R_A,N,C,B,C,1,4,1
       - xPB_R_A,N,C,B,C,2,4,1 - xGS_R_A,N,C,B,C,0,4,1 - xGS_R_A,N,C,B,C,1,4,1
       - xGS_R_A,N,C,B,C,2,4,1 - xGS_R_A,N,C,B,C,3,4,1 + fire_A,N,C,B,B,3,C
       + fire_A,N,C,B,C,3,C + fire_A,N,C,B,D,3,C + fire_A,N,C,B,E,3,C
       + fire_A,N,C,B,F,3,C + fire_A,N,C,B,G,3,C + fire_A,N,C,B,H,3,C
       + fire_A,N,C,B,I,3,C  = 0
 c441: - xNG_R_A,N,C,B,D,0,4,1 - xPB_R_A,N,C,B,D,0,4,1 - xGS_R_A,N,C,B,D,0,4,1
       - xGS_R_A,N,C,B,D,1,4,1 - xGS_R_A,N,C,B,D,2,4,1 - xGS_R_A,N,C,B,D,3,4,1
       + fire_A,N,C,B,B,3,D + fire_A,N,C,B,C,3,D + fire_A,N,C,B,D,3,D
       + fire_A,N,C,B,E,3,D + fire_A,N,C,B,F,3,D + fire_A,N,C,B,G,3,D
       + fire_A,N,C,B,H,3,D + fire_A,N,C,B,I,3,D  = 0
 c442: - xNG_R_A,N,C,B,E,0,4,1 - xPB_R_A,N,C,B,E,0,4,1 - xPB_R_A,N,C,B,E,1,4,1
       - xPB_R_A,N,C,B,E,2,4,1 + fire_A,N,C,B,B,3,E + fire_A,N,C,B,C,3,E
       + fire_A,N,C,B,D,3,E + fire_A,N,C,B,E,3,E + fire_A,N,C,B,F,3,E
       + fire_A,N,C,B,G,3,E + fire_A,N,C,B,H,3,E + fire_A,N,C,B,I,3,E  = 0
 c443: - xNG_R_A,N,C,B,F,0,4,1 - xPB_R_A,N,C,B,F,0,4,1 - xPB_R_A,N,C,B,F,1,4,1
       - xPB_R_A,N,C,B,F,2,4,1 - xGS_R_A,N,C,B,F,0,4,1 - xGS_R_A,N,C,B,F,1,4,1
       - xGS_R_A,N,C,B,F,2,4,1 - xGS_R_A,N,C,B,F,3,4,1 + fire_A,N,C,B,B,3,F
       + fire_A,N,C,B,C,3,F + fire_A,N,C,B,D,3,F + fire_A,N,C,B,E,3,F
       + fire_A,N,C,B,F,3,F + fire_A,N,C,B,G,3,F + fire_A,N,C,B,H,3,F
       + fire_A,N,C,B,I,3,F  = 0
 c444: - xNG_R_A,N,C,B,G,0,4,1 + fire_A,N,C,B,B,3,G + fire_A,N,C,B,C,3,G
       + fire_A,N,C,B,D,3,G + fire_A,N,C,B,E,3,G + fire_A,N,C,B,F,3,G
       + fire_A,N,C,B,G,3,G + fire_A,N,C,B,H,3,G + fire_A,N,C,B,I,3,G  = 0
 c445: - xNG_R_A,N,C,B,H,0,4,1 - xPB_R_A,N,C,B,H,0,4,1 - xPB_R_A,N,C,B,H,1,4,1
       - xPB_R_A,N,C,B,H,2,4,1 - xGS_R_A,N,C,B,H,0,4,1 - xGS_R_A,N,C,B,H,1,4,1
       - xGS_R_A,N,C,B,H,2,4,1 - xGS_R_A,N,C,B,H,3,4,1 + fire_A,N,C,B,B,3,H
       + fire_A,N,C,B,C,3,H + fire_A,N,C,B,D,3,H + fire_A,N,C,B,E,3,H
       + fire_A,N,C,B,F,3,H + fire_A,N,C,B,G,3,H + fire_A,N,C,B,H,3,H
       + fire_A,N,C,B,I,3,H  = 0
 c446: - xNG_R_A,N,C,B,I,0,4,1 - xPB_R_A,N,C,B,I,0,4,1 - xPB_R_A,N,C,B,I,1,4,1
       - xPB_R_A,N,C,B,I,2,4,1 - xGS_R_A,N,C,B,I,0,4,1 - xGS_R_A,N,C,B,I,1,4,1
       - xGS_R_A,N,C,B,I,2,4,1 - xGS_R_A,N,C,B,I,3,4,1 + fire_A,N,C,B,B,3,I
       + fire_A,N,C,B,C,3,I + fire_A,N,C,B,D,3,I + fire_A,N,C,B,E,3,I
       + fire_A,N,C,B,F,3,I + fire_A,N,C,B,G,3,I + fire_A,N,C,B,H,3,I
       + fire_A,N,C,B,I,3,I  = 0
 c447: - xNG_R_A,N,C,B,B,0,5,1 - xPB_R_A,N,C,B,B,0,5,1 - xPB_R_A,N,C,B,B,1,5,1
       - xPB_R_A,N,C,B,B,2,5,1 - xGS_R_A,N,C,B,B,0,5,1 - xGS_R_A,N,C,B,B,1,5,1
       - xGS_R_A,N,C,B,B,2,5,1 - xGS_R_A,N,C,B,B,3,5,1 + fire_A,N,C,B,B,4,B
       + fire_A,N,C,B,C,4,B + fire_A,N,C,B,D,4,B + fire_A,N,C,B,E,4,B
       + fire_A,N,C,B,F,4,B + fire_A,N,C,B,G,4,B + fire_A,N,C,B,H,4,B
       + fire_A,N,C,B,I,4,B  = 0
 c448: - xNG_R_A,N,C,B,C,0,5,1 - xPB_R_A,N,C,B,C,0,5,1 - xPB_R_A,N,C,B,C,1,5,1
       - xPB_R_A,N,C,B,C,2,5,1 - xGS_R_A,N,C,B,C,0,5,1 - xGS_R_A,N,C,B,C,1,5,1
       - xGS_R_A,N,C,B,C,2,5,1 - xGS_R_A,N,C,B,C,3,5,1 + fire_A,N,C,B,B,4,C
       + fire_A,N,C,B,C,4,C + fire_A,N,C,B,D,4,C + fire_A,N,C,B,E,4,C
       + fire_A,N,C,B,F,4,C + fire_A,N,C,B,G,4,C + fire_A,N,C,B,H,4,C
       + fire_A,N,C,B,I,4,C  = 0
 c449: - xNG_R_A,N,C,B,D,0,5,1 - xPB_R_A,N,C,B,D,0,5,1 - xGS_R_A,N,C,B,D,0,5,1
       - xGS_R_A,N,C,B,D,1,5,1 - xGS_R_A,N,C,B,D,2,5,1 - xGS_R_A,N,C,B,D,3,5,1
       + fire_A,N,C,B,B,4,D + fire_A,N,C,B,C,4,D + fire_A,N,C,B,D,4,D
       + fire_A,N,C,B,E,4,D + fire_A,N,C,B,F,4,D + fire_A,N,C,B,G,4,D
       + fire_A,N,C,B,H,4,D + fire_A,N,C,B,I,4,D  = 0
 c450: - xNG_R_A,N,C,B,E,0,5,1 - xPB_R_A,N,C,B,E,0,5,1 - xPB_R_A,N,C,B,E,1,5,1
       - xPB_R_A,N,C,B,E,2,5,1 + fire_A,N,C,B,B,4,E + fire_A,N,C,B,C,4,E
       + fire_A,N,C,B,D,4,E + fire_A,N,C,B,E,4,E + fire_A,N,C,B,F,4,E
       + fire_A,N,C,B,G,4,E + fire_A,N,C,B,H,4,E + fire_A,N,C,B,I,4,E  = 0
 c451: - xNG_R_A,N,C,B,F,0,5,1 - xPB_R_A,N,C,B,F,0,5,1 - xPB_R_A,N,C,B,F,1,5,1
       - xPB_R_A,N,C,B,F,2,5,1 - xGS_R_A,N,C,B,F,0,5,1 - xGS_R_A,N,C,B,F,1,5,1
       - xGS_R_A,N,C,B,F,2,5,1 - xGS_R_A,N,C,B,F,3,5,1 + fire_A,N,C,B,B,4,F
       + fire_A,N,C,B,C,4,F + fire_A,N,C,B,D,4,F + fire_A,N,C,B,E,4,F
       + fire_A,N,C,B,F,4,F + fire_A,N,C,B,G,4,F + fire_A,N,C,B,H,4,F
       + fire_A,N,C,B,I,4,F  = 0
 c452: - xNG_R_A,N,C,B,G,0,5,1 + fire_A,N,C,B,B,4,G + fire_A,N,C,B,C,4,G
       + fire_A,N,C,B,D,4,G + fire_A,N,C,B,E,4,G + fire_A,N,C,B,F,4,G
       + fire_A,N,C,B,G,4,G + fire_A,N,C,B,H,4,G + fire_A,N,C,B,I,4,G  = 0
 c453: - xNG_R_A,N,C,B,H,0,5,1 - xPB_R_A,N,C,B,H,0,5,1 - xPB_R_A,N,C,B,H,1,5,1
       - xPB_R_A,N,C,B,H,2,5,1 - xGS_R_A,N,C,B,H,0,5,1 - xGS_R_A,N,C,B,H,1,5,1
       - xGS_R_A,N,C,B,H,2,5,1 - xGS_R_A,N,C,B,H,3,5,1 + fire_A,N,C,B,B,4,H
       + fire_A,N,C,B,C,4,H + fire_A,N,C,B,D,4,H + fire_A,N,C,B,E,4,H
       + fire_A,N,C,B,F,4,H + fire_A,N,C,B,G,4,H + fire_A,N,C,B,H,4,H
       + fire_A,N,C,B,I,4,H  = 0
 c454: - xNG_R_A,N,C,B,I,0,5,1 - xPB_R_A,N,C,B,I,0,5,1 - xPB_R_A,N,C,B,I,1,5,1
       - xPB_R_A,N,C,B,I,2,5,1 - xGS_R_A,N,C,B,I,0,5,1 - xGS_R_A,N,C,B,I,1,5,1
       - xGS_R_A,N,C,B,I,2,5,1 - xGS_R_A,N,C,B,I,3,5,1 + fire_A,N,C,B,B,4,I
       + fire_A,N,C,B,C,4,I + fire_A,N,C,B,D,4,I + fire_A,N,C,B,E,4,I
       + fire_A,N,C,B,F,4,I + fire_A,N,C,B,G,4,I + fire_A,N,C,B,H,4,I
       + fire_A,N,C,B,I,4,I  = 0
 c455: xNG_R_A,N,C,B,B,0,2,1 - xNG_R_A,N,C,B,B,0,3,2  = 0
 c456: xNG_R_A,N,C,B,B,0,3,1 - xNG_R_A,N,C,B,B,0,4,2  = 0
 c457: xNG_R_A,N,C,B,B,0,3,2 - xNG_R_A,N,C,B,B,0,4,3  = 0
 c458: xNG_R_A,N,C,B,B,0,4,1 - xNG_R_A,N,C,B,B,0,5,2  = 0
 c459: xNG_R_A,N,C,B,B,0,4,2 - xNG_R_A,N,C,B,B,0,5,3  = 0
 c460: xNG_R_A,N,C,B,B,0,4,3 - xNG_R_A,N,C,B,B,0,5,4  = 0
 c461: xNG_R_A,N,C,B,C,0,2,1 - xNG_R_A,N,C,B,C,0,3,2  = 0
 c462: xNG_R_A,N,C,B,C,0,3,1 - xNG_R_A,N,C,B,C,0,4,2  = 0
 c463: xNG_R_A,N,C,B,C,0,3,2 - xNG_R_A,N,C,B,C,0,4,3  = 0
 c464: xNG_R_A,N,C,B,C,0,4,1 - xNG_R_A,N,C,B,C,0,5,2  = 0
 c465: xNG_R_A,N,C,B,C,0,4,2 - xNG_R_A,N,C,B,C,0,5,3  = 0
 c466: xNG_R_A,N,C,B,C,0,4,3 - xNG_R_A,N,C,B,C,0,5,4  = 0
 c467: xNG_R_A,N,C,B,D,0,2,1 - xNG_R_A,N,C,B,D,0,3,2  = 0
 c468: xNG_R_A,N,C,B,D,0,3,1 - xNG_R_A,N,C,B,D,0,4,2  = 0
 c469: xNG_R_A,N,C,B,D,0,3,2 - xNG_R_A,N,C,B,D,0,4,3  = 0
 c470: xNG_R_A,N,C,B,D,0,4,1 - xNG_R_A,N,C,B,D,0,5,2  = 0
 c471: xNG_R_A,N,C,B,D,0,4,2 - xNG_R_A,N,C,B,D,0,5,3  = 0
 c472: xNG_R_A,N,C,B,D,0,4,3 - xNG_R_A,N,C,B,D,0,5,4  = 0
 c473: xNG_R_A,N,C,B,E,0,2,1 - xNG_R_A,N,C,B,E,0,3,2  = 0
 c474: xNG_R_A,N,C,B,E,0,3,1 - xNG_R_A,N,C,B,E,0,4,2  = 0
 c475: xNG_R_A,N,C,B,E,0,3,2 - xNG_R_A,N,C,B,E,0,4,3  = 0
 c476: xNG_R_A,N,C,B,E,0,4,1 - xNG_R_A,N,C,B,E,0,5,2  = 0
 c477: xNG_R_A,N,C,B,E,0,4,2 - xNG_R_A,N,C,B,E,0,5,3  = 0
 c478: xNG_R_A,N,C,B,E,0,4,3 - xNG_R_A,N,C,B,E,0,5,4  = 0
 c479: xNG_R_A,N,C,B,F,0,2,1 - xNG_R_A,N,C,B,F,0,3,2  = 0
 c480: xNG_R_A,N,C,B,F,0,3,1 - xNG_R_A,N,C,B,F,0,4,2  = 0
 c481: xNG_R_A,N,C,B,F,0,3,2 - xNG_R_A,N,C,B,F,0,4,3  = 0
 c482: xNG_R_A,N,C,B,F,0,4,1 - xNG_R_A,N,C,B,F,0,5,2  = 0
 c483: xNG_R_A,N,C,B,F,0,4,2 - xNG_R_A,N,C,B,F,0,5,3  = 0
 c484: xNG_R_A,N,C,B,F,0,4,3 - xNG_R_A,N,C,B,F,0,5,4  = 0
 c485: xNG_R_A,N,C,B,G,0,2,1 - xNG_R_A,N,C,B,G,0,3,2  = 0
 c486: xNG_R_A,N,C,B,G,0,3,1 - xNG_R_A,N,C,B,G,0,4,2  = 0
 c487: xNG_R_A,N,C,B,G,0,3,2 - xNG_R_A,N,C,B,G,0,4,3  = 0
 c488: xNG_R_A,N,C,B,G,0,4,1 - xNG_R_A,N,C,B,G,0,5,2  = 0
 c489: xNG_R_A,N,C,B,G,0,4,2 - xNG_R_A,N,C,B,G,0,5,3  = 0
 c490: xNG_R_A,N,C,B,G,0,4,3 - xNG_R_A,N,C,B,G,0,5,4  = 0
 c491: xNG_R_A,N,C,B,H,0,2,1 - xNG_R_A,N,C,B,H,0,3,2  = 0
 c492: xNG_R_A,N,C,B,H,0,3,1 - xNG_R_A,N,C,B,H,0,4,2  = 0
 c493: xNG_R_A,N,C,B,H,0,3,2 - xNG_R_A,N,C,B,H,0,4,3  = 0
 c494: xNG_R_A,N,C,B,H,0,4,1 - xNG_R_A,N,C,B,H,0,5,2  = 0
 c495: xNG_R_A,N,C,B,H,0,4,2 - xNG_R_A,N,C,B,H,0,5,3  = 0
 c496: xNG_R_A,N,C,B,H,0,4,3 - xNG_R_A,N,C,B,H,0,5,4  = 0
 c497: xNG_R_A,N,C,B,I,0,2,1 - xNG_R_A,N,C,B,I,0,3,2  = 0
 c498: xNG_R_A,N,C,B,I,0,3,1 - xNG_R_A,N,C,B,I,0,4,2  = 0
 c499: xNG_R_A,N,C,B,I,0,3,2 - xNG_R_A,N,C,B,I,0,4,3  = 0
 c500: xNG_R_A,N,C,B,I,0,4,1 - xNG_R_A,N,C,B,I,0,5,2  = 0
 c501: xNG_R_A,N,C,B,I,0,4,2 - xNG_R_A,N,C,B,I,0,5,3  = 0
 c502: xNG_R_A,N,C,B,I,0,4,3 - xNG_R_A,N,C,B,I,0,5,4  = 0
 c503: xPB_R_A,N,C,B,B,0,2,1 - xPB_R_A,N,C,B,B,0,3,2  = 0
 c504: xPB_R_A,N,C,B,B,0,3,1 - xPB_R_A,N,C,B,B,0,4,2  = 0
 c505: xPB_R_A,N,C,B,B,0,3,2 - xPB_R_A,N,C,B,B,0,4,3  = 0
 c506: xPB_R_A,N,C,B,B,0,4,1 - xPB_R_A,N,C,B,B,0,5,2  = 0
 c507: xPB_R_A,N,C,B,B,0,4,2 - xPB_R_A,N,C,B,B,0,5,3  = 0
 c508: xPB_R_A,N,C,B,B,0,4,3 - xPB_R_A,N,C,B,B,0,5,4  = 0
 c509: xPB_R_A,N,C,B,B,1,2,1 - xPB_R_A,N,C,B,B,1,3,2  = 0
 c510: xPB_R_A,N,C,B,B,1,3,1 - xPB_R_A,N,C,B,B,1,4,2  = 0
 c511: xPB_R_A,N,C,B,B,1,3,2 - xPB_R_A,N,C,B,B,1,4,3  = 0
 c512: xPB_R_A,N,C,B,B,1,4,1 - xPB_R_A,N,C,B,B,1,5,2  = 0
 c513: xPB_R_A,N,C,B,B,1,4,2 - xPB_R_A,N,C,B,B,1,5,3  = 0
 c514: xPB_R_A,N,C,B,B,1,4,3 - xPB_R_A,N,C,B,B,1,5,4  = 0
 c515: xPB_R_A,N,C,B,B,2,2,1 - xPB_R_A,N,C,B,B,2,3,2  = 0
 c516: xPB_R_A,N,C,B,B,2,3,1 - xPB_R_A,N,C,B,B,2,4,2  = 0
 c517: xPB_R_A,N,C,B,B,2,3,2 - xPB_R_A,N,C,B,B,2,4,3  = 0
 c518: xPB_R_A,N,C,B,B,2,4,1 - xPB_R_A,N,C,B,B,2,5,2  = 0
 c519: xPB_R_A,N,C,B,B,2,4,2 - xPB_R_A,N,C,B,B,2,5,3  = 0
 c520: xPB_R_A,N,C,B,B,2,4,3 - xPB_R_A,N,C,B,B,2,5,4  = 0
 c521: xPB_R_A,N,C,B,C,0,2,1 - xPB_R_A,N,C,B,C,0,3,2  = 0
 c522: xPB_R_A,N,C,B,C,0,3,1 - xPB_R_A,N,C,B,C,0,4,2  = 0
 c523: xPB_R_A,N,C,B,C,0,3,2 - xPB_R_A,N,C,B,C,0,4,3  = 0
 c524: xPB_R_A,N,C,B,C,0,4,1 - xPB_R_A,N,C,B,C,0,5,2  = 0
 c525: xPB_R_A,N,C,B,C,0,4,2 - xPB_R_A,N,C,B,C,0,5,3  = 0
 c526: xPB_R_A,N,C,B,C,0,4,3 - xPB_R_A,N,C,B,C,0,5,4  = 0
 c527: xPB_R_A,N,C,B,C,1,2,1 - xPB_R_A,N,C,B,C,1,3,2  = 0
 c528: xPB_R_A,N,C,B,C,1,3,1 - xPB_R_A,N,C,B,C,1,4,2  = 0
 c529: xPB_R_A,N,C,B,C,1,3,2 - xPB_R_A,N,C,B,C,1,4,3  = 0
 c530: xPB_R_A,N,C,B,C,1,4,1 - xPB_R_A,N,C,B,C,1,5,2  = 0
 c531: xPB_R_A,N,C,B,C,1,4,2 - xPB_R_A,N,C,B,C,1,5,3  = 0
 c532: xPB_R_A,N,C,B,C,1,4,3 - xPB_R_A,N,C,B,C,1,5,4  = 0
 c533: xPB_R_A,N,C,B,C,2,2,1 - xPB_R_A,N,C,B,C,2,3,2  = 0
 c534: xPB_R_A,N,C,B,C,2,3,1 - xPB_R_A,N,C,B,C,2,4,2  = 0
 c535: xPB_R_A,N,C,B,C,2,3,2 - xPB_R_A,N,C,B,C,2,4,3  = 0
 c536: xPB_R_A,N,C,B,C,2,4,1 - xPB_R_A,N,C,B,C,2,5,2  = 0
 c537: xPB_R_A,N,C,B,C,2,4,2 - xPB_R_A,N,C,B,C,2,5,3  = 0
 c538: xPB_R_A,N,C,B,C,2,4,3 - xPB_R_A,N,C,B,C,2,5,4  = 0
 c539: xPB_R_A,N,C,B,D,0,2,1 - xPB_R_A,N,C,B,D,0,3,2  = 0
 c540: xPB_R_A,N,C,B,D,0,3,1 - xPB_R_A,N,C,B,D,0,4,2  = 0
 c541: xPB_R_A,N,C,B,D,0,3,2 - xPB_R_A,N,C,B,D,0,4,3  = 0
 c542: xPB_R_A,N,C,B,D,0,4,1 - xPB_R_A,N,C,B,D,0,5,2  = 0
 c543: xPB_R_A,N,C,B,D,0,4,2 - xPB_R_A,N,C,B,D,0,5,3  = 0
 c544: xPB_R_A,N,C,B,D,0,4,3 - xPB_R_A,N,C,B,D,0,5,4  = 0
 c545: xPB_R_A,N,C,B,E,0,2,1 - xPB_R_A,N,C,B,E,0,3,2  = 0
 c546: xPB_R_A,N,C,B,E,0,3,1 - xPB_R_A,N,C,B,E,0,4,2  = 0
 c547: xPB_R_A,N,C,B,E,0,3,2 - xPB_R_A,N,C,B,E,0,4,3  = 0
 c548: xPB_R_A,N,C,B,E,0,4,1 - xPB_R_A,N,C,B,E,0,5,2  = 0
 c549: xPB_R_A,N,C,B,E,0,4,2 - xPB_R_A,N,C,B,E,0,5,3  = 0
 c550: xPB_R_A,N,C,B,E,0,4,3 - xPB_R_A,N,C,B,E,0,5,4  = 0
 c551: xPB_R_A,N,C,B,E,1,2,1 - xPB_R_A,N,C,B,E,1,3,2  = 0
 c552: xPB_R_A,N,C,B,E,1,3,1 - xPB_R_A,N,C,B,E,1,4,2  = 0
 c553: xPB_R_A,N,C,B,E,1,3,2 - xPB_R_A,N,C,B,E,1,4,3  = 0
 c554: xPB_R_A,N,C,B,E,1,4,1 - xPB_R_A,N,C,B,E,1,5,2  = 0
 c555: xPB_R_A,N,C,B,E,1,4,2 - xPB_R_A,N,C,B,E,1,5,3  = 0
 c556: xPB_R_A,N,C,B,E,1,4,3 - xPB_R_A,N,C,B,E,1,5,4  = 0
 c557: xPB_R_A,N,C,B,E,2,2,1 - xPB_R_A,N,C,B,E,2,3,2  = 0
 c558: xPB_R_A,N,C,B,E,2,3,1 - xPB_R_A,N,C,B,E,2,4,2  = 0
 c559: xPB_R_A,N,C,B,E,2,3,2 - xPB_R_A,N,C,B,E,2,4,3  = 0
 c560: xPB_R_A,N,C,B,E,2,4,1 - xPB_R_A,N,C,B,E,2,5,2  = 0
 c561: xPB_R_A,N,C,B,E,2,4,2 - xPB_R_A,N,C,B,E,2,5,3  = 0
 c562: xPB_R_A,N,C,B,E,2,4,3 - xPB_R_A,N,C,B,E,2,5,4  = 0
 c563: xPB_R_A,N,C,B,F,0,2,1 - xPB_R_A,N,C,B,F,0,3,2  = 0
 c564: xPB_R_A,N,C,B,F,0,3,1 - xPB_R_A,N,C,B,F,0,4,2  = 0
 c565: xPB_R_A,N,C,B,F,0,3,2 - xPB_R_A,N,C,B,F,0,4,3  = 0
 c566: xPB_R_A,N,C,B,F,0,4,1 - xPB_R_A,N,C,B,F,0,5,2  = 0
 c567: xPB_R_A,N,C,B,F,0,4,2 - xPB_R_A,N,C,B,F,0,5,3  = 0
 c568: xPB_R_A,N,C,B,F,0,4,3 - xPB_R_A,N,C,B,F,0,5,4  = 0
 c569: xPB_R_A,N,C,B,F,1,2,1 - xPB_R_A,N,C,B,F,1,3,2  = 0
 c570: xPB_R_A,N,C,B,F,1,3,1 - xPB_R_A,N,C,B,F,1,4,2  = 0
 c571: xPB_R_A,N,C,B,F,1,3,2 - xPB_R_A,N,C,B,F,1,4,3  = 0
 c572: xPB_R_A,N,C,B,F,1,4,1 - xPB_R_A,N,C,B,F,1,5,2  = 0
 c573: xPB_R_A,N,C,B,F,1,4,2 - xPB_R_A,N,C,B,F,1,5,3  = 0
 c574: xPB_R_A,N,C,B,F,1,4,3 - xPB_R_A,N,C,B,F,1,5,4  = 0
 c575: xPB_R_A,N,C,B,F,2,2,1 - xPB_R_A,N,C,B,F,2,3,2  = 0
 c576: xPB_R_A,N,C,B,F,2,3,1 - xPB_R_A,N,C,B,F,2,4,2  = 0
 c577: xPB_R_A,N,C,B,F,2,3,2 - xPB_R_A,N,C,B,F,2,4,3  = 0
 c578: xPB_R_A,N,C,B,F,2,4,1 - xPB_R_A,N,C,B,F,2,5,2  = 0
 c579: xPB_R_A,N,C,B,F,2,4,2 - xPB_R_A,N,C,B,F,2,5,3  = 0
 c580: xPB_R_A,N,C,B,F,2,4,3 - xPB_R_A,N,C,B,F,2,5,4  = 0
 c581: xPB_R_A,N,C,B,H,0,2,1 - xPB_R_A,N,C,B,H,0,3,2  = 0
 c582: xPB_R_A,N,C,B,H,0,3,1 - xPB_R_A,N,C,B,H,0,4,2  = 0
 c583: xPB_R_A,N,C,B,H,0,3,2 - xPB_R_A,N,C,B,H,0,4,3  = 0
 c584: xPB_R_A,N,C,B,H,0,4,1 - xPB_R_A,N,C,B,H,0,5,2  = 0
 c585: xPB_R_A,N,C,B,H,0,4,2 - xPB_R_A,N,C,B,H,0,5,3  = 0
 c586: xPB_R_A,N,C,B,H,0,4,3 - xPB_R_A,N,C,B,H,0,5,4  = 0
 c587: xPB_R_A,N,C,B,H,1,2,1 - xPB_R_A,N,C,B,H,1,3,2  = 0
 c588: xPB_R_A,N,C,B,H,1,3,1 - xPB_R_A,N,C,B,H,1,4,2  = 0
 c589: xPB_R_A,N,C,B,H,1,3,2 - xPB_R_A,N,C,B,H,1,4,3  = 0
 c590: xPB_R_A,N,C,B,H,1,4,1 - xPB_R_A,N,C,B,H,1,5,2  = 0
 c591: xPB_R_A,N,C,B,H,1,4,2 - xPB_R_A,N,C,B,H,1,5,3  = 0
 c592: xPB_R_A,N,C,B,H,1,4,3 - xPB_R_A,N,C,B,H,1,5,4  = 0
 c593: xPB_R_A,N,C,B,H,2,2,1 - xPB_R_A,N,C,B,H,2,3,2  = 0
 c594: xPB_R_A,N,C,B,H,2,3,1 - xPB_R_A,N,C,B,H,2,4,2  = 0
 c595: xPB_R_A,N,C,B,H,2,3,2 - xPB_R_A,N,C,B,H,2,4,3  = 0
 c596: xPB_R_A,N,C,B,H,2,4,1 - xPB_R_A,N,C,B,H,2,5,2  = 0
 c597: xPB_R_A,N,C,B,H,2,4,2 - xPB_R_A,N,C,B,H,2,5,3  = 0
 c598: xPB_R_A,N,C,B,H,2,4,3 - xPB_R_A,N,C,B,H,2,5,4  = 0
 c599: xPB_R_A,N,C,B,I,0,2,1 - xPB_R_A,N,C,B,I,0,3,2  = 0
 c600: xPB_R_A,N,C,B,I,0,3,1 - xPB_R_A,N,C,B,I,0,4,2  = 0
 c601: xPB_R_A,N,C,B,I,0,3,2 - xPB_R_A,N,C,B,I,0,4,3  = 0
 c602: xPB_R_A,N,C,B,I,0,4,1 - xPB_R_A,N,C,B,I,0,5,2  = 0
 c603: xPB_R_A,N,C,B,I,0,4,2 - xPB_R_A,N,C,B,I,0,5,3  = 0
 c604: xPB_R_A,N,C,B,I,0,4,3 - xPB_R_A,N,C,B,I,0,5,4  = 0
 c605: xPB_R_A,N,C,B,I,1,2,1 - xPB_R_A,N,C,B,I,1,3,2  = 0
 c606: xPB_R_A,N,C,B,I,1,3,1 - xPB_R_A,N,C,B,I,1,4,2  = 0
 c607: xPB_R_A,N,C,B,I,1,3,2 - xPB_R_A,N,C,B,I,1,4,3  = 0
 c608: xPB_R_A,N,C,B,I,1,4,1 - xPB_R_A,N,C,B,I,1,5,2  = 0
 c609: xPB_R_A,N,C,B,I,1,4,2 - xPB_R_A,N,C,B,I,1,5,3  = 0
 c610: xPB_R_A,N,C,B,I,1,4,3 - xPB_R_A,N,C,B,I,1,5,4  = 0
 c611: xPB_R_A,N,C,B,I,2,2,1 - xPB_R_A,N,C,B,I,2,3,2  = 0
 c612: xPB_R_A,N,C,B,I,2,3,1 - xPB_R_A,N,C,B,I,2,4,2  = 0
 c613: xPB_R_A,N,C,B,I,2,3,2 - xPB_R_A,N,C,B,I,2,4,3  = 0
 c614: xPB_R_A,N,C,B,I,2,4,1 - xPB_R_A,N,C,B,I,2,5,2  = 0
 c615: xPB_R_A,N,C,B,I,2,4,2 - xPB_R_A,N,C,B,I,2,5,3  = 0
 c616: xPB_R_A,N,C,B,I,2,4,3 - xPB_R_A,N,C,B,I,2,5,4  = 0
 c617: xGS_R_A,N,C,B,B,0,2,1 - xGS_R_A,N,C,B,B,0,3,2  = 0
 c618: xGS_R_A,N,C,B,B,0,3,1 - xGS_R_A,N,C,B,B,0,4,2  = 0
 c619: xGS_R_A,N,C,B,B,0,3,2 - xGS_R_A,N,C,B,B,0,4,3  = 0
 c620: xGS_R_A,N,C,B,B,0,4,1 - xGS_R_A,N,C,B,B,0,5,2  = 0
 c621: xGS_R_A,N,C,B,B,0,4,2 - xGS_R_A,N,C,B,B,0,5,3  = 0
 c622: xGS_R_A,N,C,B,B,0,4,3 - xGS_R_A,N,C,B,B,0,5,4  = 0
 c623: xGS_R_A,N,C,B,B,1,2,1 - xGS_R_A,N,C,B,B,1,3,2  = 0
 c624: xGS_R_A,N,C,B,B,1,3,1 - xGS_R_A,N,C,B,B,1,4,2  = 0
 c625: xGS_R_A,N,C,B,B,1,3,2 - xGS_R_A,N,C,B,B,1,4,3  = 0
 c626: xGS_R_A,N,C,B,B,1,4,1 - xGS_R_A,N,C,B,B,1,5,2  = 0
 c627: xGS_R_A,N,C,B,B,1,4,2 - xGS_R_A,N,C,B,B,1,5,3  = 0
 c628: xGS_R_A,N,C,B,B,1,4,3 - xGS_R_A,N,C,B,B,1,5,4  = 0
 c629: xGS_R_A,N,C,B,B,2,2,1 - xGS_R_A,N,C,B,B,2,3,2  = 0
 c630: xGS_R_A,N,C,B,B,2,3,1 - xGS_R_A,N,C,B,B,2,4,2  = 0
 c631: xGS_R_A,N,C,B,B,2,3,2 - xGS_R_A,N,C,B,B,2,4,3  = 0
 c632: xGS_R_A,N,C,B,B,2,4,1 - xGS_R_A,N,C,B,B,2,5,2  = 0
 c633: xGS_R_A,N,C,B,B,2,4,2 - xGS_R_A,N,C,B,B,2,5,3  = 0
 c634: xGS_R_A,N,C,B,B,2,4,3 - xGS_R_A,N,C,B,B,2,5,4  = 0
 c635: xGS_R_A,N,C,B,B,3,2,1 - xGS_R_A,N,C,B,B,3,3,2  = 0
 c636: xGS_R_A,N,C,B,B,3,3,1 - xGS_R_A,N,C,B,B,3,4,2  = 0
 c637: xGS_R_A,N,C,B,B,3,3,2 - xGS_R_A,N,C,B,B,3,4,3  = 0
 c638: xGS_R_A,N,C,B,B,3,4,1 - xGS_R_A,N,C,B,B,3,5,2  = 0
 c639: xGS_R_A,N,C,B,B,3,4,2 - xGS_R_A,N,C,B,B,3,5,3  = 0
 c640: xGS_R_A,N,C,B,B,3,4,3 - xGS_R_A,N,C,B,B,3,5,4  = 0
 c641: xGS_R_A,N,C,B,C,0,2,1 - xGS_R_A,N,C,B,C,0,3,2  = 0
 c642: xGS_R_A,N,C,B,C,0,3,1 - xGS_R_A,N,C,B,C,0,4,2  = 0
 c643: xGS_R_A,N,C,B,C,0,3,2 - xGS_R_A,N,C,B,C,0,4,3  = 0
 c644: xGS_R_A,N,C,B,C,0,4,1 - xGS_R_A,N,C,B,C,0,5,2  = 0
 c645: xGS_R_A,N,C,B,C,0,4,2 - xGS_R_A,N,C,B,C,0,5,3  = 0
 c646: xGS_R_A,N,C,B,C,0,4,3 - xGS_R_A,N,C,B,C,0,5,4  = 0
 c647: xGS_R_A,N,C,B,C,1,2,1 - xGS_R_A,N,C,B,C,1,3,2  = 0
 c648: xGS_R_A,N,C,B,C,1,3,1 - xGS_R_A,N,C,B,C,1,4,2  = 0
 c649: xGS_R_A,N,C,B,C,1,3,2 - xGS_R_A,N,C,B,C,1,4,3  = 0
 c650: xGS_R_A,N,C,B,C,1,4,1 - xGS_R_A,N,C,B,C,1,5,2  = 0
 c651: xGS_R_A,N,C,B,C,1,4,2 - xGS_R_A,N,C,B,C,1,5,3  = 0
 c652: xGS_R_A,N,C,B,C,1,4,3 - xGS_R_A,N,C,B,C,1,5,4  = 0
 c653: xGS_R_A,N,C,B,C,2,2,1 - xGS_R_A,N,C,B,C,2,3,2  = 0
 c654: xGS_R_A,N,C,B,C,2,3,1 - xGS_R_A,N,C,B,C,2,4,2  = 0
 c655: xGS_R_A,N,C,B,C,2,3,2 - xGS_R_A,N,C,B,C,2,4,3  = 0
 c656: xGS_R_A,N,C,B,C,2,4,1 - xGS_R_A,N,C,B,C,2,5,2  = 0
 c657: xGS_R_A,N,C,B,C,2,4,2 - xGS_R_A,N,C,B,C,2,5,3  = 0
 c658: xGS_R_A,N,C,B,C,2,4,3 - xGS_R_A,N,C,B,C,2,5,4  = 0
 c659: xGS_R_A,N,C,B,C,3,2,1 - xGS_R_A,N,C,B,C,3,3,2  = 0
 c660: xGS_R_A,N,C,B,C,3,3,1 - xGS_R_A,N,C,B,C,3,4,2  = 0
 c661: xGS_R_A,N,C,B,C,3,3,2 - xGS_R_A,N,C,B,C,3,4,3  = 0
 c662: xGS_R_A,N,C,B,C,3,4,1 - xGS_R_A,N,C,B,C,3,5,2  = 0
 c663: xGS_R_A,N,C,B,C,3,4,2 - xGS_R_A,N,C,B,C,3,5,3  = 0
 c664: xGS_R_A,N,C,B,C,3,4,3 - xGS_R_A,N,C,B,C,3,5,4  = 0
 c665: xGS_R_A,N,C,B,D,0,2,1 - xGS_R_A,N,C,B,D,0,3,2  = 0
 c666: xGS_R_A,N,C,B,D,0,3,1 - xGS_R_A,N,C,B,D,0,4,2  = 0
 c667: xGS_R_A,N,C,B,D,0,3,2 - xGS_R_A,N,C,B,D,0,4,3  = 0
 c668: xGS_R_A,N,C,B,D,0,4,1 - xGS_R_A,N,C,B,D,0,5,2  = 0
 c669: xGS_R_A,N,C,B,D,0,4,2 - xGS_R_A,N,C,B,D,0,5,3  = 0
 c670: xGS_R_A,N,C,B,D,0,4,3 - xGS_R_A,N,C,B,D,0,5,4  = 0
 c671: xGS_R_A,N,C,B,D,1,2,1 - xGS_R_A,N,C,B,D,1,3,2  = 0
 c672: xGS_R_A,N,C,B,D,1,3,1 - xGS_R_A,N,C,B,D,1,4,2  = 0
 c673: xGS_R_A,N,C,B,D,1,3,2 - xGS_R_A,N,C,B,D,1,4,3  = 0
 c674: xGS_R_A,N,C,B,D,1,4,1 - xGS_R_A,N,C,B,D,1,5,2  = 0
 c675: xGS_R_A,N,C,B,D,1,4,2 - xGS_R_A,N,C,B,D,1,5,3  = 0
 c676: xGS_R_A,N,C,B,D,1,4,3 - xGS_R_A,N,C,B,D,1,5,4  = 0
 c677: xGS_R_A,N,C,B,D,2,2,1 - xGS_R_A,N,C,B,D,2,3,2  = 0
 c678: xGS_R_A,N,C,B,D,2,3,1 - xGS_R_A,N,C,B,D,2,4,2  = 0
 c679: xGS_R_A,N,C,B,D,2,3,2 - xGS_R_A,N,C,B,D,2,4,3  = 0
 c680: xGS_R_A,N,C,B,D,2,4,1 - xGS_R_A,N,C,B,D,2,5,2  = 0
 c681: xGS_R_A,N,C,B,D,2,4,2 - xGS_R_A,N,C,B,D,2,5,3  = 0
 c682: xGS_R_A,N,C,B,D,2,4,3 - xGS_R_A,N,C,B,D,2,5,4  = 0
 c683: xGS_R_A,N,C,B,D,3,2,1 - xGS_R_A,N,C,B,D,3,3,2  = 0
 c684: xGS_R_A,N,C,B,D,3,3,1 - xGS_R_A,N,C,B,D,3,4,2  = 0
 c685: xGS_R_A,N,C,B,D,3,3,2 - xGS_R_A,N,C,B,D,3,4,3  = 0
 c686: xGS_R_A,N,C,B,D,3,4,1 - xGS_R_A,N,C,B,D,3,5,2  = 0
 c687: xGS_R_A,N,C,B,D,3,4,2 - xGS_R_A,N,C,B,D,3,5,3  = 0
 c688: xGS_R_A,N,C,B,D,3,4,3 - xGS_R_A,N,C,B,D,3,5,4  = 0
 c689: xGS_R_A,N,C,B,F,0,2,1 - xGS_R_A,N,C,B,F,0,3,2  = 0
 c690: xGS_R_A,N,C,B,F,0,3,1 - xGS_R_A,N,C,B,F,0,4,2  = 0
 c691: xGS_R_A,N,C,B,F,0,3,2 - xGS_R_A,N,C,B,F,0,4,3  = 0
 c692: xGS_R_A,N,C,B,F,0,4,1 - xGS_R_A,N,C,B,F,0,5,2  = 0
 c693: xGS_R_A,N,C,B,F,0,4,2 - xGS_R_A,N,C,B,F,0,5,3  = 0
 c694: xGS_R_A,N,C,B,F,0,4,3 - xGS_R_A,N,C,B,F,0,5,4  = 0
 c695: xGS_R_A,N,C,B,F,1,2,1 - xGS_R_A,N,C,B,F,1,3,2  = 0
 c696: xGS_R_A,N,C,B,F,1,3,1 - xGS_R_A,N,C,B,F,1,4,2  = 0
 c697: xGS_R_A,N,C,B,F,1,3,2 - xGS_R_A,N,C,B,F,1,4,3  = 0
 c698: xGS_R_A,N,C,B,F,1,4,1 - xGS_R_A,N,C,B,F,1,5,2  = 0
 c699: xGS_R_A,N,C,B,F,1,4,2 - xGS_R_A,N,C,B,F,1,5,3  = 0
 c700: xGS_R_A,N,C,B,F,1,4,3 - xGS_R_A,N,C,B,F,1,5,4  = 0
 c701: xGS_R_A,N,C,B,F,2,2,1 - xGS_R_A,N,C,B,F,2,3,2  = 0
 c702: xGS_R_A,N,C,B,F,2,3,1 - xGS_R_A,N,C,B,F,2,4,2  = 0
 c703: xGS_R_A,N,C,B,F,2,3,2 - xGS_R_A,N,C,B,F,2,4,3  = 0
 c704: xGS_R_A,N,C,B,F,2,4,1 - xGS_R_A,N,C,B,F,2,5,2  = 0
 c705: xGS_R_A,N,C,B,F,2,4,2 - xGS_R_A,N,C,B,F,2,5,3  = 0
 c706: xGS_R_A,N,C,B,F,2,4,3 - xGS_R_A,N,C,B,F,2,5,4  = 0
 c707: xGS_R_A,N,C,B,F,3,2,1 - xGS_R_A,N,C,B,F,3,3,2  = 0
 c708: xGS_R_A,N,C,B,F,3,3,1 - xGS_R_A,N,C,B,F,3,4,2  = 0
 c709: xGS_R_A,N,C,B,F,3,3,2 - xGS_R_A,N,C,B,F,3,4,3  = 0
 c710: xGS_R_A,N,C,B,F,3,4,1 - xGS_R_A,N,C,B,F,3,5,2  = 0
 c711: xGS_R_A,N,C,B,F,3,4,2 - xGS_R_A,N,C,B,F,3,5,3  = 0
 c712: xGS_R_A,N,C,B,F,3,4,3 - xGS_R_A,N,C,B,F,3,5,4  = 0
 c713: xGS_R_A,N,C,B,H,0,2,1 - xGS_R_A,N,C,B,H,0,3,2  = 0
 c714: xGS_R_A,N,C,B,H,0,3,1 - xGS_R_A,N,C,B,H,0,4,2  = 0
 c715: xGS_R_A,N,C,B,H,0,3,2 - xGS_R_A,N,C,B,H,0,4,3  = 0
 c716: xGS_R_A,N,C,B,H,0,4,1 - xGS_R_A,N,C,B,H,0,5,2  = 0
 c717: xGS_R_A,N,C,B,H,0,4,2 - xGS_R_A,N,C,B,H,0,5,3  = 0
 c718: xGS_R_A,N,C,B,H,0,4,3 - xGS_R_A,N,C,B,H,0,5,4  = 0
 c719: xGS_R_A,N,C,B,H,1,2,1 - xGS_R_A,N,C,B,H,1,3,2  = 0
 c720: xGS_R_A,N,C,B,H,1,3,1 - xGS_R_A,N,C,B,H,1,4,2  = 0
 c721: xGS_R_A,N,C,B,H,1,3,2 - xGS_R_A,N,C,B,H,1,4,3  = 0
 c722: xGS_R_A,N,C,B,H,1,4,1 - xGS_R_A,N,C,B,H,1,5,2  = 0
 c723: xGS_R_A,N,C,B,H,1,4,2 - xGS_R_A,N,C,B,H,1,5,3  = 0
 c724: xGS_R_A,N,C,B,H,1,4,3 - xGS_R_A,N,C,B,H,1,5,4  = 0
 c725: xGS_R_A,N,C,B,H,2,2,1 - xGS_R_A,N,C,B,H,2,3,2  = 0
 c726: xGS_R_A,N,C,B,H,2,3,1 - xGS_R_A,N,C,B,H,2,4,2  = 0
 c727: xGS_R_A,N,C,B,H,2,3,2 - xGS_R_A,N,C,B,H,2,4,3  = 0
 c728: xGS_R_A,N,C,B,H,2,4,1 - xGS_R_A,N,C,B,H,2,5,2  = 0
 c729: xGS_R_A,N,C,B,H,2,4,2 - xGS_R_A,N,C,B,H,2,5,3  = 0
 c730: xGS_R_A,N,C,B,H,2,4,3 - xGS_R_A,N,C,B,H,2,5,4  = 0
 c731: xGS_R_A,N,C,B,H,3,2,1 - xGS_R_A,N,C,B,H,3,3,2  = 0
 c732: xGS_R_A,N,C,B,H,3,3,1 - xGS_R_A,N,C,B,H,3,4,2  = 0
 c733: xGS_R_A,N,C,B,H,3,3,2 - xGS_R_A,N,C,B,H,3,4,3  = 0
 c734: xGS_R_A,N,C,B,H,3,4,1 - xGS_R_A,N,C,B,H,3,5,2  = 0
 c735: xGS_R_A,N,C,B,H,3,4,2 - xGS_R_A,N,C,B,H,3,5,3  = 0
 c736: xGS_R_A,N,C,B,H,3,4,3 - xGS_R_A,N,C,B,H,3,5,4  = 0
 c737: xGS_R_A,N,C,B,I,0,2,1 - xGS_R_A,N,C,B,I,0,3,2  = 0
 c738: xGS_R_A,N,C,B,I,0,3,1 - xGS_R_A,N,C,B,I,0,4,2  = 0
 c739: xGS_R_A,N,C,B,I,0,3,2 - xGS_R_A,N,C,B,I,0,4,3  = 0
 c740: xGS_R_A,N,C,B,I,0,4,1 - xGS_R_A,N,C,B,I,0,5,2  = 0
 c741: xGS_R_A,N,C,B,I,0,4,2 - xGS_R_A,N,C,B,I,0,5,3  = 0
 c742: xGS_R_A,N,C,B,I,0,4,3 - xGS_R_A,N,C,B,I,0,5,4  = 0
 c743: xGS_R_A,N,C,B,I,1,2,1 - xGS_R_A,N,C,B,I,1,3,2  = 0
 c744: xGS_R_A,N,C,B,I,1,3,1 - xGS_R_A,N,C,B,I,1,4,2  = 0
 c745: xGS_R_A,N,C,B,I,1,3,2 - xGS_R_A,N,C,B,I,1,4,3  = 0
 c746: xGS_R_A,N,C,B,I,1,4,1 - xGS_R_A,N,C,B,I,1,5,2  = 0
 c747: xGS_R_A,N,C,B,I,1,4,2 - xGS_R_A,N,C,B,I,1,5,3  = 0
 c748: xGS_R_A,N,C,B,I,1,4,3 - xGS_R_A,N,C,B,I,1,5,4  = 0
 c749: xGS_R_A,N,C,B,I,2,2,1 - xGS_R_A,N,C,B,I,2,3,2  = 0
 c750: xGS_R_A,N,C,B,I,2,3,1 - xGS_R_A,N,C,B,I,2,4,2  = 0
 c751: xGS_R_A,N,C,B,I,2,3,2 - xGS_R_A,N,C,B,I,2,4,3  = 0
 c752: xGS_R_A,N,C,B,I,2,4,1 - xGS_R_A,N,C,B,I,2,5,2  = 0
 c753: xGS_R_A,N,C,B,I,2,4,2 - xGS_R_A,N,C,B,I,2,5,3  = 0
 c754: xGS_R_A,N,C,B,I,2,4,3 - xGS_R_A,N,C,B,I,2,5,4  = 0
 c755: xGS_R_A,N,C,B,I,3,2,1 - xGS_R_A,N,C,B,I,3,3,2  = 0
 c756: xGS_R_A,N,C,B,I,3,3,1 - xGS_R_A,N,C,B,I,3,4,2  = 0
 c757: xGS_R_A,N,C,B,I,3,3,2 - xGS_R_A,N,C,B,I,3,4,3  = 0
 c758: xGS_R_A,N,C,B,I,3,4,1 - xGS_R_A,N,C,B,I,3,5,2  = 0
 c759: xGS_R_A,N,C,B,I,3,4,2 - xGS_R_A,N,C,B,I,3,5,3  = 0
 c760: xGS_R_A,N,C,B,I,3,4,3 - xGS_R_A,N,C,B,I,3,5,4  = 0
Bounds
      x_A,N,C,B,D,G,3 = 0
      x_A,N,C,B,D,G,4 = 0
      x_A,N,C,B,D,G,5 = 0
      x_A,N,C,B,D,I,3 = 0
      x_A,N,C,B,D,I,4 = 0
      x_A,N,C,B,D,I,5 = 0
End
