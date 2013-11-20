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
package de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.aggregators;

import lombok.AllArgsConstructor;
import de.uniwue.dmir.heatmap.core.filters.operators.IMapper;
import de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.IDistanceFunction;
import de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.IRadialBasisFunction;
import de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.ReferencedData;
import de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.distances.EuclidianDistance;
import de.uniwue.dmir.heatmap.core.processors.visualizers.rbf.rbfs.GaussianRbf;
import de.uniwue.dmir.heatmap.core.tiles.coordinates.RelativeCoordinates;
import de.uniwue.dmir.heatmap.core.util.IAggregator;

@AllArgsConstructor
public abstract class AbstractGenericRbfAggregator<TData>
implements IAggregator<ReferencedData<TData>, Double> {

	private IMapper<TData, Double> pixelToValueMapper;
	private IDistanceFunction<RelativeCoordinates> distanceFunction;
	private IRadialBasisFunction radialBasisFunction;
	
	public AbstractGenericRbfAggregator(
			IMapper<TData, Double> pixelToValueMapper,
			double pointRadius) {
		
		this.pixelToValueMapper = pixelToValueMapper;
		this.distanceFunction = new EuclidianDistance();
		this.radialBasisFunction = new GaussianRbf(pointRadius);
	}
	
	@Override
	public void addData(ReferencedData<TData> dataContainer) {
		double distance = this.distanceFunction.distance(
				dataContainer.getReferenceCoordaintes(), 
				dataContainer.getDataCoordinates());
		double distanceWeight = this.radialBasisFunction.value(distance);
		double value = this.pixelToValueMapper.map(dataContainer.getData());
		this.addData(value, distanceWeight);
	}
	
	protected abstract void addData(double value, double distanceWeight);
}