package com.thiagomv.spritecompiler.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import com.thiagomv.spritecompiler.business.OtimizacaoBusiness;
import com.thiagomv.spritecompiler.commons.factories.BusinessFactory;
import com.thiagomv.spritecompiler.data.PontoAncora;
import com.thiagomv.spritecompiler.data.Rectangle2D;
import com.thiagomv.spritecompiler.data.Size;
import com.thiagomv.spritecompiler.data.StatusEmpacotamento;
import com.thiagomv.spritecompiler.enums.IndicadorLocalRetangulo;

public class OtimizacaoBusinessTest {
	private final OtimizacaoBusiness otimizacaoBusiness = BusinessFactory
			.getBusinessInstance(OtimizacaoBusiness.class);

	@Test
	public void areaMinimaOpenGL() {
		Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		mapa.put(1, 1);
		mapa.put(2, 2);
		mapa.put(3, 4);
		mapa.put(4, 4);
		mapa.put(5, 8);
		mapa.put(6, 8);
		mapa.put(7, 8);
		mapa.put(8, 8);
		mapa.put(9, 16);
		mapa.put(511, 512);
		mapa.put(512, 512);
		mapa.put(513, 1024);
		mapa.put(2048, 2048);
		mapa.put(4096, 4096);
		mapa.put(4096 * 4096, 4096 * 4096);
		mapa.put(4096 * 4096 + 1, 0);
		mapa.put(0, 0);
		mapa.put(-1, 0);

		for (Integer key : mapa.keySet()) {
			Assert.assertThat(otimizacaoBusiness.calcularAreaMinimaOpenGL(key),
					CoreMatchers.is(mapa.get(key)));
		}
	}

	@Test
	public void retanguloEquilibradoOpenGL() {
		Map<Integer, Rectangle2D> mapa = new HashMap<Integer, Rectangle2D>();
		mapa.put(1, new Rectangle2D(1, 1));
		mapa.put(2, new Rectangle2D(2, 1));
		mapa.put(4, new Rectangle2D(2, 2));
		mapa.put(8, new Rectangle2D(4, 2));
		mapa.put(16, new Rectangle2D(4, 4));
		mapa.put(32, new Rectangle2D(8, 4));
		mapa.put(64, new Rectangle2D(8, 8));
		mapa.put(128, new Rectangle2D(16, 8));
		mapa.put(256, new Rectangle2D(16, 16));
		mapa.put(512, new Rectangle2D(32, 16));
		mapa.put(1024, new Rectangle2D(32, 32));
		mapa.put(2048, new Rectangle2D(64, 32));
		mapa.put(4096, new Rectangle2D(64, 64));
		mapa.put(8192, new Rectangle2D(128, 64));
		mapa.put(4096 * 4096, new Rectangle2D(4096, 4096));

		for (Integer key : mapa.keySet()) {
			Assert.assertThat(
					otimizacaoBusiness.calcularRetanguloEquilibradoOpenGL(key),
					CoreMatchers.is(mapa.get(key)));
		}
	}

	@Test
	public void ordenarSizes() {
		List<Size> sizes = Arrays.asList(new Size(20, 15), new Size(12, 28),
				new Size(30, 11), new Size(14, 15), new Size(14, 11), new Size(
						128, 2), new Size(2, 44), new Size(64, 64), new Size(
						836, 12), new Size(71, 89));
		otimizacaoBusiness.ordenarSizes(sizes);

		List<Size> expected = Arrays.asList(new Size(836, 12),
				new Size(128, 2), new Size(71, 89), new Size(64, 64), new Size(
						2, 44), new Size(30, 11), new Size(12, 28), new Size(
						20, 15), new Size(14, 15), new Size(14, 11));

		Assert.assertArrayEquals(expected.toArray(), sizes.toArray());
	}

	@Test
	public void criarAncorasSemRedundancia() {
		Size size = new Size(10, 20);
		List<PontoAncora> grupo1 = Arrays.asList(new PontoAncora(0, 0,
				IndicadorLocalRetangulo.SUDOESTE), new PontoAncora(0, 19,
				IndicadorLocalRetangulo.NOROESTE), new PontoAncora(9, 19,
				IndicadorLocalRetangulo.NORDESTE));
		List<PontoAncora> grupo2 = Arrays.asList(new PontoAncora(9, 0,
				IndicadorLocalRetangulo.SUDOESTE), new PontoAncora(18, 0,
				IndicadorLocalRetangulo.SUDESTE));
		List<PontoAncora> grupo3 = Arrays.asList(new PontoAncora(20, 20,
				IndicadorLocalRetangulo.NORDESTE));

		Set<PontoAncora> ancoras = new HashSet<>();
		ancoras.addAll(grupo1);
		ancoras.addAll(grupo2);
		ancoras.addAll(grupo3);

		Set<PontoAncora> ancorasSemRedundancia = otimizacaoBusiness
				.obterAncorasSemRedundancia(ancoras, size);

		Assert.assertThat(ancorasSemRedundancia.size(), CoreMatchers.is(3));
		Assert.assertThat(ancorasSemRedundancia,
				CoreMatchers.anyOf(criarMatchersHasItem(grupo1)));
		Assert.assertThat(ancorasSemRedundancia,
				CoreMatchers.anyOf(criarMatchersHasItem(grupo2)));
		Assert.assertThat(ancorasSemRedundancia,
				CoreMatchers.anyOf(criarMatchersHasItem(grupo3)));
	}

	private Iterable<Matcher<? super Iterable<PontoAncora>>> criarMatchersHasItem(
			Iterable<PontoAncora> conjunto) {
		List<Matcher<? super Iterable<PontoAncora>>> matchers = new ArrayList<>();

		for (PontoAncora ancora : conjunto) {
			matchers.add(CoreMatchers.hasItem(ancora));
		}

		return matchers;
	}

	@Test
	public void criarAncoras() {
		Rectangle2D recipiente = new Rectangle2D(0, 0, 99, 49);
		List<Rectangle2D> regioesOcupadas = Arrays.asList(new Rectangle2D(0, 0,
				20, 15), new Rectangle2D(21, 0, 25, 17), new Rectangle2D(99,
				20, 99, 30));
		Rectangle2D region = new Rectangle2D(0, 18, 98, 40);
		Set<PontoAncora> expected = new HashSet<>(Arrays.asList(
				new PontoAncora(20, 17, IndicadorLocalRetangulo.NORDESTE),
				new PontoAncora(0, 17, IndicadorLocalRetangulo.NOROESTE),
				new PontoAncora(0, 41, IndicadorLocalRetangulo.SUDOESTE),
				new PontoAncora(99, 31, IndicadorLocalRetangulo.SUDOESTE),
				new PontoAncora(99, 19, IndicadorLocalRetangulo.NOROESTE),
				new PontoAncora(26, 17, IndicadorLocalRetangulo.NOROESTE)));

		Set<PontoAncora> ancoras = otimizacaoBusiness.criarAncoras(recipiente,
				regioesOcupadas, region);

		Assert.assertThat(ancoras.size(), CoreMatchers.equalTo(expected.size()));
		Assert.assertThat(ancoras,
				CoreMatchers.allOf(criarMatchersHasItem(expected)));
	}

	@Test
	public void eliminarAncorasInvalidas() {
		Rectangle2D recipiente = new Rectangle2D(0, 0, 99, 49);
		List<Rectangle2D> regioesOcupadas = new ArrayList<>();
		Rectangle2D region = new Rectangle2D(0, 0, 10, 40);
		Set<PontoAncora> ancoras = new HashSet<>(Arrays.asList(new PontoAncora(
				0, 0, IndicadorLocalRetangulo.SUDOESTE), new PontoAncora(0, 49,
				IndicadorLocalRetangulo.NOROESTE), new PontoAncora(99, 49,
				IndicadorLocalRetangulo.NORDESTE), new PontoAncora(99, 0,
				IndicadorLocalRetangulo.SUDESTE)));

		Set<PontoAncora> novasAncoras = otimizacaoBusiness.criarAncoras(
				recipiente, regioesOcupadas, region);
		ancoras.addAll(novasAncoras);

		regioesOcupadas.add(region);
		StatusEmpacotamento status = new StatusEmpacotamento();
		status.recipiente = recipiente;
		status.regioesOcupadas = regioesOcupadas;
		status.ancoras = ancoras;
		status.pendencia = new ArrayList<>();

		otimizacaoBusiness.eliminarAncorasInvalidas(status);

		Set<PontoAncora> expected = new HashSet<>(Arrays.asList(
				new PontoAncora(0, 41, IndicadorLocalRetangulo.SUDOESTE),
				new PontoAncora(0, 49, IndicadorLocalRetangulo.NOROESTE),
				new PontoAncora(99, 49, IndicadorLocalRetangulo.NORDESTE),
				new PontoAncora(99, 0, IndicadorLocalRetangulo.SUDESTE),
				new PontoAncora(11, 0, IndicadorLocalRetangulo.SUDOESTE)));

		Assert.assertThat(status.ancoras.size(),
				CoreMatchers.equalTo(expected.size()));
		Assert.assertThat(status.ancoras,
				CoreMatchers.allOf(criarMatchersHasItem(expected)));
	}
}
