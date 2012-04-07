/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da Licença Pública Geral GNU como publicada pela Fundação
 * do Software Livre (FSF); na versão 2 da Licença.
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/GPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse o Portal do Software Público
 * Brasileiro no endereço www.softwarepublico.gov.br ou escreva para a Fundação do Software
 * Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package contactlist.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import contactlist.view.ContactView;

@SuppressWarnings("serial")
public class ContactFrame extends JFrame {

	@Inject
	private ContactView pmod;

	@Inject
	private ResourceBundle bundle;

	private JTable contactList;
	private JLabel nameLabel;
	private JLabel cpfLabel;
	private JTextField name;
	private JTextField cpf;

	private JButton buttonNew;
	private JButton buttonSave;
	private JButton buttonDelete;
	private JButton buttonClose;

	public void loadFromPmod() {
		setTitle(pmod.getTitle());
		contactList.setModel(pmod.getContactList());
		name.setText(pmod.getName());
		cpf.setText(pmod.getCpf());
		buttonSave.setEnabled(pmod.isSaveEnabled());
		buttonDelete.setEnabled(pmod.isDeleteEnabled());
	}

	public void saveToPmod() {
		pmod.setName(name.getText());
		pmod.setCpf(cpf.getText());
	}

	@PostConstruct
	public void init() {
		initComponents();
		loadFromPmod();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setSize(new Dimension(600, 400));

		contactList = new JTable();
		contactList.setPreferredScrollableViewportSize(new Dimension(600, 280));
		contactList.setFillsViewportHeight(true);
		contactList.setShowVerticalLines(false);
		contactList.setShowHorizontalLines(true);
		contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactList.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				pmod.setSelectedContact(contactList.rowAtPoint(e.getPoint()));
				loadFromPmod();
			}
		});
		
		JScrollPane tablePanel = new JScrollPane(contactList);
		tablePanel.setOpaque(true);
	    this.add(tablePanel, BorderLayout.NORTH);
//		this.add(contactList, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		MigLayout layout = new MigLayout();
		panel.setLayout(layout);
		name = new JTextField();
		name.setPreferredSize(new Dimension(580, 20));
		nameLabel = new JLabel("Nome:");
		panel.add(nameLabel);
		panel.add(name, "wrap");

		cpf = new JTextField();
		cpf.setPreferredSize(new Dimension(580, 20));
		cpfLabel = new JLabel("CPF:");
		panel.add(cpfLabel);
		panel.add(cpf, "wrap");

		KeyListener listener = new TextChangedListener();
		name.addKeyListener(listener);
		cpf.addKeyListener(listener);

		buttonNew = new JButton("New");
		buttonSave = new JButton("Save");
		buttonDelete = new JButton("Delete");
		buttonClose = new JButton("Close");
		
		panel.add(buttonNew);
		panel.add(buttonSave);
		panel.add(buttonDelete);
		panel.add(buttonClose);

		buttonNew.setMnemonic('N');
		buttonNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				name.setText("");
				cpf.setText("");
				name.requestFocus();
			}
		});
		
		buttonSave.setMnemonic('S');
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveToPmod();
				pmod.save();
				if (pmod.hasError()) {
					JOptionPane.showMessageDialog(ContactFrame.this,
							bundle.getString("message-from-exceptionhandler", pmod.getErrorMessage()));
				}
				loadFromPmod();
			}
		});

		buttonDelete.setMnemonic('D');
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveToPmod();
				pmod.delete();
				loadFromPmod();
			}
		});

		buttonClose.setMnemonic('C');
		buttonClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});

		add(panel, BorderLayout.SOUTH);
	}

	class TextChangedListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			pmod.contactChanged();
			buttonSave.setEnabled(pmod.isSaveEnabled());
			buttonDelete.setEnabled(pmod.isDeleteEnabled());
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	}
	
}
