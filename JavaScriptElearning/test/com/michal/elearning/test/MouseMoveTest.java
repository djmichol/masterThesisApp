package com.michal.elearning.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.dao.UserMauseClick;
import com.michal.elearning.dao.UserMauseMove;
import com.michal.elearning.modeldata.vectors.MauseMoveFeatures;

public class MouseMoveTest {

	/*private static List<UserMauseClick> click;
	private static List<UserMauseMove> moves;
	
	static{
		click = new ArrayList<>();
		click.add(new UserMauseClick("click",new BigDecimal(16116432)));
		click.add(new UserMauseClick("click",new BigDecimal(16118272)));
		click.add(new UserMauseClick("click",new BigDecimal(16119318)));
		click.add(new UserMauseClick("click",new BigDecimal(16122250)));
		
		moves = new ArrayList<>();
		moves.add(new UserMauseMove(new BigDecimal(16115308), 322, 3));
		moves.add(new UserMauseMove(new BigDecimal(16115324), 322, 13));
		moves.add(new UserMauseMove(new BigDecimal(16115340), 324, 23));
		moves.add(new UserMauseMove(new BigDecimal(16115355), 324, 27));
		moves.add(new UserMauseMove(new BigDecimal(16115386), 324, 29));
		moves.add(new UserMauseMove(new BigDecimal(16115402), 324, 35));
		moves.add(new UserMauseMove(new BigDecimal(16115418), 323, 43));
		moves.add(new UserMauseMove(new BigDecimal(16115433), 321, 47));
		moves.add(new UserMauseMove(new BigDecimal(16116806), 316, 49));
		moves.add(new UserMauseMove(new BigDecimal(16116822), 308, 53));
		moves.add(new UserMauseMove(new BigDecimal(16116837), 304, 55));
		moves.add(new UserMauseMove(new BigDecimal(16116868), 300, 56));
		moves.add(new UserMauseMove(new BigDecimal(16116931), 298, 56));
		moves.add(new UserMauseMove(new BigDecimal(16116946), 297, 58));
		moves.add(new UserMauseMove(new BigDecimal(16116978), 295, 60));
		moves.add(new UserMauseMove(new BigDecimal(16116993), 293, 63));
		moves.add(new UserMauseMove(new BigDecimal(16117009), 292, 66));
		moves.add(new UserMauseMove(new BigDecimal(16117040), 291, 71));
		moves.add(new UserMauseMove(new BigDecimal(16117056), 291, 82));
		moves.add(new UserMauseMove(new BigDecimal(16117071), 291, 91));
		moves.add(new UserMauseMove(new BigDecimal(16117087), 292, 96));
		moves.add(new UserMauseMove(new BigDecimal(16117102), 294, 98));
		moves.add(new UserMauseMove(new BigDecimal(16117118), 303, 100));
		moves.add(new UserMauseMove(new BigDecimal(16117134), 318, 103));
		moves.add(new UserMauseMove(new BigDecimal(16117149), 330, 105));
		moves.add(new UserMauseMove(new BigDecimal(16117165), 351, 107));
		moves.add(new UserMauseMove(new BigDecimal(16117180), 372, 108));
		moves.add(new UserMauseMove(new BigDecimal(16117196), 395, 112));
		moves.add(new UserMauseMove(new BigDecimal(16117212), 416, 116));
		moves.add(new UserMauseMove(new BigDecimal(16117227), 432, 119));
		moves.add(new UserMauseMove(new BigDecimal(16117243), 440, 123));
		moves.add(new UserMauseMove(new BigDecimal(16117274), 446, 128));
		moves.add(new UserMauseMove(new BigDecimal(16117290), 451, 140));
		moves.add(new UserMauseMove(new BigDecimal(16117305), 452, 151));
		moves.add(new UserMauseMove(new BigDecimal(16117321), 452, 161));
		moves.add(new UserMauseMove(new BigDecimal(16117336), 451, 169));
		moves.add(new UserMauseMove(new BigDecimal(16117352), 449, 173));
		moves.add(new UserMauseMove(new BigDecimal(16117368), 445, 179));
		moves.add(new UserMauseMove(new BigDecimal(16117383), 442, 183));
		moves.add(new UserMauseMove(new BigDecimal(16117399), 433, 190));
		moves.add(new UserMauseMove(new BigDecimal(16117414), 423, 195));
		moves.add(new UserMauseMove(new BigDecimal(16117430), 407, 199));
		moves.add(new UserMauseMove(new BigDecimal(16117446), 389, 204));
		moves.add(new UserMauseMove(new BigDecimal(16117461), 372, 208));
		moves.add(new UserMauseMove(new BigDecimal(16117477), 356, 213));
		moves.add(new UserMauseMove(new BigDecimal(16117492), 343, 215));
		moves.add(new UserMauseMove(new BigDecimal(16117508), 326, 217));
		moves.add(new UserMauseMove(new BigDecimal(16117524), 308, 219));
		moves.add(new UserMauseMove(new BigDecimal(16117539), 289, 222));
		moves.add(new UserMauseMove(new BigDecimal(16117555), 272, 222));
		moves.add(new UserMauseMove(new BigDecimal(16117570), 262, 222));
		moves.add(new UserMauseMove(new BigDecimal(16117586), 255, 225));
		moves.add(new UserMauseMove(new BigDecimal(16117602), 250, 226));
		moves.add(new UserMauseMove(new BigDecimal(16117617), 247, 231));
		moves.add(new UserMauseMove(new BigDecimal(16117633), 246, 241));
		moves.add(new UserMauseMove(new BigDecimal(16117648), 244, 258));
		moves.add(new UserMauseMove(new BigDecimal(16117664), 241, 278));
		moves.add(new UserMauseMove(new BigDecimal(16117680), 240, 294));
		moves.add(new UserMauseMove(new BigDecimal(16117695), 240, 303));
		moves.add(new UserMauseMove(new BigDecimal(16117711), 241, 311));
		moves.add(new UserMauseMove(new BigDecimal(16117726), 246, 317));
		moves.add(new UserMauseMove(new BigDecimal(16117742), 253, 324));
		moves.add(new UserMauseMove(new BigDecimal(16117758), 265, 329));
		moves.add(new UserMauseMove(new BigDecimal(16117773), 277, 332));
		moves.add(new UserMauseMove(new BigDecimal(16117789), 296, 334));
		moves.add(new UserMauseMove(new BigDecimal(16117804), 309, 334));
		moves.add(new UserMauseMove(new BigDecimal(16117820), 324, 332));
		moves.add(new UserMauseMove(new BigDecimal(16117836), 342, 328));
		moves.add(new UserMauseMove(new BigDecimal(16117851), 353, 325));
		moves.add(new UserMauseMove(new BigDecimal(16117867), 366, 322));
		moves.add(new UserMauseMove(new BigDecimal(16117898), 375, 319));
		moves.add(new UserMauseMove(new BigDecimal(16117914), 378, 319));
		moves.add(new UserMauseMove(new BigDecimal(16118850), 379, 306));
		moves.add(new UserMauseMove(new BigDecimal(16118865), 381, 276));
		moves.add(new UserMauseMove(new BigDecimal(16118881), 381, 242));
		moves.add(new UserMauseMove(new BigDecimal(16118896), 381, 200));
		moves.add(new UserMauseMove(new BigDecimal(16118912), 381, 163));
		moves.add(new UserMauseMove(new BigDecimal(16118928), 381, 139));
		moves.add(new UserMauseMove(new BigDecimal(16118943), 381, 118));
		moves.add(new UserMauseMove(new BigDecimal(16118959), 375, 105));
		moves.add(new UserMauseMove(new BigDecimal(16118974), 371, 98));
		moves.add(new UserMauseMove(new BigDecimal(16119006), 368, 95));
		moves.add(new UserMauseMove(new BigDecimal(16119895), 367, 96));
		moves.add(new UserMauseMove(new BigDecimal(16119910), 369, 106));
		moves.add(new UserMauseMove(new BigDecimal(16119926), 373, 112));
		moves.add(new UserMauseMove(new BigDecimal(16119942), 377, 119));
		moves.add(new UserMauseMove(new BigDecimal(16119957), 383, 131));
		moves.add(new UserMauseMove(new BigDecimal(16119973), 390, 139));
		moves.add(new UserMauseMove(new BigDecimal(16119988), 393, 143));
		moves.add(new UserMauseMove(new BigDecimal(16120004), 396, 147));
		moves.add(new UserMauseMove(new BigDecimal(16120020), 399, 150));
		moves.add(new UserMauseMove(new BigDecimal(16120051), 401, 154));
		moves.add(new UserMauseMove(new BigDecimal(16120066), 403, 156));
		moves.add(new UserMauseMove(new BigDecimal(16120082), 409, 160));
		moves.add(new UserMauseMove(new BigDecimal(16120098), 414, 164));
		moves.add(new UserMauseMove(new BigDecimal(16120113), 420, 165));
		moves.add(new UserMauseMove(new BigDecimal(16120129), 426, 169));
		moves.add(new UserMauseMove(new BigDecimal(16120144), 432, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120176), 436, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120191), 439, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120207), 442, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120222), 444, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120238), 448, 170));
		moves.add(new UserMauseMove(new BigDecimal(16120254), 453, 162));
		moves.add(new UserMauseMove(new BigDecimal(16120269), 456, 157));
		moves.add(new UserMauseMove(new BigDecimal(16120316), 463, 133));
		moves.add(new UserMauseMove(new BigDecimal(16120332), 463, 129));
		moves.add(new UserMauseMove(new BigDecimal(16120347), 464, 122));
		moves.add(new UserMauseMove(new BigDecimal(16120378), 464, 115));
		moves.add(new UserMauseMove(new BigDecimal(16120394), 466, 110));
		moves.add(new UserMauseMove(new BigDecimal(16120410), 467, 106));
		moves.add(new UserMauseMove(new BigDecimal(16120425), 468, 103));
		moves.add(new UserMauseMove(new BigDecimal(16120472), 473, 97));
		moves.add(new UserMauseMove(new BigDecimal(16120503), 475, 97));
		moves.add(new UserMauseMove(new BigDecimal(16120519), 479, 97));
		moves.add(new UserMauseMove(new BigDecimal(16120534), 483, 97));
		moves.add(new UserMauseMove(new BigDecimal(16120550), 492, 101));
		moves.add(new UserMauseMove(new BigDecimal(16120597), 507, 124));
		moves.add(new UserMauseMove(new BigDecimal(16120612), 511, 133));
		moves.add(new UserMauseMove(new BigDecimal(16120628), 515, 144));
		moves.add(new UserMauseMove(new BigDecimal(16120644), 519, 154));
		moves.add(new UserMauseMove(new BigDecimal(16120659), 524, 164));
		moves.add(new UserMauseMove(new BigDecimal(16120690), 526, 169));
		moves.add(new UserMauseMove(new BigDecimal(16120706), 529, 174));
		moves.add(new UserMauseMove(new BigDecimal(16120722), 533, 179));
		moves.add(new UserMauseMove(new BigDecimal(16120737), 533, 182));
		moves.add(new UserMauseMove(new BigDecimal(16120753), 534, 185));
		moves.add(new UserMauseMove(new BigDecimal(16120784), 536, 188));
		moves.add(new UserMauseMove(new BigDecimal(16120800), 538, 189));
		moves.add(new UserMauseMove(new BigDecimal(16120846), 562, 190));
		moves.add(new UserMauseMove(new BigDecimal(16120862), 577, 186));
		moves.add(new UserMauseMove(new BigDecimal(16120878), 590, 177));
		moves.add(new UserMauseMove(new BigDecimal(16120909), 602, 166));
		moves.add(new UserMauseMove(new BigDecimal(16120924), 607, 160));
		moves.add(new UserMauseMove(new BigDecimal(16120940), 608, 156));
		moves.add(new UserMauseMove(new BigDecimal(16120956), 612, 152));
		moves.add(new UserMauseMove(new BigDecimal(16121002), 613, 149));
		moves.add(new UserMauseMove(new BigDecimal(16121018), 614, 144));
		moves.add(new UserMauseMove(new BigDecimal(16121034), 616, 138));
		moves.add(new UserMauseMove(new BigDecimal(16121049), 616, 134));
		moves.add(new UserMauseMove(new BigDecimal(16121065), 616, 129));
		moves.add(new UserMauseMove(new BigDecimal(16121080), 616, 127));
		moves.add(new UserMauseMove(new BigDecimal(16121112), 616, 122));
		moves.add(new UserMauseMove(new BigDecimal(16121127), 619, 117));
		moves.add(new UserMauseMove(new BigDecimal(16121143), 620, 113));
		moves.add(new UserMauseMove(new BigDecimal(16121158), 623, 107));
		moves.add(new UserMauseMove(new BigDecimal(16121174), 626, 103));
		moves.add(new UserMauseMove(new BigDecimal(16121190), 628, 99));
		moves.add(new UserMauseMove(new BigDecimal(16121205), 630, 98));
		moves.add(new UserMauseMove(new BigDecimal(16121236), 631, 96));
		moves.add(new UserMauseMove(new BigDecimal(16121268), 633, 96));
		moves.add(new UserMauseMove(new BigDecimal(16121283), 636, 96));
		moves.add(new UserMauseMove(new BigDecimal(16121314), 640, 96));
		moves.add(new UserMauseMove(new BigDecimal(16121361), 644, 100));
		moves.add(new UserMauseMove(new BigDecimal(16121377), 649, 109));
		moves.add(new UserMauseMove(new BigDecimal(16121392), 651, 118));
		moves.add(new UserMauseMove(new BigDecimal(16121408), 656, 129));
		moves.add(new UserMauseMove(new BigDecimal(16121424), 663, 145));
		moves.add(new UserMauseMove(new BigDecimal(16121439), 668, 155));
		moves.add(new UserMauseMove(new BigDecimal(16121455), 670, 158));
		moves.add(new UserMauseMove(new BigDecimal(16121470), 672, 159));
		moves.add(new UserMauseMove(new BigDecimal(16122687), 639, 154));
		moves.add(new UserMauseMove(new BigDecimal(16122703), 576, 140));
		moves.add(new UserMauseMove(new BigDecimal(16122718), 548, 134));
		moves.add(new UserMauseMove(new BigDecimal(16122734), 520, 129));
		moves.add(new UserMauseMove(new BigDecimal(16122750), 467, 116));
		moves.add(new UserMauseMove(new BigDecimal(16122765), 389, 92));
		moves.add(new UserMauseMove(new BigDecimal(16122781), 320, 72));
		moves.add(new UserMauseMove(new BigDecimal(16122796), 259, 46));
		moves.add(new UserMauseMove(new BigDecimal(16122812), 219, 34));
		moves.add(new UserMauseMove(new BigDecimal(16122828), 201, 28));
		moves.add(new UserMauseMove(new BigDecimal(16122843), 196, 27));
		moves.add(new UserMauseMove(new BigDecimal(16122859), 190, 24));
		moves.add(new UserMauseMove(new BigDecimal(16122952), 187, 24));
		moves.add(new UserMauseMove(new BigDecimal(16122968), 181, 25));
		moves.add(new UserMauseMove(new BigDecimal(16122984), 172, 27));
		moves.add(new UserMauseMove(new BigDecimal(16122999), 165, 29));
		moves.add(new UserMauseMove(new BigDecimal(16123015), 160, 29));
		moves.add(new UserMauseMove(new BigDecimal(16123030), 150, 31));
		moves.add(new UserMauseMove(new BigDecimal(16123046), 126, 34));
		moves.add(new UserMauseMove(new BigDecimal(16123062), 100, 34));
		moves.add(new UserMauseMove(new BigDecimal(16123077), 73, 34));
		moves.add(new UserMauseMove(new BigDecimal(16123093), 52, 31));
		moves.add(new UserMauseMove(new BigDecimal(16123108), 38, 24));
		moves.add(new UserMauseMove(new BigDecimal(16123124), 35, 23));
	}
	
	@Test
	public void calculateClickToClickDistanceToTotalPathLengthRatio(){
		MauseMoveFeatures mmf = new MauseMoveFeatures();
		mmf.clear();
		mmf.prepareVector(moves, click);
		Assert.assertEquals(101.6105146329747, mmf.getClickToClickDistanceToTotalPathLengthRatio().get(1),0.5);
	}
	
	@Test
	public void calculateMauseSpeed(){
		MauseMoveFeatures mmf = new MauseMoveFeatures();
		mmf.clear();
		mmf.prepareVector(moves, click);
		Assert.assertEquals(281.5946829, mmf.getMauseSpeed(),0.5);
	}*/
	
}
