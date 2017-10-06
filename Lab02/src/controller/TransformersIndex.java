/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package controller;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

/**
 * <p>Title: TransformersIndex</p>
 * <p>Description: Class that contains and returns the appropriate transformer when requested by an AbstractTransformer class instance or a child instance.</p>
 * <p>Copyright: Copyright (c) 2003 Mohammed Elghaouat, Eric Paquette</p>
 * <p>Company: (�TS) - �cole de Technologie Sup�rieure</p>
 * @author unascribed
 * @version $Revision: 1.19 $
 */
@SuppressWarnings("LossyEncoding")
public class TransformersIndex extends AbstractTransformer {
	private Selector theSelector;
	private RectangularRegionSelector theRectangularSelector;
	private ImageLineFiller theFiller;
	private FilteringTransformer theFilter;
	private Curves theCurves;
	private Translation theTranslation;
	private Scaling theScaling;
	private Shearing theShearing;
	private Rotation theRotation;
	private Deletion theDeletion;
	
	private AbstractTransformer TransformersArray[][];
	
	private AbstractTransformer theDefaultTransformer;;
	/**
	 * Default constructor
	 */
	public TransformersIndex() {
		int MaxEvtIdx;
		/*
		MaxEvtIdx = Math.max(MouseEvent.MOUSE_PRESSED,
							 Math.max(MouseEvent.MOUSE_DRAGGED,
									  MouseEvent.MOUSE_RELEASED));
		*/
		MaxEvtIdx = Math.max(Math.max(MouseEvent.MOUSE_PRESSED ,MouseEvent.MOUSE_DRAGGED),
							 Math.max(MouseEvent.MOUSE_RELEASED,KeyEvent.KEY_TYPED));
											  
		TransformersArray = new AbstractTransformer[idTransformersIndex][MaxEvtIdx+1];
		theSelector = new Selector();
		theRectangularSelector = new RectangularRegionSelector();
		theFiller = new ImageLineFiller();
		theFilter = new FilteringTransformer();
		theCurves = new Curves();
		theTranslation = new Translation();
		theScaling = new Scaling();
		theShearing = new Shearing();
		theRotation = new Rotation();
		theDeletion = new Deletion();
		
		theDefaultTransformer = theSelector;
		TransformersArray[idSelector][MouseEvent.MOUSE_DRAGGED] = theRectangularSelector;
		TransformersArray[idRectangularRegionSelector][MouseEvent.MOUSE_DRAGGED] = theTranslation;
		TransformersArray[idRectangularRegionSelector][MouseEvent.MOUSE_PRESSED] = theSelector;
		
		TransformersArray[ID_DELETE][KeyEvent.KEY_TYPED] = theDeletion;
		
		//translating
		TransformersArray[ID_TRANSLATE][MouseEvent.MOUSE_CLICKED] = theSelector;
		TransformersArray[ID_TRANSLATE][MouseEvent.MOUSE_RELEASED] = theSelector;
		TransformersArray[ID_TRANSLATE][MouseEvent.MOUSE_PRESSED] = theSelector;
		
		//scaling
		TransformersArray[ID_SCALE][MouseEvent.MOUSE_DRAGGED] = theScaling;
		TransformersArray[ID_SCALE][MouseEvent.MOUSE_CLICKED] = theSelector;
		TransformersArray[ID_SCALE][MouseEvent.MOUSE_RELEASED] = theSelector;
		
		//rotating
		TransformersArray[ID_ROTATE][MouseEvent.MOUSE_DRAGGED] = theRotation;
		TransformersArray[ID_ROTATE][MouseEvent.MOUSE_CLICKED] = theSelector;
		TransformersArray[ID_ROTATE][MouseEvent.MOUSE_RELEASED] = theSelector;
		
		//shearing
		TransformersArray[ID_SHEAR][MouseEvent.MOUSE_DRAGGED] = theShearing;
		TransformersArray[ID_SHEAR][MouseEvent.MOUSE_CLICKED] = theSelector;
		TransformersArray[ID_SHEAR][MouseEvent.MOUSE_RELEASED] = theSelector;
		
	}
        
	/**
	 * Transformer ID getter
	 */
	public int getID(){
		return idTransformersIndex;
	}

	/**
	 * Returns the appropriate transformer from an AbstractTransformer request
	 * @param aEvent mouse event generated by the system
	 * @param aTransformer AbstractTransformer instance that generated the event
	 * @throws TransformerUnfound
	 */
	public AbstractTransformer getTheAppropriateTransformer(InputEvent aEvent,
		AbstractTransformer aTransformer){
		return TransformersArray[aTransformer.getID()][aEvent.getID()];
	}

	/**
	 * Returns the default transformer
	 * @return default transformer instance (the selector)
	 */
	public AbstractTransformer getTheDefaultTransformer(){
		return theDefaultTransformer;
	}
	
	/**
	 * 
	 * @param at
	 */
	public void setTheDefaultTransformer(AbstractTransformer at){
		theDefaultTransformer = at;
		at.activate();
	}
	
	/**
	 * @return
	 */
	public ImageLineFiller getTheFiller() {
		return theFiller;
	}
	
	/**
	 * 
	 * @return
	 */
	public Selector getTheSelector() {	
		return theSelector;
	}
	
	/**
	 * 
	 * @return
	 */
	public FilteringTransformer getTheFilter() {	
		return theFilter;
	}
	
	public Curves getTheCurves() {	
		return theCurves;
	}
	
	public Translation getTheTranslation() {	
		return theTranslation;
	}
	
	public Scaling getTheScale() {	
		return theScaling;
	}
	
	public Shearing getTheShear() {	
		return theShearing;
	}
	
	public Rotation getTheRotation() {	
		return theRotation;
	}
	
	public Deletion getTheDeletion() {	
		return theDeletion;
	}
}