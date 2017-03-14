package br.com.zup.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.zup.domain.entity.Coordinate;
import br.com.zup.repositories.CoordinateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryConfiguration.class })
public class CoordinateRepositoryTest {

	@Autowired
	private CoordinateRepository coordinateRepository;

	@Test
	public void testSaveCoordinate() {
		// setup coordinate
		Coordinate coordinate = new Coordinate();
		coordinate.setPoiName("Hello World");
		coordinate.setX(new Double("18.95"));
		coordinate.setY(new Double("17.95"));

		// save coordinate, verify has ID value after save
		assertNull(coordinate.getId()); // null before save
		coordinateRepository.save(coordinate);
		assertNotNull(coordinate.getId()); // not null after save

		// fetch from DB
		Coordinate fetchedCoordinate = coordinateRepository.findOne(coordinate.getId());

		// should not be null
		assertNotNull(fetchedCoordinate);

		// should equal
		assertEquals(coordinate.getId(), fetchedCoordinate.getId());
		assertEquals(coordinate.getPoiName(), fetchedCoordinate.getPoiName());

		// update description and save
		fetchedCoordinate.setPoiName("Hello World");
		coordinateRepository.save(fetchedCoordinate);

		// get from DB, should be updated
		Coordinate fetchedUpdatedCoordinate = coordinateRepository.findOne(fetchedCoordinate.getId());
		assertEquals(fetchedCoordinate.getPoiName(), fetchedUpdatedCoordinate.getPoiName());

		// verify count of coordinates in DB
		long coordinateCount = coordinateRepository.count();
		assertEquals(coordinateCount, 1);

		// get all coordinates, list should only have one
		Iterable<Coordinate> coordinates = coordinateRepository.findAll();

		int count = 0;

		for (Coordinate p : coordinates) {
			count++;
		}

		assertEquals(count, 1);
	}
 }
