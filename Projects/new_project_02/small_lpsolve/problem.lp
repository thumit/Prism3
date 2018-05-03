/* Objective function */
min: ;

/* Constraints */
+x_A,N,C,B,D,G,0 +x_A,N,C,B,D,G,1 +x_A,N,C,B,D,G,2 +x_A,N,C,B,D,G,3 +x_A,N,C,B,D,G,4 +x_A,N,C,B,D,G,5 = 20;
+x_A,N,C,B,D,I,0 +x_A,N,C,B,D,I,1 +x_A,N,C,B,D,I,2 +x_A,N,C,B,D,I,3 +x_A,N,C,B,D,I,4 +x_A,N,C,B,D,I,5 = 191;
+x_A,N,C,B,D,G,0 -xNG_E_A,N,C,B,D,G,0,1 = 0;
+x_A,N,C,B,D,I,0 -xNG_E_A,N,C,B,D,I,0,1 = 0;
+xNG_E_A,N,C,B,D,G,0,1 -xNG_E_A,N,C,B,D,G,0,2 = 0;
+xNG_E_A,N,C,B,D,G,0,2 -xNG_E_A,N,C,B,D,G,0,3 = 0;
+xNG_E_A,N,C,B,D,G,0,3 -xNG_E_A,N,C,B,D,G,0,4 = 0;
+xNG_E_A,N,C,B,D,G,0,4 -xNG_E_A,N,C,B,D,G,0,5 = 0;
+xNG_E_A,N,C,B,D,I,0,1 -xNG_E_A,N,C,B,D,I,0,2 = 0;
+xNG_E_A,N,C,B,D,I,0,2 -xNG_E_A,N,C,B,D,I,0,3 = 0;
+xNG_E_A,N,C,B,D,I,0,3 -xNG_E_A,N,C,B,D,I,0,4 = 0;
+xNG_E_A,N,C,B,D,I,0,4 -xNG_E_A,N,C,B,D,I,0,5 = 0;
+x_A,N,C,B,D,G,1 -xPB_E_A,N,C,B,D,G,0,1 -xPB_E_A,N,C,B,D,G,1,1 -xPB_E_A,N,C,B,D,G,2,1 = 0;
+x_A,N,C,B,D,I,1 -xPB_E_A,N,C,B,D,I,0,1 -xPB_E_A,N,C,B,D,I,1,1 -xPB_E_A,N,C,B,D,I,2,1 = 0;
+xPB_E_A,N,C,B,D,G,0,1 -xPB_E_A,N,C,B,D,G,0,2 = 0;
+xPB_E_A,N,C,B,D,G,0,2 -xPB_E_A,N,C,B,D,G,0,3 = 0;
+xPB_E_A,N,C,B,D,G,0,3 -xPB_E_A,N,C,B,D,G,0,4 = 0;
+xPB_E_A,N,C,B,D,G,0,4 -xPB_E_A,N,C,B,D,G,0,5 = 0;
+xPB_E_A,N,C,B,D,G,1,1 -xPB_E_A,N,C,B,D,G,1,2 = 0;
+xPB_E_A,N,C,B,D,G,1,2 -xPB_E_A,N,C,B,D,G,1,3 = 0;
+xPB_E_A,N,C,B,D,G,1,3 -xPB_E_A,N,C,B,D,G,1,4 = 0;
+xPB_E_A,N,C,B,D,G,1,4 -xPB_E_A,N,C,B,D,G,1,5 = 0;
+xPB_E_A,N,C,B,D,G,2,1 -xPB_E_A,N,C,B,D,G,2,2 = 0;
+xPB_E_A,N,C,B,D,G,2,2 -xPB_E_A,N,C,B,D,G,2,3 = 0;
+xPB_E_A,N,C,B,D,G,2,3 -xPB_E_A,N,C,B,D,G,2,4 = 0;
+xPB_E_A,N,C,B,D,G,2,4 -xPB_E_A,N,C,B,D,G,2,5 = 0;
+xPB_E_A,N,C,B,D,I,0,1 -xPB_E_A,N,C,B,D,I,0,2 = 0;
+xPB_E_A,N,C,B,D,I,0,2 -xPB_E_A,N,C,B,D,I,0,3 = 0;
+xPB_E_A,N,C,B,D,I,0,3 -xPB_E_A,N,C,B,D,I,0,4 = 0;
+xPB_E_A,N,C,B,D,I,0,4 -xPB_E_A,N,C,B,D,I,0,5 = 0;
+xPB_E_A,N,C,B,D,I,1,1 -xPB_E_A,N,C,B,D,I,1,2 = 0;
+xPB_E_A,N,C,B,D,I,1,2 -xPB_E_A,N,C,B,D,I,1,3 = 0;
+xPB_E_A,N,C,B,D,I,1,3 -xPB_E_A,N,C,B,D,I,1,4 = 0;
+xPB_E_A,N,C,B,D,I,1,4 -xPB_E_A,N,C,B,D,I,1,5 = 0;
+xPB_E_A,N,C,B,D,I,2,1 -xPB_E_A,N,C,B,D,I,2,2 = 0;
+xPB_E_A,N,C,B,D,I,2,2 -xPB_E_A,N,C,B,D,I,2,3 = 0;
+xPB_E_A,N,C,B,D,I,2,3 -xPB_E_A,N,C,B,D,I,2,4 = 0;
+xPB_E_A,N,C,B,D,I,2,4 -xPB_E_A,N,C,B,D,I,2,5 = 0;
+x_A,N,C,B,D,G,2 -xGS_E_A,N,C,B,D,G,0,1 = 0;
+x_A,N,C,B,D,I,2 -xGS_E_A,N,C,B,D,I,0,1 -xGS_E_A,N,C,B,D,I,1,1 -xGS_E_A,N,C,B,D,I,2,1 -xGS_E_A,N,C,B,D,I,3,1 = 0;
+xGS_E_A,N,C,B,D,G,0,1 -xGS_E_A,N,C,B,D,G,0,2 = 0;
+xGS_E_A,N,C,B,D,G,0,2 -xGS_E_A,N,C,B,D,G,0,3 = 0;
+xGS_E_A,N,C,B,D,G,0,3 -xGS_E_A,N,C,B,D,G,0,4 = 0;
+xGS_E_A,N,C,B,D,G,0,4 -xGS_E_A,N,C,B,D,G,0,5 = 0;
+xGS_E_A,N,C,B,D,I,0,1 -xGS_E_A,N,C,B,D,I,0,2 = 0;
+xGS_E_A,N,C,B,D,I,0,2 -xGS_E_A,N,C,B,D,I,0,3 = 0;
+xGS_E_A,N,C,B,D,I,0,3 -xGS_E_A,N,C,B,D,I,0,4 = 0;
+xGS_E_A,N,C,B,D,I,0,4 -xGS_E_A,N,C,B,D,I,0,5 = 0;
+xGS_E_A,N,C,B,D,I,1,1 -xGS_E_A,N,C,B,D,I,1,2 = 0;
+xGS_E_A,N,C,B,D,I,1,2 -xGS_E_A,N,C,B,D,I,1,3 = 0;
+xGS_E_A,N,C,B,D,I,1,3 -xGS_E_A,N,C,B,D,I,1,4 = 0;
+xGS_E_A,N,C,B,D,I,1,4 -xGS_E_A,N,C,B,D,I,1,5 = 0;
+xGS_E_A,N,C,B,D,I,2,1 -xGS_E_A,N,C,B,D,I,2,2 = 0;
+xGS_E_A,N,C,B,D,I,2,2 -xGS_E_A,N,C,B,D,I,2,3 = 0;
+xGS_E_A,N,C,B,D,I,2,3 -xGS_E_A,N,C,B,D,I,2,4 = 0;
+xGS_E_A,N,C,B,D,I,2,4 -xGS_E_A,N,C,B,D,I,2,5 = 0;
+xGS_E_A,N,C,B,D,I,3,1 -xGS_E_A,N,C,B,D,I,3,2 = 0;
+xGS_E_A,N,C,B,D,I,3,2 -xGS_E_A,N,C,B,D,I,3,3 = 0;
+xGS_E_A,N,C,B,D,I,3,3 -xGS_E_A,N,C,B,D,I,3,4 = 0;
+xGS_E_A,N,C,B,D,I,3,4 -xGS_E_A,N,C,B,D,I,3,5 = 0;
+x_A,N,C,B,D,G,4 -xMS_E_A,N,C,B,D,G,0,1 -xMS_E_A,N,C,B,D,G,1,1 -xMS_E_A,N,C,B,D,G,2,1 -xMS_E_A,N,C,B,D,G,3,1
 -xMS_E_A,N,C,B,D,G,4,1 = 0;
+x_A,N,C,B,D,I,4 -xMS_E_A,N,C,B,D,I,0,1 -xMS_E_A,N,C,B,D,I,1,1 -xMS_E_A,N,C,B,D,I,2,1 -xMS_E_A,N,C,B,D,I,3,1
 -xMS_E_A,N,C,B,D,I,4,1 = 0;
+xMS_E_A,N,C,B,D,G,0,1 -xMS_E_A,N,C,B,D,G,0,2 = 0;
+xMS_E_A,N,C,B,D,G,0,2 -xMS_E_A,N,C,B,D,G,0,3 = 0;
+xMS_E_A,N,C,B,D,G,0,3 -xMS_E_A,N,C,B,D,G,0,4 = 0;
+xMS_E_A,N,C,B,D,G,0,4 -xMS_E_A,N,C,B,D,G,0,5 = 0;
+xMS_E_A,N,C,B,D,G,1,1 -xMS_E_A,N,C,B,D,G,1,2 = 0;
+xMS_E_A,N,C,B,D,G,1,2 -xMS_E_A,N,C,B,D,G,1,3 = 0;
+xMS_E_A,N,C,B,D,G,1,3 -xMS_E_A,N,C,B,D,G,1,4 = 0;
+xMS_E_A,N,C,B,D,G,1,4 -xMS_E_A,N,C,B,D,G,1,5 = 0;
+xMS_E_A,N,C,B,D,G,2,1 -xMS_E_A,N,C,B,D,G,2,2 = 0;
+xMS_E_A,N,C,B,D,G,2,2 -xMS_E_A,N,C,B,D,G,2,3 = 0;
+xMS_E_A,N,C,B,D,G,2,3 -xMS_E_A,N,C,B,D,G,2,4 = 0;
+xMS_E_A,N,C,B,D,G,2,4 -xMS_E_A,N,C,B,D,G,2,5 = 0;
+xMS_E_A,N,C,B,D,G,3,1 -xMS_E_A,N,C,B,D,G,3,2 = 0;
+xMS_E_A,N,C,B,D,G,3,2 -xMS_E_A,N,C,B,D,G,3,3 = 0;
+xMS_E_A,N,C,B,D,G,3,3 -xMS_E_A,N,C,B,D,G,3,4 = 0;
+xMS_E_A,N,C,B,D,G,3,4 -xMS_E_A,N,C,B,D,G,3,5 = 0;
+xMS_E_A,N,C,B,D,G,4,1 -xMS_E_A,N,C,B,D,G,4,2 = 0;
+xMS_E_A,N,C,B,D,G,4,2 -xMS_E_A,N,C,B,D,G,4,3 = 0;
+xMS_E_A,N,C,B,D,G,4,3 -xMS_E_A,N,C,B,D,G,4,4 = 0;
+xMS_E_A,N,C,B,D,G,4,4 -xMS_E_A,N,C,B,D,G,4,5 = 0;
+xMS_E_A,N,C,B,D,I,0,1 -xMS_E_A,N,C,B,D,I,0,2 = 0;
+xMS_E_A,N,C,B,D,I,0,2 -xMS_E_A,N,C,B,D,I,0,3 = 0;
+xMS_E_A,N,C,B,D,I,0,3 -xMS_E_A,N,C,B,D,I,0,4 = 0;
+xMS_E_A,N,C,B,D,I,0,4 -xMS_E_A,N,C,B,D,I,0,5 = 0;
+xMS_E_A,N,C,B,D,I,1,1 -xMS_E_A,N,C,B,D,I,1,2 = 0;
+xMS_E_A,N,C,B,D,I,1,2 -xMS_E_A,N,C,B,D,I,1,3 = 0;
+xMS_E_A,N,C,B,D,I,1,3 -xMS_E_A,N,C,B,D,I,1,4 = 0;
+xMS_E_A,N,C,B,D,I,1,4 -xMS_E_A,N,C,B,D,I,1,5 = 0;
+xMS_E_A,N,C,B,D,I,2,1 -xMS_E_A,N,C,B,D,I,2,2 = 0;
+xMS_E_A,N,C,B,D,I,2,2 -xMS_E_A,N,C,B,D,I,2,3 = 0;
+xMS_E_A,N,C,B,D,I,2,3 -xMS_E_A,N,C,B,D,I,2,4 = 0;
+xMS_E_A,N,C,B,D,I,2,4 -xMS_E_A,N,C,B,D,I,2,5 = 0;
+xMS_E_A,N,C,B,D,I,3,1 -xMS_E_A,N,C,B,D,I,3,2 = 0;
+xMS_E_A,N,C,B,D,I,3,2 -xMS_E_A,N,C,B,D,I,3,3 = 0;
+xMS_E_A,N,C,B,D,I,3,3 -xMS_E_A,N,C,B,D,I,3,4 = 0;
+xMS_E_A,N,C,B,D,I,3,4 -xMS_E_A,N,C,B,D,I,3,5 = 0;
+xMS_E_A,N,C,B,D,I,4,1 -xMS_E_A,N,C,B,D,I,4,2 = 0;
+xMS_E_A,N,C,B,D,I,4,2 -xMS_E_A,N,C,B,D,I,4,3 = 0;
+xMS_E_A,N,C,B,D,I,4,3 -xMS_E_A,N,C,B,D,I,4,4 = 0;
+xMS_E_A,N,C,B,D,I,4,4 -xMS_E_A,N,C,B,D,I,4,5 = 0;
R103: +fire_A,N,C,B,B,1,B = 0;
R104: +fire_A,N,C,B,B,1,C = 0;
R105: +fire_A,N,C,B,B,1,D = 0;
R106: +fire_A,N,C,B,B,1,E = 0;
R107: +fire_A,N,C,B,B,1,F = 0;
R108: +fire_A,N,C,B,B,1,G = 0;
R109: +fire_A,N,C,B,B,1,H = 0;
R110: +fire_A,N,C,B,B,1,I = 0;
R111: +fire_A,N,C,B,B,2,B = 0;
R112: +fire_A,N,C,B,B,2,C = 0;
R113: +fire_A,N,C,B,B,2,D = 0;
R114: +fire_A,N,C,B,B,2,E = 0;
R115: +fire_A,N,C,B,B,2,F = 0;
R116: +fire_A,N,C,B,B,2,G = 0;
R117: +fire_A,N,C,B,B,2,H = 0;
R118: +fire_A,N,C,B,B,2,I = 0;
R119: +fire_A,N,C,B,B,3,B = 0;
R120: +fire_A,N,C,B,B,3,C = 0;
R121: +fire_A,N,C,B,B,3,D = 0;
R122: +fire_A,N,C,B,B,3,E = 0;
R123: +fire_A,N,C,B,B,3,F = 0;
R124: +fire_A,N,C,B,B,3,G = 0;
R125: +fire_A,N,C,B,B,3,H = 0;
R126: +fire_A,N,C,B,B,3,I = 0;
R127: +fire_A,N,C,B,B,4,B = 0;
R128: +fire_A,N,C,B,B,4,C = 0;
R129: +fire_A,N,C,B,B,4,D = 0;
R130: +fire_A,N,C,B,B,4,E = 0;
R131: +fire_A,N,C,B,B,4,F = 0;
R132: +fire_A,N,C,B,B,4,G = 0;
R133: +fire_A,N,C,B,B,4,H = 0;
R134: +fire_A,N,C,B,B,4,I = 0;
R135: +fire_A,N,C,B,B,5,B = 0;
R136: +fire_A,N,C,B,B,5,C = 0;
R137: +fire_A,N,C,B,B,5,D = 0;
R138: +fire_A,N,C,B,B,5,E = 0;
R139: +fire_A,N,C,B,B,5,F = 0;
R140: +fire_A,N,C,B,B,5,G = 0;
R141: +fire_A,N,C,B,B,5,H = 0;
R142: +fire_A,N,C,B,B,5,I = 0;
R143: +fire_A,N,C,B,C,1,B = 0;
R144: +fire_A,N,C,B,C,1,C = 0;
R145: +fire_A,N,C,B,C,1,D = 0;
R146: +fire_A,N,C,B,C,1,E = 0;
R147: +fire_A,N,C,B,C,1,F = 0;
R148: +fire_A,N,C,B,C,1,G = 0;
R149: +fire_A,N,C,B,C,1,H = 0;
R150: +fire_A,N,C,B,C,1,I = 0;
R151: +fire_A,N,C,B,C,2,B = 0;
R152: +fire_A,N,C,B,C,2,C = 0;
R153: +fire_A,N,C,B,C,2,D = 0;
R154: +fire_A,N,C,B,C,2,E = 0;
R155: +fire_A,N,C,B,C,2,F = 0;
R156: +fire_A,N,C,B,C,2,G = 0;
R157: +fire_A,N,C,B,C,2,H = 0;
R158: +fire_A,N,C,B,C,2,I = 0;
R159: +fire_A,N,C,B,C,3,B = 0;
R160: +fire_A,N,C,B,C,3,C = 0;
R161: +fire_A,N,C,B,C,3,D = 0;
R162: +fire_A,N,C,B,C,3,E = 0;
R163: +fire_A,N,C,B,C,3,F = 0;
R164: +fire_A,N,C,B,C,3,G = 0;
R165: +fire_A,N,C,B,C,3,H = 0;
R166: +fire_A,N,C,B,C,3,I = 0;
R167: +fire_A,N,C,B,C,4,B = 0;
R168: +fire_A,N,C,B,C,4,C = 0;
R169: +fire_A,N,C,B,C,4,D = 0;
R170: +fire_A,N,C,B,C,4,E = 0;
R171: +fire_A,N,C,B,C,4,F = 0;
R172: +fire_A,N,C,B,C,4,G = 0;
R173: +fire_A,N,C,B,C,4,H = 0;
R174: +fire_A,N,C,B,C,4,I = 0;
R175: +fire_A,N,C,B,C,5,B = 0;
R176: +fire_A,N,C,B,C,5,C = 0;
R177: +fire_A,N,C,B,C,5,D = 0;
R178: +fire_A,N,C,B,C,5,E = 0;
R179: +fire_A,N,C,B,C,5,F = 0;
R180: +fire_A,N,C,B,C,5,G = 0;
R181: +fire_A,N,C,B,C,5,H = 0;
R182: +fire_A,N,C,B,C,5,I = 0;
R183: +fire_A,N,C,B,D,1,B = 0;
R184: +fire_A,N,C,B,D,1,C = 0;
R185: +fire_A,N,C,B,D,1,D = 0;
R186: +fire_A,N,C,B,D,1,E = 0;
R187: +fire_A,N,C,B,D,1,F = 0;
R188: +fire_A,N,C,B,D,1,G = 0;
R189: +fire_A,N,C,B,D,1,H = 0;
R190: +fire_A,N,C,B,D,1,I = 0;
R191: +fire_A,N,C,B,D,2,B = 0;
R192: +fire_A,N,C,B,D,2,C = 0;
R193: +fire_A,N,C,B,D,2,D = 0;
R194: +fire_A,N,C,B,D,2,E = 0;
R195: +fire_A,N,C,B,D,2,F = 0;
R196: +fire_A,N,C,B,D,2,G = 0;
R197: +fire_A,N,C,B,D,2,H = 0;
R198: +fire_A,N,C,B,D,2,I = 0;
R199: +fire_A,N,C,B,D,3,B = 0;
R200: +fire_A,N,C,B,D,3,C = 0;
R201: +fire_A,N,C,B,D,3,D = 0;
R202: +fire_A,N,C,B,D,3,E = 0;
R203: +fire_A,N,C,B,D,3,F = 0;
R204: +fire_A,N,C,B,D,3,G = 0;
R205: +fire_A,N,C,B,D,3,H = 0;
R206: +fire_A,N,C,B,D,3,I = 0;
R207: +fire_A,N,C,B,D,4,B = 0;
R208: +fire_A,N,C,B,D,4,C = 0;
R209: +fire_A,N,C,B,D,4,D = 0;
R210: +fire_A,N,C,B,D,4,E = 0;
R211: +fire_A,N,C,B,D,4,F = 0;
R212: +fire_A,N,C,B,D,4,G = 0;
R213: +fire_A,N,C,B,D,4,H = 0;
R214: +fire_A,N,C,B,D,4,I = 0;
R215: +fire_A,N,C,B,D,5,B = 0;
R216: +fire_A,N,C,B,D,5,C = 0;
R217: +fire_A,N,C,B,D,5,D = 0;
R218: +fire_A,N,C,B,D,5,E = 0;
R219: +fire_A,N,C,B,D,5,F = 0;
R220: +fire_A,N,C,B,D,5,G = 0;
R221: +fire_A,N,C,B,D,5,H = 0;
R222: +fire_A,N,C,B,D,5,I = 0;
R223: +fire_A,N,C,B,E,1,B = 0;
R224: +fire_A,N,C,B,E,1,C = 0;
R225: +fire_A,N,C,B,E,1,D = 0;
R226: +fire_A,N,C,B,E,1,E = 0;
R227: +fire_A,N,C,B,E,1,F = 0;
R228: +fire_A,N,C,B,E,1,G = 0;
R229: +fire_A,N,C,B,E,1,H = 0;
R230: +fire_A,N,C,B,E,1,I = 0;
R231: +fire_A,N,C,B,E,2,B = 0;
R232: +fire_A,N,C,B,E,2,C = 0;
R233: +fire_A,N,C,B,E,2,D = 0;
R234: +fire_A,N,C,B,E,2,E = 0;
R235: +fire_A,N,C,B,E,2,F = 0;
R236: +fire_A,N,C,B,E,2,G = 0;
R237: +fire_A,N,C,B,E,2,H = 0;
R238: +fire_A,N,C,B,E,2,I = 0;
R239: +fire_A,N,C,B,E,3,B = 0;
R240: +fire_A,N,C,B,E,3,C = 0;
R241: +fire_A,N,C,B,E,3,D = 0;
R242: +fire_A,N,C,B,E,3,E = 0;
R243: +fire_A,N,C,B,E,3,F = 0;
R244: +fire_A,N,C,B,E,3,G = 0;
R245: +fire_A,N,C,B,E,3,H = 0;
R246: +fire_A,N,C,B,E,3,I = 0;
R247: +fire_A,N,C,B,E,4,B = 0;
R248: +fire_A,N,C,B,E,4,C = 0;
R249: +fire_A,N,C,B,E,4,D = 0;
R250: +fire_A,N,C,B,E,4,E = 0;
R251: +fire_A,N,C,B,E,4,F = 0;
R252: +fire_A,N,C,B,E,4,G = 0;
R253: +fire_A,N,C,B,E,4,H = 0;
R254: +fire_A,N,C,B,E,4,I = 0;
R255: +fire_A,N,C,B,E,5,B = 0;
R256: +fire_A,N,C,B,E,5,C = 0;
R257: +fire_A,N,C,B,E,5,D = 0;
R258: +fire_A,N,C,B,E,5,E = 0;
R259: +fire_A,N,C,B,E,5,F = 0;
R260: +fire_A,N,C,B,E,5,G = 0;
R261: +fire_A,N,C,B,E,5,H = 0;
R262: +fire_A,N,C,B,E,5,I = 0;
R263: +fire_A,N,C,B,F,1,B = 0;
R264: +fire_A,N,C,B,F,1,C = 0;
R265: +fire_A,N,C,B,F,1,D = 0;
R266: +fire_A,N,C,B,F,1,E = 0;
R267: +fire_A,N,C,B,F,1,F = 0;
R268: +fire_A,N,C,B,F,1,G = 0;
R269: +fire_A,N,C,B,F,1,H = 0;
R270: +fire_A,N,C,B,F,1,I = 0;
R271: +fire_A,N,C,B,F,2,B = 0;
R272: +fire_A,N,C,B,F,2,C = 0;
R273: +fire_A,N,C,B,F,2,D = 0;
R274: +fire_A,N,C,B,F,2,E = 0;
R275: +fire_A,N,C,B,F,2,F = 0;
R276: +fire_A,N,C,B,F,2,G = 0;
R277: +fire_A,N,C,B,F,2,H = 0;
R278: +fire_A,N,C,B,F,2,I = 0;
R279: +fire_A,N,C,B,F,3,B = 0;
R280: +fire_A,N,C,B,F,3,C = 0;
R281: +fire_A,N,C,B,F,3,D = 0;
R282: +fire_A,N,C,B,F,3,E = 0;
R283: +fire_A,N,C,B,F,3,F = 0;
R284: +fire_A,N,C,B,F,3,G = 0;
R285: +fire_A,N,C,B,F,3,H = 0;
R286: +fire_A,N,C,B,F,3,I = 0;
R287: +fire_A,N,C,B,F,4,B = 0;
R288: +fire_A,N,C,B,F,4,C = 0;
R289: +fire_A,N,C,B,F,4,D = 0;
R290: +fire_A,N,C,B,F,4,E = 0;
R291: +fire_A,N,C,B,F,4,F = 0;
R292: +fire_A,N,C,B,F,4,G = 0;
R293: +fire_A,N,C,B,F,4,H = 0;
R294: +fire_A,N,C,B,F,4,I = 0;
R295: +fire_A,N,C,B,F,5,B = 0;
R296: +fire_A,N,C,B,F,5,C = 0;
R297: +fire_A,N,C,B,F,5,D = 0;
R298: +fire_A,N,C,B,F,5,E = 0;
R299: +fire_A,N,C,B,F,5,F = 0;
R300: +fire_A,N,C,B,F,5,G = 0;
R301: +fire_A,N,C,B,F,5,H = 0;
R302: +fire_A,N,C,B,F,5,I = 0;
R303: +fire_A,N,C,B,G,1,B = 0;
R304: +fire_A,N,C,B,G,1,C = 0;
R305: +fire_A,N,C,B,G,1,D = 0;
R306: +fire_A,N,C,B,G,1,E = 0;
R307: +fire_A,N,C,B,G,1,F = 0;
R308: +fire_A,N,C,B,G,1,G = 0;
R309: +fire_A,N,C,B,G,1,H = 0;
R310: +fire_A,N,C,B,G,1,I = 0;
R311: +fire_A,N,C,B,G,2,B = 0;
R312: +fire_A,N,C,B,G,2,C = 0;
R313: +fire_A,N,C,B,G,2,D = 0;
R314: +fire_A,N,C,B,G,2,E = 0;
R315: +fire_A,N,C,B,G,2,F = 0;
R316: +fire_A,N,C,B,G,2,G = 0;
R317: +fire_A,N,C,B,G,2,H = 0;
R318: +fire_A,N,C,B,G,2,I = 0;
R319: +fire_A,N,C,B,G,3,B = 0;
R320: +fire_A,N,C,B,G,3,C = 0;
R321: +fire_A,N,C,B,G,3,D = 0;
R322: +fire_A,N,C,B,G,3,E = 0;
R323: +fire_A,N,C,B,G,3,F = 0;
R324: +fire_A,N,C,B,G,3,G = 0;
R325: +fire_A,N,C,B,G,3,H = 0;
R326: +fire_A,N,C,B,G,3,I = 0;
R327: +fire_A,N,C,B,G,4,B = 0;
R328: +fire_A,N,C,B,G,4,C = 0;
R329: +fire_A,N,C,B,G,4,D = 0;
R330: +fire_A,N,C,B,G,4,E = 0;
R331: +fire_A,N,C,B,G,4,F = 0;
R332: +fire_A,N,C,B,G,4,G = 0;
R333: +fire_A,N,C,B,G,4,H = 0;
R334: +fire_A,N,C,B,G,4,I = 0;
R335: +fire_A,N,C,B,G,5,B = 0;
R336: +fire_A,N,C,B,G,5,C = 0;
R337: +fire_A,N,C,B,G,5,D = 0;
R338: +fire_A,N,C,B,G,5,E = 0;
R339: +fire_A,N,C,B,G,5,F = 0;
R340: +fire_A,N,C,B,G,5,G = 0;
R341: +fire_A,N,C,B,G,5,H = 0;
R342: +fire_A,N,C,B,G,5,I = 0;
R343: +fire_A,N,C,B,H,1,B = 0;
R344: +fire_A,N,C,B,H,1,C = 0;
R345: +fire_A,N,C,B,H,1,D = 0;
R346: +fire_A,N,C,B,H,1,E = 0;
R347: +fire_A,N,C,B,H,1,F = 0;
R348: +fire_A,N,C,B,H,1,G = 0;
R349: +fire_A,N,C,B,H,1,H = 0;
R350: +fire_A,N,C,B,H,1,I = 0;
R351: +fire_A,N,C,B,H,2,B = 0;
R352: +fire_A,N,C,B,H,2,C = 0;
R353: +fire_A,N,C,B,H,2,D = 0;
R354: +fire_A,N,C,B,H,2,E = 0;
R355: +fire_A,N,C,B,H,2,F = 0;
R356: +fire_A,N,C,B,H,2,G = 0;
R357: +fire_A,N,C,B,H,2,H = 0;
R358: +fire_A,N,C,B,H,2,I = 0;
R359: +fire_A,N,C,B,H,3,B = 0;
R360: +fire_A,N,C,B,H,3,C = 0;
R361: +fire_A,N,C,B,H,3,D = 0;
R362: +fire_A,N,C,B,H,3,E = 0;
R363: +fire_A,N,C,B,H,3,F = 0;
R364: +fire_A,N,C,B,H,3,G = 0;
R365: +fire_A,N,C,B,H,3,H = 0;
R366: +fire_A,N,C,B,H,3,I = 0;
R367: +fire_A,N,C,B,H,4,B = 0;
R368: +fire_A,N,C,B,H,4,C = 0;
R369: +fire_A,N,C,B,H,4,D = 0;
R370: +fire_A,N,C,B,H,4,E = 0;
R371: +fire_A,N,C,B,H,4,F = 0;
R372: +fire_A,N,C,B,H,4,G = 0;
R373: +fire_A,N,C,B,H,4,H = 0;
R374: +fire_A,N,C,B,H,4,I = 0;
R375: +fire_A,N,C,B,H,5,B = 0;
R376: +fire_A,N,C,B,H,5,C = 0;
R377: +fire_A,N,C,B,H,5,D = 0;
R378: +fire_A,N,C,B,H,5,E = 0;
R379: +fire_A,N,C,B,H,5,F = 0;
R380: +fire_A,N,C,B,H,5,G = 0;
R381: +fire_A,N,C,B,H,5,H = 0;
R382: +fire_A,N,C,B,H,5,I = 0;
R383: +fire_A,N,C,B,I,1,B = 0;
R384: +fire_A,N,C,B,I,1,C = 0;
R385: +fire_A,N,C,B,I,1,D = 0;
R386: +fire_A,N,C,B,I,1,E = 0;
R387: +fire_A,N,C,B,I,1,F = 0;
R388: +fire_A,N,C,B,I,1,G = 0;
R389: +fire_A,N,C,B,I,1,H = 0;
R390: +fire_A,N,C,B,I,1,I = 0;
R391: +fire_A,N,C,B,I,2,B = 0;
R392: +fire_A,N,C,B,I,2,C = 0;
R393: +fire_A,N,C,B,I,2,D = 0;
R394: +fire_A,N,C,B,I,2,E = 0;
R395: +fire_A,N,C,B,I,2,F = 0;
R396: +fire_A,N,C,B,I,2,G = 0;
R397: +fire_A,N,C,B,I,2,H = 0;
R398: +fire_A,N,C,B,I,2,I = 0;
R399: +fire_A,N,C,B,I,3,B = 0;
R400: +fire_A,N,C,B,I,3,C = 0;
R401: +fire_A,N,C,B,I,3,D = 0;
R402: +fire_A,N,C,B,I,3,E = 0;
R403: +fire_A,N,C,B,I,3,F = 0;
R404: +fire_A,N,C,B,I,3,G = 0;
R405: +fire_A,N,C,B,I,3,H = 0;
R406: +fire_A,N,C,B,I,3,I = 0;
R407: +fire_A,N,C,B,I,4,B = 0;
R408: +fire_A,N,C,B,I,4,C = 0;
R409: +fire_A,N,C,B,I,4,D = 0;
R410: +fire_A,N,C,B,I,4,E = 0;
R411: +fire_A,N,C,B,I,4,F = 0;
R412: +fire_A,N,C,B,I,4,G = 0;
R413: +fire_A,N,C,B,I,4,H = 0;
R414: +fire_A,N,C,B,I,4,I = 0;
R415: +fire_A,N,C,B,I,5,B = 0;
R416: +fire_A,N,C,B,I,5,C = 0;
R417: +fire_A,N,C,B,I,5,D = 0;
R418: +fire_A,N,C,B,I,5,E = 0;
R419: +fire_A,N,C,B,I,5,F = 0;
R420: +fire_A,N,C,B,I,5,G = 0;
R421: +fire_A,N,C,B,I,5,H = 0;
R422: +fire_A,N,C,B,I,5,I = 0;
-xNG_R_A,N,C,B,B,0,2,1 -xPB_R_A,N,C,B,B,0,2,1 -xPB_R_A,N,C,B,B,1,2,1 -xPB_R_A,N,C,B,B,2,2,1 -xGS_R_A,N,C,B,B,0,2,1
 -xGS_R_A,N,C,B,B,1,2,1 -xGS_R_A,N,C,B,B,2,2,1 -xGS_R_A,N,C,B,B,3,2,1 +fire_A,N,C,B,B,1,B +fire_A,N,C,B,C,1,B
 +fire_A,N,C,B,D,1,B +fire_A,N,C,B,E,1,B +fire_A,N,C,B,F,1,B +fire_A,N,C,B,G,1,B +fire_A,N,C,B,H,1,B
 +fire_A,N,C,B,I,1,B = 0;
-xNG_R_A,N,C,B,C,0,2,1 -xPB_R_A,N,C,B,C,0,2,1 -xPB_R_A,N,C,B,C,1,2,1 -xPB_R_A,N,C,B,C,2,2,1 -xGS_R_A,N,C,B,C,0,2,1
 -xGS_R_A,N,C,B,C,1,2,1 -xGS_R_A,N,C,B,C,2,2,1 -xGS_R_A,N,C,B,C,3,2,1 +fire_A,N,C,B,B,1,C +fire_A,N,C,B,C,1,C
 +fire_A,N,C,B,D,1,C +fire_A,N,C,B,E,1,C +fire_A,N,C,B,F,1,C +fire_A,N,C,B,G,1,C +fire_A,N,C,B,H,1,C
 +fire_A,N,C,B,I,1,C = 0;
-xNG_R_A,N,C,B,D,0,2,1 -xPB_R_A,N,C,B,D,0,2,1 -xGS_R_A,N,C,B,D,0,2,1 -xGS_R_A,N,C,B,D,1,2,1 -xGS_R_A,N,C,B,D,2,2,1
 -xGS_R_A,N,C,B,D,3,2,1 +fire_A,N,C,B,B,1,D +fire_A,N,C,B,C,1,D +fire_A,N,C,B,D,1,D +fire_A,N,C,B,E,1,D
 +fire_A,N,C,B,F,1,D +fire_A,N,C,B,G,1,D +fire_A,N,C,B,H,1,D +fire_A,N,C,B,I,1,D = 0;
-xNG_R_A,N,C,B,E,0,2,1 -xPB_R_A,N,C,B,E,0,2,1 -xPB_R_A,N,C,B,E,1,2,1 -xPB_R_A,N,C,B,E,2,2,1 +fire_A,N,C,B,B,1,E
 +fire_A,N,C,B,C,1,E +fire_A,N,C,B,D,1,E +fire_A,N,C,B,E,1,E +fire_A,N,C,B,F,1,E +fire_A,N,C,B,G,1,E
 +fire_A,N,C,B,H,1,E +fire_A,N,C,B,I,1,E = 0;
-xNG_R_A,N,C,B,F,0,2,1 -xPB_R_A,N,C,B,F,0,2,1 -xPB_R_A,N,C,B,F,1,2,1 -xPB_R_A,N,C,B,F,2,2,1 -xGS_R_A,N,C,B,F,0,2,1
 -xGS_R_A,N,C,B,F,1,2,1 -xGS_R_A,N,C,B,F,2,2,1 -xGS_R_A,N,C,B,F,3,2,1 +fire_A,N,C,B,B,1,F +fire_A,N,C,B,C,1,F
 +fire_A,N,C,B,D,1,F +fire_A,N,C,B,E,1,F +fire_A,N,C,B,F,1,F +fire_A,N,C,B,G,1,F +fire_A,N,C,B,H,1,F
 +fire_A,N,C,B,I,1,F = 0;
-xNG_R_A,N,C,B,G,0,2,1 +fire_A,N,C,B,B,1,G +fire_A,N,C,B,C,1,G +fire_A,N,C,B,D,1,G +fire_A,N,C,B,E,1,G
 +fire_A,N,C,B,F,1,G +fire_A,N,C,B,G,1,G +fire_A,N,C,B,H,1,G +fire_A,N,C,B,I,1,G = 0;
-xNG_R_A,N,C,B,H,0,2,1 -xPB_R_A,N,C,B,H,0,2,1 -xPB_R_A,N,C,B,H,1,2,1 -xPB_R_A,N,C,B,H,2,2,1 -xGS_R_A,N,C,B,H,0,2,1
 -xGS_R_A,N,C,B,H,1,2,1 -xGS_R_A,N,C,B,H,2,2,1 -xGS_R_A,N,C,B,H,3,2,1 +fire_A,N,C,B,B,1,H +fire_A,N,C,B,C,1,H
 +fire_A,N,C,B,D,1,H +fire_A,N,C,B,E,1,H +fire_A,N,C,B,F,1,H +fire_A,N,C,B,G,1,H +fire_A,N,C,B,H,1,H
 +fire_A,N,C,B,I,1,H = 0;
-xNG_R_A,N,C,B,I,0,2,1 -xPB_R_A,N,C,B,I,0,2,1 -xPB_R_A,N,C,B,I,1,2,1 -xPB_R_A,N,C,B,I,2,2,1 -xGS_R_A,N,C,B,I,0,2,1
 -xGS_R_A,N,C,B,I,1,2,1 -xGS_R_A,N,C,B,I,2,2,1 -xGS_R_A,N,C,B,I,3,2,1 +fire_A,N,C,B,B,1,I +fire_A,N,C,B,C,1,I
 +fire_A,N,C,B,D,1,I +fire_A,N,C,B,E,1,I +fire_A,N,C,B,F,1,I +fire_A,N,C,B,G,1,I +fire_A,N,C,B,H,1,I
 +fire_A,N,C,B,I,1,I = 0;
-xNG_R_A,N,C,B,B,0,3,1 -xPB_R_A,N,C,B,B,0,3,1 -xPB_R_A,N,C,B,B,1,3,1 -xPB_R_A,N,C,B,B,2,3,1 -xGS_R_A,N,C,B,B,0,3,1
 -xGS_R_A,N,C,B,B,1,3,1 -xGS_R_A,N,C,B,B,2,3,1 -xGS_R_A,N,C,B,B,3,3,1 +fire_A,N,C,B,B,2,B +fire_A,N,C,B,C,2,B
 +fire_A,N,C,B,D,2,B +fire_A,N,C,B,E,2,B +fire_A,N,C,B,F,2,B +fire_A,N,C,B,G,2,B +fire_A,N,C,B,H,2,B
 +fire_A,N,C,B,I,2,B = 0;
-xNG_R_A,N,C,B,C,0,3,1 -xPB_R_A,N,C,B,C,0,3,1 -xPB_R_A,N,C,B,C,1,3,1 -xPB_R_A,N,C,B,C,2,3,1 -xGS_R_A,N,C,B,C,0,3,1
 -xGS_R_A,N,C,B,C,1,3,1 -xGS_R_A,N,C,B,C,2,3,1 -xGS_R_A,N,C,B,C,3,3,1 +fire_A,N,C,B,B,2,C +fire_A,N,C,B,C,2,C
 +fire_A,N,C,B,D,2,C +fire_A,N,C,B,E,2,C +fire_A,N,C,B,F,2,C +fire_A,N,C,B,G,2,C +fire_A,N,C,B,H,2,C
 +fire_A,N,C,B,I,2,C = 0;
-xNG_R_A,N,C,B,D,0,3,1 -xPB_R_A,N,C,B,D,0,3,1 -xGS_R_A,N,C,B,D,0,3,1 -xGS_R_A,N,C,B,D,1,3,1 -xGS_R_A,N,C,B,D,2,3,1
 -xGS_R_A,N,C,B,D,3,3,1 +fire_A,N,C,B,B,2,D +fire_A,N,C,B,C,2,D +fire_A,N,C,B,D,2,D +fire_A,N,C,B,E,2,D
 +fire_A,N,C,B,F,2,D +fire_A,N,C,B,G,2,D +fire_A,N,C,B,H,2,D +fire_A,N,C,B,I,2,D = 0;
-xNG_R_A,N,C,B,E,0,3,1 -xPB_R_A,N,C,B,E,0,3,1 -xPB_R_A,N,C,B,E,1,3,1 -xPB_R_A,N,C,B,E,2,3,1 +fire_A,N,C,B,B,2,E
 +fire_A,N,C,B,C,2,E +fire_A,N,C,B,D,2,E +fire_A,N,C,B,E,2,E +fire_A,N,C,B,F,2,E +fire_A,N,C,B,G,2,E
 +fire_A,N,C,B,H,2,E +fire_A,N,C,B,I,2,E = 0;
-xNG_R_A,N,C,B,F,0,3,1 -xPB_R_A,N,C,B,F,0,3,1 -xPB_R_A,N,C,B,F,1,3,1 -xPB_R_A,N,C,B,F,2,3,1 -xGS_R_A,N,C,B,F,0,3,1
 -xGS_R_A,N,C,B,F,1,3,1 -xGS_R_A,N,C,B,F,2,3,1 -xGS_R_A,N,C,B,F,3,3,1 +fire_A,N,C,B,B,2,F +fire_A,N,C,B,C,2,F
 +fire_A,N,C,B,D,2,F +fire_A,N,C,B,E,2,F +fire_A,N,C,B,F,2,F +fire_A,N,C,B,G,2,F +fire_A,N,C,B,H,2,F
 +fire_A,N,C,B,I,2,F = 0;
-xNG_R_A,N,C,B,G,0,3,1 +fire_A,N,C,B,B,2,G +fire_A,N,C,B,C,2,G +fire_A,N,C,B,D,2,G +fire_A,N,C,B,E,2,G
 +fire_A,N,C,B,F,2,G +fire_A,N,C,B,G,2,G +fire_A,N,C,B,H,2,G +fire_A,N,C,B,I,2,G = 0;
-xNG_R_A,N,C,B,H,0,3,1 -xPB_R_A,N,C,B,H,0,3,1 -xPB_R_A,N,C,B,H,1,3,1 -xPB_R_A,N,C,B,H,2,3,1 -xGS_R_A,N,C,B,H,0,3,1
 -xGS_R_A,N,C,B,H,1,3,1 -xGS_R_A,N,C,B,H,2,3,1 -xGS_R_A,N,C,B,H,3,3,1 +fire_A,N,C,B,B,2,H +fire_A,N,C,B,C,2,H
 +fire_A,N,C,B,D,2,H +fire_A,N,C,B,E,2,H +fire_A,N,C,B,F,2,H +fire_A,N,C,B,G,2,H +fire_A,N,C,B,H,2,H
 +fire_A,N,C,B,I,2,H = 0;
-xNG_R_A,N,C,B,I,0,3,1 -xPB_R_A,N,C,B,I,0,3,1 -xPB_R_A,N,C,B,I,1,3,1 -xPB_R_A,N,C,B,I,2,3,1 -xGS_R_A,N,C,B,I,0,3,1
 -xGS_R_A,N,C,B,I,1,3,1 -xGS_R_A,N,C,B,I,2,3,1 -xGS_R_A,N,C,B,I,3,3,1 +fire_A,N,C,B,B,2,I +fire_A,N,C,B,C,2,I
 +fire_A,N,C,B,D,2,I +fire_A,N,C,B,E,2,I +fire_A,N,C,B,F,2,I +fire_A,N,C,B,G,2,I +fire_A,N,C,B,H,2,I
 +fire_A,N,C,B,I,2,I = 0;
-xNG_R_A,N,C,B,B,0,4,1 -xPB_R_A,N,C,B,B,0,4,1 -xPB_R_A,N,C,B,B,1,4,1 -xPB_R_A,N,C,B,B,2,4,1 -xGS_R_A,N,C,B,B,0,4,1
 -xGS_R_A,N,C,B,B,1,4,1 -xGS_R_A,N,C,B,B,2,4,1 -xGS_R_A,N,C,B,B,3,4,1 +fire_A,N,C,B,B,3,B +fire_A,N,C,B,C,3,B
 +fire_A,N,C,B,D,3,B +fire_A,N,C,B,E,3,B +fire_A,N,C,B,F,3,B +fire_A,N,C,B,G,3,B +fire_A,N,C,B,H,3,B
 +fire_A,N,C,B,I,3,B = 0;
-xNG_R_A,N,C,B,C,0,4,1 -xPB_R_A,N,C,B,C,0,4,1 -xPB_R_A,N,C,B,C,1,4,1 -xPB_R_A,N,C,B,C,2,4,1 -xGS_R_A,N,C,B,C,0,4,1
 -xGS_R_A,N,C,B,C,1,4,1 -xGS_R_A,N,C,B,C,2,4,1 -xGS_R_A,N,C,B,C,3,4,1 +fire_A,N,C,B,B,3,C +fire_A,N,C,B,C,3,C
 +fire_A,N,C,B,D,3,C +fire_A,N,C,B,E,3,C +fire_A,N,C,B,F,3,C +fire_A,N,C,B,G,3,C +fire_A,N,C,B,H,3,C
 +fire_A,N,C,B,I,3,C = 0;
-xNG_R_A,N,C,B,D,0,4,1 -xPB_R_A,N,C,B,D,0,4,1 -xGS_R_A,N,C,B,D,0,4,1 -xGS_R_A,N,C,B,D,1,4,1 -xGS_R_A,N,C,B,D,2,4,1
 -xGS_R_A,N,C,B,D,3,4,1 +fire_A,N,C,B,B,3,D +fire_A,N,C,B,C,3,D +fire_A,N,C,B,D,3,D +fire_A,N,C,B,E,3,D
 +fire_A,N,C,B,F,3,D +fire_A,N,C,B,G,3,D +fire_A,N,C,B,H,3,D +fire_A,N,C,B,I,3,D = 0;
-xNG_R_A,N,C,B,E,0,4,1 -xPB_R_A,N,C,B,E,0,4,1 -xPB_R_A,N,C,B,E,1,4,1 -xPB_R_A,N,C,B,E,2,4,1 +fire_A,N,C,B,B,3,E
 +fire_A,N,C,B,C,3,E +fire_A,N,C,B,D,3,E +fire_A,N,C,B,E,3,E +fire_A,N,C,B,F,3,E +fire_A,N,C,B,G,3,E
 +fire_A,N,C,B,H,3,E +fire_A,N,C,B,I,3,E = 0;
-xNG_R_A,N,C,B,F,0,4,1 -xPB_R_A,N,C,B,F,0,4,1 -xPB_R_A,N,C,B,F,1,4,1 -xPB_R_A,N,C,B,F,2,4,1 -xGS_R_A,N,C,B,F,0,4,1
 -xGS_R_A,N,C,B,F,1,4,1 -xGS_R_A,N,C,B,F,2,4,1 -xGS_R_A,N,C,B,F,3,4,1 +fire_A,N,C,B,B,3,F +fire_A,N,C,B,C,3,F
 +fire_A,N,C,B,D,3,F +fire_A,N,C,B,E,3,F +fire_A,N,C,B,F,3,F +fire_A,N,C,B,G,3,F +fire_A,N,C,B,H,3,F
 +fire_A,N,C,B,I,3,F = 0;
-xNG_R_A,N,C,B,G,0,4,1 +fire_A,N,C,B,B,3,G +fire_A,N,C,B,C,3,G +fire_A,N,C,B,D,3,G +fire_A,N,C,B,E,3,G
 +fire_A,N,C,B,F,3,G +fire_A,N,C,B,G,3,G +fire_A,N,C,B,H,3,G +fire_A,N,C,B,I,3,G = 0;
-xNG_R_A,N,C,B,H,0,4,1 -xPB_R_A,N,C,B,H,0,4,1 -xPB_R_A,N,C,B,H,1,4,1 -xPB_R_A,N,C,B,H,2,4,1 -xGS_R_A,N,C,B,H,0,4,1
 -xGS_R_A,N,C,B,H,1,4,1 -xGS_R_A,N,C,B,H,2,4,1 -xGS_R_A,N,C,B,H,3,4,1 +fire_A,N,C,B,B,3,H +fire_A,N,C,B,C,3,H
 +fire_A,N,C,B,D,3,H +fire_A,N,C,B,E,3,H +fire_A,N,C,B,F,3,H +fire_A,N,C,B,G,3,H +fire_A,N,C,B,H,3,H
 +fire_A,N,C,B,I,3,H = 0;
-xNG_R_A,N,C,B,I,0,4,1 -xPB_R_A,N,C,B,I,0,4,1 -xPB_R_A,N,C,B,I,1,4,1 -xPB_R_A,N,C,B,I,2,4,1 -xGS_R_A,N,C,B,I,0,4,1
 -xGS_R_A,N,C,B,I,1,4,1 -xGS_R_A,N,C,B,I,2,4,1 -xGS_R_A,N,C,B,I,3,4,1 +fire_A,N,C,B,B,3,I +fire_A,N,C,B,C,3,I
 +fire_A,N,C,B,D,3,I +fire_A,N,C,B,E,3,I +fire_A,N,C,B,F,3,I +fire_A,N,C,B,G,3,I +fire_A,N,C,B,H,3,I
 +fire_A,N,C,B,I,3,I = 0;
-xNG_R_A,N,C,B,B,0,5,1 -xPB_R_A,N,C,B,B,0,5,1 -xPB_R_A,N,C,B,B,1,5,1 -xPB_R_A,N,C,B,B,2,5,1 -xGS_R_A,N,C,B,B,0,5,1
 -xGS_R_A,N,C,B,B,1,5,1 -xGS_R_A,N,C,B,B,2,5,1 -xGS_R_A,N,C,B,B,3,5,1 +fire_A,N,C,B,B,4,B +fire_A,N,C,B,C,4,B
 +fire_A,N,C,B,D,4,B +fire_A,N,C,B,E,4,B +fire_A,N,C,B,F,4,B +fire_A,N,C,B,G,4,B +fire_A,N,C,B,H,4,B
 +fire_A,N,C,B,I,4,B = 0;
-xNG_R_A,N,C,B,C,0,5,1 -xPB_R_A,N,C,B,C,0,5,1 -xPB_R_A,N,C,B,C,1,5,1 -xPB_R_A,N,C,B,C,2,5,1 -xGS_R_A,N,C,B,C,0,5,1
 -xGS_R_A,N,C,B,C,1,5,1 -xGS_R_A,N,C,B,C,2,5,1 -xGS_R_A,N,C,B,C,3,5,1 +fire_A,N,C,B,B,4,C +fire_A,N,C,B,C,4,C
 +fire_A,N,C,B,D,4,C +fire_A,N,C,B,E,4,C +fire_A,N,C,B,F,4,C +fire_A,N,C,B,G,4,C +fire_A,N,C,B,H,4,C
 +fire_A,N,C,B,I,4,C = 0;
-xNG_R_A,N,C,B,D,0,5,1 -xPB_R_A,N,C,B,D,0,5,1 -xGS_R_A,N,C,B,D,0,5,1 -xGS_R_A,N,C,B,D,1,5,1 -xGS_R_A,N,C,B,D,2,5,1
 -xGS_R_A,N,C,B,D,3,5,1 +fire_A,N,C,B,B,4,D +fire_A,N,C,B,C,4,D +fire_A,N,C,B,D,4,D +fire_A,N,C,B,E,4,D
 +fire_A,N,C,B,F,4,D +fire_A,N,C,B,G,4,D +fire_A,N,C,B,H,4,D +fire_A,N,C,B,I,4,D = 0;
-xNG_R_A,N,C,B,E,0,5,1 -xPB_R_A,N,C,B,E,0,5,1 -xPB_R_A,N,C,B,E,1,5,1 -xPB_R_A,N,C,B,E,2,5,1 +fire_A,N,C,B,B,4,E
 +fire_A,N,C,B,C,4,E +fire_A,N,C,B,D,4,E +fire_A,N,C,B,E,4,E +fire_A,N,C,B,F,4,E +fire_A,N,C,B,G,4,E
 +fire_A,N,C,B,H,4,E +fire_A,N,C,B,I,4,E = 0;
-xNG_R_A,N,C,B,F,0,5,1 -xPB_R_A,N,C,B,F,0,5,1 -xPB_R_A,N,C,B,F,1,5,1 -xPB_R_A,N,C,B,F,2,5,1 -xGS_R_A,N,C,B,F,0,5,1
 -xGS_R_A,N,C,B,F,1,5,1 -xGS_R_A,N,C,B,F,2,5,1 -xGS_R_A,N,C,B,F,3,5,1 +fire_A,N,C,B,B,4,F +fire_A,N,C,B,C,4,F
 +fire_A,N,C,B,D,4,F +fire_A,N,C,B,E,4,F +fire_A,N,C,B,F,4,F +fire_A,N,C,B,G,4,F +fire_A,N,C,B,H,4,F
 +fire_A,N,C,B,I,4,F = 0;
-xNG_R_A,N,C,B,G,0,5,1 +fire_A,N,C,B,B,4,G +fire_A,N,C,B,C,4,G +fire_A,N,C,B,D,4,G +fire_A,N,C,B,E,4,G
 +fire_A,N,C,B,F,4,G +fire_A,N,C,B,G,4,G +fire_A,N,C,B,H,4,G +fire_A,N,C,B,I,4,G = 0;
-xNG_R_A,N,C,B,H,0,5,1 -xPB_R_A,N,C,B,H,0,5,1 -xPB_R_A,N,C,B,H,1,5,1 -xPB_R_A,N,C,B,H,2,5,1 -xGS_R_A,N,C,B,H,0,5,1
 -xGS_R_A,N,C,B,H,1,5,1 -xGS_R_A,N,C,B,H,2,5,1 -xGS_R_A,N,C,B,H,3,5,1 +fire_A,N,C,B,B,4,H +fire_A,N,C,B,C,4,H
 +fire_A,N,C,B,D,4,H +fire_A,N,C,B,E,4,H +fire_A,N,C,B,F,4,H +fire_A,N,C,B,G,4,H +fire_A,N,C,B,H,4,H
 +fire_A,N,C,B,I,4,H = 0;
-xNG_R_A,N,C,B,I,0,5,1 -xPB_R_A,N,C,B,I,0,5,1 -xPB_R_A,N,C,B,I,1,5,1 -xPB_R_A,N,C,B,I,2,5,1 -xGS_R_A,N,C,B,I,0,5,1
 -xGS_R_A,N,C,B,I,1,5,1 -xGS_R_A,N,C,B,I,2,5,1 -xGS_R_A,N,C,B,I,3,5,1 +fire_A,N,C,B,B,4,I +fire_A,N,C,B,C,4,I
 +fire_A,N,C,B,D,4,I +fire_A,N,C,B,E,4,I +fire_A,N,C,B,F,4,I +fire_A,N,C,B,G,4,I +fire_A,N,C,B,H,4,I
 +fire_A,N,C,B,I,4,I = 0;
+xNG_R_A,N,C,B,B,0,2,1 -xNG_R_A,N,C,B,B,0,3,2 = 0;
+xNG_R_A,N,C,B,B,0,3,1 -xNG_R_A,N,C,B,B,0,4,2 = 0;
+xNG_R_A,N,C,B,B,0,3,2 -xNG_R_A,N,C,B,B,0,4,3 = 0;
+xNG_R_A,N,C,B,B,0,4,1 -xNG_R_A,N,C,B,B,0,5,2 = 0;
+xNG_R_A,N,C,B,B,0,4,2 -xNG_R_A,N,C,B,B,0,5,3 = 0;
+xNG_R_A,N,C,B,B,0,4,3 -xNG_R_A,N,C,B,B,0,5,4 = 0;
+xNG_R_A,N,C,B,C,0,2,1 -xNG_R_A,N,C,B,C,0,3,2 = 0;
+xNG_R_A,N,C,B,C,0,3,1 -xNG_R_A,N,C,B,C,0,4,2 = 0;
+xNG_R_A,N,C,B,C,0,3,2 -xNG_R_A,N,C,B,C,0,4,3 = 0;
+xNG_R_A,N,C,B,C,0,4,1 -xNG_R_A,N,C,B,C,0,5,2 = 0;
+xNG_R_A,N,C,B,C,0,4,2 -xNG_R_A,N,C,B,C,0,5,3 = 0;
+xNG_R_A,N,C,B,C,0,4,3 -xNG_R_A,N,C,B,C,0,5,4 = 0;
+xNG_R_A,N,C,B,D,0,2,1 -xNG_R_A,N,C,B,D,0,3,2 = 0;
+xNG_R_A,N,C,B,D,0,3,1 -xNG_R_A,N,C,B,D,0,4,2 = 0;
+xNG_R_A,N,C,B,D,0,3,2 -xNG_R_A,N,C,B,D,0,4,3 = 0;
+xNG_R_A,N,C,B,D,0,4,1 -xNG_R_A,N,C,B,D,0,5,2 = 0;
+xNG_R_A,N,C,B,D,0,4,2 -xNG_R_A,N,C,B,D,0,5,3 = 0;
+xNG_R_A,N,C,B,D,0,4,3 -xNG_R_A,N,C,B,D,0,5,4 = 0;
+xNG_R_A,N,C,B,E,0,2,1 -xNG_R_A,N,C,B,E,0,3,2 = 0;
+xNG_R_A,N,C,B,E,0,3,1 -xNG_R_A,N,C,B,E,0,4,2 = 0;
+xNG_R_A,N,C,B,E,0,3,2 -xNG_R_A,N,C,B,E,0,4,3 = 0;
+xNG_R_A,N,C,B,E,0,4,1 -xNG_R_A,N,C,B,E,0,5,2 = 0;
+xNG_R_A,N,C,B,E,0,4,2 -xNG_R_A,N,C,B,E,0,5,3 = 0;
+xNG_R_A,N,C,B,E,0,4,3 -xNG_R_A,N,C,B,E,0,5,4 = 0;
+xNG_R_A,N,C,B,F,0,2,1 -xNG_R_A,N,C,B,F,0,3,2 = 0;
+xNG_R_A,N,C,B,F,0,3,1 -xNG_R_A,N,C,B,F,0,4,2 = 0;
+xNG_R_A,N,C,B,F,0,3,2 -xNG_R_A,N,C,B,F,0,4,3 = 0;
+xNG_R_A,N,C,B,F,0,4,1 -xNG_R_A,N,C,B,F,0,5,2 = 0;
+xNG_R_A,N,C,B,F,0,4,2 -xNG_R_A,N,C,B,F,0,5,3 = 0;
+xNG_R_A,N,C,B,F,0,4,3 -xNG_R_A,N,C,B,F,0,5,4 = 0;
+xNG_R_A,N,C,B,G,0,2,1 -xNG_R_A,N,C,B,G,0,3,2 = 0;
+xNG_R_A,N,C,B,G,0,3,1 -xNG_R_A,N,C,B,G,0,4,2 = 0;
+xNG_R_A,N,C,B,G,0,3,2 -xNG_R_A,N,C,B,G,0,4,3 = 0;
+xNG_R_A,N,C,B,G,0,4,1 -xNG_R_A,N,C,B,G,0,5,2 = 0;
+xNG_R_A,N,C,B,G,0,4,2 -xNG_R_A,N,C,B,G,0,5,3 = 0;
+xNG_R_A,N,C,B,G,0,4,3 -xNG_R_A,N,C,B,G,0,5,4 = 0;
+xNG_R_A,N,C,B,H,0,2,1 -xNG_R_A,N,C,B,H,0,3,2 = 0;
+xNG_R_A,N,C,B,H,0,3,1 -xNG_R_A,N,C,B,H,0,4,2 = 0;
+xNG_R_A,N,C,B,H,0,3,2 -xNG_R_A,N,C,B,H,0,4,3 = 0;
+xNG_R_A,N,C,B,H,0,4,1 -xNG_R_A,N,C,B,H,0,5,2 = 0;
+xNG_R_A,N,C,B,H,0,4,2 -xNG_R_A,N,C,B,H,0,5,3 = 0;
+xNG_R_A,N,C,B,H,0,4,3 -xNG_R_A,N,C,B,H,0,5,4 = 0;
+xNG_R_A,N,C,B,I,0,2,1 -xNG_R_A,N,C,B,I,0,3,2 = 0;
+xNG_R_A,N,C,B,I,0,3,1 -xNG_R_A,N,C,B,I,0,4,2 = 0;
+xNG_R_A,N,C,B,I,0,3,2 -xNG_R_A,N,C,B,I,0,4,3 = 0;
+xNG_R_A,N,C,B,I,0,4,1 -xNG_R_A,N,C,B,I,0,5,2 = 0;
+xNG_R_A,N,C,B,I,0,4,2 -xNG_R_A,N,C,B,I,0,5,3 = 0;
+xNG_R_A,N,C,B,I,0,4,3 -xNG_R_A,N,C,B,I,0,5,4 = 0;
+xPB_R_A,N,C,B,B,0,2,1 -xPB_R_A,N,C,B,B,0,3,2 = 0;
+xPB_R_A,N,C,B,B,0,3,1 -xPB_R_A,N,C,B,B,0,4,2 = 0;
+xPB_R_A,N,C,B,B,0,3,2 -xPB_R_A,N,C,B,B,0,4,3 = 0;
+xPB_R_A,N,C,B,B,0,4,1 -xPB_R_A,N,C,B,B,0,5,2 = 0;
+xPB_R_A,N,C,B,B,0,4,2 -xPB_R_A,N,C,B,B,0,5,3 = 0;
+xPB_R_A,N,C,B,B,0,4,3 -xPB_R_A,N,C,B,B,0,5,4 = 0;
+xPB_R_A,N,C,B,B,1,2,1 -xPB_R_A,N,C,B,B,1,3,2 = 0;
+xPB_R_A,N,C,B,B,1,3,1 -xPB_R_A,N,C,B,B,1,4,2 = 0;
+xPB_R_A,N,C,B,B,1,3,2 -xPB_R_A,N,C,B,B,1,4,3 = 0;
+xPB_R_A,N,C,B,B,1,4,1 -xPB_R_A,N,C,B,B,1,5,2 = 0;
+xPB_R_A,N,C,B,B,1,4,2 -xPB_R_A,N,C,B,B,1,5,3 = 0;
+xPB_R_A,N,C,B,B,1,4,3 -xPB_R_A,N,C,B,B,1,5,4 = 0;
+xPB_R_A,N,C,B,B,2,2,1 -xPB_R_A,N,C,B,B,2,3,2 = 0;
+xPB_R_A,N,C,B,B,2,3,1 -xPB_R_A,N,C,B,B,2,4,2 = 0;
+xPB_R_A,N,C,B,B,2,3,2 -xPB_R_A,N,C,B,B,2,4,3 = 0;
+xPB_R_A,N,C,B,B,2,4,1 -xPB_R_A,N,C,B,B,2,5,2 = 0;
+xPB_R_A,N,C,B,B,2,4,2 -xPB_R_A,N,C,B,B,2,5,3 = 0;
+xPB_R_A,N,C,B,B,2,4,3 -xPB_R_A,N,C,B,B,2,5,4 = 0;
+xPB_R_A,N,C,B,C,0,2,1 -xPB_R_A,N,C,B,C,0,3,2 = 0;
+xPB_R_A,N,C,B,C,0,3,1 -xPB_R_A,N,C,B,C,0,4,2 = 0;
+xPB_R_A,N,C,B,C,0,3,2 -xPB_R_A,N,C,B,C,0,4,3 = 0;
+xPB_R_A,N,C,B,C,0,4,1 -xPB_R_A,N,C,B,C,0,5,2 = 0;
+xPB_R_A,N,C,B,C,0,4,2 -xPB_R_A,N,C,B,C,0,5,3 = 0;
+xPB_R_A,N,C,B,C,0,4,3 -xPB_R_A,N,C,B,C,0,5,4 = 0;
+xPB_R_A,N,C,B,C,1,2,1 -xPB_R_A,N,C,B,C,1,3,2 = 0;
+xPB_R_A,N,C,B,C,1,3,1 -xPB_R_A,N,C,B,C,1,4,2 = 0;
+xPB_R_A,N,C,B,C,1,3,2 -xPB_R_A,N,C,B,C,1,4,3 = 0;
+xPB_R_A,N,C,B,C,1,4,1 -xPB_R_A,N,C,B,C,1,5,2 = 0;
+xPB_R_A,N,C,B,C,1,4,2 -xPB_R_A,N,C,B,C,1,5,3 = 0;
+xPB_R_A,N,C,B,C,1,4,3 -xPB_R_A,N,C,B,C,1,5,4 = 0;
+xPB_R_A,N,C,B,C,2,2,1 -xPB_R_A,N,C,B,C,2,3,2 = 0;
+xPB_R_A,N,C,B,C,2,3,1 -xPB_R_A,N,C,B,C,2,4,2 = 0;
+xPB_R_A,N,C,B,C,2,3,2 -xPB_R_A,N,C,B,C,2,4,3 = 0;
+xPB_R_A,N,C,B,C,2,4,1 -xPB_R_A,N,C,B,C,2,5,2 = 0;
+xPB_R_A,N,C,B,C,2,4,2 -xPB_R_A,N,C,B,C,2,5,3 = 0;
+xPB_R_A,N,C,B,C,2,4,3 -xPB_R_A,N,C,B,C,2,5,4 = 0;
+xPB_R_A,N,C,B,D,0,2,1 -xPB_R_A,N,C,B,D,0,3,2 = 0;
+xPB_R_A,N,C,B,D,0,3,1 -xPB_R_A,N,C,B,D,0,4,2 = 0;
+xPB_R_A,N,C,B,D,0,3,2 -xPB_R_A,N,C,B,D,0,4,3 = 0;
+xPB_R_A,N,C,B,D,0,4,1 -xPB_R_A,N,C,B,D,0,5,2 = 0;
+xPB_R_A,N,C,B,D,0,4,2 -xPB_R_A,N,C,B,D,0,5,3 = 0;
+xPB_R_A,N,C,B,D,0,4,3 -xPB_R_A,N,C,B,D,0,5,4 = 0;
+xPB_R_A,N,C,B,E,0,2,1 -xPB_R_A,N,C,B,E,0,3,2 = 0;
+xPB_R_A,N,C,B,E,0,3,1 -xPB_R_A,N,C,B,E,0,4,2 = 0;
+xPB_R_A,N,C,B,E,0,3,2 -xPB_R_A,N,C,B,E,0,4,3 = 0;
+xPB_R_A,N,C,B,E,0,4,1 -xPB_R_A,N,C,B,E,0,5,2 = 0;
+xPB_R_A,N,C,B,E,0,4,2 -xPB_R_A,N,C,B,E,0,5,3 = 0;
+xPB_R_A,N,C,B,E,0,4,3 -xPB_R_A,N,C,B,E,0,5,4 = 0;
+xPB_R_A,N,C,B,E,1,2,1 -xPB_R_A,N,C,B,E,1,3,2 = 0;
+xPB_R_A,N,C,B,E,1,3,1 -xPB_R_A,N,C,B,E,1,4,2 = 0;
+xPB_R_A,N,C,B,E,1,3,2 -xPB_R_A,N,C,B,E,1,4,3 = 0;
+xPB_R_A,N,C,B,E,1,4,1 -xPB_R_A,N,C,B,E,1,5,2 = 0;
+xPB_R_A,N,C,B,E,1,4,2 -xPB_R_A,N,C,B,E,1,5,3 = 0;
+xPB_R_A,N,C,B,E,1,4,3 -xPB_R_A,N,C,B,E,1,5,4 = 0;
+xPB_R_A,N,C,B,E,2,2,1 -xPB_R_A,N,C,B,E,2,3,2 = 0;
+xPB_R_A,N,C,B,E,2,3,1 -xPB_R_A,N,C,B,E,2,4,2 = 0;
+xPB_R_A,N,C,B,E,2,3,2 -xPB_R_A,N,C,B,E,2,4,3 = 0;
+xPB_R_A,N,C,B,E,2,4,1 -xPB_R_A,N,C,B,E,2,5,2 = 0;
+xPB_R_A,N,C,B,E,2,4,2 -xPB_R_A,N,C,B,E,2,5,3 = 0;
+xPB_R_A,N,C,B,E,2,4,3 -xPB_R_A,N,C,B,E,2,5,4 = 0;
+xPB_R_A,N,C,B,F,0,2,1 -xPB_R_A,N,C,B,F,0,3,2 = 0;
+xPB_R_A,N,C,B,F,0,3,1 -xPB_R_A,N,C,B,F,0,4,2 = 0;
+xPB_R_A,N,C,B,F,0,3,2 -xPB_R_A,N,C,B,F,0,4,3 = 0;
+xPB_R_A,N,C,B,F,0,4,1 -xPB_R_A,N,C,B,F,0,5,2 = 0;
+xPB_R_A,N,C,B,F,0,4,2 -xPB_R_A,N,C,B,F,0,5,3 = 0;
+xPB_R_A,N,C,B,F,0,4,3 -xPB_R_A,N,C,B,F,0,5,4 = 0;
+xPB_R_A,N,C,B,F,1,2,1 -xPB_R_A,N,C,B,F,1,3,2 = 0;
+xPB_R_A,N,C,B,F,1,3,1 -xPB_R_A,N,C,B,F,1,4,2 = 0;
+xPB_R_A,N,C,B,F,1,3,2 -xPB_R_A,N,C,B,F,1,4,3 = 0;
+xPB_R_A,N,C,B,F,1,4,1 -xPB_R_A,N,C,B,F,1,5,2 = 0;
+xPB_R_A,N,C,B,F,1,4,2 -xPB_R_A,N,C,B,F,1,5,3 = 0;
+xPB_R_A,N,C,B,F,1,4,3 -xPB_R_A,N,C,B,F,1,5,4 = 0;
+xPB_R_A,N,C,B,F,2,2,1 -xPB_R_A,N,C,B,F,2,3,2 = 0;
+xPB_R_A,N,C,B,F,2,3,1 -xPB_R_A,N,C,B,F,2,4,2 = 0;
+xPB_R_A,N,C,B,F,2,3,2 -xPB_R_A,N,C,B,F,2,4,3 = 0;
+xPB_R_A,N,C,B,F,2,4,1 -xPB_R_A,N,C,B,F,2,5,2 = 0;
+xPB_R_A,N,C,B,F,2,4,2 -xPB_R_A,N,C,B,F,2,5,3 = 0;
+xPB_R_A,N,C,B,F,2,4,3 -xPB_R_A,N,C,B,F,2,5,4 = 0;
+xPB_R_A,N,C,B,H,0,2,1 -xPB_R_A,N,C,B,H,0,3,2 = 0;
+xPB_R_A,N,C,B,H,0,3,1 -xPB_R_A,N,C,B,H,0,4,2 = 0;
+xPB_R_A,N,C,B,H,0,3,2 -xPB_R_A,N,C,B,H,0,4,3 = 0;
+xPB_R_A,N,C,B,H,0,4,1 -xPB_R_A,N,C,B,H,0,5,2 = 0;
+xPB_R_A,N,C,B,H,0,4,2 -xPB_R_A,N,C,B,H,0,5,3 = 0;
+xPB_R_A,N,C,B,H,0,4,3 -xPB_R_A,N,C,B,H,0,5,4 = 0;
+xPB_R_A,N,C,B,H,1,2,1 -xPB_R_A,N,C,B,H,1,3,2 = 0;
+xPB_R_A,N,C,B,H,1,3,1 -xPB_R_A,N,C,B,H,1,4,2 = 0;
+xPB_R_A,N,C,B,H,1,3,2 -xPB_R_A,N,C,B,H,1,4,3 = 0;
+xPB_R_A,N,C,B,H,1,4,1 -xPB_R_A,N,C,B,H,1,5,2 = 0;
+xPB_R_A,N,C,B,H,1,4,2 -xPB_R_A,N,C,B,H,1,5,3 = 0;
+xPB_R_A,N,C,B,H,1,4,3 -xPB_R_A,N,C,B,H,1,5,4 = 0;
+xPB_R_A,N,C,B,H,2,2,1 -xPB_R_A,N,C,B,H,2,3,2 = 0;
+xPB_R_A,N,C,B,H,2,3,1 -xPB_R_A,N,C,B,H,2,4,2 = 0;
+xPB_R_A,N,C,B,H,2,3,2 -xPB_R_A,N,C,B,H,2,4,3 = 0;
+xPB_R_A,N,C,B,H,2,4,1 -xPB_R_A,N,C,B,H,2,5,2 = 0;
+xPB_R_A,N,C,B,H,2,4,2 -xPB_R_A,N,C,B,H,2,5,3 = 0;
+xPB_R_A,N,C,B,H,2,4,3 -xPB_R_A,N,C,B,H,2,5,4 = 0;
+xPB_R_A,N,C,B,I,0,2,1 -xPB_R_A,N,C,B,I,0,3,2 = 0;
+xPB_R_A,N,C,B,I,0,3,1 -xPB_R_A,N,C,B,I,0,4,2 = 0;
+xPB_R_A,N,C,B,I,0,3,2 -xPB_R_A,N,C,B,I,0,4,3 = 0;
+xPB_R_A,N,C,B,I,0,4,1 -xPB_R_A,N,C,B,I,0,5,2 = 0;
+xPB_R_A,N,C,B,I,0,4,2 -xPB_R_A,N,C,B,I,0,5,3 = 0;
+xPB_R_A,N,C,B,I,0,4,3 -xPB_R_A,N,C,B,I,0,5,4 = 0;
+xPB_R_A,N,C,B,I,1,2,1 -xPB_R_A,N,C,B,I,1,3,2 = 0;
+xPB_R_A,N,C,B,I,1,3,1 -xPB_R_A,N,C,B,I,1,4,2 = 0;
+xPB_R_A,N,C,B,I,1,3,2 -xPB_R_A,N,C,B,I,1,4,3 = 0;
+xPB_R_A,N,C,B,I,1,4,1 -xPB_R_A,N,C,B,I,1,5,2 = 0;
+xPB_R_A,N,C,B,I,1,4,2 -xPB_R_A,N,C,B,I,1,5,3 = 0;
+xPB_R_A,N,C,B,I,1,4,3 -xPB_R_A,N,C,B,I,1,5,4 = 0;
+xPB_R_A,N,C,B,I,2,2,1 -xPB_R_A,N,C,B,I,2,3,2 = 0;
+xPB_R_A,N,C,B,I,2,3,1 -xPB_R_A,N,C,B,I,2,4,2 = 0;
+xPB_R_A,N,C,B,I,2,3,2 -xPB_R_A,N,C,B,I,2,4,3 = 0;
+xPB_R_A,N,C,B,I,2,4,1 -xPB_R_A,N,C,B,I,2,5,2 = 0;
+xPB_R_A,N,C,B,I,2,4,2 -xPB_R_A,N,C,B,I,2,5,3 = 0;
+xPB_R_A,N,C,B,I,2,4,3 -xPB_R_A,N,C,B,I,2,5,4 = 0;
+xGS_R_A,N,C,B,B,0,2,1 -xGS_R_A,N,C,B,B,0,3,2 = 0;
+xGS_R_A,N,C,B,B,0,3,1 -xGS_R_A,N,C,B,B,0,4,2 = 0;
+xGS_R_A,N,C,B,B,0,3,2 -xGS_R_A,N,C,B,B,0,4,3 = 0;
+xGS_R_A,N,C,B,B,0,4,1 -xGS_R_A,N,C,B,B,0,5,2 = 0;
+xGS_R_A,N,C,B,B,0,4,2 -xGS_R_A,N,C,B,B,0,5,3 = 0;
+xGS_R_A,N,C,B,B,0,4,3 -xGS_R_A,N,C,B,B,0,5,4 = 0;
+xGS_R_A,N,C,B,B,1,2,1 -xGS_R_A,N,C,B,B,1,3,2 = 0;
+xGS_R_A,N,C,B,B,1,3,1 -xGS_R_A,N,C,B,B,1,4,2 = 0;
+xGS_R_A,N,C,B,B,1,3,2 -xGS_R_A,N,C,B,B,1,4,3 = 0;
+xGS_R_A,N,C,B,B,1,4,1 -xGS_R_A,N,C,B,B,1,5,2 = 0;
+xGS_R_A,N,C,B,B,1,4,2 -xGS_R_A,N,C,B,B,1,5,3 = 0;
+xGS_R_A,N,C,B,B,1,4,3 -xGS_R_A,N,C,B,B,1,5,4 = 0;
+xGS_R_A,N,C,B,B,2,2,1 -xGS_R_A,N,C,B,B,2,3,2 = 0;
+xGS_R_A,N,C,B,B,2,3,1 -xGS_R_A,N,C,B,B,2,4,2 = 0;
+xGS_R_A,N,C,B,B,2,3,2 -xGS_R_A,N,C,B,B,2,4,3 = 0;
+xGS_R_A,N,C,B,B,2,4,1 -xGS_R_A,N,C,B,B,2,5,2 = 0;
+xGS_R_A,N,C,B,B,2,4,2 -xGS_R_A,N,C,B,B,2,5,3 = 0;
+xGS_R_A,N,C,B,B,2,4,3 -xGS_R_A,N,C,B,B,2,5,4 = 0;
+xGS_R_A,N,C,B,B,3,2,1 -xGS_R_A,N,C,B,B,3,3,2 = 0;
+xGS_R_A,N,C,B,B,3,3,1 -xGS_R_A,N,C,B,B,3,4,2 = 0;
+xGS_R_A,N,C,B,B,3,3,2 -xGS_R_A,N,C,B,B,3,4,3 = 0;
+xGS_R_A,N,C,B,B,3,4,1 -xGS_R_A,N,C,B,B,3,5,2 = 0;
+xGS_R_A,N,C,B,B,3,4,2 -xGS_R_A,N,C,B,B,3,5,3 = 0;
+xGS_R_A,N,C,B,B,3,4,3 -xGS_R_A,N,C,B,B,3,5,4 = 0;
+xGS_R_A,N,C,B,C,0,2,1 -xGS_R_A,N,C,B,C,0,3,2 = 0;
+xGS_R_A,N,C,B,C,0,3,1 -xGS_R_A,N,C,B,C,0,4,2 = 0;
+xGS_R_A,N,C,B,C,0,3,2 -xGS_R_A,N,C,B,C,0,4,3 = 0;
+xGS_R_A,N,C,B,C,0,4,1 -xGS_R_A,N,C,B,C,0,5,2 = 0;
+xGS_R_A,N,C,B,C,0,4,2 -xGS_R_A,N,C,B,C,0,5,3 = 0;
+xGS_R_A,N,C,B,C,0,4,3 -xGS_R_A,N,C,B,C,0,5,4 = 0;
+xGS_R_A,N,C,B,C,1,2,1 -xGS_R_A,N,C,B,C,1,3,2 = 0;
+xGS_R_A,N,C,B,C,1,3,1 -xGS_R_A,N,C,B,C,1,4,2 = 0;
+xGS_R_A,N,C,B,C,1,3,2 -xGS_R_A,N,C,B,C,1,4,3 = 0;
+xGS_R_A,N,C,B,C,1,4,1 -xGS_R_A,N,C,B,C,1,5,2 = 0;
+xGS_R_A,N,C,B,C,1,4,2 -xGS_R_A,N,C,B,C,1,5,3 = 0;
+xGS_R_A,N,C,B,C,1,4,3 -xGS_R_A,N,C,B,C,1,5,4 = 0;
+xGS_R_A,N,C,B,C,2,2,1 -xGS_R_A,N,C,B,C,2,3,2 = 0;
+xGS_R_A,N,C,B,C,2,3,1 -xGS_R_A,N,C,B,C,2,4,2 = 0;
+xGS_R_A,N,C,B,C,2,3,2 -xGS_R_A,N,C,B,C,2,4,3 = 0;
+xGS_R_A,N,C,B,C,2,4,1 -xGS_R_A,N,C,B,C,2,5,2 = 0;
+xGS_R_A,N,C,B,C,2,4,2 -xGS_R_A,N,C,B,C,2,5,3 = 0;
+xGS_R_A,N,C,B,C,2,4,3 -xGS_R_A,N,C,B,C,2,5,4 = 0;
+xGS_R_A,N,C,B,C,3,2,1 -xGS_R_A,N,C,B,C,3,3,2 = 0;
+xGS_R_A,N,C,B,C,3,3,1 -xGS_R_A,N,C,B,C,3,4,2 = 0;
+xGS_R_A,N,C,B,C,3,3,2 -xGS_R_A,N,C,B,C,3,4,3 = 0;
+xGS_R_A,N,C,B,C,3,4,1 -xGS_R_A,N,C,B,C,3,5,2 = 0;
+xGS_R_A,N,C,B,C,3,4,2 -xGS_R_A,N,C,B,C,3,5,3 = 0;
+xGS_R_A,N,C,B,C,3,4,3 -xGS_R_A,N,C,B,C,3,5,4 = 0;
+xGS_R_A,N,C,B,D,0,2,1 -xGS_R_A,N,C,B,D,0,3,2 = 0;
+xGS_R_A,N,C,B,D,0,3,1 -xGS_R_A,N,C,B,D,0,4,2 = 0;
+xGS_R_A,N,C,B,D,0,3,2 -xGS_R_A,N,C,B,D,0,4,3 = 0;
+xGS_R_A,N,C,B,D,0,4,1 -xGS_R_A,N,C,B,D,0,5,2 = 0;
+xGS_R_A,N,C,B,D,0,4,2 -xGS_R_A,N,C,B,D,0,5,3 = 0;
+xGS_R_A,N,C,B,D,0,4,3 -xGS_R_A,N,C,B,D,0,5,4 = 0;
+xGS_R_A,N,C,B,D,1,2,1 -xGS_R_A,N,C,B,D,1,3,2 = 0;
+xGS_R_A,N,C,B,D,1,3,1 -xGS_R_A,N,C,B,D,1,4,2 = 0;
+xGS_R_A,N,C,B,D,1,3,2 -xGS_R_A,N,C,B,D,1,4,3 = 0;
+xGS_R_A,N,C,B,D,1,4,1 -xGS_R_A,N,C,B,D,1,5,2 = 0;
+xGS_R_A,N,C,B,D,1,4,2 -xGS_R_A,N,C,B,D,1,5,3 = 0;
+xGS_R_A,N,C,B,D,1,4,3 -xGS_R_A,N,C,B,D,1,5,4 = 0;
+xGS_R_A,N,C,B,D,2,2,1 -xGS_R_A,N,C,B,D,2,3,2 = 0;
+xGS_R_A,N,C,B,D,2,3,1 -xGS_R_A,N,C,B,D,2,4,2 = 0;
+xGS_R_A,N,C,B,D,2,3,2 -xGS_R_A,N,C,B,D,2,4,3 = 0;
+xGS_R_A,N,C,B,D,2,4,1 -xGS_R_A,N,C,B,D,2,5,2 = 0;
+xGS_R_A,N,C,B,D,2,4,2 -xGS_R_A,N,C,B,D,2,5,3 = 0;
+xGS_R_A,N,C,B,D,2,4,3 -xGS_R_A,N,C,B,D,2,5,4 = 0;
+xGS_R_A,N,C,B,D,3,2,1 -xGS_R_A,N,C,B,D,3,3,2 = 0;
+xGS_R_A,N,C,B,D,3,3,1 -xGS_R_A,N,C,B,D,3,4,2 = 0;
+xGS_R_A,N,C,B,D,3,3,2 -xGS_R_A,N,C,B,D,3,4,3 = 0;
+xGS_R_A,N,C,B,D,3,4,1 -xGS_R_A,N,C,B,D,3,5,2 = 0;
+xGS_R_A,N,C,B,D,3,4,2 -xGS_R_A,N,C,B,D,3,5,3 = 0;
+xGS_R_A,N,C,B,D,3,4,3 -xGS_R_A,N,C,B,D,3,5,4 = 0;
+xGS_R_A,N,C,B,F,0,2,1 -xGS_R_A,N,C,B,F,0,3,2 = 0;
+xGS_R_A,N,C,B,F,0,3,1 -xGS_R_A,N,C,B,F,0,4,2 = 0;
+xGS_R_A,N,C,B,F,0,3,2 -xGS_R_A,N,C,B,F,0,4,3 = 0;
+xGS_R_A,N,C,B,F,0,4,1 -xGS_R_A,N,C,B,F,0,5,2 = 0;
+xGS_R_A,N,C,B,F,0,4,2 -xGS_R_A,N,C,B,F,0,5,3 = 0;
+xGS_R_A,N,C,B,F,0,4,3 -xGS_R_A,N,C,B,F,0,5,4 = 0;
+xGS_R_A,N,C,B,F,1,2,1 -xGS_R_A,N,C,B,F,1,3,2 = 0;
+xGS_R_A,N,C,B,F,1,3,1 -xGS_R_A,N,C,B,F,1,4,2 = 0;
+xGS_R_A,N,C,B,F,1,3,2 -xGS_R_A,N,C,B,F,1,4,3 = 0;
+xGS_R_A,N,C,B,F,1,4,1 -xGS_R_A,N,C,B,F,1,5,2 = 0;
+xGS_R_A,N,C,B,F,1,4,2 -xGS_R_A,N,C,B,F,1,5,3 = 0;
+xGS_R_A,N,C,B,F,1,4,3 -xGS_R_A,N,C,B,F,1,5,4 = 0;
+xGS_R_A,N,C,B,F,2,2,1 -xGS_R_A,N,C,B,F,2,3,2 = 0;
+xGS_R_A,N,C,B,F,2,3,1 -xGS_R_A,N,C,B,F,2,4,2 = 0;
+xGS_R_A,N,C,B,F,2,3,2 -xGS_R_A,N,C,B,F,2,4,3 = 0;
+xGS_R_A,N,C,B,F,2,4,1 -xGS_R_A,N,C,B,F,2,5,2 = 0;
+xGS_R_A,N,C,B,F,2,4,2 -xGS_R_A,N,C,B,F,2,5,3 = 0;
+xGS_R_A,N,C,B,F,2,4,3 -xGS_R_A,N,C,B,F,2,5,4 = 0;
+xGS_R_A,N,C,B,F,3,2,1 -xGS_R_A,N,C,B,F,3,3,2 = 0;
+xGS_R_A,N,C,B,F,3,3,1 -xGS_R_A,N,C,B,F,3,4,2 = 0;
+xGS_R_A,N,C,B,F,3,3,2 -xGS_R_A,N,C,B,F,3,4,3 = 0;
+xGS_R_A,N,C,B,F,3,4,1 -xGS_R_A,N,C,B,F,3,5,2 = 0;
+xGS_R_A,N,C,B,F,3,4,2 -xGS_R_A,N,C,B,F,3,5,3 = 0;
+xGS_R_A,N,C,B,F,3,4,3 -xGS_R_A,N,C,B,F,3,5,4 = 0;
+xGS_R_A,N,C,B,H,0,2,1 -xGS_R_A,N,C,B,H,0,3,2 = 0;
+xGS_R_A,N,C,B,H,0,3,1 -xGS_R_A,N,C,B,H,0,4,2 = 0;
+xGS_R_A,N,C,B,H,0,3,2 -xGS_R_A,N,C,B,H,0,4,3 = 0;
+xGS_R_A,N,C,B,H,0,4,1 -xGS_R_A,N,C,B,H,0,5,2 = 0;
+xGS_R_A,N,C,B,H,0,4,2 -xGS_R_A,N,C,B,H,0,5,3 = 0;
+xGS_R_A,N,C,B,H,0,4,3 -xGS_R_A,N,C,B,H,0,5,4 = 0;
+xGS_R_A,N,C,B,H,1,2,1 -xGS_R_A,N,C,B,H,1,3,2 = 0;
+xGS_R_A,N,C,B,H,1,3,1 -xGS_R_A,N,C,B,H,1,4,2 = 0;
+xGS_R_A,N,C,B,H,1,3,2 -xGS_R_A,N,C,B,H,1,4,3 = 0;
+xGS_R_A,N,C,B,H,1,4,1 -xGS_R_A,N,C,B,H,1,5,2 = 0;
+xGS_R_A,N,C,B,H,1,4,2 -xGS_R_A,N,C,B,H,1,5,3 = 0;
+xGS_R_A,N,C,B,H,1,4,3 -xGS_R_A,N,C,B,H,1,5,4 = 0;
+xGS_R_A,N,C,B,H,2,2,1 -xGS_R_A,N,C,B,H,2,3,2 = 0;
+xGS_R_A,N,C,B,H,2,3,1 -xGS_R_A,N,C,B,H,2,4,2 = 0;
+xGS_R_A,N,C,B,H,2,3,2 -xGS_R_A,N,C,B,H,2,4,3 = 0;
+xGS_R_A,N,C,B,H,2,4,1 -xGS_R_A,N,C,B,H,2,5,2 = 0;
+xGS_R_A,N,C,B,H,2,4,2 -xGS_R_A,N,C,B,H,2,5,3 = 0;
+xGS_R_A,N,C,B,H,2,4,3 -xGS_R_A,N,C,B,H,2,5,4 = 0;
+xGS_R_A,N,C,B,H,3,2,1 -xGS_R_A,N,C,B,H,3,3,2 = 0;
+xGS_R_A,N,C,B,H,3,3,1 -xGS_R_A,N,C,B,H,3,4,2 = 0;
+xGS_R_A,N,C,B,H,3,3,2 -xGS_R_A,N,C,B,H,3,4,3 = 0;
+xGS_R_A,N,C,B,H,3,4,1 -xGS_R_A,N,C,B,H,3,5,2 = 0;
+xGS_R_A,N,C,B,H,3,4,2 -xGS_R_A,N,C,B,H,3,5,3 = 0;
+xGS_R_A,N,C,B,H,3,4,3 -xGS_R_A,N,C,B,H,3,5,4 = 0;
+xGS_R_A,N,C,B,I,0,2,1 -xGS_R_A,N,C,B,I,0,3,2 = 0;
+xGS_R_A,N,C,B,I,0,3,1 -xGS_R_A,N,C,B,I,0,4,2 = 0;
+xGS_R_A,N,C,B,I,0,3,2 -xGS_R_A,N,C,B,I,0,4,3 = 0;
+xGS_R_A,N,C,B,I,0,4,1 -xGS_R_A,N,C,B,I,0,5,2 = 0;
+xGS_R_A,N,C,B,I,0,4,2 -xGS_R_A,N,C,B,I,0,5,3 = 0;
+xGS_R_A,N,C,B,I,0,4,3 -xGS_R_A,N,C,B,I,0,5,4 = 0;
+xGS_R_A,N,C,B,I,1,2,1 -xGS_R_A,N,C,B,I,1,3,2 = 0;
+xGS_R_A,N,C,B,I,1,3,1 -xGS_R_A,N,C,B,I,1,4,2 = 0;
+xGS_R_A,N,C,B,I,1,3,2 -xGS_R_A,N,C,B,I,1,4,3 = 0;
+xGS_R_A,N,C,B,I,1,4,1 -xGS_R_A,N,C,B,I,1,5,2 = 0;
+xGS_R_A,N,C,B,I,1,4,2 -xGS_R_A,N,C,B,I,1,5,3 = 0;
+xGS_R_A,N,C,B,I,1,4,3 -xGS_R_A,N,C,B,I,1,5,4 = 0;
+xGS_R_A,N,C,B,I,2,2,1 -xGS_R_A,N,C,B,I,2,3,2 = 0;
+xGS_R_A,N,C,B,I,2,3,1 -xGS_R_A,N,C,B,I,2,4,2 = 0;
+xGS_R_A,N,C,B,I,2,3,2 -xGS_R_A,N,C,B,I,2,4,3 = 0;
+xGS_R_A,N,C,B,I,2,4,1 -xGS_R_A,N,C,B,I,2,5,2 = 0;
+xGS_R_A,N,C,B,I,2,4,2 -xGS_R_A,N,C,B,I,2,5,3 = 0;
+xGS_R_A,N,C,B,I,2,4,3 -xGS_R_A,N,C,B,I,2,5,4 = 0;
+xGS_R_A,N,C,B,I,3,2,1 -xGS_R_A,N,C,B,I,3,3,2 = 0;
+xGS_R_A,N,C,B,I,3,3,1 -xGS_R_A,N,C,B,I,3,4,2 = 0;
+xGS_R_A,N,C,B,I,3,3,2 -xGS_R_A,N,C,B,I,3,4,3 = 0;
+xGS_R_A,N,C,B,I,3,4,1 -xGS_R_A,N,C,B,I,3,5,2 = 0;
+xGS_R_A,N,C,B,I,3,4,2 -xGS_R_A,N,C,B,I,3,5,3 = 0;
+xGS_R_A,N,C,B,I,3,4,3 -xGS_R_A,N,C,B,I,3,5,4 = 0;

/* Variable bounds */
x_A,N,C,B,D,G,3 = 0;
x_A,N,C,B,D,G,4 = 0;
x_A,N,C,B,D,G,5 = 0;
x_A,N,C,B,D,I,3 = 0;
x_A,N,C,B,D,I,4 = 0;
x_A,N,C,B,D,I,5 = 0;
