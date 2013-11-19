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
package de.uniwue.dmir.heatmap.core.util;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.FactoryBean;

import de.uniwue.dmir.heatmap.core.processors.visualizers.color.ImageColorScheme;

@AllArgsConstructor
public class RangeFactoryBean implements FactoryBean<double[]> {

	private double min;
	private double max;
	
	private int colors;
	
	public RangeFactoryBean(double min, double max, BufferedImage image) {
		this(min, max, image.getHeight());
	}
	
	@Override
	public double[] getObject() throws Exception {
		return ImageColorScheme.ranges(this.min, this.max, this.colors);
	}

	@Override
	public Class<?> getObjectType() {
		return double[].class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
