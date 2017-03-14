package br.com.zup.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zup.domain.entity.Coordinate;
import br.com.zup.repositories.CoordinateRepository;

@Service
public class CoordinateService {

	private static final Logger logger = Logger.getLogger(CoordinateService.class);

	@Autowired
	private CoordinateRepository coordinateRepository;

	public List<Coordinate> calculateDistanceListPOI(Double x, Double y, Double range) throws Exception {

		List<Coordinate> listPOI = new ArrayList<Coordinate>();
		try {
			List<Coordinate> listAllCoordinates = coordinateRepository.findAll();

			if (listAllCoordinates != null && !listAllCoordinates.isEmpty()) {

				for (Coordinate poi : listAllCoordinates) {

					Double distance = Math.sqrt((Math.pow(x - poi.getX(), 2) + (Math.pow((y - poi.getY()), 2))));
					if (distance <= range) {
						listPOI.add(poi);
					}
				}

			}
		} catch (Exception e) {
			logger.error("Erro ao buscar as pois" + e.getMessage(), e);
			throw new Exception(e);
		}

		return listPOI;

	}

	public Coordinate findById(Long id) {
		return coordinateRepository.findOne(id);
	}

	public void save(Coordinate coordinate) throws Exception {
		try {
			coordinateRepository.save(coordinate);
		} catch (Exception e) {
			logger.error("Erro ao salvar o ponto de interesse" + e.getMessage(), e);
			throw new Exception(e);
		}
	}

	public void delete(Long id) throws Exception {
		try {
			coordinateRepository.delete(id);
		} catch (Exception e) {
			logger.error("Erro ao deletar o ponto de interesse" + e.getMessage(), e);
			throw new Exception(e);
		}
	}

	public Page<Coordinate> findAll(Pageable pageable) throws Exception {
		try {
			return coordinateRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Erro ao buscar os pontos de interesse" + e.getMessage(), e);
			throw new Exception(e);
		}
	}

	public List<Coordinate> findCoordinates(Coordinate coordinate) throws Exception {
		try {
			List<Coordinate> listPOIbyFilter = new ArrayList<Coordinate>();

			listPOIbyFilter = calculateDistanceListPOI(coordinate.getX(), coordinate.getY(), coordinate.getRange());

			return listPOIbyFilter;
		} catch (Exception e) {
			logger.error("Erro ao buscar os pontos de interesse por coordenada" + e.getMessage(), e);
			throw new Exception(e);
		}
	}
}
