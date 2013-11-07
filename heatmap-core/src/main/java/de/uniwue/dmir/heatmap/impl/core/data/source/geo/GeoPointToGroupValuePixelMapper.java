/**
 * Heatmap Framework - Core
 *
 * Copyright (C) 2013	Martin Becker
 * 						becker@informatik.uni-wuerzburg.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package de.uniwue.dmir.heatmap.impl.core.data.source.geo;

import lombok.AllArgsConstructor;
import de.uniwue.dmir.heatmap.core.data.type.IToInternalDataMapper;
import de.uniwue.dmir.heatmap.core.filter.operators.IMapper;
import de.uniwue.dmir.heatmap.core.tile.coordinates.RelativeCoordinates;
import de.uniwue.dmir.heatmap.impl.core.data.type.external.GroupValuePixel;

@AllArgsConstructor
public class GeoPointToGroupValuePixelMapper<TGroupDescription>
implements IToInternalDataMapper<GeoPoint<TGroupDescription>, GroupValuePixel> {

	private IMapper<TGroupDescription, String> descriptionToIdMapper;
	
	public GroupValuePixel map(
			GeoPoint<TGroupDescription> sourceObject,
			RelativeCoordinates relativeCoordinates) {
		
		return new GroupValuePixel(
				relativeCoordinates.getX(), 
				relativeCoordinates.getY(), 
				sourceObject.getValue(),
				sourceObject.getTimestamp(),
				this.descriptionToIdMapper.map(
						sourceObject.getGroupDescription()));
	}
	
}